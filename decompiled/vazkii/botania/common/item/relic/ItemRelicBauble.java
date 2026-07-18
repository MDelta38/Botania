/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.relic;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.relic.ItemRelic;

public abstract class ItemRelicBauble
extends ItemBauble
implements IRelic {
    Achievement achievement;

    public ItemRelicBauble(String name) {
        super(name);
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        if (p_77663_3_ instanceof EntityPlayer) {
            ItemRelic.updateRelic(p_77663_1_, (EntityPlayer)p_77663_3_);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (player instanceof EntityPlayer) {
            EntityPlayer ePlayer = (EntityPlayer)player;
            ItemRelic.updateRelic(stack, ePlayer);
            if (ItemRelic.isRightPlayer(ePlayer, stack)) {
                this.onValidPlayerWornTick(stack, ePlayer);
            }
        }
    }

    @Override
    public void addHiddenTooltip(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        super.addHiddenTooltip(par1ItemStack, par2EntityPlayer, par3List, par4);
        ItemRelic.addBindInfo(par3List, par1ItemStack, par2EntityPlayer);
    }

    public void onValidPlayerWornTick(ItemStack stack, EntityPlayer player) {
    }

    @Override
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return player instanceof EntityPlayer && ItemRelic.isRightPlayer((EntityPlayer)player, stack);
    }

    @Override
    public void bindToUsername(String playerName, ItemStack stack) {
        ItemRelic.bindToUsernameS(playerName, stack);
    }

    @Override
    public String getSoulbindUsername(ItemStack stack) {
        return ItemRelic.getSoulbindUsernameS(stack);
    }

    @Override
    public Achievement getBindAchievement() {
        return this.achievement;
    }

    @Override
    public void setBindAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public EnumRarity func_77613_e(ItemStack p_77613_1_) {
        return BotaniaAPI.rarityRelic;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }
}

