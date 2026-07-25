/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.client;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.WitchingGadgets;

public class ClientUtilities {
    public static int colour_CloakBlue = 2041173;
    public static int colour_CloakNil = 0xE0E0E0;
    public static int colour_CloakRaven = 3487288;
    public static int colour_CloakStorage = Aspect.VOID.getColor();
    public static String[] nodeTypeChatColour = new String[]{"7", "2", "8", "5", "4", "f"};
    public static String[] nodeModifierChatColour = new String[]{"f", "7", "8"};
    static HashMap<String, IModelCustom> modelMap = new HashMap();
    static HashMap<String, ResourceLocation> textureMap = new HashMap();

    private static Minecraft mc() {
        return Minecraft.func_71410_x();
    }

    public static void renderPixelBlock(Tessellator tes, double x, double y, double z, double pixelLength, double uMin, double vMin, double uMax, double vMax) {
        double dXMin = x * pixelLength;
        double dXMax = (x + 1.0) * pixelLength;
        double dYMin = y * pixelLength;
        double dYMax = (y + 1.0) * pixelLength;
        double dZMin = z * pixelLength;
        double dZMax = (z + 1.0) * pixelLength;
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMin);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78381_a();
        tes.func_78382_b();
        tes.func_78374_a(dXMax, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
        tes.func_78381_a();
    }

