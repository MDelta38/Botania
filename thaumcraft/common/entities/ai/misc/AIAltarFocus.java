/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.ai.misc;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityCultistCleric;

public class AIAltarFocus
extends EntityAIBase {
    private EntityCultistCleric entity;
    private World world;
    int field_48399_a = 0;

    public AIAltarFocus(EntityCultistCleric par1EntityLiving) {
        this.entity = par1EntityLiving;
        this.world = par1EntityLiving.field_70170_p;
        this.func_75248_a(7);
    }

    public boolean func_75250_a() {
        return this.entity.getIsRitualist() && this.entity.func_110172_bL() != null;
    }

    public void func_75249_e() {
    }

    public void func_75251_c() {
    }

    public boolean func_75253_b() {
        return this.entity.getIsRitualist() && this.entity.func_110172_bL() != null;
    }

    public void func_75246_d() {
        if (this.entity.func_110172_bL() != null && this.entity.field_70173_aa % 40 == 0 && (this.entity.func_110172_bL().func_71569_e((int)this.entity.field_70165_t, (int)this.entity.field_70163_u, (int)this.entity.field_70161_v) > 16.0f || this.world.func_147439_a(this.entity.func_110172_bL().field_71574_a, this.entity.func_110172_bL().field_71572_b, this.entity.func_110172_bL().field_71573_c) != ConfigBlocks.blockEldritch)) {
            this.entity.setIsRitualist(false);
        }
    }
}

