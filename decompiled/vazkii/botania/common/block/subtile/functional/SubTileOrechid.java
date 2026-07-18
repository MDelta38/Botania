/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandom$Item
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileOrechid
extends SubTileFunctional {
    private static final int COST = 17500;
    private static final int COST_GOG = 700;
    private static final int DELAY = 100;
    private static final int DELAY_GOG = 2;
    private static final int RANGE = 5;
    private static final int RANGE_Y = 3;

    @Override
    public void onUpdate() {
        ItemStack stack;
        ChunkCoordinates coords;
        super.onUpdate();
        if (this.redstoneSignal > 0 || !this.canOperate()) {
            return;
        }
        int cost = this.getCost();
        if (!this.supertile.func_145831_w().field_72995_K && this.mana >= cost && this.ticksExisted % this.getDelay() == 0 && (coords = this.getCoordsToPut()) != null && (stack = this.getOreToPut()) != null) {
            Block block = Block.func_149634_a((Item)stack.func_77973_b());
            int meta = stack.func_77960_j();
            this.supertile.func_145831_w().func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, block, meta, 3);
            if (ConfigHandler.blockBreakParticles) {
                this.supertile.func_145831_w().func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)block) + (meta << 12));
            }
            this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "botania:orechid", 2.0f, 1.0f);
            this.mana -= cost;
            this.sync();
        }
    }

    public ItemStack getOreToPut() {
        ArrayList<StringRandomItem> values = new ArrayList<StringRandomItem>();
        Map<String, Integer> map = this.getOreMap();
        for (String s : map.keySet()) {
            values.add(new StringRandomItem(map.get(s), s));
        }
        String ore = ((StringRandomItem)WeightedRandom.func_76271_a((Random)this.supertile.func_145831_w().field_73012_v, values)).s;
        ArrayList ores = OreDictionary.getOres((String)ore);
        for (ItemStack stack : ores) {
            Item item = stack.func_77973_b();
            String clname = item.getClass().getName();
            if (clname.startsWith("gregtech") || clname.startsWith("gregapi")) continue;
            return stack;
        }
        return this.getOreToPut();
    }

    public ChunkCoordinates getCoordsToPut() {
        ArrayList<ChunkCoordinates> possibleCoords = new ArrayList<ChunkCoordinates>();
        Block source = this.getSourceBlock();
        for (int i = -5; i < 6; ++i) {
            for (int j = -3; j < 3; ++j) {
                for (int k = -5; k < 6; ++k) {
                    int x = this.supertile.field_145851_c + i;
                    int y = this.supertile.field_145848_d + j;
                    int z = this.supertile.field_145849_e + k;
                    Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                    if (block == null || !block.isReplaceableOreGen(this.supertile.func_145831_w(), x, y, z, source)) continue;
                    possibleCoords.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        if (possibleCoords.isEmpty()) {
            return null;
        }
        return (ChunkCoordinates)possibleCoords.get(this.supertile.func_145831_w().field_73012_v.nextInt(possibleCoords.size()));
    }

    public boolean canOperate() {
        return true;
    }

    public Map<String, Integer> getOreMap() {
        return BotaniaAPI.oreWeights;
    }

    public Block getSourceBlock() {
        return Blocks.field_150348_b;
    }

    public int getCost() {
        return Botania.gardenOfGlassLoaded ? 700 : 17500;
    }

    public int getDelay() {
        return Botania.gardenOfGlassLoaded ? 2 : 100;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 5);
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 0x818181;
    }

    @Override
    public int getMaxMana() {
        return this.getCost();
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.orechid;
    }

    private static class StringRandomItem
    extends WeightedRandom.Item {
        public String s;

        public StringRandomItem(int par1, String s) {
            super(par1);
            this.s = s;
        }
    }
}

