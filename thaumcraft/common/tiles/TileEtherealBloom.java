/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.biome.BiomeGenBase
 */
package thaumcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class TileEtherealBloom
extends TileEntity {
    public int counter = 0;
    public int growthCounter = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.counter == 0) {
            this.counter = this.field_145850_b.field_73012_v.nextInt(100);
        }
        ++this.counter;
        if (!this.field_145850_b.field_72995_K && this.counter % 20 == 0) {
            int x = this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            int z = this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            if ((this.field_145850_b.func_72807_a((int)(x + this.field_145851_c), (int)(z + this.field_145849_e)).field_76756_M == Config.biomeTaintID || this.field_145850_b.func_72807_a((int)(x + this.field_145851_c), (int)(z + this.field_145849_e)).field_76756_M == Config.biomeEerieID || this.field_145850_b.func_72807_a((int)(x + this.field_145851_c), (int)(z + this.field_145849_e)).field_76756_M == Config.biomeMagicalForestID) && this.func_145835_a((double)(x + this.field_145851_c) + 0.5, this.field_145848_d, (double)(z + this.field_145849_e) + 0.5) <= 81.0) {
                BiomeGenBase[] biomesForGeneration = null;
                biomesForGeneration = this.field_145850_b.func_72959_q().func_76933_b(biomesForGeneration, x + this.field_145851_c, z + this.field_145849_e, 1, 1);
                if (biomesForGeneration != null && biomesForGeneration[0] != null) {
                    BiomeGenBase biome = biomesForGeneration[0];
                    if (biome.field_76756_M == ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
                        biome = BiomeGenBase.field_76772_c;
                    }
                    Utils.setBiomeAt(this.field_145850_b, x + this.field_145851_c, z + this.field_145849_e, biome);
                }
            }
        }
        if (this.field_145850_b.field_72995_K && this.growthCounter == 0) {
            this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:roots", 1.0f, 0.6f, false);
        }
        ++this.growthCounter;
    }
}

