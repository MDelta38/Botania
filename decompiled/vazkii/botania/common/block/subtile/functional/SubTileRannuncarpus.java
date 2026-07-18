/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemRedstone
 *  net.minecraft.item.ItemReed
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemRedstone;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IFlowerPlaceable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.decor.IFloatingFlower;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;

public class SubTileRannuncarpus
extends SubTileFunctional {
    private static final int RANGE = 2;
    private static final int RANGE_Y = 3;
    private static final int RANGE_PLACE_MANA = 8;
    private static final int RANGE_PLACE = 6;
    private static final int RANGE_PLACE_Y = 6;
    private static final int RANGE_PLACE_MANA_MINI = 3;
    private static final int RANGE_PLACE_MINI = 2;
    private static final int RANGE_PLACE_Y_MINI = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        if (this.ticksExisted % 10 == 0) {
            BlockData filter = this.getUnderlyingBlock();
            boolean scanned = false;
            ArrayList<ChunkCoordinates> validPositions = new ArrayList<ChunkCoordinates>();
            int rangePlace = this.getRange();
            int rangePlaceY = this.getRangeY();
            int x = this.supertile.field_145851_c;
            int y = this.supertile.field_145848_d;
            int z = this.supertile.field_145849_e;
            List items = this.supertile.func_145831_w().func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(x - 2), (double)(y - 3), (double)(z - 2), (double)(x + 2 + 1), (double)(y + 3), (double)(z + 2 + 1)));
            int slowdown = this.getSlowdownFactor();
            for (EntityItem item : items) {
                ItemStack stack;
                Item stackItem;
                if (item.field_70292_b < 60 + slowdown || item.field_70128_L || !((stackItem = (stack = item.func_92059_d()).func_77973_b()) instanceof ItemBlock) && !(stackItem instanceof ItemReed) && !(stackItem instanceof ItemRedstone) && !(stackItem instanceof IFlowerPlaceable)) continue;
                if (!scanned) {
                    for (int i = -rangePlace; i < rangePlace + 1; ++i) {
                        for (int j = -rangePlaceY; j < rangePlaceY + 1; ++j) {
                            for (int l = -rangePlace; l < rangePlace + 1; ++l) {
                                int xp = x + i;
                                int yp = y + j;
                                int zp = z + l;
                                Block blockAbove = this.supertile.func_145831_w().func_147439_a(xp, yp + 1, zp);
                                if (!filter.equals(this.supertile.func_145831_w(), xp, yp, zp) || !blockAbove.isAir((IBlockAccess)this.supertile.func_145831_w(), xp, yp + 1, zp) && !blockAbove.isReplaceable((IBlockAccess)this.supertile.func_145831_w(), xp, yp + 1, zp)) continue;
                                validPositions.add(new ChunkCoordinates(xp, yp + 1, zp));
                            }
                        }
                    }
                    scanned = true;
                }
                if (validPositions.isEmpty() || this.supertile.func_145831_w().field_72995_K) continue;
                ChunkCoordinates coords = (ChunkCoordinates)validPositions.get(this.supertile.func_145831_w().field_73012_v.nextInt(validPositions.size()));
                Block blockToPlace = null;
                if (stackItem instanceof IFlowerPlaceable) {
                    blockToPlace = ((IFlowerPlaceable)stackItem).getBlockToPlaceByFlower(stack, this, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                }
                if (stackItem instanceof ItemBlock) {
                    blockToPlace = ((ItemBlock)stackItem).field_150939_a;
                } else if (stackItem instanceof ItemReed) {
                    blockToPlace = (Block)ReflectionHelper.getPrivateValue(ItemReed.class, (Object)((ItemReed)stackItem), (String[])LibObfuscation.REED_ITEM);
                } else if (stackItem instanceof ItemRedstone) {
                    blockToPlace = Blocks.field_150488_af;
                }
                if (blockToPlace == null || !blockToPlace.func_149742_c(this.supertile.func_145831_w(), coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) continue;
                this.supertile.func_145831_w().func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, blockToPlace, stack.func_77960_j(), 3);
                if (ConfigHandler.blockBreakParticles) {
                    this.supertile.func_145831_w().func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)blockToPlace) + (stack.func_77960_j() << 12));
                }
                validPositions.remove(coords);
                TileEntity tile = this.supertile.func_145831_w().func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                if (tile != null && tile instanceof ISubTileContainer) {
                    ISubTileContainer container = (ISubTileContainer)tile;
                    String subtileName = ItemBlockSpecialFlower.getType(stack);
                    container.setSubTile(subtileName);
                    SubTileEntity subtile = container.getSubTile();
                    subtile.onBlockPlacedBy(this.supertile.func_145831_w(), coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, null, stack);
                }
                if (stackItem instanceof IFlowerPlaceable) {
                    ((IFlowerPlaceable)stackItem).onBlockPlacedByFlower(stack, this, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                }
                if (!this.supertile.func_145831_w().field_72995_K) {
                    --stack.field_77994_a;
                    if (stack.field_77994_a == 0) {
                        item.func_70106_y();
                    }
                }
                if (this.mana > 1) {
                    --this.mana;
                }
                return;
            }
        }
    }

    public BlockData getUnderlyingBlock() {
        return new BlockData(this.supertile.func_145831_w(), this.supertile.field_145851_c, this.supertile.field_145848_d - (this.supertile instanceof IFloatingFlower ? 1 : 2), this.supertile.field_145849_e);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        BlockData filter = this.getUnderlyingBlock();
        ItemStack recieverStack = new ItemStack(Item.func_150898_a((Block)filter.block), 1, filter.meta);
        int color = this.getColor();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (recieverStack != null && recieverStack.func_77973_b() != null) {
            String stackName = recieverStack.func_82833_r();
            int width = 16 + mc.field_71466_p.func_78256_a(stackName) / 2;
            int x = res.func_78326_a() / 2 - width;
            int y = res.func_78328_b() / 2 + 30;
            mc.field_71466_p.func_78261_a(stackName, x + 20, y + 5, color);
            RenderHelper.func_74520_c();
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, recieverStack, x, y);
            RenderHelper.func_74518_a();
        }
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    public int getRange() {
        return this.mana > 0 ? 8 : 6;
    }

    public int getRangeY() {
        return 6;
    }

    @Override
    public int getMaxMana() {
        return 20;
    }

    @Override
    public int getColor() {
        return 16757375;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.rannuncarpus;
    }

    static class BlockData {
        Block block;
        int meta;

        public BlockData(World world, int x, int y, int z) {
            this.block = world.func_147439_a(x, y, z);
            this.meta = world.func_72805_g(x, y, z);
        }

        public boolean equals(BlockData data) {
            return this.block == data.block && this.meta == data.meta;
        }

        public boolean equals(World world, int x, int y, int z) {
            return this.equals(new BlockData(world, x, y, z));
        }
    }

    public static class Mini
    extends SubTileRannuncarpus {
        @Override
        public int getRange() {
            return this.mana > 0 ? 3 : 2;
        }

        @Override
        public int getRangeY() {
            return 2;
        }
    }
}

