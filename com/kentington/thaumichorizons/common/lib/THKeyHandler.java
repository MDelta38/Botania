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
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import com.kentington.thaumichorizons.common.lib.PacketFingersToServer;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketLensChangeToServer;
import com.kentington.thaumichorizons.common.lib.PacketToggleClimbToServer;
import com.kentington.thaumichorizons.common.lib.PacketToggleInvisibleToServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import thaumcraft.api.nodes.IRevealer;

public class THKeyHandler {
    public KeyBinding keyV = new KeyBinding("Change Arcane Lens", 47, "key.categories.misc");
    public KeyBinding keyM = new KeyBinding("Activate Morphic Fingers", 49, "key.categories.misc");
    public KeyBinding keyC = new KeyBinding("Toggle Spider Climb", 46, "key.categories.misc");
    public KeyBinding keyX = new KeyBinding("Toggle Chameleon Skin", 45, "key.categories.misc");
    private boolean keyPressedM = false;
    public static long lastPressM = 0L;
    private boolean keyPressedC = false;
    public static long lastPressC = 0L;
    private boolean keyPressedX = false;
    public static long lastPressX = 0L;
    private boolean keyPressedV = false;
    public static boolean radialActive = false;
    public static boolean radialLock = false;
    public static long lastPressV = 0L;

    public THKeyHandler() {
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyV);
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyM);
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyC);
        ClientRegistry.registerKeyBinding((KeyBinding)this.keyX);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayer player;
            if (this.keyV.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedV) {
                            lastPressV = System.currentTimeMillis();
                            radialLock = false;
                        }
                        if (!radialLock && player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer) {
                            if (player.func_70093_af()) {
                                PacketHandler.INSTANCE.sendToServer((IMessage)new PacketLensChangeToServer(player, "REMOVE"));
                            } else {
                                radialActive = true;
                            }
                        }
                    }
                    this.keyPressedV = true;
                }
            } else {
                radialActive = false;
                if (this.keyPressedV) {
                    lastPressV = System.currentTimeMillis();
                }
                this.keyPressedV = false;
            }
            if (this.keyM.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedM) {
                            lastPressM = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(2) && !this.keyPressedM) {
                            player.openGui((Object)ThaumicHorizons.instance, 9, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketFingersToServer(player, player.field_71093_bK));
                        }
                    }
                    this.keyPressedM = true;
                }
            } else {
                if (this.keyPressedM) {
                    lastPressM = System.currentTimeMillis();
                }
                this.keyPressedM = false;
            }
            if (this.keyC.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedC) {
                            lastPressC = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(9) && !this.keyPressedC) {
                            boolean bl = ((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb = !((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb;
                            if (((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleClimb) {
                                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Spider Climb disabled."));
                            } else {
                                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Spider Climb enabled."));
                            }
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketToggleClimbToServer(player, player.field_71093_bK));
                        }
                    }
                    this.keyPressedC = true;
                }
            } else {
                if (this.keyPressedC) {
                    lastPressC = System.currentTimeMillis();
                }
                this.keyPressedC = false;
            }
            if (this.keyX.func_151470_d()) {
                if (FMLClientHandler.instance().getClient().field_71415_G) {
                    player = event.player;
                    if (player != null) {
                        if (!this.keyPressedX) {
                            lastPressX = System.currentTimeMillis();
                        }
                        if (((EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(10) && !this.keyPressedX) {
                            boolean bl = ((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleInvisible = !((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleInvisible;
                            if (((EntityInfusionProperties)player.getExtendedProperties((String)"CreatureInfusion")).toggleInvisible) {
                                player.func_70618_n(Potion.field_76441_p.field_76415_H);
                                player.func_82142_c(false);
                                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Chameleon Skin disabled."));
                            } else {
                                PotionEffect effect = new PotionEffect(Potion.field_76441_p.field_76415_H, Integer.MAX_VALUE, 0, true);
                                effect.setCurativeItems(new ArrayList());
                                player.func_70690_d(effect);
                                player.func_82142_c(true);
                                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + "Chameleon Skin enabled."));
                            }
                            PacketHandler.INSTANCE.sendToServer((IMessage)new PacketToggleInvisibleToServer(player, player.field_71093_bK));
                        }
                    }
                    this.keyPressedX = true;
                }
            } else {
                if (this.keyPressedX) {
                    lastPressX = System.currentTimeMillis();
                }
                this.keyPressedX = false;
            }
        }
    }
}

