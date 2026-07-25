/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.ThaumicInfusion;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="motus")
public class Motus
extends AspectEffect {
    private final ForgeDirection direction = ForgeDirection.NORTH;
    private long cooldown;
    private long maxCooldown = 10000L;
    private int maxSteps = 20;
    private int step;

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public void readConfig(Configuration config) {
        super.readConfig(config);
        this.maxSteps = config.getInt("The amount of blocks that motus is able to push, the larger the amount the more taxing it will be", "Motus", 1, 1, 50, "");
        this.maxCooldown = config.getInt("Cooldown for the effect, stops it from bogging down the CPU if maxing out steps", "Motus", (int)this.maxCooldown, 1000, 1000000, "");
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
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
        if (world.field_72995_K) {
            return;
        }
        if (System.currentTimeMillis() < this.cooldown) {
            return;
        }
        if (world.func_72899_e(x, y, z) && world.func_72864_z(x, y, z)) {
            this.step = 0;
            int airPosition = this.canPushBlock(world, x, y, z, world.func_147439_a(x, y, z));
            ThaumicInfusion.getLogger().info((Object)airPosition);
            if (airPosition != 0) {
                for (int i = airPosition; i >= 0; --i) {
                    int xCoord = x + i * this.direction.offsetX;
                    int yCoord = y + i * this.direction.offsetY;
                    int zCoord = z + i * this.direction.offsetZ;
                    Block block = world.func_147439_a(xCoord, yCoord, zCoord);
                    TileEntity tileEntity = world.func_147438_o(xCoord, yCoord, zCoord);
                    if (block instanceof BlockAir) continue;
                    world.func_147449_b(xCoord, yCoord, zCoord, Blocks.field_150350_a);
                    world.func_147455_a(xCoord, yCoord, zCoord, null);
                    xCoord = x + (i + 1) * this.direction.offsetX;
                    yCoord = y + (i + 1) * this.direction.offsetY;
                    zCoord = z + (i + 1) * this.direction.offsetZ;
                    world.func_147449_b(xCoord, yCoord, zCoord, block);
                    if (tileEntity == null) continue;
                    tileEntity.field_145851_c = xCoord;
                    tileEntity.field_145848_d = yCoord;
                    tileEntity.field_145849_e = zCoord;
                    world.func_147455_a(xCoord, yCoord, zCoord, tileEntity);
                }
            }
            this.cooldown = System.currentTimeMillis() + this.maxCooldown;
        }
    }

    private int canPushBlock(World world, int x, int y, int z, Block block) {
        if (block instanceof BlockAir) {
            return this.step;
        }
        ++this.step;
        return block.func_149688_o().func_76227_m() == 0 && this.step != this.maxSteps ? this.canPushBlock(world, x + this.direction.offsetX, y + this.direction.offsetY, z + this.direction.offsetZ, world.func_147439_a(x + this.direction.offsetX, y + this.direction.offsetY, z + this.direction.offsetZ)) : 0;
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

