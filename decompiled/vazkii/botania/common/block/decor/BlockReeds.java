/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockReeds
extends BlockRotatedPillar
implements ILexiconable {
    IIcon topIcon;

    public BlockReeds() {
        super(Material.field_151575_d);
        this.func_149711_c(1.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("reedBlock");
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.topIcon = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.decorativeBlocks;
    }

    protected IIcon func_150163_b(int p_150163_1_) {
        return this.field_149761_L;
    }

    protected IIcon func_150161_d(int p_150161_1_) {
        return this.topIcon;
    }
}

