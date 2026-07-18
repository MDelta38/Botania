/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IRedirectable;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.lens.Lens;

public class LensRedirect
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        TileEntity tile;
        ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
        if (!(entity.field_70170_p.field_72995_K || pos.field_72308_g != null || coords.field_71572_b == -1 || pos.field_72311_b == coords.field_71574_a && pos.field_72312_c == coords.field_71572_b && pos.field_72309_d == coords.field_71573_c || (tile = entity.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d)) == null || !(tile instanceof IRedirectable) || burst.isFake())) {
            IRedirectable redir = (IRedirectable)tile;
            Vector3 tileVec = Vector3.fromTileEntityCenter(tile);
            Vector3 sourceVec = new Vector3((double)coords.field_71574_a + 0.5, (double)coords.field_71572_b + 0.5, (double)coords.field_71573_c + 0.5);
            AxisAlignedBB axis = entity.field_70170_p.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c).func_149668_a(entity.field_70170_p, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            if (axis == null) {
                axis = AxisAlignedBB.func_72330_a((double)coords.field_71574_a, (double)coords.field_71572_b, (double)coords.field_71573_c, (double)(coords.field_71574_a + 1), (double)(coords.field_71572_b + 1), (double)(coords.field_71573_c + 1));
            }
            if (!sourceVec.isInside(axis)) {
                sourceVec = new Vector3(axis.field_72340_a + (axis.field_72336_d - axis.field_72340_a) / 2.0, axis.field_72338_b + (axis.field_72337_e - axis.field_72338_b) / 2.0, axis.field_72339_c + (axis.field_72334_f - axis.field_72339_c) / 2.0);
            }
            Vector3 diffVec = sourceVec.copy().sub(tileVec);
            Vector3 diffVec2D = new Vector3(diffVec.x, diffVec.z, 0.0);
            Vector3 rotVec = new Vector3(0.0, 1.0, 0.0);
            double angle = rotVec.angle(diffVec2D) / Math.PI * 180.0;
            if (sourceVec.x < tileVec.x) {
                angle = -angle;
            }
            redir.setRotationX((float)angle + 90.0f);
            rotVec = new Vector3(diffVec.x, 0.0, diffVec.z);
            angle = diffVec.angle(rotVec) * 180.0 / Math.PI;
            if (sourceVec.y < tileVec.y) {
                angle = -angle;
            }
            redir.setRotationY((float)angle);
            redir.commitRedirection();
            if (redir instanceof IThrottledPacket) {
                ((IThrottledPacket)((Object)redir)).markDispatchable();
            }
        }
        if (!isManaBlock) {
            dead = false;
            burst.setMinManaLoss(Math.max(0, burst.getMinManaLoss() - 4));
        }
        return dead;
    }
}

