/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
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
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockFluxGoo;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityFallingTaint;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
import thaumcraft.common.lib.CustomSoundType;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class BlockTaint
extends Block {
    private IIcon iconCrust;
    private IIcon iconSoil;
    private IIcon iconFlesh;

    public BlockTaint() {
        super(Config.taintMaterial);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(new CustomSoundType("gore", 0.5f, 0.8f));
        this.func_149675_a(true);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 <= 2; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconCrust = par1IconRegister.func_94245_a("thaumcraft:taint_crust");
        this.iconSoil = par1IconRegister.func_94245_a("thaumcraft:taint_soil");
        this.iconFlesh = par1IconRegister.func_94245_a("thaumcraft:fleshblock");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side) {
        int md = ba.func_72805_g(x, y, z);
        if (md == 0) {
            return this.iconCrust;
        }
        if (md == 1) {
            return this.iconSoil;
        }
        if (md == 2) {
            return this.iconFlesh;
        }
        return this.iconCrust;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int md) {
        if (md == 0) {
            return this.iconCrust;
        }
        if (md == 1) {
            return this.iconSoil;
        }
        if (md == 2) {
            return this.iconFlesh;
        }
        return this.iconCrust;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149635_D() {
        double d0 = 0.5;
        double d1 = 1.0;
        return ColorizerGrass.func_77480_a((double)d0, (double)d1);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int par1) {
        return par1 == 1 ? ThaumcraftWorldGenerator.biomeTaint.field_76790_z : super.func_149635_D();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int md = par1IBlockAccess.func_72805_g(par2, par3, par4);
        if (md != 1) {
            return super.func_149720_d(par1IBlockAccess, par2, par3, par4);
        }
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

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        if (!world.field_72995_K) {
            int md = world.func_72805_g(x, y, z);
            if (md == 2) {
                return;
            }
            BlockTaintFibres.taintBiomeSpread(world, x, y, z, random, this);
            if (md == 0) {
                if (this.tryToFall(world, x, y, z, x, y, z)) {
                    return;
                }
                if (world.func_147437_c(x, y + 1, z)) {
                    boolean doIt = true;
                    ForgeDirection dir = ForgeDirection.getOrientation((int)(2 + random.nextInt(4)));
                    for (int a = 0; a < 4; ++a) {
                        if (!world.func_147437_c(x + dir.offsetX, y - a, z + dir.offsetZ)) {
                            doIt = false;
                            break;
                        }
                        if (world.func_147439_a(x, y - a, z) == this) continue;
                        doIt = false;
                        break;
                    }
                    if (doIt && this.tryToFall(world, x, y, z, x + dir.offsetX, y, z + dir.offsetZ)) {
                        return;
                    }
                }
            }
            int xx = x + random.nextInt(3) - 1;
            int yy = y + random.nextInt(5) - 3;
            int zz = z + random.nextInt(3) - 1;
            if (world.func_72807_a((int)xx, (int)zz).field_76756_M == Config.biomeTaintID) {
                Block bi = world.func_147439_a(xx, yy, zz);
                if (BlockTaintFibres.spreadFibres(world, xx, yy, zz)) {
                    // empty if block
                }
                if (md == 0) {
                    if (Config.spawnTaintSpore && world.func_147437_c(x, y + 1, z) && random.nextInt(200) == 0) {
                        List targets = world.func_72872_a(EntityTaintSporeSwarmer.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)).func_72314_b(16.0, 16.0, 16.0));
                        if (targets.size() <= 0) {
                            world.func_147468_f(x, y, z);
                            EntityTaintSporeSwarmer spore = new EntityTaintSporeSwarmer(world);
                            spore.func_70012_b((float)x + 0.5f, y, (float)z + 0.5f, 0.0f, 0.0f);
                            world.func_72838_d((Entity)spore);
                            world.func_72956_a((Entity)spore, "thaumcraft:roots", 0.1f, 0.9f + world.field_73012_v.nextFloat() * 0.2f);
                        }
                    } else {
                        boolean doIt;
                        boolean bl = doIt = world.func_147439_a(x, y + 1, z) == this;
                        if (doIt) {
                            for (int a = 2; a < 6; ++a) {
                                ForgeDirection dir = ForgeDirection.getOrientation((int)a);
                                if (world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ) == this) continue;
                                doIt = false;
                                break;
                            }
                        }
                        if (doIt) {
                            world.func_147465_d(x, y, z, ConfigBlocks.blockFluxGoo, ((BlockFluxGoo)ConfigBlocks.blockFluxGoo).getQuanta(), 3);
                        }
                    }
                }
            } else if (md == 0 && random.nextInt(20) == 0) {
                world.func_147465_d(x, y, z, ConfigBlocks.blockFluxGoo, ((BlockFluxGoo)ConfigBlocks.blockFluxGoo).getQuanta(), 3);
            } else if (md == 1 && random.nextInt(10) == 0) {
                world.func_147465_d(x, y, z, Blocks.field_150346_d, 0, 3);
            }
        }
    }

    public Item func_149650_a(int md, Random rand, int fortune) {
        return md == 1 ? Blocks.field_150346_d.func_149650_a(0, rand, fortune) : (md == 2 ? Items.field_151078_bh : Item.func_150899_d((int)0));
    }

    public int func_149692_a(int par1) {
        return 0;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack((Block)this, 1, world.func_72805_g(x, y, z));
    }

    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata) {
        if (metadata == 2) {
            return true;
        }
        return super.canSilkHarvest(world, player, x, y, z, metadata);
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        if (meta == 2) {
            return 9;
        }
        return super.quantityDropped(meta, fortune, random);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 0) {
            return 1.75f;
        }
        if (md == 1) {
            return 1.5f;
        }
        if (md == 2) {
            return 0.2f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public static boolean canFallBelow(World par0World, int par1, int par2, int par3) {
        Block l = par0World.func_147439_a(par1, par2, par3);
        int md = par0World.func_72805_g(par1, par2, par3);
        for (int xx = -1; xx <= 1; ++xx) {
            for (int zz = -1; zz <= 1; ++zz) {
                for (int yy = -1; yy <= 1; ++yy) {
                    if (!Utils.isWoodLog((IBlockAccess)par0World, par1 + xx, par2 + yy, par3 + zz)) continue;
                    return false;
                }
            }
        }
        if (l.isAir((IBlockAccess)par0World, par1, par2, par3)) {
            return true;
        }
        if (l == ConfigBlocks.blockFluxGoo && md >= 4) {
            return false;
        }
        if (l == Blocks.field_150480_ab || l == ConfigBlocks.blockTaintFibres) {
            return true;
        }
        if (l.isReplaceable((IBlockAccess)par0World, par1, par2, par3)) {
            return true;
        }
        return l.func_149688_o() == Material.field_151586_h ? true : l.func_149688_o() == Material.field_151587_i;
    }

    private boolean tryToFall(World par1World, int x, int y, int z, int x2, int y2, int z2) {
        int md = par1World.func_72805_g(x, y, z);
        if (BlockTaint.canFallBelow(par1World, x2, y2 - 1, z2) && y2 >= 0) {
            int b0 = 32;
            if (par1World.func_72904_c(x2 - b0, y2 - b0, z2 - b0, x2 + b0, y2 + b0, z2 + b0)) {
                if (!par1World.field_72995_K) {
                    EntityFallingTaint entityfalling = new EntityFallingTaint(par1World, (float)x2 + 0.5f, (float)y2 + 0.5f, (float)z2 + 0.5f, this, md, x, y, z);
                    this.onStartFalling(entityfalling);
                    par1World.func_72838_d((Entity)entityfalling);
                    return true;
                }
            } else {
                par1World.func_147468_f(x, y, z);
                while (BlockTaint.canFallBelow(par1World, x2, y2 - 1, z2) && y2 > 0) {
                    --y2;
                }
                if (y2 > 0) {
                    par1World.func_147465_d(x, y, z, (Block)this, md, 3);
                }
            }
        }
        return false;
    }

    public void func_149724_b(World world, int i, int j, int k, Entity entity) {
        int md = world.func_72805_g(i, j, k);
        if (md == 2) {
            return;
        }
        if (!world.field_72995_K && entity instanceof EntityLivingBase && !((EntityLivingBase)entity).func_70662_br()) {
            if (entity instanceof EntityPlayer && world.field_73012_v.nextInt(100) == 0) {
                ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 80, 0, false));
            } else if (!(entity instanceof EntityPlayer) && world.field_73012_v.nextInt(20) == 0) {
                ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Config.potionTaintPoisonID, 160, 0, false));
            }
        }
        super.func_149724_b(world, i, j, k, entity);
    }

    protected void onStartFalling(EntityFallingTaint entityfalling) {
    }

    public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5) {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int i, int j, int k, Random random) {
        int md = world.func_72805_g(i, j, k);
        if (md == 0 && world.func_147437_c(i, j - 1, k) && random.nextInt(10) == 0) {
            Thaumcraft.proxy.dropletFX(world, (float)i + 0.1f + world.field_73012_v.nextFloat() * 0.8f, j, (float)k + 0.1f + world.field_73012_v.nextFloat() * 0.8f, 0.3f, 0.1f, 0.8f);
        }
    }

    public boolean func_149696_a(World world, int x, int y, int z, int id, int cd) {
        if (id == 1) {
            if (world.field_72995_K) {
                world.func_72980_b((double)x, (double)y, (double)z, "thaumcraft:roots", 0.1f, 0.9f + world.field_73012_v.nextFloat() * 0.2f, false);
            }
            return true;
        }
        return super.func_149696_a(world, x, y, z, id, cd);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
}

