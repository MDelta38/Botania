/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.api.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;

public interface IBotaniaBoss
extends IBossDisplayData {
    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getBossBarTexture();

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarTextureRect();

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarHPTextureRect();

    @SideOnly(value=Side.CLIENT)
    public void bossBarRenderCallback(ScaledResolution var1, int var2, int var3);
}

