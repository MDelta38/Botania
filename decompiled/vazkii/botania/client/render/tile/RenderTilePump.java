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
import vazkii.botania.client.model.ModelPump;
import vazkii.botania.common.block.tile.mana.TilePump;

public class RenderTilePump
extends TileEntitySpecialRenderer {
    private static final float[] ROTATIONS = new float[]{180.0f, 0.0f, 90.0f, 270.0f};
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/pump.png");
    private static final ModelPump model = new ModelPump();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TilePump pump = (TilePump)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        int meta = pump.func_145831_w() != null ? pump.func_145832_p() : 0;
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)ROTATIONS[Math.max(Math.min(ROTATIONS.length - 1, meta - 2), 0)], (float)0.0f, (float)1.0f, (float)0.0f);
        model.render(Math.max(0.0f, Math.min(8.0f, pump.innerRingPos + pump.moving * f)));
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

