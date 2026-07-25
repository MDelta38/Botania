/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.OreDictionary
 *  org.apache.logging.log4j.Level
 */
package witchinggadgets.common.items;

import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;

public class ItemClusters
extends Item {
    public static String[] subNames = new String[]{"Aluminum", "Cobalt", "Ardite", "Nickel", "FZDarkIron", "Manganese", "Zinc", "Platinum", "Ignatius", "ShadowIron", "Lemurite", "Midasium", "Vyroxeres", "Ceruclase", "Alduorite", "Kalendrite", "Vulcanite", "Sanguinite", "Prometheum", "DeepIron", "Infuscolium", "Oureclase", "AstralSilver", "Carmot", "Mithril", "Rubracium", "Orichalcum", "Adamantine", "Atlarus", "Eximite", "Meutoite"};
    public static HashMap<String, Integer[]> materialMap = new HashMap();
    IIcon iconMetal;
    IIcon[] iconOverlay = new IIcon[3];

    public ItemClusters() {
        this.field_77777_bU = 64;
        this.func_77637_a(WitchingGadgets.tabWG);
        this.func_77627_a(true);
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0 && materialMap.get(subNames[stack.func_77960_j()]) != null) {
            return materialMap.get(subNames[stack.func_77960_j()])[0];
        }
        return 0xFFFFFF;
    }

    public static ItemStack getCluster(String ore) {
        if (WGConfig.allowClusters) {
            for (int sn = 0; sn < subNames.length; ++sn) {
                if (!subNames[sn].equalsIgnoreCase(ore)) continue;
                return new ItemStack(WGContent.ItemCluster, 1, sn);
            }
        }
        return null;
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.iconMetal = iconRegister.func_94245_a("witchinggadgets:cluster_metal");
        this.iconOverlay[0] = iconRegister.func_94245_a("witchinggadgets:cluster_overlay");
        this.iconOverlay[1] = iconRegister.func_94245_a("witchinggadgets:cluster_overlayNether");
        this.iconOverlay[2] = iconRegister.func_94245_a("witchinggadgets:cluster_overlayEnd");
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon func_77618_c(int damage, int pass) {
        if (pass == 0) {
            return this.iconMetal;
        }
        if (materialMap.get(subNames[damage]) != null) {
            return this.iconOverlay[materialMap.get(subNames[damage])[1]];
        }
        return this.iconOverlay[0];
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77618_c(stack.func_77960_j(), pass);
    }

    public String func_77653_i(ItemStack stack) {
        String ss = "";
        if (!OreDictionary.getOres((String)("ingot" + subNames[stack.func_77960_j()])).isEmpty()) {
            ItemStack ingot = (ItemStack)OreDictionary.getOres((String)("ingot" + subNames[stack.func_77960_j()])).get(0);
            int limit = ingot.func_82833_r().lastIndexOf(" ");
            ss = ingot.func_82833_r().substring(0, Math.max(0, limit));
        }
        return StatCollector.func_74837_a((String)(this.func_77657_g(stack) + ".name"), (Object[])new Object[]{ss}).trim();
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        if (WGConfig.allowClusters) {
            for (int iOre = 0; iOre < subNames.length; ++iOre) {
                if (OreDictionary.getOres((String)("ore" + subNames[iOre])).isEmpty() || OreDictionary.getOres((String)("ingot" + subNames[iOre])).isEmpty()) continue;
                itemList.add(new ItemStack(item, 1, iOre));
            }
        }
    }

    public static void setupClusters() {
        if (WGConfig.allowClusters) {
            for (String ore : subNames) {
                if (OreDictionary.getOres((String)("ore" + ore)).isEmpty() || OreDictionary.getOres((String)("ingot" + ore)).isEmpty()) continue;
                try {
                    List<Integer> colList = ClientUtilities.getItemColours((ItemStack)OreDictionary.getOres((String)("ore" + ore)).get(0));
                    if (colList.isEmpty()) continue;
                    int oreBlockColour = colList.get(0);
                    int[] rgb = new int[]{oreBlockColour >> 16 & 0xFF, oreBlockColour >> 8 & 0xFF, oreBlockColour & 0xFF};
                    int clustertype = rgb[0] > rgb[2] && rgb[1] > rgb[2] ? 2 : (rgb[0] > rgb[1] && rgb[0] > rgb[2] ? 1 : 0);
                    List<Integer> colours = ClientUtilities.getItemColours((ItemStack)OreDictionary.getOres((String)("ingot" + ore)).get(0));
                    int colour = ClientUtilities.getVibrantColourToInt(colours.get((int)((double)colours.size() * 0.65)));
                    colour = ClientUtilities.getVibrantColourToInt(colour);
                    materialMap.put(ore, new Integer[]{colour, clustertype});
                }
                catch (Exception e) {
                    WitchingGadgets.logger.log(Level.ERROR, "Error setting up cluster for " + ore);
                }
            }
        }
    }
}

