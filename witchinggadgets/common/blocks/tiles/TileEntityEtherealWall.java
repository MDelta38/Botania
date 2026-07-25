/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  thaumcraft.common.config.ConfigBlocks
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;
import witchinggadgets.common.util.EtherealWallMaster;

public class TileEntityEtherealWall
extends TileEntityWGBase {
    public EtherealWallMaster master;
    public Block camoID = null;
    public int camoMeta = -1;
    public int camoRenderType = 0;

    public EtherealWallMaster getMaster() {
        EtherealWallMaster masterOV = null;
        EtherealWallMaster masterYmin = null;
        EtherealWallMaster masterYmax = null;
        EtherealWallMaster masterZmin = null;
        EtherealWallMaster masterZmax = null;
        EtherealWallMaster masterXmin = null;
        EtherealWallMaster masterXmax = null;
        if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != null && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d - 1), (int)this.field_145849_e)).master != null) {
            masterYmin = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d - 1), (int)this.field_145849_e)).master;
        }
        if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != null && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d + 1), (int)this.field_145849_e)).master != null) {
            masterYmax = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d + 1), (int)this.field_145849_e)).master;
        }
        if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) != null && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e - 1))).master != null) {
            masterZmin = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e - 1))).master;
        }
        if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) != null && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e + 1))).master != null) {
            masterZmax = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e + 1))).master;
        }
        if (this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) != null && this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c - 1), (int)this.field_145848_d, (int)this.field_145849_e)).master != null) {
            masterXmin = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c - 1), (int)this.field_145848_d, (int)this.field_145849_e)).master;
        }
        if (this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) != null && this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileEntityEtherealWall && ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c + 1), (int)this.field_145848_d, (int)this.field_145849_e)).master != null) {
            masterXmax = ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c + 1), (int)this.field_145848_d, (int)this.field_145849_e)).master;
        }
        if (masterYmin != null) {
            masterOV = masterYmin;
        } else if (masterYmax != null) {
            masterOV = masterYmax;
        } else if (masterZmin != null) {
            masterOV = masterZmin;
        } else if (masterZmax != null) {
            masterOV = masterZmax;
        } else if (masterXmin != null) {
            masterOV = masterXmin;
        } else if (masterXmax != null) {
            masterOV = masterXmax;
        }
        if (masterYmin != null && masterYmin != masterOV) {
            masterOV.integrateOtherNet(masterYmin);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d - 1), (int)this.field_145849_e)).master = masterOV;
        }
        if (masterYmax != null && masterYmax != masterOV) {
            masterOV.integrateOtherNet(masterYmax);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)(this.field_145848_d + 1), (int)this.field_145849_e)).master = masterOV;
        }
        if (masterZmin != null && masterZmin != masterOV) {
            masterOV.integrateOtherNet(masterZmin);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e - 1))).master = masterOV;
        }
        if (masterZmax != null && masterZmax != masterOV) {
            masterOV.integrateOtherNet(masterZmax);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)(this.field_145849_e + 1))).master = masterOV;
        }
        if (masterXmin != null && masterXmin != masterOV) {
            masterOV.integrateOtherNet(masterXmin);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c - 1), (int)this.field_145848_d, (int)this.field_145849_e)).master = masterOV;
        }
        if (masterXmax != null && masterXmax != masterOV) {
            masterOV.integrateOtherNet(masterXmax);
            ((TileEntityEtherealWall)this.field_145850_b.func_147438_o((int)(this.field_145851_c + 1), (int)this.field_145848_d, (int)this.field_145849_e)).master = masterOV;
        }
        return masterOV;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.master == null) {
            this.master = this.getMaster();
            if (this.master == null) {
                this.master = new EtherealWallMaster();
            }
            this.master.addTileToNet(this);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        if (tag.func_74764_b("camo")) {
            this.camoID = Block.func_149684_b((String)tag.func_74779_i("camo"));
        }
        this.camoMeta = tag.func_74762_e("camoMeta");
        this.camoRenderType = tag.func_74762_e("camoRenderType");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        if (this.camoID != null) {
            tag.func_74778_a("camo", Block.field_149771_c.func_148750_c((Object)this.camoID));
        }
        tag.func_74768_a("camoMeta", this.camoMeta);
        tag.func_74768_a("camoRenderType", this.camoRenderType);
    }

    public boolean isRenderTypeValid(int renderType, int blockMeta) {
        if (renderType == 0 || renderType == 31 || renderType == 39) {
            return true;
        }
        if (renderType == ConfigBlocks.blockWoodenDeviceRI) {
            return blockMeta == 2 || blockMeta == 6 || blockMeta == 7;
        }
        if (renderType == ConfigBlocks.blockStoneDeviceRI) {
            return blockMeta == 0;
        }
        if (renderType == ConfigBlocks.blockMetalDeviceRI) {
            return blockMeta == 9;
        }
        if (renderType == ConfigBlocks.blockCustomOreRI) {
            return blockMeta == 0 || blockMeta == 7;
        }
        return renderType == ConfigBlocks.blockCosmeticOpaqueRI;
    }
}

