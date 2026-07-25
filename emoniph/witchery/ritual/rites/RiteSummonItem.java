/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.predictions.PredictionManager;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteSummonItem
extends Rite {
    private final ItemStack itemToSummon;
    private final Binding binding;

    public RiteSummonItem(ItemStack itemToSummon, Binding binding) {
        this.itemToSummon = itemToSummon;
        this.binding = binding;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepSummonItem(this));
    }

    private static class StepSummonItem
    extends RitualStep {
        private final RiteSummonItem rite;

        public StepSummonItem(RiteSummonItem rite) {
            super(false);
            this.rite = rite;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            EntityPlayer player;
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (world.field_72995_K) return RitualStep.Result.COMPLETED;
            ItemStack itemstack = ItemStack.func_77944_b((ItemStack)this.rite.itemToSummon);
            if (this.rite.binding == Binding.LOCATION) {
                Witchery.Items.GENERIC.bindToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, world.field_73011_w.func_80007_l(), itemstack);
            } else if (this.rite.binding == Binding.ENTITY) {
                int r = 4;
                EntityPlayer target = null;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - 4), (double)posY, (double)(posZ - 4), (double)(posX + 4), (double)(posY + 1), (double)(posZ + 4));
                for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
                    EntityPlayer player2 = (EntityPlayer)obj;
                    if (!(Coord.distance(player2.field_70165_t, player2.field_70163_u, player2.field_70161_v, posX, posY, posZ) <= 4.0)) continue;
                    target = player2;
                }
                if (target != null) {
                    bounds = AxisAlignedBB.func_72330_a((double)(posX - 4), (double)posY, (double)(posZ - 4), (double)(posX + 4), (double)(posY + 1), (double)(posZ + 4));
                    for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                        EntityLiving entity = (EntityLiving)obj;
                        if (!(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) <= 4.0)) continue;
                        target = entity;
                    }
                }
                if (target == null) return RitualStep.Result.ABORTED_REFUND;
                Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(itemstack, null, (Entity)target, false, (Integer)1);
            } else if (this.rite.binding == Binding.PLAYER) {
                int r = 4;
                EntityPlayer target = null;
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(posX - 4), (double)posY, (double)(posZ - 4), (double)(posX + 4), (double)(posY + 1), (double)(posZ + 4));
                for (Object obj : world.func_72872_a(EntityPlayer.class, bounds)) {
                    EntityPlayer player3 = (EntityPlayer)obj;
                    if (!(Coord.distance(player3.field_70165_t, player3.field_70163_u, player3.field_70161_v, posX, posY, posZ) <= 4.0)) continue;
                    target = player3;
                }
                if (target == null) return RitualStep.Result.ABORTED_REFUND;
                NBTTagCompound nbtRoot = new NBTTagCompound();
                nbtRoot.func_74778_a("WITCBoundPlayer", target.func_70005_c_());
                itemstack.func_77982_d(nbtRoot);
            } else if (this.rite.binding == Binding.COPY_LOCATION) {
                for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                    if (!Witchery.Items.GENERIC.hasLocationBinding(item.itemstack)) continue;
                    Witchery.Items.GENERIC.copyLocationBinding(item.itemstack, itemstack);
                    break;
                }
            }
            if (itemstack.func_77973_b() == Item.func_150898_a((Block)Witchery.Blocks.CRYSTAL_BALL) && (player = ritual.getInitiatingPlayer(world)) != null) {
                PredictionManager.instance().setFortuneTeller(player, true);
            }
            EntityItem entity = new EntityItem(world, 0.5 + (double)posX, (double)posY + 1.5, 0.5 + (double)posZ, itemstack);
            entity.field_70159_w = 0.0;
            entity.field_70181_x = 0.3;
            entity.field_70179_y = 0.0;
            world.func_72838_d((Entity)entity);
            ParticleEffect.SPELL.send(SoundEffect.RANDOM_FIZZ, (Entity)entity, 0.5, 0.5, 16);
            return RitualStep.Result.COMPLETED;
        }
    }

    public static enum Binding {
        NONE,
        LOCATION,
        ENTITY,
        COPY_LOCATION,
        PLAYER;

    }
}

