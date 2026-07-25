/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.common.config.ConfigBlocks
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.client.render.model.ModelCandle;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.config.ConfigBlocks;

@SideOnly(value=Side.CLIENT)
public class TileEntityFloatyCandleRender
extends TileEntitySpecialRenderer {
    private ModelCandle candleModel = new ModelCandle();
    private static final ResourceLocation candleTexture = new ResourceLocation("thaumicexploration:textures/models/floatyCandle.png");
    private static final ResourceLocation wickTexture = new ResourceLocation("thaumicexploration:textures/models/floatyCandleStub.png");

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        GL11.glPushMatrix();
        int ticks = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
        int offset = tileentity.field_145851_c + tileentity.field_145848_d + tileentity.field_145849_e;
        float move = 0.2f * MathHelper.func_76126_a((float)((float)(offset * 10 + ticks) / 30.0f));
        GL11.glTranslatef((float)((float)d0), (float)((float)d1 + move), (float)((float)d2));
        this.func_147499_a(wickTexture);
        this.candleModel.renderWick();
        this.func_147499_a(candleTexture);
        Color color = new Color(ConfigBlocks.blockCandle.func_149741_i(tileentity.func_145831_w().func_72805_g(tileentity.field_145851_c, tileentity.field_145848_d, tileentity.field_145849_e)));
        GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
        this.candleModel.renderAll(ticks);
        GL11.glPopMatrix();
    }
}

