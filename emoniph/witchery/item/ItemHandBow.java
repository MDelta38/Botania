/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.InvUtil;
import com.emoniph.witchery.util.ItemUtil;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemHandBow
extends ItemBow {
    private static final int TICKS_TO_LOAD = 10;
    private static final String BOLT_TYPE_CURRENT = "WITCBoltTypeCurrent";
    private static final String BOLT_TYPE_PREFERRED = "WITCBoltTypePreferred";
    private static ItemGeneral.SubItem[] BOLT_TYPES = null;

    public ItemHandBow() {
        this.func_77656_e(768);
        this.func_77664_n();
        this.func_77637_a(WitcheryCreativeTab.INSTANCE);
    }

    public Item func_77655_b(String itemName) {
        ItemUtil.registerItem((Item)this, itemName);
        return super.func_77655_b(itemName);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        ItemGeneral.SubItem loadedBoltType = ItemHandBow.getBoltType(BOLT_TYPE_CURRENT, stack);
        ExtendedPlayer playerEx = ExtendedPlayer.get(player);
        if ((loadedBoltType != null || player.func_70093_af()) && playerEx.getCreatureType() == TransformCreature.NONE) {
            player.func_71008_a(stack, this.func_77626_a(stack));
        }
        return stack;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int boltTypesCount;
        int elapsed = this.func_77626_a(stack) - count;
        ItemGeneral.SubItem loadedBoltType = ItemHandBow.getBoltType(BOLT_TYPE_CURRENT, stack);
        if (player.func_70093_af() && (elapsed == 5 || elapsed == 10 || elapsed == 15) && (boltTypesCount = this.getBoltTypesInInventory((IInventory)player.field_71071_by, loadedBoltType)) > 0) {
            SoundEffect.WITCHERY_RANDOM_CLICK.playOnlyTo(player, 1.0f, 1.0f);
        }
        super.onUsingTick(stack, player, count);
    }

    private int getBoltTypesInInventory(IInventory inventory, ItemGeneral.SubItem typeToIgnore) {
        HashSet<ItemGeneral.BoltType> typesFound = new HashSet<ItemGeneral.BoltType>();
        for (int slot = 0; slot < inventory.func_70302_i_(); ++slot) {
            ItemStack stack = inventory.func_70301_a(slot);
            ItemGeneral.BoltType boltType = ItemGeneral.BoltType.getBolt(stack);
            if (boltType == null || boltType == typeToIgnore) continue;
            typesFound.add(boltType);
        }
        return typesFound.size();
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int count) {
        int elapsed = this.func_77626_a(stack) - count;
        ItemGeneral.SubItem loadedBoltType = ItemHandBow.getBoltType(BOLT_TYPE_CURRENT, stack);
        if (!(loadedBoltType == null || player.func_70093_af() && elapsed >= 10)) {
            if (this.launchBolt(stack, world, player, elapsed >= TimeUtil.secsToTicks(1) ? 20 : 19)) {
                this.setBoltType(BOLT_TYPE_CURRENT, stack, null);
            }
        } else if (player.func_70093_af() && elapsed >= 10) {
            int boltTypesCount = this.getBoltTypesInInventory((IInventory)player.field_71071_by, loadedBoltType);
            if (loadedBoltType != null && boltTypesCount > 0) {
                ItemGeneral.SubItem boltTypeToUse = this.getNextBoltType(loadedBoltType);
                if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue)) {
                    boltTypeToUse = null;
                    ItemGeneral.SubItem currentBoltType = loadedBoltType;
                    while ((currentBoltType = this.getNextBoltType(currentBoltType)) != loadedBoltType) {
                        if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, currentBoltType.damageValue)) continue;
                        boltTypeToUse = currentBoltType;
                        break;
                    }
                }
                if (boltTypeToUse != null) {
                    this.setBoltType(BOLT_TYPE_CURRENT, stack, boltTypeToUse);
                    this.setBoltType(BOLT_TYPE_PREFERRED, stack, boltTypeToUse);
                    SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0f, 1.0f);
                    if (!player.field_71075_bZ.field_75098_d) {
                        InvUtil.consumeItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue);
                        ItemStack unloadedBolt = loadedBoltType.createStack();
                        if (!player.field_71071_by.func_70441_a(unloadedBolt)) {
                            EntityUtil.spawnEntityInWorld(world, (Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, unloadedBolt));
                        }
                    }
                }
            } else if (loadedBoltType == null && boltTypesCount > 0) {
                ItemGeneral.SubItem preferredBoltType = ItemHandBow.getBoltType(BOLT_TYPE_PREFERRED, stack);
                if (preferredBoltType == null) {
                    preferredBoltType = Witchery.Items.GENERIC.itemBoltStake;
                }
                ItemGeneral.SubItem boltTypeToUse = preferredBoltType;
                if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue)) {
                    boltTypeToUse = null;
                    ItemGeneral.SubItem currentBoltType = preferredBoltType;
                    while ((currentBoltType = this.getNextBoltType(currentBoltType)) != preferredBoltType) {
                        if (!InvUtil.hasItem(player.field_71071_by, Witchery.Items.GENERIC, currentBoltType.damageValue)) continue;
                        boltTypeToUse = currentBoltType;
                        break;
                    }
                }
                if (boltTypeToUse != null) {
                    SoundEffect.WITCHERY_RANDOM_WINDUP.playOnlyTo(player, 1.0f, 1.0f);
                    this.setBoltType(BOLT_TYPE_CURRENT, stack, boltTypeToUse);
                    if (!player.field_71075_bZ.field_75098_d) {
                        InvUtil.consumeItem(player.field_71071_by, Witchery.Items.GENERIC, boltTypeToUse.damageValue);
                    }
                }
            }
        }
    }

    private boolean launchBolt(ItemStack stack, World world, EntityPlayer player, int ticks) {
        boolean isInfinite = EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)stack) > 0 && world.field_73012_v.nextDouble() < 0.25;
        ItemGeneral.SubItem boltType = ItemHandBow.getBoltType(BOLT_TYPE_CURRENT, stack);
        if (boltType != null) {
            float f = (float)ticks / 20.0f;
            if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                return true;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            int boltID = 0;
            int boltCount = 1;
            float arcStart = 0.0f;
            float arcInc = 0.0f;
            float damage = 2.0f;
            if (boltType == Witchery.Items.GENERIC.itemBoltSilver) {
                boltID = 4;
            } else if (boltType == Witchery.Items.GENERIC.itemBoltHoly) {
                boltID = 3;
            } else if (boltType == Witchery.Items.GENERIC.itemBoltAntiMagic) {
                if (ItemHunterClothes.isFullSetWorn((EntityLivingBase)player, false)) {
                    boltID = 2;
                    Witchery.modHooks.reducePowerLevels((EntityLivingBase)player, 1.0f);
                } else {
                    boltID = 1;
                }
            } else if (boltType == Witchery.Items.GENERIC.itemBoltSplitting) {
                boltCount = 3;
                arcStart = -20.0f;
                arcInc = 20.0f;
                damage = 1.0f;
            }
            for (int i = 0; i < boltCount; ++i) {
                EntityBolt bolt = new EntityBolt(world, (EntityLivingBase)player, f * 2.0f, arcStart + (float)i * arcInc);
                bolt.setShooter((EntityLivingBase)player);
                bolt.setBoltType(boltID);
                bolt.setDamage(damage);
                int n = bolt.canBePickedUp = isInfinite || player.field_71075_bZ.field_75098_d ? 0 : 1;
                if (f == 1.0f) {
                    bolt.setIsCritical(true);
                }
                if (boltType != Witchery.Items.GENERIC.itemBoltAntiMagic) {
                    int knockbackBonus;
                    int powerBonus = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)stack);
                    if (powerBonus > 0) {
                        bolt.setDamage(bolt.getDamage() + (double)powerBonus * 0.5 + 0.5);
                    }
                    if ((knockbackBonus = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)stack)) > 0) {
                        bolt.setKnockbackStrength(knockbackBonus);
                    }
                    if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)stack) > 0) {
                        bolt.func_70015_d(100);
                    }
                }
                EntityUtil.spawnEntityInWorld(world, bolt);
                EntityUtil.correctProjectileTrackerSync(world, bolt);
            }
            stack.func_77972_a(2, (EntityLivingBase)player);
            world.func_72956_a((Entity)player, "random.bow", 1.0f, 1.0f / (field_77697_d.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            if (isInfinite && !world.field_72995_K) {
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
                return false;
            }
        }
        return true;
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77619_b() {
        return 1;
    }

    public static ItemGeneral.BoltType getLoadedBoltType(ItemStack stack) {
        ItemGeneral.SubItem boltType = ItemHandBow.getBoltType(BOLT_TYPE_CURRENT, stack);
        if (boltType != null) {
            return (ItemGeneral.BoltType)boltType;
        }
        return null;
    }

    private static ItemGeneral.SubItem getBoltType(String key, ItemStack stack) {
        if (!stack.func_77942_o()) {
            return null;
        }
        NBTTagCompound nbtRoot = stack.func_77978_p();
        int boltID = nbtRoot.func_74762_e(key);
        return ItemHandBow.intToBoltType(boltID);
    }

    private void setBoltType(String key, ItemStack stack, ItemGeneral.SubItem boltType) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbtRoot = stack.func_77978_p();
        int boltID = this.boltTypeToInt(boltType);
        nbtRoot.func_74768_a(key, boltID);
    }

    private static ItemGeneral.SubItem[] getBoltTypes() {
        if (BOLT_TYPES == null) {
            BOLT_TYPES = new ItemGeneral.SubItem[]{Witchery.Items.GENERIC.itemBoltStake, Witchery.Items.GENERIC.itemBoltAntiMagic, Witchery.Items.GENERIC.itemBoltHoly, Witchery.Items.GENERIC.itemBoltSplitting, Witchery.Items.GENERIC.itemBoltSilver};
        }
        return BOLT_TYPES;
    }

    private static ItemGeneral.SubItem intToBoltType(int boltID) {
        if (boltID > 0 && boltID <= ItemHandBow.getBoltTypes().length) {
            return BOLT_TYPES[boltID - 1];
        }
        return null;
    }

    private int boltTypeToInt(ItemGeneral.SubItem boltType) {
        for (int i = 0; i < ItemHandBow.getBoltTypes().length; ++i) {
            if (ItemHandBow.getBoltTypes()[i] != boltType) continue;
            return i + 1;
        }
        return 0;
    }

    private ItemGeneral.SubItem getNextBoltType(ItemGeneral.SubItem boltType) {
        return ItemHandBow.intToBoltType(this.getNextBoltTypeID(this.boltTypeToInt(boltType)));
    }

    private int getNextBoltTypeID(int boltID) {
        if (++boltID > ItemHandBow.getBoltTypes().length) {
            boltID = 1;
        }
        return boltID;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(this.func_111208_A());
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_94599_c(int par1) {
        return this.field_77791_bV;
    }
}

