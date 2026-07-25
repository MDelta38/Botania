/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package drunkmafia.thaumicinfusion.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import drunkmafia.thaumicinfusion.common.item.ItemCoordinatePaper;
import drunkmafia.thaumicinfusion.common.item.ItemFocusInfusing;
import net.minecraft.item.Item;

public class TIItems {
    public static Item focusInfusing;
    public static Item coordinatePaper;

    public static void init() {
        focusInfusing = new ItemFocusInfusing().func_77655_b("FocusInfusion");
        coordinatePaper = new ItemCoordinatePaper().func_77655_b("CoordinatePaper");
        GameRegistry.registerItem((Item)focusInfusing, (String)"FocusInfusion", (String)"thaumicinfusion");
        GameRegistry.registerItem((Item)coordinatePaper, (String)"CoordinatePaper", (String)"thaumicinfusion");
    }
}

