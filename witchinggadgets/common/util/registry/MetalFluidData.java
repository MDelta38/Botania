/*
 * Decompiled with CFR 0.152.
 */
package witchinggadgets.common.util.registry;

import java.util.HashMap;

public class MetalFluidData {
    static HashMap<String, String> oreFluidName = new HashMap();
    static HashMap<String, Integer> oreFluidTemp = new HashMap();

    public static int getOreFluidTemp(String ore) {
        if (oreFluidTemp.get(ore) != null) {
            return oreFluidTemp.get(ore);
        }
        return 0;
    }

    public static String getOreFluidName(String ore) {
        if (oreFluidTemp.get(ore) != null) {
            return oreFluidName.get(ore);
        }
        return "";
    }

    public static void addOreFluid(String ore, String fluidName, int temp) {
        oreFluidName.put(ore, fluidName);
        oreFluidTemp.put(ore, temp);
    }

    static {
        MetalFluidData.addOreFluid("Aluminum", "aluminum.molten", 350);
        MetalFluidData.addOreFluid("Cobalt", "cobalt.molten", 650);
        MetalFluidData.addOreFluid("Ardite", "ardite.molten", 650);
        MetalFluidData.addOreFluid("Nickel", "nickel.molten", 400);
        MetalFluidData.addOreFluid("FzDarkIron", "fzdarkiron.molten", 600);
        MetalFluidData.addOreFluid("Manganese", "manganese.molten", 700);
        MetalFluidData.addOreFluid("Zinc", "zinc.molten", 550);
        MetalFluidData.addOreFluid("Platinum", "platinum.molten", 550);
        MetalFluidData.addOreFluid("Ignatius", "ignatius.molten", 550);
        MetalFluidData.addOreFluid("ShadowIron", "shadow.iron.molten", 550);
        MetalFluidData.addOreFluid("Lemurite", "lemurite.molten", 550);
        MetalFluidData.addOreFluid("Midasium", "midasium.molten", 550);
        MetalFluidData.addOreFluid("Vyroxeres", "vyroxeres.molten", 550);
        MetalFluidData.addOreFluid("Ceruclase", "ceruclase.molten", 550);
        MetalFluidData.addOreFluid("Alduorite", "alduorite.molten", 550);
        MetalFluidData.addOreFluid("Kalendrite", "kalendrite.molten", 550);
        MetalFluidData.addOreFluid("Vulcanite", "vulcanite.molten", 550);
        MetalFluidData.addOreFluid("Sanguinite", "sanguinite.molten", 550);
        MetalFluidData.addOreFluid("Prometheum", "prometheum.molten", 550);
        MetalFluidData.addOreFluid("DeepIron", "deep.iron.molten", 550);
        MetalFluidData.addOreFluid("Infuscolium", "infuscolium.molten", 550);
        MetalFluidData.addOreFluid("Oureclase", "oureclase.molten", 550);
        MetalFluidData.addOreFluid("AstralSilver", "astral.silver.molten", 550);
        MetalFluidData.addOreFluid("Carmot", "carmot.molten", 550);
        MetalFluidData.addOreFluid("Mithril", "mithril.molten", 550);
        MetalFluidData.addOreFluid("Rubracium", "rubracium.molten", 550);
        MetalFluidData.addOreFluid("Orichalcum", "orichalcum.molten", 550);
        MetalFluidData.addOreFluid("Adamantine", "adamantine.molten", 550);
        MetalFluidData.addOreFluid("Atlarus", "atlarus.molten", 550);
        MetalFluidData.addOreFluid("Eximite", "eximite.molten", 600);
        MetalFluidData.addOreFluid("Meutoite", "meutoite.molten", 600);
    }
}

