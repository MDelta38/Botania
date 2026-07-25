/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.wands.IWandRodOnUpdate
 *  thaumcraft.api.wands.StaffRod
 */
package flaxbeard.thaumicexploration.wand;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;

public class StaffRodNecromancer
extends StaffRod {
    public StaffRodNecromancer(String tag, int capacity, ItemStack item, int craftCost, IWandRodOnUpdate onUpdate, ResourceLocation texture) {
        super(tag, capacity, item, craftCost, onUpdate, texture);
    }
}

