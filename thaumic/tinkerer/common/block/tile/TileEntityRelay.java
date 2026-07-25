/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileEntityMobilizer;

public class TileEntityRelay
extends TileEntity
implements IMovableTile {
    public boolean hasPartner;
    public int partnerX;
    public int partnerZ;

    public void verifyPartner() {
        TileEntity te = this.field_145850_b.func_147438_o(this.partnerX, this.field_145848_d, this.partnerZ);
        if (!this.hasPartner || !(te instanceof TileEntityRelay) || ((TileEntityRelay)te).partnerX != this.field_145851_c || ((TileEntityRelay)te).partnerZ != this.field_145849_e) {
            this.hasPartner = false;
        }
    }

    public void func_145841_b(NBTTagCompound nbt) {
        super.func_145841_b(nbt);
        nbt.func_74757_a("HasPartner", this.hasPartner);
        nbt.func_74768_a("PartnerX", this.partnerX);
        nbt.func_74768_a("PartnerZ", this.partnerZ);
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.hasPartner = nbt.func_74767_n("HasPartner");
        this.partnerX = nbt.func_74762_e("PartnerX");
        this.partnerZ = nbt.func_74762_e("PartnerZ");
    }

    public void func_145845_h() {
        int j;
        int i;
        this.verifyPartner();
        if (this.hasPartner) {
            i = this.field_145851_c;
            do {
                j = this.field_145849_e;
                do {
                    float xInc = 0.0f;
                    float zInc = 0.0f;
                    if (this.partnerZ - this.field_145849_e != 0) {
                        zInc = (float)Math.copySign(0.05, (double)(this.partnerZ - this.field_145849_e)) * (float)(this.field_145850_b.func_82737_E() % 20L);
                    }
                    if (this.partnerX - this.field_145851_c != 0) {
                        xInc = (float)Math.copySign(0.05, (double)(this.partnerX - this.field_145851_c)) * (float)(this.field_145850_b.func_82737_E() % 20L);
                    }
                    ThaumicTinkerer.tcProxy.sparkle((float)(0.5 + (double)i + (double)xInc), (float)((double)this.field_145848_d + 0.5), (float)((double)j + 0.5 + (double)zInc), this.field_145851_c < this.partnerX || this.field_145849_e > this.partnerX ? 2 : 14);
                } while ((j = (int)((float)j + Math.copySign(1.0f, this.partnerZ - this.field_145849_e))) < this.partnerZ);
            } while ((i = (int)((float)i + Math.copySign(1.0f, this.partnerX - this.field_145851_c))) < this.partnerX);
        }
        if (this.field_145850_b.func_82737_E() % 40L == 0L) {
            this.checkForPartner();
        }
        i = this.field_145851_c;
        if (this.hasPartner && this.field_145850_b.func_82737_E() % 40L == 0L) {
            do {
                j = this.field_145849_e;
                do {
                    if (!(this.field_145850_b.func_147438_o(i, this.field_145848_d, j) instanceof TileEntityMobilizer)) continue;
                    TileEntityMobilizer te = (TileEntityMobilizer)this.field_145850_b.func_147438_o(i, this.field_145848_d, j);
                    te.verifyRelay();
                    if (te.linked) continue;
                    te.firstRelayX = this.field_145851_c;
                    te.firstRelayZ = this.field_145849_e;
                    te.secondRelayX = this.partnerX;
                    te.secondRelayZ = this.partnerZ;
                    te.linked = true;
                    te.movementDirection = this.field_145851_c != this.partnerX ? ForgeDirection.EAST : ForgeDirection.NORTH;
                } while ((j = (int)((float)j + Math.copySign(1.0f, this.partnerZ - this.field_145849_e))) < this.partnerZ);
            } while ((i = (int)((float)i + Math.copySign(1.0f, this.partnerX - this.field_145851_c))) < this.partnerX);
        }
    }

    public void checkForPartner() {
        if (!this.hasPartner) {
            for (int i = -32; i < 32; ++i) {
                if (i == 0) continue;
                TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + i);
                this.setPartners(te);
                te = this.field_145850_b.func_147438_o(this.field_145851_c + i, this.field_145848_d, this.field_145849_e);
                this.setPartners(te);
            }
        }
    }

    private void setPartners(TileEntity te) {
        if (te instanceof TileEntityRelay) {
            ((TileEntityRelay)te).partnerX = this.field_145851_c;
            ((TileEntityRelay)te).partnerZ = this.field_145849_e;
            this.partnerX = te.field_145851_c;
            this.partnerZ = te.field_145849_e;
            this.hasPartner = true;
            ((TileEntityRelay)te).hasPartner = true;
        }
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }
}

