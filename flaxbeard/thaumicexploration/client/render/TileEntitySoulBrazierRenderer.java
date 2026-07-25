/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package flaxbeard.thaumicexploration.client.render;

import flaxbeard.thaumicexploration.client.render.model.ModelSoulBrazier;
import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class TileEntitySoulBrazierRenderer
extends TileEntitySpecialRenderer {
    private ModelSoulBrazier brazierModel = new ModelSoulBrazier();
    private static final ResourceLocation baseTexture = new ResourceLocation("thaumicexploration:textures/models/soulBrazier.png");

    public void func_147500_a(TileEntity tile, double d0, double d1, double d2, float par8) {
        TileEntitySoulBrazier brazier = (TileEntitySoulBrazier)tile;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d0 + 0.5f), (float)((float)d1 + 1.5f), (float)((float)d2 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_147499_a(baseTexture);
        this.brazierModel.func_78088_a(null, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        if (brazier.checkPower()) {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glDepthMask((boolean)false);
            float alpha = MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71451_h.field_70173_aa / 8.0f)) * 0.1f + 0.5f;
            UtilsFX.bindTexture((String)"textures/misc/node_bubble.png");
            int count = brazier.count % 60 == 0 || (brazier.count - 1) % 60 == 0 ? 7 : 37;
            UtilsFX.renderFacingQuad((double)((double)tile.field_145851_c + 0.5), (double)((double)tile.field_145848_d + 1.5), (double)((double)tile.field_145849_e + 0.5), (float)0.0f, (float)0.7f, (float)((float)count / 37.0f * alpha * 4.0f), (int)1, (int)0, (float)par8, (int)11665663);
            GL11.glDepthMask((boolean)true);
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }
}

