/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.damagesource.DamageSourceThaumcraft
 */
package witchinggadgets.common.magic;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import witchinggadgets.common.WGContent;

public class WGPotion
extends Potion {
    static ResourceLocation tex = new ResourceLocation("witchinggadgets", "textures/gui/potioneffects.png");
    final int tickrate;
    final boolean halfTickRateWIthAmplifier;

    public WGPotion(int id, boolean isBad, int colour, int tick, boolean halveTick, int icon) {
        super(id, isBad, colour);
        this.tickrate = tick;
        this.halfTickRateWIthAmplifier = halveTick;
        this.func_76399_b(icon % 8, icon / 8);
    }

    public int func_76392_e() {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(tex);
        return super.func_76392_e();
    }

    public boolean func_76397_a(int duration, int amplifier) {
        if (this.tickrate < 0) {
            return false;
        }
        int k = this.tickrate >> amplifier;
        return k > 0 ? duration % k == 0 : true;
    }

    public void func_76394_a(EntityLivingBase living, int amplifier) {
        if (((Object)((Object)this)).equals(WGContent.pot_dissolve)) {
            living.func_70097_a(DamageSourceThaumcraft.dissolve, 1.0f);
        }
    }
}

