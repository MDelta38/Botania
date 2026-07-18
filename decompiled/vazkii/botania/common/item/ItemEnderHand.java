/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.rod.ItemExchangeRod;

public class ItemEnderHand
extends ItemMod
implements IManaUsingItem,
IBlockProvider {
    private static final int COST_PROVIDE = 5;
    private static final int COST_SELF = 250;
    private static final int COST_OTHER = 5000;

    public ItemEnderHand() {
        this.func_77625_d(1);
        this.func_77655_b("enderHand");
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (ManaItemHandler.requestManaExact(stack, player, 250, false)) {
            player.func_71007_a((IInventory)player.func_71005_bN());
            ManaItemHandler.requestManaExact(stack, player, 250, true);
            world.func_72956_a((Entity)player, "mob.endermen.portal", 1.0f, 1.0f);
        }
        return stack;
    }

    public boolean func_111207_a(ItemStack stack, EntityPlayer iplayer, EntityLivingBase entity) {
        if (ConfigHandler.enderPickpocketEnabled && entity instanceof EntityPlayer && ManaItemHandler.requestManaExact(stack, iplayer, 5000, false)) {
            iplayer.func_71007_a((IInventory)((EntityPlayer)entity).func_71005_bN());
            ManaItemHandler.requestManaExact(stack, iplayer, 5000, true);
            iplayer.field_70170_p.func_72956_a((Entity)iplayer, "mob.endermen.portal", 1.0f, 1.0f);
            return true;
        }
        return false;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public boolean provideBlock(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta, boolean doit) {
        boolean mana;
        if (requestor != null && requestor.func_77973_b() == this) {
            return false;
        }
        ItemStack istack = ItemExchangeRod.removeFromInventory(player, (IInventory)player.func_71005_bN(), stack, block, meta, false);
        if (istack != null && (mana = ManaItemHandler.requestManaExact(stack, player, 5, false))) {
            if (doit) {
                ManaItemHandler.requestManaExact(stack, player, 5, true);
                ItemExchangeRod.removeFromInventory(player, (IInventory)player.func_71005_bN(), stack, block, meta, true);
            }
            return true;
        }
        return false;
    }

    @Override
    public int getBlockCount(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta) {
        if (requestor != null && requestor.func_77973_b() == this) {
            return 0;
        }
        return ItemExchangeRod.getInventoryItemCount(player, (IInventory)player.func_71005_bN(), stack, block, meta);
    }
}

