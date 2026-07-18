/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileCocoon;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockCocoon
extends BlockModContainer
implements ILexiconable {
    protected BlockCocoon() {
        super(Material.field_151580_n);
        this.func_149711_c(3.0f);
        this.func_149752_b(50.0f);
        this.func_149672_a(field_149775_l);
        this.func_149663_c("cocoon");
        float f = 0.1875f;
        float f1 = 0.875f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, f1, 1.0f - f);
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return Blocks.field_150321_G.func_149733_h(0);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idCocoon;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        TileCocoon cocoon = (TileCocoon)world.func_147438_o(x, y, z);
        ItemStack item = player.func_71045_bC();
        if (cocoon.emeraldsGiven < 20 && item != null && item.func_77973_b() == Items.field_151166_bC) {
            if (!player.field_71075_bZ.field_75098_d) {
                --item.field_77994_a;
            }
            ++cocoon.emeraldsGiven;
            world.func_72926_e(2005, x, y, z, 6 + world.field_73012_v.nextInt(4));
        }
        return false;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    protected boolean func_149700_E() {
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileCocoon();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.cocoon;
    }
}

