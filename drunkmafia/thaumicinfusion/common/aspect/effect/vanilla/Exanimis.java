/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="exanimis")
public class Exanimis
extends AspectEffect {
    private final List<String> deadPlayers = new ArrayList<String>();

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
        for (int i = 0; i < this.deadPlayers.size(); ++i) {
            EntityPlayer player = world.func_72924_a(this.deadPlayers.get(i));
            if (player == null || player.field_70128_L) continue;
            this.deadPlayers.remove(i);
            player.func_70634_a((double)((float)this.pos.x + 0.5f), (double)((float)this.pos.y + 1.0f), (double)((float)this.pos.z + 0.5f));
        }
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)this.pos.x, (double)this.pos.y, (double)this.pos.z, (double)(this.pos.x + 1), (double)(this.pos.y + 2), (double)(this.pos.z + 1));
        ArrayList ents = (ArrayList)world.func_72872_a(EntityPlayer.class, bb);
        for (EntityPlayer ent : ents) {
            if (!ent.field_70128_L || this.deadPlayers.contains(ent.func_70005_c_())) continue;
            this.deadPlayers.add(ent.func_70005_c_());
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
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        return false;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }
}

