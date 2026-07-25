/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.data.MCDataInput
 *  codechicken.lib.data.MCDataOutput
 *  codechicken.lib.raytracer.IndexedCuboid6
 *  codechicken.lib.raytracer.RayTracer
 *  codechicken.lib.vec.Cuboid6
 *  codechicken.lib.vec.Vector3
 *  codechicken.multipart.TMultiPart
 *  codechicken.multipart.TileMultipart
 *  codechicken.multipart.minecraft.IPartMeta
 *  codechicken.multipart.minecraft.McMetaPart
 *  codechicken.multipart.minecraft.PartMetaAccess
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.api.wands.IWandable
 *  thaumcraft.common.blocks.BlockTube
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.relics.ItemResonator
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileBellows
 *  thaumcraft.common.tiles.TileTube
 *  thaumcraft.common.tiles.TileTubeBuffer
 */
package witchinggadgets.common.blocks.tiles;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.raytracer.RayTracer;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.IPartMeta;
import codechicken.multipart.minecraft.McMetaPart;
import codechicken.multipart.minecraft.PartMetaAccess;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.relics.ItemResonator;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileTube;
import thaumcraft.common.tiles.TileTubeBuffer;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube_Valve;

public class MultipartEssentiaBuffer
extends McMetaPart
implements IAspectContainer,
IEssentiaTransport,
IWandable {
    public AspectList aspects = new AspectList();
    public final int MAXAMOUNT = 8;
    public boolean[] openSides = new boolean[]{true, true, true, true, true, true};
    public byte[] chokedSides = new byte[]{0, 0, 0, 0, 0, 0};
    int count = 0;
    int bellows = -1;

    public MultipartEssentiaBuffer(int meta) {
        super(meta);
    }

    public Cuboid6 getBounds() {
        float minx = 0.375f;
        float maxx = 0.625f;
        float miny = 0.375f;
        float maxy = 0.625f;
        float minz = 0.375f;
        float maxz = 0.625f;
        ForgeDirection fd = null;
        block8: for (int side = 0; side < 6; ++side) {
            fd = ForgeDirection.getOrientation((int)side);
            TileEntity te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)fd);
            if (te == null) continue;
            switch (side) {
                case 0: {
                    miny = 0.0f;
                    continue block8;
                }
                case 1: {
                    maxy = 1.0f;
                    continue block8;
                }
                case 2: {
                    minz = 0.0f;
                    continue block8;
                }
                case 3: {
                    maxz = 1.0f;
                    continue block8;
                }
                case 4: {
                    minx = 0.0f;
                    continue block8;
                }
                case 5: {
                    maxx = 1.0f;
                }
            }
        }
        return new Cuboid6((double)minx, (double)miny, (double)minz, (double)maxx, (double)maxy, (double)maxz);
    }

    public Iterable<IndexedCuboid6> getSubParts() {
        ArrayList<IndexedCuboid6> t = new ArrayList<IndexedCuboid6>();
        if (this.world().field_72995_K && (Minecraft.func_71410_x().field_71439_g.func_71045_bC() == null || !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemWandCasting) && !(Minecraft.func_71410_x().field_71439_g.func_71045_bC().func_77973_b() instanceof ItemResonator))) {
            t.add(new IndexedCuboid6(null, this.getBounds()));
        } else {
            for (int i = 0; i < 6; ++i) {
                ForgeDirection fd = ForgeDirection.getOrientation((int)i);
                if (!(this.world().func_147438_o(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ) instanceof IEssentiaTransport)) continue;
                t.add(this.getConnectionPipe(ForgeDirection.getOrientation((int)i)));
            }
            t.add(new IndexedCuboid6(null, new Cuboid6(0.25, 0.25, 0.25, 0.75, 0.75, 0.75)));
        }
        return t;
    }

    public Iterable<Cuboid6> getOcclusionBoxes() {
        ArrayList<Cuboid6> t = new ArrayList<Cuboid6>();
        t.add(new Cuboid6(0.25, 0.25, 0.25, 0.75, 0.75, 0.75));
        return t;
    }

    public Iterable<Cuboid6> getCollisionBoxes() {
        ArrayList<Cuboid6> t = new ArrayList<Cuboid6>();
        t.add(this.getBounds());
        return t;
    }

    public void invalidateConvertedTile() {
        super.invalidateConvertedTile();
        TileEntity te = this.world().func_147438_o(this.x(), this.y(), this.z());
        if (te instanceof TileTubeBuffer) {
            this.aspects = ((TileTubeBuffer)te).aspects;
            this.openSides = ((TileTubeBuffer)te).openSides;
            this.chokedSides = ((TileTubeBuffer)te).chokedSides;
        }
    }

    public boolean doesTick() {
        return true;
    }

    public void update() {
        super.update();
        ++this.count;
        if (this.bellows < 0 || this.count % 20 == 0) {
            this.getBellows();
        }
        if (!this.world().field_72995_K && this.count % 5 == 0 && this.aspects.visSize() < 8) {
            this.fillBuffer();
        }
    }

    void fillBuffer() {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)dir);
            if (te == null || (ic = (IEssentiaTransport)te).getEssentiaAmount(dir.getOpposite()) <= 0 || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(dir) || this.getSuctionAmount(dir) < ic.getMinimumSuction()) continue;
            Aspect ta = ic.getEssentiaType(dir.getOpposite());
            this.addToContainer(ta, ic.takeEssentia(ta, 1, dir.getOpposite()));
            return;
        }
    }

    public void getBellows() {
        this.bellows = TileBellows.getBellows((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection[])ForgeDirection.VALID_DIRECTIONS);
    }

    public boolean renderStatic(Vector3 pos, int pass) {
        RenderBlocks renderer = new RenderBlocks((IBlockAccess)new PartMetaAccess((IPartMeta)this));
        BlockTube b = (BlockTube)ConfigBlocks.blockTube;
        renderer.func_147782_a(0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
        renderer.func_147784_q(this.getWorld().func_147439_a(this.x(), this.y(), this.z()), this.x(), this.y(), this.z());
        boolean hasConnections = false;
        for (int i = 0; i < 6; ++i) {
            double yMax;
            double xMax;
            double zMin;
            double yMin;
            double xMin;
            TileEntity te;
            ForgeDirection fd = ForgeDirection.getOrientation((int)i);
            if (!this.isConnectable(fd) || (te = this.getConnectableTile(renderer.field_147845_a, this.x(), this.y(), this.z(), fd)) == null) continue;
            if (!hasConnections) {
                hasConnections = true;
            }
            double d = fd == ForgeDirection.WEST ? 0.0 : (xMin = fd == ForgeDirection.EAST ? 0.75 : 0.4375);
            double d2 = fd == ForgeDirection.DOWN ? 0.0 : (yMin = fd == ForgeDirection.UP ? 0.75 : 0.4375);
            double d3 = fd == ForgeDirection.NORTH ? 0.0 : (zMin = fd == ForgeDirection.SOUTH ? 0.75 : 0.4375);
            double d4 = fd == ForgeDirection.WEST ? 0.25 : (xMax = fd == ForgeDirection.EAST ? 1.0 : 0.5625);
            double d5 = fd == ForgeDirection.DOWN ? 0.25 : (yMax = fd == ForgeDirection.UP ? 1.0 : 0.5625);
            double zMax = fd == ForgeDirection.NORTH ? 0.25 : (fd == ForgeDirection.SOUTH ? 1.0 : 0.5625);
            ClientUtilities.addBoxToBlockrender(xMin, yMin, zMin, xMax, yMax, zMax, b.icon[5], this.x(), this.y(), this.z());
            if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue;
            double d6 = fd == ForgeDirection.WEST ? 0.0 : (xMin = fd == ForgeDirection.EAST ? 0.625 : 0.4375);
            double d7 = fd == ForgeDirection.DOWN ? 0.0 : (yMin = fd == ForgeDirection.UP ? 0.625 : 0.4375);
            double d8 = fd == ForgeDirection.NORTH ? 0.0 : (zMin = fd == ForgeDirection.SOUTH ? 0.625 : 0.4375);
            double d9 = fd == ForgeDirection.WEST ? 0.375 : (xMax = fd == ForgeDirection.EAST ? 1.0 : 0.5625);
            double d10 = fd == ForgeDirection.DOWN ? 0.375 : (yMax = fd == ForgeDirection.UP ? 1.0 : 0.5625);
            zMax = fd == ForgeDirection.NORTH ? 0.375 : (fd == ForgeDirection.SOUTH ? 1.0 : 0.5625);
            ClientUtilities.addBoxToBlockrender(Vec3.func_72443_a((double)((double)fd.offsetX * 0.25), (double)((double)fd.offsetY * 0.375), (double)((double)fd.offsetZ * 0.375)), xMin, yMin, zMin, xMax, yMax, zMax, b.icon[5], this.x(), this.y(), this.z());
        }
        ClientUtilities.addBoxToBlockrender(0.25, 0.25, 0.25, 0.75, 0.75, 0.75, b.icon[5], this.x(), this.y(), this.z());
        return true;
    }

    public void renderDynamic(Vector3 pos, float partialTickTime, int pass) {
        ClientUtilities.bindTexture("thaumcraft:textures/models/valve.png");
        for (ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
            if (this.chokedSides[fd.ordinal()] == 0 || !this.openSides[fd.ordinal()] || ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)fd) == null) continue;
            GL11.glPushMatrix();
            GL11.glTranslated((double)(pos.x + 0.5), (double)(pos.y + 0.5), (double)(pos.z + 0.5));
            if (fd.getOpposite().offsetY == 0) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)fd.getOpposite().offsetY, (float)0.0f, (float)0.0f);
            }
            GL11.glRotatef((float)90.0f, (float)fd.getOpposite().offsetX, (float)fd.getOpposite().offsetY, (float)fd.getOpposite().offsetZ);
            GL11.glPushMatrix();
            if (this.chokedSides[fd.ordinal()] == 2) {
                GL11.glColor3f((float)1.0f, (float)0.3f, (float)0.3f);
            } else {
                GL11.glColor3f((float)0.3f, (float)0.3f, (float)1.0f);
            }
            GL11.glScaled((double)1.2, (double)1.0, (double)1.2);
            GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
            MultipartEssentiaTube_Valve.valveModel.render();
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
    }

    public TileEntity getConnectableTile(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        TileEntity te = world.func_147438_o(x + face.offsetX, y + face.offsetY, z + face.offsetZ);
        if (te instanceof IEssentiaTransport && ((IEssentiaTransport)te).isConnectable(face.getOpposite())) {
            return te;
        }
        if (te instanceof TileBellows && ((TileBellows)te).orientation == face.getOpposite().ordinal()) {
            return te;
        }
        return null;
    }

    public void save(NBTTagCompound tag) {
        super.save(tag);
        this.aspects.writeToNBT(tag);
        byte[] sides = new byte[6];
        for (int a = 0; a < 6; ++a) {
            sides[a] = (byte)(this.openSides[a] ? 1 : 0);
        }
        tag.func_74773_a("open", sides);
        tag.func_74773_a("choke", this.chokedSides);
    }

    public void load(NBTTagCompound tag) {
        super.load(tag);
        this.aspects.readFromNBT(tag);
        byte[] sides = tag.func_74770_j("open");
        if (sides != null && sides.length == 6) {
            for (int a = 0; a < 6; ++a) {
                this.openSides[a] = sides[a] == 1;
            }
        }
        this.chokedSides = tag.func_74770_j("choke");
        if (this.chokedSides == null || this.chokedSides.length < 6) {
            this.chokedSides = new byte[]{0, 0, 0, 0, 0, 0};
        }
    }

    public void writeDesc(MCDataOutput packet) {
        super.writeDesc(packet);
        NBTTagCompound tag = new NBTTagCompound();
        this.save(tag);
        packet.writeNBTTagCompound(tag);
    }

    public void readDesc(MCDataInput packet) {
        super.readDesc(packet);
        NBTTagCompound tag = packet.readNBTTagCompound();
        this.load(tag);
    }

    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(new ItemStack(this.getBlock(), 1, (int)this.meta));
    }

    public ItemStack pickItem(MovingObjectPosition hit) {
        return new ItemStack(this.getBlock(), 1, (int)this.meta);
    }

    public Block getBlock() {
        return ConfigBlocks.blockTube;
    }

    public String getType() {
        return "witchingGadgets:essentia_buffer";
    }

    public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, MovingObjectPosition mop) {
        Vec3 localHit = mop.field_72307_f.func_72441_c((double)(-this.x()), (double)(-this.y()), (double)(-this.z()));
        for (IndexedCuboid6 ibox : this.getSubParts()) {
            if (!(ibox.min.x <= localHit.field_72450_a) || !(ibox.max.x >= localHit.field_72450_a) || !(ibox.min.y <= localHit.field_72448_b) || !(ibox.max.y >= localHit.field_72448_b) || !(ibox.min.z <= localHit.field_72449_c) || !(ibox.max.z >= localHit.field_72449_c)) continue;
            if (ibox.data instanceof ForgeDirection) {
                ForgeDirection fd = (ForgeDirection)ibox.data;
                player.func_71038_i();
                if (player.func_70093_af()) {
                    player.field_70170_p.func_72980_b((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:tool", 0.6f, 1.1f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                    int n = fd.ordinal();
                    this.chokedSides[n] = (byte)(this.chokedSides[n] + 1);
                    if (this.chokedSides[fd.ordinal()] > 2) {
                        this.chokedSides[fd.ordinal()] = 0;
                    }
                    world.func_147471_g(this.x(), this.y(), this.z());
                } else {
                    player.field_70170_p.func_72980_b((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                    this.openSides[fd.ordinal()] = !this.openSides[fd.ordinal()];
                    world.func_147471_g(this.x(), this.y(), this.z());
                    TileEntity tile = this.world().func_147438_o(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                    if (tile != null) {
                        if (tile instanceof TileTube) {
                            ((TileTube)tile).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                            world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                            tile.func_70296_d();
                        }
                        if (tile instanceof TileTubeBuffer) {
                            ((TileTubeBuffer)tile).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                            world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                            tile.func_70296_d();
                        }
                        if (tile instanceof TileMultipart) {
                            for (TMultiPart part : ((TileMultipart)tile).jPartList()) {
                                if (part instanceof MultipartEssentiaTube) {
                                    ((MultipartEssentiaTube)part).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                                    world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                                    continue;
                                }
                                if (!(part instanceof MultipartEssentiaBuffer)) continue;
                                ((MultipartEssentiaBuffer)part).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                                world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                            }
                        }
                    }
                }
            }
            return world.field_72995_K ? 0 : 1;
        }
        return 0;
    }

    public boolean activate(EntityPlayer player, MovingObjectPosition hit, ItemStack stack) {
        return false;
    }

    IndexedCuboid6 getConnectionPipe(ForgeDirection fd) {
        double yMax;
        double xMax;
        double zMin;
        double yMin;
        double xMin;
        double d = fd == ForgeDirection.WEST ? 0.0 : (xMin = fd == ForgeDirection.EAST ? 0.578125 : 0.421875);
        double d2 = fd == ForgeDirection.DOWN ? 0.0 : (yMin = fd == ForgeDirection.UP ? 0.578125 : 0.421875);
        double d3 = fd == ForgeDirection.NORTH ? 0.0 : (zMin = fd == ForgeDirection.SOUTH ? 0.578125 : 0.421875);
        double d4 = fd == ForgeDirection.WEST ? 0.421875 : (xMax = fd == ForgeDirection.EAST ? 1.0 : 0.578125);
        double d5 = fd == ForgeDirection.DOWN ? 0.421875 : (yMax = fd == ForgeDirection.UP ? 1.0 : 0.578125);
        double zMax = fd == ForgeDirection.NORTH ? 0.421875 : (fd == ForgeDirection.SOUTH ? 1.0 : 0.578125);
        return new IndexedCuboid6((Object)fd, new Cuboid6(xMin, yMin, zMin, xMax, yMax, zMax));
    }

    public boolean isConnectable(ForgeDirection fd) {
        if (fd == ForgeDirection.UNKNOWN) {
            return false;
        }
        boolean wall = false;
        IndexedCuboid6 pipe = this.getConnectionPipe(fd);
        for (TMultiPart otherPart : this.tile().jPartList()) {
            if (otherPart == this) continue;
            for (Cuboid6 box : otherPart.getCollisionBoxes()) {
                if (!box.intersects((Cuboid6)pipe)) continue;
                wall = true;
            }
        }
        return this.openSides[fd.ordinal()] && !wall;
    }

    public boolean canInputFrom(ForgeDirection fd) {
        if (fd == ForgeDirection.UNKNOWN) {
            return false;
        }
        return this.isConnectable(fd) && this.openSides[fd.ordinal()];
    }

    public boolean canOutputTo(ForgeDirection fd) {
        if (fd == ForgeDirection.UNKNOWN) {
            return false;
        }
        return this.isConnectable(fd) && this.openSides[fd.ordinal()];
    }

    public void setSuction(Aspect aspect, int amount) {
    }

    public Aspect getSuctionType(ForgeDirection fd) {
        return null;
    }

    public int getSuctionAmount(ForgeDirection fd) {
        return this.chokedSides[fd.ordinal()] == 2 ? 0 : (this.bellows <= 0 || this.chokedSides[fd.ordinal()] == 1 ? 1 : this.bellows * 32);
    }

    public Aspect getEssentiaType(ForgeDirection fd) {
        return this.aspects.size() > 0 ? this.aspects.getAspects()[this.world().field_73012_v.nextInt(this.aspects.getAspects().length)] : null;
    }

    public int getEssentiaAmount(ForgeDirection fd) {
        return this.aspects.visSize();
    }

    public int takeEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        if (!this.canOutputTo(fd)) {
            return 0;
        }
        TileEntity te = null;
        IEssentiaTransport ic = null;
        int suction = 0;
        te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)fd);
        if (te != null) {
            ic = (IEssentiaTransport)te;
            suction = ic.getSuctionAmount(fd.getOpposite());
        }
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (!this.canOutputTo(dir) || dir == fd || (te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)dir)) == null) continue;
            ic = (IEssentiaTransport)te;
            int sa = ic.getSuctionAmount(dir.getOpposite());
            Aspect su = ic.getSuctionType(dir.getOpposite());
            if (su != aspect && su != null || suction >= sa || this.getSuctionAmount(dir) >= sa) continue;
            return 0;
        }
        return this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    public int addEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        return this.canInputFrom(fd) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    void markDirty() {
        this.tile().func_70296_d();
    }

    public int addToContainer(Aspect aspect, int amount) {
        if (amount != 1) {
            return amount;
        }
        if (this.aspects.visSize() < 8) {
            this.aspects.add(aspect, amount);
            this.markDirty();
            this.world().func_147471_g(this.x(), this.y(), this.z());
            return 0;
        }
        return amount;
    }

    public boolean takeFromContainer(Aspect aspect, int amount) {
        if (this.aspects.getAmount(aspect) >= amount) {
            this.aspects.remove(aspect, amount);
            this.markDirty();
            this.world().func_147471_g(this.x(), this.y(), this.z());
            return true;
        }
        return false;
    }

    public boolean takeFromContainer(AspectList arg0) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect aspect, int amount) {
        return this.aspects.getAmount(aspect) >= amount;
    }

    public boolean doesContainerContain(AspectList arg0) {
        return false;
    }

    public int containerContains(Aspect aspect) {
        return this.aspects.getAmount(aspect);
    }

    public boolean doesContainerAccept(Aspect aspect) {
        return true;
    }

    public AspectList getAspects() {
        return this.aspects;
    }

    public void setAspects(AspectList aspects) {
    }

    public void onUsingWandTick(ItemStack wand, EntityPlayer player, int time) {
    }

    public ItemStack onWandRightClick(World world, ItemStack wand, EntityPlayer player) {
        return null;
    }

    public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, int x, int y, int z, int side, int meta) {
        return this.onWandRightClick(world, wand, player, RayTracer.retraceBlock((World)this.world(), (EntityPlayer)player, (int)this.x(), (int)this.y(), (int)this.z()));
    }

    public void onWandStoppedUsing(ItemStack wand, World arg1, EntityPlayer player, int time) {
    }
}

