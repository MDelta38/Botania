/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.block.material.Material
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBehaviorDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.dispenser.IPosition
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class DispenseBehaviourItemBrew
implements IBehaviorDispenseItem {
    private final BehaviorDefaultDispenseItem defaultDispenserItemBehavior = new BehaviorDefaultDispenseItem();

    public ItemStack func_82482_a(IBlockSource block, ItemStack stack) {
        if (stack.func_77973_b() == Witchery.Items.BREW && WitcheryBrewRegistry.INSTANCE.isSplash(stack.func_77978_p())) {
            return new DispenserBehaviorBrew(this, stack).func_82482_a(block, stack);
        }
        if (stack.func_77973_b() == Items.field_151069_bo) {
            EnumFacing[] FACINGS;
            EnumFacing facing = BlockDispenser.func_149937_b((int)block.func_82620_h());
            for (EnumFacing cauldronFacing : FACINGS = new EnumFacing[]{EnumFacing.DOWN, EnumFacing.UP, EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST}) {
                if (cauldronFacing == facing) continue;
                int x = block.func_82623_d() + cauldronFacing.func_82601_c();
                int y = block.func_82622_e() + cauldronFacing.func_96559_d();
                int z = block.func_82621_f() + cauldronFacing.func_82599_e();
                Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
                if (replaceBlock != Witchery.Blocks.CAULDRON) continue;
                ItemStack brew = Witchery.Blocks.CAULDRON.fillBottleFromCauldron(block.func_82618_k(), x, y, z, 3000);
                if (brew != null) {
                    IPosition position = BlockDispenser.func_149939_a((IBlockSource)block);
                    BehaviorDefaultDispenseItem.func_82486_a((World)block.func_82618_k(), (ItemStack)brew, (int)6, (EnumFacing)facing, (IPosition)position);
                    stack.func_77979_a(1);
                    block.func_82618_k().func_72926_e(1000, block.func_82623_d(), block.func_82622_e(), block.func_82621_f(), 0);
                }
                return stack;
            }
            return this.defaultDispenserItemBehavior.func_82482_a(block, stack);
        }
        if (stack.func_77973_b() == Witchery.Items.BREW_ENDLESS_WATER) {
            if (!block.func_82618_k().field_72995_K) {
                EnumFacing facing = BlockDispenser.func_149937_b((int)block.func_82620_h());
                int x = block.func_82623_d() + facing.func_82601_c();
                int y = block.func_82622_e() + facing.func_96559_d();
                int z = block.func_82621_f() + facing.func_82599_e();
                Block replaceBlock = block.func_82618_k().func_147439_a(x, y, z);
                FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((WorldServer)((WorldServer)block.func_82618_k()));
                if (stack.func_77960_j() <= stack.func_77958_k()) {
                    if (BlockUtil.isReplaceableBlock(block.func_82618_k(), x, y, z, (EntityLivingBase)fakePlayer) && replaceBlock.func_149688_o() != Material.field_151586_h) {
                        stack.func_77972_a(1, (EntityLivingBase)fakePlayer);
                        block.func_82618_k().func_147449_b(x, y, z, (Block)Blocks.field_150358_i);
                        block.func_82618_k().func_147471_g(x, y, z);
                        SoundEffect.WATER_SPLASH.playAt(block.func_82618_k(), x, y, z);
                    } else if (replaceBlock == Witchery.Blocks.CAULDRON) {
                        if (Witchery.Blocks.CAULDRON.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 3000))) {
                            stack.func_77972_a(1, (EntityLivingBase)fakePlayer);
                        }
                    } else if (replaceBlock == Witchery.Blocks.KETTLE && Witchery.Blocks.KETTLE.tryFillWith(block.func_82618_k(), x, y, z, new FluidStack(FluidRegistry.WATER, 1000))) {
                        stack.func_77972_a(1, (EntityLivingBase)fakePlayer);
                    }
                }
            }
            return stack;
        }
        if (stack.func_77973_b() == Witchery.Items.SUN_GRENADE) {
            return new DispenserGrenade(this, stack).func_82482_a(block, stack);
        }
        if (stack.func_77973_b() == Witchery.Items.DUP_GRENADE) {
            return new DispenserGrenade(this, stack).func_82482_a(block, stack);
        }
        return this.defaultDispenserItemBehavior.func_82482_a(block, stack);
    }

    public static class DispenserGrenade
    extends BehaviorDefaultDispenseItem {
        final ItemStack potionItemStack;
        final DispenseBehaviourItemBrew dispenserPotionBehavior;

        DispenserGrenade(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
            this.dispenserPotionBehavior = behavior;
            this.potionItemStack = brewStack;
        }

        public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
            World world = dispenserBlock.func_82618_k();
            IPosition iposition = BlockDispenser.func_149939_a((IBlockSource)dispenserBlock);
            EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenserBlock.func_82620_h());
            EntityGrenade iprojectile = this.getProjectileEntity(world, iposition);
            iprojectile.setThrowableHeading(enumfacing.func_82601_c(), (float)enumfacing.func_96559_d() + 0.1f, enumfacing.func_82599_e(), this.func_82500_b(), this.func_82498_a());
            world.func_72838_d((Entity)iprojectile);
            stack.func_77979_a(1);
            return stack;
        }

        protected void func_82485_a(IBlockSource dispenserBlock) {
            dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
        }

        protected EntityGrenade getProjectileEntity(World world, IPosition position) {
            return new EntityGrenade(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), this.potionItemStack);
        }

        protected float func_82498_a() {
            return 3.0f;
        }

        protected float func_82500_b() {
            return 1.375f;
        }
    }

    public static class DispenserBehaviorBrew
    extends BehaviorDefaultDispenseItem {
        final ItemStack potionItemStack;
        final DispenseBehaviourItemBrew dispenserPotionBehavior;

        DispenserBehaviorBrew(DispenseBehaviourItemBrew behavior, ItemStack brewStack) {
            this.dispenserPotionBehavior = behavior;
            this.potionItemStack = brewStack;
        }

        public ItemStack func_82487_b(IBlockSource dispenserBlock, ItemStack stack) {
            World world = dispenserBlock.func_82618_k();
            IPosition iposition = BlockDispenser.func_149939_a((IBlockSource)dispenserBlock);
            EnumFacing enumfacing = BlockDispenser.func_149937_b((int)dispenserBlock.func_82620_h());
            EntityBrew iprojectile = this.getProjectileEntity(world, iposition);
            iprojectile.setThrowableHeading(enumfacing.func_82601_c(), (float)enumfacing.func_96559_d() + 0.1f, enumfacing.func_82599_e(), this.func_82500_b(), this.func_82498_a());
            world.func_72838_d((Entity)iprojectile);
            stack.func_77979_a(1);
            return stack;
        }

        protected void func_82485_a(IBlockSource dispenserBlock) {
            dispenserBlock.func_82618_k().func_72926_e(1002, dispenserBlock.func_82623_d(), dispenserBlock.func_82622_e(), dispenserBlock.func_82621_f(), 0);
        }

        protected EntityBrew getProjectileEntity(World world, IPosition position) {
            return new EntityBrew(world, position.func_82615_a(), position.func_82617_b(), position.func_82616_c(), this.potionItemStack, false);
        }

        protected float func_82498_a() {
            return 3.0f;
        }

        protected float func_82500_b() {
            return 1.375f;
        }
    }
}

