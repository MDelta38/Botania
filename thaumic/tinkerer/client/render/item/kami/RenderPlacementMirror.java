/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.render.item.kami;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemPlacementMirror;

public class RenderPlacementMirror
implements IItemRenderer {
    RenderItem render = new RenderItem();
    ItemRenderer renderer = new ItemRenderer(Minecraft.func_71410_x());

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return type == IItemRenderer.ItemRenderType.ENTITY || type == IItemRenderer.ItemRenderType.INVENTORY;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type) {
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.0f);
                this.renderItem(IItemRenderer.ItemRenderType.EQUIPPED, item, data);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: {
                for (int i = 1; i >= 0; --i) {
                    IIcon icon = ThaumicTinkerer.registry.getFirstItemFromClass(ItemPlacementMirror.class).func_77618_c(0, i);
                    if (i == 0) {
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                    }
                    float f = icon.func_94209_e();
                    float f1 = icon.func_94212_f();
                    float f2 = icon.func_94206_g();
                    float f3 = icon.func_94210_h();
                    ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                }
                GL11.glDisable((int)3042);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                this.renderItem(IItemRenderer.ItemRenderType.EQUIPPED, item, data);
                break;
            }
            case INVENTORY: {
                GL11.glPushMatrix();
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)1.5f, (float)1.8f, (float)1.8f);
                GL11.glRotatef((float)-45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.46f, (float)-0.58f, (float)0.0f);
                this.renderItem(IItemRenderer.ItemRenderType.EQUIPPED, item, data);
                GL11.glPopMatrix();
                break;
            }
        }
    }
}

