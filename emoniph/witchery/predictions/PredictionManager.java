/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.predictions.Prediction;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.TimeUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class PredictionManager {
    private static final PredictionManager INSTANCE = new PredictionManager();
    public static final String PREDICTION_ROOT_KEY = "WITCPredict";
    public static final String PREDICTION_LIST_KEY = "WITCPreList";
    public static final String PREDICTION_ID_KEY = "WITCPreID";
    public static final String PREDICTION_TIME_KEY = "WITCPreTime";
    public static final String PREDICTION_PLAYER_ATTUNED_KEY = "WITCFTeller";
    private static final int MAX_CONCURRENT_PREDICTIONS = 1;
    public static final long PREDICTION_EXTREME_DURATION_IN_TICKS = 36000L;
    public static final long PREDICTION_DURATION_IN_TICKS = 9600L;
    public static final int RECHARGE_PERIOD_MILLISECS = 100;
    private final Hashtable<Integer, Prediction> predictions = new Hashtable();

    public static PredictionManager instance() {
        return INSTANCE;
    }

    private PredictionManager() {
    }

    public void addPrediction(Prediction prediction) {
        this.predictions.put(prediction.predictionID, prediction);
    }

    public void setFortuneTeller(EntityPlayer player, boolean active) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null) {
            nbtPlayer.func_74757_a(PREDICTION_PLAYER_ATTUNED_KEY, active);
        }
    }

    public void makePrediction(EntityPlayer player, EntityPlayer fortuneTeller, boolean sendChatMessage) {
        block10: {
            boolean gotPrediction;
            block11: {
                HashSet<Integer> currentPredictions;
                NBTTagCompound nbtPlayer;
                block12: {
                    NBTTagCompound nbtRoot;
                    NBTTagCompound nbtTeller;
                    if (player.field_70170_p.field_72995_K) break block10;
                    gotPrediction = false;
                    if (!(player.field_71075_bZ.field_75098_d || (nbtTeller = Infusion.getNBT((Entity)fortuneTeller)) != null && nbtTeller.func_74764_b(PREDICTION_PLAYER_ATTUNED_KEY) && nbtTeller.func_74767_n(PREDICTION_PLAYER_ATTUNED_KEY))) {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.prediction.unskilled", new Object[0]);
                        return;
                    }
                    nbtPlayer = Infusion.getNBT((Entity)player);
                    if (nbtPlayer == null) break block11;
                    if (fortuneTeller.func_70005_c_().equalsIgnoreCase("emoniph") && fortuneTeller.func_70093_af()) {
                        this.clearPredictions(player);
                    }
                    if ((currentPredictions = this.getPlayerPredictionIDs(player)).size() >= 1) break block12;
                    ArrayList<Prediction> possiblePredictions = new ArrayList<Prediction>();
                    for (Prediction prediction : this.predictions.values()) {
                        if (currentPredictions.contains(prediction.predictionID) || !prediction.isPredictionPossible(player.field_70170_p, player)) continue;
                        possiblePredictions.add(prediction);
                    }
                    if (possiblePredictions.size() <= 0) break block11;
                    if (!nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
                        nbtPlayer.func_74782_a(PREDICTION_ROOT_KEY, (NBTBase)new NBTTagCompound());
                    }
                    if (!(nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY)).func_74764_b(PREDICTION_LIST_KEY)) {
                        nbtRoot.func_74782_a(PREDICTION_LIST_KEY, (NBTBase)new NBTTagList());
                    }
                    NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
                    Prediction prediction = PredictionManager.getRandomItem(player.field_70170_p.field_73012_v, possiblePredictions);
                    if (prediction == null) break block11;
                    NBTTagCompound nbtPrediction = prediction.createTagCompound(player.field_70170_p);
                    nbtList.func_74742_a((NBTBase)nbtPrediction);
                    gotPrediction = true;
                    if (!sendChatMessage) break block11;
                    ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, (ICommandSender)player, prediction.getTranslationKey(), player.func_70005_c_());
                    break block11;
                }
                gotPrediction = true;
                if (sendChatMessage) {
                    block1: for (int predictionID : currentPredictions) {
                        Prediction prediction = this.predictions.get(predictionID);
                        if (prediction != null) {
                            ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, (ICommandSender)player, prediction.getTranslationKey(), player.func_70005_c_());
                            continue;
                        }
                        NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
                        NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
                        for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                            NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                            if (predictionID != nbtPrediction.func_74762_e(PREDICTION_ID_KEY)) continue;
                            nbtList.func_74744_a(i);
                            continue block1;
                        }
                    }
                }
            }
            if (!gotPrediction && sendChatMessage) {
                ChatUtil.sendTranslated(EnumChatFormatting.DARK_PURPLE, (ICommandSender)player, "witchery.prediction.none", player.func_70005_c_());
            }
        }
    }

    private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1Collection) {
        return PredictionManager.getRandomItem(par0Random, par1Collection, PredictionManager.getTotalWeight(par1Collection));
    }

    private static int getTotalWeight(ArrayList<Prediction> par0ArrayOfWeightedRandomItem) {
        int i = 0;
        ArrayList<Prediction> aweightedrandomitem1 = par0ArrayOfWeightedRandomItem;
        int j = par0ArrayOfWeightedRandomItem.size();
        for (int k = 0; k < j; ++k) {
            Prediction weightedrandomitem = aweightedrandomitem1.get(k);
            i = (int)((double)i + weightedrandomitem.itemWeight);
        }
        return i;
    }

    private static Prediction getRandomItem(Random par0Random, ArrayList<Prediction> par1ArrayOfWeightedRandomItem, int par2) {
        if (par2 <= 0) {
            throw new IllegalArgumentException();
        }
        int j = par0Random.nextInt(par2);
        ArrayList<Prediction> aweightedrandomitem1 = par1ArrayOfWeightedRandomItem;
        int k = par1ArrayOfWeightedRandomItem.size();
        for (int l = 0; l < k; ++l) {
            Prediction weightedrandomitem = aweightedrandomitem1.get(l);
            if ((j = (int)((double)j - weightedrandomitem.itemWeight)) >= 0) continue;
            return weightedrandomitem;
        }
        return null;
    }

    private void clearPredictions(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            while (nbtList.func_74745_c() > 0) {
                nbtList.func_74744_a(0);
            }
        }
    }

    private HashSet<Integer> getPlayerPredictionIDs(EntityPlayer player) {
        HashSet<Integer> currentPredictions = new HashSet<Integer>();
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                int predictionID = nbtPrediction.func_74762_e(PREDICTION_ID_KEY);
                currentPredictions.add(predictionID);
            }
        }
        return currentPredictions;
    }

    public void checkIfFulfilled(EntityPlayer player, LivingHurtEvent event) {
        NBTTagCompound nbtPlayer;
        if (!player.field_70170_p.field_72995_K && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            World world = player.field_70170_p;
            long currentTime = TimeUtil.getServerTimeInTicks();
            ArrayList<Integer> tagsToRemove = new ArrayList<Integer>();
            for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                boolean veryOld;
                NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                int predictionID = nbtPrediction.func_74762_e(PREDICTION_ID_KEY);
                long predictionTime = nbtPrediction.func_74763_f(PREDICTION_TIME_KEY);
                Prediction prediction = this.predictions.get(predictionID);
                boolean pastDue = prediction != null && prediction.isPredictionPastDue(predictionTime, currentTime);
                boolean bl = veryOld = currentTime - predictionTime > 36000L;
                if (!prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) continue;
                tagsToRemove.add(i);
            }
            for (int j = tagsToRemove.size() - 1; j >= 0; --j) {
                nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
            }
        }
    }

    public void checkIfFulfilled(EntityPlayer player, PlayerInteractEvent event) {
        NBTTagCompound nbtPlayer;
        if (!player.field_70170_p.field_72995_K && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            World world = player.field_70170_p;
            long currentTime = TimeUtil.getServerTimeInTicks();
            ArrayList<Integer> tagsToRemove = new ArrayList<Integer>();
            for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                boolean veryOld;
                NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                int predictionID = nbtPrediction.func_74762_e(PREDICTION_ID_KEY);
                long predictionTime = nbtPrediction.func_74763_f(PREDICTION_TIME_KEY);
                Prediction prediction = this.predictions.get(predictionID);
                boolean pastDue = prediction != null && prediction.isPredictionPastDue(predictionTime, currentTime);
                boolean bl = veryOld = currentTime - predictionTime > 36000L;
                if (!prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) continue;
                tagsToRemove.add(i);
            }
            for (int j = tagsToRemove.size() - 1; j >= 0; --j) {
                nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
            }
        }
    }

    public void checkIfFulfilled(EntityPlayer player, BlockEvent.HarvestDropsEvent event) {
        NBTTagCompound nbtPlayer;
        if (!player.field_70170_p.field_72995_K && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            World world = player.field_70170_p;
            long currentTime = TimeUtil.getServerTimeInTicks();
            ArrayList<Integer> tagsToRemove = new ArrayList<Integer>();
            for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                boolean veryOld;
                NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                int predictionID = nbtPrediction.func_74762_e(PREDICTION_ID_KEY);
                long predictionTime = nbtPrediction.func_74763_f(PREDICTION_TIME_KEY);
                Prediction prediction = this.predictions.get(predictionID);
                boolean pastDue = prediction != null && prediction.isPredictionPastDue(predictionTime, currentTime);
                boolean bl = veryOld = currentTime - predictionTime > 36000L;
                if (!prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) continue;
                tagsToRemove.add(i);
            }
            for (int j = tagsToRemove.size() - 1; j >= 0; --j) {
                nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
            }
        }
    }

    public void checkIfFulfilled(EntityPlayer player, LivingEvent.LivingUpdateEvent event) {
        NBTTagCompound nbtPlayer;
        if (!player.field_70170_p.field_72995_K && (nbtPlayer = Infusion.getNBT((Entity)player)) != null && nbtPlayer.func_74764_b(PREDICTION_ROOT_KEY)) {
            NBTTagCompound nbtRoot = nbtPlayer.func_74775_l(PREDICTION_ROOT_KEY);
            NBTTagList nbtList = nbtRoot.func_150295_c(PREDICTION_LIST_KEY, 10);
            World world = player.field_70170_p;
            long currentTime = TimeUtil.getServerTimeInTicks();
            ArrayList<Integer> tagsToRemove = new ArrayList<Integer>();
            for (int i = 0; i < nbtList.func_74745_c(); ++i) {
                boolean veryOld;
                NBTTagCompound nbtPrediction = nbtList.func_150305_b(i);
                int predictionID = nbtPrediction.func_74762_e(PREDICTION_ID_KEY);
                long predictionTime = nbtPrediction.func_74763_f(PREDICTION_TIME_KEY);
                Prediction prediction = this.predictions.get(predictionID);
                boolean pastDue = prediction != null && prediction.isPredictionPastDue(predictionTime, currentTime);
                boolean bl = veryOld = currentTime - predictionTime > 36000L;
                if (prediction == null) {
                    Log.instance().debug(String.format("Removing prediction %d from player %s because it is not registered", predictionID, player.toString()));
                    tagsToRemove.add(i);
                    continue;
                }
                if (prediction.checkIfFulfilled(player.field_70170_p, player, event, pastDue, veryOld)) {
                    tagsToRemove.add(i);
                    continue;
                }
                if (!pastDue || !prediction.shouldTrySelfFulfill(world, player) || !prediction.doSelfFulfillment(world, player)) continue;
                tagsToRemove.add(i);
            }
            for (int j = tagsToRemove.size() - 1; j >= 0; --j) {
                nbtList.func_74744_a(((Integer)tagsToRemove.get(j)).intValue());
            }
        }
    }
}

