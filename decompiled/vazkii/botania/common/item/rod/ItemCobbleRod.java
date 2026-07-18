/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.rod.ItemDirtRod;

public class ItemCobbleRod
extends ItemMod
implements IManaUsingItem,
IBlockProvider {
    static final int COST = 150;

    public ItemCobbleRod() {
        this.func_77625_d(1);
        this.func_77655_b("cobbleRod");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return ItemDirtRod.place(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, Blocks.field_150347_e, 150, 0.3f, 0.3f, 0.3f);
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public boolean provideBlock(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta, boolean doit) {
        if (block == Blocks.field_150347_e && meta == 0) {
            return !doit || ManaItemHandler.requestManaExactForTool(requestor, player, 150, true);
        }
        return false;
    }

    @Override
    public int getBlockCount(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta) {
        if (block == Blocks.field_150347_e && meta == 0) {
            return -1;
        }
        return 0;
    }
}

