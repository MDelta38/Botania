/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.client.render.model.ModelChestOverlay;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import java.util.Calendar;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class TileEntityBoundChestRender
extends TileEntitySpecialRenderer {
    private static final ResourceLocation field_110637_a = new ResourceLocation("textures/entity/chest/normal.png");
    private static final ResourceLocation christmasTexture = new ResourceLocation("textures/entity/chest/christmas.png");
    private static final ResourceLocation overlayn = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlaynone.png");
    private static final ResourceLocation overlay0 = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlay0.png");
    private static final ResourceLocation overlay1 = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlay1.png");
    private static final ResourceLocation overlay2 = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlay2.png");
    private static final ResourceLocation overlay3 = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlay3.png");
    private static final ResourceLocation overlay4 = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlay4.png");
    private static final ResourceLocation[] overlays = new ResourceLocation[]{overlayn, overlay0, overlay1, overlay2, overlay3, overlay4};
    private static final ResourceLocation seal = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlayseal.png");
    private static final ResourceLocation christmasSeal = new ResourceLocation("thaumicexploration:textures/blocks/boundchestoverlaysealchristmas.png");
    private ModelChest theEnderChestModel = new ModelChest();
    private ModelChestOverlay theOverlayModel = new ModelChestOverlay();

    public void renderBoundChest(TileEntityBoundChest par1TileEntityEnderChest, double par2, double par4, double par6, float par8) {
        int i = 0;
        if (par1TileEntityEnderChest.func_145830_o()) {
            i = par1TileEntityEnderChest.func_145832_p();
        }
        this.func_147499_a(field_110637_a);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.func_147499_a(christmasTexture);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6 + 1.0f));
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int short1 = 0;
        if (i == 2) {
            short1 = 180;
        }
        if (i == 3) {
            short1 = 0;
        }
        if (i == 4) {
            short1 = 90;
        }
        if (i == 5) {
            short1 = -90;
        }
        GL11.glRotatef((float)short1, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float f1 = par1TileEntityEnderChest.prevLidAngle + (par1TileEntityEnderChest.lidAngle - par1TileEntityEnderChest.prevLidAngle) * par8;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        this.theEnderChestModel.field_78234_a.field_78795_f = -(f1 * (float)Math.PI / 2.0f);
        this.theEnderChestModel.func_78231_a();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderBoundChestFrame(TileEntityBoundChest par1TileEntityEnderChest, double par2, double par4, double par6, float par8) {
        int ticks;
        int i = 0;
        if (par1TileEntityEnderChest.func_145830_o()) {
            i = par1TileEntityEnderChest.func_145832_p();
        }
        if ((ticks = par1TileEntityEnderChest.getAccessTicks()) > 0) {
            double divisor = 13.0001;
            double frame = (double)(par1TileEntityEnderChest.getAccessTicks() - 1) / divisor - 1.0;
            int trueFrame = (int)Math.ceil(frame + 0.5);
            if (trueFrame > 5) {
                trueFrame = 5;
            }
            if (trueFrame < 0) {
                trueFrame = 0;
            }
            this.func_147499_a(overlays[trueFrame]);
        } else {
            this.func_147499_a(overlays[0]);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)32826);
        GL11.glBlendFunc((int)770, (int)771);
        float colormod = 1.0f;
        int j = par1TileEntityEnderChest.clientColor;
        GL11.glColor4f((float)(colormod * EntitySheep.field_70898_d[j][0]), (float)(colormod * EntitySheep.field_70898_d[j][1]), (float)(colormod * EntitySheep.field_70898_d[j][2]), (float)0.9f);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6 + 1.0f));
        float size = 1.0f;
        GL11.glScalef((float)size, (float)(-size), (float)(-size));
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int short1 = 0;
        if (i == 2) {
            short1 = 180;
        }
        if (i == 3) {
            short1 = 0;
        }
        if (i == 4) {
            short1 = 90;
        }
        if (i == 5) {
            short1 = -90;
        }
        GL11.glRotatef((float)short1, (float)0.0f, (float)1.0f, (float)0.0f);
        float offset = 0.0f;
        GL11.glTranslatef((float)(-0.5f - offset), (float)(-0.5f - 2.0f * offset), (float)(-0.5f + offset));
        float f1 = par1TileEntityEnderChest.prevLidAngle + (par1TileEntityEnderChest.lidAngle - par1TileEntityEnderChest.prevLidAngle) * par8;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        this.theOverlayModel.chestLid.field_78795_f = -(f1 * (float)Math.PI / 2.0f);
        this.theOverlayModel.renderAll();
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void renderBoundChestSeal(TileEntityBoundChest par1TileEntityEnderChest, double par2, double par4, double par6, float par8) {
        int i = 0;
        if (par1TileEntityEnderChest.func_145830_o()) {
            i = par1TileEntityEnderChest.func_145832_p();
        }
        int ticks = par1TileEntityEnderChest.getAccessTicks();
        this.func_147499_a(seal);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            this.func_147499_a(christmasSeal);
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        float colormod = 1.0f;
        int j = par1TileEntityEnderChest.clientColor;
        GL11.glColor4f((float)(colormod * EntitySheep.field_70898_d[j][0]), (float)(colormod * EntitySheep.field_70898_d[j][1]), (float)(colormod * EntitySheep.field_70898_d[j][2]), (float)1.0f);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6 + 1.0f));
        float size = 1.0f;
        GL11.glScalef((float)size, (float)(-size), (float)(-size));
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        int short1 = 0;
        if (i == 2) {
            short1 = 180;
        }
        if (i == 3) {
            short1 = 0;
        }
        if (i == 4) {
            short1 = 90;
        }
        if (i == 5) {
            short1 = -90;
        }
        GL11.glRotatef((float)short1, (float)0.0f, (float)1.0f, (float)0.0f);
        float offset = 0.0f;
        GL11.glTranslatef((float)(-0.5f - offset), (float)(-0.5f - 2.0f * offset), (float)(-0.5f + offset));
        float f1 = par1TileEntityEnderChest.prevLidAngle + (par1TileEntityEnderChest.lidAngle - par1TileEntityEnderChest.prevLidAngle) * par8;
        f1 = 1.0f - f1;
        f1 = 1.0f - f1 * f1 * f1;
        this.theOverlayModel.chestLid.field_78795_f = -(f1 * (float)Math.PI / 2.0f);
        this.theOverlayModel.renderAll();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderBoundChest((TileEntityBoundChest)par1TileEntity, par2, par4, par6, par8);
        this.renderBoundChestFrame((TileEntityBoundChest)par1TileEntity, par2, par4, par6, par8);
        this.renderBoundChestSeal((TileEntityBoundChest)par1TileEntity, par2, par4, par6, par8);
    }
}

