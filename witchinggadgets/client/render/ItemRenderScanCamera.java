/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.research.IScanEventHandler
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.research.ScanManager
 *  thaumcraft.common.lib.utils.BlockUtils
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package witchinggadgets.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;
import witchinggadgets.client.ClientProxy;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.util.Utilities;

public class ItemRenderScanCamera
implements IItemRenderer {
    String goldTexture = "thaumcraft:textures/models/scanner.png";
    String leatherTexture = "witchinggadgets:textures/models/cameraLeather.png";
    String scannerTexture = "thaumcraft:textures/models/scanscreen.png";
    String woodTexture = "thaumcraft:textures/blocks/planks_greatwood.png";

    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        try {
            ScanResult scan;
            Minecraft mc = Minecraft.func_71410_x();
            EntityClientPlayerMP player = mc.field_71439_g;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
                GL11.glTranslated((double)0.0, (double)0.125, (double)0.0);
                ClientUtilities.bindTexture(this.goldTexture);
                ClientProxy.cameraModel.renderPart("gold_01");
                ClientUtilities.bindTexture(this.leatherTexture);
                ClientProxy.cameraModel.renderPart("leather_02");
                ClientUtilities.bindTexture(this.scannerTexture);
                ClientProxy.cameraModel.renderPart("scanner_03");
                ClientUtilities.bindTexture(this.woodTexture);
                ClientProxy.cameraModel.renderPart("wood_04");
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                return;
            }
            if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
                ClientUtilities.bindTexture(this.goldTexture);
                ClientProxy.cameraModel.renderPart("gold_01");
                ClientUtilities.bindTexture(this.leatherTexture);
                ClientProxy.cameraModel.renderPart("leather_02");
                ClientUtilities.bindTexture(this.scannerTexture);
                ClientProxy.cameraModel.renderPart("scanner_03");
                ClientUtilities.bindTexture(this.woodTexture);
                ClientProxy.cameraModel.renderPart("wood_04");
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                return;
            }
            GL11.glPushMatrix();
            if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)-70.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)2.0f, (float)1.5f, (float)-1.5f);
                GL11.glTranslatef((float)-0.5f, (float)-0.875f, (float)-0.1f);
            }
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                float f11;
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
                GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
                GL11.glTranslatef((float)-0.35f, (float)0.5f, (float)1.0f);
                float timer = UtilsFX.getTimer((Minecraft)mc).field_74281_c;
                float f12 = 0.8f;
                int i = mc.field_71441_e.func_72802_i(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), 0);
                int k = i / 65536;
                f12 = 0.8f;
                float f7 = player.func_70678_g(timer);
                f7 = 0.0f;
                float f8 = MathHelper.func_76126_a((float)(f7 * (float)Math.PI));
                float f6 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f7) * (float)Math.PI));
                GL11.glTranslatef((float)(-f6 * 0.4f), (float)(MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f7) * (float)Math.PI * 2.0f)) * 0.2f), (float)(-f8 * 0.2f));
                GL11.glTranslatef((float)0.0f, (float)(0.0f * f12 - 0.0f + 0.04f), (float)(-0.9f * f12));
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glEnable((int)32826);
                ClientUtilities.bindTexture(player.func_110306_p().func_110624_b() + ":" + player.func_110306_p().func_110623_a());
                for (k = 0; k < 2; ++k) {
                    int l = k * 2 - 1;
                    GL11.glPushMatrix();
                    GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glTranslatef((float)-0.4f, (float)-0.5f, (float)(0.4f * (float)l));
                    GL11.glRotatef((float)(-35 * l), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)49.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glRotatef((float)(80 * l), (float)0.0f, (float)1.0f, (float)0.0f);
                    Render render = RenderManager.field_78727_a.func_78713_a((Entity)mc.field_71439_g);
                    RenderPlayer renderplayer = (RenderPlayer)render;
                    f11 = 1.0f;
                    GL11.glScalef((float)f11, (float)f11, (float)f11);
                    renderplayer.func_82441_a((EntityPlayer)mc.field_71439_g);
                    GL11.glPopMatrix();
                }
                f8 = player.func_70678_g(timer);
                f6 = MathHelper.func_76126_a((float)(f8 * f8 * (float)Math.PI));
                float f9 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f8) * (float)Math.PI));
                GL11.glRotatef((float)(-f6 * 20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-f9 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(-f9 * 80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
                float f10 = 0.38f;
                GL11.glScalef((float)f10, (float)f10, (float)f10);
                GL11.glTranslatef((float)-1.0f, (float)-1.0f, (float)0.0f);
                f11 = 0.015625f;
                GL11.glScalef((float)f11, (float)f11, (float)f11);
                GL11.glScalef((float)(1.0f / f11), (float)(1.0f / f11), (float)(1.0f / f11));
                GL11.glScalef((float)(1.0f / f10), (float)(1.0f / f10), (float)(1.0f / f10));
                GL11.glRotatef((float)11.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glTranslated((double)0.6, (double)0.15, (double)0.005);
            GL11.glScalef((float)0.875f, (float)1.75f, (float)1.75f);
            ClientUtilities.bindTexture(this.goldTexture);
            ClientProxy.cameraModel.renderPart("gold_01");
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glColor3d((double)0.6, (double)0.6, (double)0.6);
                GL11.glScalef((float)1.75f, (float)1.0f, (float)1.0f);
                GL11.glTranslated((double)0.1, (double)0.0, (double)0.0);
                ClientProxy.cameraModel.renderPart("Display_00");
                GL11.glTranslated((double)-0.1, (double)0.0, (double)0.0);
                GL11.glScalef((float)0.5714286f, (float)1.0f, (float)1.0f);
                GL11.glColor3d((double)1.0, (double)1.0, (double)1.0);
            }
            ClientUtilities.bindTexture(this.leatherTexture);
            ClientProxy.cameraModel.renderPart("leather_02");
            ClientUtilities.bindTexture(this.woodTexture);
            ClientProxy.cameraModel.renderPart("wood_04");
            GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
            ClientUtilities.bindTexture(this.scannerTexture);
            ClientProxy.cameraModel.renderPart("scanner_03");
            GL11.glScalef((float)0.375f, (float)0.375f, (float)-0.375f);
            GL11.glRotated((double)90.0, (double)0.0, (double)1.0, (double)0.0);
            if (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glPushMatrix();
                GL11.glColor3d((double)0.5, (double)0.5, (double)0.5);
                GL11.glTranslated((double)-0.425, (double)-0.425, (double)-0.5625);
                GL11.glScalef((float)0.85f, (float)0.85f, (float)0.85f);
                IIcon ic = ConfigBlocks.blockWoodenDevice.func_149691_a(6, 0);
                ClientUtilities.bindTexture(mc.func_110434_K().func_130087_a(new ItemStack(ConfigBlocks.blockWoodenDevice).func_94608_d()).func_110624_b() + ":" + mc.func_110434_K().func_130087_a(stack.func_94608_d()).func_110623_a());
                Tessellator tes = Tessellator.field_78398_a;
                tes.func_78382_b();
                tes.func_78374_a(0.0, 1.0, 0.0, (double)ic.func_94209_e(), (double)ic.func_94210_h());
                tes.func_78374_a(1.0, 1.0, 0.0, (double)ic.func_94212_f(), (double)ic.func_94210_h());
                tes.func_78374_a(1.0, 0.0, 0.0, (double)ic.func_94212_f(), (double)ic.func_94206_g());
                tes.func_78374_a(0.0, 0.0, 0.0, (double)ic.func_94209_e(), (double)ic.func_94206_g());
                tes.func_78381_a();
                GL11.glPopMatrix();
                GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotated((double)180.0, (double)0.0, (double)0.0, (double)1.0);
            }
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && (scan = this.doScan(stack, (EntityPlayer)player)) != null) {
                AspectList aspects = null;
                GL11.glScalef((float)0.45f, (float)0.45f, (float)0.45f);
                GL11.glRotated((double)-50.0, (double)1.0, (double)0.0, (double)0.0);
                GL11.glTranslatef((float)0.0f, (float)-0.2f, (float)-1.8f);
                String text = "?";
                ItemStack scanStack = null;
                if (scan.id > 0) {
                    scanStack = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
                    if (ScanManager.hasBeenScanned((EntityPlayer)player, (ScanResult)scan)) {
                        aspects = ScanManager.getScanAspects((ScanResult)scan, (World)player.field_70170_p);
                    }
                }
                if (scan.type == 2) {
                    if (scan.entity instanceof EntityItem) {
                        scanStack = ((EntityItem)scan.entity).func_92059_d();
                    } else {
                        text = scan.entity.func_70005_c_();
                    }
                    if (ScanManager.hasBeenScanned((EntityPlayer)player, (ScanResult)scan)) {
                        aspects = ScanManager.getScanAspects((ScanResult)scan, (World)player.field_70170_p);
                    }
                }
                if (scan.type == 3 && scan.phenomena.startsWith("NODE") && ScanManager.hasBeenScanned((EntityPlayer)player, (ScanResult)scan)) {
                    TileEntity tile;
                    MovingObjectPosition mop = null;
                    if (scanStack != null && scanStack.func_77973_b() != null) {
                        mop = EntityUtils.getMovingObjectPositionFromPlayer((World)player.field_70170_p, (EntityPlayer)player, (boolean)true);
                    }
                    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && (tile = player.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) != null && tile instanceof INode) {
                        aspects = ((INode)tile).getAspects();
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)1);
                        String t = StatCollector.func_74838_a((String)("nodetype." + ((INode)tile).getNodeType() + ".name"));
                        if (((INode)tile).getNodeModifier() != null) {
                            t = t + ", " + StatCollector.func_74838_a((String)("nodemod." + ((INode)tile).getNodeModifier() + ".name"));
                        }
                        int sw = mc.field_71466_p.func_78256_a(t);
                        float scale = 0.004f;
                        GL11.glScalef((float)scale, (float)scale, (float)scale);
                        mc.field_71466_p.func_85187_a(t, -sw / 2, -40, 0xFFFFFF, true);
                        GL11.glDisable((int)3042);
                        GL11.glPopMatrix();
                    }
                }
                if (scanStack != null && scanStack.func_77973_b() != null) {
                    text = scanStack.func_82833_r();
                }
                if (aspects != null) {
                    int posX = 0;
                    int posY = 0;
                    int aa = aspects.size();
                    int baseX = Math.min(5, aa) * 8;
                    for (Aspect aspect : aspects.getAspectsSorted()) {
                        GL11.glPushMatrix();
                        GL11.glScalef((float)0.0075f, (float)0.0075f, (float)0.0075f);
                        int j = (int)(190.0f + MathHelper.func_76126_a((float)(posX + player.field_70173_aa - player.field_70170_p.field_73012_v.nextInt(2))) * 10.0f + 10.0f);
                        int k = j % 65536;
                        int l = j / 65536;
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                        UtilsFX.drawTag((int)(-baseX + posX * 16), (int)(-8 + posY * 16), (Aspect)aspect, (float)aspects.getAmount(aspect), (int)0, (double)0.01, (int)1, (float)1.0f, (boolean)false);
                        GL11.glPopMatrix();
                        if (++posX < 5 - posY) continue;
                        posX = 0;
                        baseX = Math.min(5 - posY, aa -= 5 - ++posY) * 8;
                    }
                }
                if (text == null) {
                    text = "?";
                }
                if (text.length() > 0) {
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)1);
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    int sw = mc.field_71466_p.func_78256_a(text);
                    float scale = 0.005f;
                    if (sw > 90) {
                        scale -= 2.5E-5f * (float)(sw - 90);
                    }
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    mc.field_71466_p.func_85187_a(text, -sw / 2, 0, 0xFFFFFF, true);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ScanResult doScan(ItemStack stack, EntityPlayer p) {
        if (stack == null || p == null) {
            return null;
        }
        Entity pointedEntity = EntityUtils.getPointedEntity((World)p.field_70170_p, (Entity)p, (double)0.5, (double)10.0, (float)0.0f, (boolean)true);
        if (pointedEntity != null) {
            ScanResult sr = new ScanResult(2, 0, 0, pointedEntity, "");
            return sr;
        }
        MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer((World)p.field_70170_p, (EntityPlayer)p, (boolean)true);
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int bi = Block.func_149682_b((Block)p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d));
            TileEntity tile = p.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (tile != null && tile instanceof INode) {
                int md = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149643_k(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = new ScanResult(3, bi, md, null, "NODE" + ((INode)tile).getId());
                return sr;
            }
            if (p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) != null) {
                ItemStack is = Utilities.getPickedBlock(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = null;
                int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                try {
                    if (is == null) {
                        is = BlockUtils.createStackedBlock((Block)p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d), (int)md);
                    }
                }
                catch (Exception e) {
                    // empty catch block
                }
                sr = is == null ? new ScanResult(1, bi, md, null, "") : new ScanResult(1, Item.func_150891_b((Item)is.func_77973_b()), is.func_77960_j(), null, "");
                return sr;
            }
        }
        for (IScanEventHandler seh : ThaumcraftApi.scanEventhandlers) {
            ScanResult scan = seh.scanPhenomena(stack, p.field_70170_p, p);
            if (scan == null) continue;
            return scan;
        }
        return null;
    }
}

