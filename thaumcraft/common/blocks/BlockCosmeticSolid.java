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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileWardingStone;

public class BlockCosmeticSolid
extends Block {
    public IIcon[] icon = new IIcon[27];

    public BlockCosmeticSolid() {
        super(Material.field_151576_e);
        this.func_149752_b(10.0f);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149769_e);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149675_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:obsidiantile");
        this.icon[1] = ir.func_94245_a("thaumcraft:obsidiantotembase");
        this.icon[2] = ir.func_94245_a("thaumcraft:obsidiantotem1");
        this.icon[3] = ir.func_94245_a("thaumcraft:obsidiantotem2");
        this.icon[4] = ir.func_94245_a("thaumcraft:obsidiantotem3");
        this.icon[5] = ir.func_94245_a("thaumcraft:obsidiantotem4");
        this.icon[6] = ir.func_94245_a("thaumcraft:obsidiantotembaseshaded");
        this.icon[7] = ir.func_94245_a("thaumcraft:paving_stone_travel");
        this.icon[8] = ir.func_94245_a("thaumcraft:paving_stone_warding");
        this.icon[9] = ir.func_94245_a("thaumcraft:thaumiumblock");
        this.icon[10] = ir.func_94245_a("thaumcraft:tallowblock");
        this.icon[11] = ir.func_94245_a("thaumcraft:tallowblock_top");
        this.icon[12] = ir.func_94245_a("thaumcraft:pedestal_top");
        this.icon[13] = ir.func_94245_a("thaumcraft:arcane_stone");
        this.icon[14] = ir.func_94245_a("thaumcraft:golem_stone_top");
        this.icon[15] = ir.func_94245_a("thaumcraft:golem_stone_side");
        this.icon[16] = ir.func_94245_a("thaumcraft:golem_stone_top_active");
        this.icon[17] = ir.func_94245_a("thaumcraft:es_1");
        this.icon[18] = ir.func_94245_a("thaumcraft:es_2");
        this.icon[19] = ir.func_94245_a("thaumcraft:es_3");
        this.icon[20] = ir.func_94245_a("thaumcraft:es_4");
        this.icon[21] = ir.func_94245_a("thaumcraft:er_1");
        this.icon[22] = ir.func_94245_a("thaumcraft:er_2");
        this.icon[23] = ir.func_94245_a("thaumcraft:er_3");
        this.icon[24] = ir.func_94245_a("thaumcraft:er_4");
        this.icon[25] = ir.func_94245_a("thaumcraft:crust");
        this.icon[26] = ir.func_94245_a("thaumcraft:es_p");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        if (par2 <= 1 || par2 == 8) {
            return this.icon[0];
        }
        if (par2 == 2) {
            return this.icon[7];
        }
        if (par2 == 3) {
            return this.icon[8];
        }
        if (par2 == 4) {
            return this.icon[9];
        }
        if (par2 == 5) {
            return par1 > 1 ? this.icon[10] : this.icon[11];
        }
        if (par2 == 6) {
            return this.icon[12];
        }
        if (par2 == 7) {
            return this.icon[13];
        }
        if (par2 == 9 || par2 == 10) {
            return par1 == 0 ? this.icon[13] : (par1 == 1 ? (par2 == 9 ? this.icon[14] : this.icon[16]) : this.icon[15]);
        }
        if (par2 == 11 || par2 == 13) {
            return this.icon[17];
        }
        if (par2 == 12) {
            return this.icon[21];
        }
        if (par2 == 14) {
            return this.icon[25];
        }
        if (par2 == 15) {
            return par1 <= 1 ? this.icon[17] : this.icon[26];
        }
        return super.func_149691_a(par1, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side) {
        int md = ba.func_72805_g(x, y, z);
        if ((md == 0 || md == 8) && side > 1 && side < 100) {
            if (ba.func_147439_a(x, y + 1, z) == this && (ba.func_72805_g(x, y + 1, z) == 0 || ba.func_72805_g(x, y + 1, z) == 8)) {
                return this.icon[6];
            }
            if (ba.func_147439_a(x, y - 1, z) != this || ba.func_72805_g(x, y - 1, z) != 0 && ba.func_72805_g(x, y - 1, z) != 8) {
                return this.icon[1];
            }
            return this.icon[2 + Math.abs((side + x % 4 + z % 4 + y % 4) % 4)];
        }
        if (md == 11 || md == 13 || side >= 100) {
            String l = x + "" + y + "" + z;
            Random r1 = new Random(Math.abs(l.hashCode() * 100) + 1);
            int i = r1.nextInt(12345 + side) % 4;
            return this.icon[17 + i];
        }
        if (md == 12) {
            switch (side) {
                case 0: 
                case 1: {
                    return this.icon[21 + Math.abs(x % 2) + Math.abs(z % 2) * 2];
                }
                case 2: 
                case 3: {
                    return this.icon[21 + Math.abs(x % 2) + Math.abs(y % 2) * 2];
                }
                case 4: 
                case 5: {
                    return this.icon[21 + Math.abs(z % 2) + Math.abs(y % 2) * 2];
                }
            }
        }
        return super.func_149673_e(ba, x, y, z, side);
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public void func_149683_g() {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        return md == 2 || md == 3 || md == 13 ? false : super.canCreatureSpawn(type, world, x, y, z);
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
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
        par3List.add(new ItemStack(par1, 1, 11));
        par3List.add(new ItemStack(par1, 1, 12));
        par3List.add(new ItemStack(par1, 1, 14));
        par3List.add(new ItemStack(par1, 1, 15));
    }

    public float func_149712_f(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) != this) {
            return 4.0f;
        }
        int md = world.func_72805_g(x, y, z);
        if (md <= 1 || md == 8) {
            return 30.0f;
        }
        if (md == 4 || md == 6 || md == 7) {
            return 4.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) != this) {
            return 0;
        }
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return 9;
        }
        if (md == 14) {
            return 4;
        }
        return super.getLightValue(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        if (world.func_147439_a(x, y, z) != this) {
            return 20.0f;
        }
        int md = world.func_72805_g(x, y, z);
        if (md <= 1 || md == 8) {
            return 999.0f;
        }
        if (md == 4 || md == 6 || md == 7) {
            return 20.0f;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public int func_149692_a(int par1) {
        return par1 == 8 ? 1 : (par1 == 10 ? 9 : par1);
    }

    public void func_149724_b(World world, int x, int y, int z, Entity e) {
        if (world.func_147439_a(x, y, z) != this) {
            return;
        }
        int md = world.func_72805_g(x, y, z);
        if (md == 2 && e instanceof EntityLivingBase) {
            if (world.field_72995_K) {
                Thaumcraft.proxy.blockSparkle(world, x, y, z, 32768, 5);
            }
            ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 40, 1));
            ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 40, 0));
        }
        super.func_149724_b(world, x, y, z, e);
    }

    public boolean hasTileEntity(int metadata) {
        if (metadata == 3) {
            return true;
        }
        if (metadata == 8) {
            return true;
        }
        return super.hasTileEntity(metadata);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 3) {
            return new TileWardingStone();
        }
        if (metadata == 8) {
            return new TileNode();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        if (meta == 8) {
            Thaumcraft.proxy.burst(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.0f);
            world.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f, false);
        }
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        TileEntity te;
        if (par1World.func_147439_a(par2, par3, par4) != this) {
            return;
        }
        if (par5 == 8 && !par1World.field_72995_K && (te = par1World.func_147438_o(par2, par3, par4)) != null && te instanceof INode && ((INode)te).getAspects().size() > 0) {
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

    public void func_149734_b(World world, int x, int y, int z, Random random) {
        block4: {
            block6: {
                block5: {
                    if (world.func_147439_a(x, y, z) != this) {
                        return;
                    }
                    int md = world.func_72805_g(x, y, z);
                    if (md != 3) break block4;
                    if (!world.func_72864_z(x, y, z)) break block5;
                    for (int a = 0; a < Thaumcraft.proxy.particleCount(2); ++a) {
                        Thaumcraft.proxy.blockRunes(world, x, (float)y + 0.7f, z, 0.2f + world.field_73012_v.nextFloat() * 0.4f, world.field_73012_v.nextFloat() * 0.3f, 0.8f + world.field_73012_v.nextFloat() * 0.2f, 20, -0.02f);
                    }
                    break block4;
                }
                if ((world.func_147439_a(x, y + 1, z) == ConfigBlocks.blockAiry || !world.func_147439_a(x, y + 1, z).func_149655_b((IBlockAccess)world, x, y + 1, z)) && (world.func_147439_a(x, y + 2, z) == ConfigBlocks.blockAiry || !world.func_147439_a(x, y + 1, z).func_149655_b((IBlockAccess)world, x, y + 1, z))) break block6;
                for (int a = 0; a < Thaumcraft.proxy.particleCount(3); ++a) {
                    Thaumcraft.proxy.blockRunes(world, x, (float)y + 0.7f, z, 0.9f + world.field_73012_v.nextFloat() * 0.1f, world.field_73012_v.nextFloat() * 0.3f, world.field_73012_v.nextFloat() * 0.3f, 24, -0.02f);
                }
                break block4;
            }
            List list = world.func_72839_b((Entity)null, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)).func_72314_b(1.0, 1.0, 1.0));
            if (list.isEmpty()) break block4;
            for (Entity entity : list) {
                if (!(entity instanceof EntityLivingBase) || entity instanceof EntityPlayer) continue;
                Thaumcraft.proxy.blockRunes(world, x, (float)y + 0.6f + world.field_73012_v.nextFloat() * Math.max(0.8f, entity.func_70047_e()), z, 0.6f + world.field_73012_v.nextFloat() * 0.4f, 0.0f, 0.3f + world.field_73012_v.nextFloat() * 0.7f, 20, 0.0f);
                break;
            }
        }
    }

    public boolean isBeaconBase(IBlockAccess worldObj, int x, int y, int z, int beaconX, int beaconY, int beaconZ) {
        return worldObj.func_147439_a(x, y, z) == this;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (world.func_147439_a(x, y, z) == this) {
            int md = world.func_72805_g(x, y, z);
            if (md == 9 && world.func_72864_z(x, y, z)) {
                world.func_72921_c(x, y, z, 10, 3);
            } else if (md == 10 && !world.func_72864_z(x, y, z)) {
                world.func_72921_c(x, y, z, 9, 3);
            }
        }
        super.func_149695_a(world, x, y, z, block);
    }
}

