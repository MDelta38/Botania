/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate
 *  net.minecraftforge.event.terraingen.DecorateBiomeEvent$Decorate$EventType
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import vazkii.botania.api.item.IFlowerlessBiome;
import vazkii.botania.api.item.IFlowerlessWorld;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.handler.ConfigHandler;

public class BiomeDecorationHandler {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onWorldDecoration(DecorateBiomeEvent.Decorate event) {
        if ((event.getResult() == Event.Result.ALLOW || event.getResult() == Event.Result.DEFAULT) && event.type == DecorateBiomeEvent.Decorate.EventType.FLOWERS) {
            int color;
            int y;
            int z;
            int x;
            int i;
            boolean flowers = true;
            if (event.world.field_73011_w instanceof IFlowerlessWorld) {
                flowers = ((IFlowerlessWorld)event.world.field_73011_w).generateFlowers(event.world);
            } else if (event.world.func_72807_a(event.chunkX, event.chunkZ) instanceof IFlowerlessBiome) {
                flowers = ((IFlowerlessBiome)event.world.func_72807_a(event.chunkX, event.chunkZ)).canGenerateFlowers(event.world, event.chunkX, event.chunkZ);
            }
            if (!flowers) {
                return;
            }
            int dist = Math.min(8, Math.max(1, ConfigHandler.flowerPatchSize));
            for (i = 0; i < ConfigHandler.flowerQuantity; ++i) {
                if (event.rand.nextInt(ConfigHandler.flowerPatchChance) != 0) continue;
                x = event.chunkX + event.rand.nextInt(16) + 8;
                z = event.chunkZ + event.rand.nextInt(16) + 8;
                y = event.world.func_72825_h(x, z);
                color = event.rand.nextInt(16);
                boolean primus = event.rand.nextInt(380) == 0;
                for (int j = 0; j < ConfigHandler.flowerDensity * ConfigHandler.flowerPatchChance; ++j) {
                    int z1;
                    int y1;
                    int x1 = x + event.rand.nextInt(dist * 2) - dist;
                    if (!event.world.func_147437_c(x1, y1 = y + event.rand.nextInt(4) - event.rand.nextInt(4), z1 = z + event.rand.nextInt(dist * 2) - dist) || event.world.field_73011_w.field_76576_e && y1 >= 127 || !ModBlocks.flower.func_149718_j(event.world, x1, y1, z1)) continue;
                    if (primus) {
                        event.world.func_147465_d(x1, y1, z1, ModBlocks.specialFlower, 0, 2);
                        TileSpecialFlower flower = (TileSpecialFlower)event.world.func_147438_o(x1, y1, z1);
                        flower.setSubTile(event.rand.nextBoolean() ? "nightshadePrime" : "daybloomPrime");
                        SubTileDaybloom subtile = (SubTileDaybloom)flower.getSubTile();
                        subtile.setPrimusPosition();
                        continue;
                    }
                    event.world.func_147465_d(x1, y1, z1, ModBlocks.flower, color, 2);
                    if (!(event.rand.nextDouble() < ConfigHandler.flowerTallChance) || !((BlockModFlower)ModBlocks.flower).func_149851_a(event.world, x1, y1, z1, false)) continue;
                    BlockModFlower.placeDoubleFlower(event.world, x1, y1, z1, color, 0);
                }
            }
            for (i = 0; i < ConfigHandler.mushroomQuantity; ++i) {
                x = event.chunkX + event.rand.nextInt(16) + 8;
                z = event.chunkZ + event.rand.nextInt(16) + 8;
                y = event.rand.nextInt(26) + 4;
                color = event.rand.nextInt(16);
                if (!event.world.func_147437_c(x, y, z) || !ModBlocks.mushroom.func_149718_j(event.world, x, y, z)) continue;
                event.world.func_147465_d(x, y, z, ModBlocks.mushroom, color, 2);
            }
        }
    }
}

