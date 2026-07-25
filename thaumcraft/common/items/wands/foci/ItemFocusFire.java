/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.projectile.EntityEmber;
import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

public class ItemFocusFire
extends ItemFocusBasic {
    private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 10);
    private static final AspectList costBeam = new AspectList().add(Aspect.FIRE, 10).add(Aspect.ORDER, 3);
    private static final AspectList costBall = new AspectList().add(Aspect.FIRE, 66).add(Aspect.ENTROPY, 33);
    long soundDelay = 0L;
    public static FocusUpgradeType fireball = new FocusUpgradeType(9, new ResourceLocation("thaumcraft", "textures/foci/fireball.png"), "focus.upgrade.fireball.name", "focus.upgrade.fireball.text", new AspectList().add(Aspect.DARKNESS, 1));
    public static FocusUpgradeType firebeam = new FocusUpgradeType(10, new ResourceLocation("thaumcraft", "textures/foci/firebeam.png"), "focus.upgrade.firebeam.name", "focus.upgrade.firebeam.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.AIR, 1));

    public ItemFocusFire() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_fire");
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "AF" + super.getSortingHelper(itemstack);
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 15028484;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, firebeam) ? costBeam : (this.isUpgradedWith(itemstack, fireball) ? costBall : costBase);
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return this.isUpgradedWith(focusstack, fireball) ? 1000 : 0;
    }

    @Override
    public boolean isVisCostPerTick(ItemStack itemstack) {
        return true;
    }

    @Override
    public ItemFocusBasic.WandFocusAnimation getAnimation(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, fireball) ? ItemFocusBasic.WandFocusAnimation.WAVE : ItemFocusBasic.WandFocusAnimation.CHARGE;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition movingobjectposition) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (this.isUpgradedWith(wand.getFocusItem(itemstack), fireball)) {
            if (wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), !p.field_70170_p.field_72995_K, false)) {
                if (!world.field_72995_K) {
                    EntityExplosiveOrb orb = new EntityExplosiveOrb(world, (EntityLivingBase)p);
                    orb.strength += (float)wand.getFocusPotency(itemstack) * 0.4f;
                    orb.onFire = this.isUpgradedWith(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfire);
                    world.func_72838_d((Entity)orb);
                    world.func_72889_a((EntityPlayer)null, 1009, (int)p.field_70165_t, (int)p.field_70163_u, (int)p.field_70161_v, 0);
                }
                p.func_71038_i();
            }
        } else {
            p.func_71008_a(itemstack, Integer.MAX_VALUE);
            WandManager.setCooldown((EntityLivingBase)p, -1);
        }
        return itemstack;
    }

    @Override
    public void onUsingFocusTick(ItemStack itemstack, EntityPlayer p, int count) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (!wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), false, false)) {
            p.func_71034_by();
            return;
        }
        int range = 17;
        Vec3 vec3d = p.func_70676_i((float)range);
        if (!p.field_70170_p.field_72995_K && this.soundDelay < System.currentTimeMillis()) {
            p.field_70170_p.func_72956_a((Entity)p, "thaumcraft:fireloop", 0.33f, 2.0f);
            this.soundDelay = System.currentTimeMillis() + 500L;
        }
        if (!p.field_70170_p.field_72995_K && wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), true, false)) {
            float scatter = this.isUpgradedWith(wand.getFocusItem(itemstack), firebeam) ? 0.25f : 15.0f;
            for (int a = 0; a < 2 + wand.getFocusPotency(itemstack); ++a) {
                EntityEmber orb = new EntityEmber(p.field_70170_p, (EntityLivingBase)p, scatter);
                orb.damage = 2 + wand.getFocusPotency(itemstack);
                if (this.isUpgradedWith(wand.getFocusItem(itemstack), firebeam)) {
                    orb.damage += 0.5f;
                    orb.damage *= 1.5f;
                    orb.duration = 30;
                }
                orb.firey = this.getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfire);
                orb.field_70165_t += orb.field_70159_w;
                orb.field_70163_u += orb.field_70181_x;
                orb.field_70161_v += orb.field_70179_y;
                p.field_70170_p.func_72838_d((Entity)orb);
            }
        }
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, fireball, firebeam};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfire};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
        }
        return null;
    }

    @Override
    public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
        return !type.equals(FocusUpgradeType.alchemistsfire) || !this.isUpgradedWith(focusstack, fireball) || !this.isUpgradedWith(focusstack, FocusUpgradeType.alchemistsfire);
    }
}

