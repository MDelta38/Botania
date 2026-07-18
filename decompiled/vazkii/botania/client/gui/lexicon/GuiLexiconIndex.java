/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.IParented;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBack;
import vazkii.botania.client.gui.lexicon.button.GuiButtonInvisible;
import vazkii.botania.client.gui.lexicon.button.GuiButtonPage;
import vazkii.botania.common.lexicon.DLexiconEntry;

public class GuiLexiconIndex
extends GuiLexicon
implements IParented {
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_PAGE = "page";
    LexiconCategory category;
    String title;
    int page = 0;
    int tutPage = -1;
    GuiButton leftButton;
    GuiButton rightButton;
    GuiButton backButton;
    GuiLexicon parent;
    GuiTextField searchField;
    GuiButton currentButton;
    LexiconEntry currentEntry;
    float infoTime;
    List<LexiconEntry> entriesToDisplay = new ArrayList<LexiconEntry>();
    int fx = 0;
    boolean swiped = false;

    public GuiLexiconIndex() {
        this.parent = new GuiLexicon();
    }

    public GuiLexiconIndex(LexiconCategory category) {
        this.category = category;
        this.parent = new GuiLexicon();
        this.setTitle();
    }

    public void setTitle() {
        this.title = StatCollector.func_74838_a((String)(this.category == null ? "botaniamisc.lexiconIndex" : this.category.getUnlocalizedName()));
    }

    @Override
    boolean isMainPage() {
        return false;
    }

    @Override
    String getTitle() {
        return this.title;
    }

    @Override
    boolean isIndex() {
        return true;
    }

    @Override
    boolean isCategoryIndex() {
        return false;
    }

    @Override
    public void onInitGui() {
        super.onInitGui();
        if (!GuiLexicon.isValidLexiconGui(this)) {
            currentOpenLexicon = new GuiLexicon();
            this.field_146297_k.func_147108_a((GuiScreen)currentOpenLexicon);
            ClientTickHandler.notifyPageChange();
            return;
        }
        this.backButton = new GuiButtonBack(12, this.left + this.guiWidth / 2 - 8, this.top + this.guiHeight + 2);
        this.field_146292_n.add(this.backButton);
        this.leftButton = new GuiButtonPage(13, this.left, this.top + this.guiHeight - 10, false);
        this.field_146292_n.add(this.leftButton);
        this.rightButton = new GuiButtonPage(14, this.left + this.guiWidth - 18, this.top + this.guiHeight - 10, true);
        this.field_146292_n.add(this.rightButton);
        this.searchField = new GuiTextField(this.field_146289_q, this.left + this.guiWidth / 2 + 28, this.top + this.guiHeight + 6, 200, 10);
        this.searchField.func_146205_d(false);
        this.searchField.func_146195_b(true);
        this.searchField.func_146185_a(false);
        this.updateAll();
    }

    void updateAll() {
        this.buildEntries();
        this.updatePageButtons();
        this.populateIndex();
    }

    void buildEntries() {
        this.entriesToDisplay.clear();
        ILexicon lex = (ILexicon)stackUsed.func_77973_b();
        for (LexiconEntry entry : this.category == null ? BotaniaAPI.getAllEntries() : this.category.entries) {
            if (!entry.isVisible() || !lex.isKnowledgeUnlocked(stackUsed, entry.getKnowledgeType()) || !this.matchesSearch(entry)) continue;
            this.entriesToDisplay.add(entry);
        }
        Collections.sort(this.entriesToDisplay);
    }

    boolean matchesSearch(LexiconEntry e) {
        String search = this.searchField.func_146179_b().trim();
        if (search.isEmpty()) {
            return true;
        }
        search = search.toLowerCase();
        if (StatCollector.func_74838_a((String)e.getUnlocalizedName()).toLowerCase().contains(search)) {
            return true;
        }
        for (ItemStack stack : e.getDisplayedRecipes()) {
            String stackName = stack.func_82833_r().toLowerCase().trim();
            if (!stackName.contains(search)) continue;
            return true;
        }
        return false;
    }

    @Override
    void populateIndex() {
        LexiconEntry tutEntry = tutorial != null && !tutorial.isEmpty() ? (LexiconEntry)tutorial.peek() : null;
        for (int i = this.page * 12; i < (this.page + 1) * 12; ++i) {
            LexiconEntry entry;
            GuiButtonInvisible button = (GuiButtonInvisible)((Object)this.field_146292_n.get(i - this.page * 12));
            LexiconEntry lexiconEntry = entry = i >= this.entriesToDisplay.size() ? null : this.entriesToDisplay.get(i);
            if (entry != null) {
                button.field_146126_j = entry.getKnowledgeType().color + "" + (entry.isPriority() ? EnumChatFormatting.ITALIC : "") + StatCollector.func_74838_a((String)entry.getUnlocalizedName());
                button.displayStack = entry.getIcon();
                if (entry == tutEntry) {
                    this.tutPage = this.page;
                }
                if (!(entry instanceof DLexiconEntry)) continue;
                button.dog = true;
                continue;
            }
            button.field_146126_j = "";
        }
    }

    public void setHoveredButton(GuiButtonInvisible b) {
        this.currentEntry = b == null ? null : this.entriesToDisplay.get(b.field_146127_k + this.page * 12);
        this.currentButton = b;
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        String s;
        boolean unicode;
        super.func_73863_a(par1, par2, par3);
        if (!this.searchField.func_146179_b().isEmpty()) {
            this.drawBookmark(this.left + 138, this.top + this.guiHeight - 24, "  " + this.searchField.func_146179_b(), false);
            this.field_146297_k.field_71446_o.func_110577_a(texture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(this.left + 134, this.top + this.guiHeight - 26, 86, 180, 12, 12);
            if (this.entriesToDisplay.size() == 1) {
                unicode = this.field_146297_k.field_71466_p.func_82883_a();
                this.field_146297_k.field_71466_p.func_78264_a(true);
                s = StatCollector.func_74838_a((String)"botaniamisc.enterToView");
                this.field_146297_k.field_71466_p.func_78276_b(s, this.left + this.guiWidth / 2 - this.field_146297_k.field_71466_p.func_78256_a(s) / 2, this.top + 30, 0x666666);
                this.field_146297_k.field_71466_p.func_78264_a(unicode);
            }
        } else {
            unicode = this.field_146297_k.field_71466_p.func_82883_a();
            this.field_146297_k.field_71466_p.func_78264_a(true);
            s = StatCollector.func_74838_a((String)"botaniamisc.typeToSearch");
            this.field_146297_k.field_71466_p.func_78276_b(s, this.left + 120 - this.field_146297_k.field_71466_p.func_78256_a(s), this.top + this.guiHeight - 18, 0x666666);
            this.field_146297_k.field_71466_p.func_78264_a(unicode);
        }
        float animationTime = 4.0f;
        if (GuiLexiconIndex.func_146272_n()) {
            if (this.currentButton != null) {
                this.infoTime = Math.min(animationTime, this.infoTime + this.timeDelta);
            }
        } else {
            this.infoTime = Math.max(0.0f, this.infoTime - this.timeDelta);
            if (this.currentButton != null && this.infoTime == 0.0f) {
                int x = par1 + 10;
                int y = par2;
                x = this.currentButton.field_146128_h - 20;
                y = this.currentButton.field_146129_i;
                this.field_146297_k.field_71466_p.func_78261_a("?", x, y, 0xFFFFFF);
                GL11.glScalef((float)0.5f, (float)0.5f, (float)1.0f);
                this.field_146297_k.field_71466_p.func_78261_a(EnumChatFormatting.BOLD + "Shift", x * 2 - 6, y * 2 + 20, 0xFFFFFF);
                GL11.glScalef((float)2.0f, (float)2.0f, (float)1.0f);
            }
        }
        if (this.currentButton != null && this.infoTime > 0.0f) {
            float fract = this.infoTime / animationTime;
            int x = this.currentButton.field_146128_h;
            int y = this.currentButton.field_146129_i;
            String s2 = StatCollector.func_74838_a((String)this.currentEntry.getTagline());
            boolean unicode2 = this.field_146297_k.field_71466_p.func_82883_a();
            this.field_146297_k.field_71466_p.func_78264_a(true);
            int width = this.field_146297_k.field_71466_p.func_78256_a(s2);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x, (float)y, (float)0.0f);
            GL11.glScalef((float)fract, (float)1.0f, (float)1.0f);
            Gui.func_73734_a((int)12, (int)-30, (int)(width + 20), (int)-2, (int)0x44000000);
            Gui.func_73734_a((int)10, (int)-32, (int)(width + 22), (int)-2, (int)0x44000000);
            this.drawBookmark(width / 2 + 16, -8, s2, true, 0xFFFFFF, 180);
            this.field_146297_k.field_71466_p.func_78264_a(unicode2);
            RenderHelper.func_74520_c();
            GL11.glEnable((int)32826);
            ItemStack paper = new ItemStack(Items.field_151121_aF, this.currentEntry.pages.size());
            RenderItem.getInstance().func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, paper, 14, -28);
            RenderItem.getInstance().func_77021_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, paper, 14, -28);
            List<ItemStack> stacks = this.currentEntry.getDisplayedRecipes();
            if (stacks.size() > 0) {
                int spaceForEach = Math.min(18, (width - 30) / stacks.size());
                for (int i = 0; i < stacks.size(); ++i) {
                    ItemStack stack = stacks.get(i);
                    RenderItem.getInstance().func_82406_b(this.field_146297_k.field_71466_p, this.field_146297_k.field_71446_o, stack, 38 + spaceForEach * i, -28);
                }
            }
            RenderHelper.func_74518_a();
            GL11.glPopMatrix();
        }
        this.setHoveredButton(null);
    }

    @Override
    public void positionTutorialArrow() {
        LexiconEntry entry = (LexiconEntry)tutorial.peek();
        LexiconCategory category = entry.category;
        if (category != this.category) {
            this.orientTutorialArrowWithButton(this.backButton);
            return;
        }
        if (this.tutPage != -1 && this.tutPage != this.page) {
            this.orientTutorialArrowWithButton(this.tutPage < this.page ? this.leftButton : this.rightButton);
            return;
        }
        List buttons = this.field_146292_n;
        for (GuiButton button : buttons) {
            int id = button.field_146127_k;
            int index = id + this.page * 12;
            if (index >= this.entriesToDisplay.size() || entry != this.entriesToDisplay.get(index)) continue;
            this.orientTutorialArrowWithButton(id >= 12 ? this.rightButton : button);
            break;
        }
    }

    @Override
    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton.field_146127_k >= 1337) {
            this.handleBookmark(par1GuiButton);
        } else if (par1GuiButton.field_146127_k == 1336) {
            notesEnabled = !notesEnabled;
        } else {
            switch (par1GuiButton.field_146127_k) {
                case 12: {
                    this.field_146297_k.func_147108_a((GuiScreen)this.parent);
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                case 13: {
                    --this.page;
                    this.updatePageButtons();
                    this.populateIndex();
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                case 14: {
                    ++this.page;
                    this.updatePageButtons();
                    this.populateIndex();
                    ClientTickHandler.notifyPageChange();
                    break;
                }
                default: {
                    if (par1GuiButton instanceof GuiButtonInvisible && ((GuiButtonInvisible)par1GuiButton).dog) {
                        ((GuiButtonInvisible)par1GuiButton).click();
                        break;
                    }
                    int index = par1GuiButton.field_146127_k + this.page * 12;
                    this.openEntry(index);
                }
            }
        }
    }

    void openEntry(int index) {
        if (index >= this.entriesToDisplay.size()) {
            return;
        }
        LexiconEntry entry = this.entriesToDisplay.get(index);
        this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconEntry(entry, this));
        ClientTickHandler.notifyPageChange();
    }

    public void updatePageButtons() {
        this.leftButton.field_146124_l = this.page != 0;
        this.rightButton.field_146124_l = this.page < (this.entriesToDisplay.size() - 1) / 12;
        this.putTutorialArrow();
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
        this.searchField.func_146192_a(par1, par2, par3);
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
    boolean closeScreenOnInvKey() {
        return false;
    }

    @Override
    protected void func_73869_a(char par1, int par2) {
        if (par2 == 203 || par2 == 200 || par2 == 201) {
            this.prevPage();
        } else if (par2 == 205 || par2 == 208 || par2 == 209) {
            this.nextPage();
        } else if (par2 == 14 && !notesEnabled && this.searchField.func_146179_b().isEmpty()) {
            this.back();
        } else if (par2 == 199) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLexicon());
            ClientTickHandler.notifyPageChange();
        } else if (par2 == 28 && this.entriesToDisplay.size() == 1) {
            this.openEntry(0);
        }
        if (!notesEnabled) {
            String search = this.searchField.func_146179_b();
            this.searchField.func_146201_a(par1, par2);
            if (!this.searchField.func_146179_b().equalsIgnoreCase(search)) {
                this.updateAll();
            }
        }
        super.func_73869_a(par1, par2);
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
        }
    }

    void prevPage() {
        if (this.leftButton.field_146124_l) {
            this.func_146284_a(this.leftButton);
            this.leftButton.func_146113_a(this.field_146297_k.func_147118_V());
        }
    }

    @Override
    public void serialize(NBTTagCompound cmp) {
        super.serialize(cmp);
        cmp.func_74778_a(TAG_CATEGORY, this.category == null ? "" : this.category.getUnlocalizedName());
        cmp.func_74768_a(TAG_PAGE, this.page);
    }

    @Override
    public void load(NBTTagCompound cmp) {
        super.load(cmp);
        String categoryStr = cmp.func_74779_i(TAG_CATEGORY);
        if (categoryStr.isEmpty()) {
            this.category = null;
        } else {
            for (LexiconCategory cat : BotaniaAPI.getAllCategories()) {
                if (!cat.getUnlocalizedName().equals(categoryStr)) continue;
                this.category = cat;
                break;
            }
        }
        this.page = cmp.func_74762_e(TAG_PAGE);
        this.setTitle();
    }

    @Override
    public GuiLexicon copy() {
        GuiLexiconIndex gui = new GuiLexiconIndex(this.category);
        gui.page = this.page;
        gui.setTitle();
        return gui;
    }

    @Override
    public String getNotesKey() {
        return "category_" + (this.category == null ? "lexindex" : this.category.unlocalizedName);
    }
}

