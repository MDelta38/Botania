/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.entity.EntityThrowableCopy;

public class EntityFallingStar
extends EntityThrowableCopy {
    public EntityFallingStar(World world) {
        super(world);
        this.func_70105_a(0.0f, 0.0f);
    }

    public EntityFallingStar(World world, EntityLivingBase e) {
        super(world, e);
        this.func_70105_a(0.0f, 0.0f);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        float dist = 1.5f;
        for (int i = 0; i < 10; ++i) {
            float xs = (float)(Math.random() - 0.5) * dist;
            float ys = (float)(Math.random() - 0.5) * dist;
            float zs = (float)(Math.random() - 0.5) * dist;
            Botania.proxy.sparkleFX(this.field_70170_p, this.field_70165_t + (double)xs, this.field_70163_u + (double)ys, this.field_70161_v + (double)zs, 1.0f, 0.4f, 1.0f, 2.0f, 6);
        }
        EntityLivingBase thrower = this.getThrower();
        if (!this.field_70170_p.field_72995_K && thrower != null) {
            AxisAlignedBB axis = AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70142_S, (double)this.field_70137_T, (double)this.field_70136_U).func_72314_b(2.0, 2.0, 2.0);
            List entities = this.field_70170_p.func_72872_a(EntityLivingBase.class, axis);
            for (EntityLivingBase living : entities) {
                if (living == thrower || living.field_70737_aN != 0) continue;
                this.onImpact(new MovingObjectPosition((Entity)living));
                return;
            }
        }
        if (this.field_70173_aa > 200) {
            this.func_70106_y();
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition pos) {
        EntityLivingBase thrower = this.getThrower();
        if (pos.field_72308_g != null && thrower != null && pos.field_72308_g != thrower && !pos.field_72308_g.field_70128_L) {
            if (thrower instanceof EntityPlayer) {
                pos.field_72308_g.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)thrower)), 10.0f);
            } else {
                pos.field_72308_g.func_70097_a(DamageSource.field_76377_j, 10.0f);
            }
        }
        Block block = this.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        if (ConfigHandler.blockBreakParticles && !block.isAir((IBlockAccess)this.field_70170_p, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) {
            this.field_70170_p.func_72926_e(2001, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, Block.func_149682_b((Block)block) + (this.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) << 12));
        }
        this.func_70106_y();
    }
}

