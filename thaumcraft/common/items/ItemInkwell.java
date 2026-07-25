/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.IScribeTools;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.tiles.TileResearchTable;
import thaumcraft.common.tiles.TileTable;

public class ItemInkwell
extends Item
implements IScribeTools {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemInkwell() {
        this.field_77777_bU = 1;
        this.canRepair = true;
        this.func_77656_e(100);
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(false);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:inkwell");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        TileEntity tile = world.func_147438_o(x, y, z);
        int md = world.func_72805_g(x, y, z);
        Block bi = world.func_147439_a(x, y, z);
        if (tile != null && tile instanceof TileTable && md != 6) {
            if (world.field_72995_K) {
                return false;
            }
            for (int a = 2; a < 6; ++a) {
                TileEntity tile2 = world.func_147438_o(x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ);
                int md2 = world.func_72805_g(x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ);
                if (tile2 == null || !(tile2 instanceof TileTable) || md2 >= 6) continue;
                world.func_147465_d(x, y, z, bi, a, 0);
                world.func_147455_a(x, y, z, (TileEntity)new TileResearchTable());
                world.func_147465_d(x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ, bi, ForgeDirection.getOrientation((int)a).getOpposite().ordinal() + 4, 0);
                world.func_147471_g(x, y, z);
                world.func_147471_g(x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ);
                TileEntity tile3 = world.func_147438_o(x, y, z);
                if (tile3 != null && tile3 instanceof TileResearchTable) {
                    ((TileResearchTable)tile3).func_70299_a(0, stack.func_77946_l());
                    if (!player.field_71075_bZ.field_75098_d) {
                        player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                        player.field_71071_by.func_70296_d();
                    }
                    world.func_147471_g(x, y, z);
                }
                return true;
            }
        }
        return false;
    }
}

