/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.client.gui;

import java.util.ArrayList;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumcraft.common.lib.research.ScanManager;

public class MappingThread
implements Runnable {
    Map<String, Integer> idMappings = null;

    public MappingThread(Map<String, Integer> idMappings) {
        this.idMappings = idMappings;
    }

    @Override
    public void run() {
        for (Integer id : this.idMappings.values()) {
            try {
                Item i = Item.func_150899_d((int)id);
                if (i != null) {
                    ArrayList q = new ArrayList();
                    i.func_150895_a(i, i.func_77640_w(), q);
                    if (q == null || q.size() <= 0) continue;
                    for (ItemStack stack : q) {
                        GuiResearchRecipe.putToCache(ScanManager.generateItemHash(i, stack.func_77960_j()), stack.func_77946_l());
                    }
                    continue;
                }
                Block b = Block.func_149729_e((int)id);
                for (int a = 0; a < 16; ++a) {
                    GuiResearchRecipe.putToCache(ScanManager.generateItemHash(Item.func_150898_a((Block)b), a), new ItemStack(b, 1, a));
                }
            }
            catch (Exception exception) {
            }
        }
    }
}

