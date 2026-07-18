/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.client.core.handler;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class MultiblockBlockAccess
implements IBlockAccess {
    protected IBlockAccess originalBlockAccess;
    protected boolean hasBlockAccess = false;
    protected Multiblock multiblock;
    protected int anchorX;
    protected int anchorY;
    protected int anchorZ;

    public Block func_147439_a(int x, int y, int z) {
        MultiblockComponent comp = this.getComponent(x, y, z);
        if (comp != null) {
            return comp.getBlock();
        }
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_147439_a(x, y, z);
        }
        return Blocks.field_150350_a;
    }

    public TileEntity func_147438_o(int x, int y, int z) {
        MultiblockComponent comp = this.getComponent(x, y, z);
        if (comp != null) {
            return comp.getTileEntity();
        }
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_147438_o(x, y, z);
        }
        return null;
    }

    public int func_72802_i(int x, int y, int z, int p_72802_4_) {
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_72802_i(x, y, z, p_72802_4_);
        }
        return 0xF00000;
    }

    public int func_72805_g(int x, int y, int z) {
        MultiblockComponent comp = this.getComponent(x, y, z);
        if (comp != null) {
            return comp.getMeta();
        }
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_72805_g(x, y, z);
        }
        return 0;
    }

    public int func_72879_k(int x, int y, int z, int direction) {
        return 0;
    }

    public boolean func_147437_c(int x, int y, int z) {
        MultiblockComponent comp = this.getComponent(x, y, z);
        if (comp != null) {
            return false;
        }
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_147437_c(x, y, z);
        }
        return true;
    }

    public BiomeGenBase func_72807_a(int x, int z) {
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_72807_a(x, z);
        }
        return null;
    }

    public int func_72800_K() {
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_72800_K();
        }
        return 256;
    }

    public boolean func_72806_N() {
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.func_72806_N();
        }
        return false;
    }

    public boolean isSideSolid(int x, int y, int z, ForgeDirection side, boolean _default) {
        if (this.hasBlockAccess) {
            return this.originalBlockAccess.isSideSolid(x, y, z, side, _default);
        }
        return _default;
    }

    public void update(IBlockAccess access, Multiblock mb, int anchorX, int anchorY, int anchorZ) {
        this.originalBlockAccess = access;
        this.multiblock = mb;
        this.anchorX = anchorX;
        this.anchorY = anchorY;
        this.anchorZ = anchorZ;
        this.hasBlockAccess = access != null;
    }

    protected MultiblockComponent getComponent(int x, int y, int z) {
        MultiblockComponent comp = this.multiblock.getComponentForLocation(x - this.anchorX, y - this.anchorY, z - this.anchorZ);
        return comp;
    }
}

