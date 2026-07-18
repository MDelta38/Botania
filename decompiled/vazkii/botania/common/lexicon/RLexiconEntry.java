/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.stats.Achievement
 */
package vazkii.botania.common.lexicon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.stats.Achievement;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class RLexiconEntry
extends BLexiconEntry {
    Achievement a;

    public RLexiconEntry(String unlocalizedName, LexiconCategory category, Achievement a) {
        super(unlocalizedName, category);
        this.setKnowledgeType(BotaniaAPI.relicKnowledge);
        this.a = a;
        if (a != null) {
            this.setIcon(a.field_75990_d.func_77946_l());
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean isVisible() {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        return this.a == null || player.field_71075_bZ.field_75098_d || player.func_146107_m().func_77443_a(this.a);
    }
}

