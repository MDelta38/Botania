/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.codechicken.lib.vec.Vector3
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.block.tile.transvector;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvector;
import thaumic.tinkerer.common.block.transvector.BlockTransvectorDislocator;

public class TileTransvectorDislocator
extends TileTransvector {
    private static final String TAG_ORIENTATION = "orientation";
    public int orientation;
    private int cooldown = 0;
    private boolean pulseStored = false;

    public void func_145845_h() {
        super.func_145845_h();
        this.cooldown = Math.max(0, this.cooldown - 1);
        if (this.cooldown == 0 && this.pulseStored) {
            this.pulseStored = false;
            this.receiveRedstonePulse();
        }
    }

    public void receiveRedstonePulse() {
        this.getTile();
        if (this.y < 0) {
            return;
        }
        if (this.cooldown > 0) {
            this.pulseStored = true;
            return;
        }
        ChunkCoordinates endCoords = new ChunkCoordinates(this.x, this.y, this.z);
        ChunkCoordinates targetCoords = this.getBlockTarget();
        if (this.field_145850_b.func_72899_e(this.x, this.y, this.z)) {
            BlockData endData = new BlockData(endCoords);
            BlockData targetData = new BlockData(targetCoords);
            if (this.checkBlock(targetCoords) && this.checkBlock(endCoords)) {
                endData.clearTileEntityAt();
                targetData.clearTileEntityAt();
                endData.setTo(targetCoords);
                targetData.setTo(endCoords);
                endData.notify(targetCoords);
                targetData.notify(endCoords);
            }
        }
        List<Entity> entitiesAtEnd = this.getEntitiesAtPoint(endCoords);
        List<Entity> entitiesAtTarget = this.getEntitiesAtPoint(targetCoords);
        Vector3 targetToEnd = this.asVector(targetCoords, endCoords);
        Vector3 endToTarget = this.asVector(endCoords, targetCoords);
        for (Entity entity : entitiesAtEnd) {
            this.moveEntity(entity, endToTarget);
        }
        for (Entity entity : entitiesAtTarget) {
            this.moveEntity(entity, targetToEnd);
        }
        this.cooldown = 10;
    }

    private boolean checkBlock(ChunkCoordinates coords) {
        Block block = this.field_145850_b.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        int meta = this.field_145850_b.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        return (block != ConfigBlocks.blockAiry || meta != 0) && !ThaumcraftApi.portableHoleBlackList.contains(block) && block != null && block.func_149712_f(this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) != -1.0f || block != Blocks.field_150350_a;
    }

    private List<Entity> getEntitiesAtPoint(ChunkCoordinates coords) {
        return this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)coords.field_71574_a, (double)coords.field_71572_b, (double)coords.field_71573_c, (double)(coords.field_71574_a + 1), (double)(coords.field_71572_b + 1), (double)(coords.field_71573_c + 1)));
    }

    private Vector3 asVector(ChunkCoordinates source, ChunkCoordinates target) {
        return new Vector3((double)target.field_71574_a, (double)target.field_71572_b, (double)target.field_71573_c).subtract(new Vector3((double)source.field_71574_a, (double)source.field_71572_b, (double)source.field_71573_c));
    }

    private void moveEntity(Entity entity, Vector3 vec) {
        if (entity instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP)entity;
            player.field_71135_a.func_147364_a(entity.field_70165_t + vec.x, entity.field_70163_u + vec.y, entity.field_70161_v + vec.z, player.field_70177_z, player.field_70125_A);
        } else {
            entity.func_70107_b(entity.field_70165_t + vec.x, entity.field_70163_u + vec.y, entity.field_70161_v + vec.z);
        }
    }

    public ChunkCoordinates getBlockTarget() {
        ForgeDirection dir = ForgeDirection.getOrientation((int)this.orientation);
        return new ChunkCoordinates(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.orientation = cmp.func_74762_e(TAG_ORIENTATION);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74768_a(TAG_ORIENTATION, this.orientation);
    }

    @Override
    public int getMaxDistance() {
        return 16;
    }

    @Override
    boolean tileRequiredAtLink() {
        return false;
    }

    class BlockData {
        Block block;
        int meta;
        NBTTagCompound tile;
        ChunkCoordinates coords;

        public BlockData(Block block, int meta, TileEntity tile, ChunkCoordinates coords) {
            this.block = block;
            this.meta = meta;
            if (tile != null) {
                NBTTagCompound cmp = new NBTTagCompound();
                tile.func_145841_b(cmp);
                this.tile = cmp;
            }
            this.coords = coords;
        }

        public BlockData(ChunkCoordinates coords) {
            this(tileTransvectorDislocator.field_145850_b.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c), tileTransvectorDislocator.field_145850_b.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c), tileTransvectorDislocator.field_145850_b.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c), coords);
        }

        public void clearTileEntityAt() {
            if (this.block != null) {
                TileEntity tileToSet = this.block.createTileEntity(TileTransvectorDislocator.this.field_145850_b, this.meta);
                TileTransvectorDislocator.this.field_145850_b.func_147455_a(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c, tileToSet);
            }
        }

        public void setTo(ChunkCoordinates coords) {
            TileTransvectorDislocator.this.field_145850_b.func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, this.block, this.meta, 2);
            TileEntity tile = this.tile == null ? null : TileEntity.func_145827_c((NBTTagCompound)this.tile);
            TileTransvectorDislocator.this.field_145850_b.func_147455_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, tile);
            if (tile != null) {
                tile.field_145851_c = coords.field_71574_a;
                tile.field_145848_d = coords.field_71572_b;
                tile.field_145849_e = coords.field_71573_c;
                tile.func_145836_u();
            }
            TileTransvectorDislocator.this.field_145850_b.func_72921_c(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, this.meta, 2);
        }

        public void notify(ChunkCoordinates coords) {
            if (this.block != null) {
                this.block.func_149695_a(TileTransvectorDislocator.this.field_145850_b, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorDislocator.class));
            }
        }
    }
}

