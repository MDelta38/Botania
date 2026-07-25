/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCustomPlantItem
extends ItemBlock {
    public IIcon[] icon = new IIcon[6];

    public BlockCustomPlantItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodsapling");
        this.icon[1] = ir.func_94245_a("thaumcraft:silverwoodsapling");
        this.icon[2] = ir.func_94245_a("thaumcraft:shimmerleaf");
        this.icon[3] = ir.func_94245_a("thaumcraft:cinderpearl");
        this.icon[4] = ir.func_94245_a("thaumcraft:purifier_seed");
        this.icon[5] = ir.func_94245_a("thaumcraft:manashroom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.icon[meta];
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (side != 1) {
            return false;
        }
        if (player.func_82247_a(x, y, z, side, stack) && player.func_82247_a(x, y + 1, z, side, stack)) {
            if (world.func_147439_a(x, y, z).canSustainPlant((IBlockAccess)world, x, y, z, ForgeDirection.UP, (IPlantable)new CustomPlantTypes(stack.func_77960_j())) && world.func_147437_c(x, y + 1, z)) {
                world.func_147465_d(x, y + 1, z, ConfigBlocks.blockCustomPlant, stack.func_77960_j(), 3);
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 1.5f), (double)((float)z + 0.5f), ConfigBlocks.blockCustomPlant.field_149762_H.func_150498_e(), (ConfigBlocks.blockCustomPlant.field_149762_H.func_150497_c() + 1.0f) / 2.0f, ConfigBlocks.blockCustomPlant.field_149762_H.func_150494_d() * 0.8f);
                --stack.field_77994_a;
                return true;
            }
            return false;
        }
        return false;
    }

    private class CustomPlantTypes
    implements IPlantable {
        int md = 0;

        public CustomPlantTypes(int md) {
            this.md = md;
        }

        public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
            if (this.md == 3) {
                return EnumPlantType.Desert;
            }
            if (this.md == 4 || this.md == 5) {
                return EnumPlantType.Cave;
            }
            return EnumPlantType.Plains;
        }

        public Block getPlant(IBlockAccess world, int x, int y, int z) {
            return ConfigBlocks.blockCustomPlant;
        }

        public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
            return this.md;
        }
    }
}

