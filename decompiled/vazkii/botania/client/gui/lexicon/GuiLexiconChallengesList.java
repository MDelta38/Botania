/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.gui.lexicon;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import vazkii.botania.client.challenge.Challenge;
import vazkii.botania.client.challenge.EnumChallengeLevel;
import vazkii.botania.client.challenge.ModChallenges;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconChallenge;
import vazkii.botania.client.gui.lexicon.IParented;
import vazkii.botania.client.gui.lexicon.button.GuiButtonBack;
import vazkii.botania.client.gui.lexicon.button.GuiButtonChallengeIcon;

public class GuiLexiconChallengesList
extends GuiLexicon
implements IParented {
    GuiLexicon parent = new GuiLexicon();
    GuiButton backButton;

    public GuiLexiconChallengesList() {
        this.title = StatCollector.func_74838_a((String)"botaniamisc.challenges");
    }

    @Override
    public void onInitGui() {
        super.onInitGui();
        this.title = StatCollector.func_74838_a((String)"botaniamisc.challenges");
        this.backButton = new GuiButtonBack(12, this.left + this.guiWidth / 2 - 8, this.top + this.guiHeight + 2);
        this.field_146292_n.add(this.backButton);
        int perline = 6;
        int i = 13;
        int y = this.top + 20;
        for (EnumChallengeLevel level : (EnumChallengeLevel[])EnumChallengeLevel.class.getEnumConstants()) {
            int j = 0;
            for (Challenge c : ModChallenges.challenges.get((Object)level)) {
                this.field_146292_n.add(new GuiButtonChallengeIcon(i, this.left + 20 + j % perline * 18, y + j / perline * 17, c));
                ++i;
                ++j;
            }
            y += 44;
        }
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        boolean unicode = this.field_146289_q.func_82883_a();
        this.field_146289_q.func_78264_a(true);
        for (EnumChallengeLevel level : (EnumChallengeLevel[])EnumChallengeLevel.class.getEnumConstants()) {
            List<Challenge> list = ModChallenges.challenges.get((Object)level);
            int complete = 0;
            for (Challenge c : list) {
                if (!c.complete) continue;
                ++complete;
            }
            this.field_146289_q.func_78276_b(EnumChatFormatting.BOLD + StatCollector.func_74838_a((String)level.getName()) + EnumChatFormatting.RESET + " (" + complete + "/" + list.size() + ")", this.left + 20, this.top + 11 + level.ordinal() * 44, 0);
        }
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
        } else if (par1GuiButton instanceof GuiButtonChallengeIcon) {
            GuiButtonChallengeIcon cbutton = (GuiButtonChallengeIcon)par1GuiButton;
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLexiconChallenge(this, cbutton.challenge));
        } else if (par1GuiButton.field_146127_k == 1336) {
            notesEnabled = !notesEnabled;
        }
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
        return new GuiLexiconChallengesList();
    }

    @Override
    public String getNotesKey() {
        return "challengelist";
    }
}

