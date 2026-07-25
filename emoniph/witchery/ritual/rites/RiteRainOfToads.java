/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.storage.WorldInfo
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class RiteRainOfToads
extends Rite {
    private final int minRadius;
    private final int maxRadius;
    private final int bolts;

    public RiteRainOfToads(int minRadius, int maxRadius, int bolts) {
        this.minRadius = minRadius;
        this.maxRadius = maxRadius;
        this.bolts = bolts;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int initialStage) {
        steps.add(new StepRainOfToads(this, initialStage));
    }

    private static class StepRainOfToads
    extends RitualStep {
        private final RiteRainOfToads rite;
        private int stage;

        public StepRainOfToads(RiteRainOfToads rite, int initialStage) {
            super(true);
            this.rite = rite;
            this.stage = initialStage;
        }

        @Override
        public int getCurrentStage() {
            return this.stage;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 30L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (ritual.covenSize < 1) {
                EntityPlayer player = ritual.getInitiatingPlayer(world);
                SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                if (player != null) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.coventoosmall", new Object[0]);
                }
                return RitualStep.Result.ABORTED_REFUND;
            }
            ++this.stage;
            switch (this.stage) {
                case 1: {
                    this.spawnBolt(world, posX, posY, posZ);
                    return RitualStep.Result.STARTING;
                }
                case 2: {
                    this.spawnBolt(world, posX, posY, posZ);
                    return RitualStep.Result.STARTING;
                }
                case 3: {
                    this.spawnBolt(world, posX, posY, posZ);
                    return RitualStep.Result.STARTING;
                }
                case 4: {
                    if (world instanceof WorldServer && !world.func_72896_J()) {
                        WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
                        int i = (300 + world.field_73012_v.nextInt(600)) * 20;
                        worldinfo.func_76080_g(i);
                        worldinfo.func_76084_b(true);
                    }
                    this.spawnBolt(world, posX, posY, posZ);
                    return RitualStep.Result.STARTING;
                }
            }
            int activeRadius = this.rite.maxRadius - this.rite.minRadius;
            for (int n = 0; n < world.field_73012_v.nextInt(this.rite.bolts) + 8; ++n) {
                int z;
                int y;
                int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                if (ax > activeRadius) {
                    ax += this.rite.minRadius * 2;
                }
                int x = posX - this.rite.maxRadius + ax;
                int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                if (az > activeRadius) {
                    az += this.rite.minRadius * 2;
                }
                if (!world.func_147437_c(x, y = world.func_72825_h(x, z = posZ - this.rite.maxRadius + az), z)) continue;
                EntityToad toad = new EntityToad(world);
                toad.func_70012_b(x, y + 8 + world.field_73012_v.nextInt(7), z, 0.0f, 0.0f);
                toad.setTimeToLive(30, true);
                world.func_72838_d((Entity)toad);
            }
            return this.stage < 200 ? RitualStep.Result.UPKEEP : RitualStep.Result.COMPLETED;
        }

        private void spawnBolt(World world, int posX, int posY, int posZ) {
            int activeRadius = this.rite.maxRadius - this.rite.minRadius;
            int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (ax > activeRadius) {
                ax += this.rite.minRadius * 2;
            }
            int x = posX - this.rite.maxRadius + ax;
            int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (az > activeRadius) {
                az += this.rite.minRadius * 2;
            }
            int z = posZ - this.rite.maxRadius + az;
            EntityLightningBolt bolt = new EntityLightningBolt(world, (double)x, (double)posY, (double)z);
            world.func_72942_c((Entity)bolt);
        }
    }
}

