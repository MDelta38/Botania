/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 */
package vazkii.botania.common.core.handler;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.event.FMLInterModComms;
import vazkii.botania.common.item.equipment.bauble.ItemMagnetRing;

public final class IMCHandler {
    public static void processMessages(ImmutableList<FMLInterModComms.IMCMessage> messageList) {
        for (FMLInterModComms.IMCMessage message : messageList) {
            if (message == null || message.key == null || !message.key.equals("blackListItem") || !message.isStringMessage()) continue;
            String value = message.getStringValue();
            ItemMagnetRing.addItemToBlackList(value);
        }
    }
}

