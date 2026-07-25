/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IAspectSource
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectSource;

@Effect(aspect="mortuus")
public class Mortuus
extends AspectEffect {
    static final long maxCooldown = 2000L;
    private static final int[] mobs = new int[]{50, 51, 52, 54, 55, 58};
    long cooldown;

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
        WorldCoordinates pos = this.getPos();
        if (world.field_72995_K || world.func_72957_l(pos.x, pos.y, pos.z) > 8 || !world.func_147437_c(pos.x, pos.y + 1, pos.z) || !world.func_147437_c(pos.x, pos.y + 2, pos.z)) {
            return;
        }
        Random rand = world.field_73012_v;
        if (System.currentTimeMillis() > this.cooldown + 2000L && rand.nextInt(1000) == 1 && this.drainAspects(world, Aspect.DEATH)) {
            Entity entity = EntityList.func_75616_a((int)mobs[rand.nextInt(mobs.length)], (World)world);
            entity.func_70107_b((double)pos.x, (double)(pos.y + 1), (double)pos.z);
            world.func_72838_d(entity);
            this.cooldown = System.currentTimeMillis();
        }
    }

    public boolean drainAspects(World world, Aspect aspect) {
        int cost = AspectHandler.getCostOfEffect(aspect);
        for (int x = this.pos.x - 10; x < this.pos.x + 10; ++x) {
            for (int y = this.pos.y - 10; y < this.pos.y + 10; ++y) {
                for (int z = this.pos.z - 10; z < this.pos.z + 10; ++z) {
                    IAspectSource source;
                    TileEntity tileEntity = world.func_147438_o(x, y, z);
                    if (!(tileEntity instanceof IAspectSource) || !(source = (IAspectSource)tileEntity).doesContainerContainAmount(aspect, cost)) continue;
                    source.takeFromContainer(aspect, cost);
                    world.func_72980_b((double)((float)tileEntity.field_145851_c + 0.5f), (double)((float)tileEntity.field_145848_d + 0.5f), (double)((float)tileEntity.field_145849_e + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f, false);
                    return true;
                }
            }
        }
        return false;
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

