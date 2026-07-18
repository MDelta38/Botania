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
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.mana.TileManaVoid;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockManaVoid
extends BlockModContainer
implements ILexiconable,
IPoolOverlayProvider {
    IIcon overlay;

    public BlockManaVoid() {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(2000.0f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149663_c("manaVoid");
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.overlay = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    public TileEntity func_149915_a(World world, int id) {
        return new TileManaVoid();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.manaVoid;
    }

    @Override
    public IIcon getIcon(World world, int x, int y, int z) {
        return this.overlay;
    }
}

