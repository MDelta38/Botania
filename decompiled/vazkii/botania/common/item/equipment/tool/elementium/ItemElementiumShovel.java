/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.equipment.tool.elementium;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelShovel;

public class ItemElementiumShovel
extends ItemManasteelShovel {
    public static Material[] materialsShovel = new Material[]{Material.field_151577_b, Material.field_151578_c, Material.field_151595_p, Material.field_151597_y, Material.field_151596_z, Material.field_151571_B};

    public ItemElementiumShovel() {
        super(BotaniaAPI.elementiumToolMaterial, "elementiumShovel");
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        World world = player.field_70170_p;
        Material mat = world.func_147439_a(x, y, z).func_149688_o();
        if (!ToolCommons.isRightMaterial(mat, materialsShovel)) {
            return false;
        }
        MovingObjectPosition block = ToolCommons.raytraceFromEntity(world, (Entity)player, true, 10.0);
        if (block == null) {
            return false;
        }
        ForgeDirection.getOrientation((int)block.field_72310_e);
        int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
        boolean silk = EnchantmentHelper.func_77502_d((EntityLivingBase)player);
        Block blk = world.func_147439_a(x, y, z);
        if (blk instanceof BlockFalling) {
            ToolCommons.removeBlocksInIteration(player, stack, world, x, y, z, 0, -12, 0, 1, 12, 1, blk, materialsShovel, silk, fortune, false);
        }
        return false;
    }
}

