/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBellows
extends BlockModContainer
implements ILexiconable {
    private static final int[] META_ROTATIONS = new int[]{3, 4, 2, 5};

    public BlockBellows() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("bellows");
        float f = 0.1875f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, 0.625f, 1.0f - f);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.func_76128_c((double)((double)(p_149689_5_.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        p_149689_1_.func_72921_c(p_149689_2_, p_149689_3_, p_149689_4_, META_ROTATIONS[l], 2);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        if (EntityDoppleganger.isTruePlayer((Entity)player)) {
            ((TileBellows)world.func_147438_o(x, y, z)).interact();
        }
        return true;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public IIcon func_149691_a(int par1, int par2) {
        return ModBlocks.livingwood.func_149691_a(par1, 0);
    }

    public int func_149645_b() {
        return LibRenderIDs.idBellows;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileBellows();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.bellows;
    }
}

