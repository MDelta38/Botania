/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockVine
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSolidVines
extends BlockVine
implements ILexiconable {
    public BlockSolidVines() {
        this.func_149663_c("solidVine");
        this.func_149711_c(0.5f);
        this.func_149672_a(field_149779_h);
        this.func_149658_d("vine");
        this.func_149647_a(null);
    }

    public AxisAlignedBB func_149668_a(World w, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)w, x, y, z);
        return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E), (double)((double)y + this.field_149756_F), (double)((double)z + this.field_149757_G));
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack(Blocks.field_150395_bd);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.vineBall;
    }
}

