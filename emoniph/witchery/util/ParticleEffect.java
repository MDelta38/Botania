/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TargetPointUtil;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public enum ParticleEffect {
    HUGE_EXPLOSION("hugeexplosion"),
    LARGE_EXPLODE("largeexplode"),
    WATER_BUBBLE("bubble"),
    SUSPENDED("suspended"),
    DEPTH_SUSPEND("depthsuspend"),
    TOWN_AURA("townaura"),
    CRIT("crit"),
    MAGIC_CRIT("magicCrit"),
    SMOKE("smoke"),
    MOB_SPELL("mobSpell"),
    SPELL("spell"),
    INSTANT_SPELL("instantSpell"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    EXPLODE("explode"),
    FLAME("flame"),
    LAVA("lava"),
    FOOTSTEP("footstep"),
    SPLASH("splash"),
    LARGE_SMOKE("largesmoke"),
    CLOUD("cloud"),
    REDDUST("reddust"),
    SNOWBALL_POOF("snowballpoof"),
    DRIP_WATER("dripWater"),
    DRIP_LAVA("dripLava"),
    SNOW_SHOVEL("snowshovel"),
    SLIME("slime"),
    HEART("heart"),
    ICON_CRACK("iconcrack_"),
    TILE_CRACK("tilecrack_"),
    SPELL_COLORED("spell");

    final String particleID;

    private ParticleEffect(String particleID) {
        this.particleID = particleID;
    }

    public String toString() {
        return this.particleID;
    }

    public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range) {
        this.send(sound, world, x, y, z, width, height, range, 0xFFFFFF);
    }

    public void send(SoundEffect sound, World world, double x, double y, double z, double width, double height, int range, int color) {
        if (!world.field_72995_K) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, x, y, z, width, height, color), TargetPointUtil.from(world, x, y, z, range));
        }
    }

    public void send(SoundEffect sound, Entity entity, double width, double height, int range) {
        if (!entity.field_70170_p.field_72995_K) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height), TargetPointUtil.from(entity, range));
        }
    }

    public void send(SoundEffect sound, Entity entity, double width, double height, int range, int color) {
        if (!entity.field_70170_p.field_72995_K) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, entity, width, height, color), TargetPointUtil.from(entity, range));
        }
    }

    public void send(SoundEffect sound, TileEntity tile, double width, double height, int range, int color) {
        if (!tile.func_145831_w().field_72995_K) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(this, sound, 0.5 + (double)tile.field_145851_c, 0.5 + (double)tile.field_145848_d, 0.5 + (double)tile.field_145849_e, width, height, color), TargetPointUtil.from(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, range));
        }
    }

    public void send(SoundEffect sound, World world, Coord center, double width, double height, int range) {
        this.send(sound, world, (double)center.x + 0.5, center.y, (double)center.z + 0.5, width, height, range);
    }
}

