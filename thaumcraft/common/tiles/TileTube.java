/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
import thaumcraft.codechicken.lib.raytracer.RayTracer;
import thaumcraft.codechicken.lib.vec.Cuboid6;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

public class TileTube
extends TileThaumcraft
implements IEssentiaTransport,
IWandable {
    public ForgeDirection facing = ForgeDirection.NORTH;
    public boolean[] openSides = new boolean[]{true, true, true, true, true, true};
    Aspect essentiaType = null;
    int essentiaAmount = 0;
    Aspect suctionType = null;
    int suction = 0;
    int venting = 0;
    int count = 0;
    static final int freq = 5;
    int ventColor = 0;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.essentiaType = Aspect.getAspect(nbttagcompound.func_74779_i("type"));
        this.essentiaAmount = nbttagcompound.func_74762_e("amount");
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("side"));
        byte[] sides = nbttagcompound.func_74770_j("open");
        if (sides != null && sides.length == 6) {
            for (int a = 0; a < 6; ++a) {
                this.openSides[a] = sides[a] == 1;
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.essentiaType != null) {
            nbttagcompound.func_74778_a("type", this.essentiaType.getTag());
        }
        nbttagcompound.func_74768_a("amount", this.essentiaAmount);
        byte[] sides = new byte[6];
        for (int a = 0; a < 6; ++a) {
            sides[a] = this.openSides[a] ? (byte)1 : 0;
        }
        nbttagcompound.func_74768_a("side", this.facing.ordinal());
        nbttagcompound.func_74773_a("open", sides);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.suctionType = Aspect.getAspect(nbttagcompound.func_74779_i("stype"));
        this.suction = nbttagcompound.func_74762_e("samount");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        if (this.suctionType != null) {
            nbttagcompound.func_74778_a("stype", this.suctionType.getTag());
        }
        nbttagcompound.func_74768_a("samount", this.suction);
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.venting > 0) {
            --this.venting;
        }
        if (this.count == 0) {
            this.count = this.field_145850_b.field_73012_v.nextInt(10);
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.venting <= 0) {
                if (++this.count % 2 == 0) {
                    this.calculateSuction(null, false, false);
                    this.checkVenting();
                    if (this.essentiaType != null && this.essentiaAmount == 0) {
                        this.essentiaType = null;
                    }
                }
                if (this.count % 5 == 0 && this.suction > 0) {
                    this.equalizeWithNeighbours(false);
                }
            }
        } else if (this.venting > 0) {
            Random r = new Random(this.hashCode() * 4);
            float rp = r.nextFloat() * 360.0f;
            float ry = r.nextFloat() * 360.0f;
            double fx = -MathHelper.func_76126_a((float)(ry / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rp / 180.0f * (float)Math.PI));
            double fz = MathHelper.func_76134_b((float)(ry / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(rp / 180.0f * (float)Math.PI));
            double fy = -MathHelper.func_76126_a((float)(rp / 180.0f * (float)Math.PI));
            Thaumcraft.proxy.drawVentParticles(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, fx / 5.0, fy / 5.0, fx / 5.0, this.ventColor);
        }
    }

    void calculateSuction(Aspect filter, boolean restrict, boolean directional) {
        this.suction = 0;
        this.suctionType = null;
        ForgeDirection loc = null;
        for (int dir = 0; dir < 6; ++dir) {
            try {
                int suck;
                TileEntity te;
                loc = ForgeDirection.getOrientation((int)dir);
                if (directional && this.facing != loc.getOpposite() || !this.isConnectable(loc) || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc)) == null) continue;
                IEssentiaTransport ic = (IEssentiaTransport)te;
                if (filter != null && ic.getSuctionType(loc.getOpposite()) != null && ic.getSuctionType(loc.getOpposite()) != filter || filter == null && this.getEssentiaAmount(loc) > 0 && ic.getSuctionType(loc.getOpposite()) != null && this.getEssentiaType(loc) != ic.getSuctionType(loc.getOpposite()) || filter != null && this.getEssentiaAmount(loc) > 0 && this.getEssentiaType(loc) != null && ic.getSuctionType(loc.getOpposite()) != null && this.getEssentiaType(loc) != ic.getSuctionType(loc.getOpposite()) || (suck = ic.getSuctionAmount(loc.getOpposite())) <= 0 || suck <= this.suction + 1) continue;
                Aspect st = ic.getSuctionType(loc.getOpposite());
                if (st == null) {
                    st = filter;
                }
                this.setSuction(st, restrict ? suck / 2 : suck - 1);
                continue;
            }
            catch (Exception e) {
                // empty catch block
            }
        }
    }

    void checkVenting() {
        ForgeDirection loc = null;
        for (int dir = 0; dir < 6; ++dir) {
            try {
                TileEntity te;
                loc = ForgeDirection.getOrientation((int)dir);
                if (!this.isConnectable(loc) || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc)) == null) continue;
                IEssentiaTransport ic = (IEssentiaTransport)te;
                int suck = ic.getSuctionAmount(loc.getOpposite());
                if (this.suction <= 0 || suck != this.suction && suck != this.suction - 1 || this.suctionType == ic.getSuctionType(loc.getOpposite())) continue;
                int c = -1;
                if (this.suctionType != null) {
                    c = Config.aspectOrder.indexOf(this.suctionType);
                }
                this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTube, 1, c);
                this.venting = 40;
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    void equalizeWithNeighbours(boolean directional) {
        ForgeDirection loc = null;
        if (this.essentiaAmount > 0) {
            return;
        }
        for (int dir = 0; dir < 6; ++dir) {
            try {
                int am;
                IEssentiaTransport ic;
                TileEntity te;
                loc = ForgeDirection.getOrientation((int)dir);
                if (directional && this.facing == loc.getOpposite() || !this.isConnectable(loc) || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, loc)) == null || !(ic = (IEssentiaTransport)te).canOutputTo(loc.getOpposite()) || this.getSuctionType(null) != null && this.getSuctionType(null) != ic.getEssentiaType(loc.getOpposite()) && ic.getEssentiaType(loc.getOpposite()) != null || this.getSuctionAmount(null) <= ic.getSuctionAmount(loc.getOpposite()) || this.getSuctionAmount(null) < ic.getMinimumSuction()) continue;
                Aspect a = this.getSuctionType(null);
                if (a == null && (a = ic.getEssentiaType(loc.getOpposite())) == null) {
                    a = ic.getEssentiaType(ForgeDirection.UNKNOWN);
                }
                if ((am = this.addEssentia(a, ic.takeEssentia(a, 1, loc.getOpposite()), loc)) <= 0) continue;
                if (this.field_145850_b.field_73012_v.nextInt(100) == 0) {
                    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockTube, 0, 0);
                }
                return;
            }
            catch (Exception e) {
                // empty catch block
            }
        }
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        if (face == ForgeDirection.UNKNOWN) {
            return false;
        }
        return this.openSides[face.ordinal()];
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        if (face == ForgeDirection.UNKNOWN) {
            return false;
        }
        return this.openSides[face.ordinal()];
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        if (face == ForgeDirection.UNKNOWN) {
            return false;
        }
        return this.openSides[face.ordinal()];
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
        this.suctionType = aspect;
        this.suction = amount;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return this.suctionType;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return this.suction;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.essentiaType;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.essentiaAmount;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.canOutputTo(face) && this.essentiaType == aspect && this.essentiaAmount > 0 && amount > 0) {
            --this.essentiaAmount;
            if (this.essentiaAmount <= 0) {
                this.essentiaType = null;
            }
            this.func_70296_d();
            return 1;
        }
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.canInputFrom(face) && this.essentiaAmount == 0 && amount > 0) {
            this.essentiaType = aspect;
            ++this.essentiaAmount;
            this.func_70296_d();
            return 1;
        }
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 0) {
            if (this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:creak", 1.0f, 1.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, false);
            }
            return true;
        }
        if (i == 1) {
            if (this.field_145850_b.field_72995_K) {
                if (this.venting <= 0) {
                    this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "random.fizz", 0.1f, 1.0f + this.field_145850_b.field_73012_v.nextFloat() * 0.1f, false);
                }
                this.venting = 50;
                this.ventColor = j == -1 || j >= Config.aspectOrder.size() ? 0xAAAAAA : Config.aspectOrder.get(j).getColor();
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
        if (hit == null) {
            return 0;
        }
        if (hit.subHit >= 0 && hit.subHit < 6) {
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
            this.func_70296_d();
            world.func_147471_g(x, y, z);
            this.openSides[hit.subHit] = !this.openSides[hit.subHit];
            ForgeDirection dir = ForgeDirection.getOrientation((int)hit.subHit);
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
            if (tile != null && tile instanceof TileTube) {
                ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
                world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                tile.func_70296_d();
            }
        }
        if (hit.subHit == 6) {
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
            int a = this.facing.ordinal();
            this.func_70296_d();
            while (++a < 20) {
                if (!this.canConnectSide(ForgeDirection.getOrientation((int)(a % 6)).getOpposite().ordinal()) || !this.isConnectable(ForgeDirection.getOrientation((int)(a % 6)).getOpposite())) continue;
                this.facing = ForgeDirection.getOrientation((int)(a %= 6));
                world.func_147471_g(x, y, z);
                break;
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

    protected boolean canConnectSide(int side) {
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
        cuboids.add(new IndexedCuboid6(6, new Cuboid6((double)this.field_145851_c + 0.34375, (double)this.field_145848_d + 0.34375, (double)this.field_145849_e + 0.34375, (double)this.field_145851_c + 0.65625, (double)this.field_145848_d + 0.65625, (double)this.field_145849_e + 0.65625)));
    }
}

