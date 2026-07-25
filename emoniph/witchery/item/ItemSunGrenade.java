/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.item.ItemBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSunGrenade
extends ItemBase {
    private final int mode;
    @SideOnly(value=Side.CLIENT)
    protected IIcon itemIconOverlay;

    public ItemSunGrenade(int mode) {
        this.mode = mode;
        this.func_77625_d(16);
        this.func_77656_e(0);
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips) {
        super.func_77624_a(stack, player, list, moreTips);
        if (this.mode == 1) {
            list.add(String.format(Witchery.resource("item.witchery:dupgrenade.tip"), ItemSunGrenade.getOwnerName(stack)));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        return pass == 0;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            return this.itemIconOverlay;
        }
        return this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        super.func_94581_a(iconRegister);
        this.itemIconOverlay = iconRegister.func_94245_a("witchery:ingredient.quartzSphere");
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.uncommon;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.bow;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!world.field_72995_K) {
            EntityGrenade grenade = new EntityGrenade(world, (EntityLivingBase)player, stack);
            grenade.setMode(this.mode);
            if (this.mode == 1) {
                grenade.setOwner(ItemSunGrenade.getOwnerName(stack));
            }
            world.func_72838_d((Entity)grenade);
        }
        return stack;
    }

    public static String getOwnerName(ItemStack stack) {
        if (stack.func_77942_o()) {
            NBTTagCompound nbtRoot = stack.func_77978_p();
            return nbtRoot.func_74779_i("Owner");
        }
        return null;
    }

    public static void setOwnerName(ItemStack stack, String name) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbtRoot = stack.func_77978_p();
        nbtRoot.func_74778_a("Owner", name);
    }
}

