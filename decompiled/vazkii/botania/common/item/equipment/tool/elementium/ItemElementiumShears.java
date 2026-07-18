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
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package vazkii.botania.common.item.equipment.tool.elementium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelShears;

public class ItemElementiumShears
extends ItemManasteelShears {
    IIcon dammitReddit;

    public ItemElementiumShears() {
        super("elementiumShears");
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.dammitReddit = IconHelper.forName(par1IconRegister, "dammitReddit");
    }

    public IIcon func_77650_f(ItemStack par1ItemStack) {
        return par1ItemStack.func_82833_r().equalsIgnoreCase("dammit reddit") ? this.dammitReddit : super.func_77650_f(par1ItemStack);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        int range;
        List sheep;
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        if (count != this.func_77626_a(stack) && count % 5 == 0 && (sheep = player.field_70170_p.func_72872_a(IShearable.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)(range = 12)), (double)(player.field_70163_u - (double)range), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)range), (double)(player.field_70161_v + (double)range)))).size() > 0) {
            for (IShearable target : sheep) {
                Entity entity = (Entity)target;
                if (!target.isShearable(stack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v)) continue;
                ArrayList drops = target.onSheared(stack, (IBlockAccess)entity.field_70170_p, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)stack));
                Random rand = new Random();
                for (ItemStack drop : drops) {
                    EntityItem ent = entity.func_70099_a(drop, 1.0f);
                    ent.field_70181_x += (double)(rand.nextFloat() * 0.05f);
                    ent.field_70159_w += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                    ent.field_70179_y += (double)((rand.nextFloat() - rand.nextFloat()) * 0.1f);
                }
                ToolCommons.damageItem(stack, 1, (EntityLivingBase)player, 30);
                break;
            }
        }
    }
}

