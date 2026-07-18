/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemTravelBelt
extends ItemBauble
implements IBaubleRender,
IManaUsingItem {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/travelBelt.png");
    @SideOnly(value=Side.CLIENT)
    private static ModelBiped model;
    private static final int COST = 1;
    private static final int COST_INTERVAL = 10;
    public static List<String> playersWithStepup;
    final float speed;
    final float jump;
    final float fallBuffer;

    public ItemTravelBelt() {
        this("travelBelt", 0.035f, 0.2f, 2.0f);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public ItemTravelBelt(String name, float speed, float jump, float fallBuffer) {
        super(name);
        this.speed = speed;
        this.jump = jump;
        this.fallBuffer = fallBuffer;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

    @SubscribeEvent
    public void updatePlayerStepStatus(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            String s = ItemTravelBelt.playerStr(player);
            ItemStack belt = PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70301_a(3);
            if (playersWithStepup.contains(s)) {
                if (this.shouldPlayerHaveStepup(player)) {
                    ItemTravelBelt beltItem = (ItemTravelBelt)belt.func_77973_b();
                    if ((player.field_70122_E || player.field_71075_bZ.field_75100_b) && player.field_70701_bs > 0.0f && !player.func_70055_a(Material.field_151586_h)) {
                        float speed = beltItem.getSpeed(belt);
                        player.func_70060_a(0.0f, 1.0f, player.field_71075_bZ.field_75100_b ? speed : speed);
                        beltItem.onMovedTick(belt, player);
                        if (player.field_70173_aa % 10 == 0) {
                            ManaItemHandler.requestManaExact(belt, player, 1, true);
                        }
                    } else {
                        beltItem.onNotMovingTick(belt, player);
                    }
                    player.field_70138_W = player.func_70093_af() ? 0.50001f : 1.0f;
                } else {
                    player.field_70138_W = 0.5f;
                    playersWithStepup.remove(s);
                }
            } else if (this.shouldPlayerHaveStepup(player)) {
                playersWithStepup.add(s);
                player.field_70138_W = 1.0f;
            }
        }
    }

    public float getSpeed(ItemStack stack) {
        return this.speed;
    }

    public void onMovedTick(ItemStack stack, EntityPlayer player) {
    }

    public void onNotMovingTick(ItemStack stack, EntityPlayer player) {
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        EntityPlayer player;
        ItemStack belt;
        if (event.entityLiving instanceof EntityPlayer && (belt = PlayerHandler.getPlayerBaubles((EntityPlayer)(player = (EntityPlayer)event.entityLiving)).func_70301_a(3)) != null && belt.func_77973_b() instanceof ItemTravelBelt && ManaItemHandler.requestManaExact(belt, player, 1, false)) {
            player.field_70181_x += (double)((ItemTravelBelt)belt.func_77973_b()).jump;
            player.field_70143_R = -((ItemTravelBelt)belt.func_77973_b()).fallBuffer;
        }
    }

    private boolean shouldPlayerHaveStepup(EntityPlayer player) {
        ItemStack armor = PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70301_a(3);
        return armor != null && armor.func_77973_b() instanceof ItemTravelBelt && ManaItemHandler.requestManaExact(armor, player, 1, false);
    }

    @SubscribeEvent
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        String username = event.player.func_146103_bH().getName();
        playersWithStepup.remove(username + ":false");
        playersWithStepup.remove(username + ":true");
    }

    public static String playerStr(EntityPlayer player) {
        return player.func_146103_bH().getName() + ":" + player.field_70170_p.field_72995_K;
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
            GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.0f);
            float s = 0.065625f;
            GL11.glScalef((float)s, (float)s, (float)s);
            if (model == null) {
                model = new ModelBiped();
            }
            ItemTravelBelt.model.field_78115_e.func_78785_a(1.0f);
        }
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    static {
        playersWithStepup = new ArrayList<String>();
    }
}

