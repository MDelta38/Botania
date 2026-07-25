/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

public class BlockLoot
extends Block {
    String iconPre = "urn";
    int renderType = 0;
    public IIcon[] icon = new IIcon[4];

    public BlockLoot(Material mat, String ip, int rt) {
        super(mat);
        this.func_149711_c(0.15f);
        this.func_149752_b(0.0f);
        this.iconPre = ip;
        this.renderType = rt;
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_top");
        this.icon[1] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_0");
        this.icon[2] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_1");
        this.icon[3] = ir.func_94245_a("thaumcraft:" + this.iconPre + "_side_2");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side <= 1) {
            return this.icon[0];
        }
        return this.icon[meta + 1];
    }

    public int func_149645_b() {
        return this.renderType == 1 ? ConfigBlocks.blockLootUrnRI : ConfigBlocks.blockLootCrateRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149633_g(World w, int i, int j, int k) {
        if (this.renderType == 1) {
            this.func_149676_a(BlockRenderer.W2, BlockRenderer.W1, BlockRenderer.W2, BlockRenderer.W14, BlockRenderer.W13, BlockRenderer.W14);
        } else {
            this.func_149676_a(BlockRenderer.W1, 0.0f, BlockRenderer.W1, BlockRenderer.W15, BlockRenderer.W14, BlockRenderer.W15);
        }
        return super.func_149633_g(w, i, j, k);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int q = 1 + md + world.field_73012_v.nextInt(3);
        for (int a = 0; a < q; ++a) {
            ItemStack is = Utils.generateLoot(md, world.field_73012_v);
            if (is == null) continue;
            ret.add(is.func_77946_l());
        }
        return ret;
    }
}

