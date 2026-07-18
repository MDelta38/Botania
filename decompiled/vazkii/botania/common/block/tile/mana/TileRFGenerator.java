/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cofh.api.energy.IEnergyConnection
 *  cofh.api.energy.IEnergyReceiver
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.mana;

import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.Optional;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.block.tile.TileMod;

@Optional.Interface(iface="cofh.api.energy.IEnergyConnection", modid="CoFHAPI|energy")
public class TileRFGenerator
extends TileMod
implements IManaReceiver,
IEnergyConnection {
    private static final int CONVERSION_RATE = 10;
    private static final int MAX_MANA = 12800;
    private static final String TAG_MANA = "mana";
    int mana = 0;
    private IEnergyReceiver[] receiverCache;
    private boolean deadCache;

    @Optional.Method(modid="CoFHAPI|energy")
    public void func_145829_t() {
        super.func_145829_t();
        this.deadCache = true;
        this.receiverCache = null;
    }

    @Optional.Method(modid="CoFHAPI|energy")
    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            if (this.deadCache) {
                this.reCache();
            }
            int transfer = Math.min(this.mana, 1600);
            this.mana -= transfer;
            this.mana += this.transmitEnergy(transfer);
        }
    }

    @Optional.Method(modid="CoFHAPI|energy")
    protected final int transmitEnergy(int energy) {
        if (this.receiverCache != null) {
            int i = this.receiverCache.length;
            while (i-- > 0) {
                IEnergyReceiver tile = this.receiverCache[i];
                if (tile == null) continue;
                ForgeDirection from = ForgeDirection.VALID_DIRECTIONS[i];
                if (tile.receiveEnergy(from, energy, true) > 0) {
                    energy -= tile.receiveEnergy(from, energy, false);
                }
                if (energy > 0) continue;
                return 0;
            }
        }
        return energy;
    }

    @Optional.Method(modid="CoFHAPI|energy")
    private void reCache() {
        if (this.deadCache) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                this.onNeighborTileChange(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
            }
            this.deadCache = false;
        }
    }

    @Optional.Method(modid="CoFHAPI|energy")
    public void onNeighborTileChange(int x, int y, int z) {
        TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
        if (x < this.field_145851_c) {
            this.addCache(tile, 5);
        } else if (x > this.field_145851_c) {
            this.addCache(tile, 4);
        } else if (z < this.field_145849_e) {
            this.addCache(tile, 3);
        } else if (z > this.field_145849_e) {
            this.addCache(tile, 2);
        } else if (y < this.field_145848_d) {
            this.addCache(tile, 1);
        } else if (y > this.field_145848_d) {
            this.addCache(tile, 0);
        }
    }

    @Optional.Method(modid="CoFHAPI|energy")
    private void addCache(TileEntity tile, int side) {
        if (this.receiverCache != null) {
            this.receiverCache[side] = null;
        }
        if (tile instanceof IEnergyReceiver && ((IEnergyReceiver)tile).canConnectEnergy(ForgeDirection.VALID_DIRECTIONS[side])) {
            if (this.receiverCache == null) {
                this.receiverCache = new IEnergyReceiver[6];
            }
            this.receiverCache[side] = (IEnergyReceiver)tile;
        }
    }

    @Override
    public int getCurrentMana() {
        return this.mana / 10;
    }

    @Override
    public boolean isFull() {
        return this.mana >= 12800;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(12800, this.mana + mana * 10);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
    }

    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }
}

