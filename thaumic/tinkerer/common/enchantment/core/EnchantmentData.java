/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.aspects.AspectList
 */
package thaumic.tinkerer.common.enchantment.core;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;

public class EnchantmentData {
    public final ResourceLocation texture;
    public final AspectList aspects;
    public final boolean vanilla;
    public final String research;

    public EnchantmentData(String texture, boolean vanilla, AspectList aspects) {
        this(texture, vanilla, aspects, "");
    }

    public EnchantmentData(String texture, boolean vanilla, AspectList aspects, String research) {
        this.texture = new ResourceLocation(texture);
        this.vanilla = vanilla;
        this.aspects = aspects;
        this.research = research;
    }
}

