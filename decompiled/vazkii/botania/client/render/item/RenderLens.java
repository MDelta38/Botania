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
package vazkii.botania.client.render.item;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.common.item.lens.ItemLens;

public class RenderLens
implements IItemRenderer {
    static RenderItem render = new RenderItem();
    ItemRenderer renderer = new ItemRenderer(Minecraft.func_71410_x());

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type != IItemRenderer.ItemRenderType.INVENTORY;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper == IItemRenderer.ItemRendererHelper.ENTITY_ROTATION || helper == IItemRenderer.ItemRendererHelper.ENTITY_BOBBING;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type) {
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.0f);
                if (item.func_82839_y()) {
                    GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.01f);
                }
                RenderLens.render(item);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: {
                RenderLens.render(item);
                break;
            }
            case EQUIPPED_FIRST_PERSON: {
                RenderLens.render(item);
                break;
            }
        }
    }

    public static void render(ItemStack item) {
        Color color = new Color(((ILens)item.func_77973_b()).getLensColor(item));
        RenderLens.render(item, color.getRGB());
    }

    public static void render(ItemStack item, int color_) {
        int dmg = item.func_77960_j();
        IIcon icon = item.func_77973_b().func_77618_c(dmg, 1);
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        float scale = 0.0625f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)scale);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glBlendFunc((int)770, (int)771);
        Color color = new Color(color_);
        GL11.glColor4ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()), (byte)-1);
        boolean shiny = ItemLens.getStoredColor(item) != -1;
        icon = ItemLens.iconGlass;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.5f);
        RenderLens.renderShinyLensIcon(icon, shiny);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-16.0f, (float)0.0f, (float)0.0f);
        RenderLens.renderShinyLensIcon(icon, shiny);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void renderShinyLensIcon(IIcon icon, boolean shiny) {
        float par1 = 0.0f;
        float par2 = 0.0f;
        float par4 = 16.0f;
        float par5 = 16.0f;
        float zLevel = 0.0f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        if (shiny) {
            tessellator.func_78380_c(240);
        }
        tessellator.func_78374_a((double)(par1 + 0.0f), (double)(par2 + par5), (double)zLevel, (double)icon.func_94209_e(), (double)icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + par5), (double)zLevel, (double)icon.func_94212_f(), (double)icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + 0.0f), (double)zLevel, (double)icon.func_94212_f(), (double)icon.func_94206_g());
        tessellator.func_78374_a((double)(par1 + 0.0f), (double)(par2 + 0.0f), (double)zLevel, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        tessellator.func_78381_a();
    }
}

