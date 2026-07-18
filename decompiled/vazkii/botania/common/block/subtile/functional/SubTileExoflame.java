/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFurnace
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.subtile.functional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import vazkii.botania.api.item.IExoflameHeatable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileExoflame
extends SubTileFunctional {
    private static final int RANGE = 5;
    private static final int RANGE_Y = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        boolean did = false;
        int cost = 300;
        block0: for (int i = -5; i < 6; ++i) {
            for (int j = -2; j < 3; ++j) {
                for (int k = -5; k < 6; ++k) {
                    IExoflameHeatable heatable;
                    int x = this.supertile.field_145851_c + i;
                    int y = this.supertile.field_145848_d + j;
                    int z = this.supertile.field_145849_e + k;
                    TileEntity tile = this.supertile.func_145831_w().func_147438_o(x, y, z);
                    Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                    if (tile == null) continue;
                    if (tile instanceof TileEntityFurnace && (block == Blocks.field_150460_al || block == Blocks.field_150470_am)) {
                        TileEntityFurnace furnace = (TileEntityFurnace)tile;
                        boolean canSmelt = SubTileExoflame.canFurnaceSmelt(furnace);
                        if (!canSmelt || this.mana <= 2) continue;
                        if (furnace.field_145956_a < 2) {
                            if (furnace.field_145956_a == 0) {
                                BlockFurnace.func_149931_a((boolean)true, (World)this.supertile.func_145831_w(), (int)x, (int)y, (int)z);
                            }
                            furnace.field_145956_a = 200;
                            this.mana = Math.max(0, this.mana - cost);
                        }
                        if (this.ticksExisted % 2 == 0) {
                            furnace.field_145961_j = Math.min(199, furnace.field_145961_j + 1);
                        }
                        did = true;
                        if (this.mana > 0) continue;
                        break block0;
                    }
                    if (!(tile instanceof IExoflameHeatable) || !(heatable = (IExoflameHeatable)tile).canSmelt() || this.mana <= 2) continue;
                    if (heatable.getBurnTime() == 0) {
                        heatable.boostBurnTime();
                        this.mana = Math.max(0, this.mana - cost);
                    }
                    if (this.ticksExisted % 2 == 0) {
                        heatable.boostCookTime();
                    }
                    if (this.mana <= 0) break block0;
                }
            }
        }
        if (did) {
            this.sync();
        }
    }

    public static boolean canFurnaceSmelt(TileEntityFurnace furnace) {
        if (furnace.func_70301_a(0) == null) {
            return false;
        }
        ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(furnace.func_70301_a(0));
        if (itemstack == null) {
            return false;
        }
        if (furnace.func_70301_a(2) == null) {
            return true;
        }
        if (!furnace.func_70301_a(2).func_77969_a(itemstack)) {
            return false;
        }
        int result = furnace.func_70301_a((int)2).field_77994_a + itemstack.field_77994_a;
        return result <= 64 && result <= itemstack.func_77976_d();
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 5);
    }

    @Override
    public int getMaxMana() {
        return 300;
    }

    @Override
    public int getColor() {
        return 0x661600;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.exoflame;
    }
}

