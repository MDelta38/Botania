/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package thaumic.tinkerer.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumic.tinkerer.common.item.ItemInfusedSeeds;

public class RenderGenericSeeds
implements IItemRenderer {
    private static RenderItem renderItem = new RenderItem();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            return true;
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            return true;
        }
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            return true;
        }
        return type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        if (helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING) {
            return true;
        }
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack itemstack, Object ... data) {
        GL11.glPushAttrib((int)16384);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        ItemInfusedSeeds item = (ItemInfusedSeeds)itemstack.func_77973_b();
        IIcon icon = item.func_77650_f(itemstack);
        Aspect aspect = ItemInfusedSeeds.getAspect(itemstack);
        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            this.renderItemInInventory(itemstack, aspect, icon);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            this.renderItemInEquipped(itemstack, aspect, icon);
        } else {
            EntityItem entityItem = (EntityItem)data[1];
            if (entityItem.field_70170_p == null) {
                float angle = (float)(Minecraft.func_71386_F() % 8000L) / 8000.0f * 360.0f;
                GL11.glPushMatrix();
                GL11.glRotatef((float)angle, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.2f, (float)-0.5f, (float)0.0f);
                this.renderItemAsEntity(itemstack, aspect, icon);
                GL11.glPopMatrix();
            } else {
                this.renderItemAsEntity(itemstack, aspect, icon);
            }
        }
        GL11.glDisable((int)3042);
        GL11.glPopAttrib();
    }

    private void renderItemInInventory(ItemStack itemstack, Aspect aspect, IIcon icon) {
        this.setColorForAspect(aspect);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.drawTexturedRectUV(0.0f, 0.0f, 0.0f, 16, 16, icon);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderItemInEquipped(ItemStack itemstack, Aspect aspect, IIcon icon) {
        Tessellator tessellator = Tessellator.field_78398_a;
        this.setColorForAspect(aspect);
        ItemRenderer.func_78439_a((Tessellator)tessellator, (float)icon.func_94212_f(), (float)icon.func_94206_g(), (float)icon.func_94209_e(), (float)icon.func_94210_h(), (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderItemAsEntity(ItemStack itemstack, Aspect aspect, IIcon icon) {
        GL11.glPushMatrix();
        this.setColorForAspect(aspect);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.drawTextureIn3D(icon);
        GL11.glPopMatrix();
    }

    private void drawTextureIn3D(IIcon texture) {
        Tessellator tesselator = Tessellator.field_78398_a;
        float scale = 0.7f;
        GL11.glPushMatrix();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        ItemRenderer.func_78439_a((Tessellator)tesselator, (float)texture.func_94212_f(), (float)texture.func_94206_g(), (float)texture.func_94209_e(), (float)texture.func_94210_h(), (int)texture.func_94211_a(), (int)texture.func_94216_b(), (float)0.05f);
        GL11.glPopMatrix();
    }

    private void drawTexturedRectUV(float x, float y, float z, int w, int h, IIcon icon) {
        Tessellator tesselator = Tessellator.field_78398_a;
        tesselator.func_78382_b();
        tesselator.func_78374_a((double)x, (double)(y + (float)h), (double)z, (double)icon.func_94209_e(), (double)icon.func_94210_h());
        tesselator.func_78374_a((double)(x + (float)w), (double)(y + (float)h), (double)z, (double)icon.func_94212_f(), (double)icon.func_94210_h());
        tesselator.func_78374_a((double)(x + (float)w), (double)y, (double)z, (double)icon.func_94212_f(), (double)icon.func_94206_g());
        tesselator.func_78374_a((double)x, (double)y, (double)z, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        tesselator.func_78381_a();
    }

    private void setColorForAspect(Aspect aspect) {
    }
}

