/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.model.ModelSpinningCubes;
import vazkii.botania.common.block.tile.TileRuneAltar;

public class RenderTileRuneAltar
extends TileEntitySpecialRenderer {
    ModelSpinningCubes cubes = new ModelSpinningCubes();
    RenderBlocks renderBlocks = new RenderBlocks();

    public void func_147500_a(TileEntity tileentity, double x, double y, double z, float partticks) {
        float scale;
        TileRuneAltar altar = (TileRuneAltar)tileentity;
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)x, (double)y, (double)z);
        int items = 0;
        for (int i = 0; i < altar.func_70302_i_() && altar.func_70301_a(i) != null; ++i) {
            ++items;
        }
        float[] angles = new float[altar.func_70302_i_()];
        float anglePer = 360.0f / (float)items;
        float totalAngle = 0.0f;
        for (int i = 0; i < angles.length; ++i) {
            angles[i] = totalAngle += anglePer;
        }
        double time = (float)ClientTickHandler.ticksInGame + partticks;
        for (int i = 0; i < altar.func_70302_i_(); ++i) {
            GL11.glPushMatrix();
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glTranslatef((float)1.0f, (float)2.5f, (float)1.0f);
            GL11.glRotatef((float)(angles[i] + (float)time), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)2.25f, (float)0.0f, (float)0.5f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslated((double)0.0, (double)(0.15 * Math.sin((time + (double)(i * 10)) / 5.0)), (double)0.0);
            ItemStack stack = altar.func_70301_a(i);
            Minecraft mc = Minecraft.func_71410_x();
            if (stack != null) {
                mc.field_71446_o.func_110577_a(stack.func_77973_b() instanceof ItemBlock ? TextureMap.field_110575_b : TextureMap.field_110576_c);
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
                if (!ForgeHooksClient.renderEntityItem((EntityItem)new EntityItem(altar.func_145831_w(), (double)altar.field_145851_c, (double)altar.field_145848_d, (double)altar.field_145849_e, stack), (ItemStack)stack, (float)0.0f, (float)0.0f, (Random)altar.func_145831_w().field_73012_v, (TextureManager)mc.field_71446_o, (RenderBlocks)this.renderBlocks, (int)1)) {
                    GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    if (stack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)stack.func_77973_b()).func_149645_b())) {
                        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                        GL11.glTranslatef((float)1.0f, (float)1.1f, (float)0.0f);
                        this.renderBlocks.func_147800_a(Block.func_149634_a((Item)stack.func_77973_b()), stack.func_77960_j(), 1.0f);
                        GL11.glTranslatef((float)-1.0f, (float)-1.1f, (float)0.0f);
                        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
                    } else {
                        int renderPass = 0;
                        do {
                            IIcon icon;
                            if ((icon = stack.func_77973_b().getIcon(stack, renderPass)) == null) continue;
                            Color color = new Color(stack.func_77973_b().func_82790_a(stack, renderPass));
                            GL11.glColor3ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()));
                            float f = icon.func_94209_e();
                            float f1 = icon.func_94212_f();
                            float f2 = icon.func_94206_g();
                            float f3 = icon.func_94210_h();
                            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
                            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                        } while (++renderPass < stack.func_77973_b().getRenderPasses(stack.func_77960_j()));
                    }
                }
            }
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.5f, (float)1.8f, (float)0.5f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)1.0f);
        int repeat = 15;
        this.cubes.renderSpinningCubes(2, repeat, repeat);
        GL11.glPopMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.2f, (float)0.0f);
        float f = scale = altar.getTargetMana() == 0 ? 0.0f : (float)altar.getCurrentMana() / (float)altar.getTargetMana() / 75.0f;
        if (scale != 0.0f) {
            int seed = altar.field_145851_c ^ altar.field_145848_d ^ altar.field_145849_e;
            GL11.glTranslatef((float)0.5f, (float)0.7f, (float)0.5f);
            RenderHelper.renderStar(58583, scale, scale, scale, seed);
        }
        GL11.glEnable((int)3008);
        GL11.glPopMatrix();
    }
}

