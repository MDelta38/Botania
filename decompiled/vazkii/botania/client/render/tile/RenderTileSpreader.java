/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.model.ModelSpreader;
import vazkii.botania.client.render.item.RenderLens;
import vazkii.botania.common.block.tile.mana.TileSpreader;

public class RenderTileSpreader
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/spreader.png");
    private static final ResourceLocation textureRs = new ResourceLocation("botania:textures/model/spreaderRedstone.png");
    private static final ResourceLocation textureDw = new ResourceLocation("botania:textures/model/spreaderDreamwood.png");
    private static final ResourceLocation textureHalloween = new ResourceLocation("botania:textures/model/spreader_halloween.png");
    private static final ResourceLocation textureRsHalloween = new ResourceLocation("botania:textures/model/spreaderRedstone_halloween.png");
    private static final ResourceLocation textureDwHalloween = new ResourceLocation("botania:textures/model/spreaderDreamwood_halloween.png");
    private static final ModelSpreader model = new ModelSpreader();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float ticks) {
        ResourceLocation r;
        TileSpreader spreader = (TileSpreader)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glRotatef((float)(spreader.rotationX + 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        GL11.glRotatef((float)spreader.rotationY, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        ResourceLocation resourceLocation = spreader.isRedstone() ? textureRs : (r = spreader.isDreamwood() ? textureDw : texture);
        if (ClientProxy.dootDoot) {
            r = spreader.isRedstone() ? textureRsHalloween : (spreader.isDreamwood() ? textureDwHalloween : textureHalloween);
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(r);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        double time = (float)ClientTickHandler.ticksInGame + ticks;
        if (spreader.isULTRA_SPREADER()) {
            Color color = Color.getHSBColor((float)((time * 5.0 + (double)new Random(spreader.field_145851_c ^ spreader.field_145848_d ^ spreader.field_145849_e).nextInt(10000)) % 360.0) / 360.0f, 0.4f, 0.9f);
            GL11.glColor3f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f));
        }
        model.render();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        double worldTicks = tileentity.func_145831_w() == null ? 0.0 : time;
        GL11.glRotatef((float)((float)worldTicks % 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)((float)Math.sin(worldTicks / 20.0) * 0.05f), (float)0.0f);
        model.renderCube();
        GL11.glPopMatrix();
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        ItemStack stack = spreader.func_70301_a(0);
        if (stack != null) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            ILens lens = (ILens)stack.func_77973_b();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.4f, (float)-1.4f, (float)-0.4375f);
            GL11.glScalef((float)0.8f, (float)0.8f, (float)0.8f);
            RenderLens.render(stack, lens.getLensColor(stack));
            GL11.glPopMatrix();
        }
        if (spreader.paddingColor != -1) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            Block block = Blocks.field_150404_cg;
            int color = spreader.paddingColor;
            RenderBlocks render = RenderBlocks.getInstance();
            float f = 0.0625f;
            GL11.glTranslatef((float)0.0f, (float)(-f), (float)0.0f);
            render.func_147800_a(block, color, 1.0f);
            GL11.glTranslatef((float)0.0f, (float)(-f * 15.0f), (float)0.0f);
            render.func_147800_a(block, color, 1.0f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)(f * 14.0f), (float)1.0f, (float)1.0f);
            render.func_147800_a(block, color, 1.0f);
            GL11.glPopMatrix();
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-f / 2.0f));
            GL11.glScalef((float)(f * 14.0f), (float)1.0f, (float)(f * 15.0f));
            render.func_147800_a(block, color, 1.0f);
            GL11.glTranslatef((float)0.0f, (float)(f * 15.0f), (float)0.0f);
            render.func_147800_a(block, color, 1.0f);
        }
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

