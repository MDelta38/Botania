/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.mana;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockTerraPlate
extends BlockModContainer
implements ILexiconable {
    public static IIcon overlay;
    IIcon[] icons;

    public BlockTerraPlate() {
        super(Material.field_151573_f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149777_j);
        this.func_149663_c("terraPlate");
        BotaniaAPI.blacklistBlockFromMagnet((Block)this, Short.MAX_VALUE);
    }

    public boolean func_149727_a(World worldObj, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ItemStack stack = player.func_71045_bC();
        if (stack != null && stack.func_77973_b() == ModItems.manaResource && stack.func_77960_j() < 3) {
            if (player == null || !player.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
                if (stack.field_77994_a == 0 && player != null) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
            ItemStack target = stack.func_77946_l();
            target.field_77994_a = 1;
            EntityItem item = new EntityItem(worldObj, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, target);
            item.field_145804_b = 40;
            item.field_70179_y = 0.0;
            item.field_70181_x = 0.0;
            item.field_70159_w = 0.0;
            if (!worldObj.field_72995_K) {
                worldObj.func_72838_d((Entity)item);
            }
            return true;
        }
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[3];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
        overlay = IconHelper.forBlock(par1IconRegister, (Block)this, "Overlay");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icons[Math.min(2, par1)];
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileTerraPlate();
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.terrasteel;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        TileTerraPlate plate = (TileTerraPlate)par1World.func_147438_o(par2, par3, par4);
        int val = (int)((double)plate.getCurrentMana() / 500000.0 * 15.0);
        if (plate.getCurrentMana() > 0) {
            val = Math.max(val, 1);
        }
        return val;
    }
}

