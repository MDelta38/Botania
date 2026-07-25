/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.aspect.entity.InfusedBlockFalling;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.EntitySyncPacketC;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="terra")
public class Terra
extends AspectEffect {
    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            world.func_147464_a(pos.x, pos.y, pos.z, world.func_147439_a(pos.x, pos.y, pos.z), 1);
        }
    }

    @Override
    public int getCost() {
        return 2;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149674_a(World world, int x, int y, int z, Random random) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        WorldCoordinates pos = this.getPos();
        if (!world.func_147437_c(pos.x, pos.y, pos.z) && world.func_147437_c(pos.x, pos.y - 1, pos.z)) {
            InfusedBlockFalling entity = new InfusedBlockFalling(world, (float)pos.x + 0.5f, (float)pos.y + 0.5f, (float)pos.z + 0.5f, Block.func_149682_b((Block)world.func_147439_a(pos.x, pos.y, pos.z)), world.func_72805_g(pos.x, pos.y, pos.z), world.func_147438_o(pos.x, pos.y, pos.z));
            world.func_147475_p(pos.x, pos.y, pos.z);
            world.func_147449_b(pos.x, pos.y, pos.z, Blocks.field_150350_a);
            world.func_72838_d((Entity)entity);
            ChannelHandler.instance().sendToDimension(new EntitySyncPacketC(entity), world.field_73011_w.field_76574_g);
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

