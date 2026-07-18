/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile.mana;

import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IKeyLocked;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.ILensControl;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.api.mana.IRedirectable;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;

public class TileSpreader
extends TileSimpleInventory
implements IManaCollector,
IWandBindable,
IKeyLocked,
IThrottledPacket,
IManaSpreader,
IRedirectable {
    private static final int MAX_MANA = 1000;
    private static final int ULTRA_MAX_MANA = 6400;
    private static final int TICKS_ALLOWED_WITHOUT_PINGBACK = 20;
    private static final double PINGBACK_EXPIRED_SEARCH_DISTANCE = 0.5;
    private static final String TAG_HAS_IDENTITY = "hasIdentity";
    private static final String TAG_UUID_MOST = "uuidMost";
    private static final String TAG_UUID_LEAST = "uuidLeast";
    private static final String TAG_MANA = "mana";
    private static final String TAG_KNOWN_MANA = "knownMana";
    private static final String TAG_REQUEST_UPDATE = "requestUpdate";
    private static final String TAG_ROTATION_X = "rotationX";
    private static final String TAG_ROTATION_Y = "rotationY";
    private static final String TAG_PADDING_COLOR = "paddingColor";
    private static final String TAG_CAN_SHOOT_BURST = "canShootBurst";
    private static final String TAG_PINGBACK_TICKS = "pingbackTicks";
    private static final String TAG_LAST_PINGBACK_X = "lastPingbackX";
    private static final String TAG_LAST_PINGBACK_Y = "lastPingbackY";
    private static final String TAG_LAST_PINGBACK_Z = "lastPingbackZ";
    private static final String TAG_FORCE_CLIENT_BINDING_X = "forceClientBindingX";
    private static final String TAG_FORCE_CLIENT_BINDING_Y = "forceClientBindingY";
    private static final String TAG_FORCE_CLIENT_BINDING_Z = "forceClientBindingZ";
    private static final String TAG_INPUT_KEY = "inputKey";
    private static final String TAG_OUTPUT_KEY = "outputKey";
    private static final String TAG_MAPMAKER_OVERRIDE = "mapmakerOverrideEnabled";
    private static final String TAG_FORCED_COLOR = "mmForcedColor";
    private static final String TAG_FORCED_MANA_PAYLOAD = "mmForcedManaPayload";
    private static final String TAG_FORCED_TICKS_BEFORE_MANA_LOSS = "mmForcedTicksBeforeManaLoss";
    private static final String TAG_FORCED_MANA_LOSS_PER_TICK = "mmForcedManaLossPerTick";
    private static final String TAG_FORCED_GRAVITY = "mmForcedGravity";
    private static final String TAG_FORCED_VELOCITY_MULTIPLIER = "mmForcedVelocityMultiplier";
    boolean mapmakerOverride = false;
    int mmForcedColor = 0x20FF20;
    int mmForcedManaPayload = 160;
    int mmForcedTicksBeforeManaLoss = 60;
    float mmForcedManaLossPerTick = 4.0f;
    float mmForcedGravity = 0.0f;
    float mmForcedVelocityMultiplier = 1.0f;
    String inputKey = "";
    String outputKey = "";
    public static boolean staticRedstone = false;
    public static boolean staticDreamwood = false;
    public static boolean staticUltra = false;
    UUID identity;
    int mana;
    int knownMana = -1;
    public float rotationX;
    public float rotationY;
    public int paddingColor = -1;
    boolean requestsClientUpdate = false;
    boolean hasReceivedInitialPacket = false;
    IManaReceiver receiver = null;
    IManaReceiver receiverLastTick = null;
    boolean redstoneLastTick = true;
    public boolean canShootBurst = true;
    public int lastBurstDeathTick = -1;
    public int burstParticleTick = 0;
    public int pingbackTicks = 0;
    public double lastPingbackX = 0.0;
    public double lastPingbackY = -1.0;
    public double lastPingbackZ = 0.0;
    List<EntityManaBurst.PositionProperties> lastTentativeBurst;
    boolean invalidTentativeBurst = false;

    @Override
    public boolean isFull() {
        return this.mana >= this.getMaxMana();
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(this.mana + mana, this.getMaxMana());
    }

    public void func_145843_s() {
        super.func_145843_s();
        ManaNetworkEvent.removeCollector(this);
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        this.func_145843_s();
    }

    public void func_145845_h() {
        ItemStack lens;
        ILensControl control;
        boolean inNetwork;
        boolean wasInNetwork = inNetwork = ManaNetworkHandler.instance.isCollectorIn(this);
        if (!inNetwork && !this.func_145837_r()) {
            ManaNetworkEvent.addCollector(this);
            inNetwork = true;
        }
        boolean redstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide;
            TileEntity tileAt = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
            if (tileAt instanceof IManaPool) {
                IManaPool pool = (IManaPool)tileAt;
                if (wasInNetwork && (pool != this.receiver || this.isRedstone())) {
                    if (pool instanceof IKeyLocked && !((IKeyLocked)((Object)pool)).getOutputKey().equals(this.getInputKey())) continue;
                    int manaInPool = pool.getCurrentMana();
                    if (manaInPool > 0 && !this.isFull()) {
                        int manaMissing = this.getMaxMana() - this.mana;
                        int manaToRemove = Math.min(manaInPool, manaMissing);
                        pool.recieveMana(-manaToRemove);
                        this.recieveMana(manaToRemove);
                    }
                }
            }
            if ((redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal())) <= 0) continue;
            redstone = true;
        }
        if (this.needsNewBurstSimulation()) {
            this.checkForReceiver();
        }
        if (!this.canShootBurst) {
            if (this.pingbackTicks <= 0) {
                double x = this.lastPingbackX;
                double y = this.lastPingbackY;
                double z = this.lastPingbackZ;
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)x, (double)y, (double)z).func_72314_b(0.5, 0.5, 0.5);
                List bursts = this.field_145850_b.func_72872_a(IManaBurst.class, aabb);
                IManaBurst found = null;
                UUID identity = this.getIdentifier();
                for (IManaBurst burst : bursts) {
                    if (burst == null || !identity.equals(burst.getShooterUIID())) continue;
                    found = burst;
                    break;
                }
                if (found != null) {
                    found.ping();
                } else {
                    this.setCanShoot(true);
                }
            } else {
                --this.pingbackTicks;
            }
        }
        boolean shouldShoot = !redstone;
        boolean isredstone = this.isRedstone();
        if (isredstone) {
            boolean bl = shouldShoot = redstone && !this.redstoneLastTick;
        }
        if (shouldShoot && this.receiver != null && this.receiver instanceof IKeyLocked) {
            shouldShoot = ((IKeyLocked)((Object)this.receiver)).getInputKey().equals(this.getOutputKey());
        }
        if ((control = this.getLensController(lens = this.func_70301_a(0))) != null) {
            if (isredstone) {
                if (shouldShoot) {
                    control.onControlledSpreaderPulse(lens, this, redstone);
                }
            } else {
                control.onControlledSpreaderTick(lens, this, redstone);
            }
            shouldShoot &= control.allowBurstShooting(lens, this, redstone);
        }
        if (shouldShoot) {
            this.tryShootBurst();
        }
        if (this.receiverLastTick != this.receiver && !this.field_145850_b.field_72995_K) {
            this.requestsClientUpdate = true;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        this.redstoneLastTick = redstone;
        this.receiverLastTick = this.receiver;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        UUID identity = this.getIdentifier();
        cmp.func_74757_a(TAG_HAS_IDENTITY, true);
        cmp.func_74772_a(TAG_UUID_MOST, identity.getMostSignificantBits());
        cmp.func_74772_a(TAG_UUID_LEAST, identity.getLeastSignificantBits());
        cmp.func_74768_a(TAG_MANA, this.mana);
        cmp.func_74776_a(TAG_ROTATION_X, this.rotationX);
        cmp.func_74776_a(TAG_ROTATION_Y, this.rotationY);
        cmp.func_74757_a(TAG_REQUEST_UPDATE, this.requestsClientUpdate);
        cmp.func_74768_a(TAG_PADDING_COLOR, this.paddingColor);
        cmp.func_74757_a(TAG_CAN_SHOOT_BURST, this.canShootBurst);
        cmp.func_74768_a(TAG_PINGBACK_TICKS, this.pingbackTicks);
        cmp.func_74780_a(TAG_LAST_PINGBACK_X, this.lastPingbackX);
        cmp.func_74780_a(TAG_LAST_PINGBACK_Y, this.lastPingbackY);
        cmp.func_74780_a(TAG_LAST_PINGBACK_Z, this.lastPingbackZ);
        cmp.func_74778_a(TAG_INPUT_KEY, this.inputKey);
        cmp.func_74778_a(TAG_OUTPUT_KEY, this.outputKey);
        cmp.func_74768_a(TAG_FORCE_CLIENT_BINDING_X, this.receiver == null ? 0 : ((TileEntity)this.receiver).field_145851_c);
        cmp.func_74768_a(TAG_FORCE_CLIENT_BINDING_Y, this.receiver == null ? -1 : ((TileEntity)this.receiver).field_145848_d);
        cmp.func_74768_a(TAG_FORCE_CLIENT_BINDING_Z, this.receiver == null ? 0 : ((TileEntity)this.receiver).field_145849_e);
        cmp.func_74757_a(TAG_MAPMAKER_OVERRIDE, this.mapmakerOverride);
        cmp.func_74768_a(TAG_FORCED_COLOR, this.mmForcedColor);
        cmp.func_74768_a(TAG_FORCED_MANA_PAYLOAD, this.mmForcedManaPayload);
        cmp.func_74768_a(TAG_FORCED_TICKS_BEFORE_MANA_LOSS, this.mmForcedTicksBeforeManaLoss);
        cmp.func_74776_a(TAG_FORCED_MANA_LOSS_PER_TICK, this.mmForcedManaLossPerTick);
        cmp.func_74776_a(TAG_FORCED_GRAVITY, this.mmForcedGravity);
        cmp.func_74776_a(TAG_FORCED_VELOCITY_MULTIPLIER, this.mmForcedVelocityMultiplier);
        this.requestsClientUpdate = false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        if (cmp.func_74767_n(TAG_HAS_IDENTITY)) {
            long most = cmp.func_74763_f(TAG_UUID_MOST);
            long least = cmp.func_74763_f(TAG_UUID_LEAST);
            UUID identity = this.getIdentifierUnsafe();
            if (identity == null || most != identity.getMostSignificantBits() || least != identity.getLeastSignificantBits()) {
                identity = new UUID(most, least);
            }
        } else {
            this.getIdentifier();
        }
        this.mana = cmp.func_74762_e(TAG_MANA);
        this.rotationX = cmp.func_74760_g(TAG_ROTATION_X);
        this.rotationY = cmp.func_74760_g(TAG_ROTATION_Y);
        this.requestsClientUpdate = cmp.func_74767_n(TAG_REQUEST_UPDATE);
        if (cmp.func_74764_b(TAG_INPUT_KEY)) {
            this.inputKey = cmp.func_74779_i(TAG_INPUT_KEY);
        }
        if (cmp.func_74764_b(TAG_OUTPUT_KEY)) {
            this.inputKey = cmp.func_74779_i(TAG_OUTPUT_KEY);
        }
        this.mapmakerOverride = cmp.func_74767_n(TAG_MAPMAKER_OVERRIDE);
        this.mmForcedColor = cmp.func_74762_e(TAG_FORCED_COLOR);
        this.mmForcedManaPayload = cmp.func_74762_e(TAG_FORCED_MANA_PAYLOAD);
        this.mmForcedTicksBeforeManaLoss = cmp.func_74762_e(TAG_FORCED_TICKS_BEFORE_MANA_LOSS);
        this.mmForcedManaLossPerTick = cmp.func_74760_g(TAG_FORCED_MANA_LOSS_PER_TICK);
        this.mmForcedGravity = cmp.func_74760_g(TAG_FORCED_GRAVITY);
        this.mmForcedVelocityMultiplier = cmp.func_74760_g(TAG_FORCED_VELOCITY_MULTIPLIER);
        if (cmp.func_74764_b(TAG_KNOWN_MANA)) {
            this.knownMana = cmp.func_74762_e(TAG_KNOWN_MANA);
        }
        if (cmp.func_74764_b(TAG_PADDING_COLOR)) {
            this.paddingColor = cmp.func_74762_e(TAG_PADDING_COLOR);
        }
        if (cmp.func_74764_b(TAG_CAN_SHOOT_BURST)) {
            this.canShootBurst = cmp.func_74767_n(TAG_CAN_SHOOT_BURST);
        }
        this.pingbackTicks = cmp.func_74762_e(TAG_PINGBACK_TICKS);
        this.lastPingbackX = cmp.func_74769_h(TAG_LAST_PINGBACK_X);
        this.lastPingbackY = cmp.func_74769_h(TAG_LAST_PINGBACK_Y);
        this.lastPingbackZ = cmp.func_74769_h(TAG_LAST_PINGBACK_Z);
        if (this.requestsClientUpdate && this.field_145850_b != null) {
            TileEntity tile;
            int x = cmp.func_74762_e(TAG_FORCE_CLIENT_BINDING_X);
            int y = cmp.func_74762_e(TAG_FORCE_CLIENT_BINDING_Y);
            int z = cmp.func_74762_e(TAG_FORCE_CLIENT_BINDING_Z);
            this.receiver = y != -1 ? ((tile = this.field_145850_b.func_147438_o(x, y, z)) instanceof IManaReceiver ? (IManaReceiver)tile : null) : null;
        }
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.hasReceivedInitialPacket = true;
        }
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return;
        }
        if (!player.func_70093_af()) {
            if (!this.field_145850_b.field_72995_K) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                this.writeCustomNBT(nbttagcompound);
                nbttagcompound.func_74768_a(TAG_KNOWN_MANA, this.mana);
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound));
                }
            }
            this.field_145850_b.func_72956_a((Entity)player, "botania:ding", 0.1f, 1.0f);
        } else {
            MovingObjectPosition pos = TileSpreader.raytraceFromEntity(this.field_145850_b, (Entity)player, true, 5.0);
            if (pos != null && pos.field_72307_f != null && !this.field_145850_b.field_72995_K) {
                double x = pos.field_72307_f.field_72450_a - (double)this.field_145851_c - 0.5;
                double y = pos.field_72307_f.field_72448_b - (double)this.field_145848_d - 0.5;
                double z = pos.field_72307_f.field_72449_c - (double)this.field_145849_e - 0.5;
                if (pos.field_72310_e != 0 && pos.field_72310_e != 1) {
                    Vector3 clickVector = new Vector3(x, 0.0, z);
                    Vector3 relative = new Vector3(-0.5, 0.0, 0.0);
                    double angle = Math.acos(clickVector.dotProduct(relative) / (relative.mag() * clickVector.mag())) * 180.0 / Math.PI;
                    this.rotationX = (float)angle + 180.0f;
                    if (clickVector.z < 0.0) {
                        this.rotationX = 360.0f - this.rotationX;
                    }
                }
                double angle = y * 180.0;
                this.rotationY = -((float)angle);
                this.checkForReceiver();
                this.requestsClientUpdate = true;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    private boolean needsNewBurstSimulation() {
        if (this.field_145850_b.field_72995_K && !this.hasReceivedInitialPacket) {
            return false;
        }
        if (this.lastTentativeBurst == null) {
            return true;
        }
        for (EntityManaBurst.PositionProperties props : this.lastTentativeBurst) {
            if (props.contentsEqual(this.field_145850_b)) continue;
            this.invalidTentativeBurst = props.invalid;
            return !this.invalidTentativeBurst;
        }
        return false;
    }

    public void tryShootBurst() {
        EntityManaBurst burst;
        if ((this.receiver != null || this.isRedstone()) && !this.invalidTentativeBurst && this.canShootBurst && (this.isRedstone() || this.receiver.canRecieveManaFromBursts() && !this.receiver.isFull()) && (burst = this.getBurst(false)) != null && !this.field_145850_b.field_72995_K) {
            this.mana -= burst.getStartingMana();
            burst.setShooterUUID(this.getIdentifier());
            this.field_145850_b.func_72838_d((Entity)burst);
            burst.ping();
            if (!ConfigHandler.silentSpreaders) {
                this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:spreaderFire", 0.05f * (this.paddingColor != -1 ? 0.2f : 1.0f), 0.7f + 0.3f * (float)Math.random());
            }
        }
    }

    public boolean isRedstone() {
        return this.field_145850_b == null ? staticRedstone : this.func_145832_p() == 1;
    }

    public boolean isDreamwood() {
        return this.field_145850_b == null ? staticDreamwood : this.func_145832_p() == 2 || this.func_145832_p() == 3;
    }

    public boolean isULTRA_SPREADER() {
        return this.field_145850_b == null ? staticUltra : this.func_145832_p() == 3;
    }

    public void checkForReceiver() {
        ItemStack stack = this.func_70301_a(0);
        ILensControl control = this.getLensController(stack);
        if (control != null && !control.allowBurstShooting(stack, this, false)) {
            return;
        }
        EntityManaBurst fakeBurst = this.getBurst(true);
        fakeBurst.setScanBeam();
        TileEntity receiver = fakeBurst.getCollidedTile(true);
        this.receiver = receiver != null && receiver instanceof IManaReceiver ? (IManaReceiver)receiver : null;
        this.lastTentativeBurst = fakeBurst.propsList;
    }

    public EntityManaBurst getBurst(boolean fake) {
        float manaLossPerTick;
        int color;
        int maxMana;
        EntityManaBurst burst = new EntityManaBurst(this, fake);
        boolean dreamwood = this.isDreamwood();
        boolean ultra = this.isULTRA_SPREADER();
        int n = ultra ? 640 : (maxMana = dreamwood ? 240 : 160);
        int n2 = this.isRedstone() ? 0xFF2020 : (color = dreamwood ? 16729540 : 0x20FF20);
        int ticksBeforeManaLoss = ultra ? 120 : (dreamwood ? 80 : 60);
        float f = manaLossPerTick = ultra ? 20.0f : 4.0f;
        float motionModifier = ultra ? 2.0f : (dreamwood ? 1.25f : 1.0f);
        float gravity = 0.0f;
        BurstProperties props = new BurstProperties(maxMana, ticksBeforeManaLoss, manaLossPerTick, gravity, motionModifier, color);
        ItemStack lens = this.func_70301_a(0);
        if (lens != null && lens.func_77973_b() instanceof ILensEffect) {
            ((ILensEffect)lens.func_77973_b()).apply(lens, props);
        }
        burst.setSourceLens(lens);
        if (this.getCurrentMana() >= props.maxMana || fake) {
            if (this.mapmakerOverride) {
                burst.setColor(this.mmForcedColor);
                burst.setMana(this.mmForcedManaPayload);
                burst.setStartingMana(this.mmForcedManaPayload);
                burst.setMinManaLoss(this.mmForcedTicksBeforeManaLoss);
                burst.setManaLossPerTick(this.mmForcedManaLossPerTick);
                burst.setGravity(this.mmForcedGravity);
                burst.setMotion(burst.field_70159_w * (double)this.mmForcedVelocityMultiplier, burst.field_70181_x * (double)this.mmForcedVelocityMultiplier, burst.field_70179_y * (double)this.mmForcedVelocityMultiplier);
            } else {
                burst.setColor(props.color);
                burst.setMana(props.maxMana);
                burst.setStartingMana(props.maxMana);
                burst.setMinManaLoss(props.ticksBeforeManaLoss);
                burst.setManaLossPerTick(props.manaLossPerTick);
                burst.setGravity(props.gravity);
                burst.setMotion(burst.field_70159_w * (double)props.motionModifier, burst.field_70181_x * (double)props.motionModifier, burst.field_70179_y * (double)props.motionModifier);
            }
            return burst;
        }
        return null;
    }

    public ILensControl getLensController(ItemStack stack) {
        ILensControl control;
        if (stack != null && stack.func_77973_b() instanceof ILensControl && (control = (ILensControl)stack.func_77973_b()).isControlLens(stack)) {
            return control;
        }
        return null;
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
        float f = 1.0f;
        float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * f;
        float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * f;
        double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)f;
        double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)f;
        if (!world.field_72995_K && player instanceof EntityPlayer) {
            d1 += 1.62;
        }
        double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)player).field_71134_c.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        String name = StatCollector.func_74838_a((String)(new ItemStack(ModBlocks.spreader, 1, this.func_145832_p()).func_77977_a().replaceAll("tile.", "tile.botania:") + ".name"));
        int color = this.isRedstone() ? 0xFF0000 : (this.isDreamwood() ? 16711854 : 65280);
        HUDHandler.drawSimpleManaHUD(color, this.knownMana, this.getMaxMana(), name, res);
        ItemStack lens = this.func_70301_a(0);
        if (lens != null) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            String lensName = lens.func_82833_r();
            int width = 16 + mc.field_71466_p.func_78256_a(lensName) / 2;
            int x = res.func_78326_a() / 2 - width;
            int y = res.func_78328_b() / 2 + 50;
            mc.field_71466_p.func_78261_a(lensName, x + 20, y + 5, color);
            RenderHelper.func_74520_c();
            RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, lens, x, y);
            RenderHelper.func_74518_a();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3042);
        }
        if (this.receiver != null) {
            TileEntity receiverTile = (TileEntity)this.receiver;
            ItemStack recieverStack = new ItemStack(this.field_145850_b.func_147439_a(receiverTile.field_145851_c, receiverTile.field_145848_d, receiverTile.field_145849_e), 1, receiverTile.func_145832_p());
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            if (recieverStack != null && recieverStack.func_77973_b() != null) {
                String stackName = recieverStack.func_82833_r();
                int width = 16 + mc.field_71466_p.func_78256_a(stackName) / 2;
                int x = res.func_78326_a() / 2 - width;
                int y = res.func_78328_b() / 2 + 30;
                mc.field_71466_p.func_78261_a(stackName, x + 20, y + 5, color);
                RenderHelper.func_74520_c();
                RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, recieverStack, x, y);
                RenderHelper.func_74518_a();
            }
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3042);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void onClientDisplayTick() {
        if (this.field_145850_b != null) {
            EntityManaBurst burst = this.getBurst(true);
            burst.getCollidedTile(false);
        }
    }

    @Override
    public float getManaYieldMultiplier(IManaBurst burst) {
        return burst.getMana() < 16 ? 0.0f : 0.95f;
    }

    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "spreader";
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return itemstack.func_77973_b() instanceof ILens;
    }

    public void func_70296_d() {
        this.checkForReceiver();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public ChunkCoordinates getBinding() {
        if (this.receiver == null) {
            return null;
        }
        TileEntity tile = (TileEntity)this.receiver;
        return new ChunkCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    }

    @Override
    public int getMaxMana() {
        return this.isULTRA_SPREADER() ? 6400 : 1000;
    }

    @Override
    public String getInputKey() {
        return this.inputKey;
    }

    @Override
    public String getOutputKey() {
        return this.outputKey;
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        Vector3 thisVec = Vector3.fromTileEntityCenter(this);
        Vector3 blockVec = new Vector3((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
        AxisAlignedBB axis = player.field_70170_p.func_147439_a(x, y, z).func_149668_a(player.field_70170_p, x, y, z);
        if (axis == null) {
            axis = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
        }
        if (!blockVec.isInside(axis)) {
            blockVec = new Vector3(axis.field_72340_a + (axis.field_72336_d - axis.field_72340_a) / 2.0, axis.field_72338_b + (axis.field_72337_e - axis.field_72338_b) / 2.0, axis.field_72339_c + (axis.field_72334_f - axis.field_72339_c) / 2.0);
        }
        Vector3 diffVec = blockVec.copy().sub(thisVec);
        Vector3 diffVec2D = new Vector3(diffVec.x, diffVec.z, 0.0);
        Vector3 rotVec = new Vector3(0.0, 1.0, 0.0);
        double angle = rotVec.angle(diffVec2D) / Math.PI * 180.0;
        if (blockVec.x < thisVec.x) {
            angle = -angle;
        }
        this.rotationX = (float)angle + 90.0f;
        rotVec = new Vector3(diffVec.x, 0.0, diffVec.z);
        angle = diffVec.angle(rotVec) * 180.0 / Math.PI;
        if (blockVec.y < thisVec.y) {
            angle = -angle;
        }
        this.rotationY = (float)angle;
        this.checkForReceiver();
        return true;
    }

    @Override
    public void markDispatchable() {
    }

    @Override
    public float getRotationX() {
        return this.rotationX;
    }

    @Override
    public float getRotationY() {
        return this.rotationY;
    }

    @Override
    public void setRotationX(float rot) {
        this.rotationX = rot;
    }

    @Override
    public void setRotationY(float rot) {
        this.rotationY = rot;
    }

    @Override
    public void commitRedirection() {
        this.checkForReceiver();
    }

    @Override
    public void setCanShoot(boolean canShoot) {
        this.canShootBurst = canShoot;
    }

    @Override
    public int getBurstParticleTick() {
        return this.burstParticleTick;
    }

    @Override
    public void setBurstParticleTick(int i) {
        this.burstParticleTick = i;
    }

    @Override
    public int getLastBurstDeathTick() {
        return this.lastBurstDeathTick;
    }

    @Override
    public void setLastBurstDeathTick(int i) {
        this.lastBurstDeathTick = i;
    }

    @Override
    public void pingback(IManaBurst burst, UUID expectedIdentity) {
        if (this.getIdentifier().equals(expectedIdentity)) {
            this.pingbackTicks = 20;
            Entity e = (Entity)burst;
            this.lastPingbackX = e.field_70165_t;
            this.lastPingbackY = e.field_70163_u;
            this.lastPingbackZ = e.field_70161_v;
            this.setCanShoot(false);
        }
    }

    @Override
    public UUID getIdentifier() {
        if (this.identity == null) {
            this.identity = UUID.randomUUID();
        }
        return this.identity;
    }

    public UUID getIdentifierUnsafe() {
        return this.identity;
    }
}

