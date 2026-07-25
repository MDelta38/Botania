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
 *  net.minecraft.util.IIcon
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
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.projectile.EntityPechBlast;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusPech
extends ItemFocusBasic {
    IIcon depthIcon = null;
    private static final AspectList cost = new AspectList().add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 10).add(Aspect.WATER, 10);
    private static final AspectList costAll = new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10).add(Aspect.WATER, 10);
    public static FocusUpgradeType nightshade = new FocusUpgradeType(15, new ResourceLocation("thaumcraft", "textures/foci/nightshade.png"), "focus.upgrade.nightshade.name", "focus.upgrade.nightshade.text", new AspectList().add(Aspect.LIFE, 1).add(Aspect.POISON, 1).add(Aspect.MAGIC, 1));

    public ItemFocusPech() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "PP" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_pech");
        this.depthIcon = ir.func_94245_a("thaumcraft:focus_pech_depth");
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return 250;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        EntityPechBlast blast = new EntityPechBlast(world, (EntityLivingBase)p, wand.getFocusPotency(itemstack), wand.getFocusExtend(itemstack), this.isUpgradedWith(wand.getFocusItem(itemstack), nightshade));
        if (!world.field_72995_K && wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), true, false)) {
            world.func_72838_d((Entity)blast);
            world.func_72956_a((Entity)blast, "thaumcraft:ice", 0.4f, 1.0f + world.field_73012_v.nextFloat() * 0.1f);
        }
        p.func_71038_i();
        return itemstack;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 0x229944;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return this.isUpgradedWith(itemstack, nightshade) ? costAll : cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, FocusUpgradeType.extend};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.potency, nightshade};
            }
        }
        return null;
    }
}

