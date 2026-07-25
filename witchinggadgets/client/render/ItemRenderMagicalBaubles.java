/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import witchinggadgets.common.WGContent;

public class ItemRenderMagicalBaubles
implements IItemRenderer {
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        EntityLivingBase par1EntityLivingBase;
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        try {
            par1EntityLivingBase = (EntityLivingBase)data[1];
        }
        catch (Exception e) {
            e.printStackTrace();
            GL11.glPopMatrix();
            return;
        }
        GL11.glEnable((int)32826);
        for (int l = 0; l < WGContent.ItemMagicalBaubles.getRenderPasses(stack.func_77960_j()); ++l) {
            IIcon iicon = par1EntityLivingBase.func_70620_b(stack, l);
            if (iicon == null) {
                GL11.glPopMatrix();
                return;
            }
            textureManager.func_110577_a(textureManager.func_130087_a(stack.func_94608_d()));
            TextureUtil.func_152777_a((boolean)false, (boolean)false, (float)1.0f);
            Tessellator tessellator = Tessellator.field_78398_a;
            float f = iicon.func_94209_e();
            float f1 = iicon.func_94212_f();
            float f2 = iicon.func_94206_g();
            float f3 = iicon.func_94210_h();
            int colour = WGContent.ItemMagicalBaubles.func_82790_a(stack, l);
            Color col = new Color(colour);
            if (l > 0) {
                GL11.glColor3d((double)((double)col.getRed() / 255.0), (double)((double)col.getGreen() / 255.0), (double)((double)col.getBlue() / 255.0));
            }
            ItemRenderer.func_78439_a((Tessellator)tessellator, (float)f1, (float)f2, (float)f, (float)f3, (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
        }
        GL11.glDisable((int)32826);
        textureManager.func_110577_a(textureManager.func_130087_a(stack.func_94608_d()));
        TextureUtil.func_147945_b();
    }
}

