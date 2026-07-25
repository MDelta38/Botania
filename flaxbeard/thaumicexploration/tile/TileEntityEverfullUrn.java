/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 *  net.minecraftforge.fluids.IFluidTank
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.tiles.TileCrucible
 *  thaumcraft.common.tiles.TileSpa
 */
package flaxbeard.thaumicexploration.tile;

import cpw.mods.fml.common.Loader;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.integration.BotaniaIntegration;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileSpa;

public class TileEntityEverfullUrn
extends TileEntity
implements IFluidTank,
IFluidHandler {
    private int ticks = 0;
    private int drainTicks = 0;
    private int dX;
    private int dY;
    private int dZ;
    private int excessTicks = 0;
    private int drainType = 0;
    private float distance = 0.0f;
    private int range = 3;
    private int yRange = 2;
    private EntityPlayer burningPlayer;

    public FluidStack getFluid() {
        return new FluidStack(FluidRegistry.WATER, 100);
    }

    public int getFluidAmount() {
        return 9999;
    }

    public int getCapacity() {
        return 9999;
    }

    public FluidTankInfo getInfo() {
        return new FluidTankInfo((IFluidTank)this);
    }

    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    public FluidStack drain(int maxDrain, boolean doDrain) {
        int drained = maxDrain;
        if (999999 < drained) {
            drained = 999999;
        }
        FluidStack stack = new FluidStack(FluidRegistry.WATER, drained);
        return stack;
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return this.fill(resource, doFill);
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (!resource.isFluidEqual(new FluidStack(FluidRegistry.WATER, 1)) || from != ForgeDirection.UP) {
            return null;
        }
        return this.drain(resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (from == ForgeDirection.UP) {
            return this.drain(maxDrain, doDrain);
        }
        FluidStack stack = new FluidStack(FluidRegistry.WATER, 0);
        return stack;
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from == ForgeDirection.UP;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.getInfo()};
    }

    public void func_145845_h() {
        super.func_145845_h();
        ++this.ticks;
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o() == Material.field_151579_a || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o() == Config.airyMaterial) {
            TileEntity tile;
            if (this.drainTicks > 0 && this.drainType == 1) {
                if (this.field_145850_b.func_147439_a(this.dX, this.dY, this.dZ) == ConfigBlocks.blockMetalDevice) {
                    if (this.field_145850_b.func_72805_g(this.dX, this.dY, this.dZ) == 0) {
                        tile = (TileCrucible)this.field_145850_b.func_147438_o(this.dX, this.dY, this.dZ);
                        if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
                            this.drainTicks = (tile.tank.getCapacity() - tile.tank.getFluidAmount()) / 10;
                            if ((float)this.excessTicks > 20.0f * this.distance) {
                                tile.fill(ForgeDirection.SOUTH, new FluidStack(FluidRegistry.WATER, 10), true);
                            }
                            if (this.drainTicks % 5 == 0 && this.field_145850_b.field_72995_K && (float)this.excessTicks < 40.0f * this.distance) {
                                ThaumicExploration.proxy.spawnWaterAtLocation(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.1f, (float)this.field_145849_e + 0.5f, (float)this.dX + 0.5f, (float)this.dY + 1.1f, (float)this.dZ + 0.5f);
                            }
                            ++this.excessTicks;
                            --this.drainTicks;
                        } else {
                            this.drainTicks = 0;
                        }
                    } else {
                        this.drainTicks = 0;
                    }
                } else {
                    this.drainTicks = 0;
                }
            }
            if (this.drainTicks > 0 && this.drainType == 4) {
                if (this.field_145850_b.func_147439_a(this.dX, this.dY, this.dZ) == ConfigBlocks.blockStoneDevice) {
                    if (this.field_145850_b.func_72805_g(this.dX, this.dY, this.dZ) == 12) {
                        tile = (TileSpa)this.field_145850_b.func_147438_o(this.dX, this.dY, this.dZ);
                        if (tile.tank.getFluidAmount() < tile.tank.getCapacity()) {
                            this.drainTicks = (tile.tank.getCapacity() - tile.tank.getFluidAmount()) / 10;
                            if ((float)this.excessTicks > 20.0f * this.distance) {
                                tile.fill(ForgeDirection.SOUTH, new FluidStack(FluidRegistry.WATER, 10), true);
                            }
                            if (this.drainTicks % 5 == 0 && this.field_145850_b.field_72995_K && (float)this.excessTicks < 40.0f * this.distance) {
                                ThaumicExploration.proxy.spawnWaterAtLocation(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.1f, (float)this.field_145849_e + 0.5f, (float)this.dX + 0.5f, (float)this.dY + 1.1f, (float)this.dZ + 0.5f);
                            }
                            ++this.excessTicks;
                            --this.drainTicks;
                        } else {
                            this.drainTicks = 0;
                        }
                    } else {
                        this.drainTicks = 0;
                    }
                } else {
                    this.drainTicks = 0;
                }
            }
            if (this.drainTicks > 0 && this.drainType == 3) {
                if (Loader.isModLoaded((String)"Botania")) {
                    if (this.field_145850_b.func_147439_a(this.dX, this.dY, this.dZ) == BotaniaIntegration.getAltar()) {
                        tile = this.field_145850_b.func_147438_o(this.dX, this.dY, this.dZ);
                        if (BotaniaIntegration.needsWater(tile)) {
                            if (this.drainTicks % 5 == 0 && this.field_145850_b.field_72995_K && (float)this.excessTicks < 40.0f * this.distance) {
                                ThaumicExploration.proxy.spawnWaterAtLocation(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.1f, (float)this.field_145849_e + 0.5f, (float)this.dX + 0.5f, (float)this.dY + 1.1f, (float)this.dZ + 0.5f);
                            }
                            ++this.excessTicks;
                            --this.drainTicks;
                            if (this.drainTicks == 0) {
                                BotaniaIntegration.fillWater(tile);
                            }
                        } else {
                            this.drainTicks = 0;
                        }
                    } else {
                        this.drainTicks = 0;
                    }
                } else {
                    this.drainTicks = 0;
                }
            }
            if (this.drainTicks > 0 && this.drainType == 2) {
                EntityPlayer player = this.burningPlayer;
                List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - this.range), (double)(this.field_145848_d - this.yRange), (double)(this.field_145849_e - this.range), (double)(this.field_145851_c + this.range), (double)(this.field_145848_d + this.yRange), (double)(this.field_145849_e + this.range)));
                if (players.contains(player) && player.func_70027_ad()) {
                    if (this.drainTicks % 3 == 0 && this.field_145850_b.field_72995_K && (float)this.excessTicks < 40.0f * this.distance) {
                        ThaumicExploration.proxy.spawnWaterOnPlayer(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, player);
                    }
                    ++this.excessTicks;
                    --this.drainTicks;
                    if ((float)this.excessTicks > 15.0f * this.distance) {
                        player.func_70066_B();
                        this.drainTicks = 0;
                        this.field_145850_b.func_72956_a((Entity)player, "liquid.swim", 2.0f, 1.0f);
                        for (int x = -1; x < 2; ++x) {
                            for (int z = -1; z < 2; ++z) {
                                if (this.field_145850_b.func_147439_a((int)player.field_70165_t + x, (int)player.field_70163_u, (int)player.field_70161_v + z) != Blocks.field_150480_ab) continue;
                                this.field_145850_b.func_147468_f((int)player.field_70165_t + x, (int)player.field_70163_u, (int)player.field_70161_v + z);
                            }
                        }
                    }
                } else {
                    this.drainTicks = 0;
                }
            }
            if (this.ticks % 2 == 0 && this.field_145850_b.field_72995_K && (this.drainTicks <= 0 || (float)this.excessTicks > 40.0f * this.distance)) {
                ThaumicExploration.proxy.spawnRandomWaterFountain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.ticks % 5 == 0) {
                if (this.drainTicks == 0 || this.drainType != 2) {
                    List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - this.range), (double)(this.field_145848_d - this.yRange), (double)(this.field_145849_e - this.range), (double)(this.field_145851_c + this.range), (double)(this.field_145848_d + this.yRange), (double)(this.field_145849_e + this.range)));
                    for (EntityPlayer player : players) {
                        if (!player.func_70027_ad()) continue;
                        this.distance = (float)Math.sqrt(Math.pow((double)this.field_145851_c - player.field_70165_t, 2.0) + Math.pow((double)this.field_145848_d - player.field_70163_u, 2.0) + Math.pow((double)this.field_145849_e - player.field_70161_v, 2.0));
                        this.drainTicks = 100;
                        this.excessTicks = 0;
                        this.drainType = 2;
                        this.burningPlayer = player;
                        break;
                    }
                }
                if (this.drainTicks == 0) {
                    for (int x = -1 * this.range; x < this.range + 1; ++x) {
                        block4: for (int z = -1 * this.range; z < this.range + 1; ++z) {
                            for (int y = -1 * this.yRange; y < this.yRange + 1; ++y) {
                                TileCrucible tile2;
                                if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockMetalDevice && this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == 0) {
                                    tile2 = (TileCrucible)this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                                    if (tile2.tank.getFluidAmount() < tile2.tank.getCapacity()) {
                                        this.distance = (float)Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0) + Math.pow(z, 2.0));
                                        this.drainTicks = (tile2.tank.getCapacity() - tile2.tank.getFluidAmount()) / 10;
                                        this.excessTicks = 0;
                                        this.drainType = 1;
                                        this.dX = this.field_145851_c + x;
                                        this.dY = this.field_145848_d + y;
                                        this.dZ = this.field_145849_e + z;
                                        continue block4;
                                    }
                                }
                                if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockStoneDevice && this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == 12) {
                                    int capacity;
                                    tile2 = (TileSpa)this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                                    int amount = tile2.tank.getFluidAmount();
                                    if (amount < (capacity = tile2.tank.getCapacity())) {
                                        this.distance = (float)Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0) + Math.pow(z, 2.0));
                                        this.drainTicks = (tile2.tank.getCapacity() - tile2.tank.getFluidAmount()) / 10;
                                        this.excessTicks = 0;
                                        this.drainType = 4;
                                        this.dX = this.field_145851_c + x;
                                        this.dY = this.field_145848_d + y;
                                        this.dZ = this.field_145849_e + z;
                                        continue block4;
                                    }
                                }
                                if (!Loader.isModLoaded((String)"Botania") || this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) != BotaniaIntegration.getAltar() || !BotaniaIntegration.needsWater((TileEntity)(tile2 = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z)))) continue;
                                this.distance = (float)Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0) + Math.pow(z, 2.0));
                                this.drainTicks = 100;
                                this.excessTicks = 0;
                                this.drainType = 3;
                                this.dX = this.field_145851_c + x;
                                this.dY = this.field_145848_d + y;
                                this.dZ = this.field_145849_e + z;
                                continue block4;
                            }
                        }
                    }
                }
            }
        }
    }
}

