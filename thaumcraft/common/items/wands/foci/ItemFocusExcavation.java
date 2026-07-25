/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemFocusExcavation
extends ItemFocusBasic {
    private static final AspectList cost = new AspectList().add(Aspect.EARTH, 15);
    private static AspectList cost2 = null;
    static HashMap<String, Long> soundDelay = new HashMap();
    static HashMap<String, Object> beam = new HashMap();
    static HashMap<String, Float> breakcount = new HashMap();
    static HashMap<String, Integer> lastX = new HashMap();
    static HashMap<String, Integer> lastY = new HashMap();
    static HashMap<String, Integer> lastZ = new HashMap();
    public static FocusUpgradeType dowsing = new FocusUpgradeType(20, new ResourceLocation("thaumcraft", "textures/foci/dowsing.png"), "focus.upgrade.dowsing.name", "focus.upgrade.dowsing.text", new AspectList().add(Aspect.MINE, 1));

    public ItemFocusExcavation() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_excavation");
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BE" + super.getSortingHelper(itemstack);
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 409606;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        if (this.isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
            if (cost2 == null) {
                cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
                cost2.add(cost);
            }
            return cost2;
        }
        if (this.isUpgradedWith(itemstack, dowsing)) {
            if (cost2 == null) {
                cost2 = new AspectList().add(Aspect.FIRE, 2).add(Aspect.ORDER, 2);
                cost2.add(cost);
            }
            return cost2;
        }
        return cost;
    }

    @Override
    public boolean isVisCostPerTick(ItemStack itemstack) {
        return true;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop) {
        p.func_71008_a(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false)) {
            p.func_71034_by();
            return;
        }
        String pp = "R" + p.func_70005_c_();
        if (!p.field_70170_p.field_72995_K) {
            pp = "S" + p.func_70005_c_();
        }
        if (soundDelay.get(pp) == null) {
            soundDelay.put(pp, 0L);
        }
        if (breakcount.get(pp) == null) {
            breakcount.put(pp, Float.valueOf(0.0f));
        }
        if (lastX.get(pp) == null) {
            lastX.put(pp, 0);
        }
        if (lastY.get(pp) == null) {
            lastY.put(pp, 0);
        }
        if (lastZ.get(pp) == null) {
            lastZ.put(pp, 0);
        }
        MovingObjectPosition mop = BlockUtils.getTargetBlock(p.field_70170_p, (Entity)p, false);
        Vec3 v = p.func_70040_Z();
        double tx = p.field_70165_t + v.field_72450_a * 10.0;
        double ty = p.field_70163_u + v.field_72448_b * 10.0;
        double tz = p.field_70161_v + v.field_72449_c * 10.0;
        int impact = 0;
        if (mop != null) {
            tx = mop.field_72307_f.field_72450_a;
            ty = mop.field_72307_f.field_72448_b;
            tz = mop.field_72307_f.field_72449_c;
            impact = 5;
            if (!p.field_70170_p.field_72995_K && soundDelay.get(pp) < System.currentTimeMillis()) {
                p.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:rumble", 0.3f, 1.0f);
                soundDelay.put(pp, System.currentTimeMillis() + 1200L);
            }
        } else {
            soundDelay.put(pp, 0L);
        }
        if (p.field_70170_p.field_72995_K) {
            beam.put(pp, Thaumcraft.proxy.beamCont(p.field_70170_p, p, tx, ty, tz, 2, 65382, false, impact > 0 ? 2.0f : 0.0f, beam.get(pp), impact));
        }
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && p.field_70170_p.func_72962_a(p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
            Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            float hardness = bi.func_149712_f(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (hardness >= 0.0f) {
                int pot = wand.getFocusPotency(stack);
                float speed = 0.05f + (float)pot * 0.1f;
                if (bi.func_149688_o() == Material.field_151576_e || bi.func_149688_o() == Material.field_151577_b || bi.func_149688_o() == Material.field_151578_c || bi.func_149688_o() == Material.field_151595_p) {
                    speed = 0.25f + (float)pot * 0.25f;
                }
                if (bi == Blocks.field_150343_Z) {
                    speed *= 3.0f;
                }
                if (lastX.get(pp) == mop.field_72311_b && lastY.get(pp) == mop.field_72312_c && lastZ.get(pp) == mop.field_72309_d) {
                    float bc = breakcount.get(pp).floatValue();
                    if (p.field_70170_p.field_72995_K && bc > 0.0f && bi != Blocks.field_150350_a) {
                        int progress = (int)(bc / hardness * 9.0f);
                        Thaumcraft.proxy.excavateFX(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, p, Block.func_149682_b((Block)bi), md, progress);
                    }
                    if (p.field_70170_p.field_72995_K) {
                        if (bc >= hardness) {
                            breakcount.put(pp, Float.valueOf(0.0f));
                        } else {
                            breakcount.put(pp, Float.valueOf(bc + speed));
                        }
                    } else if (bc >= hardness && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false)) {
                        if (this.excavate(p.field_70170_p, stack, p, bi, md, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) {
                            for (int a = 0; a < wand.getFocusEnlarge(stack); ++a) {
                                if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, false) || !this.breakNeighbour(p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, bi, md, stack)) continue;
                                wand.consumeAllVis(stack, p, this.getVisCost(stack), true, false);
                            }
                        }
                        lastX.put(pp, Integer.MAX_VALUE);
                        lastY.put(pp, Integer.MAX_VALUE);
                        lastZ.put(pp, Integer.MAX_VALUE);
                        breakcount.put(pp, Float.valueOf(0.0f));
                    } else {
                        breakcount.put(pp, Float.valueOf(bc + speed));
                    }
                } else {
                    lastX.put(pp, mop.field_72311_b);
                    lastY.put(pp, mop.field_72312_c);
                    lastZ.put(pp, mop.field_72309_d);
                    breakcount.put(pp, Float.valueOf(0.0f));
                }
            }
        } else {
            lastX.put(pp, Integer.MAX_VALUE);
            lastY.put(pp, Integer.MAX_VALUE);
            lastZ.put(pp, Integer.MAX_VALUE);
            breakcount.put(pp, Float.valueOf(0.0f));
        }
    }

    private boolean excavate(World world, ItemStack stack, EntityPlayer player, Block block, int md, int x, int y, int z) {
        BlockEvent.BreakEvent event;
        WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
        if (player.field_71075_bZ.field_75099_e) {
            if (player.field_71075_bZ.field_75098_d) {
                gt = WorldSettings.GameType.CREATIVE;
            }
        } else {
            gt = WorldSettings.GameType.ADVENTURE;
        }
        if (!(event = ForgeHooks.onBlockBreakEvent((World)world, (WorldSettings.GameType)gt, (EntityPlayerMP)((EntityPlayerMP)player), (int)x, (int)y, (int)z)).isCanceled()) {
            ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
            int fortune = wand.getFocusTreasure(stack);
            boolean silk = this.isUpgradedWith(wand.getFocusItem(stack), FocusUpgradeType.silktouch);
            if (silk && block.canSilkHarvest(player.field_70170_p, player, x, y, z, md)) {
                ArrayList<ItemStack> items = new ArrayList<ItemStack>();
                ItemStack itemstack = BlockUtils.createStackedBlock(block, md);
                if (itemstack != null) {
                    items.add(itemstack);
                }
                ForgeEventFactory.fireBlockHarvesting(items, (World)world, (Block)block, (int)x, (int)y, (int)z, (int)md, (int)0, (float)1.0f, (boolean)true, (EntityPlayer)player);
                for (ItemStack is : items) {
                    BlockUtils.dropBlockAsItem(world, x, y, z, is, block);
                }
            } else {
                BlockUtils.dropBlockAsItemWithChance(world, block, x, y, z, md, 1.0f, fortune, player);
                block.func_149657_c(world, x, y, z, block.getExpDrop((IBlockAccess)world, md, fortune));
            }
            world.func_147468_f(x, y, z);
            world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (md << 12));
            return true;
        }
        return false;
    }

    boolean breakNeighbour(EntityPlayer p, int x, int y, int z, Block block, int md, ItemStack stack) {
        List<ForgeDirection> directions = Arrays.asList(ForgeDirection.DOWN, ForgeDirection.UP, ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST);
        Collections.shuffle(directions, p.field_70170_p.field_73012_v);
        for (ForgeDirection dir : directions) {
            if (p.field_70170_p.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != block || p.field_70170_p.func_72805_g(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) != md || !this.excavate(p.field_70170_p, stack, p, block, md, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) continue;
            return true;
        }
        return false;
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer p, int count) {
        String pp = "R" + p.func_70005_c_();
        if (!p.field_70170_p.field_72995_K) {
            pp = "S" + p.func_70005_c_();
        }
        if (soundDelay.get(pp) == null) {
            soundDelay.put(pp, 0L);
        }
        if (breakcount.get(pp) == null) {
            breakcount.put(pp, Float.valueOf(0.0f));
        }
        if (lastX.get(pp) == null) {
            lastX.put(pp, 0);
        }
        if (lastY.get(pp) == null) {
            lastY.put(pp, 0);
        }
        if (lastZ.get(pp) == null) {
            lastZ.put(pp, 0);
        }
        beam.put(pp, null);
        lastX.put(pp, Integer.MAX_VALUE);
        lastY.put(pp, Integer.MAX_VALUE);
        lastZ.put(pp, Integer.MAX_VALUE);
        breakcount.put(pp, Float.valueOf(0.0f));
    }

    @Override
    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack) {
        return ItemFocusBasic.WandFocusAnimation.CHARGE;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, dowsing};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.treasure, FocusUpgradeType.silktouch};
            }
        }
        return null;
    }
}

