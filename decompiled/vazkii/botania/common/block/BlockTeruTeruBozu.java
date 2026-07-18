/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileTeruTeruBozu;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockTeruTeruBozu
extends BlockModContainer
implements ILexiconable {
    public BlockTeruTeruBozu() {
        super(Material.field_151580_n);
        this.func_149663_c("teruTeruBozu");
        float f = 0.25f;
        this.func_149676_a(f, 0.01f, f, 1.0f - f, 0.99f, 1.0f - f);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity e) {
        EntityItem item;
        ItemStack stack;
        if (!world.field_72995_K && e instanceof EntityItem && this.isSunflower(stack = (item = (EntityItem)e).func_92059_d()) && this.removeRain(world)) {
            --stack.field_77994_a;
            if (stack.field_77994_a == 0) {
                e.func_70106_y();
            }
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null && (this.isSunflower(stack) && this.removeRain(world) || this.isBlueOrchid(stack) && this.startRain(world))) {
            if (!player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
            }
            return true;
        }
        return false;
    }

    public boolean isSunflower(ItemStack stack) {
        return stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150398_cm) && stack.func_77960_j() == 0;
    }

    public boolean isBlueOrchid(ItemStack stack) {
        return stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150328_O) && stack.func_77960_j() == 1;
    }

    public boolean removeRain(World world) {
        if (world.func_72896_J()) {
            world.func_72912_H().func_76084_b(false);
            return true;
        }
        return false;
    }

    public boolean startRain(World world) {
        if (!world.func_72896_J()) {
            if (world.field_73012_v.nextInt(10) == 0) {
                world.func_72912_H().func_76084_b(true);
            }
            return true;
        }
        return false;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int s) {
        return world.func_72896_J() ? 15 : 0;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150325_L.func_149691_a(0, 0);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idTeruTeruBozu;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileTeruTeruBozu();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.teruTeruBozu;
    }
}

