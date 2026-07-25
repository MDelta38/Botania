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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatConnector;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;

public class BlockVat
extends BlockContainer {
    public IIcon iconGlassTL;
    public IIcon iconGlassT;
    public IIcon iconGlassTR;
    public IIcon iconGlassBL;
    public IIcon iconGlassB;
    public IIcon iconGlassBR;

    public BlockVat() {
        super(Material.field_151575_d);
        this.func_149711_c(3.0f);
        this.func_149752_b(15.0f);
        this.field_149784_t = 8;
        this.func_149663_c("ThaumicHorizons_vat");
    }

    public BlockVat(Material m) {
        super(m);
        this.func_149711_c(3.0f);
        this.func_149752_b(15.0f);
        this.field_149784_t = 8;
        this.func_149663_c("ThaumicHorizons_vatSolid");
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        TileThaumcraft vat = metadata == 7 ? new TileVat() : (metadata == 4 ? new TileVatConnector() : new TileVatSlave());
        return vat;
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        if (md == 7) {
            ((TileVat)world.func_147438_o(x, y, z)).killMe();
        } else {
            ((TileVatSlave)world.func_147438_o(x, y, z)).killMyBoss(md);
        }
        super.func_149749_a(world, x, y, z, block, md);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.func_147438_o(x, y, z) instanceof TileVat) {
            return ((TileVat)world.func_147438_o(x, y, z)).activate(player, true);
        }
        if (world.func_147438_o(x, y, z) instanceof TileVatSlave) {
            return ((TileVatSlave)world.func_147438_o(x, y, z)).activate(player);
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockVatRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconGlassTL = ir.func_94245_a("thaumichorizons:vattopleft");
        this.iconGlassT = ir.func_94245_a("thaumichorizons:vattop");
        this.iconGlassTR = ir.func_94245_a("thaumichorizons:vattopright");
        this.iconGlassBL = ir.func_94245_a("thaumichorizons:vatbottomleft");
        this.iconGlassB = ir.func_94245_a("thaumichorizons:vatbottom");
        this.iconGlassBR = ir.func_94245_a("thaumichorizons:vatbottomright");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.iconGlassT;
    }
}

