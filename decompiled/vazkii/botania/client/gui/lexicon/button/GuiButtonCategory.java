/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.button.GuiButtonLexicon;
import vazkii.botania.common.core.handler.ConfigHandler;

public class GuiButtonCategory
extends GuiButtonLexicon {
    private static final ResourceLocation fallbackResource = new ResourceLocation("botania:textures/gui/categories/index.png");
    private static final ResourceLocation stencilResource = new ResourceLocation("botania:textures/gui/stencil.png");
    private ShaderCallback shaderCallback = new ShaderCallback(){

        @Override
        public void call(int shader) {
            TextureManager r = Minecraft.func_71410_x().field_71446_o;
            int heightMatchUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"heightMatch");
            int imageUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"image");
            int maskUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"mask");
            float heightMatch = GuiButtonCategory.this.ticksHovered / GuiButtonCategory.this.time;
            OpenGlHelper.func_77473_a((int)33984);
            GL11.glBindTexture((int)3553, (int)r.func_110581_b(GuiButtonCategory.this.resource).func_110552_b());
            ARBShaderObjects.glUniform1iARB((int)imageUniform, (int)0);
            OpenGlHelper.func_77473_a((int)(33984 + ConfigHandler.glSecondaryTextureUnit));
            GL11.glEnable((int)3553);
            GL11.glGetInteger((int)32873);
            GL11.glBindTexture((int)3553, (int)r.func_110581_b(stencilResource).func_110552_b());
            ARBShaderObjects.glUniform1iARB((int)maskUniform, (int)ConfigHandler.glSecondaryTextureUnit);
            ARBShaderObjects.glUniform1fARB((int)heightMatchUniform, (float)heightMatch);
        }
    };
    static boolean boundStencil = false;
    GuiLexicon gui;
    LexiconCategory category;
    ResourceLocation resource = null;
    float ticksHovered = 0.0f;
    float time = 12.0f;
    int activeTex = 0;

    public GuiButtonCategory(int id, int x, int y, GuiLexicon gui, LexiconCategory category) {
        super(id, x, y, 16, 16, "");
        this.gui = gui;
        this.category = category;
    }

    public void func_146112_a(Minecraft mc, int mx, int my) {
        boolean inside = mx >= this.field_146128_h && my >= this.field_146129_i && mx < this.field_146128_h + this.field_146120_f && my < this.field_146129_i + this.field_146121_g;
        this.ticksHovered = inside ? Math.min(this.time, this.ticksHovered + this.gui.timeDelta) : Math.max(0.0f, this.ticksHovered - this.gui.timeDelta);
        if (this.resource == null) {
            this.resource = this.category == null ? fallbackResource : this.category.getIcon();
            if (this.resource == null) {
                this.resource = fallbackResource;
            }
        }
        float s = 0.03125f;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (!boundStencil) {
            mc.field_71446_o.func_110577_a(stencilResource);
            boundStencil = true;
        }
        mc.field_71446_o.func_110577_a(this.resource);
        int texture = 0;
        boolean shaders = ShaderHelper.useShaders();
        if (shaders) {
            OpenGlHelper.func_77473_a((int)(33984 + ConfigHandler.glSecondaryTextureUnit));
            texture = GL11.glGetInteger((int)32873);
        }
        ShaderHelper.useShader(ShaderHelper.categoryButton, this.shaderCallback);
        RenderHelper.drawTexturedModalRect(this.field_146128_h * 2, this.field_146129_i * 2, this.field_73735_i * 2.0f, 0, 0, 32, 32, s, s);
        ShaderHelper.releaseShader();
        if (shaders) {
            OpenGlHelper.func_77473_a((int)(33984 + ConfigHandler.glSecondaryTextureUnit));
            GL11.glBindTexture((int)3553, (int)texture);
            OpenGlHelper.func_77473_a((int)33984);
        }
        GL11.glPopMatrix();
        if (inside) {
            this.gui.categoryHighlight = StatCollector.func_74838_a((String)this.getTooltipText());
        }
    }

    String getTooltipText() {
        if (this.category == null) {
            return "botaniamisc.lexiconIndex";
        }
        return this.category.getUnlocalizedName();
    }

    public LexiconCategory getCategory() {
        return this.category;
    }
}

