/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemThaumometerRenderer
implements IItemRenderer {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)SCANNER);
    private static final ResourceLocation SCANNER = new ResourceLocation("thaumcraft", "textures/models/scanner.obj");

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft mc = Minecraft.func_71410_x();
        int rve_id = 0;
        int player_id = 0;
        if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
            rve_id = mc.field_71451_h.func_145782_y();
            player_id = ((EntityLivingBase)data[1]).func_145782_y();
        }
        EntityClientPlayerMP playermp = mc.field_71439_g;
        float par1 = UtilsFX.getTimer((Minecraft)mc).field_74281_c;
        float var7 = 0.8f;
        EntityPlayerSP playersp = (EntityPlayerSP)playermp;
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && player_id == rve_id && mc.field_71474_y.field_74320_O == 0) {
            GL11.glTranslatef((float)1.0f, (float)0.75f, (float)-1.0f);
            GL11.glRotatef((float)135.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            float f3 = playersp.field_71164_i + (playersp.field_71155_g - playersp.field_71164_i) * par1;
            float f4 = playersp.field_71163_h + (playersp.field_71154_f - playersp.field_71163_h) * par1;
            GL11.glRotatef((float)((playermp.field_70125_A - f3) * 0.1f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)((playermp.field_70177_z - f4) * 0.1f), (float)0.0f, (float)1.0f, (float)0.0f);
            float var4 = playermp.field_70127_C + (playermp.field_70125_A - playermp.field_70127_C) * par1;
            float f1 = UtilsFX.getPrevEquippedProgress(mc.field_71460_t.field_78516_c) + (UtilsFX.getEquippedProgress(mc.field_71460_t.field_78516_c) - UtilsFX.getPrevEquippedProgress(mc.field_71460_t.field_78516_c)) * par1;
            GL11.glTranslatef((float)(-0.7f * var7), (float)(-(-0.65f * var7) + (1.0f - f1) * 1.5f), (float)(0.9f * var7));
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)(0.0f * var7), (float)(-0.9f * var7));
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)0.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glEnable((int)32826);
            GL11.glPushMatrix();
            GL11.glScalef((float)5.0f, (float)5.0f, (float)5.0f);
            mc.field_71446_o.func_110577_a(mc.field_71439_g.func_110306_p());
            for (int var9 = 0; var9 < 2; ++var9) {
                int var22 = var9 * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.0f, (float)-0.6f, (float)(1.1f * (float)var22));
                GL11.glRotatef((float)(-45 * var22), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)59.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(-65 * var22), (float)0.0f, (float)1.0f, (float)0.0f);
                Render var24 = RenderManager.field_78727_a.func_78713_a((Entity)mc.field_71439_g);
                RenderPlayer var26 = (RenderPlayer)var24;
                float var13 = 1.0f;
                GL11.glScalef((float)var13, (float)var13, (float)var13);
                var26.func_82441_a((EntityPlayer)mc.field_71439_g);
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)0.4f, (float)-0.4f, (float)0.0f);
            GL11.glEnable((int)32826);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        } else {
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
                GL11.glTranslatef((float)1.6f, (float)0.3f, (float)2.0f);
                GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            } else if (type == IItemRenderer.ItemRenderType.INVENTORY) {
                GL11.glRotatef((float)60.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotatef((float)248.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            }
        }
        UtilsFX.bindTexture("textures/models/scanner.png");
        this.model.renderAll();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.11f, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        UtilsFX.renderQuadCenteredFromTexture("textures/models/scanscreen.png", 2.5f, 1.0f, 1.0f, 1.0f, (int)(190.0f + MathHelper.func_76126_a((float)(playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2))) * 10.0f + 10.0f), 771, 1.0f);
        if (playermp instanceof EntityPlayer && type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON && player_id == rve_id && mc.field_71474_y.field_74320_O == 0) {
            RenderHelper.func_74518_a();
            int j = (int)(190.0f + MathHelper.func_76126_a((float)(playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2))) * 10.0f + 10.0f);
            int k = j % 65536;
            int l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            ScanResult scan = this.doScan(playermp.field_71071_by.func_70448_g(), (EntityPlayer)playermp);
            if (scan != null) {
                AspectList aspects = null;
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.01f);
                String text = "?";
                ItemStack stack = null;
                if (scan.id > 0) {
                    stack = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
                    if (ScanManager.hasBeenScanned((EntityPlayer)playermp, scan)) {
                        aspects = ScanManager.getScanAspects(scan, playermp.field_70170_p);
                    }
                }
                if (scan.type == 2) {
                    if (scan.entity instanceof EntityItem) {
                        stack = ((EntityItem)scan.entity).func_92059_d();
                    } else {
                        text = scan.entity.func_70005_c_();
                    }
                    if (ScanManager.hasBeenScanned((EntityPlayer)playermp, scan)) {
                        aspects = ScanManager.getScanAspects(scan, playermp.field_70170_p);
                    }
                }
                if (scan.type == 3 && scan.phenomena.startsWith("NODE") && ScanManager.hasBeenScanned((EntityPlayer)playermp, scan)) {
                    TileEntity tile;
                    MovingObjectPosition mop = null;
                    if (stack != null && stack.func_77973_b() != null) {
                        mop = EntityUtils.getMovingObjectPositionFromPlayer(playermp.field_70170_p, (EntityPlayer)playermp, true);
                    }
                    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && (tile = playermp.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)) != null && tile instanceof INode) {
                        aspects = ((INode)tile).getAspects();
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)1);
                        String t = StatCollector.func_74838_a((String)("nodetype." + (Object)((Object)((INode)tile).getNodeType()) + ".name"));
                        if (((INode)tile).getNodeModifier() != null) {
                            t = t + ", " + StatCollector.func_74838_a((String)("nodemod." + (Object)((Object)((INode)tile).getNodeModifier()) + ".name"));
                        }
                        int sw = mc.field_71466_p.func_78256_a(t);
                        float scale = 0.004f;
                        GL11.glScalef((float)scale, (float)scale, (float)scale);
                        mc.field_71466_p.func_78276_b(t, -sw / 2, -40, 15642134);
                        GL11.glDisable((int)3042);
                        GL11.glPopMatrix();
                    }
                }
                if (stack != null) {
                    if (stack.func_77973_b() != null) {
                        try {
                            text = stack.func_82833_r();
                        }
                        catch (Exception e) {}
                    } else if (stack.func_77973_b() != null) {
                        try {
                            text = stack.func_77973_b().func_77653_i(stack);
                        }
                        catch (Exception e) {
                            // empty catch block
                        }
                    }
                }
                if (aspects != null) {
                    int posX = 0;
                    int posY = 0;
                    int aa = aspects.size();
                    int baseX = Math.min(5, aa) * 8;
                    for (Aspect aspect : aspects.getAspectsSorted()) {
                        GL11.glPushMatrix();
                        GL11.glScalef((float)0.0075f, (float)0.0075f, (float)0.0075f);
                        j = (int)(190.0f + MathHelper.func_76126_a((float)(posX + playermp.field_70173_aa - playermp.field_70170_p.field_73012_v.nextInt(2))) * 10.0f + 10.0f);
                        k = j % 65536;
                        l = j / 65536;
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                        UtilsFX.drawTag(-baseX + posX * 16, -8 + posY * 16, aspect, (float)aspects.getAmount(aspect), 0, 0.01, 1, 1.0f, false);
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
                    RenderHelper.func_74518_a();
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
                    mc.field_71466_p.func_78276_b(text, -sw / 2, 0, 0xFFFFFF);
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                }
            }
            RenderHelper.func_74520_c();
        }
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private ScanResult doScan(ItemStack stack, EntityPlayer p) {
        if (stack == null || p == null) {
            return null;
        }
        Entity pointedEntity = EntityUtils.getPointedEntity(p.field_70170_p, (Entity)p, 0.5, 10.0, 0.0f, true);
        if (pointedEntity != null) {
            ScanResult sr = new ScanResult(2, 0, 0, pointedEntity, "");
            return sr;
        }
        MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer(p.field_70170_p, p, true);
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block bi = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            TileEntity tile = p.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (tile != null && tile instanceof INode) {
                int md = bi.func_149643_k(p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = new ScanResult(3, Block.func_149682_b((Block)bi), md, null, "NODE" + ((INode)tile).getId());
                return sr;
            }
            if (bi != Blocks.field_150350_a) {
                ItemStack is = bi.getPickBlock(mop, p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = null;
                int md = p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                try {
                    if (is == null) {
                        is = BlockUtils.createStackedBlock(bi, md);
                    }
                }
                catch (Exception e) {
                    // empty catch block
                }
                try {
                    sr = is == null ? new ScanResult(1, Block.func_149682_b((Block)bi), md, null, "") : new ScanResult(1, Item.func_150891_b((Item)is.func_77973_b()), is.func_77960_j(), null, "");
                }
                catch (Exception e) {
                    // empty catch block
                }
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

