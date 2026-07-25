/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.client.gui.GuiResearchRecipe
 */
package thaumic.tinkerer.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.gui.GuiResearchPeripheral;

public class ClientTickHandler {
    public static int elapsedTicks;

    @SubscribeEvent
    public void tickEnd(TickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = ClientHelper.minecraft();
            if (mc.field_71462_r != null && mc.field_71462_r instanceof GuiResearchRecipe && !(mc.field_71462_r instanceof GuiResearchPeripheral)) {
                ResearchItem research = (ResearchItem)ReflectionHelper.getPrivateValue(GuiResearchRecipe.class, (Object)((GuiResearchRecipe)mc.field_71462_r), (int)9);
                if (research.key.equals("PERIPHERALS") || research.key.equals("GOLEM_CONNECTOR")) {
                    mc.func_147108_a((GuiScreen)new GuiResearchPeripheral(research));
                }
            }
            ToolModeHUDHandler.clientTick();
            ++elapsedTicks;
        }
    }
}

