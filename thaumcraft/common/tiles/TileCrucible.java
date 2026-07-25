/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTank
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.container.InventoryFake;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class TileCrucible
extends TileThaumcraft
implements IFluidHandler,
IWandable,
IAspectContainer {
    public short heat = 0;
    public AspectList aspects = new AspectList();
    public final int maxTags = 100;
    int bellows = -1;
    private int delay = 0;
    public FluidTank tank = new FluidTank(FluidRegistry.WATER, 0, 1000);
    private long counter = -100L;
    int prevcolor = 0;
    int prevx = 0;
    int prevy = 0;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.heat = nbttagcompound.func_74765_d("Heat");
        this.tank.readFromNBT(nbttagcompound);
        if (nbttagcompound.func_74764_b("Empty")) {
            this.tank.setFluid(null);
        }
        this.aspects.readFromNBT(nbttagcompound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74777_a("Heat", this.heat);
        this.tank.writeToNBT(nbttagcompound);
        this.aspects.writeToNBT(nbttagcompound);
    }

    public void func_145845_h() {
        ++this.counter;
        short prevheat = this.heat;
        if (!this.field_145850_b.field_72995_K) {
            if (this.bellows < 0) {
                this.getBellows();
            }
            if (this.tank.getFluidAmount() > 0) {
                Material mat = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e).func_149688_o();
                Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                if (mat == Material.field_151587_i || mat == Material.field_151581_o || bi == ConfigBlocks.blockAiry && md == 1) {
                    if (this.heat < 200) {
                        this.heat = (short)(this.heat + (1 + this.bellows * 2));
                        if (prevheat < 151 && this.heat >= 151) {
                            this.func_70296_d();
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        }
                    }
                } else if (this.heat > 0) {
                    this.heat = (short)(this.heat - 1);
                    if (this.heat == 149) {
                        this.func_70296_d();
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                }
            } else if (this.heat > 0) {
                this.heat = (short)(this.heat - 1);
            }
            if (this.tagAmount() > 100 && this.counter % 5L == 0L) {
                AspectList tt = this.takeRandomFromSource();
                this.spill();
            }
            if (this.counter > 100L && this.heat > 150) {
                this.counter = 0L;
                if (this.tagAmount() > 0) {
                    int s = this.aspects.getAspects().length;
                    Aspect a = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(s)];
                    if (a.isPrimal()) {
                        a = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(s)];
                    }
                    this.tank.drain(2, true);
                    this.aspects.remove(a, 1);
                    if (!a.isPrimal()) {
                        if (this.field_145850_b.field_73012_v.nextBoolean()) {
                            this.aspects.add(a.getComponents()[0], 1);
                        } else {
                            this.aspects.add(a.getComponents()[1], 1);
                        }
                    } else {
                        this.spill();
                    }
                }
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        } else if (this.tank.getFluidAmount() > 0) {
            this.drawEffects();
        }
        if (this.field_145850_b.field_72995_K && prevheat < 151 && this.heat >= 151) {
            this.heat = (short)(this.heat + 1);
        }
    }

    private void drawEffects() {
        if (this.heat > 150) {
            Thaumcraft.proxy.crucibleFroth(this.field_145850_b, (float)this.field_145851_c + 0.2f + this.field_145850_b.field_73012_v.nextFloat() * 0.6f, (float)this.field_145848_d + this.getFluidHeight(), (float)this.field_145849_e + 0.2f + this.field_145850_b.field_73012_v.nextFloat() * 0.6f);
            if (this.tagAmount() > 100) {
                for (int a = 0; a < 2; ++a) {
                    Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
                    Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d + 1, (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat());
                    Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, (float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e);
                    Thaumcraft.proxy.crucibleFrothDown(this.field_145850_b, (float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat(), this.field_145848_d + 1, this.field_145849_e + 1);
                }
            }
        }
        if (this.field_145850_b.field_73012_v.nextInt(6) == 0 && this.aspects.size() > 0) {
            int color = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.size())].getColor() + -16777216;
            int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
            int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
            this.delay = this.field_145850_b.field_73012_v.nextInt(10);
            this.prevcolor = color;
            this.prevx = x;
            this.prevy = y;
            Color c = new Color(color);
            float r = (float)c.getRed() / 255.0f;
            float g = (float)c.getGreen() / 255.0f;
            float b = (float)c.getBlue() / 255.0f;
            Thaumcraft.proxy.crucibleBubble(this.field_145850_b, (float)this.field_145851_c + (float)x / 32.0f + 0.015625f, (float)this.field_145848_d + 0.05f + this.getFluidHeight(), (float)this.field_145849_e + (float)y / 32.0f + 0.015625f, r, g, b);
        }
    }

    public void spill() {
        if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
            if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
                if (this.field_145850_b.field_73012_v.nextBoolean()) {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, 0, 3);
                } else {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, 0, 3);
                }
            } else {
                Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
                if (bi == ConfigBlocks.blockFluxGoo && md < 7) {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, md + 1, 3);
                } else if (bi == ConfigBlocks.blockFluxGas && md < 7) {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, md + 1, 3);
                } else {
                    int z;
                    int y;
                    int x = -1 + this.field_145850_b.field_73012_v.nextInt(3);
                    if (this.field_145850_b.func_147437_c(this.field_145851_c + x, this.field_145848_d + (y = -1 + this.field_145850_b.field_73012_v.nextInt(3)), this.field_145849_e + (z = -1 + this.field_145850_b.field_73012_v.nextInt(3)))) {
                        if (this.field_145850_b.field_73012_v.nextBoolean()) {
                            this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGas, 0, 3);
                        } else {
                            this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGoo, 0, 3);
                        }
                    }
                }
            }
        }
    }

    public void spillRemnants() {
        if (this.tank.getFluidAmount() > 0 || this.aspects.visSize() > 0) {
            this.tank.setFluid(null);
            for (int a = 0; a < this.aspects.visSize() / 2; ++a) {
                this.spill();
            }
            this.aspects = new AspectList();
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 5);
        }
    }

    public void ejectItem(ItemStack items) {
        boolean stacks = true;
        boolean first = true;
        do {
            ItemStack spitout;
            if (spitout.field_77994_a > (spitout = items.func_77946_l()).func_77976_d()) {
                spitout.field_77994_a = spitout.func_77976_d();
            }
            items.field_77994_a -= spitout.field_77994_a;
            EntitySpecialItem entityitem = new EntitySpecialItem(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.71f, (float)this.field_145849_e + 0.5f, spitout);
            entityitem.field_70181_x = 0.1f;
            entityitem.field_70159_w = first ? 0.0 : (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01f);
            entityitem.field_70179_y = first ? 0.0 : (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.01f);
            this.field_145850_b.func_72838_d((Entity)entityitem);
            first = false;
        } while (items.field_77994_a > 0);
    }

    public void attemptSmelt(EntityItem entity) {
        boolean bubble = false;
        boolean event = false;
        ItemStack item = entity.func_92059_d();
        NBTTagCompound itemData = entity.getEntityData();
        String username = itemData.func_74779_i("thrower");
        int stacksize = item.field_77994_a;
        for (int a = 0; a < stacksize; ++a) {
            CrucibleRecipe rc = ThaumcraftCraftingManager.findMatchingCrucibleRecipe(username, this.aspects, item);
            if (rc != null && this.tank.getFluidAmount() > 0) {
                ItemStack out = rc.getRecipeOutput().func_77946_l();
                EntityPlayer p = this.field_145850_b.func_72924_a(username);
                if (p != null) {
                    FMLCommonHandler.instance().firePlayerCraftingEvent(p, out, (IInventory)new InventoryFake(new ItemStack[]{item}));
                }
                this.aspects = rc.removeMatching(this.aspects);
                this.tank.drain(50, true);
                this.ejectItem(out);
                event = true;
                --stacksize;
                this.counter = -250L;
                continue;
            }
            AspectList ot = ThaumcraftCraftingManager.getObjectTags(item);
            if ((ot = ThaumcraftCraftingManager.getBonusTags(item, ot)) == null || ot.size() == 0) {
                entity.field_70181_x = 0.35f;
                entity.field_70159_w = (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2f;
                entity.field_70179_y = (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2f;
                this.field_145850_b.func_72956_a((Entity)entity, "random.pop", 0.2f, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.7f + 1.0f);
                return;
            }
            for (Aspect tag : ot.getAspects()) {
                this.aspects.add(tag, ot.getAmount(tag));
            }
            bubble = true;
            --stacksize;
            this.counter = -150L;
        }
        if (bubble) {
            this.field_145850_b.func_72956_a((Entity)entity, "thaumcraft:bubble", 0.2f, 1.0f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 1);
        }
        if (event) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMetalDevice, 2, 5);
        }
        if (stacksize <= 0) {
            entity.func_70106_y();
        } else {
            item.field_77994_a = stacksize;
            entity.func_92058_a(item);
        }
        this.func_70296_d();
    }

    public int tagAmount() {
        int tt = 0;
        if (this.aspects.size() > 0) {
            for (Aspect tag : this.aspects.getAspects()) {
                tt += this.aspects.getAmount(tag);
            }
            return tt;
        }
        return 0;
    }

    public float getFluidHeight() {
        float base = 0.3f + 0.5f * ((float)this.tank.getFluidAmount() / (float)this.tank.getCapacity());
        float out = base + (float)this.tagAmount() / 100.0f * (1.0f - base);
        if (out > 1.0f) {
            out = 1.001f;
        }
        if (out == 1.0f) {
            out = 0.9999f;
        }
        return out;
    }

    public AspectList takeRandomFromSource() {
        AspectList output = new AspectList();
        if (this.aspects.size() > 0) {
            Aspect tag = this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.getAspects().length)];
            output.add(tag, 1);
            this.aspects.remove(tag, 1);
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return output;
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 1) {
            if (this.field_145850_b.field_72995_K) {
                Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, -9999, 5);
            }
            return true;
        }
        if (i == 2) {
            Thaumcraft.proxy.crucibleBoilSound(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (this.field_145850_b.field_72995_K) {
                for (int q = 0; q < 10; ++q) {
                    int x = 5 + this.field_145850_b.field_73012_v.nextInt(22);
                    int y = 5 + this.field_145850_b.field_73012_v.nextInt(22);
                    Thaumcraft.proxy.crucibleBoil(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this, j);
                }
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    public void getBellows() {
        this.bellows = 0;
        for (int a = 2; a < 6; ++a) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)a);
            int xx = this.field_145851_c + dir.offsetX;
            int zz = this.field_145849_e + dir.offsetZ;
            Block bi = this.field_145850_b.func_147439_a(xx, this.field_145848_d, zz);
            int md = this.field_145850_b.func_72805_g(xx, this.field_145848_d, zz);
            if (bi != ConfigBlocks.blockWoodenDevice || md != 0) continue;
            ++this.bellows;
        }
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource != null && resource.fluidID != FluidRegistry.WATER.getID()) {
            return 0;
        }
        if (doFill) {
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return this.tank.fill(resource, doFill);
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(this.tank.getFluid())) {
            return null;
        }
        if (doDrain) {
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return this.tank.drain(resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.tank.drain(maxDrain, doDrain);
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid != null && fluid.getID() == FluidRegistry.WATER.getID();
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.tank.getInfo()};
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        if (!world.field_72995_K && player.func_70093_af()) {
            this.spillRemnants();
        }
        return wandstack;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @Override
    public AspectList getAspects() {
        return this.aspects;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
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
}

