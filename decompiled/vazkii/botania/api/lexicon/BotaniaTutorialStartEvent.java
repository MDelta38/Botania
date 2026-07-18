/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 */
package vazkii.botania.api.lexicon;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.Queue;
import vazkii.botania.api.lexicon.LexiconEntry;

public class BotaniaTutorialStartEvent
extends Event {
    public final Queue<LexiconEntry> tutorial;

    public BotaniaTutorialStartEvent(Queue<LexiconEntry> tutorial) {
        this.tutorial = tutorial;
    }
}

