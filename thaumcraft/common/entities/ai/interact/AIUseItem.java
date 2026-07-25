/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.server.management.ItemInWorldManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.entities.ai.interact;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;
import thaumcraft.common.entities.golems.Marker;

public class AIUseItem
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private int xx;
    private int yy;
    private int zz;
    private float movementSpeed;
    private float distance;
    private World theWorld;
    private Block block = Blocks.field_150350_a;
    private int blockMd = 0;
    FakePlayer player;
    private int count = 0;
    private int color = -1;
    ItemInWorldManager im;
    int nextTick = 0;

    public AIUseItem(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
        this.distance = MathHelper.func_76123_f((float)(this.theGolem.getRange() / 3.0f));
        if (this.theWorld instanceof WorldServer) {
            this.player = FakePlayerFactory.get((WorldServer)((WorldServer)this.theWorld), (GameProfile)new GameProfile((UUID)null, "FakeThaumcraftGolem"));
        }
        try {
            this.nextTick = this.theGolem.field_70173_aa + this.theWorld.field_73012_v.nextInt(6);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public boolean func_75250_a() {
        int d;
        boolean ignoreItem = false;
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
        if (tile == null || !(tile instanceof IInventory)) {
            ignoreItem = true;
        }
        if ((d = 5 - this.theGolem.field_70173_aa) < 1) {
            d = 1;
        }
        if (this.theGolem.itemCarried == null && !ignoreItem || this.theGolem.field_70173_aa < this.nextTick || !this.theGolem.func_70661_as().func_75500_f()) {
            return false;
        }
        this.nextTick = this.theGolem.field_70173_aa + d * 3;
        return this.findSomething();
    }

    public boolean func_75253_b() {
        return this.theWorld.func_147439_a(this.xx, this.yy, this.zz) == this.block && this.theWorld.func_72805_g(this.xx, this.yy, this.zz) == this.blockMd && this.count-- > 0 && !this.theGolem.func_70661_as().func_75500_f();
    }

    public void func_75246_d() {
        this.theGolem.func_70671_ap().func_75650_a((double)this.xx + 0.5, (double)this.yy + 0.5, (double)this.zz + 0.5, 30.0f, 30.0f);
        double dist = this.theGolem.func_70092_e((double)this.xx + 0.5, (double)this.yy + 0.5, (double)this.zz + 0.5);
        if (dist <= 4.0) {
            this.click();
        }
    }

    public void func_75251_c() {
        this.count = 0;
        this.theGolem.func_70661_as().func_75499_g();
    }

    public void func_75249_e() {
        this.count = 200;
        this.theGolem.func_70661_as().func_75492_a((double)this.xx + 0.5, (double)this.yy + 0.5, (double)this.zz + 0.5, (double)this.theGolem.func_70689_ay());
    }

    void click() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        boolean ignoreItem = false;
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        TileEntity tile = this.theGolem.field_70170_p.func_147438_o(cX, cY, cZ);
        if (tile == null || !(tile instanceof IInventory)) {
            ignoreItem = true;
        }
        this.player.func_70080_a(this.theGolem.field_70165_t, this.theGolem.field_70163_u, this.theGolem.field_70161_v, this.theGolem.field_70177_z, this.theGolem.field_70125_A);
        this.player.func_70062_b(0, this.theGolem.itemCarried);
        this.player.func_70095_a(this.theGolem.getToggles()[2]);
        Iterator<Integer> i$ = GolemHelper.getMarkedSides(this.theGolem, this.xx, this.yy, this.zz, this.theGolem.field_70170_p.field_73011_w.field_76574_g, (byte)this.color).iterator();
        if (i$.hasNext()) {
            Integer side = i$.next();
            int x = 0;
            int y = 0;
            int z = 0;
            if (this.theGolem.field_70170_p.func_147437_c(this.xx, this.yy, this.zz)) {
                x = ForgeDirection.getOrientation((int)side.intValue()).getOpposite().offsetX;
                y = ForgeDirection.getOrientation((int)side.intValue()).getOpposite().offsetY;
                z = ForgeDirection.getOrientation((int)side.intValue()).getOpposite().offsetZ;
            }
            if (this.im == null) {
                this.im = new ItemInWorldManager(this.theGolem.field_70170_p);
            }
            if (this.theGolem.itemCarried == null && !ignoreItem) {
                this.func_75251_c();
                return;
            }
            try {
                if (this.theGolem.getToggles()[1]) {
                    this.theGolem.startLeftArmTimer();
                    this.im.func_73074_a(this.xx + x, this.yy + y, this.zz + z, side.intValue());
                } else if (this.im.func_73078_a((EntityPlayer)this.player, this.theGolem.field_70170_p, this.theGolem.itemCarried, this.xx + x, this.yy + y, this.zz + z, side.intValue(), 0.5f, 0.5f, 0.5f)) {
                    this.theGolem.startRightArmTimer();
                }
                this.theGolem.itemCarried = this.player.func_71045_bC();
                if (this.theGolem.itemCarried.field_77994_a <= 0) {
                    this.theGolem.itemCarried = null;
                }
                for (int a = 1; a < this.player.field_71071_by.field_70462_a.length; ++a) {
                    if (this.player.field_71071_by.func_70301_a(a) == null) continue;
                    if (this.theGolem.itemCarried == null) {
                        this.theGolem.itemCarried = this.player.field_71071_by.func_70301_a(a).func_77946_l();
                    } else {
                        this.player.func_71019_a(this.player.field_71071_by.func_70301_a(a), false);
                    }
                    this.player.field_71071_by.func_70299_a(a, null);
                }
                this.theGolem.updateCarried();
                this.func_75251_c();
                return;
            }
            catch (Exception e) {
                this.func_75251_c();
                return;
            }
        }
    }

    boolean findSomething() {
        ArrayList<Byte> matchingColors = this.theGolem.getColorsMatching(this.theGolem.itemCarried);
        for (byte col : matchingColors) {
            ArrayList<Marker> markers = this.theGolem.getMarkers();
            for (Marker marker : markers) {
                if (marker.color != col && col != -1 || this.theGolem.getToggles()[0] && !this.theGolem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z) || !this.theGolem.getToggles()[0] && this.theGolem.field_70170_p.func_147437_c(marker.x, marker.y, marker.z)) continue;
                ForgeDirection opp = ForgeDirection.getOrientation((int)marker.side);
                if (!this.theGolem.field_70170_p.func_147437_c(marker.x + opp.offsetX, marker.y + opp.offsetY, marker.z + opp.offsetZ)) continue;
                this.color = col;
                this.xx = marker.x;
                this.yy = marker.y;
                this.zz = marker.z;
                this.block = this.theWorld.func_147439_a(this.xx, this.yy, this.zz);
                this.blockMd = this.theWorld.func_72805_g(this.xx, this.yy, this.zz);
                return true;
            }
        }
        return false;
    }
}

