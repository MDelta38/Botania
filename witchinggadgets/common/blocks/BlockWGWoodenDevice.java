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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidContainerRegistry$FluidContainerData
 *  net.minecraftforge.fluids.FluidStack
 *  thaumcraft.api.wands.IWandable
 *  thaumcraft.common.Thaumcraft
 */
package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.BlockRenderWoodenDevice;
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.blocks.tiles.TileEntitySnowGen;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class BlockWGWoodenDevice
extends BlockContainer
implements IWandable {
    public static String[] subNames = new String[]{"spinningWheel", "snowGen", "cobbleGen", "cuttingTable", "saunaStove", "labelLibrary"};
    IIcon[] icons = new IIcon[subNames.length];
    IIcon saunaTop;

    public BlockWGWoodenDevice() {
        super(Material.field_151575_d);
        this.func_149647_a(WitchingGadgets.tabWG);
        this.func_149711_c(2.5f);
        this.func_149752_b(10.0f);
        this.func_149675_a(true);
    }

    public void func_149651_a(IIconRegister iconRegister) {
        this.icons[0] = iconRegister.func_94245_a("thaumcraft:woodplain");
        this.icons[4] = iconRegister.func_94245_a("witchinggadgets:saunaStove_side");
        this.saunaTop = iconRegister.func_94245_a("witchinggadgets:saunaStove_top");
        BlockRenderWoodenDevice.coal = iconRegister.func_94245_a("witchinggadgets:saunaStove_coal");
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        return this.func_149691_a(side, world.func_72805_g(x, y, z));
    }

    public IIcon func_149691_a(int side, int metadata) {
        if (metadata == 4) {
            return side == 1 ? this.saunaTop : this.icons[4];
        }
        if (metadata == 1) {
            return this.icons[1];
        }
        return this.icons[0];
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return BlockRenderWoodenDevice.renderID;
    }

    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x - (side == 4 ? -1 : (side == 5 ? 1 : 0)), y - (side == 0 ? -1 : (side == 1 ? 1 : 0)), z - (side == 2 ? -1 : (side == 3 ? 1 : 0)));
        if (meta == 3 || meta == 4) {
            return true;
        }
        return super.func_149646_a(world, x, y, z, side);
    }

    public void func_149666_a(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < subNames.length; ++i) {
            if (i == 1) continue;
            list.add(new ItemStack(item, 1, i));
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        FluidStack fs;
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            TileEntitySpinningWheel tile = (TileEntitySpinningWheel)world.func_147438_o(x, y, z);
            if (tile == null || player.func_70093_af()) {
                return false;
            }
            player.openGui((Object)WitchingGadgets.instance, 0, world, x, y, z);
            return true;
        }
        if (meta == 3 && !player.func_70093_af()) {
            player.openGui((Object)WitchingGadgets.instance, 9, world, x, y, z);
            return true;
        }
        if (meta == 4 && (fs = FluidContainerRegistry.getFluidForFilledItem((ItemStack)player.field_71071_by.func_70448_g())) != null && !world.field_72995_K) {
            TileEntitySaunaStove tile = (TileEntitySaunaStove)world.func_147438_o(x, y, z);
            if (tile.tank.getFluidAmount() < tile.tank.getCapacity() && tile.tank.getFluid() == null || tile.tank.getFluid().isFluidEqual(fs)) {
                boolean b;
                FluidContainerRegistry.FluidContainerData[] fcs;
                tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem((ItemStack)player.field_71071_by.func_70448_g()), true);
                ItemStack emptyContainer = null;
                for (FluidContainerRegistry.FluidContainerData fcd : fcs = FluidContainerRegistry.getRegisteredFluidContainerData()) {
                    if (!fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) continue;
                    emptyContainer = fcd.emptyContainer.func_77946_l();
                }
                player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                if (emptyContainer != null && !(b = player.field_71071_by.func_70441_a(emptyContainer))) {
                    player.func_71019_a(emptyContainer, false);
                }
                player.field_71069_bz.func_75142_b();
                world.func_147471_g(x, y, z);
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "game.neutral.swim", 0.33f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f);
            }
        }
        if (meta == 5 && !player.func_70093_af()) {
            if (!world.field_72995_K) {
                player.openGui((Object)WitchingGadgets.instance, 8, world, x, y, z);
            }
            return true;
        }
        return false;
    }

    public void func_149719_a(IBlockAccess iBlockAccess, int x, int y, int z) {
        int meta = iBlockAccess.func_72805_g(x, y, z);
        if (meta == 0 && iBlockAccess.func_147438_o(x, y, z) instanceof TileEntitySpinningWheel) {
            switch (((TileEntitySpinningWheel)iBlockAccess.func_147438_o((int)x, (int)y, (int)z)).facing) {
                default: {
                    this.func_149676_a(0.0f, 0.0f, 0.3125f, 1.0f, 1.25f, 0.6875f);
                    break;
                }
                case 3: {
                    this.func_149676_a(0.0f, 0.0f, 0.3125f, 1.0f, 1.25f, 0.6875f);
                    break;
                }
                case 4: {
                    this.func_149676_a(0.3125f, 0.0f, 0.0f, 0.6875f, 1.25f, 1.0f);
                    break;
                }
                case 5: {
                    this.func_149676_a(0.3125f, 0.0f, 0.0f, 0.6875f, 1.25f, 1.0f);
                    break;
                }
            }
        } else if (meta == 3) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.875f, 1.0f);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
        return super.func_149668_a(world, x, y, z);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack stack) {
        int f;
        int playerViewQuarter = MathHelper.func_76128_c((double)((double)(entityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        int meta = world.func_72805_g(x, y, z);
        int n = playerViewQuarter == 0 ? 2 : (playerViewQuarter == 1 ? 5 : (f = playerViewQuarter == 2 ? 3 : 4));
        if (meta == 0) {
            ((TileEntitySpinningWheel)world.func_147438_o((int)x, (int)y, (int)z)).facing = f;
        } else if (meta == 1) {
            ((TileEntitySnowGen)world.func_147438_o((int)x, (int)y, (int)z)).facing = ForgeDirection.getOrientation((int)f);
        } else if (meta == 2) {
            ((TileEntityCobbleGen)world.func_147438_o((int)x, (int)y, (int)z)).facing = ForgeDirection.getOrientation((int)f);
        } else if (meta == 3) {
            ((TileEntityCuttingTable)world.func_147438_o((int)x, (int)y, (int)z)).facing = f;
        } else if (meta == 4) {
            TileEntitySaunaStove tile = (TileEntitySaunaStove)world.func_147438_o(x, y, z);
            tile.prepareAreaCheck();
        } else if (meta == 5) {
            ((TileEntityLabelLibrary)world.func_147438_o((int)x, (int)y, (int)z)).facing = f;
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.func_72805_g(x, y, z) == 4) {
            return 8;
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        if (world.func_147438_o(x, y, z) instanceof TileEntitySaunaStove && ((TileEntitySaunaStove)world.func_147438_o((int)x, (int)y, (int)z)).tick > 0 && world.field_73012_v.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
            Thaumcraft.proxy.wispFX3(world, (double)((float)x + 0.5f), (double)((float)y + 0.875f), (double)((float)z + 0.5f), (double)((float)x + 0.3f + world.field_73012_v.nextFloat() * 0.4f), (double)((float)y + 0.5f), (double)((float)z + 0.3f + world.field_73012_v.nextFloat() * 0.4f), 0.5f, 4, true, -0.025f);
        }
    }

    public TileEntity func_149915_a(World world, int metadata) {
        switch (metadata) {
            case 0: {
                return new TileEntitySpinningWheel();
            }
            case 1: {
                return new TileEntitySnowGen();
            }
            case 2: {
                return new TileEntityCobbleGen();
            }
            case 3: {
                return new TileEntityCuttingTable();
            }
            case 4: {
                return new TileEntitySaunaStove();
            }
            case 5: {
                return new TileEntityLabelLibrary();
            }
        }
        return null;
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    public void func_149749_a(World world, int x, int y, int z, Block par5, int par6) {
        float f3;
        EntityItem entityitem;
        int k1;
        float f2;
        float f1;
        float f;
        ItemStack stack;
        int i;
        TileEntityWGBase tile;
        if (world.func_147438_o(x, y, z) instanceof TileEntitySpinningWheel) {
            tile = (TileEntitySpinningWheel)world.func_147438_o(x, y, z);
            for (i = 0; i < 4; ++i) {
                stack = ((TileEntitySpinningWheel)tile).func_70301_a(i);
                if (stack == null) continue;
                f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                while (stack.field_77994_a > 0) {
                    k1 = world.field_73012_v.nextInt(21) + 10;
                    if (k1 > stack.field_77994_a) {
                        k1 = stack.field_77994_a;
                    }
                    stack.field_77994_a -= k1;
                    entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.func_77973_b(), k1, stack.func_77960_j()));
                    f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                    if (stack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)stack.func_77978_p().func_74737_b());
                    }
                    world.func_72838_d((Entity)entityitem);
                }
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityCuttingTable) {
            tile = (TileEntityCuttingTable)world.func_147438_o(x, y, z);
            for (i = 0; i < ((TileEntityCuttingTable)tile).func_70302_i_(); ++i) {
                stack = ((TileEntityCuttingTable)tile).func_70301_a(i);
                if (stack == null) continue;
                f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                while (stack.field_77994_a > 0) {
                    k1 = world.field_73012_v.nextInt(21) + 10;
                    if (k1 > stack.field_77994_a) {
                        k1 = stack.field_77994_a;
                    }
                    stack.field_77994_a -= k1;
                    entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.func_77973_b(), k1, stack.func_77960_j()));
                    f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                    if (stack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)stack.func_77978_p().func_74737_b());
                    }
                    world.func_72838_d((Entity)entityitem);
                }
            }
        }
        if (world.func_147438_o(x, y, z) instanceof TileEntityLabelLibrary) {
            tile = (TileEntityLabelLibrary)world.func_147438_o(x, y, z);
            for (i = 0; i < ((TileEntityLabelLibrary)tile).func_70302_i_(); ++i) {
                stack = ((TileEntityLabelLibrary)tile).func_70301_a(i);
                if (stack == null) continue;
                f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                while (stack.field_77994_a > 0) {
                    k1 = world.field_73012_v.nextInt(21) + 10;
                    if (k1 > stack.field_77994_a) {
                        k1 = stack.field_77994_a;
                    }
                    stack.field_77994_a -= k1;
                    entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(stack.func_77973_b(), k1, stack.func_77960_j()));
                    f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * f3;
                    if (stack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)stack.func_77978_p().func_74737_b());
                    }
                    world.func_72838_d((Entity)entityitem);
                }
            }
        }
        super.func_149749_a(world, x, y, z, par5, par6);
    }

    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        ForgeDirection dir;
        if (md == 1) {
            ((TileEntitySnowGen)world.func_147438_o((int)x, (int)y, (int)z)).facing = dir = player.func_70093_af() ? ForgeDirection.getOrientation((int)side).getOpposite() : ForgeDirection.getOrientation((int)side);
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.3f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
        }
        if (md == 2) {
            ((TileEntityCobbleGen)world.func_147438_o((int)x, (int)y, (int)z)).facing = dir = player.func_70093_af() ? ForgeDirection.getOrientation((int)side).getOpposite() : ForgeDirection.getOrientation((int)side);
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.3f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
        }
        return 0;
    }

    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }
}

