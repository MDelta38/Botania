/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon;

import cpw.mods.fml.common.eventhandler.Event;
import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.BotaniaTutorialStartEvent;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.gui.GuiAchievementsHacky;
import vazkii.botania.client.gui.GuiBotaniaConfig;
import vazkii.botania.client.gui.lexicon.GuiLexiconChallengesList;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexiconHistory;
import vazkii.botania.client.gui.lexicon.GuiLexiconIndex;
import vazkii.botania.client.gui.lexicon.IParented;
import vazkii.botania.client.gui.lexicon.button.GuiButtonAchievement;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBookmark;
import vazkii.botania.client.gui.lexicon.button.GuiButtonCategory;
import vazkii.botania.client.gui.lexicon.button.GuiButtonChallengeInfo;
import vazkii.botania.client.gui.lexicon.button.GuiButtonChallenges;
import vazkii.botania.client.gui.lexicon.button.GuiButtonDoot;
import vazkii.botania.client.gui.lexicon.button.GuiButtonHistory;
import vazkii.botania.client.gui.lexicon.button.GuiButtonInvisible;
import vazkii.botania.client.gui.lexicon.button.GuiButtonNotes;
import vazkii.botania.client.gui.lexicon.button.GuiButtonOptions;
import vazkii.botania.client.gui.lexicon.button.GuiButtonUpdateWarning;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemLexicon;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lexicon.page.PageText;

