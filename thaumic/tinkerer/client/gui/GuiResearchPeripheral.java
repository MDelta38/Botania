/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.client.gui.GuiResearchRecipe
 */
package thaumic.tinkerer.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Desktop;
import java.net.URI;
import net.minecraft.util.StatCollector;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.client.gui.GuiResearchRecipe;

@SideOnly(value=Side.CLIENT)
public class GuiResearchPeripheral
extends GuiResearchRecipe {
    public GuiResearchPeripheral(ResearchItem research) {
        super(research, 0, -100.0, -75.0);
    }

    protected void func_73869_a(char par1, int par2) {
        if (par2 == 28) {
            String url = StatCollector.func_74838_a((String)"ttresearch.webpage.peripherals");
            try {
                Desktop.getDesktop().browse(new URI(url));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            super.func_73869_a(par1, par2);
        }
    }
}

