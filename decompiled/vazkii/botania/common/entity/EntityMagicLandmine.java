/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.entity.EntityDoppleganger;

public class EntityMagicLandmine
extends Entity {
    public EntityDoppleganger summoner;

    public EntityMagicLandmine(World par1World) {
        super(par1World);
        this.func_70105_a(0.0f, 0.0f);
    }

    public void func_70071_h_() {
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        super.func_70071_h_();
        float range = 2.5f;
        float r = 0.2f;
        float g = 0.0f;
        float b = 0.2f;
        for (int i = 0; i < 6; ++i) {
            Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t - (double)range + Math.random() * (double)range * 2.0, this.field_70163_u, this.field_70161_v - (double)range + Math.random() * (double)range * 2.0, r, g, b, 0.4f, -0.015f, 1.0f);
        }
        if (this.field_70173_aa >= 55) {
            this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "botania:gaiaTrap", 0.3f, 1.0f);
            float m = 0.35f;
            g = 0.4f;
            for (int i = 0; i < 25; ++i) {
                Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v, r, g, b, 0.5f, (float)(Math.random() - 0.5) * m, (float)(Math.random() - 0.5) * m, (float)(Math.random() - 0.5) * m);
            }
            if (!this.field_70170_p.field_72995_K) {
                List players = this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)range), (double)(this.field_70163_u - (double)range), (double)(this.field_70161_v - (double)range), (double)(this.field_70165_t + (double)range), (double)(this.field_70163_u + (double)range), (double)(this.field_70161_v + (double)range)));
                for (EntityPlayer player : players) {
                    player.func_70097_a(this.summoner == null ? DamageSource.field_76377_j : DamageSource.func_76358_a((EntityLivingBase)this.summoner), 10.0f);
                    player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 25, 0));
                    PotionEffect wither = new PotionEffect(Potion.field_82731_v.field_76415_H, 70, 3);
                    wither.getCurativeItems().clear();
                    player.func_70690_d(wither);
                }
            }
            this.func_70106_y();
        }
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }
}

