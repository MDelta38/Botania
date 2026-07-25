/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.predictions.Prediction;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Log;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PredictionWet
extends Prediction {
    public PredictionWet(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
    }

    @Override
    public boolean doSelfFulfillment(World world, EntityPlayer player) {
        int FALL_DISTANCE = 3;
        boolean RADIUS = true;
        int x0 = MathHelper.func_76128_c((double)player.field_70165_t);
        int y0 = MathHelper.func_76128_c((double)player.field_70163_u) - 1;
        int z0 = MathHelper.func_76128_c((double)player.field_70161_v);
        if (!world.field_72995_K && y0 > 5 && !world.field_73011_w.field_76575_d) {
            int z;
            int x;
            int dirtCount = 0;
            for (x = x0 - 1; x <= x0 + 1; ++x) {
                for (z = z0 - 1; z <= z0 + 1; ++z) {
                    Material material = world.func_147439_a(x, y0, z).func_149688_o();
                    if (material != Material.field_151578_c && material != Material.field_151577_b) continue;
                    ++dirtCount;
                }
            }
            if ((double)dirtCount == Math.pow(3.0, 2.0)) {
                for (x = x0 - 1; x <= x0 + 1; ++x) {
                    for (z = z0 - 1; z <= z0 + 1; ++z) {
                        for (int y = y0; y > y0 - 3; --y) {
                            if (y == y0) {
                                world.func_147449_b(x, y, z, Blocks.field_150351_n);
                                continue;
                            }
                            if (!BlockProtect.canBreak(world.func_147439_a(x, y, z), world)) continue;
                            world.func_147449_b(x, y, z, Blocks.field_150355_j);
                        }
                    }
                }
                Log.instance().debug(String.format("Prediction for getting wet has been forced", new Object[0]));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld) {
        if (player.func_70026_G()) {
            Log.instance().debug(String.format("Prediction for WET fulfilled as predicted", new Object[0]));
            return true;
        }
        return false;
    }
}

