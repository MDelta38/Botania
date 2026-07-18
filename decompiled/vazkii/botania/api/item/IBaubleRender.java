/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.api.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public interface IBaubleRender {
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack var1, RenderPlayerEvent var2, RenderType var3);

    public static enum RenderType {
        BODY,
        HEAD;

    }

    public static class Helper {
        public static void rotateIfSneaking(EntityPlayer player) {
            if (player.func_70093_af()) {
                Helper.applySneakingRotation();
            }
        }

        public static void applySneakingRotation() {
            GL11.glRotatef((float)28.64789f, (float)1.0f, (float)0.0f, (float)0.0f);
        }

        public static void translateToHeadLevel(EntityPlayer player) {
            GL11.glTranslated((double)0.0, (double)((double)((player != Minecraft.func_71410_x().field_71439_g ? 1.68f : 0.0f) - player.getDefaultEyeHeight()) + (player.func_70093_af() ? 0.0625 : 0.0)), (double)0.0);
        }
    }
}

