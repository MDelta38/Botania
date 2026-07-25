/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockInspiratron
extends BlockContainer {
    public IIcon iconLiquid;
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;

    public BlockInspiratron() {
        super(Material.field_151592_s);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149663_c("ThaumicHorizons_inspiratron");
        this.func_149658_d("ThaumicHorizons:inspiratron");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileInspiratron te = new TileInspiratron();
        return te;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        player.openGui((Object)ThaumicHorizons.instance, 3, world, x, y, z);
        return true;
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te instanceof TileInspiratron) {
            TileInspiratron tile = (TileInspiratron)te;
            if (tile.paper != null) {
                world.func_72838_d((Entity)new EntityItem(world, (double)x, (double)y, (double)z, tile.paper));
            }
            if (tile.knowledge != null) {
                world.func_72838_d((Entity)new EntityItem(world, (double)x, (double)y, (double)z, tile.knowledge));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconJarSide = ir.func_94245_a("thaumichorizons:jar_side");
        this.iconJarTop = ir.func_94245_a("thaumichorizons:jar_top");
        this.iconJarBottom = ir.func_94245_a("thaumichorizons:jar_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side == 1 ? this.iconJarTop : (side == 0 ? this.iconJarBottom : this.iconJarSide);
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockInspiratronRI;
    }
}

