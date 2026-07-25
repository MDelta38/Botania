/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.wands.WandCap
 */
package thaumic.tinkerer.common.item.kami.wand;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandCap;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;

public class CapIchor
extends WandCap {
    ResourceLocation res = new ResourceLocation("ttinkerer:textures/model/capIchor.png");

    public CapIchor() {
        super("ICHOR", 0.7f, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 4), 10);
    }

    public CapIchor(String s) {
        super(s, 0.7f, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 10), 10);
    }

    public ResourceLocation getTexture() {
        return this.res;
    }
}

