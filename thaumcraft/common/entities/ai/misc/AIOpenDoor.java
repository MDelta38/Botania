/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import thaumcraft.common.entities.ai.misc.AIDoorInteract;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIOpenDoor
extends AIDoorInteract {
    boolean field_75361_i;
    int field_75360_j;

    public AIOpenDoor(EntityGolemBase par1EntityLiving, boolean par2) {
        super(par1EntityLiving);
        this.theEntity = par1EntityLiving;
        this.field_75361_i = par2;
    }

    @Override
    public boolean func_75253_b() {
        return this.field_75361_i && this.field_75360_j > 0 && super.func_75253_b();
    }

    @Override
    public void func_75249_e() {
        this.field_75360_j = 20;
        if (this.targetDoor == Blocks.field_150466_ao) {
            ((BlockDoor)this.targetDoor).func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, true);
        } else {
            int var10 = this.theEntity.field_70170_p.func_72805_g(this.entityPosX, this.entityPosY, this.entityPosZ);
            if (!BlockFenceGate.func_149896_b((int)var10)) {
                int var11 = (MathHelper.func_76128_c((double)((double)(this.theEntity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) % 4;
                int var12 = BlockFenceGate.func_149895_l((int)var10);
                if (var12 == (var11 + 2) % 4) {
                    var10 = var11;
                }
                this.theEntity.field_70170_p.func_147465_d(this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor, var10 | 4, 3);
                this.theEntity.field_70170_p.func_72889_a((EntityPlayer)null, 1003, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
            }
        }
    }

    public void func_75251_c() {
        if (this.field_75361_i) {
            if (this.targetDoor == Blocks.field_150466_ao) {
                ((BlockDoor)this.targetDoor).func_150014_a(this.theEntity.field_70170_p, this.entityPosX, this.entityPosY, this.entityPosZ, false);
            } else {
                int var10 = this.theEntity.field_70170_p.func_72805_g(this.entityPosX, this.entityPosY, this.entityPosZ);
                if (BlockFenceGate.func_149896_b((int)var10)) {
                    this.theEntity.field_70170_p.func_147465_d(this.entityPosX, this.entityPosY, this.entityPosZ, this.targetDoor, var10 & 0xFFFFFFFB, 3);
                    this.theEntity.field_70170_p.func_72889_a((EntityPlayer)null, 1003, this.entityPosX, this.entityPosY, this.entityPosZ, 0);
                }
            }
        }
    }

    @Override
    public void func_75246_d() {
        --this.field_75360_j;
        super.func_75246_d();
    }
}

