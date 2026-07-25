/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="gelum")
public class Gelum
extends AspectEffect {
    public static long cooldownTimer = 10000L;
    private final int radius = 10;
    private long cooldown;

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, world.field_73012_v);
        }
    }

    @Override
    public int getCost() {
        return 1;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149695_a(World world, int x, int y, int z, Block block) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        return false;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149674_a(World world, int x, int y, int z, Random random) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        if (world.field_72995_K || System.currentTimeMillis() < this.cooldown + cooldownTimer) {
            return;
        }
        for (int xPos = x - this.radius; xPos < x + this.radius; ++xPos) {
            for (int yPos = y - this.radius; yPos < y + this.radius; ++yPos) {
                for (int zPos = z - this.radius; zPos < z + this.radius; ++zPos) {
                    Block block = world.func_147439_a(xPos, yPos, zPos);
                    if (block == null) continue;
                    if (block.func_149688_o() == Material.field_151586_h) {
                        world.func_147449_b(xPos, yPos, zPos, Blocks.field_150432_aD);
                        this.cooldown = System.currentTimeMillis();
                    } else if (block != Blocks.field_150431_aC && world.func_72937_j(xPos, yPos, zPos) && world.func_147437_c(xPos, yPos + 1, zPos)) {
                        world.func_147449_b(xPos, yPos + 1, zPos, Blocks.field_150431_aC);
                        this.cooldown = System.currentTimeMillis();
                    }
                    return;
                }
            }
        }
        this.cooldown = System.currentTimeMillis();
    }
}

