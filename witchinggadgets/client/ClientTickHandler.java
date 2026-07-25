/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$RenderTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.fx.particles.FXWisp
 */
package witchinggadgets.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXWisp;
import witchinggadgets.client.ClientUtilities;

public class ClientTickHandler {
    public static HashMap<ChunkCoordinates, Integer> oreHighlightMap = new HashMap();
    public static HashMap<ChunkCoordinates, Object> oreHighlightBeamMap = new HashMap();
    static int highlight;

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event) {
        if (!(Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        if (player == null || oreHighlightMap.isEmpty()) {
            oreHighlightMap.clear();
            oreHighlightBeamMap.clear();
            highlight = 0;
            return;
        }
        Map.Entry e = oreHighlightMap.entrySet().toArray(new Map.Entry[0])[highlight];
        if (player.field_70170_p.func_82737_E() % 30L == 0L) {
            float x = (float)((ChunkCoordinates)e.getKey()).field_71574_a + 0.5f;
            float y = (float)((ChunkCoordinates)e.getKey()).field_71572_b + 0.75f;
            float z = (float)((ChunkCoordinates)e.getKey()).field_71573_c + 0.5f;
            double[] hand = ClientUtilities.getPlayerHandPos(player, true);
            FXWisp ef = new FXWisp(player.field_70170_p, hand[0], hand[1], hand[2], (double)x, (double)y, (double)z, 0.05f, 3);
            ef.field_70145_X = true;
            ef.setGravity(0.0f);
            ef.shrink = true;
            ParticleEngine.instance.addEffect(player.field_70170_p, (EntityFX)ef);
            if (++highlight >= oreHighlightMap.size()) {
                highlight = 0;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        Iterator<Map.Entry<ChunkCoordinates, Integer>> it = oreHighlightMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<ChunkCoordinates, Integer> e = it.next();
            oreHighlightMap.put(e.getKey(), e.getValue() - 1);
            if (oreHighlightMap.get(e.getKey()) > 0) continue;
            oreHighlightBeamMap.remove(e.getKey());
            it.remove();
            highlight = 0;
        }
    }
}

