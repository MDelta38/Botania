/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemShears
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatList
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package vazkii.botania.common.item.equipment.tool.manasteel;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemManasteelShears
extends ItemShears
implements IManaUsingItem {
    public static final int MANA_PER_DAMAGE = 30;

    public ItemManasteelShears() {
        this("manasteelShears");
    }

    public ItemManasteelShears(String name) {
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b(name);
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item.", "item.botania:");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
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
                ToolCommons.damageItem(itemstack, 1, (EntityLivingBase)player, 30);
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
        Block block = player.field_70170_p.func_147439_a(x, y, z);
        if (block instanceof IShearable && (target = (IShearable)block).isShearable(itemstack, (IBlockAccess)player.field_70170_p, x, y, z)) {
            ArrayList drops = target.onSheared(itemstack, (IBlockAccess)player.field_70170_p, x, y, z, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)itemstack));
            Random rand = new Random();
            for (ItemStack stack : drops) {
                float f = 0.7f;
                double d = (double)(rand.nextFloat() * f) + (1.0 - (double)f) * 0.5;
                double d1 = (double)(rand.nextFloat() * f) + (1.0 - (double)f) * 0.5;
                double d2 = (double)(rand.nextFloat() * f) + (1.0 - (double)f) * 0.5;
                EntityItem entityitem = new EntityItem(player.field_70170_p, (double)x + d, (double)y + d1, (double)z + d2, stack);
                entityitem.field_145804_b = 10;
                player.field_70170_p.func_72838_d((Entity)entityitem);
            }
            ToolCommons.damageItem(itemstack, 1, (EntityLivingBase)player, 30);
            player.func_71064_a(StatList.field_75934_C[Block.func_149682_b((Block)block)], 1);
        }
        return false;
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (!world.field_72995_K && player instanceof EntityPlayer && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExactForTool(stack, (EntityPlayer)player, 60, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 0 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

