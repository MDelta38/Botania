/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.aspects.Aspect;

public interface ITerraformFocus {
    public Aspect requiredAspect(int var1);

    public Aspect requiredAspect(World var1, int var2, int var3, int var4);

    public BiomeGenBase getCreatedBiome(World var1, int var2, int var3, int var4);

    public ItemStack getDisplayedBlock(World var1, int var2, int var3, int var4);
}

