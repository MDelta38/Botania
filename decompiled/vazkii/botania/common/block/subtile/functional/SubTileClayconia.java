/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileClayconia
extends SubTileFunctional {
    private static final int COST = 80;
    private static final int RANGE = 5;
    private static final int RANGE_Y = 3;
    private static final int RANGE_MINI = 2;
    private static final int RANGE_Y_MINI = 1;

    @Override
    public void onUpdate() {
        ChunkCoordinates coords;
        super.onUpdate();
        if (!this.supertile.func_145831_w().field_72995_K && this.ticksExisted % 5 == 0 && this.mana >= 80 && (coords = this.getCoordsToPut()) != null) {
            this.supertile.func_145831_w().func_147468_f(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            if (ConfigHandler.blockBreakParticles) {
                this.supertile.func_145831_w().func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)Block.func_149684_b((String)"sand")));
            }
            EntityItem item = new EntityItem(this.supertile.func_145831_w(), (double)coords.field_71574_a + 0.5, (double)coords.field_71572_b + 0.5, (double)coords.field_71573_c + 0.5, new ItemStack(Items.field_151119_aD));
            this.supertile.func_145831_w().func_72838_d((Entity)item);
            this.mana -= 80;
        }
    }

    public ChunkCoordinates getCoordsToPut() {
        ArrayList<ChunkCoordinates> possibleCoords = new ArrayList<ChunkCoordinates>();
        int range = this.getRange();
        int rangeY = this.getRangeY();
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -rangeY; j < rangeY + 1; ++j) {
                for (int k = -range; k < range + 1; ++k) {
                    int x = this.supertile.field_145851_c + i;
                    int y = this.supertile.field_145848_d + j;
                    int z = this.supertile.field_145849_e + k;
                    Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                    if (block != Block.func_149684_b((String)"sand")) continue;
                    possibleCoords.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        if (possibleCoords.isEmpty()) {
            return null;
        }
        return (ChunkCoordinates)possibleCoords.get(this.supertile.func_145831_w().field_73012_v.nextInt(possibleCoords.size()));
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    public int getRange() {
        return 5;
    }

    public int getRangeY() {
        return 3;
    }

    @Override
    public int getColor() {
        return 8095634;
    }

    @Override
    public int getMaxMana() {
        return 640;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.clayconia;
    }

    public static class Mini
    extends SubTileClayconia {
        @Override
        public int getRange() {
            return 2;
        }

        @Override
        public int getRangeY() {
            return 1;
        }
    }
}

