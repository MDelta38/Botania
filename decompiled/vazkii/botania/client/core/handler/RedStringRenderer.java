/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public final class RedStringRenderer {
    public static final Queue<TileRedString> redStringTiles = new ArrayDeque<TileRedString>();
    static float sizeAlpha = 0.0f;

    public static void renderAll() {
        if (!redStringTiles.isEmpty()) {
            TileRedString tile;
            GL11.glPushMatrix();
            GL11.glDisable((int)3553);
            GL11.glEnable((int)3042);
            GL11.glPushAttrib((int)2896);
            GL11.glDisable((int)2896);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.0f, (float)sizeAlpha);
            Tessellator.renderingWorldRenderer = false;
            while ((tile = redStringTiles.poll()) != null) {
                RedStringRenderer.renderTile(tile);
            }
            GL11.glEnable((int)3553);
            GL11.glDisable((int)3042);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    public static void tick() {
        boolean hasWand;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        boolean bl = hasWand = player != null && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == ModItems.twigWand;
        if (sizeAlpha > 0.0f && !hasWand) {
            sizeAlpha -= 0.1f;
        } else if (sizeAlpha < 1.0f && hasWand) {
            sizeAlpha += 0.1f;
        }
    }

    private static void renderTile(TileRedString tile) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)tile.func_145832_p());
        ChunkCoordinates bind = tile.getBinding();
        if (bind != null) {
            GL11.glPushMatrix();
            GL11.glTranslated((double)((double)tile.field_145851_c + 0.5 - RenderManager.field_78725_b), (double)((double)tile.field_145848_d + 0.5 - RenderManager.field_78726_c), (double)((double)tile.field_145849_e + 0.5 - RenderManager.field_78723_d));
            Vector3 vecOrig = new Vector3(bind.field_71574_a - tile.field_145851_c, bind.field_71572_b - tile.field_145848_d, bind.field_71573_c - tile.field_145849_e);
            Vector3 vecNorm = vecOrig.copy().normalize();
            Vector3 vecMag = vecNorm.copy().multiply(0.025);
            Vector3 vecApply = vecMag.copy();
            int stages = (int)(vecOrig.mag() / vecMag.mag());
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glLineWidth((float)1.0f);
            tessellator.func_78371_b(1);
            double len = (double)(-ClientTickHandler.ticksInGame) / 100.0 + (double)new Random(dir.ordinal() ^ tile.field_145851_c ^ tile.field_145848_d ^ tile.field_145849_e).nextInt(10000);
            double add = vecMag.mag();
            double rand = Math.random() - 0.5;
            for (int i = 0; i < stages; ++i) {
                RedStringRenderer.addVertexAtWithTranslation(tessellator, dir, vecApply.x, vecApply.y, vecApply.z, rand, len);
                rand = Math.random() - 0.5;
                vecApply.add(vecMag);
                RedStringRenderer.addVertexAtWithTranslation(tessellator, dir, vecApply.x, vecApply.y, vecApply.z, rand, len += add);
            }
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
    }

    private static void addVertexAtWithTranslation(Tessellator tess, ForgeDirection dir, double xpos, double ypos, double zpos, double rand, double l) {
        double freq = 20.0;
        double ampl = (0.15 * (Math.sin(l * 2.0) * 0.5 + 0.5) + 0.1) * (double)sizeAlpha;
        double randMul = 0.05;
        double x = xpos + Math.sin(l * freq) * ampl * (double)Math.abs(Math.abs(dir.offsetX) - 1) + rand * randMul;
        double y = ypos + Math.cos(l * freq) * ampl * (double)Math.abs(Math.abs(dir.offsetY) - 1) + rand * randMul;
        double z = zpos + (dir.offsetY == 0 ? Math.sin(l * freq) : Math.cos(l * freq)) * ampl * (double)Math.abs(Math.abs(dir.offsetZ) - 1) + rand * randMul;
        tess.func_78377_a(x, y, z);
    }
}

