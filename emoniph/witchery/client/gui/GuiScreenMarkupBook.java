/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.gui;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.client.gui.GuiButtonNavigate;
import com.emoniph.witchery.client.gui.GuiButtonUrl;
import com.emoniph.witchery.network.PacketSyncMarkupBook;
import com.emoniph.witchery.util.NBT;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class GuiScreenMarkupBook
extends GuiScreen {
    private static final ResourceLocation BACKGROUND = new ResourceLocation("witchery:textures/gui/bookSingle.png");
    private final EntityPlayer player;
    private final ItemStack itemstack;
    private final int meta;
    private int updateCount;
    private int bookImageWidth = 192;
    private int bookImageHeight = 192;
    private GuiButtonNavigate buttonTopPage;
    private GuiButtonNavigate buttonPreviousPage;
    private GuiButtonNavigate buttonNextPage;
    private final List<String> pageStack = new ArrayList<String>();
    final List<Element> elements = new ArrayList<Element>();
    private NextPage nextPage;

    public GuiScreenMarkupBook(EntityPlayer player, ItemStack itemstack) {
        this.player = player;
        this.itemstack = itemstack;
        this.meta = itemstack != null ? itemstack.func_77960_j() : 0;
        NBTTagList nbtPageStack = NBT.get(itemstack).func_150295_c("pageStack", 8);
        for (int i = 0; i < nbtPageStack.func_74745_c(); ++i) {
            this.pageStack.add(nbtPageStack.func_150307_f(i));
        }
    }

    public void func_73876_c() {
        super.func_73876_c();
        ++this.updateCount;
    }

    public void func_73866_w_() {
        Keyboard.enableRepeatEvents((boolean)true);
        this.constructPage();
    }

    private void constructPage() {
        String page = this.pageStack.size() > 0 ? this.pageStack.get(this.pageStack.size() - 1) : "toc";
        this.field_146292_n.clear();
        this.elements.clear();
        int b0 = 2;
        int mid = (this.field_146294_l - this.bookImageWidth) / 2;
        this.buttonTopPage = new GuiButtonNavigate(1, mid + 120, b0 + 16, 2, BACKGROUND);
        this.field_146292_n.add(this.buttonTopPage);
        this.buttonPreviousPage = new GuiButtonNavigate(2, mid + 34, b0 + 16, 1, BACKGROUND);
        this.field_146292_n.add(this.buttonPreviousPage);
        this.buttonNextPage = new GuiButtonNavigate(3, mid + 120, b0 + 16, 0, BACKGROUND);
        this.field_146292_n.add(this.buttonNextPage);
        String itemName = Item.field_150901_e.func_148750_c((Object)this.itemstack.func_77973_b());
        String untranslated = itemName + "." + page;
        StringBuilder markup = new StringBuilder(StatCollector.func_74838_a((String)untranslated));
        if (markup == null || markup.toString().equals(untranslated)) {
            return;
        }
        block4: for (int i = 0; i < markup.length(); ++i) {
            char c = markup.charAt(i);
            switch (c) {
                case '[': {
                    this.elements.add(new Element());
                    this.elements.get(this.elements.size() - 1).append(c);
                    continue block4;
                }
                case ']': {
                    String templatePathRoot;
                    String templatePath;
                    String template;
                    Element e = this.elements.get(this.elements.size() - 1);
                    if (e.tag.toString().equals("template") && !(template = StatCollector.func_74838_a((String)(templatePath = (templatePathRoot = Item.field_150901_e.func_148750_c((Object)this.itemstack.func_77973_b())) + "." + e.attribute))).isEmpty()) {
                        String[] parms = e.text.toString().split("\\s");
                        Object[] components = new Object[parms.length];
                        for (int j = 0; j < parms.length; ++j) {
                            String[] kv = parms[j].split("=");
                            if (kv.length != 2) continue;
                            if (kv[0].matches("stack\\|\\d+")) {
                                StringBuilder stackList = new StringBuilder();
                                for (String stack : kv[1].split(",")) {
                                    stackList.append(String.format("[stack=%s]", stack));
                                }
                                int index = Math.min(Integer.parseInt(kv[0].substring(kv[0].indexOf(124) + 1)), components.length - 1);
                                components[index] = stackList.toString();
                                continue;
                            }
                            if (!kv[0].matches("\\d+")) continue;
                            int index = Math.min(Integer.parseInt(kv[0]), components.length - 1);
                            components[index] = kv[1];
                        }
                        markup.insert(i + 1, String.format(template, components));
                        this.elements.remove(this.elements.size() - 1);
                    }
                    this.elements.add(new Element());
                    continue block4;
                }
                default: {
                    if (this.elements.size() == 0) {
                        this.elements.add(new Element());
                    }
                    this.elements.get(this.elements.size() - 1).append(c);
                }
            }
        }
        this.nextPage = null;
        for (Element element : this.elements) {
            NextPage defaultNextPage = element.constructButtons(this.field_146292_n, this.itemstack);
            if (defaultNextPage == null) continue;
            this.nextPage = defaultNextPage;
        }
        this.updateButtons();
    }

    public void func_146281_b() {
        Keyboard.enableRepeatEvents((boolean)false);
        this.sendBookToServer();
    }

    private void updateButtons() {
        this.buttonNextPage.field_146125_m = this.nextPage != null && this.nextPage.visible;
        this.buttonPreviousPage.field_146125_m = this.pageStack.size() > 0;
        this.buttonTopPage.field_146125_m = this.pageStack.size() > 0;
    }

    private void sendBookToServer() {
        if (this.player != null) {
            Witchery.packetPipeline.sendToServer(new PacketSyncMarkupBook(this.player.field_71071_by.field_70461_c, this.pageStack));
        }
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146124_l) {
            if (button.field_146127_k == 0) {
                this.field_146297_k.func_147108_a((GuiScreen)null);
            } else if (button.field_146127_k == 1) {
                if (this.pageStack.size() > 0) {
                    this.pageStack.remove(this.pageStack.size() - 1);
                    for (int i = this.pageStack.size() - 1; i >= 0 && !this.pageStack.get(i).startsWith("toc/"); --i) {
                        this.pageStack.remove(i);
                    }
                }
                this.constructPage();
            } else if (button.field_146127_k == 2) {
                if (this.pageStack.size() > 0) {
                    this.pageStack.remove(this.pageStack.size() - 1);
                    this.constructPage();
                }
            } else if (button.field_146127_k == 3) {
                this.pageStack.add(this.nextPage.pageName);
                this.constructPage();
            } else if (button.field_146127_k == 4) {
                this.pageStack.add(((GuiButtonUrl)button).nextPage);
                this.constructPage();
            }
            this.updateButtons();
        }
    }

    protected void func_73869_a(char par1, int par2) {
        super.func_73869_a(par1, par2);
    }

    public void func_73863_a(int mouseX, int mouseY, float par3) {
        int marginX;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(BACKGROUND);
        int k = (this.field_146294_l - this.bookImageWidth) / 2;
        int b0 = 2;
        this.func_73729_b(k, b0, 0, 0, this.bookImageWidth, this.bookImageHeight);
        int maxWidth = 116;
        this.buttonPreviousPage.field_146128_h = marginX = k + 36;
        this.buttonPreviousPage.field_146129_i = 16;
        this.buttonTopPage.field_146128_h = k + this.bookImageWidth / 2 - this.buttonTopPage.field_146120_f / 2 - 4;
        this.buttonTopPage.field_146129_i = 16;
        this.buttonNextPage.field_146128_h = k + this.bookImageWidth - this.buttonNextPage.field_146120_f - 44;
        this.buttonNextPage.field_146129_i = 16;
        int[] pos = new int[]{0, 32};
        RenderState state = new RenderState(this.field_146289_q, this.field_73735_i, mouseX, mouseY);
        for (Element element : this.elements) {
            element.draw(pos, marginX, 116, state);
        }
        super.func_73863_a(mouseX, mouseY, par3);
        if (state.tooltipStack != null) {
            this.func_146285_a(state.tooltipStack, mouseX, mouseY + 16);
        }
    }

    protected void func_146285_a(ItemStack stack, int x, int y) {
        int power;
        List list = stack.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
        if (list != null && (power = WitcheryBrewRegistry.INSTANCE.getAltarPower(stack)) >= 0) {
            list.add(String.format(Witchery.resource("witchery.brewing.ingredientpowercost"), power, MathHelper.func_76143_f((double)(1.4 * (double)power))));
        }
        for (int k = 0; k < list.size(); ++k) {
            if (k == 0) {
                list.set(k, stack.func_77953_t().field_77937_e + (String)list.get(k));
                continue;
            }
            list.set(k, EnumChatFormatting.GRAY + (String)list.get(k));
        }
        FontRenderer font = stack.func_77973_b().getFontRenderer(stack);
        this.drawHoveringText(list, x, y, font == null ? this.field_146289_q : font);
    }

    private static class RenderState {
        final FontRenderer font;
        final float zLevel;
        final int mouseX;
        final int mouseY;
        ItemStack tooltipStack;
        int lineheight;

        public RenderState(FontRenderer font, float zLevel, int mouseX, int mouseY) {
            this.font = font;
            this.zLevel = zLevel;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
            this.lineheight = font.field_78288_b;
        }

        public void newline(int[] pos) {
            pos[0] = 0;
            pos[1] = pos[1] + (this.lineheight + 1);
            this.lineheight = this.font.field_78288_b;
        }

        public void adjustLineHeight(int newHeight) {
            if (newHeight > this.lineheight) {
                this.lineheight = newHeight;
            }
        }
    }

    private static class Element {
        private final StringBuilder tag = new StringBuilder();
        private final StringBuilder attribute = new StringBuilder();
        private final StringBuilder text = new StringBuilder();
        private Capture capture = Capture.TEXT;
        private static final String FORMAT_CHAR = "\u00a7";
        private static final String FORMAT_CLEAR = "\u00a7r";
        private static final Hashtable<String, String> FORMATS = Element.getFormats();
        private GuiButtonUrl button;

        private Element() {
        }

        public String toString() {
            return String.format("tag=%s attribute=%s text=%s", this.tag, this.attribute, this.text);
        }

        public void append(char c) {
            switch (c) {
                case '[': {
                    this.capture = Capture.TAG;
                    break;
                }
                case '=': {
                    if (this.capture == Capture.TAG) {
                        this.capture = Capture.ATTRIB;
                        break;
                    }
                }
                case '\t': 
                case ' ': {
                    if (this.capture == Capture.TAG || this.capture == Capture.ATTRIB) {
                        this.capture = Capture.TEXT;
                        break;
                    }
                }
                default: {
                    if (this.capture == Capture.TAG) {
                        this.tag.append(c);
                        break;
                    }
                    if (this.capture == Capture.ATTRIB) {
                        this.attribute.append(c);
                        break;
                    }
                    this.text.append(c);
                }
            }
        }

        private static Hashtable<String, String> getFormats() {
            Hashtable<String, String> formats = new Hashtable<String, String>();
            formats.put("black", "\u00a70");
            formats.put("darkblue", "\u00a71");
            formats.put("darkgreen", "\u00a72");
            formats.put("darkaqua", "\u00a73");
            formats.put("darkred", "\u00a74");
            formats.put("darkpurple", "\u00a75");
            formats.put("darkyellow", "\u00a76");
            formats.put("gray", "\u00a77");
            formats.put("darkgray", "\u00a78");
            formats.put("blue", "\u00a79");
            formats.put("green", "\u00a7a");
            formats.put("aqua", "\u00a7b");
            formats.put("red", "\u00a7c");
            formats.put("purple", "\u00a7d");
            formats.put("yellow", "\u00a7e");
            formats.put("white", "\u00a7f");
            formats.put("b", "\u00a7l");
            formats.put("s", "\u00a7m");
            formats.put("u", "\u00a7n");
            formats.put("i", "\u00a7o");
            formats.put("h1", "\u00a73\u00a7o");
            return formats;
        }

        public NextPage constructButtons(List buttonList, ItemStack stack) {
            String tag = this.tag.toString();
            if (tag.equals("url")) {
                String attrib = this.attribute.toString();
                int pipeIndex = attrib.indexOf(124);
                if (pipeIndex != -1) {
                    attrib = attrib.substring(0, pipeIndex);
                }
                this.button = new GuiButtonUrl(4, 0, 0, attrib, this.text.toString());
                buttonList.add(this.button);
            } else if (tag.equals("next")) {
                return new NextPage(this.attribute.toString(), stack);
            }
            return null;
        }

        public void draw(int[] pos, int marginX, int maxWidth, RenderState state) {
            String[] words;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            String tag = this.tag.toString();
            if (tag.equals("br")) {
                state.newline(pos);
                return;
            }
            if (tag.equals("tab")) {
                int TAB_SPACE = 10;
                if (pos[0] + 10 > maxWidth) {
                    state.newline(pos);
                } else {
                    pos[0] = pos[0] + 10;
                }
                return;
            }
            if (tag.equals("img")) {
                int height;
                String[] parms = this.attribute.toString().split("\\|");
                int defaultWidth = 32;
                String url = parms.length > 0 ? parms[0] : "";
                String halign = parms.length > 1 ? parms[1] : "left";
                String valign = parms.length > 2 ? parms[2] : "top";
                int width = parms.length > 3 ? this.parseInt(parms[3], 32) : 32;
                int n = height = parms.length > 4 ? this.parseInt(parms[4], width) : width;
                if (!url.isEmpty()) {
                    ResourceLocation location = new ResourceLocation(url);
                    Minecraft.func_71410_x().func_110434_K().func_110577_a(location);
                    if (halign.equals("right")) {
                        pos[0] = maxWidth - width;
                    } else if (halign.equals("center")) {
                        pos[0] = maxWidth / 2 - width / 2;
                    }
                    if (pos[0] + width > maxWidth) {
                        state.newline(pos);
                    }
                    int y = pos[1];
                    if (state.lineheight > height) {
                        if (valign.equals("bottom")) {
                            y += state.lineheight - height;
                        } else if (valign.equals("middle")) {
                            y += state.lineheight / 2 - height / 2;
                        }
                    }
                    Element.drawTexturedQuadFit(pos[0] + marginX, y, width, height, state.zLevel);
                    pos[0] = pos[0] + width;
                    state.adjustLineHeight(height);
                }
                return;
            }
            if (tag.equals("url")) {
                String[] parms;
                this.button.field_146121_g = state.font.field_78288_b;
                this.button.field_146120_f = state.font.func_78256_a(this.text.toString());
                if (pos[0] + this.button.field_146120_f > maxWidth) {
                    state.newline(pos);
                }
                String url = (parms = this.attribute.toString().split("\\|")).length > 0 ? parms[0] : "";
                String valign = parms.length > 1 ? parms[1] : "top";
                this.button.field_146128_h = pos[0] + marginX;
                int y = pos[1];
                if (state.lineheight > this.button.field_146121_g) {
                    if (valign.equals("bottom")) {
                        y += state.lineheight - this.button.field_146121_g;
                    } else if (valign.equals("middle")) {
                        y += state.lineheight / 2 - this.button.field_146121_g / 2;
                    }
                }
                this.button.field_146129_i = y;
                pos[0] = pos[0] + this.button.field_146120_f;
                return;
            }
            if (tag.equals("locked")) {
                return;
            }
            if (tag.equals("stack")) {
                String valign;
                String[] parms = this.attribute.toString().split("\\|");
                String name = parms.length > 0 ? parms[0] : "";
                int damage = 0;
                int size = 1;
                int offset = 1;
                if (parms.length > offset && parms[offset].matches("\\d+")) {
                    damage = this.parseInt(parms[offset], 0);
                    ++offset;
                }
                if (parms.length > offset && parms[offset].matches("\\d+")) {
                    size = this.parseInt(parms[offset], 1);
                    ++offset;
                }
                String halign = parms.length > offset ? parms[offset] : "left";
                String string = valign = parms.length > ++offset ? parms[offset] : "top";
                if (!name.isEmpty()) {
                    String[] words2;
                    boolean empty = name.equals("empty");
                    Item item = !empty ? (Item)Item.field_150901_e.func_82594_a(name) : null;
                    ItemStack stack = !empty ? new ItemStack(item, size, damage) : null;
                    int width = 18;
                    int height = 18;
                    if (halign.equals("right")) {
                        pos[0] = maxWidth - width;
                    } else if (halign.equals("center")) {
                        pos[0] = maxWidth / 2 - width / 2;
                    }
                    if (pos[0] + width > maxWidth) {
                        state.newline(pos);
                    }
                    int y = pos[1];
                    if (state.lineheight > height) {
                        if (valign.equals("bottom")) {
                            y += state.lineheight - height;
                        } else if (valign.equals("middle")) {
                            y += state.lineheight / 2 - height / 2;
                        }
                    }
                    if (!empty) {
                        RenderItem render = new RenderItem();
                        GL11.glPushMatrix();
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        RenderHelper.func_74520_c();
                        GL11.glEnable((int)32826);
                        GL11.glEnable((int)2929);
                        int x = pos[0] + marginX;
                        render.func_82406_b(state.font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
                        render.func_77021_b(state.font, Minecraft.func_71410_x().func_110434_K(), stack, x, y);
                        RenderHelper.func_74518_a();
                        GL11.glPopMatrix();
                        if (state.mouseX >= x && state.mouseY >= y && state.mouseX <= x + width && state.mouseY <= y + height) {
                            state.tooltipStack = stack;
                        }
                        GL11.glDisable((int)2896);
                    }
                    pos[0] = pos[0] + width;
                    state.adjustLineHeight(height);
                    for (String word : words2 = this.text.toString().split("(?<=\\s)")) {
                        int textWidth = state.font.func_78256_a(word);
                        if (pos[0] + textWidth > maxWidth) {
                            state.newline(pos);
                            y = pos[1];
                        }
                        state.font.func_78276_b(word, marginX + pos[0], y + (height - state.font.field_78288_b) / 2, 0);
                        pos[0] = pos[0] + textWidth;
                    }
                }
                return;
            }
            if (tag.equals("next")) {
                return;
            }
            String preText = FORMATS.containsKey(tag) ? FORMATS.get(tag) : "";
            String postText = FORMATS.containsKey(tag) ? FORMAT_CLEAR : "";
            for (String word : words = this.text.toString().split("(?<=\\s)")) {
                int width = state.font.func_78256_a(word);
                if (pos[0] + width > maxWidth) {
                    state.newline(pos);
                }
                if (pos[0] == 0 && word.trim().isEmpty()) continue;
                state.font.func_78276_b(preText + word + postText, marginX + pos[0], pos[1], 0);
                pos[0] = pos[0] + width;
            }
            if (tag.equals("h1")) {
                state.adjustLineHeight((int)Math.ceil((float)state.lineheight * 1.5f));
                state.newline(pos);
            }
        }

        private int parseInt(String text, int defaultValue) {
            try {
                return Integer.parseInt(text);
            }
            catch (NumberFormatException ex) {
                return defaultValue;
            }
        }

        public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78374_a(x + 0.0, y + height, zLevel, 0.0, 1.0);
            tessellator.func_78374_a(x + width, y + height, zLevel, 1.0, 1.0);
            tessellator.func_78374_a(x + width, y + 0.0, zLevel, 1.0, 0.0);
            tessellator.func_78374_a(x + 0.0, y + 0.0, zLevel, 0.0, 0.0);
            tessellator.func_78381_a();
        }

        private static enum Capture {
            TAG,
            ATTRIB,
            TEXT;

        }
    }

    private static class NextPage {
        public final String pageName;
        public final boolean visible;

        public NextPage(String attrib, ItemStack book) {
            int pipeIndex = attrib.indexOf(124);
            if (pipeIndex != -1) {
                this.pageName = attrib.substring(0, pipeIndex);
                this.visible = book.func_77960_j() >= Integer.parseInt(attrib.substring(pipeIndex + 1));
            } else {
                this.pageName = attrib;
                this.visible = true;
            }
        }
    }
}

