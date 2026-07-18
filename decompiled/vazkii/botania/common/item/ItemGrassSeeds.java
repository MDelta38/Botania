/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$WorldTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.decor.IFloatingFlower;
import vazkii.botania.common.item.IFloatingFlowerVariant;
import vazkii.botania.common.item.ItemMod;

public class ItemGrassSeeds
extends ItemMod
implements IFloatingFlowerVariant {
    private static Map<Integer, Set<BlockSwapper>> blockSwappers = new HashMap<Integer, Set<BlockSwapper>>();
    private static final IFloatingFlower.IslandType[] ISLAND_TYPES = new IFloatingFlower.IslandType[]{IFloatingFlower.IslandType.GRASS, IFloatingFlower.IslandType.PODZOL, IFloatingFlower.IslandType.MYCEL, IFloatingFlower.IslandType.DRY, IFloatingFlower.IslandType.GOLDEN, IFloatingFlower.IslandType.VIVID, IFloatingFlower.IslandType.SCORCHED, IFloatingFlower.IslandType.INFUSED, IFloatingFlower.IslandType.MUTATED};
    private static final int SUBTYPES = 9;
    IIcon[] icons;

    public ItemGrassSeeds() {
        this.func_77655_b("grassSeeds");
        this.func_77627_a(true);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public void func_150895_a(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 9; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[9];
        for (int i = 0; i < 9; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(this.icons.length - 1, par1)];
    }

    public String func_77667_c(ItemStack stack) {
        return super.func_77658_a() + stack.func_77960_j();
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = par3World.func_147439_a(par4, par5, par6);
        int bmeta = par3World.func_72805_g(par4, par5, par6);
        if ((block == Blocks.field_150346_d || block == Blocks.field_150349_c && par1ItemStack.func_77960_j() != 0) && bmeta == 0) {
            int meta = par1ItemStack.func_77960_j();
            BlockSwapper swapper = ItemGrassSeeds.addBlockSwapper(par3World, par4, par5, par6, meta);
            par3World.func_147465_d(par4, par5, par6, swapper.blockToSet, swapper.metaToSet, 3);
            for (int i = 0; i < 50; ++i) {
                double x = (Math.random() - 0.5) * 3.0;
                double y = Math.random() - 0.5 + 1.0;
                double z = (Math.random() - 0.5) * 3.0;
                float r = 0.0f;
                float g = 0.4f;
                float b = 0.0f;
                switch (meta) {
                    case 1: {
                        r = 0.5f;
                        g = 0.37f;
                        b = 0.0f;
                        break;
                    }
                    case 2: {
                        r = 0.27f;
                        g = 0.0f;
                        b = 0.33f;
                        break;
                    }
                    case 3: {
                        r = 0.4f;
                        g = 0.5f;
                        b = 0.05f;
                        break;
                    }
                    case 4: {
                        r = 0.75f;
                        g = 0.7f;
                        b = 0.0f;
                        break;
                    }
                    case 5: {
                        r = 0.0f;
                        g = 0.5f;
                        b = 0.1f;
                        break;
                    }
                    case 6: {
                        r = 0.75f;
                        g = 0.0f;
                        b = 0.0f;
                        break;
                    }
                    case 7: {
                        r = 0.0f;
                        g = 0.55f;
                        b = 0.55f;
                        break;
                    }
                    case 8: {
                        r = 0.4f;
                        g = 0.1f;
                        b = 0.4f;
                    }
                }
                float velMul = 0.025f;
                Botania.proxy.wispFX(par3World, (double)par4 + 0.5 + x, (double)par5 + 0.5 + y, (double)par6 + 0.5 + z, r, g, b, (float)Math.random() * 0.15f + 0.15f, (float)(-x) * velMul, (float)(-y) * velMul, (float)(-z) * velMul);
            }
            --par1ItemStack.field_77994_a;
        }
        return true;
    }

    @SubscribeEvent
    public void onTickEnd(TickEvent.WorldTickEvent event) {
        int dim;
        if (event.world.field_72995_K) {
            return;
        }
        if (event.phase == TickEvent.Phase.END && blockSwappers.containsKey(dim = event.world.field_73011_w.field_76574_g)) {
            Set<BlockSwapper> swappers = blockSwappers.get(dim);
            Iterator<BlockSwapper> iter = swappers.iterator();
            while (iter.hasNext()) {
                BlockSwapper next = iter.next();
                if (next != null && next.tick()) continue;
                iter.remove();
            }
        }
    }

    private static BlockSwapper addBlockSwapper(World world, int x, int y, int z, int meta) {
        BlockSwapper swapper = ItemGrassSeeds.swapperFromMeta(world, x, y, z, meta);
        if (world.field_72995_K) {
            return swapper;
        }
        int dim = world.field_73011_w.field_76574_g;
        if (!blockSwappers.containsKey(dim)) {
            blockSwappers.put(dim, new HashSet());
        }
        blockSwappers.get(dim).add(swapper);
        return swapper;
    }

    private static BlockSwapper swapperFromMeta(World world, int x, int y, int z, int meta) {
        switch (meta) {
            case 1: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), Blocks.field_150346_d, 2);
            }
            case 2: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), (Block)Blocks.field_150391_bh, 0);
            }
            case 3: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 0);
            }
            case 4: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 1);
            }
            case 5: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 2);
            }
            case 6: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 3);
            }
            case 7: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 4);
            }
            case 8: {
                return new BlockSwapper(world, new ChunkCoordinates(x, y, z), ModBlocks.altGrass, 5);
            }
        }
        return new BlockSwapper(world, new ChunkCoordinates(x, y, z), (Block)Blocks.field_150349_c, 0);
    }

    @Override
    public IFloatingFlower.IslandType getIslandType(ItemStack stack) {
        return ISLAND_TYPES[Math.min(stack.func_77960_j(), ISLAND_TYPES.length - 1)];
    }

    private static class BlockSwapper {
        public static final int RANGE = 3;
        public static final int TICK_RANGE = 1;
        private final World world;
        private final Random rand;
        private final Block blockToSet;
        private final int metaToSet;
        private ChunkCoordinates startCoords;
        private int ticksExisted = 0;

        public BlockSwapper(World world, ChunkCoordinates coords, Block block, int meta) {
            this.world = world;
            this.blockToSet = block;
            this.metaToSet = meta;
            this.rand = new Random(coords.field_71574_a ^ coords.field_71572_b ^ coords.field_71573_c);
            this.startCoords = coords;
        }

        public boolean tick() {
            ++this.ticksExisted;
            for (int i = -3; i <= 3; ++i) {
                for (int j = -3; j <= 3; ++j) {
                    int x = this.startCoords.field_71574_a + i;
                    int y = this.startCoords.field_71572_b;
                    int z = this.startCoords.field_71573_c + j;
                    Block block = this.world.func_147439_a(x, y, z);
                    int meta = this.world.func_72805_g(x, y, z);
                    if (block != this.blockToSet || meta != this.metaToSet || this.ticksExisted % 20 != 0) continue;
                    this.tickBlock(x, y, z);
                }
            }
            return this.ticksExisted < 80;
        }

        public void tickBlock(int x, int y, int z) {
            ArrayList<ChunkCoordinates> validCoords = new ArrayList<ChunkCoordinates>();
            for (int xOffset = -1; xOffset <= 1; ++xOffset) {
                for (int zOffset = -1; zOffset <= 1; ++zOffset) {
                    if (xOffset == 0 && zOffset == 0 || !this.isValidSwapPosition(x + xOffset, y, z + zOffset)) continue;
                    validCoords.add(new ChunkCoordinates(x + xOffset, y, z + zOffset));
                }
            }
            if (!validCoords.isEmpty() && !this.world.field_72995_K) {
                ChunkCoordinates toSwap = (ChunkCoordinates)validCoords.get(this.rand.nextInt(validCoords.size()));
                this.world.func_147465_d(toSwap.field_71574_a, toSwap.field_71572_b, toSwap.field_71573_c, this.blockToSet, this.metaToSet, 3);
            }
        }

        public boolean isValidSwapPosition(int x, int y, int z) {
            Block block = this.world.func_147439_a(x, y, z);
            int meta = this.world.func_72805_g(x, y, z);
            Block aboveBlock = this.world.func_147439_a(x, y + 1, z);
            return (block == Blocks.field_150346_d || block == Blocks.field_150349_c) && meta == 0 && aboveBlock.getLightOpacity((IBlockAccess)this.world, x, y, z) <= 1;
        }
    }
}

