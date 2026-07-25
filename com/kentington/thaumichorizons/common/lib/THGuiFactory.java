/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.IModGuiFactory
 *  cpw.mods.fml.client.IModGuiFactory$RuntimeOptionCategoryElement
 *  cpw.mods.fml.client.IModGuiFactory$RuntimeOptionGuiHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.THConfigGUI;
import cpw.mods.fml.client.IModGuiFactory;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class THGuiFactory
implements IModGuiFactory {
    public void initialize(Minecraft minecraftInstance) {
    }

    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return THConfigGUI.class;
    }

    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
        return null;
    }
}

