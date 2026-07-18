/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 */
package vazkii.botania.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.IPickupAchievement;

public class ItemBlockMod
extends ItemBlock
implements IPickupAchievement,
ICraftAchievement {
    public ItemBlockMod(Block block) {
        super(block);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return this.getUnlocalizedNameInefficiently_(par1ItemStack).replaceAll("tile.", "tile.botania:");
    }

    public String getUnlocalizedNameInefficiently_(ItemStack stack) {
        return super.func_77657_g(stack);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return this.field_150939_a instanceof ICraftAchievement ? ((ICraftAchievement)this.field_150939_a).getAchievementOnCraft(stack, player, matrix) : null;
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return this.field_150939_a instanceof IPickupAchievement ? ((IPickupAchievement)this.field_150939_a).getAchievementOnPickup(stack, player, item) : null;
    }
}

