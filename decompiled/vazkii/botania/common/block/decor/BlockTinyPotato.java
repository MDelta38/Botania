/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.item.block.ItemBlockTinyPotato;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockTinyPotato
extends BlockModContainer
implements ILexiconable {
    public BlockTinyPotato() {
        super(Material.field_151580_n);
        this.func_149711_c(0.25f);
        this.func_149663_c("tinyPotato");
        float f = 0.375f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, f, 1.0f - f);
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockTinyPotato.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int side, int meta) {
        return Blocks.field_150405_ch.func_149691_a(0, 0);
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile instanceof TileTinyPotato) {
            ((TileTinyPotato)tile).interact();
            par5EntityPlayer.func_71064_a((StatBase)ModAchievements.tinyPotatoPet, 1);
            par1World.func_72869_a("heart", (double)par2 + this.field_149759_B + Math.random() * (this.field_149755_E - this.field_149759_B), (double)par3 + this.field_149756_F, (double)par4 + this.field_149754_D + Math.random() * (this.field_149757_G - this.field_149754_D), 0.0, 0.0, 0.0);
        }
        return true;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int l1 = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        par1World.func_72921_c(par2, par3, par4, l1, 2);
        if (par6ItemStack.func_82837_s()) {
            ((TileTinyPotato)par1World.func_147438_o((int)par2, (int)par3, (int)par4)).name = par6ItemStack.func_82833_r();
        }
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (!par6EntityPlayer.field_71075_bZ.field_75098_d) {
            this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            ItemStack stack = new ItemStack((Block)this);
            String name = ((TileTinyPotato)tile).name;
            if (!name.isEmpty()) {
                stack.func_151001_c(name);
            }
            list.add(stack);
        }
        return list;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idTinyPotato;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileTinyPotato();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.tinyPotato;
    }
}

