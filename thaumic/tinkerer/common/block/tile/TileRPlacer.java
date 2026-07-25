/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumic.tinkerer.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;
import thaumic.tinkerer.common.block.tile.TileCamo;

public class TileRPlacer
extends TileCamo
implements IInventory {
    private static final String TAG_ORIENTATION = "orientation";
    private static final String TAG_BLOCKS = "blocks";
    public int orientation;
    public int blocks = 1;
    ItemStack[] inventorySlots = new ItemStack[1];

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.orientation = cmp.func_74762_e(TAG_ORIENTATION);
        this.blocks = cmp.func_74762_e(TAG_BLOCKS);
        NBTTagList var2 = cmp.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound par1nbtTagCompound) {
        super.func_145841_b(par1nbtTagCompound);
    }

    @Override
    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74768_a(TAG_ORIENTATION, this.orientation);
        cmp.func_74768_a(TAG_BLOCKS, this.blocks);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        cmp.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int var1) {
        return this.inventorySlots[var1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.inventorySlots[par1] != null) {
            if (this.inventorySlots[par1].field_77994_a <= par2) {
                ItemStack stackAt = this.inventorySlots[par1];
                this.inventorySlots[par1] = null;
                if (!this.field_145850_b.field_72995_K) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[par1].func_77979_a(par2);
            if (this.inventorySlots[par1].field_77994_a == 0) {
                this.inventorySlots[par1] = null;
            }
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public String func_145825_b() {
        return "remotePlacer";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int var1, ItemStack var2) {
        return var2.func_77973_b() instanceof ItemBlock;
    }

    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
    }

    public void receiveRedstonePulse() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.inventorySlots[0] != null) {
            int z = 0;
            int x = 0;
            int y = 0;
            switch (this.orientation) {
                case 0: {
                    x = this.field_145851_c;
                    y = this.field_145848_d - this.blocks;
                    z = this.field_145849_e;
                    break;
                }
                case 1: {
                    x = this.field_145851_c;
                    y = this.field_145848_d + this.blocks;
                    z = this.field_145849_e;
                    break;
                }
                case 2: {
                    x = this.field_145851_c;
                    y = this.field_145848_d;
                    z = this.field_145849_e - this.blocks;
                    break;
                }
                case 3: {
                    x = this.field_145851_c;
                    y = this.field_145848_d;
                    z = this.field_145849_e + this.blocks;
                    break;
                }
                case 4: {
                    x = this.field_145851_c - this.blocks;
                    y = this.field_145848_d;
                    z = this.field_145849_e;
                    break;
                }
                case 5: {
                    x = this.field_145851_c - this.blocks;
                    y = this.field_145848_d;
                    z = this.field_145849_e;
                }
            }
            if (this.field_145850_b.func_147439_a(x, y, z) == Blocks.field_150350_a) {
                boolean done = false;
                FakePlayer player = FakePlayerFactory.getMinecraft((WorldServer)((WorldServer)this.field_145850_b));
                Item item = this.inventorySlots[0].func_77973_b();
                ItemStack stack = this.inventorySlots[0];
                if (!done) {
                    item.onItemUseFirst(stack, (EntityPlayer)player, this.field_145850_b, x, y, z, ForgeDirection.OPPOSITES[this.orientation], 0.0f, 0.0f, 0.0f);
                }
                if (!done) {
                    done = item.func_77648_a(stack, (EntityPlayer)player, this.field_145850_b, x, y, z, ForgeDirection.OPPOSITES[this.orientation], 0.0f, 0.0f, 0.0f);
                }
                if (!done) {
                    item.func_77659_a(stack, this.field_145850_b, (EntityPlayer)player);
                    done = true;
                }
            }
        }
    }
}

