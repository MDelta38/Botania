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

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.model.ModelTeruTeruBozu;
import vazkii.botania.common.Botania;

public class RenderTileTeruTeruBozu
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/teruTeruBozu.png");
    private static final ResourceLocation textureHalloween = new ResourceLocation("botania:textures/model/teruTeruBozu_halloween.png");
    ModelTeruTeruBozu model = new ModelTeruTeruBozu();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        boolean hasWorld;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ClientProxy.dootDoot ? textureHalloween : texture);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        double time = (float)Botania.proxy.getWorldElapsedTicks() + f;
        boolean bl = hasWorld = tileentity.func_145831_w() != null;
        if (hasWorld) {
            time += (double)new Random(tileentity.field_145851_c ^ tileentity.field_145848_d ^ tileentity.field_145849_e).nextInt(1000);
        }
        GL11.glTranslatef((float)0.5f, (float)(-1.25f + (hasWorld ? (float)Math.sin(time * (double)0.01f) * 0.05f : 0.0f)), (float)-0.5f);
        if (hasWorld) {
            GL11.glRotated((double)(time * 0.3), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotatef((float)(4.0f * (float)Math.sin(time * (double)0.05f)), (float)0.0f, (float)0.0f, (float)1.0f);
            float s = 0.75f;
            GL11.glScalef((float)s, (float)s, (float)s);
        }
        this.model.render();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

