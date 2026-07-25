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
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.projectile.EntityFrostShard;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusFrost
extends ItemFocusBasic {
    private static final AspectList costBase = new AspectList().add(Aspect.WATER, 5).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2);
    private static final AspectList costScatter = new AspectList().add(Aspect.WATER, 20).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2).add(Aspect.AIR, 5);
    private static final AspectList costBoulder = new AspectList().add(Aspect.WATER, 20).add(Aspect.FIRE, 2).add(Aspect.ENTROPY, 2).add(Aspect.EARTH, 5);
    public static FocusUpgradeType scattershot = new FocusUpgradeType(11, new ResourceLocation("thaumcraft", "textures/foci/scattershot.png"), "focus.upgrade.scattershot.name", "focus.upgrade.scattershot.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.WEAPON, 1));
    public static FocusUpgradeType iceboulder = new FocusUpgradeType(12, new ResourceLocation("thaumcraft", "textures/foci/iceboulder.png"), "focus.upgrade.iceboulder.name", "focus.upgrade.iceboulder.text", new AspectList().add(Aspect.COLD, 1).add(Aspect.CRYSTAL, 1));

    public ItemFocusFrost() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BF" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_frost");
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (!world.field_72995_K && wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), true, false)) {
            int frosty = this.getUpgradeLevel(wand.getFocusItem(itemstack), FocusUpgradeType.alchemistsfrost);
            EntityFrostShard shard = null;
            if (this.isUpgradedWith(wand.getFocusItem(itemstack), scattershot)) {
                for (int a = 0; a < 5 + wand.getFocusPotency(itemstack) * 2; ++a) {
                    shard = new EntityFrostShard(world, (EntityLivingBase)p, 8.0f);
                    shard.setDamage(1.0f);
                    shard.fragile = true;
                    shard.setFrosty(frosty);
                    world.func_72838_d((Entity)shard);
                }
            } else if (this.isUpgradedWith(wand.getFocusItem(itemstack), iceboulder)) {
                shard = new EntityFrostShard(world, (EntityLivingBase)p, 1.0f);
                shard.setDamage(4 + wand.getFocusPotency(itemstack) * 2);
                shard.bounce = 0.8;
                shard.bounceLimit = 6;
                shard.setFrosty(frosty);
                world.func_72838_d((Entity)shard);
            } else {
                shard = new EntityFrostShard(world, (EntityLivingBase)p, 1.0f);
                shard.setDamage((float)(3.0 + (double)wand.getFocusPotency(itemstack) * 1.5));
                shard.setFrosty(frosty);
                world.func_72838_d((Entity)shard);
            }
            world.func_72956_a((Entity)shard, "thaumcraft:ice", 0.4f, 1.0f + world.field_73012_v.nextFloat() * 0.1f);
        }
        p.func_71038_i();
        return itemstack;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 5204428;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, scattershot) ? costScatter : (this.isUpgradedWith(itemstack, iceboulder) ? costBoulder : costBase);
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return this.getUpgradeLevel(focusstack, scattershot) > 0 || this.getUpgradeLevel(focusstack, iceboulder) > 0 ? 500 : 200;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, scattershot, iceboulder, FocusUpgradeType.alchemistsfrost};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.alchemistsfrost};
            }
        }
        return null;
    }
}

