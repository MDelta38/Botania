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
 *  codechicken.multipart.JNormalOcclusion
 *  codechicken.multipart.JPartialOcclusion
 *  codechicken.multipart.TMultiPart
 *  codechicken.multipart.TSlottedPart
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
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.api.wands.IWandable
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.blocks.BlockTube
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.relics.ItemResonator
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileBellows
 *  thaumcraft.common.tiles.TileTube
 */
package witchinggadgets.common.blocks.tiles;

import codechicken.lib.data.MCDataInput;
import codechicken.lib.data.MCDataOutput;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.raytracer.RayTracer;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.JPartialOcclusion;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TSlottedPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.IPartMeta;
import codechicken.multipart.minecraft.McMetaPart;
import codechicken.multipart.minecraft.PartMetaAccess;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTube;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.relics.ItemResonator;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileTube;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube_Valve;
import witchinggadgets.common.util.handler.WGMultiPartHandler;

public class MultipartEssentiaTube
extends McMetaPart
implements IEssentiaTransport,
IWandable,
TSlottedPart {
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

    public MultipartEssentiaTube(int meta) {
        super(meta);
    }

    public int getSlotMask() {
        return 64;
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

    public Iterable<Cuboid6> getOcclusionBoxes() {
        ArrayList<Cuboid6> t = new ArrayList<Cuboid6>();
        t.add(new Cuboid6(0.375, 0.375, 0.375, 0.625, 0.625, 0.625));
        return t;
    }

    public Iterable<Cuboid6> getCollisionBoxes() {
        ArrayList<Cuboid6> t = new ArrayList<Cuboid6>();
        t.add(this.getBounds());
        return t;
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
            t.add(new IndexedCuboid6(null, new Cuboid6(0.375, 0.375, 0.375, 0.625, 0.625, 0.625)));
        }
        return t;
    }

    public boolean drawHighlight(MovingObjectPosition hit, EntityPlayer player, float frame) {
        return false;
    }

    public void invalidateConvertedTile() {
        super.invalidateConvertedTile();
        TileEntity te = this.world().func_147438_o(this.x(), this.y(), this.z());
        if (te instanceof TileTube) {
            this.facing = ((TileTube)te).facing;
            this.openSides = ((TileTube)te).openSides;
            this.essentiaType = ((TileTube)te).getEssentiaType(this.facing);
            this.essentiaAmount = ((TileTube)te).getEssentiaAmount(this.facing);
            this.suctionType = ((TileTube)te).getSuctionType(this.facing);
            this.suction = ((TileTube)te).getSuctionAmount(this.facing);
        }
    }

    public boolean doesTick() {
        return true;
    }

    public void update() {
        super.update();
        if (this.venting > 0) {
            --this.venting;
        }
        if (this.count == 0) {
            this.count = this.world().field_73012_v.nextInt(10);
        }
        if (!this.world().field_72995_K) {
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
            Random r = new Random(((Object)((Object)this)).hashCode() * 4);
            float rp = r.nextFloat() * 360.0f;
            float ry = r.nextFloat() * 360.0f;
            double fx = -MathHelper.func_76126_a((float)(ry / 180.0f * 3.141593f)) * MathHelper.func_76134_b((float)(rp / 180.0f * 3.141593f));
            double fz = MathHelper.func_76134_b((float)(ry / 180.0f * 3.141593f)) * MathHelper.func_76134_b((float)(rp / 180.0f * 3.141593f));
            double fy = -MathHelper.func_76126_a((float)(rp / 180.0f * 3.141593f));
            Thaumcraft.proxy.drawVentParticles(this.world(), (double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, fx / 5.0, fy / 5.0, fz / 5.0, this.ventColor);
        }
    }

    void calculateSuction(Aspect filter, boolean restrict, boolean directional) {
        if (this.meta == 5) {
            restrict = true;
        }
        if (this.meta == 6) {
            directional = true;
        }
        this.suction = 0;
        this.suctionType = null;
        ForgeDirection loc = null;
        for (int dir = 0; dir < 6; ++dir) {
            try {
                int suck;
                TileEntity te;
                loc = ForgeDirection.getOrientation((int)dir);
                if (directional && this.facing != loc.getOpposite() || !this.isConnectable(loc) || (te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)loc)) == null) continue;
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
                if (!this.isConnectable(loc) || (te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)loc)) == null) continue;
                IEssentiaTransport ic = (IEssentiaTransport)te;
                int suck = ic.getSuctionAmount(loc.getOpposite());
                if (this.suction <= 0 || suck != this.suction && suck != this.suction - 1 || this.suctionType == ic.getSuctionType(loc.getOpposite())) continue;
                this.venting = 40;
                continue;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    void equalizeWithNeighbours(boolean directional) {
        if (this.meta == 6) {
            directional = true;
        }
        ForgeDirection fd = null;
        if (this.essentiaAmount > 0) {
            return;
        }
        for (int dir = 0; dir < 6; ++dir) {
            try {
                int am;
                IEssentiaTransport ic;
                TileEntity te;
                fd = ForgeDirection.getOrientation((int)dir);
                if (directional && this.facing == fd.getOpposite() || !this.isConnectable(fd) || (te = ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)fd)) == null || !(ic = (IEssentiaTransport)te).canOutputTo(fd.getOpposite()) || this.getSuctionType(null) != null && this.getSuctionType(null) != ic.getEssentiaType(fd.getOpposite()) && ic.getEssentiaType(fd.getOpposite()) != null || this.getSuctionAmount(null) <= ic.getSuctionAmount(fd.getOpposite()) || this.getSuctionAmount(null) < ic.getMinimumSuction()) continue;
                Aspect a = this.getSuctionType(null);
                if (a == null && (a = ic.getEssentiaType(fd.getOpposite())) == null) {
                    a = ic.getEssentiaType(ForgeDirection.UNKNOWN);
                }
                if ((am = this.addEssentia(a, ic.takeEssentia(a, 1, fd.getOpposite()), fd)) <= 0) continue;
                return;
            }
            catch (Exception e) {
                // empty catch block
            }
        }
    }

    public boolean renderStatic(Vector3 pos, int pass) {
        RenderBlocks renderer = new RenderBlocks((IBlockAccess)new PartMetaAccess((IPartMeta)this));
        BlockTube b = (BlockTube)ConfigBlocks.blockTube;
        Block bMP = this.getWorld().func_147439_a(this.x(), this.y(), this.z());
        renderer.func_147782_a(0.375, 0.375, 0.375, 0.625, 0.625, 0.625);
        renderer.func_147784_q(bMP, this.x(), this.y(), this.z());
        IIcon icon = this.meta == 5 ? b.icon[6] : b.icon[0];
        boolean hasConnections = false;
        boolean overrideCenter = false;
        for (ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
            double yMax;
            double xMax;
            double zMin;
            double yMin;
            double xMin;
            TileEntity te;
            if (!this.isConnectable(fd) || (te = this.getConnectableTile(renderer.field_147845_a, this.x(), this.y(), this.z(), fd)) == null) continue;
            if (!hasConnections) {
                hasConnections = true;
            }
            if (!WGMultiPartHandler.tileIsEssentiaTube(te)) {
                overrideCenter = true;
            }
            double d = fd == ForgeDirection.WEST ? 0.0 : (xMin = fd == ForgeDirection.EAST ? 0.5625 : 0.4375);
            double d2 = fd == ForgeDirection.DOWN ? 0.0 : (yMin = fd == ForgeDirection.UP ? 0.5625 : 0.4375);
            double d3 = fd == ForgeDirection.NORTH ? 0.0 : (zMin = fd == ForgeDirection.SOUTH ? 0.5625 : 0.4375);
            double d4 = fd == ForgeDirection.WEST ? 0.4375 : (xMax = fd == ForgeDirection.EAST ? 1.0 : 0.5625);
            double d5 = fd == ForgeDirection.DOWN ? 0.4375 : (yMax = fd == ForgeDirection.UP ? 1.0 : 0.5625);
            double zMax = fd == ForgeDirection.NORTH ? 0.4375 : (fd == ForgeDirection.SOUTH ? 1.0 : 0.5625);
            ClientUtilities.addBoxToBlockrender(xMin, yMin, zMin, xMax, yMax, zMax, icon, this.x(), this.y(), this.z());
            if (!(te instanceof IEssentiaTransport) || !((IEssentiaTransport)te).renderExtendedTube()) continue;
            double d6 = fd == ForgeDirection.WEST ? 0.0 : (xMin = fd == ForgeDirection.EAST ? 0.625 : 0.4375);
            double d7 = fd == ForgeDirection.DOWN ? 0.0 : (yMin = fd == ForgeDirection.UP ? 0.625 : 0.4375);
            double d8 = fd == ForgeDirection.NORTH ? 0.0 : (zMin = fd == ForgeDirection.SOUTH ? 0.625 : 0.4375);
            double d9 = fd == ForgeDirection.WEST ? 0.375 : (xMax = fd == ForgeDirection.EAST ? 1.0 : 0.5625);
            double d10 = fd == ForgeDirection.DOWN ? 0.375 : (yMax = fd == ForgeDirection.UP ? 1.0 : 0.5625);
            zMax = fd == ForgeDirection.NORTH ? 0.375 : (fd == ForgeDirection.SOUTH ? 1.0 : 0.5625);
            ClientUtilities.addBoxToBlockrender(Vec3.func_72443_a((double)((double)fd.offsetX * 0.25), (double)((double)fd.offsetY * 0.375), (double)((double)fd.offsetZ * 0.375)), xMin, yMin, zMin, xMax, yMax, zMax, icon, this.x(), this.y(), this.z());
        }
        if (overrideCenter) {
            hasConnections = false;
        }
        if (this.meta == 0 || this.meta == 6) {
            if (hasConnections) {
                ClientUtilities.addBoxToBlockrender(0.40625, 0.40625, 0.40625, 0.59375, 0.59375, 0.59375, b.icon[2], this.x(), this.y(), this.z());
            } else {
                ClientUtilities.addBoxToBlockrender(0.375, 0.375, 0.375, 0.625, 0.625, 0.625, b.icon[1], this.x(), this.y(), this.z());
            }
        }
        if (this.meta == 5) {
            if (hasConnections) {
                ClientUtilities.addBoxToBlockrender(0.40625, 0.40625, 0.40625, 0.59375, 0.59375, 0.59375, b.icon[6], this.x(), this.y(), this.z());
            } else {
                ClientUtilities.addBoxToBlockrender(0.375, 0.375, 0.375, 0.625, 0.625, 0.625, b.icon[1], this.x(), this.y(), this.z());
            }
        }
        return true;
    }

    public void renderDynamic(Vector3 pos, float partialRenderTick, int pass) {
        if (this.meta == 6 && ThaumcraftApiHelper.getConnectableTile((World)this.world(), (int)this.x(), (int)this.y(), (int)this.z(), (ForgeDirection)this.facing.getOpposite()) != null) {
            ClientUtilities.bindTexture("thaumcraft:textures/models/valve.png");
            GL11.glPushMatrix();
            GL11.glTranslated((double)(pos.x + 0.5), (double)(pos.y + 0.5), (double)(pos.z + 0.5));
            if (this.facing.offsetY == 0) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                GL11.glRotatef((float)90.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)90.0f, (float)this.facing.offsetY, (float)0.0f, (float)0.0f);
            }
            GL11.glRotatef((float)90.0f, (float)this.facing.offsetX, (float)this.facing.offsetY, (float)this.facing.offsetZ);
            GL11.glPushMatrix();
            GL11.glColor3f((float)0.45f, (float)0.5f, (float)1.0f);
            GL11.glScaled((double)1.1, (double)0.5, (double)1.1);
            GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
            MultipartEssentiaTube_Valve.valveModel.render();
            GL11.glTranslated((double)0.0, (double)-0.25, (double)0.0);
            MultipartEssentiaTube_Valve.valveModel.render();
            GL11.glTranslated((double)0.0, (double)-0.25, (double)0.0);
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
        if (this.essentiaType != null) {
            tag.func_74778_a("type", this.essentiaType.getTag());
        }
        tag.func_74768_a("amount", this.essentiaAmount);
        byte[] sides = new byte[6];
        for (int a = 0; a < 6; ++a) {
            sides[a] = (byte)(this.openSides[a] ? 1 : 0);
        }
        tag.func_74768_a("side", this.facing.ordinal());
        tag.func_74773_a("open", sides);
    }

    public void load(NBTTagCompound tag) {
        super.load(tag);
        this.essentiaType = Aspect.getAspect((String)tag.func_74779_i("type"));
        this.essentiaAmount = tag.func_74762_e("amount");
        this.facing = ForgeDirection.getOrientation((int)tag.func_74762_e("side"));
        byte[] sides = tag.func_74770_j("open");
        if (sides != null && sides.length == 6) {
            for (int a = 0; a < 6; ++a) {
                this.openSides[a] = sides[a] == 1;
            }
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
        return "witchingGadgets:essentia_tube";
    }

    public int onWandRightClick(World world, ItemStack wand, EntityPlayer player, MovingObjectPosition mop) {
        Vec3 localHit = mop.field_72307_f.func_72441_c((double)(-this.x()), (double)(-this.y()), (double)(-this.z()));
        for (IndexedCuboid6 ibox : this.getSubParts()) {
            if (!(ibox.min.x <= localHit.field_72450_a) || !(ibox.max.x >= localHit.field_72450_a) || !(ibox.min.y <= localHit.field_72448_b) || !(ibox.max.y >= localHit.field_72448_b) || !(ibox.min.z <= localHit.field_72449_c) || !(ibox.max.z >= localHit.field_72449_c)) continue;
            if (ibox.data instanceof ForgeDirection) {
                ForgeDirection fd = (ForgeDirection)ibox.data;
                player.field_70170_p.func_72980_b((double)this.x() + 0.5, (double)this.y() + 0.5, (double)this.z() + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
                player.func_71038_i();
                this.openSides[fd.ordinal()] = !this.openSides[fd.ordinal()];
                world.func_147471_g(this.x(), this.y(), this.z());
                TileEntity tile = this.world().func_147438_o(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                if (tile != null) {
                    if (tile instanceof TileTube) {
                        ((TileTube)tile).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                        world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
                        tile.func_70296_d();
                    }
                    if (tile instanceof TileMultipart) {
                        for (TMultiPart part : ((TileMultipart)tile).jPartList()) {
                            if (!(part instanceof MultipartEssentiaTube)) continue;
                            ((MultipartEssentiaTube)part).openSides[fd.getOpposite().ordinal()] = this.openSides[fd.ordinal()];
                            world.func_147471_g(this.x() + fd.offsetX, this.y() + fd.offsetY, this.z() + fd.offsetZ);
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
            if (otherPart instanceof JNormalOcclusion) {
                for (Cuboid6 box : ((JNormalOcclusion)otherPart).getOcclusionBoxes()) {
                    if (!box.intersects((Cuboid6)pipe)) continue;
                    wall = true;
                }
            }
            if (!(otherPart instanceof JPartialOcclusion)) continue;
            for (Cuboid6 box : ((JPartialOcclusion)otherPart).getPartialOcclusionBoxes()) {
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
        this.suctionType = aspect;
        this.suction = amount;
    }

    public Aspect getSuctionType(ForgeDirection fd) {
        return this.suctionType;
    }

    public int getSuctionAmount(ForgeDirection fd) {
        return this.suction;
    }

    public Aspect getEssentiaType(ForgeDirection fd) {
        return this.essentiaType;
    }

    public int getEssentiaAmount(ForgeDirection fd) {
        return this.essentiaAmount;
    }

    public int takeEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        if (this.canOutputTo(fd) && this.essentiaType == aspect && this.essentiaAmount > 0 && amount > 0) {
            --this.essentiaAmount;
            if (this.essentiaAmount <= 0) {
                this.essentiaType = null;
            }
            this.markDirty();
            return 1;
        }
        return 0;
    }

    public int addEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        if (this.canInputFrom(fd) && this.essentiaAmount == 0 && amount > 0) {
            this.essentiaType = aspect;
            ++this.essentiaAmount;
            this.markDirty();
            return 1;
        }
        return 0;
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

