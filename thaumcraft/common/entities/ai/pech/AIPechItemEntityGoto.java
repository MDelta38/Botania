/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathPoint
 */
package thaumcraft.common.entities.ai.pech;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathPoint;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityPech;

public class AIPechItemEntityGoto
extends EntityAIBase {
    private EntityPech pech;
    private Entity targetEntity;
    float maxTargetDistance = 16.0f;
    private int count;
    private int failedPathFindingPenalty;

    public AIPechItemEntityGoto(EntityPech par1EntityCreature) {
        this.pech = par1EntityCreature;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.pech.field_70173_aa % Config.golemDelay > 0) {
            return false;
        }
        if (--this.count > 0) {
            return false;
        }
        double range = Double.MAX_VALUE;
        List targets = this.pech.field_70170_p.func_72839_b((Entity)this.pech, this.pech.field_70121_D.func_72314_b((double)this.maxTargetDistance, (double)this.maxTargetDistance, (double)this.maxTargetDistance));
        if (targets.size() == 0) {
            return false;
        }
        for (Entity e : targets) {
            double distance;
            if (!(e instanceof EntityItem) || !this.pech.canPickup(((EntityItem)e).func_92059_d())) continue;
            NBTTagCompound itemData = ((EntityItem)e).getEntityData();
            String username = ((EntityItem)e).func_145800_j();
            if (username != null && username.equals("PechDrop") || !((distance = e.func_70092_e(this.pech.field_70165_t, this.pech.field_70163_u, this.pech.field_70161_v)) < range) || !(distance <= (double)(this.maxTargetDistance * this.maxTargetDistance))) continue;
            range = distance;
            this.targetEntity = e;
        }
        return this.targetEntity != null;
    }

    public boolean func_75253_b() {
        return this.targetEntity == null ? false : (!this.targetEntity.func_70089_S() ? false : !this.pech.func_70661_as().func_75500_f() && this.targetEntity.func_70068_e((Entity)this.pech) < (double)(this.maxTargetDistance * this.maxTargetDistance));
    }

    public void func_75251_c() {
        this.targetEntity = null;
    }

    public void func_75249_e() {
        this.pech.func_70661_as().func_75484_a(this.pech.func_70661_as().func_75494_a(this.targetEntity), this.pech.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5);
        this.count = 0;
    }

    public void func_75246_d() {
        double distance;
        this.pech.func_70671_ap().func_75651_a(this.targetEntity, 30.0f, 30.0f);
        if (this.pech.func_70635_at().func_75522_a(this.targetEntity) && --this.count <= 0) {
            PathPoint finalPathPoint;
            this.count = this.failedPathFindingPenalty + 4 + this.pech.func_70681_au().nextInt(4);
            this.pech.func_70661_as().func_75497_a(this.targetEntity, this.pech.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 1.5);
            this.failedPathFindingPenalty = this.pech.func_70661_as().func_75505_d() != null ? ((finalPathPoint = this.pech.func_70661_as().func_75505_d().func_75870_c()) != null && this.targetEntity.func_70092_e((double)finalPathPoint.field_75839_a, (double)finalPathPoint.field_75837_b, (double)finalPathPoint.field_75838_c) < 1.0 ? 0 : (this.failedPathFindingPenalty += 10)) : (this.failedPathFindingPenalty += 10);
        }
        if ((distance = this.pech.func_70092_e(this.targetEntity.field_70165_t, this.targetEntity.field_70121_D.field_72338_b, this.targetEntity.field_70161_v)) <= 1.5) {
            this.count = 0;
            int am = ((EntityItem)this.targetEntity).func_92059_d().field_77994_a;
            ItemStack is = this.pech.pickupItem(((EntityItem)this.targetEntity).func_92059_d());
            if (is != null && is.field_77994_a > 0) {
                ((EntityItem)this.targetEntity).func_92058_a(is);
            } else {
                this.targetEntity.func_70106_y();
            }
            if (is == null || is.field_77994_a != am) {
                this.targetEntity.field_70170_p.func_72956_a(this.targetEntity, "random.pop", 0.2f, ((this.targetEntity.field_70170_p.field_73012_v.nextFloat() - this.targetEntity.field_70170_p.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            }
        }
    }
}

