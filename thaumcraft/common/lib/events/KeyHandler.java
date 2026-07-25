/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 */
package thaumcraft.common.lib.events;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.items.armor.Hover;
import thaumcraft.common.items.armor.ItemHoverHarness;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketFocusChangeToServer;
import thaumcraft.common.lib.network.misc.PacketItemKeyToServer;

public class KeyHandler {
    public KeyBinding keyF = new KeyBinding("Change Wand Focus", 33, "key.categories.misc");
    public KeyBinding keyH = new KeyBinding("Activate Hover Harness", 35, "key.categories.misc");
    public KeyBinding keyG = new KeyBinding("Misc Wand Toggle", 34, "key.categories.misc");
    private boolean keyPressedF = false;
    private boolean keyPressedH = false;
    private boolean keyPressedG = false;
    public static boolean radialActive = false;
    public static boolean radialLock = false;
    public static long lastPressF = 0L;
    public static long lastPressH = 0L;
    public static long lastPressG = 0L;

    public KeyHandler() {
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyF);
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyH);
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyG);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayer player;
            if (this.keyF.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedF) {
                            lastPressF = System.currentTimeMillis();
                            radialLock = false;
                        }
                        if (!radialLock && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)player.func_70694_bm().func_77973_b()).isSceptre(player.func_70694_bm())) {
                            if (player.func_70093_af()) {
                                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFocusChangeToServer(player, "REMOVE"));
                            } else {
                                radialActive = true;
                            }
                        } else if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemGolemBell && !this.keyPressedF) {
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketItemKeyToServer(player, 0));
                        }
                    }
                    this.keyPressedF = true;
                }
            } else {
                radialActive = false;
                if (this.keyPressedF) {
                    lastPressF = System.currentTimeMillis();
                }
                this.keyPressedF = false;
            }
            if (this.keyH.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedH) {
                            lastPressH = System.currentTimeMillis();
                        }
                        if (player.field_71071_by.func_70440_f(2) != null && player.field_71071_by.func_70440_f(2).func_77973_b() instanceof ItemHoverHarness && !this.keyPressedH) {
                            Hover.toggleHover(player, player.func_145782_y(), player.field_71071_by.func_70440_f(2));
                        }
                    }
                    this.keyPressedH = true;
                }
            } else {
                if (this.keyPressedH) {
                    lastPressH = System.currentTimeMillis();
                }
                this.keyPressedH = false;
            }
            if (this.keyG.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null && !this.keyPressedG) {
                        lastPressG = System.currentTimeMillis();
                        PacketHandler.INSTANCE.sendToServer((IMessage)new PacketItemKeyToServer(player, 1));
                    }
                    this.keyPressedG = true;
                }
            } else {
                if (this.keyPressedG) {
                    lastPressG = System.currentTimeMillis();
                }
                this.keyPressedG = false;
            }
        }
    }
}

