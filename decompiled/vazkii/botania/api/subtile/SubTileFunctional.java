/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.api.subtile;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.SubTileEntity;

public class SubTileFunctional
extends SubTileEntity {
    public static final int RANGE = 10;
    private static final String TAG_MANA = "mana";
    private static final String TAG_POOL_X = "poolX";
    private static final String TAG_POOL_Y = "poolY";
    private static final String TAG_POOL_Z = "poolZ";
    public int mana;
    public int redstoneSignal = 0;
    int sizeLastCheck = -1;
    TileEntity linkedPool = null;
    public int knownMana = -1;
    ChunkCoordinates cachedPoolCoordinates = null;

    public boolean acceptsRedstone() {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.linkPool();
        if (this.linkedPool != null && this.isValidBinding()) {
            ForgeDirection[] pool = (ForgeDirection[])this.linkedPool;
            int manaInPool = pool.getCurrentMana();
            int manaMissing = this.getMaxMana() - this.mana;
            int manaToRemove = Math.min(manaMissing, manaInPool);
            pool.recieveMana(-manaToRemove);
            this.addMana(manaToRemove);
        }
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
    }

    public void linkPool() {
        TileEntity tileAt;
        boolean needsNew = false;
        if (this.linkedPool == null) {
            needsNew = true;
            if (this.cachedPoolCoordinates != null) {
                needsNew = false;
                if (this.supertile.func_145831_w().func_72899_e(this.cachedPoolCoordinates.field_71574_a, this.cachedPoolCoordinates.field_71572_b, this.cachedPoolCoordinates.field_71573_c)) {
                    needsNew = true;
                    tileAt = this.supertile.func_145831_w().func_147438_o(this.cachedPoolCoordinates.field_71574_a, this.cachedPoolCoordinates.field_71572_b, this.cachedPoolCoordinates.field_71573_c);
                    if (tileAt != null && tileAt instanceof IManaPool && !tileAt.func_145837_r()) {
                        this.linkedPool = tileAt;
                        needsNew = false;
                    }
                    this.cachedPoolCoordinates = null;
                }
            }
        } else {
            tileAt = this.supertile.func_145831_w().func_147438_o(this.linkedPool.field_145851_c, this.linkedPool.field_145848_d, this.linkedPool.field_145849_e);
            if (tileAt != null && tileAt instanceof IManaPool) {
                this.linkedPool = tileAt;
            }
        }
        if (needsNew && this.ticksExisted == 1) {
            IManaNetwork network = BotaniaAPI.internalHandler.getManaNetworkInstance();
            int size = network.getAllPoolsInWorld(this.supertile.func_145831_w()).size();
            if (BotaniaAPI.internalHandler.shouldForceCheck() || size != this.sizeLastCheck) {
                ChunkCoordinates coords = new ChunkCoordinates(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e);
                this.linkedPool = network.getClosestPool(coords, this.supertile.func_145831_w(), 10);
                this.sizeLastCheck = size;
            }
        }
    }

    public void linkToForcefully(TileEntity pool) {
        this.linkedPool = pool;
    }

    public void addMana(int mana) {
        this.mana = Math.min(this.getMaxMana(), this.mana + mana);
    }

    @Override
    public boolean onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return false;
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
        int x = cmp.func_74762_e(TAG_POOL_X);
        int y = cmp.func_74762_e(TAG_POOL_Y);
        int z = cmp.func_74762_e(TAG_POOL_Z);
        this.cachedPoolCoordinates = y < 0 ? null : new ChunkCoordinates(x, y, z);
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
        if (this.cachedPoolCoordinates != null) {
            cmp.func_74768_a(TAG_POOL_X, this.cachedPoolCoordinates.field_71574_a);
            cmp.func_74768_a(TAG_POOL_Y, this.cachedPoolCoordinates.field_71572_b);
            cmp.func_74768_a(TAG_POOL_Z, this.cachedPoolCoordinates.field_71573_c);
        } else {
            int x = this.linkedPool == null ? 0 : this.linkedPool.field_145851_c;
            int y = this.linkedPool == null ? -1 : this.linkedPool.field_145848_d;
            int z = this.linkedPool == null ? 0 : this.linkedPool.field_145849_e;
            cmp.func_74768_a(TAG_POOL_X, x);
            cmp.func_74768_a(TAG_POOL_Y, y);
            cmp.func_74768_a(TAG_POOL_Z, z);
        }
    }

    @Override
    public ChunkCoordinates getBinding() {
        if (this.linkedPool == null) {
            return null;
        }
        return new ChunkCoordinates(this.linkedPool.field_145851_c, this.linkedPool.field_145848_d, this.linkedPool.field_145849_e);
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        TileEntity tile;
        int range = 10;
        double dist = (x - this.supertile.field_145851_c) * (x - this.supertile.field_145851_c) + (y - this.supertile.field_145848_d) * (y - this.supertile.field_145848_d) + (z - this.supertile.field_145849_e) * (z - this.supertile.field_145849_e);
        if ((double)(range *= range) >= dist && (tile = player.field_70170_p.func_147438_o(x, y, z)) instanceof IManaPool) {
            this.linkedPool = tile;
            return true;
        }
        return false;
    }

    public boolean isValidBinding() {
        return this.linkedPool != null && !this.linkedPool.func_145837_r() && this.supertile.func_145831_w().func_147438_o(this.linkedPool.field_145851_c, this.linkedPool.field_145848_d, this.linkedPool.field_145849_e) == this.linkedPool;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        String name = StatCollector.func_74838_a((String)("tile.botania:flower." + this.getUnlocalizedName() + ".name"));
        int color = this.getColor();
        BotaniaAPI.internalHandler.drawComplexManaHUD(color, this.knownMana, this.getMaxMana(), name, res, BotaniaAPI.internalHandler.getBindDisplayForFlowerType(this), this.isValidBinding());
    }
}

