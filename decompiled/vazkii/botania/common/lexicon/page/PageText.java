/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.lexicon.page;

import com.google.common.base.Joiner;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PageText
extends LexiconPage {
    public PageText(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        int width = gui.getWidth() - 30;
        int x = gui.getLeft() + 16;
        int y = gui.getTop() + 2;
        PageText.renderText(x, y, width, gui.getHeight(), this.getUnlocalizedName());
    }

    public static void renderText(int x, int y, int width, int height, String unlocalizedText) {
        PageText.renderText(x, y, width, height, 10, unlocalizedText);
    }

    /*
     * WARNING - void declaration
     */
    @SideOnly(value=Side.CLIENT)
    public static void renderText(int x, int y, int width, int height, int paragraphSize, String unlocalizedText) {
        void var14_17;
        x += 2;
        y += 10;
        width -= 4;
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        boolean unicode = font.func_82883_a();
        font.func_78264_a(true);
        String text = StatCollector.func_74838_a((String)unlocalizedText).replaceAll("&", "\u00a7");
        String[] textEntries = text.split("<br>");
        ArrayList lines = new ArrayList();
        String controlCodes = "";
        String[] stringArray = textEntries;
        int n = stringArray.length;
        boolean bl = false;
        while (var14_17 < n) {
            String[] tokens;
            String s = stringArray[var14_17];
            ArrayList<String> words = new ArrayList<String>();
            String lineStr = "";
            for (String token : tokens = s.split(" ")) {
                String prev = lineStr;
                String spaced = token + " ";
                lineStr = lineStr + spaced;
                controlCodes = PageText.toControlCodes(PageText.getControlCodes(prev));
                if (font.func_78256_a(lineStr) > width) {
                    lines.add(words);
                    lineStr = controlCodes + spaced;
                    words = new ArrayList();
                }
                words.add(controlCodes + token);
            }
            if (!lineStr.isEmpty()) {
                lines.add(words);
            }
            lines.add(new ArrayList());
            ++var14_17;
        }
        int i = 0;
        for (List list : lines) {
            boolean justify;
            list.size();
            int xi = x;
            int spacing = 4;
            int wcount = list.size();
            int compensationSpaces = 0;
            boolean bl2 = justify = ConfigHandler.lexiconJustifiedText && wcount > 0 && lines.size() > i && !((List)lines.get(i + 1)).isEmpty();
            if (justify) {
                String s = Joiner.on((String)"").join((Iterable)list);
                int swidth = font.func_78256_a(s);
                int space = width - swidth;
                spacing = wcount == 1 ? 0 : space / (wcount - 1);
                compensationSpaces = wcount == 1 ? 0 : space % (wcount - 1);
            }
            for (String s : list) {
                int extra = 0;
                if (compensationSpaces > 0) {
                    --compensationSpaces;
                    ++extra;
                }
                font.func_78276_b(s, xi, y, 0);
                xi += font.func_78256_a(s) + spacing + extra;
            }
            y += list.isEmpty() ? paragraphSize : 10;
            ++i;
        }
        font.func_78264_a(unicode);
    }

    public static String getControlCodes(String s) {
        String controls = s.replaceAll("(?<!\u00a7)(.)", "");
        String wiped = controls.replaceAll(".*r", "r");
        return wiped;
    }

    public static String toControlCodes(String s) {
        return s.replaceAll(".", "\u00a7$0");
    }
}

