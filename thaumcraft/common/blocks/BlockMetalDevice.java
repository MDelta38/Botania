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
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidContainerRegistry$FluidContainerData
 *  net.minecraftforge.fluids.FluidRegistry
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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.items.ItemShard;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileArcaneLamp;
import thaumcraft.common.tiles.TileArcaneLampFertility;
import thaumcraft.common.tiles.TileArcaneLampGrowth;
import thaumcraft.common.tiles.TileBrainbox;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileGrate;
import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
import thaumcraft.common.tiles.TileThaumatorium;
import thaumcraft.common.tiles.TileThaumatoriumTop;
import thaumcraft.common.tiles.TileVisRelay;

public class BlockMetalDevice
extends BlockContainer {
    public IIcon[] icon = new IIcon[23];
    public IIcon iconGlow;
    private int delay = 0;

    public BlockMetalDevice() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(17.0f);
        this.func_149672_a(Block.field_149777_j);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:metalbase");
        for (int a = 1; a <= 6; ++a) {
            this.icon[a] = ir.func_94245_a("thaumcraft:crucible" + a);
        }
        this.icon[7] = ir.func_94245_a("thaumcraft:goldbase");
        this.icon[8] = ir.func_94245_a("thaumcraft:grate");
        this.icon[9] = ir.func_94245_a("thaumcraft:grate_hatch");
        this.icon[10] = ir.func_94245_a("thaumcraft:lamp_side");
        this.icon[11] = ir.func_94245_a("thaumcraft:lamp_top");
        this.icon[12] = ir.func_94245_a("thaumcraft:lamp_grow_side");
        this.icon[13] = ir.func_94245_a("thaumcraft:lamp_grow_top");
        this.icon[14] = ir.func_94245_a("thaumcraft:lamp_grow_side_off");
        this.icon[15] = ir.func_94245_a("thaumcraft:lamp_grow_top_off");
        this.icon[16] = ir.func_94245_a("thaumcraft:alchemyblock");
        this.icon[17] = ir.func_94245_a("thaumcraft:brainbox");
        this.icon[18] = ir.func_94245_a("thaumcraft:lamp_fert_side");
        this.icon[19] = ir.func_94245_a("thaumcraft:lamp_fert_top");
        this.icon[20] = ir.func_94245_a("thaumcraft:lamp_fert_side_off");
        this.icon[21] = ir.func_94245_a("thaumcraft:lamp_fert_top_off");
        this.icon[22] = ir.func_94245_a("thaumcraft:alchemyblockadv");
        this.iconGlow = ir.func_94245_a("thaumcraft:animatedglow");
    }

    public IIcon func_149691_a(int i, int md) {
        if (md == 3) {
            return this.icon[22];
        }
        if (md == 7) {
            return this.icon[10];
        }
        if (md == 8) {
            return this.icon[12];
        }
        if (md == 10 || md == 9 || md == 11) {
            return this.icon[16];
        }
        if (md == 12) {
            return this.icon[17];
        }
        if (md == 13) {
            return this.icon[18];
        }
        if (md == 14 || md == 2) {
            return this.icon[0];
        }
        return md == 0 || md == 1 || md == 5 || md == 6 ? this.icon[0] : this.icon[7];
    }

    public IIcon func_149673_e(IBlockAccess iblockaccess, int i, int j, int k, int side) {
        int metadata = iblockaccess.func_72805_g(i, j, k);
        if (metadata == 5 || metadata == 6) {
            return this.icon[8];
        }
        if (metadata == 7) {
            if (side <= 1) {
                return this.icon[11];
            }
            return this.icon[10];
        }
        if (metadata == 8) {
            TileEntity te = iblockaccess.func_147438_o(i, j, k);
            if (te != null && te instanceof TileArcaneLampGrowth) {
                if (((TileArcaneLampGrowth)te).charges > 0) {
                    if (side <= 1) {
                        return this.icon[13];
                    }
                    return this.icon[12];
                }
                if (side <= 1) {
                    return this.icon[15];
                }
                return this.icon[14];
            }
        } else if (metadata == 13) {
            TileEntity te = iblockaccess.func_147438_o(i, j, k);
            if (te != null && te instanceof TileArcaneLampFertility) {
                if (((TileArcaneLampFertility)te).charges > 0) {
                    if (side <= 1) {
                        return this.icon[19];
                    }
                    return this.icon[18];
                }
                if (side <= 1) {
                    return this.icon[21];
                }
                return this.icon[20];
            }
        } else {
            if (metadata == 10 || metadata == 9 || metadata == 11) {
                return this.icon[16];
            }
            if (metadata == 12) {
                return this.icon[17];
            }
            if (metadata == 3) {
                return this.icon[22];
            }
        }
        if (side == 1) {
            return this.icon[1];
        }
        if (side == 0) {
            return this.icon[2];
        }
        return this.icon[3];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 13));
        par3List.add(new ItemStack(par1, 1, 9));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 12));
        par3List.add(new ItemStack(par1, 1, 14));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    public int func_149645_b() {
        return ConfigBlocks.blockMetalDeviceRI;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        int metadata;
        if (!world.field_72995_K && (metadata = world.func_72805_g(i, j, k)) == 0) {
            TileCrucible tile = (TileCrucible)world.func_147438_o(i, j, k);
            if (tile != null && entity instanceof EntityItem && !(entity instanceof EntitySpecialItem) && tile.heat > 150 && tile.tank.getFluidAmount() > 0) {
                tile.attemptSmelt((EntityItem)entity);
            } else {
                ++this.delay;
                if (this.delay < 10) {
                    return;
                }
                this.delay = 0;
                if (entity instanceof EntityLivingBase && tile != null && tile.heat > 150 && tile.tank.getFluidAmount() > 0) {
                    entity.func_70097_a(DamageSource.field_76372_a, 1.0f);
                    world.func_72908_a((double)i, (double)j, (double)k, "random.fizz", 0.4f, 2.0f + world.field_73012_v.nextFloat() * 0.4f);
                }
            }
        }
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 5 || metadata == 6) {
            this.func_149676_a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else if (metadata == 7 || metadata == 8 || metadata == 13) {
            this.func_149676_a(BlockRenderer.W4, BlockRenderer.W2, BlockRenderer.W4, BlockRenderer.W12, BlockRenderer.W14, BlockRenderer.W12);
        } else if (metadata == 10) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
        } else if (metadata == 11) {
            this.func_149676_a(0.0f, -1.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else if (metadata == 12) {
            this.func_149676_a(BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W13, BlockRenderer.W13);
        } else if (metadata == 2) {
            this.func_149676_a(BlockRenderer.W5, 0.5f, BlockRenderer.W5, BlockRenderer.W11, 1.0f, BlockRenderer.W11);
        } else if (metadata == 14) {
            TileEntity te = world.func_147438_o(i, j, k);
            if (te != null && te instanceof TileVisRelay) {
                switch (ForgeDirection.getOrientation((int)((TileVisRelay)te).orientation).getOpposite()) {
                    case UP: {
                        this.func_149676_a(BlockRenderer.W5, 0.5f, BlockRenderer.W5, BlockRenderer.W11, 1.0f, BlockRenderer.W11);
                        break;
                    }
                    case DOWN: {
                        this.func_149676_a(BlockRenderer.W5, 0.0f, BlockRenderer.W5, BlockRenderer.W11, 0.5f, BlockRenderer.W11);
                        break;
                    }
                    case EAST: {
                        this.func_149676_a(0.5f, BlockRenderer.W5, BlockRenderer.W5, 1.0f, BlockRenderer.W11, BlockRenderer.W11);
                        break;
                    }
                    case WEST: {
                        this.func_149676_a(0.0f, BlockRenderer.W5, BlockRenderer.W5, 0.5f, BlockRenderer.W11, BlockRenderer.W11);
                        break;
                    }
                    case SOUTH: {
                        this.func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.5f, BlockRenderer.W11, BlockRenderer.W11, 1.0f);
                        break;
                    }
                    case NORTH: {
                        this.func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.0f, BlockRenderer.W11, BlockRenderer.W11, 0.5f);
                    }
                }
            }
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
        super.func_149719_a(world, i, j, k);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        int metadata = world.func_72805_g(i, j, k);
        if (metadata == 0) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.3125f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            float f = 0.125f;
            this.func_149676_a(0.0f, 0.0f, 0.0f, f, 0.85f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.85f, f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            this.func_149676_a(1.0f - f, 0.0f, 0.0f, 1.0f, 0.85f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            this.func_149676_a(0.0f, 0.0f, 1.0f - f, 1.0f, 0.85f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (metadata == 2) {
            this.func_149676_a(BlockRenderer.W5, 0.5f, BlockRenderer.W5, BlockRenderer.W11, 1.0f, BlockRenderer.W11);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (metadata == 5) {
            if (par7Entity != null && !(par7Entity instanceof EntityItem)) {
                this.func_149676_a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
                super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            }
        } else if (metadata == 6) {
            this.func_149676_a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (metadata == 7 || metadata == 8 || metadata == 13) {
            this.func_149676_a(BlockRenderer.W4, BlockRenderer.W2, BlockRenderer.W4, BlockRenderer.W12, BlockRenderer.W14, BlockRenderer.W12);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (metadata == 12) {
            this.func_149676_a(BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W3, BlockRenderer.W13, BlockRenderer.W13, BlockRenderer.W13);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        } else if (metadata == 14) {
            TileEntity te = world.func_147438_o(i, j, k);
            if (te != null && te instanceof TileVisRelay) {
                switch (ForgeDirection.getOrientation((int)((TileVisRelay)te).orientation).getOpposite()) {
                    case UP: {
                        this.func_149676_a(BlockRenderer.W5, 0.5f, BlockRenderer.W5, BlockRenderer.W11, 1.0f, BlockRenderer.W11);
                        break;
                    }
                    case DOWN: {
                        this.func_149676_a(BlockRenderer.W5, 0.0f, BlockRenderer.W5, BlockRenderer.W11, 0.5f, BlockRenderer.W11);
                        break;
                    }
                    case EAST: {
                        this.func_149676_a(0.5f, BlockRenderer.W5, BlockRenderer.W5, 1.0f, BlockRenderer.W11, BlockRenderer.W11);
                        break;
                    }
                    case WEST: {
                        this.func_149676_a(0.0f, BlockRenderer.W5, BlockRenderer.W5, 0.5f, BlockRenderer.W11, BlockRenderer.W11);
                        break;
                    }
                    case SOUTH: {
                        this.func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.5f, BlockRenderer.W11, BlockRenderer.W11, 1.0f);
                        break;
                    }
                    case NORTH: {
                        this.func_149676_a(BlockRenderer.W5, BlockRenderer.W5, 0.0f, BlockRenderer.W11, BlockRenderer.W11, 0.5f);
                    }
                }
                super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            }
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        TileEntity te;
        if (r.nextInt(10) == 0 && (te = w.func_147438_o(i, j, k)) != null && te instanceof TileCrucible && ((TileCrucible)te).tank.getFluidAmount() > 0 && ((TileCrucible)te).heat > 150) {
            w.func_72980_b((double)i, (double)j, (double)k, "liquid.lavapop", 0.1f + r.nextFloat() * 0.1f, 1.2f + r.nextFloat() * 0.2f, false);
        }
    }

    public int func_149692_a(int metadata) {
        if (metadata == 6) {
            return 5;
        }
        if (metadata == 10 || metadata == 11) {
            return 9;
        }
        return metadata;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileCrucible();
        }
        if (metadata == 5) {
            return new TileGrate();
        }
        if (metadata == 6) {
            return new TileGrate();
        }
        if (metadata == 1) {
            return new TileAlembic();
        }
        if (metadata == 7) {
            return new TileArcaneLamp();
        }
        if (metadata == 8) {
            return new TileArcaneLampGrowth();
        }
        if (metadata == 10) {
            return new TileThaumatorium();
        }
        if (metadata == 11) {
            return new TileThaumatoriumTop();
        }
        if (metadata == 12) {
            return new TileBrainbox();
        }
        if (metadata == 13) {
            return new TileArcaneLampFertility();
        }
        if (metadata == 14) {
            return new TileVisRelay();
        }
        if (metadata == 2) {
            return new TileMagicWorkbenchCharger();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileThaumatorium) {
            return Container.func_94526_b((IInventory)((IInventory)te));
        }
        if (te != null && te instanceof TileAlembic) {
            float r = (float)((TileAlembic)te).amount / (float)((TileAlembic)te).maxAmount;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileAlembic)te).amount > 0 ? 1 : 0);
        }
        if (te != null && te instanceof TileCrucible) {
            float f = ((TileCrucible)te).aspects.visSize();
            ((TileCrucible)te).getClass();
            float r = f / 100.0f;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileCrucible)te).aspects.visSize() > 0 ? 1 : 0);
        }
        return 0;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public void func_149695_a(World world, int x, int y, int z, Block nbid) {
        TileEntity te = world.func_147438_o(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (te != null && te instanceof TileCrucible) {
            ((TileCrucible)te).getBellows();
        }
        if (!world.field_72995_K) {
            TileEntity tile;
            TileThaumcraft telb;
            if (te != null && te instanceof TileAlembic) {
                world.func_147471_g(x, y, z);
            } else if (te != null && te instanceof TileArcaneLamp) {
                telb = (TileArcaneLamp)te;
                if (world.func_147437_c(x + telb.facing.offsetX, y + telb.facing.offsetY, z + telb.facing.offsetZ)) {
                    this.func_149697_b(world, x, y, z, 7, 0);
                    world.func_147468_f(x, y, z);
                }
            } else if (te != null && te instanceof TileArcaneLampGrowth) {
                telb = (TileArcaneLampGrowth)te;
                if (world.func_147437_c(x + ((TileArcaneLampGrowth)telb).facing.offsetX, y + ((TileArcaneLampGrowth)telb).facing.offsetY, z + ((TileArcaneLampGrowth)telb).facing.offsetZ)) {
                    this.func_149697_b(world, x, y, z, 8, 0);
                    world.func_147468_f(x, y, z);
                }
            } else if (te != null && te instanceof TileBrainbox) {
                telb = (TileBrainbox)te;
                if (world.func_147437_c(x + ((TileBrainbox)telb).facing.offsetX, y + ((TileBrainbox)telb).facing.offsetY, z + ((TileBrainbox)telb).facing.offsetZ)) {
                    this.func_149697_b(world, x, y, z, 12, 0);
                    world.func_147468_f(x, y, z);
                }
            } else if (te != null && te instanceof TileVisRelay && md == 14) {
                telb = (TileVisRelay)te;
                if (world.func_147437_c(x + ForgeDirection.getOrientation((int)((TileVisRelay)telb).orientation).getOpposite().offsetX, y + ForgeDirection.getOrientation((int)((TileVisRelay)telb).orientation).getOpposite().offsetY, z + ForgeDirection.getOrientation((int)((TileVisRelay)telb).orientation).getOpposite().offsetZ)) {
                    this.func_149697_b(world, x, y, z, 14, 0);
                    world.func_147468_f(x, y, z);
                }
            } else if (md == 10) {
                if (world.func_147439_a(x, y + 1, z) != this || world.func_72805_g(x, y + 1, z) != 11 || world.func_147439_a(x, y - 1, z) != this || world.func_72805_g(x, y - 1, z) != 0) {
                    InventoryUtils.dropItems(world, x, y, z);
                    world.func_147468_f(x, y, z);
                    world.func_147465_d(x, y, z, (Block)this, 9, 3);
                    return;
                }
                tile = world.func_147438_o(x, y, z);
                if (tile != null && tile instanceof TileThaumatorium) {
                    ((TileThaumatorium)tile).getUpgrades();
                }
            } else if (md == 11) {
                if (world.func_147439_a(x, y - 1, z) != this || world.func_72805_g(x, y - 1, z) != 10) {
                    world.func_147468_f(x, y, z);
                    world.func_147465_d(x, y, z, (Block)this, 9, 3);
                    return;
                }
                tile = world.func_147438_o(x, y - 1, z);
                if (tile != null && tile instanceof TileThaumatorium) {
                    ((TileThaumatorium)tile).getUpgrades();
                }
            }
            boolean flag = world.func_72864_z(x, y, z);
            if (flag || nbid.func_149744_f()) {
                this.onPoweredBlockChange(world, x, y, z, flag);
            }
        }
        super.func_149695_a(world, x, y, z, nbid);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        InventoryUtils.dropItems(par1World, par2, par3, par4);
        TileEntity te = par1World.func_147438_o(par2, par3, par4);
        if (te != null && te instanceof TileCrucible) {
            ((TileCrucible)te).spillRemnants();
        } else if (te != null && te instanceof TileAlembic && ((TileAlembic)te).aspectFilter != null) {
            par1World.func_72838_d((Entity)new EntityItem(par1World, (double)((float)par2 + 0.5f), (double)((float)par3 + 0.5f), (double)((float)par4 + 0.5f), new ItemStack(ConfigItems.itemResource, 1, 13)));
        } else if (te != null && te instanceof TileArcaneLamp) {
            ((TileArcaneLamp)te).removeLights();
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        TileEntity te;
        FluidStack fs;
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0 && !world.field_72995_K && (fs = FluidContainerRegistry.getFluidForFilledItem((ItemStack)player.field_71071_by.func_70448_g())) != null && fs.isFluidEqual(new FluidStack(FluidRegistry.WATER, 1000))) {
            int volume = fs.amount;
            TileEntity te2 = world.func_147438_o(x, y, z);
            if (te2 != null && te2 instanceof TileCrucible) {
                TileCrucible tile = (TileCrucible)te2;
                if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
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
                    te2.func_70296_d();
                    world.func_147471_g(x, y, z);
                    world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "game.neutral.swim", 0.33f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f);
                } else {
                    return true;
                }
            }
        }
        if (metadata == 1 && !world.field_72995_K && !player.func_70093_af() && player.func_70694_bm() == null && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileAlembic) {
            TileAlembic tile = (TileAlembic)te;
            String msg = "";
            if (tile.aspect == null || tile.amount == 0) {
                msg = StatCollector.func_74838_a((String)"tile.alembic.msg.1");
            } else if ((double)tile.amount < (double)tile.maxAmount * 0.4) {
                msg = StatCollector.func_74838_a((String)"tile.alembic.msg.2");
            } else if ((double)tile.amount < (double)tile.maxAmount * 0.8) {
                msg = StatCollector.func_74838_a((String)"tile.alembic.msg.3");
            } else if (tile.amount < tile.maxAmount) {
                msg = StatCollector.func_74838_a((String)"tile.alembic.msg.4");
            } else if (tile.amount == tile.maxAmount) {
                msg = StatCollector.func_74838_a((String)"tile.alembic.msg.5");
            }
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a73" + msg, new Object[0]));
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:alembicknock", 0.2f, 1.0f);
        }
        if (metadata == 1 && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileAlembic) {
            if (player.func_70093_af() && ((TileAlembic)te).aspectFilter != null) {
                ((TileAlembic)te).aspectFilter = null;
                world.func_147471_g(x, y, z);
                te.func_70296_d();
                if (world.field_72995_K) {
                    world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:page", 1.0f, 1.1f, false);
                } else {
                    ForgeDirection fd = ForgeDirection.getOrientation((int)side);
                    world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f + (float)fd.offsetX / 3.0f), (double)((float)y + 0.5f), (double)((float)z + 0.5f + (float)fd.offsetZ / 3.0f), new ItemStack(ConfigItems.itemResource, 1, 13)));
                }
                return true;
            }
            if (player.func_70093_af() && player.func_70694_bm() == null) {
                ((TileAlembic)te).amount = 0;
                ((TileAlembic)te).aspect = null;
                if (world.field_72995_K) {
                    world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:alembicknock", 0.2f, 1.0f, false);
                    world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f, false);
                }
            } else {
                if (player.func_70694_bm() != null && ((TileAlembic)te).aspectFilter == null && player.func_70694_bm().func_77973_b() == ConfigItems.itemResource && player.func_70694_bm().func_77960_j() == 13) {
                    if (((TileAlembic)te).amount == 0 && ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) == null) {
                        return true;
                    }
                    if (((TileAlembic)te).amount == 0 && ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
                        ((TileAlembic)te).aspect = ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
                    }
                    --player.func_70694_bm().field_77994_a;
                    ((TileAlembic)te).aspectFilter = ((TileAlembic)te).aspect;
                    world.func_147471_g(x, y, z);
                    te.func_70296_d();
                    if (world.field_72995_K) {
                        world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:page", 1.0f, 0.9f, false);
                    }
                    return true;
                }
                if (player.func_70694_bm() != null && ((TileAlembic)te).amount > 0 && (player.func_70694_bm().func_77973_b() == ConfigItems.itemJarFilled || player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 0)) || player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 3)))) {
                    boolean doit = false;
                    ItemStack drop = null;
                    if (player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 0)) || player.func_70694_bm().func_77969_a(new ItemStack(ConfigBlocks.blockJar, 1, 3))) {
                        drop = new ItemStack(ConfigItems.itemJarFilled, 1, player.func_70694_bm().func_77960_j());
                        doit = true;
                        ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, new AspectList().add(((TileAlembic)te).aspect, ((TileAlembic)te).amount));
                        ((TileAlembic)te).amount = 0;
                        ((TileAlembic)te).aspect = null;
                        --player.func_70694_bm().field_77994_a;
                        if (!player.field_71071_by.func_70441_a(drop) && !world.field_72995_K) {
                            world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, drop));
                        }
                    } else {
                        drop = player.func_70694_bm();
                        if (!(((ItemJarFilled)drop.func_77973_b()).getAspects(drop) != null && ((ItemJarFilled)drop.func_77973_b()).getAspects(drop).visSize() != 0 && ((ItemJarFilled)drop.func_77973_b()).getAspects(drop).getAmount(((TileAlembic)te).aspect) <= 0 || ((ItemJarFilled)drop.func_77973_b()).getFilter(drop) != null && ((ItemJarFilled)drop.func_77973_b()).getFilter(drop) != ((TileAlembic)te).aspect)) {
                            int amount = Math.min(((ItemJarFilled)drop.func_77973_b()).getAspects(drop) == null ? 64 : 64 - ((ItemJarFilled)drop.func_77973_b()).getAspects(drop).visSize(), ((TileAlembic)te).amount);
                            if (drop.func_77960_j() == 3) {
                                amount = ((TileAlembic)te).amount;
                            }
                            if (amount > 0) {
                                ((TileAlembic)te).amount -= amount;
                                AspectList as = ((ItemJarFilled)drop.func_77973_b()).getAspects(drop);
                                if (as == null) {
                                    as = new AspectList();
                                }
                                as.add(((TileAlembic)te).aspect, amount);
                                if (as.getAmount(((TileAlembic)te).aspect) > 64) {
                                    int q = as.getAmount(((TileAlembic)te).aspect) - 64;
                                    as.reduce(((TileAlembic)te).aspect, q);
                                }
                                ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, as);
                                if (((TileAlembic)te).amount <= 0) {
                                    ((TileAlembic)te).aspect = null;
                                }
                                doit = true;
                                player.func_70062_b(0, drop);
                            }
                        }
                    }
                    if (doit) {
                        te.func_70296_d();
                        world.func_147471_g(x, y, z);
                        if (world.field_72995_K) {
                            world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f, false);
                        }
                    }
                    return true;
                }
            }
        }
        if (metadata == 5) {
            world.func_72921_c(x, y, z, 6, 2);
            world.func_72889_a(player, 1003, x, y, z, 0);
            return true;
        }
        if (metadata == 6) {
            world.func_72921_c(x, y, z, 5, 2);
            world.func_72889_a(player, 1003, x, y, z, 0);
            return true;
        }
        if (world.field_72995_K) {
            return true;
        }
        if (metadata == 10 && (te = world.func_147438_o(x, y, z)) instanceof TileThaumatorium && !player.func_70093_af()) {
            player.openGui((Object)Thaumcraft.instance, 3, world, x, y, z);
            return true;
        }
        if (metadata == 11 && (te = world.func_147438_o(x, y - 1, z)) instanceof TileThaumatorium && !player.func_70093_af()) {
            player.openGui((Object)Thaumcraft.instance, 3, world, x, y - 1, z);
            return true;
        }
        if ((metadata == 14 || metadata == 2) && !world.field_72995_K && !player.func_70093_af() && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemShard && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileVisRelay) {
            TileVisRelay tile = (TileVisRelay)te;
            byte c = (byte)player.func_70694_bm().func_77960_j();
            tile.color = c == tile.color || c == 6 ? (byte)-1 : c;
            tile.removeThisNode();
            tile.nodeRefresh = true;
            tile.func_70296_d();
            world.func_147471_g(x, y, z);
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:crystal", 0.2f, 1.0f);
        }
        return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
    }

    public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean flag) {
        int l = par1World.func_72805_g(par2, par3, par4);
        if (l == 5 && flag) {
            par1World.func_72921_c(par2, par3, par4, 6, 2);
            par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
        } else if (l == 6 && !flag) {
            par1World.func_72921_c(par2, par3, par4, 5, 2);
            par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
        }
    }

    public void func_149689_a(World world, int par2, int par3, int par4, EntityLivingBase ent, ItemStack stack) {
        TileEntity tile;
        int l = MathHelper.func_76128_c((double)((double)(ent.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (stack.func_77960_j() == 1 && (tile = world.func_147438_o(par2, par3, par4)) instanceof TileAlembic) {
            if (l == 0) {
                ((TileAlembic)tile).facing = 2;
            }
            if (l == 1) {
                ((TileAlembic)tile).facing = 5;
            }
            if (l == 2) {
                ((TileAlembic)tile).facing = 3;
            }
            if (l == 3) {
                ((TileAlembic)tile).facing = 4;
            }
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileEntity te;
        int md = world.func_72805_g(x, y, z);
        if (md == 3) {
            return 11;
        }
        if (md == 7) {
            return 15;
        }
        if (md == 8) {
            TileEntity te2 = world.func_147438_o(x, y, z);
            if (te2 != null && te2 instanceof TileArcaneLampGrowth) {
                if (((TileArcaneLampGrowth)te2).charges > 0) {
                    return 15;
                }
                return 8;
            }
        } else if (md == 13) {
            TileEntity te3 = world.func_147438_o(x, y, z);
            if (te3 != null && te3 instanceof TileArcaneLampFertility) {
                if (((TileArcaneLampFertility)te3).charges > 0) {
                    return 15;
                }
                return 8;
            }
        } else if (md == 14 && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileVisRelay) {
            if (VisNetHandler.isNodeValid(((TileVisRelay)te).getParent())) {
                return 10;
            }
            return 2;
        }
        return super.getLightValue(world, x, y, z);
    }
}

