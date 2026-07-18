/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.lens.Lens;

public class LensBounce
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!isManaBlock && pos.field_72308_g == null) {
            ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
            if (coords.field_71574_a != pos.field_72311_b || coords.field_71572_b != pos.field_72312_c || coords.field_71573_c != pos.field_72309_d) {
                Vector3 currentMovementVec = new Vector3(entity.field_70159_w, entity.field_70181_x, entity.field_70179_y);
                ForgeDirection dir = ForgeDirection.getOrientation((int)pos.field_72310_e);
                Vector3 normalVector = new Vector3(dir.offsetX, dir.offsetY, dir.offsetZ).normalize();
                Vector3 movementVec = normalVector.multiply(-2.0 * currentMovementVec.dotProduct(normalVector)).add(currentMovementVec);
                burst.setMotion(movementVec.x, movementVec.y, movementVec.z);
                dead = false;
            }
        }
        return dead;
    }
}

