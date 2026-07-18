/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.block.ModBlocks;

public class EntityVineBall
extends EntityThrowable {
    public EntityVineBall(World par1World) {
        super(par1World);
        this.field_70180_af.func_75682_a(30, (Object)Float.valueOf(0.0f));
        this.field_70180_af.func_82708_h(30);
    }

    public EntityVineBall(EntityPlayer player, boolean gravity) {
        super(player.field_70170_p, (EntityLivingBase)player);
        this.field_70180_af.func_75682_a(30, (Object)Float.valueOf(gravity ? 0.03f : 0.0f));
        this.field_70180_af.func_82708_h(30);
    }

    protected void func_70184_a(MovingObjectPosition var1) {
        if (var1 != null) {
            int meta = var1.field_72310_e;
            int[] metaPlace = new int[]{1, 4, 8, 2};
            if (meta > 1 && meta < 6) {
                Block block;
                ForgeDirection dir = ForgeDirection.getOrientation((int)meta);
                int x = var1.field_72311_b + dir.offsetX;
                int z = var1.field_72309_d + dir.offsetZ;
                for (int y = var1.field_72312_c + dir.offsetY; y > 0 && (block = this.field_70170_p.func_147439_a(x, y, z)).isAir((IBlockAccess)this.field_70170_p, x, y, z); --y) {
                    this.field_70170_p.func_147465_d(x, y, z, ModBlocks.solidVines, metaPlace[meta - 2], 3);
                    this.field_70170_p.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)ModBlocks.solidVines) + (metaPlace[meta - 2] << 12));
                }
            }
        }
        this.func_70106_y();
    }

    protected float func_70185_h() {
        return this.field_70180_af.func_111145_d(30);
    }
}

