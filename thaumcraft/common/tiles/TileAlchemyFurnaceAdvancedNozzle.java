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
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;

public class TileAlchemyFurnaceAdvancedNozzle
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport {
    ForgeDirection facing = ForgeDirection.UNKNOWN;
    public TileAlchemyFurnaceAdvanced furnace = null;

    public boolean canUpdate() {
        return this.facing != null;
    }

    public void func_145845_h() {
        if (this.facing == ForgeDirection.UNKNOWN && this.furnace == null) {
            this.facing = null;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                if (tile == null || !(tile instanceof TileAlchemyFurnaceAdvanced)) continue;
                this.facing = dir.getOpposite();
                this.furnace = (TileAlchemyFurnaceAdvanced)tile;
                break;
            }
        }
    }

    @Override
    public AspectList getAspects() {
        return this.furnace != null ? this.furnace.aspects : null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.furnace == null) {
            return false;
        }
        if (this.furnace.aspects.getAmount(tt) >= am) {
            this.furnace.aspects.remove(tt, am);
            this.furnace.func_70296_d();
            this.furnace.vis = this.furnace.aspects.visSize();
            this.field_145850_b.func_147471_g(this.furnace.field_145851_c, this.furnace.field_145848_d, this.furnace.field_145849_e);
            return true;
        }
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tt, int am) {
        if (this.furnace == null) {
            return false;
        }
        return this.furnace.aspects.getAmount(tt) >= am;
    }

    @Override
    public int containerContains(Aspect tt) {
        if (this.furnace == null) {
            return 0;
        }
        return this.furnace.aspects.getAmount(tt);
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return false;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return face == this.facing;
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
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.furnace != null ? this.furnace.aspects.getAspects()[0] : null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.furnace != null ? Integer.valueOf(this.furnace.aspects.getAmount(this.furnace.aspects.getAspects()[0])) : null;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection facing) {
        return this.canOutputTo(facing) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection facing) {
        return 0;
    }
}

