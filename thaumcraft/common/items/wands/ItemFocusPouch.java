/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.wands;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class ItemFocusPouch
extends Item {
    protected IIcon icon;

    public ItemFocusPouch() {
        this.func_77625_d(1);
        this.func_77627_a(false);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumcraft:focuspouch");
    }

    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public boolean func_77651_p() {
        return true;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_77636_d(ItemStack par1ItemStack) {
        return false;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.field_72995_K) {
            par3EntityPlayer.openGui((Object)Thaumcraft.instance, 5, par2World, MathHelper.func_76128_c((double)par3EntityPlayer.field_70165_t), MathHelper.func_76128_c((double)par3EntityPlayer.field_70163_u), MathHelper.func_76128_c((double)par3EntityPlayer.field_70161_v));
        }
        return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
    }

    public ItemStack[] getInventory(ItemStack item) {
        ItemStack[] stackList = new ItemStack[18];
        if (item.func_77942_o()) {
            NBTTagList var2 = item.field_77990_d.func_150295_c("Inventory", 10);
            for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
                NBTTagCompound var4 = var2.func_150305_b(var3);
                int var5 = var4.func_74771_c("Slot") & 0xFF;
                if (var5 < 0 || var5 >= stackList.length) continue;
                stackList[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
            }
        }
        return stackList;
    }

    public void setInventory(ItemStack item, ItemStack[] stackList) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < stackList.length; ++var3) {
            if (stackList[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            stackList[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        item.func_77983_a("Inventory", (NBTBase)var2);
    }
}

