/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelSkeleton
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.models.gear.ModelRobe
 */
package witchinggadgets.client.render;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.gear.ModelRobe;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;

public class TileRenderSarcophagus
extends TileEntitySpecialRenderer {
    static ModelSkeleton modelSkel = new ModelSkeleton();
    static ModelRobe modelRobe = new ModelRobe(0.0625f);

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        TileEntitySarcophagus tile = (TileEntitySarcophagus)tileentity;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        GL11.glRotated((double)(tile.facing == 2 || tile.facing == 3 ? 90.0 : 180.0), (double)0.0, (double)0.0, (double)1.0);
        GL11.glTranslated((double)(tile.facing == 2 || tile.facing == 3 ? 0.5 : -0.5), (double)0.0, (double)-0.25);
        this.func_147499_a(new ResourceLocation("textures/entity/skeleton/skeleton.png"));
        if (!tile.dummyLeft && tile.dummyRight && tile.open) {
            GL11.glPushMatrix();
            TileRenderSarcophagus.modelSkel.field_78116_c.func_78785_a(0.0625f);
            TileRenderSarcophagus.modelSkel.field_78115_e.func_78785_a(0.0625f);
            TileRenderSarcophagus.modelSkel.field_78124_i.func_78785_a(0.0625f);
            TileRenderSarcophagus.modelSkel.field_78123_h.func_78785_a(0.0625f);
            GL11.glTranslated((double)0.03125, (double)0.0, (double)0.0);
            TileRenderSarcophagus.modelSkel.field_78113_g.func_78785_a(0.0625f);
            GL11.glTranslated((double)-0.0625, (double)0.0, (double)0.0);
            TileRenderSarcophagus.modelSkel.field_78112_f.func_78785_a(0.0625f);
            GL11.glPopMatrix();
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.015625);
            GL11.glPushMatrix();
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            ClientUtilities.bindTexture("thaumcraft:textures/models/void_robe_armor_overlay.png");
            TileRenderSarcophagus.modelRobe.field_78116_c.func_78785_a(0.03125f);
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.0f);
            TileRenderSarcophagus.modelRobe.field_78115_e.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78124_i.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78123_h.func_78785_a(0.03125f);
            GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
            TileRenderSarcophagus.modelRobe.field_78113_g.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78112_f.func_78785_a(0.03125f);
            GL11.glPopMatrix();
            ClientUtilities.bindTexture("thaumcraft:textures/models/void_robe_armor.png");
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            TileRenderSarcophagus.modelRobe.field_78116_c.func_78785_a(0.03125f);
            GL11.glScalef((float)0.75f, (float)0.75f, (float)0.75f);
            GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.0f);
            TileRenderSarcophagus.modelRobe.field_78115_e.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78124_i.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78123_h.func_78785_a(0.03125f);
            GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
            TileRenderSarcophagus.modelRobe.field_78113_g.func_78785_a(0.03125f);
            TileRenderSarcophagus.modelRobe.field_78112_f.func_78785_a(0.03125f);
        }
        GL11.glPopMatrix();
    }

    static {
        TileRenderSarcophagus.modelRobe.field_78091_s = false;
    }
}

