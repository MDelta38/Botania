/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelCrystalCube;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;

public class RenderTileCorporeaCrystalCube
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/crystalCube.png");
    ModelCrystalCube model = new ModelCrystalCube();
    EntityItem entity = null;

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        TileCorporeaCrystalCube cube = (TileCorporeaCrystalCube)tileentity;
        if (this.entity == null) {
            this.entity = new EntityItem(cube.func_145831_w(), (double)cube.field_145851_c, (double)cube.field_145848_d, (double)cube.field_145849_e, new ItemStack(Blocks.field_150348_b));
        }
        this.entity.field_70292_b = ClientTickHandler.ticksInGame;
        ItemStack stack = cube.getRequestTarget();
        this.entity.func_92058_a(stack);
        double time = (float)ClientTickHandler.ticksInGame + f;
        double worldTicks = tileentity.func_145831_w() == null ? 0.0 : time;
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        mc.field_71446_o.func_110577_a(texture);
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.renderBase();
        GL11.glTranslatef((float)0.0f, (float)((float)Math.sin(worldTicks / 20.0) * 0.05f), (float)0.0f);
        if (stack != null) {
            GL11.glPushMatrix();
            float s = stack.func_77973_b() instanceof ItemBlock ? 0.7f : 0.5f;
            GL11.glTranslatef((float)0.0f, (float)0.8f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ((Render)RenderManager.field_78727_a.field_78729_o.get(EntityItem.class)).func_76986_a((Entity)this.entity, 0.0, 0.0, 0.0, 1.0f, f);
            GL11.glPopMatrix();
            mc.field_71446_o.func_110577_a(texture);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.4f);
        this.model.renderCube();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (stack != null) {
            int count = cube.getItemCount();
            String countStr = "" + count;
            int color = 0xFFFFFF;
            if (count > 9999) {
                countStr = count / 1000 + "K";
                color = 0xFFFF00;
                if (count > 9999999) {
                    countStr = count / 10000000 + "M";
                    color = 65280;
                }
            }
            int colorShade = ((color |= 0xA0000000) & 0xFCFCFC) >> 2 | color & 0xFF000000;
            float s = 0.015625f;
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glDisable((int)2896);
            int l = mc.field_71466_p.func_78256_a(countStr);
            GL11.glTranslatef((float)0.0f, (float)55.0f, (float)0.0f);
            float tr = -16.5f;
            for (int i = 0; i < 4; ++i) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)tr);
                mc.field_71466_p.func_78276_b(countStr, -l / 2, 0, color);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.1f);
                mc.field_71466_p.func_78276_b(countStr, -l / 2 + 1, 1, colorShade);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-tr - 0.1f));
            }
            GL11.glEnable((int)2896);
        }
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }
}

