/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBore;
import thaumcraft.client.renderers.models.ModelBoreEmit;
import thaumcraft.client.renderers.models.ModelJar;
import thaumcraft.common.tiles.TileArcaneBore;

public class TileArcaneBoreRenderer
extends TileEntitySpecialRenderer {
    private ModelBoreEmit modelEmit = new ModelBoreEmit();
    private ModelBore modelBore = new ModelBore();
    private ModelJar modelJar = new ModelJar();

    public void renderEntityAt(TileArcaneBore bore, double x, double y, double z, float fq) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        UtilsFX.bindTexture("textures/models/Bore.png");
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)((float)bore.rotX - bore.vRadX + fq * (float)bore.speedX), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glPushMatrix();
        if (bore.baseOrientation.ordinal() == 0) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        this.modelBore.renderBase();
        GL11.glPopMatrix();
        GL11.glRotatef((float)((float)bore.rotZ - bore.vRadZ + fq * (float)bore.speedZ), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)-0.5f, (float)0.0f);
        this.modelBore.renderNozzle();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glRotatef((float)bore.topRotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.5f, (float)0.0f);
        this.modelEmit.render(bore.hasFocus);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        float rotation = (float)(Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45) + fq;
        GL11.glTranslatef((float)0.0f, (float)-0.17f, (float)0.0f);
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(-(rotation * 8.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)10.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.4f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        rotation = (float)(Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45) + fq;
        GL11.glTranslatef((float)0.0f, (float)-0.21f, (float)0.0f);
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(rotation * 8.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)10.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.3f, 1.0f, 1.0f, 1.0f, 200, 771, 0.8f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        rotation = (float)(Minecraft.func_71410_x().field_71451_h.field_70173_aa % 45) + fq;
        GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(-(rotation * 8.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-10.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        UtilsFX.renderQuadCenteredFromTexture("textures/misc/vortex.png", 0.2f, 1.0f, 1.0f, 1.0f, 200, 771, 0.8f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/jar.png");
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.modelJar.Core.func_78785_a(0.0625f);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d, double d1, double d2, float f) {
        this.renderEntityAt((TileArcaneBore)tileentity, d, d1, d2, f);
    }
}

