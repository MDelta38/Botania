/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.integration.coloredlights;

import vazkii.botania.common.integration.coloredlights.ILightHelper;

public class LightHelperVanilla
implements ILightHelper {
    @Override
    public int makeRGBLightValue(float r, float g, float b, int currentLightValue) {
        return currentLightValue;
    }

    @Override
    public int getPackedColor(int meta, int light) {
        return light;
    }
}

