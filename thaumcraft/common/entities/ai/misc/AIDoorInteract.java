/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import thaumcraft.common.entities.golems.EntityGolemBase;

public abstract class AIDoorInteract
extends EntityAIBase {
    protected EntityGolemBase theEntity;
    protected int entityPosX;
    protected int entityPosY;
    protected int entityPosZ;
    protected Block targetDoor;
    boolean hasStoppedDoorInteraction;
    float entityPositionX;
    float entityPositionZ;
    int count = 0;

    public AIDoorInteract(EntityGolemBase par1EntityLiving) {
        this.theEntity = par1EntityLiving;
    }

    public boolean func_75250_a() {
        if (!this.theEntity.field_70123_F) {
            return false;
        }
        PathNavigate var1 = this.theEntity.func_70661_as();
        PathEntity var2 = var1.func_75505_d();
        if (var2 != null && !var2.func_75879_b() && var1.func_75507_c()) {
            for (int var3 = 0; var3 < Math.min(var2.func_75873_e() + 2, var2.func_75874_d()); ++var3) {
                PathPoint var4 = var2.func_75877_a(var3);
                this.entityPosX = var4.field_75839_a;
                this.entityPosY = var4.field_75837_b;
                this.entityPosZ = var4.field_75838_c;
                if (!(this.theEntity.func_70092_e(this.entityPosX, this.theEntity.field_70163_u, this.entityPosZ) <= 2.25)) continue;
                this.targetDoor = this.findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
                if (this.targetDoor == null || this.targetDoor == Blocks.field_150350_a) continue;
                this.count = 200;
                return true;
            }
            this.entityPosX = MathHelper.func_76128_c((double)this.theEntity.field_70165_t);
            this.entityPosY = MathHelper.func_76128_c((double)this.theEntity.field_70163_u);
            this.entityPosZ = MathHelper.func_76128_c((double)this.theEntity.field_70161_v);
            this.targetDoor = this.findUsableDoor(this.entityPosX, this.entityPosY, this.entityPosZ);
            if (this.targetDoor != null && this.targetDoor != Blocks.field_150350_a) {
                this.count = 200;
            }
            return this.targetDoor != null && this.targetDoor != Blocks.field_150350_a;
        }
        return false;
    }

    public boolean func_75253_b() {
        return this.count > 0 && !this.hasStoppedDoorInteraction;
    }

    public void func_75249_e() {
        this.count = 100;
        this.hasStoppedDoorInteraction = false;
        this.entityPositionX = (float)((double)((float)this.entityPosX + 0.5f) - this.theEntity.field_70165_t);
        this.entityPositionZ = (float)((double)((float)this.entityPosZ + 0.5f) - this.theEntity.field_70161_v);
    }

    public void func_75246_d() {
        --this.count;
        float var1 = (float)((double)((float)this.entityPosX + 0.5f) - this.theEntity.field_70165_t);
        float var2 = (float)((double)((float)this.entityPosZ + 0.5f) - this.theEntity.field_70161_v);
        float var3 = this.entityPositionX * var1 + this.entityPositionZ * var2;
        if (var3 < 0.0f) {
            this.hasStoppedDoorInteraction = true;
        }
    }

    private Block findUsableDoor(int par1, int par2, int par3) {
        Block var4 = this.theEntity.field_70170_p.func_147439_a(par1, par2, par3);
        return var4 != Blocks.field_150466_ao && var4 != Blocks.field_150396_be ? Block.func_149729_e((int)0) : var4;
    }
}

