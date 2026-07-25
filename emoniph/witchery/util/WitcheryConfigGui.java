/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class WitcheryConfigGui
extends GuiConfig {
    public WitcheryConfigGui(GuiScreen parent) {
        super(parent, new ConfigElement(Witchery.config.getCategory("general")).getChildElements(), "witchery", false, false, GuiConfig.getAbridgedConfigPath((String)Witchery.config.toString()));
    }
}

