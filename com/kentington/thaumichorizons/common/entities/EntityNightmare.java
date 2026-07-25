/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityEndersteed;
import com.kentington.thaumichorizons.common.lib.NightmareTeleporter;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketMountNightmare;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityNightmare
extends EntityEndersteed {
    NightmareTeleporter nightmareTeleporterOverworld;
    NightmareTeleporter nightmareTeleporterNether;

    public EntityNightmare(World p_i1685_1_) {
        super(p_i1685_1_);
        this.field_70178_ae = true;
        if (!p_i1685_1_.field_72995_K) {
            this.nightmareTeleporterOverworld = new NightmareTeleporter(MinecraftServer.func_71276_C().func_71218_a(0));
            this.nightmareTeleporterNether = new NightmareTeleporter(MinecraftServer.func_71276_C().func_71218_a(-1));
        }
    }

    @Override
    public void func_110206_u(int p_110206_1_) {
        double blocks = (double)p_110206_1_ / 7.0;
        if (p_110206_1_ < 90 || this.field_70170_p.field_73011_w.field_76574_g != 0 && this.field_70170_p.field_73011_w.field_76574_g != -1) {
            this.teleportTo(this.field_70165_t - blocks * Math.sin(Math.toRadians(this.field_70177_z)), this.field_70163_u, this.field_70161_v + blocks * Math.cos(Math.toRadians(this.field_70177_z)));
        } else if (this.field_71093_bK == 0 || this.field_71093_bK == -1) {
            if (this.field_71093_bK == 0) {
                this.netherport(-1);
            } else {
                this.netherport(0);
            }
        }
    }

    private void netherport(int dim) {
        Entity newNightmare;
        this.field_70170_p.func_72885_a((Entity)this, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0f), this.field_70161_v, 2.0f, true, true);
        EntityPlayerMP player = (EntityPlayerMP)this.field_70153_n;
        player.func_70078_a((Entity)null);
        if (this.field_71093_bK == 0) {
            player = this.playerTravelToDimension(player, -1);
            newNightmare = this.nightmareTravelToDimension(-1);
        } else {
            player = this.playerTravelToDimension(player, 0);
            newNightmare = this.nightmareTravelToDimension(0);
        }
        player.field_70177_z = newNightmare.field_70177_z;
        player.field_70125_A = newNightmare.field_70125_A;
        player.func_70078_a((Entity)null);
        player.func_70078_a(newNightmare);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketMountNightmare(newNightmare, (EntityPlayer)player), player);
    }

    public Entity nightmareTravelToDimension(int p_71027_1_) {
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            this.field_70170_p.field_72984_F.func_76320_a("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            int j = this.field_71093_bK;
            WorldServer worldserver = minecraftserver.func_71218_a(j);
            WorldServer worldserver1 = minecraftserver.func_71218_a(p_71027_1_);
            this.field_71093_bK = p_71027_1_;
            this.field_70170_p.func_72900_e((Entity)this);
            this.field_70128_L = false;
            this.field_70170_p.field_72984_F.func_76320_a("reposition");
            if (p_71027_1_ == -1) {
                minecraftserver.func_71203_ab().transferEntityToWorld((Entity)this, j, worldserver, worldserver1, (Teleporter)this.nightmareTeleporterNether);
            } else {
                minecraftserver.func_71203_ab().transferEntityToWorld((Entity)this, j, worldserver, worldserver1, (Teleporter)this.nightmareTeleporterOverworld);
            }
            this.field_70170_p.field_72984_F.func_76318_c("reloading");
            Entity entity = EntityList.func_75620_a((String)EntityList.func_75621_b((Entity)this), (World)worldserver1);
            if (entity != null) {
                entity.func_82141_a((Entity)this, true);
                worldserver1.func_72838_d(entity);
            }
            this.field_70128_L = true;
            this.field_70170_p.field_72984_F.func_76319_b();
            worldserver.func_82742_i();
            worldserver1.func_82742_i();
            this.field_70170_p.field_72984_F.func_76319_b();
            return entity;
        }
        return null;
    }

    public EntityPlayerMP playerTravelToDimension(EntityPlayerMP player, int p_71027_1_) {
        if (p_71027_1_ == -1) {
            player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, p_71027_1_, (Teleporter)this.nightmareTeleporterNether);
        } else {
            player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, p_71027_1_, (Teleporter)this.nightmareTeleporterOverworld);
        }
        return player;
    }

    @Override
    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.ThaumicHorizons.Nightmare.name");
    }

    public void func_70071_h_() {
        AxisAlignedBB axisalignedbb = AxisAlignedBB.func_72330_a((double)this.field_70121_D.field_72340_a, (double)this.field_70121_D.field_72338_b, (double)this.field_70121_D.field_72339_c, (double)this.field_70121_D.field_72336_d, (double)this.field_70121_D.field_72337_e, (double)this.field_70121_D.field_72334_f);
        if (this.field_70170_p.func_72830_b(axisalignedbb, Material.field_151587_i)) {
            this.field_70181_x += 0.1;
            if (this.field_70181_x > 0.25) {
                this.field_70181_x = 0.25;
            }
            this.field_70122_E = true;
            this.field_70143_R = 0.0f;
        } else if (this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)Math.floor(this.field_70163_u - 1.0), (int)this.field_70161_v).func_149688_o() == Material.field_151587_i) {
            this.field_70122_E = true;
            this.field_70143_R = 0.0f;
            if (this.field_70181_x < 0.0) {
                this.field_70181_x = 0.0;
            }
        }
        super.func_70071_h_();
        Block underfoot = this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v);
        Block in = this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
        Block up = this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v);
        if (underfoot.func_149688_o() == Material.field_151577_b) {
            this.field_70170_p.func_147449_b((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v, Blocks.field_150346_d);
            ThaumicHorizons.proxy.smeltFX((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v, this.field_70170_p, 10, false);
        }
        if (this.field_70170_p.field_72995_K && underfoot.func_149637_q() && this.field_70701_bs > 0.0f) {
            ThaumicHorizons.proxy.smeltFX((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v, this.field_70170_p, 3, false);
        }
        if (in.func_149688_o() == Material.field_151584_j || in.func_149688_o() == Material.field_151569_G || in.func_149688_o() == Material.field_151582_l || in.func_149688_o() == Material.field_151585_k) {
            this.field_70170_p.func_147468_f((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            ThaumicHorizons.proxy.smeltFX((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, this.field_70170_p, 15, false);
        }
        if (up.func_149688_o() == Material.field_151584_j || up.func_149688_o() == Material.field_151569_G || up.func_149688_o() == Material.field_151582_l || up.func_149688_o() == Material.field_151585_k) {
            this.field_70170_p.func_147468_f((int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v);
            ThaumicHorizons.proxy.smeltFX((int)this.field_70165_t, (int)this.field_70163_u + 1, (int)this.field_70161_v, this.field_70170_p, 15, false);
        }
    }

    public void func_70110_aj() {
        this.field_70134_J = false;
        this.field_70143_R = 0.0f;
    }
}

