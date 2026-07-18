/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IRenderHandler
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 */
package vazkii.botania.client.render.world;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import vazkii.botania.client.render.world.SkyblockSkyRenderer;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.world.WorldTypeSkyblock;

public final class SkyblockRenderEvents {
    @SubscribeEvent
    public void onRender(RenderWorldLastEvent event) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        if (ConfigHandler.enableFancySkybox && world.field_73011_w.field_76574_g == 0 && (ConfigHandler.enableFancySkyboxInNormalWorlds || WorldTypeSkyblock.isWorldSkyblock((World)Minecraft.func_71410_x().field_71441_e)) && !(world.field_73011_w.getSkyRenderer() instanceof SkyblockSkyRenderer)) {
            world.field_73011_w.setSkyRenderer((IRenderHandler)new SkyblockSkyRenderer());
        }
    }
}

