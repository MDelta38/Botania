/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileMemory
extends TileEntity {
    public Block oldblock;
    public int oldmeta;
    public NBTTagCompound tileEntityCompound;

    public TileMemory() {
    }

    public TileMemory(Block bi, int md, TileEntity te) {
        this.oldblock = bi;
        this.oldmeta = md;
        if (te != null) {
            this.tileEntityCompound = new NBTTagCompound();
            te.func_145841_b(this.tileEntityCompound);
        }
    }

    public boolean canUpdate() {
        return false;
    }

    public void recreateTileEntity() {
        if (this.tileEntityCompound != null && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != null) {
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.oldblock, this.oldmeta, 0);
            this.tileEntityCompound.func_74768_a("x", this.field_145851_c);
            this.tileEntityCompound.func_74768_a("y", this.field_145848_d);
            this.tileEntityCompound.func_74768_a("z", this.field_145849_e);
            this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_145839_a(this.tileEntityCompound);
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.oldblock = Block.func_149729_e((int)nbttagcompound.func_74762_e("oldblock"));
        this.oldmeta = nbttagcompound.func_74762_e("oldmeta");
        if (nbttagcompound.func_74764_b("TileEntity")) {
            this.tileEntityCompound = nbttagcompound.func_74775_l("TileEntity");
        }
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("oldblock", Block.func_149682_b((Block)this.oldblock));
        nbttagcompound.func_74768_a("oldmeta", this.oldmeta);
        if (this.tileEntityCompound != null) {
            nbttagcompound.func_74782_a("TileEntity", (NBTBase)this.tileEntityCompound);
        }
    }
}

