/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEnderAirBottle
extends EntityThrowable {
    public EntityEnderAirBottle(World world) {
        super(world);
    }

    public EntityEnderAirBottle(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    protected void func_70184_a(MovingObjectPosition pos) {
        if (pos.field_72308_g == null && !this.field_70170_p.field_72995_K) {
            List<ChunkCoordinates> coordsList = this.getCoordsToPut(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), 8);
            for (ChunkCoordinates coords : coordsList) {
                this.field_70170_p.func_147449_b(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Blocks.field_150377_bs);
                if (!(Math.random() < 0.1)) continue;
                this.field_70170_p.func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)Blocks.field_150377_bs));
            }
            this.func_70106_y();
        }
    }

    public List<ChunkCoordinates> getCoordsToPut(int xCoord, int yCoord, int zCoord) {
        ArrayList<ChunkCoordinates> possibleCoords = new ArrayList<ChunkCoordinates>();
        ArrayList<ChunkCoordinates> selectedCoords = new ArrayList<ChunkCoordinates>();
        int range = 4;
        int rangeY = 4;
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -rangeY; j < rangeY; ++j) {
                for (int k = -range; k < range + 1; ++k) {
                    int x = xCoord + i;
                    int y = yCoord + j;
                    int z = zCoord + k;
                    Block block = this.field_70170_p.func_147439_a(x, y, z);
                    if (block == null || !block.isReplaceableOreGen(this.field_70170_p, x, y, z, Blocks.field_150348_b)) continue;
                    possibleCoords.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        for (int count = 64; !possibleCoords.isEmpty() && count > 0; --count) {
            ChunkCoordinates coords = (ChunkCoordinates)possibleCoords.get(this.field_70170_p.field_73012_v.nextInt(possibleCoords.size()));
            possibleCoords.remove(coords);
            selectedCoords.add(coords);
        }
        return selectedCoords;
    }
}

