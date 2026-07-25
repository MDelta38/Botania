/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockGas;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.ItemBrightNitor;
import thaumic.tinkerer.common.item.kami.armor.ItemGemLegs;

public class BlockNitorGas
extends BlockGas {
    public int func_149738_a(World par1World) {
        return par1World.field_73011_w.field_76574_g == -1 ? 60 : 20;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par5Random.nextFloat() < 0.03f) {
            ThaumicTinkerer.tcProxy.sparkle((float)par2 + 0.5f, (float)par3 + 0.5f, (float)par4 + 0.5f, 1.0f, 4, par5Random.nextFloat() / 2.0f);
        }
    }

    @Override
    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.field_72995_K) {
            boolean remove = false;
            int dist = par1World.func_72805_g(par2, par3, par4) == 1 ? 6 : 1;
            List players = par1World.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(par2 - dist), (double)(par3 - dist), (double)(par4 - dist), (double)(par2 + dist), (double)(par3 + dist), (double)(par4 + dist)));
            if (players.isEmpty()) {
                par1World.func_147468_f(par2, par3, par4);
                remove = true;
            } else {
                boolean has = false;
                for (EntityPlayer player : players) {
                    if (!player.field_71071_by.func_146028_b(ThaumicTinkerer.registry.getFirstItemFromClass(ItemBrightNitor.class)) && (!ConfigHandler.enableKami || player.func_82169_q(1) == null || player.func_82169_q(1).func_77973_b() != ThaumicTinkerer.registry.getFirstItemFromClass(ItemGemLegs.class))) continue;
                    has = true;
                    break;
                }
                if (!has) {
                    par1World.func_147468_f(par2, par3, par4);
                    remove = true;
                }
            }
            if (!remove) {
                par1World.func_147464_a(par2, par3, par4, (Block)this, this.func_149738_a(par1World));
            }
        }
    }

    public int func_149750_m() {
        return 15;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) == 1 ? 15 : 12;
    }

    public void func_149726_b(World par1World, int par2, int par3, int par4) {
        if (!par1World.field_72995_K) {
            par1World.func_147464_a(par2, par3, par4, (Block)this, this.func_149738_a(par1World));
        }
    }

    @Override
    public String getBlockName() {
        return "nitorGas";
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }
}

