/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.render.entity.RenderSparkBase;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.item.ItemCorporeaSpark;

public class RenderCorporeaSpark
extends RenderSparkBase<EntityCorporeaSpark> {
    @Override
    public IIcon getBaseIcon(EntityCorporeaSpark entity) {
        return entity.isMaster() ? ItemCorporeaSpark.worldIconMaster : ItemCorporeaSpark.worldIcon;
    }

    @Override
    public void colorSpinningIcon(EntityCorporeaSpark entity, float a) {
        int network = Math.min(15, entity.getNetwork());
        GL11.glColor4f((float)EntitySheep.field_70898_d[network][0], (float)EntitySheep.field_70898_d[network][1], (float)EntitySheep.field_70898_d[network][2], (float)a);
    }

    @Override
    public IIcon getSpinningIcon(EntityCorporeaSpark entity) {
        return ItemCorporeaSpark.iconColorStar;
    }

    @Override
    public void renderCallback(EntityCorporeaSpark entity, float pticks) {
        IIcon icon;
        int time = entity.getItemDisplayTicks();
        if (time == 0) {
            return;
        }
        float absTime = (float)Math.abs(time) - pticks;
        GL11.glPushMatrix();
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        float scalef = 0.16666667f;
        GL11.glScalef((float)scalef, (float)scalef, (float)scalef);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(absTime / 10.0f));
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)(-2.0f + (time < 0 ? -absTime : absTime) / 6.0f));
        ItemStack stack = entity.getDisplayedItem();
        if (stack == null) {
            return;
        }
        Item item = stack.func_77973_b();
        boolean block = item instanceof ItemBlock;
        Minecraft.func_71410_x().field_71446_o.func_110577_a(block ? TextureMap.field_110575_b : TextureMap.field_110576_c);
        IIcon iIcon = icon = block ? Block.func_149634_a((Item)item).func_149733_h(ForgeDirection.UP.ordinal()) : item.getIcon(stack, 0);
        if (icon != null) {
            float minU = icon.func_94209_e();
            float maxU = icon.func_94212_f();
            float minV = icon.func_94206_g();
            float maxV = icon.func_94210_h();
            int pieces = 8;
            float stepU = (maxU - minU) / (float)pieces;
            float stepV = (maxV - minV) / (float)pieces;
            float gap = 1.0f + (time > 0 ? 10.0f - absTime : absTime) * 0.2f;
            int shift = pieces / 2;
            float scale = 1.0f / (float)pieces * 3.0f;
            GL11.glScalef((float)scale, (float)scale, (float)1.0f);
            for (int i = -shift; i < shift; ++i) {
                GL11.glTranslated((double)(gap * (float)i), (double)0.0, (double)0.0);
                for (int j = -shift; j < shift; ++j) {
                    GL11.glTranslated((double)0.0, (double)(gap * (float)j), (double)0.0);
                    ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)(minU + stepU * (float)(i + shift)), (float)(minV + stepV * (float)(j + shift + 1)), (float)(minU + stepU * (float)(i + shift + 1)), (float)(minV + stepV * (float)(j + shift)), (int)(icon.func_94211_a() / pieces), (int)(icon.func_94216_b() / pieces), (float)0.125f);
                    GL11.glTranslated((double)0.0, (double)(-gap * (float)j), (double)0.0);
                }
                GL11.glTranslated((double)(-gap * (float)i), (double)0.0, (double)0.0);
            }
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

