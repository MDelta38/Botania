/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.tiles.TileEldritchNothing;

public class BlockEldritchNothing
extends Block {
    public IIcon icon;

    public BlockEldritchNothing() {
        super(Material.field_151576_e);
        this.func_149722_s();
        this.func_149752_b(6000000.0f);
        this.func_149672_a(Block.field_149775_l);
        this.func_149715_a(0.2f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149675_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:blank");
    }

    public IIcon func_149691_a(int i, int m) {
        return this.icon;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    public AxisAlignedBB func_149633_g(World w, int i, int j, int k) {
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean hasTileEntity(int metadata) {
        return metadata == 1;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return metadata == 1 ? new TileEldritchNothing() : null;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (BlockUtils.isBlockExposed(world, x, y, z)) {
            world.func_72921_c(x, y, z, 1, 3);
        } else {
            world.func_72921_c(x, y, z, 0, 3);
        }
        super.func_149695_a(world, x, y, z, block);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        if (!(entity.field_70173_aa <= 20 || entity instanceof EntityPlayer && ((EntityPlayer)entity).field_71075_bZ.field_75098_d)) {
            entity.func_70097_a(DamageSource.field_76380_i, 8.0f);
        }
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        float f = 0.125f;
        return AxisAlignedBB.func_72330_a((double)((float)x + f), (double)((double)y + (double)f), (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }
}

