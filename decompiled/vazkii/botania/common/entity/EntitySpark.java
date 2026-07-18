/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.common.lib.PlayerHandler
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import baubles.common.lib.PlayerHandler;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public class EntitySpark
extends Entity
implements ISparkEntity {
    private static final int TRANSFER_RATE = 1000;
    private static final String TAG_UPGRADE = "upgrade";
    private static final String TAG_INVIS = "invis";
    public static final int INVISIBILITY_DATA_WATCHER_KEY = 27;
    Set<ISparkEntity> transfers = Collections.newSetFromMap(new WeakHashMap());
    int removeTransferants = 2;
    boolean firstTick = false;

    public EntitySpark(World world) {
        super(world);
        this.field_70178_ae = true;
    }

    protected void func_70088_a() {
        this.func_70105_a(0.1f, 0.5f);
        this.field_70180_af.func_75682_a(27, (Object)0);
        this.field_70180_af.func_75682_a(28, (Object)0);
        this.field_70180_af.func_82708_h(27);
        this.field_70180_af.func_82708_h(28);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        ISparkAttachable tile = this.getAttachedTile();
        if (tile == null) {
            if (!this.field_70170_p.field_72995_K) {
                this.func_70106_y();
            }
            return;
        }
        boolean first = this.field_70170_p.field_72995_K && !this.firstTick;
        int upgrade = this.getUpgrade();
        List<ISparkEntity> allSparks = null;
        if (first || upgrade == 2 || upgrade == 3) {
            allSparks = SparkHelper.getSparksAround(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        if (first) {
            first = true;
        }
        Collection<ISparkEntity> transfers = this.getTransfers();
        if (upgrade != 0) {
            switch (upgrade) {
                case 1: {
                    EntityPlayer player2;
                    List<EntityPlayer> players = SparkHelper.getEntitiesAround(EntityPlayer.class, this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    HashMap receivingPlayers = new HashMap();
                    ItemStack input = new ItemStack(ModItems.spark);
                    for (EntityPlayer player2 : players) {
                        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
                        stacks.addAll(Arrays.asList(player2.field_71071_by.field_70462_a));
                        stacks.addAll(Arrays.asList(player2.field_71071_by.field_70460_b));
                        stacks.addAll(Arrays.asList(PlayerHandler.getPlayerBaubles((EntityPlayer)player2).stackList));
                        for (ItemStack stack : stacks) {
                            int recv;
                            HashMap<ItemStack, Integer> receivingStacks;
                            IManaItem manaItem;
                            if (stack == null || !(stack.func_77973_b() instanceof IManaItem) || !(manaItem = (IManaItem)stack.func_77973_b()).canReceiveManaFromItem(stack, input)) continue;
                            boolean add = false;
                            if (!receivingPlayers.containsKey(player2)) {
                                add = true;
                                receivingStacks = new HashMap<ItemStack, Integer>();
                            } else {
                                receivingStacks = (HashMap<ItemStack, Integer>)receivingPlayers.get(player2);
                            }
                            if ((recv = Math.min(this.getAttachedTile().getCurrentMana(), Math.min(1000, manaItem.getMaxMana(stack) - manaItem.getMana(stack)))) <= 0) continue;
                            receivingStacks.put(stack, recv);
                            if (!add) continue;
                            receivingPlayers.put(player2, receivingStacks);
                        }
                    }
                    if (receivingPlayers.isEmpty()) break;
                    ArrayList keys = new ArrayList(receivingPlayers.keySet());
                    Collections.shuffle(keys);
                    player2 = (EntityPlayer)keys.iterator().next();
                    Map items = (Map)receivingPlayers.get(player2);
                    ItemStack stack = (ItemStack)items.keySet().iterator().next();
                    int cost = (Integer)items.get(stack);
                    int manaToPut = Math.min(this.getAttachedTile().getCurrentMana(), cost);
                    ((IManaItem)stack.func_77973_b()).addMana(stack, manaToPut);
                    this.getAttachedTile().recieveMana(-manaToPut);
                    this.particlesTowards((Entity)player2);
                    break;
                }
                case 2: {
                    ArrayList validSparks = new ArrayList();
                    for (ISparkEntity spark : allSparks) {
                        int upgrade_;
                        if (spark == this || (upgrade_ = spark.getUpgrade()) != 0 || !(spark.getAttachedTile() instanceof IManaPool)) continue;
                        validSparks.add(spark);
                    }
                    if (validSparks.size() <= 0) break;
                    ((ISparkEntity)validSparks.get(this.field_70170_p.field_73012_v.nextInt(validSparks.size()))).registerTransfer(this);
                    break;
                }
                case 3: {
                    for (ISparkEntity spark : allSparks) {
                        int upgrade_;
                        if (spark == this || (upgrade_ = spark.getUpgrade()) == 2 || upgrade_ == 3 || upgrade_ == 4) continue;
                        transfers.add(spark);
                    }
                    break;
                }
            }
        }
        if (!transfers.isEmpty()) {
            int manaTotal = Math.min(1000 * transfers.size(), tile.getCurrentMana());
            int manaForEach = manaTotal / transfers.size();
            int manaSpent = 0;
            if (manaForEach > transfers.size()) {
                for (ISparkEntity spark : transfers) {
                    if (spark.getAttachedTile() == null || spark.getAttachedTile().isFull() || spark.areIncomingTransfersDone()) {
                        manaTotal -= manaForEach;
                        continue;
                    }
                    ISparkAttachable attached = spark.getAttachedTile();
                    int spend = Math.min(attached.getAvailableSpaceForMana(), manaForEach);
                    attached.recieveMana(spend);
                    manaSpent += spend;
                    this.particlesTowards((Entity)spark);
                }
                tile.recieveMana(-manaSpent);
            }
        }
        if (this.removeTransferants > 0) {
            --this.removeTransferants;
        }
        this.getTransfers();
    }

    void particlesTowards(Entity e) {
        Vector3 thisVec = Vector3.fromEntityCenter(this).add(0.0, 0.0, 0.0);
        Vector3 receiverVec = Vector3.fromEntityCenter(e).add(0.0, 0.0, 0.0);
        double rc = 0.45;
        thisVec.add((Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc);
        receiverVec.add((Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc);
        Vector3 motion = receiverVec.copy().sub(thisVec);
        motion.multiply(0.04f);
        float r = 0.4f + 0.3f * (float)Math.random();
        float g = 0.4f + 0.3f * (float)Math.random();
        float b = 0.4f + 0.3f * (float)Math.random();
        float size = 0.125f + 0.125f * (float)Math.random();
        Botania.proxy.wispFX(this.field_70170_p, thisVec.x, thisVec.y, thisVec.z, r, g, b, size, (float)motion.x, (float)motion.y, (float)motion.z);
    }

    public static void particleBeam(Entity e1, Entity e2) {
        if (e1 == null || e2 == null) {
            return;
        }
        Vector3 orig = new Vector3(e1.field_70165_t, e1.field_70163_u + 0.25, e1.field_70161_v);
        Vector3 end = new Vector3(e2.field_70165_t, e2.field_70163_u + 0.25, e2.field_70161_v);
        Vector3 diff = end.copy().sub(orig);
        Vector3 movement = diff.copy().normalize().multiply(0.1);
        int iters = (int)(diff.mag() / movement.mag());
        float huePer = 1.0f / (float)iters;
        float hueSum = (float)Math.random();
        Vector3 currentPos = orig.copy();
        for (int i = 0; i < iters; ++i) {
            float hue = (float)i * huePer + hueSum;
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
            float r = Math.min(1.0f, (float)color.getRed() / 255.0f + 0.4f);
            float g = Math.min(1.0f, (float)color.getGreen() / 255.0f + 0.4f);
            float b = Math.min(1.0f, (float)color.getBlue() / 255.0f + 0.4f);
            Botania.proxy.setSparkleFXNoClip(true);
            Botania.proxy.sparkleFX(e1.field_70170_p, currentPos.x, currentPos.y, currentPos.z, r, g, b, 1.0f, 12);
            Botania.proxy.setSparkleFXNoClip(false);
            currentPos.add(movement);
        }
    }

    public boolean func_70067_L() {
        return true;
    }

    public boolean func_130002_c(EntityPlayer player) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            int upgrade = this.getUpgrade();
            if (stack.func_77973_b() == ModItems.twigWand) {
                if (player.func_70093_af()) {
                    if (upgrade > 0) {
                        if (!this.field_70170_p.field_72995_K) {
                            this.func_70099_a(new ItemStack(ModItems.sparkUpgrade, 1, upgrade - 1), 0.0f);
                        }
                        this.setUpgrade(0);
                        this.transfers.clear();
                        this.removeTransferants = 2;
                    } else {
                        this.func_70106_y();
                    }
                    if (player.field_70170_p.field_72995_K) {
                        player.func_71038_i();
                    }
                    return true;
                }
                List<ISparkEntity> allSparks = SparkHelper.getSparksAround(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
                for (ISparkEntity spark : allSparks) {
                    EntitySpark.particleBeam(this, (Entity)spark);
                }
                return true;
            }
            if (stack.func_77973_b() == ModItems.sparkUpgrade && upgrade == 0) {
                int newUpgrade = stack.func_77960_j() + 1;
                this.setUpgrade(newUpgrade);
                --stack.field_77994_a;
                if (player.field_70170_p.field_72995_K) {
                    player.func_71038_i();
                }
                return true;
            }
        }
        return this.doPhantomInk(stack);
    }

    public boolean doPhantomInk(ItemStack stack) {
        if (stack != null && stack.func_77973_b() == ModItems.phantomInk && !this.field_70170_p.field_72995_K) {
            int invis = this.field_70180_af.func_75679_c(27);
            this.field_70180_af.func_75692_b(27, (Object)(~invis & 1));
            return true;
        }
        return false;
    }

    protected void func_70037_a(NBTTagCompound cmp) {
        this.setUpgrade(cmp.func_74762_e(TAG_UPGRADE));
        this.field_70180_af.func_75692_b(27, (Object)cmp.func_74762_e(TAG_INVIS));
    }

    protected void func_70014_b(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_UPGRADE, this.getUpgrade());
        cmp.func_74768_a(TAG_INVIS, this.field_70180_af.func_75679_c(27));
    }

    public void func_70106_y() {
        super.func_70106_y();
        if (!this.field_70170_p.field_72995_K) {
            int upgrade = this.getUpgrade();
            this.func_70099_a(new ItemStack(ModItems.spark), 0.0f);
            if (upgrade > 0) {
                this.func_70099_a(new ItemStack(ModItems.sparkUpgrade, 1, upgrade - 1), 0.0f);
            }
        }
    }

    @Override
    public ISparkAttachable getAttachedTile() {
        int z;
        int y;
        int x = MathHelper.func_76128_c((double)this.field_70165_t);
        TileEntity tile = this.field_70170_p.func_147438_o(x, y = MathHelper.func_76128_c((double)this.field_70163_u) - 1, z = MathHelper.func_76128_c((double)this.field_70161_v));
        if (tile != null && tile instanceof ISparkAttachable) {
            return (ISparkAttachable)tile;
        }
        return null;
    }

    @Override
    public Collection<ISparkEntity> getTransfers() {
        ArrayList<ISparkEntity> removals = new ArrayList<ISparkEntity>();
        Iterator<ISparkEntity> iterator = this.transfers.iterator();
        while (iterator.hasNext()) {
            ISparkEntity e;
            ISparkEntity spark = e = iterator.next();
            int upgr = this.getUpgrade();
            int supgr = spark.getUpgrade();
            ISparkAttachable atile = spark.getAttachedTile();
            if (spark != this && !spark.areIncomingTransfersDone() && atile != null && !atile.isFull() && (upgr == 0 && supgr == 2 || upgr == 3 && (supgr == 0 || supgr == 1) || !(atile instanceof IManaPool))) continue;
            removals.add(e);
        }
        if (!removals.isEmpty()) {
            this.transfers.removeAll(removals);
        }
        return this.transfers;
    }

    private boolean hasTransfer(ISparkEntity entity) {
        return this.transfers.contains(entity);
    }

    @Override
    public void registerTransfer(ISparkEntity entity) {
        if (this.hasTransfer(entity)) {
            return;
        }
        this.transfers.add(entity);
    }

    @Override
    public int getUpgrade() {
        return this.field_70180_af.func_75679_c(28);
    }

    @Override
    public void setUpgrade(int upgrade) {
        this.field_70180_af.func_75692_b(28, (Object)upgrade);
    }

    @Override
    public boolean areIncomingTransfersDone() {
        ISparkAttachable tile = this.getAttachedTile();
        if (tile instanceof IManaPool) {
            return this.removeTransferants > 0;
        }
        return tile != null && tile.areIncomingTranfersDone();
    }
}

