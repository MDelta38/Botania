/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockGrassper;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.Sacrifice;
import com.emoniph.witchery.ritual.SacrificeItem;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class SacrificeOptionalItem
extends SacrificeItem {
    public SacrificeOptionalItem(ItemStack optionalSacrifice) {
        super(optionalSacrifice);
    }

    @Override
    public void addDescription(StringBuffer sb) {
        for (ItemStack itemstack : this.itemstacks) {
            sb.append("\u00a78> ");
            if (itemstack.func_77973_b() == Items.field_151068_bn) {
                List list = Items.field_151068_bn.func_77832_l(itemstack);
                if (list != null && !list.isEmpty()) {
                    PotionEffect effect = (PotionEffect)list.get(0);
                    String s = itemstack.func_82833_r();
                    if (effect.func_76458_c() > 0) {
                        s = s + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
                    }
                    if (effect.func_76459_b() > 20) {
                        s = s + " (" + Potion.func_76389_a((PotionEffect)effect) + ")";
                    }
                    sb.append(s);
                } else {
                    sb.append(itemstack.func_82833_r());
                }
            } else {
                sb.append(itemstack.func_82833_r());
            }
            sb.append(" ");
            sb.append(Witchery.resource("witchery.rite.optional"));
            sb.append("\u00a70");
            sb.append(Const.BOOK_NEWLINE);
        }
    }

    @Override
    public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks) {
        boolean found = super.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks);
        return true;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {
        for (ItemStack itemstack : this.itemstacks) {
            steps.add(new StepSacrificeOptionalItem(itemstack, bounds, maxDistance));
        }
    }

    private static class StepSacrificeOptionalItem
    extends SacrificeItem.StepSacrificeItem {
        public StepSacrificeOptionalItem(ItemStack itemstack, AxisAlignedBB bounds, int maxDistance) {
            super(itemstack, bounds, maxDistance);
            this.showMessages = false;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                ItemStack foundItemstack;
                EntityItem entity;
                List itemEntities = world.func_72872_a(EntityItem.class, this.bounds);
                if (Config.instance().traceRites()) {
                    for (Object obj : itemEntities) {
                        entity = (EntityItem)obj;
                        foundItemstack = entity.func_92059_d();
                        Log.instance().traceRite(String.format(" * found: %s, distance: %f", foundItemstack.toString(), Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ)));
                    }
                }
                for (Object obj : itemEntities) {
                    entity = (EntityItem)obj;
                    foundItemstack = entity.func_92059_d();
                    if (!SacrificeItem.isItemEqual(this.itemstack, foundItemstack) || !(Sacrifice.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= (double)this.maxDistance)) continue;
                    boolean skip = false;
                    if (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(foundItemstack) && !Witchery.Items.GENERIC.copyLocationBinding(world, foundItemstack, ritual)) {
                        skip = true;
                    }
                    if (!skip) {
                        ItemStack sacrificedItemstack = ItemStack.func_77944_b((ItemStack)foundItemstack);
                        sacrificedItemstack.field_77994_a = 1;
                        ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord((Entity)entity)));
                        if (foundItemstack.func_77985_e() && foundItemstack.field_77994_a > 1) {
                            --foundItemstack.field_77994_a;
                        } else {
                            world.func_72900_e((Entity)entity);
                        }
                    }
                    if (!skip) {
                        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, (Entity)entity, 0.5, 1.0, 16);
                    } else {
                        ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, (Entity)entity, 0.5, 1.0, 16);
                    }
                    return RitualStep.Result.COMPLETED;
                }
                int radius = 5;
                for (int x = posX - 5; x <= posX + 5; ++x) {
                    for (int z = posZ - 5; z <= posZ + 5; ++z) {
                        BlockGrassper.TileEntityGrassper grassper;
                        ItemStack stack;
                        TileEntity tile;
                        Block blockID = world.func_147439_a(x, posY, z);
                        if (blockID != Witchery.Blocks.GRASSPER || (tile = world.func_147438_o(x, posY, z)) == null || !(tile instanceof BlockGrassper.TileEntityGrassper) || (stack = (grassper = (BlockGrassper.TileEntityGrassper)tile).func_70301_a(0)) == null || !SacrificeItem.isItemEqual(this.itemstack, stack)) continue;
                        boolean skip = false;
                        if (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(stack) && !Witchery.Items.GENERIC.copyLocationBinding(world, stack, ritual)) {
                            skip = true;
                        }
                        if (!skip) {
                            ItemStack sacrificedItemstack = ItemStack.func_77944_b((ItemStack)stack);
                            sacrificedItemstack.field_77994_a = 1;
                            ritual.sacrificedItems.add(new RitualStep.SacrificedItem(sacrificedItemstack, new Coord(tile)));
                            if (stack.func_77985_e() && stack.field_77994_a > 1) {
                                --stack.field_77994_a;
                            } else {
                                grassper.func_70299_a(0, null);
                            }
                        }
                        if (!skip) {
                            ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, world, 0.5 + (double)x, 0.8 + (double)posY, 0.5 + (double)z, 0.5, 1.0, 16);
                        } else {
                            ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, world, 0.5 + (double)x, 0.8 + (double)posY, 0.5 + (double)z, 0.5, 1.0, 16);
                        }
                        return RitualStep.Result.COMPLETED;
                    }
                }
            }
            return RitualStep.Result.COMPLETED;
        }

        @Override
        public String toString() {
            return String.format("StepSacrificeOptionalItem: %s, maxDistance: %d", this.itemstack.toString(), this.maxDistance);
        }
    }
}

