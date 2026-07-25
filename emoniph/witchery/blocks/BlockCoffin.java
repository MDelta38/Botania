/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCoffin
extends BlockBaseContainer {
    private static final int[][] DIRECTIONS = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public BlockCoffin() {
        super(Material.field_151575_d, TileEntityCoffin.class);
        this.registerWithCreateTab = false;
        this.func_149711_c(1.0f);
        this.func_149649_H();
        this.setupBounds();
    }

    public static int getDirection(int meta) {
        return meta & 3;
    }

    public boolean isBed(IBlockAccess world, int x, int y, int z, EntityLivingBase player) {
        return true;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        int j1;
        if (world.field_72995_K) {
            return true;
        }
        int i1 = world.func_72805_g(x, y, z);
        int origX = x;
        int origZ = z;
        if (!BlockCoffin.isBlockHeadOfBed(i1)) {
            j1 = BlockCoffin.getDirection(i1);
            if (world.func_147439_a(x += DIRECTIONS[j1][0], y, z += DIRECTIONS[j1][1]) != this) {
                return true;
            }
            i1 = world.func_72805_g(x, y, z);
        } else {
            j1 = BlockCoffin.getDirection(i1);
            origX -= DIRECTIONS[j1][0];
            origZ -= DIRECTIONS[j1][1];
        }
        if (player.func_70093_af()) {
            TileEntityCoffin tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityCoffin.class);
            if (tile != null) {
                TileEntityCoffin tile2;
                if (tile.open && BlockCoffin.isBedOccupied(i1)) {
                    EntityPlayer entityplayer1 = null;
                    for (EntityPlayer entityplayer2 : world.field_73010_i) {
                        if (!entityplayer2.func_70608_bn()) continue;
                        ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
                        if (chunkcoordinates.field_71574_a != x || chunkcoordinates.field_71572_b != y || chunkcoordinates.field_71573_c != z) continue;
                        entityplayer1 = entityplayer2;
                    }
                    if (entityplayer1 != null) {
                        return true;
                    }
                }
                if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN) || world.isSideSolid(origX, y + 1, origZ, ForgeDirection.DOWN)) {
                    return true;
                }
                tile.open = !tile.open;
                if (!BlockCoffin.isBlockHeadOfBed(i1)) {
                    int j12 = BlockCoffin.getDirection(i1);
                    tile2 = BlockUtil.getTileEntity((IBlockAccess)world, x + DIRECTIONS[j12][0], y, z + DIRECTIONS[j12][1], TileEntityCoffin.class);
                    if (tile2 != null) {
                        tile2.open = tile.open;
                        world.func_147471_g(tile2.field_145851_c, tile2.field_145848_d, tile2.field_145849_e);
                    }
                } else {
                    int j13 = BlockCoffin.getDirection(i1);
                    tile2 = BlockUtil.getTileEntity((IBlockAccess)world, x - DIRECTIONS[j13][0], y, z - DIRECTIONS[j13][1], TileEntityCoffin.class);
                    if (tile2 != null) {
                        tile2.open = tile.open;
                        world.func_147471_g(tile2.field_145851_c, tile2.field_145848_d, tile2.field_145849_e);
                    }
                }
                world.func_147471_g(x, y, z);
            }
            return true;
        }
        TileEntityCoffin tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityCoffin.class);
        if (tile != null) {
            if (!tile.open) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("witchery.nosleep.closedcoffin", new Object[0]));
                return true;
            }
        } else {
            return true;
        }
        if (world.field_73011_w.func_76567_e() && world.func_72807_a(x, z) != BiomeGenBase.field_76778_j) {
            EntityPlayer.EnumStatus enumstatus;
            if (BlockCoffin.isBedOccupied(i1)) {
                EntityPlayer entityplayer1 = null;
                for (EntityPlayer entityplayer2 : world.field_73010_i) {
                    if (!entityplayer2.func_70608_bn()) continue;
                    ChunkCoordinates chunkcoordinates = entityplayer2.field_71081_bT;
                    if (chunkcoordinates.field_71574_a != x || chunkcoordinates.field_71572_b != y || chunkcoordinates.field_71573_c != z) continue;
                    entityplayer1 = entityplayer2;
                }
                if (entityplayer1 != null) {
                    player.func_146105_b((IChatComponent)new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                    return true;
                }
                BlockCoffin.setBedOccupied(world, x, y, z, false);
            }
            if ((enumstatus = player.func_71018_a(x, y, z)) == EntityPlayer.EnumStatus.OK) {
                BlockCoffin.setBedOccupied(world, x, y, z, true);
                return true;
            }
            if (enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
            } else if (enumstatus == EntityPlayer.EnumStatus.NOT_SAFE) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
            }
            return true;
        }
        double d2 = (double)x + 0.5;
        double d0 = (double)y + 0.5;
        double d1 = (double)z + 0.5;
        world.func_147468_f(x, y, z);
        int k1 = BlockCoffin.getDirection(i1);
        if (world.func_147439_a(x += DIRECTIONS[k1][0], y, z += DIRECTIONS[k1][1]) == this) {
            world.func_147468_f(x, y, z);
            d2 = (d2 + (double)x + 0.5) / 2.0;
            d0 = (d0 + (double)y + 0.5) / 2.0;
            d1 = (d1 + (double)z + 0.5) / 2.0;
        }
        world.func_72885_a((Entity)null, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), 5.0f, true, true);
        return true;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        super.func_149719_a(world, x, y, z);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        int l = world.func_72805_g(x, y, z);
        int i1 = BlockCoffin.getDirection(l);
        if (BlockCoffin.isBlockHeadOfBed(l)) {
            if (world.func_147439_a(x - DIRECTIONS[i1][0], y, z - DIRECTIONS[i1][1]) != this) {
                world.func_147468_f(x, y, z);
            }
        } else if (world.func_147439_a(x + DIRECTIONS[i1][0], y, z + DIRECTIONS[i1][1]) != this) {
            world.func_147468_f(x, y, z);
            if (!world.field_72995_K) {
                this.func_149697_b(world, x, y, z, l, 0);
            }
        }
    }

    public Item func_149650_a(int meta, Random rand, int p_149650_3_) {
        return BlockCoffin.isBlockHeadOfBed(meta) ? Item.func_150899_d((int)0) : Witchery.Items.COFFIN;
    }

    private void setupBounds() {
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB mask, List boxes, Entity entity) {
        TileEntityCoffin tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityCoffin.class);
        if (tile != null && !tile.open) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, x, y, z, mask, boxes, entity);
            return;
        }
        int meta = world.func_72805_g(x, y, z);
        float baseHeight = 0.4375f;
        float wallThick = 0.05f;
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, baseHeight, 1.0f);
        super.func_149743_a(world, x, y, z, mask, boxes, entity);
        int direction = BlockCoffin.getDirection(meta);
        boolean head = BlockCoffin.isBlockHeadOfBed(meta);
        boolean n = true;
        boolean s = true;
        boolean e = true;
        boolean w = true;
        boolean n1 = false;
        boolean s1 = false;
        boolean e1 = false;
        boolean w1 = false;
        switch (direction) {
            case 0: {
                n = !head;
                s = head;
                e1 = true;
                break;
            }
            case 1: {
                e = !head;
                w = head;
                s1 = true;
                break;
            }
            case 2: {
                s = !head;
                n = head;
                w1 = true;
                break;
            }
            case 3: {
                w = !head;
                e = head;
                n1 = true;
            }
        }
        if (n) {
            this.func_149676_a(0.0f, baseHeight, 0.0f, 1.0f, n1 ? 2.0f : 1.0f, wallThick);
            super.func_149743_a(world, x, y, z, mask, boxes, entity);
        }
        if (s) {
            this.func_149676_a(0.0f, baseHeight, 1.0f - wallThick, 1.0f, s1 ? 2.0f : 1.0f, 1.0f);
            super.func_149743_a(world, x, y, z, mask, boxes, entity);
        }
        if (w) {
            this.func_149676_a(0.0f, baseHeight, 0.0f, wallThick, w1 ? 2.0f : 1.0f, 1.0f);
            super.func_149743_a(world, x, y, z, mask, boxes, entity);
        }
        if (e) {
            this.func_149676_a(1.0f - wallThick, baseHeight, 0.0f, 1.0f, e1 ? 2.0f : 1.0f, 1.0f);
            super.func_149743_a(world, x, y, z, mask, boxes, entity);
        }
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
        this.setupBounds();
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E), (double)((double)y + 1.0), (double)((double)z + this.field_149757_G));
    }

    public static boolean isBlockHeadOfBed(int meta) {
        return (meta & 8) != 0;
    }

    public static boolean isBedOccupied(int meta) {
        return (meta & 4) != 0;
    }

    public static void setBedOccupied(World world, int x, int y, int z, boolean p_149979_4_) {
        int l = world.func_72805_g(x, y, z);
        l = p_149979_4_ ? (l |= 4) : (l &= 0xFFFFFFFB);
        world.func_72921_c(x, y, z, l, 3);
    }

    public static ChunkCoordinates func_149977_a(World world, int x, int y, int z, int p_149977_4_) {
        int i1 = world.func_72805_g(x, y, z);
        int j1 = BlockDirectional.func_149895_l((int)i1);
        for (int k1 = 0; k1 <= 1; ++k1) {
            int l1 = x - DIRECTIONS[j1][0] * k1 - 1;
            int i2 = z - DIRECTIONS[j1][1] * k1 - 1;
            int j2 = l1 + 2;
            int k2 = i2 + 2;
            for (int l2 = l1; l2 <= j2; ++l2) {
                for (int i3 = i2; i3 <= k2; ++i3) {
                    if (!World.func_147466_a((IBlockAccess)world, (int)l2, (int)(y - 1), (int)i3) || world.func_147439_a(l2, y, i3).func_149688_o().func_76218_k() || world.func_147439_a(l2, y + 1, i3).func_149688_o().func_76218_k()) continue;
                    if (p_149977_4_ <= 0) {
                        return new ChunkCoordinates(l2, y, i3);
                    }
                    --p_149977_4_;
                }
            }
        }
        return null;
    }

    public void func_149690_a(World world, int x, int y, int z, int p_149690_5_, float p_149690_6_, int p_149690_7_) {
        if (!BlockCoffin.isBlockHeadOfBed(p_149690_5_)) {
            super.func_149690_a(world, x, y, z, p_149690_5_, p_149690_6_, 0);
        }
    }

    public int func_149656_h() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World world, int x, int y, int z) {
        return Witchery.Items.COFFIN;
    }

    public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
        int i1;
        if (player.field_71075_bZ.field_75098_d && BlockCoffin.isBlockHeadOfBed(meta) && world.func_147439_a(x -= DIRECTIONS[i1 = BlockCoffin.getDirection(meta)][0], y, z -= DIRECTIONS[i1][1]) == this) {
            world.func_147468_f(x, y, z);
        }
    }

    public static class TileEntityCoffin
    extends TileEntity {
        private boolean open;
        public float lidAngle;
        public float prevLidAngle;

        public void func_145845_h() {
            this.prevLidAngle = this.lidAngle;
            if (this.open && this.lidAngle == 0.0f) {
                double d1 = (double)this.field_145851_c + 0.5;
                double d0 = (double)this.field_145849_e + 0.5;
                this.field_145850_b.func_72908_a(d1, (double)this.field_145848_d + 0.5, d0, "random.chestopen", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
            if (!this.open && this.lidAngle > 0.0f || this.open && this.lidAngle < 1.0f) {
                float f2;
                float f = 0.1f;
                float f1 = this.lidAngle;
                this.lidAngle = this.open ? (this.lidAngle += 0.1f) : (this.lidAngle -= 0.1f);
                if (this.lidAngle > 1.0f) {
                    this.lidAngle = 1.0f;
                }
                if (this.lidAngle < (f2 = 0.5f) && f1 >= f2) {
                    double d0 = (double)this.field_145851_c + 0.5;
                    double d2 = (double)this.field_145849_e + 0.5;
                    this.field_145850_b.func_72908_a(d0, (double)this.field_145848_d + 0.5, d2, "random.chestclosed", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
                }
                if (this.lidAngle < 0.0f) {
                    this.lidAngle = 0.0f;
                }
            }
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            nbtTag.func_74776_a("Angle", this.lidAngle);
            nbtTag.func_74776_a("AnglePrev", this.prevLidAngle);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            NBTTagCompound nbtTag = packet.func_148857_g();
            this.func_145839_a(nbtTag);
            this.lidAngle = nbtTag.func_74760_g("Angle");
            this.prevLidAngle = nbtTag.func_74760_g("AnglePrev");
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            nbtRoot.func_74757_a("Opened", this.open);
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            this.open = nbtRoot.func_74767_n("Opened");
        }
    }
}

