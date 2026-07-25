/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelChest
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class ItemTrunkSpawnerRenderer
implements IItemRenderer {
    private ModelChest chest = new ModelChest();

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/trunk.png");
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        boolean var11 = false;
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)-0.25f, (float)-0.5f, (float)-0.25f);
            if (type == IItemRenderer.ItemRenderType.EQUIPPED && type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glTranslatef((float)1.0f, (float)0.0f, (float)0.0f);
            }
        }
        GL11.glRotatef((float)((float)var11), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        this.chest.func_78231_a();
        GL11.glPopMatrix();
    }
}

