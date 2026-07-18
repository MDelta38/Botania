/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;
import vazkii.botania.common.lib.LibObfuscation;

public final class TooltipAdditionDisplayHandler {
    private static float lexiconLookupTime = 0.0f;

    public static void render() {
        Minecraft mc = Minecraft.func_71410_x();
        GuiScreen gui = mc.field_71462_r;
        if (gui != null && gui instanceof GuiContainer && mc.field_71439_g != null && mc.field_71439_g.field_71071_by.func_70445_o() == null) {
            GuiContainer container = (GuiContainer)gui;
            Slot slot = (Slot)ReflectionHelper.getPrivateValue(GuiContainer.class, (Object)container, (String[])LibObfuscation.THE_SLOT);
            if (slot != null && slot.func_75216_d()) {
                ItemStack stack = slot.func_75211_c();
                if (stack != null) {
                    List tooltip;
                    ScaledResolution res = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
                    FontRenderer font = mc.field_71466_p;
                    int mouseX = Mouse.getX() * res.func_78326_a() / mc.field_71443_c;
                    int mouseY = res.func_78328_b() - Mouse.getY() * res.func_78328_b() / mc.field_71440_d;
                    try {
                        tooltip = stack.func_82840_a((EntityPlayer)mc.field_71439_g, mc.field_71474_y.field_82882_x);
                    }
                    catch (Exception e) {
                        tooltip = new ArrayList();
                    }
                    int width = 0;
                    for (String s : tooltip) {
                        width = Math.max(width, font.func_78256_a(s) + 2);
                    }
                    int tooltipHeight = (tooltip.size() - 1) * 10 + 5;
                    int height = 3;
                    int offx = 11;
                    int offy = 17;
                    boolean offscreen = mouseX + width + 19 >= res.func_78326_a();
                    int fixY = res.func_78328_b() - (mouseY + tooltipHeight);
                    if (fixY < 0) {
                        offy -= fixY;
                    }
                    if (offscreen) {
                        offx = -13 - width;
                    }
                    if (stack.func_77973_b() instanceof ItemTerraPick) {
                        TooltipAdditionDisplayHandler.drawTerraPick(stack, mouseX, mouseY, offx, offy, width, height, font);
                    } else if (stack.func_77973_b() instanceof IManaTooltipDisplay) {
                        TooltipAdditionDisplayHandler.drawManaBar(stack, (IManaTooltipDisplay)stack.func_77973_b(), mouseX, mouseY, offx, offy, width, height);
                    }
                    LexiconRecipeMappings.EntryData data = LexiconRecipeMappings.getDataForStack(stack);
                    if (data != null) {
                        int lexSlot = -1;
                        ItemStack lexiconStack = null;
                        for (int i = 0; i < InventoryPlayer.func_70451_h(); ++i) {
                            ItemStack stackAt = mc.field_71439_g.field_71071_by.func_70301_a(i);
                            if (stackAt == null || !(stackAt.func_77973_b() instanceof ILexicon) || !((ILexicon)stackAt.func_77973_b()).isKnowledgeUnlocked(stackAt, data.entry.getKnowledgeType())) continue;
                            lexiconStack = stackAt;
                            lexSlot = i;
                            break;
                        }
                        if (lexSlot > -1) {
                            int x = mouseX + offx - 34;
                            int y = mouseY - offy;
                            GL11.glDisable((int)2929);
                            Gui.func_73734_a((int)(x - 4), (int)(y - 4), (int)(x + 20), (int)(y + 26), (int)0x44000000);
                            Gui.func_73734_a((int)(x - 6), (int)(y - 6), (int)(x + 22), (int)(y + 28), (int)0x44000000);
                            if (ConfigHandler.useShiftForQuickLookup ? GuiScreen.func_146272_n() : GuiScreen.func_146271_m()) {
                                int cx = x + 8;
                                int cy = y + 8;
                                float r = 12.0f;
                                float time = 20.0f;
                                float angles = (lexiconLookupTime += ClientTickHandler.delta) / time * 360.0f;
                                GL11.glDisable((int)2896);
                                GL11.glDisable((int)3553);
                                GL11.glShadeModel((int)7425);
                                GL11.glEnable((int)3042);
                                GL11.glBlendFunc((int)770, (int)771);
                                GL11.glBegin((int)6);
                                float a = 0.5f + 0.2f * ((float)Math.cos((double)((float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) / 10.0) * 0.5f + 0.5f);
                                GL11.glColor4f((float)0.0f, (float)0.5f, (float)0.0f, (float)a);
                                GL11.glVertex2i((int)cx, (int)cy);
                                GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                                for (float i = angles; i > 0.0f; i -= 1.0f) {
                                    double rad = (double)((i - 90.0f) / 180.0f) * Math.PI;
                                    GL11.glVertex2d((double)((double)cx + Math.cos(rad) * (double)r), (double)((double)cy + Math.sin(rad) * (double)r));
                                }
                                GL11.glVertex2i((int)cx, (int)cy);
                                GL11.glEnd();
                                GL11.glDisable((int)3042);
                                GL11.glEnable((int)3553);
                                GL11.glShadeModel((int)7424);
                                if (lexiconLookupTime >= time) {
                                    mc.field_71439_g.field_71071_by.field_70461_c = lexSlot;
                                    Botania.proxy.setEntryToOpen(data.entry);
                                    Botania.proxy.setLexiconStack(lexiconStack);
                                    mc.field_71439_g.func_71053_j();
                                    ItemLexicon.openBook((EntityPlayer)mc.field_71439_g, lexiconStack, (World)mc.field_71441_e, false);
                                }
                            } else {
                                lexiconLookupTime = 0.0f;
                            }
                            RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, new ItemStack(ModItems.lexicon), x, y);
                            GL11.glDisable((int)2896);
                            font.func_78261_a("?", x + 10, y + 8, -1);
                            GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
                            boolean mac = Minecraft.field_142025_a;
                            mc.field_71466_p.func_78261_a(EnumChatFormatting.BOLD + (ConfigHandler.useShiftForQuickLookup ? "Shift" : (mac ? "Cmd" : "Ctrl")), (x + 10) * 2 - 16, (y + 8) * 2 + 20, -1);
                            GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
                            GL11.glEnable((int)2929);
                        } else {
                            lexiconLookupTime = 0.0f;
                        }
                    } else {
                        lexiconLookupTime = 0.0f;
                    }
                } else {
                    lexiconLookupTime = 0.0f;
                }
            } else {
                lexiconLookupTime = 0.0f;
            }
        } else {
            lexiconLookupTime = 0.0f;
        }
    }

