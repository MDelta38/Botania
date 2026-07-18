/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.decor.panes;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;

public class BlockModPane
extends BlockPane {
    Block source;
    public IIcon iconTop;

    public BlockModPane(Block source) {
        super("", "", Material.field_151592_s, false);
        this.source = source;
        this.func_149663_c(source.func_149739_a().replaceAll("tile.", "") + "Pane");
        this.func_149647_a(BotaniaCreativeTab.INSTANCE);
        this.func_149711_c(0.3f);
        this.func_149672_a(field_149778_k);
        this.func_149715_a(1.0f);
        this.field_149783_u = true;
    }

    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockMod.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister reg) {
        this.iconTop = IconHelper.forBlock(reg, (Block)this);
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public int func_149645_b() {
        return 18;
    }

    public int func_149701_w() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_150097_e() {
        return this.source.func_149691_a(0, 0);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side >= 2 ? this.iconTop : this.source.func_149691_a(side, meta);
    }

    public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
        Block block = world.func_147439_a(x, y, z);
        return block == ModBlocks.elfGlass || block == ModBlocks.manaGlass || block == ModBlocks.bifrostPerm || super.canPaneConnectTo(world, x, y, z, dir);
    }
}

