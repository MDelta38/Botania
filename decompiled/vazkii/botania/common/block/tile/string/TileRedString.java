/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.string;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.wand.ITileBound;
import vazkii.botania.common.block.tile.TileMod;

public abstract class TileRedString
extends TileMod
implements ITileBound {
    private ChunkCoordinates binding;

    public void func_145845_h() {
        ForgeDirection dir = this.getOrientation();
        int x = this.field_145851_c;
        int y = this.field_145848_d;
        int z = this.field_145849_e;
        int range = this.getRange();
        ChunkCoordinates currBinding = this.getBinding();
        this.setBinding(null);
        for (int i = 0; i < range; ++i) {
            TileEntity tile;
            if (this.field_145850_b.func_147437_c(x += dir.offsetX, y += dir.offsetY, z += dir.offsetZ) || (tile = this.field_145850_b.func_147438_o(x, y, z)) instanceof TileRedString || !this.acceptBlock(x, y, z)) continue;
            this.setBinding(new ChunkCoordinates(x, y, z));
            if (currBinding != null && currBinding.field_71574_a == x && currBinding.field_71572_b == y && currBinding.field_71573_c == z) break;
            this.onBound(x, y, z);
            break;
        }
    }

    public int getRange() {
        return 8;
    }

    public abstract boolean acceptBlock(int var1, int var2, int var3);

    public void onBound(int x, int y, int z) {
    }

    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public ChunkCoordinates getBinding() {
        return this.binding;
    }

    public void setBinding(ChunkCoordinates binding) {
        this.binding = binding;
    }

    public ForgeDirection getOrientation() {
        return ForgeDirection.getOrientation((int)this.func_145832_p());
    }

    public TileEntity getTileAtBinding() {
        ChunkCoordinates binding = this.getBinding();
        return binding == null ? null : this.field_145850_b.func_147438_o(binding.field_71574_a, binding.field_71572_b, binding.field_71573_c);
    }

    public Block getBlockAtBinding() {
        ChunkCoordinates binding = this.getBinding();
        return binding == null ? Blocks.field_150350_a : this.field_145850_b.func_147439_a(binding.field_71574_a, binding.field_71572_b, binding.field_71573_c);
    }
}

