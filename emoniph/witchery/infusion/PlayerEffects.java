/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.infusion;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class PlayerEffects {
    private static final ArrayList<PlayerEffect> effects = new ArrayList();
    public static final String KEY_EFFECTS = "witchery.effects";
    public static final PlayerEffect IMP_FIRE_TOUCH = new PlayerEffect("witchery.imp.firetouch", (ArrayList)effects){

        @Override
        protected void doUpdate(EntityPlayer player, int worldTicks) {
        }

        @Override
        protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
        }

        @Override
        protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {
            Block block;
            World world = player.field_70170_p;
            if (world.field_73012_v.nextDouble() < 0.2 && (block = BlockUtil.getBlock(world, event.x, event.y, event.z)) != null && block != Blocks.field_150350_a) {
                int par4 = event.x;
                int par5 = event.y;
                int par6 = event.z;
                int par7 = event.face;
                if (par7 == 0) {
                    --par5;
                }
                if (par7 == 1) {
                    ++par5;
                }
                if (par7 == 2) {
                    --par6;
                }
                if (par7 == 3) {
                    ++par6;
                }
                if (par7 == 4) {
                    --par4;
                }
                if (par7 == 5) {
                    ++par4;
                }
                if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
                    par4 = par4 - 1 + world.field_73012_v.nextInt(3);
                    par6 = par6 - 1 + world.field_73012_v.nextInt(3);
                }
                if (world.func_147437_c(par4, par5, par6) && !world.func_147437_c(par4, par5 - 1, par6)) {
                    world.func_72908_a((double)par4 + 0.5, (double)par5 + 0.5, (double)par6 + 0.5, SoundEffect.FIRE_FIRE.toString(), 1.0f, world.field_73012_v.nextFloat() * 0.4f + 0.8f);
                    world.func_147449_b(par4, par5, par6, (Block)Blocks.field_150480_ab);
                }
            }
        }
    };
    public static final PlayerEffect IMP_EVAPORATION = new PlayerEffect("witchery.imp.evaporation", (ArrayList)effects){

        @Override
        protected void doUpdate(EntityPlayer player, int worldTicks) {
            if (player.field_70170_p.field_73012_v.nextInt(5) == 0) {
                int midX = MathHelper.func_76128_c((double)player.field_70165_t);
                int midY = MathHelper.func_76128_c((double)player.field_70163_u);
                int midZ = MathHelper.func_76128_c((double)player.field_70161_v);
                int R = 3;
                int RSq = 9;
                boolean found = false;
                for (int x = midX - 3; x <= midX + 3; ++x) {
                    for (int z = midZ - 3; z <= midZ + 3; ++z) {
                        for (int y = midY + 2; y >= midY - 1; --y) {
                            Block block;
                            if (!(player.func_70092_e((double)x, (double)y, (double)z) <= 9.0) || (block = BlockUtil.getBlock(player.field_70170_p, x, y, z)) != Blocks.field_150355_j && block != Blocks.field_150358_i || !player.field_70170_p.func_147437_c(x, y + 1, z)) continue;
                            player.field_70170_p.func_147468_f(x, y, z);
                            ParticleEffect.EXPLODE.send(SoundEffect.NONE, player.field_70170_p, x, y + 1, z, 1.0, 1.0, 16);
                            found = true;
                        }
                    }
                }
                if (found) {
                    SoundEffect.RANDOM_FIZZ.playAt(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0f, 2.6f + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.8f);
                }
            }
        }

        @Override
        protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
        }

        @Override
        protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {
        }
    };
    public static final PlayerEffect IMP_METLING_TOUCH = new PlayerEffect("witchery.im.meltingtouch", (ArrayList)effects){

        @Override
        protected void doUpdate(EntityPlayer player, int worldTicks) {
        }

        @Override
        protected void doHarvest(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
            ArrayList<ItemStack> newDrops = new ArrayList<ItemStack>();
            for (ItemStack drop : event.drops) {
                ItemStack smeltedDrop = FurnaceRecipes.func_77602_a().func_151395_a(drop);
                if (smeltedDrop != null) {
                    Log.instance().debug("Smelting Touch: " + drop.toString() + " -> " + smeltedDrop.toString());
                    ItemStack smelted = smeltedDrop.func_77946_l();
                    if (player.field_70170_p.field_73012_v.nextDouble() < 0.25) {
                        ++smelted.field_77994_a;
                    }
                    newDrops.add(smelted);
                    continue;
                }
                Log.instance().debug("Smelting Touch: " + drop.toString() + " -> none");
                newDrops.add(drop);
            }
            event.drops.clear();
            for (ItemStack newDrop : newDrops) {
                event.drops.add(newDrop);
            }
        }

        @Override
        protected void doInteract(EntityPlayer player, PlayerInteractEvent event) {
        }
    };
    private static final int TICKS_PER_UPDATE = 20;

    public static void onDeath(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && nbtPlayer.func_74764_b(KEY_EFFECTS)) {
            NBTTagCompound nbtEffects = nbtPlayer.func_74775_l(KEY_EFFECTS);
            for (PlayerEffect effect : effects) {
                effect.removeFrom(nbtEffects);
            }
            if (nbtEffects.func_82582_d()) {
                nbtPlayer.func_82580_o(KEY_EFFECTS);
            }
        }
    }

    public static void onUpdate(EntityPlayer player, long ticks) {
        NBTTagCompound nbtPlayer;
        if (ticks % 20L == 3L && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74764_b(KEY_EFFECTS)) {
            NBTTagCompound nbtEffects = nbtPlayer.func_74775_l(KEY_EFFECTS);
            for (PlayerEffect effect : effects) {
                effect.update(nbtEffects, 20, player);
            }
            if (nbtEffects.func_82582_d()) {
                nbtPlayer.func_82580_o(KEY_EFFECTS);
            }
        }
    }

    public static void onHarvestDrops(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && nbtPlayer.func_74764_b(KEY_EFFECTS)) {
            NBTTagCompound nbtEffects = nbtPlayer.func_74775_l(KEY_EFFECTS);
            for (PlayerEffect effect : effects) {
                effect.harvest(nbtEffects, event, player);
            }
            if (nbtEffects.func_82582_d()) {
                nbtPlayer.func_82580_o(KEY_EFFECTS);
            }
        }
    }

    public static void onInteract(EntityPlayer player, PlayerInteractEvent event) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && nbtPlayer.func_74764_b(KEY_EFFECTS)) {
            NBTTagCompound nbtEffects = nbtPlayer.func_74775_l(KEY_EFFECTS);
            for (PlayerEffect effect : effects) {
                effect.interact(nbtEffects, event, player);
            }
            if (nbtEffects.func_82582_d()) {
                nbtPlayer.func_82580_o(KEY_EFFECTS);
            }
        }
    }

    public static abstract class PlayerEffect {
        protected final String unlocalizedName;

        protected PlayerEffect(String unlocalizedName, ArrayList<PlayerEffect> effects) {
            this.unlocalizedName = unlocalizedName;
            effects.add(this);
        }

        public void interact(NBTTagCompound nbtEffects, PlayerInteractEvent event, EntityPlayer player) {
            if (nbtEffects.func_74764_b(this.unlocalizedName)) {
                this.doInteract(player, event);
            }
        }

        protected abstract void doInteract(EntityPlayer var1, PlayerInteractEvent var2);

        public void harvest(NBTTagCompound nbtEffects, BlockEvent.HarvestDropsEvent event, EntityPlayer player) {
            if (nbtEffects.func_74764_b(this.unlocalizedName)) {
                this.doHarvest(player, event);
            }
        }

        protected abstract void doHarvest(EntityPlayer var1, BlockEvent.HarvestDropsEvent var2);

        public void applyTo(EntityPlayer player, int durationTicks) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            if (nbtPlayer != null) {
                if (!nbtPlayer.func_74764_b(PlayerEffects.KEY_EFFECTS)) {
                    nbtPlayer.func_74782_a(PlayerEffects.KEY_EFFECTS, (NBTBase)new NBTTagCompound());
                }
                NBTTagCompound nbtEffects = nbtPlayer.func_74775_l(PlayerEffects.KEY_EFFECTS);
                nbtEffects.func_74768_a(this.unlocalizedName, durationTicks);
            }
        }

        private void removeFrom(NBTTagCompound nbtEffects) {
            if (nbtEffects.func_74764_b(this.unlocalizedName)) {
                nbtEffects.func_82580_o(this.unlocalizedName);
            }
        }

        private void update(NBTTagCompound nbtEffects, int ticks, EntityPlayer player) {
            if (nbtEffects.func_74764_b(this.unlocalizedName)) {
                int remainingTicks = nbtEffects.func_74762_e(this.unlocalizedName);
                int newTicks = Math.max(remainingTicks - ticks, 0);
                if (newTicks == 0) {
                    this.removeFrom(nbtEffects);
                } else {
                    nbtEffects.func_74768_a(this.unlocalizedName, newTicks);
                    this.doUpdate(player, ticks);
                }
            }
        }

        protected abstract void doUpdate(EntityPlayer var1, int var2);
    }
}

