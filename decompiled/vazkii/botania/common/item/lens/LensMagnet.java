/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.lens.Lens;

public class LensMagnet
extends Lens {
    private static final String TAG_MAGNETIZED = "Botania:Magnetized";
    private static final String TAG_MAGNETIZED_X = "Botania:MagnetizedX";
    private static final String TAG_MAGNETIZED_Y = "Botania:MagnetizedY";
    private static final String TAG_MAGNETIZED_Z = "Botania:MagnetizedZ";

    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        int x = (int)entity.field_70165_t;
        int y = (int)entity.field_70163_u;
        int z = (int)entity.field_70161_v;
        boolean magnetized = entity.getEntityData().func_74764_b(TAG_MAGNETIZED);
        int range = 3;
        block0: for (int i = -range; i < range; ++i) {
            for (int j = -range; j < range; ++j) {
                for (int k = -range; k < range; ++k) {
                    if (!(entity.field_70170_p.func_147438_o(i + x, j + y, k + z) instanceof IManaReceiver)) continue;
                    TileEntity tile = entity.field_70170_p.func_147438_o(i + x, j + y, k + z);
                    if (magnetized) {
                        int magX = entity.getEntityData().func_74762_e(TAG_MAGNETIZED_X);
                        int magY = entity.getEntityData().func_74762_e(TAG_MAGNETIZED_Y);
                        int magZ = entity.getEntityData().func_74762_e(TAG_MAGNETIZED_Z);
                        if (tile.field_145851_c != magX || tile.field_145848_d != magY || tile.field_145849_e != magZ) continue;
                    }
                    IManaReceiver receiver = (IManaReceiver)tile;
                    ChunkCoordinates srcCoords = burst.getBurstSourceChunkCoordinates();
                    if (!(MathHelper.pointDistanceSpace(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, srcCoords.field_71574_a, srcCoords.field_71572_b, srcCoords.field_71573_c) > 3.0f) || !receiver.canRecieveManaFromBursts() || receiver.isFull()) continue;
                    Vector3 burstVec = Vector3.fromEntity((Entity)entity);
                    Vector3 tileVec = Vector3.fromTileEntityCenter(tile).add(0.0, -0.1, 0.0);
                    Vector3 motionVec = new Vector3(entity.field_70159_w, entity.field_70181_x, entity.field_70179_y);
                    Vector3 normalMotionVec = motionVec.copy().normalize();
                    Vector3 magnetVec = tileVec.sub(burstVec).normalize();
                    Vector3 differenceVec = normalMotionVec.sub(magnetVec).multiply(motionVec.mag() * 0.1);
                    Vector3 finalMotionVec = motionVec.sub(differenceVec);
                    if (!magnetized) {
                        finalMotionVec.multiply(0.75);
                        entity.getEntityData().func_74757_a(TAG_MAGNETIZED, true);
                        entity.getEntityData().func_74768_a(TAG_MAGNETIZED_X, tile.field_145851_c);
                        entity.getEntityData().func_74768_a(TAG_MAGNETIZED_Y, tile.field_145848_d);
                        entity.getEntityData().func_74768_a(TAG_MAGNETIZED_Z, tile.field_145849_e);
                    }
                    burst.setMotion(finalMotionVec.x, finalMotionVec.y, finalMotionVec.z);
                    break block0;
                }
            }
        }
    }
}

