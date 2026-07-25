/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemEldritchObject;
import thaumcraft.common.tiles.TileEldritchAltar;
import thaumcraft.common.tiles.TileEldritchCap;
import thaumcraft.common.tiles.TileEldritchCrabSpawner;
import thaumcraft.common.tiles.TileEldritchLock;
import thaumcraft.common.tiles.TileEldritchObelisk;
import thaumcraft.common.tiles.TileEldritchTrap;

public class BlockEldritch
extends BlockContainer {
    public IIcon icon = null;
    public IIcon[] insIcon = new IIcon[9];
    private Random rand = new Random();

    public BlockEldritch() {
        super(Material.field_151576_e);
        this.func_149752_b(20000.0f);
        this.func_149711_c(50.0f);
        this.func_149672_a(field_149769_e);
        this.func_149675_a(true);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        this.func_149713_g(0);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:obsidiantile");
        this.insIcon[0] = ir.func_94245_a("thaumcraft:es_i_1");
        this.insIcon[1] = ir.func_94245_a("thaumcraft:es_i_2");
        this.insIcon[2] = ir.func_94245_a("thaumcraft:deco_1");
        this.insIcon[3] = ir.func_94245_a("thaumcraft:deco_2");
        this.insIcon[4] = ir.func_94245_a("thaumcraft:deco_3");
        this.insIcon[5] = ir.func_94245_a("thaumcraft:es_5");
        this.insIcon[6] = ir.func_94245_a("thaumcraft:es_6");
        this.insIcon[7] = ir.func_94245_a("thaumcraft:es_7");
        this.insIcon[8] = ir.func_94245_a("thaumcraft:es_8");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return meta == 4 ? this.insIcon[0] : (meta == 5 ? this.insIcon[1] : (meta == 6 ? this.insIcon[2] : (meta == 7 ? this.insIcon[4] : (meta == 8 ? this.insIcon[3] : (meta == 9 ? ConfigBlocks.blockCosmeticSolid.func_149691_a(side, 14) : (meta == 10 ? this.insIcon[5] : this.icon))))));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess ba, int x, int y, int z, int side) {
        int md = ba.func_72805_g(x, y, z);
        if (md == 8) {
            TileEntity te = ba.func_147438_o(x, y, z);
            if (te instanceof TileEldritchLock && ((TileEldritchLock)te).getFacing() == side) {
                return this.insIcon[3];
            }
            return this.insIcon[4];
        }
        if (md == 10) {
            String l = x + "" + y + "" + z;
            Random r1 = new Random(Math.abs(l.hashCode() * 100) + 1);
            int i = r1.nextInt(12345 + side) % 4;
            return this.insIcon[5 + i];
        }
        return super.func_149673_e(ba, x, y, z, side);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 4));
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 4 || meta == 5 || meta == 7) {
            return 12;
        }
        if (meta == 6 || meta == 8) {
            return 5;
        }
        if (meta == 9) {
            return 4;
        }
        if (meta == 10) {
            return 0;
        }
        return 8;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149719_a(world, i, j, k);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public boolean hasTileEntity(int metadata) {
        return metadata == 0 || metadata == 1 || metadata == 3 || metadata == 8 || metadata == 9 || metadata == 10;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileEldritchAltar();
        }
        if (metadata == 1) {
            return new TileEldritchObelisk();
        }
        if (metadata == 3) {
            return new TileEldritchCap();
        }
        if (metadata == 8) {
            return new TileEldritchLock();
        }
        if (metadata == 9) {
            return new TileEldritchCrabSpawner();
        }
        if (metadata == 10) {
            return new TileEldritchTrap();
        }
        return null;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockEldritchRI;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public Item func_149650_a(int md, Random rand, int fortune) {
        return md == 4 ? Item.func_150898_a((Block)this) : (md == 5 ? ConfigItems.itemResource : Item.func_150899_d((int)0));
    }

    public int func_149692_a(int metadata) {
        return metadata == 2 ? 1 : metadata;
    }

    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        if (metadata == 5 || metadata == 10) {
            return MathHelper.func_76136_a((Random)this.rand, (int)1, (int)4);
        }
        if (metadata == 9) {
            return MathHelper.func_76136_a((Random)this.rand, (int)6, (int)10);
        }
        return super.getExpDrop(world, metadata, fortune);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (md == 5) {
            ret.add(new ItemStack(ConfigItems.itemResource, 1, 9));
            return ret;
        }
        return super.getDrops(world, x, y, z, md, fortune);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        if (!world.field_72995_K && meta < 4) {
            for (int xx = x - 3; xx <= x + 3; ++xx) {
                for (int yy = y - 2; yy <= y + 2; ++yy) {
                    for (int zz = z - 3; zz <= z + 3; ++zz) {
                        if (world.func_147439_a(xx, yy, zz) != this || world.func_72805_g(xx, yy, zz) >= 4) continue;
                        world.func_147468_f(xx, yy, zz);
                    }
                }
            }
            world.func_72876_a(null, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.0f, false);
        }
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 4 || meta == 5) {
            return 2.0f;
        }
        if (meta == 6) {
            return 4.0f;
        }
        if (meta == 7 || meta == 8) {
            return -1.0f;
        }
        if (meta == 9 || meta == 10) {
            return 15.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 4 || meta == 5 || meta == 9 || meta == 10) {
            return 30.0f;
        }
        if (meta == 6) {
            return 100.0f;
        }
        if (meta == 7 || meta == 8) {
            return Float.MAX_VALUE;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        TileEldritchAltar tile;
        TileEntity te;
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0 && !world.field_72995_K && !player.func_70093_af() && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemEldritchObject && player.func_70694_bm().func_77960_j() == 0 && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileEldritchAltar && (tile = (TileEldritchAltar)te).getEyes() < 4) {
            if (tile.getEyes() >= 2) {
                tile.setSpawner(true);
                tile.setSpawnType((byte)1);
            }
            tile.setEyes((byte)(tile.getEyes() + 1));
            tile.checkForMaze();
            --player.func_70694_bm().field_77994_a;
            tile.func_70296_d();
            world.func_147471_g(x, y, z);
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:crystal", 0.2f, 1.0f);
        }
        if (metadata == 8 && player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemEldritchObject && player.field_71071_by.func_70448_g().func_77960_j() == 2 && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileEldritchLock && ((TileEldritchLock)te).count < 0) {
            ((TileEldritchLock)te).count = 0;
            world.func_147471_g(x, y, z);
            te.func_70296_d();
            --player.func_70694_bm().field_77994_a;
            world.func_72908_a((double)x, (double)y, (double)z, "thaumcraft:runicShieldCharge", 1.0f, 1.0f);
        }
        return super.func_149727_a(world, x, y, z, player, side, par7, par8, par9);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        int z;
        int y;
        int x;
        int md = w.func_72805_g(i, j, k);
        if (md == 8) {
            TileEntity te = w.func_147438_o(i, j, k);
            if (te == null || !(te instanceof TileEldritchLock) || ((TileEldritchLock)te).count < 0) {
                return;
            }
            FXSpark ef = new FXSpark(w, (float)i + w.field_73012_v.nextFloat(), (float)j + w.field_73012_v.nextFloat(), (float)k + w.field_73012_v.nextFloat(), 0.5f);
            ef.func_70538_b(0.65f + w.field_73012_v.nextFloat() * 0.1f, 1.0f, 1.0f);
            ef.func_82338_g(0.8f);
            ParticleEngine.instance.addEffect(w, ef);
        } else if (md == 10 && w.func_147437_c(x = i + r.nextInt(2) - r.nextInt(2), y = j + r.nextInt(2) - r.nextInt(2), z = k + r.nextInt(2) - r.nextInt(2))) {
            Thaumcraft.proxy.blockRunes(w, (float)x + r.nextFloat(), (float)y + r.nextFloat(), (float)z + r.nextFloat(), 0.5f + r.nextFloat() * 0.5f, r.nextFloat() * 0.3f, 0.9f + r.nextFloat() * 0.1f, 16 + r.nextInt(4), 0.0f);
        }
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return null;
    }
}

