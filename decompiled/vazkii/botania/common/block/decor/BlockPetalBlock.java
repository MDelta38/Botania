/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPetalBlock
extends BlockMod
implements ILexiconable {
    public BlockPetalBlock() {
        super(Material.field_151585_k);
        this.func_149711_c(0.4f);
        this.func_149672_a(field_149779_h);
        this.func_149663_c("petalBlock");
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 16; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public int func_149741_i(int meta) {
        return new Color(EntitySheep.field_70898_d[meta][0], EntitySheep.field_70898_d[meta][1], EntitySheep.field_70898_d[meta][2]).getRGB();
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        return this.func_149741_i(world.func_72805_g(x, y, z));
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.flowers;
    }
}

