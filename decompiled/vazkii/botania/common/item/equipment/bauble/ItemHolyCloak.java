/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemHolyCloak
extends ItemBauble
implements IBaubleRender {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/holyCloak.png");
    @SideOnly(value=Side.CLIENT)
    private static ModelBiped model;
    private static final String TAG_COOLDOWN = "cooldown";
    private static final String TAG_IN_EFFECT = "inEffect";

    public ItemHolyCloak() {
        this("holyCloak");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public ItemHolyCloak(String name) {
        super(name);
    }

    @SubscribeEvent
    public void onPlayerDamage(LivingHurtEvent event) {
        EntityPlayer player;
        InventoryBaubles baubles;
        ItemStack belt;
        if (event.entityLiving instanceof EntityPlayer && (belt = (baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)(player = (EntityPlayer)event.entityLiving))).func_70301_a(3)) != null && belt.func_77973_b() instanceof ItemHolyCloak && !ItemHolyCloak.isInEffect(belt)) {
            ItemHolyCloak cloak = (ItemHolyCloak)belt.func_77973_b();
            int cooldown = ItemHolyCloak.getCooldown(belt);
            ItemHolyCloak.setInEffect(belt, true);
            if (cooldown == 0 && cloak.effectOnDamage(event, player, belt)) {
                ItemHolyCloak.setCooldown(belt, cloak.getCooldownTime(belt));
            }
            ItemHolyCloak.setInEffect(belt, false);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        int cooldown = ItemHolyCloak.getCooldown(stack);
        if (cooldown > 0) {
            ItemHolyCloak.setCooldown(stack, cooldown - 1);
        }
    }

    public boolean effectOnDamage(LivingHurtEvent event, EntityPlayer player, ItemStack stack) {
        if (!event.source.func_82725_o()) {
            event.setCanceled(true);
            player.field_70170_p.func_72956_a((Entity)player, "botania:holyCloak", 1.0f, 1.0f);
            for (int i = 0; i < 30; ++i) {
                double x = player.field_70165_t + Math.random() * (double)player.field_70130_N * 2.0 - (double)player.field_70130_N;
                double y = player.field_70163_u + Math.random() * (double)player.field_70131_O;
                double z = player.field_70161_v + Math.random() * (double)player.field_70130_N * 2.0 - (double)player.field_70130_N;
                boolean yellow = Math.random() > 0.5;
                Botania.proxy.sparkleFX(player.field_70170_p, x, y, z, yellow ? 1.0f : 0.3f, yellow ? 1.0f : 0.3f, yellow ? 0.3f : 1.0f, 0.8f + (float)Math.random() * 0.4f, 3);
            }
            return true;
        }
        return false;
    }

    public int getCooldownTime(ItemStack stack) {
        return 200;
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.BELT;
    }

    public static int getCooldown(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
    }

    public static void setCooldown(ItemStack stack, int cooldown) {
        ItemNBTHelper.setInt(stack, TAG_COOLDOWN, cooldown);
    }

    public static boolean isInEffect(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_IN_EFFECT, false);
    }

    public static void setInEffect(ItemStack stack, boolean effect) {
        ItemNBTHelper.setBoolean(stack, TAG_IN_EFFECT, effect);
    }

    @SideOnly(value=Side.CLIENT)
    ResourceLocation getRenderTexture() {
        return texture;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.BODY) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(this.getRenderTexture());
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            boolean armor = event.entityPlayer.func_82169_q(2) != null;
            GL11.glTranslatef((float)0.0f, (float)(armor ? -0.07f : -0.01f), (float)0.0f);
            float s = 0.1f;
            GL11.glScalef((float)s, (float)s, (float)s);
            if (model == null) {
                model = new ModelBiped();
            }
            ItemHolyCloak.model.field_78115_e.func_78785_a(1.0f);
        }
    }
}

