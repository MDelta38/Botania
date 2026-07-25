/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiConfig
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraftforge.common.config.ConfigElement
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;

public class THConfigGUI
extends GuiConfig {
    public THConfigGUI(GuiScreen parent) {
        super(parent, new ConfigElement(ThaumicHorizons.config.getCategory("general")).getChildElements(), "ThaumicHorizons", false, false, GuiConfig.getAbridgedConfigPath((String)ThaumicHorizons.config.toString()));
    }
}

