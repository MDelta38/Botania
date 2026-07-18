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
package vazkii.botania.common.brew.potion;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionMod
extends Potion {
    private static final ResourceLocation resource = new ResourceLocation("botania:textures/gui/potions.png");

    public PotionMod(int id, String name, boolean badEffect, int color, int iconIndex) {
        super(id, badEffect, color);
        this.func_76390_b("botania.potion." + name);
        this.func_76399_b(iconIndex % 8, iconIndex / 8);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76392_e() {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(resource);
        return super.func_76392_e();
    }

    public boolean hasEffect(EntityLivingBase entity) {
        return this.hasEffect(entity, this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.func_70660_b(potion) != null;
    }
}

