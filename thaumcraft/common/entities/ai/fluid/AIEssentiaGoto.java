/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.ai.fluid;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;

public class AIEssentiaGoto
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private double jarX;
    private double jarY;
    private double jarZ;
    private World theWorld;
    int count = 0;
    int prevX = 0;
    int prevY = 0;
    int prevZ = 0;

    public AIEssentiaGoto(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.theGolem.field_70173_aa % Config.golemDelay > 0 || this.theGolem.essentia == null || this.theGolem.essentiaAmount == 0) {
            return false;
        }
        ChunkCoordinates jarloc = GolemHelper.findJarWithRoom(this.theGolem);
        if (jarloc == null) {
            return false;
        }
        this.jarX = jarloc.field_71574_a;
        this.jarY = jarloc.field_71572_b;
        this.jarZ = jarloc.field_71573_c;
        return true;
    }

    public boolean func_75253_b() {
        return this.count > 0 && !this.theGolem.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
        this.count = 0;
    }

    public void func_75246_d() {
        Vec3 var2;
        --this.count;
        if (this.count == 0 && this.prevX == MathHelper.func_76128_c((double)this.theGolem.field_70165_t) && this.prevY == MathHelper.func_76128_c((double)this.theGolem.field_70163_u) && this.prevZ == MathHelper.func_76128_c((double)this.theGolem.field_70161_v) && (var2 = RandomPositionGenerator.func_75463_a((EntityCreature)this.theGolem, (int)2, (int)1)) != null) {
            this.count = 20;
            this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, (double)this.theGolem.func_70689_ay());
        }
        super.func_75246_d();
    }

    public void func_75249_e() {
        this.count = 200;
        this.prevX = MathHelper.func_76128_c((double)this.theGolem.field_70165_t);
        this.prevY = MathHelper.func_76128_c((double)this.theGolem.field_70163_u);
        this.prevZ = MathHelper.func_76128_c((double)this.theGolem.field_70161_v);
        this.theGolem.func_70661_as().func_75492_a(this.jarX, this.jarY, this.jarZ, (double)this.theGolem.func_70689_ay());
    }
}

