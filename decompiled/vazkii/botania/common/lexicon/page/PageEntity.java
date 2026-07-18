/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.lexicon.page.PageText;

public class PageEntity
extends LexiconPage {
    Entity dummyEntity;
    int relativeMouseX;
    int relativeMouseY;
    boolean tooltipEntity;
    int size;
    Constructor entityConstructor;

    public PageEntity(String unlocalizedName, String entity, int size) {
        super(unlocalizedName);
        Class EntityClass = (Class)EntityList.field_75625_b.get(entity);
        this.size = size;
        try {
            this.entityConstructor = EntityClass.getConstructor(World.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        this.prepDummy();
        int text_x = gui.getLeft() + 16;
        int text_y = gui.getTop() + gui.getHeight() - 40;
        int entity_scale = this.getEntityScale(this.size);
        int entity_x = gui.getLeft() + gui.getWidth() / 2;
        int entity_y = gui.getTop() + gui.getHeight() / 2 + MathHelper.func_76141_d((float)(this.dummyEntity.field_70131_O * (float)entity_scale / 2.0f));
        this.renderEntity(gui, this.dummyEntity, entity_x, entity_y, entity_scale, this.dummyEntity.field_70173_aa * 2);
        PageText.renderText(text_x, text_y, gui.getWidth() - 30, gui.getHeight(), this.getUnlocalizedName());
    }

    @SideOnly(value=Side.CLIENT)
    public int getEntityScale(int targetSize) {
        float entity_size = this.dummyEntity.field_70130_N;
        if (this.dummyEntity.field_70130_N < this.dummyEntity.field_70131_O) {
            entity_size = this.dummyEntity.field_70131_O;
        }
        return MathHelper.func_76141_d((float)((float)this.size / entity_size));
    }

    @Override
    public void updateScreen() {
        this.prepDummy();
        ++this.dummyEntity.field_70173_aa;
    }

    @SideOnly(value=Side.CLIENT)
    public void renderEntity(IGuiLexiconEntry gui, Entity entity, int x, int y, int scale, float rotation) {
        this.dummyEntity.field_70170_p = Minecraft.func_71410_x() != null ? Minecraft.func_71410_x().field_71441_e : null;
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)x, (float)y, (float)50.0f);
        GL11.glScalef((float)(-scale), (float)scale, (float)scale);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a(entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        if ((float)this.relativeMouseX >= (float)x - this.dummyEntity.field_70130_N * (float)scale / 2.0f - 10.0f && (float)this.relativeMouseY >= (float)y - this.dummyEntity.field_70131_O * (float)scale - 20.0f && (float)this.relativeMouseX <= (float)x + this.dummyEntity.field_70130_N * (float)scale / 2.0f + 10.0f && this.relativeMouseY <= y + 20) {
            this.tooltipEntity = true;
        }
    }

    public void prepDummy() {
        if (this.dummyEntity == null || this.dummyEntity.field_70128_L) {
            try {
                this.dummyEntity = (Entity)this.entityConstructor.newInstance(Minecraft.func_71410_x().field_71441_e);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

