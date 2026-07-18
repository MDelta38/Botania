/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public class TileOpenCrate
extends TileSimpleInventory {
    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "openCrate";
    }

    public void func_145845_h() {
        ItemStack stack;
        boolean redstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal());
            if (redstoneSide <= 0) continue;
            redstone = true;
            break;
        }
        if (this.canEject() && (stack = this.func_70301_a(0)) != null) {
            this.eject(stack, redstone);
        }
    }

    public boolean canEject() {
        Block blockBelow = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        return blockBelow.isAir((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) || blockBelow.func_149668_a(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == null;
    }

    public void eject(ItemStack stack, boolean redstone) {
        EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d - 0.5, (double)this.field_145849_e + 0.5, stack);
        item.field_70159_w = 0.0;
        item.field_70181_x = 0.0;
        item.field_70179_y = 0.0;
        if (redstone) {
            item.field_70292_b = -200;
        }
        this.func_70299_a(0, null);
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_72838_d((Entity)item);
        }
    }

    public boolean onWanded(EntityPlayer player, ItemStack stack) {
        return false;
    }

    public int getSignal() {
        return 0;
    }
}

