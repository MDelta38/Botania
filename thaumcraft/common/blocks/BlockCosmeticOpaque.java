/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileOwned;

public class BlockCosmeticOpaque
extends BlockContainer {
    public IIcon[] icon = new IIcon[3];
    public static IIcon[] wardedGlassIcon = new IIcon[47];
    public int currentPass;

    public BlockCosmeticOpaque() {
        super(Material.field_151576_e);
        this.func_149752_b(5.0f);
        this.func_149711_c(1.5f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:amberblock");
        this.icon[1] = ir.func_94245_a("thaumcraft:amberbrick");
        this.icon[2] = ir.func_94245_a("thaumcraft:amberblock_top");
        for (int a = 0; a < 47; ++a) {
            BlockCosmeticOpaque.wardedGlassIcon[a] = ir.func_94245_a("thaumcraft:warded_glass_" + (a + 1));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 == 0 && par1 < 2) {
            return this.icon[2];
        }
        if (par2 == 2) {
            return wardedGlassIcon[0];
        }
        return this.icon[par2];
    }

    public int func_149645_b() {
        return ConfigBlocks.blockCosmeticOpaqueRI;
    }

    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
        if (md == 2) {
            float f = (float)target.field_72307_f.field_72450_a - (float)target.field_72311_b;
            float f1 = (float)target.field_72307_f.field_72448_b - (float)target.field_72312_c;
            float f2 = (float)target.field_72307_f.field_72449_c - (float)target.field_72309_d;
            Thaumcraft.proxy.blockWard(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, ForgeDirection.getOrientation((int)target.field_72310_e), f, f1, f2);
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            boolean[] bitMatrix = new boolean[8];
            if (side == 0 || side == 1) {
                bitMatrix[0] = world.func_147439_a(x - 1, y, z - 1) == this && world.func_72805_g(x - 1, y, z - 1) == 2;
                bitMatrix[1] = world.func_147439_a(x, y, z - 1) == this && world.func_72805_g(x, y, z - 1) == 2;
                bitMatrix[2] = world.func_147439_a(x + 1, y, z - 1) == this && world.func_72805_g(x + 1, y, z - 1) == 2;
                bitMatrix[3] = world.func_147439_a(x - 1, y, z) == this && world.func_72805_g(x - 1, y, z) == 2;
                bitMatrix[4] = world.func_147439_a(x + 1, y, z) == this && world.func_72805_g(x + 1, y, z) == 2;
                bitMatrix[5] = world.func_147439_a(x - 1, y, z + 1) == this && world.func_72805_g(x - 1, y, z + 1) == 2;
                bitMatrix[6] = world.func_147439_a(x, y, z + 1) == this && world.func_72805_g(x, y, z + 1) == 2;
                boolean bl = bitMatrix[7] = world.func_147439_a(x + 1, y, z + 1) == this && world.func_72805_g(x + 1, y, z + 1) == 2;
            }
            if (side == 2 || side == 3) {
                bitMatrix[0] = world.func_147439_a(x + (side == 2 ? 1 : -1), y + 1, z) == this && world.func_72805_g(x + (side == 2 ? 1 : -1), y + 1, z) == 2;
                bitMatrix[1] = world.func_147439_a(x, y + 1, z) == this && world.func_72805_g(x, y + 1, z) == 2;
                bitMatrix[2] = world.func_147439_a(x + (side == 3 ? 1 : -1), y + 1, z) == this && world.func_72805_g(x + (side == 3 ? 1 : -1), y + 1, z) == 2;
                bitMatrix[3] = world.func_147439_a(x + (side == 2 ? 1 : -1), y, z) == this && world.func_72805_g(x + (side == 2 ? 1 : -1), y, z) == 2;
                bitMatrix[4] = world.func_147439_a(x + (side == 3 ? 1 : -1), y, z) == this && world.func_72805_g(x + (side == 3 ? 1 : -1), y, z) == 2;
                bitMatrix[5] = world.func_147439_a(x + (side == 2 ? 1 : -1), y - 1, z) == this && world.func_72805_g(x + (side == 2 ? 1 : -1), y - 1, z) == 2;
                bitMatrix[6] = world.func_147439_a(x, y - 1, z) == this && world.func_72805_g(x, y - 1, z) == 2;
                boolean bl = world.func_147439_a(x + (side == 3 ? 1 : -1), y - 1, z) == this && world.func_72805_g(x + (side == 3 ? 1 : -1), y - 1, z) == 2 ? true : (bitMatrix[7] = false);
            }
            if (side == 4 || side == 5) {
                bitMatrix[0] = world.func_147439_a(x, y + 1, z + (side == 5 ? 1 : -1)) == this && world.func_72805_g(x, y + 1, z + (side == 5 ? 1 : -1)) == 2;
                bitMatrix[1] = world.func_147439_a(x, y + 1, z) == this && world.func_72805_g(x, y + 1, z) == 2;
                bitMatrix[2] = world.func_147439_a(x, y + 1, z + (side == 4 ? 1 : -1)) == this && world.func_72805_g(x, y + 1, z + (side == 4 ? 1 : -1)) == 2;
                bitMatrix[3] = world.func_147439_a(x, y, z + (side == 5 ? 1 : -1)) == this && world.func_72805_g(x, y, z + (side == 5 ? 1 : -1)) == 2;
                bitMatrix[4] = world.func_147439_a(x, y, z + (side == 4 ? 1 : -1)) == this && world.func_72805_g(x, y, z + (side == 4 ? 1 : -1)) == 2;
                bitMatrix[5] = world.func_147439_a(x, y - 1, z + (side == 5 ? 1 : -1)) == this && world.func_72805_g(x, y - 1, z + (side == 5 ? 1 : -1)) == 2;
                bitMatrix[6] = world.func_147439_a(x, y - 1, z) == this && world.func_72805_g(x, y - 1, z) == 2;
                bitMatrix[7] = world.func_147439_a(x, y - 1, z + (side == 4 ? 1 : -1)) == this && world.func_72805_g(x, y - 1, z + (side == 4 ? 1 : -1)) == 2;
            }
            int idBuilder = 0;
            for (int i = 0; i <= 7; ++i) {
                idBuilder += bitMatrix[i] ? (i == 0 ? 1 : (i == 1 ? 2 : (i == 2 ? 4 : (i == 3 ? 8 : (i == 4 ? 16 : (i == 5 ? 32 : (i == 6 ? 64 : 128))))))) : 0;
            }
            return idBuilder > 255 || idBuilder < 0 ? wardedGlassIcon[0] : wardedGlassIcon[UtilsFX.connectedTextureRefByID[idBuilder]];
        }
        return super.func_149673_e(world, x, y, z, side);
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean canRenderInPass(int pass) {
        this.currentPass = pass;
        return pass == 1 || pass == 0;
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md <= 1) {
            return 3;
        }
        return super.getLightOpacity(world, x, y, z);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.func_147439_a(x, y, z);
        if (world.func_72805_g(x, y, z) != world.func_72805_g(x - Facing.field_71586_b[side], y - Facing.field_71587_c[side], z - Facing.field_71585_d[side])) {
            return true;
        }
        if (block == this) {
            return false;
        }
        return super.func_149646_a(world, x, y, z, side);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 2) {
            return new TileOwned();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return false;
        }
        return super.canEntityDestroy(world, x, y, z, entity);
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        int md = world.func_72805_g(x, y, z);
        if (md != 2) {
            super.onBlockExploded(world, x, y, z, explosion);
        }
    }

    public boolean func_149659_a(Explosion explosion) {
        return false;
    }

    public void func_149689_a(World w, int x, int y, int z, EntityLivingBase p, ItemStack is) {
        TileEntity tile = w.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileOwned && p instanceof EntityPlayer) {
            ((TileOwned)tile).owner = ((EntityPlayer)p).func_70005_c_();
            tile.func_70296_d();
        }
        super.func_149689_a(w, x, y, z, p, is);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return null;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return Config.wardedStone ? -1.0f : 5.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return 999.0f;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }
}

