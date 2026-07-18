/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.TileSignature;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.client.core.handler.RedStringRenderer;
import vazkii.botania.client.core.handler.TooltipAdditionDisplayHandler;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.common.block.subtile.functional.SubTileVinculotus;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.item.ItemTwigWand;

public class ClientTickHandler {
    public static int ticksWithLexicaOpen = 0;
    public static int pageFlipTicks = 0;
    public static int ticksInGame = 0;
    public static float partialTicks = 0.0f;
    public static float delta = 0.0f;
    public static float total = 0.0f;

    private void calcDelta() {
        float oldTotal = total;
        total = (float)ticksInGame + partialTicks;
        delta = total - oldTotal;
    }

    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            partialTicks = event.renderTickTime;
        } else {
            TooltipAdditionDisplayHandler.render();
            this.calcDelta();
        }
    }

    @SubscribeEvent
    public void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GuiScreen gui;
            LightningHandler.LightningBolt.update();
            RedStringRenderer.tick();
            ItemsRemainingRenderHandler.tick();
            if (Minecraft.func_71410_x().field_71441_e == null) {
                ManaNetworkHandler.instance.clear();
                TileCorporeaIndex.indexes.clear();
                SubTileVinculotus.existingFlowers.clear();
            }
            if ((gui = Minecraft.func_71410_x().field_71462_r) == null || !gui.func_73868_f()) {
                ItemStack stack;
                ++ticksInGame;
                partialTicks = 0.0f;
                EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
                if (player != null && (stack = player.func_71045_bC()) != null && stack.func_77973_b() instanceof ItemTwigWand) {
                    ArrayList<TileSignature> list = new ArrayList<TileSignature>(ManaNetworkHandler.instance.getAllCollectorsInWorld((World)Minecraft.func_71410_x().field_71441_e));
                    for (TileSignature sig : list) {
                        TileEntity tile;
                        if (!sig.remoteWorld || !((tile = sig.tile) instanceof IManaCollector)) continue;
                        ((IManaCollector)tile).onClientDisplayTick();
                    }
                }
            }
            int ticksToOpen = 10;
            if (gui instanceof GuiLexicon) {
                if (ticksWithLexicaOpen < 0) {
                    ticksWithLexicaOpen = 0;
                }
                if (ticksWithLexicaOpen < ticksToOpen) {
                    ++ticksWithLexicaOpen;
                }
                if (pageFlipTicks > 0) {
                    --pageFlipTicks;
                }
            } else {
                pageFlipTicks = 0;
                if (ticksWithLexicaOpen > 0) {
                    if (ticksWithLexicaOpen > ticksToOpen) {
                        ticksWithLexicaOpen = ticksToOpen;
                    }
                    --ticksWithLexicaOpen;
                }
            }
            this.calcDelta();
        }
    }

    public static void notifyPageChange() {
        if (pageFlipTicks == 0) {
            pageFlipTicks = 5;
        }
    }
}

