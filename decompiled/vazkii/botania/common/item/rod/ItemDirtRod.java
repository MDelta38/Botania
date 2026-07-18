/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.rod;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.item.ItemMod;

public class ItemDirtRod
extends ItemMod
implements IManaUsingItem,
ICraftAchievement,
IBlockProvider {
    static final int COST = 75;

    public ItemDirtRod() {
        this("dirtRod");
    }

    public ItemDirtRod(String name) {
        this.func_77625_d(1);
        this.func_77655_b(name);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return ItemDirtRod.place(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, Blocks.field_150346_d, 75, 0.35f, 0.2f, 0.05f);
    }

    public static boolean place(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10, Block block, int cost, float r, float g, float b) {
        if (ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, cost, false)) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)par7);
            int entities = par3World.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(par4 + dir.offsetX), (double)(par5 + dir.offsetY), (double)(par6 + dir.offsetZ), (double)(par4 + dir.offsetX + 1), (double)(par5 + dir.offsetY + 1), (double)(par6 + dir.offsetZ + 1))).size();
            if (entities == 0) {
                ItemStack stackToPlace = new ItemStack(block);
                stackToPlace.func_77943_a(par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                if (stackToPlace.field_77994_a == 0) {
                    ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, cost, true);
                    for (int i = 0; i < 6; ++i) {
                        Botania.proxy.sparkleFX(par3World, (double)(par4 + dir.offsetX) + Math.random(), (double)(par5 + dir.offsetY) + Math.random(), (double)(par6 + dir.offsetZ) + Math.random(), r, g, b, 1.0f, 5);
                    }
                }
            }
        }
        return true;
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.dirtRodCraft;
    }

    @Override
    public boolean provideBlock(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta, boolean doit) {
        if (block == Blocks.field_150346_d && meta == 0) {
            return !doit || ManaItemHandler.requestManaExactForTool(requestor, player, 75, true);
        }
        return false;
    }

    @Override
    public int getBlockCount(EntityPlayer player, ItemStack requestor, ItemStack stack, Block block, int meta) {
        if (block == Blocks.field_150346_d && meta == 0) {
            return -1;
        }
        return 0;
    }
}

