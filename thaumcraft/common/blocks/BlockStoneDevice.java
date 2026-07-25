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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidContainerRegistry$FluidContainerData
 *  net.minecraftforge.fluids.FluidStack
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileAlchemyFurnace;
import thaumcraft.common.tiles.TileFluxScrubber;
import thaumcraft.common.tiles.TileFocalManipulator;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumcraft.common.tiles.TileNodeConverter;
import thaumcraft.common.tiles.TileNodeStabilizer;
import thaumcraft.common.tiles.TilePedestal;
import thaumcraft.common.tiles.TileSpa;
import thaumcraft.common.tiles.TileWandPedestal;

public class BlockStoneDevice
extends BlockContainer {
    public IIcon[] iconFurnace = new IIcon[5];
    public IIcon[] iconPedestal = new IIcon[2];
    public IIcon[] iconWandPedestal = new IIcon[2];
    public IIcon[] iconWandPedestalFocus = new IIcon[3];
    public IIcon[] iconSpa = new IIcon[2];

    public BlockStoneDevice() {
        super(Material.field_151576_e);
        this.func_149711_c(3.0f);
        this.func_149752_b(25.0f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconPedestal[0] = ir.func_94245_a("thaumcraft:pedestal_side");
        this.iconPedestal[1] = ir.func_94245_a("thaumcraft:pedestal_top");
        this.iconWandPedestal[0] = ir.func_94245_a("thaumcraft:wandpedestal_side");
        this.iconWandPedestal[1] = ir.func_94245_a("thaumcraft:wandpedestal_top");
        this.iconWandPedestalFocus[0] = ir.func_94245_a("thaumcraft:wandpedestal_focus_side");
        this.iconWandPedestalFocus[1] = ir.func_94245_a("thaumcraft:wandpedestal_focus_top");
        this.iconWandPedestalFocus[2] = ir.func_94245_a("thaumcraft:wandpedestal_focus_bot");
        this.iconFurnace[0] = ir.func_94245_a("thaumcraft:al_furnace_side");
        this.iconFurnace[1] = ir.func_94245_a("thaumcraft:al_furnace_top");
        this.iconFurnace[2] = ir.func_94245_a("thaumcraft:al_furnace_front_off");
        this.iconFurnace[3] = ir.func_94245_a("thaumcraft:al_furnace_front_on");
        this.iconFurnace[4] = ir.func_94245_a("thaumcraft:al_furnace_top_filled");
        this.iconSpa[0] = ir.func_94245_a("thaumcraft:spa_side");
        this.iconSpa[1] = ir.func_94245_a("thaumcraft:spa_top");
    }

    public int func_149645_b() {
        return ConfigBlocks.blockStoneDeviceRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public IIcon func_149691_a(int side, int md) {
        if (md == 0) {
            if (side == 1) {
                return this.iconFurnace[1];
            }
            if (side > 1) {
                return this.iconFurnace[2];
            }
        } else if (md == 1) {
            if (side <= 1) {
                return this.iconPedestal[1];
            }
            if (side > 1) {
                return this.iconPedestal[0];
            }
        } else if (md == 5) {
            if (side == 0) {
                return this.iconPedestal[1];
            }
            if (side == 1) {
                return this.iconWandPedestal[1];
            }
            if (side > 1) {
                return this.iconWandPedestal[0];
            }
        } else if (md == 8) {
            if (side == 0) {
                return this.iconWandPedestalFocus[2];
            }
            if (side == 1) {
                return this.iconWandPedestalFocus[1];
            }
            if (side > 1) {
                return this.iconWandPedestalFocus[0];
            }
        } else if (md == 12) {
            if (side == 0) {
                return this.iconPedestal[1];
            }
            if (side == 1) {
                return this.iconSpa[1];
            }
            if (side > 1) {
                return this.iconSpa[0];
            }
        }
        return this.iconPedestal[1];
    }

    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        int metadata = iblockaccess.func_72805_g(i, j, k);
        if (metadata == 0) {
            TileEntity te = iblockaccess.func_147438_o(i, j, k);
            if (side == 1) {
                if (te != null && te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace)te).vis > 0) {
                    return this.iconFurnace[4];
                }
                return this.iconFurnace[1];
            }
            if (side > 1) {
                if (te != null && te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace)te).isBurning()) {
                    return this.iconFurnace[3];
                }
                return this.iconFurnace[2];
            }
        } else if (metadata == 1 || metadata == 5 || metadata == 8 || metadata == 12) {
            return super.func_149673_e(iblockaccess, i, j, k, side);
        }
        return this.iconFurnace[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
        par3List.add(new ItemStack(par1, 1, 10));
        par3List.add(new ItemStack(par1, 1, 11));
        par3List.add(new ItemStack(par1, 1, 12));
        par3List.add(new ItemStack(par1, 1, 13));
        par3List.add(new ItemStack(par1, 1, 14));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        TileEntity te = w.func_147438_o(i, j, k);
        if (te != null && te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace)te).isBurning()) {
            float f = (float)i + 0.5f;
            float f1 = (float)j + 0.2f + r.nextFloat() * 5.0f / 16.0f;
            float f2 = (float)k + 0.5f;
            float f3 = 0.52f;
            float f4 = r.nextFloat() * 0.5f - 0.25f;
            w.func_72869_a("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            w.func_72869_a("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            w.func_72869_a("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            w.func_72869_a("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
            w.func_72869_a("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
            w.func_72869_a("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
            w.func_72869_a("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
            w.func_72869_a("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileAlchemyFurnace && ((TileAlchemyFurnace)te).isBurning()) {
                return 12;
            }
        } else if (meta == 2) {
            return 10;
        }
        return super.getLightValue(world, x, y, z);
    }

    public int func_149692_a(int metadata) {
        return metadata == 3 ? 7 : (metadata == 4 ? 6 : metadata);
    }

    public Item func_149650_a(int metadata, Random par2Random, int par3) {
        return metadata == 3 || metadata == 4 ? Item.func_150898_a((Block)ConfigBlocks.blockCosmeticSolid) : super.func_149650_a(metadata, par2Random, par3);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileAlchemyFurnace();
        }
        if (metadata == 1) {
            return new TilePedestal();
        }
        if (metadata == 2) {
            return new TileInfusionMatrix();
        }
        if (metadata == 3) {
            return new TileInfusionPillar();
        }
        if (metadata == 5) {
            return new TileWandPedestal();
        }
        if (metadata == 9 || metadata == 10) {
            return new TileNodeStabilizer();
        }
        if (metadata == 11) {
            return new TileNodeConverter();
        }
        if (metadata == 12) {
            return new TileSpa();
        }
        if (metadata == 13) {
            return new TileFocalManipulator();
        }
        if (metadata == 14) {
            return new TileFluxScrubber();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && (te instanceof TilePedestal || te instanceof TileAlchemyFurnace)) {
            return Container.func_94526_b((IInventory)((IInventory)te));
        }
        if (te != null && te instanceof TileWandPedestal && ((TileWandPedestal)te).getAspects() != null && ((TileWandPedestal)te).func_70301_a(0) != null && ((TileWandPedestal)te).func_70301_a(0).func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)((TileWandPedestal)te).func_70301_a(0).func_77973_b();
            float r = (float)wand.getAllVis(((TileWandPedestal)te).func_70301_a(0)).visSize() / ((float)wand.getMaxVis(((TileWandPedestal)te).func_70301_a(0)) * 6.0f);
            return MathHelper.func_76141_d((float)(r * 14.0f)) + 1;
        }
        return 0;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        InventoryUtils.dropItems(par1World, par2, par3, par4);
        TileEntity tileEntity = par1World.func_147438_o(par2, par3, par4);
        if (tileEntity != null && tileEntity instanceof TileInfusionMatrix && ((TileInfusionMatrix)tileEntity).crafting) {
            par1World.func_72876_a(null, (double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, 2.0f, true);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileAlchemyFurnace) {
            ((TileAlchemyFurnace)te).getBellows();
        } else if (te != null && te instanceof TileNodeConverter) {
            ((TileNodeConverter)te).checkStatus();
        } else {
            int metadata = world.func_72805_g(x, y, z);
            if (metadata == 1) {
                if (!world.func_147437_c(x, y + 1, z)) {
                    InventoryUtils.dropItems(world, x, y, z);
                }
            } else if (metadata == 5) {
                if (!(world.func_147437_c(x, y + 1, z) || world.func_147439_a(x, y + 1, z) == this && world.func_72805_g(x, y + 1, z) == 8)) {
                    InventoryUtils.dropItems(world, x, y, z);
                }
            } else if (metadata == 3) {
                if (world.func_147439_a(x, y + 1, z) != this || world.func_72805_g(x, y + 1, z) != 4) {
                    this.func_149697_b(world, x, y, z, metadata, 0);
                    world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
                }
            } else if (metadata == 4 && (world.func_147439_a(x, y - 1, z) != this || world.func_72805_g(x, y - 1, z) != 3)) {
                this.func_149697_b(world, x, y, z, metadata, 0);
                world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
            }
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        TileThaumcraft ped;
        if (world.field_72995_K) {
            return true;
        }
        int metadata = world.func_72805_g(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (metadata == 0 && tileEntity instanceof TileAlchemyFurnace && !player.func_70093_af()) {
            player.openGui((Object)Thaumcraft.instance, 9, world, x, y, z);
            return true;
        }
        if (metadata == 1 && tileEntity instanceof TilePedestal) {
            ped = (TilePedestal)tileEntity;
            if (((TilePedestal)ped).func_70301_a(0) != null) {
                InventoryUtils.dropItemsAtEntity(world, x, y, z, (Entity)player);
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.5f);
                return true;
            }
            if (player.func_71045_bC() != null) {
                ItemStack i = player.func_71045_bC().func_77946_l();
                i.field_77994_a = 1;
                ((TilePedestal)ped).func_70299_a(0, i);
                --player.func_71045_bC().field_77994_a;
                if (player.func_71045_bC().field_77994_a == 0) {
                    player.func_70062_b(0, null);
                }
                player.field_71071_by.func_70296_d();
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.6f);
                return true;
            }
        }
        if (metadata == 8) {
            metadata = world.func_72805_g(x, --y, z);
            tileEntity = world.func_147438_o(x, y, z);
        }
        if (metadata == 5 && tileEntity instanceof TileWandPedestal) {
            if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77969_a(new ItemStack((Block)this, 1, 8))) {
                return false;
            }
            ped = (TileWandPedestal)tileEntity;
            if (((TileWandPedestal)ped).func_70301_a(0) != null) {
                InventoryUtils.dropItemsAtEntity(world, x, y, z, (Entity)player);
                world.func_147471_g(x, y, z);
                ped.func_70296_d();
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.5f);
                return true;
            }
            if (player.func_71045_bC() != null && (player.func_71045_bC().func_77973_b() instanceof ItemWandCasting || player.func_71045_bC().func_77973_b() instanceof ItemAmuletVis)) {
                ItemStack i = player.func_71045_bC().func_77946_l();
                i.field_77994_a = 1;
                ((TileWandPedestal)ped).func_70299_a(0, i);
                --player.func_71045_bC().field_77994_a;
                if (player.func_71045_bC().field_77994_a == 0) {
                    player.func_70062_b(0, null);
                }
                player.field_71071_by.func_70296_d();
                world.func_147471_g(x, y, z);
                ped.func_70296_d();
                world.func_72908_a((double)x, (double)y, (double)z, "random.pop", 0.2f, ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.7f + 1.0f) * 1.6f);
                return true;
            }
        }
        if (metadata == 12 && tileEntity instanceof TileSpa && !player.func_70093_af()) {
            FluidStack fs = FluidContainerRegistry.getFluidForFilledItem((ItemStack)player.field_71071_by.func_70448_g());
            if (fs != null) {
                int volume = fs.amount;
                TileSpa tile = (TileSpa)tileEntity;
                if (tile.tank.getFluidAmount() < tile.tank.getCapacity() && (tile.tank.getFluid() == null || tile.tank.getFluid().isFluidEqual(fs))) {
                    FluidContainerRegistry.FluidContainerData[] fcs;
                    tile.fill(ForgeDirection.UNKNOWN, FluidContainerRegistry.getFluidForFilledItem((ItemStack)player.field_71071_by.func_70448_g()), true);
                    ItemStack emptyContainer = null;
                    for (FluidContainerRegistry.FluidContainerData fcd : fcs = FluidContainerRegistry.getRegisteredFluidContainerData()) {
                        if (!fcd.filledContainer.func_77969_a(player.field_71071_by.func_70448_g())) continue;
                        emptyContainer = fcd.emptyContainer.func_77946_l();
                    }
                    player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
                    if (emptyContainer != null && !player.field_71071_by.func_70441_a(emptyContainer)) {
                        player.func_71019_a(emptyContainer, false);
                    }
                    player.field_71069_bz.func_75142_b();
                    tile.func_70296_d();
                    world.func_147471_g(x, y, z);
                    world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "game.neutral.swim", 0.33f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f);
                }
            } else {
                player.openGui((Object)Thaumcraft.instance, 19, world, x, y, z);
            }
            return true;
        }
        if (metadata == 13 && tileEntity instanceof TileFocalManipulator && !player.func_70093_af()) {
            if (ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "FOCALMANIPULATION")) {
                player.openGui((Object)Thaumcraft.instance, 20, world, x, y, z);
            } else if (!world.field_72995_K) {
                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"tc.researchmissing")));
            }
            return true;
        }
        return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 5) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            this.func_149676_a(0.25f, 0.5f, 0.25f, 0.75f, 1.0f, 0.75f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            this.func_149676_a(0.125f, 0.25f, 0.125f, 0.875f, 0.5f, 0.875f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        }
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 1) {
            this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 0.99f, 0.75f);
        } else if (metadata == 5) {
            this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f);
        } else if (metadata == 3) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        } else if (metadata == 4) {
            this.func_149676_a(0.0f, -1.0f, 0.0f, 1.0f, -0.5f, 1.0f);
        } else if (metadata == 8) {
            this.func_149676_a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.4375f, 0.9375f);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
        super.func_149719_a(world, i, j, k);
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        if (par5 == 1) {
            if (par1World.field_72995_K) {
                Thaumcraft.proxy.blockSparkle(par1World, par2, par3, par4, 11960575, 2);
                par1World.func_72926_e(2001, par2, par3, par4, Block.func_149682_b((Block)Blocks.field_150417_aV) + 0);
            }
            return true;
        }
        return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 11 && side == ForgeDirection.UP) {
            return true;
        }
        if (meta == 12) {
            return true;
        }
        return super.isSideSolid(world, x, y, z, side);
    }
}

