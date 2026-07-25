/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTank
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.crafting.KettleRecipes;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class BlockKettle
extends BlockBaseContainer {
    static final int POWER_SOURCE_RADIUS = 16;

    public BlockKettle() {
        super(Material.field_151574_g, TileEntityKettle.class);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149777_j);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        float f = 0.0625f;
        return AxisAlignedBB.func_72330_a((double)((float)x + f), (double)y, (double)((float)z + f), (double)((float)(x + 1) - f), (double)((float)(y + 1) - f), (double)((float)(z + 1) - f));
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        return super.func_149633_g(par1World, par2, par3, par4);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        this.func_111046_k(par1World, par2, par3, par4);
    }

    private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
        if (!this.func_149718_j(par1World, par2, par3, par4)) {
            par1World.func_147468_f(par2, par3, par4);
            return false;
        }
        return true;
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        return true;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            par1World.func_72921_c(par2, par3, par4, 2, 2);
        }
        if (l == 1) {
            par1World.func_72921_c(par2, par3, par4, 5, 2);
        }
        if (l == 2) {
            par1World.func_72921_c(par2, par3, par4, 3, 2);
        }
        if (l == 3) {
            par1World.func_72921_c(par2, par3, par4, 4, 2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return false;
    }

    private static IPowerSource findNewPowerSource(World world, int posX, int posY, int posZ) {
        ArrayList<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
        return sources != null && sources.size() > 0 ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
    }

    private static ItemStack consumeItem(ItemStack stack) {
        if (stack.field_77994_a == 1) {
            if (stack.func_77973_b().hasContainerItem(stack)) {
                return stack.func_77973_b().getContainerItem(stack);
            }
            return null;
        }
        stack.func_77979_a(1);
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        TileEntityKettle tileEntity = (TileEntityKettle)world.func_147438_o(x, y, z);
        if (tileEntity != null) {
            double d0 = (float)x + 0.45f;
            double d1 = (float)y + 0.4f;
            double d2 = (float)z + 0.5f;
            if (tileEntity.isRuined()) {
                world.func_72869_a(ParticleEffect.LARGE_SMOKE.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
            } else if (tileEntity.isReady()) {
                world.func_72869_a(ParticleEffect.SLIME.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
                if (tileEntity.isPowered) {
                    world.func_72869_a(ParticleEffect.SPELL.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
                } else {
                    world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
                }
            } else if (tileEntity.isBrewing()) {
                world.func_72869_a(ParticleEffect.MOB_SPELL.toString(), d0, d1, d2, 0.0, 0.0, 0.0);
            }
        }
    }

    public boolean tryFillWith(World world, int x, int y, int z, FluidStack fluidStack) {
        if (world.field_72995_K) {
            return true;
        }
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile == null || !(tile instanceof TileEntityKettle)) {
            return false;
        }
        TileEntityKettle tank = (TileEntityKettle)tile;
        if (tank != null && tank.canFill(ForgeDirection.UNKNOWN, fluidStack.getFluid())) {
            int qty = tank.fill(ForgeDirection.UNKNOWN, fluidStack, true);
            fluidStack.amount -= qty;
            if (fluidStack.amount < 0) {
                fluidStack.amount = 0;
            }
            if (qty > 0) {
                world.func_147471_g(x, y, z);
                SoundEffect.WATER_SWIM.playAt(world, x, y, z);
            }
            return qty > 0;
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack current = player.field_71071_by.func_70448_g();
        if (current != null) {
            ItemStack filled;
            TileEntity tile = world.func_147438_o(posX, posY, posZ);
            if (tile == null || !(tile instanceof TileEntityKettle)) {
                return false;
            }
            TileEntityKettle tank = (TileEntityKettle)tile;
            if (current.func_77973_b() == Items.field_151069_bo && tank.isReady()) {
                if (KettleRecipes.instance().isBrewableBy(tank.furnaceItemStacks[6], player)) {
                    ItemStack itemstack1 = null;
                    try {
                        tank.setConsumeBottle(false);
                        itemstack1 = tank.func_70298_a(6, 1);
                    }
                    finally {
                        tank.setConsumeBottle(true);
                    }
                    double bonusChance = 0.0;
                    double bonusChance2 = 0.0;
                    if (player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.WITCH_HAT) {
                        bonusChance += 0.35;
                    } else if (player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.BABAS_HAT) {
                        bonusChance += 0.25;
                        bonusChance2 += 0.25;
                    }
                    if (!Witchery.Items.GENERIC.itemBrewOfRaising.isMatch(itemstack1) && Witchery.Items.WITCH_ROBES.isRobeWorn(player)) {
                        bonusChance += 0.35;
                    } else if (Witchery.Items.GENERIC.itemBrewOfRaising.isMatch(itemstack1) && Witchery.Items.NECROMANCERS_ROBES.isRobeWorn(player)) {
                        bonusChance += 0.35;
                    }
                    if (Familiar.hasActiveBrewMasteryFamiliar(player)) {
                        bonusChance += 0.05;
                        if (player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.BABAS_HAT) {
                            bonusChance2 += 0.05;
                        }
                    }
                    if (bonusChance > 0.0 && world.field_73012_v.nextDouble() <= bonusChance) {
                        itemstack1.field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
                    }
                    if (bonusChance2 > 0.0 && world.field_73012_v.nextDouble() <= bonusChance2) {
                        itemstack1.field_77994_a += KettleRecipes.instance().getHatBonus(itemstack1);
                    }
                    if (!world.field_72995_K) {
                        if (current.field_77994_a == 1) {
                            player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, itemstack1);
                            if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
                        } else {
                            if (!player.field_71071_by.func_70441_a(itemstack1)) {
                                world.func_72838_d((Entity)new EntityItem(world, (double)posX + 0.5, (double)posY + 1.5, (double)posZ + 0.5, itemstack1));
                            } else if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
                            --current.field_77994_a;
                            if (current.field_77994_a <= 0) {
                                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
                            }
                        }
                    }
                    SoundEffect.WATER_SWIM.playAtPlayer(world, player);
                }
                return true;
            }
            FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem((ItemStack)current);
            if (liquid != null) {
                if (tank.canFill(ForgeDirection.UNKNOWN, liquid.getFluid())) {
                    int qty = tank.fill(ForgeDirection.UNKNOWN, liquid, true);
                    if (qty != 0 && !player.field_71075_bZ.field_75098_d) {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, BlockKettle.consumeItem(current));
                    }
                    tank.reset(false);
                    SoundEffect.WATER_SWIM.playAtPlayer(world, player);
                }
                return true;
            }
            if (current.func_77973_b() == Witchery.Items.BREW_ENDLESS_WATER) {
                if (this.tryFillWith(world, posX, posY, posZ, new FluidStack(FluidRegistry.WATER, 1000))) {
                    current.func_77972_a(1, (EntityLivingBase)player);
                }
                return true;
            }
            FluidStack available = tank.getTankInfo((ForgeDirection)ForgeDirection.UNKNOWN)[0].fluid;
            if (available != null && (liquid = FluidContainerRegistry.getFluidForFilledItem((ItemStack)(filled = FluidContainerRegistry.fillFluidContainer((FluidStack)available, (ItemStack)current)))) != null) {
                if (!player.field_71075_bZ.field_75098_d) {
                    if (current.field_77994_a > 1) {
                        if (!player.field_71071_by.func_70441_a(filled)) {
                            return false;
                        }
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, BlockKettle.consumeItem(current));
                    } else {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, BlockKettle.consumeItem(current));
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, filled);
                    }
                }
                tank.drain(ForgeDirection.UNKNOWN, liquid.amount, true);
                tank.reset(false);
                SoundEffect.WATER_SWIM.playAtPlayer(world, player);
                return true;
            }
        }
        return false;
    }

    public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity) {
        TileEntityKettle tileEntity;
        if (!world.field_72995_K && entity instanceof EntityItem && (tileEntity = (TileEntityKettle)world.func_147438_o(posX, posY, posZ)) != null) {
            EntityItem itemEntity = (EntityItem)entity;
            if (itemEntity.func_92059_d().func_77973_b() == Items.field_151069_bo) {
                ItemStack stack = tileEntity.func_70301_a(7);
                if (stack == null) {
                    tileEntity.func_70299_a(7, itemEntity.func_92059_d());
                    itemEntity.func_70106_y();
                } else if (stack.field_77994_a + itemEntity.func_92059_d().field_77994_a <= tileEntity.func_70297_j_()) {
                    stack.field_77994_a += itemEntity.func_92059_d().field_77994_a;
                    tileEntity.func_70299_a(7, stack);
                    itemEntity.func_70106_y();
                }
            } else if (tileEntity.isFilled()) {
                boolean spaceFound = false;
                for (int i = 0; i < tileEntity.func_70302_i_() - 2; ++i) {
                    if (tileEntity.func_70301_a(i) != null) continue;
                    tileEntity.func_70299_a(i, itemEntity.func_92059_d());
                    spaceFound = true;
                    break;
                }
                if (!spaceFound && !tileEntity.isRuined()) {
                    tileEntity.setRuined();
                }
                itemEntity.func_70106_y();
                ParticleEffect.SPLASH.send(SoundEffect.WATER_SPLASH, world, (double)posX + 0.5, (double)posY + 0.2, (double)posZ + 0.5, 0.5, 0.5, 5);
            }
        }
    }

    public static class TileEntityKettle
    extends TileEntityBase
    implements ISidedInventory,
    IFluidHandler {
        private static final int RESULT_SLOT = 6;
        private static final int BOTTLE_SLOT = 7;
        private ItemStack[] furnaceItemStacks = new ItemStack[8];
        private boolean isRuined = false;
        private boolean isPowered = false;
        private int color;
        private static final int[] side_slots = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
        private int lastExtractionQuantity = 0;
        private boolean consumeBottles = true;
        private FluidTank tank = new FluidTank(1000);

        @Override
        public void func_145845_h() {
            super.func_145845_h();
            if (!this.field_145850_b.field_72995_K && !this.isRuined && this.ticks % 20L == 0L && this.isFilled() && (this.someFilled() || this.furnaceItemStacks[6] != null)) {
                boolean sendPacket = false;
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o() != Material.field_151581_o) {
                    this.isRuined = true;
                    this.color = 0;
                    this.furnaceItemStacks[6] = null;
                } else if (this.furnaceItemStacks[6] == null) {
                    if (this.allFilled()) {
                        KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(this.furnaceItemStacks, this.furnaceItemStacks.length - 2, false, this.field_145850_b);
                        if (recipe == null) {
                            this.color = 0;
                            this.isRuined = true;
                            this.furnaceItemStacks[6] = null;
                        } else {
                            this.color = recipe.getColor();
                            boolean wasPowered = this.isPowered;
                            float powerNeeded = recipe.getRequiredPower();
                            if (powerNeeded == 0.0f) {
                                this.isPowered = true;
                            } else {
                                IPowerSource powerSource = BlockKettle.findNewPowerSource(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
                                boolean bl = this.isPowered = powerSource != null && powerSource.consumePower(powerNeeded);
                            }
                            if (this.isPowered) {
                                this.furnaceItemStacks[6] = recipe.getOutput(null, true);
                                for (int i = 0; i < this.furnaceItemStacks.length - 2; ++i) {
                                    this.furnaceItemStacks[i] = null;
                                }
                            }
                            sendPacket = this.isPowered || wasPowered != this.isPowered;
                        }
                    } else {
                        KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().getResult(this.furnaceItemStacks, this.furnaceItemStacks.length - 2, true, this.field_145850_b);
                        if (recipe == null || recipe.getColor() == 0) {
                            this.color = 0;
                            this.isRuined = true;
                            this.furnaceItemStacks[6] = null;
                        } else if (recipe.getColor() != this.color) {
                            this.color = recipe.getColor();
                            sendPacket = true;
                        }
                        if (!this.isRuined) {
                            IPowerSource powerSource;
                            boolean wasPowered = this.isPowered;
                            float powerNeeded = recipe.getRequiredPower();
                            this.isPowered = powerNeeded == 0.0f ? true : (powerSource = BlockKettle.findNewPowerSource(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) != null && powerSource.getCurrentPower() >= powerNeeded;
                            boolean bl = sendPacket = wasPowered != this.isPowered;
                        }
                    }
                }
                if (this.isRuined || sendPacket) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
            }
        }

        public void reset(boolean flushWater) {
            if (!this.field_145850_b.field_72995_K) {
                Log.instance().debug(String.format("Reset kettle %s", flushWater ? "Flush" : "No Flush"));
                if (flushWater) {
                    FluidStack drained = this.tank.drain(this.tank.getFluidAmount(), true);
                    Log.instance().debug(String.format("Drained %d remaining %d of  %d", drained.amount, this.tank.getFluidAmount(), this.tank.getCapacity()));
                }
                this.isRuined = false;
                this.isPowered = false;
                for (int i = 0; i < this.furnaceItemStacks.length - 1; ++i) {
                    this.furnaceItemStacks[i] = null;
                }
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public boolean allFilled() {
            for (int i = 0; i < this.furnaceItemStacks.length - 2; ++i) {
                if (this.furnaceItemStacks[i] != null) continue;
                return false;
            }
            return true;
        }

        public boolean someFilled() {
            for (int i = 0; i < this.furnaceItemStacks.length - 2; ++i) {
                if (this.furnaceItemStacks[i] == null) continue;
                return true;
            }
            return false;
        }

        public int func_70302_i_() {
            return this.furnaceItemStacks.length;
        }

        public boolean func_94041_b(int slot, ItemStack itemstack) {
            Log.instance().debug(String.format("isItemValidForSlot(%d, %s)", slot, itemstack.toString()));
            ItemStack stackInSlot = this.func_70301_a(slot);
            if (slot == 6) {
                return true;
            }
            if (slot == 7) {
                return itemstack.func_77973_b() == Items.field_151069_bo && (stackInSlot != null ? stackInSlot.field_77994_a : 0) + itemstack.field_77994_a <= this.func_70297_j_();
            }
            return this.func_70301_a(6) == null && (stackInSlot != null ? stackInSlot.field_77994_a : 0) + itemstack.field_77994_a <= this.func_70297_j_();
        }

        public int[] func_94128_d(int var1) {
            return side_slots;
        }

        public boolean func_102007_a(int slot, ItemStack stack, int side) {
            ItemStack stackInSlot = this.func_70301_a(slot);
            if (slot == 6) {
                return false;
            }
            if (slot == 7) {
                return stack.func_77973_b() == Items.field_151069_bo && (stackInSlot != null ? stackInSlot.field_77994_a : 0) + stack.field_77994_a <= this.func_70297_j_();
            }
            return stack.func_77973_b() != Items.field_151069_bo && this.func_70301_a(6) == null && this.isFilled();
        }

        public boolean func_102008_b(int slot, ItemStack stack, int side) {
            boolean canExtract;
            Log.instance().debug(String.format("canExtract(%d, %s, %d)", slot, stack.toString(), side));
            ItemStack bottles = this.func_70301_a(7);
            boolean bl = canExtract = slot == 6 && this.isFilled() && this.isReady() && bottles != null && bottles.field_77994_a >= stack.field_77994_a;
            if (canExtract) {
                if (!KettleRecipes.instance().isBrewableBy(stack, null)) {
                    return false;
                }
                this.lastExtractionQuantity = stack.field_77994_a;
            }
            return canExtract;
        }

        public int getLiquidColor() {
            return this.color;
        }

        public ItemStack func_70301_a(int par1) {
            return this.furnaceItemStacks[par1];
        }

        public void func_70299_a(int slot, ItemStack stack) {
            Log.instance().debug("setInventorySlotContents");
            if (slot == 6 && this.consumeBottles) {
                ItemStack resultStack = this.func_70301_a(6);
                ItemStack bottleStack = this.func_70301_a(7);
                if (stack == null && resultStack != null && bottleStack != null) {
                    bottleStack.field_77994_a -= resultStack.field_77994_a;
                    if (bottleStack.field_77994_a <= 0) {
                        this.furnaceItemStacks[7] = null;
                    }
                } else if (stack != null && resultStack != null && bottleStack != null) {
                    int reduction = resultStack.field_77994_a - stack.field_77994_a;
                    if (reduction == 0) {
                        reduction = this.lastExtractionQuantity;
                    }
                    this.lastExtractionQuantity = 0;
                    Log.instance().debug(String.format("bottles; %d %s %s", reduction, stack.toString(), resultStack.toString()));
                    bottleStack.field_77994_a -= reduction;
                    if (bottleStack.field_77994_a <= 0) {
                        this.furnaceItemStacks[7] = null;
                    }
                }
            }
            this.furnaceItemStacks[slot] = stack;
            if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
                stack.field_77994_a = this.func_70297_j_();
            } else if (stack == null && slot == 6) {
                this.reset(true);
                return;
            }
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public void setConsumeBottle(boolean consume) {
            this.consumeBottles = consume;
        }

        public ItemStack func_70298_a(int slot, int quantity) {
            Log.instance().debug("decrStackSize");
            if (this.furnaceItemStacks[slot] != null) {
                ItemStack bottles = this.func_70301_a(7);
                if (this.consumeBottles && bottles != null) {
                    bottles.field_77994_a -= quantity;
                }
                if (bottles != null && bottles.field_77994_a <= 0) {
                    this.furnaceItemStacks[7] = null;
                }
                if (this.furnaceItemStacks[slot].field_77994_a <= quantity) {
                    ItemStack itemstack = this.furnaceItemStacks[slot];
                    this.furnaceItemStacks[slot] = null;
                    if (slot == 6) {
                        this.reset(true);
                    } else if (!this.field_145850_b.field_72995_K) {
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                    return itemstack;
                }
                ItemStack itemstack = this.furnaceItemStacks[slot].func_77979_a(quantity);
                if (this.furnaceItemStacks[slot].field_77994_a == 0) {
                    this.furnaceItemStacks[slot] = null;
                    if (slot == 6) {
                        this.reset(true);
                    } else if (!this.field_145850_b.field_72995_K) {
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                } else if (!this.field_145850_b.field_72995_K) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                return itemstack;
            }
            return null;
        }

        public ItemStack func_70304_b(int par1) {
            Log.instance().debug("getStackInSlotOnClosing");
            if (this.furnaceItemStacks[par1] != null) {
                ItemStack itemstack = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                if (par1 == 6) {
                    this.reset(true);
                } else if (!this.field_145850_b.field_72995_K) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                return itemstack;
            }
            return null;
        }

        public String func_145825_b() {
            return this.func_145838_q().func_149732_F();
        }

        public boolean func_145818_k_() {
            return true;
        }

        public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
            super.func_145839_a(par1NBTTagCompound);
            if (this.tank.getFluidAmount() > 0) {
                this.tank.drain(this.tank.getFluidAmount(), true);
            }
            this.tank.readFromNBT(par1NBTTagCompound);
            NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
            this.furnaceItemStacks = new ItemStack[this.func_70302_i_()];
            for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                byte b0 = nbttagcompound1.func_74771_c("Slot");
                if (b0 < 0 || b0 >= this.furnaceItemStacks.length) continue;
                this.furnaceItemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
            }
            this.isRuined = par1NBTTagCompound.func_74767_n("Ruined");
            this.isPowered = par1NBTTagCompound.func_74767_n("Powered");
            this.color = par1NBTTagCompound.func_74762_e("LiquidColor");
        }

        public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
            super.func_145841_b(par1NBTTagCompound);
            par1NBTTagCompound.func_74757_a("Ruined", this.isRuined);
            par1NBTTagCompound.func_74757_a("Powered", this.isPowered);
            par1NBTTagCompound.func_74768_a("LiquidColor", this.color);
            NBTTagList nbttaglist = new NBTTagList();
            for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
                if (this.furnaceItemStacks[i] == null) continue;
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.func_74774_a("Slot", (byte)i);
                this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
                nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
            }
            par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
            this.tank.writeToNBT(par1NBTTagCompound);
        }

        public int func_70297_j_() {
            return 64;
        }

        public void func_70295_k_() {
        }

        public void func_70305_f() {
        }

        public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
            return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            this.func_145839_a(packet.func_148857_g());
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        public boolean isFilled() {
            return this.tank.getFluidAmount() == this.tank.getCapacity();
        }

        public boolean isBrewing() {
            return this.isFilled() && this.someFilled() && !this.isRuined();
        }

        public boolean isReady() {
            return !this.isRuined() && this.furnaceItemStacks[6] != null;
        }

        public boolean isRuined() {
            return this.isRuined;
        }

        public void setRuined() {
            this.isRuined = true;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
            int result = this.tank.fill(resource, doFill);
            return result;
        }

        public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
            if (resource == null || !resource.isFluidEqual(this.tank.getFluid())) {
                return null;
            }
            return this.tank.drain(resource.amount, doDrain);
        }

        public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
            return this.tank.drain(maxDrain, doDrain);
        }

        public boolean canFill(ForgeDirection from, Fluid fluid) {
            if (fluid == null) {
                return false;
            }
            return fluid.getName().equals(FluidRegistry.WATER.getName());
        }

        public boolean canDrain(ForgeDirection from, Fluid fluid) {
            if (fluid == null) {
                return false;
            }
            return fluid.getName().equals(FluidRegistry.WATER.getName());
        }

        public FluidTankInfo[] getTankInfo(ForgeDirection from) {
            return new FluidTankInfo[]{this.tank.getInfo()};
        }

        public int bottleCount() {
            ItemStack stack = this.func_70301_a(7);
            return stack != null ? stack.field_77994_a : 0;
        }
    }
}

