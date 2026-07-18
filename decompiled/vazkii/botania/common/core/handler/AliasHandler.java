/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent$MissingMapping
 *  cpw.mods.fml.common.registry.GameRegistry$Type
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class AliasHandler {
    public static void onMissingMappings(FMLMissingMappingsEvent event) {
        List mappings = event.get();
        for (FMLMissingMappingsEvent.MissingMapping mapping : mappings) {
            String name = mapping.name.substring("botania:".length() * 2);
            if (mapping.type == GameRegistry.Type.ITEM) {
                AliasHandler.remapItem(mapping, AliasHandler.getItem(name));
                continue;
            }
            AliasHandler.remapBlock(mapping, AliasHandler.getBlock(name));
        }
    }

    private static void remapItem(FMLMissingMappingsEvent.MissingMapping mapping, Item item) {
        if (item != null) {
            mapping.remap(item);
        }
    }

    private static void remapBlock(FMLMissingMappingsEvent.MissingMapping mapping, Block block) {
        if (block != null) {
            mapping.remap(block);
        }
    }

    private static Item getItem(String name) {
        for (Object o : Item.field_150901_e.func_148742_b()) {
            Item i = (Item)Item.field_150901_e.func_82594_a(o);
            if (!i.func_77658_a().substring("item.".length()).equals(name)) continue;
            return i;
        }
        return null;
    }

    private static Block getBlock(String name) {
        for (Object o : Block.field_149771_c.func_148742_b()) {
            Block b = (Block)Block.field_149771_c.func_82594_a(o);
            if (!b.func_149739_a().substring("tile.".length()).equals(name)) continue;
            return b;
        }
        return null;
    }
}

