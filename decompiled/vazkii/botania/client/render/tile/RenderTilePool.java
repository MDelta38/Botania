/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.mana.IPoolOverlayProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.ModelPool;
import vazkii.botania.common.block.mana.BlockPool;
import vazkii.botania.common.block.tile.mana.TilePool;

public class RenderTilePool
extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/pool.png");
    private static final ResourceLocation textureInf = new ResourceLocation("botania:textures/model/infinitePool.png");
    private static final ResourceLocation textureDil = new ResourceLocation("botania:textures/model/dilutedPool.png");
    private static final ModelPool model = new ModelPool();
    RenderItem renderItem = new RenderItem();
    public static int forceMeta = 0;
    public static boolean forceMana = false;
    public static int forceManaNumber = -1;

    public void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f) {
        IIcon overlay;
        Block below;
        int cap;
        boolean dil;
        boolean inf;
        TilePool pool = (TilePool)tileentity;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)32826);
        float a = MultiblockRenderHandler.rendering ? 0.6f : 1.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        GL11.glTranslated((double)d0, (double)d1, (double)d2);
        boolean bl = tileentity.func_145831_w() == null ? forceMeta == 1 : (inf = tileentity.func_145832_p() == 1);
        boolean bl2 = tileentity.func_145831_w() == null ? forceMeta == 2 : (dil = tileentity.func_145832_p() == 2);
        boolean fab = tileentity.func_145831_w() == null ? forceMeta == 3 : tileentity.func_145832_p() == 3;
        Minecraft.func_71410_x().field_71446_o.func_110577_a(inf ? textureInf : (dil ? textureDil : texture));
        GL11.glTranslatef((float)0.5f, (float)1.5f, (float)0.5f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        if (fab) {
            float time = (float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks;
            if (tileentity != null) {
                time += (float)new Random(tileentity.field_145851_c ^ tileentity.field_145848_d ^ tileentity.field_145849_e).nextInt(100000);
            }
            Color color = Color.getHSBColor(time * 0.005f, 0.6f, 1.0f);
            GL11.glColor4ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()), (byte)-1);
        } else {
            int color = pool.color;
            float[] acolor = EntitySheep.field_70898_d[color];
            GL11.glColor4f((float)acolor[0], (float)acolor[1], (float)acolor[2], (float)a);
        }
        model.render();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        GL11.glEnable((int)32826);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        int mana = pool.getCurrentMana();
        if (forceManaNumber > -1) {
            mana = forceManaNumber;
        }
        if ((cap = pool.manaCap) == -1) {
            cap = 1000000;
        }
        float waterLevel = (float)mana / (float)cap * 0.4f;
        if (forceMana) {
            waterLevel = 0.4f;
        }
        float s = 0.0625f;
        float v = 0.125f;
        float w = -v * 3.5f;
        if (pool.func_145831_w() != null && (below = pool.func_145831_w().func_147439_a(pool.field_145851_c, pool.field_145848_d - 1, pool.field_145849_e)) instanceof IPoolOverlayProvider && (overlay = ((IPoolOverlayProvider)below).getIcon(pool.func_145831_w(), pool.field_145851_c, pool.field_145848_d - 1, pool.field_145849_e)) != null) {
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(a * (float)((Math.sin((double)((float)ClientTickHandler.ticksInGame + f) / 20.0) + 1.0) * 0.3 + 0.2)));
            GL11.glTranslatef((float)-0.5f, (float)-1.4300001f, (float)-0.5f);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            this.renderIcon(0, 0, overlay, 16, 16, 240);
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        if (waterLevel > 0.0f) {
            s = 0.0546875f;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)a);
            GL11.glTranslatef((float)w, (float)(-1.0f - (0.43f - waterLevel)), (float)w);
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)s, (float)s, (float)s);
            ShaderHelper.useShader(ShaderHelper.manaPool);
            this.renderIcon(0, 0, BlockPool.manaIcon, 16, 16, 240);
            ShaderHelper.releaseShader();
            GL11.glEnable((int)3008);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        forceMeta = 0;
        forceMana = false;
        forceManaNumber = -1;
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, int brightness) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par5), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + par5), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94210_h());
        tessellator.func_78374_a((double)(par1 + par4), (double)(par2 + 0), 0.0, (double)par3Icon.func_94212_f(), (double)par3Icon.func_94206_g());
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), 0.0, (double)par3Icon.func_94209_e(), (double)par3Icon.func_94206_g());
        tessellator.func_78381_a();
    }
}

