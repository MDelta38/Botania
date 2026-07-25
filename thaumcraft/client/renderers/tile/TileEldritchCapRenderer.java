/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileEldritchAltar;

public class TileEldritchCapRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model;
    private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");
    private String tex = "textures/models/obelisk_cap.png";
    private String tex2 = "textures/models/obelisk_cap_2.png";
    private ItemStack eye = null;
    EntityItem entityitem = null;

    public TileEldritchCapRenderer(String texture) {
        this.tex = texture;
        this.model = AdvancedModelLoader.loadModel((ResourceLocation)CAP);
    }

    public TileEldritchCapRenderer() {
        this.model = AdvancedModelLoader.loadModel((ResourceLocation)CAP);
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        String tempTex = this.tex;
        GL11.glPushMatrix();
        if (te.func_145831_w() != null) {
            int j = te.func_145838_q().func_149677_c((IBlockAccess)te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e);
            int k = j % 65536;
            int l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            if (te.func_145831_w().field_73011_w.field_76574_g == Config.dimensionOuterId) {
                tempTex = this.tex2;
            }
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture(tempTex);
        GL11.glTranslated((double)(x + 0.5), (double)y, (double)(z + 0.5));
        GL11.glRotated((double)90.0, (double)-1.0, (double)0.0, (double)0.0);
        this.model.renderPart("Cap");
        GL11.glPopMatrix();
        if (te.func_145831_w() != null && te instanceof TileEldritchAltar && ((TileEldritchAltar)te).getEyes() > 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.0f), (float)((float)z + 0.5f));
            if (this.entityitem == null || this.eye == null) {
                this.eye = new ItemStack(ConfigItems.itemEldritchObject, 1, 0);
                this.entityitem = new EntityItem(te.func_145831_w(), 0.0, 0.0, 0.0, this.eye);
                this.entityitem.field_70290_d = 0.0f;
            }
            if (this.entityitem != null && this.eye != null) {
                for (int a = 0; a < ((TileEldritchAltar)te).getEyes(); ++a) {
                    GL11.glPushMatrix();
                    GL11.glRotated((double)(a * 90), (double)0.0, (double)1.0, (double)0.0);
                    GL11.glTranslatef((float)0.46f, (float)0.2f, (float)0.0f);
                    GL11.glRotated((double)90.0, (double)0.0, (double)1.0, (double)0.0);
                    GL11.glRotated((double)18.0, (double)-1.0, (double)0.0, (double)0.0);
                    RenderItem.field_82407_g = true;
                    RenderManager.field_78727_a.func_147940_a((Entity)this.entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
                    RenderItem.field_82407_g = false;
                    GL11.glPopMatrix();
                }
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }
}

