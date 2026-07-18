/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.subtile.functional;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileFakeAir;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileBubbell
extends SubTileFunctional {
    private static final int RANGE = 12;
    private static final int RANGE_MINI = 6;
    private static final int COST_PER_TICK = 4;
    private static final String TAG_RANGE = "range";
    int range = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mana > 4) {
            this.mana -= 4;
            if (this.ticksExisted % 10 == 0 && this.range < this.getRange()) {
                ++this.range;
            }
            for (int i = -this.range; i < this.range + 1; ++i) {
                for (int j = -this.range; j < this.range + 1; ++j) {
                    for (int k = -this.range; k < this.range + 1; ++k) {
                        Block block;
                        if (!(MathHelper.pointDistanceSpace(i, j, k, 0.0, 0.0, 0.0) < (float)this.range) || (block = this.supertile.func_145831_w().func_147439_a(this.supertile.field_145851_c + i, this.supertile.field_145848_d + j, this.supertile.field_145849_e + k)).func_149688_o() != Material.field_151586_h) continue;
                        this.supertile.func_145831_w().func_147465_d(this.supertile.field_145851_c + i, this.supertile.field_145848_d + j, this.supertile.field_145849_e + k, ModBlocks.fakeAir, 0, 2);
                        TileFakeAir air = (TileFakeAir)this.supertile.func_145831_w().func_147438_o(this.supertile.field_145851_c + i, this.supertile.field_145848_d + j, this.supertile.field_145849_e + k);
                        air.setFlower(this.supertile);
                    }
                }
            }
        }
    }

    public static boolean isValidBubbell(World world, int x, int y, int z) {
        ISubTileContainer container;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof ISubTileContainer && (container = (ISubTileContainer)tile).getSubTile() != null && container.getSubTile() instanceof SubTileBubbell) {
            SubTileBubbell bubbell = (SubTileBubbell)container.getSubTile();
            return bubbell.mana > 4;
        }
        return false;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_RANGE, this.range);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.range = cmp.func_74762_e(TAG_RANGE);
    }

    @Override
    public int getMaxMana() {
        return 2000;
    }

    @Override
    public int getColor() {
        return 905097;
    }

    public int getRange() {
        return 12;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Circle(this.toChunkCoordinates(), this.range);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.bubbell;
    }

    public static class Mini
    extends SubTileBubbell {
        @Override
        public int getRange() {
            return 6;
        }
    }
}

