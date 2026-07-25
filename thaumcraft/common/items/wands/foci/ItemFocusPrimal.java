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
import java.util.Random;
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
import thaumcraft.common.entities.projectile.EntityPrimalOrb;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusPrimal
extends ItemFocusBasic {
    IIcon depthIcon = null;
    public static FocusUpgradeType seeker = new FocusUpgradeType(16, new ResourceLocation("thaumcraft", "textures/foci/seeker.png"), "focus.upgrade.seeker.name", "focus.upgrade.seeker.text", new AspectList().add(Aspect.SENSES, 1).add(Aspect.MIND, 1));

    public ItemFocusPrimal() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "FP" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_primal");
        this.depthIcon = ir.func_94245_a("thaumcraft:focus_primal_depth");
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @Override
    public int getActivationCooldown(ItemStack focusstack) {
        return 500;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer p, MovingObjectPosition mob) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        EntityPrimalOrb shard = new EntityPrimalOrb(world, (EntityLivingBase)p, this.isUpgradedWith(wand.getFocusItem(itemstack), seeker));
        if (!world.field_72995_K && wand.consumeAllVis(itemstack, p, this.getVisCost(itemstack), true, false)) {
            world.func_72838_d((Entity)shard);
            world.func_72956_a((Entity)shard, "thaumcraft:ice", 0.3f, 0.8f + world.field_73012_v.nextFloat() * 0.1f);
        }
        p.func_71038_i();
        return itemstack;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 10854849;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        Random rand = new Random(System.currentTimeMillis() / 200L);
        AspectList cost = new AspectList().add(Aspect.WATER, 50 + rand.nextInt(5) * 50).add(Aspect.AIR, 50 + rand.nextInt(5) * 50).add(Aspect.EARTH, 50 + rand.nextInt(5) * 50).add(Aspect.FIRE, 50 + rand.nextInt(5) * 50).add(Aspect.ORDER, 50 + rand.nextInt(5) * 50).add(Aspect.ENTROPY, 50 + rand.nextInt(5) * 50);
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, seeker};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
        }
        return null;
    }
}

