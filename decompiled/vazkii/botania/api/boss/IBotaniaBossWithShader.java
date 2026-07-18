/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package vazkii.botania.api.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.internal.ShaderCallback;

public interface IBotaniaBossWithShader
extends IBotaniaBoss {
    @SideOnly(value=Side.CLIENT)
    public int getBossBarShaderProgram(boolean var1);

    @SideOnly(value=Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean var1, int var2);
}

