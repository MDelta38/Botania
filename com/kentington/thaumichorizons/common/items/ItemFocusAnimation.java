/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusAnimation
extends ItemFocusBasic {
    private static final AspectList cost = new AspectList().add(Aspect.FIRE, 1000).add(Aspect.AIR, 1000).add(Aspect.ORDER, 1000).add(Aspect.WATER, 1000).add(Aspect.EARTH, 1000).add(Aspect.ENTROPY, 1000);
    public static FocusUpgradeType berserk = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/berserk.png"), "focus.upgrade.berserk.name", "focus.upgrade.berserk.text", new AspectList().add(Aspect.WEAPON, 8));
    public static FocusUpgradeType detonation = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/detonation.png"), "focus.upgrade.detonation.name", "focus.upgrade.detonation.text", new AspectList().add(Aspect.WEAPON, 8));
    public IIcon ornamentIcon;

    public ItemFocusAnimation() {
        this.func_77656_e(0);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @Override
    public IIcon getOrnament(ItemStack focusstack) {
        return this.ornamentIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:focus_animation");
        this.ornamentIcon = ir.func_94245_a("thaumcraft:focus_whatever_orn");
    }

    public String func_77653_i(ItemStack stack) {
        return StatCollector.func_74838_a((String)"item.focusAnimation.name");
    }

    @Override
    public int getFocusColor(ItemStack focusstack) {
        return 15054592;
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.extend};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.extend};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.extend, berserk};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.extend};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.extend, detonation};
            }
        }
        return null;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        int md;
        int z;
        int y;
        int x;
        Block blocky;
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && !(blocky = world.func_147439_a(x = mop.field_72311_b, y = mop.field_72312_c, z = mop.field_72309_d)).hasTileEntity(md = world.func_72805_g(x, y, z)) && !blocky.isAir((IBlockAccess)world, x, y, z) && (blocky.func_149662_c() || ItemFocusAnimation.isWhitelisted(blocky, md)) && blocky.func_149712_f(world, x, y, z) != -1.0f) {
            WorldSettings.GameType gt = WorldSettings.GameType.SURVIVAL;
            if (player.field_71075_bZ.field_75099_e) {
                if (player.field_71075_bZ.field_75098_d) {
                    gt = WorldSettings.GameType.CREATIVE;
                }
            } else {
                gt = WorldSettings.GameType.ADVENTURE;
            }
            if (!world.field_72995_K) {
                EntityGolemTH golem = new EntityGolemTH(world);
                golem.loadGolem((double)x + 0.5, y, (double)z + 0.5, blocky, md, 600 + wand.getFocusExtend(itemstack) * 200, false, this.getUpgradeLevel(wand.getFocusItem(itemstack), berserk) > 0, this.getUpgradeLevel(wand.getFocusItem(itemstack), detonation) > 0);
                AspectList cost = new AspectList().add(Aspect.FIRE, golem.type.visCost).add(Aspect.ORDER, golem.type.visCost).add(Aspect.AIR, golem.type.visCost).add(Aspect.EARTH, golem.type.visCost).add(Aspect.ENTROPY, golem.type.visCost).add(Aspect.WATER, golem.type.visCost);
                if (!wand.consumeAllVis(itemstack, player, cost, false, false)) {
                    golem.func_70106_y();
                    return itemstack;
                }
                BlockEvent.BreakEvent event = ForgeHooks.onBlockBreakEvent((World)player.field_70170_p, (WorldSettings.GameType)gt, (EntityPlayerMP)((EntityPlayerMP)player), (int)x, (int)y, (int)z);
                if (event.isCanceled() || !wand.consumeAllVis(itemstack, player, cost, true, false)) {
                    golem.func_70106_y();
                    return itemstack;
                }
                world.func_147468_f(x, y, z);
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                golem.func_110171_b((int)golem.field_70165_t, (int)golem.field_70163_u, (int)golem.field_70161_v, 32);
                golem.setOwner(player.func_70005_c_());
                world.func_72838_d((Entity)golem);
                world.func_72960_a((Entity)golem, (byte)7);
            } else {
                Minecraft.func_71410_x().field_71452_i.func_147215_a(x, y, z, blocky, md);
                player.func_71038_i();
            }
        }
        return itemstack;
    }

    public static boolean isWhitelisted(Block blocky, int md) {
        return blocky == Blocks.field_150414_aQ || blocky == Blocks.field_150434_aF || blocky == Blocks.field_150359_w || blocky == Blocks.field_150403_cj || blocky == Blocks.field_150432_aD || blocky == Blocks.field_150321_G || blocky == ConfigBlocks.blockCosmeticOpaque && md < 2 || blocky == ConfigBlocks.blockWoodenDevice && md == 6 || md == 7;
    }
}

