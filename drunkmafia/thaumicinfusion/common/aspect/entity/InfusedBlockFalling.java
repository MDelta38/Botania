/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class InfusedBlockFalling
extends Entity {
    public TileEntity tileEntity;
    public int meta;
    public int blockCount;
    public int id;

    public InfusedBlockFalling(World world) {
        super(world);
    }

    public InfusedBlockFalling(World world, double x, double y, double z, int id, int meta, TileEntity tileEntity) {
        super(world);
        this.id = id;
        this.meta = meta;
        this.tileEntity = tileEntity;
        this.field_70156_m = true;
        this.func_70105_a(0.98f, 0.98f);
        this.field_70129_M = this.field_70131_O / 2.0f;
        this.func_70107_b(x, y, z);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = x;
        this.field_70167_r = z;
        this.field_70166_s = y;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70088_a() {
    }

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        ++this.blockCount;
        this.field_70181_x -= (double)0.04f;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= (double)0.98f;
        this.field_70181_x *= (double)0.98f;
        this.field_70179_y *= (double)0.98f;
        if (!this.field_70170_p.field_72995_K) {
            int x = MathHelper.func_76128_c((double)this.field_70165_t);
            int y = MathHelper.func_76128_c((double)this.field_70163_u);
            int z = MathHelper.func_76128_c((double)this.field_70161_v);
            if (this.field_70122_E) {
                this.field_70159_w *= (double)0.7f;
                this.field_70179_y *= (double)0.7f;
                this.field_70181_x *= -0.5;
                if (this.field_70170_p.func_147439_a(x, y, z) != Blocks.field_150326_M) {
                    this.func_70106_y();
                    if (this.field_70170_p.func_147472_a(Block.func_149729_e((int)this.id), x, y, z, true, 1, null, null) && !BlockFalling.func_149831_e((World)this.field_70170_p, (int)x, (int)(y - 1), (int)z)) {
                        this.field_70170_p.func_147465_d(x, y, z, Block.func_149729_e((int)this.id), this.meta, 3);
                        if (this.tileEntity != null) {
                            this.tileEntity.field_145851_c = x;
                            this.tileEntity.field_145848_d = y;
                            this.tileEntity.field_145849_e = z;
                            this.tileEntity.func_145834_a(this.field_70170_p);
                            if (this.field_70170_p.func_147438_o(x, y, z) != null) {
                                NBTTagCompound tileTag = new NBTTagCompound();
                                this.tileEntity.func_145841_b(tileTag);
                                this.field_70170_p.func_147438_o(x, y, z).func_145839_a(tileTag);
                            } else {
                                this.field_70170_p.func_147455_a(x, y, z, this.tileEntity);
                            }
                        }
                    } else {
                        this.dropAsItem(x, y, z);
                    }
                }
            } else if (this.blockCount > 100 && !this.field_70170_p.field_72995_K && (y < 1 || y > 256) || this.blockCount > 600) {
                this.dropAsItem(x, y, z);
                this.func_70106_y();
            }
        }
    }

    public void dropAsItem(int x, int y, int z) {
        float f = 0.7f;
        double tempX = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5 + (double)x;
        double tempY = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5 + (double)y;
        double tempZ = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f) * 0.5 + (double)z;
        EntityItem entityitem = new EntityItem(this.field_70170_p, tempX, tempY, tempZ, new ItemStack(Block.func_149729_e((int)this.id), 1, this.meta));
        entityitem.field_145804_b = 10;
        this.field_70170_p.func_72838_d((Entity)entityitem);
    }

    protected void func_70014_b(NBTTagCompound nbt) {
        nbt.func_74768_a("blockID", this.id);
        nbt.func_74768_a("blockMETA", this.meta);
        if (this.tileEntity != null) {
            NBTTagCompound tileTag = new NBTTagCompound();
            this.tileEntity.func_145841_b(tileTag);
            nbt.func_74782_a("tileTAG", (NBTBase)tileTag);
        }
    }

    protected void func_70037_a(NBTTagCompound nbt) {
        this.id = nbt.func_74762_e("blockID");
        this.meta = nbt.func_74762_e("blockMETA");
        if (nbt.func_74764_b("tileTAG")) {
            this.tileEntity = TileEntity.func_145827_c((NBTTagCompound)nbt.func_74775_l("tileTAG"));
        }
    }

    public int getMetaData() {
        return this.meta;
    }
}

