/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelBook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.common.item.ModItems;

public class RenderLexicon
implements IItemRenderer {
    ModelBook model = new ModelBook();
    ResourceLocation texture = new ResourceLocation("botania:textures/model/lexica.png");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        GL11.glPushMatrix();
        Minecraft mc = Minecraft.func_71410_x();
        mc.field_71446_o.func_110577_a(this.texture);
        float opening = 0.0f;
        float pageFlip = 0.0f;
        float ticks = ClientTickHandler.ticksWithLexicaOpen;
        if (ticks > 0.0f && ticks < 10.0f) {
            ticks = mc.field_71462_r instanceof GuiLexicon ? (ticks += ClientTickHandler.partialTicks) : (ticks -= ClientTickHandler.partialTicks);
        }
        GL11.glTranslatef((float)(0.3f + 0.02f * ticks), (float)(0.475f + 0.01f * ticks), (float)(-0.2f - 0.01f * ticks));
        GL11.glRotatef((float)(87.5f + ticks * 5.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(ticks * 2.5f), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        opening = ticks / 12.0f;
        float pageFlipTicks = ClientTickHandler.pageFlipTicks;
        if (pageFlipTicks > 0.0f) {
            pageFlipTicks -= ClientTickHandler.partialTicks;
        }
        pageFlip = pageFlipTicks / 5.0f;
        this.model.func_78088_a(null, 0.0f, 0.0f, pageFlip, opening, 0.0f, 0.0625f);
        if (ticks < 3.0f) {
            String title;
            FontRenderer font = Minecraft.func_71410_x().field_71466_p;
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.3f, (float)-0.21f, (float)-0.07f);
            GL11.glScalef((float)0.0035f, (float)0.0035f, (float)-0.0035f);
            boolean bevo = Minecraft.func_71410_x().field_71439_g.func_70005_c_().equalsIgnoreCase("BevoLJ");
            boolean saice = Minecraft.func_71410_x().field_71439_g.func_70005_c_().equalsIgnoreCase("saice");
            String origTitle = title = ModItems.lexicon.func_77653_i(null);
            if (Minecraft.func_71410_x().field_71439_g.func_71045_bC() != null) {
                title = Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_82833_r();
            }
            if (title.equals(origTitle) && bevo) {
                title = StatCollector.func_74838_a((String)"item.botania:lexicon.bevo");
            }
            if (title.equals(origTitle) && saice) {
                title = StatCollector.func_74838_a((String)"item.botania:lexicon.saice");
            }
            font.func_78276_b(font.func_78269_a(title, 80), 0, 0, 14063360);
            GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
            GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
            font.func_78276_b(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.BOLD + String.format(StatCollector.func_74838_a((String)"botaniamisc.edition"), ItemLexicon.getEdition()), 0, 0, 10514688);
            GL11.glTranslatef((float)0.0f, (float)15.0f, (float)0.0f);
            font.func_78276_b(StatCollector.func_74838_a((String)"botaniamisc.lexiconcover0"), 0, 0, 7995282);
            GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
            font.func_78276_b(StatCollector.func_74838_a((String)"botaniamisc.lexiconcover1"), 0, 0, 7995282);
            GL11.glTranslatef((float)0.0f, (float)50.0f, (float)0.0f);
            font.func_78276_b(StatCollector.func_74838_a((String)"botaniamisc.lexiconcover2"), 0, 0, 7995282);
            GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
            font.func_78276_b(EnumChatFormatting.UNDERLINE + "" + EnumChatFormatting.ITALIC + StatCollector.func_74838_a((String)"botaniamisc.lexiconcover3"), 0, 0, 7995282);
            if (bevo || saice) {
                GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
                font.func_78276_b(StatCollector.func_74838_a((String)("botaniamisc.lexiconcover" + (bevo ? 4 : 5))), 0, 0, 7995282);
            }
        }
        GL11.glPopMatrix();
    }
}

