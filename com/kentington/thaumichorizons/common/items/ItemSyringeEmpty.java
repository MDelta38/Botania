/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemFocusContainment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemSyringeEmpty
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemSyringeEmpty() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:syringeEmpty");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.syringeEmpty";
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World world, EntityPlayer p) {
        Entity ent = ItemFocusContainment.getPointedEntity(world, (EntityLivingBase)p, 1.5);
        if (ent != null && ent instanceof EntityLiving && !(ent instanceof EntityPlayer)) {
            EntityLiving critter = (EntityLiving)ent;
            if (critter.func_70668_bt() != EnumCreatureAttribute.UNDEAD && !(critter instanceof INpc) && !(critter instanceof IMerchant) && (critter.isCreatureType(EnumCreatureType.creature, false) || critter.isCreatureType(EnumCreatureType.ambient, false) || critter.isCreatureType(EnumCreatureType.waterCreature, false))) {
                ItemStack bloodSample = new ItemStack(ThaumicHorizons.itemSyringeBloodSample);
                bloodSample.field_77990_d = new NBTTagCompound();
                NBTTagCompound critterTag = new NBTTagCompound();
                critter.func_70109_d(critterTag);
                critterTag.func_74778_a("id", EntityList.func_75621_b((Entity)ent));
                bloodSample.field_77990_d.func_74778_a("critterName", ent.func_70005_c_());
                bloodSample.field_77990_d.func_74782_a("critter", (NBTBase)critterTag);
                if (p.field_71071_by.func_70441_a(bloodSample)) {
                    --p_77659_1_.field_77994_a;
                }
            }
        } else {
            ItemStack result = new ItemStack(ThaumicHorizons.itemSyringeHuman);
            if (p.field_71071_by.func_70441_a(result)) {
                --p_77659_1_.field_77994_a;
            }
        }
        return p_77659_1_;
    }
}

