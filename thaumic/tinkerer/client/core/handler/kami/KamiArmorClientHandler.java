/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.util.StatCollector
 */
package thaumic.tinkerer.client.core.handler.kami;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.util.StatCollector;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.network.packet.kami.PacketToggleArmor;

public class KamiArmorClientHandler {
    public static boolean ArmorEnabled = true;

    public static void SetStatus(boolean status) {
        if (FMLClientHandler.instance().getClient().field_71462_r == null) {
            if (status) {
                ToolModeHUDHandler.setTooltip(StatCollector.func_74838_a((String)"ttmisc.enableAllArmor"));
            } else {
                ToolModeHUDHandler.setTooltip(StatCollector.func_74838_a((String)"ttmisc.disableAllArmor"));
            }
            ArmorEnabled = status;
            ThaumicTinkerer.netHandler.sendToServer((IMessage)new PacketToggleArmor(status));
        }
    }
}

