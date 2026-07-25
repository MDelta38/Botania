/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 */
package thaumic.tinkerer.common.block.tile.kami;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;

public class TileWarpGate
extends TileEntity
implements IInventory {
    private static final String TAG_LOCKED = "locked";
    public boolean locked = false;
    boolean teleportedThisTick = false;
    ItemStack[] inventorySlots = new ItemStack[10];

    public static boolean teleportPlayer(EntityPlayer player, ChunkCoordinates coords) {
        int x = coords.field_71574_a;
        int y = coords.field_71572_b;
        int z = coords.field_71573_c;
        TileEntity tile = player.field_70170_p.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileWarpGate) {
            TileWarpGate destGate = (TileWarpGate)tile;
            if (!destGate.locked) {
                int i;
                player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:wand", 1.0f, 1.0f);
                for (i = 0; i < 20; ++i) {
                    ThaumicTinkerer.tcProxy.sparkle((float)player.field_70165_t + player.field_70170_p.field_73012_v.nextFloat() - 0.5f, (float)player.field_70163_u + player.field_70170_p.field_73012_v.nextFloat(), (float)player.field_70161_v + player.field_70170_p.field_73012_v.nextFloat() - 0.5f, 6);
                }
                player.func_70078_a(null);
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).field_71135_a.func_147364_a((double)x + 0.5, (double)y + 1.6, (double)z + 0.5, player.field_70177_z, player.field_70125_A);
                }
                for (i = 0; i < 20; ++i) {
                    ThaumicTinkerer.tcProxy.sparkle((float)player.field_70165_t + player.field_70170_p.field_73012_v.nextFloat() - 0.5f, (float)player.field_70163_u + player.field_70170_p.field_73012_v.nextFloat(), (float)player.field_70161_v + player.field_70170_p.field_73012_v.nextFloat() - 0.5f, 6);
                }
                player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:wand", 1.0f, 0.1f);
                return true;
            }
            if (!player.field_70170_p.field_72995_K) {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.noTeleport", new Object[0]));
            }
        } else if (!player.field_70170_p.field_72995_K) {
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.noDest", new Object[0]));
        }
        return false;
    }

    public void func_145845_h() {
        List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)((double)this.field_145848_d + 1.5), (double)(this.field_145849_e + 1)));
        EntityPlayer clientPlayer = ThaumicTinkerer.proxy.getClientPlayer();
        for (EntityPlayer player : players) {
            if (player == null || player != clientPlayer || !player.func_70093_af()) continue;
            player.openGui((Object)ThaumicTinkerer.instance, 52, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            break;
        }
        this.teleportedThisTick = false;
    }

    public void teleportPlayer(EntityPlayer player, int index) {
        int z;
        int y;
        int x;
        ItemStack stack;
        if (this.teleportedThisTick) {
            return;
        }
        ItemStack itemStack = stack = index < this.func_70302_i_() ? this.func_70301_a(index) : null;
        if (stack != null && ItemSkyPearl.isAttuned(stack) && TileWarpGate.teleportPlayer(player, new ChunkCoordinates(x = ItemSkyPearl.getX(stack), y = ItemSkyPearl.getY(stack), z = ItemSkyPearl.getZ(stack)))) {
            this.teleportedThisTick = true;
        }
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
        this.locked = par1NBTTagCompound.func_74767_n(TAG_LOCKED);
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
        par1NBTTagCompound.func_74757_a(TAG_LOCKED, this.locked);
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

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventorySlots[i] != null) {
            if (this.inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = this.inventorySlots[i];
                this.inventorySlots[i] = null;
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[i].func_77979_a(j);
            if (this.inventorySlots[i].field_77994_a == 0) {
                this.inventorySlots[i] = null;
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
    }

    public String func_145825_b() {
        return "warpGate";
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
        return itemstack.func_77973_b() == ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class);
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
}

