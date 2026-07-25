/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.FOVUpdateEvent
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  net.minecraftforge.client.event.TextureStitchEvent$Post
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.oredict.OreDictionary
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.nodes.IRevealer
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.client.gui.GuiResearchBrowser
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  travellersgear.api.RenderTravellersGearEvent
 */
package witchinggadgets.client;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.IGoggles;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import travellersgear.api.RenderTravellersGearEvent;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGResearch;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.WGKeyHandler;
import witchinggadgets.common.util.handler.InfusedGemHandler;
import witchinggadgets.common.util.network.message.MessagePrimordialGlove;

public class ClientEventHandler {
    boolean headgearDisabled = true;
    boolean armDisabled = true;
    boolean capeDisabled = true;
    boolean shouldResetSpecialRenders = false;
    public static boolean inGemSearch = false;
    static float spectralAlpha = 0.5f;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderPlayerSpecials(RenderPlayerEvent.Specials.Pre event) {
        if (Minecraft.func_71410_x().field_71439_g != null && !event.entityPlayer.equals((Object)Minecraft.func_71410_x().field_71439_g) && event.entityPlayer.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g) && EnchantmentHelper.func_77506_a((int)WGContent.enc_unveiling.field_77352_x, (ItemStack)Minecraft.func_71410_x().field_71439_g.func_71124_b(4)) > 0) {
            float x = (float)event.entityPlayer.field_70165_t + event.entityPlayer.func_70681_au().nextFloat() - 0.5f;
            float y = (float)event.entityPlayer.field_70163_u + 1.0f + event.entityPlayer.func_70681_au().nextFloat() - 0.5f;
            float z = (float)event.entityPlayer.field_70161_v + event.entityPlayer.func_70681_au().nextFloat() - 0.5f;
            Thaumcraft.proxy.sparkle(x, y, z, 1.0f, 0, 0.0f);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void getTooltip(ItemTooltipEvent event) {
        if (OreDictionary.itemMatches((ItemStack)new ItemStack(ConfigItems.itemResource, 1, 18), (ItemStack)event.itemStack, (boolean)true) && EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)event.itemStack) == 1 && EnchantmentHelper.func_77506_a((int)Enchantment.field_77335_o.field_77352_x, (ItemStack)event.itemStack) == 1) {
            event.toolTip.set(0, StatCollector.func_74838_a((String)"item.modifiedTC.luckyCoin"));
        }
        if (event.itemStack.func_77973_b().equals(Items.field_151144_bL)) {
            event.toolTip.add(StatCollector.func_74838_a((String)"wg.desc.infusionStabilizer"));
        } else if (Block.func_149634_a((Item)event.itemStack.func_77973_b()) != null) {
            for (Class<?> clazz : Block.func_149634_a((Item)event.itemStack.func_77973_b()).getClass().getInterfaces()) {
                if (!clazz.getCanonicalName().endsWith("IInfusionStabiliser")) continue;
                event.toolTip.add(StatCollector.func_74838_a((String)"wg.desc.infusionStabilizer"));
            }
        }
        if (event.entityPlayer != null && ThaumcraftApiHelper.isResearchComplete((String)event.entityPlayer.func_70005_c_(), (String)"GEMCUTTING") && InfusedGemHandler.isGem(event.itemStack) && GuiScreen.func_146272_n()) {
            if (InfusedGemHandler.getNaturalAffinities(event.itemStack) != null && InfusedGemHandler.getNaturalAffinities(event.itemStack).length > 0) {
                event.toolTip.add(EnumChatFormatting.DARK_GREEN + StatCollector.func_74838_a((String)"wg.desc.gemaffinity"));
                for (Class<?> clazz : InfusedGemHandler.getNaturalAffinities(event.itemStack)) {
                    if (clazz == null) continue;
                    event.toolTip.add(" " + EnumChatFormatting.DARK_GREEN + clazz.getName());
                }
            }
            if (InfusedGemHandler.getNaturalAversions(event.itemStack) != null && InfusedGemHandler.getNaturalAversions(event.itemStack).length > 0) {
                event.toolTip.add(EnumChatFormatting.RED + StatCollector.func_74838_a((String)"wg.desc.gemaversion"));
                for (Class<?> clazz : InfusedGemHandler.getNaturalAversions(event.itemStack)) {
                    if (clazz == null) continue;
                    event.toolTip.add(" " + EnumChatFormatting.RED + clazz.getName());
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void handleMouse(MouseEvent event) {
        if (event.button == 0 && inGemSearch) {
            inGemSearch = false;
            WGKeyHandler.gemLock = false;
            Minecraft mc = Minecraft.func_71410_x();
            EntityClientPlayerMP player = mc.field_71439_g;
            mc.func_71381_h();
            int mx = event.x - mc.field_71443_c / 2;
            int my = event.y - mc.field_71440_d / 2;
            double radius = Math.sqrt(mx * mx + my * my);
            double cx = (double)mx / radius;
            double angle = (double)(mx < 0 ? 180 : 0) + Math.abs((double)((mx < 0 ? -180 : 0) + (my < 0 ? 90 : 0)) + Math.abs((double)(my < 0 ? -90 : 0) + Math.abs(Math.toDegrees(Math.acos(cx)) - 90.0)));
            int sel = angle > 288.0 ? 0 : (angle < 72.0 ? 1 : 2 + (int)((288.0 - angle) / 72.0));
            WitchingGadgets.packetHandler.sendToServer((IMessage)new MessagePrimordialGlove((EntityPlayer)player, 0, sel));
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent.Pre event) {
        if (TileEntitySaunaStove.targetedPlayers.containsKey(Minecraft.func_71410_x().field_71439_g.func_145782_y()) && event.type == RenderGameOverlayEvent.ElementType.HELMET) {
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3008);
            ClientUtilities.bindTexture("witchinggadgets:textures/gui/steam_overlay.png");
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78374_a(0.0, (double)event.resolution.func_78328_b(), -90.0, 0.0, 1.0);
            tessellator.func_78374_a((double)event.resolution.func_78326_a(), (double)event.resolution.func_78328_b(), -90.0, 1.0, 1.0);
            tessellator.func_78374_a((double)event.resolution.func_78326_a(), 0.0, -90.0, 1.0, 0.0);
            tessellator.func_78374_a(0.0, 0.0, -90.0, 0.0, 0.0);
            tessellator.func_78381_a();
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2929);
            GL11.glEnable((int)3008);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT && WGKeyHandler.gemRadial > 0.0f) {
            Minecraft mc = Minecraft.func_71410_x();
            RenderItem ri = RenderItem.getInstance();
            if (WGKeyHandler.gemLock && (mc.field_71439_g.func_71045_bC() == null || !(mc.field_71439_g.func_71045_bC().func_77973_b() instanceof ItemPrimordialGlove))) {
                WGKeyHandler.gemLock = false;
            }
            GL11.glEnable((int)3042);
            double rad = 50.0f * WGKeyHandler.gemRadial;
            int x = event.resolution.func_78326_a() / 2;
            int y = event.resolution.func_78328_b() / 2;
            ClientUtilities.bindTexture("witchinggadgets:textures/gui/gauntletRadial0.png");
            GL11.glTranslatef((float)x, (float)y, (float)0.0f);
            GL11.glRotatef((float)(180.0f + 180.0f * WGKeyHandler.gemRadial), (float)0.0f, (float)0.0f, (float)1.0f);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78378_d(0xFFFFFF);
            tessellator.func_78374_a(-rad, rad, 0.0, 0.0, 1.0);
            tessellator.func_78374_a(rad, rad, 0.0, 1.0, 1.0);
            tessellator.func_78374_a(rad, -rad, 0.0, 1.0, 0.0);
            tessellator.func_78374_a(-rad, -rad, 0.0, 0.0, 0.0);
            tessellator.func_78381_a();
            ClientUtilities.bindTexture("witchinggadgets:textures/gui/gauntletRadial1.png");
            float mod = (float)((int)(System.currentTimeMillis() % 10000L)) / 10000.0f;
            GL11.glRotatef((float)(360.0f * mod), (float)0.0f, (float)0.0f, (float)1.0f);
            tessellator.func_78382_b();
            tessellator.func_78378_d(0xFFFFFF);
            tessellator.func_78374_a(-rad, rad, 0.0, 0.0, 1.0);
            tessellator.func_78374_a(rad, rad, 0.0, 1.0, 1.0);
            tessellator.func_78374_a(rad, -rad, 0.0, 1.0, 0.0);
            tessellator.func_78374_a(-rad, -rad, 0.0, 0.0, 0.0);
            tessellator.func_78381_a();
            GL11.glRotatef((float)(360.0f * mod), (float)0.0f, (float)0.0f, (float)-1.0f);
            if (mc.field_71439_g.func_71045_bC() != null && mc.field_71439_g.func_71045_bC().func_77973_b() instanceof ItemPrimordialGlove) {
                double reverseRadius;
                int my;
                ItemStack[] gems = ItemPrimordialGlove.getSetGems(mc.field_71439_g.func_71045_bC());
                int mx = Mouse.getX() - mc.field_71443_c / 2;
                double reverseAngle = (double)(mx < 0 ? 180 : 0) + Math.abs((double)((mx < 0 ? -180 : 0) + ((my = Mouse.getY() - mc.field_71440_d / 2) < 0 ? 90 : 0)) + Math.abs((double)(my < 0 ? -90 : 0) + Math.abs(Math.toDegrees(Math.acos((double)mx / (reverseRadius = Math.sqrt(mx * mx + my * my)))) - 90.0)));
                int sel = reverseAngle > 288.0 ? 0 : (reverseAngle < 72.0 ? 1 : 2 + (int)((288.0 - reverseAngle) / 72.0));
                GL11.glPushMatrix();
                for (int g = 0; g < gems.length; ++g) {
                    if (gems[g] == null) continue;
                    int ix = (int)((double)((float)(g == 0 ? -54 : (g == 1 ? 13 : (g == 3 ? -22 : (g == 2 ? -76 : 35)))) / 256.0f) * rad * 2.0);
                    int iy = (int)((double)((float)(g == 0 || g == 1 ? -64 : (g == 3 ? 36 : -6)) / 256.0f) * rad * 2.0);
                    ri.func_82406_b(mc.field_71466_p, mc.func_110434_K(), gems[g], ix, iy);
                    if (sel == g) continue;
                    GL11.glDepthFunc((int)514);
                    GL11.glDisable((int)2896);
                    GL11.glDepthMask((boolean)false);
                    ClientUtilities.bindTexture("witchinggadgets:textures/models/white.png");
                    GL11.glEnable((int)3042);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    for (int j1 = 0; j1 < 2; ++j1) {
                        tessellator.func_78382_b();
                        tessellator.func_78384_a(0, 64);
                        tessellator.func_78374_a((double)(ix - 2 + 0), (double)(iy - 2 + 20), 50.0, 0.0, 1.0);
                        tessellator.func_78374_a((double)(ix - 2 + 20), (double)(iy - 2 + 20), 50.0, 1.0, 1.0);
                        tessellator.func_78374_a((double)(ix - 2 + 20), (double)(iy - 2 + 0), 50.0, 1.0, 0.0);
                        tessellator.func_78374_a((double)(ix - 2 + 0), (double)(iy - 2 + 0), 50.0, 0.0, 0.0);
                        tessellator.func_78381_a();
                    }
                    GL11.glDepthMask((boolean)true);
                    GL11.glDisable((int)3042);
                    GL11.glDisable((int)3008);
                    GL11.glEnable((int)2896);
                    GL11.glDepthFunc((int)515);
                }
                GL11.glPopMatrix();
            }
            GL11.glRotatef((float)(180.0f + 180.0f * WGKeyHandler.gemRadial), (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glTranslatef((float)(-x), (float)(-y), (float)0.0f);
            GL11.glDisable((int)2896);
            if (WGKeyHandler.gemLock) {
                if (!inGemSearch || mc.field_71415_G) {
                    inGemSearch = true;
                    mc.func_71381_h();
                    mc.func_71364_i();
                }
            } else if (inGemSearch) {
                inGemSearch = false;
                mc.func_71381_h();
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderArmor(RenderPlayerEvent.SetArmorModel event) {
        int translucency = EnchantmentHelper.func_77506_a((int)WGContent.enc_invisibleGear.field_77352_x, (ItemStack)event.stack);
        if (event.stack != null && (translucency > 1 || translucency > 0 && event.entityPlayer.func_82150_aj())) {
            boolean unveiling;
            boolean bl = unveiling = EnchantmentHelper.func_77506_a((int)WGContent.enc_unveiling.field_77352_x, (ItemStack)Minecraft.func_71410_x().field_71439_g.func_71124_b(4)) > 0;
            if (event.entityPlayer.equals((Object)Minecraft.func_71410_x().field_71439_g) || !unveiling) {
                event.result = -2;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderTravellersGear(RenderTravellersGearEvent event) {
        int translucency = EnchantmentHelper.func_77506_a((int)WGContent.enc_invisibleGear.field_77352_x, (ItemStack)event.stack);
        if (event.stack != null && (translucency > 1 || translucency > 0 && event.entityPlayer.func_82150_aj())) {
            boolean unveiling;
            boolean bl = unveiling = EnchantmentHelper.func_77506_a((int)WGContent.enc_unveiling.field_77352_x, (ItemStack)Minecraft.func_71410_x().field_71439_g.func_71124_b(4)) > 0;
            if (event.entityPlayer.equals((Object)Minecraft.func_71410_x().field_71439_g) || !unveiling) {
                event.shouldRender = false;
            }
        }
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer)) {
            if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
            event.shouldRender = false;
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (Minecraft.func_71410_x().field_71439_g != null && event.gui instanceof GuiResearchBrowser) {
            ((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)"WITCHGADG")).background = ThaumcraftApiHelper.isResearchComplete((String)Minecraft.func_71410_x().field_71439_g.func_70005_c_(), (String)"WGFAKEELDRITCHMINOR") ? WGResearch.wgbackgrounds[1] : WGResearch.wgbackgrounds[0];
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void initializeIcons(TextureStitchEvent.Post event) {
        if (Minecraft.func_71410_x().field_71439_g != null) {
            ((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)"WITCHGADG")).background = ThaumcraftApiHelper.isResearchComplete((String)Minecraft.func_71410_x().field_71439_g.func_70005_c_(), (String)"WGELDRITCHBASE") ? WGResearch.wgbackgrounds[1] : WGResearch.wgbackgrounds[0];
        }
        ItemClusters.setupClusters();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void setSpecialRendersLiving(RenderLivingEvent.Pre event) {
        EntityPlayer pl;
        if (event.entity instanceof EntityPlayer && (pl = Minecraft.func_71410_x().field_71439_g.field_70170_p.func_72924_a(event.entity.func_70005_c_())) != null) {
            for (ItemStack cloak : Utilities.getActiveMagicalCloak(pl)) {
                boolean unveiling;
                if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
                GL11.glEnable((int)3042);
                boolean goggles = Minecraft.func_71410_x().field_71439_g.func_71124_b(4) != null && (Minecraft.func_71410_x().field_71439_g.func_71124_b(4).func_77973_b() instanceof IRevealer || Minecraft.func_71410_x().field_71439_g.func_71124_b(4).func_77973_b() instanceof IGoggles);
                boolean bl = unveiling = EnchantmentHelper.func_77506_a((int)WGContent.enc_unveiling.field_77352_x, (ItemStack)Minecraft.func_71410_x().field_71439_g.func_71124_b(4)) > 0;
                if (event.entity.equals((Object)Minecraft.func_71410_x().field_71439_g)) {
                    GL11.glColor4f((float)0.5f, (float)0.5f, (float)0.5f, (float)spectralAlpha);
                    continue;
                }
                if (unveiling) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
                    continue;
                }
                if (goggles) {
                    GL11.glColor4f((float)0.25f, (float)0.25f, (float)0.25f, (float)spectralAlpha);
                    continue;
                }
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.0f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void resetResetSpecialLiving(RenderLivingEvent.Post event) {
        EntityPlayer pl;
        if (event.entity instanceof EntityPlayer && (pl = Minecraft.func_71410_x().field_71439_g.field_70170_p.func_72924_a(event.entity.func_70005_c_())) != null) {
            for (ItemStack cloak : Utilities.getActiveMagicalCloak(pl)) {
                if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
                GL11.glDisable((int)3042);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderPlayerSpecials(RenderLivingEvent.Specials.Pre event) {
        EntityPlayer pl;
        if (event.entity instanceof EntityPlayer && (pl = Minecraft.func_71410_x().field_71439_g.field_70170_p.func_72924_a(event.entity.func_70005_c_())) != null) {
            for (ItemStack cloak : Utilities.getActiveMagicalCloak(pl)) {
                boolean unveiling;
                if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
                boolean bl = unveiling = EnchantmentHelper.func_77506_a((int)WGContent.enc_unveiling.field_77352_x, (ItemStack)Minecraft.func_71410_x().field_71439_g.func_71124_b(4)) > 0;
                if (unveiling) continue;
                event.setCanceled(true);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onFOVUpdate(FOVUpdateEvent event) {
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)event.entity);
        if (Utilities.isPlayerUsingBow((EntityPlayer)event.entity) && baubles != null && (OreDictionary.itemMatches((ItemStack)new ItemStack(WGContent.ItemMagicalBaubles, 1, 6), (ItemStack)baubles.func_70301_a(1), (boolean)true) || OreDictionary.itemMatches((ItemStack)new ItemStack(WGContent.ItemMagicalBaubles, 1, 6), (ItemStack)baubles.func_70301_a(2), (boolean)true)) && event.entity.func_70093_af()) {
            event.newfov = 0.25f;
        }
    }
}

