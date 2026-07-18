/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileDistributor;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockDistributor
extends BlockModContainer
implements ILexiconable {
    IIcon iconSide;
    IIcon iconTop;

    public BlockDistributor() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("distributor");
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconTop = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconSide = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return par1 == 0 ? ModBlocks.livingrock.func_149691_a(0, 0) : (par1 == 1 ? this.iconTop : this.iconSide);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileDistributor();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.distributor;
    }
}

