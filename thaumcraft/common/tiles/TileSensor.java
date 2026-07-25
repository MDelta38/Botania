/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package thaumcraft.common.tiles;

import java.util.ArrayList;
import java.util.WeakHashMap;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import thaumcraft.common.config.ConfigBlocks;

public class TileSensor
extends TileEntity {
    public byte note = 0;
    public byte tone = 0;
    public int redstoneSignal = 0;
    public static WeakHashMap<WorldServer, ArrayList<Integer[]>> noteBlockEvents = new WeakHashMap();

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74774_a("note", this.note);
        par1NBTTagCompound.func_74774_a("tone", this.tone);
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.note = par1NBTTagCompound.func_74771_c("note");
        this.tone = par1NBTTagCompound.func_74771_c("tone");
        if (this.note < 0) {
            this.note = 0;
        }
        if (this.note > 24) {
            this.note = (byte)24;
        }
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            ArrayList<Integer[]> nbe;
            if (this.redstoneSignal > 0) {
                --this.redstoneSignal;
                if (this.redstoneSignal == 0) {
                    this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
                    this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
            }
            if ((nbe = noteBlockEvents.get(this.field_145850_b)) != null) {
                for (Integer[] dat : nbe) {
                    if (dat[3] != this.tone || dat[4] != this.note || !(this.func_145835_a((double)dat[0].intValue() + 0.5, (double)dat[1].intValue() + 0.5, (double)dat[2].intValue() + 0.5) <= 4096.0)) continue;
                    this.triggerNote(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, false);
                    this.redstoneSignal = 10;
                    this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
                    this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockWoodenDevice);
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    break;
                }
            }
        }
    }

    public double func_145835_a(double par1, double par3, double par5) {
        double var7 = (double)this.field_145851_c + 0.5 - par1;
        double var9 = (double)this.field_145848_d + 0.5 - par3;
        double var11 = (double)this.field_145849_e + 0.5 - par5;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    public boolean canUpdate() {
        return true;
    }

    public void updateTone() {
        Material var5 = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o();
        this.tone = 0;
        if (var5 == Material.field_151576_e) {
            this.tone = 1;
        }
        if (var5 == Material.field_151595_p) {
            this.tone = (byte)2;
        }
        if (var5 == Material.field_151592_s) {
            this.tone = (byte)3;
        }
        if (var5 == Material.field_151575_d) {
            this.tone = (byte)4;
        }
    }

    public void changePitch() {
        this.note = (byte)((this.note + 1) % 25);
        this.func_70296_d();
    }

    public void triggerNote(World par1World, int par2, int par3, int par4, boolean sound) {
        if (par1World.func_147439_a(par2, par3 + 1, par4).func_149688_o() == Material.field_151579_a) {
            int var6 = -1;
            if (sound) {
                Material var5 = par1World.func_147439_a(par2, par3 - 1, par4).func_149688_o();
                var6 = 0;
                if (var5 == Material.field_151576_e) {
                    var6 = 1;
                }
                if (var5 == Material.field_151595_p) {
                    var6 = 2;
                }
                if (var5 == Material.field_151592_s) {
                    var6 = 3;
                }
                if (var5 == Material.field_151575_d) {
                    var6 = 4;
                }
            }
            par1World.func_147452_c(par2, par3, par4, ConfigBlocks.blockWoodenDevice, var6, (int)this.note);
        }
    }
}

