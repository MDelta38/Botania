/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.common.lib.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionUnnaturalHunger
extends Potion {
    public static PotionUnnaturalHunger instance = null;
    private int statusIconIndex = -1;
    static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");

    public PotionUnnaturalHunger(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        this.func_76399_b(0, 0);
    }

    public static void init() {
        instance.func_76390_b("potion.unhunger");
        instance.func_76399_b(7, 1);
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
        if (!target.field_70170_p.field_72995_K && target instanceof EntityPlayer) {
            ((EntityPlayer)target).func_71020_j(0.025f * (float)(par2 + 1));
        }
    }

    public boolean func_76397_a(int par1, int par2) {
        return true;
    }
}

