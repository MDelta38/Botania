/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.api.subtile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.subtile.SubTileEntity;

public class SubTileGenerating
extends SubTileEntity {
    public static final int RANGE = 6;
    private static final String TAG_MANA = "mana";
    private static final String TAG_COLLECTOR_X = "collectorX";
    private static final String TAG_COLLECTOR_Y = "collectorY";
    private static final String TAG_COLLECTOR_Z = "collectorZ";
    private static final String TAG_PASSIVE_DECAY_TICKS = "passiveDecayTicks";
    protected int mana;
    public int redstoneSignal = 0;
    int sizeLastCheck = -1;
    protected TileEntity linkedCollector = null;
    public int knownMana = -1;
    public int passiveDecayTicks;
    ChunkCoordinates cachedCollectorCoordinates = null;

    public boolean acceptsRedstone() {
        return false;
    }

    @Override
    public void onUpdate() {
        int delay;
        super.onUpdate();
        this.linkCollector();
        if (this.canGeneratePassively() && (delay = this.getDelayBetweenPassiveGeneration()) > 0 && this.ticksExisted % delay == 0 && !this.supertile.func_145831_w().field_72995_K) {
            if (this.shouldSyncPassiveGeneration()) {
                this.sync();
            }
            this.addMana(this.getValueForPassiveGeneration());
        }
        this.emptyManaIntoCollector();
        if (this.acceptsRedstone()) {
            this.redstoneSignal = 0;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                int redstoneSide = this.supertile.func_145831_w().func_72878_l(this.supertile.field_145851_c + dir.offsetX, this.supertile.field_145848_d + dir.offsetY, this.supertile.field_145849_e + dir.offsetZ, dir.ordinal());
                this.redstoneSignal = Math.max(this.redstoneSignal, redstoneSide);
            }
        }
        if (this.supertile.func_145831_w().field_72995_K) {
            double particleChance = 1.0 - (double)this.mana / (double)this.getMaxMana() / 3.5;
            Color color = new Color(this.getColor());
            if (Math.random() > particleChance) {
                BotaniaAPI.internalHandler.sparkleFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + 0.3 + Math.random() * 0.5, (double)this.supertile.field_145848_d + 0.5 + Math.random() * 0.5, (double)this.supertile.field_145849_e + 0.3 + Math.random() * 0.5, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)Math.random(), 5);
            }
        }
        boolean passive = this.isPassiveFlower();
        if (!this.supertile.func_145831_w().field_72995_K) {
            int muhBalance = BotaniaAPI.internalHandler.getPassiveFlowerDecay();
            if (passive && muhBalance > 0 && this.passiveDecayTicks > muhBalance) {
                this.supertile.func_145831_w().func_72926_e(2001, this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e, Block.func_149682_b((Block)this.supertile.func_145838_q()));
                if (this.supertile.func_145831_w().func_147439_a(this.supertile.field_145851_c, this.supertile.field_145848_d - 1, this.supertile.field_145849_e).isSideSolid((IBlockAccess)this.supertile.func_145831_w(), this.supertile.field_145851_c, this.supertile.field_145848_d - 1, this.supertile.field_145849_e, ForgeDirection.UP)) {
                    this.supertile.func_145831_w().func_147449_b(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e, (Block)Blocks.field_150330_I);
                } else {
                    this.supertile.func_145831_w().func_147468_f(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e);
                }
            }
        }
        if (!this.overgrowth && passive) {
            ++this.passiveDecayTicks;
        }
    }

    public void linkCollector() {
        TileEntity tileAt;
        boolean needsNew = false;
        if (this.linkedCollector == null) {
            needsNew = true;
            if (this.cachedCollectorCoordinates != null) {
                needsNew = false;
                if (this.supertile.func_145831_w().func_72899_e(this.cachedCollectorCoordinates.field_71574_a, this.cachedCollectorCoordinates.field_71572_b, this.cachedCollectorCoordinates.field_71573_c)) {
                    needsNew = true;
                    tileAt = this.supertile.func_145831_w().func_147438_o(this.cachedCollectorCoordinates.field_71574_a, this.cachedCollectorCoordinates.field_71572_b, this.cachedCollectorCoordinates.field_71573_c);
                    if (tileAt != null && tileAt instanceof IManaCollector && !tileAt.func_145837_r()) {
                        this.linkedCollector = tileAt;
                        needsNew = false;
                    }
                    this.cachedCollectorCoordinates = null;
                }
            }
        } else {
            tileAt = this.supertile.func_145831_w().func_147438_o(this.linkedCollector.field_145851_c, this.linkedCollector.field_145848_d, this.linkedCollector.field_145849_e);
            if (tileAt != null && tileAt instanceof IManaCollector) {
                this.linkedCollector = tileAt;
            }
        }
        if (needsNew && this.ticksExisted == 1) {
            IManaNetwork network = BotaniaAPI.internalHandler.getManaNetworkInstance();
            int size = network.getAllCollectorsInWorld(this.supertile.func_145831_w()).size();
            if (BotaniaAPI.internalHandler.shouldForceCheck() || size != this.sizeLastCheck) {
                ChunkCoordinates coords = new ChunkCoordinates(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e);
                this.linkedCollector = network.getClosestCollector(coords, this.supertile.func_145831_w(), 6);
                this.sizeLastCheck = size;
            }
        }
    }

    public void linkToForcefully(TileEntity collector) {
        this.linkedCollector = collector;
    }

    public void addMana(int mana) {
        this.mana = Math.min(this.getMaxMana(), this.mana + mana);
    }

    public void emptyManaIntoCollector() {
        IManaCollector collector;
        if (this.linkedCollector != null && this.isValidBinding() && !(collector = (IManaCollector)this.linkedCollector).isFull() && this.mana > 0) {
            int manaval = Math.min(this.mana, collector.getMaxMana() - collector.getCurrentMana());
            this.mana -= manaval;
            collector.recieveMana(manaval);
        }
    }

    public boolean isPassiveFlower() {
        return false;
    }

    public boolean shouldSyncPassiveGeneration() {
        return false;
    }

    public boolean canGeneratePassively() {
        return false;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 20;
    }

    public int getValueForPassiveGeneration() {
        return 1;
    }

    @Override
    public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
        ArrayList<ItemStack> drops = super.getDrops(list);
        this.populateDropStackNBTs(drops);
        return drops;
    }

    public void populateDropStackNBTs(List<ItemStack> drops) {
        ItemStack drop;
        if (this.isPassiveFlower() && this.ticksExisted > 0 && BotaniaAPI.internalHandler.getPassiveFlowerDecay() > 0 && (drop = drops.get(0)) != null) {
            if (!drop.func_77942_o()) {
                drop.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound cmp = drop.func_77978_p();
            cmp.func_74768_a(TAG_PASSIVE_DECAY_TICKS, this.passiveDecayTicks);
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        if (this.isPassiveFlower()) {
            NBTTagCompound cmp = stack.func_77978_p();
            this.passiveDecayTicks = cmp.func_74762_e(TAG_PASSIVE_DECAY_TICKS);
        }
    }

    @Override
    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return false;
        }
        if (!player.field_70170_p.field_72995_K) {
            this.sync();
        }
        this.knownMana = this.mana;
        player.field_70170_p.func_72956_a((Entity)player, "botania:ding", 0.1f, 1.0f);
        return super.onWanded(player, wand);
    }

    public int getMaxMana() {
        return 20;
    }

    public int getColor() {
        return 0xFFFFFF;
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
        this.passiveDecayTicks = cmp.func_74762_e(TAG_PASSIVE_DECAY_TICKS);
        int x = cmp.func_74762_e(TAG_COLLECTOR_X);
        int y = cmp.func_74762_e(TAG_COLLECTOR_Y);
        int z = cmp.func_74762_e(TAG_COLLECTOR_Z);
        this.cachedCollectorCoordinates = y < 0 ? null : new ChunkCoordinates(x, y, z);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
        cmp.func_74768_a("ticksExisted", this.ticksExisted);
        cmp.func_74768_a(TAG_PASSIVE_DECAY_TICKS, this.passiveDecayTicks);
        if (this.cachedCollectorCoordinates != null) {
            cmp.func_74768_a(TAG_COLLECTOR_X, this.cachedCollectorCoordinates.field_71574_a);
            cmp.func_74768_a(TAG_COLLECTOR_Y, this.cachedCollectorCoordinates.field_71572_b);
            cmp.func_74768_a(TAG_COLLECTOR_Z, this.cachedCollectorCoordinates.field_71573_c);
        } else {
            int x = this.linkedCollector == null ? 0 : this.linkedCollector.field_145851_c;
            int y = this.linkedCollector == null ? -1 : this.linkedCollector.field_145848_d;
            int z = this.linkedCollector == null ? 0 : this.linkedCollector.field_145849_e;
            cmp.func_74768_a(TAG_COLLECTOR_X, x);
            cmp.func_74768_a(TAG_COLLECTOR_Y, y);
            cmp.func_74768_a(TAG_COLLECTOR_Z, z);
        }
    }

    @Override
    public ChunkCoordinates getBinding() {
        if (this.linkedCollector == null) {
            return null;
        }
        return new ChunkCoordinates(this.linkedCollector.field_145851_c, this.linkedCollector.field_145848_d, this.linkedCollector.field_145849_e);
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        TileEntity tile;
        int range = 6;
        double dist = (x - this.supertile.field_145851_c) * (x - this.supertile.field_145851_c) + (y - this.supertile.field_145848_d) * (y - this.supertile.field_145848_d) + (z - this.supertile.field_145849_e) * (z - this.supertile.field_145849_e);
        if ((double)(range *= range) >= dist && (tile = player.field_70170_p.func_147438_o(x, y, z)) instanceof IManaCollector) {
            this.linkedCollector = tile;
            return true;
        }
        return false;
    }

    public boolean isValidBinding() {
        return this.linkedCollector != null && !this.linkedCollector.func_145837_r() && this.supertile.func_145831_w().func_147438_o(this.linkedCollector.field_145851_c, this.linkedCollector.field_145848_d, this.linkedCollector.field_145849_e) == this.linkedCollector;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        String name = StatCollector.func_74838_a((String)("tile.botania:flower." + this.getUnlocalizedName() + ".name"));
        int color = this.getColor();
        BotaniaAPI.internalHandler.drawComplexManaHUD(color, this.knownMana, this.getMaxMana(), name, res, BotaniaAPI.internalHandler.getBindDisplayForFlowerType(this), this.isValidBinding());
    }

    @Override
    public boolean isOvergrowthAffected() {
        return !this.isPassiveFlower();
    }
}

