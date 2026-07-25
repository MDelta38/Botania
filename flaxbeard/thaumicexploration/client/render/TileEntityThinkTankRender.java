/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBook
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelBrain
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.client.render.model.ModelLargeJar;
import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBrain;

@SideOnly(value=Side.CLIENT)
public class TileEntityThinkTankRender
extends TileEntitySpecialRenderer {
    private ModelLargeJar model = new ModelLargeJar();
    private ModelBrain brain = new ModelBrain();
    private ModelRenderer modelBox;
    private float oldRotationDegrees;
    private ModelBook enchantmentBook = new ModelBook();
    private int[] lastDirection = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    private int[] direction = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    private int[] numBooks = new int[]{6, 8, 8};
    private static final ResourceLocation brineTexture = new ResourceLocation("thaumicexploration:textures/models/largejarbrine.png");
    private static final ResourceLocation enchantingTableBookTextures = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private static final ResourceLocation largeJarTexture = new ResourceLocation("thaumicexploration:textures/models/largejar.png");

    public void renderInventoryIcon(double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.1f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderBrainInventory(x, y, z, f);
        UtilsFX.bindTexture((String)"textures/models/jar.png");
        this.model.renderLid();
        this.func_147499_a(largeJarTexture);
        this.model.renderAll();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntityThinkTank tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderBrain(tile, x, y, z, f);
        UtilsFX.bindTexture((String)"textures/models/jar.png");
        this.model.renderLid();
        this.func_147499_a(largeJarTexture);
        this.model.renderAll();
        int space = tile.space;
        for (int i = 0; i < this.numBooks[space]; ++i) {
            GL11.glPushMatrix();
            if ((tile.rotationTicks + i) % 7 == 0) {
                this.lastDirection[i] = this.direction[i];
                this.direction[i] = 4 - new Random().nextInt(4);
            }
            float f4 = (float)((tile.rotationTicks + i) % 7) / 7.0f;
            float f1 = (float)tile.rotationTicks + f;
            this.func_147499_a(enchantingTableBookTextures);
            float rotationDegrees = -2 * (tile.rotationTicks % 360);
            float bob = (float)(i % 2 == 0 || i == 0 ? 1 : -1) * MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa / 28.0f)) * 0.24f;
            GL11.glTranslatef((float)0.0f, (float)(-0.5f + bob), (float)0.0f);
            GL11.glRotatef((float)(rotationDegrees + (float)(360 / this.numBooks[space] * i)), (float)0.0f, (float)1.0f, (float)0.0f);
            switch (space) {
                case 0: {
                    GL11.glTranslatef((float)-1.4f, (float)0.0f, (float)0.0f);
                    break;
                }
                default: {
                    GL11.glTranslatef((float)-2.0f, (float)0.0f, (float)0.0f);
                }
            }
            GL11.glEnable((int)2884);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((float)tile.warmedUpNumber / 40.0f));
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            Minecraft.func_71410_x().field_71439_g.field_70170_p.func_72869_a("enchantmenttable", (double)((float)x + 0.5f) + 2.0 * Math.sin(rotationDegrees), (double)((float)y), (double)((float)z + 0.5f) + 2.0 * Math.cos(rotationDegrees), 0.0, (double)0.1f, 0.0);
            switch (this.direction[i]) {
                case 2: {
                    this.enchantmentBook.func_78088_a((Entity)null, f1, 1.0f - f4, 0.0f, 0.75f, 0.0f, 0.0625f);
                    break;
                }
                case 3: {
                    this.enchantmentBook.func_78088_a((Entity)null, f1, f4, 0.0f, 0.75f, 0.0f, 0.0625f);
                    break;
                }
                default: {
                    this.enchantmentBook.func_78088_a((Entity)null, f1, 0.0f, 0.0f, 0.75f, 0.0f, 0.0625f);
                }
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
            GL11.glDisable((int)2884);
            GL11.glEnable((int)2884);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void renderBrain(TileEntityThinkTank te, double x, double y, double z, float f) {
        float bob = MathHelper.func_76126_a((float)((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa / 14.0f)) * 0.03f + 0.03f;
        float bob2 = MathHelper.func_76126_a((float)(((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa + 9.32f) / 14.0f)) * 0.03f + 0.03f;
        float bob3 = MathHelper.func_76126_a((float)(((float)Minecraft.func_71410_x().field_71439_g.field_70173_aa + 4.66f) / 14.0f)) * 0.03f + 0.03f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob + (float)te.warmedUpNumber / -160.0f), (float)0.0f);
        float rotationDegrees = 2 * (te.rotationTicks % 360);
        GL11.glRotatef((float)rotationDegrees, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob2 + (float)te.warmedUpNumber / -160.0f), (float)0.0f);
        GL11.glRotatef((float)(rotationDegrees + 120.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob3 + (float)te.warmedUpNumber / -160.0f), (float)0.0f);
        GL11.glRotatef((float)(rotationDegrees + 240.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        this.func_147499_a(brineTexture);
        this.model.renderBrine();
    }

    public void renderBrainInventory(double x, double y, double z, float f) {
        float bob = MathHelper.func_76126_a((float)0.0f) * 0.03f + 0.03f;
        float bob2 = MathHelper.func_76126_a((float)0.66571426f) * 0.03f + 0.03f;
        float bob3 = MathHelper.func_76126_a((float)0.33285713f) * 0.03f + 0.03f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob + -0.25f), (float)0.0f);
        float rotationDegrees = 0.0f;
        GL11.glRotatef((float)rotationDegrees, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob2 + -0.25f), (float)0.0f);
        GL11.glRotatef((float)(rotationDegrees + 120.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-0.55f + bob3 + -0.25f), (float)0.0f);
        GL11.glRotatef((float)(rotationDegrees + 240.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.266f);
        UtilsFX.bindTexture((String)"textures/models/brain2.png");
        GL11.glScalef((float)0.3f, (float)0.3f, (float)0.3f);
        this.brain.render();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
        this.func_147499_a(brineTexture);
        this.model.renderBrine();
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileEntityThinkTank)par1TileEntity, par2, par4, par6, par8);
    }
}

