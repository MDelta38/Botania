/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileMunchdew
extends SubTileGenerating {
    private static final String TAG_COOLDOWN = "cooldown";
    private static final String TAG_ATE_ONCE = "ateOnce";
    private static final int RANGE = 8;
    private static final int RANGE_Y = 16;
    boolean ateOnce = false;
    int ticksWithoutEating = -1;
    int cooldown = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.cooldown > 0) {
            --this.cooldown;
            this.ticksWithoutEating = 0;
            return;
        }
        int manaPerLeaf = 160;
        if (this.getMaxMana() - this.mana >= manaPerLeaf && !this.supertile.func_145831_w().field_72995_K && this.ticksExisted % 4 == 0) {
            ArrayList<ChunkCoordinates> coords = new ArrayList<ChunkCoordinates>();
            int x = this.supertile.field_145851_c;
            int y = this.supertile.field_145848_d;
            int z = this.supertile.field_145849_e;
            for (int i = -8; i < 9; ++i) {
                for (int j = 0; j < 16; ++j) {
                    for (int k = -8; k < 9; ++k) {
                        int xp = x + i;
                        int yp = y + j;
                        int zp = z + k;
                        Block block = this.supertile.func_145831_w().func_147439_a(xp, yp, zp);
                        if (block.func_149688_o() != Material.field_151584_j) continue;
                        boolean exposed = false;
                        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                            if (!this.supertile.func_145831_w().func_147439_a(xp + dir.offsetX, yp + dir.offsetY, zp + dir.offsetZ).isAir((IBlockAccess)this.supertile.func_145831_w(), xp + dir.offsetX, yp + dir.offsetY, zp + dir.offsetZ)) continue;
                            exposed = true;
                            break;
                        }
                        if (!exposed) continue;
                        coords.add(new ChunkCoordinates(xp, yp, zp));
                    }
                }
            }
            if (!coords.isEmpty()) {
                Collections.shuffle(coords);
                ChunkCoordinates breakCoords = (ChunkCoordinates)coords.get(0);
                Block block = this.supertile.func_145831_w().func_147439_a(breakCoords.field_71574_a, breakCoords.field_71572_b, breakCoords.field_71573_c);
                int meta = this.supertile.func_145831_w().func_72805_g(breakCoords.field_71574_a, breakCoords.field_71572_b, breakCoords.field_71573_c);
                this.supertile.func_145831_w().func_147468_f(breakCoords.field_71574_a, breakCoords.field_71572_b, breakCoords.field_71573_c);
                this.ticksWithoutEating = 0;
                this.ateOnce = true;
                if (ConfigHandler.blockBreakParticles) {
                    this.supertile.func_145831_w().func_72926_e(2001, breakCoords.field_71574_a, breakCoords.field_71572_b, breakCoords.field_71573_c, Block.func_149682_b((Block)block) + (meta << 12));
                }
                this.mana += manaPerLeaf;
            }
        }
        if (this.ateOnce) {
            ++this.ticksWithoutEating;
            if (this.ticksWithoutEating >= 5) {
                this.cooldown = 1600;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 8);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_COOLDOWN, this.cooldown);
        cmp.func_74757_a(TAG_ATE_ONCE, this.ateOnce);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.cooldown = cmp.func_74762_e(TAG_COOLDOWN);
        this.ateOnce = cmp.func_74767_n(TAG_ATE_ONCE);
    }

    @Override
    public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
        ArrayList<ItemStack> drops = super.getDrops(list);
        if (this.cooldown > 0) {
            ItemNBTHelper.setInt(drops.get(0), TAG_COOLDOWN, this.cooldown);
        }
        return drops;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        this.cooldown = ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
    }

    @Override
    public int getColor() {
        return 7980079;
    }

    @Override
    public int getMaxMana() {
        return 10000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.munchdew;
    }
}

