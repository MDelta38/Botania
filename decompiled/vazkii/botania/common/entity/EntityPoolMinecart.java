/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibMisc;

public class EntityPoolMinecart
extends EntityMinecart {
    private static final int TRANSFER_RATE = 10000;
    private static final String TAG_MANA = "mana";

    public EntityPoolMinecart(World p_i1712_1_) {
        super(p_i1712_1_);
    }

    public EntityPoolMinecart(World p_i1715_1_, double p_i1715_2_, double p_i1715_4_, double p_i1715_6_) {
        super(p_i1715_1_, p_i1715_2_, p_i1715_4_, p_i1715_6_);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
    }

    public Block func_145817_o() {
        return ModBlocks.pool;
    }

    public ItemStack getCartItem() {
        return new ItemStack(ModItems.poolMinecart);
    }

    public int func_94087_l() {
        return 0;
    }

    public void func_94095_a(DamageSource p_94095_1_) {
        super.func_94095_a(p_94095_1_);
        this.func_145778_a(Item.func_150898_a((Block)ModBlocks.pool), 1, 0.0f);
    }

    public int func_94085_r() {
        return 8;
    }

    public void moveMinecartOnRail(int x, int y, int z, double par4) {
        super.moveMinecartOnRail(x, y, z, par4);
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            int cartMana;
            int xp = x + dir.offsetX;
            int zp = z + dir.offsetZ;
            Block block = this.field_70170_p.func_147439_a(xp, y, zp);
            if (block != ModBlocks.pump) continue;
            int xp_ = xp + dir.offsetX;
            int zp_ = zp + dir.offsetZ;
            int meta = this.field_70170_p.func_72805_g(xp, y, zp);
            TileEntity tile = this.field_70170_p.func_147438_o(xp_, y, zp_);
            TileEntity tile_ = this.field_70170_p.func_147438_o(xp, y, zp);
            TilePump pump = (TilePump)tile_;
            if (tile == null || !(tile instanceof IManaPool) || pump.hasRedstone) continue;
            IManaPool pool = (IManaPool)tile;
            ForgeDirection pumpDir = ForgeDirection.getOrientation((int)meta);
            boolean did = false;
            boolean can = false;
            if (pumpDir == dir) {
                int poolMana;
                int transfer;
                can = true;
                cartMana = this.getMana();
                int actualTransfer = Math.min(1000000 - cartMana, transfer = Math.min(10000, poolMana = pool.getCurrentMana()));
                if (actualTransfer > 0) {
                    pool.recieveMana(-transfer);
                    this.setMana(cartMana + actualTransfer);
                    did = true;
                }
            } else if (pumpDir == dir.getOpposite()) {
                int transfer;
                can = true;
                if (!pool.isFull() && (transfer = Math.min(10000, cartMana = this.getMana())) > 0) {
                    pool.recieveMana(transfer);
                    this.setMana(cartMana - transfer);
                    did = true;
                }
            }
            if (did) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_70170_p, xp_, y, zp_);
                pump.hasCart = true;
                if (!pump.active) {
                    pump.setActive(true);
                }
            }
            if (!can) continue;
            pump.hasCartOnTop = true;
            pump.comparator = (int)((double)this.getMana() / 1000000.0 * 15.0);
        }
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a(TAG_MANA, this.getMana());
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.setMana(p_70037_1_.func_74762_e(TAG_MANA));
    }

    public int getMana() {
        return this.field_70180_af.func_75679_c(16);
    }

    public void setMana(int mana) {
        this.field_70180_af.func_75692_b(16, (Object)mana);
    }
}

