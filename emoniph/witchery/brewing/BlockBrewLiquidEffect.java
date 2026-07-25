/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidBlock
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.TileEntityBrewFluid;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.google.common.collect.Maps;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;

public class BlockBrewLiquidEffect
extends BlockBaseContainer
implements IFluidBlock {
    protected static final Map<Block, Boolean> defaultDisplacements = Maps.newHashMap();
    protected Map<Block, Boolean> displacements = Maps.newHashMap();
    protected int quantaPerBlock = 6;
    protected float quantaPerBlockFloat = 8.0f;
    protected int density = 1;
    protected int densityDir = -1;
    protected int temperature = 295;
    protected int tickRate = 20;
    protected int renderPass = 1;
    protected int maxScaledLight = 0;
    protected boolean[] isOptimalFlowDirection = new boolean[4];
    protected int[] flowCost = new int[4];
    protected FluidStack stack;
    protected final String fluidName;
    @SideOnly(value=Side.CLIENT)
    protected IIcon[] icons;

    public BlockBrewLiquidEffect() {
        super(Material.field_151586_h, TileEntityBrewFluid.class);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149675_a(true);
        this.registerWithCreateTab = false;
        this.func_149649_H();
        Fluid fluid = Witchery.Fluids.BREW_LIQUID;
        this.fluidName = fluid.getName();
        this.density = fluid.getDensity();
        this.temperature = fluid.getTemperature();
        this.maxScaledLight = fluid.getLuminosity();
        this.tickRate = fluid.getViscosity() / 200;
        this.densityDir = fluid.getDensity() > 0 ? -1 : 1;
        fluid.setBlock((Block)this);
        this.stack = new FluidStack(fluid, 1000);
        this.displacements.putAll(defaultDisplacements);
    }

    public int func_149720_d(IBlockAccess world, int x, int y, int z) {
        TileEntityBrewFluid fluid = BlockUtil.getTileEntity(world, x, y, z, TileEntityBrewFluid.class);
        if (fluid != null) {
            return fluid.color;
        }
        return 68;
    }

    public BlockBrewLiquidEffect setFluidStack(FluidStack stack) {
        this.stack = stack;
        return this;
    }

    public BlockBrewLiquidEffect setFluidStackAmount(int amount) {
        this.stack.amount = amount;
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return side != 0 && side != 1 ? this.icons[1] : this.icons[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.icons = new IIcon[]{iconRegister.func_94245_a(this.func_149641_N() + "_still"), iconRegister.func_94245_a(this.func_149641_N() + "_flow")};
    }

    public BlockBrewLiquidEffect setQuantaPerBlock(int quantaPerBlock) {
        if (quantaPerBlock > 16 || quantaPerBlock < 1) {
            quantaPerBlock = 8;
        }
        this.quantaPerBlock = quantaPerBlock;
        this.quantaPerBlockFloat = quantaPerBlock;
        return this;
    }

    public BlockBrewLiquidEffect setDensity(int density) {
        if (density == 0) {
            density = 1;
        }
        this.density = density;
        this.densityDir = density > 0 ? -1 : 1;
        return this;
    }

    public BlockBrewLiquidEffect setTemperature(int temperature) {
        this.temperature = temperature;
        return this;
    }

    public BlockBrewLiquidEffect setTickRate(int tickRate) {
        if (tickRate <= 0) {
            tickRate = 20;
        }
        this.tickRate = tickRate;
        return this;
    }

    public BlockBrewLiquidEffect setRenderPass(int renderPass) {
        this.renderPass = renderPass;
        return this;
    }

    public BlockBrewLiquidEffect setMaxScaledLight(int maxScaledLight) {
        this.maxScaledLight = maxScaledLight;
        return this;
    }

    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
            return true;
        }
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        Block block = world.func_147439_a(x, y, z);
        if (block == this) {
            return false;
        }
        if (this.displacements.containsKey(block)) {
            return this.displacements.get(block);
        }
        Material material = block.func_149688_o();
        if (material.func_76230_c() || material == Material.field_151567_E) {
            return false;
        }
        int density = BlockBrewLiquidEffect.getDensity(world, x, y, z);
        if (density == Integer.MAX_VALUE) {
            return true;
        }
        return this.density > density;
    }

    public boolean displaceIfPossible(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z)) {
            return true;
        }
        if (world.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        Block block = world.func_147439_a(x, y, z);
        if (block == this) {
            return false;
        }
        if (this.displacements.containsKey(block)) {
            if (this.displacements.get(block).booleanValue()) {
                block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
                return true;
            }
            return false;
        }
        Material material = block.func_149688_o();
        if (material.func_76230_c() || material == Material.field_151567_E) {
            return false;
        }
        int density = BlockBrewLiquidEffect.getDensity((IBlockAccess)world, x, y, z);
        if (density == Integer.MAX_VALUE) {
            block.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
            return true;
        }
        return this.density > density;
    }

    public void func_149726_b(World world, int x, int y, int z) {
        world.func_147464_a(x, y, z, (Block)this, this.tickRate);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        world.func_147464_a(x, y, z, (Block)this, this.tickRate);
    }

    public boolean func_149698_L() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return null;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    public int func_149738_a(World world) {
        return this.tickRate;
    }

    public void func_149640_a(World world, int x, int y, int z, Entity entity, Vec3 vec) {
        if (this.densityDir > 0) {
            return;
        }
        Vec3 vec_flow = this.getFlowVector((IBlockAccess)world, x, y, z);
        vec.field_72450_a += vec_flow.field_72450_a * (double)(this.quantaPerBlock * 4);
        vec.field_72448_b += vec_flow.field_72448_b * (double)(this.quantaPerBlock * 4);
        vec.field_72449_c += vec_flow.field_72449_c * (double)(this.quantaPerBlock * 4);
    }

    public int func_149645_b() {
        return Witchery.proxy.getBrewLiquidRenderId();
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149677_c(IBlockAccess world, int x, int y, int z) {
        int lightThis = world.func_72802_i(x, y, z, 0);
        int lightUp = world.func_72802_i(x, y + 1, z, 0);
        int lightThisBase = lightThis & 0xFF;
        int lightUpBase = lightUp & 0xFF;
        int lightThisExt = lightThis >> 16 & 0xFF;
        int lightUpExt = lightUp >> 16 & 0xFF;
        return (lightThisBase > lightUpBase ? lightThisBase : lightUpBase) | (lightThisExt > lightUpExt ? lightThisExt : lightUpExt) << 16;
    }

    public int func_149701_w() {
        return this.renderPass;
    }

    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        Block block = world.func_147439_a(x, y, z);
        if (block != this) {
            return !block.func_149662_c();
        }
        return block.func_149688_o() == this.func_149688_o() ? false : super.func_149646_a(world, x, y, z, side);
    }

    public static final int getDensity(IBlockAccess world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        if (!(block instanceof BlockBrewLiquidEffect)) {
            return Integer.MAX_VALUE;
        }
        return ((BlockBrewLiquidEffect)block).density;
    }

    public static final int getTemperature(IBlockAccess world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        if (!(block instanceof BlockBrewLiquidEffect)) {
            return Integer.MAX_VALUE;
        }
        return ((BlockBrewLiquidEffect)block).temperature;
    }

    public static double getFlowDirection(IBlockAccess world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        if (!block.func_149688_o().func_76224_d()) {
            return -1000.0;
        }
        Vec3 vec = ((BlockBrewLiquidEffect)block).getFlowVector(world, x, y, z);
        return vec.field_72450_a == 0.0 && vec.field_72449_c == 0.0 ? -1000.0 : Math.atan2(vec.field_72449_c, vec.field_72450_a) - 1.5707963267948966;
    }

    public final int getQuantaValueBelow(IBlockAccess world, int x, int y, int z, int belowThis) {
        int quantaRemaining = this.getQuantaValue(world, x, y, z);
        if (quantaRemaining >= belowThis) {
            return -1;
        }
        return quantaRemaining;
    }

    public final int getQuantaValueAbove(IBlockAccess world, int x, int y, int z, int aboveThis) {
        int quantaRemaining = this.getQuantaValue(world, x, y, z);
        if (quantaRemaining <= aboveThis) {
            return -1;
        }
        return quantaRemaining;
    }

    public final float getQuantaPercentage(IBlockAccess world, int x, int y, int z) {
        int quantaRemaining = this.getQuantaValue(world, x, y, z);
        return (float)quantaRemaining / this.quantaPerBlockFloat;
    }

    public Vec3 getFlowVector(IBlockAccess world, int x, int y, int z) {
        Vec3 vec = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);
        int decay = this.quantaPerBlock - this.getQuantaValue(world, x, y, z);
        for (int side = 0; side < 4; ++side) {
            int power;
            int x2 = x;
            int z2 = z;
            switch (side) {
                case 0: {
                    --x2;
                    break;
                }
                case 1: {
                    --z2;
                    break;
                }
                case 2: {
                    ++x2;
                    break;
                }
                case 3: {
                    ++z2;
                }
            }
            int otherDecay = this.quantaPerBlock - this.getQuantaValue(world, x2, y, z2);
            if (otherDecay >= this.quantaPerBlock) {
                if (world.func_147439_a(x2, y, z2).func_149688_o().func_76230_c() || (otherDecay = this.quantaPerBlock - this.getQuantaValue(world, x2, y - 1, z2)) < 0) continue;
                power = otherDecay - (decay - this.quantaPerBlock);
                vec = vec.func_72441_c((double)((x2 - x) * power), (double)((y - y) * power), (double)((z2 - z) * power));
                continue;
            }
            if (otherDecay < 0) continue;
            power = otherDecay - decay;
            vec = vec.func_72441_c((double)((x2 - x) * power), (double)((y - y) * power), (double)((z2 - z) * power));
        }
        if (world.func_147439_a(x, y + 1, z) == this) {
            boolean flag;
            boolean bl = flag = this.func_149747_d(world, x, y, z - 1, 2) || this.func_149747_d(world, x, y, z + 1, 3) || this.func_149747_d(world, x - 1, y, z, 4) || this.func_149747_d(world, x + 1, y, z, 5) || this.func_149747_d(world, x, y + 1, z - 1, 2) || this.func_149747_d(world, x, y + 1, z + 1, 3) || this.func_149747_d(world, x - 1, y + 1, z, 4) || this.func_149747_d(world, x + 1, y + 1, z, 5);
            if (flag) {
                vec = vec.func_72432_b().func_72441_c(0.0, -6.0, 0.0);
            }
        }
        vec = vec.func_72432_b();
        return vec;
    }

    public Fluid getFluid() {
        return FluidRegistry.getFluid((String)this.fluidName);
    }

    public float getFilledPercentage(World world, int x, int y, int z) {
        int quantaRemaining = this.getQuantaValue((IBlockAccess)world, x, y, z) + 1;
        float remaining = (float)quantaRemaining / this.quantaPerBlockFloat;
        if (remaining > 1.0f) {
            remaining = 1.0f;
        }
        return remaining * (float)(this.density > 0 ? 1 : -1);
    }

    public int getQuantaValue(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) == Blocks.field_150350_a) {
            return 0;
        }
        if (world.func_147439_a(x, y, z) != this) {
            return -1;
        }
        int quantaRemaining = this.quantaPerBlock - world.func_72805_g(x, y, z);
        return quantaRemaining;
    }

    public boolean func_149678_a(int meta, boolean fullHit) {
        return fullHit && meta == 0;
    }

    public int getMaxRenderHeightMeta() {
        return 0;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (this.maxScaledLight == 0) {
            return super.getLightValue(world, x, y, z);
        }
        int data = this.quantaPerBlock - world.func_72805_g(x, y, z) - 1;
        return (int)((float)data / this.quantaPerBlockFloat * (float)this.maxScaledLight);
    }

    private boolean isTargetBlock(World world, Block block, int x, int y, int z) {
        return block != null && (block != Blocks.field_150350_a || world.func_147439_a(x, y - 1, z).func_149688_o().func_76220_a()) && block != this;
    }

    public boolean isFlowingVertically(IBlockAccess world, int x, int y, int z) {
        return world.func_147439_a(x, y + this.densityDir, z) == this || world.func_147439_a(x, y, z) == this && this.canFlowInto(world, x, y + this.densityDir, z);
    }

    public boolean isSourceBlock(IBlockAccess world, int x, int y, int z) {
        return world.func_147439_a(x, y, z) == this && world.func_72805_g(x, y, z) == 0;
    }

    protected boolean[] getOptimalFlowDirections(World world, int x, int y, int z) {
        int side;
        for (int side2 = 0; side2 < 4; ++side2) {
            this.flowCost[side2] = 1000;
            int x2 = x;
            int y2 = y;
            int z2 = z;
            switch (side2) {
                case 0: {
                    --x2;
                    break;
                }
                case 1: {
                    ++x2;
                    break;
                }
                case 2: {
                    --z2;
                    break;
                }
                case 3: {
                    ++z2;
                }
            }
            if (!this.canFlowInto((IBlockAccess)world, x2, y2, z2) || this.isSourceBlock((IBlockAccess)world, x2, y2, z2)) continue;
            this.flowCost[side2] = this.canFlowInto((IBlockAccess)world, x2, y2 + this.densityDir, z2) ? 0 : this.calculateFlowCost(world, x2, y2, z2, 1, side2);
        }
        int min = this.flowCost[0];
        for (side = 1; side < 4; ++side) {
            if (this.flowCost[side] >= min) continue;
            min = this.flowCost[side];
        }
        for (side = 0; side < 4; ++side) {
            this.isOptimalFlowDirection[side] = this.flowCost[side] == min;
        }
        return this.isOptimalFlowDirection;
    }

    protected int calculateFlowCost(World world, int x, int y, int z, int recurseDepth, int side) {
        int cost = 1000;
        for (int adjSide = 0; adjSide < 4; ++adjSide) {
            int min;
            if (adjSide == 0 && side == 1 || adjSide == 1 && side == 0 || adjSide == 2 && side == 3 || adjSide == 3 && side == 2) continue;
            int x2 = x;
            int y2 = y;
            int z2 = z;
            switch (adjSide) {
                case 0: {
                    --x2;
                    break;
                }
                case 1: {
                    ++x2;
                    break;
                }
                case 2: {
                    --z2;
                    break;
                }
                case 3: {
                    ++z2;
                }
            }
            if (!this.canFlowInto((IBlockAccess)world, x2, y2, z2) || this.isSourceBlock((IBlockAccess)world, x2, y2, z2)) continue;
            if (this.canFlowInto((IBlockAccess)world, x2, y2 + this.densityDir, z2)) {
                return recurseDepth;
            }
            if (recurseDepth >= 4 || (min = this.calculateFlowCost(world, x2, y2, z2, recurseDepth + 1, adjSide)) >= cost) continue;
            cost = min;
        }
        return cost;
    }

    protected void flowIntoBlock(World world, int x, int y, int z, int meta, TileEntityBrewFluid sourceFluid) {
        if (meta < 0) {
            return;
        }
        if (this.displaceIfPossible(world, x, y, z)) {
            world.func_147465_d(x, y, z, (Block)this, meta, 3);
            TileEntityBrewFluid targetFluid = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBrewFluid.class);
            if (targetFluid != null && sourceFluid != null && sourceFluid.nbtEffect != null) {
                targetFluid.nbtEffect = (NBTTagCompound)sourceFluid.nbtEffect.func_74737_b();
                targetFluid.expansion = sourceFluid.expansion;
                targetFluid.color = sourceFluid.color;
                targetFluid.duration = sourceFluid.duration;
                targetFluid.thrower = sourceFluid.thrower;
            }
        }
    }

    protected boolean canFlowInto(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).isAir(world, x, y, z)) {
            return true;
        }
        Block block = world.func_147439_a(x, y, z);
        if (block == this) {
            return true;
        }
        if (this.displacements.containsKey(block)) {
            return this.displacements.get(block);
        }
        Material material = block.func_149688_o();
        if (material.func_76230_c() || material == Material.field_151586_h || material == Material.field_151587_i || material == Material.field_151567_E) {
            return false;
        }
        int density = BlockBrewLiquidEffect.getDensity(world, x, y, z);
        if (density == Integer.MAX_VALUE) {
            return true;
        }
        return this.density > density;
    }

    protected int getLargerQuanta(IBlockAccess world, int x, int y, int z, int compare) {
        int quantaRemaining = this.getQuantaValue(world, x, y, z);
        if (quantaRemaining <= 0) {
            return compare;
        }
        return quantaRemaining >= compare ? quantaRemaining : compare;
    }

    public FluidStack drain(World world, int x, int y, int z, boolean doDrain) {
        return null;
    }

    public boolean canDrain(World world, int x, int y, int z) {
        return false;
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        if (world.field_72995_K) {
            return;
        }
        boolean evaporated = false;
        TileEntityBrewFluid fluid = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBrewFluid.class);
        if (!world.field_72995_K && fluid != null && this.isSourceBlock((IBlockAccess)world, x, y, z)) {
            if (++fluid.updateCount > 3 && (fluid.duration == 0 || rand.nextInt(fluid.duration) == 0)) {
                world.func_147468_f(x, y, z);
                evaporated = true;
            } else {
                world.func_147464_a(x, y, z, (Block)this, this.tickRate);
            }
        }
        if (!evaporated) {
            int quantaRemaining = this.quantaPerBlock - world.func_72805_g(x, y, z);
            int expQuanta = -101;
            if (quantaRemaining < this.quantaPerBlock) {
                int y2 = y - this.densityDir;
                if (world.func_147439_a(x, y2, z) == this || world.func_147439_a(x - 1, y2, z) == this || world.func_147439_a(x + 1, y2, z) == this || world.func_147439_a(x, y2, z - 1) == this || world.func_147439_a(x, y2, z + 1) == this) {
                    expQuanta = this.quantaPerBlock - 1;
                } else {
                    int maxQuanta = -100;
                    maxQuanta = this.getLargerQuanta((IBlockAccess)world, x - 1, y, z, maxQuanta);
                    maxQuanta = this.getLargerQuanta((IBlockAccess)world, x + 1, y, z, maxQuanta);
                    maxQuanta = this.getLargerQuanta((IBlockAccess)world, x, y, z - 1, maxQuanta);
                    maxQuanta = this.getLargerQuanta((IBlockAccess)world, x, y, z + 1, maxQuanta);
                    expQuanta = maxQuanta - 1;
                }
                if (expQuanta != quantaRemaining) {
                    quantaRemaining = expQuanta;
                    if (expQuanta <= 0) {
                        world.func_147449_b(x, y, z, Blocks.field_150350_a);
                    } else {
                        world.func_72921_c(x, y, z, this.quantaPerBlock - expQuanta, 3);
                        world.func_147464_a(x, y, z, (Block)this, this.tickRate);
                        world.func_147459_d(x, y, z, (Block)this);
                    }
                }
            } else if (quantaRemaining >= this.quantaPerBlock) {
                world.func_72921_c(x, y, z, 0, 2);
            }
            if (this.canDisplace((IBlockAccess)world, x, y + this.densityDir, z)) {
                this.flowIntoBlock(world, x, y + this.densityDir, z, 1, fluid);
                return;
            }
            int flowMeta = this.quantaPerBlock - quantaRemaining + 1;
            if (flowMeta >= this.quantaPerBlock) {
                return;
            }
            if (this.isSourceBlock((IBlockAccess)world, x, y, z) || !this.isFlowingVertically((IBlockAccess)world, x, y, z)) {
                boolean[] flowTo;
                if (world.func_147439_a(x, y - this.densityDir, z) == this) {
                    flowMeta = 1;
                }
                if ((flowTo = this.getOptimalFlowDirections(world, x, y, z))[0]) {
                    this.flowIntoBlock(world, x - 1, y, z, flowMeta, fluid);
                }
                if (flowTo[1]) {
                    this.flowIntoBlock(world, x + 1, y, z, flowMeta, fluid);
                }
                if (flowTo[2]) {
                    this.flowIntoBlock(world, x, y, z - 1, flowMeta, fluid);
                }
                if (flowTo[3]) {
                    this.flowIntoBlock(world, x, y, z + 1, flowMeta, fluid);
                }
            }
            if (fluid != null && fluid.nbtEffect != null) {
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    int x2 = x + direction.offsetX;
                    int y2 = y + direction.offsetY;
                    int z2 = z + direction.offsetZ;
                    if (!(world.field_73012_v.nextDouble() < 0.01) || !this.isTargetBlock(world, world.func_147439_a(x2, y2, z2), x2, y2, z2)) continue;
                    ModifiersEffect modifiers = new ModifiersEffect(1.0, 1.0, false, new EntityPosition((double)x + 0.5, (double)y, (double)z + 0.5), false, 0, EntityUtil.playerOrFake(world, fluid.thrower));
                    ++modifiers.strengthPenalty;
                    WitcheryBrewRegistry.INSTANCE.applyToBlock(world, x2, y2, z2, direction.getOpposite(), 1, fluid.nbtEffect, modifiers);
                }
                world.func_147464_a(x, y, z, (Block)this, this.tickRate);
            }
        }
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        TileEntityBrewFluid liquid;
        if (entity != null && entity instanceof EntityLivingBase && !world.field_72995_K && world.field_73012_v.nextInt(10) == 4 && (liquid = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBrewFluid.class)) != null && liquid.nbtEffect != null) {
            EntityLivingBase living = (EntityLivingBase)entity;
            WitcheryBrewRegistry.INSTANCE.applyToEntity(world, living, liquid.nbtEffect, new ModifiersEffect(0.25, 0.5, false, new EntityPosition(x, y, z), false, 0, EntityUtil.playerOrFake(world, liquid.thrower)));
        }
    }

    static {
        defaultDisplacements.put(Blocks.field_150466_ao, false);
        defaultDisplacements.put(Blocks.field_150454_av, false);
        defaultDisplacements.put(Blocks.field_150472_an, false);
        defaultDisplacements.put(Blocks.field_150444_as, false);
        defaultDisplacements.put(Blocks.field_150436_aH, false);
    }
}