    public static void renderPixelBlockOnlyVertex(Tessellator tes, double x, double y, double z, double pixelLength, double uMin, double vMin, double uMax, double vMax) {
        double dXMin = x * pixelLength;
        double dXMax = (x + 1.0) * pixelLength;
        double dYMin = y * pixelLength;
        double dYMax = (y + 1.0) * pixelLength;
        double dZMin = z * pixelLength;
        double dZMax = (z + 1.0) * pixelLength;
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMin);
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMax, vMin);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMin, vMin);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMin, vMax);
        tes.func_78374_a(dXMin, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMin, dYMin, dZMax, uMax, vMin);
        tes.func_78374_a(dXMin, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMin, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMin, uMin, vMin);
        tes.func_78374_a(dXMax, dYMax, dZMin, uMin, vMax);
        tes.func_78374_a(dXMax, dYMax, dZMax, uMax, vMax);
        tes.func_78374_a(dXMax, dYMin, dZMax, uMax, vMin);
    }

    public static void drawSubBlock(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean inverseXZ, boolean mirror, int x, int y, int z, Block block, RenderBlocks renderer) {
        if (mirror) {
            minX = 1.0 - minX;
            minZ = 1.0 - minZ;
            maxX = 1.0 - maxX;
            maxZ = 1.0 - maxZ;
        }
        if (inverseXZ) {
            renderer.func_147782_a(minZ, minY, minX, maxZ, maxY, maxX);
        } else {
            renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
        }
        renderer.func_147784_q(block, x, y, z);
    }

    public static void drawSubBlockInInventory(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, boolean inverseXZ, boolean mirror, Block block, RenderBlocks renderer) {
        if (mirror) {
            minX = 1.0 - minX;
            minZ = 1.0 - minZ;
            maxX = 1.0 - maxX;
            maxZ = 1.0 - maxZ;
        }
        if (inverseXZ) {
            renderer.func_147782_a(minZ, minY, minX, maxZ, maxY, maxX);
        } else {
            renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
        }
        ClientUtilities.drawStandardBlock(block, 0, renderer);
    }

    public static void drawStandardBlock(Block block, int meta, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, 0.0, 0.0, 0.0, block.func_149691_a(0, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, 0.0, 0.0, 0.0, block.func_149691_a(1, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, 0.0, 0.0, 0.0, block.func_149691_a(2, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, 0.0, 0.0, 0.0, block.func_149691_a(3, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, 0.0, 0.0, 0.0, block.func_149691_a(4, meta));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, 0.0, 0.0, 0.0, block.func_149691_a(5, meta));
        tessellator.func_78381_a();
    }

    public static void renderIconWithMask(IIcon icon, IIcon mask, Color colour, IItemRenderer.ItemRenderType paramItemRenderType) {
        if (mask == null || icon == null) {
            return;
        }
        if (colour != null) {
            GL11.glColor4f((float)((float)colour.getRed() / 255.0f), (float)((float)colour.getGreen() / 255.0f), (float)((float)colour.getBlue() / 255.0f), (float)((float)colour.getAlpha() / 255.0f));
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)2884);
        Tessellator localTessellator = Tessellator.field_78398_a;
        localTessellator.func_78382_b();
        localTessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        if (paramItemRenderType.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            Tessellator.field_78398_a.func_78374_a(0.0, 16.0, 10.0, (double)mask.func_94209_e(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 16.0, 10.0, (double)mask.func_94212_f(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 0.0, 10.0, (double)mask.func_94212_f(), (double)mask.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, 10.0, (double)mask.func_94209_e(), (double)mask.func_94206_g());
        } else {
            Tessellator.field_78398_a.func_78374_a(0.0, 1.0, 0.001, (double)mask.func_94209_e(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 1.0, 0.001, (double)mask.func_94212_f(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 0.0, 0.001, (double)mask.func_94212_f(), (double)mask.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, 0.001, (double)mask.func_94209_e(), (double)mask.func_94206_g());
        }
        localTessellator.func_78381_a();
        localTessellator.func_78382_b();
        localTessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        if (paramItemRenderType.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            Tessellator.field_78398_a.func_78374_a(0.0, 16.0, -0.0635, (double)mask.func_94209_e(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 16.0, -0.0635, (double)mask.func_94212_f(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 0.0, -0.0635, (double)mask.func_94212_f(), (double)mask.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, -0.0635, (double)mask.func_94209_e(), (double)mask.func_94206_g());
        } else {
            Tessellator.field_78398_a.func_78374_a(0.0, 1.0, -0.0635, (double)mask.func_94209_e(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 1.0, -0.0635, (double)mask.func_94212_f(), (double)mask.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 0.0, -0.0635, (double)mask.func_94212_f(), (double)mask.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, -0.0635, (double)mask.func_94209_e(), (double)mask.func_94206_g());
        }
        localTessellator.func_78381_a();
        ClientUtilities.bindTexture("textures/atlas/blocks.png");
        GL11.glDepthFunc((int)514);
        GL11.glDepthMask((boolean)false);
        localTessellator.func_78382_b();
        localTessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        if (paramItemRenderType.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            Tessellator.field_78398_a.func_78374_a(0.0, 16.0, 10.0, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 16.0, 10.0, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 0.0, 10.0, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, 10.0, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        } else {
            Tessellator.field_78398_a.func_78374_a(0.0, 1.0, 0.001, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 1.0, 0.001, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 0.0, 0.001, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, 0.001, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        }
        localTessellator.func_78381_a();
        localTessellator.func_78382_b();
        localTessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        if (paramItemRenderType.equals((Object)IItemRenderer.ItemRenderType.INVENTORY)) {
            Tessellator.field_78398_a.func_78374_a(0.0, 16.0, -0.0635, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 16.0, -0.0635, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(16.0, 0.0, -0.0635, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, -0.0635, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        } else {
            Tessellator.field_78398_a.func_78374_a(0.0, 1.0, -0.0635, (double)icon.func_94209_e(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 1.0, -0.0635, (double)icon.func_94212_f(), (double)icon.func_94210_h());
            Tessellator.field_78398_a.func_78374_a(1.0, 0.0, -0.0635, (double)icon.func_94212_f(), (double)icon.func_94206_g());
            Tessellator.field_78398_a.func_78374_a(0.0, 0.0, -0.0635, (double)icon.func_94209_e(), (double)icon.func_94206_g());
        }
        localTessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glDepthFunc((int)515);
        GL11.glEnable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void drawTooltipHoveringText(GuiScreen gui, List<String> list, int x, int y, FontRenderer font, int minimalWidth, int minimalHeight) {
        if (list == null || list.isEmpty()) {
            return;
        }
        GL11.glDisable((int)32826);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        int k = minimalWidth;
        for (String s : list) {
            int l = font.func_78256_a(s);
            if (l <= k) continue;
            k = l;
        }
        int i1 = x + 12;
        int j1 = y - 12;
        int k1 = 8;
        if (list.size() > 1) {
            k1 += 2 + (list.size() - 1) * 10;
        }
        if (i1 + k > gui.field_146294_l) {
            i1 -= i1 + k - gui.field_146294_l;
        }
        if (j1 + k1 + 6 > gui.field_146295_m) {
            j1 = gui.field_146295_m - k1 - 6;
        }
        int l1 = -267386864;
        ClientUtilities.drawGradientRect(i1 - 3, j1 - 4, i1 + k + 3, j1 - 3, l1, l1);
        ClientUtilities.drawGradientRect(i1 - 3, j1 + k1 + 3, i1 + k + 3, j1 + k1 + 4, l1, l1);
        ClientUtilities.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 + k1 + 3, l1, l1);
        ClientUtilities.drawGradientRect(i1 - 4, j1 - 3, i1 - 3, j1 + k1 + 3, l1, l1);
        ClientUtilities.drawGradientRect(i1 + k + 3, j1 - 3, i1 + k + 4, j1 + k1 + 3, l1, l1);
        int i2 = 0x505000FF;
        int j2 = (i2 & 0xFEFEFE) >> 1 | i2 & 0xFF000000;
        ClientUtilities.drawGradientRect(i1 - 3, j1 - 3 + 1, i1 - 3 + 1, j1 + k1 + 3 - 1, i2, j2);
        ClientUtilities.drawGradientRect(i1 + k + 2, j1 - 3 + 1, i1 + k + 3, j1 + k1 + 3 - 1, i2, j2);
        ClientUtilities.drawGradientRect(i1 - 3, j1 - 3, i1 + k + 3, j1 - 3 + 1, i2, i2);
        ClientUtilities.drawGradientRect(i1 - 3, j1 + k1 + 2, i1 + k + 3, j1 + k1 + 3, j2, j2);
        ClientUtilities.drawHoveringText(list, i1, j1, font);
        GL11.glEnable((int)2929);
        GL11.glEnable((int)32826);
    }

    public static void drawHoveringText(List<String> list, int x, int y, FontRenderer font) {
        for (int iS = 0; iS < list.size(); ++iS) {
            String s1 = list.get(iS);
            font.func_78261_a(s1, x, y, -1);
            if (iS == 0) {
                y += 2;
            }
            y += 10;
        }
    }

    public static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = (float)(par5 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(par5 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(par5 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(par5 & 0xFF) / 255.0f;
        float f4 = (float)(par6 >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(par6 >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(par6 >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(par6 & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        Tessellator.field_78398_a.func_78382_b();
        Tessellator.field_78398_a.func_78369_a(f1, f2, f3, f);
        Tessellator.field_78398_a.func_78377_a((double)par3, (double)par2, 500.0);
        Tessellator.field_78398_a.func_78377_a((double)par1, (double)par2, 500.0);
        Tessellator.field_78398_a.func_78369_a(f5, f6, f7, f4);
        Tessellator.field_78398_a.func_78377_a((double)par1, (double)par4, 500.0);
        Tessellator.field_78398_a.func_78377_a((double)par3, (double)par4, 500.0);
        Tessellator.field_78398_a.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static double[] getPlayerHandPos(EntityPlayer player, boolean targetItem) {
        boolean flag = player.func_145782_y() == Minecraft.func_71410_x().field_71439_g.func_145782_y() && Minecraft.func_71410_x().field_71474_y.field_74320_O != 0;
        double x = player.field_70165_t;
        double y = player.field_70163_u - (targetItem ? 0.75 : 0.875);
        double z = player.field_70161_v;
        float radius = targetItem ? 0.5f : 0.375f;
        double rotation = (double)((player.field_70761_aq % 360.0f + (float)(player.func_70694_bm() != null ? -25 : 0) + (float)(targetItem ? -30 : 0)) / 180.0f) * Math.PI;
        if (!flag) {
            y += 0.25;
            rotation = (double)((player.field_70177_z % 360.0f + (float)(player.func_70694_bm() != null ? -25 : 0)) / 180.0f) * Math.PI;
        }
        double xOff = Math.cos(rotation);
        double zOff = Math.sin(rotation);
        return new double[]{x - xOff * (double)radius, y, z - zOff * (double)radius};
    }

    public static void bindTexture(String path) {
        ClientUtilities.mc().func_110434_K().func_110577_a(ClientUtilities.getResource(path));
    }

    public static ResourceLocation getResource(String path) {
        ResourceLocation rl;
        ResourceLocation resourceLocation = rl = textureMap.containsKey(path) ? textureMap.get(path) : new ResourceLocation(path);
        if (!textureMap.containsKey(path)) {
            textureMap.put(path, rl);
        }
        return rl;
    }

    public static IModelCustom bindModel(String domain, String path) {
        String mapPath = domain + ":" + path;
        if (modelMap.containsKey(mapPath)) {
            return modelMap.get(mapPath);
        }
        try {
            IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(domain, path));
            modelMap.put(mapPath, model);
            return model;
        }
        catch (Exception e) {
            WitchingGadgets.logger.log(Level.ERROR, "Error on attempt to load model: " + domain + "," + path);
            e.printStackTrace();
            return null;
        }
    }

    public static void addBoxToBlockrender(double xMin, double yMin, double zMin, double xMax, double yMax, double zMax, IIcon icon, int ... coords) {
        ClientUtilities.addBoxToBlockrender(null, xMin, yMin, zMin, xMax, yMax, zMax, icon, coords);
    }

    public static void addBoxToBlockrender(Vec3 offset, double xMin, double yMin, double zMin, double xMax, double yMax, double zMax, IIcon icon, int ... coords) {
        Tessellator tes = Tessellator.field_78398_a;
        double w = icon.func_94212_f() - icon.func_94209_e();
        double tx = offset != null ? offset.field_72450_a : 0.0;
        double ty = offset != null ? offset.field_72448_b : 0.0;
        double tz = offset != null ? offset.field_72449_c : 0.0;
        int x = coords[0];
        int y = coords[1];
        int z = coords[2];
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - zMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - zMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - zMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - zMax * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - zMax * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - zMin * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - zMin * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - zMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMin), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(xMax), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(zMax), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(zMin), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94209_e() + w * Math.abs(zMin), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMin, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94209_e() + w * Math.abs(zMax), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMax, (double)icon.func_94214_a(16.0 - zMax * 16.0), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMin, (double)z + tz + zMin, (double)icon.func_94214_a(16.0 - zMin * 16.0), (double)icon.func_94207_b(16.0 - yMin * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMin, (double)icon.func_94214_a(16.0 - zMin * 16.0), (double)icon.func_94207_b(16.0 - yMax * 16.0));
        tes.func_78374_a((double)x + tx + xMax, (double)y + ty + yMax, (double)z + tz + zMax, (double)icon.func_94214_a(16.0 - zMax * 16.0), (double)icon.func_94207_b(16.0 - yMax * 16.0));
    }

    public static BufferedImage getImageForResource(ResourceLocation resource) throws IOException {
        InputStream layer = Minecraft.func_71410_x().func_110442_L().func_110536_a(resource).func_110527_b();
        return ImageIO.read(layer);
    }

    public static List<Integer> getItemColours(ItemStack stack) {
        ArrayList<Integer> colourSet = new ArrayList<Integer>();
        Item item = stack.func_77973_b();
        try {
            for (int pass = 0; pass < item.getRenderPasses(stack.func_77960_j()); ++pass) {
                IIcon icon = item.getIcon(stack, pass);
                if (!(icon instanceof TextureAtlasSprite)) continue;
                TextureAtlasSprite tas = (TextureAtlasSprite)icon;
                String iconName = tas.func_94215_i();
                iconName = iconName.substring(0, Math.max(0, iconName.indexOf(":") + 1)) + (item.func_94901_k() == 0 ? "textures/blocks/" : "textures/items/") + iconName.substring(Math.max(0, iconName.indexOf(":") + 1)) + ".png";
                ResourceLocation resource = ClientUtilities.getResource(iconName);
                BufferedImage buffered = ClientUtilities.getImageForResource(resource);
                int passColour = item.func_82790_a(stack, pass);
                int[] data = new int[buffered.getWidth() * buffered.getHeight()];
                buffered.getRGB(0, 0, buffered.getWidth(), buffered.getHeight(), data, 0, tas.func_94211_a());
                for (int rgb : data) {
                    int coloured;
                    if (rgb == 0 || (coloured = ClientUtilities.blendColoursToInt(rgb, passColour) & 0xFFFFFF) <= 0) continue;
                    colourSet.add(coloured);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return colourSet;
    }

    public static int getAverageItemColour(ItemStack stack) {
        List<Integer> col = ClientUtilities.getItemColours(stack);
        if (col != null && col.size() > 0) {
            int rgb = 0xFFFFFF;
            for (int rgb2 : col) {
                rgb = ClientUtilities.blendColoursToInt(rgb, rgb2);
            }
            return rgb & 0xFFFFFF;
        }
        return 0xFFFFFF;
    }

    public static List<Integer> getImageColours(BufferedImage image) {
        ArrayList<Integer> colourSet = new ArrayList<Integer>();
        int[] data = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), data, 0, image.getWidth());
        for (int i = 0; i < data.length; ++i) {
            colourSet.add(data[i]);
        }
        Collections.sort(colourSet, new ColourBrightnessComparator());
        return colourSet;
    }

    public static int blendColoursToInt(Object o0, Object o1) {
        return ClientUtilities.blendColours(o0, o1).getRGB();
    }

    public static Color blendColours(Object o0, Object o1) {
        assert (o0 instanceof Color || o0 instanceof Integer);
        assert (o1 instanceof Color || o1 instanceof Integer);
        Color c0 = o0 instanceof Color ? (Color)o0 : new Color((Integer)o0);
        Color c1 = o1 instanceof Color ? (Color)o1 : new Color((Integer)o1);
        double totalAlpha = c0.getAlpha() + c1.getAlpha();
        double weight0 = (double)c0.getAlpha() / totalAlpha;
        double weight1 = (double)c1.getAlpha() / totalAlpha;
        double r = weight0 * (double)c0.getRed() + weight1 * (double)c1.getRed();
        double g = weight0 * (double)c0.getGreen() + weight1 * (double)c1.getGreen();
        double b = weight0 * (double)c0.getBlue() + weight1 * (double)c1.getBlue();
        double a = Math.max(c0.getAlpha(), c1.getAlpha());
        return new Color((int)r, (int)g, (int)b, (int)a);
    }

    public static int getVibrantColourToInt(Object o0) {
        return ClientUtilities.getVibrantColour(o0).getRGB();
    }

    public static Color getVibrantColour(Object o0) {
        int modifierG;
        int modifierR;
        Color c;
        assert (o0 instanceof Color || o0 instanceof Integer);
        Color color = c = o0 instanceof Color ? (Color)o0 : new Color((Integer)o0);
        int n = c.getRed() > c.getGreen() && c.getRed() > c.getBlue() ? 255 : (modifierR = c.getRed() < c.getGreen() && c.getRed() < c.getBlue() ? 136 : 187);
        int n2 = c.getGreen() > c.getRed() && c.getGreen() > c.getBlue() ? 255 : (modifierG = c.getGreen() < c.getRed() && c.getGreen() < c.getBlue() ? 136 : 187);
        int modifierB = c.getBlue() > c.getGreen() && c.getBlue() > c.getRed() ? 255 : (c.getBlue() < c.getGreen() && c.getBlue() < c.getRed() ? 136 : 187);
        int modifier = (modifierR << 16) + (modifierG << 8) + modifierB;
        Color cV = ClientUtilities.blendColours(c, modifier);
        return cV;
    }

    public static int getPerceptualBrightness(int col) {
        double r = (double)(col >> 16 & 0xFF) / 255.0;
        double g = (double)(col >> 8 & 0xFF) / 255.0;
        double b = (double)(col & 0xFF) / 255.0;
        double brightness = Math.sqrt(0.241 * r * r + 0.691 * g * g + 0.068 * b * b);
        return (int)(brightness * 255.0);
    }

    public static class ColourBrightnessComparator
    implements Comparator<Integer> {
        @Override
        public int compare(Integer i0, Integer i1) {
            Integer b1 = ClientUtilities.getPerceptualBrightness(i0);
            Integer b2 = ClientUtilities.getPerceptualBrightness(i1);
            return b1.compareTo(b2);
        }
    }
}

