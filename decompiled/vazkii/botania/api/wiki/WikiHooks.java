/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$UniqueIdentifier
 *  net.minecraft.block.Block
 */
package vazkii.botania.api.wiki;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import vazkii.botania.api.wiki.IWikiProvider;
import vazkii.botania.api.wiki.SimpleWikiProvider;

public class WikiHooks {
    private static final IWikiProvider FALLBACK_PROVIDER = new SimpleWikiProvider("FTB Wiki", "http://ftb.gamepedia.com/%s");
    private static final Map<String, IWikiProvider> modWikis = new HashMap<String, IWikiProvider>();

    public static IWikiProvider getWikiFor(Block block) {
        GameRegistry.UniqueIdentifier mod = GameRegistry.findUniqueIdentifierFor((Block)block);
        return WikiHooks.getWikiFor(mod == null ? "" : mod.modId.toLowerCase());
    }

    public static IWikiProvider getWikiFor(String mod) {
        if (!modWikis.containsKey(mod)) {
            modWikis.put(mod, FALLBACK_PROVIDER);
        }
        return modWikis.get(mod);
    }

    public static void registerModWiki(String mod, IWikiProvider provider) {
        modWikis.put(mod.toLowerCase(), provider);
    }
}

