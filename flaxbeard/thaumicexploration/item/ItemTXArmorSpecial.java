/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  thaumcraft.api.IRepairable
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.items.armor.Hover
 */
package flaxbeard.thaumicexploration.item;

import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.armor.Hover;

public class ItemTXArmorSpecial
extends ItemArmor
implements IRepairable {
    public ItemTXArmorSpecial(int par1, ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        super(par2EnumArmorMaterial, par3, par4);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer) {
        if (stack.func_77973_b() == ThaumicExploration.bootsMeteor) {
            return "thaumicexploration:textures/models/armor/bootsMeteor.png";
        }
        return "thaumicexploration:textures/models/armor/bootsComet.png";
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return EnumRarity.rare;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsMeteor && player.field_70143_R > 0.0f) {
            player.field_70143_R = 0.0f;
        }
        if (!player.field_71075_bZ.field_75100_b && player.field_70701_bs > 0.0f) {
            int haste = EnchantmentHelper.func_77506_a((int)Config.enchHaste.field_77352_x, (ItemStack)player.field_71071_by.func_70440_f(0));
            if (player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsMeteor) {
                if (player.field_70170_p.field_72995_K) {
                    if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.func_145782_y())) {
                        Thaumcraft.instance.entityEventHandler.prevStep.put(player.func_145782_y(), Float.valueOf(player.field_70138_W));
                    }
                    player.field_70138_W = 1.0f;
                }
                float bonus = 0.055f;
                if (player.func_70090_H()) {
                    bonus /= 4.0f;
                }
                if (player.field_70122_E) {
                    player.func_70060_a(0.0f, 1.0f, bonus);
                } else {
                    player.field_70747_aH = Hover.getHover((int)player.func_145782_y()) ? 0.03f : 0.05f;
                }
                if (player.field_70143_R > 0.0f) {
                    player.field_70143_R = 0.0f;
                }
            } else if (player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsComet) {
                if (player.field_70170_p.field_72995_K) {
                    if (!Thaumcraft.instance.entityEventHandler.prevStep.containsKey(player.func_145782_y())) {
                        Thaumcraft.instance.entityEventHandler.prevStep.put(player.func_145782_y(), Float.valueOf(player.field_70138_W));
                    }
                    player.field_70138_W = 1.0f;
                }
                if (!player.field_71071_by.func_70440_f(0).func_77942_o()) {
                    NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                    player.field_71071_by.func_70440_f(0).func_77982_d(par1NBTTagCompound);
                    player.field_71071_by.func_70440_f((int)0).field_77990_d.func_74768_a("runTicks", 0);
                }
                int ticks = player.field_71071_by.func_70440_f((int)0).field_77990_d.func_74762_e("runTicks");
                float bonus = 0.11f;
                bonus += (float)(ticks / 5) * 0.003f;
                if (player.func_70090_H()) {
                    bonus /= 4.0f;
                }
                if (player.field_70122_E || player.func_70617_f_()) {
                    player.func_70060_a(0.0f, 1.0f, bonus);
                } else {
                    player.field_70747_aH = Hover.getHover((int)player.func_145782_y()) ? 0.03f : 0.05f;
                }
                if (player.field_70143_R > 0.25f) {
                    player.field_70143_R -= 0.25f;
                }
            }
        }
    }
}

