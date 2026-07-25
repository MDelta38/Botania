/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 */
package appeng.api.features;

import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;

public interface IWorldGen {
    public void disableWorldGenForProviderID(WorldGenType var1, Class<? extends WorldProvider> var2);

    public void enableWorldGenForDimension(WorldGenType var1, int var2);

    public void disableWorldGenForDimension(WorldGenType var1, int var2);

    public boolean isWorldGenEnabled(WorldGenType var1, World var2);

    public static enum WorldGenType {
        CertusQuartz,
        ChargedCertusQuartz,
        Meteorites;

    }
}

