/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import com.kentington.thaumichorizons.common.lib.SelfInfusionRecipe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class ItemInfusionSelfCheat
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon1;
    public IIcon icon2;
    public IIcon icon3;
    public IIcon icon4;
    public IIcon icon5;
    public IIcon icon6;
    public IIcon icon7;
    public IIcon icon8;
    public IIcon icon9;
    public IIcon icon10;

    public ItemInfusionSelfCheat() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon1 = ir.func_94245_a("thaumichorizons:quicksilverlimbs");
        this.icon2 = ir.func_94245_a("thaumichorizons:morphicfingers");
        this.icon3 = ir.func_94245_a("thaumichorizons:awakenedblood");
        this.icon4 = ir.func_94245_a("thaumichorizons:diamondskin");
        this.icon5 = ir.func_94245_a("thaumichorizons:silverwoodheart");
        this.icon6 = ir.func_94245_a("thaumichorizons:synthskin");
        this.icon7 = ir.func_94245_a("fish_cod_raw");
        this.icon8 = ir.func_94245_a("thaumichorizons:warpedtumor");
        this.icon9 = ir.func_94245_a("thaumichorizons:spiderclimb");
        this.icon10 = ir.func_94245_a("thaumichorizons:chameleonskin");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        switch (par1) {
            case 1: {
                return this.icon1;
            }
            case 2: {
                return this.icon2;
            }
            case 3: {
                return this.icon3;
            }
            case 4: {
                return this.icon4;
            }
            case 5: {
                return this.icon5;
            }
            case 6: {
                return this.icon6;
            }
            case 7: {
                return this.icon7;
            }
            case 8: {
                return this.icon8;
            }
            case 9: {
                return this.icon9;
            }
            case 10: {
                return this.icon10;
            }
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 1));
        par3List.add(new ItemStack((Item)this, 1, 2));
        par3List.add(new ItemStack((Item)this, 1, 3));
        par3List.add(new ItemStack((Item)this, 1, 4));
        par3List.add(new ItemStack((Item)this, 1, 5));
        par3List.add(new ItemStack((Item)this, 1, 6));
        par3List.add(new ItemStack((Item)this, 1, 7));
        par3List.add(new ItemStack((Item)this, 1, 8));
        par3List.add(new ItemStack((Item)this, 1, 9));
        par3List.add(new ItemStack((Item)this, 1, 10));
    }

    public String func_77653_i(ItemStack stack) {
        String stringy = "";
        switch (stack.func_77960_j()) {
            case 1: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.quicksilver");
                break;
            }
            case 2: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.morphic");
                break;
            }
            case 3: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.awakeBlood");
                break;
            }
            case 4: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.diamondSkin");
                break;
            }
            case 5: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.silverHeart");
                break;
            }
            case 6: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.synthSkin");
                break;
            }
            case 7: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.amphibious");
                break;
            }
            case 8: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.warpedTumor");
                break;
            }
            case 9: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.spiderClimb");
                break;
            }
            case 10: {
                stringy = stringy + StatCollector.func_74838_a((String)"selfInfusions.chameleonSkin");
            }
        }
        return stringy;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World world, EntityPlayer p) {
        EntityPlayer critter = p;
        for (SelfInfusionRecipe recipe : ThaumicHorizons.selfRecipes) {
            if (recipe.getID() != p_77659_1_.func_77960_j() || ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(recipe.getID())) continue;
            ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).addPlayerInfusion(recipe.getID());
            ThaumicHorizons.instance.eventHandlerEntity.applyInfusions((EntityLivingBase)critter);
            Thaumcraft.proxy.burst(world, critter.field_70165_t, critter.field_70163_u + (double)critter.func_70047_e(), critter.field_70161_v, 1.0f);
            return p_77659_1_;
        }
        return p_77659_1_;
    }
}

