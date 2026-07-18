/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.corporea;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.corporea.BlockCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockCorporeaCrystalCube
extends BlockCorporeaBase
implements ILexiconable {
    public BlockCorporeaCrystalCube() {
        super(Material.field_151573_f, "corporeaCrystalCube");
        this.func_149711_c(5.5f);
        this.func_149672_a(field_149777_j);
        float f = 0.1875f;
        this.func_149676_a(f, 0.0f, f, 1.0f - f, 1.0f, 1.0f - f);
    }

    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        if (!world.field_72995_K) {
            TileCorporeaCrystalCube cube = (TileCorporeaCrystalCube)world.func_147438_o(x, y, z);
            cube.doRequest(player.func_70093_af());
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            TileCorporeaCrystalCube cube = (TileCorporeaCrystalCube)world.func_147438_o(x, y, z);
            cube.setRequestTarget(stack);
            return true;
        }
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idCorporeaCrystalCybe;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    public IIcon func_149691_a(int side, int meta) {
        return ModBlocks.storage.func_149691_a(0, 2);
    }

    public TileCorporeaBase createNewTileEntity(World world, int meta) {
        return new TileCorporeaCrystalCube();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.corporeaCrystalCube;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int s) {
        return ((TileCorporeaCrystalCube)world.func_147438_o((int)x, (int)y, (int)z)).compValue;
    }
}

