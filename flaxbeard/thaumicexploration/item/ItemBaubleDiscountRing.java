/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package flaxbeard.thaumicexploration.item;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.item.ItemBaubleDiscounter;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ItemBaubleDiscountRing
extends ItemBaubleDiscounter {
    private String[] textures = new String[]{"ringAer", "ringTerra", "ringIgnis", "ringAqua", "ringOrdo", "ringPerdito"};
    public static int[] correspondingShards = new int[]{0, 3, 1, 2, 4, 5};
    private IIcon[] icons = new IIcon[6];

    public ItemBaubleDiscountRing() {
        super(BaubleType.RING, new AspectList(), 0);
        this.func_77656_e(0);
    }

    @Override
    public int getVisDiscount(ItemStack arg0, EntityPlayer arg1, Aspect arg2) {
        if (arg2 == Aspect.getPrimalAspects().get(arg0.func_77960_j())) {
            return 3;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item itemID, CreativeTabs tab, List itemList) {
        for (int i = 0; i < 6; ++i) {
            itemList.add(new ItemStack(itemID, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        for (int i = 0; i < 6; ++i) {
            this.icons[i] = par1IconRegister.func_94245_a("thaumicexploration:" + this.textures[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icons[par1];
    }

    public String func_77667_c(ItemStack item) {
        return this.func_77658_a() + ":" + item.func_77960_j();
    }
}

