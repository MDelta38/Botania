/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraftforge.common.ForgeHooks
 *  travellersgear.api.TravellersGearAPI
 */
package witchinggadgets.common.util;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.ForgeHooks;
import travellersgear.api.TravellersGearAPI;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;
import witchinggadgets.common.util.network.message.MessagePrimordialGlove;

public class WGKeyHandler {
    public static KeyBinding thaumcraftFKey;
    public static KeyBinding jumpKey;
    public boolean[] keyDown = new boolean[]{false, false, false};
    public static float gemRadial;
    public static boolean gemLock;
    private boolean isJumping = false;
    private int multiJumps = 0;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {
            if (thaumcraftFKey == null) {
                for (KeyBinding kb : Minecraft.func_71410_x().field_71474_y.field_74324_K) {
                    if (kb.func_151466_e() != "key.categories.misc" || kb.func_151464_g() != "Change Wand Focus") continue;
                    thaumcraftFKey = kb;
                }
            }
            if (jumpKey == null) {
                jumpKey = Minecraft.func_71410_x().field_71474_y.field_74314_A;
            }
            EntityPlayer player = event.player;
            if (FMLClientHandler.instance().getClient().field_71415_G) {
                if (jumpKey.func_151470_d() && !this.keyDown[2] && Minecraft.func_71410_x().field_71462_r == null) {
                    if (this.isJumping && this.multiJumps > 0) {
                        event.player.field_70181_x = 0.42;
                        event.player.field_70143_R = 0.0f;
                        if (event.player.func_70644_a(Potion.field_76430_j)) {
                            event.player.field_70181_x += (double)((float)(event.player.func_70660_b(Potion.field_76430_j).func_76458_c() + 1) * 0.1f);
                        }
                        ForgeHooks.onLivingJump((EntityLivingBase)event.player);
                        --this.multiJumps;
                    }
                    if (!this.isJumping) {
                        this.multiJumps = 0;
                        this.isJumping = event.player.field_70160_al;
                        if (TravellersGearAPI.getExtendedInventory((EntityPlayer)event.player)[1] != null && TravellersGearAPI.getExtendedInventory((EntityPlayer)event.player)[1].func_77973_b().equals(WGContent.ItemMagicalBaubles) && TravellersGearAPI.getExtendedInventory((EntityPlayer)event.player)[1].func_77960_j() == 0) {
                            ++this.multiJumps;
                        }
                    }
                    this.keyDown[2] = true;
                } else if (this.keyDown[2]) {
                    this.keyDown[2] = false;
                }
            }
            float step = WGConfig.radialSpeed;
            if (thaumcraftFKey != null && thaumcraftFKey.func_151470_d() && !this.keyDown[1]) {
                if (player.func_70093_af() && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemPrimordialGlove) {
                    WitchingGadgets.packetHandler.sendToServer((IMessage)new MessagePrimordialGlove(player, 1, 0));
                } else if (gemLock) {
                    gemLock = false;
                    this.keyDown[1] = true;
                } else if (FMLClientHandler.instance().getClient().field_71415_G && player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof ItemPrimordialGlove) {
                    if (gemRadial < 1.0f) {
                        gemRadial += step;
                    }
                    if (gemRadial > 1.0f) {
                        gemRadial = 1.0f;
                    }
                    if (gemRadial >= 1.0f) {
                        gemLock = true;
                        this.keyDown[1] = true;
                    }
                }
            } else {
                if (this.keyDown[1] && !thaumcraftFKey.func_151470_d()) {
                    this.keyDown[1] = false;
                }
                if (!gemLock) {
                    if (gemRadial > 0.0f) {
                        gemRadial -= step;
                    }
                    if (gemRadial < 0.0f) {
                        gemRadial = 0.0f;
                    }
                }
            }
        }
        if (this.isJumping && event.player.field_70122_E) {
            event.player.field_70160_al = false;
            this.isJumping = false;
        }
    }

    static {
        gemLock = false;
    }
}

