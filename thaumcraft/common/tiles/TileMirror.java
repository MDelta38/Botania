/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

public class TileMirror
extends TileThaumcraft
implements IInventory {
    public boolean linked = false;
    public int linkX;
    public int linkY;
    public int linkZ;
    public int linkDim;
    public int instability;
    int count = 0;
    int inc = 40;
    private ArrayList<ItemStack> outputStacks = new ArrayList();

    public boolean canUpdate() {
        return true;
    }

    public void restoreLink() {
        if (this.isDestinationValid()) {
            WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
            if (targetWorld == null) {
                return;
            }
            TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
            if (te != null && te instanceof TileMirror) {
                TileMirror tm = (TileMirror)te;
                tm.linked = true;
                tm.linkX = this.field_145851_c;
                tm.linkY = this.field_145848_d;
                tm.linkZ = this.field_145849_e;
                tm.linkDim = this.field_145850_b.field_73011_w.field_76574_g;
                targetWorld.func_147471_g(tm.field_145851_c, tm.field_145848_d, tm.field_145849_e);
                this.linked = true;
                this.func_70296_d();
                tm.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    public void invalidateLink() {
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return;
        }
        if (!Utils.isChunkLoaded((World)targetWorld, this.linkX, this.linkZ)) {
            return;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te != null && te instanceof TileMirror) {
            TileMirror tm = (TileMirror)te;
            tm.linked = false;
            this.func_70296_d();
            tm.func_70296_d();
            targetWorld.func_147471_g(this.linkX, this.linkY, this.linkZ);
        }
    }

    public boolean isLinkValid() {
        if (!this.linked) {
            return false;
        }
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirror)) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        TileMirror tm = (TileMirror)te;
        if (!tm.linked) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        if (tm.linkX != this.field_145851_c || tm.linkY != this.field_145848_d || tm.linkZ != this.field_145849_e || tm.linkDim != this.field_145850_b.field_73011_w.field_76574_g) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        return true;
    }

    public boolean isLinkValidSimple() {
        if (!this.linked) {
            return false;
        }
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirror)) {
            return false;
        }
        TileMirror tm = (TileMirror)te;
        if (!tm.linked) {
            return false;
        }
        return tm.linkX == this.field_145851_c && tm.linkY == this.field_145848_d && tm.linkZ == this.field_145849_e && tm.linkDim == this.field_145850_b.field_73011_w.field_76574_g;
    }

    public boolean isDestinationValid() {
        WorldServer targetWorld = DimensionManager.getWorld((int)this.linkDim);
        if (targetWorld == null) {
            return false;
        }
        TileEntity te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (te == null || !(te instanceof TileMirror)) {
            this.linked = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return false;
        }
        TileMirror tm = (TileMirror)te;
        return !tm.isLinkValid();
    }

    public boolean transport(EntityItem ie) {
        ItemStack items = ie.func_92059_d();
        if (!this.linked || !this.isLinkValid()) {
            return false;
        }
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
        TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (target != null && target instanceof TileMirror) {
            ((TileMirror)target).addStack(items);
            this.addInstability(null, items.field_77994_a);
            ie.func_70106_y();
            this.func_70296_d();
            target.func_70296_d();
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
            return true;
        }
        return false;
    }

    public void eject() {
        int i;
        if (this.outputStacks.size() > 0 && this.count > 20 && this.outputStacks.get(i = this.field_145850_b.field_73012_v.nextInt(this.outputStacks.size())) != null) {
            ItemStack outItem = this.outputStacks.get(i).func_77946_l();
            outItem.field_77994_a = 1;
            if (this.spawnItem(outItem)) {
                --this.outputStacks.get((int)i).field_77994_a;
                this.addInstability(null, 1);
                this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
                if (this.outputStacks.get((int)i).field_77994_a <= 0) {
                    this.outputStacks.remove(i);
                }
                this.func_70296_d();
            }
        }
    }

    public boolean spawnItem(ItemStack stack) {
        try {
            ForgeDirection face = ForgeDirection.getOrientation((int)this.func_145832_p());
            EntityItem ie2 = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 - (double)face.offsetX * 0.3, (double)this.field_145848_d + 0.5 - (double)face.offsetY * 0.3, (double)this.field_145849_e + 0.5 - (double)face.offsetZ * 0.3, stack);
            ie2.field_70159_w = (float)face.offsetX * 0.15f;
            ie2.field_70181_x = (float)face.offsetY * 0.15f;
            ie2.field_70179_y = (float)face.offsetZ * 0.15f;
            ie2.field_71088_bW = 20;
            this.field_145850_b.func_72838_d((Entity)ie2);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    protected void addInstability(World targetWorld, int amt) {
        TileEntity te;
        this.instability += amt;
        if (targetWorld != null && (te = targetWorld.func_147438_o(this.linkX, this.linkY, this.linkZ)) != null && te instanceof TileMirror) {
            ((TileMirror)te).instability += amt;
            if (((TileMirror)te).instability < 0) {
                ((TileMirror)te).instability = 0;
            }
            te.func_70296_d();
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.linked = nbttagcompound.func_74767_n("linked");
        this.linkX = nbttagcompound.func_74762_e("linkX");
        this.linkY = nbttagcompound.func_74762_e("linkY");
        this.linkZ = nbttagcompound.func_74762_e("linkZ");
        this.linkDim = nbttagcompound.func_74762_e("linkDim");
        this.instability = nbttagcompound.func_74762_e("instability");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("linked", this.linked);
        nbttagcompound.func_74768_a("linkX", this.linkX);
        nbttagcompound.func_74768_a("linkY", this.linkY);
        nbttagcompound.func_74768_a("linkZ", this.linkZ);
        nbttagcompound.func_74768_a("linkDim", this.linkDim);
        nbttagcompound.func_74768_a("instability", this.instability);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_145842_c(int i, int j) {
        if (i == 1) {
            if (this.field_145850_b.field_72995_K) {
                ForgeDirection face = ForgeDirection.getOrientation((int)this.func_145832_p());
                for (int q = 0; q < Thaumcraft.proxy.particleCount(1); ++q) {
                    double xx = (double)this.field_145851_c + 0.33 + (double)(this.field_145850_b.field_73012_v.nextFloat() * 0.33f) - (double)face.offsetX / 2.0;
                    double yy = (double)this.field_145848_d + 0.33 + (double)(this.field_145850_b.field_73012_v.nextFloat() * 0.33f) - (double)face.offsetY / 2.0;
                    double zz = (double)this.field_145849_e + 0.33 + (double)(this.field_145850_b.field_73012_v.nextFloat() * 0.33f) - (double)face.offsetZ / 2.0;
                    EntitySpellParticleFX var21 = new EntitySpellParticleFX(this.field_145850_b, xx, yy, zz, 0.0, 0.0, 0.0);
                    var21.field_70159_w = (double)face.offsetX * 0.05;
                    var21.field_70181_x = (double)face.offsetY * 0.05;
                    var21.field_70179_y = (double)face.offsetZ * 0.05;
                    var21.func_82338_g(0.5f);
                    var21.func_70538_b(0.0f, 0.0f, 0.0f);
                    Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)var21);
                }
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            int tickrate = this.instability / 50;
            if (tickrate == 0 || this.count % (tickrate * tickrate) == 0) {
                this.eject();
            }
            this.checkInstability();
            if (this.count++ % this.inc == 0) {
                if (!this.isLinkValidSimple()) {
                    if (this.inc < 600) {
                        this.inc += 20;
                    }
                    this.restoreLink();
                } else {
                    this.inc = 40;
                }
            }
        }
    }

    public void checkInstability() {
        int amt;
        if (this.instability > 0 && this.count % 20 == 0) {
            --this.instability;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.instability > 0 && (amt = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ORDER, Math.min(this.instability, 1))) > 0) {
            WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
            this.addInstability((World)targetWorld, -amt);
        }
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
        this.outputStacks = new ArrayList();
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            this.outputStacks.add(ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1));
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.outputStacks.size(); ++i) {
            if (this.outputStacks.get(i) == null || this.outputStacks.get((int)i).field_77994_a <= 0) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.outputStacks.get(i).func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbtCompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return null;
    }

    public ItemStack func_70298_a(int par1, int par2) {
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        return null;
    }

    public void addStack(ItemStack stack) {
        this.outputStacks.add(stack);
        this.func_70296_d();
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
        TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
        if (target != null && target instanceof TileMirror) {
            ((TileMirror)target).addStack(par2ItemStack.func_77946_l());
            this.addInstability(null, par2ItemStack.field_77994_a);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockMirror, 1, 0);
        } else {
            this.spawnItem(par2ItemStack.func_77946_l());
        }
    }

    public String func_145825_b() {
        return "container.mirror";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer var1) {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int var1, ItemStack var2) {
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(this.linkDim);
        TileEntity target = world.func_147438_o(this.linkX, this.linkY, this.linkZ);
        return target != null && target instanceof TileMirror;
    }
}

