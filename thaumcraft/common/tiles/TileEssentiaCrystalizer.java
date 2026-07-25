/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.lib.utils.InventoryUtils;

public class TileEssentiaCrystalizer
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport {
    public Aspect aspect = null;
    public ForgeDirection facing = ForgeDirection.DOWN;
    int count = 0;
    int progress = 0;
    final int progMax = 200;
    public float spin = 0.0f;
    public float spinInc = 0.0f;
    float tr = 1.0f;
    float tg = 1.0f;
    float tb = 1.0f;
    public float cr = 1.0f;
    public float cg = 1.0f;
    public float cb = 1.0f;
    int venting = 0;

    public boolean canUpdate() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74771_c("face"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
        }
        nbttagcompound.func_74774_a("face", (byte)this.facing.ordinal());
    }

    @Override
    public AspectList getAspects() {
        AspectList al = new AspectList();
        if (this.aspect != null) {
            al.add(this.aspect, 1);
        }
        return al;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (am == 0) {
            return am;
        }
        if (this.aspect == null) {
            --am;
            this.aspect = tt;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.aspect != null && am == 1) {
            this.aspect = null;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amt) {
        return amt == 1 && this.aspect != null && tag == this.aspect;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (this.aspect != null && this.aspect == tt && ot.getAmount(tt) == 1) continue;
            return false;
        }
        return true;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.aspect != null && tag == this.aspect ? 1 : 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return this.gettingPower() ? 0 : (loc == this.facing && this.aspect == null ? 128 : 64);
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspect;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.aspect == null ? 0 : 1;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            if (++this.count % 5 == 0 && !this.gettingPower()) {
                if (this.aspect == null) {
                    this.fillReservoir();
                    this.progress = 0;
                } else {
                    this.progress += 1 + VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.EARTH, Math.min(20, Math.max(1, (200 - this.progress) / 2))) * 2;
                }
            }
            if (this.aspect != null && this.progress >= 200) {
                this.eject();
                this.aspect = null;
                this.progress = 0;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
        } else {
            if (this.aspect == null) {
                this.tb = 1.0f;
                this.tg = 1.0f;
                this.tr = 1.0f;
            } else {
                Color c = new Color(this.aspect.getColor());
                this.tr = (float)c.getRed() / 220.0f;
                this.tg = (float)c.getGreen() / 220.0f;
                this.tb = (float)c.getBlue() / 220.0f;
            }
            if (this.cr < this.tr) {
                this.cr += 0.05f;
            }
            if (this.cr > this.tr) {
                this.cr -= 0.05f;
            }
            if (this.cg < this.tg) {
                this.cg += 0.05f;
            }
            if (this.cg > this.tg) {
                this.cg -= 0.05f;
            }
            if (this.cb < this.tb) {
                this.cb += 0.05f;
            }
            if (this.cb > this.tb) {
                this.cb -= 0.05f;
            }
            this.spin += this.spinInc;
            if (this.spin > 360.0f) {
                this.spin -= 360.0f;
            }
            if (this.aspect != null && this.spinInc < 20.0f && !this.gettingPower()) {
                this.spinInc += 0.1f;
                if (this.spinInc > 20.0f) {
                    this.spinInc = 20.0f;
                }
            } else if ((this.aspect == null || this.gettingPower()) && this.spinInc > 0.0f) {
                this.spinInc -= 0.2f;
                if (this.spinInc < 0.0f) {
                    this.spinInc = 0.0f;
                }
            }
            if (this.venting > 0) {
                --this.venting;
                float fx = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                float fz = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                float fy = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                float fx2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                float fz2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                float fy2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
                int color = 0xFFFFFF;
                Thaumcraft.proxy.drawVentParticles(this.field_145850_b, (float)this.field_145851_c + 0.5f + fx + (float)this.facing.getOpposite().offsetX / 2.1f, (float)this.field_145848_d + 0.5f + fy + (float)this.facing.getOpposite().offsetY / 2.1f, (float)this.field_145849_e + 0.5f + fz + (float)this.facing.getOpposite().offsetZ / 2.1f, (float)this.facing.getOpposite().offsetX / 4.0f + fx2, (float)this.facing.getOpposite().offsetY / 4.0f + fy2, (float)this.facing.getOpposite().offsetZ / 4.0f + fz2, color);
            }
        }
    }

    public boolean func_145842_c(int i, int j) {
        if (i >= 0) {
            if (this.field_145850_b.field_72995_K) {
                this.venting = 7;
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    public void eject() {
        ItemStack stack = new ItemStack(ConfigItems.itemCrystalEssence, 1, 0);
        ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(this.aspect, 1));
        TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.getOpposite().offsetX, this.field_145848_d + this.facing.getOpposite().offsetY, this.field_145849_e + this.facing.getOpposite().offsetZ);
        if (inventory != null && inventory instanceof IInventory) {
            stack = InventoryUtils.placeItemStackIntoInventory(stack, (IInventory)inventory, this.facing.ordinal(), true);
        }
        if (stack != null) {
            this.spawnItem(stack);
        }
        this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "random.fizz", 0.25f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f);
    }

    public boolean spawnItem(ItemStack stack) {
        EntityItem ie2 = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 + (double)this.facing.getOpposite().offsetX * 0.65, (double)this.field_145848_d + 0.5 + (double)this.facing.getOpposite().offsetY * 0.65, (double)this.field_145849_e + 0.5 + (double)this.facing.getOpposite().offsetZ * 0.65, stack);
        ie2.field_70159_w = (float)this.facing.getOpposite().offsetX * 0.04f;
        ie2.field_70181_x = (float)this.facing.getOpposite().offsetY * 0.04f;
        ie2.field_70179_y = (float)this.facing.getOpposite().offsetZ * 0.04f;
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 0, 0);
        return this.field_145850_b.func_72838_d((Entity)ie2);
    }

    void fillReservoir() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(this.facing.getOpposite())) {
                return;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(this.facing.getOpposite()) > 0 && ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing) && this.getSuctionAmount(this.facing) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(this.facing.getOpposite());
            }
            if (ta != null && ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing)) {
                this.addToContainer(ta, ic.takeEssentia(ta, 1, this.facing.getOpposite()));
            }
        }
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

