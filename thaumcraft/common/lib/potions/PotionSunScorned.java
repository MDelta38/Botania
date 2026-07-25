/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.common.lib.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class PotionSunScorned
extends Potion {
    public static PotionSunScorned instance = null;
    private int statusIconIndex = -1;
    static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");

    public PotionSunScorned(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        this.func_76399_b(0, 0);
    }

    public static void init() {
        instance.func_76390_b("potion.sunscorned");
        instance.func_76399_b(6, 2);
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
        if (!target.field_70170_p.field_72995_K) {
            float f = target.func_70013_c(1.0f);
            if (f > 0.5f && target.field_70170_p.field_73012_v.nextFloat() * 30.0f < (f - 0.4f) * 2.0f && target.field_70170_p.func_72937_j(MathHelper.func_76128_c((double)target.field_70165_t), MathHelper.func_76128_c((double)target.field_70163_u), MathHelper.func_76128_c((double)target.field_70161_v))) {
                target.func_70015_d(4);
            } else if (f < 0.25f && target.field_70170_p.field_73012_v.nextFloat() > f * 2.0f) {
                target.func_70691_i(1.0f);
            }
        }
    }

    public boolean func_76397_a(int par1, int par2) {
        return par1 % 40 == 0;
    }
}

