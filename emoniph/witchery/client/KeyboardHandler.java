/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.network.PacketClearFallDamage;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyboardHandler {
    private final List<KeyInfo> bindings = new ArrayList<KeyInfo>();
    private final KeyInfo JUMP;
    private final KeyInfo HOTBAR1;

    public KeyboardHandler() {
        this.JUMP = new KeyInfo(Minecraft.func_71410_x().field_71474_y.field_74314_A, this.bindings){
            private boolean isJumping;
            private int remainingJumps;
            private boolean clearFall;

            @Override
            protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
                if (!player.field_71075_bZ.field_75098_d && !end) {
                    if (this.isJumping) {
                        if (this.remainingJumps > 0) {
                            int jumpsLeft = this.remainingJumps--;
                            player.field_70181_x = 0.42;
                            if (player.func_70644_a(Potion.field_76430_j)) {
                                player.field_70181_x += 0.1 * (double)(1 + player.func_70660_b(Potion.field_76430_j).func_76458_c());
                            }
                        }
                    } else {
                        this.isJumping = player.field_70160_al;
                        if (player.func_70644_a(Witchery.Potions.DOUBLE_JUMP)) {
                            this.remainingJumps += 1 + player.func_70660_b(Witchery.Potions.DOUBLE_JUMP).func_76458_c();
                        }
                    }
                }
                if (this.clearFall) {
                    this.clearFall = false;
                    player.field_70143_R = 0.0f;
                    Witchery.packetPipeline.sendToServer(new PacketClearFallDamage());
                }
            }

            @Override
            protected void onTick(EntityPlayer player, boolean end) {
                if (player.field_70122_E) {
                    this.isJumping = false;
                    this.remainingJumps = 0;
                }
            }
        };
        this.HOTBAR1 = new KeyInfo(Minecraft.func_71410_x().field_71474_y.field_151456_ac[0], this.bindings){

            @Override
            protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
                ExtendedPlayer playerEx;
                if (!end && (playerEx = ExtendedPlayer.get(player)).isVampire() && !Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146241_e()) {
                    int MAXPOWER = playerEx.getMaxAvailablePowerOrdinal();
                    if (player.field_71071_by.field_70461_c == 0) {
                        int power = playerEx.getSelectedVampirePower().ordinal();
                        if (power == MAXPOWER) {
                            playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
                        } else {
                            playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.values()[power + 1], true);
                        }
                    }
                }
            }

            @Override
            protected void onKeyUp(EntityPlayer player, boolean end) {
            }

            @Override
            protected void onTick(EntityPlayer player, boolean end) {
            }
        };
        for (int i = 1; i < Minecraft.func_71410_x().field_71474_y.field_151456_ac.length; ++i) {
            KeyBinding binding = Minecraft.func_71410_x().field_71474_y.field_151456_ac[i];
            new KeyInfo(binding, this.bindings){

                @Override
                protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
                    ExtendedPlayer playerEx;
                    if (!end && (playerEx = ExtendedPlayer.get(player)).isVampire() && playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE) {
                        playerEx.setSelectedVampirePower(ExtendedPlayer.VampirePower.NONE, true);
                    }
                }
            };
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.side == Side.CLIENT) {
            Minecraft mc = Minecraft.func_71410_x();
            EntityClientPlayerMP player = mc.field_71439_g;
            if (player != null) {
                for (KeyInfo keyInfo : this.bindings) {
                    keyInfo.doTick((EntityPlayer)player, event.phase == TickEvent.Phase.END);
                }
            }
        }
    }

    private static abstract class KeyInfo {
        private final KeyBinding bind;
        private boolean repeat;
        private boolean down;

        public KeyInfo(KeyBinding bind, List<KeyInfo> bindings) {
            this.bind = bind;
            bindings.add(this);
        }

        public void doTick(EntityPlayer player, boolean end) {
            boolean newlyDown;
            int keyCode = this.bind.func_151463_i();
            boolean bl = newlyDown = keyCode < 0 ? Mouse.isButtonDown((int)(keyCode + 100)) : Keyboard.isKeyDown((int)keyCode);
            if (newlyDown != this.down || newlyDown && this.repeat) {
                if (newlyDown) {
                    this.onKeyDown(player, newlyDown != this.down, end);
                } else {
                    this.onKeyUp(player, end);
                }
                if (end) {
                    this.down = newlyDown;
                }
            }
            if (end) {
                this.onTick(player, end);
            }
        }

        protected void onKeyDown(EntityPlayer player, boolean repeated, boolean end) {
        }

        protected void onKeyUp(EntityPlayer player, boolean end) {
        }

        protected void onTick(EntityPlayer player, boolean end) {
        }
    }
}

