/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemChestSealLinked
extends Item {
    public IIcon theIcon;
    public static final String[] itemNames = new String[]{"Pale", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Dark"};

    public ItemChestSealLinked(int par1) {
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77625_d(64);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.func_77960_j() <= 15) {
            if (par2 == 0) {
                int j = 15 - par1ItemStack.func_77960_j();
                Color c = new Color(EntitySheep.field_70898_d[j][0], EntitySheep.field_70898_d[j][1], EntitySheep.field_70898_d[j][2]);
                return c.getRGB() & 0xFFFFFF;
            }
            int j = 15 - par1ItemStack.func_77960_j();
            Color c = new Color(EntitySheep.field_70898_d[j][0], EntitySheep.field_70898_d[j][1], EntitySheep.field_70898_d[j][2]);
            Color c2 = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
            return c2.getRGB() & 0xFFFFFF;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.theIcon = par1IconRegister.func_94245_a(this.field_111218_cA + "Inset");
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        Block var11 = world.func_147439_a(x, y, z);
        if (var11 == Blocks.field_150486_ae) {
            stack = new ItemStack(ThaumicExploration.chestSealLinked, 1, stack.func_77960_j());
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item itemID, CreativeTabs tab, List itemList) {
        for (int i = 0; i < itemNames.length; ++i) {
            itemList.add(new ItemStack(itemID, 1, i));
        }
    }

    public String func_77667_c(ItemStack item) {
        if (item.func_77960_j() <= 15) {
            return this.func_77658_a() + ":" + itemNames[15 - item.func_77960_j()];
        }
        return "";
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (par1ItemStack.func_77942_o()) {
            String dimension = "dimension " + par1ItemStack.field_77990_d.func_74762_e("dim");
            if (par1ItemStack.field_77990_d.func_74762_e("dim") == 0) {
                dimension = "Overworld";
            }
            if (par1ItemStack.field_77990_d.func_74762_e("dim") == -1) {
                dimension = "Nether";
            }
            if (par1ItemStack.field_77990_d.func_74762_e("dim") == 1) {
                dimension = "End";
            }
            par3List.add("Linked to " + par1ItemStack.field_77990_d.func_74762_e("x") + "," + par1ItemStack.field_77990_d.func_74762_e("y") + "," + par1ItemStack.field_77990_d.func_74762_e("z") + " in the " + dimension + " (Network " + par1ItemStack.field_77990_d.func_74762_e("ID") + ")");
        }
    }

    public IIcon func_77618_c(int par1, int par2) {
        return par2 > 0 ? this.theIcon : super.func_77618_c(par1, par2);
    }
}

