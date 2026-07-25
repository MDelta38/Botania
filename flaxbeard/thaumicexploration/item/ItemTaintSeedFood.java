/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemSeedFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 */
package flaxbeard.thaumicexploration.item;

import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class ItemTaintSeedFood
extends ItemSeedFood
implements IPlantable {
    private int cropId;
    private int soilId;

    public ItemTaintSeedFood(int par1, int par2, float par3, Block par4, Block par5) {
        super(par2, par3, par4, par5);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 != 1) {
            return false;
        }
        if (par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.func_82247_a(par4, par5 + 1, par6, par7, par1ItemStack)) {
            Block soil = par3World.func_147439_a(par4, par5, par6);
            if (soil != null && (soil == Blocks.field_150349_c && par3World.func_72807_a(par4, par6) == ThaumcraftWorldGenerator.biomeTaint || soil == ConfigBlocks.blockTaint && par3World.func_72805_g(par4, par5, par6) == 1) && par3World.func_147437_c(par4, par5 + 1, par6)) {
                par3World.func_147449_b(par4, par5 + 1, par6, ThaumicExploration.taintBerryCrop);
                --par1ItemStack.field_77994_a;
                return true;
            }
            return false;
        }
        return false;
    }
}

