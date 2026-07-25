/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions.symbols;

import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public abstract class SymbolEffectProjectile
extends SymbolEffect {
    private float size = 1.0f;
    private int color = 0xFF0000;
    private int timetolive = -1;

    public SymbolEffectProjectile(int effectID, String unlocalisedName) {
        super(effectID, unlocalisedName);
    }

    public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown) {
        super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown);
    }

    public SymbolEffectProjectile(int effectID, String unlocalisedName, int spellCost, boolean curse, boolean fallsToEarth, String knowledgeKey, int cooldown, boolean isVisible) {
        super(effectID, unlocalisedName, spellCost, curse, fallsToEarth, knowledgeKey, cooldown, isVisible);
    }

    public SymbolEffectProjectile setColor(int color) {
        this.color = color;
        return this;
    }

    public SymbolEffectProjectile setSize(float size) {
        this.size = size;
        return this;
    }

    public SymbolEffectProjectile setTimeToLive(int ticks) {
        this.timetolive = ticks;
        return this;
    }

    public int getColor() {
        return this.color;
    }

    public float getSize() {
        return this.size;
    }

    @Override
    public void perform(World world, EntityPlayer player, int effectLevel) {
        world.func_72889_a((EntityPlayer)null, 1008, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
        float f = 1.0f;
        double motionX = -MathHelper.func_76126_a((float)(player.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
        double motionZ = MathHelper.func_76134_b((float)(player.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
        double motionY = -MathHelper.func_76126_a((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
        EntitySpellEffect entity = new EntitySpellEffect(world, (EntityLivingBase)player, motionX, motionY, motionZ, this, effectLevel);
        if (this.timetolive > 0) {
            entity.setLifeTime(this.timetolive);
        }
        entity.func_70012_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, entity.field_70177_z, entity.field_70125_A);
        entity.func_70107_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
        double d6 = MathHelper.func_76133_a((double)(motionX * motionX + motionY * motionY + motionZ * motionZ));
        entity.accelerationX = motionX / d6 * 0.3;
        entity.accelerationY = motionY / d6 * 0.3;
        entity.accelerationZ = motionZ / d6 * 0.3;
        double d8 = 1.5;
        Vec3 vec3 = player.func_70676_i(1.0f);
        entity.field_70165_t = player.field_70165_t + vec3.field_72450_a * 1.5;
        entity.field_70163_u = player.field_70163_u + (double)player.eyeHeight - (double)0.1f + vec3.field_72448_b * 1.5;
        entity.field_70161_v = player.field_70161_v + vec3.field_72449_c * 1.5;
        world.func_72838_d((Entity)entity);
    }

    public abstract void onCollision(World var1, EntityLivingBase var2, MovingObjectPosition var3, EntitySpellEffect var4);
}

