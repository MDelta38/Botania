/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package thaumic.tinkerer.common.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.network.packet.kami.PacketSoulHearts;

public class SoulHeartHandler {
    private static final String COMPOUND = "ThaumicTinkerer";
    private static final String TAG_HP = "soulHearts";
    private static final int MAX_HP = 20;

    public static void addHearts(EntityPlayer player) {
        SoulHeartHandler.addHP(player, 1);
        SoulHeartHandler.updateClient(player);
    }

    public static boolean addHP(EntityPlayer player, int hp) {
        int current = SoulHeartHandler.getHP(player);
        if (current >= 20) {
            return false;
        }
        SoulHeartHandler.setHP(player, Math.min(20, current + hp));
        return true;
    }

    public static int removeHP(EntityPlayer player, int hp) {
        int current = SoulHeartHandler.getHP(player);
        int newHp = current - hp;
        SoulHeartHandler.setHP(player, Math.max(0, newHp));
        return Math.max(0, -newHp);
    }

    public static void setHP(EntityPlayer player, int hp) {
        NBTTagCompound cmp = SoulHeartHandler.getCompoundToSet(player);
        cmp.func_74768_a(TAG_HP, hp);
    }

    public static int getHP(EntityPlayer player) {
        NBTTagCompound cmp = SoulHeartHandler.getCompoundToSet(player);
        return cmp.func_74764_b(TAG_HP) ? cmp.func_74762_e(TAG_HP) : 0;
    }

    private static NBTTagCompound getCompoundToSet(EntityPlayer player) {
        NBTTagCompound cmp = player.getEntityData();
        if (!cmp.func_74764_b(COMPOUND)) {
            cmp.func_74782_a(COMPOUND, (NBTBase)new NBTTagCompound());
        }
        return cmp.func_74775_l(COMPOUND);
    }

    public static void updateClient(EntityPlayer player) {
        if (player instanceof EntityPlayerMP && ((EntityPlayerMP)player).field_71135_a != null) {
            ThaumicTinkerer.netHandler.sendTo((IMessage)new PacketSoulHearts(SoulHeartHandler.getHP(player)), (EntityPlayerMP)player);
        }
    }

    @SubscribeEvent
    public void onPlayerDamage(LivingHurtEvent event) {
        if (event.entityLiving instanceof EntityPlayer && event.ammount > 0.0f) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            event.ammount = SoulHeartHandler.removeHP(player, (int)event.ammount);
            SoulHeartHandler.updateClient(player);
        }
    }

    protected float applyArmorCalculations(EntityLivingBase entity, DamageSource par1DamageSource, float par2) {
        if (!par1DamageSource.func_76363_c()) {
            int i = 25 - entity.func_70658_aO();
            float f1 = par2 * (float)i;
            par2 = f1 / 25.0f;
        }
        return par2;
    }

    protected float applyPotionDamageCalculations(EntityLivingBase entity, DamageSource par1DamageSource, float par2) {
        float f1;
        int j;
        int i;
        if (entity.func_70644_a(Potion.field_76429_m) && par1DamageSource != DamageSource.field_76380_i) {
            i = (entity.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
            j = 25 - i;
            f1 = par2 * (float)j;
            par2 = f1 / 25.0f;
        }
        if (par2 <= 0.0f) {
            return 0.0f;
        }
        i = EnchantmentHelper.func_77508_a((ItemStack[])entity.func_70035_c(), (DamageSource)par1DamageSource);
        if (i > 20) {
            i = 20;
        }
        if (i > 0 && i <= 20) {
            j = 25 - i;
            f1 = par2 * (float)j;
            par2 = f1 / 25.0f;
        }
        return par2;
    }
}

