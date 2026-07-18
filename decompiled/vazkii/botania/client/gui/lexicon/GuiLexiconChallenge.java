/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.gui.lexicon;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.challenge.Challenge;
import vazkii.botania.client.challenge.ModChallenges;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconChallengesList;
import vazkii.botania.client.gui.lexicon.IParented;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBack;
import vazkii.botania.common.lexicon.page.PageText;

public class GuiLexiconChallenge
extends GuiLexicon
implements IParented {
    private static final String TAG_CHALLENGE = "challenge";
    Challenge challenge;
    GuiLexicon parent;
    GuiButton backButton;
    GuiButton completeButton;

    public GuiLexiconChallenge() {
        this.parent = new GuiLexiconChallengesList();
    }

    public GuiLexiconChallenge(GuiLexicon parent, Challenge challenge) {
        this.parent = parent;
        this.challenge = challenge;
        this.setTitle();
    }

    public void setTitle() {
        this.title = this.challenge == null ? "(null)" : StatCollector.func_74838_a((String)this.challenge.unlocalizedName);
    }

    @Override
    public void onInitGui() {
        super.onInitGui();
        this.setTitle();
        this.backButton = new GuiButtonBack(12, this.left + this.guiWidth / 2 - 8, this.top + this.guiHeight + 2);
        this.field_146292_n.add(this.backButton);
        this.completeButton = new GuiButton(13, this.left + 20, this.top + this.guiHeight - 35, this.guiWidth - 40, 20, "");
        this.field_146292_n.add(this.completeButton);
        this.setCompleteButtonTitle();
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().func_77015_a(this.field_146289_q, this.field_146297_k.field_71446_o, this.challenge.icon, this.left + 18, this.top + 15);
        RenderHelper.func_74518_a();
        GL11.glEnable((int)3042);
        boolean unicode = this.field_146289_q.func_82883_a();
        this.field_146289_q.func_78264_a(true);
        this.field_146289_q.func_78276_b(EnumChatFormatting.BOLD + StatCollector.func_74838_a((String)this.challenge.unlocalizedName), this.left + 38, this.top + 13, 0);
        this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)this.challenge.level.getName()) + " / " + (this.challenge.complete ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED) + StatCollector.func_74838_a((String)(this.challenge.complete ? "botaniamisc.completed" : "botaniamisc.notCompleted")), this.left + 38, this.top + 23, 0);
        int width = this.guiWidth - 30;
        int x = this.left + 16;
        int y = this.top + 28;
        PageText.renderText(x, y, width, this.guiHeight, this.challenge.unlocalizedName + ".desc");
        this.field_146289_q.func_78264_a(unicode);
    }

    @Override
    protected void func_73869_a(char par1, int par2) {
        if (par2 == 14 && !notesEnabled) {
            this.back();
        } else if (par2 == 199) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLexicon());
            ClientTickHandler.notifyPageChange();
        }
        super.func_73869_a(par1, par2);
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        super.func_73864_a(par1, par2, par3);
        if (par3 == 1) {
            this.back();
        }
    }

    @Override
    protected void func_146284_a(GuiButton par1GuiButton) {
        if (par1GuiButton.field_146127_k >= 1337) {
            super.func_146284_a(par1GuiButton);
        } else if (par1GuiButton.field_146127_k == 12) {
            this.field_146297_k.func_147108_a((GuiScreen)this.parent);
            ClientTickHandler.notifyPageChange();
        } else if (par1GuiButton.field_146127_k == 13) {
            this.challenge.complete = !this.challenge.complete;
            this.setCompleteButtonTitle();
            PersistentVariableHelper.saveSafe();
        } else if (par1GuiButton.field_146127_k == 1336) {
            notesEnabled = !notesEnabled;
        }
    }

    void setCompleteButtonTitle() {
        this.completeButton.field_146126_j = StatCollector.func_74838_a((String)(this.challenge.complete ? "botaniamisc.markNotCompleted" : "botaniamisc.markCompleted"));
    }

    void back() {
        if (this.backButton.field_146124_l) {
            this.func_146284_a(this.backButton);
            this.backButton.func_146113_a(this.field_146297_k.func_147118_V());
        }
    }

    @Override
    public void setParent(GuiLexicon gui) {
        this.parent = gui;
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
    boolean isChallenge() {
        return true;
    }

    @Override
    boolean isCategoryIndex() {
        return false;
    }

    @Override
    public GuiLexicon copy() {
        return new GuiLexiconChallenge(this.parent, this.challenge);
    }

    @Override
    public void serialize(NBTTagCompound cmp) {
        super.serialize(cmp);
        cmp.func_74778_a(TAG_CHALLENGE, this.challenge.unlocalizedName);
    }

    @Override
    public void load(NBTTagCompound cmp) {
        Challenge c;
        super.load(cmp);
        String challengeName = cmp.func_74779_i(TAG_CHALLENGE);
        this.challenge = c = ModChallenges.challengeLookup.get(challengeName);
        this.setTitle();
    }

    @Override
    public String getNotesKey() {
        return "challenge_" + this.challenge.unlocalizedName;
    }
}

