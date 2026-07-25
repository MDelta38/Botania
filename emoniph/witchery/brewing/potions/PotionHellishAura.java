/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleRenderLiving;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;

public class PotionHellishAura
extends PotionBase
implements IHandleRenderLiving {
    public PotionHellishAura(int id, int color) {
        super(id, color);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        int frequencyFactor = 25;
        int k = frequencyFactor >> amplifier;
        return k > 0 ? duration % k == 0 : true;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        World world = entity.field_70170_p;
        if (!world.field_72995_K) {
            List entities = world.func_72872_a(EntityLivingBase.class, entity.field_70121_D.func_72314_b(1.5, 0.0, 1.5));
            for (EntityLivingBase otherEntity : entities) {
                if (entity == otherEntity) continue;
                otherEntity.func_70097_a(new EntityDamageSource(DamageSource.field_76370_b.field_76373_n, (Entity)entity).func_76361_j().func_76348_h(), 1.0f);
                ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, (Entity)otherEntity, otherEntity.field_70130_N, otherEntity.field_70131_O, 16);
                if (amplifier <= 0) continue;
                otherEntity.func_70015_d(amplifier);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onLivingRender(World world, EntityLivingBase entity, RenderLivingEvent.Post event, int amplifier) {
        EntityLivingBase p_76977_1_ = entity;
        double p_76977_2_ = entity.field_70165_t;
        double p_76977_4_ = entity.field_70163_u;
        double p_76977_6_ = entity.field_70161_v;
        GL11.glDisable((int)2896);
        IIcon iicon = Blocks.field_150480_ab.func_149840_c(0);
        IIcon iicon1 = Blocks.field_150480_ab.func_149840_c(1);
        GL11.glPushMatrix();
        float f1 = p_76977_1_.field_70130_N * 1.4f;
        GL11.glScalef((float)f1, (float)f1, (float)f1);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f2 = 0.5f;
        float f3 = 0.0f;
        float f4 = p_76977_1_.field_70131_O / f1;
        float f5 = (float)(p_76977_1_.field_70163_u - p_76977_1_.field_70121_D.field_72338_b);
        GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-0.3f + (float)((int)f4) * 0.02f));
        GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)1.0f);
        float f6 = 0.0f;
        int i = 0;
        tessellator.func_78382_b();
        while (f4 > 0.0f) {
            IIcon iicon2 = i % 2 == 0 ? iicon : iicon1;
            RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110575_b);
            float f7 = iicon2.func_94209_e();
            float f8 = iicon2.func_94206_g();
            float f9 = iicon2.func_94212_f();
            float f10 = iicon2.func_94210_h();
            if (i / 2 % 2 == 0) {
                float f11 = f9;
                f9 = f7;
                f7 = f11;
            }
            tessellator.func_78374_a((double)(f2 - f3), (double)(0.0f - f5), (double)f6, (double)f9, (double)f10);
            tessellator.func_78374_a((double)(-f2 - f3), (double)(0.0f - f5), (double)f6, (double)f7, (double)f10);
            tessellator.func_78374_a((double)(-f2 - f3), (double)(1.4f - f5), (double)f6, (double)f7, (double)f8);
            tessellator.func_78374_a((double)(f2 - f3), (double)(1.4f - f5), (double)f6, (double)f9, (double)f8);
            f4 -= 0.45f;
            f5 -= 0.45f;
            f2 *= 0.9f;
            f6 += 0.03f;
            ++i;
        }
        tessellator.func_78381_a();
        GL11.glPopMatrix();
        GL11.glEnable((int)2896);
    }
}

