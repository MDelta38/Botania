/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.common.lib.events.EssentiaHandler;
import thaumcraft.common.lib.utils.Utils;

public class TileMirrorEssentia
extends TileThaumcraft
implements IAspectSource {
    public boolean linked = false;
    public int linkX;
    public int linkY;
    public int linkZ;
    public int linkDim;
    public ForgeDirection linkedFacing = ForgeDirection.UNKNOWN;
    int count = 0;
    int inc = 40;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.linked = nbttagcompound.func_74767_n("linked");
        this.linkX = nbttagcompound.func_74762_e("linkX");
        this.linkY = nbttagcompound.func_74762_e("linkY");
        this.linkZ = nbttagcompound.func_74762_e("linkZ");
        this.linkDim = nbttagcompound.func_74762_e("linkDim");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("linked", this.linked);
        nbttagcompound.func_74768_a("linkX", this.linkX);
        nbttagcompound.func_74768_a("linkY", this.linkY);
        nbttagcompound.func_74768_a("linkZ", this.linkZ);
        nbttagcompound.func_74768_a("linkDim", this.linkDim);
    }

    public void restoreLink() {
        if (this.isDestinationValid()) {
            WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
            if (targetWorld == null) {
                return;
            }
            TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
            if (te != null && te instanceof TileMirrorEssentia) {
                TileMirrorEssentia tm = (TileMirrorEssentia)te;
                tm.linked = true;
                tm.linkX = this.field_145851_c;
                tm.linkY = this.field_145848_d;
                tm.linkZ = this.field_145849_e;
                tm.linkDim = this.field_145850_b.field_73011_w.field_76574_g;
                targetWorld.func_147471_g(tm.field_145851_c, tm.field_145848_d, tm.field_145849_e);
                this.linkedFacing = ForgeDirection.getOrientation((int)targetWorld.func_72805_g(this.linkX, this.linkY, this.linkZ));
                this.linked = true;
                this.func_70296_d();
                tm.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    public void invalidateLink() {
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return;
        }
        if (!Utils.isChunkLoaded((World)targetWorld, this.linkX, this.linkZ)) {
            return;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te != null && te instanceof TileMirrorEssentia) {
            TileMirrorEssentia tm = (TileMirrorEssentia)te;
            tm.linked = false;
            tm.linkedFacing = ForgeDirection.UNKNOWN;
            this.func_70296_d();
            tm.func_70296_d();
            targetWorld.func_147471_g(this.linkX, this.linkY, this.linkZ);
        }
    }

    public boolean isLinkValid() {
        if (!this.linked) {
            return false;
        }
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirrorEssentia)) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        TileMirrorEssentia tm = (TileMirrorEssentia)te;
        if (!tm.linked) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        if (tm.linkX != this.field_145851_c || tm.linkY != this.field_145848_d || tm.linkZ != this.field_145849_e || tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        return true;
    }

    public boolean isLinkValidSimple() {
        if (!this.linked) {
            return false;
        }
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirrorEssentia)) {
            return false;
        }
        TileMirrorEssentia tm = (TileMirrorEssentia)te;
        if (!tm.linked) {
            return false;
        }
        return tm.linkX == this.field_145851_c && tm.linkY == this.field_145848_d && tm.linkZ == this.field_145849_e && tm.linkDim == this.field_145850_b.field_73011_w.field_76574_g;
    }

    public boolean isDestinationValid() {
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirrorEssentia)) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        TileMirrorEssentia tm = (TileMirrorEssentia)te;
        return !tm.isLinkValid();
    }

    @Override
    public AspectList getAspects() {
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        TileEntity te;
        if (!this.isLinkValid() || amount > 1) {
            return false;
        }
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (this.linkedFacing == ForgeDirection.UNKNOWN && targetWorld != null) {
            this.linkedFacing = ForgeDirection.getOrientation((int)(targetWorld.func_72805_g(this.linkX, this.linkY, this.linkZ) % 6));
        }
        if ((te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ)) != null && te instanceof TileMirrorEssentia) {
            return EssentiaHandler.drainEssentia(te, tag, this.linkedFacing, 8, true);
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && this.count++ % this.inc == 0) {
            if (!this.isLinkValidSimple()) {
                if (this.inc < 600) {
                    this.inc += 20;
                }
                this.restoreLink();
            } else {
                this.inc = 40;
            }
        }
    }
}

