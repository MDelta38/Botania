/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedOutEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package thaumic.tinkerer.common.network;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.kami.KamiArmorHandler;
import thaumic.tinkerer.common.item.foci.ItemFocusHeal;
import thaumic.tinkerer.common.item.foci.ItemFocusSmelt;
import thaumic.tinkerer.common.item.kami.armor.ItemGemBoots;
import thaumic.tinkerer.common.item.kami.armor.ItemGemChest;
import thaumic.tinkerer.common.network.packet.kami.PacketToggleArmor;

public class PlayerTracker {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            ItemFocusHeal.playerHealData.put(event.player.func_146103_bH().getName(), 0);
            ThaumicTinkerer.netHandler.sendTo((IMessage)new PacketToggleArmor(KamiArmorHandler.getArmorStatus(event.player)), (EntityPlayerMP)event.player);
        }
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        EntityPlayer player = event.player;
        String username = player.func_146103_bH().getName();
        ItemFocusSmelt.playerData.remove(username);
        ItemFocusHeal.playerHealData.remove(username);
        ItemGemChest.playersWithFlight.remove(username + ":false");
        ItemGemChest.playersWithFlight.remove(username + ":true");
        ItemGemBoots.playersWith1Step.remove(username);
    }
}

