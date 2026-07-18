/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.model.ModelCocoon;
import vazkii.botania.common.block.tile.TileCocoon;

public class RenderTileCocoon
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/cocoon.png");
    ModelCocoon model = new ModelCocoon();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileCocoon cocoon = (TileCocoon)tileentity;
        float rot = 0.0f;
        float modval = 60.0f - (float)cocoon.timePassed / 2400.0f * 30.0f;
        if ((float)cocoon.timePassed % modval < 10.0f) {
            float mod = ((float)cocoon.timePassed + f) % modval;
            float v = mod / 5.0f * (float)Math.PI * 2.0f;
            rot = (float)Math.sin(v) * (float)Math.log((float)cocoon.timePassed + f);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.5f, (float)-0.6875f, (float)-0.4375f);
        GL11.glRotatef((float)rot, (float)0.0f, (float)1.0f, (float)0.0f);
        this.model.render();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

