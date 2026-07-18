/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.item.ItemMod;

public class ItemCorporeaSpark
extends ItemMod {
    public static IIcon invIcon;
    public static IIcon worldIcon;
    public static IIcon invIconMaster;
    public static IIcon worldIconMaster;
    public static IIcon iconColorStar;

    public ItemCorporeaSpark() {
        this.func_77655_b("corporeaSpark");
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xv, float yv, float zv) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile instanceof IInventory && !CorporeaHelper.doesBlockHaveSpark(world, x, y, z)) {
            --stack.field_77994_a;
            if (!world.field_72995_K) {
                EntityCorporeaSpark spark = new EntityCorporeaSpark(world);
                if (stack.func_77960_j() == 1) {
                    spark.setMaster(true);
                }
                spark.func_70107_b((double)x + 0.5, (double)y + 1.5, (double)z + 0.5);
                world.func_72838_d((Entity)spark);
                world.func_147471_g(x, y, z);
            }
            return true;
        }
        return false;
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        invIcon = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        worldIcon = IconHelper.forItem(par1IconRegister, (Item)this, 1);
        invIconMaster = IconHelper.forItem(par1IconRegister, (Item)this, 2);
        worldIconMaster = IconHelper.forItem(par1IconRegister, (Item)this, 3);
        iconColorStar = IconHelper.forItem(par1IconRegister, (Item)this, "Star");
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77667_c(par1ItemStack) + par1ItemStack.func_77960_j();
    }

    public IIcon func_77617_a(int meta) {
        return meta == 0 ? invIcon : invIconMaster;
    }
}

