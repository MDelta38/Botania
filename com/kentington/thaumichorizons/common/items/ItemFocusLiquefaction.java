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
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.utils.BlockUtils
 *  thaumcraft.common.lib.utils.Utils
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

public class ItemFocusLiquefaction
extends ItemFocusBasic {
    public static FocusUpgradeType nuggets = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/nuggets.png"), "focus.upgrade.nuggets.name", "focus.upgrade.nuggets.text", new AspectList().add(Aspect.METAL, 8));
    public static FocusUpgradeType purity = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/purity.png"), "focus.upgrade.purity.name", "focus.upgrade.purity.text", new AspectList().add(Aspect.ORDER, 8));
    private static final AspectList cost = new AspectList().add(Aspect.FIRE, 40);
    private static final AspectList costCritter = new AspectList().add(Aspect.FIRE, 15);
    static HashMap<String, Long> soundDelay = new HashMap();
    static HashMap<String, Object> beam = new HashMap();
    static HashMap<String, Float> breakcount = new HashMap();
    static HashMap<String, Integer> lastX = new HashMap();
    static HashMap<String, Integer> lastY = new HashMap();
    static HashMap<String, Integer> lastZ = new HashMap();

    public ItemFocusLiquefaction() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:focus_liquefaction");
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BE" + super.getSortingHelper(itemstack);
    }

    public int getFocusColor() {
        return 0xFF4444;
    }

    public boolean isVisCostPerTick() {
        return false;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mop) {
        p.func_71008_a(itemstack, Integer.MAX_VALUE);
        return itemstack;
    }

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int count) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        int size = 2 + wand.getFocusEnlarge(stack) * 8;
        if (!wand.consumeAllVis(stack, p, this.getVisCost(stack), false, true)) {
            p.func_71034_by();
        } else {
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
            MovingObjectPosition mop = BlockUtils.getTargetBlock((World)p.field_70170_p, (Entity)p, (boolean)true);
            Entity ent = ItemFocusLiquefaction.getPointedEntity(p.field_70170_p, (EntityLivingBase)p, 10.0);
            Vec3 v = p.func_70040_Z();
            double tx = p.field_70165_t + v.field_72450_a * 10.0;
            double ty = p.field_70163_u + v.field_72448_b * 10.0;
            double tz = p.field_70161_v + v.field_72449_c * 10.0;
            int impact = 0;
            if ((ent == null || ent instanceof EntityItem && FurnaceRecipes.func_77602_a().func_151395_a(((EntityItem)ent).func_92059_d()) == null) && mop != null) {
                tx = mop.field_72307_f.field_72450_a;
                ty = mop.field_72307_f.field_72448_b;
                tz = mop.field_72307_f.field_72449_c;
                impact = 5;
                if (!p.field_70170_p.field_72995_K && soundDelay.get(pp) < System.currentTimeMillis()) {
                    p.field_70170_p.func_72908_a(tx, ty, tz, "fire.fire", 8.0f, 1.0f);
                    soundDelay.put(pp, System.currentTimeMillis() + 1200L);
                }
            } else if (ent != null) {
                tx = ent.field_70165_t;
                ty = ent.field_70163_u;
                tz = ent.field_70161_v;
                impact = 5;
                if (!p.field_70170_p.field_72995_K && soundDelay.get(pp) < System.currentTimeMillis()) {
                    p.field_70170_p.func_72908_a(tx, ty, tz, "fire.fire", 0.3f, 1.0f);
                    soundDelay.put(pp, System.currentTimeMillis() + 1200L);
                }
            } else {
                soundDelay.put(pp, 0L);
            }
            if (p.field_70170_p.field_72995_K) {
                beam.put(pp, Thaumcraft.proxy.beamCont(p.field_70170_p, p, tx, ty, tz, 0, 0xFF4444, false, impact > 0 ? (float)size : 0.0f, beam.get(pp), 5));
            }
            if (!(ent != null && (ent instanceof EntityLiving || ent instanceof EntityItem && FurnaceRecipes.func_77602_a().func_151395_a(((EntityItem)ent).func_92059_d()) != null) || mop == null || mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) {
                Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                int meltable = this.isMeltableBlock(bi, md);
                boolean flammable = bi.isFlammable((IBlockAccess)p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.UNKNOWN);
                if (meltable > 0 || flammable || FurnaceRecipes.func_77602_a().func_151395_a(new ItemStack(bi, 1, md)) != null) {
                    int pot = wand.getFocusPotency(stack);
                    float speed = 0.15f + (float)pot * 0.05f;
                    float hardness = 2.0f;
                    if (meltable > 0 || bi.isFlammable((IBlockAccess)p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, ForgeDirection.UP)) {
                        hardness = 0.25f;
                    }
                    if (meltable == 6) {
                        hardness = 20.0f;
                    }
                    if (lastX.get(pp) == mop.field_72311_b && lastY.get(pp) == mop.field_72312_c && lastZ.get(pp) == mop.field_72309_d) {
                        float bc = breakcount.get(pp).floatValue();
                        if (p.field_70170_p.field_72995_K && bc > 0.0f && bi != null) {
                            int gt = (int)(bc / hardness * 9.0f);
                            ThaumicHorizons.proxy.smeltFX(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, p.field_70170_p, 15, size > 2);
                        }
                        if (p.field_70170_p.field_72995_K) {
                            if (bc >= hardness) {
                                p.field_70170_p.func_72926_e(2001, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 0);
                                breakcount.put(pp, Float.valueOf(0.0f));
                            } else {
                                breakcount.put(pp, Float.valueOf(bc + speed));
                            }
                        } else if (bc >= hardness) {
                            this.processBlock(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, wand, stack, p, pp);
                            if (size > 2) {
                                for (int x = -1; x < 2; ++x) {
                                    for (int y = -1; y < 2; ++y) {
                                        for (int z = -1; z < 2; ++z) {
                                            if (x == 0 && y == 0 && z == 0) continue;
                                            this.processBlock(mop.field_72311_b + x, mop.field_72312_c + y, mop.field_72309_d + z, wand, stack, p, pp);
                                        }
                                    }
                                }
                            }
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
            } else if (ent != null && ent instanceof EntityLiving && wand.consumeAllVis(stack, p, costCritter, true, true)) {
                ThaumicHorizons.proxy.smeltFX(ent.field_70165_t - 0.5, ent.field_70163_u, ent.field_70161_v - 0.5, p.field_70170_p, 5, false);
                ((EntityLiving)ent).func_70097_a(DamageSource.field_76372_a, 1.0f + 0.5f * (float)wand.getFocusPotency(stack));
            } else if (ent != null && ent instanceof EntityItem && FurnaceRecipes.func_77602_a().func_151395_a(((EntityItem)ent).func_92059_d()) != null) {
                int num = ((EntityItem)ent).func_92059_d().field_77994_a;
                if (wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
                    ThaumicHorizons.proxy.smeltFX(ent.field_70165_t - 0.5, ent.field_70163_u, ent.field_70161_v - 0.5, p.field_70170_p, 5 * num, false);
                    ItemStack stacky = FurnaceRecipes.func_77602_a().func_151395_a(((EntityItem)ent).func_92059_d());
                    stacky.field_77994_a = num;
                    ((EntityItem)ent).func_92058_a(stacky);
                }
            } else {
                lastX.put(pp, Integer.MAX_VALUE);
                lastY.put(pp, Integer.MAX_VALUE);
                lastZ.put(pp, Integer.MAX_VALUE);
                breakcount.put(pp, Float.valueOf(0.0f));
            }
        }
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
    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack focusstack) {
        return ItemFocusBasic.WandFocusAnimation.CHARGE;
    }

    public int isMeltableBlock(Block block, int meta) {
        if (block == Blocks.field_150348_b) {
            return 6;
        }
        Material mat = block.func_149688_o();
        if (mat == Material.field_151590_u) {
            return 4;
        }
        if (mat == Material.field_151577_b) {
            return 3;
        }
        if (mat == Material.field_151588_w || mat == Material.field_151598_x) {
            return 2;
        }
        if (mat == Material.field_151596_z || mat == Material.field_151584_j || mat == Material.field_151585_k || mat == Material.field_151597_y || mat == Material.field_151582_l || mat == Material.field_151569_G) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getFocusColor(ItemStack focusstack) {
        return 0xFF0000;
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, nuggets};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, nuggets};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, nuggets};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, purity};
            }
        }
        return null;
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.focusLiquefaction.name");
    }

    public static Entity getPointedEntity(World world, EntityLivingBase entityplayer, double range) {
        Entity pointedEntity = null;
        Vec3 vec3d = Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v);
        Vec3 vec3d1 = entityplayer.func_70040_Z();
        Vec3 vec3d2 = vec3d.func_72441_c(vec3d1.field_72450_a * range, vec3d1.field_72448_b * range, vec3d1.field_72449_c * range);
        float f1 = 1.1f;
        List list = world.func_72839_b((Entity)entityplayer, entityplayer.field_70121_D.func_72321_a(vec3d1.field_72450_a * range, vec3d1.field_72448_b * range, vec3d1.field_72449_c * range).func_72314_b((double)f1, (double)f1, (double)f1));
        double d2 = 0.0;
        for (int i = 0; i < list.size(); ++i) {
            double d3;
            Entity entity = (Entity)list.get(i);
            if (world.func_72901_a(Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v), Vec3.func_72443_a((double)entity.field_70165_t, (double)(entity.field_70163_u + (double)entity.func_70047_e()), (double)entity.field_70161_v), false) != null) continue;
            float f2 = Math.max(0.8f, entity.func_70111_Y());
            AxisAlignedBB axisalignedbb = entity.field_70121_D.func_72314_b((double)f2, (double)f2, (double)f2);
            MovingObjectPosition movingobjectposition = axisalignedbb.func_72327_a(vec3d, vec3d2);
            if (axisalignedbb.func_72318_a(vec3d)) {
                if (!(0.0 < d2) && d2 != 0.0) continue;
                pointedEntity = entity;
                d2 = 0.0;
                continue;
            }
            if (movingobjectposition == null || !((d3 = vec3d.func_72438_d(movingobjectposition.field_72307_f)) < d2) && d2 != 0.0) continue;
            pointedEntity = entity;
            d2 = d3;
        }
        return pointedEntity;
    }

    void processBlock(int x, int y, int z, ItemWandCasting wand, ItemStack stack, EntityPlayer p, String pp) {
        BlockEvent.BreakEvent event;
        WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
        if (p.field_71075_bZ.field_75099_e) {
            if (p.field_71075_bZ.field_75098_d) {
                gt = WorldSettings.GameType.CREATIVE;
            }
        } else {
            gt = WorldSettings.GameType.ADVENTURE;
        }
        if ((event = ForgeHooks.onBlockBreakEvent((World)p.field_70170_p, (WorldSettings.GameType)gt, (EntityPlayerMP)((EntityPlayerMP)p), (int)x, (int)y, (int)z)).isCanceled()) {
            return;
        }
        Block bi = p.field_70170_p.func_147439_a(x, y, z);
        if (bi.func_149712_f(p.field_70170_p, x, y, z) == -1.0f) {
            return;
        }
        int md = p.field_70170_p.func_72805_g(x, y, z);
        int meltable = this.isMeltableBlock(bi, md);
        boolean flammable = bi.isFlammable((IBlockAccess)p.field_70170_p, x, y, z, ForgeDirection.UNKNOWN);
        ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(new ItemStack(bi, 1, md));
        if (result != null && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
            if (this.getUpgradeLevel(wand.getFocusItem(stack), purity) > 0 && Utils.findSpecialMiningResult((ItemStack)new ItemStack(bi, 1, md), (float)9999.0f, (Random)p.field_70170_p.field_73012_v) != null) {
                ItemStack pure = Utils.findSpecialMiningResult((ItemStack)new ItemStack(bi, 1, md), (float)9999.0f, (Random)p.field_70170_p.field_73012_v);
                if (FurnaceRecipes.func_77602_a().func_151395_a(pure) != null) {
                    result = FurnaceRecipes.func_77602_a().func_151395_a(pure);
                }
            }
            if (this.getUpgradeLevel(wand.getFocusItem(stack), nuggets) > 0 && ThaumcraftApi.getSmeltingBonus(new ItemStack(bi, 1, md)) != null) {
                ItemStack bonus = ThaumcraftApi.getSmeltingBonus(new ItemStack(bi, 1, md)).func_77946_l();
                for (int i = 0; i < this.getUpgradeLevel(wand.getFocusItem(stack), nuggets); ++i) {
                    if (!(p.field_70170_p.field_73012_v.nextFloat() < 0.45f)) continue;
                    ++bonus.field_77994_a;
                }
                if (bonus.field_77994_a > 0) {
                    EntityItem bonusEntity = new EntityItem(p.field_70170_p, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), bonus);
                    p.field_70170_p.func_72838_d((Entity)bonusEntity);
                }
            }
            if (result.func_77973_b() instanceof ItemBlock) {
                p.field_70170_p.func_147449_b(x, y, z, Block.func_149634_a((Item)result.func_77973_b()));
                p.field_70170_p.func_72921_c(x, y, z, result.func_77960_j(), 3);
            } else {
                p.field_70170_p.func_147468_f(x, y, z);
                EntityItem theItem = new EntityItem(p.field_70170_p, (double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f));
                theItem.func_92058_a(result.func_77946_l());
                p.field_70170_p.func_72838_d((Entity)theItem);
            }
        } else if (meltable > 0 && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
            if (meltable == 1) {
                p.field_70170_p.func_147468_f(x, y, z);
            } else if (meltable == 2) {
                if (p.field_70170_p.field_73011_w.field_76574_g != -1) {
                    p.field_70170_p.func_147465_d(x, y, z, Blocks.field_150355_j, 0, 3);
                } else {
                    p.field_70170_p.func_147468_f(x, y, z);
                }
            } else if (meltable == 3) {
                p.field_70170_p.func_147465_d(x, y, z, Blocks.field_150346_d, 0, 3);
            } else if (meltable == 4) {
                Blocks.field_150335_W.func_149664_b(p.field_70170_p, x, y, z, 1);
                p.field_70170_p.func_147468_f(x, y, z);
            } else if (meltable == 6) {
                p.field_70170_p.func_147465_d(x, y, z, Blocks.field_150353_l, 0, 3);
            }
        } else if (flammable && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
            p.field_70170_p.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);
        }
        lastX.put(pp, Integer.MAX_VALUE);
        lastY.put(pp, Integer.MAX_VALUE);
        lastZ.put(pp, Integer.MAX_VALUE);
        breakcount.put(pp, Float.valueOf(0.0f));
        p.field_70170_p.func_147471_g(x, y, z);
    }
}

