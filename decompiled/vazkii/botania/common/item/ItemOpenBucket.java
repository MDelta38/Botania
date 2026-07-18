/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.item.ItemMod;

public class ItemOpenBucket
extends ItemMod {
    public ItemOpenBucket() {
        this.func_77625_d(1);
        this.func_77655_b("openBucket");
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        MovingObjectPosition movingobjectposition = this.func_77621_a(par2World, par3EntityPlayer, true);
        if (movingobjectposition == null) {
            return par1ItemStack;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (!par2World.func_72962_a(par3EntityPlayer, i, j, k)) {
                return par1ItemStack;
            }
            if (!par3EntityPlayer.func_82247_a(i, j, k, movingobjectposition.field_72310_e, par1ItemStack)) {
                return par1ItemStack;
            }
            Material material = par2World.func_147439_a(i, j, k).func_149688_o();
            int l = par2World.func_72805_g(i, j, k);
            if ((material == Material.field_151587_i || material == Material.field_151586_h) && l == 0) {
                par2World.func_147468_f(i, j, k);
                for (int x = 0; x < 5; ++x) {
                    par2World.func_72869_a("explode", (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0, 0.0, 0.0);
                }
                return par1ItemStack;
            }
        }
        return par1ItemStack;
    }
}

