/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.corporea;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.corporea.BlockCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaFunnel;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockCorporeaFunnel
extends BlockCorporeaBase
implements ILexiconable {
    IIcon[] icons;

    public BlockCorporeaFunnel() {
        super(Material.field_151573_f, "corporeaFunnel");
        this.func_149711_c(5.5f);
        this.func_149672_a(field_149777_j);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            ((TileCorporeaFunnel)world.func_147438_o(x, y, z)).doRequest();
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[par1 > 1 ? 1 : 0];
    }

    public TileCorporeaBase createNewTileEntity(World world, int meta) {
        return new TileCorporeaFunnel();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.corporeaFunnel;
    }
}

