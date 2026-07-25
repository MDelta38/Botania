/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteCurseCreature
extends Rite {
    private final boolean curse;
    private final int level;
    private final String curseType;

    public RiteCurseCreature(boolean curse, String curseType, int level) {
        this.curse = curse;
        this.level = level;
        this.curseType = curseType;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepCurseCreature(this));
    }

    private static class StepCurseCreature
    extends RitualStep {
        private final RiteCurseCreature rite;
        private static final int CURSE_MASTER_BONUS_LEVELS = 1;

        public StepCurseCreature(RiteCurseCreature rite) {
            super(false);
            this.rite = rite;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            if (ticks % 20L != 0L) {
                return RitualStep.Result.STARTING;
            }
            if (!world.field_72995_K) {
                int levelBuff;
                boolean complete = false;
                boolean cursed = false;
                EntityPlayer curseMasterPlayer = ritual.getInitiatingPlayer(world);
                int n = levelBuff = curseMasterPlayer != null && Familiar.hasActiveCurseMasteryFamiliar(curseMasterPlayer) ? 1 : 0;
                if (ritual.covenSize == 6) {
                    levelBuff += 2;
                } else if (ritual.covenSize >= 3) {
                    ++levelBuff;
                }
                for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                    int currentLevel;
                    NBTTagCompound nbtTag;
                    if (item.itemstack.func_77973_b() != Witchery.Items.TAGLOCK_KIT || item.itemstack.func_77960_j() != 1) continue;
                    EntityLivingBase entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, item.itemstack, 1);
                    if (entity == null) break;
                    NBTTagCompound nBTTagCompound = nbtTag = entity instanceof EntityPlayer ? Infusion.getNBT((Entity)entity) : entity.getEntityData();
                    if (nbtTag == null) break;
                    int n2 = currentLevel = nbtTag.func_74764_b(this.rite.curseType) ? nbtTag.func_74762_e(this.rite.curseType) : 0;
                    if (this.rite.curse) {
                        boolean isImmune;
                        EntityWitchHunter.blackMagicPerformed(curseMasterPlayer);
                        boolean bl = isImmune = ItemHunterClothes.isCurseProtectionActive(entity) && (this.rite.curseType.equals("witcheryCursed") || this.rite.curseType.equals("witcheryWakingNightmare"));
                        if (!isImmune) {
                            isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this.rite);
                        }
                        if (!isImmune && !Witchery.Items.POPPET.voodooProtectionActivated(curseMasterPlayer, null, entity, levelBuff > 0 ? 3 : 1)) {
                            nbtTag.func_74768_a(this.rite.curseType, Math.max(this.rite.level + levelBuff, currentLevel));
                            cursed = true;
                            if (entity instanceof EntityPlayer) {
                                Infusion.syncPlayer(entity.field_70170_p, (EntityPlayer)entity);
                            }
                        }
                        if (isImmune) {
                            if (curseMasterPlayer == null) break;
                            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)curseMasterPlayer, "witchery.rite.blackmagicdampening", new Object[0]);
                            break;
                        }
                        complete = true;
                        break;
                    }
                    int newLevel = 0;
                    if (currentLevel > 0) {
                        if (this.rite.level + levelBuff > currentLevel) {
                            newLevel = world.field_73012_v.nextInt(20) == 0 ? currentLevel + 1 : 0;
                        } else if (this.rite.level + levelBuff < currentLevel) {
                            newLevel = world.field_73012_v.nextInt(4) == 0 ? 0 : currentLevel + 1;
                        } else {
                            int n3 = newLevel = world.field_73012_v.nextInt(4) == 0 ? currentLevel + 1 : 0;
                        }
                    }
                    if (newLevel == 0) {
                        if (nbtTag.func_74764_b(this.rite.curseType)) {
                            nbtTag.func_82580_o(this.rite.curseType);
                        }
                        if (entity.func_70644_a(Potion.field_76436_u)) {
                            entity.func_82170_o(Potion.field_76436_u.field_76415_H);
                        }
                        if (entity.func_70644_a(Potion.field_76437_t)) {
                            entity.func_82170_o(Potion.field_76437_t.field_76415_H);
                        }
                        if (entity.func_70644_a(Potion.field_76440_q)) {
                            entity.func_82170_o(Potion.field_76440_q.field_76415_H);
                        }
                        if (entity.func_70644_a(Potion.field_76419_f)) {
                            entity.func_82170_o(Potion.field_76419_f.field_76415_H);
                        }
                        if (entity.func_70644_a(Potion.field_76421_d)) {
                            entity.func_82170_o(Potion.field_76421_d.field_76415_H);
                        }
                    } else {
                        nbtTag.func_74768_a(this.rite.curseType, newLevel);
                        cursed = true;
                    }
                    if (entity instanceof EntityPlayer) {
                        Infusion.syncPlayer(entity.field_70170_p, (EntityPlayer)entity);
                    }
                    complete = true;
                    break;
                }
                if (complete) {
                    if (cursed) {
                        ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5 + (double)posX, 0.1 + (double)posY, 0.5 + (double)posZ, 1.0, 2.0, 16);
                    } else {
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_LEVELUP, world, 0.5 + (double)posX, 0.1 + (double)posY, 0.5 + (double)posZ, 1.0, 2.0, 16);
                    }
                } else {
                    return RitualStep.Result.ABORTED_REFUND;
                }
            }
            return RitualStep.Result.COMPLETED;
        }
    }
}

