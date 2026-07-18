/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.Vector3;

public class EntityThrownItem
extends EntityItem {
    public EntityThrownItem(World par1World) {
        super(par1World);
    }

    public EntityThrownItem(World p_i1710_1_, double p_i1710_2_, double p_i1710_4_, double p_i1710_6_, EntityItem item) {
        super(p_i1710_1_, p_i1710_2_, p_i1710_4_, p_i1710_6_, item.func_92059_d());
        this.field_145804_b = item.field_145804_b;
        this.field_70159_w = item.field_70159_w;
        this.field_70181_x = item.field_70181_x;
        this.field_70179_y = item.field_70179_y;
    }

    public boolean func_85032_ar() {
        return true;
    }

    public void func_70071_h_() {
        Vector3 vec3m;
        super.func_70071_h_();
        Vec3 vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
        Vec3 vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
        MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
        if (!this.field_70170_p.field_72995_K) {
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w * 2.0, this.field_70181_x * 2.0, this.field_70179_y * 2.0).func_72314_b(2.0, 2.0, 2.0));
            double d0 = 0.0;
            for (int j = 0; j < list.size(); ++j) {
                double d1;
                float f;
                AxisAlignedBB axisalignedbb;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(j);
                if (!entity1.func_70067_L() || entity1 instanceof EntityPlayer && this.field_145804_b != 0 || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 1.0f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }
        if (movingobjectposition != null) {
            if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.field_150427_aO) {
                this.func_70063_aa();
            } else if (movingobjectposition.field_72308_g != null) {
                movingobjectposition.field_72308_g.func_70097_a(DamageSource.field_76376_m, 2.0f);
                if (!this.field_70170_p.field_72995_K) {
                    Entity item = this.func_92059_d().func_77973_b().createEntity(this.field_70170_p, (Entity)this, this.func_92059_d());
                    if (item == null) {
                        item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_92059_d());
                        this.field_70170_p.func_72838_d(item);
                        item.field_70159_w = this.field_70159_w * 0.25;
                        item.field_70181_x = this.field_70181_x * 0.25;
                        item.field_70179_y = this.field_70179_y * 0.25;
                    } else {
                        item.field_70159_w = this.field_70159_w * 0.25;
                        item.field_70181_x = this.field_70181_x * 0.25;
                        item.field_70179_y = this.field_70179_y * 0.25;
                    }
                }
                this.func_70106_y();
            }
        }
        if ((vec3m = new Vector3(this.field_70159_w, this.field_70181_x, this.field_70179_y)).mag() < 1.0) {
            if (!this.field_70170_p.field_72995_K) {
                Entity item = this.func_92059_d().func_77973_b().createEntity(this.field_70170_p, (Entity)this, this.func_92059_d());
                if (item == null) {
                    item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_92059_d());
                    this.field_70170_p.func_72838_d(item);
                    item.field_70159_w = this.field_70159_w;
                    item.field_70181_x = this.field_70181_x;
                    item.field_70179_y = this.field_70179_y;
                } else {
                    item.field_70159_w = this.field_70159_w;
                    item.field_70181_x = this.field_70181_x;
                    item.field_70179_y = this.field_70179_y;
                }
            }
            this.func_70106_y();
        }
    }
}

