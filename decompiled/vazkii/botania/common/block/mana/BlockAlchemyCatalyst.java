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
package vazkii.botania.common.block.mana;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockAlchemyCatalyst
extends BlockMod
implements ILexiconable,
IPoolOverlayProvider {
    IIcon[] icons;

    public BlockAlchemyCatalyst() {
        this("alchemyCatalyst");
    }

    public BlockAlchemyCatalyst(String name) {
        super(Material.field_151576_e);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149663_c(name);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[Math.min(2, par1)];
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.alchemy;
    }

    @Override
    public IIcon getIcon(World world, int x, int y, int z) {
        return this.icons[3];
    }
}

