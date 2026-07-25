/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.block.BlockDynamicLiquid
 *  net.minecraft.block.BlockStaticLiquid
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fluids.IFluidContainerItem
 *  net.minecraftforge.fluids.IFluidHandler
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package witchinggadgets.common.items;

import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidContainerItem;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.util.Utilities;

public class ItemCrystalCapsule
extends Item
implements IFluidContainerItem {
    public IIcon overlay;
    public IIcon fluidMask;

    public ItemCrystalCapsule() {
        this.func_77637_a(WitchingGadgets.tabWG);
        this.func_77627_a(true);
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack((Item)this);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        MovingObjectPosition localMovingObjectPosition = this.func_77621_a(world, player, false);
        if (localMovingObjectPosition == null || localMovingObjectPosition.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            return stack;
        }
        if (!world.field_72995_K) {
            int x = localMovingObjectPosition.field_72311_b;
            int y = localMovingObjectPosition.field_72312_c;
            int z = localMovingObjectPosition.field_72309_d;
            if (this.tryFillTank(stack, world, x, y, z, localMovingObjectPosition.field_72310_e, player)) {
                return stack;
            }
            switch (localMovingObjectPosition.field_72310_e) {
                case 0: {
                    --y;
                    break;
                }
                case 1: {
                    ++y;
                    break;
                }
                case 2: {
                    --z;
                    break;
                }
                case 3: {
                    ++z;
                    break;
                }
                case 4: {
                    --x;
                    break;
                }
                case 5: {
                    ++x;
                }
            }
            if (!player.func_82247_a(x, y, z, localMovingObjectPosition.field_72310_e, stack) || !world.func_147437_c(x, y, z) && world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
                return stack;
            }
            ItemStack used = this.useCapsule(world, x, y, z, stack);
            if (stack.equals(used)) {
                return stack;
            }
            if (!player.field_71071_by.func_70441_a(used)) {
                player.func_71019_a(used, false);
            }
        }
        return stack;
    }

    boolean tryFillTank(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te instanceof IFluidHandler) {
            IFluidHandler handler = (IFluidHandler)te;
            ForgeDirection dir = ForgeDirection.getOrientation((int)side);
            FluidStack fs = this.getFluid(stack);
            if (fs == null) {
                FluidStack input = handler.drain(dir, 1000, false);
                if (input == null || input.amount < 1000) {
                    return false;
                }
                ItemStack filled = Utilities.copyStackWithSize(stack, 1);
                this.fill(filled, handler.drain(dir, 1000, true), true);
                if (!player.field_71071_by.func_70441_a(filled)) {
                    player.func_71019_a(filled, false);
                }
                --stack.field_77994_a;
                return true;
            }
            int space = handler.fill(dir, fs, false);
            if (space < 1000) {
                return false;
            }
            handler.fill(dir, fs, true);
            if (!player.field_71071_by.func_70441_a(this.getContainerItem(stack))) {
                player.func_71019_a(stack, false);
            }
            --stack.field_77994_a;
            return true;
        }
        return false;
    }

    ItemStack useCapsule(World world, int x, int y, int z, ItemStack stack) {
        if (this.getFluidStored(stack) != null) {
            Fluid f = this.getFluidStored(stack);
            if (f == null || f.getBlock() == null || !f.canBePlacedInWorld()) {
                return stack;
            }
            world.func_147449_b(x, y, z, f.getBlock());
            --stack.field_77994_a;
            world.func_147460_e(x, y, z, world.func_147439_a(x, y, z));
            return this.getContainerItem(stack);
        }
        Fluid f = FluidRegistry.lookupFluidForBlock((Block)world.func_147439_a(x, y, z));
        if (f == null && world.func_147439_a(x, y, z) instanceof BlockDynamicLiquid && world.func_72805_g(x, y, z) == 0) {
            if (world.func_147439_a(x, y, z).func_149688_o().equals(Material.field_151586_h)) {
                f = FluidRegistry.WATER;
            } else if (world.func_147439_a(x, y, z).func_149688_o().equals(Material.field_151587_i)) {
                f = FluidRegistry.LAVA;
            }
        }
        if (world.func_147439_a(x, y, z) instanceof IFluidBlock && !((IFluidBlock)world.func_147439_a(x, y, z)).canDrain(world, x, y, z)) {
            return stack;
        }
        if (world.func_147439_a(x, y, z) instanceof BlockStaticLiquid && world.func_72805_g(x, y, z) != 0) {
            return stack;
        }
        if (f == null) {
            return stack;
        }
        ItemStack filled = this.getContainerItem(stack);
        if (!filled.func_77942_o()) {
            filled.func_77982_d(new NBTTagCompound());
        }
        filled.func_77978_p().func_74778_a("fluid", FluidRegistry.getFluidName((FluidStack)new FluidStack(f, 1000)));
        world.func_147468_f(x, y, z);
        --stack.field_77994_a;
        return filled;
    }

    public FluidStack getFluid(ItemStack container) {
        Fluid f = this.getFluidStored(container);
        if (f == null) {
            return null;
        }
        return new FluidStack(f, 1000);
    }

    public int getCapacity(ItemStack container) {
        return 1000;
    }

    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (resource == null || resource.amount < 1000) {
            return 0;
        }
        if (!container.func_77942_o()) {
            container.func_77982_d(new NBTTagCompound());
        }
        if (doFill) {
            container.func_77978_p().func_74778_a("fluid", FluidRegistry.getFluidName((FluidStack)resource));
        }
        return 1000;
    }

    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        if (maxDrain < 1000) {
            return null;
        }
        Fluid f = this.getFluidStored(container);
        if (f == null) {
            return null;
        }
        if (doDrain) {
            container.func_77978_p().func_82580_o("fluid");
        }
        return new FluidStack(f, 1000);
    }

    public void func_77624_a(ItemStack item, EntityPlayer player, List list, boolean adv) {
        Fluid f = this.getFluidStored(item);
        if (f != null) {
            String fluidName = "ERROR";
            try {
                FluidStack fs = new FluidStack(f, 1000);
                fluidName = fs.getLocalizedName();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            list.add(fluidName);
        }
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item));
        for (Map.Entry f : FluidRegistry.getRegisteredFluids().entrySet()) {
            ItemStack s = new ItemStack(item);
            s.func_77982_d(new NBTTagCompound());
            s.func_77978_p().func_74778_a("fluid", (String)f.getKey());
            list.add(s);
        }
    }

    public Fluid getFluidStored(ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("fluid") && FluidRegistry.getFluid((String)stack.func_77978_p().func_74779_i("fluid")) != null) {
            return FluidRegistry.getFluid((String)stack.func_77978_p().func_74779_i("fluid"));
        }
        return null;
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:crystalCapsule");
        this.overlay = iconRegister.func_94245_a("witchinggadgets:crystalCapsule_overlay");
        this.fluidMask = iconRegister.func_94245_a("witchinggadgets:crystalCapsule_mask");
    }

    public IIcon func_77618_c(int meta, int pass) {
        return pass == 0 ? this.field_77791_bV : this.overlay;
    }

    public boolean func_77623_v() {
        return true;
    }

    public static class CapsuleDispenserBehaviour
    extends BehaviorDefaultDispenseItem {
        private final BehaviorDefaultDispenseItem deafultBehaviour = new BehaviorDefaultDispenseItem();

        public ItemStack func_82487_b(IBlockSource dispenser, ItemStack stack) {
            ItemCrystalCapsule capsule = (ItemCrystalCapsule)stack.func_77973_b();
            int x = dispenser.func_82623_d();
            int y = dispenser.func_82622_e();
            int z = dispenser.func_82621_f();
            EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenser.func_82620_h());
            ItemStack s = capsule.useCapsule(dispenser.func_82618_k(), x + enumfacing.func_82601_c(), y + enumfacing.func_96559_d(), z + enumfacing.func_82599_e(), stack);
            s = InventoryUtils.insertStack((IInventory)((IInventory)dispenser.func_150835_j()), (ItemStack)s, (int)enumfacing.ordinal(), (boolean)true);
            if (s != null) {
                this.deafultBehaviour.func_82482_a(dispenser, s);
            }
            return stack;
        }
    }
}

