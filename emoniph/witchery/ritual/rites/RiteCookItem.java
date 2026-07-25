/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;

public class RiteCookItem
extends Rite {
    private final float radius;
    private final double burnChance;

    public RiteCookItem(float radius, double burnChance) {
        this.radius = radius;
        this.burnChance = burnChance;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepCookItem(this));
    }

    private static class StepCookItem
    extends RitualStep {
        private final RiteCookItem rite;

        public StepCookItem(RiteCookItem rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                ArrayList<EntityItem> items = this.rite.getItemsInRadius(world, posX, posY, posZ, this.rite.radius);
                int count = 0;
                for (EntityItem item : items) {
                    ItemStack cookedStack = FurnaceRecipes.func_77602_a().func_151395_a(item.func_92059_d());
                    if (cookedStack == null || !(cookedStack.func_77973_b() instanceof ItemFood) || item.func_92059_d().field_77994_a <= 0) continue;
                    int size = item.func_92059_d().field_77994_a;
                    int burnCount = 0;
                    for (int i = 0; i < size; ++i) {
                        if (!(world.field_73012_v.nextDouble() < this.rite.burnChance)) continue;
                        ++burnCount;
                    }
                    item.func_70106_y();
                    if (size - burnCount > 0) {
                        cookedStack.field_77994_a = size - burnCount;
                        EntityItem cookedEntity = new EntityItem(world, (double)posX, (double)posY + 0.05, (double)posZ, cookedStack);
                        cookedEntity.field_70159_w = 0.0;
                        cookedEntity.field_70179_y = 0.0;
                        world.func_72838_d((Entity)cookedEntity);
                    }
                    if (burnCount > 0) {
                        EntityItem burntEntity = new EntityItem(world, (double)posX, (double)posY + 0.05, (double)posZ, new ItemStack(Items.field_151044_h, burnCount, 1));
                        burntEntity.field_70159_w = 0.0;
                        burntEntity.field_70179_y = 0.0;
                        world.func_72838_d((Entity)burntEntity);
                    }
                    ++count;
                }
                if (count == 0) {
                    return RitualStep.Result.ABORTED_REFUND;
                }
                ParticleEffect.FLAME.send(SoundEffect.MOB_GHAST_FIREBALL, world, posX, posY, posZ, 3.0, 2.0, 16);
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

