/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.fx.PacketFXEssentiaSource
 *  thaumcraft.common.tiles.TileMirrorEssentia
 */
package witchinggadgets.common.blocks.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import thaumcraft.common.tiles.TileMirrorEssentia;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityEssentiaPump
extends TileEntityWGBase
implements IEssentiaTransport {
    int tick;
    public ForgeDirection facing = ForgeDirection.NORTH;
    Aspect aspect = null;
    int amount = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            Aspect a = null;
            if (this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d + this.facing.offsetY, this.field_145849_e + this.facing.offsetZ) instanceof IEssentiaTransport) {
                a = ((IEssentiaTransport)this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d + this.facing.offsetY, this.field_145849_e + this.facing.offsetZ)).getSuctionType(this.facing.getOpposite());
            }
            if (a != null && (this.aspect == null || this.aspect == a) && this.amount < 1) {
                for (TileMirrorEssentia mirror : this.getMirrors()) {
                    if (!mirror.takeFromContainer(a, 1)) continue;
                    if (this.aspect == null) {
                        this.aspect = a;
                    }
                    ++this.amount;
                    this.func_70296_d();
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXEssentiaSource(this.field_145851_c, this.field_145848_d, this.field_145849_e, (byte)(this.field_145851_c - mirror.field_145851_c), (byte)(this.field_145848_d - mirror.field_145848_d), (byte)(this.field_145849_e - mirror.field_145849_e), this.aspect.getColor()), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                    return;
                }
            }
        }
    }

    ArrayList<TileMirrorEssentia> getMirrors() {
        ArrayList<TileMirrorEssentia> list = new ArrayList<TileMirrorEssentia>();
        int range = 8;
        ForgeDirection fd = this.facing.getOpposite();
        for (int h = -range; h <= range; ++h) {
            for (int w = -range; w <= range; ++w) {
                for (int l = 1; l < range; ++l) {
                    int xx = this.field_145851_c;
                    int yy = this.field_145848_d;
                    int zz = this.field_145849_e;
                    if (fd.offsetY != 0) {
                        xx += w;
                        yy += l * fd.offsetY;
                        zz += h;
                    } else if (fd.offsetX != 0) {
                        xx += l * fd.offsetX;
                        yy += h;
                        zz += w;
                    } else {
                        xx += w;
                        yy += h;
                        zz += l * fd.offsetZ;
                    }
                    TileEntity te = this.field_145850_b.func_147438_o(xx, yy, zz);
                    if (te == null || !(te instanceof TileMirrorEssentia) || !this.canSeeMirror((TileMirrorEssentia)te)) continue;
                    list.add((TileMirrorEssentia)te);
                }
            }
        }
        return list;
    }

    boolean canSeeMirror(TileMirrorEssentia tile) {
        Vec3 tPos = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5 + (double)this.facing.getOpposite().offsetX), (double)((double)this.field_145848_d + 0.5 + (double)this.facing.getOpposite().offsetY), (double)((double)this.field_145849_e + 0.5 + (double)this.facing.getOpposite().offsetZ));
        ForgeDirection fd = ForgeDirection.getOrientation((int)(this.field_145850_b.func_72805_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e) % 6));
        Vec3 mPos = Vec3.func_72443_a((double)((double)tile.field_145851_c + 0.5 + (double)fd.offsetX), (double)((double)this.field_145848_d + 0.5 + (double)fd.offsetY), (double)((double)this.field_145849_e + 0.5 + (double)fd.offsetZ));
        MovingObjectPosition mop = this.field_145850_b.func_72933_a(tPos, mPos);
        return mop == null || mop.field_72311_b == this.field_145851_c && mop.field_72312_c == this.field_145848_d && mop.field_72309_d == this.field_145849_e || mop.field_72311_b == tile.field_145851_c && mop.field_72312_c == tile.field_145848_d && mop.field_72309_d == tile.field_145849_e;
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        this.tick = tag.func_74762_e("tick");
        this.facing = ForgeDirection.getOrientation((int)tag.func_74762_e("facing"));
        this.aspect = Aspect.getAspect((String)tag.func_74779_i("aspect"));
        this.amount = tag.func_74762_e("amount");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.func_74768_a("tick", this.tick);
        tag.func_74768_a("facing", this.facing.ordinal());
        if (this.aspect != null) {
            tag.func_74778_a("aspect", this.aspect.getTag());
        }
        tag.func_74768_a("amount", this.amount);
    }

    public int addEssentia(Aspect arg0, int arg1, ForgeDirection fd) {
        return 0;
    }

    public boolean canInputFrom(ForgeDirection fd) {
        return false;
    }

    public boolean canOutputTo(ForgeDirection fd) {
        return fd != null && fd.equals((Object)this.facing);
    }

    public int getEssentiaAmount(ForgeDirection fd) {
        return this.amount;
    }

    public Aspect getEssentiaType(ForgeDirection arg0) {
        return this.aspect;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public int getSuctionAmount(ForgeDirection arg0) {
        return 0;
    }

    public Aspect getSuctionType(ForgeDirection arg0) {
        return null;
    }

    public boolean isConnectable(ForgeDirection fd) {
        return fd != null && fd.equals((Object)this.facing);
    }

    public boolean renderExtendedTube() {
        return false;
    }

    public void setSuction(Aspect arg0, int arg1) {
    }

    public int takeEssentia(Aspect aspect, int am, ForgeDirection fd) {
        if (this.amount >= am) {
            this.amount -= am;
            if (this.amount <= 0) {
                this.aspect = null;
            }
            return am;
        }
        return 0;
    }
}

