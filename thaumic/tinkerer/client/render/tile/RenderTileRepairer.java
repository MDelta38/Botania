/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.tile;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.model.ModelRepairer;
import thaumic.tinkerer.common.block.tile.TileRepairer;

public class RenderTileRepairer
extends TileEntitySpecialRenderer {
    private static final ResourceLocation modelTex = new ResourceLocation("ttinkerer:textures/model/repairer.png");
    private static final ResourceLocation repair = new ResourceLocation("ttinkerer:textures/misc/repair.png");
    private static final ResourceLocation repairOff = new ResourceLocation("ttinkerer:textures/misc/repairOff.png");
    ModelRepairer model = new ModelRepairer();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float t) {
        int meta;
        int n = meta = tileentity.func_145831_w() == null ? 3 : tileentity.func_145832_p();
        int rotation = meta == 2 ? 0 : (meta == 3 ? 180 : (meta == 4 ? 270 : 90));
        TileRepairer repairer = (TileRepairer)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.func_147499_a(modelTex);
        GL11.glTranslatef((float)0.0f, (float)2.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        this.model.render();
        GL11.glDisable((int)3042);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        ItemStack item = ((TileRepairer)tileentity).func_70301_a(0);
        if (item != null) {
            GL11.glPushMatrix();
            float scale = 0.5f;
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glTranslatef((float)-0.5f, (float)((float)(-2.5 + Math.sin((float)repairer.ticksExisted / 10.0f) * (double)0.1f)), (float)0.0f);
            float deg = (float)repairer.ticksExisted * 0.75f % 360.0f;
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.03125f);
            GL11.glRotatef((float)deg, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.03125f);
            this.func_147499_a(TextureMap.field_110576_c);
            int renderPass = 0;
            do {
                IIcon icon;
                if ((icon = item.func_77973_b().getIcon(item, renderPass)) == null) continue;
                Color color = new Color(item.func_77973_b().func_82790_a(item, renderPass));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            } while (++renderPass < item.func_77973_b().getRenderPasses(item.func_77960_j()));
            GL11.glPopMatrix();
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.func_147499_a(modelTex);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        this.model.renderGlass();
        GL11.glDisable((int)3042);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.renderOverlay((TileRepairer)tileentity, ((TileRepairer)tileentity).tookLastTick ? repair : repairOff, 1.25);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderOverlay(TileRepairer tablet, ResourceLocation texture, double size) {
        Minecraft mc = ClientHelper.minecraft();
        mc.field_71446_o.func_110577_a(texture);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)0.0f, (float)-0.525f, (float)0.0f);
        float deg = (float)tablet.ticksExisted * 0.75f % 360.0f;
        GL11.glRotatef((float)deg, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator tess = Tessellator.field_78398_a;
        double size1 = size / 2.0;
        double size2 = -size1;
        tess.func_78382_b();
        tess.func_78374_a(size2, 0.0, size1, 0.0, 1.0);
        tess.func_78374_a(size1, 0.0, size1, 1.0, 1.0);
        tess.func_78374_a(size1, 0.0, size2, 1.0, 0.0);
        tess.func_78374_a(size2, 0.0, size2, 0.0, 0.0);
        tess.func_78381_a();
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }
}

