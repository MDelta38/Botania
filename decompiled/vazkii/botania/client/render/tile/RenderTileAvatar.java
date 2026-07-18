/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IAvatarWieldable;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelAvatar;
import vazkii.botania.common.block.tile.TileAvatar;

public class RenderTileAvatar
extends TileEntitySpecialRenderer {
    private static final float[] ROTATIONS = new float[]{180.0f, 0.0f, 90.0f, 270.0f};
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/avatar.png");
    private static final ModelAvatar model = new ModelAvatar();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        TileAvatar avatar = (TileAvatar)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        int meta = avatar.func_145831_w() != null ? avatar.func_145832_p() : 0;
        GL11.glTranslatef((float)0.5f, (float)1.6f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glRotatef((float)ROTATIONS[Math.max(Math.min(ROTATIONS.length - 1, meta - 2), 0)], (float)0.0f, (float)1.0f, (float)0.0f);
        model.render();
        ItemStack stack = avatar.func_70301_a(0);
        if (stack != null) {
            GL11.glPushMatrix();
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            float s = 0.4f;
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslated((double)-1.2f, (double)-3.5, (double)-0.65f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            int renderPass = 0;
            do {
                IIcon icon;
                if ((icon = stack.func_77973_b().getIcon(stack, renderPass)) == null) continue;
                Color color = new Color(stack.func_77973_b().func_82790_a(stack, renderPass));
                GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                float f = icon.func_94209_e();
                float f1 = icon.func_94212_f();
                float f2 = icon.func_94206_g();
                float f3 = icon.func_94210_h();
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            } while (++renderPass < stack.func_77973_b().getRenderPasses(stack.func_77960_j()));
            GL11.glPopMatrix();
            IAvatarWieldable wieldable = (IAvatarWieldable)stack.func_77973_b();
            Minecraft.func_71410_x().field_71446_o.func_110577_a(wieldable.getOverlayResource(avatar, stack));
            s = 1.01f;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glTranslatef((float)0.0f, (float)-0.01f, (float)0.0f);
            int light = 0xF000F0;
            int lightmapX = light % 65536;
            int lightmapY = light / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightmapX, (float)lightmapY);
            float alpha = (float)Math.sin((double)ClientTickHandler.ticksInGame / 20.0) / 2.0f + 0.5f;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(alpha + 0.183f));
            model.render();
            GL11.glPopMatrix();
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

