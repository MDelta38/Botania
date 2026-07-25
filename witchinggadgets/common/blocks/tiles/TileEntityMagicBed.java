/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class TileEntityMagicBed
extends TileEntity {
    public boolean isHead = false;
    public boolean isOccupied = false;

    public boolean activate(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        if (world.field_73011_w.func_76567_e() && world.func_72807_a(x, z) != BiomeGenBase.field_76778_j) {
            EntityPlayer.EnumStatus enumstatus;
            if (this.isOccupied) {
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
                this.isOccupied = false;
            }
            if ((enumstatus = player.func_71018_a(x, y, z)) == EntityPlayer.EnumStatus.OK) {
                this.isOccupied = true;
                return true;
            }
            return true;
        }
        return true;
    }

    public static ChunkCoordinates getNearestEmptyChunkCoordinates(World par0World, int par1, int par2, int par3, int par4) {
        for (int k1 = 0; k1 <= 1; ++k1) {
        }
        return null;
    }

    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7) {
    }

    public int getMobilityFlag() {
        return 1;
    }

    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
    }
}

