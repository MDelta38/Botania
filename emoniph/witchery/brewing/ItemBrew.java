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
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.item.ItemBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemBrew
extends ItemBase {
    @SideOnly(value=Side.CLIENT)
    protected IIcon itemIconOverlay;
    @SideOnly(value=Side.CLIENT)
    protected IIcon itemIconSplash;

    public ItemBrew() {
        this.func_77625_d(8);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.registerWithCreativeTab = false;
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
        return stack == null || WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p()) ? this.itemIconSplash : this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        super.func_94581_a(iconRegister);
        this.itemIconOverlay = iconRegister.func_94245_a("witchery:brew_overlay");
        this.itemIconSplash = iconRegister.func_94245_a("witchery:brew_splash");
    }

    @SideOnly(value=Side.CLIENT)
    public String func_77653_i(ItemStack stack) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if (nbtRoot != null) {
            return nbtRoot.func_74779_i("BrewName");
        }
        return super.func_77653_i(stack);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            NBTTagCompound nbtRoot = stack.func_77978_p();
            return WitcheryBrewRegistry.INSTANCE.getBrewColor(nbtRoot);
        }
        return super.func_82790_a(stack, pass);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expanded) {
        String localText;
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if (nbtRoot != null && (localText = nbtRoot.func_74779_i("BrewInfo")) != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.common;
    }

    public int func_77626_a(ItemStack stack) {
        int DEFAULT_SPEED = 32;
        NBTTagCompound nbtRoot = stack.func_77978_p();
        int drinkSpeed = nbtRoot != null ? nbtRoot.func_74762_e("BrewDrinkSpeed") : 32;
        return drinkSpeed > 0 ? drinkSpeed : 32;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        if (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) {
            return EnumAction.bow;
        }
        return EnumAction.drink;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) {
            if (!player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
            }
            world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)new EntityBrew(world, (EntityLivingBase)player, stack, false));
            }
        } else {
            player.func_71008_a(stack, this.func_77626_a(stack));
        }
        return stack;
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        if (!player.field_71075_bZ.field_75098_d) {
            --stack.field_77994_a;
        }
        if (!world.field_72995_K) {
            ModifiersEffect modifiers = new ModifiersEffect(1.0, 1.0, false, null, false, 0, player);
            WitcheryBrewRegistry.INSTANCE.applyToEntity(world, (EntityLivingBase)player, stack.func_77978_p(), modifiers);
        }
        if (!player.field_71075_bZ.field_75098_d) {
            if (stack.field_77994_a <= 0) {
                return new ItemStack(Items.field_151069_bo);
            }
            player.field_71071_by.func_70441_a(new ItemStack(Items.field_151069_bo));
        }
        return stack;
    }
}

