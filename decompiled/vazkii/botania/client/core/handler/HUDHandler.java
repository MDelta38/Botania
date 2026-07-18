/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiIngame
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.profiler.Profiler
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Pre
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ICreativeManaProvider;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wiki.IWikiProvider;
import vazkii.botania.api.wiki.WikiHooks;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemCraftingHalo;
import vazkii.botania.common.item.ItemSextant;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;
import vazkii.botania.common.item.equipment.bauble.ItemMonocle;
import vazkii.botania.common.lib.LibObfuscation;

public final class HUDHandler {
    public static final ResourceLocation manaBar = new ResourceLocation("botania:textures/gui/manaHud.png");

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onDrawScreenPre(RenderGameOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        if (event.type == RenderGameOverlayEvent.ElementType.HEALTH) {
            profiler.func_76320_a("botania-hud");
            ItemStack amulet = PlayerHandler.getPlayerBaubles((EntityPlayer)mc.field_71439_g).func_70301_a(0);
            if (amulet != null && amulet.func_77973_b() == ModItems.flightTiara) {
                profiler.func_76320_a("flugelTiara");
                ItemFlightTiara.renderHUD(event.resolution, (EntityPlayer)mc.field_71439_g, amulet);
                profiler.func_76319_b();
            }
            profiler.func_76319_b();
        }
    }

