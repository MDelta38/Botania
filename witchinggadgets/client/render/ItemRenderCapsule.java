/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidStack
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.ItemCrystalCapsule;

public class ItemRenderCapsule
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return type.equals((Object)IItemRenderer.ItemRenderType.ENTITY);
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        IIcon iicon;
        TextureManager textureManager = Minecraft.func_71410_x().func_110434_K();
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glEnable((int)3008);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        if (type.equals((Object)IItemRenderer.ItemRenderType.ENTITY)) {
            GL11.glScaled((double)0.75, (double)0.75, (double)0.75);
            GL11.glTranslated((double)-0.5, (double)-0.2, (double)0.0);
        }
        Tessellator tes = Tessellator.field_78398_a;
        if (((ItemCrystalCapsule)WGContent.ItemCapsule).getFluidStored(item) != null) {
            Fluid fluid = ((ItemCrystalCapsule)WGContent.ItemCapsule).getFluidStored(item);
            iicon = fluid.getIcon(new FluidStack(fluid, 1000));
            int colour = fluid.getColor(new FluidStack(fluid, 1000));
            if (fluid.getBlock() != null) {
                iicon = fluid.getBlock().func_149733_h(0);
                colour = fluid.getBlock().func_149720_d((IBlockAccess)Minecraft.func_71410_x().field_71441_e, (int)Minecraft.func_71410_x().field_71439_g.field_70165_t, (int)Minecraft.func_71410_x().field_71439_g.field_70163_u, (int)Minecraft.func_71410_x().field_71439_g.field_70161_v);
            }
            if (iicon == null) {
                GL11.glPopMatrix();
                return;
            }
            GL11.glScaled((double)1.0, (double)1.0, (double)0.75);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.01f);
            ClientUtilities.renderIconWithMask(iicon, ((ItemCrystalCapsule)WGContent.ItemCapsule).fluidMask, new Color(colour), type);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.01f);
            GL11.glScaled((double)1.0, (double)1.0, (double)1.3333333730697632);
        }
        iicon = WGContent.ItemCapsule.getIcon(item, 0);
        textureManager.func_110577_a(textureManager.func_130087_a(item.func_94608_d()));
        if (type.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            GL11.glScaled((double)16.0, (double)16.0, (double)16.0);
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)1.0f);
        }
        ItemRenderer.func_78439_a((Tessellator)tes, (float)iicon.func_94212_f(), (float)iicon.func_94206_g(), (float)iicon.func_94209_e(), (float)iicon.func_94210_h(), (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
        iicon = WGContent.ItemCapsule.getIcon(item, 1);
        if (iicon == null) {
            GL11.glPopMatrix();
            return;
        }
        GL11.glEnable((int)3042);
        ItemRenderer.func_78439_a((Tessellator)tes, (float)iicon.func_94212_f(), (float)iicon.func_94206_g(), (float)iicon.func_94209_e(), (float)iicon.func_94210_h(), (int)iicon.func_94211_a(), (int)iicon.func_94216_b(), (float)0.0625f);
        if (type.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            GL11.glScaled((double)0.0, (double)0.0, (double)0.0);
        }
        GL11.glDisable((int)3042);
        textureManager.func_110577_a(textureManager.func_130087_a(item.func_94608_d()));
        TextureUtil.func_147945_b();
        GL11.glPopMatrix();
    }
}

