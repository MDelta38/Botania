/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package vazkii.botania.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import vazkii.botania.common.core.handler.ConfigHandler;

public class GuiBotaniaConfig
extends GuiConfig {
    public GuiBotaniaConfig(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory("general")).getChildElements(), "Botania", false, false, GuiConfig.getAbridgedConfigPath((String)ConfigHandler.config.toString()));
    }
}

