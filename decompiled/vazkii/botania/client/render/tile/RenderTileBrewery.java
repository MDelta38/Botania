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
 *  net.minecraft.util.ResourceLocation
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.model.ModelBrewery;
import vazkii.botania.common.block.tile.TileBrewery;

public class RenderTileBrewery
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/brewery.png");
    ModelBrewery model = new ModelBrewery();
    public TileBrewery brewery;
    public static boolean rotate = true;

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        this.brewery = (TileBrewery)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glTranslatef((float)0.5f, (float)-1.5f, (float)-0.5f);
        double time = (float)ClientTickHandler.ticksInGame + f;
        if (!rotate) {
            time = -1.0;
        }
        this.model.render(this, time);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }

    public void renderItemStack(ItemStack stack) {
        if (stack != null) {
            Minecraft mc = Minecraft.func_71410_x();
            mc.field_71446_o.func_110577_a(stack.func_77973_b() instanceof ItemBlock ? TextureMap.field_110575_b : TextureMap.field_110576_c);
            float s = 0.25f;
            GL11.glScalef((float)s, (float)s, (float)s);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            if (!ForgeHooksClient.renderEntityItem((EntityItem)new EntityItem(this.brewery.func_145831_w(), (double)this.brewery.field_145851_c, (double)this.brewery.field_145848_d, (double)this.brewery.field_145849_e, stack), (ItemStack)stack, (float)0.0f, (float)0.0f, (Random)this.brewery.func_145831_w().field_73012_v, (TextureManager)mc.field_71446_o, (RenderBlocks)RenderBlocks.getInstance(), (int)1)) {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                if (stack.func_77973_b() instanceof ItemBlock && RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)stack.func_77973_b()).func_149645_b())) {
                    GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                    GL11.glTranslatef((float)1.0f, (float)1.1f, (float)0.0f);
                    GL11.glPushMatrix();
                    RenderBlocks.getInstance().func_147800_a(Block.func_149634_a((Item)stack.func_77973_b()), stack.func_77960_j(), 1.0f);
                    GL11.glPopMatrix();
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
            GL11.glScalef((float)(1.0f / s), (float)(1.0f / s), (float)(1.0f / s));
            Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        }
    }
}

