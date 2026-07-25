/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.foci.ItemFocusPortableHole;
import thaumcraft.common.tiles.TileMemory;

public class TileHole
extends TileMemory {
    public short countdown = 0;
    public short countdownmax = (short)120;
    public byte count = 0;
    public byte direction = 0;

    public TileHole() {
    }

    public TileHole(Block bi, int md, short max, byte count, byte direction, TileEntity te) {
        super(bi, md, te);
        this.count = count;
        this.countdownmax = max;
        this.direction = direction;
    }

    public TileHole(byte count) {
        this.count = count;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.field_72995_K) {
            this.surroundwithsparkles();
        }
        if (this.countdown == 0 && this.count > 1 && this.direction != -1) {
            int ii = this.field_145851_c;
            int jj = this.field_145848_d;
            int kk = this.field_145849_e;
            switch (this.direction) {
                case 0: 
                case 1: {
                    for (int a = 0; a < 9; ++a) {
                        if (a / 3 == 1 && a % 3 == 1) continue;
                        ItemFocusPortableHole.createHole(this.field_145850_b, ii - 1 + a / 3, jj, kk - 1 + a % 3, -1, (byte)1, this.countdownmax);
                    }
                    break;
                }
                case 2: 
                case 3: {
                    for (int a = 0; a < 9; ++a) {
                        if (a / 3 == 1 && a % 3 == 1) continue;
                        ItemFocusPortableHole.createHole(this.field_145850_b, ii - 1 + a / 3, jj - 1 + a % 3, kk, -1, (byte)1, this.countdownmax);
                    }
                    break;
                }
                case 4: 
                case 5: {
                    for (int a = 0; a < 9; ++a) {
                        if (a / 3 == 1 && a % 3 == 1) continue;
                        ItemFocusPortableHole.createHole(this.field_145850_b, ii, jj - 1 + a / 3, kk - 1 + a % 3, -1, (byte)1, this.countdownmax);
                    }
                    break;
                }
            }
            switch (this.direction) {
                case 0: {
                    ++jj;
                    break;
                }
                case 1: {
                    --jj;
                    break;
                }
                case 2: {
                    ++kk;
                    break;
                }
                case 3: {
                    --kk;
                    break;
                }
                case 4: {
                    ++ii;
                    break;
                }
                case 5: {
                    --ii;
                }
            }
            if (!ItemFocusPortableHole.createHole(this.field_145850_b, ii, jj, kk, this.direction, (byte)(this.count - 1), this.countdownmax)) {
                this.count = 0;
            }
        }
        this.countdown = (short)(this.countdown + 1);
        if (this.countdown >= this.countdownmax) {
            if (this.field_145850_b.field_72995_K) {
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 0x400040, 1);
            } else {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, this.oldmeta, 0);
                this.recreateTileEntity();
            }
            this.field_145850_b.func_147464_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, 2);
        }
    }

    private void surroundwithsparkles() {
        boolean b6;
        boolean yp = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149662_c();
        boolean xp = this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e).func_149662_c();
        boolean zp = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1).func_149662_c();
        boolean yn = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149662_c();
        boolean xn = this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e).func_149662_c();
        boolean zn = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1).func_149662_c();
        boolean b1 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != ConfigBlocks.blockHole;
        boolean b2 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != ConfigBlocks.blockHole;
        boolean b3 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) != ConfigBlocks.blockHole;
        boolean b4 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) != ConfigBlocks.blockHole;
        boolean b5 = this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockHole;
        boolean bl = b6 = this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockHole;
        if (!xp && yp && b6) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!xn && yp && b5) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!zp && yp && b4) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1, 2);
        }
        if (!zn && yp && b3) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e, 2);
        }
        if (!xp && yn && b6) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!xn && yn && b5) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!zp && yn && b4) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e + 1, 2);
        }
        if (!zn && yn && b3) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e, 2);
        }
        if (!yp && xp && b1) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!yn && xp && b2) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, this.field_145848_d, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!zp && xp && b4) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
        }
        if (!zn && xp && b3) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
        }
        if (!yp && xn && b1) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!yn && xn && b2) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, this.field_145848_d, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat(), 2);
        }
        if (!zp && xn && b4) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
        }
        if (!zn && xn && b3) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
        }
        if (!xp && zp && b6) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
        }
        if (!xn && zp && b5) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e + 1, 2);
        }
        if (!yp && zp && b1) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1, 2);
        }
        if (!yn && zp && b2) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e + 1, 2);
        }
        if (!xp && zn && b6) {
            Thaumcraft.proxy.sparkle(this.field_145851_c + 1, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
        }
        if (!xn && zn && b5) {
            Thaumcraft.proxy.sparkle(this.field_145851_c, (float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat(), this.field_145849_e, 2);
        }
        if (!yp && zn && b1) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e, 2);
        }
        if (!yn && zn && b2) {
            Thaumcraft.proxy.sparkle((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d, this.field_145849_e, 2);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.readCustomNBT(nbttagcompound);
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.countdown = nbttagcompound.func_74765_d("countdown");
        this.countdownmax = nbttagcompound.func_74765_d("countdownmax");
        this.count = nbttagcompound.func_74771_c("count");
        this.direction = nbttagcompound.func_74771_c("direction");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        this.writeCustomNBT(nbttagcompound);
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74777_a("countdown", this.countdown);
        nbttagcompound.func_74777_a("countdownmax", this.countdownmax);
        nbttagcompound.func_74774_a("count", this.count);
        nbttagcompound.func_74774_a("direction", this.direction);
    }

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readCustomNBT(pkt.func_148857_g());
    }
}

