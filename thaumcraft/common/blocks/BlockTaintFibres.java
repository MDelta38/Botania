/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.ColorizerGrass
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
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.lib.CustomSoundType;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class BlockTaintFibres
extends Block {
    public IIcon[] iconOver = new IIcon[4];
    public IIcon[] icon = new IIcon[5];
    protected int currentPass;

    public BlockTaintFibres() {
        super(Config.taintMaterial);
        this.func_149711_c(1.0f);
        this.func_149752_b(5.0f);
        this.func_149672_a(new CustomSoundType("gore", 0.5f, 0.8f));
        this.func_149675_a(true);
        this.func_149647_a(Thaumcraft.tabTC);
        float f = 0.2f;
        this.func_149676_a(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, f * 3.0f, 0.5f + f);
        this.currentPass = 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:taint_fibres");
        this.icon[1] = ir.func_94245_a("thaumcraft:taintgrass1");
        this.icon[2] = ir.func_94245_a("thaumcraft:taintgrass2");
        this.icon[3] = ir.func_94245_a("thaumcraft:taint_spore_stalk_1");
        this.icon[4] = ir.func_94245_a("thaumcraft:taint_spore_stalk_2");
        this.iconOver[0] = ir.func_94245_a("thaumcraft:blank");
        for (int a = 1; a < 4; ++a) {
            this.iconOver[a] = ir.func_94245_a("thaumcraft:taint_over_" + a);
        }
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 <= 3; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return 8;
        }
        if (md == 4) {
            return 10;
        }
        return super.getLightValue(world, x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149635_D() {
        double d0 = 0.5;
        double d1 = 1.0;
        return ColorizerGrass.func_77480_a((double)d0, (double)d1);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int par1) {
        return this.func_149635_D();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        for (int k1 = -1; k1 <= 1; ++k1) {
            for (int l1 = -1; l1 <= 1; ++l1) {
                int i2 = par1IBlockAccess.func_72807_a(par2 + l1, par4 + k1).func_150558_b(par2, par3, par4);
                l += (i2 & 0xFF0000) >> 16;
                i1 += (i2 & 0xFF00) >> 8;
                j1 += i2 & 0xFF;
            }
        }
        return (l / 9 & 0xFF) << 16 | (i1 / 9 & 0xFF) << 8 | j1 / 9 & 0xFF;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getOverlayBlockTexture(int x, int y, int z, int side) {
        Random r = new Random(side + y + x * z);
        if (r.nextInt(100) < 95) {
            return this.iconOver[0];
        }
        return this.iconOver[r.nextInt(3) + 1];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return this.icon[par2];
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        if (BlockTaintFibres.isOnlyAdjacentToTaint(world, x, y, z)) {
            world.func_147449_b(x, y, z, Blocks.field_150350_a);
        }
        super.func_149695_a(world, x, y, z, par5);
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K) {
            int md = world.func_72805_g(x, y, z);
            BlockTaintFibres.taintBiomeSpread(world, x, y, z, random, this);
            if (md == 0 && BlockTaintFibres.isOnlyAdjacentToTaint(world, x, y, z) || world.func_72807_a((int)x, (int)z).field_76756_M != Config.biomeTaintID) {
                world.func_147449_b(x, y, z, Blocks.field_150350_a);
                return;
            }
            int xx = x + random.nextInt(3) - 1;
            int yy = y + random.nextInt(5) - 3;
            int zz = z + random.nextInt(3) - 1;
            if (world.func_72807_a((int)xx, (int)zz).field_76756_M == Config.biomeTaintID) {
                Block bi = world.func_147439_a(xx, yy, zz);
                if (!BlockTaintFibres.spreadFibres(world, xx, yy, zz)) {
                    List targets;
                    int adjacentTaint = BlockTaintFibres.getAdjacentTaint((IBlockAccess)world, xx, yy, zz);
                    Material bm = world.func_147439_a(xx, yy, zz).func_149688_o();
                    if (adjacentTaint >= 2 && (Utils.isWoodLog((IBlockAccess)world, xx, yy, zz) || bm == Material.field_151572_C || bm == Material.field_151570_A)) {
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaint, 0, 3);
                        world.func_147452_c(xx, yy, zz, ConfigBlocks.blockTaint, 1, 0);
                    }
                    if (adjacentTaint >= 3 && bi != Blocks.field_150350_a && (bm == Material.field_151595_p || bm == Material.field_151578_c || bm == Material.field_151577_b || bm == Material.field_151571_B)) {
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaint, 1, 3);
                        world.func_147452_c(xx, yy, zz, ConfigBlocks.blockTaint, 1, 0);
                    }
                    if (md == 3 && Config.spawnTaintSpore && random.nextInt(10) == 0 && world.func_147437_c(x, y + 1, z)) {
                        world.func_72921_c(x, y, z, 4, 3);
                        EntityTaintSpore spore = new EntityTaintSpore(world);
                        spore.func_70012_b((float)x + 0.5f, y + 1, (float)z + 0.5f, 0.0f, 0.0f);
                        world.func_72838_d((Entity)spore);
                    } else if (md == 4 && (targets = world.func_72872_a(EntityTaintSpore.class, AxisAlignedBB.func_72330_a((double)x, (double)(y + 1), (double)z, (double)(x + 1), (double)(y + 2), (double)(z + 1)))).size() <= 0) {
                        world.func_72921_c(x, y, z, 3, 3);
                    }
                }
            }
        }
    }

    public static boolean spreadFibres(World world, int x, int y, int z) {
        Block bi = world.func_147439_a(x, y, z);
        if (BlockUtils.isAdjacentToSolidBlock(world, x, y, z) && !BlockTaintFibres.isOnlyAdjacentToTaint(world, x, y, z) && !world.func_147439_a(x, y, z).func_149688_o().func_76224_d() && (world.func_147437_c(x, y, z) || bi.isReplaceable((IBlockAccess)world, x, y, z) || bi instanceof BlockFlower || bi.isLeaves((IBlockAccess)world, x, y, z))) {
            if (world.field_73012_v.nextInt(10) == 0 && world.func_147437_c(x, y + 1, z) && world.isSideSolid(x, y - 1, z, ForgeDirection.UP)) {
                if (world.field_73012_v.nextInt(10) < 9) {
                    world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 1, 3);
                } else if (world.field_73012_v.nextInt(12) < 10) {
                    world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 2, 3);
                } else {
                    world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 3, 3);
                }
            } else {
                world.func_147465_d(x, y, z, ConfigBlocks.blockTaintFibres, 0, 3);
            }
            world.func_147452_c(x, y, z, ConfigBlocks.blockTaintFibres, 1, 0);
            return true;
        }
        return false;
    }

    public static void taintBiomeSpread(World world, int x, int y, int z, Random rand, Block block) {
        if (Config.taintSpreadRate > 0) {
            int xx = rand.nextInt(3) - 1;
            int zz = rand.nextInt(3) - 1;
            if (world.func_72807_a((int)(x + xx), (int)(z + zz)).field_76756_M != Config.biomeTaintID && rand.nextInt(Config.taintSpreadRate * 5) == 0 && BlockTaintFibres.getAdjacentTaint((IBlockAccess)world, x, y, z) >= 2) {
                Utils.setBiomeAt(world, x + xx, z + zz, ThaumcraftWorldGenerator.biomeTaint);
                world.func_147452_c(x, y, z, block, 1, 0);
            }
        }
    }

    public static int getAdjacentTaint(IBlockAccess world, int x, int y, int z) {
        int count = 0;
        for (int a = 0; a < 6; ++a) {
            ForgeDirection d = ForgeDirection.getOrientation((int)a);
            int xx = x + d.offsetX;
            int yy = y + d.offsetY;
            int zz = z + d.offsetZ;
            Block bi = world.func_147439_a(xx, yy, zz);
            if (bi != ConfigBlocks.blockTaint && bi != ConfigBlocks.blockTaintFibres) continue;
            ++count;
        }
        return count;
    }

    public static boolean isOnlyAdjacentToTaint(World world, int x, int y, int z) {
        for (int a = 0; a < 6; ++a) {
            ForgeDirection d = ForgeDirection.getOrientation((int)a);
            int xx = x + d.offsetX;
            int yy = y + d.offsetY;
            int zz = z + d.offsetZ;
            Block bi = world.func_147439_a(xx, yy, zz);
            if (world.func_147437_c(xx, yy, zz) || world.func_147439_a(xx, yy, zz).func_149688_o() == Config.taintMaterial) continue;
            return false;
        }
        return true;
    }

    public Item func_149650_a(int md, Random rand, int fortune) {
        return Item.func_150899_d((int)0);
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        int md = world.func_72805_g(i, j, k);
        if (!world.field_72995_K && entity instanceof EntityLivingBase && !((EntityLivingBase)entity).func_70662_br()) {
            if (entity instanceof EntityPlayer && world.field_73012_v.nextInt(1000) == 0) {
                ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 80, 0, false));
            } else if (!(entity instanceof EntityPlayer) && world.field_73012_v.nextInt(500) == 0) {
                ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 160, 0, false));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149696_a(World world, int x, int y, int z, int id, int cd) {
        if (id == 1) {
            if (world.field_72995_K) {
                world.func_72980_b((double)x, (double)y, (double)z, "thaumcraft:roots", 0.1f, 0.9f + world.field_73012_v.nextFloat() * 0.2f, false);
            }
            return true;
        }
        return super.func_149696_a(world, x, y, z, id, cd);
    }

    public int func_149645_b() {
        return ConfigBlocks.blockTaintFibreRI;
    }

    public boolean canRenderInPass(int pass) {
        return pass == 1;
    }

    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        return true;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 0) {
            float f = 0.0625f;
            try {
                for (int a = 0; a < 6; ++a) {
                    ForgeDirection side = ForgeDirection.getOrientation((int)a);
                    if (!world.isSideSolid(x + side.offsetX, y + side.offsetY, z + side.offsetZ, side.getOpposite(), false)) continue;
                    switch (a) {
                        case 0: {
                            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
                            break;
                        }
                        case 1: {
                            this.func_149676_a(0.0f, 1.0f - f, 0.0f, 1.0f, 1.0f, 1.0f);
                            break;
                        }
                        case 2: {
                            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, f);
                            break;
                        }
                        case 3: {
                            this.func_149676_a(0.0f, 0.0f, 1.0f - f, 1.0f, 1.0f, 1.0f);
                            break;
                        }
                        case 4: {
                            this.func_149676_a(0.0f, 0.0f, 0.0f, f, 1.0f, 1.0f);
                            break;
                        }
                        case 5: {
                            this.func_149676_a(1.0f - f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        }
                    }
                    return;
                }
            }
            catch (Throwable t) {
                // empty catch block
            }
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, f, 1.0f);
        } else {
            this.func_149676_a(0.2f, 0.0f, 0.2f, 0.8f, 0.8f, 0.8f);
        }
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        boolean biome = par1World.func_72807_a((int)par2, (int)par4).field_76756_M == Config.biomeTaintID;
        return biome && super.func_149742_c(par1World, par2, par3, par4);
    }
}

