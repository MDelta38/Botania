/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="aer")
public class Aer
extends AspectEffect {
    static long maxCooldown = 2000L;
    long cooldown;

    @Override
    public int getCost() {
        return 2;
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
        if (this.cooldown + maxCooldown < System.currentTimeMillis()) {
            AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.pos.x, (double)this.pos.y, (double)this.pos.z, (double)(this.pos.x + 1), (double)(this.pos.y + 1), (double)(this.pos.z + 1)).func_72314_b(1.0, 1.0, 1.0);
            List players = world.func_72872_a(EntityPlayer.class, axisalignedbb);
            for (EntityPlayer player : players) {
                player.func_70690_d(new PotionEffect(Potion.field_76430_j.func_76396_c(), 100));
            }
            this.cooldown = System.currentTimeMillis();
        }
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, world.func_147439_a(x, y, z), 1);
    }
}

