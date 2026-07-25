/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.tiles.TileThaumatorium;

@SideOnly(value=Side.CLIENT)
public class TileThaumatoriumRenderer
extends TileEntitySpecialRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)TM);
    private static final ResourceLocation TM = new ResourceLocation("thaumcraft", "textures/models/thaumatorium.obj");
    EntityItem entityitem = null;

    public void renderTileEntityAt(TileThaumatorium tile, double par2, double par4, double par6, float par8) {
        int stack;
        CrucibleRecipe recipe;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        UtilsFX.bindTexture("textures/models/thaumatorium.png");
        boolean md = false;
        if (tile.func_145831_w() != null) {
            switch (tile.facing.ordinal()) {
                case 5: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
                case 3: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                }
            }
        }
        this.model.renderAll();
        GL11.glPopMatrix();
        float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
        if (tile != null && tile.func_145831_w() != null && tile.recipeHash != null && tile.recipeHash.size() > 0 && (recipe = ThaumcraftApi.getCrucibleRecipeFromHash(tile.recipeHash.get(stack = Minecraft.func_71410_x().field_71451_h.field_70173_aa / 40 % tile.recipeHash.size()))) != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2 + 0.5f + (float)tile.facing.offsetX / 1.99f), (float)((float)par4 + 1.325f), (float)((float)par6 + 0.5f + (float)tile.facing.offsetZ / 1.99f));
            switch (tile.facing.ordinal()) {
                case 5: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 4: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
            }
            GL11.glScaled((double)0.75, (double)0.75, (double)0.75);
            ItemStack is = recipe.getRecipeOutput().func_77946_l();
            is.field_77994_a = 1;
            this.entityitem = new EntityItem(tile.func_145831_w(), 0.0, 0.0, 0.0, is);
            this.entityitem.field_70290_d = 0.0f;
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)this.entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileThaumatorium)par1TileEntity, par2, par4, par6, par8);
    }
}

