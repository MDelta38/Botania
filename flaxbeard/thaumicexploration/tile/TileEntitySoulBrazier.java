/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 *  net.minecraftforge.common.ForgeChunkManager$Type
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.blocks.BlockTaintFibres
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.lib.utils.Utils
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 *  thaumcraft.common.tiles.TileVisRelay
 */
package flaxbeard.thaumicexploration.tile;

import com.mojang.authlib.GameProfile;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.chunkLoader.ITXChunkLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.tiles.TileVisRelay;

public class TileEntitySoulBrazier
extends TileVisRelay
implements IEssentiaTransport,
ITXChunkLoader {
    public int storedWarp;
    public int currentEssentia;
    public int currentVis;
    public boolean active;
    public GameProfile owner;
    public int count;
    private int ticks = 0;
    private static int EssentiaCapacity = 16;
    private static int VisCapacity = 16;
    public static int EssentiaRate = 1;
    public static int VisRate = 3;
    public ForgeChunkManager.Ticket heldChunk;
    public static boolean renderWisp = false;

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.storedWarp = nbttagcompound.func_74762_e("storedWarp");
        this.currentEssentia = nbttagcompound.func_74762_e("currentEssentia");
        this.currentVis = nbttagcompound.func_74762_e("currentVis");
        this.active = nbttagcompound.func_74767_n("active");
        this.owner = NBTUtil.func_152459_a((NBTTagCompound)nbttagcompound.func_74775_l("owner"));
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("storedWarp", this.storedWarp);
        nbttagcompound.func_74768_a("currentEssentia", this.currentEssentia);
        nbttagcompound.func_74768_a("currentVis", this.currentVis);
        nbttagcompound.func_74757_a("active", this.active);
        NBTTagCompound gameProfile = new NBTTagCompound();
        NBTUtil.func_152460_a((NBTTagCompound)gameProfile, (GameProfile)this.owner);
        nbttagcompound.func_74782_a("owner", (NBTBase)gameProfile);
    }

    public boolean setActive(EntityPlayer player) {
        if (!this.field_145850_b.field_72995_K) {
            int playerWarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(this.owner.getName());
            if (playerWarp <= 0) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("soulbrazier.noWarp", new Object[0]));
                return false;
            }
            if (!EntityPlayer.func_146094_a((GameProfile)player.func_146103_bH()).equals(this.owner.getId())) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("soulbrazier.invalidplayer", new Object[0]));
                return false;
            }
            if (!this.checkPower()) {
                player.func_146105_b((IChatComponent)new ChatComponentTranslation("soulbrazier.nopower", new Object[0]));
                return false;
            }
            this.active = true;
            this.storedWarp += playerWarp;
            Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(this.owner.getName(), 0);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        ++this.count;
        if (!renderWisp && ThaumicExploration.proxy.getIsReadyForWisp()) {
            renderWisp = true;
        }
        if (this.count % 10 == 0 && renderWisp && this.active) {
            ThaumicExploration.proxy.spawnActiveBrazierParticle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.count % 5 == 0) {
            if (this.currentEssentia < EssentiaCapacity) {
                this.fillJar();
            }
        }
        this.changeTaint();
        this.getPower();
        if (this.active) {
            if (this.heldChunk == null) {
                this.addTicket();
            }
            if (this.count % 60 == 0) {
                this.spendPower();
            }
            if (!this.checkPower()) {
                this.active = false;
                if (!this.field_145850_b.field_72995_K) {
                    int temp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(this.owner.getName()) + this.storedWarp;
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(this.owner.getName(), temp);
                }
                this.storedWarp = 0;
                ForgeChunkManager.unforceChunk((ForgeChunkManager.Ticket)this.heldChunk, (ChunkCoordIntPair)new ChunkCoordIntPair(this.field_145851_c >> 4, this.field_145849_e >> 4));
                this.heldChunk = null;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    void fillJar() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (ForgeDirection)ForgeDirection.DOWN);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(ForgeDirection.UP)) {
                return;
            }
            Aspect ta = Aspect.DEATH;
            if (ic.getEssentiaAmount(ForgeDirection.UP) > 0 && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.UP) && this.getSuctionAmount(ForgeDirection.UP) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(ForgeDirection.UP);
            }
            if (ta != null && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN)) {
                this.addEssentia(ta, ic.takeEssentia(ta, 1, ForgeDirection.UP), ForgeDirection.DOWN);
            }
        }
    }

    public boolean checkPower() {
        return this.currentEssentia > 0 && this.currentVis > 0;
    }

    private void spendPower() {
        this.currentEssentia = Math.max(0, this.currentEssentia - EssentiaRate);
        this.currentVis = Math.max(0, this.currentVis - VisRate);
    }

    private void getPower() {
        if (this.count % 5 == 0 && this.currentVis < VisCapacity) {
            this.currentVis += this.consumeVis(Aspect.FIRE, 1);
        }
    }

    public void changeTaint() {
        if (this.active && this.count % 50 == 0) {
            int x = 0;
            int z = 0;
            int y = 0;
            x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            BiomeGenBase bg = this.field_145850_b.func_72807_a(x, z);
            if (bg.field_76756_M != ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
                float offsetY = (float)(Math.sin(Math.toRadians((float)this.count * 1.0f)) / 4.0);
                float offsetZ = (float)(Math.sin(Math.toRadians((float)this.count * 3.0f)) / 4.0);
                float offsetX = (float)(Math.cos(Math.toRadians((float)this.count * 3.0f)) / 4.0);
                boolean found = false;
                for (int yTest = this.field_145848_d - 5; yTest <= this.field_145848_d + 5; ++yTest) {
                    if (this.field_145850_b.func_147439_a(x, yTest, z) == Blocks.field_150350_a || this.field_145850_b.func_147439_a(x, yTest, z) != Blocks.field_150350_a) continue;
                    found = true;
                    y = yTest;
                    break;
                }
                ThaumicExploration.proxy.spawnLightningBolt(this.field_145850_b, (float)this.field_145851_c + 0.5f + offsetX, (float)this.field_145848_d + 1.5f + offsetY, (float)this.field_145849_e + offsetZ + 0.5f, x, found ? (double)y : (double)(this.field_145848_d - 1), z);
                Utils.setBiomeAt((World)this.field_145850_b, (int)x, (int)z, (BiomeGenBase)ThaumcraftWorldGenerator.biomeTaint);
            }
            if (Config.hardNode && this.field_145850_b.field_73012_v.nextBoolean()) {
                x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                y = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                if (!BlockTaintFibres.spreadFibres((World)this.field_145850_b, (int)x, (int)y, (int)z)) {
                    // empty if block
                }
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean isConnectable(ForgeDirection forgeDirection) {
        return forgeDirection == ForgeDirection.DOWN;
    }

    public boolean canInputFrom(ForgeDirection forgeDirection) {
        return forgeDirection == ForgeDirection.DOWN;
    }

    public boolean canOutputTo(ForgeDirection forgeDirection) {
        return false;
    }

    public void setSuction(Aspect aspect, int i) {
    }

    public Aspect getSuctionType(ForgeDirection forgeDirection) {
        return forgeDirection == ForgeDirection.DOWN ? Aspect.DEATH : null;
    }

    public int getSuctionAmount(ForgeDirection forgeDirection) {
        return forgeDirection == ForgeDirection.DOWN ? 128 : 0;
    }

    public int takeEssentia(Aspect aspect, int i, ForgeDirection forgeDirection) {
        return 0;
    }

    public int addEssentia(Aspect aspect, int i, ForgeDirection forgeDirection) {
        boolean newEssentia = false;
        int filled = EssentiaCapacity - this.currentEssentia;
        if (i < filled) {
            this.currentEssentia += i;
            filled = i;
        } else {
            this.currentEssentia = EssentiaCapacity;
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return filled;
    }

    public Aspect getEssentiaType(ForgeDirection forgeDirection) {
        return forgeDirection == ForgeDirection.DOWN ? Aspect.DEATH : null;
    }

    public int getEssentiaAmount(ForgeDirection forgeDirection) {
        return 0;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public void forceChunkLoading(ForgeChunkManager.Ticket ticket) {
        this.heldChunk = ticket;
        ForgeChunkManager.forceChunk((ForgeChunkManager.Ticket)this.heldChunk, (ChunkCoordIntPair)new ChunkCoordIntPair(this.field_145851_c >> 4, this.field_145849_e >> 4));
    }

    @Override
    public void addTicket() {
        ForgeChunkManager.Ticket newTicket = ForgeChunkManager.requestTicket((Object)ThaumicExploration.instance, (World)this.field_145850_b, (ForgeChunkManager.Type)ForgeChunkManager.Type.NORMAL);
        newTicket.getModData().func_74768_a("xCoord", this.field_145851_c);
        newTicket.getModData().func_74768_a("yCoord", this.field_145848_d);
        newTicket.getModData().func_74768_a("zCoord", this.field_145849_e);
        newTicket.getModData().func_74757_a("warpChunk", true);
        this.heldChunk = newTicket;
        ForgeChunkManager.forceChunk((ForgeChunkManager.Ticket)this.heldChunk, (ChunkCoordIntPair)new ChunkCoordIntPair(this.field_145851_c >> 4, this.field_145849_e >> 4));
    }

    @Override
    public void removeTicket(ForgeChunkManager.Ticket ticket) {
        if (this.heldChunk != null) {
            ForgeChunkManager.releaseTicket((ForgeChunkManager.Ticket)this.heldChunk);
            this.heldChunk = null;
        }
    }
}

