/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package vazkii.botania.api.corporea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface ICorporeaAutoCompleteController {
    @SideOnly(value=Side.CLIENT)
    public boolean shouldAutoComplete();
}

