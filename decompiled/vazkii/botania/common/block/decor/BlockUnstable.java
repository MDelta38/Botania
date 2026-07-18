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
import java.util.Random;
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
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockUnstable
extends BlockMod
implements ILexiconable {
    public BlockUnstable() {
        super(Material.field_151573_f);
        this.func_149711_c(5.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149777_j);
        this.func_149676_a(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
        this.func_149663_c("unstableBlock");
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 16; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int color = this.func_149741_i(par1World.func_72805_g(par2, par3, par4));
        int colorBright = new Color(color).brighter().getRGB();
        int colorDark = new Color(color).darker().getRGB();
        Vector3 origVector = new Vector3((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5);
        Vector3 endVector = origVector.copy().add(par1World.field_73012_v.nextDouble() * 2.0 - 1.0, par1World.field_73012_v.nextDouble() * 2.0 - 1.0, par1World.field_73012_v.nextDouble() * 2.0 - 1.0);
        Botania.proxy.lightningFX(par1World, origVector, endVector, 5.0f, colorDark, colorBright);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public int func_149741_i(int par1) {
        float[] color = EntitySheep.field_70898_d[par1];
        return new Color(color[0], color[1], color[2]).getRGB();
    }

    public int func_149720_d(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        return this.func_149741_i(par1iBlockAccess.func_72805_g(par2, par3, par4));
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.unstableBlocks;
    }
}

