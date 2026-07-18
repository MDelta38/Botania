/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelHourglass;
import vazkii.botania.common.block.tile.TileHourglass;

public class RenderTileHourglass
extends TileEntitySpecialRenderer {
    ResourceLocation texture = new ResourceLocation("botania:textures/model/hourglass.png");
    ModelHourglass model = new ModelHourglass();

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float ticks) {
        float rot;
        int wtime;
        TileHourglass hourglass = (TileHourglass)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(this.texture);
        int n = wtime = tileentity.func_145831_w() == null ? 0 : ClientTickHandler.ticksInGame;
        if (wtime != 0) {
            wtime += new Random(tileentity.field_145851_c ^ tileentity.field_145848_d ^ tileentity.field_145849_e).nextInt(360);
        }
        float time = wtime == 0 ? 0.0f : (float)wtime + ticks;
        float x = 0.5f + (float)Math.cos(time * 0.05f) * 0.025f;
        float y = 0.55f + (float)(Math.sin(time * 0.04f) + 1.0) * 0.05f;
        float z = 0.5f + (float)Math.sin(time * 0.05f) * 0.025f;
        ItemStack stack = hourglass.func_70301_a(0);
        float fract1 = stack == null ? 0.0f : hourglass.timeFraction;
        float fract2 = stack == null ? 0.0f : 1.0f - hourglass.timeFraction;
        GL11.glTranslatef((float)x, (float)y, (float)z);
        float f = rot = hourglass.flip ? 180.0f : 1.0f;
        if (hourglass.flipTicks > 0) {
            rot += ((float)hourglass.flipTicks - ticks) * 45.0f;
        }
        GL11.glRotatef((float)rot, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        this.model.render(fract1, fract2, hourglass.flip, hourglass.getColor());
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glPopMatrix();
    }
}

