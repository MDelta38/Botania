/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 */
package thaumcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileWarded
extends TileThaumcraft {
    public int owner = 0;
    public Block block = Blocks.field_150350_a;
    public byte blockMd = 0;
    public boolean safeToRemove = false;
    public byte light;

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        String s;
        this.block = Block.func_149729_e((int)nbttagcompound.func_74762_e("bi"));
        this.blockMd = nbttagcompound.func_74771_c("md");
        this.light = nbttagcompound.func_74771_c("ll");
        this.owner = nbttagcompound.func_74762_e("oi");
        if (this.owner == 0 && (s = nbttagcompound.func_74779_i("owner")) != null) {
            this.owner = s.hashCode();
        }
        if (this.block == null) {
            this.block = Blocks.field_150348_b;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("bi", Block.func_149682_b((Block)this.block));
        nbttagcompound.func_74774_a("md", this.blockMd);
        nbttagcompound.func_74774_a("ll", this.light);
        nbttagcompound.func_74768_a("oi", this.owner);
    }
}

