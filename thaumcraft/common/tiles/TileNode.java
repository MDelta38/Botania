/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.research.ScanResult;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.items.ItemCompassStone;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class TileNode
extends TileThaumcraft
implements INode,
IWandable {
    long lastActive = 0L;
    AspectList aspects = new AspectList();
    AspectList aspectsBase = new AspectList();
    public static HashMap<String, ArrayList<Integer>> locations = new HashMap();
    private NodeType nodeType = NodeType.NORMAL;
    private NodeModifier nodeModifier = null;
    int count = 0;
    int regeneration = -1;
    int wait = 0;
    String id = null;
    byte nodeLock = 0;
    boolean catchUp = false;
    public Entity drainEntity = null;
    public MovingObjectPosition drainCollision = null;
    public int drainColor = 0xFFFFFF;
    public Color targetColor = new Color(0xFFFFFF);
    public Color color = new Color(0xFFFFFF);

    @Override
    public String getId() {
        if (this.id == null) {
            this.id = this.generateId();
        }
        return this.id;
    }

    public String generateId() {
        this.id = this.field_145850_b.field_73011_w.field_76574_g + ":" + this.field_145851_c + ":" + this.field_145848_d + ":" + this.field_145849_e;
        if (this.field_145850_b != null && locations != null) {
            ArrayList<Integer> t = new ArrayList<Integer>();
            t.add(this.field_145850_b.field_73011_w.field_76574_g);
            t.add(this.field_145851_c);
            t.add(this.field_145848_d);
            t.add(this.field_145849_e);
            locations.put(this.id, t);
        }
        return this.id;
    }

    public void onChunkUnload() {
        if (locations != null) {
            locations.remove(this.id);
        }
        super.onChunkUnload();
    }

    public void func_145829_t() {
        super.func_145829_t();
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.id == null) {
            this.generateId();
        }
        boolean change = false;
        change = this.handleHungryNodeFirst(change);
        ++this.count;
        this.checkLock();
        if (!this.field_145850_b.field_72995_K) {
            change = this.handleDischarge(change);
            change = this.handleRecharge(change);
            change = this.handleTaintNode(change);
            change = this.handleNodeStability(change);
            change = this.handleDarkNode(change);
            change = this.handlePureNode(change);
            if (change = this.handleHungryNodeSecond(change)) {
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        } else if (this.getNodeType() == NodeType.DARK && this.count % 50 == 0) {
            ItemCompassStone.sinisterNodes.put(new WorldCoordinates(this), System.currentTimeMillis());
        }
    }

    public void nodeChange() {
        this.regeneration = -1;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public boolean canUpdate() {
        return true;
    }

    public double getDistanceTo(double par1, double par3, double par5) {
        double var7 = (double)this.field_145851_c + 0.5 - par1;
        double var9 = (double)this.field_145848_d + 0.5 - par3;
        double var11 = (double)this.field_145849_e + 0.5 - par5;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        return -1;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        player.func_71008_a(wandstack, Integer.MAX_VALUE);
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        wand.setObjectInUse(wandstack, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return wandstack;
    }

    @Override
    public AspectList getAspects() {
        return this.aspects;
    }

    @Override
    public AspectList getAspectsBase() {
        return this.aspectsBase;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.aspects = aspects;
        this.aspectsBase = aspects.copy();
    }

    @Override
    public int addToContainer(Aspect aspect, int amount) {
        int left = amount + this.aspects.getAmount(aspect) - this.aspectsBase.getAmount(aspect);
        left = left > 0 ? left : 0;
        this.aspects.add(aspect, amount - left);
        return left;
    }

    @Override
    public boolean takeFromContainer(Aspect aspect, int amount) {
        return this.aspects.reduce(aspect, amount);
    }

    public Aspect takeRandomPrimalFromSource() {
        Aspect[] primals = this.aspects.getPrimalAspects();
        Aspect asp = primals[this.field_145850_b.field_73012_v.nextInt(primals.length)];
        if (asp != null && this.aspects.reduce(asp, 1)) {
            return asp;
        }
        return null;
    }

    public Aspect chooseRandomFilteredFromSource(AspectList filter, boolean preserve) {
        int min = preserve ? 1 : 0;
        ArrayList<Aspect> validaspects = new ArrayList<Aspect>();
        for (Aspect prim : this.aspects.getAspects()) {
            if (filter.getAmount(prim) <= 0 || this.aspects.getAmount(prim) <= min) continue;
            validaspects.add(prim);
        }
        if (validaspects.size() == 0) {
            return null;
        }
        Aspect asp = (Aspect)validaspects.get(this.field_145850_b.field_73012_v.nextInt(validaspects.size()));
        if (asp != null && this.aspects.getAmount(asp) > min) {
            return asp;
        }
        return null;
    }

    @Override
    public NodeType getNodeType() {
        return this.nodeType;
    }

    @Override
    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public void setNodeModifier(NodeModifier nodeModifier) {
        this.nodeModifier = nodeModifier;
    }

    @Override
    public NodeModifier getNodeModifier() {
        return this.nodeModifier;
    }

    @Override
    public int getNodeVisBase(Aspect aspect) {
        return this.aspectsBase.getAmount(aspect);
    }

    @Override
    public void setNodeVisBase(Aspect aspect, short nodeVisBase) {
        if (this.aspectsBase.getAmount(aspect) < nodeVisBase) {
            this.aspectsBase.merge(aspect, nodeVisBase);
        } else {
            this.aspectsBase.reduce(aspect, this.aspectsBase.getAmount(aspect) - nodeVisBase);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.lastActive = nbttagcompound.func_74763_f("lastActive");
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("AspectsBase", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        Short oldBase = nbttagcompound.func_74765_d("nodeVisBase");
        this.aspectsBase = new AspectList();
        if (oldBase > 0 && al.size() == 0) {
            for (Aspect a : this.aspects.getAspects()) {
                this.aspectsBase.merge(a, oldBase.shortValue());
            }
        } else {
            this.aspectsBase = al.copy();
        }
        int regen = 600;
        if (this.getNodeModifier() != null) {
            switch (this.getNodeModifier()) {
                case BRIGHT: {
                    regen = 400;
                    break;
                }
                case PALE: {
                    regen = 900;
                    break;
                }
                case FADING: {
                    regen = 0;
                }
            }
        }
        long ct = System.currentTimeMillis();
        int inc = regen * 75;
        if (regen > 0 && this.lastActive > 0L && ct > this.lastActive + (long)inc) {
            this.catchUp = true;
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74772_a("lastActive", this.lastActive);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("AspectsBase", (NBTBase)tlist);
        for (Aspect aspect : this.aspectsBase.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspectsBase.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.id = nbttagcompound.func_74779_i("nodeId");
        if (this.field_145850_b != null && locations != null) {
            ArrayList<Integer> t = new ArrayList<Integer>();
            t.add(this.field_145850_b.field_73011_w.field_76574_g);
            t.add(this.field_145851_c);
            t.add(this.field_145848_d);
            t.add(this.field_145849_e);
            locations.put(this.id, t);
        }
        this.setNodeType(NodeType.values()[nbttagcompound.func_74771_c("type")]);
        byte mod = nbttagcompound.func_74771_c("modifier");
        if (mod >= 0) {
            this.setNodeModifier(NodeModifier.values()[mod]);
        } else {
            this.setNodeModifier(null);
        }
        this.aspects.readFromNBT(nbttagcompound);
        String de = nbttagcompound.func_74779_i("drainer");
        if (de != null && de.length() > 0 && this.func_145831_w() != null) {
            this.drainEntity = this.func_145831_w().func_72924_a(de);
            if (this.drainEntity != null) {
                this.drainCollision = new MovingObjectPosition(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, Vec3.func_72443_a((double)this.drainEntity.field_70165_t, (double)this.drainEntity.field_70163_u, (double)this.drainEntity.field_70161_v));
            }
        }
        this.drainColor = nbttagcompound.func_74762_e("draincolor");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.id == null) {
            this.id = this.generateId();
        }
        if (this.field_145850_b != null && locations != null) {
            ArrayList<Integer> t = new ArrayList<Integer>();
            t.add(this.field_145850_b.field_73011_w.field_76574_g);
            t.add(this.field_145851_c);
            t.add(this.field_145848_d);
            t.add(this.field_145849_e);
            locations.put(this.id, t);
        }
        nbttagcompound.func_74778_a("nodeId", this.id);
        nbttagcompound.func_74774_a("type", (byte)this.getNodeType().ordinal());
        nbttagcompound.func_74774_a("modifier", this.getNodeModifier() == null ? (byte)-1 : (byte)this.getNodeModifier().ordinal());
        this.aspects.writeToNBT(nbttagcompound);
        if (this.drainEntity != null && this.drainEntity instanceof EntityPlayer) {
            nbttagcompound.func_74778_a("drainer", this.drainEntity.func_70005_c_());
        }
        nbttagcompound.func_74768_a("draincolor", this.drainColor);
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
        boolean mfu = false;
        ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
        MovingObjectPosition movingobjectposition = EntityUtils.getMovingObjectPositionFromPlayer(this.field_145850_b, player, true);
        if (movingobjectposition == null || movingobjectposition.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            player.func_71034_by();
        } else {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (i != this.field_145851_c || j != this.field_145848_d || k != this.field_145849_e) {
                player.func_71034_by();
            }
        }
        if (count % 5 == 0) {
            int tap = 1;
            if (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODETAPPER1")) {
                ++tap;
            }
            if (ResearchManager.isResearchComplete(player.func_70005_c_(), "NODETAPPER2")) {
                ++tap;
            }
            boolean preserve = !player.func_70093_af() && ResearchManager.isResearchComplete(player.func_70005_c_(), "NODEPRESERVE") && !wand.getRod(wandstack).getTag().equals("wood") && !wand.getCap(wandstack).getTag().equals("iron");
            boolean success = false;
            Aspect aspect = null;
            aspect = this.chooseRandomFilteredFromSource(wand.getAspectsWithRoom(wandstack), preserve);
            if (aspect != null) {
                int rem;
                int amt = this.getAspects().getAmount(aspect);
                if (tap > amt) {
                    tap = amt;
                }
                if (preserve && tap == amt) {
                    --tap;
                }
                if (tap > 0 && (rem = wand.addVis(wandstack, aspect, tap, !this.field_145850_b.field_72995_K)) < tap) {
                    this.drainColor = aspect.getColor();
                    if (!this.field_145850_b.field_72995_K) {
                        this.takeFromContainer(aspect, tap - rem);
                        mfu = true;
                    }
                    success = true;
                }
            }
            if (success) {
                this.drainEntity = player;
                this.drainCollision = movingobjectposition;
                this.targetColor = new Color(this.drainColor);
            } else {
                this.drainEntity = null;
                this.drainCollision = null;
            }
            if (mfu) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        }
        if (player.field_70170_p.field_72995_K) {
            int r = this.targetColor.getRed();
            int g = this.targetColor.getGreen();
            int b = this.targetColor.getBlue();
            int r2 = this.color.getRed() * 4;
            int g2 = this.color.getGreen() * 4;
            int b2 = this.color.getBlue() * 4;
            this.color = new Color((r + r2) / 5, (g + g2) / 5, (b + b2) / 5);
        }
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
        this.drainEntity = null;
        this.drainCollision = null;
    }

    public boolean func_145842_c(int i, int j) {
        return super.func_145842_c(i, j);
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

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    private boolean handleHungryNodeFirst(boolean change) {
        if (this.getNodeType() == NodeType.HUNGRY) {
            List ents;
            if (this.field_145850_b.field_72995_K) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(1); ++a) {
                    Vec3 v2;
                    Vec3 v1;
                    MovingObjectPosition mop;
                    int tz;
                    int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                    int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                    if (ty > this.field_145850_b.func_72976_f(tx, tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16))) {
                        ty = this.field_145850_b.func_72976_f(tx, tz);
                    }
                    if ((mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d + 0.5), (double)((double)this.field_145849_e + 0.5)), v2 = Vec3.func_72443_a((double)((double)tx + 0.5), (double)((double)ty + 0.5), (double)((double)tz + 0.5)), true, false, false)) == null || !(this.func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0)) continue;
                    tx = mop.field_72311_b;
                    ty = mop.field_72312_c;
                    tz = mop.field_72309_d;
                    Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
                    int md = this.field_145850_b.func_72805_g(tx, ty, tz);
                    if (bi.isAir((IBlockAccess)this.field_145850_b, tx, ty, tz)) continue;
                    Thaumcraft.proxy.hungryNodeFX(this.field_145850_b, tx, ty, tz, this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
                }
            }
            if (Config.hardNode && (ents = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(15.0, 15.0, 15.0))) != null && ents.size() > 0) {
                for (Object ent : ents) {
                    double var7;
                    double var5;
                    double var3;
                    double var9;
                    double var11;
                    double d;
                    Entity eo = (Entity)ent;
                    if (eo instanceof EntityPlayer && ((EntityPlayer)eo).field_71075_bZ.field_75102_a) continue;
                    if (eo.func_70089_S() && !eo.func_85032_ar() && (d = this.getDistanceTo(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v)) < 2.0) {
                        ScanResult scan;
                        AspectList al;
                        eo.func_70097_a(DamageSource.field_76380_i, 1.0f);
                        if (!eo.func_70089_S() && !this.field_145850_b.field_72995_K && (al = ScanManager.getScanAspects(scan = new ScanResult(2, 0, 0, eo, ""), this.field_145850_b)) != null && al.size() > 0 && (al = ResearchManager.reduceToPrimals(al.copy())) != null && al.size() > 0) {
                            Aspect a = al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())];
                            if (this.getAspects().getAmount(a) < this.getNodeVisBase(a)) {
                                this.addToContainer(a, 1);
                                change = true;
                            } else if (this.field_145850_b.field_73012_v.nextInt(1 + this.getNodeVisBase(a) * 2) < al.getAmount(a)) {
                                this.aspectsBase.add(a, 1);
                                change = true;
                            }
                        }
                    }
                    if (!((var11 = 1.0 - (var9 = Math.sqrt((var3 = ((double)this.field_145851_c + 0.5 - eo.field_70165_t) / 15.0) * var3 + (var5 = ((double)this.field_145848_d + 0.5 - eo.field_70163_u) / 15.0) * var5 + (var7 = ((double)this.field_145849_e + 0.5 - eo.field_70161_v) / 15.0) * var7))) > 0.0)) continue;
                    var11 *= var11;
                    eo.field_70159_w += var3 / var9 * var11 * 0.15;
                    eo.field_70181_x += var5 / var9 * var11 * 0.25;
                    eo.field_70179_y += var7 / var9 * var11 * 0.15;
                }
            }
        }
        return change;
    }

    private boolean handleDischarge(boolean change) {
        TileEntity te;
        int inc;
        boolean shiny;
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) != ConfigBlocks.blockAiry || this.getLock() == 1) {
            return change;
        }
        if (this.getNodeModifier() == NodeModifier.FADING) {
            return change;
        }
        boolean bl = shiny = this.getNodeType() == NodeType.HUNGRY || this.getNodeModifier() == NodeModifier.BRIGHT;
        int n = this.getNodeModifier() == null ? 2 : (shiny ? 1 : (inc = this.getNodeModifier() == NodeModifier.PALE ? 3 : 2));
        if (this.count % inc != 0) {
            return change;
        }
        int x = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
        int y = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
        int z = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
        if (this.getNodeModifier() == NodeModifier.PALE && this.field_145850_b.field_73012_v.nextBoolean()) {
            return change;
        }
        if ((x != 0 || y != 0 || z != 0) && (te = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z)) != null && te instanceof INode && this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockAiry) {
            int thisavg;
            if (te instanceof TileNode && ((TileNode)te).getLock() > 0) {
                return change;
            }
            INode nd = (INode)te;
            int ndavg = (nd.getAspects().visSize() + nd.getAspectsBase().visSize()) / 2;
            if (ndavg < (thisavg = (this.getAspects().visSize() + this.getAspectsBase().visSize()) / 2) && nd.getAspects().size() > 0) {
                Aspect a = nd.getAspects().getAspects()[this.field_145850_b.field_73012_v.nextInt(nd.getAspects().size())];
                boolean u = false;
                if (this.getAspects().getAmount(a) < this.getNodeVisBase(a) && nd.takeFromContainer(a, 1)) {
                    this.addToContainer(a, 1);
                    u = true;
                } else if (nd.takeFromContainer(a, 1)) {
                    if (this.field_145850_b.field_73012_v.nextInt(1 + (int)((double)this.getNodeVisBase(a) / (shiny ? 1.5 : 1.0))) == 0) {
                        this.aspectsBase.add(a, 1);
                        if (this.getNodeModifier() == NodeModifier.PALE && this.field_145850_b.field_73012_v.nextInt(100) == 0) {
                            this.setNodeModifier(null);
                            this.regeneration = -1;
                        }
                        if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                            nd.setNodeVisBase(a, (short)(nd.getNodeVisBase(a) - 1));
                        }
                    }
                    u = true;
                }
                if (u) {
                    ((TileNode)te).wait = ((TileNode)te).regeneration / 2;
                    this.field_145850_b.func_147471_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                    te.func_70296_d();
                    change = true;
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)(this.field_145851_c + x) + 0.5f, (float)(this.field_145848_d + y) + 0.5f, (float)(this.field_145849_e + z) + 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                }
            }
        }
        return change;
    }

    private boolean handleRecharge(boolean change) {
        if (this.regeneration < 0) {
            this.regeneration = 600;
            if (this.getNodeModifier() != null) {
                switch (this.getNodeModifier()) {
                    case BRIGHT: {
                        this.regeneration = 400;
                        break;
                    }
                    case PALE: {
                        this.regeneration = 900;
                        break;
                    }
                    case FADING: {
                        this.regeneration = 0;
                    }
                }
            }
            if (this.getLock() == 1) {
                this.regeneration *= 2;
            }
            if (this.getLock() == 2) {
                this.regeneration *= 20;
            }
        }
        if (this.catchUp) {
            int amt;
            this.catchUp = false;
            long ct = System.currentTimeMillis();
            int inc = this.regeneration * 75;
            int n = amt = inc > 0 ? (int)((ct - this.lastActive) / (long)inc) : 0;
            if (amt > 0) {
                for (int a = 0; a < Math.min(amt, this.aspectsBase.visSize()); ++a) {
                    AspectList al = new AspectList();
                    for (Aspect aspect : this.getAspects().getAspects()) {
                        if (this.getAspects().getAmount(aspect) >= this.getNodeVisBase(aspect)) continue;
                        al.add(aspect, 1);
                    }
                    if (al.size() <= 0) continue;
                    this.addToContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())], 1);
                }
            }
        }
        if (this.count % 1200 == 0) {
            for (Aspect aspect : this.getAspects().getAspects()) {
                if (this.getAspects().getAmount(aspect) > 0) continue;
                this.setNodeVisBase(aspect, (short)(this.getNodeVisBase(aspect) - 1));
                if (this.field_145850_b.field_73012_v.nextInt(20) == 0 || this.getNodeVisBase(aspect) <= 0) {
                    this.getAspects().remove(aspect);
                    if (this.field_145850_b.field_73012_v.nextInt(5) == 0) {
                        if (this.getNodeModifier() == NodeModifier.BRIGHT) {
                            this.setNodeModifier(null);
                        } else if (this.getNodeModifier() == null) {
                            this.setNodeModifier(NodeModifier.PALE);
                        }
                        if (this.getNodeModifier() == NodeModifier.PALE && this.field_145850_b.field_73012_v.nextInt(5) == 0) {
                            this.setNodeModifier(NodeModifier.FADING);
                        }
                    }
                    this.nodeChange();
                    break;
                }
                this.nodeChange();
            }
            if (this.getAspects().size() <= 0) {
                this.func_145843_s();
                if (this.func_145838_q() == ConfigBlocks.blockAiry) {
                    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                } else if (this.func_145838_q() == ConfigBlocks.blockMagicalLog) {
                    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) - 1, 3);
                }
            }
        }
        if (this.wait > 0) {
            --this.wait;
        }
        if (this.regeneration > 0 && this.wait == 0 && this.count % this.regeneration == 0) {
            this.lastActive = System.currentTimeMillis();
            AspectList al = new AspectList();
            for (Aspect aspect : this.getAspects().getAspects()) {
                if (this.getAspects().getAmount(aspect) >= this.getNodeVisBase(aspect)) continue;
                al.add(aspect, 1);
            }
            if (al.size() > 0) {
                this.addToContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.size())], 1);
                change = true;
            }
        }
        return change;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean handleTaintNode(boolean change) {
        if (this.getNodeType() == NodeType.TAINTED && this.count % 50 == 0) {
            int x = 0;
            int z = 0;
            int y = 0;
            x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
            if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
                Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeTaint);
            }
            if (!Config.hardNode || !this.field_145850_b.field_73012_v.nextBoolean()) return change;
            x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
            z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
            y = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
            if (!BlockTaintFibres.spreadFibres(this.field_145850_b, x, y, z)) return change;
        }
        if (this.getNodeType() == NodeType.PURE || this.getNodeType() == NodeType.TAINTED || this.count % 100 != 0) return change;
        BiomeGenBase bg = this.field_145850_b.func_72807_a(this.field_145851_c, this.field_145849_e);
        if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeTaint.field_76756_M || this.field_145850_b.field_73012_v.nextInt(500) != 0) return change;
        this.setNodeType(NodeType.TAINTED);
        this.nodeChange();
        return change;
    }

    private boolean handleNodeStability(boolean change) {
        if (this.count % 100 == 0) {
            if (this.getNodeType() == NodeType.UNSTABLE && this.field_145850_b.field_73012_v.nextBoolean()) {
                if (this.getLock() == 0) {
                    Aspect aspect = null;
                    aspect = this.takeRandomPrimalFromSource();
                    if (aspect != null) {
                        EntityAspectOrb orb = new EntityAspectOrb(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, aspect, 1);
                        this.field_145850_b.func_72838_d((Entity)orb);
                        change = true;
                    }
                } else if (this.field_145850_b.field_73012_v.nextInt(10000 / this.getLock()) == 42) {
                    this.setNodeType(NodeType.NORMAL);
                    change = true;
                }
            }
            if (this.getNodeModifier() == NodeModifier.FADING && this.getLock() > 0 && this.field_145850_b.field_73012_v.nextInt(12500 / this.getLock()) == 69) {
                this.setNodeModifier(NodeModifier.PALE);
                change = true;
            }
        }
        return change;
    }

    private boolean handlePureNode(boolean change) {
        int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.field_145850_b.field_73011_w.field_76574_g);
        if (this.field_145850_b.field_73011_w.field_76574_g != -1 && this.field_145850_b.field_73011_w.field_76574_g != 1 && dimbl != 0 && dimbl != 2 && this.getNodeType() == NodeType.PURE && this.count % 50 == 0) {
            int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(8) - this.field_145850_b.field_73012_v.nextInt(8);
            BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
            int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(bg.field_76756_M);
            if (biobl != 0 && biobl != 2 && bg.field_76756_M != ThaumcraftWorldGenerator.biomeMagicalForest.field_76756_M) {
                if (bg.field_76756_M == ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
                    Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
                } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockMagicalLog) {
                    Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeMagicalForest);
                }
            }
        }
        return change;
    }

    private boolean handleDarkNode(boolean change) {
        int dimbl = ThaumcraftWorldGenerator.getDimBlacklist(this.field_145850_b.field_73011_w.field_76574_g);
        int biobl = ThaumcraftWorldGenerator.getBiomeBlacklist(this.field_145850_b.func_72807_a((int)this.field_145851_c, (int)this.field_145849_e).field_76756_M);
        if (biobl != 0 && biobl != 2 && this.field_145850_b.field_73011_w.field_76574_g != -1 && this.field_145850_b.field_73011_w.field_76574_g != 1 && dimbl != 0 && dimbl != 2 && this.getNodeType() == NodeType.DARK && this.count % 50 == 0) {
            int j;
            EntityGiantBrainyZombie entity;
            int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(12) - this.field_145850_b.field_73012_v.nextInt(12);
            int z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(12) - this.field_145850_b.field_73012_v.nextInt(12);
            BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
            if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeEerie.field_76756_M) {
                Utils.setBiomeAt(this.field_145850_b, x, z, ThaumcraftWorldGenerator.biomeEerie);
            }
            if (Config.hardNode && this.field_145850_b.field_73012_v.nextBoolean() && this.field_145850_b.func_72977_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, 24.0) != null && (entity = new EntityGiantBrainyZombie(this.field_145850_b)) != null && (j = this.field_145850_b.func_72872_a(((Object)((Object)entity)).getClass(), AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 6.0, 10.0)).size()) <= 3) {
                double d0 = (double)this.field_145851_c + (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 5.0;
                double d3 = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(3) - 1;
                double d4 = (double)this.field_145849_e + (this.field_145850_b.field_73012_v.nextDouble() - this.field_145850_b.field_73012_v.nextDouble()) * 5.0;
                EntityGiantBrainyZombie entityliving = entity instanceof EntityLiving ? entity : null;
                entity.func_70012_b(d0, d3, d4, this.field_145850_b.field_73012_v.nextFloat() * 360.0f, 0.0f);
                if (entityliving == null || entityliving.func_70601_bi()) {
                    this.field_145850_b.func_72838_d((Entity)entityliving);
                    this.field_145850_b.func_72926_e(2004, this.field_145851_c, this.field_145848_d, this.field_145849_e, 0);
                    if (entityliving != null) {
                        entityliving.func_70656_aK();
                    }
                }
            }
        }
        return change;
    }

    private boolean handleHungryNodeSecond(boolean change) {
        if (this.getNodeType() == NodeType.HUNGRY && this.count % 50 == 0) {
            Vec3 v2;
            Vec3 v1;
            MovingObjectPosition mop;
            int tz;
            int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            if (ty > this.field_145850_b.func_72976_f(tx, tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16))) {
                ty = this.field_145850_b.func_72976_f(tx, tz);
            }
            if ((mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d + 0.5), (double)((double)this.field_145849_e + 0.5)), v2 = Vec3.func_72443_a((double)((double)tx + 0.5), (double)((double)ty + 0.5), (double)((double)tz + 0.5)), true, false, false)) != null && this.func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0) {
                float h;
                tx = mop.field_72311_b;
                ty = mop.field_72312_c;
                tz = mop.field_72309_d;
                Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
                int md = this.field_145850_b.func_72805_g(tx, ty, tz);
                if (!bi.isAir((IBlockAccess)this.field_145850_b, tx, ty, tz) && (h = bi.func_149712_f(this.field_145850_b, tx, ty, tz)) >= 0.0f && h < 5.0f) {
                    this.field_145850_b.func_147480_a(tx, ty, tz, true);
                }
            }
        }
        return change;
    }

    public byte getLock() {
        return this.nodeLock;
    }

    public void checkLock() {
        if ((this.count <= 1 || this.count % 50 == 0) && this.field_145848_d > 0 && this.func_145838_q() == ConfigBlocks.blockAiry) {
            byte oldLock = this.nodeLock;
            this.nodeLock = 0;
            if (!this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockStoneDevice) {
                if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 9) {
                    this.nodeLock = 1;
                } else if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 10) {
                    this.nodeLock = (byte)2;
                }
            }
            if (oldLock != this.nodeLock) {
                this.regeneration = -1;
            }
        }
    }
}

