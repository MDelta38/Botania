/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.api.lexicon.multiblock.component;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

public class MultiblockComponent {
    public ChunkCoordinates relPos;
    public final Block block;
    public final int meta;
    public final TileEntity tileEntity;
    public boolean doFancyRender;

    public MultiblockComponent(ChunkCoordinates relPos, Block block, int meta) {
        this(relPos, block, meta, null);
    }

    public MultiblockComponent(ChunkCoordinates relPos, Block block, int meta, boolean doFancyRender) {
        this(relPos, block, meta, doFancyRender, null);
    }

    public MultiblockComponent(ChunkCoordinates relPos, Block block, int meta, TileEntity tileEntity) {
        this(relPos, block, meta, block.func_149716_u() == (tileEntity != null), tileEntity);
    }

    public MultiblockComponent(ChunkCoordinates relPos, Block block, int meta, boolean doFancyRender, TileEntity tileEntity) {
        this.relPos = relPos;
        this.block = block;
        this.meta = meta;
        this.tileEntity = tileEntity;
        this.doFancyRender = doFancyRender;
    }

    public ChunkCoordinates getRelativePosition() {
        return this.relPos;
    }

    public Block getBlock() {
        return this.block;
    }

    public int getMeta() {
        return this.meta;
    }

    public boolean matches(World world, int x, int y, int z) {
        return world.func_147439_a(x, y, z) == this.getBlock() && (this.meta == -1 || world.func_72805_g(x, y, z) == this.meta);
    }

    public ItemStack[] getMaterials() {
        return new ItemStack[]{new ItemStack(this.block, 1, this.meta)};
    }

    public void rotate(double angle) {
        double x = this.relPos.field_71574_a;
        double z = this.relPos.field_71573_c;
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double xn = x * cos - z * sin;
        double zn = x * sin + z * cos;
        this.relPos = new ChunkCoordinates((int)Math.round(xn), this.relPos.field_71572_b, (int)Math.round(zn));
    }

    public MultiblockComponent copy() {
        return new MultiblockComponent(this.relPos, this.block, this.meta, this.tileEntity);
    }

    public TileEntity getTileEntity() {
        return this.tileEntity;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldDoFancyRender() {
        return this.doFancyRender;
    }
}

