/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import thaumic.tinkerer.common.block.tile.TileMagnet;
import thaumic.tinkerer.common.item.ItemSoulMould;

public class TileMobMagnet
extends TileMagnet
implements IInventory,
IMovableTile {
    private static final String TAG_ADULT = "adultCheck";
    public boolean adult = true;
    ItemStack[] inventorySlots = new ItemStack[1];

    @Override
    IEntitySelector getEntitySelector() {
        return new IEntitySelector(){

            public boolean func_82704_a(Entity entity) {
                if (!(entity instanceof EntityLivingBase) || entity instanceof EntityPlayer) {
                    return false;
                }
                boolean can = false;
                can = entity instanceof EntityAgeable ? TileMobMagnet.this.adult != ((EntityAgeable)entity).func_70631_g_() : true;
                if (can && TileMobMagnet.this.inventorySlots[0] != null) {
                    String pattern = ItemSoulMould.getPatternName(TileMobMagnet.this.inventorySlots[0]);
                    String name = EntityList.func_75621_b((Entity)entity);
                    return name != null && name.equals(pattern);
                }
                return can;
            }
        };
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.readCustomNBT(par1NBTTagCompound);
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        this.writeCustomNBT(par1NBTTagCompound);
    }

    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        this.adult = par1NBTTagCompound.func_74767_n(TAG_ADULT);
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74757_a(TAG_ADULT, this.adult);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventorySlots[i];
    }

    public void func_145829_t() {
        super.func_145829_t();
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventorySlots[i] != null) {
            if (this.inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = this.inventorySlots[i];
                this.inventorySlots[i] = null;
                this.func_70296_d();
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[i].func_77979_a(j);
            if (this.inventorySlots[i].field_77994_a == 0) {
                this.inventorySlots[i] = null;
            }
            this.func_70296_d();
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
        this.func_70296_d();
    }

    public String func_145825_b() {
        return "magnet";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 1;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    @Override
    public String[] getMethodNames() {
        return new String[]{"isPulling", "setPulling", "getSignal", "getAdultSearch", "setAdultSearch"};
    }

    @Override
    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        switch (method) {
            case 3: {
                return new Object[]{this.adult};
            }
            case 4: {
                return this.setAdultSearchImplementation((Boolean)arguments[0]);
            }
        }
        return super.callMethod(computer, context, method, arguments);
    }

    private Object[] setAdultSearchImplementation(boolean argument) {
        this.adult = argument;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return null;
    }

    @Callback(doc="function():boolean -- Gets Whether magnet is searching for adults")
    @Optional.Method(modid="OpenComputers")
    public Object[] getAdultSearch(Context context, Arguments args) throws Exception {
        return new Object[]{this.adult};
    }

    @Callback(doc="function(boolean):nil -- Sets Whether magnet is searching for adults")
    @Optional.Method(modid="OpenComputers")
    public Object[] setAdultSearch(Context context, Arguments args) throws Exception {
        this.setAdultSearchImplementation(args.checkBoolean(0));
        return new Object[0];
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }
}

