/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Teleporter
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.NightmareTeleporter;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.Teleporter;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;

public class TilePortalTH
extends TileThaumcraft {
    public int opencount = -1;
    private int count = 0;
    public int dimension = 0;
    public int pocket = -1;

    public boolean canUpdate() {
        return true;
    }

    public double func_145833_n() {
        return 9216.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    public void func_145845_h() {
        List ents;
        ++this.count;
        if (this.field_145850_b.field_72995_K && (this.count % 250 == 0 || this.count == 0)) {
            this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:evilportal", 1.0f, 1.0f, false);
        }
        if (this.field_145850_b.field_72995_K && this.opencount < 30) {
            ++this.opencount;
        }
        if (!this.field_145850_b.field_72995_K && this.count % 5 == 0 && (ents = this.field_145850_b.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(0.5, 1.0, 0.5))).size() > 0) {
            for (Object e : ents) {
                EntityPlayerMP player = (EntityPlayerMP)e;
                if (player.field_70154_o != null || player.field_70153_n != null) continue;
                MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                if (player.field_71088_bW > 0) continue;
                player.field_71088_bW = 50;
                int oldDim = player.field_71093_bK;
                player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, this.dimension, (Teleporter)new NightmareTeleporter(mServer.func_71218_a(this.dimension)));
                if (oldDim == ThaumicHorizons.dimensionPocketId && ((HashMap)((Object)PocketPlaneData.positions)).containsKey(player.func_70005_c_())) {
                    System.out.println("Loading position " + ((HashMap)((Object)PocketPlaneData.positions)).get(player.func_70005_c_()));
                    player.func_70634_a(((Vec3)((HashMap)((Object)PocketPlaneData.positions)).get((Object)player.func_70005_c_())).field_72450_a, ((Vec3)((HashMap)((Object)PocketPlaneData.positions)).get((Object)player.func_70005_c_())).field_72448_b, ((Vec3)((HashMap)((Object)PocketPlaneData.positions)).get((Object)player.func_70005_c_())).field_72449_c);
                    continue;
                }
                if (this.pocket < 0) continue;
                ((HashMap)((Object)PocketPlaneData.positions)).put(player.func_70005_c_(), Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d + 0.5), (double)((double)this.field_145849_e + 0.5)));
                System.out.println("Saving position " + ((HashMap)((Object)PocketPlaneData.positions)).get(player.func_70005_c_()));
                player.func_70634_a(0.5, 129.0, (double)((float)(128 * (this.pocket - 3)) + 0.5f));
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (this.count > 250) {
            this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f, false);
            Thaumcraft.proxy.burst(this.func_145831_w(), (double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), 3.0f);
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("dimension", this.dimension);
        nbttagcompound.func_74768_a("count", this.count);
        nbttagcompound.func_74768_a("pocket", this.pocket);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.dimension = nbttagcompound.func_74762_e("dimension");
        this.count = nbttagcompound.func_74762_e("count");
        this.pocket = nbttagcompound.func_74762_e("pocket");
    }
}

