/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.block.IGrowable
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 */
package vazkii.botania.common.block.subtile.functional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileAgricarnation
extends SubTileFunctional {
    private static final int RANGE = 5;
    private static final int RANGE_MINI = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 6 == 0 && this.redstoneSignal == 0) {
            int range = this.getRange();
            int x = this.supertile.field_145851_c + this.supertile.func_145831_w().field_73012_v.nextInt(range * 2 + 1) - range;
            int z = this.supertile.field_145849_e + this.supertile.func_145831_w().field_73012_v.nextInt(range * 2 + 1) - range;
            for (int i = 4; i > -2; --i) {
                int y = this.supertile.field_145848_d + i;
                if (this.supertile.func_145831_w().func_147437_c(x, y, z) || !this.isPlant(x, y, z) || this.mana <= 5) continue;
                Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                this.mana -= 5;
                this.supertile.func_145831_w().func_147464_a(x, y, z, block, 1);
                if (ConfigHandler.blockBreakParticles) {
                    this.supertile.func_145831_w().func_72926_e(2005, x, y, z, 6 + this.supertile.func_145831_w().field_73012_v.nextInt(4));
                }
                this.supertile.func_145831_w().func_72908_a((double)x, (double)y, (double)z, "botania:agricarnation", 0.01f, 0.5f + (float)Math.random() * 0.5f);
                break;
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    boolean isPlant(int x, int y, int z) {
        Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
        if (block == Blocks.field_150349_c || block == Blocks.field_150362_t || block == Blocks.field_150361_u || block instanceof BlockBush && !(block instanceof BlockCrops) && !(block instanceof BlockSapling)) {
            return false;
        }
        Material mat = block.func_149688_o();
        return mat != null && (mat == Material.field_151585_k || mat == Material.field_151570_A || mat == Material.field_151577_b || mat == Material.field_151584_j || mat == Material.field_151572_C) && block instanceof IGrowable && ((IGrowable)block).func_149851_a(this.supertile.func_145831_w(), x, y, z, this.supertile.func_145831_w().field_72995_K);
    }

    @Override
    public int getColor() {
        return 9369640;
    }

    @Override
    public int getMaxMana() {
        return 200;
    }

    public int getRange() {
        return 5;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.agricarnation;
    }

    public static class Mini
    extends SubTileAgricarnation {
        @Override
        public int getRange() {
            return 2;
        }
    }
}

