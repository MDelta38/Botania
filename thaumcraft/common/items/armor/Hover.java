/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagShort
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.armor;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.HashMap;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.baubles.ItemGirdleHover;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketFlyToServer;
import thaumcraft.common.lib.utils.Utils;

public class Hover {
    public static final int EFFICIENCY = 360;
    static HashMap<Integer, Boolean> hovering = new HashMap();
    static HashMap<Integer, Long> timing = new HashMap();

    public static boolean toggleHover(EntityPlayer player, int playerId, ItemStack armor) {
        AspectList aspects;
        ItemStack jar;
        boolean hover = hovering.get(playerId) == null ? false : hovering.get(playerId);
        int fuel = 0;
        if (armor.func_77942_o() && armor.field_77990_d.func_74764_b("jar") && (jar = ItemStack.func_77949_a((NBTTagCompound)armor.field_77990_d.func_74775_l("jar"))) != null && jar.func_77973_b() instanceof ItemJarFilled && jar.func_77942_o() && (aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar)) != null && aspects.size() > 0 && aspects.getAmount(Aspect.ENERGY) > 0) {
            fuel = (short)aspects.getAmount(Aspect.ENERGY);
        }
        if (!hover && fuel <= 0) {
            return false;
        }
        hovering.put(playerId, !hover);
        if (player.field_70170_p.field_72995_K) {
            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFlyToServer(player, !hover));
            player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, !hover ? "thaumcraft:hhon" : "thaumcraft:hhoff", 0.33f, 1.0f, false);
        }
        return !hover;
    }

    public static void setHover(int playerId, boolean hover) {
        hovering.put(playerId, hover);
    }

    public static boolean getHover(int playerId) {
        return hovering.containsKey(playerId) ? hovering.get(playerId) : false;
    }

    public static void handleHoverArmor(EntityPlayer player, ItemStack armor) {
        if (hovering.get(player.func_145782_y()) == null && armor.func_77942_o() && armor.field_77990_d.func_74764_b("hover")) {
            hovering.put(player.func_145782_y(), armor.field_77990_d.func_74771_c("hover") == 1);
            if (player.field_70170_p.field_72995_K) {
                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFlyToServer(player, armor.field_77990_d.func_74771_c("hover") == 1));
            }
        }
        boolean hover = hovering.get(player.func_145782_y()) == null ? false : hovering.get(player.func_145782_y());
        World world = player.field_70170_p;
        player.field_71075_bZ.field_75100_b = hover;
        if (world.field_72995_K && player instanceof EntityPlayerSP) {
            if (hover && Hover.expendCharge(player, armor)) {
                long currenttime = System.currentTimeMillis();
                long time = 0L;
                if (timing.get(player.func_145782_y()) != null) {
                    time = timing.get(player.func_145782_y());
                }
                if (time < currenttime) {
                    time = currenttime + 1200L;
                    timing.put(player.func_145782_y(), time);
                    player.field_70170_p.func_72980_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:jacobs", 0.05f, 1.0f + player.field_70170_p.field_73012_v.nextFloat() * 0.05f, false);
                }
                int haste = EnchantmentHelper.func_77506_a((int)Config.enchHaste.field_77352_x, (ItemStack)armor);
                float mod = 0.7f + 0.075f * (float)haste;
                if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77973_b() instanceof ItemGirdleHover) {
                    mod += 0.21f;
                }
                if (mod > 1.0f) {
                    mod = 1.0f;
                }
                player.field_70159_w *= (double)mod;
                player.field_70179_y *= (double)mod;
            } else if (hover) {
                Hover.toggleHover(player, player.func_145782_y(), armor);
            }
        } else {
            if (armor.func_77942_o() && !armor.field_77990_d.func_74764_b("hover")) {
                armor.func_77983_a("hover", (NBTBase)new NBTTagByte((byte)(hover ? 1 : 0)));
            }
            if (hover && Hover.expendCharge(player, armor)) {
                if (player instanceof EntityPlayerMP) {
                    Utils.resetFloatCounter((EntityPlayerMP)player);
                }
                player.field_70143_R = 0.0f;
                if (armor.func_77942_o() && armor.field_77990_d.func_74764_b("hover") && armor.field_77990_d.func_74771_c("hover") != 1) {
                    armor.func_77983_a("hover", (NBTBase)new NBTTagByte((byte)(hover ? 1 : 0)));
                }
            } else {
                if (hover) {
                    Hover.toggleHover(player, player.func_145782_y(), armor);
                }
                player.field_70143_R *= 0.75f;
                if (armor.func_77942_o() && armor.field_77990_d.func_74764_b("hover") && armor.field_77990_d.func_74771_c("hover") == 1) {
                    armor.func_77983_a("hover", (NBTBase)new NBTTagByte((byte)(hover ? 1 : 0)));
                }
            }
        }
    }

    public static boolean expendCharge(EntityPlayer player, ItemStack is) {
        if (is.func_77942_o() && is.field_77990_d.func_74764_b("jar")) {
            AspectList aspects;
            ItemStack jar = ItemStack.func_77949_a((NBTTagCompound)is.field_77990_d.func_74775_l("jar"));
            int fuel = 0;
            if (jar != null && jar.func_77973_b() instanceof ItemJarFilled && jar.func_77942_o() && (aspects = ((ItemJarFilled)jar.func_77973_b()).getAspects(jar)) != null && aspects.size() > 0 && aspects.getAmount(Aspect.ENERGY) > 0) {
                fuel = (short)aspects.getAmount(Aspect.ENERGY);
            }
            float mod = 1.0f;
            if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77973_b() instanceof ItemGirdleHover) {
                mod = 0.8f;
            }
            if (!is.field_77990_d.func_74764_b("charge")) {
                is.func_77983_a("charge", (NBTBase)new NBTTagShort(0));
            }
            if (fuel > 0 && is.field_77990_d.func_74764_b("charge")) {
                short charge = is.field_77990_d.func_74765_d("charge");
                if ((float)charge < 360.0f * mod) {
                    is.func_77983_a("charge", (NBTBase)new NBTTagShort((short)(charge + 1)));
                    return true;
                }
                is.func_77983_a("charge", (NBTBase)new NBTTagShort(0));
                fuel = (short)(fuel - 1);
                if (fuel > 0) {
                    ((ItemJarFilled)jar.func_77973_b()).setAspects(jar, new AspectList().add(Aspect.ENERGY, fuel));
                } else {
                    ((ItemJarFilled)jar.func_77973_b()).setAspects(jar, new AspectList().remove(Aspect.ENERGY));
                }
                NBTTagCompound var4 = new NBTTagCompound();
                jar.func_77955_b(var4);
                is.func_77983_a("jar", (NBTBase)var4);
                return fuel > 0;
            }
        }
        return false;
    }
}

