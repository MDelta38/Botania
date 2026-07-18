/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.common.block.decor.walls;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import vazkii.botania.common.block.decor.walls.BlockModWall;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

public class BlockVariantWall
extends BlockModWall {
    int metaStates;
    int metaShift;

    public BlockVariantWall(Block block, int metaStates, int metaShift) {
        super(block, 0);
        this.metaStates = metaStates;
        this.metaShift = metaShift;
    }

    public BlockVariantWall(Block block, int metaStates) {
        this(block, metaStates, 0);
    }

    @Override
    public void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)name);
    }

    @Override
    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < this.metaStates; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public IIcon func_149691_a(int side, int meta) {
        return this.block.func_149691_a(side, meta + this.metaShift);
    }
}

