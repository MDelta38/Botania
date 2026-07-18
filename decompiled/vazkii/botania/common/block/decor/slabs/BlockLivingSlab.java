/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.common.block.decor.slabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;

public abstract class BlockLivingSlab
extends BlockModSlab {
    Block source;
    int meta;

    public BlockLivingSlab(boolean full, Block source, int meta) {
        super(full, source.func_149688_o(), source.func_149739_a().replaceAll("tile.", "") + meta + "Slab" + (full ? "Full" : ""));
        this.func_149672_a(source.field_149762_H);
        this.source = source;
        this.meta = meta;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.source.func_149691_a(par1, this.meta);
    }
}

