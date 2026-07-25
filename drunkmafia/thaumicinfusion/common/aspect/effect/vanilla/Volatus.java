/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.config.Configuration
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="volatus")
public class Volatus
extends AspectEffect {
    private final List<Integer> isFlying = new ArrayList<Integer>();
    private int defSize = 10;
    private int tickTime = 1;

    @Override
    public int getCost() {
        return 8;
    }

    @Override
    public void readConfig(Configuration config) {
        super.readConfig(config);
        this.defSize = config.getInt("Default Flying Range", "Volatus", this.defSize, 1, 100, "");
        this.tickTime = config.getInt("Tick Time", "Volatus", this.tickTime, 1, 20, "Delay before the effect ticks again");
    }

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, world.field_73012_v);
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
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
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
        WorldCoordinates pos = this.getPos();
        if (!world.func_147437_c(pos.x, pos.y + 1, pos.z)) {
            return;
        }
        float size = this.getSize(world);
        AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)pos.x, (double)pos.y, (double)pos.z, (double)(pos.x + 1), (double)((float)pos.y + size), (double)(pos.z + 1));
        List list = world.func_72872_a(EntityPlayer.class, axisalignedbb);
        for (EntityPlayer player : world.field_73010_i) {
            if (player == null) continue;
            int playerHash = player.func_70005_c_().hashCode();
            if (list.contains(player)) {
                this.isFlying.add(playerHash);
                player.field_71075_bZ.field_75100_b = true;
                player.func_71016_p();
                continue;
            }
            if (!this.isFlying.contains(playerHash)) continue;
            if (this.isPlayerAboveVolatusBlock(size, player)) {
                return;
            }
            this.isFlying.remove((Object)playerHash);
            player.field_71075_bZ.field_75100_b = false;
            player.func_71016_p();
        }
    }

    boolean isPlayerAboveVolatusBlock(float size, EntityPlayer player) {
        int posZ;
        int posY;
        int posX;
        int y = 0;
        while ((float)y < size && !player.field_70170_p.func_147437_c(posX = (int)player.field_70165_t, posY = (int)(player.field_70163_u - (double)y), posZ = (int)player.field_70161_v)) {
            BlockData data = TIWorldData.getWorldData(player.field_70170_p).getBlock(BlockData.class, new WorldCoordinates(posX, posY, posZ, player.field_71093_bK));
            if (data != null) {
                return true;
            }
            ++y;
        }
        return false;
    }

    float getSize(World world) {
        BlockData data;
        WorldCoordinates pos = this.getPos();
        float ret = this.defSize;
        int curretY = pos.y - 1;
        while (!world.func_147437_c(pos.x, curretY, pos.z) && (data = TIWorldData.getWorldData(world).getBlock(BlockData.class, new WorldCoordinates(pos.x, curretY, pos.z, world.field_73011_w.field_76574_g))) != null && data.hasEffect(Volatus.class)) {
            ret += (float)this.defSize;
            --curretY;
        }
        return ret;
    }
}

