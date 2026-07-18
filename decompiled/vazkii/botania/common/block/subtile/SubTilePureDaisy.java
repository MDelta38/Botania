/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.subtile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipePureDaisy;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTilePureDaisy
extends SubTileEntity {
    private static final String TAG_POSITION = "position";
    private static final String TAG_TICKS_REMAINING = "ticksRemaining";
    private static final int TOTAL_TIME = 1200;
    private static final int TIME_PER = 150;
    private static final int[][] POSITIONS = new int[][]{{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}, {0, 0, 1}, {1, 0, 1}, {1, 0, 0}, {1, 0, -1}, {0, 0, -1}};
    int positionAt = 0;
    int[] ticksRemaining = new int[]{150, 150, 150, 150, 150, 150, 150, 150};

    @Override
    public void onUpdate() {
        super.onUpdate();
        ++this.positionAt;
        if (this.positionAt == POSITIONS.length) {
            this.positionAt = 0;
        }
        int[] acoords = POSITIONS[this.positionAt];
        ChunkCoordinates coords = new ChunkCoordinates(this.supertile.field_145851_c + acoords[0], this.supertile.field_145848_d + acoords[1], this.supertile.field_145849_e + acoords[2]);
        World world = this.supertile.func_145831_w();
        if (!world.func_147437_c(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c)) {
            Block block = world.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            int meta = world.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            RecipePureDaisy recipe = null;
            for (RecipePureDaisy recipe_ : BotaniaAPI.pureDaisyRecipes) {
                if (!recipe_.matches(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, this, block, meta)) continue;
                recipe = recipe_;
                break;
            }
            if (recipe != null) {
                this.ticksRemaining[this.positionAt] = this.ticksRemaining[this.positionAt] - 1;
                Botania.proxy.sparkleFX(this.supertile.func_145831_w(), (double)coords.field_71574_a + Math.random(), (double)coords.field_71572_b + Math.random(), (double)coords.field_71573_c + Math.random(), 1.0f, 1.0f, 1.0f, (float)Math.random(), 5);
                if (this.ticksRemaining[this.positionAt] <= 0) {
                    this.ticksRemaining[this.positionAt] = 150;
                    if (recipe.set(world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, this)) {
                        for (int i = 0; i < 25; ++i) {
                            double x = (double)coords.field_71574_a + Math.random();
                            double y = (double)coords.field_71572_b + Math.random() + 0.5;
                            double z = (double)coords.field_71573_c + Math.random();
                            Botania.proxy.wispFX(this.supertile.func_145831_w(), x, y, z, 1.0f, 1.0f, 1.0f, (float)Math.random() / 2.0f);
                        }
                        if (ConfigHandler.blockBreakParticles) {
                            this.supertile.func_145831_w().func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)recipe.getOutput()) + (recipe.getOutputMeta() << 12));
                        }
                    }
                }
            } else {
                this.ticksRemaining[this.positionAt] = 150;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        this.positionAt = cmp.func_74762_e(TAG_POSITION);
        if (this.supertile.func_145831_w() != null && !this.supertile.func_145831_w().field_72995_K) {
            for (int i = 0; i < this.ticksRemaining.length; ++i) {
                this.ticksRemaining[i] = cmp.func_74762_e(TAG_TICKS_REMAINING + i);
            }
        }
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_POSITION, this.positionAt);
        for (int i = 0; i < this.ticksRemaining.length; ++i) {
            cmp.func_74768_a(TAG_TICKS_REMAINING + i, this.ticksRemaining[i]);
        }
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.pureDaisy;
    }
}

