/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.world.IBlockAccess
 */
package vazkii.botania.common.block.subtile.functional;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileJadedAmaranthus
extends SubTileFunctional {
    private static final int COST = 100;
    int RANGE = 4;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        if (this.mana >= 100 && !this.supertile.func_145831_w().field_72995_K && this.ticksExisted % 30 == 0) {
            int x = this.supertile.field_145851_c - this.RANGE + this.supertile.func_145831_w().field_73012_v.nextInt(this.RANGE * 2 + 1);
            int y = this.supertile.field_145848_d + this.RANGE;
            int z = this.supertile.field_145849_e - this.RANGE + this.supertile.func_145831_w().field_73012_v.nextInt(this.RANGE * 2 + 1);
            for (int i = 0; i < this.RANGE * 2; ++i) {
                Block blockAbove = this.supertile.func_145831_w().func_147439_a(x, y + 1, z);
                if ((this.supertile.func_145831_w().func_147437_c(x, y + 1, z) || blockAbove.isReplaceable((IBlockAccess)this.supertile.func_145831_w(), x, y + 1, z)) && blockAbove.func_149688_o() != Material.field_151586_h && ModBlocks.flower.func_149742_c(this.supertile.func_145831_w(), x, y + 1, z)) {
                    int color = this.supertile.func_145831_w().field_73012_v.nextInt(16);
                    if (ModBlocks.flower.func_149718_j(this.supertile.func_145831_w(), x, y + 1, z)) {
                        if (ConfigHandler.blockBreakParticles) {
                            this.supertile.func_145831_w().func_72926_e(2001, x, y + 1, z, Block.func_149682_b((Block)ModBlocks.flower) + (color << 12));
                        }
                        this.supertile.func_145831_w().func_147465_d(x, y + 1, z, ModBlocks.flower, color, 3);
                    }
                    this.mana -= 100;
                    this.sync();
                    break;
                }
                --y;
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 9835139;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.RANGE);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.jadedAmaranthus;
    }

    @Override
    public int getMaxMana() {
        return 100;
    }
}

