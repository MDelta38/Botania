/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  thaumcraft.api.potions.PotionFluxTaint
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 */
package flaxbeard.thaumicexploration.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.event.DamageSourceTX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class TXTaintPotion
extends PotionFluxTaint {
    public TXTaintPotion(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
        this.func_76399_b(0, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_76392_e() {
        UtilsFX.bindTexture((String)"textures/misc/potions.png");
        return super.func_76392_e();
    }

    public void func_76394_a(EntityLivingBase target, int par2) {
        if (target.field_70170_p.func_72807_a((int)target.field_70165_t, (int)target.field_70161_v) == ThaumcraftWorldGenerator.biomeTaint) {
            target.func_82170_o(ThaumicExploration.potionTaintWithdrawl.field_76415_H);
        }
        if (!target.func_70662_br() && (target.func_110138_aP() > 1.0f || target instanceof EntityPlayer)) {
            target.func_70097_a(DamageSourceTX.noTaint, 1.0f);
        }
    }
}

