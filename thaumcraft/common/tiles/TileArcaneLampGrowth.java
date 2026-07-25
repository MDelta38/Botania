/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.utils.CropUtils;

public class TileArcaneLampGrowth
extends TileThaumcraft
implements IEssentiaTransport {
    public ForgeDirection facing = ForgeDirection.getOrientation((int)0);
    private boolean reserve = false;
    public int charges = -1;
    int lx = 0;
    int ly = 0;
    int lz = 0;
    Block lid = Blocks.field_150350_a;
    int lmd = 0;
    ArrayList<BlockCoordinates> checklist = new ArrayList();
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
            if (this.charges <= 0) {
                if (this.reserve) {
                    this.charges = 100;
                    this.reserve = false;
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                } else if (this.drawEssentia()) {
                    this.charges = 100;
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
            }
            if (!this.reserve && this.drawEssentia()) {
                this.reserve = true;
            }
            if (this.charges == 0) {
                this.charges = -1;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.charges > 0) {
                this.updatePlant();
            }
        }
    }

    boolean isPlant(int x, int y, int z) {
        boolean flag = this.field_145850_b.func_147439_a(x, y, z) instanceof IGrowable;
        Material mat = this.field_145850_b.func_147439_a(x, y, z).func_149688_o();
        return (flag || mat == Material.field_151570_A || mat == Material.field_151585_k) && mat != Material.field_151577_b;
    }

    private void updatePlant() {
        if (this.lid != this.field_145850_b.func_147439_a(this.lx, this.ly, this.lz) || this.lmd != this.field_145850_b.func_72805_g(this.lx, this.ly, this.lz)) {
            EntityPlayer p = this.field_145850_b.func_72977_a((double)this.lx, (double)this.ly, (double)this.lz, 32.0);
            if (p != null) {
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(this.lx, this.ly, this.lz, 0x40FF40), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.lx, (double)this.ly, (double)this.lz, 32.0));
            }
            this.lid = this.field_145850_b.func_147439_a(this.lx, this.ly, this.lz);
            this.lmd = this.field_145850_b.func_72805_g(this.lx, this.ly, this.lz);
        }
        int distance = 6;
        if (this.checklist.size() == 0) {
            for (int a = -distance; a <= distance; ++a) {
                for (int b = -distance; b <= distance; ++b) {
                    this.checklist.add(new BlockCoordinates(this.field_145851_c + a, this.field_145848_d + distance, this.field_145849_e + b));
                }
            }
            Collections.shuffle(this.checklist, this.field_145850_b.field_73012_v);
        }
        int x = this.checklist.get((int)0).x;
        int z = this.checklist.get((int)0).z;
        this.checklist.remove(0);
        for (int y = this.checklist.get((int)0).y; y >= this.field_145848_d - distance; --y) {
            if (this.field_145850_b.func_147437_c(x, y, z) || !this.isPlant(x, y, z) || !(this.func_145835_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5) < (double)(distance * distance)) || CropUtils.isGrownCrop(this.field_145850_b, x, y, z) || !CropUtils.doesLampGrow(this.field_145850_b, x, y, z)) continue;
            --this.charges;
            this.lx = x;
            this.ly = y;
            this.lz = z;
            this.lid = this.field_145850_b.func_147439_a(x, y, z);
            this.lmd = this.field_145850_b.func_72805_g(x, y, z);
            this.field_145850_b.func_147464_a(x, y, z, this.field_145850_b.func_147439_a(x, y, z), 1);
            return;
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("orientation"));
        this.reserve = nbttagcompound.func_74767_n("reserve");
        this.charges = nbttagcompound.func_74762_e("charges");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
        nbttagcompound.func_74757_a("reserve", this.reserve);
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
            if (ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing) && ic.takeEssentia(Aspect.PLANT, 1, this.facing.getOpposite()) == 1) {
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
        return Aspect.PLANT;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return face == this.facing && (!this.reserve || this.charges <= 0) ? 128 : 0;
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
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection loc) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection loc) {
        return 0;
    }
}

