/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemFocusIllumination
extends ItemFocusBasic {
    public static FocusUpgradeType solar = new FocusUpgradeType(FocusUpgradeType.types.length, new ResourceLocation("thaumichorizons", "textures/foci/solar.png"), "focus.upgrade.solar.name", "focus.upgrade.solar.text", new AspectList().add(Aspect.ORDER, 8).add(Aspect.VOID, 4));
    public static final int[] colors = new int[]{0x1E1B1B, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 0xABABAB, 0x434343, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 0xF0F0F0};
    private static final AspectList cost = new AspectList().add(Aspect.FIRE, 100).add(Aspect.AIR, 100);
    public static final IIcon[] icons = new IIcon[16];

    public ItemFocusIllumination() {
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        for (int i = 0; i < 16; ++i) {
            ItemFocusIllumination.icons[i] = ir.func_94245_a("thaumichorizons:focus_illumination." + i);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int damage) {
        if (damage > 15) {
            return null;
        }
        return icons[damage];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < 16; ++i) {
            p_150895_3_.add(new ItemStack(p_150895_1_, 1, i));
        }
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "I" + itemstack.func_77960_j() + super.getSortingHelper(itemstack);
    }

    public String func_77653_i(ItemStack p_77653_1_) {
        return StatCollector.func_74838_a((String)("item.focusIllumination." + p_77653_1_.func_77960_j() + ".name"));
    }

    @Override
    public int getFocusColor(ItemStack focusstack) {
        if (focusstack.func_77960_j() < 16) {
            return colors[focusstack.func_77960_j()];
        }
        return 420;
    }

    @Override
    public AspectList getVisCost(ItemStack focusstack) {
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack focusstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, solar};
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

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = mop.field_72311_b;
            int y = mop.field_72312_c;
            int z = mop.field_72309_d;
            switch (mop.field_72310_e) {
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
            if (world.func_147437_c(x, y, z) && wand.consumeAllVis(itemstack, player, this.getVisCost(itemstack), true, false)) {
                if (this.getUpgradeLevel(wand.getFocusItem(itemstack), solar) > 0) {
                    world.func_147465_d(x, y, z, ThaumicHorizons.blockLightSolar, wand.getFocusItem(itemstack).func_77960_j(), 3);
                } else {
                    world.func_147465_d(x, y, z, ThaumicHorizons.blockLight, wand.getFocusItem(itemstack).func_77960_j(), 3);
                }
                player.func_71038_i();
                if (!world.field_72995_K) {
                    world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                }
            }
        }
        return itemstack;
    }
}

