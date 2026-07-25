/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelBrain
 *  thaumcraft.client.renderers.models.ModelJar
 *  thaumcraft.client.renderers.tile.TileNodeRenderer
 *  thaumcraft.common.blocks.BlockJar
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.tiles.TileJar
 *  thaumcraft.common.tiles.TileJarFillable
 *  thaumcraft.common.tiles.TileJarNode
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelBrain;
import thaumcraft.client.renderers.models.ModelJar;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.common.blocks.BlockJar;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileJar;
import thaumcraft.common.tiles.TileJarFillable;
import thaumcraft.common.tiles.TileJarNode;

@SideOnly(value=Side.CLIENT)
public class TileEntityTrashJarRenderer
extends TileEntitySpecialRenderer {
    private ModelJar model = new ModelJar();
    private ModelBrain brain = new ModelBrain();
    private TileNodeRenderer tnr = new TileNodeRenderer();
    private static final ResourceLocation texture = new ResourceLocation("thaumicexploration:textures/models/jar_oblivion.png");

    public void renderTileEntityAt(TileJar tile, double x, double y, double z, float f) {
        if (tile instanceof TileJarNode) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
            this.tnr.func_147500_a((TileEntity)tile, x, y, z, f);
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.01f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (tile instanceof TileJarFillable) {
            if (((TileJarFillable)tile).amount > 0) {
                this.renderLiquid((TileJarFillable)tile, x, y, z, f);
            }
            if (((TileJarFillable)tile).aspectFilter != null) {
                GL11.glPushMatrix();
                switch (((TileJarFillable)tile).facing) {
                    case 3: {
                        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        break;
                    }
                    case 5: {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                        break;
                    }
                    case 4: {
                        GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    }
                }
                float rot = (((TileJarFillable)tile).aspectFilter.getTag().hashCode() + tile.field_145851_c + ((TileJarFillable)tile).facing) % 4 - 2;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.315f);
                if (Config.crooked) {
                    GL11.glRotatef((float)rot, (float)0.0f, (float)0.0f, (float)1.0f);
                }
                UtilsFX.renderQuadCenteredFromTexture((String)"textures/models/label.png", (float)0.5f, (float)1.0f, (float)1.0f, (float)1.0f, (int)-99, (int)771, (float)1.0f);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.316f);
                if (Config.crooked) {
                    GL11.glRotatef((float)rot, (float)0.0f, (float)0.0f, (float)1.0f);
                }
                GL11.glScaled((double)0.021, (double)0.021, (double)0.021);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                UtilsFX.drawTag((int)-8, (int)-8, (Aspect)((TileJarFillable)tile).aspectFilter);
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
        }
        this.func_147499_a(texture);
        this.model.renderAll();
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }

    public void renderLiquid(TileJarFillable te, double x, double y, double z, float f) {
        if (this.field_147501_a.field_147553_e == null) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        World world = te.func_145831_w();
        RenderBlocks renderBlocks = new RenderBlocks();
        GL11.glDisable((int)2896);
        float level = (float)(te.amount / te.maxAmount) * 0.625f;
        Tessellator t = Tessellator.field_78398_a;
        renderBlocks.func_147782_a(0.25, 0.0625, 0.25, 0.75, 0.0625 + (double)level, 0.75);
        t.func_78382_b();
        if (te.aspect != null) {
            t.func_78378_d(te.aspect.getColor());
        }
        int bright = 200;
        if (te.func_145831_w() != null) {
            bright = Math.max(200, ConfigBlocks.blockJar.func_149677_c((IBlockAccess)te.func_145831_w(), te.field_145851_c, te.field_145848_d, te.field_145849_e));
        }
        t.func_78380_c(bright);
        IIcon icon = ((BlockJar)ConfigBlocks.blockJar).iconLiquid;
        this.field_147501_a.field_147553_e.func_110577_a(TextureMap.field_110575_b);
        renderBlocks.func_147768_a(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        renderBlocks.func_147806_b(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        renderBlocks.func_147761_c(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        renderBlocks.func_147734_d(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        renderBlocks.func_147798_e(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        renderBlocks.func_147764_f(ConfigBlocks.blockJar, -0.5, 0.0, -0.5, icon);
        t.func_78381_a();
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderTileEntityAt((TileJar)par1TileEntity, par2, par4, par6, par8);
    }
}

