/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.tile.TileCraftCrate;
import vazkii.botania.common.item.ItemMod;

public class ItemCraftPattern
extends ItemMod {
    IIcon[] icons;

    public ItemCraftPattern() {
        this.func_77627_a(true);
        this.func_77655_b("craftPattern");
        this.func_77625_d(1);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer p, World world, int x, int y, int z, int s, float xs, float ys, float zs) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileCraftCrate && !world.field_72995_K) {
            TileCraftCrate crate = (TileCraftCrate)tile;
            crate.pattern = stack.func_77960_j();
            world.func_147471_g(x, y, z);
        }
        return false;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < TileCraftCrate.PATTERNS.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[TileCraftCrate.PATTERNS.length];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public IIcon func_77617_a(int dmg) {
        return this.icons[Math.min(this.icons.length - 1, dmg)];
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }
}

