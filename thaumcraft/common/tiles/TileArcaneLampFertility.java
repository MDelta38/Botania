/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileArcaneLampFertility
extends TileThaumcraft
implements IEssentiaTransport {
    public ForgeDirection facing = ForgeDirection.getOrientation((int)0);
    public int charges = 0;
    int count = 0;
    int drawDelay = 0;

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.charges < 4 && this.drawEssentia()) {
                ++this.charges;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.charges > 1 && this.count++ % 300 == 0) {
                this.updateAnimals();
            }
        }
    }

    private void updateAnimals() {
        int distance = 7;
        List var5 = this.field_145850_b.func_72872_a(EntityAnimal.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b((double)distance, (double)distance, (double)distance));
        Iterator var2 = var5.iterator();
        block0: while (var2.hasNext()) {
            EntityAnimal var3;
            EntityAnimal var4 = var3 = (EntityAnimal)var2.next();
            if (var3.func_70874_b() != 0 || var3.func_70880_s()) continue;
            ArrayList<EntityAnimal> sa = new ArrayList<EntityAnimal>();
            for (EntityLivingBase var7 : var5) {
                if (!var7.getClass().equals(var4.getClass())) continue;
                sa.add((EntityAnimal)var7);
            }
            if (sa != null && sa.size() > 7) continue;
            Iterator var22 = sa.iterator();
            EntityAnimal partner = null;
            while (var22.hasNext()) {
                EntityAnimal var33 = (EntityAnimal)var22.next();
                if (var33.func_70874_b() != 0 || var33.func_70880_s()) continue;
                if (partner == null) {
                    partner = var33;
                    continue;
                }
                this.charges -= 2;
                var33.func_146082_f(null);
                partner.func_146082_f(null);
                break block0;
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("orientation"));
        this.charges = nbttagcompound.func_74762_e("charges");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
        nbttagcompound.func_74768_a("charges", this.charges);
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
            if (ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing) && ic.takeEssentia(Aspect.LIFE, 1, this.facing.getOpposite()) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == this.facing;
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
        return Aspect.LIFE;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return face == this.facing ? 128 - this.charges * 10 : 0;
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

