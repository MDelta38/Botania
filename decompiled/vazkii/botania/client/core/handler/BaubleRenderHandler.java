/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.client.core.handler.ContributorFancinessHandler;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelHelm;

public final class BaubleRenderHandler {
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Specials.Post event) {
        if (!ConfigHandler.renderBaubles || event.entityLiving.func_70660_b(Potion.field_76441_p) != null) {
            return;
        }
        EntityPlayer player = event.entityPlayer;
        InventoryBaubles inv = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        this.dispatchRenders(inv, (RenderPlayerEvent)event, IBaubleRender.RenderType.BODY);
        if (inv.func_70301_a(3) != null) {
            this.renderManaTablet((RenderPlayerEvent)event);
        }
        float yaw = player.field_70758_at + (player.field_70759_as - player.field_70758_at) * event.partialRenderTick;
        float yawOffset = player.field_70760_ar + (player.field_70761_aq - player.field_70760_ar) * event.partialRenderTick;
        float pitch = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * event.partialRenderTick;
        GL11.glPushMatrix();
        GL11.glRotatef((float)yawOffset, (float)0.0f, (float)-1.0f, (float)0.0f);
        GL11.glRotatef((float)(yaw - 270.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)pitch, (float)0.0f, (float)0.0f, (float)1.0f);
        this.dispatchRenders(inv, (RenderPlayerEvent)event, IBaubleRender.RenderType.HEAD);
        ItemStack helm = player.field_71071_by.func_70440_f(3);
        if (helm != null && helm.func_77973_b() instanceof ItemTerrasteelHelm) {
            ItemTerrasteelHelm.renderOnPlayer(helm, (RenderPlayerEvent)event);
        }
        ContributorFancinessHandler.render((RenderPlayerEvent.Specials)event);
        GL11.glPopMatrix();
    }

    private void dispatchRenders(InventoryBaubles inv, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ICosmeticAttachable attachable;
            ItemStack cosmetic;
            IPhantomInkable inkable;
            Item item;
            ItemStack stack = inv.func_70301_a(i);
            if (stack == null || (item = stack.func_77973_b()) instanceof IPhantomInkable && (inkable = (IPhantomInkable)item).hasPhantomInk(stack)) continue;
            if (item instanceof ICosmeticAttachable && (cosmetic = (attachable = (ICosmeticAttachable)item).getCosmeticItem(stack)) != null) {
                GL11.glPushMatrix();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ((IBaubleRender)cosmetic.func_77973_b()).onPlayerBaubleRender(cosmetic, event, type);
                GL11.glPopMatrix();
                continue;
            }
            if (!(item instanceof IBaubleRender)) continue;
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ((IBaubleRender)stack.func_77973_b()).onPlayerBaubleRender(stack, event, type);
            GL11.glPopMatrix();
        }
    }

    private void renderManaTablet(RenderPlayerEvent event) {
        EntityPlayer player = event.entityPlayer;
        boolean renderedOne = false;
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stack = player.field_71071_by.func_70301_a(i);
            if (stack == null || stack.func_77973_b() != ModItems.manaTablet) continue;
            Item item = stack.func_77973_b();
            GL11.glPushMatrix();
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            boolean armor = event.entityPlayer.func_82169_q(1) != null;
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.25f, (float)-0.85f, (float)(renderedOne ? (armor ? 0.2f : 0.28f) : (armor ? -0.3f : -0.25f)));
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
            for (int j = 0; j < 2; ++j) {
                IIcon icon = item.getIcon(stack, j);
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                Color color = new Color(item.func_82790_a(stack, 1));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
            }
            GL11.glPopMatrix();
            if (renderedOne) {
                return;
            }
            renderedOne = true;
        }
    }
}

