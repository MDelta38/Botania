/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ItemUtil;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class ItemBoline
extends ItemSword {
    public static final Block[] blocksEffectiveAgainst = new Block[]{Blocks.field_150344_f, Blocks.field_150342_X, Blocks.field_150344_f, Blocks.field_150486_ae, Blocks.field_150333_U, Blocks.field_150423_aK, Blocks.field_150428_aP};
    private float effectiveWeaponDamage = 4.0f + Item.ToolMaterial.WOOD.func_78000_c();

    public ItemBoline() {
        super(Item.ToolMaterial.IRON);
        this.func_77637_a(WitcheryCreativeTab.INSTANCE);
    }

    public Item func_77655_b(String itemName) {
        ItemUtil.registerItem((Item)this, itemName);
        return super.func_77655_b(itemName);
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.uncommon;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean moreTips) {
        String localText = Witchery.resource("item.witchery:boline.tip");
        if (localText != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
    }

    public Multimap func_111205_h() {
        HashMultimap multimap = HashMultimap.create();
        multimap.put((Object)SharedMonsterAttributes.field_111264_e.func_111108_a(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", (double)this.effectiveWeaponDamage, 0));
        return multimap;
    }

    public float func_150931_i() {
        return Item.ToolMaterial.WOOD.func_78000_c();
    }

    public boolean func_150894_a(ItemStack stack, World world, Block block, int posX, int posY, int posZ, EntityLivingBase entity) {
        if (block != null && block != Blocks.field_150362_t && block != Blocks.field_150321_G && block != Blocks.field_150329_H && block != Blocks.field_150395_bd && block != Blocks.field_150473_bD && !(block instanceof IShearable) && block.func_149712_f(world, posX, posY, posZ) != 0.0f) {
            stack.func_77972_a(2, entity);
        }
        return true;
    }

    public boolean canHarvestBlock(Block par1Block, ItemStack stack) {
        return par1Block == Witchery.Blocks.WEB || par1Block == Blocks.field_150321_G || par1Block == Blocks.field_150488_af || par1Block == Blocks.field_150473_bD;
    }

    public float func_150893_a(ItemStack stack, Block block) {
        if (block == Witchery.Blocks.WEB || block == Blocks.field_150321_G || block == Blocks.field_150362_t) {
            return 15.0f;
        }
        if (block == Blocks.field_150325_L || block == Witchery.Blocks.TRAPPED_PLANT) {
            return 5.0f;
        }
        return super.func_150893_a(stack, block);
    }

    public boolean func_111207_a(ItemStack itemstack, EntityPlayer player, EntityLivingBase entity) {
        if (entity.field_70170_p.field_72995_K) {
            return false;
        }
        if (entity instanceof IShearable) {
            IShearable target = (IShearable)entity;
            if (target.isShearable(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) {
                ArrayList drops = target.onSheared(itemstack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)itemstack));
                Random rand = new Random();
                for (ItemStack stack : drops) {
                    EntityItem ent = entity.func_70099_a(stack, 1.0f);
                    ent.field_70181_x += (double)(rand.nextFloat() * 0.05f);
                    ent.field_70159_w += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                    ent.field_70179_y += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                }
                itemstack.func_77972_a(1, entity);
            }
            return true;
        }
        return false;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        IShearable target;
        if (player.field_70170_p.field_72995_K) {
            return false;
        }
        World world = player.field_70170_p;
        Block block = BlockUtil.getBlock(world, x, y, z);
        if (block == null) {
            return false;
        }
        if (block == Blocks.field_150321_G) {
            world.func_147468_f(x, y, z);
            world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, new ItemStack(block)));
            this.func_150894_a(itemstack, world, block, x, y, z, (EntityLivingBase)player);
            if (itemstack.field_77994_a == 0) {
                player.func_71028_bD();
            }
            return true;
        }
        if (block == Witchery.Blocks.TRAPPED_PLANT) {
            int meta = world.func_72805_g(x, y, z);
            world.func_147468_f(x, y, z);
            world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, new ItemStack(block, 1, meta)));
            this.func_150894_a(itemstack, world, block, x, y, z, (EntityLivingBase)player);
            if (itemstack.field_77994_a == 0) {
                player.func_71028_bD();
            }
            return true;
        }
        if (block == Witchery.Blocks.BLOOD_ROSE) {
            int meta = world.func_72805_g(x, y, z);
            world.func_147468_f(x, y, z);
            world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, new ItemStack(block, 1, meta)));
            this.func_150894_a(itemstack, world, block, x, y, z, (EntityLivingBase)player);
            if (itemstack.field_77994_a == 0) {
                player.func_71028_bD();
            }
            return true;
        }
        if (block instanceof IShearable && (target = (IShearable)block).isShearable(itemstack, (IBlockAccess)player.field_70170_p, x, y, z)) {
            ArrayList drops = target.onSheared(itemstack, (IBlockAccess)player.field_70170_p, x, y, z, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)itemstack));
            Random rand = new Random();
            for (ItemStack stack : drops) {
                float f = 0.7f;
                double d = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
                double d1 = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
                double d2 = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
                EntityItem entityitem = new EntityItem(player.field_70170_p, (double)x + d, (double)y + d1, (double)z + d2, stack);
                entityitem.field_145804_b = 10;
                player.field_70170_p.func_72838_d((Entity)entityitem);
            }
            itemstack.func_77972_a(1, (EntityLivingBase)player);
        }
        return false;
    }
}

