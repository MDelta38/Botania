/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.common.INullSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockVoidBramble
extends BlockBaseContainer {
    public BlockVoidBramble() {
        super(Material.field_151585_k, TileEntityVoidBramble.class);
        this.func_149722_s();
        this.func_149752_b(1000.0f);
        this.func_149715_a(0.125f);
        this.func_149672_a(Block.field_149779_h);
        float f = 0.45f;
        this.func_149676_a(0.050000012f, 0.0f, 0.050000012f, 0.95f, 1.0f, 0.95f);
    }

    public int func_149645_b() {
        return 6;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity) {
        if (!world.field_72995_K && entity instanceof EntityLivingBase && entity instanceof EntityLivingBase) {
            BlockVoidBramble.teleportRandomly(world, posX, posY, posZ, entity, 500);
        }
    }

    public static void teleportRandomly(World world, int posX, int posY, int posZ, Entity entity, int distance) {
        int doubleDistance = distance * 2;
        posX += world.field_73012_v.nextInt(doubleDistance) - distance;
        posZ += world.field_73012_v.nextInt(doubleDistance) - distance;
        int maxY = Math.min(posY + 64, 250);
        while (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a() && posY >= 0) {
            --posY;
        }
        while (!(world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a() && world.func_147439_a(posX, posY, posZ) != Blocks.field_150357_h && world.func_147437_c(posX, posY + 1, posZ) && world.func_147437_c(posX, posY + 2, posZ) && world.func_147437_c(posX, posY + 3, posZ) || posY >= maxY)) {
            ++posY;
        }
        if (posY > 0 && posY < maxY) {
            ItemGeneral.teleportToLocation(world, 0.5 + (double)posX, 1.0 + (double)posY, 0.5 + (double)posZ, world.field_73011_w.field_76574_g, entity, true);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(2) == 0) {
            double d0 = (float)x + rand.nextFloat();
            double d1 = (double)((float)y + 0.15f + rand.nextFloat() * 0.3f) + 0.5;
            double d2 = (float)z + rand.nextFloat();
            world.func_72869_a(ParticleEffect.PORTAL.toString(), d0, d1, d2, 0.0, -1.2, 0.0);
        }
    }

    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        TileEntityVoidBramble tile;
        if (!world.field_72995_K && (tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z)) != null && (player.field_71075_bZ.field_75098_d || player.func_70005_c_().equals(tile.getOwner()))) {
            int dy = y;
            while (world.func_147439_a(x, dy, z) == this) {
                world.func_147468_f(x, dy, z);
                world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)dy, 0.5 + (double)z, new ItemStack((Block)this)));
                ++dy;
            }
        }
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!world.field_72995_K && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            TileEntityVoidBramble tile = (TileEntityVoidBramble)world.func_147438_o(x, y, z);
            if (tile != null) {
                tile.setOwner(player.func_70005_c_());
            }
        }
    }

    @Override
    public TileEntity func_149915_a(World world, int metadata) {
        return new TileEntityVoidBramble();
    }

    public static class TileEntityVoidBramble
    extends TileEntityBase
    implements INullSource {
        private String owner;
        private static final String OWNER_KEY = "WITCPlacer";

        @Override
        public boolean isPowerInvalid() {
            return this.func_145837_r();
        }

        @Override
        protected void initiate() {
            super.initiate();
            if (!this.field_145850_b.field_72995_K) {
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == Witchery.Blocks.VOID_BRAMBLE) {
                    Log.instance().debug("Initiating void bramble tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
                    PowerSources.instance().registerNullSource(this);
                } else {
                    Log.instance().warning("Void bramble tile entity exists without a corresponding block at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
                }
            }
        }

        public void func_145843_s() {
            super.func_145843_s();
            if (!this.field_145850_b.field_72995_K) {
                Log.instance().debug("Invalidating void bramble tile at: " + this.field_145851_c + ", " + this.field_145848_d + ", " + this.field_145849_e);
                PowerSources.instance().removeNullSource(this);
            }
        }

        public void setOwner(String username) {
            this.owner = username;
        }

        public String getOwner() {
            return this.owner != null ? this.owner : "";
        }

        public void func_145841_b(NBTTagCompound nbtTag) {
            super.func_145841_b(nbtTag);
            nbtTag.func_74778_a(OWNER_KEY, this.getOwner());
        }

        public void func_145839_a(NBTTagCompound nbtTag) {
            super.func_145839_a(nbtTag);
            this.owner = nbtTag.func_74764_b(OWNER_KEY) ? nbtTag.func_74779_i(OWNER_KEY) : "";
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            this.func_145839_a(packet.func_148857_g());
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        @Override
        public World getWorld() {
            return this.field_145850_b;
        }

        @Override
        public int getPosX() {
            return this.field_145851_c;
        }

        @Override
        public int getPosY() {
            return this.field_145848_d;
        }

        @Override
        public int getPosZ() {
            return this.field_145849_e;
        }

        @Override
        public float getRange() {
            return 32.0f;
        }
    }
}

