/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.integration.coloredlights;

import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.integration.coloredlights.ILightHelper;

public class LightHelperColored
implements ILightHelper {
    @Override
    public int makeRGBLightValue(float r, float g, float b, int currentLightValue) {
        return ColoredLightHelper.makeRGBLightValue(r, g, b, currentLightValue);
    }

    @Override
    public int getPackedColor(int meta, int light) {
        return ColoredLightHelper.getPackedColor(meta, light);
    }
}

