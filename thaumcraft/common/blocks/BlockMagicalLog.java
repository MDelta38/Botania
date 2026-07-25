/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.tiles.TileNode;

public class BlockMagicalLog
extends BlockRotatedPillar {
    public static final String[] woodType = new String[]{"greatwood", "silverwood", "silverwoodknot"};
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tree_side;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] tree_top;

    public BlockMagicalLog() {
        super(Material.field_151575_d);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149766_f);
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon func_150161_d(int par1) {
        return this.tree_top[par1 % this.tree_top.length];
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon func_150163_b(int i) {
        return this.tree_side[i % this.tree_side.length];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.tree_side = new IIcon[woodType.length];
        this.tree_top = new IIcon[woodType.length];
        for (int i = 0; i < this.tree_side.length; ++i) {
            this.tree_side[i] = ir.func_94245_a("thaumcraft:" + woodType[i] + "side");
            this.tree_top[i] = ir.func_94245_a("thaumcraft:" + woodType[i] + "top");
        }
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
        int b0 = 4;
        int j1 = b0 + 1;
        if (par1World.func_72904_c(par2 - j1, par3 - j1, par4 - j1, par2 + j1, par3 + j1, par4 + j1)) {
            for (int k1 = -b0; k1 <= b0; ++k1) {
                for (int l1 = -b0; l1 <= b0; ++l1) {
                    for (int i2 = -b0; i2 <= b0; ++i2) {
                        Block j2 = par1World.func_147439_a(par2 + k1, par3 + l1, par4 + i2);
                        if (j2.isAir((IBlockAccess)par1World, par2 + k1, par3 + l1, par4 + i2)) continue;
                        j2.beginLeavesDecay(par1World, par2 + k1, par3 + l1, par4 + i2);
                    }
                }
            }
        }
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        TileEntity te;
        if (BlockMagicalLog.limitToValidMetadata(par5) == 2 && !par1World.field_72995_K && (te = par1World.func_147438_o(par2, par3, par4)) != null && te instanceof INode && ((INode)te).getAspects().size() > 0) {
            for (Aspect aspect : ((INode)te).getAspects().getAspects()) {
                for (int a = 0; a <= ((INode)te).getAspects().getAmount(aspect) / 10; ++a) {
                    if (((INode)te).getAspects().getAmount(aspect) < 5) continue;
                    ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
                    AspectList al = new AspectList();
                    ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(aspect, 2));
                    this.func_149642_a(par1World, par2, par3, par4, ess);
                }
            }
        }
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public int func_149692_a(int par1) {
        if ((par1 & 3) == 2) {
            return 1;
        }
        return par1 & 3;
    }

    public static int limitToValidMetadata(int par0) {
        return par0 & 3;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if ((world.func_72805_g(x, y, z) & 2) == 1) {
            return 7;
        }
        if ((world.func_72805_g(x, y, z) & 2) == 2) {
            return 7;
        }
        return super.getLightValue(world, x, y, z);
    }

    public boolean hasTileEntity(int metadata) {
        if (BlockMagicalLog.limitToValidMetadata(metadata) == 2) {
            return true;
        }
        return super.hasTileEntity(metadata);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (BlockMagicalLog.limitToValidMetadata(metadata) == 2) {
            return new TileNode();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        if (BlockMagicalLog.limitToValidMetadata(meta) == 2) {
            Thaumcraft.proxy.burst(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.0f);
            world.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f, false);
        }
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 5;
    }
}

