/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockBreakable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.GatewayTeleporter;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import com.kentington.thaumichorizons.common.tiles.TileSlot;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.BlockBreakable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class BlockPortalTH
extends BlockBreakable {
    public BlockPortalTH() {
        super("portal", ThaumicHorizons.portal, false);
        this.func_149675_a(true);
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {
        for (int l = 0; l < 4; ++l) {
            double d0 = (float)p_149734_2_ + p_149734_5_.nextFloat();
            double d1 = (float)p_149734_3_ + p_149734_5_.nextFloat();
            double d2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
            double d3 = 0.0;
            double d4 = 0.0;
            double d5 = 0.0;
            int i1 = p_149734_5_.nextInt(2) * 2 - 1;
            d3 = ((double)p_149734_5_.nextFloat() - 0.5) * 0.5;
            d4 = ((double)p_149734_5_.nextFloat() - 0.5) * 0.5;
            d5 = ((double)p_149734_5_.nextFloat() - 0.5) * 0.5;
            if (p_149734_1_.func_147439_a(p_149734_2_ - 1, p_149734_3_, p_149734_4_) != this && p_149734_1_.func_147439_a(p_149734_2_ + 1, p_149734_3_, p_149734_4_) != this) {
                d0 = (double)p_149734_2_ + 0.5 + 0.25 * (double)i1;
                d3 = p_149734_5_.nextFloat() * 2.0f * (float)i1;
            } else {
                d2 = (double)p_149734_4_ + 0.5 + 0.25 * (double)i1;
                d5 = p_149734_5_.nextFloat() * 2.0f * (float)i1;
            }
            p_149734_1_.func_72869_a("portal", d0, d1, d2, d3, d4, d5);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.func_150899_d((int)0);
    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity player) {
        if (player.field_70154_o == null && player.field_70153_n == null && player instanceof EntityPlayerMP) {
            if (player.field_71088_bW > 0) {
                player.field_71088_bW = 100;
                return;
            }
            player.field_71088_bW = 100;
            int targetX = 0;
            int targetY = 0;
            int targetZ = 0;
            if (world.field_73011_w.field_76574_g == ThaumicHorizons.dimensionPocketId) {
                int planeNum = (z + 128) / 256;
                int which = world.func_72805_g(x, y, z);
                switch (which) {
                    case 0: {
                        targetX = PocketPlaneData.planes.get((int)planeNum).portalA[0];
                        targetY = PocketPlaneData.planes.get((int)planeNum).portalA[1] - 2;
                        targetZ = PocketPlaneData.planes.get((int)planeNum).portalA[2];
                        break;
                    }
                    case 2: {
                        targetX = PocketPlaneData.planes.get((int)planeNum).portalB[0];
                        targetY = PocketPlaneData.planes.get((int)planeNum).portalB[1] - 2;
                        targetZ = PocketPlaneData.planes.get((int)planeNum).portalB[2];
                        break;
                    }
                    case 1: {
                        targetX = PocketPlaneData.planes.get((int)planeNum).portalC[0];
                        targetY = PocketPlaneData.planes.get((int)planeNum).portalC[1] - 2;
                        targetZ = PocketPlaneData.planes.get((int)planeNum).portalC[2];
                        break;
                    }
                    case 3: {
                        targetX = PocketPlaneData.planes.get((int)planeNum).portalD[0];
                        targetY = PocketPlaneData.planes.get((int)planeNum).portalD[1] - 2;
                        targetZ = PocketPlaneData.planes.get((int)planeNum).portalD[2];
                    }
                }
                MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                ((EntityPlayerMP)player).field_71133_b.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)player, 0, (Teleporter)new GatewayTeleporter(mServer.func_71218_a(ThaumicHorizons.dimensionPocketId), targetX, targetY, targetZ, player.field_70177_z));
            } else {
                TileEntity te;
                int slotY = y;
                int slotX = x;
                int slotZ = z;
                while (world.func_147439_a(slotX, ++slotY, slotZ) == ThaumicHorizons.blockPortal) {
                }
                if (world.func_147439_a(slotX, slotY, slotZ) == ThaumicHorizons.blockGateway) {
                    if (world.func_147439_a(slotX + 1, slotY, slotZ) == ThaumicHorizons.blockSlot) {
                        ++slotX;
                    } else if (world.func_147439_a(slotX - 1, slotY, slotZ) == ThaumicHorizons.blockSlot) {
                        --slotX;
                    } else if (world.func_147439_a(slotX, slotY, slotZ + 1) == ThaumicHorizons.blockSlot) {
                        ++slotZ;
                    } else if (world.func_147439_a(slotX, slotY, slotZ - 1) == ThaumicHorizons.blockSlot) {
                        --slotZ;
                    }
                }
                if ((te = world.func_147438_o(slotX, slotY, slotZ)) instanceof TileSlot) {
                    TileSlot tco = (TileSlot)te;
                    targetY = 128;
                    float targetYaw = 0.0f;
                    switch (tco.which) {
                        case 1: {
                            targetZ = tco.pocketID * 256 + PocketPlaneData.planes.get((int)tco.pocketID).radius;
                            targetYaw = 180.0f;
                            break;
                        }
                        case 2: {
                            targetZ = tco.pocketID * 256 - PocketPlaneData.planes.get((int)tco.pocketID).radius;
                            break;
                        }
                        case 3: {
                            targetZ = tco.pocketID * 256;
                            targetX = PocketPlaneData.planes.get((int)tco.pocketID).radius;
                            targetYaw = 90.0f;
                            break;
                        }
                        case 4: {
                            targetZ = tco.pocketID * 256;
                            targetX = -PocketPlaneData.planes.get((int)tco.pocketID).radius;
                            targetYaw = 270.0f;
                        }
                    }
                    MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                    ((EntityPlayerMP)player).field_71133_b.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)player, ThaumicHorizons.dimensionPocketId, (Teleporter)new GatewayTeleporter(mServer.func_71218_a(ThaumicHorizons.dimensionPocketId), targetX, targetY, targetZ, targetYaw));
                }
            }
        }
    }
}

