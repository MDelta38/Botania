/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.client.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;

public class TileRenderCuttingTable
extends TileEntitySpecialRenderer {
    static ModelCuttingTable model = new ModelCuttingTable();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
        TileEntityCuttingTable tile = (TileEntityCuttingTable)tileentity;
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
        ClientUtilities.bindTexture("witchinggadgets:textures/models/cuttingTable.png");
        model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glBlendFunc((int)770, (int)771);
        for (byte i = 0; i < 3; i = (byte)((byte)(i + 1))) {
            if (tile.func_70301_a(1 + i) == null || !tile.func_70301_a(1 + i).func_77942_o()) continue;
            AspectList aspects = new AspectList();
            aspects.readFromNBT(tile.func_70301_a(1 + i).func_77978_p());
            if (aspects.aspects.isEmpty()) continue;
            if (tile.func_70301_a(1 + i).func_77973_b().equals(ConfigItems.itemEssence)) {
                model.renderFlask(i, aspects.getAspects()[0].getColor());
                continue;
            }
            if (!tile.func_70301_a(1 + i).func_77973_b().equals(ConfigItems.itemWispEssence)) continue;
            model.renderEssence(i, aspects.getAspects()[0].getColor());
        }
        ClientUtilities.bindTexture("textures/atlas/items.png");
        GL11.glTranslatef((float)-0.2f, (float)0.875f, (float)-0.25f);
        GL11.glScaled((double)0.375, (double)0.75, (double)0.375);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        if (tile.func_70301_a(0) != null) {
            for (int pass = 0; pass < tile.func_70301_a(0).func_77973_b().getRenderPasses(tile.func_70301_a(0).func_77960_j()); ++pass) {
                IIcon iicon = tile.func_70301_a(0).func_77973_b().getIcon(tile.func_70301_a(0), pass);
                ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)iicon.func_94212_f(), (float)iicon.func_94206_g(), (float)iicon.func_94209_e(), (float)iicon.func_94210_h(), (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
            }
        }
        GL11.glPopMatrix();
    }

    static class ModelCuttingTable
    extends ModelBase {
        List<ModelRenderer> parts = new ArrayList<ModelRenderer>();
        List<ModelRenderer[]> flasks = new ArrayList<ModelRenderer[]>();
        List<ModelRenderer[]> bowls = new ArrayList<ModelRenderer[]>();

        public ModelCuttingTable() {
            this.parts.clear();
            ModelRenderer temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 16);
            temp.func_78793_a(-8.0f, 0.0f, -8.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 16);
            temp.func_78793_a(-8.0f, 10.0f, -8.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, -32, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 16, 0, 16);
            temp.func_78793_a(-8.0f, 14.05f, -8.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 20);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 3);
            temp.func_78793_a(-7.0f, 2.0f, -7.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 20);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 3);
            temp.func_78793_a(4.0f, 2.0f, -7.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 20);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 3);
            temp.func_78793_a(-7.0f, 2.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 0, 20);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 3);
            temp.func_78793_a(4.0f, 2.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 52, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
            temp.func_78793_a(4.8f, 13.9f, 2.2f);
            temp.func_78787_b(64, 32);
            temp.field_78796_g = 0.4f;
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 52, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
            temp.func_78793_a(7.0f, 14.0f, -2.5f);
            temp.func_78787_b(64, 32);
            temp.field_78796_g = -0.4f;
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 52, 6);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
            temp.func_78793_a(-6.0f, 13.9f, -0.8f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp.field_78796_g = -0.1f;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 52, 0);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 5);
            temp.func_78793_a(-6.5f, 14.0f, -5.5f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp.field_78796_g = 0.1f;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 52, 1);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 4);
            temp.func_78793_a(4.5f, 14.0f, -5.5f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 58, 6);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
            temp.func_78793_a(4.5f, 13.9f, -2.8f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp.field_78796_g = -0.1f;
            this.parts.add(temp);
            temp = new ModelRenderer((ModelBase)this, 16, 26);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 3);
            temp.func_78793_a(-7.5f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            ModelRenderer temp2 = new ModelRenderer((ModelBase)this, 16, 23);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp2.func_78793_a(-7.0f, 17.0f, 4.5f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            ModelRenderer temp3 = new ModelRenderer((ModelBase)this, 24, 24);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
            temp3.func_78793_a(-6.5f, 17.5f, 5.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            ModelRenderer temp4 = new ModelRenderer((ModelBase)this, 28, 27);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
            temp4.func_78793_a(-7.0f, 14.5f, 4.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            this.flasks.add(new ModelRenderer[]{temp, temp2, temp3, temp4});
            temp = new ModelRenderer((ModelBase)this, 16, 26);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 3);
            temp.func_78793_a(-3.0f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp2 = new ModelRenderer((ModelBase)this, 16, 23);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp2.func_78793_a(-2.5f, 17.0f, 4.5f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            temp3 = new ModelRenderer((ModelBase)this, 24, 24);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
            temp3.func_78793_a(-2.0f, 17.5f, 5.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            temp4 = new ModelRenderer((ModelBase)this, 28, 27);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
            temp4.func_78793_a(-2.5f, 14.5f, 4.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            this.flasks.add(new ModelRenderer[]{temp, temp2, temp3, temp4});
            temp = new ModelRenderer((ModelBase)this, 16, 26);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 3, 3);
            temp.func_78793_a(1.5f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp2 = new ModelRenderer((ModelBase)this, 16, 23);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp2.func_78793_a(2.0f, 17.0f, 4.5f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            temp3 = new ModelRenderer((ModelBase)this, 24, 24);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
            temp3.func_78793_a(2.5f, 17.5f, 5.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            temp4 = new ModelRenderer((ModelBase)this, 28, 27);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
            temp4.func_78793_a(2.0f, 14.5f, 4.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            this.flasks.add(new ModelRenderer[]{temp, temp2, temp3, temp4});
            temp = new ModelRenderer((ModelBase)this, 36, 24);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
            temp.func_78793_a(-7.5f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp2 = new ModelRenderer((ModelBase)this, 36, 20);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp2.func_78793_a(-8.0f, 15.0f, 4.0f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            temp3 = new ModelRenderer((ModelBase)this, 36, 20);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp3.func_78793_a(-5.0f, 15.0f, 4.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            temp4 = new ModelRenderer((ModelBase)this, 44, 20);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp4.func_78793_a(-7.5f, 15.0f, 6.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            ModelRenderer temp5 = new ModelRenderer((ModelBase)this, 44, 20);
            temp5.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp5.func_78793_a(-7.5f, 15.0f, 3.5f);
            temp5.func_78787_b(64, 32);
            temp5.field_78809_i = true;
            ModelRenderer temp6 = new ModelRenderer((ModelBase)this, 48, 22);
            temp6.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp6.func_78793_a(-7.0f, 14.75f, 4.5f);
            temp6.func_78787_b(64, 32);
            temp6.field_78809_i = true;
            this.bowls.add(new ModelRenderer[]{temp, temp2, temp3, temp4, temp5, temp6});
            temp = new ModelRenderer((ModelBase)this, 36, 24);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
            temp.func_78793_a(-3.0f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp2 = new ModelRenderer((ModelBase)this, 36, 20);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp2.func_78793_a(-0.5f, 15.0f, 4.0f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            temp3 = new ModelRenderer((ModelBase)this, 36, 20);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp3.func_78793_a(-3.5f, 15.0f, 4.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            temp4 = new ModelRenderer((ModelBase)this, 44, 20);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp4.func_78793_a(-3.0f, 15.0f, 6.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            temp5 = new ModelRenderer((ModelBase)this, 44, 20);
            temp5.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp5.func_78793_a(-3.0f, 15.0f, 3.5f);
            temp5.func_78787_b(64, 32);
            temp5.field_78809_i = true;
            temp6 = new ModelRenderer((ModelBase)this, 48, 22);
            temp6.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp6.func_78793_a(-2.5f, 14.75f, 4.5f);
            temp6.func_78787_b(64, 32);
            temp6.field_78809_i = true;
            this.bowls.add(new ModelRenderer[]{temp, temp2, temp3, temp4, temp5, temp6});
            temp = new ModelRenderer((ModelBase)this, 36, 24);
            temp.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
            temp.func_78793_a(1.5f, 14.0f, 4.0f);
            temp.func_78787_b(64, 32);
            temp.field_78809_i = true;
            temp2 = new ModelRenderer((ModelBase)this, 36, 20);
            temp2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp2.func_78793_a(1.0f, 15.0f, 4.0f);
            temp2.func_78787_b(64, 32);
            temp2.field_78809_i = true;
            temp3 = new ModelRenderer((ModelBase)this, 36, 20);
            temp3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
            temp3.func_78793_a(4.0f, 15.0f, 4.0f);
            temp3.func_78787_b(64, 32);
            temp3.field_78809_i = true;
            temp4 = new ModelRenderer((ModelBase)this, 44, 20);
            temp4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp4.func_78793_a(1.5f, 15.0f, 6.5f);
            temp4.func_78787_b(64, 32);
            temp4.field_78809_i = true;
            temp5 = new ModelRenderer((ModelBase)this, 44, 20);
            temp5.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 1);
            temp5.func_78793_a(1.5f, 15.0f, 3.5f);
            temp5.func_78787_b(64, 32);
            temp5.field_78809_i = true;
            temp6 = new ModelRenderer((ModelBase)this, 48, 22);
            temp6.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
            temp6.func_78793_a(2.0f, 14.75f, 4.5f);
            temp6.func_78787_b(64, 32);
            temp6.field_78809_i = true;
            this.bowls.add(new ModelRenderer[]{temp, temp2, temp3, temp4, temp5, temp6});
        }

        public void func_78088_a(Entity ent, float par2, float par3, float par4, float par5, float par6, float par7) {
            for (ModelRenderer mr : this.parts) {
                mr.func_78785_a(par7);
            }
        }

        public void renderFlask(byte fl, int colour) {
            GL11.glEnable((int)3042);
            this.flasks.get(fl)[2].func_78785_a(0.0625f);
            GL11.glColor3f((float)((float)(colour >> 16 & 0xFF) / 255.0f), (float)((float)(colour >> 8 & 0xFF) / 255.0f), (float)((float)(colour & 0xFF) / 255.0f));
            this.flasks.get(fl)[3].func_78785_a(0.0625f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.flasks.get(fl)[0].func_78785_a(0.0625f);
            this.flasks.get(fl)[1].func_78785_a(0.0625f);
            GL11.glDisable((int)3042);
        }

        public void renderEssence(byte fl, int colour) {
            this.bowls.get(fl)[0].func_78785_a(0.0625f);
            this.bowls.get(fl)[1].func_78785_a(0.0625f);
            this.bowls.get(fl)[2].func_78785_a(0.0625f);
            this.bowls.get(fl)[3].func_78785_a(0.0625f);
            this.bowls.get(fl)[4].func_78785_a(0.0625f);
            GL11.glColor3f((float)((float)(colour >> 16 & 0xFF) / 255.0f), (float)((float)(colour >> 8 & 0xFF) / 255.0f), (float)((float)(colour & 0xFF) / 255.0f));
            this.bowls.get(fl)[5].func_78785_a(0.0625f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }
}

