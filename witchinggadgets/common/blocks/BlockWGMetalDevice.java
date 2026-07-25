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
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 */
package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.ITerraformFocus;
import witchinggadgets.client.render.BlockRenderMetalDevice;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;

public class BlockWGMetalDevice
extends BlockContainer
implements ITerraformFocus {
    public static String[] subNames = new String[]{"essentiaPump", "terraformer", "tfFocusPlains", "tfFocusColdTaiga", "tfFocusDesert", "tfFocusJungle", "tfFocusHell", "voidmetalBlock", "tfFocusTaint", "tfFocusMushroom"};
    IIcon[] icons = new IIcon[subNames.length];

    public BlockWGMetalDevice() {
        super(Material.field_151573_f);
        this.func_149711_c(4.0f);
        this.func_149752_b(15.0f);
        this.func_149647_a(WitchingGadgets.tabWG);
    }

    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return worldObj.func_72805_g(x, y, z) == 7;
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public void func_149651_a(IIconRegister iconRegister) {
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = i < 7 || i > 7 ? iconRegister.func_94245_a("thaumcraft:metalbase") : iconRegister.func_94245_a("witchinggadgets:" + subNames[i]);
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        if (meta < this.icons.length) {
            return this.icons[meta];
        }
        return null;
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta < this.icons.length) {
            return this.icons[meta];
        }
        return null;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        return super.func_149646_a(iBlockAccess, x, y, z, side);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return super.func_149668_a(world, x, y, z);
    }

    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        return super.func_149633_g(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        if (world.func_147438_o(x, y, z) instanceof TileEntityEssentiaPump) {
            ForgeDirection fd = ((TileEntityEssentiaPump)world.func_147438_o((int)x, (int)y, (int)z)).facing;
            this.func_149676_a(fd == ForgeDirection.EAST ? 0.25f : 0.0f, fd == ForgeDirection.UP ? 0.25f : 0.0f, fd == ForgeDirection.SOUTH ? 0.25f : 0.0f, fd == ForgeDirection.WEST ? 0.75f : 1.0f, fd == ForgeDirection.DOWN ? 0.75f : 1.0f, fd == ForgeDirection.SOUTH ? 0.75f : 1.0f);
        } else if (world.func_72805_g(x, y, z) >= 2 && world.func_72805_g(x, y, z) <= 6) {
            this.func_149676_a(0.125f, 0.0f, 0.125f, 0.875f, 0.75f, 0.875f);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public TileEntity func_149915_a(World world, int metadata) {
        switch (metadata) {
            case 0: {
                return new TileEntityEssentiaPump();
            }
            case 1: {
                return new TileEntityTerraformer();
            }
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: {
                return new TileEntityTerraformFocus();
            }
            case 7: {
                return null;
            }
            case 8: 
            case 9: {
                return new TileEntityTerraformFocus();
            }
        }
        return null;
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < subNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int f;
        int playerViewQuarter = MathHelper.func_76128_c((double)((double)(entityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int meta = world.func_72805_g(x, y, z);
        int n = playerViewQuarter == 0 ? 2 : (playerViewQuarter == 1 ? 5 : (f = playerViewQuarter == 2 ? 3 : 4));
        if (meta == 0) {
            ((TileEntityEssentiaPump)world.func_147438_o((int)x, (int)y, (int)z)).facing = ForgeDirection.getOrientation((int)f).getOpposite();
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return super.func_149727_a(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
    }

    public int func_149645_b() {
        return BlockRenderMetalDevice.renderID;
    }

    @Override
    public Aspect requiredAspect(int meta) {
        if (meta < subNames.length && subNames[meta].startsWith("tfFocus")) {
            if (subNames[meta].equalsIgnoreCase("tfFocusPlains")) {
                return Aspect.PLANT;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusColdTaiga")) {
                return Aspect.COLD;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusDesert")) {
                return Aspect.FIRE;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusJungle")) {
                return Aspect.TREE;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusHell")) {
                return Aspect.DARKNESS;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusTaint")) {
                return Aspect.TAINT;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusMushroom")) {
                return Aspect.SLIME;
            }
        }
        return null;
    }

    @Override
    public Aspect requiredAspect(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta < subNames.length && subNames[meta].startsWith("tfFocus")) {
            if (subNames[meta].equalsIgnoreCase("tfFocusPlains")) {
                return Aspect.PLANT;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusColdTaiga")) {
                return Aspect.COLD;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusDesert")) {
                return Aspect.FIRE;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusJungle")) {
                return Aspect.TREE;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusHell")) {
                return Aspect.DARKNESS;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusTaint")) {
                return Aspect.TAINT;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusMushroom")) {
                return Aspect.SLIME;
            }
        }
        return null;
    }

    @Override
    public BiomeGenBase getCreatedBiome(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta < subNames.length && subNames[meta].startsWith("tfFocus")) {
            if (subNames[meta].equalsIgnoreCase("tfFocusPlains")) {
                return BiomeGenBase.field_76772_c;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusColdTaiga")) {
                return BiomeGenBase.field_150584_S;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusDesert")) {
                return BiomeGenBase.field_76769_d;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusJungle")) {
                return BiomeGenBase.field_76782_w;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusHell")) {
                return BiomeGenBase.field_76778_j;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusTaint")) {
                return ThaumcraftWorldGenerator.biomeTaint;
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusMushroom")) {
                return BiomeGenBase.field_76789_p;
            }
        }
        return null;
    }

    @Override
    public ItemStack getDisplayedBlock(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta < subNames.length && subNames[meta].startsWith("tfFocus")) {
            if (subNames[meta].equalsIgnoreCase("tfFocusPlains")) {
                return new ItemStack((Block)Blocks.field_150349_c);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusColdTaiga")) {
                return new ItemStack(Blocks.field_150432_aD);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusDesert")) {
                return new ItemStack((Block)Blocks.field_150354_m);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusJungle")) {
                return new ItemStack(Blocks.field_150364_r, 1, 3);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusHell")) {
                return new ItemStack(Blocks.field_150385_bj);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusTaint")) {
                return new ItemStack(ConfigBlocks.blockTaint);
            }
            if (subNames[meta].equalsIgnoreCase("tfFocusMushroom")) {
                return new ItemStack((Block)Blocks.field_150391_bh);
            }
        }
        return null;
    }
}

