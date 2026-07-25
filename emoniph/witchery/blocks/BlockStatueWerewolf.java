/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockStatueWerewolf
extends BlockBaseContainer {
    public BlockStatueWerewolf() {
        super(Material.field_151576_e, TileEntityStatueWerewolf.class);
        this.func_149752_b(1000.0f);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149769_e);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        switch (MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) {
            case 0: {
                world.func_72921_c(x, y, z, 2, 2);
                break;
            }
            case 1: {
                world.func_72921_c(x, y, z, 5, 2);
                break;
            }
            case 2: {
                world.func_72921_c(x, y, z, 3, 2);
                break;
            }
            case 3: {
                world.func_72921_c(x, y, z, 4, 2);
            }
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K) {
            TileEntityStatueWerewolf statue = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityStatueWerewolf.class);
            if (statue != null) {
                int meta = world.func_72805_g(x, y, z);
                ForgeDirection direction = ForgeDirection.getOrientation((int)meta);
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                ItemStack heldStack = player.func_70694_bm();
                SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0f, 1.0f);
                int level = playerEx.getWerewolfLevel();
                int GOLD_REQUIRED = 3;
                if (level >= 2 && heldStack != null && heldStack.func_77973_b() == Items.field_151043_k && heldStack.field_77994_a >= 3) {
                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.mooncharmcrafted", new Object[0]);
                    heldStack.func_77979_a(3);
                    EntityItem itemEntity = new EntityItem(world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, new ItemStack(Witchery.Items.MOON_CHARM));
                    itemEntity.field_70179_y = 0.0;
                    itemEntity.field_70181_x = 0.0;
                    itemEntity.field_70159_w = 0.0;
                    world.func_72838_d((Entity)itemEntity);
                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_ORB, (Entity)itemEntity, 0.2, 0.2, 16);
                } else {
                    switch (level) {
                        case 0: {
                            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, (Entity)player, 1.0, 1.0, 16);
                            player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(60), 0));
                            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.notworthy", new Object[0]);
                            break;
                        }
                        case 1: {
                            if (heldStack != null && heldStack.func_77973_b() == Items.field_151043_k) {
                                if (heldStack.field_77994_a >= 3) {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level2complete", new Object[0]);
                                    heldStack.func_77979_a(3);
                                    EntityItem itemEntity = new EntityItem(world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, new ItemStack(Witchery.Items.MOON_CHARM));
                                    itemEntity.field_70179_y = 0.0;
                                    itemEntity.field_70181_x = 0.0;
                                    itemEntity.field_70159_w = 0.0;
                                    world.func_72838_d((Entity)itemEntity);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, (Entity)itemEntity, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                    break;
                                }
                                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level2progress", Integer.valueOf(3).toString(), Integer.valueOf(3 - heldStack.field_77994_a).toString());
                                break;
                            }
                            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level2begin", Integer.valueOf(3).toString());
                            break;
                        }
                        case 2: {
                            int MUTTON_REQUIRED = 30;
                            if (heldStack != null && Witchery.Items.GENERIC.itemMuttonRaw.isMatch(heldStack)) {
                                if (heldStack.field_77994_a >= 30) {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level3complete", new Object[0]);
                                    heldStack.func_77979_a(30);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                    break;
                                }
                                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level3progress", Integer.valueOf(30).toString(), Integer.valueOf(30 - heldStack.field_77994_a).toString());
                                break;
                            }
                            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level3begin", Integer.valueOf(30).toString());
                            break;
                        }
                        case 3: {
                            int TONGUES_REQUIRED = 10;
                            if (heldStack != null && Witchery.Items.GENERIC.itemDogTongue.isMatch(heldStack)) {
                                if (heldStack.field_77994_a >= 10) {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level4complete", new Object[0]);
                                    heldStack.func_77979_a(10);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                    break;
                                }
                                ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level4progress", Integer.valueOf(10).toString(), Integer.valueOf(10 - heldStack.field_77994_a).toString());
                                break;
                            }
                            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level4begin", Integer.valueOf(10).toString());
                            break;
                        }
                        case 4: {
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level5begin", new Object[0]);
                                    EntityItem itemEntity = new EntityItem(world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, new ItemStack(Witchery.Items.HORN_OF_THE_HUNT));
                                    itemEntity.field_70179_y = 0.0;
                                    itemEntity.field_70181_x = 0.0;
                                    itemEntity.field_70159_w = 0.0;
                                    world.func_72838_d((Entity)itemEntity);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, (Entity)itemEntity, 0.2, 0.2, 16);
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level5progress", new Object[0]);
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level5complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 5: {
                            int KILLS_REQUIRED = 10;
                            if (playerEx.getWolfmanQuestCounter() >= 10) {
                                playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                            }
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level6begin", Integer.valueOf(10).toString());
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level6progress", Integer.valueOf(10).toString(), Integer.valueOf(10 - playerEx.getWolfmanQuestCounter()).toString());
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level6complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 6: {
                            int PLACES_HOWLED_AT = 16;
                            if (playerEx.getWolfmanQuestCounter() >= 16) {
                                playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                            }
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level7begin", Integer.valueOf(16).toString());
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level7progress", Integer.valueOf(16).toString(), Integer.valueOf(16 - playerEx.getWolfmanQuestCounter()).toString());
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level7complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 7: {
                            int WOLVES_TAMED = 6;
                            if (playerEx.getWolfmanQuestCounter() >= 6) {
                                playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                            }
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level8begin", Integer.valueOf(6).toString());
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level8progress", Integer.valueOf(6).toString(), Integer.valueOf(6 - playerEx.getWolfmanQuestCounter()).toString());
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level8complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 8: {
                            int PIGMEN_KILLED = 30;
                            if (playerEx.getWolfmanQuestCounter() >= 30) {
                                playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                            }
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level9begin", Integer.valueOf(30).toString());
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level9progress", Integer.valueOf(30).toString(), Integer.valueOf(30 - playerEx.getWolfmanQuestCounter()).toString());
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level9complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 9: {
                            boolean PEOPLE_KILLED = true;
                            if (playerEx.getWolfmanQuestCounter() >= 1) {
                                playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                            }
                            switch (playerEx.getWolfmanQuestState()) {
                                case NOT_STATED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level10begin", Integer.valueOf(1).toString());
                                    playerEx.setWolfmanQuestState(ExtendedPlayer.QuestState.STARTED);
                                    break;
                                }
                                case STARTED: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level10progress", Integer.valueOf(1).toString(), Integer.valueOf(1 - playerEx.getWolfmanQuestCounter()).toString());
                                    break;
                                }
                                case COMPLETE: {
                                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level10complete", new Object[0]);
                                    ParticleEffect.REDDUST.send(SoundEffect.RANDOM_LEVELUP, world, (double)direction.offsetX + 0.5 + (double)x, 1.1 + (double)y, 0.5 + (double)z + (double)direction.offsetZ, 0.2, 0.2, 16);
                                    playerEx.increaseWerewolfLevel();
                                }
                            }
                            break;
                        }
                        case 10: {
                            SoundEffect.WITCHERY_MOB_WOLFMAN_LORD.playOnlyTo(player, 1.0f, 1.0f);
                            ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)player, "witchery.werewolf.level10complete", new Object[0]);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public int func_149745_a(Random rand) {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
    }

    public static class TileEntityStatueWerewolf
    extends TileEntity {
        public boolean canUpdate() {
            return false;
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
        }
    }
}