    private static void drawTerraPick(ItemStack stack, int mouseX, int mouseY, int offx, int offy, int width, int height, FontRenderer font) {
        int level = ItemTerraPick.getLevel(stack);
        int max = ItemTerraPick.LEVELS[Math.min(ItemTerraPick.LEVELS.length - 1, level + 1)];
        boolean ss = level >= ItemTerraPick.LEVELS.length - 1;
        int curr = ItemTerraPick.getMana_(stack);
        float percent = level == 0 ? 0.0f : (float)curr / (float)max;
        int rainbowWidth = Math.min(width - (ss ? 0 : 1), (int)((float)width * percent));
        float huePer = width == 0 ? 0.0f : 1.0f / (float)width;
        float hueOff = ((float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) * 0.01f;
        GL11.glDisable((int)2929);
        Gui.func_73734_a((int)(mouseX + offx - 1), (int)(mouseY - offy - height - 1), (int)(mouseX + offx + width + 1), (int)(mouseY - offy), (int)-16777216);
        for (int i = 0; i < rainbowWidth; ++i) {
            Gui.func_73734_a((int)(mouseX + offx + i), (int)(mouseY - offy - height), (int)(mouseX + offx + i + 1), (int)(mouseY - offy), (int)Color.HSBtoRGB(hueOff + huePer * (float)i, 1.0f, 1.0f));
        }
        Gui.func_73734_a((int)(mouseX + offx + rainbowWidth), (int)(mouseY - offy - height), (int)(mouseX + offx + width), (int)(mouseY - offy), (int)-11184811);
        String rank = StatCollector.func_74838_a((String)("botania.rank" + level)).replaceAll("&", "\u00a7");
        GL11.glPushAttrib((int)2896);
        GL11.glDisable((int)2896);
        font.func_78261_a(rank, mouseX + offx, mouseY - offy - 12, 0xFFFFFF);
        if (!ss) {
            rank = StatCollector.func_74838_a((String)("botania.rank" + (level + 1))).replaceAll("&", "\u00a7");
            font.func_78261_a(rank, mouseX + offx + width - font.func_78256_a(rank), mouseY - offy - 12, 0xFFFFFF);
        }
        GL11.glEnable((int)2929);
        GL11.glPopAttrib();
    }

    private static void drawManaBar(ItemStack stack, IManaTooltipDisplay display, int mouseX, int mouseY, int offx, int offy, int width, int height) {
        float fraction = display.getManaFractionForDisplay(stack);
        int manaBarWidth = (int)Math.ceil((float)width * fraction);
        GL11.glDisable((int)2929);
        Gui.func_73734_a((int)(mouseX + offx - 1), (int)(mouseY - offy - height - 1), (int)(mouseX + offx + width + 1), (int)(mouseY - offy), (int)-16777216);
        Gui.func_73734_a((int)(mouseX + offx), (int)(mouseY - offy - height), (int)(mouseX + offx + manaBarWidth), (int)(mouseY - offy), (int)Color.HSBtoRGB(0.528f, ((float)Math.sin((double)((float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) * 0.2) + 1.0f) * 0.3f + 0.4f, 1.0f));
        Gui.func_73734_a((int)(mouseX + offx + manaBarWidth), (int)(mouseY - offy - height), (int)(mouseX + offx + width), (int)(mouseY - offy), (int)-11184811);
    }
}

