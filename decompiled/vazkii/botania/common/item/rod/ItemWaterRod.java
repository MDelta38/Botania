/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.rod;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.ItemMod;

public class ItemWaterRod
extends ItemMod
implements IManaUsingItem {
    public static final int COST = 75;

    public ItemWaterRod() {
        this.func_77625_d(1);
        this.func_77655_b("waterRod");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, 75, false) && !par3World.field_73011_w.field_76575_d) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)par7);
            ItemStack stackToPlace = new ItemStack((Block)Blocks.field_150358_i);
            stackToPlace.func_77943_a(par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
            if (stackToPlace.field_77994_a == 0) {
                ManaItemHandler.requestManaExactForTool(par1ItemStack, par2EntityPlayer, 75, true);
                for (int i = 0; i < 6; ++i) {
                    Botania.proxy.sparkleFX(par3World, (double)(par4 + dir.offsetX) + Math.random(), (double)(par5 + dir.offsetY) + Math.random(), (double)(par6 + dir.offsetZ) + Math.random(), 0.2f, 0.2f, 1.0f, 1.0f, 5);
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
}

