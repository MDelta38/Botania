/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.block.tile.mana;

import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IKeyLocked;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemManaTablet;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibMisc;

public class TilePool
extends TileMod
implements IManaPool,
IDyablePool,
IKeyLocked,
ISparkAttachable,
IThrottledPacket {
    public static final int MAX_MANA = 1000000;
    public static final int MAX_MANA_DILLUTED = 10000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_KNOWN_MANA = "knownMana";
    private static final String TAG_OUTPUTTING = "outputting";
    private static final String TAG_COLOR = "color";
    private static final String TAG_MANA_CAP = "manaCap";
    private static final String TAG_CAN_ACCEPT = "canAccept";
    private static final String TAG_CAN_SPARE = "canSpare";
    private static final String TAG_FRAGILE = "fragile";
    private static final String TAG_INPUT_KEY = "inputKey";
    private static final String TAG_OUTPUT_KEY = "outputKey";
    boolean outputting = false;
    public boolean alchemy = false;
    public boolean conjuration = false;
    boolean catalystsRegistered = false;
    public int color = 0;
    int mana;
    int knownMana = -1;
    public int manaCap = -1;
    int soundTicks = 0;
    boolean canAccept = true;
    boolean canSpare = true;
    public boolean fragile = false;
    public boolean isDoingTransfer = false;
    public int ticksDoingTransfer = 0;
    String inputKey = "";
    String outputKey = "";
    int ticks = 0;
    boolean sendPacket = false;

    @Override
    public boolean isFull() {
        Block blockBelow = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        return blockBelow != ModBlocks.manaVoid && this.getCurrentMana() >= this.manaCap;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + mana, this.manaCap));
        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        this.markDispatchable();
    }

    public void func_145843_s() {
        super.func_145843_s();
        ManaNetworkEvent.removePool(this);
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        this.func_145843_s();
    }

    public boolean collideEntityItem(EntityItem item) {
        if (item.field_70128_L) {
            return false;
        }
        boolean didChange = false;
        ItemStack stack = item.func_92059_d();
        if (stack == null) {
            return false;
        }
        if (stack.func_77973_b() instanceof IManaDissolvable) {
            ((IManaDissolvable)stack.func_77973_b()).onDissolveTick(this, stack, item);
            if (stack.field_77994_a == 0) {
                item.func_70106_y();
            }
        }
        if (item.field_70292_b > 100 && item.field_70292_b < 130 || !this.catalystsRegistered) {
            return false;
        }
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (!recipe.matches(stack) || recipe.isAlchemy() && !this.alchemy || recipe.isConjuration() && !this.conjuration) continue;
            int mana = recipe.getManaToConsume();
            if (this.getCurrentMana() < mana) break;
            this.recieveMana(-mana);
            if (!this.field_145850_b.field_72995_K) {
                --stack.field_77994_a;
                if (stack.field_77994_a == 0) {
                    item.func_70106_y();
                }
                ItemStack output = recipe.getOutput().func_77946_l();
                EntityItem outputItem = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, output);
                outputItem.field_70292_b = 105;
                this.field_145850_b.func_72838_d((Entity)outputItem);
            }
            this.craftingFanciness();
            didChange = true;
            break;
        }
        return didChange;
    }

    public void craftingFanciness() {
        if (this.soundTicks == 0) {
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "botania:manaPoolCraft", 0.4f, 4.0f);
            this.soundTicks = 6;
        }
        for (int i = 0; i < 25; ++i) {
            float red = (float)Math.random();
            float green = (float)Math.random();
            float blue = (float)Math.random();
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + 0.5 + Math.random() * 0.4 - 0.2, this.field_145848_d + 1, (double)this.field_145849_e + 0.5 + Math.random() * 0.4 - 0.2, red, green, blue, (float)Math.random(), 10);
        }
    }

    public void func_145845_h() {
        boolean wasDoingTransfer = this.isDoingTransfer;
        this.isDoingTransfer = false;
        if (this.manaCap == -1) {
            int n = this.manaCap = this.func_145832_p() == 2 ? 10000 : 1000000;
        }
        if (!ManaNetworkHandler.instance.isPoolIn(this) && !this.func_145837_r()) {
            ManaNetworkEvent.addPool(this);
        }
        if (this.soundTicks > 0) {
            --this.soundTicks;
        }
        if (this.field_145850_b.field_72995_K) {
            double particleChance = 1.0 - (double)this.getCurrentMana() / (double)this.manaCap * 0.1;
            Color color = new Color(50943);
            if (Math.random() > particleChance) {
                Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.3 + Math.random() * 0.5, (double)this.field_145848_d + 0.6 + Math.random() * 0.25, (double)this.field_145849_e + Math.random(), (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)Math.random() / 3.0f, (float)(-Math.random()) / 25.0f, 2.0f);
            }
        }
        if (this.sendPacket && this.ticks % 10 == 0) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            this.sendPacket = false;
        }
        this.alchemy = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ModBlocks.alchemyCatalyst;
        this.conjuration = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ModBlocks.conjurationCatalyst;
        this.catalystsRegistered = true;
        List items = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)));
        for (EntityItem item : items) {
            int manaVal;
            ItemStack stack;
            if (item.field_70128_L || (stack = item.func_92059_d()) == null || !(stack.func_77973_b() instanceof IManaItem)) continue;
            IManaItem mana = (IManaItem)stack.func_77973_b();
            if ((!this.outputting || !mana.canReceiveManaFromPool(stack, this)) && (this.outputting || !mana.canExportManaToPool(stack, this))) continue;
            boolean didSomething = false;
            int bellowCount = 0;
            if (this.outputting) {
                for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
                    TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
                    if (tile == null || !(tile instanceof TileBellows) || ((TileBellows)tile).getLinkedTile() != this) continue;
                    ++bellowCount;
                }
            }
            int transfRate = 1000 * (bellowCount + 1);
            if (this.outputting) {
                if (this.canSpare) {
                    if (this.getCurrentMana() > 0 && mana.getMana(stack) < mana.getMaxMana(stack)) {
                        didSomething = true;
                    }
                    manaVal = Math.min(transfRate, Math.min(this.getCurrentMana(), mana.getMaxMana(stack) - mana.getMana(stack)));
                    if (!this.field_145850_b.field_72995_K) {
                        mana.addMana(stack, manaVal);
                    }
                    this.recieveMana(-manaVal);
                }
            } else if (this.canAccept) {
                if (mana.getMana(stack) > 0 && !this.isFull()) {
                    didSomething = true;
                }
                manaVal = Math.min(transfRate, Math.min(this.manaCap - this.getCurrentMana(), mana.getMana(stack)));
                if (!this.field_145850_b.field_72995_K) {
                    mana.addMana(stack, -manaVal);
                }
                this.recieveMana(manaVal);
            }
            if (!didSomething) continue;
            if (this.field_145850_b.field_72995_K && ConfigHandler.chargingAnimationEnabled && this.field_145850_b.field_73012_v.nextInt(20) == 0) {
                Vector3 itemVec = Vector3.fromTileEntity(this).add(0.5, 0.5 + Math.random() * 0.3, 0.5);
                Vector3 tileVec = Vector3.fromTileEntity(this).add(0.2 + Math.random() * 0.6, 0.0, 0.2 + Math.random() * 0.6);
                LightningHandler.spawnLightningBolt(this.field_145850_b, this.outputting ? tileVec : itemVec, this.outputting ? itemVec : tileVec, 80.0f, this.field_145850_b.field_73012_v.nextLong(), 1140881820, 1140901631);
            }
            this.isDoingTransfer = this.outputting;
        }
        if (this.isDoingTransfer) {
            ++this.ticksDoingTransfer;
        } else {
            this.ticksDoingTransfer = 0;
            if (wasDoingTransfer) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            }
        }
        ++this.ticks;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
        cmp.func_74757_a(TAG_OUTPUTTING, this.outputting);
        cmp.func_74768_a(TAG_COLOR, this.color);
        cmp.func_74768_a(TAG_MANA_CAP, this.manaCap);
        cmp.func_74757_a(TAG_CAN_ACCEPT, this.canAccept);
        cmp.func_74757_a(TAG_CAN_SPARE, this.canSpare);
        cmp.func_74757_a(TAG_FRAGILE, this.fragile);
        cmp.func_74778_a(TAG_INPUT_KEY, this.inputKey);
        cmp.func_74778_a(TAG_OUTPUT_KEY, this.outputKey);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
        this.outputting = cmp.func_74767_n(TAG_OUTPUTTING);
        this.color = cmp.func_74762_e(TAG_COLOR);
        if (cmp.func_74764_b(TAG_MANA_CAP)) {
            this.manaCap = cmp.func_74762_e(TAG_MANA_CAP);
        }
        if (cmp.func_74764_b(TAG_CAN_ACCEPT)) {
            this.canAccept = cmp.func_74767_n(TAG_CAN_ACCEPT);
        }
        if (cmp.func_74764_b(TAG_CAN_SPARE)) {
            this.canSpare = cmp.func_74767_n(TAG_CAN_SPARE);
        }
        this.fragile = cmp.func_74767_n(TAG_FRAGILE);
        if (cmp.func_74764_b(TAG_INPUT_KEY)) {
            this.inputKey = cmp.func_74779_i(TAG_INPUT_KEY);
        }
        if (cmp.func_74764_b(TAG_OUTPUT_KEY)) {
            this.inputKey = cmp.func_74779_i(TAG_OUTPUT_KEY);
        }
        if (cmp.func_74764_b(TAG_KNOWN_MANA)) {
            this.knownMana = cmp.func_74762_e(TAG_KNOWN_MANA);
        }
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return;
        }
        if (player.func_70093_af()) {
            this.outputting = !this.outputting;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (!this.field_145850_b.field_72995_K) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            this.writeCustomNBT(nbttagcompound);
            nbttagcompound.func_74768_a(TAG_KNOWN_MANA, this.getCurrentMana());
            if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP)player).field_71135_a.func_147359_a((Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound));
            }
        }
        this.field_145850_b.func_72956_a((Entity)player, "botania:ding", 0.11f, 1.0f);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        ItemStack pool = new ItemStack(ModBlocks.pool, 1, this.func_145832_p());
        String name = StatCollector.func_74838_a((String)(pool.func_77977_a().replaceAll("tile.", "tile.botania:") + ".name"));
        int color = 0x4444FF;
        HUDHandler.drawSimpleManaHUD(color, this.knownMana, this.manaCap, name, res);
        int x = res.func_78326_a() / 2 - 11;
        int y = res.func_78328_b() / 2 + 30;
        int u = this.outputting ? 22 : 0;
        int v = 38;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.field_71446_o.func_110577_a(HUDHandler.manaBar);
        RenderHelper.drawTexturedModalRect(x, y, 0.0f, u, v, 22, 15);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack tablet = new ItemStack(ModItems.manaTablet);
        ItemManaTablet.setStackCreative(tablet);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, tablet, x - 20, y);
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, pool, x + 26, y);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Override
    public boolean isOutputtingPower() {
        return this.outputting;
    }

    @Override
    public int getCurrentMana() {
        return this.field_145850_b != null && this.func_145832_p() == 1 ? 1000000 : this.mana;
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
    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    @Override
    public void attachSpark(ISparkEntity entity) {
    }

    @Override
    public ISparkEntity getAttachedSpark() {
        List sparks = this.field_145850_b.func_72872_a(ISparkEntity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1)));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    @Override
    public boolean areIncomingTranfersDone() {
        return false;
    }

    @Override
    public int getAvailableSpaceForMana() {
        return Math.max(0, this.manaCap - this.getCurrentMana());
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public void markDispatchable() {
        this.sendPacket = true;
    }
}

