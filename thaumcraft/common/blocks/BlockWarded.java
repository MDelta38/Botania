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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileWarded;

public class BlockWarded
extends BlockContainer {
    public IIcon icon;
    public IIcon iconRune;
    int sc = 0;

    public BlockWarded() {
        super(Material.field_151576_e);
        this.func_149672_a(field_149769_e);
        this.func_149649_H();
        this.func_149752_b(999.0f);
        this.func_149722_s();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:blank");
        this.iconRune = ir.func_94245_a("thaumcraft:runeborder");
    }

    public IIcon func_149691_a(int i, int m) {
        return this.icon;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        float f = (float)target.field_72307_f.field_72450_a - (float)target.field_72311_b;
        float f1 = (float)target.field_72307_f.field_72448_b - (float)target.field_72312_c;
        float f2 = (float)target.field_72307_f.field_72449_c - (float)target.field_72309_d;
        Thaumcraft.proxy.blockWard(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, ForgeDirection.getOrientation((int)target.field_72310_e), f, f1, f2);
        return true;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockWardedRI;
    }

    public Block getBlock(World world, int x, int y, int z) {
        if (this.sc > 5) {
            this.sc = 0;
            return Blocks.field_150348_b;
        }
        ++this.sc;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileWarded) {
            this.sc = 0;
            return ((TileWarded)tile).block;
        }
        return Blocks.field_150348_b;
    }

    public Block getBlock(IBlockAccess world, int x, int y, int z) {
        if (this.sc > 5) {
            this.sc = 0;
            return Blocks.field_150348_b;
        }
        ++this.sc;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileWarded) {
            this.sc = 0;
            return ((TileWarded)tile).block;
        }
        return Blocks.field_150348_b;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public int func_149656_h() {
        return 2;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return new TileWarded();
    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        return false;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int par5) {
        return this.getBlock(ba, x, y, z).func_149673_e(ba, x, y, z, par5);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess ba, int x, int y, int z) {
        return this.getBlock(ba, x, y, z).func_149720_d(ba, x, y, z);
    }

    public int func_149643_k(World world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).func_149643_k(world, x, y, z);
    }

    public int func_149677_c(IBlockAccess ba, int x, int y, int z) {
        return this.getBlock(ba, x, y, z).func_149677_c(ba, x, y, z);
    }

    public boolean func_149646_a(IBlockAccess ba, int x, int y, int z, int par5) {
        return this.getBlock(ba, x, y, z).func_149646_a(ba, x, y, z, par5);
    }

    public boolean func_149747_d(IBlockAccess ba, int x, int y, int z, int par5) {
        return this.getBlock(ba, x, y, z).func_149747_d(ba, x, y, z, par5);
    }

    public AxisAlignedBB func_149633_g(World ba, int x, int y, int z) {
        return this.getBlock(ba, x, y, z).func_149633_g(ba, x, y, z);
    }

    public AxisAlignedBB func_149668_a(World ba, int x, int y, int z) {
        return this.getBlock(ba, x, y, z).func_149668_a(ba, x, y, z);
    }

    public void func_149734_b(World ba, int x, int y, int z, Random par5Random) {
        this.getBlock(ba, x, y, z).func_149734_b(ba, x, y, z, par5Random);
    }

    public boolean func_149707_d(World ba, int x, int y, int z, int par5) {
        return this.getBlock(ba, x, y, z).func_149707_d(ba, x, y, z, par5);
    }

    public void func_149724_b(World ba, int x, int y, int z, Entity par5Entity) {
        this.getBlock(ba, x, y, z).func_149724_b(ba, x, y, z, par5Entity);
    }

    public void func_149699_a(World ba, int x, int y, int z, EntityPlayer par5EntityPlayer) {
        this.getBlock(ba, x, y, z).func_149699_a(ba, x, y, z, par5EntityPlayer);
    }

    public void func_149640_a(World ba, int x, int y, int z, Entity par5Entity, Vec3 par6Vec3) {
        this.getBlock(ba, x, y, z).func_149640_a(ba, x, y, z, par5Entity, par6Vec3);
    }

    public void func_149719_a(IBlockAccess ba, int x, int y, int z) {
        this.getBlock(ba, x, y, z).func_149719_a(ba, x, y, z);
    }

    public void func_149743_a(World ba, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        this.getBlock(ba, x, y, z).func_149743_a(ba, x, y, z, aabb, list, entity);
    }

    public void func_149670_a(World ba, int x, int y, int z, Entity par5Entity) {
        this.getBlock(ba, x, y, z).func_149670_a(ba, x, y, z, par5Entity);
    }

    public void func_149746_a(World ba, int x, int y, int z, Entity par5Entity, float par6) {
        this.getBlock(ba, x, y, z).func_149746_a(ba, x, y, z, par5Entity, par6);
    }

    public Item func_149694_d(World ba, int x, int y, int z) {
        return this.getBlock(ba, x, y, z).func_149694_d(ba, x, y, z);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileWarded) {
            return ((TileWarded)tile).light;
        }
        return 0;
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return this.getBlock(world, x, y, z).isLadder(world, x, y, z, entity);
    }

    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).isNormalCube(world, x, y, z);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return this.getBlock(world, x, y, z).isSideSolid(world, x, y, z, side);
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).canSustainLeaves(world, x, y, z);
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).canPlaceTorchOnTop(world, x, y, z);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).getPickBlock(target, world, x, y, z);
    }

    public boolean isFoliage(IBlockAccess world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).isFoliage(world, x, y, z);
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        return this.getBlock(world, x, y, z).canSustainPlant(world, x, y, z, direction, plant);
    }

    public boolean isFertile(World world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).isFertile(world, x, y, z);
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).getLightOpacity(world, x, y, z);
    }

    public boolean isBeaconBase(IBlockAccess world, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return this.getBlock(world, x, y, z).isBeaconBase(world, x, y, z, beaconX, beaconY, beaconZ);
    }

    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        return this.getBlock(world, x, y, z).getEnchantPowerBonus(world, x, y, z);
    }

    public boolean canHarvestBlock(EntityPlayer player, int meta) {
        return true;
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    }
}

