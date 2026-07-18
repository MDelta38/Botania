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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileEnderEye;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockEnderEye
extends BlockModContainer
implements ILexiconable {
    IIcon iconOff;
    IIcon iconOn;

    protected BlockEnderEye() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149777_j);
        this.func_149663_c("enderEyeBlock");
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconOff = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconOn = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta == 0 ? this.iconOff : this.iconOn;
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        return world.func_72805_g(x, y, z);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEnderEye();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.enderEyeBlock;
    }
}