    @SubscribeEvent
    public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        ItemStack equippedStack = mc.field_71439_g.func_71045_bC();
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            int invSize;
            profiler.func_76320_a("botania-hud");
            MovingObjectPosition pos = mc.field_71476_x;
            if (pos != null) {
                Block block = mc.field_71441_e.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                TileEntity tile = mc.field_71441_e.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                if (equippedStack != null) {
                    if (pos != null && equippedStack.func_77973_b() == ModItems.twigWand) {
                        this.renderWandModeDisplay(event.resolution);
                        if (block instanceof IWandHUD) {
                            profiler.func_76320_a("wandItem");
                            ((IWandHUD)block).renderHUD(mc, event.resolution, (World)mc.field_71441_e, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                            profiler.func_76319_b();
                        }
                    } else if (pos != null && equippedStack.func_77973_b() instanceof ILexicon) {
                        this.drawLexiconHUD(mc.field_71439_g.func_71045_bC(), block, pos, event.resolution);
                    }
                    if (tile != null && tile instanceof TilePool) {
                        this.renderPoolRecipeHUD(event.resolution, (TilePool)tile, equippedStack);
                    }
                }
                if (tile != null && tile instanceof TileAltar) {
                    ((TileAltar)tile).renderHUD(mc, event.resolution);
                } else if (tile != null && tile instanceof TileRuneAltar) {
                    ((TileRuneAltar)tile).renderHUD(mc, event.resolution);
                }
                if (tile != null && tile instanceof TileCorporeaCrystalCube) {
                    this.renderCrystalCubeHUD(event.resolution, (TileCorporeaCrystalCube)tile);
                }
            }
            TileCorporeaIndex.getInputHandler();
            if (!TileCorporeaIndex.InputHandler.getNearbyIndexes((EntityPlayer)mc.field_71439_g).isEmpty() && mc.field_71462_r != null && mc.field_71462_r instanceof GuiChat) {
                profiler.func_76320_a("nearIndex");
                this.renderNearIndexDisplay(event.resolution);
                profiler.func_76319_b();
            }
            if (MultiblockRenderHandler.currentMultiblock != null && MultiblockRenderHandler.anchor == null) {
                profiler.func_76320_a("multiblockRightClick");
                String s = StatCollector.func_74838_a((String)"botaniamisc.rightClickToAnchor");
                mc.field_71466_p.func_78261_a(s, event.resolution.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(s) / 2, event.resolution.func_78328_b() / 2 - 30, 0xFFFFFF);
                profiler.func_76319_b();
            }
            if (equippedStack != null && equippedStack.func_77973_b() instanceof ItemCraftingHalo) {
                profiler.func_76320_a("craftingHalo");
                ItemCraftingHalo.renderHUD(event.resolution, (EntityPlayer)mc.field_71439_g, equippedStack);
                profiler.func_76319_b();
            }
            if (equippedStack != null && equippedStack.func_77973_b() instanceof ItemSextant) {
                profiler.func_76320_a("sextant");
                ItemSextant.renderHUD(event.resolution, (EntityPlayer)mc.field_71439_g, equippedStack);
                profiler.func_76319_b();
            }
            if (Botania.proxy.isClientPlayerWearingMonocle()) {
                profiler.func_76320_a("monocle");
                ItemMonocle.renderHUD(event.resolution, (EntityPlayer)mc.field_71439_g);
                profiler.func_76319_b();
            }
            profiler.func_76320_a("manaBar");
            EntityClientPlayerMP player = mc.field_71439_g;
            int totalMana = 0;
            int totalMaxMana = 0;
            boolean anyRequest = false;
            boolean creative = false;
            InventoryPlayer mainInv = player.field_71071_by;
            InventoryBaubles baublesInv = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
            int size = invSize = mainInv.func_70302_i_();
            if (baublesInv != null) {
                size += baublesInv.func_70302_i_();
            }
            for (int i = 0; i < size; ++i) {
                boolean useBaubles = i >= invSize;
                Object inv = useBaubles ? baublesInv : mainInv;
                ItemStack stack = inv.func_70301_a(i - (useBaubles ? invSize : 0));
                if (stack == null) continue;
                Item item = stack.func_77973_b();
                if (item instanceof IManaUsingItem) {
                    boolean bl = anyRequest = anyRequest || ((IManaUsingItem)item).usesMana(stack);
                }
                if (item instanceof IManaItem && !((IManaItem)item).isNoExport(stack)) {
                    totalMana += ((IManaItem)item).getMana(stack);
                    totalMaxMana += ((IManaItem)item).getMaxMana(stack);
                }
                if (!(item instanceof ICreativeManaProvider) || !((ICreativeManaProvider)item).isCreative(stack)) continue;
                creative = true;
            }
            if (anyRequest) {
                this.renderManaInvBar(event.resolution, creative, totalMana, totalMaxMana);
            }
            profiler.func_76318_c("bossBar");
            BossBarHandler.render(event.resolution);
            profiler.func_76318_c("itemsRemaining");
            ItemsRemainingRenderHandler.render(event.resolution, event.partialTicks);
            profiler.func_76319_b();
            profiler.func_76319_b();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private void renderWandModeDisplay(ScaledResolution res) {
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        profiler.func_76320_a("wandMode");
        int ticks = (Integer)ReflectionHelper.getPrivateValue(GuiIngame.class, (Object)mc.field_71456_v, (String[])LibObfuscation.REMAINING_HIGHLIGHT_TICKS);
        if ((ticks -= 15) > 0) {
            int alpha = Math.min(255, (int)((float)ticks * 256.0f / 10.0f));
            int color = 52224 + (alpha << 24);
            String disp = StatCollector.func_74838_a((String)ItemTwigWand.getModeString(mc.field_71439_g.func_71045_bC()));
            int x = res.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(disp) / 2;
            int y = res.func_78328_b() - 70;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            mc.field_71466_p.func_78261_a(disp, x, y, color);
            GL11.glDisable((int)3042);
        }
        profiler.func_76319_b();
    }

    private void renderManaInvBar(ScaledResolution res, boolean hasCreative, int totalMana, int totalMaxMana) {
        Minecraft mc = Minecraft.func_71410_x();
        int width = 182;
        int x = res.func_78326_a() / 2 - width / 2;
        int y = res.func_78328_b() - ConfigHandler.manaBarHeight;
        if (!hasCreative) {
            width = totalMaxMana == 0 ? 0 : (int)((double)width * ((double)totalMana / (double)totalMaxMana));
        }
        if (width == 0) {
            if (totalMana > 0) {
                width = 1;
            } else {
                return;
            }
        }
        Color color = new Color(Color.HSBtoRGB(0.55f, (float)Math.min(1.0, Math.sin((double)System.currentTimeMillis() / 200.0) * 0.5 + 1.0), 1.0f));
        GL11.glColor4ub((byte)((byte)color.getRed()), (byte)((byte)color.getGreen()), (byte)((byte)color.getBlue()), (byte)((byte)(255 - color.getRed())));
        mc.field_71446_o.func_110577_a(manaBar);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        RenderHelper.drawTexturedModalRect(x, y, 0.0f, 0, 251, width, 5);
        GL11.glDisable((int)3042);
    }

    private void renderPoolRecipeHUD(ScaledResolution res, TilePool tile, ItemStack stack) {
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        profiler.func_76320_a("poolRecipe");
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (!recipe.matches(stack) || recipe.isAlchemy() && !tile.alchemy || recipe.isConjuration() && !tile.conjuration) continue;
            int x = res.func_78326_a() / 2 - 11;
            int y = res.func_78328_b() / 2 + 10;
            int u = tile.getCurrentMana() >= recipe.getManaToConsume() ? 0 : 22;
            int v = mc.field_71439_g.func_70005_c_().equals("haighyorkie") && mc.field_71439_g.func_70093_af() ? 23 : 8;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            mc.field_71446_o.func_110577_a(manaBar);
            RenderHelper.drawTexturedModalRect(x, y, 0.0f, u, v, 22, 15);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            net.minecraft.client.renderer.RenderHelper.func_74520_c();
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, x - 20, y);
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, recipe.getOutput(), x + 26, y);
            RenderItem.getInstance().func_77021_b(mc.field_71466_p, mc.field_71446_o, recipe.getOutput(), x + 26, y);
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3042);
            break;
        }
        profiler.func_76319_b();
    }

    private void renderCrystalCubeHUD(ScaledResolution res, TileCorporeaCrystalCube tile) {
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        profiler.func_76320_a("crystalCube");
        ItemStack target = tile.getRequestTarget();
        if (target != null) {
            String s1 = target.func_82833_r();
            String s2 = tile.getItemCount() + "x";
            int strlen = Math.max(mc.field_71466_p.func_78256_a(s1), mc.field_71466_p.func_78256_a(s2));
            int w = res.func_78326_a();
            int h = res.func_78328_b();
            Gui.func_73734_a((int)(w / 2 + 8), (int)(h / 2 - 12), (int)(w / 2 + strlen + 32), (int)(h / 2 + 10), (int)0x44000000);
            Gui.func_73734_a((int)(w / 2 + 6), (int)(h / 2 - 14), (int)(w / 2 + strlen + 34), (int)(h / 2 + 12), (int)0x44000000);
            mc.field_71466_p.func_78261_a(target.func_82833_r(), w / 2 + 30, h / 2 - 10, 0x6666FF);
            mc.field_71466_p.func_78261_a(tile.getItemCount() + "x", w / 2 + 30, h / 2, 0xFFFFFF);
            net.minecraft.client.renderer.RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, target, w / 2 + 10, h / 2 - 10);
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
        }
        profiler.func_76319_b();
    }

    private void drawLexiconHUD(ItemStack stack, Block block, MovingObjectPosition pos, ScaledResolution res) {
        LexiconEntry entry;
        Minecraft mc = Minecraft.func_71410_x();
        Profiler profiler = mc.field_71424_I;
        profiler.func_76320_a("lexicon");
        FontRenderer font = mc.field_71466_p;
        boolean draw = false;
        String drawStr = "";
        String secondLine = "";
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        int sx = res.func_78326_a() / 2 - 17;
        int sy = res.func_78328_b() / 2 + 2;
        if (block instanceof ILexiconable && (entry = ((ILexiconable)block).getEntry((World)mc.field_71441_e, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, (EntityPlayer)mc.field_71439_g, mc.field_71439_g.func_71045_bC())) != null) {
            if (!((ILexicon)stack.func_77973_b()).isKnowledgeUnlocked(stack, entry.getKnowledgeType())) {
                font = mc.field_71464_q;
            }
            drawStr = StatCollector.func_74838_a((String)entry.getUnlocalizedName());
            secondLine = EnumChatFormatting.ITALIC + StatCollector.func_74838_a((String)entry.getTagline());
            draw = true;
        }
        if (!draw && pos.field_72308_g == null) {
            IWikiProvider provider;
            String url;
            profiler.func_76320_a("wikiLookup");
            if (!(block.isAir((IBlockAccess)mc.field_71441_e, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) || block instanceof BlockLiquid || (url = (provider = WikiHooks.getWikiFor(block)).getWikiURL((World)mc.field_71441_e, pos)) == null || url.isEmpty())) {
                String name = provider.getBlockName((World)mc.field_71441_e, pos);
                String wikiName = provider.getWikiName((World)mc.field_71441_e, pos);
                drawStr = name + " @ " + EnumChatFormatting.AQUA + wikiName;
                draw = true;
            }
            profiler.func_76319_b();
        }
        if (draw) {
            if (!mc.field_71439_g.func_70093_af()) {
                drawStr = "?";
                secondLine = "";
                font = mc.field_71466_p;
            }
            RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, new ItemStack(ModItems.lexicon), sx, sy);
            GL11.glDisable((int)2896);
            font.func_78261_a(drawStr, sx + 10, sy + 8, -1);
            font.func_78261_a(secondLine, sx + 10, sy + 18, -5592406);
            if (!mc.field_71439_g.func_70093_af()) {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
                mc.field_71466_p.func_78261_a(EnumChatFormatting.BOLD + "Shift", (sx + 10) * 2 - 16, (sy + 8) * 2 + 20, -1);
                GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            }
        }
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        profiler.func_76319_b();
    }

    private void renderNearIndexDisplay(ScaledResolution res) {
        Minecraft mc = Minecraft.func_71410_x();
        String txt0 = StatCollector.func_74838_a((String)"botaniamisc.nearIndex0");
        String txt1 = EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"botaniamisc.nearIndex1");
        String txt2 = EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"botaniamisc.nearIndex2");
        int l = Math.max(mc.field_71466_p.func_78256_a(txt0), Math.max(mc.field_71466_p.func_78256_a(txt1), mc.field_71466_p.func_78256_a(txt2))) + 20;
        int x = res.func_78326_a() - l - 20;
        int y = res.func_78328_b() - 60;
        Gui.func_73734_a((int)(x - 6), (int)(y - 6), (int)(x + l + 6), (int)(y + 37), (int)0x44000000);
        Gui.func_73734_a((int)(x - 4), (int)(y - 4), (int)(x + l + 4), (int)(y + 35), (int)0x44000000);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, new ItemStack(ModBlocks.corporeaIndex), x, y + 10);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        mc.field_71466_p.func_78261_a(txt0, x + 20, y, 0xFFFFFF);
        mc.field_71466_p.func_78261_a(txt1, x + 20, y + 14, 0xFFFFFF);
        mc.field_71466_p.func_78261_a(txt2, x + 20, y + 24, 0xFFFFFF);
    }

    public static void drawSimpleManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft mc = Minecraft.func_71410_x();
        int x = res.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(name) / 2;
        int y = res.func_78328_b() / 2 + 10;
        mc.field_71466_p.func_78261_a(name, x, y, color);
        x = res.func_78326_a() / 2 - 51;
        HUDHandler.renderManaBar(x, y += 10, color, mana < 0 ? 0.5f : 1.0f, mana, maxMana);
        if (mana < 0) {
            String text = StatCollector.func_74838_a((String)"botaniamisc.statusUnknown");
            x = res.func_78326_a() / 2 - mc.field_71466_p.func_78256_a(text) / 2;
            mc.field_71466_p.func_78276_b(text, x, --y, color);
        }
        GL11.glDisable((int)3042);
    }

    public static void drawComplexManaHUD(int color, int mana, int maxMana, String name, ScaledResolution res, ItemStack bindDisplay, boolean properlyBound) {
        HUDHandler.drawSimpleManaHUD(color, mana, maxMana, name, res);
        Minecraft mc = Minecraft.func_71410_x();
        int x = res.func_78326_a() / 2 + 55;
        int y = res.func_78328_b() / 2 + 12;
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, bindDisplay, x, y);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glDisable((int)2929);
        if (properlyBound) {
            mc.field_71466_p.func_78261_a("\u2714", x + 10, y + 9, 19456);
            mc.field_71466_p.func_78261_a("\u2714", x + 10, y + 8, 774669);
        } else {
            mc.field_71466_p.func_78261_a("\u2718", x + 10, y + 9, 0x4C0000);
            mc.field_71466_p.func_78261_a("\u2718", x + 10, y + 8, 13764621);
        }
        GL11.glEnable((int)2929);
    }

    public static void renderManaBar(int x, int y, int color, float alpha, int mana, int maxMana) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
        mc.field_71446_o.func_110577_a(manaBar);
        RenderHelper.drawTexturedModalRect(x, y, 0.0f, 0, 0, 102, 5);
        int manaPercentage = Math.max(0, (int)((double)mana / (double)maxMana * 100.0));
        if (manaPercentage == 0 && mana > 0) {
            manaPercentage = 1;
        }
        RenderHelper.drawTexturedModalRect(x + 1, y + 1, 0.0f, 0, 5, 100, 3);
        Color color_ = new Color(color);
        GL11.glColor4ub((byte)((byte)color_.getRed()), (byte)((byte)color_.getGreen()), (byte)((byte)color_.getBlue()), (byte)((byte)(255.0f * alpha)));
        RenderHelper.drawTexturedModalRect(x + 1, y + 1, 0.0f, 0, 5, Math.min(100, manaPercentage), 3);
    }
}