public class GuiLexicon
extends GuiScreen {
    public static GuiLexicon currentOpenLexicon = new GuiLexicon();
    public static ItemStack stackUsed;
    public static HashMap<String, String> notes;
    private static final String TAG_TYPE = "type";
    private static final int[] KONAMI_CODE;
    public static final int BOOKMARK_START = 1337;
    public static final int NOTES_BUTTON_ID = 1336;
    public static final int MAX_BOOKMARK_COUNT = 8;
    public static List<GuiLexicon> bookmarks;
    public static List<String> bookmarkKeys;
    boolean bookmarksNeedPopulation = false;
    public static Queue<LexiconEntry> tutorial;
    public static final ResourceLocation texture;
    public static final ResourceLocation textureToff;
    public float lastTime = 0.0f;
    public float partialTicks = 0.0f;
    public float timeDelta = 0.0f;
    private static final int TUTORIAL_ARROW_WIDTH = 10;
    private static final int TUTORIAL_ARROW_HEIGHT = 12;
    boolean hasTutorialArrow;
    int tutorialArrowX;
    int tutorialArrowY;
    int konamiIndex;
    private static final int NOTE_TWEEN_TIME = 5;
    public static boolean notesEnabled;
    static int notesMoveTime;
    public String note = "";
    public String categoryHighlight = "";
    List<LexiconCategory> allCategories;
    String title;
    int guiWidth = 146;
    int guiHeight = 180;
    int left;
    int top;

    public final void func_73866_w_() {
        String key;
        super.func_73866_w_();
        if (PersistentVariableHelper.firstLoad) {
            PersistentVariableHelper.firstLoad = false;
            PersistentVariableHelper.saveSafe();
        }
        if (notes.containsKey(key = this.getNotesKey())) {
            this.note = notes.get(key);
        }
        this.onInitGui();
        this.putTutorialArrow();
    }

    public void onInitGui() {
        this.allCategories = new ArrayList<LexiconCategory>(BotaniaAPI.getAllCategories());
        Collections.sort(this.allCategories);
        this.lastTime = ClientTickHandler.ticksInGame;
        this.title = stackUsed.func_82833_r();
        currentOpenLexicon = this;
        this.left = this.field_146294_l / 2 - this.guiWidth / 2;
        this.top = this.field_146295_m / 2 - this.guiHeight / 2;
        this.field_146292_n.clear();
        if (this.isIndex()) {
            int x = 18;
            for (int i = 0; i < 12; ++i) {
                int y = 16 + i * 12;
                this.field_146292_n.add(new GuiButtonInvisible((GuiLexiconIndex)this, i, this.left + x, this.top + y, 110, 10, ""));
            }
            this.populateIndex();
        } else if (this.isCategoryIndex()) {
            int categories = this.allCategories.size();
            for (int i = 0; i < categories + 1; ++i) {
                LexiconCategory category = null;
                category = i >= categories ? null : this.allCategories.get(i);
                int perline = 5;
                int x = i % perline;
                int y = i / perline;
                int size = 22;
                GuiButtonCategory button = new GuiButtonCategory(i, this.left + 18 + x * size, this.top + 50 + y * size, this, category);
                this.field_146292_n.add(button);
            }
        }
        this.populateBookmarks();
        if (this.isMainPage()) {
            this.field_146292_n.add(new GuiButtonOptions(-1, this.left - 6, this.top + this.guiHeight - 54));
            this.field_146292_n.add(new GuiButtonAchievement(-2, this.left - 6, this.top + this.guiHeight - 40));
            this.field_146292_n.add(new GuiButtonChallenges(-3, this.left - 6, this.top + this.guiHeight - 25));
            GuiButtonUpdateWarning button = new GuiButtonUpdateWarning(-4, this.left - 6, this.top + this.guiHeight - 70);
            this.field_146292_n.add(button);
            if (PersistentVariableHelper.lastBotaniaVersion.equals("r1.8-249")) {
                button.field_146124_l = false;
                button.field_146125_m = false;
            }
            if (Calendar.getInstance().get(2) == 10 && Calendar.getInstance().get(5) == 22) {
                this.field_146292_n.add(new GuiButtonDoot(-99, this.left + 100, this.top + 12));
            }
        }
        this.field_146292_n.add(new GuiButtonNotes(this, 1336, this.left - 4, this.top - 4));
    }

    public void func_73876_c() {
        if (notesEnabled && notesMoveTime < 5) {
            ++notesMoveTime;
        } else if (!notesEnabled && notesMoveTime > 0) {
            --notesMoveTime;
        }
    }

    public void func_73863_a(int par1, int par2, float par3) {
        String subtitle;
        float time = (float)ClientTickHandler.ticksInGame + par3;
        this.timeDelta = time - this.lastTime;
        this.lastTime = time;
        this.partialTicks = par3;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        this.drawNotes(par3);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(texture);
        this.func_73729_b(this.left, this.top, 0, 0, this.guiWidth, this.guiHeight);
        if (ClientProxy.jingleTheBells) {
            this.func_73729_b(this.left + 3, this.top + 1, 0, 212, 138, 6);
        }
        if ((subtitle = this.getSubtitle()) != null) {
            this.drawBookmark(this.left + this.guiWidth / 2, this.top - this.getTitleHeight() + 10, subtitle, true, 191);
        }
        this.drawBookmark(this.left + this.guiWidth / 2, this.top - this.getTitleHeight(), this.getTitle(), true);
        if (this.isMainPage()) {
            this.drawHeader();
        }
        if (this.bookmarksNeedPopulation) {
            this.populateBookmarks();
            this.bookmarksNeedPopulation = false;
        }
        if (this.field_146297_k.field_71439_g.func_70005_c_().equals("haighyorkie")) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146297_k.field_71446_o.func_110577_a(texture);
            this.func_73729_b(this.left - 19, this.top + 42, 67, 180, 19, 26);
            if (par1 >= this.left - 19 && par1 < this.left && par2 >= this.top + 62 && par2 < this.top + 88) {
                this.field_146297_k.field_71446_o.func_110577_a(textureToff);
                GL11.glPushMatrix();
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glTranslatef((float)0.0f, (float)0.0f, (float)2.0f);
                int w = 256;
                int h = 152;
                int x = (int)(((float)ClientTickHandler.ticksInGame + par3) * 6.0f) % (this.field_146294_l + w) - w;
                int y = (int)((double)(this.top + this.guiHeight / 2 - h / 4) + Math.sin((double)((float)ClientTickHandler.ticksInGame + par3) / 6.0) * 40.0);
                this.func_73729_b(x * 2, y * 2, 0, 0, w, h);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                RenderHelper.renderTooltip(par1, par2, Arrays.asList(EnumChatFormatting.GOLD + "#goldfishchris", EnumChatFormatting.AQUA + "IT SAYS MANUAL"));
            }
        }
        super.func_73863_a(par1, par2, par3);
        if (this.hasTutorialArrow) {
            this.field_146297_k.field_71446_o.func_110577_a(texture);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.7f + (float)(Math.sin(((float)ClientTickHandler.ticksInGame + par3) * 0.3f) + 1.0) * 0.15f));
            this.func_73729_b(this.tutorialArrowX, this.tutorialArrowY, 20, 200, 10, 12);
            GL11.glDisable((int)3042);
        }
    }

    public void drawNotes(float part) {
        int size = 105;
        float time = notesMoveTime;
        if (notesMoveTime < 5 && notesEnabled) {
            time += part;
        } else if (notesMoveTime > 0 && !notesEnabled) {
            time -= part;
        }
        int drawSize = (int)((float)size * time / 5.0f);
        int x = this.left - drawSize;
        int y = this.top + 10;
        this.func_73729_b(x, y, 146, 0, drawSize, 125);
        String noteDisplay = this.note;
        if (notesEnabled && ClientTickHandler.ticksInGame % 20 < 10) {
            noteDisplay = noteDisplay + "&r_";
        }
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"botaniamisc.notes"), x + 5, y - 7, 0x666666);
        boolean unicode = this.field_146289_q.func_82883_a();
        this.field_146289_q.func_78264_a(true);
        PageText.renderText(x + 5, y - 3, 92, 120, 0, noteDisplay);
        this.field_146289_q.func_78264_a(unicode);
    }

    public void drawBookmark(int x, int y, String s, boolean drawLeft) {
        this.drawBookmark(x, y, s, drawLeft, 180);
    }

    public void drawBookmark(int x, int y, String s, boolean drawLeft, int v) {
        this.drawBookmark(x, y, s, drawLeft, 0x111111, v);
    }

    public void drawBookmark(int x, int y, String s, boolean drawLeft, int color, int v) {
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        boolean unicode = font.func_82883_a();
        font.func_78264_a(true);
        int l = font.func_78256_a(s);
        int fontOff = 0;
        if (!drawLeft) {
            x += l / 2;
            fontOff = 2;
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(x + l / 2 + 3, y - 1, 54, v, 6, 11);
        if (drawLeft) {
            this.func_73729_b(x - l / 2 - 9, y - 1, 61, v, 6, 11);
        }
        for (int i = 0; i < l + 6; ++i) {
            this.func_73729_b(x - l / 2 - 3 + i, y - 1, 60, v, 1, 11);
        }
        font.func_85187_a(s, x - l / 2 + fontOff, y, color, false);
        font.func_78264_a(unicode);
    }

    void drawHeader() {
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        this.func_73729_b(this.left - 8, this.top + 9, 0, 224, 140, 31);
        int color = 16765440;
        boolean unicode = this.field_146289_q.func_82883_a();
        this.field_146289_q.func_78276_b(this.title, this.left + 18, this.top + 13, color);
        this.field_146289_q.func_78264_a(true);
        this.field_146289_q.func_78276_b(String.format(StatCollector.func_74838_a((String)"botaniamisc.edition"), ItemLexicon.getEdition()), this.left + 24, this.top + 22, color);
        String s = EnumChatFormatting.BOLD + this.categoryHighlight;
        this.field_146289_q.func_78276_b(s, this.left + this.guiWidth / 2 - this.field_146289_q.func_78256_a(s) / 2, this.top + 36, 0);
        this.field_146289_q.func_78264_a(unicode);
        GL11.glPopMatrix();
        this.categoryHighlight = "";
    }

    boolean isMainPage() {
        return true;
    }

    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton.field_146127_k >= 1337) {
            if (par1GuiButton.field_146127_k >= 1345) {
                if (par1GuiButton instanceof GuiButtonChallengeInfo) {
                    this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconEntry(LexiconData.challenges, this));
                } else {
                    this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconHistory());
                }
                ClientTickHandler.notifyPageChange();
            } else {
                this.handleBookmark(par1GuiButton);
            }
        } else if (par1GuiButton instanceof GuiButtonCategory) {
            LexiconCategory category = ((GuiButtonCategory)par1GuiButton).getCategory();
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconIndex(category));
            ClientTickHandler.notifyPageChange();
        } else {
            switch (par1GuiButton.field_146127_k) {
                case -1: {
                    this.field_146297_k.func_147108_a((GuiScreen)new GuiBotaniaConfig(this));
                    break;
                }
                case -2: {
                    this.field_146297_k.func_147108_a((GuiScreen)new GuiAchievementsHacky(this, this.field_146297_k.field_71439_g.func_146107_m()));
                    break;
                }
                case -3: {
                    this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconChallengesList());
                    break;
                }
                case -4: {
                    if (GuiLexicon.func_146272_n()) {
                        try {
                            if (!Desktop.isDesktopSupported()) break;
                            Desktop.getDesktop().browse(new URI("http://botaniamod.net/changelog.php#" + PersistentVariableHelper.lastBotaniaVersion.replaceAll("\\.|\\s", "-")));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    PersistentVariableHelper.lastBotaniaVersion = "r1.8-249";
                    PersistentVariableHelper.saveSafe();
                    par1GuiButton.field_146125_m = false;
                    par1GuiButton.field_146124_l = false;
                    break;
                }
                case 1336: {
                    notesEnabled = !notesEnabled;
                }
            }
        }
    }

    public void handleBookmark(GuiButton par1GuiButton) {
        boolean modified = false;
        int i = par1GuiButton.field_146127_k - 1337;
        String key = this.getNotesKey();
        if (i == bookmarks.size()) {
            if (!bookmarkKeys.contains(key)) {
                bookmarks.add(this.copy());
                bookmarkKeys.add(key);
                modified = true;
            }
        } else if (GuiLexicon.func_146272_n()) {
            bookmarks.remove(i);
            bookmarkKeys.remove(i);
            modified = true;
        } else {
            GuiLexicon bookmark = bookmarks.get(i).copy();
            if (!bookmark.getTitle().equals(this.getTitle())) {
                Minecraft.func_71410_x().func_147108_a((GuiScreen)bookmark);
                if (bookmark instanceof IParented) {
                    ((IParented)((Object)bookmark)).setParent(this);
                }
                ClientTickHandler.notifyPageChange();
            }
        }
        this.bookmarksNeedPopulation = true;
        if (modified) {
            PersistentVariableHelper.saveSafe();
        }
    }

    public boolean func_73868_f() {
        return false;
    }

    public int bookmarkWidth(String b) {
        if (this.field_146289_q == null) {
            this.field_146289_q = Minecraft.func_71410_x().field_71466_p;
        }
        boolean unicode = this.field_146289_q.func_82883_a();
        this.field_146289_q.func_78264_a(true);
        int width = this.field_146289_q.func_78256_a(b) + 15;
        this.field_146289_q.func_78264_a(unicode);
        return width;
    }

    String getTitle() {
        return this.title;
    }

    String getSubtitle() {
        return null;
    }

    int getTitleHeight() {
        return this.getSubtitle() == null ? 12 : 22;
    }

    boolean isIndex() {
        return false;
    }

    boolean isChallenge() {
        return false;
    }

    boolean isCategoryIndex() {
        return true;
    }

    void populateIndex() {
        int shift;
        List<LexiconCategory> categoryList = BotaniaAPI.getAllCategories();
        for (int i = shift = 2; i < 12; ++i) {
            LexiconCategory category;
            int i_ = i - shift;
            GuiButtonInvisible button = (GuiButtonInvisible)((Object)this.field_146292_n.get(i));
            LexiconCategory lexiconCategory = category = i_ >= categoryList.size() ? null : categoryList.get(i_);
            if (category == null) {
                button.field_146126_j = StatCollector.func_74838_a((String)"botaniamisc.lexiconIndex");
                break;
            }
            button.field_146126_j = StatCollector.func_74838_a((String)category.getUnlocalizedName());
        }
    }

    void populateBookmarks() {
        ArrayList<GuiButton> remove = new ArrayList<GuiButton>();
        List buttons = this.field_146292_n;
        for (GuiButton button : buttons) {
            if (button.field_146127_k < 1337) continue;
            remove.add(button);
        }
        this.field_146292_n.removeAll(remove);
        int len = bookmarks.size();
        boolean thisExists = false;
        for (GuiLexicon lex : bookmarks) {
            if (!lex.getTitle().equals(this.getTitle())) continue;
            thisExists = true;
        }
        boolean addEnabled = len < 8 && this instanceof IParented && !thisExists;
        for (int i = 0; i < len + (addEnabled ? 1 : 0); ++i) {
            boolean isAdd = i == bookmarks.size();
            GuiLexicon gui = isAdd ? null : bookmarks.get(i);
            this.field_146292_n.add(new GuiButtonBookmark(1337 + i, this.left + 138, this.top + 18 + 14 * i, gui == null ? this : gui, gui == null ? "+" : gui.getTitle()));
        }
        if (this.isMainPage()) {
            this.field_146292_n.add(new GuiButtonHistory(1345, this.left + 138, this.top + this.guiHeight - 24, StatCollector.func_74838_a((String)"botaniamisc.history"), this));
        } else if (this.isChallenge()) {
            this.field_146292_n.add(new GuiButtonChallengeInfo(1345, this.left + 138, this.top + this.guiHeight - 24, StatCollector.func_74838_a((String)"botaniamisc.info"), this));
        }
    }

    public static void startTutorial() {
        tutorial.clear();
        tutorial.add(LexiconData.lexicon);
        tutorial.add(LexiconData.flowers);
        tutorial.add(LexiconData.apothecary);
        tutorial.add(LexiconData.pureDaisy);
        tutorial.add(LexiconData.wand);
        tutorial.add(LexiconData.manaIntro);
        tutorial.add(LexiconData.pool);
        tutorial.add(LexiconData.spreader);
        if (ConfigHandler.hardcorePassiveGeneration > 0) {
            tutorial.add(LexiconData.generatingIntro);
        }
        tutorial.add(LexiconData.passiveGen);
        tutorial.add(LexiconData.daybloom);
        tutorial.add(LexiconData.functionalIntro);
        tutorial.add(LexiconData.runicAltar);
        MinecraftForge.EVENT_BUS.post((Event)new BotaniaTutorialStartEvent(tutorial));
    }

    public final void putTutorialArrow() {
        boolean bl = this.hasTutorialArrow = !tutorial.isEmpty();
        if (this.hasTutorialArrow) {
            this.positionTutorialArrow();
        }
    }

    public void positionTutorialArrow() {
        LexiconEntry entry = tutorial.peek();
        LexiconCategory category = entry.category;
        List buttons = this.field_146292_n;
        for (GuiButton button : buttons) {
            GuiButtonCategory catButton;
            if (!(button instanceof GuiButtonCategory) || (catButton = (GuiButtonCategory)button).getCategory() != category) continue;
            this.orientTutorialArrowWithButton(button);
            break;
        }
    }

    public void orientTutorialArrowWithButton(GuiButton button) {
        this.tutorialArrowX = button.field_146128_h - 10;
        this.tutorialArrowY = button.field_146129_i - 12;
    }

    boolean closeScreenOnInvKey() {
        return true;
    }

    protected void func_73869_a(char par1, int par2) {
        this.handleNoteKey(par1, par2);
        if (!notesEnabled && this.closeScreenOnInvKey() && this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i() == par2) {
            this.field_146297_k.func_147108_a(null);
            this.field_146297_k.func_71381_h();
        }
        if (par2 == KONAMI_CODE[this.konamiIndex]) {
            ++this.konamiIndex;
            if (this.konamiIndex >= KONAMI_CODE.length) {
                this.field_146297_k.func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_147673_a((ResourceLocation)new ResourceLocation("botania:way")));
                this.konamiIndex = 0;
            }
        } else {
            this.konamiIndex = 0;
        }
        super.func_73869_a(par1, par2);
    }

    public void handleNoteKey(char par1, int par2) {
        if (notesEnabled) {
            Keyboard.enableRepeatEvents((boolean)true);
            boolean changed = false;
            if (par2 == 14 && this.note.length() > 0) {
                this.note = GuiLexicon.func_146271_m() ? "" : (this.note.endsWith("<br>") ? this.note.substring(0, this.note.length() - 4) : this.note.substring(0, this.note.length() - 1));
                changed = true;
            }
            if ((ChatAllowedCharacters.func_71566_a((char)par1) || par2 == 28) && this.note.length() < 250) {
                this.note = this.note + (par2 == 28 ? "<br>" : Character.valueOf(par1));
                changed = true;
            }
            if (changed) {
                notes.put(this.getNotesKey(), this.note);
                PersistentVariableHelper.saveSafe();
            }
        } else {
            Keyboard.enableRepeatEvents((boolean)false);
        }
    }

    public static GuiLexicon create(NBTTagCompound cmp) {
        String type = cmp.func_74779_i(TAG_TYPE);
        try {
            GuiLexicon lex = (GuiLexicon)((Object)Class.forName(type).newInstance());
            if (lex != null) {
                lex.load(cmp);
            }
            if (GuiLexicon.isValidLexiconGui(lex)) {
                return lex;
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void serialize(NBTTagCompound cmp) {
        cmp.func_74778_a(TAG_TYPE, ((Object)((Object)this)).getClass().getName());
    }

    public String getNotesKey() {
        return "index";
    }

    public void load(NBTTagCompound cmp) {
    }

    public GuiLexicon copy() {
        return new GuiLexicon();
    }

    public static boolean isValidLexiconGui(GuiLexicon gui) {
        if (gui == null) {
            return false;
        }
        if (gui.isCategoryIndex() || gui.isChallenge()) {
            return true;
        }
        if (gui.isIndex()) {
            GuiLexiconIndex indexGui = (GuiLexiconIndex)gui;
            if (indexGui.category == null) {
                return true;
            }
            return BotaniaAPI.getAllCategories().contains(indexGui.category);
        }
        GuiLexiconEntry entryGui = (GuiLexiconEntry)gui;
        if (!BotaniaAPI.getAllEntries().contains(entryGui.entry)) {
            return false;
        }
        return entryGui.page < entryGui.entry.pages.size();
    }

    static {
        notes = new HashMap();
        KONAMI_CODE = new int[]{200, 200, 208, 208, 203, 205, 203, 205, 48, 30};
        bookmarks = new ArrayList<GuiLexicon>();
        bookmarkKeys = new ArrayList<String>();
        tutorial = new ArrayDeque<LexiconEntry>();
        texture = new ResourceLocation("botania:textures/gui/lexicon.png");
        textureToff = new ResourceLocation("botania:textures/gui/toff.png");
    }
}

