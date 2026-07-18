/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.material;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.Item16Colors;

public class ItemPetal
extends Item16Colors
implements IFlowerComponent {
    public ItemPetal() {
        super("petal");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        ItemStack stackToPlace = new ItemStack(ModBlocks.buriedPetals, 1, par1ItemStack.func_77960_j());
        stackToPlace.func_77943_a(par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
        if (stackToPlace.field_77994_a == 0) {
            if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
                --par1ItemStack.field_77994_a;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean canFit(ItemStack stack, IInventory apothecary) {
        return true;
    }

    @Override
    public int getParticleColor(ItemStack stack) {
        return this.func_82790_a(stack, 0);
    }
}

