/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.tiles.TileNode
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.client.renderer.model.ModelRecombinator;
import com.kentington.thaumichorizons.common.tiles.TileRecombinator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.tiles.TileNode;

@SideOnly(value=Side.CLIENT)
public class TileRecombinatorRender
extends TileEntitySpecialRenderer {
    static String tx1 = "textures/models/recombinator.png";
    static String tx2 = "textures/items/lightningringv.png";
    private ModelRecombinator base = new ModelRecombinator();

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        TileRecombinator tco = (TileRecombinator)te;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y - 0.5f), (float)((float)z + 0.5f));
        UtilsFX.bindTexture((String)"thaumichorizons", (String)tx1);
        float sin = (float)Math.sin((float)tco.count / 8.0f);
        this.base.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, sin * 2.0f);
        if (tco.activated) {
            long nt = System.nanoTime();
            int frames = UtilsFX.getTextureAnimationSize((String)tx2);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            UtilsFX.bindTexture((String)"thaumcraft", (String)tx2);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPushMatrix();
            UtilsFX.renderFacingQuad((double)((double)tco.field_145851_c + 0.5), (double)((double)tco.field_145848_d + 1.5 + 0.2 * (double)sin), (double)((double)tco.field_145849_e + 0.5), (float)0.0f, (float)1.5f, (float)0.9f, (int)frames, (int)i, (float)f, (int)0xFFFFFF);
            GL11.glPopMatrix();
            if (te.func_145831_w().func_147438_o(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e) instanceof TileNode) {
                Thaumcraft.proxy.beam((World)Minecraft.func_71410_x().field_71441_e, (double)te.field_145851_c + 0.5, (double)te.field_145848_d + 0.2, (double)te.field_145849_e + 0.5, (double)te.field_145851_c + 0.5, (double)te.field_145848_d - 0.5, (double)te.field_145849_e + 0.5, 1, 0xFFFFFF, false, 0.1f, 3);
            }
            GL11.glDisable((int)3042);
        }
        GL11.glPopMatrix();
    }
}

