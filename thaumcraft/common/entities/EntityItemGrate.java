/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;

public class EntityItemGrate
extends EntityItem {
    public EntityItemGrate(World par1World) {
        super(par1World);
    }

    public EntityItemGrate(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
        super(par1World, par2, par4, par6, par8ItemStack);
    }

    protected boolean func_145771_j(double x, double y, double z) {
        int k;
        int j;
        int i = MathHelper.func_76128_c((double)x);
        if (this.field_70170_p.func_147439_a(i, j = MathHelper.func_76128_c((double)y), k = MathHelper.func_76128_c((double)z)) == ConfigBlocks.blockMetalDevice && (this.field_70170_p.func_72805_g(i, j, k) == 5 || this.field_70170_p.func_72805_g(i, j, k) == 6)) {
            return true;
        }
        return super.func_145771_j(x, y, z);
    }
}

