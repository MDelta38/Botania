/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
import thaumcraft.codechicken.lib.raytracer.RayTracer;
import thaumcraft.codechicken.lib.vec.Cuboid6;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileTube;

public class TileTubeBuffer
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport,
IWandable {
    public AspectList aspects = new AspectList();
    public final int MAXAMOUNT = 8;
    public boolean[] openSides = new boolean[]{true, true, true, true, true, true};
    public byte[] chokedSides = new byte[]{0, 0, 0, 0, 0, 0};
    int count = 0;
    int bellows = -1;

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspects.readFromNBT(nbttagcompound);
        byte[] sides = nbttagcompound.func_74770_j("open");
        if (sides != null && sides.length == 6) {
            for (int a = 0; a < 6; ++a) {
                this.openSides[a] = sides[a] == 1;
            }
        }
        this.chokedSides = nbttagcompound.func_74770_j("choke");
        if (this.chokedSides == null || this.chokedSides.length < 6) {
            this.chokedSides = new byte[]{0, 0, 0, 0, 0, 0};
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspects.writeToNBT(nbttagcompound);
        byte[] sides = new byte[6];
        for (int a = 0; a < 6; ++a) {
            sides[a] = this.openSides[a] ? (byte)1 : 0;
        }
        nbttagcompound.func_74773_a("open", sides);
        nbttagcompound.func_74773_a("choke", this.chokedSides);
    }

    @Override
    public AspectList getAspects() {
        return this.aspects;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (am != 1) {
            return am;
        }
        if (this.aspects.visSize() < 8) {
            this.aspects.add(tt, am);
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return 0;
        }
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.aspects.getAmount(tt) >= am) {
            this.aspects.remove(tt, am);
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amt) {
        return this.aspects.getAmount(tag) >= amt;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.aspects.getAmount(tag);
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return this.openSides[face.ordinal()];
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return this.openSides[face.ordinal()];
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return this.openSides[face.ordinal()];
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return this.chokedSides[loc.ordinal()] == 2 ? 0 : (this.bellows <= 0 || this.chokedSides[loc.ordinal()] == 1 ? 1 : this.bellows * 32);
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspects.size() > 0 ? this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)] : null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.aspects.visSize();
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (!this.canOutputTo(face)) {
            return 0;
        }
        TileEntity te = null;
        IEssentiaTransport ic = null;
        int suction = 0;
        te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, face);
        if (te != null) {
            ic = (IEssentiaTransport)te;
            suction = ic.getSuctionAmount(face.getOpposite());
        }
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (!this.canOutputTo(dir) || dir == face || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir)) == null) continue;
            ic = (IEssentiaTransport)te;
            int sa = ic.getSuctionAmount(dir.getOpposite());
            Aspect su = ic.getSuctionType(dir.getOpposite());
            if (su != aspect && su != null || suction >= sa || this.getSuctionAmount(dir) >= sa) continue;
            return 0;
        }
        if (amount > this.aspects.getAmount(aspect)) {
            amount = this.aspects.getAmount(aspect);
        }
        return this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    public void func_145845_h() {
        ++this.count;
        if (this.bellows < 0 || this.count % 20 == 0) {
            this.getBellows();
        }
        if (!this.field_145850_b.field_72995_K && this.count % 5 == 0 && this.aspects.visSize() < this.MAXAMOUNT) {
            this.fillBuffer();
        }
    }

    void fillBuffer() {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, dir);
            if (te == null || (ic = (IEssentiaTransport)te).getEssentiaAmount(dir.getOpposite()) <= 0 || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(dir) || this.getSuctionAmount(dir) < ic.getMinimumSuction()) continue;
            Aspect ta = ic.getEssentiaType(dir.getOpposite());
            this.addToContainer(ta, ic.takeEssentia(ta, 1, dir.getOpposite()));
            return;
        }
    }

    public void getBellows() {
        this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.VALID_DIRECTIONS);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
        if (hit == null) {
            return 0;
        }
        if (hit.subHit >= 0 && hit.subHit < 6) {
            player.func_71038_i();
            if (player.func_70093_af()) {
                player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:squeek", 0.6f, 1.1f + world.field_73012_v.nextFloat() * 0.2f, false);
                if (!this.field_145850_b.field_72995_K) {
                    int n = hit.subHit;
                    this.chokedSides[n] = (byte)(this.chokedSides[n] + 1);
                    if (this.chokedSides[hit.subHit] > 2) {
                        this.chokedSides[hit.subHit] = 0;
                    }
                    this.func_70296_d();
                    world.func_147471_g(x, y, z);
                }
            } else {
                player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                this.openSides[hit.subHit] = !this.openSides[hit.subHit];
                ForgeDirection dir = ForgeDirection.getOrientation((int)hit.subHit);
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                if (tile != null && tile instanceof TileTube) {
                    ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
                    world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                    tile.func_70296_d();
                }
                if (tile != null && tile instanceof TileTubeBuffer) {
                    ((TileTubeBuffer)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
                    world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                    tile.func_70296_d();
                }
                this.func_70296_d();
                world.func_147471_g(x, y, z);
            }
        }
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    public MovingObjectPosition rayTrace(World world, Vec3 vec3d, Vec3 vec3d1, MovingObjectPosition fullblock) {
        return fullblock;
    }

    private boolean canConnectSide(int side) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)side);
        TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
        return tile != null && tile instanceof IEssentiaTransport;
    }

    public void addTraceableCuboids(List<IndexedCuboid6> cuboids) {
        float min = 0.42f;
        float max = 0.58f;
        if (this.canConnectSide(0)) {
            cuboids.add(new IndexedCuboid6(0, new Cuboid6((float)this.field_145851_c + min, this.field_145848_d, (float)this.field_145849_e + min, (float)this.field_145851_c + max, (double)this.field_145848_d + 0.5, (float)this.field_145849_e + max)));
        }
        if (this.canConnectSide(1)) {
            cuboids.add(new IndexedCuboid6(1, new Cuboid6((float)this.field_145851_c + min, (double)this.field_145848_d + 0.5, (float)this.field_145849_e + min, (float)this.field_145851_c + max, this.field_145848_d + 1, (float)this.field_145849_e + max)));
        }
        if (this.canConnectSide(2)) {
            cuboids.add(new IndexedCuboid6(2, new Cuboid6((float)this.field_145851_c + min, (float)this.field_145848_d + min, this.field_145849_e, (float)this.field_145851_c + max, (float)this.field_145848_d + max, (double)this.field_145849_e + 0.5)));
        }
        if (this.canConnectSide(3)) {
            cuboids.add(new IndexedCuboid6(3, new Cuboid6((float)this.field_145851_c + min, (float)this.field_145848_d + min, (double)this.field_145849_e + 0.5, (float)this.field_145851_c + max, (float)this.field_145848_d + max, this.field_145849_e + 1)));
        }
        if (this.canConnectSide(4)) {
            cuboids.add(new IndexedCuboid6(4, new Cuboid6(this.field_145851_c, (float)this.field_145848_d + min, (float)this.field_145849_e + min, (double)this.field_145851_c + 0.5, (float)this.field_145848_d + max, (float)this.field_145849_e + max)));
        }
        if (this.canConnectSide(5)) {
            cuboids.add(new IndexedCuboid6(5, new Cuboid6((double)this.field_145851_c + 0.5, (float)this.field_145848_d + min, (float)this.field_145849_e + min, this.field_145851_c + 1, (float)this.field_145848_d + max, (float)this.field_145849_e + max)));
        }
        cuboids.add(new IndexedCuboid6(6, new Cuboid6((float)this.field_145851_c + 0.25f, (float)this.field_145848_d + 0.25f, (float)this.field_145849_e + 0.25f, (float)this.field_145851_c + 0.75f, (float)this.field_145848_d + 0.75f, (float)this.field_145849_e + 0.75f)));
    }
}

