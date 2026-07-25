/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.common.lib.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.config.Config;

public class PotionInfectiousVisExhaust
extends Potion {
    public static PotionInfectiousVisExhaust instance = null;
    private int statusIconIndex = -1;
    static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");

    public PotionInfectiousVisExhaust(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        this.func_76399_b(0, 0);
    }

    public static void init() {
        instance.func_76390_b("potion.infvisexhaust");
        instance.func_76399_b(6, 1);
        instance.func_76404_a(0.25);
    }

    public boolean func_76398_f() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76392_e() {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
        return super.func_76392_e();
    }

    public void func_76394_a(EntityLivingBase target, int par2) {
        List targets = target.field_70170_p.func_72872_a(EntityLivingBase.class, target.field_70121_D.func_72314_b(4.0, 4.0, 4.0));
        if (targets.size() > 0) {
            for (EntityLivingBase e : targets) {
                if (e.func_82165_m(Config.potionInfVisExhaustID)) continue;
                if (par2 > 0) {
                    e.func_70690_d(new PotionEffect(Config.potionInfVisExhaustID, 6000, par2 - 1, false));
                    continue;
                }
                e.func_70690_d(new PotionEffect(Config.potionVisExhaustID, 6000, 0, false));
            }
        }
    }

    public boolean func_76397_a(int par1, int par2) {
        return par1 % 40 == 0;
    }
}

