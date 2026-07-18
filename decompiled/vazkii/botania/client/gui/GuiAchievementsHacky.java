/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.achievement.GuiAchievements
 *  net.minecraft.stats.StatFileWriter
 */
package vazkii.botania.client.gui;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.stats.StatFileWriter;
import vazkii.botania.common.achievement.ModAchievements;

public class GuiAchievementsHacky
extends GuiAchievements {
    public GuiAchievementsHacky(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_) {
        super(p_i45026_1_, p_i45026_2_);
        ReflectionHelper.setPrivateValue(GuiAchievements.class, (Object)((Object)this), (Object)ModAchievements.pageIndex, (String[])new String[]{"currentPage"});
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        ((GuiButton)this.field_146292_n.get((int)1)).field_146126_j = ModAchievements.botaniaPage.getName();
    }
}

