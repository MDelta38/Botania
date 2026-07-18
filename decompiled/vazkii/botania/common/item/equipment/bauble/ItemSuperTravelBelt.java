/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.item.equipment.bauble;

import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.item.equipment.bauble.ItemTravelBelt;

public class ItemSuperTravelBelt
extends ItemTravelBelt {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/superTravelBelt.png");

    public ItemSuperTravelBelt() {
        super("superTravelBelt", 0.085f, 0.3f, 4.0f);
    }

    @Override
    public ResourceLocation getRenderTexture() {
        return texture;
    }
}

