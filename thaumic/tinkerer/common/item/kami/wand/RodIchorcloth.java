/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.wands.WandRod
 */
package thaumic.tinkerer.common.item.kami.wand;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.wands.WandRod;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;

public class RodIchorcloth
extends WandRod {
    ResourceLocation res = new ResourceLocation("ttinkerer:textures/model/rodIchorcloth.png");

    public RodIchorcloth() {
        super("ICHORCLOTH", 1000, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 5), 10);
        this.setGlowing(true);
    }

    public RodIchorcloth(String s) {
        super(s, 1000, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 9), 10);
        this.setGlowing(true);
    }

    public ResourceLocation getTexture() {
        return this.res;
    }
}

