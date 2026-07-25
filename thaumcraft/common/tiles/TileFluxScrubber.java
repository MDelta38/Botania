/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.material.Material
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;

public class TileFluxScrubber
extends TileThaumcraft
implements IEssentiaTransport {
    public int essentia = 0;
    public int charges = 0;
    public int power = 0;
    public ForgeDirection facing = ForgeDirection.getOrientation((int)0);
    public int count = 0;
    ArrayList<BlockCoordinates> checklist = new ArrayList();

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.count == 0) {
            this.count = this.field_145850_b.field_73012_v.nextInt(1000);
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.charges >= 4) {
                this.charges -= 4;
                if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
                    ++this.essentia;
                    if (this.essentia > 4) {
                        this.essentia = 4;
                    }
                    this.func_70296_d();
                }
            }
            if (this.power < 5) {
                this.power += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.AIR, 10);
            }
            if (this.power >= 5) {
                this.checkFlux();
            }
        }
    }

    boolean isFlux(int x, int y, int z) {
        Material mat = this.field_145850_b.func_147439_a(x, y, z).func_149688_o();
        return mat == Config.fluxGoomaterial;
    }

    private void checkFlux() {
        int distance = 16;
        if (this.checklist.size() == 0) {
            for (int a = -distance; a <= distance; ++a) {
                for (int c = -distance; c <= distance; ++c) {
                    for (int b = -distance; b <= distance; ++b) {
                        this.checklist.add(new BlockCoordinates(this.field_145851_c + a, this.field_145848_d + c, this.field_145849_e + b));
                    }
                }
            }
            Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
        }
        int x = 0;
        int y = 0;
        int z = 0;
        for (int cc = 0; cc < 16 && this.checklist.size() > 0; ++cc) {
            x = this.checklist.get((int)0).x;
            y = this.checklist.get((int)0).y;
            z = this.checklist.get((int)0).z;
            this.checklist.remove(0);
            if (this.field_145850_b.func_147437_c(x, y, z) || !this.isFlux(x, y, z) || !(this.func_145835_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5) < (double)(distance * distance))) continue;
            this.power -= 5;
            int lmd = this.field_145850_b.func_72805_g(x, y, z);
            if (lmd > 0) {
                this.field_145850_b.func_72921_c(x, y, z, lmd - 1, 3);
            } else {
                this.field_145850_b.func_147468_f(x, y, z);
            }
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(x, y, z, 0xDD00FF), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)x, (double)y, (double)z, 32.0));
            ++this.charges;
            this.func_70296_d();
            return;
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("facing"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("facing", this.facing.ordinal());
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.charges = nbttagcompound.func_74762_e("charges");
        this.power = nbttagcompound.func_74762_e("power");
        this.essentia = nbttagcompound.func_74762_e("essentia");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("charges", this.charges);
        nbttagcompound.func_74768_a("power", this.power);
        nbttagcompound.func_74768_a("essentia", this.essentia);
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
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
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return Aspect.MAGIC;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.essentia;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection loc) {
        int re = Math.min(this.essentia, amount);
        this.essentia -= re;
        this.func_70296_d();
        return re;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection loc) {
        return 0;
    }
}

