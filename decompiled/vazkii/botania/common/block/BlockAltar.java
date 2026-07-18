/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidContainerItem
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockAltar
extends BlockModContainer
implements ILexiconable {
    Random random;

    protected BlockAltar() {
        super(Material.field_151576_e);
        this.func_149711_c(3.5f);
        this.func_149672_a(field_149769_e);
        this.func_149663_c("altar");
        float f = 0.125f;
        this.func_149676_a(f, f, f, 1.0f - f, 1.25f, 1.0f - f);
        this.random = new Random();
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 9; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        TileAltar tile;
        if (par5Entity instanceof EntityItem && (tile = (TileAltar)par1World.func_147438_o(par2, par3, par4)).collideEntityItem((EntityItem)par5Entity)) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileAltar tile = (TileAltar)world.func_147438_o(x, y, z);
        return tile.hasLava ? 15 : 0;
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        ItemStack stack = par5EntityPlayer.func_71045_bC();
        TileAltar tile = (TileAltar)par1World.func_147438_o(par2, par3, par4);
        if (par5EntityPlayer.func_70093_af()) {
            for (int i = tile.func_70302_i_() - 1; i >= 0; --i) {
                ItemStack stackAt = tile.func_70301_a(i);
                if (stackAt == null) continue;
                ItemStack copy = stackAt.func_77946_l();
                if (!par5EntityPlayer.field_71071_by.func_70441_a(copy)) {
                    par5EntityPlayer.func_71019_a(copy, false);
                }
                tile.func_70299_a(i, null);
                par1World.func_147453_f(par2, par3, par4, (Block)this);
                break;
            }
        } else if (tile.isEmpty() && tile.hasWater && stack == null) {
            tile.trySetLastRecipe(par5EntityPlayer);
        } else {
            if (stack != null && (this.isValidWaterContainer(stack) || stack.func_77973_b() == ModItems.waterRod && ManaItemHandler.requestManaExact(stack, par5EntityPlayer, 75, false))) {
                if (!tile.hasWater) {
                    if (stack.func_77973_b() == ModItems.waterRod) {
                        ManaItemHandler.requestManaExact(stack, par5EntityPlayer, 75, true);
                    } else if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                        par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, this.getContainer(stack));
                    }
                    tile.setWater(true);
                    par1World.func_147453_f(par2, par3, par4, (Block)this);
                }
                return true;
            }
            if (stack != null && stack.func_77973_b() == Items.field_151129_at) {
                if (!par5EntityPlayer.field_71075_bZ.field_75098_d) {
                    par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, this.getContainer(stack));
                }
                tile.setLava(true);
                tile.setWater(false);
                par1World.func_147453_f(par2, par3, par4, (Block)this);
                return true;
            }
            if (stack != null && stack.func_77973_b() == Items.field_151133_ar && (tile.hasWater || tile.hasLava) && !Botania.gardenOfGlassLoaded) {
                ItemStack bucket;
                ItemStack itemStack = bucket = tile.hasLava ? new ItemStack(Items.field_151129_at) : new ItemStack(Items.field_151131_as);
                if (stack.field_77994_a == 1) {
                    par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, bucket);
                } else {
                    if (!par5EntityPlayer.field_71071_by.func_70441_a(bucket)) {
                        par5EntityPlayer.func_71019_a(bucket, false);
                    }
                    --stack.field_77994_a;
                }
                if (tile.hasLava) {
                    tile.setLava(false);
                } else {
                    tile.setWater(false);
                }
                par1World.func_147453_f(par2, par3, par4, (Block)this);
                return true;
            }
        }
        return false;
    }

    public void func_149639_l(World world, int x, int y, int z) {
        TileEntity tile;
        if (world.field_73012_v.nextInt(20) == 1 && (tile = world.func_147438_o(x, y, z)) instanceof TileAltar) {
            TileAltar altar = (TileAltar)tile;
            if (!altar.hasLava && !altar.hasWater) {
                altar.setWater(true);
            }
            world.func_147453_f(x, y, z, (Block)this);
        }
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    private boolean isValidWaterContainer(ItemStack stack) {
        if (stack == null || stack.field_77994_a != 1) {
            return false;
        }
        if (stack.func_77973_b() == ModItems.waterBowl) {
            return true;
        }
        if (stack.func_77973_b() instanceof IFluidContainerItem) {
            FluidStack fluidStack = ((IFluidContainerItem)stack.func_77973_b()).getFluid(stack);
            return fluidStack != null && fluidStack.getFluid() == FluidRegistry.WATER && fluidStack.amount >= 1000;
        }
        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem((ItemStack)stack);
        return fluidStack != null && fluidStack.getFluid() == FluidRegistry.WATER && fluidStack.amount >= 1000;
    }

    private ItemStack getContainer(ItemStack stack) {
        if (stack.func_77973_b() == ModItems.waterBowl) {
            return new ItemStack(Items.field_151054_z);
        }
        if (stack.func_77973_b().hasContainerItem(stack)) {
            return stack.func_77973_b().getContainerItem(stack);
        }
        if (stack.func_77973_b() instanceof IFluidContainerItem) {
            ((IFluidContainerItem)stack.func_77973_b()).drain(stack, 1000, true);
            return stack;
        }
        return FluidContainerRegistry.drainFluidContainer((ItemStack)stack);
    }

    public IIcon func_149691_a(int par1, int par2) {
        return par2 == 0 ? Blocks.field_150347_e.func_149691_a(par1, par2) : ModFluffBlocks.biomeStoneA.func_149691_a(par1, par2 + 7);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idAltar;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileAltar();
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileSimpleInventory inv = (TileSimpleInventory)par1World.func_147438_o(par2, par3, par4);
        if (inv != null) {
            for (int j1 = 0; j1 < inv.func_70302_i_(); ++j1) {
                ItemStack itemstack = inv.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        TileAltar altar = (TileAltar)par1World.func_147438_o(par2, par3, par4);
        return altar.hasWater ? 15 : 0;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.apothecary;
    }
}

