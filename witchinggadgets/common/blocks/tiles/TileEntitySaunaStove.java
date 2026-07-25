/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTank
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 */
package witchinggadgets.common.blocks.tiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntitySaunaStove
extends TileEntityWGBase
implements IFluidHandler {
    public static HashMap<Integer, TileEntitySaunaStove> targetedPlayers = new HashMap();
    public AxisAlignedBB[] boundingBoxes = new AxisAlignedBB[0];
    public FluidTank tank = new FluidTank(4000);
    public int tick;
    boolean recheck = false;
    List<ChunkCoordinates> openList = new ArrayList<ChunkCoordinates>();
    List<ChunkCoordinates> closedList = new ArrayList<ChunkCoordinates>();
    List<ChunkCoordinates> checked = new ArrayList<ChunkCoordinates>();
    int outlineMinX = this.field_145851_c;
    int outlineMaxX = this.field_145851_c;
    int outlineMinY = this.field_145848_d;
    int outlineMaxY = this.field_145848_d;
    int outlineMinZ = this.field_145849_e;
    int outlineMaxZ = this.field_145849_e;
    List<AxisAlignedBB> aabbList = new ArrayList<AxisAlignedBB>();
    Set<ChunkCoordinates> aabbUsedBlocks = new HashSet<ChunkCoordinates>();
    Vec3 start = null;
    boolean recreate = false;

    public void prepareAreaCheck() {
        this.recheck = true;
        this.openList.clear();
        this.closedList.clear();
        this.checked.clear();
        this.outlineMinX = this.field_145851_c;
        this.outlineMaxX = this.field_145851_c;
        this.outlineMinY = this.field_145848_d;
        this.outlineMaxY = this.field_145848_d;
        this.outlineMinZ = this.field_145849_e;
        this.outlineMaxZ = this.field_145849_e;
        this.openList.add(new ChunkCoordinates(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e));
        this.openList.add(new ChunkCoordinates(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e));
        this.openList.add(new ChunkCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1));
        this.openList.add(new ChunkCoordinates(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1));
        this.openList.add(new ChunkCoordinates(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.tick > 0 && this.field_145850_b.func_82737_E() % 10L == 0L) {
            --this.tick;
            if (this.boundingBoxes.length <= 0 && !this.recheck) {
                this.prepareAreaCheck();
            }
        }
        if (!this.recheck && this.tick <= 0 && this.tank.getFluidAmount() >= 1000) {
            this.tank.drain(1000, true);
            this.prepareAreaCheck();
            this.tick = 1200;
        }
        if (this.recheck) {
            this.checkArea();
        }
        if (this.recreate) {
            this.createAABBs();
        }
        if (this.field_145850_b.func_82737_E() % 20L == 0L && this.tick > 0) {
            for (AxisAlignedBB aabb : this.boundingBoxes) {
                List l = this.field_145850_b.func_72872_a(EntityPlayer.class, aabb);
                for (EntityPlayer pl : l) {
                    targetedPlayers.put(pl.func_145782_y(), this);
                }
            }
        }
    }

    public void checkArea() {
        ChunkCoordinates next = null;
        int closedListMax = 1200;
        int timeout = 0;
        while (timeout < 30 && this.closedList.size() < 1200 && !this.openList.isEmpty()) {
            ++timeout;
            next = this.openList.get(0);
            if (!this.checked.contains(next)) {
                boolean overWater = this.field_145850_b.func_72830_b(AxisAlignedBB.func_72330_a((double)next.field_71574_a, (double)(this.field_145848_d - 6), (double)next.field_71573_c, (double)(next.field_71574_a + 1), (double)(this.field_145848_d + 6), (double)(next.field_71573_c + 1)), Material.field_151586_h);
                if (this.isBlockValidForSteamPassing(next.field_71574_a, next.field_71572_b, next.field_71573_c) && (this.field_145851_c - next.field_71574_a) * (this.field_145851_c - next.field_71574_a) + (this.field_145849_e - next.field_71573_c) * (this.field_145849_e - next.field_71573_c) < 64 && next.field_71572_b - this.field_145848_d < (overWater ? 2 : 6) && next.field_71572_b - this.field_145848_d > -6) {
                    this.closedList.add(next);
                    if (next.field_71574_a < this.outlineMinX) {
                        this.outlineMinX = next.field_71574_a;
                    }
                    if (next.field_71574_a > this.outlineMaxX) {
                        this.outlineMaxX = next.field_71574_a;
                    }
                    if (next.field_71572_b < this.outlineMinY) {
                        this.outlineMinY = next.field_71572_b;
                    }
                    if (next.field_71572_b > this.outlineMaxY) {
                        this.outlineMaxY = next.field_71572_b;
                    }
                    if (next.field_71573_c < this.outlineMinZ) {
                        this.outlineMinZ = next.field_71573_c;
                    }
                    if (next.field_71573_c > this.outlineMaxZ) {
                        this.outlineMaxZ = next.field_71573_c;
                    }
                    for (ChunkCoordinates cc2 : this.getConnected(next.field_71574_a, next.field_71572_b, next.field_71573_c)) {
                        if (this.checked.contains(cc2) || this.closedList.contains(cc2) || this.openList.contains(cc2) || !this.isBlockValidForSteamPassing(cc2.field_71574_a, cc2.field_71572_b, cc2.field_71573_c)) continue;
                        this.openList.add(cc2);
                    }
                }
                this.checked.add(next);
            }
            this.openList.remove(0);
        }
        if (this.closedList.size() >= 1200 || this.openList.isEmpty()) {
            this.recheck = false;
            this.aabbList.clear();
            this.aabbUsedBlocks.clear();
            this.recreate = true;
        }
    }

    void createAABBs() {
        int timeoutAABB = 0;
        while (this.aabbUsedBlocks.size() < this.closedList.size() && timeoutAABB < 1) {
            int yy;
            int xx;
            int maxY;
            this.start = null;
            ++timeoutAABB;
            boolean flag = false;
            for (int yy2 = this.outlineMinY; yy2 <= this.outlineMaxY; ++yy2) {
                for (int zz = this.outlineMinZ; zz <= this.outlineMaxZ; ++zz) {
                    for (int xx2 = this.outlineMinX; xx2 <= this.outlineMaxZ; ++xx2) {
                        if (!this.closedList.contains(new ChunkCoordinates(xx2, yy2, zz)) || this.aabbUsedBlocks.contains(new ChunkCoordinates(xx2, yy2, zz))) continue;
                        this.start = Vec3.func_72443_a((double)xx2, (double)yy2, (double)zz);
                        flag = true;
                        break;
                    }
                    if (flag) break;
                }
                if (flag) break;
            }
            if (this.start == null) continue;
            int minX = (int)this.start.field_72450_a;
            int minY = (int)this.start.field_72448_b;
            int minZ = (int)this.start.field_72449_c;
            int maxX = (int)this.start.field_72450_a;
            int maxZ = (int)this.start.field_72449_c;
            for (maxY = (int)this.start.field_72448_b; maxY < minY + 32 && this.closedList.contains(new ChunkCoordinates(minX, maxY, minZ)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(minX, maxY, minZ)); ++maxY) {
            }
            while (maxZ < minZ + 32 && (this.closedList.contains(new ChunkCoordinates(minX, maxY, minZ)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(minX, minY, maxZ)) || this.closedList.contains(new ChunkCoordinates(minX, minY, maxZ)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(minX, minY, maxZ)))) {
                ++maxZ;
            }
            while (maxX < minX + 32 && this.closedList.contains(new ChunkCoordinates(maxX, minY, minZ)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(maxX, minY, minZ))) {
                ++maxX;
            }
            for (int zz = minZ; zz < maxZ; ++zz) {
                boolean row = true;
                for (xx = minX; xx < maxX; ++xx) {
                    if (this.closedList.contains(new ChunkCoordinates(xx, minY, zz)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(xx, minY, zz))) continue;
                    row = false;
                }
                if (row) continue;
                maxZ = zz;
                break;
            }
            for (yy = minY; yy < maxY; ++yy) {
                boolean layer = true;
                for (int zz = minZ; zz < maxZ; ++zz) {
                    for (int xx3 = minX; xx3 < maxX; ++xx3) {
                        if (this.closedList.contains(new ChunkCoordinates(xx3, yy, zz)) && !this.aabbUsedBlocks.contains(new ChunkCoordinates(xx3, yy, zz))) continue;
                        layer = false;
                    }
                }
                if (layer) continue;
                maxY = yy;
                break;
            }
            for (yy = minY; yy < maxY; ++yy) {
                for (int zz = minZ; zz < maxZ; ++zz) {
                    for (xx = minX; xx < maxX; ++xx) {
                        this.aabbUsedBlocks.add(new ChunkCoordinates(xx, yy, zz));
                    }
                }
            }
            this.aabbList.add(AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ));
        }
        if (this.aabbUsedBlocks.size() >= this.closedList.size()) {
            this.recreate = false;
            this.boundingBoxes = this.aabbList.toArray(new AxisAlignedBB[0]);
        }
    }

    boolean isBlockValidForSteamPassing(int x, int y, int z) {
        return this.field_145850_b.func_147437_c(x, y, z) || this.field_145850_b.func_147439_a(x, y, z).func_149668_a(this.field_145850_b, x, y, z) == null;
    }

    ChunkCoordinates[] getConnected(int x, int y, int z) {
        return new ChunkCoordinates[]{new ChunkCoordinates(x - 1, y, z), new ChunkCoordinates(x + 1, y, z), new ChunkCoordinates(x, y, z - 1), new ChunkCoordinates(x, y, z + 1), new ChunkCoordinates(x, y - 1, z), new ChunkCoordinates(x, y + 1, z)};
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.func_74768_a("tick", this.tick);
        this.tank.writeToNBT(tag);
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        this.tick = tag.func_74762_e("tick");
        this.tank.readFromNBT(tag);
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (from != ForgeDirection.UP && resource.getFluid() == FluidRegistry.WATER) {
            int filled = this.tank.fill(resource, doFill);
            if (filled > 0 && doFill) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return filled;
        }
        return 0;
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return this.drain(from, resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (from != ForgeDirection.UP) {
            FluidStack drained = this.tank.drain(maxDrain, doDrain);
            if (drained != null && doDrain) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return drained;
        }
        return null;
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return from != ForgeDirection.UNKNOWN && from != ForgeDirection.UP && fluid == FluidRegistry.WATER;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from != ForgeDirection.UNKNOWN && from != ForgeDirection.UP;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.tank.getInfo()};
    }
}

