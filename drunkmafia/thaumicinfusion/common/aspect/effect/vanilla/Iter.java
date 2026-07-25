/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.AspectLink;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="iter")
public class Iter
extends AspectLink {
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
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        if (world.field_72995_K) {
            return;
        }
        WorldCoordinates pos = this.getPos();
        if (pos == null || world.func_147437_c(pos.x, pos.y, pos.z)) {
            return;
        }
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)pos.x, (double)pos.y, (double)pos.z, (double)(pos.x + 1), (double)(pos.y + 2), (double)(pos.z + 1));
        ArrayList ents = (ArrayList)world.func_72872_a(EntityPlayer.class, bb);
        for (EntityPlayer ent : ents) {
            WorldServer destWorld;
            if (!ent.func_70093_af()) continue;
            WorldCoordinates destin = this.getDestination();
            if (destin == null || (destWorld = DimensionManager.getWorld((int)destin.dim)) == null || destWorld.func_147437_c(destin.x, destin.y, destin.z)) {
                return;
            }
            if (destin.dim != ent.field_70170_p.field_73011_w.field_76574_g) {
                ent.func_71027_c(destin.dim);
            }
            ent.func_70634_a((double)((float)destin.x + 0.5f), (double)((float)destin.y + 1.0f), (double)((float)destin.z + 0.5f));
            ent.func_70095_a(false);
        }
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

