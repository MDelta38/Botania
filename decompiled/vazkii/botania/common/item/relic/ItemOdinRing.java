/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 */
package vazkii.botania.common.item.relic;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemOdinRing
extends ItemRelicBauble {
    public static List<String> damageNegations = new ArrayList<String>();
    Multimap<String, AttributeModifier> attributes = HashMultimap.create();

    public ItemOdinRing() {
        super("odinRing");
        MinecraftForge.EVENT_BUS.register((Object)this);
        damageNegations.add(DamageSource.field_76369_e.field_76373_n);
        damageNegations.add(DamageSource.field_76379_h.field_76373_n);
        damageNegations.add(DamageSource.field_76371_c.field_76373_n);
        if (ConfigHandler.ringOfOdinFireResist) {
            damageNegations.add(DamageSource.field_76372_a.field_76373_n);
            damageNegations.add(DamageSource.field_76370_b.field_76373_n);
        }
        damageNegations.add(DamageSource.field_76368_d.field_76373_n);
        damageNegations.add(DamageSource.field_76366_f.field_76373_n);
    }

    @Override
    public void onValidPlayerWornTick(ItemStack stack, EntityPlayer player) {
        if (player.func_70027_ad() && ConfigHandler.ringOfOdinFireResist) {
            player.func_70066_B();
        }
    }

    @SubscribeEvent
    public void onPlayerAttacked(LivingAttackEvent event) {
        EntityPlayer player;
        if (event.entityLiving instanceof EntityPlayer && ItemOdinRing.getOdinRing(player = (EntityPlayer)event.entityLiving) != null && damageNegations.contains(event.source.field_76373_n)) {
            event.setCanceled(true);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    public static ItemStack getOdinRing(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        ItemStack stack1 = baubles.func_70301_a(1);
        ItemStack stack2 = baubles.func_70301_a(2);
        return ItemOdinRing.isOdinRing(stack1) ? stack1 : (ItemOdinRing.isOdinRing(stack2) ? stack2 : null);
    }

    private static boolean isOdinRing(ItemStack stack) {
        return stack != null && (stack.func_77973_b() == ModItems.odinRing || stack.func_77973_b() == ModItems.aesirRing);
    }

    @Override
    public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111147_b(this.attributes);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111148_a(this.attributes);
    }

    void fillModifiers(Multimap<String, AttributeModifier> attributes, ItemStack stack) {
        attributes.put((Object)SharedMonsterAttributes.field_111267_a.func_111108_a(), (Object)new AttributeModifier(ItemOdinRing.getBaubleUUID(stack), "Bauble modifier", 20.0, 0));
    }
}

