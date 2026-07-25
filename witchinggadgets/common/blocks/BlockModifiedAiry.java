/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.blocks.BlockAiry
 */
package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockAiry;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.blocks.tiles.TileEntityTempLight;

public class BlockModifiedAiry
extends BlockAiry {
    public BlockModifiedAiry() {
        this.func_149647_a(WitchingGadgets.tabWG);
    }

    public void func_149651_a(IIconRegister iconRegister) {
        super.func_149651_a(iconRegister);
        this.field_149761_L = iconRegister.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addBlockHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return false;
    }

    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return false;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return 0.0f;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 0.0f;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 14;
    }

    public void func_149719_a(IBlockAccess ba, int x, int y, int z) {
        this.func_149676_a(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
        super.func_149719_a(ba, x, y, z);
    }

    public boolean isBlockReplaceable(World world, int x, int y, int z) {
        return true;
    }

    public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
        return true;
    }

    public boolean isLeaves(World world, int x, int y, int z) {
        return true;
    }

    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return 0;
    }

    public int idPicked(World world, int x, int y, int z) {
        return 0;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        if (r.nextInt(50) == 0) {
            int x1 = i + r.nextInt(2) - r.nextInt(2);
            int y1 = j + r.nextInt(2) - r.nextInt(2);
            int z1 = k + r.nextInt(2) - r.nextInt(2);
            int x2 = x1 + r.nextInt(2) - r.nextInt(2);
            int y2 = y1 + r.nextInt(2) - r.nextInt(2);
            int z2 = z1 + r.nextInt(2) - r.nextInt(2);
            Thaumcraft.proxy.wispFX3(w, (double)x1, (double)y1, (double)z1, (double)x2, (double)y2, (double)z2, 0.1f + r.nextFloat() * 0.1f, 7, false, r.nextBoolean() ? -0.033f : 0.033f);
        }
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTempLight();
    }

    public TileEntity createNewTileEntity(World var1) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
    }

    public boolean isAirBlock(World world, int x, int y, int z) {
        return true;
    }
}

