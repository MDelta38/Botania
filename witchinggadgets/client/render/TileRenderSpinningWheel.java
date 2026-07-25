/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;

public class TileRenderSpinningWheel
extends TileEntitySpecialRenderer {
    static ModelSpinningWheel model = new ModelSpinningWheel();

    public void renderTileEntityAt(TileEntitySpinningWheel tile, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        switch (tile.facing) {
            case 2: {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.0f);
                break;
            }
            case 3: {
                GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.5f, (float)0.0f, (float)-1.0f);
                break;
            }
            case 4: {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-1.0f);
                break;
            }
            case 5: {
                GL11.glTranslatef((float)0.5f, (float)0.0f, (float)0.0f);
            }
        }
        ClientUtilities.bindTexture("witchinggadgets:textures/models/spinningwheel.png");
        model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        this.renderTileEntityAt((TileEntitySpinningWheel)tileentity, d0, d1, d2, f);
    }

    static class ModelSpinningWheel
    extends ModelBase {
        List<ModelRenderer> parts = new ArrayList<ModelRenderer>();

        public ModelSpinningWheel() {
            this.parts.clear();
            ModelRenderer temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
            temp.func_78793_a(1.0f, 0.0f, 14.0f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
            temp.func_78793_a(-3.0f, 0.0f, 14.0f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 2, 6, 2);
            temp.func_78793_a(-1.0f, 0.0f, 1.0f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 6, 2, 16);
            temp.func_78793_a(-3.0f, 5.0f, 0.25f);
            temp.field_78795_f = (float)Math.toRadians(-10.0);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
            temp.func_78793_a(-0.5f, 12.0f, 0.0f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
            temp.func_78793_a(-0.5f, 12.0f, 6.35f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 9.9f, 2.2f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 16.1f, 2.2f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, -1.5f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 15.3f, 5.6f);
            temp.func_78787_b(64, 32);
            temp.field_78795_f = (float)Math.toRadians(45.0);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, -1.5f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 11.0f, 6.3f);
            temp.func_78787_b(64, 32);
            temp.field_78795_f = (float)Math.toRadians(-45.0);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, -1.5f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 15.3f, 1.8f);
            temp.func_78787_b(64, 32);
            temp.field_78795_f = (float)Math.toRadians(-45.0);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 8, 4);
            temp.func_78789_a(0.0f, 0.0f, -1.5f, 1, 1, 3);
            temp.func_78793_a(-0.5f, 11.0f, 1.1f);
            temp.func_78787_b(64, 32);
            temp.field_78795_f = (float)Math.toRadians(45.0);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 28, 0);
            temp.func_78789_a(0.0f, 13.0f, 3.7f, 1, 6, 1);
            temp.func_78793_a(-0.5f, -2.5f, -0.5f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 28, 8);
            temp.func_78789_a(0.0f, 13.6f, 3.0f, 1, 1, 6);
            temp.func_78793_a(-0.5f, -0.5f, -2.5f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 36, 0);
            temp.func_78789_a(5.0f, 10.0f, 3.7f, 1, 7, 2);
            temp.func_78793_a(-0.5f, -4.0f, -1.0f);
            temp.func_78787_b(64, 32);
            temp.field_78808_h = (float)Math.toRadians(15.0);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 28, 0);
            temp.func_78789_a(0.0f, 9.0f, 13.0f, 1, 4, 1);
            temp.func_78793_a(-0.5f, 0.0f, -0.5f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 18);
            temp.func_78789_a(0.0f, 11.0f, 13.0f, 2, 3, 2);
            temp.func_78793_a(-1.0f, 0.0f, -1.0f);
            temp.func_78787_b(64, 32);
            this.parts.add(temp);
        }

        public void func_78088_a(Entity ent, float par2, float par3, float par4, float par5, float par6, float par7) {
            for (ModelRenderer mr : this.parts) {
                mr.func_78785_a(par7);
            }
        }
    }
}

