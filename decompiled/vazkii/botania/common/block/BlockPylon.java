/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.crafting.IInfusionStabiliser
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.crafting.IInfusionStabiliser", striprefs=true)
public class BlockPylon
extends BlockModContainer
implements ILexiconable,
IInfusionStabiliser {
    public BlockPylon() {
        super(Material.field_151573_f);
        this.func_149711_c(5.5f);
        this.func_149672_a(field_149777_j);
        this.func_149663_c("pylon");
        this.func_149715_a(0.5f);
        float f = 0.125f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, 1.3125f, 1.0f - f);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149666_a(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 3; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        return par2 == 0 ? Blocks.field_150484_ah.func_149691_a(0, 0) : ModBlocks.storage.func_149691_a(0, par2);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idPylon;
    }

    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        return world.func_72805_g(x, y, z) == 0 ? 8.0f : 15.0f;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TilePylon();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 0 ? LexiconData.pylon : (meta == 1 ? LexiconData.alfhomancyIntro : LexiconData.gaiaRitual);
    }

    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return ConfigHandler.enableThaumcraftStablizers;
    }
}

