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
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileOwned;

public class ItemArcaneDoor
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemArcaneDoor() {
        this.field_77777_bU = 1;
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:arcanedoor");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        }
        Block var11 = ConfigBlocks.blockArcaneDoor;
        if (player.func_82247_a(par4, ++par5, par6, par7, stack) && player.func_82247_a(par4, par5 + 1, par6, par7, stack)) {
            if (!var11.func_149742_c(world, par4, par5, par6)) {
                return false;
            }
            int var12 = MathHelper.func_76128_c((double)((double)((player.field_70177_z + 180.0f) * 4.0f / 360.0f) - 0.5)) & 3;
            ItemArcaneDoor.placeDoorBlock(world, par4, par5, par6, var12, var11, player);
            --stack.field_77994_a;
            return true;
        }
        return false;
    }

    public static void placeDoorBlock(World world, int x, int y, int z, int par4, Block par5Block, EntityPlayer player) {
        int var6 = 0;
        int var7 = 0;
        if (par4 == 0) {
            var7 = 1;
        }
        if (par4 == 1) {
            var6 = -1;
        }
        if (par4 == 2) {
            var7 = -1;
        }
        if (par4 == 3) {
            var6 = 1;
        }
        int var8 = (world.func_147445_c(x - var6, y, z - var7, false) ? 1 : 0) + (world.func_147445_c(x - var6, y + 1, z - var7, false) ? 1 : 0);
        int var9 = (world.func_147445_c(x + var6, y, z + var7, false) ? 1 : 0) + (world.func_147445_c(x + var6, y + 1, z + var7, false) ? 1 : 0);
        boolean var10 = world.func_147439_a(x - var6, y, z - var7) == par5Block || world.func_147439_a(x - var6, y + 1, z - var7) == par5Block;
        boolean var11 = world.func_147439_a(x + var6, y, z + var7) == par5Block || world.func_147439_a(x + var6, y + 1, z + var7) == par5Block;
        boolean var12 = false;
        if (var10 && !var11) {
            var12 = true;
        } else if (var9 > var8) {
            var12 = true;
        }
        world.func_147465_d(x, y, z, par5Block, par4, 2);
        TileOwned tad = (TileOwned)world.func_147438_o(x, y, z);
        tad.owner = player.func_70005_c_();
        world.func_147465_d(x, y + 1, z, par5Block, 8 | (var12 ? 1 : 0), 2);
        TileOwned tad2 = (TileOwned)world.func_147438_o(x, y + 1, z);
        tad2.owner = player.func_70005_c_();
        world.func_147459_d(x, y, z, par5Block);
        world.func_147459_d(x, y + 1, z, par5Block);
    }
}

