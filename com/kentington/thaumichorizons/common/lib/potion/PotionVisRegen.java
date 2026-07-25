/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package com.kentington.thaumichorizons.common.lib.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionVisRegen
extends Potion {
    public static PotionVisRegen instance = null;
    private int statusIconIndex = 3;
    static final ResourceLocation rl = new ResourceLocation("thaumichorizons", "textures/misc/potions.png");

    public PotionVisRegen(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        this.func_76399_b(3, 0);
    }

    public static void init() {
        instance.func_76390_b("potion.visregen");
        instance.func_76399_b(3, 0);
        instance.func_76404_a(0.25);
    }

    public boolean func_76398_f() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76392_e() {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
        return super.func_76392_e();
    }

    public void func_76394_a(EntityLivingBase target, int par2) {
    }
}

