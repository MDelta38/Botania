/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.subtile.generating.SubTileNarslimmus;
import vazkii.botania.common.item.ItemMod;

public class ItemSlimeBottle
extends ItemMod {
    IIcon activeIcon;

    public ItemSlimeBottle() {
        this.func_77655_b("slimeBottle");
        this.func_77625_d(1);
        this.func_77627_a(true);
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.activeIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon func_77617_a(int dmg) {
        return dmg == 0 ? this.field_77791_bV : this.activeIcon;
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int something, boolean somethingelse) {
        if (!world.field_72995_K) {
            int newMeta;
            int x = MathHelper.func_76128_c((double)entity.field_70165_t);
            int z = MathHelper.func_76128_c((double)entity.field_70161_v);
            boolean slime = SubTileNarslimmus.SpawnIntercepter.isSlimeChunk(world, x, z);
            int meta = stack.func_77960_j();
            int n = newMeta = slime ? 1 : 0;
            if (meta != newMeta) {
                stack.func_77964_b(newMeta);
            }
        }
    }
}

