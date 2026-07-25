/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemCrystalEssence
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.lib.utils.BlockUtils
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityItemInvulnerable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemFocusDisintegration
extends ItemFocusBasic {
    public static FocusUpgradeType enervation = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/enervation.png"), "focus.upgrade.enervation.name", "focus.upgrade.enervation.text", new AspectList().add(Aspect.DEATH, 8));
    private static final AspectList cost = new AspectList().add(Aspect.FIRE, 30).add(Aspect.ENTROPY, 30).add(Aspect.EARTH, 30);
    private static final AspectList costCritter = new AspectList().add(Aspect.FIRE, 12).add(Aspect.ENTROPY, 12).add(Aspect.EARTH, 12);
    static HashMap<String, Long> soundDelay = new HashMap();
    static HashMap<String, Object> beam = new HashMap();
    static HashMap<String, Float> breakcount = new HashMap();
    static HashMap<String, Integer> lastX = new HashMap();
    static HashMap<String, Integer> lastY = new HashMap();
    static HashMap<String, Integer> lastZ = new HashMap();

    public ItemFocusDisintegration() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:focus_disintegration");
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "DS" + super.getSortingHelper(itemstack);
    }

    public int getFocusColor() {
        return 7930047;
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
            Entity ent = ItemFocusDisintegration.getPointedEntity(p.field_70170_p, (EntityLivingBase)p, 10.0);
            Vec3 v = p.func_70040_Z();
            double tx = p.field_70165_t + v.field_72450_a * 10.0;
            double ty = p.field_70163_u + v.field_72448_b * 10.0;
            double tz = p.field_70161_v + v.field_72449_c * 10.0;
            int impact = 0;
            if ((ent == null || ent instanceof EntityItem && (this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()) == null || this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()).size() == 0)) && mop != null) {
                tx = mop.field_72307_f.field_72450_a;
                ty = mop.field_72307_f.field_72448_b;
                tz = mop.field_72307_f.field_72449_c;
                impact = 5;
                if (!p.field_70170_p.field_72995_K && soundDelay.get(pp) < System.currentTimeMillis()) {
                    p.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:zap", 0.3f, 1.0f);
                    soundDelay.put(pp, System.currentTimeMillis() + 100L);
                }
            } else if (ent != null) {
                tx = ent.field_70165_t;
                ty = ent.field_70163_u;
                tz = ent.field_70161_v;
                impact = 5;
                if (!p.field_70170_p.field_72995_K && soundDelay.get(pp) < System.currentTimeMillis()) {
                    p.field_70170_p.func_72908_a(tx, ty, tz, "thaumcraft:zap", 0.3f, 1.0f);
                    soundDelay.put(pp, System.currentTimeMillis() + 100L);
                }
            }
            if (p.field_70170_p.field_72995_K) {
                beam.put(pp, Thaumcraft.proxy.beamCont(p.field_70170_p, p, tx, ty, tz, 0, 7930047, false, impact > 0 ? (float)size : 0.0f, beam.get(pp), 5));
            }
            if (!(ent != null && (ent instanceof EntityLiving || ent instanceof EntityItem && ((EntityItem)ent).func_92059_d().func_77973_b() != ConfigItems.itemCrystalEssence && this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()) != null && this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()).size() != 0) || mop == null || mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK)) {
                Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                if (Item.func_150898_a((Block)bi) != null && this.getAspects(Item.func_150898_a((Block)bi), md) != null) {
                    int pot = wand.getFocusPotency(stack);
                    float speed = 0.05f + (float)pot * 0.015f;
                    float hardness = bi.func_149712_f(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                    if (hardness == -1.0f) {
                        return;
                    }
                    if (lastX.get(pp) == mop.field_72311_b && lastY.get(pp) == mop.field_72312_c && lastZ.get(pp) == mop.field_72309_d) {
                        float bc = breakcount.get(pp).floatValue();
                        if (p.field_70170_p.field_72995_K && bc > 0.0f && bi != null) {
                            int gt = (int)(bc / hardness * 9.0f);
                            ThaumicHorizons.proxy.disintegrateFX(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, p, 15, size > 2);
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
                if (this.getUpgradeLevel(wand.getFocusItem(stack), enervation) > 0) {
                    ((EntityLiving)ent).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 40, 0));
                }
                ThaumicHorizons.proxy.disintegrateFX(ent.field_70165_t - 0.5, ent.field_70163_u, ent.field_70161_v - 0.5, p, 5, false);
                ((EntityLiving)ent).func_70097_a(DamageSourceThaumcraft.dissolve, 0.5f + 0.25f * (float)wand.getFocusPotency(stack) + 0.5f * (float)this.getUpgradeLevel(wand.getFocusItem(stack), enervation));
            } else if (ent != null && ent instanceof EntityItem && this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()) != null && this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j()).size() > 0) {
                int num = ((EntityItem)ent).func_92059_d().field_77994_a;
                if (wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
                    ThaumicHorizons.proxy.disintegrateFX(ent.field_70165_t - 0.5, ent.field_70163_u, ent.field_70161_v - 0.5, p, 5 * num, false);
                    AspectList aspects = this.getAspects(((EntityItem)ent).func_92059_d().func_77973_b(), ((EntityItem)ent).func_92059_d().func_77960_j());
                    if (aspects != null && !p.field_70170_p.field_72995_K) {
                        if (!p.field_70170_p.field_72995_K) {
                            for (Aspect asp : aspects.getAspects()) {
                                stack = new ItemStack(ConfigItems.itemCrystalEssence, aspects.getAmount(asp) * num);
                                ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(asp, 1));
                                p.field_70170_p.func_72838_d((Entity)new EntityItemInvulnerable(p.field_70170_p, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, stack));
                            }
                        } else {
                            ThaumicHorizons.proxy.disintegrateExplodeFX(p.field_70170_p, ent.field_70165_t, ent.field_70163_u, ent.field_70161_v);
                        }
                    }
                }
                ent.func_70106_y();
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

    @Override
    public int getFocusColor(ItemStack focusstack) {
        return 7930047;
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.enlarge};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, enervation};
            }
        }
        return null;
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.focusDisintegration.name");
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
            if (entity instanceof EntityItem && ((EntityItem)entity).func_92059_d().func_77973_b() == ConfigItems.itemCrystalEssence || world.func_72901_a(Vec3.func_72443_a((double)entityplayer.field_70165_t, (double)(entityplayer.field_70163_u + (double)entityplayer.func_70047_e()), (double)entityplayer.field_70161_v), Vec3.func_72443_a((double)entity.field_70165_t, (double)(entity.field_70163_u + (double)entity.func_70047_e()), (double)entity.field_70161_v), false) != null) continue;
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
        Block bi;
        WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
        if (p.field_71075_bZ.field_75099_e) {
            if (p.field_71075_bZ.field_75098_d) {
                gt = WorldSettings.GameType.CREATIVE;
            }
        } else {
            gt = WorldSettings.GameType.ADVENTURE;
        }
        if ((bi = p.field_70170_p.func_147439_a(x, y, z)).func_149712_f(p.field_70170_p, x, y, z) == -1.0f) {
            return;
        }
        BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent((World)p.field_70170_p, (WorldSettings.GameType)gt, (EntityPlayerMP)((EntityPlayerMP)p), (int)x, (int)y, (int)z);
        if (event.isCanceled()) {
            return;
        }
        int md = p.field_70170_p.func_72805_g(x, y, z);
        if (Item.func_150898_a((Block)bi) != null && this.getAspects(Item.func_150898_a((Block)bi), md) != null && this.getAspects(Item.func_150898_a((Block)bi), md).size() > 0 && wand.consumeAllVis(stack, p, this.getVisCost(stack), true, true)) {
            AspectList aspects = this.getAspects(Item.func_150898_a((Block)p.field_70170_p.func_147439_a(x, y, z)), p.field_70170_p.func_72805_g(x, y, z));
            if (aspects != null) {
                for (Aspect asp : aspects.getAspects()) {
                    stack = new ItemStack(ConfigItems.itemCrystalEssence, aspects.getAmount(asp));
                    ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(asp, 1));
                    p.field_70170_p.func_72838_d((Entity)new EntityItemInvulnerable(p.field_70170_p, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, stack));
                }
                ThaumicHorizons.proxy.disintegrateExplodeFX(p.field_70170_p, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            }
            p.field_70170_p.func_147468_f(x, y, z);
        }
        lastX.put(pp, Integer.MAX_VALUE);
        lastY.put(pp, Integer.MAX_VALUE);
        lastZ.put(pp, Integer.MAX_VALUE);
        breakcount.put(pp, Float.valueOf(0.0f));
        p.field_70170_p.func_147471_g(x, y, z);
    }

    private AspectList getAspects(Item block, int meta) {
        ItemStack tmpStack = new ItemStack(block, 1, meta);
        AspectList tmp = ThaumcraftCraftingManager.getObjectTags((ItemStack)tmpStack);
        if ((tmp = ThaumcraftCraftingManager.getBonusTags((ItemStack)tmpStack, (AspectList)tmp)) == null || tmp.size() == 0) {
            tmp = ThaumcraftApi.objectTags.get(Arrays.asList(block, Short.MAX_VALUE));
            if (meta == Short.MAX_VALUE && tmp == null) {
                int index = 0;
                do {
                    tmp = ThaumcraftApi.objectTags.get(Arrays.asList(block, index));
                } while (++index < 16 && tmp == null);
            }
        }
        return tmp;
    }
}

