/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.items.relics.ItemThaumometer;
import thaumcraft.common.tiles.TileJarNode;
import thaumcraft.common.tiles.TileNode;

@SideOnly(value=Side.CLIENT)
public class TileNodeRenderer
extends TileEntitySpecialRenderer {
    public static final ResourceLocation nodetex = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");

    public static void renderNode(EntityLivingBase viewer, double viewDistance, boolean visible, boolean depthIgnore, float size, int x, int y, int z, float partialTicks, AspectList aspects, NodeType type, NodeModifier mod) {
        long nt = System.nanoTime();
        UtilsFX.bindTexture(nodetex);
        int frames = 32;
        if (aspects.size() > 0 && visible) {
            double distance = viewer.func_70011_f((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            if (distance > viewDistance) {
                return;
            }
            float alpha = (float)((viewDistance - distance) / viewDistance);
            if (mod != null) {
                switch (mod) {
                    case BRIGHT: {
                        alpha *= 1.5f;
                        break;
                    }
                    case PALE: {
                        alpha *= 0.66f;
                        break;
                    }
                    case FADING: {
                        alpha *= MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / 3.0f)) * 0.25f + 0.33f;
                    }
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDepthMask((boolean)false);
            if (depthIgnore) {
                GL11.glDisable((int)2929);
            }
            GL11.glDisable((int)2884);
            long time = nt / 5000000L;
            float bscale = 0.25f;
            GL11.glPushMatrix();
            float rad = (float)Math.PI * 2;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            int i = (int)((nt / 40000000L + (long)x) % (long)frames);
            int count = 0;
            float scale = 0.0f;
            float angle = 0.0f;
            float average = 0.0f;
            for (Aspect aspect : aspects.getAspects()) {
                if (aspect.getBlend() == 771) {
                    alpha = (float)((double)alpha * 1.5);
                }
                average += (float)aspects.getAmount(aspect);
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)aspect.getBlend());
                scale = MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                scale = 0.2f + scale * ((float)aspects.getAmount(aspect) / 50.0f);
                angle = (float)(time % (long)(5000 + 500 * count)) / (5000.0f + (float)(500 * count)) * rad;
                UtilsFX.renderFacingStrip((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, angle, scale *= size, alpha / Math.max(1.0f, (float)aspects.size() / 2.0f), frames, 0, i, partialTicks, aspect.getColor());
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++count;
                if (aspect.getBlend() != 771) continue;
                alpha = (float)((double)alpha / 1.5);
            }
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            i = (int)((nt / 40000000L + (long)x) % (long)frames);
            scale = 0.1f + (average /= (float)aspects.size()) / 150.0f;
            scale *= size;
            int strip = 1;
            switch (type) {
                case NORMAL: {
                    GL11.glBlendFunc((int)770, (int)1);
                    break;
                }
                case UNSTABLE: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 6;
                    angle = 0.0f;
                    break;
                }
                case DARK: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 2;
                    break;
                }
                case TAINTED: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 5;
                    break;
                }
                case PURE: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 4;
                    break;
                }
                case HUNGRY: {
                    scale *= 0.75f;
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 3;
                }
            }
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)alpha);
            UtilsFX.renderFacingStrip((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, angle, scale, alpha, frames, strip, i, partialTicks, 0xFFFFFF);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glEnable((int)2884);
            if (depthIgnore) {
                GL11.glEnable((int)2929);
            }
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glDepthMask((boolean)false);
            int i = (int)((nt / 40000000L + (long)x) % (long)frames);
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)0.1f);
            UtilsFX.renderFacingStrip((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 0.0f, 0.5f, 0.1f, frames, 1, i, partialTicks, 0xFFFFFF);
            GL11.glDepthMask((boolean)true);
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
        if (!(tile instanceof INode)) {
            return;
        }
        float size = 1.0f;
        INode node = (INode)tile;
        double viewDistance = 64.0;
        EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
        boolean condition = false;
        boolean depthIgnore = false;
        if (viewer instanceof EntityPlayer) {
            if (tile != null && tile instanceof TileJarNode) {
                condition = true;
                size = 0.7f;
            } else if (((EntityPlayer)viewer).field_71071_by.func_70440_f(3) != null && ((EntityPlayer)viewer).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer && ((IRevealer)((EntityPlayer)viewer).field_71071_by.func_70440_f(3).func_77973_b()).showNodes(((EntityPlayer)viewer).field_71071_by.func_70440_f(3), viewer)) {
                condition = true;
                depthIgnore = true;
            } else if (((EntityPlayer)viewer).field_71071_by.func_70448_g() != null && ((EntityPlayer)viewer).field_71071_by.func_70448_g().func_77973_b() instanceof ItemThaumometer && UtilsFX.isVisibleTo(0.44f, (Entity)viewer, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)) {
                condition = true;
                depthIgnore = true;
                viewDistance = 48.0;
            }
        }
        TileNodeRenderer.renderNode(viewer, viewDistance, condition, depthIgnore, size, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, partialTicks, ((INode)tile).getAspects(), ((INode)tile).getNodeType(), ((INode)tile).getNodeModifier());
        if (tile instanceof TileNode && ((TileNode)tile).drainEntity != null && ((TileNode)tile).drainCollision != null) {
            Entity drainEntity = ((TileNode)tile).drainEntity;
            if (drainEntity instanceof EntityPlayer && !((EntityPlayer)drainEntity).func_71039_bw()) {
                ((TileNode)tile).drainEntity = null;
                ((TileNode)tile).drainCollision = null;
                return;
            }
            MovingObjectPosition drainCollision = ((TileNode)tile).drainCollision;
            GL11.glPushMatrix();
            float f10 = 0.0f;
            int iiud = ((EntityPlayer)drainEntity).func_71057_bx();
            if (drainEntity instanceof EntityPlayer) {
                f10 = MathHelper.func_76126_a((float)((float)iiud / 10.0f)) * 10.0f;
            }
            Vec3 vec3 = Vec3.func_72443_a((double)-0.1, (double)-0.1, (double)0.5);
            vec3.func_72440_a(-(drainEntity.field_70127_C + (drainEntity.field_70125_A - drainEntity.field_70127_C) * partialTicks) * (float)Math.PI / 180.0f);
            vec3.func_72442_b(-(drainEntity.field_70126_B + (drainEntity.field_70177_z - drainEntity.field_70126_B) * partialTicks) * (float)Math.PI / 180.0f);
            vec3.func_72442_b(-f10 * 0.01f);
            vec3.func_72440_a(-f10 * 0.015f);
            double d3 = drainEntity.field_70169_q + (drainEntity.field_70165_t - drainEntity.field_70169_q) * (double)partialTicks + vec3.field_72450_a;
            double d4 = drainEntity.field_70167_r + (drainEntity.field_70163_u - drainEntity.field_70167_r) * (double)partialTicks + vec3.field_72448_b;
            double d5 = drainEntity.field_70166_s + (drainEntity.field_70161_v - drainEntity.field_70166_s) * (double)partialTicks + vec3.field_72449_c;
            double d6 = drainEntity == Minecraft.func_71410_x().field_71439_g ? 0.0 : (double)drainEntity.func_70047_e();
            UtilsFX.drawFloatyLine(d3, d4 + d6, d5, (double)drainCollision.field_72311_b + 0.5, (double)drainCollision.field_72312_c + 0.5, (double)drainCollision.field_72309_d + 0.5, partialTicks, ((TileNode)tile).color.getRGB(), "textures/misc/wispy.png", -0.02f, (float)Math.min(iiud, 10) / 10.0f);
            GL11.glPopMatrix();
        }
    }
}

