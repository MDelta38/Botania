/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
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
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockAlfPortal
extends BlockModContainer
implements IWandable,
ILexiconable {
    IIcon iconOff;
    IIcon iconOn;
    public static IIcon portalTex;

    public BlockAlfPortal() {
        super(Material.field_151575_d);
        this.func_149711_c(10.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("alfheimPortal");
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconOff = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconOn = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        portalTex = IconHelper.forBlock(par1IconRegister, (Block)this, "Inside");
    }

    public IIcon func_149691_a(int side, int meta) {
        return meta == 0 ? this.iconOff : this.iconOn;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileAlfPortal();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.alfhomancyIntro;
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        boolean did = ((TileAlfPortal)world.func_147438_o(x, y, z)).onWanded();
        if (did && player != null) {
            player.func_71064_a((StatBase)ModAchievements.elfPortalOpen, 1);
        }
        return did;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) == 0 ? 0 : 15;
    }
}

