/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.mana;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.mana.TilePool;

public class TileBellows
extends TileMod {
    private static final String TAG_ACTIVE = "active";
    public float movePos;
    public boolean active = false;
    public float moving = 0.0f;

    public void interact() {
        if (this.moving == 0.0f) {
            this.setActive(true);
        }
    }

    public void func_145845_h() {
        boolean disable = true;
        TileEntity tile = this.getLinkedTile();
        if (!this.active && tile instanceof TilePool) {
            TilePool pool = (TilePool)tile;
            boolean transfer = pool.isDoingTransfer;
            if (transfer) {
                if (!this.active && pool.ticksDoingTransfer >= this.func_145832_p() * 2 - 2) {
                    this.setActive(true);
                }
                disable = false;
            }
        }
        float max = 0.9f;
        float min = 0.0f;
        float incr = max / 20.0f;
        if (this.movePos < max && this.active && this.moving >= 0.0f) {
            if (this.moving == 0.0f) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "botania:bellows", 0.1f, 3.0f);
            }
            if (tile instanceof TileEntityFurnace) {
                TileEntityFurnace furnace = (TileEntityFurnace)tile;
                if (SubTileExoflame.canFurnaceSmelt(furnace)) {
                    furnace.field_145961_j = Math.min(199, furnace.field_145961_j + 20);
                    furnace.field_145956_a = Math.max(0, furnace.field_145956_a - 10);
                }
                if (furnace.func_145838_q() == Blocks.field_150470_am) {
                    int x = furnace.field_145851_c;
                    int y = furnace.field_145848_d;
                    int z = furnace.field_145849_e;
                    int l = this.field_145850_b.func_72805_g(x, y, z);
                    float f = (float)x + 0.5f;
                    float f1 = (float)y + 0.0f + this.field_145850_b.field_73012_v.nextFloat() * 6.0f / 16.0f;
                    float f2 = (float)z + 0.5f;
                    float f3 = 0.52f;
                    float f4 = this.field_145850_b.field_73012_v.nextFloat() * 0.6f - 0.3f;
                    if (l == 4) {
                        this.field_145850_b.func_72869_a("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                        this.field_145850_b.func_72869_a("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                    } else if (l == 5) {
                        this.field_145850_b.func_72869_a("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                        this.field_145850_b.func_72869_a("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0, 0.0, 0.0);
                    } else if (l == 2) {
                        this.field_145850_b.func_72869_a("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
                        this.field_145850_b.func_72869_a("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0, 0.0, 0.0);
                    } else if (l == 3) {
                        this.field_145850_b.func_72869_a("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
                        this.field_145850_b.func_72869_a("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0, 0.0, 0.0);
                    }
                }
            }
            this.movePos += incr * 3.0f;
            this.moving = incr * 3.0f;
            if (this.movePos >= max) {
                this.movePos = Math.min(max, this.movePos);
                this.moving = 0.0f;
                if (disable) {
                    this.setActive(false);
                }
            }
        } else if (this.movePos > min) {
            this.movePos -= incr;
            this.moving = -incr;
            if (this.movePos <= min) {
                this.movePos = Math.max(min, this.movePos);
                this.moving = 0.0f;
            }
        }
        super.func_145845_h();
    }

    public TileEntity getLinkedTile() {
        int meta = this.func_145832_p();
        ForgeDirection dir = ForgeDirection.getOrientation((int)meta);
        return this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74757_a(TAG_ACTIVE, this.active);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.active = cmp.func_74767_n(TAG_ACTIVE);
    }

    public void setActive(boolean active) {
        if (!this.field_145850_b.field_72995_K) {
            boolean diff = this.active != active;
            this.active = active;
            if (diff) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }
}

