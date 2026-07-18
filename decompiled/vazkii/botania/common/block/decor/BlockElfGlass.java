/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.IElvenItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.decor.BlockManaGlass;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockElfGlass
extends BlockManaGlass
implements IElvenItem,
ILexiconable {
    private static final int ICON_COUNT = 4;
    IIcon[] icons;

    public BlockElfGlass() {
        super("elfGlass");
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        for (int i = 0; i < 4; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, this);
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int s) {
        int v = (int)Math.floor((double)new Random(x * 10 ^ y * 20 ^ z * 30).nextInt(400) / 100.0);
        return this.icons[v];
    }

    @Override
    public boolean isElvenItem(ItemStack stack) {
        return true;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.elvenResources;
    }
}

