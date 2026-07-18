/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelAltar;
import vazkii.botania.common.block.tile.TileAltar;

public class RenderTileAltar
extends TileEntitySpecialRenderer {
    private static final ResourceLocation[] textures = new ResourceLocation[]{new ResourceLocation("botania:textures/model/altar.png"), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 0)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 1)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 2)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 3)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 4)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 5)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 6)), new ResourceLocation(String.format("botania:textures/model/altarMeta%d.png", 7))};
    private static final ResourceLocation textureMossy = new ResourceLocation("botania:textures/model/altarMossy.png");
    ModelAltar model = new ModelAltar();
    RenderItem renderItem = new RenderItem();
    public static int forceMeta = -1;

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float pticks) {
        TileAltar altar = (TileAltar)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(altar.isMossy ? textureMossy : textures[Math.min(textures.length - 1, forceMeta == -1 ? tileentity.func_145832_p() : forceMeta)]);
        GL11.glTranslated((double)(d0 + 0.5), (double)(d1 + 1.5), (double)(d2 + 0.5));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        boolean water = altar.hasWater();
        boolean lava = altar.hasLava();
        if (water || lava) {
            GL11.glPushMatrix();
            float s = 0.0390625f;
            float v = 0.125f;
            float w = -v * 2.5f;
            if (water) {
                int petals = 0;
                for (int i = 0; i < altar.func_70302_i_() && altar.func_70301_a(i) != null; ++i) {
                    ++petals;
                }
                if (petals > 0) {
                    float modifier = 6.0f;
                    float rotationModifier = 0.25f;
                    float radiusBase = 1.2f;
                    float radiusMod = 0.1f;
                    double ticks = (double)((float)ClientTickHandler.ticksInGame + pticks) * 0.5;
                    float offsetPerPetal = 360 / petals;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)-0.05f, (float)-0.5f, (float)0.0f);
                    GL11.glScalef((float)v, (float)v, (float)v);
                    for (int i = 0; i < petals; ++i) {
                        float offset = offsetPerPetal * (float)i;
                        float deg = (int)(ticks / 0.25 % 360.0 + (double)offset);
                        float rad = deg * (float)Math.PI / 180.0f;
                        float radiusX = (float)((double)1.2f + (double)0.1f * Math.sin(ticks / 6.0));
                        float radiusZ = (float)((double)1.2f + (double)0.1f * Math.cos(ticks / 6.0));
                        float x = (float)((double)radiusX * Math.cos(rad));
                        float z = (float)((double)radiusZ * Math.sin(rad));
                        float y = (float)Math.cos((ticks + (double)(50 * i)) / 5.0) / 10.0f;
                        GL11.glPushMatrix();
                        GL11.glTranslatef((float)x, (float)y, (float)z);
                        float xRotate = (float)Math.sin(ticks * 0.25) / 2.0f;
                        float yRotate = (float)Math.max((double)0.6f, Math.sin(ticks * (double)0.1f) / 2.0 + 0.5);
                        float zRotate = (float)Math.cos(ticks * 0.25) / 2.0f;
                        GL11.glTranslatef((float)(v /= 2.0f), (float)v, (float)v);
                        GL11.glRotatef((float)deg, (float)xRotate, (float)yRotate, (float)zRotate);
                        GL11.glTranslatef((float)(-v), (float)(-v), (float)(-v));
                        v *= 2.0f;
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                        ItemStack stack = altar.func_70301_a(i);
                        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
                        IIcon icon = stack.func_77973_b().getIcon(stack, 0);
                        if (icon != null) {
                            Color color = new Color(stack.func_77973_b().func_82790_a(stack, 0));
                            GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                            float f = icon.func_94209_e();
                            float f1 = icon.func_94212_f();
                            float f2 = icon.func_94206_g();
                            float f3 = icon.func_94210_h();
                            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                        }
                        GL11.glPopMatrix();
                    }
                    GL11.glPopMatrix();
                }
            }
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            Block block = lava ? Blocks.field_150353_l : Blocks.field_150355_j;
            int brightness = lava ? 240 : -1;
            float alpha = lava ? 1.0f : 0.7f;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            if (lava) {
                GL11.glDisable((int)2896);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            GL11.glTranslatef((float)w, (float)-0.3f, (float)w);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            this.renderIcon(0, 0, block.func_149691_a(0, 0), 16, 16, brightness);
            if (lava) {
                GL11.glEnable((int)2896);
            }
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        forceMeta = -1;
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        if (brightness != -1) {
            tessellator.func_78380_c(brightness);
        }
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par5), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + par5), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + 0), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94206_g());
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94206_g());
        tessellator.func_78381_a();
    }
}

