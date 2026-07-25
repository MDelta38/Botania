/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class ItemBucketPure
extends Item {
    @SideOnly(value=Side.CLIENT)
    public IIcon icon;

    public ItemBucketPure() {
        this.func_77637_a(Thaumcraft.tabTC);
        this.func_77627_a(false);
        this.func_77625_d(1);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:bucket_pure");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        boolean flag = true;
        MovingObjectPosition movingobjectposition = this.func_77621_a(p_77659_2_, p_77659_3_, flag);
        if (movingobjectposition == null) {
            return p_77659_1_;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (movingobjectposition.field_72310_e == 0) {
                --j;
            }
            if (movingobjectposition.field_72310_e == 1) {
                ++j;
            }
            if (movingobjectposition.field_72310_e == 2) {
                --k;
            }
            if (movingobjectposition.field_72310_e == 3) {
                ++k;
            }
            if (movingobjectposition.field_72310_e == 4) {
                --i;
            }
            if (movingobjectposition.field_72310_e == 5) {
                ++i;
            }
            if (!p_77659_3_.func_82247_a(i, j, k, movingobjectposition.field_72310_e, p_77659_1_)) {
                return p_77659_1_;
            }
            if (this.tryPlaceContainedLiquid(p_77659_2_, i, j, k) && !p_77659_3_.field_71075_bZ.field_75098_d) {
                return new ItemStack(Items.field_151133_ar);
            }
        }
        return p_77659_1_;
    }

    public boolean tryPlaceContainedLiquid(World world, int x, int y, int z) {
        boolean flag;
        Material material = world.func_147439_a(x, y, z).func_149688_o();
        boolean bl = flag = !material.func_76220_a();
        if (!world.func_147437_c(x, y, z) && !flag) {
            return false;
        }
        if (!world.field_72995_K && flag && !material.func_76224_d()) {
            world.func_147480_a(x, y, z, true);
        }
        world.func_147465_d(x, y, z, ConfigBlocks.blockFluidPure, 0, 3);
        return true;
    }
}

