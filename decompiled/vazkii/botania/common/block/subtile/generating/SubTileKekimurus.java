/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCake
 */
package vazkii.botania.common.block.subtile.generating;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCake;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileKekimurus
extends SubTileGenerating {
    private static final int RANGE = 5;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int mana = 1800;
        if (this.getMaxMana() - this.mana >= mana && !this.supertile.func_145831_w().field_72995_K && this.ticksExisted % 80 == 0) {
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 11; ++j) {
                    for (int k = 0; k < 11; ++k) {
                        int x = this.supertile.field_145851_c + i - 5;
                        int y = this.supertile.field_145848_d + j - 5;
                        int z = this.supertile.field_145849_e + k - 5;
                        Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                        if (!(block instanceof BlockCake)) continue;
                        int meta = this.supertile.func_145831_w().func_72805_g(x, y, z) + 1;
                        if (meta == 6) {
                            this.supertile.func_145831_w().func_147468_f(x, y, z);
                        } else {
                            this.supertile.func_145831_w().func_72921_c(x, y, z, meta, 3);
                        }
                        this.supertile.func_145831_w().func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (meta << 12));
                        this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "random.eat", 1.0f, 0.5f + (float)Math.random() * 0.5f);
                        this.mana += mana;
                        this.sync();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 5);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.kekimurus;
    }

    @Override
    public int getColor() {
        return 9657640;
    }

    @Override
    public int getMaxMana() {
        return 9001;
    }
}

