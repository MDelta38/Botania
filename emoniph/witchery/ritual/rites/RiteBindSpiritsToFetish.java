/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteBindSpiritsToFetish
extends Rite {
    private final int radius;

    public RiteBindSpiritsToFetish(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepSpiritsToFetish(this));
    }

    private static class StepSpiritsToFetish
    extends RitualStep {
        private final RiteBindSpiritsToFetish rite;

        public StepSpiritsToFetish(RiteBindSpiritsToFetish rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                int r = this.rite.radius;
                int r2 = r * r;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(posX - r), (double)(posY - r), (double)(posZ - r), (double)(posX + r), (double)(posY + r), (double)(posZ + r));
                List entities = world.func_72872_a(EntityCreature.class, bb);
                ArrayList<EntitySpectre> spectreList = new ArrayList<EntitySpectre>();
                ArrayList<EntitySpirit> spiritList = new ArrayList<EntitySpirit>();
                ArrayList<EntityBanshee> bansheeList = new ArrayList<EntityBanshee>();
                ArrayList<EntityPoltergeist> poltergeistList = new ArrayList<EntityPoltergeist>();
                for (Object obj : entities) {
                    if (obj instanceof EntitySpectre) {
                        spectreList.add((EntitySpectre)((Object)obj));
                        continue;
                    }
                    if (obj instanceof EntityPoltergeist) {
                        poltergeistList.add((EntityPoltergeist)((Object)obj));
                        continue;
                    }
                    if (obj instanceof EntityBanshee) {
                        bansheeList.add((EntityBanshee)((Object)obj));
                        continue;
                    }
                    if (!(obj instanceof EntitySpirit)) continue;
                    spiritList.add((EntitySpirit)((Object)obj));
                }
                ItemStack stack = null;
                for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                    if (item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_SCARECROW))) {
                        stack = item.itemstack;
                        break;
                    }
                    if (item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_TREANT_IDOL))) {
                        stack = item.itemstack;
                        break;
                    }
                    if (!item.itemstack.func_77969_a(new ItemStack(Witchery.Blocks.FETISH_WITCHS_LADDER))) continue;
                    stack = item.itemstack;
                    break;
                }
                if (stack == null) {
                    return RitualStep.Result.ABORTED_REFUND;
                }
                int result = InfusedSpiritEffect.tryBindFetish(world, stack, spiritList, spectreList, bansheeList, poltergeistList);
                if (result == 0) {
                    return RitualStep.Result.ABORTED_REFUND;
                }
                if (result == 2) {
                    EntityPlayer deathPlayer = this.findDeathPlayer(world);
                    if (deathPlayer != null) {
                        ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                        ItemGeneral.teleportToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, (Entity)deathPlayer, true);
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, (Entity)deathPlayer, 0.5, 1.5, 16);
                    } else {
                        EntityDeath death = new EntityDeath(world);
                        death.func_70012_b(0.5 + (double)posX, (double)posY + 0.1, 0.5 + (double)posZ, 0.0f, 0.0f);
                        death.func_110163_bv();
                        world.func_72838_d((Entity)death);
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_WITHER_SPAWN, (Entity)death, 0.5, 1.5, 16);
                    }
                } else {
                    EntityItem entity = new EntityItem(world, 0.5 + (double)posX, (double)posY + 1.5, 0.5 + (double)posZ, stack);
                    entity.field_70159_w = 0.0;
                    entity.field_70181_x = 0.3;
                    entity.field_70179_y = 0.0;
                    world.func_72838_d((Entity)entity);
                    ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)entity, 0.5, 1.5, 16);
                }
            }
            return RitualStep.Result.COMPLETED;
        }

        private EntityPlayer findDeathPlayer(World world) {
            for (Object obj : world.field_73010_i) {
                EntityPlayer player = (EntityPlayer)obj;
                if (!ItemDeathsClothes.isFullSetWorn((EntityLivingBase)player) || player.func_71045_bC() == null || player.func_71045_bC().func_77973_b() != Witchery.Items.DEATH_HAND) continue;
                return player;
            }
            return null;
        }
    }
}

