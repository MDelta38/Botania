/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileEssentiaReservoir;

public class BlockEssentiaReservoir
extends BlockContainer {
    public IIcon icon = null;

    public BlockEssentiaReservoir() {
        super(Material.field_151573_f);
        this.func_149711_c(2.0f);
        this.func_149752_b(17.0f);
        this.func_149672_a(Block.field_149777_j);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:essentiareservoir");
    }

    public IIcon func_149691_a(int i, int md) {
        return this.icon;
    }

    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        return this.icon;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockEssentiaReservoirRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149701_w() {
        return 1;
    }

    public int func_149692_a(int metadata) {
        return metadata;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileEssentiaReservoir();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEssentiaReservoir) {
            float r = (float)((TileEssentiaReservoir)te).essentia.visSize() / (float)((TileEssentiaReservoir)te).maxAmount;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileEssentiaReservoir)te).essentia.visSize() > 0 ? 1 : 0);
        }
        return 0;
    }

    public void func_149749_a(World world, int x, int y, int z, Block par5, int par6) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEssentiaReservoir) {
            int sz = ((TileEssentiaReservoir)te).essentia.visSize() / 16;
            int q = 0;
            if (sz > 0) {
                world.func_72876_a(null, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.0f, false);
                for (int a = 0; a < 50; ++a) {
                    int zz;
                    int yy;
                    int xx = x + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5);
                    if (!world.func_147437_c(xx, yy = y + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5), zz = z + world.field_73012_v.nextInt(5) - world.field_73012_v.nextInt(5))) continue;
                    if (yy < y) {
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
                    } else {
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
                    }
                    if (q++ >= sz) break;
                }
            }
        }
        super.func_149749_a(world, x, y, z, par5, par6);
    }
}

