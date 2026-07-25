/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockBaseContainer
extends BlockContainer {
    protected boolean registerBlockName = true;
    protected boolean registerTileEntity = true;
    protected boolean registerWithCreateTab = true;
    protected final Class<? extends TileEntity> clazzTile;
    protected final Class<? extends ItemBlock> clazzItem;

    public BlockBaseContainer(Material material, Class<? extends TileEntity> clazzTile) {
        this(material, clazzTile, null);
    }

    public BlockBaseContainer(Material material, Class<? extends TileEntity> clazzTile, Class<? extends ItemBlock> clazzItem) {
        super(material);
        this.clazzTile = clazzTile;
        this.clazzItem = clazzItem;
    }

    public Block func_149663_c(String blockName) {
        if (this.registerWithCreateTab) {
            this.func_149647_a(WitcheryCreativeTab.INSTANCE);
        }
        if (this.registerBlockName) {
            if (this.clazzItem == null) {
                BlockUtil.registerBlock((Block)this, blockName);
            } else {
                BlockUtil.registerBlock((Block)this, this.clazzItem, blockName);
            }
        }
        if (this.registerTileEntity) {
            GameRegistry.registerTileEntity(this.clazzTile, (String)blockName);
        }
        return super.func_149663_c(blockName);
    }

    public TileEntity func_149915_a(World world, int metadata) {
        try {
            return this.clazzTile.newInstance();
        }
        catch (Throwable e) {
            return null;
        }
    }
}

