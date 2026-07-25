/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 */
package com.kentington.thaumichorizons.common.items.lenses;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import com.kentington.thaumichorizons.common.items.lenses.LensManager;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketRemoveNightvision;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;

public class ItemLensWater
extends Item
implements ILens {
    IIcon icon;

    public ItemLensWater() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @Override
    public String lensName() {
        return "LensWater";
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleRender(Minecraft mc, float partialTicks) {
        boolean inWater;
        block4: {
            block5: {
                inWater = mc.field_71439_g.func_70055_a(Material.field_151586_h);
                if (!inWater) break block4;
                if (mc.field_71439_g.func_70660_b(Potion.field_76439_r) == null) break block5;
                if (mc.field_71439_g.func_70660_b(Potion.field_76439_r).func_76459_b() >= 242) break block4;
            }
            if (mc.func_71386_F() > LensManager.nightVisionOffTime) {
                LensManager.nightVisionOffTime = mc.func_71386_F();
                mc.field_71439_g.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 255, 0, true));
                return;
            }
        }
        if (inWater) return;
        mc.field_71439_g.func_82170_o(Potion.field_76439_r.field_76415_H);
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.LensWater";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:lenswater");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @Override
    public void handleRemoval(EntityPlayer p) {
        p.func_82170_o(Potion.field_76439_r.field_76415_H);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketRemoveNightvision(), (EntityPlayerMP)p);
    }
}

