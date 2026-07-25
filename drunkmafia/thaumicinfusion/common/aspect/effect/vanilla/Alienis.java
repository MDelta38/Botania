/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.common.config.Configuration
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="alienis")
public class Alienis
extends AspectEffect {
    private int size = 10;

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public void readConfig(Configuration config) {
        super.readConfig(config);
        this.size = config.getInt("Size of random tp", "Alienis", this.size, 1, 50, "");
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            this.warpEntity(world, (EntityLivingBase)entity);
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149724_b(World world, int x, int y, int z, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            this.warpEntity(world, (EntityLivingBase)entity);
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149746_a(World world, int x, int y, int z, Entity entity, float dist) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase) {
            this.warpEntity(world, (EntityLivingBase)entity);
        }
    }

    public void warpEntity(World world, EntityLivingBase entity) {
        ChunkCoordinates[] possibleCoords = this.getPossibleWarps(world);
        if (possibleCoords == null || possibleCoords.length == 0) {
            return;
        }
        ChunkCoordinates warp = possibleCoords[world.field_73012_v.nextInt(possibleCoords.length)];
        entity.func_70634_a((double)warp.field_71574_a + 0.5, (double)warp.field_71572_b, (double)warp.field_71573_c + 0.5);
    }

    public ChunkCoordinates[] getPossibleWarps(World world) {
        WorldCoordinates pos = this.getPos();
        ArrayList<ChunkCoordinates> warps = new ArrayList<ChunkCoordinates>();
        for (int x = -this.size + pos.x; x < this.size + pos.x; ++x) {
            for (int y = -this.size + pos.y; y < this.size + pos.y; ++y) {
                for (int z = -this.size + pos.z; z < this.size + pos.z; ++z) {
                    if (world.func_147437_c(x, y - 1, z) || !world.func_147437_c(x, y, z) || !world.func_147437_c(x, y + 1, z)) continue;
                    warps.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        ChunkCoordinates[] retWarps = new ChunkCoordinates[warps.size()];
        warps.toArray(retWarps);
        return retWarps;
    }
}

