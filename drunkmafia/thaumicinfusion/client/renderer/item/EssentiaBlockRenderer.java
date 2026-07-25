/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.client.renderer.item;

import drunkmafia.thaumicinfusion.common.block.TIBlocks;
import drunkmafia.thaumicinfusion.common.util.RGB;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;

public class EssentiaBlockRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag == null) {
            return;
        }
        Block essentiaBlock = TIBlocks.essentiaBlock;
        RenderBlocks renderBlocks = new RenderBlocks();
        Tessellator tessellator = Tessellator.field_78398_a;
        essentiaBlock.func_149683_g();
        renderBlocks.func_147775_a(essentiaBlock);
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslatef((float)-1.1225f, (float)0.05f, (float)0.0f);
        } else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)-1.0f, (float)0.0f, (float)0.05f);
        } else {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        }
        new RGB(Aspect.getAspect((String)tag.func_74779_i("aspectTag")).getColor()).glColor3f();
        IIcon icon = renderBlocks.func_147787_a(essentiaBlock, 0, stack.func_77960_j());
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderBlocks.func_147768_a(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderBlocks.func_147806_b(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderBlocks.func_147761_c(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderBlocks.func_147734_d(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderBlocks.func_147798_e(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderBlocks.func_147764_f(essentiaBlock, 0.0, 0.0, 0.0, icon);
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glPopMatrix();
    }
}

