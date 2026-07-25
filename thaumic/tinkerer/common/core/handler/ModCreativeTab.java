/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.core.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.registry.ItemStackCompatator;

public class ModCreativeTab
extends CreativeTabs {
    public static ModCreativeTab INSTANCE;
    public ArrayList<ItemStack> creativeTabQueue = new ArrayList();
    ItemStack displayItem;
    List list = new ArrayList();

    public ModCreativeTab() {
        super("ThaumicTinkerer");
    }

    public ItemStack func_151244_d() {
        return this.displayItem;
    }

    public Item func_78016_d() {
        return ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class);
    }

    public void func_78018_a(List list) {
        list.addAll(this.list);
    }

    public void addWand() {
        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting);
        ((ItemWandCasting)wand.func_77973_b()).setRod(wand, ConfigItems.WAND_ROD_SILVERWOOD);
        ((ItemWandCasting)wand.func_77973_b()).setCap(wand, ConfigItems.WAND_CAP_THAUMIUM);
        ((ItemWandCasting)wand.func_77973_b()).storeAllVis(wand, new AspectList().add(Aspect.AIR, 10000).add(Aspect.EARTH, 10000).add(Aspect.FIRE, 10000).add(Aspect.WATER, 10000).add(Aspect.ORDER, 10000).add(Aspect.ENTROPY, 10000));
        if (this.list != null) {
            this.list.add(wand);
        }
        this.displayItem = wand;
        if (ConfigHandler.enableKami) {
            ItemStack wand1 = new ItemStack(ConfigItems.itemWandCasting);
            ((ItemWandCasting)wand1.func_77973_b()).setRod(wand1, ThaumicTinkerer.proxy.rodIchor);
            ((ItemWandCasting)wand1.func_77973_b()).setCap(wand1, ThaumicTinkerer.proxy.capIchor);
            ((ItemWandCasting)wand1.func_77973_b()).storeAllVis(wand1, new AspectList().add(Aspect.AIR, 100000).add(Aspect.EARTH, 100000).add(Aspect.FIRE, 100000).add(Aspect.WATER, 100000).add(Aspect.ORDER, 100000).add(Aspect.ENTROPY, 100000));
            if (this.list != null) {
                this.list.add(wand1);
            }
            this.displayItem = wand1;
        }
    }

    public void addItem(Item item) {
        item.func_150895_a(item, (CreativeTabs)this, this.creativeTabQueue);
    }

    public void addBlock(Block block) {
        block.func_149666_a(Item.func_150898_a((Block)block), (CreativeTabs)this, this.creativeTabQueue);
    }

    public void addAllItemsAndBlocks() {
        Collections.sort(this.creativeTabQueue, new ItemStackCompatator());
        this.list.addAll(this.creativeTabQueue);
    }
}

