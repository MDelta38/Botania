/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 */
package vazkii.botania.client.gui.lexicon;

import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconHistory;
import vazkii.botania.client.gui.lexicon.IParented;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBackWithShift;
import vazkii.botania.client.gui.lexicon.button.GuiButtonPage;
import vazkii.botania.client.gui.lexicon.button.GuiButtonShare;
import vazkii.botania.client.gui.lexicon.button.GuiButtonViewOnline;

public class GuiLexiconEntry
extends GuiLexicon
implements IGuiLexiconEntry,
IParented {
    private static final String TAG_ENTRY = "entry";
    private static final String TAG_PAGE = "page";
    public int page = 0;
    public boolean firstEntry = false;
    LexiconEntry entry;
    GuiScreen parent;
    String title;
    String subtitle;
    GuiButton leftButton;
    GuiButton rightButton;
    GuiButton backButton;
    int fx = 0;
    boolean swiped = false;

    public GuiLexiconEntry() {
        this.parent = new GuiLexicon();
        this.setTitle();
    }

    public GuiLexiconEntry(LexiconEntry entry, GuiScreen parent) {
        this.entry = entry;
        this.parent = parent;
        this.setTitle();
    }

    public void setTitle() {
        if (this.entry == null) {
            this.title = "(null)";
            return;
        }
        this.title = StatCollector.func_74838_a((String)this.entry.getUnlocalizedName());
        this.subtitle = this.entry instanceof IAddonEntry ? StatCollector.func_74838_a((String)((IAddonEntry)((Object)this.entry)).getSubtitle()) : null;
    }

    @Override
    public void onInitGui() {
        super.onInitGui();
        this.backButton = new GuiButtonBackWithShift(0, this.left + this.guiWidth / 2 - 8, this.top + this.guiHeight + 2);
        this.field_146292_n.add(this.backButton);
        this.leftButton = new GuiButtonPage(1, this.left, this.top + this.guiHeight - 10, false);
        this.field_146292_n.add(this.leftButton);
        this.rightButton = new GuiButtonPage(2, this.left + this.guiWidth - 18, this.top + this.guiHeight - 10, true);
        this.field_146292_n.add(this.rightButton);
        this.field_146292_n.add(new GuiButtonShare(3, this.left + this.guiWidth - 6, this.top - 2));
        if (this.entry.getWebLink() != null) {
            this.field_146292_n.add(new GuiButtonViewOnline(4, this.left - 8, this.top + 12));
        }
        if (!GuiLexicon.isValidLexiconGui(this)) {
            currentOpenLexicon = new GuiLexicon();
            this.field_146297_k.func_147108_a((GuiScreen)currentOpenLexicon);
            ClientTickHandler.notifyPageChange();
            return;
        }
        LexiconPage page = this.entry.pages.get(this.page);
        page.onOpened(this);
        this.updatePageButtons();
        GuiLexiconHistory.visit(this.entry);
    }

    @Override
    public LexiconEntry getEntry() {
        return this.entry;
    }

    @Override
    public int getPageOn() {
        return this.page;
    }

    @Override
    boolean isMainPage() {
        return false;
    }

    @Override
    String getTitle() {
        return String.format("%s " + EnumChatFormatting.ITALIC + "(%s/%s)", this.title, this.page + 1, this.entry.pages.size());
    }

    @Override
    String getSubtitle() {
        return this.subtitle;
    }

    @Override
    boolean isCategoryIndex() {
        return false;
    }

    @Override
    protected void func_146284_a(GuiButton par1GuiButton) {
        LexiconPage currentPage = this.entry.pages.get(this.page);
        if (par1GuiButton.field_146127_k >= 1337) {
            this.handleBookmark(par1GuiButton);
        } else if (par1GuiButton.field_146127_k == 1336) {
            notesEnabled = !notesEnabled;
        } else {
            switch (par1GuiButton.field_146127_k) {
                case 0: {
                    currentPage.onClosed(this);
                    this.field_146297_k.func_147108_a((GuiScreen)(GuiScreen.func_146272_n() ? new GuiLexicon() : this.parent));
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                case 1: {
                    currentPage.onClosed(this);
                    --this.page;
                    LexiconPage newPage = this.entry.pages.get(this.page);
                    newPage.onOpened(this);
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                case 2: {
                    currentPage.onClosed(this);
                    ++this.page;
                    LexiconPage newPage = this.entry.pages.get(this.page);
                    newPage.onOpened(this);
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                case 3: {
                    Minecraft mc = Minecraft.func_71410_x();
                    String cmd = "/botania-share " + this.entry.getUnlocalizedName();
                    mc.field_71456_v.func_146158_b().func_146239_a(cmd);
                    mc.field_71439_g.func_71165_d(cmd);
                    break;
                }
                case 4: {
                    try {
                        if (!Desktop.isDesktopSupported()) break;
                        Desktop.getDesktop().browse(new URI(this.entry.getWebLink()));
                        break;
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.updatePageButtons();
        currentPage.onActionPerformed(this, par1GuiButton);
    }

    public GuiLexiconEntry setFirstEntry() {
        this.firstEntry = true;
        return this;
    }

    public void updatePageButtons() {
        this.leftButton.field_146124_l = this.page != 0;
        boolean bl = this.rightButton.field_146124_l = this.page + 1 < this.entry.pages.size();
        if (this.firstEntry) {
            this.backButton.field_146124_l = !this.rightButton.field_146124_l;
        }
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        LexiconPage page = this.entry.pages.get(this.page);
        page.renderScreen(this, par1, par2);
    }

    @Override
    public void func_73876_c() {
        LexiconEntry entry;
        super.func_73876_c();
        LexiconPage page = this.entry.pages.get(this.page);
        page.updateScreen(this);
        if (this.page == this.entry.pages.size() - 1 && (entry = (LexiconEntry)tutorial.peek()) == this.entry) {
            tutorial.poll();
            this.positionTutorialArrow();
            if (tutorial.isEmpty()) {
                this.field_146297_k.field_71439_g.func_145747_a(new ChatComponentTranslation("botaniamisc.tutorialEnded", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
                this.hasTutorialArrow = false;
            }
        }
    }

    @Override
    public void positionTutorialArrow() {
        LexiconEntry entry = (LexiconEntry)tutorial.peek();
        if (entry != this.entry) {
            this.orientTutorialArrowWithButton(this.backButton);
            return;
        }
        if (this.rightButton.field_146124_l && this.rightButton.field_146125_m) {
            this.orientTutorialArrowWithButton(this.rightButton);
        }
    }

    @Override
    public int getLeft() {
        return this.left;
    }

    @Override
    public int getTop() {
        return this.top;
    }

    @Override
    public int getWidth() {
        return this.guiWidth;
    }

    @Override
    public int getHeight() {
        return this.guiHeight;
    }

    @Override
    public float getZLevel() {
        return this.field_73735_i;
    }

    @Override
    public void setParent(GuiLexicon gui) {
        this.parent = gui;
    }

    protected void func_146273_a(int x, int y, int button, long time) {
        if (button == 0 && Math.abs(x - this.fx) > 100 && this.field_146297_k.field_71474_y.field_85185_A && !this.swiped) {
            double swipe = (double)(x - this.fx) / Math.max(1.0, (double)time);
            if (swipe < 0.5) {
                this.nextPage();
                this.swiped = true;
            } else if (swipe > 0.5) {
                this.prevPage();
                this.swiped = true;
            }
        }
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        super.func_73864_a(par1, par2, par3);
        this.fx = par1;
        switch (par3) {
            case 1: {
                this.back();
                break;
            }
            case 3: {
                this.nextPage();
                break;
            }
            case 4: {
                this.prevPage();
            }
        }
    }

    public void func_146274_d() {
        int w;
        super.func_146274_d();
        if (Mouse.getEventButton() == 0) {
            this.swiped = false;
        }
        if ((w = Mouse.getEventDWheel()) < 0) {
            this.nextPage();
        } else if (w > 0) {
            this.prevPage();
        }
    }

    @Override
    protected void func_73869_a(char par1, int par2) {
        this.handleNoteKey(par1, par2);
        LexiconPage page = this.entry.pages.get(this.page);
        page.onKeyPressed(par1, par2);
        if (par2 == 1) {
            this.field_146297_k.func_147108_a((GuiScreen)null);
            this.field_146297_k.func_71381_h();
        } else if (par2 == 203 || par2 == 200 || par2 == 201) {
            this.prevPage();
        } else if (par2 == 205 || par2 == 208 || par2 == 209) {
            this.nextPage();
        }
        if (par2 == 14 && !notesEnabled) {
            this.back();
        } else if (par2 == 199) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLexicon());
            ClientTickHandler.notifyPageChange();
        }
    }

    void back() {
        if (this.backButton.field_146124_l) {
            this.func_146284_a(this.backButton);
            this.backButton.func_146113_a(this.field_146297_k.func_147118_V());
        }
    }

    void nextPage() {
        if (this.rightButton.field_146124_l) {
            this.func_146284_a(this.rightButton);
            this.rightButton.func_146113_a(this.field_146297_k.func_147118_V());
            this.updateNote();
        }
    }

    void prevPage() {
        if (this.leftButton.field_146124_l) {
            this.func_146284_a(this.leftButton);
            this.leftButton.func_146113_a(this.field_146297_k.func_147118_V());
            this.updateNote();
        }
    }

    void updateNote() {
        String key = this.getNotesKey();
        this.note = !notes.containsKey(key) ? "" : (String)notes.get(key);
    }

    @Override
    public List<GuiButton> getButtonList() {
        return this.field_146292_n;
    }

    @Override
    public float getElapsedTicks() {
        return this.lastTime;
    }

    @Override
    public float getPartialTicks() {
        return this.partialTicks;
    }

    @Override
    public float getTickDelta() {
        return this.timeDelta;
    }

    @Override
    public void serialize(NBTTagCompound cmp) {
        super.serialize(cmp);
        cmp.func_74778_a(TAG_ENTRY, this.entry.getUnlocalizedName());
        cmp.func_74768_a(TAG_PAGE, this.page);
    }

    @Override
    public void load(NBTTagCompound cmp) {
        super.load(cmp);
        String entryStr = cmp.func_74779_i(TAG_ENTRY);
        for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            if (!entry.getUnlocalizedName().equals(entryStr)) continue;
            this.entry = entry;
            break;
        }
        this.page = cmp.func_74762_e(TAG_PAGE);
        this.setTitle();
    }

    @Override
    public GuiLexicon copy() {
        GuiLexiconEntry gui = new GuiLexiconEntry(this.entry, new GuiScreen());
        gui.page = this.page;
        gui.setTitle();
        return gui;
    }

    @Override
    public String getNotesKey() {
        return "entry_" + this.entry.unlocalizedName + "_" + this.page;
    }
}

