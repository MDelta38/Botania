/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTank
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 */
package thaumcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.ItemBathSalts;
import thaumcraft.common.lib.utils.BlockUtils;

public class TileSpa
extends TileThaumcraft
implements ISidedInventory,
IFluidHandler {
    private ItemStack[] itemStacks = new ItemStack[1];
    private boolean mix = true;
    private String customName;
    private int counter = 0;
    public FluidTank tank = new FluidTank(5000);

    public void toggleMix() {
        this.mix = !this.mix;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    public boolean getMix() {
        return this.mix;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.mix = nbttagcompound.func_74767_n("mix");
        this.tank.setFluid(FluidStack.loadFluidStackFromNBT((NBTTagCompound)nbttagcompound));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("mix", this.mix);
        if (this.tank.getFluid() != null) {
            this.tank.getFluid().writeToNBT(nbttagcompound);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        this.itemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.itemStacks.length) continue;
            this.itemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.itemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.itemStacks[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.itemStacks[par1] != null) {
            if (this.itemStacks[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                return itemstack;
            }
            ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.itemStacks[par1] != null) {
            ItemStack itemstack = this.itemStacks[par1];
            this.itemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.itemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return par2ItemStack != null && par2ItemStack.func_77973_b() instanceof ItemBathSalts;
    }

    public int[] func_94128_d(int par1) {
        int[] nArray;
        if (par1 != 1) {
            int[] nArray2 = new int[1];
            nArray = nArray2;
            nArray2[0] = 0;
        } else {
            nArray = new int[]{};
        }
        return nArray;
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 1;
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 1;
    }

    public String func_145825_b() {
        return "thaumcraft.spa";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        block6: {
            if (!this.field_145850_b.field_72995_K && this.counter++ % 40 == 0 && !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) && this.hasIngredients()) {
                Block b = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                int m = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                Block tb = null;
                tb = this.mix ? ConfigBlocks.blockFluidPure : this.tank.getFluid().getFluid().getBlock();
                if (b == tb && m == 0) {
                    for (int xx = -2; xx <= 2; ++xx) {
                        for (int zz = -2; zz <= 2; ++zz) {
                            if (!this.isValidLocation(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz, true, tb)) continue;
                            this.consumeIngredients();
                            this.field_145850_b.func_147449_b(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz, tb);
                            this.checkQuanta(this.field_145851_c + xx, this.field_145848_d + 1, this.field_145849_e + zz);
                            break block6;
                        }
                    }
                } else if (this.isValidLocation(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, false, tb)) {
                    this.consumeIngredients();
                    this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, tb);
                    this.checkQuanta(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                }
            }
        }
    }

    private void checkQuanta(int i, int j, int k) {
        int md;
        float p;
        Block b = this.field_145850_b.func_147439_a(i, j, k);
        if (b instanceof BlockFluidBase && (p = ((BlockFluidBase)b).getQuantaPercentage((IBlockAccess)this.field_145850_b, i, j, k)) < 1.0f && (md = (int)(1.0f / p) - 1) >= 0 && md < 16) {
            this.field_145850_b.func_72921_c(i, j, k, md, 3);
        }
    }

    private boolean hasIngredients() {
        if (this.mix) {
            if (this.tank.getInfo().fluid == null || !this.tank.getInfo().fluid.containsFluid(new FluidStack(FluidRegistry.WATER, 1000))) {
                return false;
            }
            if (this.itemStacks[0] == null || !(this.itemStacks[0].func_77973_b() instanceof ItemBathSalts)) {
                return false;
            }
        } else if (this.tank.getInfo().fluid == null || !this.tank.getFluid().getFluid().canBePlacedInWorld() || this.tank.getFluidAmount() < 1000) {
            return false;
        }
        return true;
    }

    private void consumeIngredients() {
        if (this.mix) {
            this.func_70298_a(0, 1);
        }
        this.drain(ForgeDirection.UNKNOWN, 1000, true);
    }

    private boolean isValidLocation(int x, int y, int z, boolean mustBeAdjacent, Block target) {
        if ((target == Blocks.field_150355_j || target == Blocks.field_150358_i) && this.field_145850_b.field_73011_w.field_76575_d) {
            return false;
        }
        Block b = this.field_145850_b.func_147439_a(x, y, z);
        Block bb = this.field_145850_b.func_147439_a(x, y - 1, z);
        int m = this.field_145850_b.func_72805_g(x, y, z);
        if (bb.isSideSolid((IBlockAccess)this.field_145850_b, x, y - 1, z, ForgeDirection.UP) && b.isReplaceable((IBlockAccess)this.field_145850_b, x, y, z) && (b != target || m != 0)) {
            if (!mustBeAdjacent) {
                return true;
            }
            return BlockUtils.isBlockTouching((IBlockAccess)this.field_145850_b, x, y, z, target, 0);
        }
        return false;
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        int df = this.tank.fill(resource, doFill);
        if (df > 0 && doFill) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        return df;
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(this.tank.getFluid())) {
            return null;
        }
        return this.tank.drain(resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        FluidStack fs = this.tank.drain(maxDrain, doDrain);
        if (fs != null && doDrain) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        return fs;
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from != ForgeDirection.UP;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from != ForgeDirection.UP;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.tank.getInfo()};
    }
}

