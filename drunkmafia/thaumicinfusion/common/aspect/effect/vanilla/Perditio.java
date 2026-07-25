/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="perditio")
public class Perditio
extends AspectEffect {
    Random rand = new Random();

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, world.field_73012_v);
        }
    }

    @Override
    public int getCost() {
        return 4;
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
            if (ent.func_70093_af()) continue;
            this.explode(world);
            return;
        }
    }

    void explode(World world) {
        if (this.rand.nextInt(20) == this.rand.nextInt(20) && !world.field_72995_K) {
            world.func_72876_a(null, (double)this.getPos().x, (double)this.getPos().y, (double)this.getPos().z, 4.0f, true);
            TIWorldData worldData = TIWorldData.getWorldData(world);
            BlockData data = worldData.getBlock(BlockData.class, this.getPos());
            if (data != null) {
                data.removeEffect(this.getClass());
                if (data.getEffects().length == 0) {
                    worldData.removeData(BlockData.class, this.getPos(), true);
                }
            }
        }
    }
}

