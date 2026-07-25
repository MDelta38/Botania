/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.common.core.helper.EnumMobAspect;
import thaumic.tinkerer.common.item.ItemMobDisplay;

public class RenderMobDisplay
implements IItemRenderer {
    private static RenderItem renderItem = new RenderItem();

    public boolean handleRenderType(ItemStack itemStack, IItemRenderer.ItemRenderType itemRenderType) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType itemRenderType, ItemStack itemStack, IItemRenderer.ItemRendererHelper itemRendererHelper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType itemRenderType, ItemStack itemStack, Object ... objects) {
        ItemMobDisplay item = (ItemMobDisplay)itemStack.func_77973_b();
        EnumMobAspect aspect = item.getEntityType(itemStack);
        Entity entity = null;
        float f1 = 0.4f;
        float verticalOffset = 0.0f;
        if (aspect != null) {
            entity = EnumMobAspect.getEntityFromCache(aspect, null);
            f1 = aspect.getScale();
            verticalOffset = aspect.getVerticalOffset();
        }
        switch (itemRenderType) {
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.5, (double)(0.2 + (double)verticalOffset), (double)0.5);
                GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                EntityItem eItem = (EntityItem)objects[1];
                if (entity != null) {
                    WorldClient worldClient = entity.field_70170_p = Minecraft.func_71410_x() != null ? Minecraft.func_71410_x().field_71441_e : null;
                    if (entity.field_70170_p != null) {
                        Render renderer = RenderManager.field_78727_a.func_78713_a(entity);
                        entity.func_70029_a(eItem.field_70170_p);
                        entity.func_82149_j((Entity)eItem);
                        if (renderer != null && renderer.func_76983_a() != null) {
                            GL11.glPushAttrib((int)1048575);
                            renderer.func_76986_a(entity, 0.0, 0.0, 0.0, 0.0f, 0.0f);
                            GL11.glPopAttrib();
                        }
                    }
                    entity.field_70170_p = null;
                }
                GL11.glPopMatrix();
            }
            case EQUIPPED: {
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                break;
            }
            case INVENTORY: {
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.5, (double)(0.2 + (double)verticalOffset), (double)0.5);
                GL11.glRotatef((float)-30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                if (entity != null) {
                    Render renderer;
                    WorldClient worldClient = entity.field_70170_p = Minecraft.func_71410_x() != null ? Minecraft.func_71410_x().field_71441_e : null;
                    if (entity.field_70170_p != null && (renderer = RenderManager.field_78727_a.func_78713_a(entity)) != null && renderer.func_76983_a() != null) {
                        GL11.glPushAttrib((int)1048575);
                        renderer.func_76986_a(entity, 0.0, 0.0, 0.0, 0.0f, 0.0f);
                        GL11.glPopAttrib();
                    }
                    entity.field_70170_p = null;
                }
                GL11.glPopMatrix();
            }
        }
    }
}

