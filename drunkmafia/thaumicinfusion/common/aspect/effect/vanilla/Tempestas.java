/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="tempestas")
public class Tempestas
extends AspectEffect {
    @Override
    @OverrideBlock(overrideBlockFunc=false)
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, new Random());
        }
    }

    @Override
    public int getCost() {
        return 1;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        if (world.func_72896_J() && world.func_72937_j(x, y, z) && rand.nextInt(50) == rand.nextInt(50)) {
            world.func_72838_d((Entity)new EntityLightningBolt(world, (double)x, (double)(y + 1), (double)z));
        }
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 50);
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
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }
}

