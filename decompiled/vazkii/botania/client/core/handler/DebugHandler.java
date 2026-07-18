/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Text
 *  org.lwjgl.opengl.ContextCapabilities
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLContext
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import vazkii.botania.client.fx.ParticleRenderDispatcher;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;

public final class DebugHandler {
    private static final String PREFIX = EnumChatFormatting.GREEN + "[Botania] " + EnumChatFormatting.RESET;

    @SubscribeEvent
    public void onDrawDebugText(RenderGameOverlayEvent.Text event) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (Minecraft.func_71410_x().field_71474_y.field_74330_P) {
            event.left.add(null);
            String version = "r1.8-249";
            if (version.contains("GRADLE")) {
                version = "N/A";
            }
            event.left.add(PREFIX + "pS: " + ParticleRenderDispatcher.sparkleFxCount + ", pFS: " + ParticleRenderDispatcher.fakeSparkleFxCount + ", pW: " + ParticleRenderDispatcher.wispFxCount + ", pDIW: " + ParticleRenderDispatcher.depthIgnoringWispFxCount + ", pLB: " + ParticleRenderDispatcher.lightningCount);
            event.left.add(PREFIX + "netColl: " + ManaNetworkHandler.instance.getAllCollectorsInWorld((World)world).size() + ", netPool: " + ManaNetworkHandler.instance.getAllPoolsInWorld((World)world).size() + ", rv: " + version);
            if (GuiScreen.func_146271_m() && GuiScreen.func_146272_n()) {
                event.left.add(PREFIX + "Config Context");
                event.left.add("  shaders.enabled: " + ConfigHandler.useShaders);
                event.left.add("  shaders.secondaryUnit: " + ConfigHandler.glSecondaryTextureUnit);
                ContextCapabilities caps = GLContext.getCapabilities();
                event.left.add(PREFIX + "OpenGL Context");
                event.left.add("  GL_VERSION: " + GL11.glGetString((int)7938));
                event.left.add("  GL_RENDERER: " + GL11.glGetString((int)7937));
                event.left.add("  GL_SHADING_LANGUAGE_VERSION: " + GL11.glGetString((int)35724));
                event.left.add("  GL_MAX_TEXTURE_IMAGE_UNITS_ARB: " + GL11.glGetInteger((int)34930));
                event.left.add("  GL_ARB_multitexture: " + caps.GL_ARB_multitexture);
                event.left.add("  GL_ARB_texture_non_power_of_two: " + caps.GL_ARB_texture_non_power_of_two);
                event.left.add("  OpenGL13: " + caps.OpenGL13);
            } else if (Minecraft.field_142025_a) {
                event.left.add(PREFIX + "SHIFT+CMD for context");
            } else {
                event.left.add(PREFIX + "SHIFT+CTRL for context");
            }
        }
    }
}

