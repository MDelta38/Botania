/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.AspectLink;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="permutatio")
public class Permutatio
extends AspectLink {
    private boolean lastRedstoneSignal;

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, world.field_73012_v);
        }
    }

    @Override
    public int getCost() {
        return 8;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (world.field_72995_K) {
            return;
        }
        WorldCoordinates destin = this.getDestination();
        if (destin == null) {
            world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
            return;
        }
        WorldServer destinationWorld = DimensionManager.getWorld((int)destin.dim);
        boolean power = world.func_72864_z(x, y, z);
        if (power != this.lastRedstoneSignal) {
            this.lastRedstoneSignal = power;
            Block oldBlock = world.func_147439_a(x, y, z);
            Block newBlock = destinationWorld.func_147439_a(destin.x, destin.y, destin.z);
            TileEntity oldTile = world.func_147438_o(x, y, z);
            TileEntity newTile = destinationWorld.func_147438_o(destin.x, destin.y, destin.z);
            int oldMeta = world.func_72805_g(x, y, z);
            int newMeta = destinationWorld.func_72805_g(destin.x, destin.y, destin.z);
            destinationWorld.func_147475_p(destin.x, destin.y, destin.z);
            world.func_147475_p(x, y, z);
            destinationWorld.func_147449_b(destin.x, destin.y, destin.z, Blocks.field_150350_a);
            destinationWorld.func_147465_d(destin.x, destin.y, destin.z, oldBlock, oldMeta, 3);
            world.func_147449_b(x, y, z, Blocks.field_150350_a);
            world.func_147465_d(x, y, z, newBlock, newMeta, 3);
            destinationWorld.func_147464_a(destin.x, destin.y, destin.z, oldBlock, 1);
            if (oldTile != null) {
                destinationWorld.func_147475_p(destin.x, destin.y, destin.z);
                oldTile.func_145829_t();
                destinationWorld.func_147455_a(destin.x, destin.y, destin.z, oldTile);
            }
            if (newTile != null) {
                world.func_147475_p(x, y, z);
                newTile.func_145829_t();
                world.func_147455_a(x, y, z, newTile);
            }
        }
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
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }
}

