/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IClientManaHandler;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.api.mana.IPingable;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;

public class EntityManaBurst
extends EntityThrowable
implements IManaBurst {
    private static final String TAG_TICKS_EXISTED = "ticksExisted";
    private static final String TAG_COLOR = "color";
    private static final String TAG_MANA = "mana";
    private static final String TAG_STARTING_MANA = "startingMana";
    private static final String TAG_MIN_MANA_LOSS = "minManaLoss";
    private static final String TAG_TICK_MANA_LOSS = "manaLossTick";
    private static final String TAG_SPREADER_X = "spreaderX";
    private static final String TAG_SPREADER_Y = "spreaderY";
    private static final String TAG_SPREADER_Z = "spreaderZ";
    private static final String TAG_GRAVITY = "gravity";
    private static final String TAG_LENS_STACK = "lensStack";
    private static final String TAG_LAST_MOTION_X = "lastMotionX";
    private static final String TAG_LAST_MOTION_Y = "lastMotionY";
    private static final String TAG_LAST_MOTION_Z = "lastMotionZ";
    private static final String TAG_HAS_SHOOTER = "hasShooter";
    private static final String TAG_SHOOTER_UUID_MOST = "shooterUUIDMost";
    private static final String TAG_SHOOTER_UUID_LEAST = "shooterUUIDLeast";
    boolean fake = false;
    final int dataWatcherEntries = 10;
    final int dataWatcherStart = 22;
    List<String> alreadyCollidedAt = new ArrayList<String>();
    boolean fullManaLastTick = true;
    UUID shooterIdentity = null;
    int _ticksExisted = 0;
    boolean scanBeam = false;
    public List<PositionProperties> propsList = new ArrayList<PositionProperties>();
    float accumulatedManaLoss = 0.0f;
    private int ticksInAir;
    public TileEntity collidedTile = null;
    public boolean noParticles = false;
    final int coordsStart = 28;
    final int motionStart = 32;

    public EntityManaBurst(World world) {
        super(world);
        this.func_70105_a(0.0f, 0.0f);
        for (int i = 0; i < 10; ++i) {
            int j = 22 + i;
            if (i == 4 || i == 5) {
                this.field_70180_af.func_75682_a(j, (Object)Float.valueOf(0.0f));
            } else if (i == 9) {
                this.field_70180_af.func_75682_a(j, (Object)new ItemStack(Blocks.field_150348_b, 0, 0));
            } else {
                this.field_70180_af.func_75682_a(j, (Object)0);
            }
            this.field_70180_af.func_82708_h(j);
        }
    }

    public EntityManaBurst(IManaSpreader spreader, boolean fake) {
        this(((TileEntity)spreader).func_145831_w());
        TileEntity tile = (TileEntity)spreader;
        this.fake = fake;
        this.setBurstSourceCoords(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
        this.func_70012_b((double)tile.field_145851_c + 0.5, (double)tile.field_145848_d + 0.5, (double)tile.field_145849_e + 0.5, 0.0f, 0.0f);
        this.field_70177_z = -(spreader.getRotationX() + 90.0f);
        this.field_70125_A = spreader.getRotationY();
        float f = 0.4f;
        double mx = (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f) / 2.0;
        double mz = (double)(-(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f)) / 2.0;
        double my = (double)(MathHelper.func_76126_a((float)((this.field_70125_A + this.func_70183_g()) / 180.0f * (float)Math.PI)) * f) / 2.0;
        this.setMotion(mx, my, mz);
    }

    public EntityManaBurst(EntityPlayer player) {
        this(player.field_70170_p);
        this.setBurstSourceCoords(0, -1, 0);
        this.func_70012_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, player.field_70177_z + 180.0f, -player.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b((float)((this.field_70177_z + 180.0f) / 180.0f * (float)Math.PI)) * 0.16f);
        this.field_70163_u -= (double)0.1f;
        this.field_70161_v -= (double)(MathHelper.func_76126_a((float)((this.field_70177_z + 180.0f) / 180.0f * (float)Math.PI)) * 0.16f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        float f = 0.4f;
        double mx = (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f) / 2.0;
        double mz = (double)(-(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f)) / 2.0;
        double my = (double)(MathHelper.func_76126_a((float)((this.field_70125_A + this.func_70183_g()) / 180.0f * (float)Math.PI)) * f) / 2.0;
        this.setMotion(mx, my, mz);
    }

    public void superUpdate() {
        Vec3 vec31;
        Vec3 vec3;
        MovingObjectPosition movingobjectposition;
        this.field_70142_S = this.field_70165_t;
        this.field_70137_T = this.field_70163_u;
        this.field_70136_U = this.field_70161_v;
        this.func_70030_z();
        if (this.field_70191_b > 0) {
            --this.field_70191_b;
        }
        if ((movingobjectposition = this.clip(vec3 = new Vector3(this.field_70165_t, this.field_70163_u, this.field_70161_v).toVec3D(), vec31 = new Vector3(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y).toVec3D())) != null) {
            vec31 = new Vector3(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c).toVec3D();
        }
        if (!this.field_70170_p.field_72995_K) {
            Entity entity = null;
            List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
            double d0 = 0.0;
            EntityLivingBase entitylivingbase = this.func_85052_h();
            for (int j = 0; j < list.size(); ++j) {
                double d1;
                float f;
                AxisAlignedBB axisalignedbb;
                MovingObjectPosition movingobjectposition1;
                Entity entity1 = (Entity)list.get(j);
                if (!entity1.func_70067_L() || entity1 == entitylivingbase && this.ticksInAir < 5 || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 0.3f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                entity = entity1;
                d0 = d1;
            }
            if (entity != null) {
                movingobjectposition = new MovingObjectPosition(entity);
            }
        }
        if (movingobjectposition != null) {
            this.func_70184_a(movingobjectposition);
        }
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        float f1 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
        this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
        this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f1) * 180.0 / Math.PI);
        while (this.field_70125_A - this.field_70127_C < -180.0f) {
            this.field_70127_C -= 360.0f;
        }
        while (this.field_70125_A - this.field_70127_C >= 180.0f) {
            this.field_70127_C += 360.0f;
        }
        while (this.field_70177_z - this.field_70126_B < -180.0f) {
            this.field_70126_B -= 360.0f;
        }
        while (this.field_70177_z - this.field_70126_B >= 180.0f) {
            this.field_70126_B += 360.0f;
        }
        this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2f;
        this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
        float f3 = this.func_70185_h();
        this.field_70181_x -= (double)f3;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    public MovingObjectPosition clip(Vec3 par1Vec3, Vec3 par2Vec3) {
        boolean par3 = false;
        boolean par4 = false;
        if (!(Double.isNaN(par1Vec3.field_72450_a) || Double.isNaN(par1Vec3.field_72448_b) || Double.isNaN(par1Vec3.field_72449_c))) {
            if (!(Double.isNaN(par2Vec3.field_72450_a) || Double.isNaN(par2Vec3.field_72448_b) || Double.isNaN(par2Vec3.field_72449_c))) {
                MovingObjectPosition movingobjectposition;
                int i = MathHelper.func_76128_c((double)par2Vec3.field_72450_a);
                int j = MathHelper.func_76128_c((double)par2Vec3.field_72448_b);
                int k = MathHelper.func_76128_c((double)par2Vec3.field_72449_c);
                int l = MathHelper.func_76128_c((double)par1Vec3.field_72450_a);
                int i1 = MathHelper.func_76128_c((double)par1Vec3.field_72448_b);
                int j1 = MathHelper.func_76128_c((double)par1Vec3.field_72449_c);
                Block block = this.field_70170_p.func_147439_a(l, i1, j1);
                int l1 = this.field_70170_p.func_72805_g(l, i1, j1);
                if (block != null && (!par4 || block == null || block.func_149668_a(this.field_70170_p, l, i1, j1) != null) && block != Blocks.field_150350_a && block.func_149678_a(l1, par3) && (movingobjectposition = block.func_149731_a(this.field_70170_p, l, i1, j1, par1Vec3, par2Vec3)) != null) {
                    return movingobjectposition;
                }
                int k1 = 200;
                while (k1-- >= 0) {
                    MovingObjectPosition movingobjectposition1;
                    int b0;
                    if (Double.isNaN(par1Vec3.field_72450_a) || Double.isNaN(par1Vec3.field_72448_b) || Double.isNaN(par1Vec3.field_72449_c)) {
                        return null;
                    }
                    if (l == i && i1 == j && j1 == k) {
                        return null;
                    }
                    boolean flag2 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (i > l) {
                        d0 = (double)l + 1.0;
                    } else if (i < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag2 = false;
                    }
                    if (j > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (j < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag3 = false;
                    }
                    if (k > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (k < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag4 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = par2Vec3.field_72450_a - par1Vec3.field_72450_a;
                    double d7 = par2Vec3.field_72448_b - par1Vec3.field_72448_b;
                    double d8 = par2Vec3.field_72449_c - par1Vec3.field_72449_c;
                    if (flag2) {
                        d3 = (d0 - par1Vec3.field_72450_a) / d6;
                    }
                    if (flag3) {
                        d4 = (d1 - par1Vec3.field_72448_b) / d7;
                    }
                    if (flag4) {
                        d5 = (d2 - par1Vec3.field_72449_c) / d8;
                    }
                    if (d3 < d4 && d3 < d5) {
                        b0 = i > l ? 4 : 5;
                        par1Vec3.field_72450_a = d0;
                        par1Vec3.field_72448_b += d7 * d3;
                        par1Vec3.field_72449_c += d8 * d3;
                    } else if (d4 < d5) {
                        b0 = j > i1 ? 0 : 1;
                        par1Vec3.field_72450_a += d6 * d4;
                        par1Vec3.field_72448_b = d1;
                        par1Vec3.field_72449_c += d8 * d4;
                    } else {
                        b0 = k > j1 ? 2 : 3;
                        par1Vec3.field_72450_a += d6 * d5;
                        par1Vec3.field_72448_b += d7 * d5;
                        par1Vec3.field_72449_c = d2;
                    }
                    Vec3 vec32 = new Vector3(par1Vec3.field_72450_a, par1Vec3.field_72448_b, par1Vec3.field_72449_c).toVec3D();
                    vec32.field_72450_a = MathHelper.func_76128_c((double)par1Vec3.field_72450_a);
                    l = (int)vec32.field_72450_a;
                    if (b0 == 5) {
                        --l;
                        vec32.field_72450_a += 1.0;
                    }
                    vec32.field_72448_b = MathHelper.func_76128_c((double)par1Vec3.field_72448_b);
                    i1 = (int)vec32.field_72448_b;
                    if (b0 == 1) {
                        --i1;
                        vec32.field_72448_b += 1.0;
                    }
                    vec32.field_72449_c = MathHelper.func_76128_c((double)par1Vec3.field_72449_c);
                    j1 = (int)vec32.field_72449_c;
                    if (b0 == 3) {
                        --j1;
                        vec32.field_72449_c += 1.0;
                    }
                    Block block1 = this.field_70170_p.func_147439_a(l, i1, j1);
                    int j2 = this.field_70170_p.func_72805_g(l, i1, j1);
                    if (par4 && block1 != null && block1.func_149668_a(this.field_70170_p, l, i1, j1) == null || block1 == Blocks.field_150350_a || !block1.func_149678_a(j2, par3) || (movingobjectposition1 = block1.func_149731_a(this.field_70170_p, l, i1, j1, par1Vec3, par2Vec3)) == null) continue;
                    return movingobjectposition1;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    public void func_70071_h_() {
        ILensEffect lens;
        this.setTicksExisted(this.getTicksExisted() + 1);
        this.superUpdate();
        if (!this.fake && !this.field_70128_L) {
            this.ping();
        }
        if ((lens = this.getLensInstance()) != null) {
            lens.updateBurst(this, this.getSourceLens());
        }
        int mana = this.getMana();
        if (this.getTicksExisted() >= this.getMinManaLoss()) {
            this.accumulatedManaLoss += this.getManaLossPerTick();
            int loss = (int)this.accumulatedManaLoss;
            this.setMana(mana - loss);
            this.accumulatedManaLoss -= (float)loss;
            if (this.getMana() <= 0) {
                this.func_70106_y();
            }
        }
        this.particles();
        this.setMotion(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        boolean bl = this.fullManaLastTick = this.getMana() == this.getStartingMana();
        if (this.scanBeam) {
            PositionProperties props = new PositionProperties((Entity)this);
            if (this.propsList.isEmpty()) {
                this.propsList.add(props);
            } else {
                PositionProperties lastProps = this.propsList.get(this.propsList.size() - 1);
                if (!props.coordsEqual(lastProps)) {
                    this.propsList.add(props);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
        this.func_70107_b(p_70056_1_, p_70056_3_, p_70056_5_);
        this.func_70101_b(p_70056_7_, p_70056_8_);
    }

    public boolean func_70072_I() {
        return false;
    }

    public TileEntity getCollidedTile(boolean noParticles) {
        this.noParticles = noParticles;
        while (!this.field_70128_L) {
            this.func_70071_h_();
        }
        if (this.fake) {
            this.incrementFakeParticleTick();
        }
        return this.collidedTile;
    }

    public void func_70014_b(NBTTagCompound par1nbtTagCompound) {
        super.func_70014_b(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_TICKS_EXISTED, this.getTicksExisted());
        par1nbtTagCompound.func_74768_a(TAG_COLOR, this.getColor());
        par1nbtTagCompound.func_74768_a(TAG_MANA, this.getMana());
        par1nbtTagCompound.func_74768_a(TAG_STARTING_MANA, this.getStartingMana());
        par1nbtTagCompound.func_74768_a(TAG_MIN_MANA_LOSS, this.getMinManaLoss());
        par1nbtTagCompound.func_74776_a(TAG_TICK_MANA_LOSS, this.getManaLossPerTick());
        par1nbtTagCompound.func_74776_a(TAG_GRAVITY, this.getGravity());
        ItemStack stack = this.getSourceLens();
        NBTTagCompound lensCmp = new NBTTagCompound();
        if (stack != null) {
            stack.func_77955_b(lensCmp);
        }
        par1nbtTagCompound.func_74782_a(TAG_LENS_STACK, (NBTBase)lensCmp);
        ChunkCoordinates coords = this.getBurstSourceChunkCoordinates();
        par1nbtTagCompound.func_74768_a(TAG_SPREADER_X, coords.field_71574_a);
        par1nbtTagCompound.func_74768_a(TAG_SPREADER_Y, coords.field_71572_b);
        par1nbtTagCompound.func_74768_a(TAG_SPREADER_Z, coords.field_71573_c);
        par1nbtTagCompound.func_74780_a(TAG_LAST_MOTION_X, this.field_70159_w);
        par1nbtTagCompound.func_74780_a(TAG_LAST_MOTION_Y, this.field_70181_x);
        par1nbtTagCompound.func_74780_a(TAG_LAST_MOTION_Z, this.field_70179_y);
        UUID identity = this.getShooterUIID();
        boolean hasShooter = identity != null;
        par1nbtTagCompound.func_74757_a(TAG_HAS_SHOOTER, hasShooter);
        if (hasShooter) {
            par1nbtTagCompound.func_74772_a(TAG_SHOOTER_UUID_MOST, identity.getMostSignificantBits());
            par1nbtTagCompound.func_74772_a(TAG_SHOOTER_UUID_LEAST, identity.getLeastSignificantBits());
        }
    }

    public void func_70037_a(NBTTagCompound par1nbtTagCompound) {
        super.func_70037_a(par1nbtTagCompound);
        this.setTicksExisted(par1nbtTagCompound.func_74762_e(TAG_TICKS_EXISTED));
        this.setColor(par1nbtTagCompound.func_74762_e(TAG_COLOR));
        this.setMana(par1nbtTagCompound.func_74762_e(TAG_MANA));
        this.setStartingMana(par1nbtTagCompound.func_74762_e(TAG_STARTING_MANA));
        this.setMinManaLoss(par1nbtTagCompound.func_74762_e(TAG_MIN_MANA_LOSS));
        this.setManaLossPerTick(par1nbtTagCompound.func_74760_g(TAG_TICK_MANA_LOSS));
        this.setGravity(par1nbtTagCompound.func_74760_g(TAG_GRAVITY));
        NBTTagCompound lensCmp = par1nbtTagCompound.func_74775_l(TAG_LENS_STACK);
        ItemStack stack = ItemStack.func_77949_a((NBTTagCompound)lensCmp);
        if (stack != null) {
            this.setSourceLens(stack);
        } else {
            this.setSourceLens(new ItemStack(Blocks.field_150348_b, 0, 0));
        }
        int x = par1nbtTagCompound.func_74762_e(TAG_SPREADER_X);
        int y = par1nbtTagCompound.func_74762_e(TAG_SPREADER_Y);
        int z = par1nbtTagCompound.func_74762_e(TAG_SPREADER_Z);
        this.setBurstSourceCoords(x, y, z);
        double lastMotionX = par1nbtTagCompound.func_74769_h(TAG_LAST_MOTION_X);
        double lastMotionY = par1nbtTagCompound.func_74769_h(TAG_LAST_MOTION_Y);
        double lastMotionZ = par1nbtTagCompound.func_74769_h(TAG_LAST_MOTION_Z);
        this.setMotion(lastMotionX, lastMotionY, lastMotionZ);
        boolean hasShooter = par1nbtTagCompound.func_74767_n(TAG_HAS_SHOOTER);
        if (hasShooter) {
            long most = par1nbtTagCompound.func_74763_f(TAG_SHOOTER_UUID_MOST);
            long least = par1nbtTagCompound.func_74763_f(TAG_SHOOTER_UUID_LEAST);
            UUID identity = this.getShooterUIID();
            if (identity == null || most != identity.getMostSignificantBits() || least != identity.getLeastSignificantBits()) {
                this.shooterIdentity = new UUID(most, least);
            }
        }
    }

    public void particles() {
        float osize;
        if (this.field_70128_L || !this.field_70170_p.field_72995_K) {
            return;
        }
        ILensEffect lens = this.getLensInstance();
        if (lens != null && !lens.doParticles(this, this.getSourceLens())) {
            return;
        }
        Color color = new Color(this.getColor());
        float r = (float)color.getRed() / 255.0f;
        float g = (float)color.getGreen() / 255.0f;
        float b = (float)color.getBlue() / 255.0f;
        int mana = this.getMana();
        int maxMana = this.getStartingMana();
        float size = osize = (float)mana / (float)maxMana;
        if (this.fake) {
            if (this.getMana() == this.getStartingMana()) {
                size = 2.0f;
            } else if (this.fullManaLastTick) {
                size = 4.0f;
            }
            if (!this.noParticles && this.shouldDoFakeParticles()) {
                Botania.proxy.sparkleFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, r, g, b, 0.4f * size, 1, true);
            }
        } else {
            boolean monocle = Botania.proxy.isClientPlayerWearingMonocle();
            if (monocle) {
                Botania.proxy.setWispFXDepthTest(false);
            }
            if (ConfigHandler.subtlePowerSystem) {
                Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, r, g, b, 0.1f * size, (float)(Math.random() - 0.5) * 0.02f, (float)(Math.random() - 0.5) * 0.02f, (float)(Math.random() - 0.5) * 0.01f);
            } else {
                float or = r;
                float og = g;
                float ob = b;
                double savedPosX = this.field_70165_t;
                double savedPosY = this.field_70163_u;
                double savedPosZ = this.field_70161_v;
                Vector3 currentPos = Vector3.fromEntity((Entity)this);
                Vector3 oldPos = new Vector3(this.field_70169_q, this.field_70167_r, this.field_70166_s);
                Vector3 diffVec = oldPos.copy().sub(currentPos);
                Vector3 diffVecNorm = diffVec.copy().normalize();
                double distance = 0.095;
                do {
                    r = or + ((float)Math.random() - 0.5f) * 0.25f;
                    g = og + ((float)Math.random() - 0.5f) * 0.25f;
                    b = ob + ((float)Math.random() - 0.5f) * 0.25f;
                    size = osize + ((float)Math.random() - 0.5f) * 0.065f + (float)Math.sin(new Random(this.field_96093_i.getMostSignificantBits()).nextInt(9001)) * 0.4f;
                    Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, r, g, b, 0.2f * size, (float)(-this.field_70159_w) * 0.01f, (float)(-this.field_70181_x) * 0.01f, (float)(-this.field_70179_y) * 0.01f);
                    this.field_70165_t += diffVecNorm.x * distance;
                    this.field_70163_u += diffVecNorm.y * distance;
                    this.field_70161_v += diffVecNorm.z * distance;
                    currentPos = Vector3.fromEntity((Entity)this);
                    diffVec = oldPos.copy().sub(currentPos);
                } while (!this.getEntityData().func_74764_b("orbit") && Math.abs(diffVec.mag()) > distance);
                Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, r, g, b, 0.1f * size, (float)(Math.random() - 0.5) * 0.06f, (float)(Math.random() - 0.5) * 0.06f, (float)(Math.random() - 0.5) * 0.06f);
                this.field_70165_t = savedPosX;
                this.field_70163_u = savedPosY;
                this.field_70161_v = savedPosZ;
            }
            if (monocle) {
                Botania.proxy.setWispFXDepthTest(true);
            }
        }
    }

    protected void func_70184_a(MovingObjectPosition movingobjectposition) {
        ILensEffect lens;
        boolean collided = false;
        boolean dead = false;
        if (movingobjectposition.field_72308_g == null) {
            TileEntity tile = this.field_70170_p.func_147438_o(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
            Block block = this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
            if (tile instanceof IManaCollisionGhost && ((IManaCollisionGhost)tile).isGhost() && !(block instanceof IManaTrigger) || block instanceof BlockBush || block instanceof BlockLeaves) {
                return;
            }
            if (BotaniaAPI.internalHandler.isBuildcraftPipe(tile)) {
                return;
            }
            ChunkCoordinates coords = this.getBurstSourceChunkCoordinates();
            if (tile != null && (tile.field_145851_c != coords.field_71574_a || tile.field_145848_d != coords.field_71572_b || tile.field_145849_e != coords.field_71573_c)) {
                this.collidedTile = tile;
            }
            if (tile == null || tile.field_145851_c != coords.field_71574_a || tile.field_145848_d != coords.field_71572_b || tile.field_145849_e != coords.field_71573_c) {
                boolean ghost;
                if (!this.fake && !this.noParticles && (!this.field_70170_p.field_72995_K || tile instanceof IClientManaHandler) && tile != null && tile instanceof IManaReceiver && ((IManaReceiver)tile).canRecieveManaFromBursts()) {
                    this.onRecieverImpact((IManaReceiver)tile, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
                }
                if (block instanceof IManaTrigger) {
                    ((IManaTrigger)block).onBurstCollision(this, this.field_70170_p, movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
                }
                boolean bl = dead = !(ghost = tile instanceof IManaCollisionGhost);
                if (ghost) {
                    return;
                }
            }
            collided = true;
        }
        if ((lens = this.getLensInstance()) != null) {
            dead = lens.collideBurst(this, movingobjectposition, this.collidedTile != null && this.collidedTile instanceof IManaReceiver && ((IManaReceiver)this.collidedTile).canRecieveManaFromBursts(), dead, this.getSourceLens());
        }
        if (collided && !this.hasAlreadyCollidedAt(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d)) {
            this.alreadyCollidedAt.add(this.getCollisionLocString(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d));
        }
        if (dead && !this.field_70128_L) {
            if (!this.fake) {
                Color color = new Color(this.getColor());
                float r = (float)color.getRed() / 255.0f;
                float g = (float)color.getGreen() / 255.0f;
                float b = (float)color.getBlue() / 255.0f;
                int mana = this.getMana();
                int maxMana = this.getStartingMana();
                float size = (float)mana / (float)maxMana;
                if (!ConfigHandler.subtlePowerSystem) {
                    for (int i = 0; i < 4; ++i) {
                        Botania.proxy.wispFX(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, r, g, b, 0.15f * size, (float)(Math.random() - 0.5) * 0.04f, (float)(Math.random() - 0.5) * 0.04f, (float)(Math.random() - 0.5) * 0.04f);
                    }
                }
                Botania.proxy.sparkleFX(this.field_70170_p, (float)this.field_70165_t, (float)this.field_70163_u, (float)this.field_70161_v, r, g, b, 4.0f, 2);
            }
            this.func_70106_y();
        }
    }

    protected void onRecieverImpact(IManaReceiver tile, int x, int y, int z) {
        int mana = this.getMana();
        if (tile instanceof IManaCollector) {
            mana = (int)((float)mana * ((IManaCollector)tile).getManaYieldMultiplier(this));
        }
        tile.recieveMana(mana);
        if (tile instanceof IThrottledPacket) {
            ((IThrottledPacket)((Object)tile)).markDispatchable();
        } else {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_70170_p, x, y, z);
        }
    }

    public void func_70106_y() {
        super.func_70106_y();
        if (!this.fake) {
            TileEntity tile = this.getShooter();
            if (tile != null && tile instanceof IManaSpreader) {
                ((IManaSpreader)tile).setCanShoot(true);
            }
        } else {
            this.setDeathTicksForFakeParticle();
        }
    }

    public TileEntity getShooter() {
        ChunkCoordinates coords = this.getBurstSourceChunkCoordinates();
        TileEntity tile = this.field_70170_p.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        return tile;
    }

    protected float func_70185_h() {
        return this.getGravity();
    }

    @Override
    public boolean isFake() {
        return this.fake;
    }

    @Override
    public void setFake(boolean fake) {
        this.fake = fake;
    }

    public void setScanBeam() {
        this.scanBeam = true;
    }

    @Override
    public int getColor() {
        return this.field_70180_af.func_75679_c(22);
    }

    @Override
    public void setColor(int color) {
        this.field_70180_af.func_75692_b(22, (Object)color);
    }

    @Override
    public int getMana() {
        return this.field_70180_af.func_75679_c(23);
    }

    @Override
    public void setMana(int mana) {
        this.field_70180_af.func_75692_b(23, (Object)mana);
    }

    @Override
    public int getStartingMana() {
        return this.field_70180_af.func_75679_c(24);
    }

    @Override
    public void setStartingMana(int mana) {
        this.field_70180_af.func_75692_b(24, (Object)mana);
    }

    @Override
    public int getMinManaLoss() {
        return this.field_70180_af.func_75679_c(25);
    }

    @Override
    public void setMinManaLoss(int minManaLoss) {
        this.field_70180_af.func_75692_b(25, (Object)minManaLoss);
    }

    @Override
    public float getManaLossPerTick() {
        return this.field_70180_af.func_111145_d(26);
    }

    @Override
    public void setManaLossPerTick(float mana) {
        this.field_70180_af.func_75692_b(26, (Object)Float.valueOf(mana));
    }

    @Override
    public float getGravity() {
        return this.field_70180_af.func_111145_d(27);
    }

    @Override
    public void setGravity(float gravity) {
        this.field_70180_af.func_75692_b(27, (Object)Float.valueOf(gravity));
    }

    @Override
    public ChunkCoordinates getBurstSourceChunkCoordinates() {
        int x = this.field_70180_af.func_75679_c(28);
        int y = this.field_70180_af.func_75679_c(29);
        int z = this.field_70180_af.func_75679_c(30);
        return new ChunkCoordinates(x, y, z);
    }

    @Override
    public void setBurstSourceCoords(int x, int y, int z) {
        this.field_70180_af.func_75692_b(28, (Object)x);
        this.field_70180_af.func_75692_b(29, (Object)y);
        this.field_70180_af.func_75692_b(30, (Object)z);
    }

    @Override
    public ItemStack getSourceLens() {
        return this.field_70180_af.func_82710_f(31);
    }

    @Override
    public void setSourceLens(ItemStack lens) {
        this.field_70180_af.func_75692_b(31, (Object)(lens == null ? new ItemStack(Blocks.field_150348_b, 0, 0) : lens));
    }

    @Override
    public int getTicksExisted() {
        return this._ticksExisted;
    }

    public void setTicksExisted(int ticks) {
        this._ticksExisted = ticks;
    }

    public ILensEffect getLensInstance() {
        ItemStack lens = this.getSourceLens();
        if (lens != null && lens.func_77973_b() instanceof ILensEffect) {
            return (ILensEffect)lens.func_77973_b();
        }
        return null;
    }

    @Override
    public void setMotion(double x, double y, double z) {
        this.field_70159_w = x;
        this.field_70181_x = y;
        this.field_70179_y = z;
    }

    @Override
    public boolean hasAlreadyCollidedAt(int x, int y, int z) {
        return this.alreadyCollidedAt.contains(this.getCollisionLocString(x, y, z));
    }

    @Override
    public void setCollidedAt(int x, int y, int z) {
        if (!this.hasAlreadyCollidedAt(x, y, z)) {
            this.alreadyCollidedAt.add(this.getCollisionLocString(x, y, z));
        }
    }

    private String getCollisionLocString(int x, int y, int z) {
        return x + ":" + y + ":" + z;
    }

    @Override
    public void setShooterUUID(UUID uuid) {
        this.shooterIdentity = uuid;
    }

    @Override
    public UUID getShooterUIID() {
        return this.shooterIdentity;
    }

    @Override
    public void ping() {
        TileEntity tile = this.getShooter();
        if (tile != null && tile instanceof IPingable) {
            ((IPingable)tile).pingback(this, this.getShooterUIID());
        }
    }

    public boolean shouldDoFakeParticles() {
        if (ConfigHandler.staticWandBeam) {
            return true;
        }
        TileEntity tile = this.getShooter();
        if (tile != null && tile instanceof IManaSpreader) {
            return this.getMana() != this.getStartingMana() && this.fullManaLastTick || Math.abs(((IManaSpreader)tile).getBurstParticleTick() - this.getTicksExisted()) < 4;
        }
        return false;
    }

    public void incrementFakeParticleTick() {
        TileEntity tile = this.getShooter();
        if (tile != null && tile instanceof IManaSpreader) {
            IManaSpreader spreader = (IManaSpreader)tile;
            spreader.setBurstParticleTick(spreader.getBurstParticleTick() + 2);
            if (spreader.getLastBurstDeathTick() != -1 && spreader.getBurstParticleTick() > spreader.getLastBurstDeathTick()) {
                spreader.setBurstParticleTick(0);
            }
        }
    }

    public void setDeathTicksForFakeParticle() {
        ChunkCoordinates coords = this.getBurstSourceChunkCoordinates();
        TileEntity tile = this.field_70170_p.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
        if (tile != null && tile instanceof IManaSpreader) {
            ((IManaSpreader)tile).setLastBurstDeathTick(this.getTicksExisted());
        }
    }

    public static class PositionProperties {
        public final ChunkCoordinates coords;
        public final Block block;
        public final int meta;
        public boolean invalid = false;

        public PositionProperties(Entity entity) {
            int x = MathHelper.func_76128_c((double)entity.field_70165_t);
            int y = MathHelper.func_76128_c((double)entity.field_70163_u);
            int z = MathHelper.func_76128_c((double)entity.field_70161_v);
            this.coords = new ChunkCoordinates(x, y, z);
            this.block = entity.field_70170_p.func_147439_a(x, y, z);
            this.meta = entity.field_70170_p.func_72805_g(x, y, z);
        }

        public boolean coordsEqual(PositionProperties props) {
            return this.coords.equals((Object)props.coords);
        }

        public boolean contentsEqual(World world) {
            if (!world.func_72899_e(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c)) {
                this.invalid = true;
                return false;
            }
            Block block = world.func_147439_a(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c);
            int meta = world.func_72805_g(this.coords.field_71574_a, this.coords.field_71572_b, this.coords.field_71573_c);
            return block == this.block && meta == this.meta;
        }
    }
}

