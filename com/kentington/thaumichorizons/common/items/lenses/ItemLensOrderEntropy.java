/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.playerdata.PacketScannedToServer
 *  thaumcraft.common.lib.research.ScanManager
 *  thaumcraft.common.lib.utils.BlockUtils
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package com.kentington.thaumichorizons.common.items.lenses;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.text.DecimalFormat;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemLensOrderEntropy
extends Item
implements ILens {
    ScanResult startScan = null;
    int count = 250;
    boolean isNew = true;
    IIcon icon;

    public ItemLensOrderEntropy() {
        this.func_77637_a(ThaumicHorizons.tabTH);
    }

    @Override
    public String lensName() {
        return "LensOrderEntropy";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void handleRender(Minecraft mc, float partialTicks) {
        if (Minecraft.func_71410_x().field_71439_g.field_70170_p.field_72995_K) {
            double x = 0.0;
            double y = 0.0;
            double z = 0.0;
            EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
            this.isNew = false;
            String text = "?";
            ScanResult scan = this.doScan(new ItemStack(ConfigItems.itemThaumometer), p.field_70170_p, (EntityPlayer)p, this.count);
            if (scan != null) {
                AspectList aspects = null;
                if (!this.isNew) {
                    aspects = ScanManager.getScanAspects((ScanResult)scan, (World)p.field_70170_p);
                }
                ItemStack stack = null;
                if (scan.id > 0) {
                    stack = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
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
                if (scan.type == 2) {
                    if (!(scan.entity instanceof EntityItem)) {
                        text = scan.entity.func_70005_c_();
                        x = scan.entity.field_70165_t;
                        y = scan.entity.field_70163_u;
                        z = scan.entity.field_70161_v;
                    } else {
                        text = ((EntityItem)scan.entity).func_92059_d().func_82833_r();
                        x = scan.entity.field_70165_t;
                        y = scan.entity.field_70163_u;
                        z = scan.entity.field_70161_v;
                    }
                } else {
                    MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer((World)p.field_70170_p, (EntityPlayer)p, (boolean)true);
                    if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                        x = mop.field_72311_b;
                        y = mop.field_72312_c;
                        z = mop.field_72309_d;
                        TileEntity tile = p.field_70170_p.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                        if (scan.type == 3 && scan.phenomena.startsWith("NODE") && tile != null && tile instanceof INode) {
                            if (!this.isNew) {
                                aspects = ((INode)tile).getAspects();
                            }
                            text = p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == ConfigBlocks.blockAiry ? StatCollector.func_74838_a((String)(p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149739_a() + "." + p.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) + ".name")) : StatCollector.func_74838_a((String)p.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d).func_149732_F());
                            text = text + " (" + StatCollector.func_74838_a((String)("nodetype." + (Object)((Object)((INode)tile).getNodeType()) + ".name"));
                            if (((INode)tile).getNodeModifier() != null) {
                                text = text + ", " + StatCollector.func_74838_a((String)("nodemod." + (Object)((Object)((INode)tile).getNodeModifier()) + ".name"));
                            }
                            text = text + ")";
                        }
                    }
                }
                if (aspects != null || text.length() > 0) {
                    this.renderNameAndAspects(aspects, text);
                }
            }
            if (scan != null && scan.equals(this.startScan) && this.isNew) {
                --this.count;
                this.renderNameAndAspects(null, text);
                if (this.count <= 5) {
                    this.startScan = null;
                    if (ScanManager.completeScan((EntityPlayer)p, (ScanResult)scan, (String)"@")) {
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketScannedToServer(scan, (EntityPlayer)p, "@"));
                    }
                    this.count = 250;
                }
                if (this.count % 20 == 0) {
                    p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:cameraticks", 0.2f, 0.45f + p.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
                }
            } else {
                this.startScan = scan;
                this.count = 250;
            }
        }
    }

    private void renderNameAndAspects(AspectList aspects, String text) {
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), Minecraft.func_71410_x().field_71443_c, Minecraft.func_71410_x().field_71440_d);
        int w = sr.func_78326_a();
        int h = sr.func_78328_b();
        if (aspects != null && aspects.size() > 0) {
            int num = 0;
            int yOff = 0;
            int thisRow = 0;
            int size = 18;
            thisRow = aspects.size() - num < 5 ? aspects.size() - num : 5;
            for (Aspect asp : aspects.getAspects()) {
                yOff = num / 5 * size;
                this.drawAspectTag(asp, aspects.getAmount(asp), w / 2 - size * thisRow / 2 + size * (num % 5), h / 2 + 16 + yOff, w);
                if (++num % 5 != 0) continue;
                thisRow = aspects.size() - num < 5 ? aspects.size() - num : 5;
            }
        }
        if (text.length() > 0) {
            Minecraft.func_71410_x().field_71456_v.func_73731_b(Minecraft.func_71410_x().field_71466_p, text, w / 2 - Minecraft.func_71410_x().field_71466_p.func_78256_a(text) / 2, h / 2 - 16, 0xFFFFFF);
        }
    }

    private ScanResult doScan(ItemStack stack, World world, EntityPlayer p, int count) {
        Entity pointedEntity = EntityUtils.getPointedEntity((World)p.field_70170_p, (Entity)p, (double)0.5, (double)10.0, (float)0.0f, (boolean)true);
        if (pointedEntity != null) {
            ScanResult sr = new ScanResult(2, 0, 0, pointedEntity, "");
            if (ScanManager.isValidScanTarget((EntityPlayer)p, (ScanResult)sr, (String)"@")) {
                Thaumcraft.proxy.blockRunes(world, pointedEntity.field_70165_t - 0.5, pointedEntity.field_70163_u + (double)(pointedEntity.func_70047_e() / 2.0f), pointedEntity.field_70161_v - 0.5, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, (int)(pointedEntity.field_70131_O * 15.0f), 0.03f);
                this.isNew = true;
                return sr;
            }
            return sr;
        }
        MovingObjectPosition mop = this.func_77621_a(p.field_70170_p, p, true);
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            TileEntity tile = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (tile instanceof INode) {
                ScanResult sr = new ScanResult(3, 0, 0, null, "NODE" + ((INode)tile).getId());
                if (ScanManager.isValidScanTarget((EntityPlayer)p, (ScanResult)sr, (String)"@")) {
                    Thaumcraft.proxy.blockRunes(world, (double)mop.field_72311_b, (double)mop.field_72312_c + 0.25, (double)mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                    this.isNew = true;
                    return sr;
                }
                return sr;
            }
            Block bi = world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if (bi != Blocks.field_150350_a) {
                int md = bi.func_149643_k(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ItemStack is = bi.getPickBlock(mop, p.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                ScanResult sr = null;
                try {
                    if (is == null) {
                        is = BlockUtils.createStackedBlock((Block)bi, (int)md);
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
                if (ScanManager.isValidScanTarget((EntityPlayer)p, sr, (String)"@")) {
                    Thaumcraft.proxy.blockRunes(world, (double)mop.field_72311_b, (double)mop.field_72312_c + 0.25, (double)mop.field_72309_d, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 15, 0.03f);
                    this.isNew = true;
                    return sr;
                }
                return sr;
            }
        }
        for (IScanEventHandler seh : ThaumcraftApi.scanEventhandlers) {
            ScanResult scan = seh.scanPhenomena(stack, world, p);
            if (scan == null) continue;
            return scan;
        }
        return null;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.LensOrderEntropy";
    }

    public void drawAspectTag(Aspect aspect, int amount, int x, int y, int sw) {
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft mc = Minecraft.func_71410_x();
        Color color = new Color(aspect.getColor());
        mc.field_71446_o.func_110577_a(aspect.getImage());
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.5f);
        Tessellator var9 = Tessellator.field_78398_a;
        var9.func_78382_b();
        var9.func_78369_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 0.5f);
        var9.func_78374_a((double)x + 0.0, (double)y + 16.0, 0.0, 0.0, 1.0);
        var9.func_78374_a((double)x + 16.0, (double)y + 16.0, 0.0, 1.0, 1.0);
        var9.func_78374_a((double)x + 16.0, (double)y + 0.0, 0.0, 1.0, 0.0);
        var9.func_78374_a((double)x + 0.0, (double)y + 0.0, 0.0, 0.0, 0.0);
        var9.func_78381_a();
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        DecimalFormat myFormatter = new DecimalFormat("#######.##");
        String am = myFormatter.format(amount);
        mc.field_71466_p.func_78276_b(am, 24 + x * 2, 32 - mc.field_71466_p.field_78288_b + y * 2, 0xFFFFFF);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumichorizons:lensorderentropy");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @Override
    public void handleRemoval(EntityPlayer p) {
    }
}

