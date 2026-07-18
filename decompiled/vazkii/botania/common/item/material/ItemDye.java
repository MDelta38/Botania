/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.material;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.common.item.Item16Colors;

public class ItemDye
extends Item16Colors {
    public ItemDye() {
        super("dye");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block block = par3World.func_147439_a(par4, par5, par6);
        int meta = par1ItemStack.func_77960_j();
        if (meta != par3World.func_72805_g(par4, par5, par6) && (block == Blocks.field_150325_L || block == Blocks.field_150404_cg)) {
            par3World.func_72921_c(par4, par5, par6, meta, 3);
            --par1ItemStack.field_77994_a;
            return true;
        }
        TileEntity tile = par3World.func_147438_o(par4, par5, par6);
        if (tile instanceof IDyablePool) {
            IDyablePool dyable = (IDyablePool)tile;
            int itemMeta = par1ItemStack.func_77960_j();
            if (meta != dyable.getColor()) {
                dyable.setColor(meta);
                --par1ItemStack.field_77994_a;
                return true;
            }
        }
        return false;
    }

    public boolean func_111207_a(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_) {
        if (p_111207_3_ instanceof EntitySheep) {
            EntitySheep entitysheep = (EntitySheep)p_111207_3_;
            int i = p_111207_1_.func_77960_j();
            if (!entitysheep.func_70892_o() && entitysheep.func_70896_n() != i) {
                entitysheep.func_70891_b(i);
                --p_111207_1_.field_77994_a;
            }
            return true;
        }
        return false;
    }
}

