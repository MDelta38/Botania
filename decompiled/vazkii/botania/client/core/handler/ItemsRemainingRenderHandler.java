/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public final class ItemsRemainingRenderHandler {
    private static int maxTicks = 30;
    private static int leaveTicks = 20;
    private static ItemStack stack;
    private static int ticks;
    private static int count;

    @SideOnly(value=Side.CLIENT)
    public static void render(ScaledResolution resolution, float partTicks) {
        if (ticks > 0 && stack != null) {
            int pos = maxTicks - ticks;
            Minecraft mc = Minecraft.func_71410_x();
            int x = resolution.func_78326_a() / 2 + 10 + Math.max(0, pos - leaveTicks);
            int y = resolution.func_78328_b() / 2;
            int start = maxTicks - leaveTicks;
            float alpha = (float)ticks + partTicks > (float)start ? 1.0f : ((float)ticks + partTicks) / (float)start;
            GL11.glDisable((int)3008);
            GL11.glEnable((int)3042);
            GL11.glEnable((int)32826);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            RenderHelper.func_74520_c();
            int xp = x + (int)(16.0f * (1.0f - alpha));
            GL11.glTranslatef((float)xp, (float)y, (float)0.0f);
            GL11.glScalef((float)alpha, (float)1.0f, (float)1.0f);
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, 0, 0);
            GL11.glScalef((float)(1.0f / alpha), (float)1.0f, (float)1.0f);
            GL11.glTranslatef((float)(-xp), (float)(-y), (float)0.0f);
            RenderHelper.func_74518_a();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)3042);
            String text = EnumChatFormatting.GREEN + stack.func_82833_r();
            if (count >= 0) {
                int max = stack.func_77976_d();
                int stacks = count / max;
                int rem = count % max;
                text = stacks == 0 ? "" + count : count + " (" + EnumChatFormatting.AQUA + stacks + EnumChatFormatting.RESET + "*" + EnumChatFormatting.GRAY + max + EnumChatFormatting.RESET + "+" + EnumChatFormatting.YELLOW + rem + EnumChatFormatting.RESET + ")";
            } else if (count == -1) {
                text = "\u221e";
            }
            int color = 0xFFFFFF | (int)(alpha * 255.0f) << 24;
            mc.field_71466_p.func_78261_a(text, x + 20, y + 6, color);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)3008);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void tick() {
        if (ticks > 0) {
            --ticks;
        }
    }

    public static void set(ItemStack stack, int count) {
        ItemsRemainingRenderHandler.stack = stack;
        ItemsRemainingRenderHandler.count = count;
        ticks = stack == null ? 0 : maxTicks;
    }

    public static void set(EntityPlayer player, ItemStack displayStack, Pattern pattern) {
        int count = 0;
        for (int i = 0; i < player.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stack = player.field_71071_by.func_70301_a(i);
            if (stack == null || !pattern.matcher(stack.func_77977_a()).find()) continue;
            count += stack.field_77994_a;
        }
        ItemsRemainingRenderHandler.set(displayStack, count);
    }
}

