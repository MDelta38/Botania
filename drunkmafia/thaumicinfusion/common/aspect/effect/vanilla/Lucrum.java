/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.config.Configuration
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="lucrum")
public class Lucrum
extends AspectEffect {
    private final Map<EntityItem, PathEntity> paths = new HashMap<EntityItem, PathEntity>();
    private int range = 10;
    private int tickTime = 4;

    @Override
    public void readConfig(Configuration config) {
        super.readConfig(config);
        this.range = config.getInt("The range that lucrum can find items to pull", "Lucrum", this.range, 1, 40, "");
        this.tickTime = config.getInt("Tick Time", "Lucrum", this.tickTime, 1, 20, "Delay before the effect ticks again");
    }

    @Override
    public int getCost() {
        return 4;
    }

    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (!world.field_72995_K) {
            this.func_149674_a(world, pos.x, pos.y, pos.z, world.field_73012_v);
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149674_a(World world, int x, int y, int z, Random random) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
        WorldCoordinates coord = this.getPos();
        AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)coord.x, (double)coord.y, (double)coord.z, (double)(coord.x + 1), (double)(coord.y + 1), (double)(coord.z + 1)).func_72314_b(10.0, 10.0, 10.0);
        ArrayList list = (ArrayList)world.func_72872_a(EntityItem.class, axisalignedbb);
        double speed = 0.05;
        for (EntityItem item : list) {
            if (this.isItemNearBlock(item)) continue;
            item.field_70159_w = item.field_70165_t > (double)coord.x ? -speed : speed;
            item.field_70179_y = item.field_70161_v > (double)coord.z ? -speed : speed;
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149695_a(World world, int x, int y, int z, Block block) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
    }

    @OverrideBlock(overrideBlockFunc=false)
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), this.tickTime);
        return false;
    }

    boolean isItemNearBlock(EntityItem item) {
        return this.getPos().getDistanceSquared((int)item.field_70165_t, (int)item.field_70163_u, (int)item.field_70161_v) < 1.0f;
    }
}

