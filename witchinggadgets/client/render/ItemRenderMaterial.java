/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.lib.research.ScanManager
 */
package witchinggadgets.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ScanManager;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.util.Utilities;

public class ItemRenderMaterial
implements IItemRenderer {
    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        int meta = stack.func_77960_j();
        if (meta != 9 && meta != 10) {
            return false;
        }
        switch (type) {
            case ENTITY: {
                return true;
            }
            case EQUIPPED: {
                return false;
            }
            case EQUIPPED_FIRST_PERSON: {
                return true;
            }
            case FIRST_PERSON_MAP: {
                return false;
            }
            case INVENTORY: {
                return false;
            }
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        if (stack == null) {
            return;
        }
        String itemTexture = "witchingGadgets:textures/items/mat_" + (stack.func_77960_j() == 9 ? "photoPlate.png" : "developedPhoto.png");
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP entityclientplayermp = mc.field_71439_g;
        ItemStack scanStack = null;
        Entity scanEntity = null;
        int aspectColour = 0xFFFFFF;
        if (stack.func_77978_p() != null) {
            ScanResult scan = Utilities.readScanResultFromNBT(stack.func_77978_p().func_74775_l("scanResult"), entityclientplayermp.field_70170_p);
            AspectList scanAspects = new AspectList();
            if (scan != null) {
                switch (scan.type) {
                    case 1: {
                        scanStack = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
                        scanAspects = ThaumcraftCraftingManager.getObjectTags((ItemStack)scanStack);
                        scanAspects = ThaumcraftCraftingManager.getBonusTags((ItemStack)scanStack, (AspectList)scanAspects);
                        break;
                    }
                    case 2: {
                        if (scan.entity instanceof EntityItem) {
                            scanStack = new ItemStack(((EntityItem)scan.entity).func_92059_d().func_77973_b(), 1, ((EntityItem)scan.entity).func_92059_d().func_77960_j());
                            scanAspects = ThaumcraftCraftingManager.getObjectTags((ItemStack)scanStack);
                            scanAspects = ThaumcraftCraftingManager.getBonusTags((ItemStack)scanStack, (AspectList)scanAspects);
                            break;
                        }
                        scanEntity = scan.entity;
                        scanAspects = ScanManager.generateEntityAspects((Entity)scan.entity);
                        break;
                    }
                    case 3: {
                        if (!scan.phenomena.startsWith("NODE")) break;
                        scanAspects = Utilities.generateNodeAspects(entityclientplayermp.field_70170_p, scan.phenomena.replace("NODE", ""));
                    }
                }
            }
            if (scanAspects != null) {
                int asp = entityclientplayermp.field_70173_aa % (68 * scanAspects.size()) / 68;
                aspectColour = scanAspects.getAspectsSorted()[asp].getColor();
            }
        }
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            float f11;
            float timer = UtilsFX.getTimer((Minecraft)mc).field_74281_c;
            float f1 = UtilsFX.getPrevEquippedProgress((ItemRenderer)mc.field_71460_t.field_78516_c) + (UtilsFX.getEquippedProgress((ItemRenderer)mc.field_71460_t.field_78516_c) - UtilsFX.getPrevEquippedProgress((ItemRenderer)mc.field_71460_t.field_78516_c)) * timer;
            float f2 = entityclientplayermp.field_70127_C + (entityclientplayermp.field_70125_A - entityclientplayermp.field_70127_C) * timer;
            GL11.glEnable((int)32826);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
            GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
            GL11.glTranslatef((float)-0.35f, (float)0.875f, (float)1.0f);
            GL11.glDisable((int)32826);
            float f12 = 0.8f;
            int i = mc.field_71441_e.func_72802_i(MathHelper.func_76128_c((double)entityclientplayermp.field_70165_t), MathHelper.func_76128_c((double)entityclientplayermp.field_70163_u), MathHelper.func_76128_c((double)entityclientplayermp.field_70161_v), 0);
            int k = i / 65536;
            f12 = 0.8f;
            float f7 = entityclientplayermp.func_70678_g(timer);
            float f8 = MathHelper.func_76126_a((float)(f7 * (float)Math.PI));
            float f6 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f7) * (float)Math.PI));
            GL11.glTranslatef((float)(-f6 * 0.4f), (float)(MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f7) * (float)Math.PI * 2.0f)) * 0.2f), (float)(-f8 * 0.2f));
            f7 = 1.0f - f2 / 45.0f + 0.1f;
            if (f7 < 0.0f) {
                f7 = 0.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            f7 = -MathHelper.func_76134_b((float)(f7 * (float)Math.PI)) * 0.5f + 0.5f;
            GL11.glTranslatef((float)0.0f, (float)(0.0f * f12 - (1.0f - f1) * 1.2f - f7 * 0.5f + 0.04f), (float)(-0.9f * f12));
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(f7 * -85.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glEnable((int)32826);
            ClientUtilities.bindTexture(entityclientplayermp.func_110306_p().func_110624_b() + ":" + entityclientplayermp.func_110306_p().func_110623_a());
            for (k = 0; k < 2; ++k) {
                int l = k * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.0f, (float)-0.6f, (float)(1.1f * (float)l));
                GL11.glRotatef((float)(-45 * l), (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)59.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)(-65 * l), (float)0.0f, (float)1.0f, (float)0.0f);
                Render render = RenderManager.field_78727_a.func_78713_a((Entity)mc.field_71439_g);
                RenderPlayer renderplayer = (RenderPlayer)render;
                f11 = 1.0f;
                GL11.glScalef((float)f11, (float)f11, (float)f11);
                renderplayer.func_82441_a((EntityPlayer)mc.field_71439_g);
                GL11.glPopMatrix();
            }
            f8 = entityclientplayermp.func_70678_g(timer);
            f6 = MathHelper.func_76126_a((float)(f8 * f8 * (float)Math.PI));
            float f9 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f8) * (float)Math.PI));
            GL11.glRotatef((float)(-f6 * 20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f9 * 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(-f9 * 80.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            float f10 = 0.38f;
            GL11.glScalef((float)f10, (float)f10, (float)f10);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-1.0f, (float)-1.0f, (float)0.0f);
            f11 = 0.015625f;
            GL11.glScalef((float)f11, (float)f11, (float)f11);
            ClientUtilities.bindTexture(itemTexture);
            Tessellator tessellator = Tessellator.field_78398_a;
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            tessellator.func_78382_b();
            int b0 = 7;
            IIcon ic = stack.func_77954_c();
            ClientUtilities.bindTexture(mc.func_110434_K().func_130087_a(stack.func_94608_d()).func_110624_b() + ":" + mc.func_110434_K().func_130087_a(stack.func_94608_d()).func_110623_a());
            tessellator.func_78374_a((double)(0 - b0), (double)(128 + b0), 0.0, (double)ic.func_94209_e(), (double)ic.func_94210_h());
            tessellator.func_78374_a((double)(128 + b0), (double)(128 + b0), 0.0, (double)ic.func_94212_f(), (double)ic.func_94210_h());
            tessellator.func_78374_a((double)(128 + b0), (double)(0 - b0), 0.0, (double)ic.func_94212_f(), (double)ic.func_94206_g());
            tessellator.func_78374_a((double)(0 - b0), (double)(0 - b0), 0.0, (double)ic.func_94209_e(), (double)ic.func_94206_g());
            tessellator.func_78381_a();
        } else {
            IIcon ic = stack.func_77954_c();
            float f = ic.func_94209_e();
            float f1 = ic.func_94212_f();
            float f2 = ic.func_94206_g();
            float f3 = ic.func_94210_h();
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)ic.func_94211_a(), (int)ic.func_94216_b(), (float)0.0625f);
        }
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glColor4f((float)((float)(aspectColour >> 16 & 0xFF) / 255.0f), (float)((float)(aspectColour >> 8 & 0xFF) / 255.0f), (float)((float)(aspectColour & 0xFF) / 255.0f), (float)((float)(aspectColour >> 32 & 0xFF) / 255.0f));
            IIcon ic = stack.func_77973_b().func_77618_c(stack.func_77960_j(), 99);
            ClientUtilities.bindTexture(mc.func_110434_K().func_130087_a(stack.func_94608_d()).func_110624_b() + ":" + mc.func_110434_K().func_130087_a(stack.func_94608_d()).func_110623_a());
            int b0 = 7;
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78374_a((double)(0 - b0), (double)(128 + b0), 0.0, (double)ic.func_94209_e(), (double)ic.func_94210_h());
            tessellator.func_78374_a((double)(128 + b0), (double)(128 + b0), 0.0, (double)ic.func_94212_f(), (double)ic.func_94210_h());
            tessellator.func_78374_a((double)(128 + b0), (double)(0 - b0), 0.0, (double)ic.func_94212_f(), (double)ic.func_94206_g());
            tessellator.func_78374_a((double)(0 - b0), (double)(0 - b0), 0.0, (double)ic.func_94209_e(), (double)ic.func_94206_g());
            tessellator.func_78381_a();
            GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
            GL11.glDisable((int)3042);
        }
        if (scanStack != null) {
            ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glScalef((float)20.0f, (float)20.0f, (float)20.0f);
            } else {
                GL11.glScalef((float)0.25f, (float)-0.25f, (float)-0.25f);
                GL11.glTranslatef((float)-1.25f, (float)-4.5f, (float)1.625f);
                GL11.glRotatef((float)-25.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (scanStack.func_77973_b() instanceof ItemBlock) {
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)65.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)1.5f, (float)-2.4f, (float)-3.25f);
            } else if (MinecraftForgeClient.getItemRenderer((ItemStack)scanStack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.INVENTORY) == null) {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)-35.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)3.625f, (float)-1.5f, (float)-1.75f);
                GL11.glScalef((float)-1.0f, (float)1.0f, (float)-1.0f);
            } else {
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)30.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)3.0f, (float)-2.5f, (float)-0.5f);
            }
            for (int p = 0; p < scanStack.func_77973_b().getRenderPasses(scanStack.func_77960_j()); ++p) {
                GL11.glPushMatrix();
                int col = scanStack.func_77973_b().func_82790_a(scanStack, p);
                GL11.glColor4f((float)((float)(col >> 16 & 0xFF) / 255.0f), (float)((float)(col >> 8 & 0xFF) / 255.0f), (float)((float)(col & 0xFF) / 255.0f), (float)((float)(col >> 32 & 0xFF) / 255.0f));
                ir.renderItem((EntityLivingBase)entityclientplayermp, scanStack, p, IItemRenderer.ItemRenderType.INVENTORY);
                GL11.glPopMatrix();
            }
        } else if (scanEntity != null) {
            IBossDisplayData boss;
            Render entRender = RenderManager.field_78727_a.func_78713_a(scanEntity);
            float scaleMod = Math.max(scanEntity.field_70131_O / 1.8f, scanEntity.field_70130_N / 1.5f);
            if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)65.0f, (float)-85.0f, (float)2.0f);
                GL11.glScalef((float)(20.0f / scaleMod), (float)(20.0f / scaleMod), (float)0.1f);
            } else {
                GL11.glTranslatef((float)0.5f, (float)0.25f, (float)0.05f);
                GL11.glScalef((float)(0.25f / scaleMod), (float)(0.25f / scaleMod), (float)(0.1f / scaleMod));
            }
            if (scanEntity instanceof EntityDragon) {
                GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)2.0f, (float)0.0f);
            }
            entRender.func_76986_a(scanEntity, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            if (scanEntity instanceof IBossDisplayData && BossStatus.field_82827_c.equals((boss = (IBossDisplayData)scanEntity).func_145748_c_().func_150254_d()) && BossStatus.field_82828_a == boss.func_110143_aJ() / boss.func_110138_aP() && BossStatus.field_82826_b > 0) {
                BossStatus.field_82826_b = 0;
            }
        }
        GL11.glPopMatrix();
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
    }
}

