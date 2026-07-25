/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.models.ModelArcaneWorkbench
 */
package witchinggadgets.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.ModelArcaneWorkbench;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;

public class TileRenderLabelLibrary
extends TileEntitySpecialRenderer {
    static ModelArcaneWorkbench model = new ModelArcaneWorkbench();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        TileEntityLabelLibrary tile = (TileEntityLabelLibrary)tileentity;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y), (float)((float)z + 0.5f));
        switch (tile.facing) {
            case 2: {
                break;
            }
            case 3: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                break;
            }
            case 5: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            }
        }
        ClientUtilities.bindTexture("witchinggadgets:textures/models/labelLib.png");
        GL11.glDisable((int)2884);
        for (ModelRenderer m : TileRenderLabelLibrary.model.field_78092_r) {
            m.func_78785_a(0.0625f);
        }
        if (tile.func_145831_w() != null) {
            GL11.glBlendFunc((int)770, (int)771);
            ClientUtilities.bindTexture("textures/atlas/items.png");
            GL11.glTranslatef((float)-0.375f, (float)1.0f, (float)-0.4375f);
            GL11.glScaled((double)0.375, (double)0.75, (double)0.375);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            this.render2DItem(tile.func_70301_a(1));
            if (tile.func_70301_a(0) != null) {
                GL11.glTranslatef((float)0.875f, (float)1.4f, (float)0.0f);
                GL11.glScaled((double)1.0, (double)1.0, (double)0.5);
                int stacksize = Math.max(1, tile.func_70301_a((int)0).field_77994_a / 6);
                for (int i = 0; i < stacksize; ++i) {
                    GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                    GL11.glRotatef((float)(10 + i * i), (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                    GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.05f);
                    this.render2DItem(tile.func_70301_a(0));
                }
            }
        }
        GL11.glPopMatrix();
    }

    void render2DItem(ItemStack stack) {
        if (stack != null) {
            for (int pass = 0; pass < stack.func_77973_b().getRenderPasses(stack.func_77960_j()); ++pass) {
                int c = stack.func_77973_b().func_82790_a(stack, pass);
                GL11.glColor3f((float)((float)(c >> 16 & 0xFF) / 255.0f), (float)((float)(c >> 8 & 0xFF) / 255.0f), (float)((float)(c & 0xFF) / 255.0f));
                IIcon iicon = stack.func_77973_b().getIcon(stack, pass);
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)iicon.func_94212_f(), (float)iicon.func_94206_g(), (float)iicon.func_94209_e(), (float)iicon.func_94210_h(), (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    static {
        ModelRenderer bookOut = new ModelRenderer((ModelBase)model, 72, 8);
        bookOut.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 6);
        bookOut.func_78793_a(-7.0f, 16.0f, 0.0f);
        TileRenderLabelLibrary.model.field_78092_r.add(bookOut);
        ModelRenderer bookIn = new ModelRenderer((ModelBase)model, 72, 22);
        bookIn.func_78789_a(0.0f, 0.0f, 0.0f, 3, 7, 5);
        bookIn.func_78793_a(-7.0f, 16.0f, 1.0f);
        TileRenderLabelLibrary.model.field_78092_r.add(bookIn);
        ModelRenderer bookOut2 = new ModelRenderer((ModelBase)model, 90, 8);
        bookOut2.func_78789_a(0.0f, 0.0f, 0.0f, 6, 3, 8);
        bookOut2.func_78793_a(1.0f, 16.0001f, -7.0f);
        TileRenderLabelLibrary.model.field_78092_r.add(bookOut2);
        ModelRenderer bookIn2 = new ModelRenderer((ModelBase)model, 90, 19);
        bookIn2.func_78789_a(0.0f, 0.0f, 0.0f, 5, 3, 7);
        bookIn2.func_78793_a(2.0f, 16.0001f, -6.5f);
        TileRenderLabelLibrary.model.field_78092_r.add(bookIn2);
    }
}

