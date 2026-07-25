/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;

public class BlockCustomOre
extends Block {
    public IIcon[] icon = new IIcon[5];
    private Random rand = new Random();

    public BlockCustomOre() {
        super(Material.field_151576_e);
        this.func_149752_b(5.0f);
        this.func_149711_c(1.5f);
        this.func_149672_a(Block.field_149769_e);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149675_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:cinnibar");
        this.icon[1] = ir.func_94245_a("thaumcraft:infusedorestone");
        this.icon[2] = ir.func_94245_a("thaumcraft:infusedore");
        this.icon[3] = ir.func_94245_a("thaumcraft:amberore");
        this.icon[4] = ir.func_94245_a("thaumcraft:frostshard");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 == 0) {
            return this.icon[0];
        }
        if (par2 == 7) {
            return this.icon[3];
        }
        if (par2 == 15) {
            return this.icon[4];
        }
        return this.icon[1];
    }

    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        return true;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
        if (md != 0 && md < 6) {
            UtilsFX.infusedStoneSparkle(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, md);
        }
        return super.addHitEffects(worldObj, target, effectRenderer);
    }

    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149719_a(par1iBlockAccess, par2, par3, par4);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (md == 0) {
            ret.add(new ItemStack(ConfigBlocks.blockCustomOre, 1, 0));
        } else if (md == 7) {
            ret.add(new ItemStack(ConfigItems.itemResource, 1 + world.field_73012_v.nextInt(fortune + 1), 6));
        } else {
            int q = 1 + world.field_73012_v.nextInt(2 + fortune);
            for (int a = 0; a < q; ++a) {
                ret.add(new ItemStack(ConfigItems.itemShard, 1, md - 1));
            }
        }
        return ret;
    }

    public int getExpDrop(IBlockAccess world, int md, int fortune) {
        if (md != 0 && md != 7) {
            return MathHelper.func_76136_a((Random)this.rand, (int)0, (int)3);
        }
        if (md == 7) {
            return MathHelper.func_76136_a((Random)this.rand, (int)1, (int)4);
        }
        return super.getExpDrop(world, md, fortune);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockCustomOreRI;
    }
}

