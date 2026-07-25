/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package com.kentington.thaumichorizons.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemDummy
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon iconCow;
    public IIcon iconPig;
    public IIcon iconSheep;
    public IIcon iconChicken;
    public IIcon iconCat;
    public IIcon iconDog;
    public IIcon iconHuman;
    public IIcon iconHorse;
    public IIcon iconSpider;

    public ItemDummy() {
        this.func_77637_a(null);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.iconCow = ir.func_94245_a("thaumichorizons:cow");
        this.iconPig = ir.func_94245_a("thaumichorizons:pig");
        this.iconSheep = ir.func_94245_a("thaumichorizons:sheep");
        this.iconChicken = ir.func_94245_a("thaumichorizons:chicken");
        this.iconCat = ir.func_94245_a("thaumichorizons:cat");
        this.iconDog = ir.func_94245_a("thaumichorizons:wolf");
        this.iconHuman = ir.func_94245_a("thaumichorizons:human");
        this.iconHorse = ir.func_94245_a("thaumichorizons:horse");
        this.iconSpider = ir.func_94245_a("thaumichorizons:spider");
    }

    public String func_77653_i(ItemStack stack) {
        if (stack.func_77978_p() != null) {
            return StatCollector.func_74838_a((String)stack.func_77978_p().func_74779_i("infName"));
        }
        int md = stack.func_77960_j();
        switch (md) {
            case 0: {
                return StatCollector.func_74838_a((String)"entity.Cow.name");
            }
            case 1: {
                return StatCollector.func_74838_a((String)"entity.Pig.name");
            }
            case 2: {
                return StatCollector.func_74838_a((String)"entity.Sheep.name");
            }
            case 3: {
                return StatCollector.func_74838_a((String)"entity.Chicken.name");
            }
            case 4: {
                return StatCollector.func_74838_a((String)"entity.Cat.name");
            }
            case 5: {
                return StatCollector.func_74838_a((String)"entity.Wolf.name");
            }
            case 6: {
                return StatCollector.func_74838_a((String)"entity.horse.name");
            }
            case 8: {
                return StatCollector.func_74838_a((String)"entity.Spider.name");
            }
            case 15: {
                return StatCollector.func_74838_a((String)"thaumichorizons.revived");
            }
        }
        return "Creature";
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int md) {
        switch (md) {
            case 0: {
                return this.iconCow;
            }
            case 1: {
                return this.iconPig;
            }
            case 2: {
                return this.iconSheep;
            }
            case 3: {
                return this.iconChicken;
            }
            case 4: {
                return this.iconCat;
            }
            case 5: {
                return this.iconDog;
            }
            case 6: {
                return this.iconHorse;
            }
            case 8: {
                return this.iconSpider;
            }
            case 15: {
                return this.iconHuman;
            }
        }
        return this.iconCat;
    }
}

