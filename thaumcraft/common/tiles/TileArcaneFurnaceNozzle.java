/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileArcaneFurnace;

public class TileArcaneFurnaceNozzle
extends TileThaumcraft
implements IEssentiaTransport {
    ForgeDirection facing = ForgeDirection.UNKNOWN;
    TileArcaneFurnace furnace = null;
    int drawDelay = 0;

    public boolean canUpdate() {
        return this.facing != null;
    }

    public void func_145845_h() {
        if (this.facing == ForgeDirection.UNKNOWN && this.furnace == null) {
            this.facing = null;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                if (tile == null || !(tile instanceof TileArcaneFurnace)) continue;
                this.facing = dir.getOpposite();
                this.furnace = (TileArcaneFurnace)tile;
                break;
            }
        }
        if (!this.field_145850_b.field_72995_K) {
            try {
                if (this.furnace != null && this.furnace.speedyTime < 60 && this.drawEssentia()) {
                    this.furnace.speedyTime += 600;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    boolean drawEssentia() {
        if (++this.drawDelay % 5 != 0) {
            return false;
        }
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(this.facing.getOpposite())) {
                return false;
            }
            if (ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing) && ic.takeEssentia(Aspect.FIRE, 1, this.facing.getOpposite()) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return this.facing != null;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return this.facing != null;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        return Aspect.FIRE;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        try {
            if (this.furnace != null && this.furnace.speedyTime < 40) {
                return 128;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection facing) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection facing) {
        return 0;
    }
}

