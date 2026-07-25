/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.codechicken.lib.vec.Vector3
 */
package thaumic.tinkerer.common.item.kami;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.kami.BlockWarpGate;
import thaumic.tinkerer.common.core.helper.ItemNBTHelper;
import thaumic.tinkerer.common.core.helper.MiscHelper;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemSkyPearl
extends ItemKamiBase {
    public static final String TAG_X = "x";
    public static final String TAG_Y = "y";
    public static final String TAG_Z = "z";
    public static final String TAG_DIM = "dim";

    public ItemSkyPearl() {
        this.func_77625_d(1);
    }

    public static void addInfo(ItemStack stack, int dim, Vector3 pos, List<String> list, boolean simpleMode) {
        if (ItemSkyPearl.isAttuned(stack)) {
            int x = ItemSkyPearl.getX(stack);
            int y = ItemSkyPearl.getY(stack);
            int z = ItemSkyPearl.getZ(stack);
            list.add("X: " + x);
            if (!simpleMode) {
                list.add("Y: " + y);
            }
            list.add("Z: " + z);
            if (ItemSkyPearl.getDim(stack) != dim) {
                if (!simpleMode) {
                    list.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"ttmisc.differentDim"));
                }
            } else {
                list.add(EnumChatFormatting.BLUE + StatCollector.func_74838_a((String)"ttmisc.distance") + ": " + new BigDecimal(MiscHelper.pointDistanceSpace(x, simpleMode ? 0.0 : (double)y, z, pos.x, simpleMode ? 0.0 : pos.y, pos.z)).setScale(2, RoundingMode.UP).toString() + "m");
            }
        }
    }

    public static void setValues(ItemStack stack, int x, int y, int z, int dim) {
        ItemNBTHelper.setInt(stack, TAG_X, x);
        ItemNBTHelper.setInt(stack, TAG_Y, y);
        ItemNBTHelper.setInt(stack, TAG_Z, z);
        ItemNBTHelper.setInt(stack, TAG_DIM, dim);
    }

    public static boolean isAttuned(ItemStack stack) {
        return ItemNBTHelper.detectNBT(stack) && ItemNBTHelper.getInt(stack, TAG_Y, -1) != -1;
    }

    public static int getX(ItemStack stack) {
        if (!ItemSkyPearl.isAttuned(stack)) {
            return 0;
        }
        return ItemNBTHelper.getInt(stack, TAG_X, 0);
    }

    public static int getY(ItemStack stack) {
        if (!ItemSkyPearl.isAttuned(stack)) {
            return 0;
        }
        return ItemNBTHelper.getInt(stack, TAG_Y, 0);
    }

    public static int getZ(ItemStack stack) {
        if (!ItemSkyPearl.isAttuned(stack)) {
            return 0;
        }
        return ItemNBTHelper.getInt(stack, TAG_Z, 0);
    }

    public static int getDim(ItemStack stack) {
        if (!ItemSkyPearl.isAttuned(stack)) {
            return 0;
        }
        return ItemNBTHelper.getInt(stack, TAG_DIM, 0);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = par3World.func_147439_a(par4, par5, par6);
        if (block == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockWarpGate.class) && !ItemSkyPearl.isAttuned(par1ItemStack)) {
            ItemSkyPearl.setValues(par1ItemStack, par4, par5, par6, par2EntityPlayer.field_71093_bK);
            par3World.func_72956_a((Entity)par2EntityPlayer, "random.orb", 0.3f, 0.1f);
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.func_70093_af() && ItemSkyPearl.isAttuned(par1ItemStack)) {
            par2World.func_72956_a((Entity)par3EntityPlayer, "random.orb", 0.3f, 0.1f);
            ItemNBTHelper.setInt(par1ItemStack, TAG_Y, -1);
        }
        return par1ItemStack;
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        ItemSkyPearl.addInfo(par1ItemStack, par2EntityPlayer.field_71093_bK, Vector3.fromEntityCenter((Entity)par2EntityPlayer), par3List, false);
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return ItemSkyPearl.isAttuned(par1ItemStack);
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @Override
    public String getItemName() {
        return "skyPearl";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("SKY_PEARL", "WARP_GATE", new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class), 2), 6, new AspectList().add(Aspect.TRAVEL, 32).add(Aspect.ELDRITCH, 32).add(Aspect.FLIGHT, 32).add(Aspect.AIR, 16), new ItemStack(Items.field_151079_bi), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 7), new ItemStack(Blocks.field_150368_y), new ItemStack(Items.field_151045_i));
    }
}

