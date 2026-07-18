/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.IRunicArmor
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.IRunicArmor;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.item.ItemMod;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IRunicArmor")
public abstract class ItemBauble
extends ItemMod
implements IBauble,
ICosmeticAttachable,
IPhantomInkable,
IRunicArmor {
    private static final String TAG_HASHCODE = "playerHashcode";
    private static final String TAG_BAUBLE_UUID_MOST = "baubleUUIDMost";
    private static final String TAG_BAUBLE_UUID_LEAST = "baubleUUIDLeast";
    private static final String TAG_COSMETIC_ITEM = "cosmeticItem";
    private static final String TAG_PHANTOM_INK = "phantomInk";

    public ItemBauble(String name) {
        this.func_77625_d(1);
        this.func_77655_b(name);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!EntityDoppleganger.isTruePlayer((Entity)par3EntityPlayer)) {
            return par1ItemStack;
        }
        if (this.canEquip(par1ItemStack, (EntityLivingBase)par3EntityPlayer)) {
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)par3EntityPlayer);
            for (int i = 0; i < baubles.func_70302_i_(); ++i) {
                ItemStack stackInSlot;
                if (!baubles.func_94041_b(i, par1ItemStack) || (stackInSlot = baubles.func_70301_a(i)) != null && !((IBauble)stackInSlot.func_77973_b()).canUnequip(stackInSlot, (EntityLivingBase)par3EntityPlayer)) continue;
                if (!par2World.field_72995_K) {
                    baubles.func_70299_a(i, par1ItemStack.func_77946_l());
                    if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
                        par3EntityPlayer.field_71071_by.func_70299_a(par3EntityPlayer.field_71071_by.field_70461_c, null);
                    }
                }
                if (stackInSlot == null) break;
                ((IBauble)stackInSlot.func_77973_b()).onUnequipped(stackInSlot, (EntityLivingBase)par3EntityPlayer);
                return stackInSlot.func_77946_l();
            }
        }
        return par1ItemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (GuiScreen.func_146272_n()) {
            this.addHiddenTooltip(par1ItemStack, par2EntityPlayer, par3List, par4);
        } else {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.shiftinfo"), par3List);
        }
    }

    public void addHiddenTooltip(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        ItemStack cosmetic;
        BaubleType type = this.getBaubleType(par1ItemStack);
        this.addStringToTooltip(StatCollector.func_74838_a((String)("botania.baubletype." + type.name().toLowerCase())), par3List);
        String key = RenderHelper.getKeyDisplayString("Baubles Inventory");
        if (key != null) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.baubletooltip").replaceAll("%key%", key), par3List);
        }
        if ((cosmetic = this.getCosmeticItem(par1ItemStack)) != null) {
            this.addStringToTooltip(String.format(StatCollector.func_74838_a((String)"botaniamisc.hasCosmetic"), cosmetic.func_82833_r()), par3List);
        }
        if (this.hasPhantomInk(par1ItemStack)) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.hasPhantomInk"), par3List);
        }
    }

    void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return true;
    }

    public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
        return true;
    }

    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (ItemBauble.getLastPlayerHashcode(stack) != player.hashCode()) {
            this.onEquippedOrLoadedIntoWorld(stack, player);
            ItemBauble.setLastPlayerHashcode(stack, player.hashCode());
        }
    }

    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        if (player != null) {
            if (!player.field_70170_p.field_72995_K) {
                player.field_70170_p.func_72956_a((Entity)player, "botania:equipBauble", 0.1f, 1.3f);
            }
            if (player instanceof EntityPlayer) {
                ((EntityPlayer)player).func_71064_a((StatBase)ModAchievements.baubleWear, 1);
            }
            this.onEquippedOrLoadedIntoWorld(stack, player);
            ItemBauble.setLastPlayerHashcode(stack, player.hashCode());
        }
    }

    public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
    }

    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
    }

    @Override
    public ItemStack getCosmeticItem(ItemStack stack) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_COSMETIC_ITEM, true);
        if (cmp == null) {
            return null;
        }
        return ItemStack.func_77949_a((NBTTagCompound)cmp);
    }

    @Override
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        NBTTagCompound cmp = new NBTTagCompound();
        if (cosmetic != null) {
            cosmetic.func_77955_b(cmp);
        }
        ItemNBTHelper.setCompound(stack, TAG_COSMETIC_ITEM, cmp);
    }

    public boolean hasContainerItem(ItemStack stack) {
        return this.getContainerItem(stack) != null;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return this.getCosmeticItem(itemStack);
    }

    public boolean func_77630_h(ItemStack p_77630_1_) {
        return false;
    }

    public static UUID getBaubleUUID(ItemStack stack) {
        long most = ItemNBTHelper.getLong(stack, TAG_BAUBLE_UUID_MOST, 0L);
        if (most == 0L) {
            UUID uuid = UUID.randomUUID();
            ItemNBTHelper.setLong(stack, TAG_BAUBLE_UUID_MOST, uuid.getMostSignificantBits());
            ItemNBTHelper.setLong(stack, TAG_BAUBLE_UUID_LEAST, uuid.getLeastSignificantBits());
            return ItemBauble.getBaubleUUID(stack);
        }
        long least = ItemNBTHelper.getLong(stack, TAG_BAUBLE_UUID_LEAST, 0L);
        return new UUID(most, least);
    }

    public static int getLastPlayerHashcode(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_HASHCODE, 0);
    }

    public static void setLastPlayerHashcode(ItemStack stack, int hash) {
        ItemNBTHelper.setInt(stack, TAG_HASHCODE, hash);
    }

    @Override
    public boolean hasPhantomInk(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
    }

    @Override
    public void setPhantomInk(ItemStack stack, boolean ink) {
        ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, ink);
    }

    @Optional.Method(modid="Thaumcraft")
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }
}

