/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.quartz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartz;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSpecialQuartzSlab
extends BlockModSlab {
    Block source;

    public BlockSpecialQuartzSlab(Block source, boolean par2) {
        super(par2, Material.field_151576_e, "quartzSlab" + ((BlockSpecialQuartz)source).type + (par2 ? "Full" : "Half"));
        this.func_149711_c(0.8f);
        this.func_149752_b(10.0f);
        this.source = source;
    }

    @Override
    public BlockSlab getFullBlock() {
        if (this.source == ModFluffBlocks.darkQuartz) {
            return (BlockSlab)ModFluffBlocks.darkQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.manaQuartz) {
            return (BlockSlab)ModFluffBlocks.manaQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.blazeQuartz) {
            return (BlockSlab)ModFluffBlocks.blazeQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.lavenderQuartz) {
            return (BlockSlab)ModFluffBlocks.lavenderQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.redQuartz) {
            return (BlockSlab)ModFluffBlocks.redQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.elfQuartz) {
            return (BlockSlab)ModFluffBlocks.elfQuartzSlabFull;
        }
        if (this.source == ModFluffBlocks.sunnyQuartz) {
            return (BlockSlab)ModFluffBlocks.sunnyQuartzSlabFull;
        }
        return this;
    }

    @Override
    public BlockSlab getSingleBlock() {
        if (this.source == ModFluffBlocks.darkQuartz) {
            return (BlockSlab)ModFluffBlocks.darkQuartzSlab;
        }
        if (this.source == ModFluffBlocks.manaQuartz) {
            return (BlockSlab)ModFluffBlocks.manaQuartzSlab;
        }
        if (this.source == ModFluffBlocks.blazeQuartz) {
            return (BlockSlab)ModFluffBlocks.blazeQuartzSlab;
        }
        if (this.source == ModFluffBlocks.lavenderQuartz) {
            return (BlockSlab)ModFluffBlocks.lavenderQuartzSlab;
        }
        if (this.source == ModFluffBlocks.redQuartz) {
            return (BlockSlab)ModFluffBlocks.redQuartzSlab;
        }
        if (this.source == ModFluffBlocks.elfQuartz) {
            return (BlockSlab)ModFluffBlocks.elfQuartzSlab;
        }
        if (this.source == ModFluffBlocks.sunnyQuartz) {
            return (BlockSlab)ModFluffBlocks.sunnyQuartzSlab;
        }
        return this;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack((Block)this.getSingleBlock());
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.source.func_149733_h(par1);
    }

    @Override
    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)this.getSingleBlock());
    }

    @Override
    public ItemStack func_149644_j(int par1) {
        return new ItemStack((Block)this.getSingleBlock());
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return this == ModFluffBlocks.elfQuartzSlab ? LexiconData.elvenResources : LexiconData.decorativeBlocks;
    }
}

