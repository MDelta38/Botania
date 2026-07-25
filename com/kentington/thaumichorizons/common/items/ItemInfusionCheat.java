/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.entities.golems.EntityGolemBase
 */
package com.kentington.thaumichorizons.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemFocusContainment;
import com.kentington.thaumichorizons.common.lib.CreatureInfusionRecipe;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class ItemInfusionCheat
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

    public ItemInfusionCheat() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon1 = ir.func_94245_a("thaumichorizons:quicksilverlimbs");
        this.icon2 = ir.func_94245_a("thaumichorizons:thaumclaws");
        this.icon3 = ir.func_94245_a("thaumichorizons:awakenedblood");
        this.icon4 = ir.func_94245_a("thaumichorizons:diamondskin");
        this.icon5 = ir.func_94245_a("thaumichorizons:enderheart");
        this.icon6 = ir.func_94245_a("thaumichorizons:shockskin");
        this.icon7 = ir.func_94245_a("thaumichorizons:instilledloyalty");
        this.icon8 = ir.func_94245_a("thaumichorizons:runichide");
        this.icon9 = ir.func_94245_a("thaumichorizons:eldritchfangs");
        this.icon10 = ir.func_94245_a("thaumichorizons:portability");
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
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.quicksilver");
                break;
            }
            case 2: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.thaumClaws");
                break;
            }
            case 3: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.awakeBlood");
                break;
            }
            case 4: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.diamondSkin");
                break;
            }
            case 5: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.enderHeart");
                break;
            }
            case 6: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.shockSkin");
                break;
            }
            case 7: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.instilledLoyalty");
                break;
            }
            case 8: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.runicHide");
                break;
            }
            case 9: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.eldritchFangs");
                break;
            }
            case 10: {
                stringy = stringy + StatCollector.func_74838_a((String)"critterInfusions.portability");
            }
        }
        return stringy;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World world, EntityPlayer p) {
        Entity ent = ItemFocusContainment.getPointedEntity(world, (EntityLivingBase)p, 1.5);
        if (ent != null && ent instanceof EntityLiving && this.isValidInfusionTarget((EntityLiving)ent)) {
            EntityLiving critter = (EntityLiving)ent;
            for (CreatureInfusionRecipe recipe : ThaumicHorizons.critterRecipes) {
                boolean blockLoyalty = false;
                if (recipe.getID(null) != p_77659_1_.func_77960_j() || !(recipe.getRecipeOutput() instanceof NBTTagCompound) || ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).hasInfusion(recipe.getID(null))) continue;
                NBTTagCompound tagMods = (NBTTagCompound)recipe.getRecipeOutput();
                HashMultimap map = HashMultimap.create();
                if (tagMods.func_74769_h("generic.movementSpeed") > 0.0) {
                    map.put((Object)"generic.movementSpeed", (Object)new AttributeModifier("generic.movementSpeed", tagMods.func_74769_h("generic.movementSpeed") / 10.0, 1));
                }
                if (tagMods.func_74769_h("generic.maxHealth") > 0.0) {
                    map.put((Object)"generic.maxHealth", (Object)new AttributeModifier("generic.maxHealth", tagMods.func_74769_h("generic.maxHealth"), 1));
                }
                if (tagMods.func_74769_h("generic.attackDamage") > 0.0) {
                    map.put((Object)"generic.attackDamage", (Object)new AttributeModifier("generic.attackDamage", tagMods.func_74769_h("generic.attackDamage"), 1));
                }
                if (map.size() > 0) {
                    critter.func_110140_aT().func_111147_b((Multimap)map);
                }
                Set keys = tagMods.func_150296_c();
                for (String s : keys) {
                    if (s.substring(0, 8).equals("generic.")) continue;
                    if (tagMods.func_74762_e(s) == 7) {
                        if (critter.field_70714_bg.field_75782_a.size() == 0) {
                            blockLoyalty = true;
                        } else {
                            ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).setOwner(p.func_70005_c_());
                        }
                    }
                    if (blockLoyalty) continue;
                    ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).addInfusion(tagMods.func_74762_e(s));
                }
                if (!blockLoyalty) {
                    ((EntityInfusionProperties)critter.getExtendedProperties("CreatureInfusion")).addCost(recipe.getAspects());
                    critter.func_110163_bv();
                    ThaumicHorizons.instance.eventHandlerEntity.applyInfusions((EntityLivingBase)critter);
                    Thaumcraft.proxy.burst(world, critter.field_70165_t, critter.field_70163_u + (double)critter.func_70047_e(), critter.field_70161_v, 1.0f);
                }
                return p_77659_1_;
            }
        }
        return p_77659_1_;
    }

    public boolean isValidInfusionTarget(EntityLiving ent) {
        if (!(ent == null || ent.func_70668_bt() == EnumCreatureAttribute.UNDEAD || ent instanceof EntityGolem || ent instanceof EntityGolemBase || ent instanceof IMerchant || ent instanceof INpc)) {
            for (Class clazz : ThaumicHorizons.classBanList) {
                if (!ent.getClass().isAssignableFrom(clazz)) continue;
                return false;
            }
            return true;
        }
        return false;
    }
}

