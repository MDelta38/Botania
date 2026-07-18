/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockWall
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.walls;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockModWall
extends BlockWall
implements ILexiconable {
    Block block;
    int meta;

    public BlockModWall(Block block, int meta) {
        super(block);
        this.block = block;
        this.meta = meta;
        this.func_149663_c(block.func_149739_a().replaceAll("tile.", "") + meta + "Wall");
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

    public Block func_149663_c(String par1Str) {
        this.register(par1Str);
        return super.func_149663_c(par1Str);
    }

    public void register(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)name);
    }

    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        list.add(new ItemStack(item));
    }

    public IIcon func_149691_a(int side, int meta) {
        return this.block.func_149691_a(side, this.meta);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.decorativeBlocks;
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }
}

