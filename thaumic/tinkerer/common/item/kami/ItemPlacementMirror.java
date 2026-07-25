/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item.kami;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemBlockTalisman;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.tool.ToolHandler;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemPlacementMirror
extends ItemKamiBase {
    @Deprecated
    private static final String TAG_BLOCK_ID = "blockID";
    private static final String TAG_BLOCK_NAME = "blockName";
    private static final String TAG_BLOCK_META = "blockMeta";
    private static final String TAG_SIZE = "size";
    IIcon[] icons = new IIcon[2];

    public ItemPlacementMirror() {
        this.func_77625_d(1);
    }

    public static boolean hasBlocks(ItemStack stack, EntityPlayer player, ChunkCoordinates[] blocks) {
        if (player.field_71075_bZ.field_75098_d) {
            return true;
        }
        int required = blocks.length;
        int current = 0;
        ItemStack reqStack = new ItemStack(ItemPlacementMirror.getBlock(stack), 1, ItemPlacementMirror.getBlockMeta(stack));
        ArrayList<ItemStack> talismansToCheck = new ArrayList<ItemStack>();
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stackInSlot = player.field_71071_by.func_70301_a(i);
            if (stackInSlot != null && stackInSlot.func_77973_b() == reqStack.func_77973_b() && stackInSlot.func_77960_j() == reqStack.func_77960_j() && (current += stackInSlot.field_77994_a) >= required) {
                return true;
            }
            if (stackInSlot == null || stackInSlot.func_77973_b() != ThaumicTinkerer.registry.getFirstItemFromClass(ItemBlockTalisman.class)) continue;
            talismansToCheck.add(stackInSlot);
        }
        for (ItemStack talisman : talismansToCheck) {
            Block block = ItemBlockTalisman.getBlock(talisman);
            int meta = ItemBlockTalisman.getBlockMeta(talisman);
            if (Item.func_150898_a((Block)block) != reqStack.func_77973_b() || meta != reqStack.func_77960_j() || (current += ItemBlockTalisman.getBlockCount(talisman)) < required) continue;
            return true;
        }
        return false;
    }

    public static ChunkCoordinates[] getBlocksToPlace(ItemStack stack, EntityPlayer player) {
        ArrayList<ChunkCoordinates> coords = new ArrayList<ChunkCoordinates>();
        MovingObjectPosition pos = ToolHandler.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 5.0);
        if (pos != null) {
            int yOff;
            int xOff;
            boolean topOrBottom;
            Block block = player.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            if (block != null && block.isReplaceable((IBlockAccess)player.field_70170_p, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) {
                --pos.field_72312_c;
            }
            ForgeDirection dir = ForgeDirection.getOrientation((int)pos.field_72310_e);
            int rotation = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
            int range = (ItemPlacementMirror.getSize(stack) ^ 1) / 2;
            boolean bl = topOrBottom = dir == ForgeDirection.UP || dir == ForgeDirection.DOWN;
            int n = dir != ForgeDirection.WEST && dir != ForgeDirection.EAST ? (topOrBottom ? (player.field_70125_A > 75.0f || (rotation & 1) == 0 ? range : 0) : range) : (xOff = 0);
            int n2 = topOrBottom ? (player.field_70125_A > 75.0f ? 0 : range) : (yOff = range);
            int zOff = dir != ForgeDirection.SOUTH && dir != ForgeDirection.NORTH ? (topOrBottom ? (player.field_70125_A > 75.0f || (rotation & 1) == 1 ? range : 0) : range) : 0;
            for (int x = -xOff; x < xOff + 1; ++x) {
                for (int y = 0; y < yOff * 2 + 1; ++y) {
                    for (int z = -zOff; z < zOff + 1; ++z) {
                        int xp = pos.field_72311_b + x + dir.offsetX;
                        int yp = pos.field_72312_c + y + dir.offsetY;
                        int zp = pos.field_72309_d + z + dir.offsetZ;
                        Block block1 = player.field_70170_p.func_147439_a(xp, yp, zp);
                        if (block1 != null && !block1.isAir((IBlockAccess)player.field_70170_p, xp, yp, zp) && !block1.isReplaceable((IBlockAccess)player.field_70170_p, xp, yp, zp)) continue;
                        coords.add(new ChunkCoordinates(xp, yp, zp));
                    }
                }
            }
        }
        return coords.toArray(new ChunkCoordinates[coords.size()]);
    }

    private static void setSize(ItemStack stack, int size) {
        ItemNBTHelper.setInt(stack, TAG_SIZE, size | 1);
    }

    public static int getSize(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_SIZE, 3) | 1;
    }

    @Deprecated
    public static int getBlockID(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_ID, 0);
    }

    public static Block getBlock(ItemStack stack) {
        Block block = Block.func_149684_b((String)ItemPlacementMirror.getBlockName(stack));
        if (block == Blocks.field_150350_a) {
            block = Block.func_149729_e((int)ItemPlacementMirror.getBlockID(stack));
        }
        return block;
    }

    public static String getBlockName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_BLOCK_NAME, "");
    }

    public static int getBlockMeta(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_META, 0);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = par3World.func_147439_a(par4, par5, par6);
        int meta = par3World.func_72805_g(par4, par5, par6);
        if (par2EntityPlayer.func_70093_af()) {
            if (block != null && block.func_149645_b() == 0) {
                this.setBlock(par1ItemStack, block, meta);
            }
        } else {
            this.placeAllBlocks(par1ItemStack, par2EntityPlayer);
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af()) {
            int size = ItemPlacementMirror.getSize(par1ItemStack);
            int newSize = size == 11 ? 3 : size + 2;
            ItemPlacementMirror.setSize(par1ItemStack, newSize);
            ToolModeHUDHandler.setTooltip(newSize + " x " + newSize);
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
        }
        return par1ItemStack;
    }

    public void placeAllBlocks(ItemStack stack, EntityPlayer player) {
        ChunkCoordinates[] blocksToPlace = ItemPlacementMirror.getBlocksToPlace(stack, player);
        if (!ItemPlacementMirror.hasBlocks(stack, player, blocksToPlace)) {
            return;
        }
        ItemStack stackToPlace = new ItemStack(ItemPlacementMirror.getBlock(stack), 1, ItemPlacementMirror.getBlockMeta(stack));
        for (ChunkCoordinates coords : blocksToPlace) {
            this.placeBlockAndConsume(player, stackToPlace, coords);
        }
        player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:wand", 1.0f, 1.0f);
    }

    private void placeBlockAndConsume(EntityPlayer player, ItemStack blockToPlace, ChunkCoordinates coords) {
        if (blockToPlace.func_77973_b() == null) {
            return;
        }
        player.field_70170_p.func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149634_a((Item)blockToPlace.func_77973_b()), blockToPlace.func_77960_j(), 3);
        if (player.field_71075_bZ.field_75098_d) {
            return;
        }
        ArrayList<ItemStack> talismansToCheck = new ArrayList<ItemStack>();
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stackInSlot = player.field_71071_by.func_70301_a(i);
            if (stackInSlot != null && stackInSlot.func_77973_b() == blockToPlace.func_77973_b() && stackInSlot.func_77960_j() == blockToPlace.func_77960_j()) {
                --stackInSlot.field_77994_a;
                if (stackInSlot.field_77994_a == 0) {
                    player.field_71071_by.func_70299_a(i, null);
                }
                return;
            }
            if (stackInSlot == null || stackInSlot.func_77973_b() != ThaumicTinkerer.registry.getFirstItemFromClass(ItemBlockTalisman.class)) continue;
            talismansToCheck.add(stackInSlot);
        }
        for (ItemStack talisman : talismansToCheck) {
            Block block = ItemBlockTalisman.getBlock(talisman);
            int meta = ItemBlockTalisman.getBlockMeta(talisman);
            if (Item.func_150898_a((Block)block) != blockToPlace.func_77973_b() || meta != blockToPlace.func_77960_j()) continue;
            ItemBlockTalisman.remove(talisman, 1);
            return;
        }
    }

    private void setBlock(ItemStack stack, Block block, int meta) {
        ItemNBTHelper.setString(stack, TAG_BLOCK_NAME, Block.field_149771_c.func_148750_c((Object)block));
        ItemNBTHelper.setInt(stack, TAG_BLOCK_META, meta);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons[0] = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.icons[1] = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon func_77618_c(int par1, int par2) {
        return this.icons[par2 & 1];
    }

    public boolean func_77623_v() {
        return true;
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        Block block = ItemPlacementMirror.getBlock(par1ItemStack);
        int size = ItemPlacementMirror.getSize(par1ItemStack);
        par3List.add(size + " x " + size);
        if (block != null && block != Blocks.field_150350_a) {
            par3List.add(StatCollector.func_74838_a((String)(new ItemStack(block, 1, ItemPlacementMirror.getBlockMeta(par1ItemStack)).func_77977_a() + ".name")));
        }
    }

    @Override
    public String getItemName() {
        return "placementMirror";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("PLACEMENT_MIRROR", new AspectList().add(Aspect.CRAFT, 2).add(Aspect.CRYSTAL, 1).add(Aspect.ELDRITCH, 1).add(Aspect.MIND, 1), 17, 16, 5, new ItemStack((Item)this)).setParents(new String[]{"BLOCK_TALISMAN"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("PLACEMENT_MIRROR")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("PLACEMENT_MIRROR", new ItemStack((Item)this), 12, new AspectList().add(Aspect.CRAFT, 65).add(Aspect.CRYSTAL, 32).add(Aspect.MAGIC, 50).add(Aspect.MIND, 32), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemBlockTalisman.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Blocks.field_150409_cd), new ItemStack(Items.field_151045_i), new ItemStack(Blocks.field_150359_w), new ItemStack(Items.field_151065_br), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)));
    }
}

