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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
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
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSlimyBubble;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
import thaumcraft.common.tiles.TileAlchemyFurnaceAdvancedNozzle;

public class BlockAlchemyFurnace
extends BlockContainer {
    public IIcon icon;

    public BlockAlchemyFurnace() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(17.0f);
        this.func_149672_a(Block.field_149777_j);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:metalbase");
    }

    public IIcon func_149691_a(int i, int md) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public AxisAlignedBB func_149633_g(World w, int i, int j, int k) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        return super.func_149633_g(w, i, j, k);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity entity) {
        int md = world.func_72805_g(i, j, k);
        if (md == 0 && !(entity instanceof EntityLivingBase)) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.7f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, entity);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, entity);
        }
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileAlchemyFurnaceAdvanced tile;
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0 && (tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x, y, z)) != null && tile.heat > 100) {
            return (int)((float)tile.heat / (float)tile.maxPower * 12.0f);
        }
        return super.getLightValue(world, x, y, z);
    }

    public void func_149670_a(World world, int i, int j, int k, Entity entity) {
        TileAlchemyFurnaceAdvanced tile;
        int metadata;
        if (!world.field_72995_K && (metadata = world.func_72805_g(i, j, k)) == 0 && (tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(i, j, k)) != null && entity instanceof EntityItem && tile.process(((EntityItem)entity).func_92059_d())) {
            ItemStack s = ((EntityItem)entity).func_92059_d();
            --s.field_77994_a;
            world.func_72956_a(entity, "thaumcraft:bubble", 0.2f, 1.0f + world.field_73012_v.nextFloat() * 0.4f);
            if (s.field_77994_a <= 0) {
                entity.func_70106_y();
            } else {
                ((EntityItem)entity).func_92058_a(s);
            }
        }
    }

    public Item func_149650_a(int md, Random par2Random, int par3) {
        return md == 0 ? Item.func_150898_a((Block)ConfigBlocks.blockStoneDevice) : (md == 1 || md == 2 || md == 3 || md == 4 ? Item.func_150898_a((Block)ConfigBlocks.blockMetalDevice) : Item.func_150899_d((int)0));
    }

    public int func_149692_a(int metadata) {
        if (metadata == 1 || metadata == 4) {
            return 3;
        }
        if (metadata == 3) {
            return 9;
        }
        if (metadata == 2) {
            return 1;
        }
        return 0;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileAlchemyFurnaceAdvanced();
        }
        if (metadata == 1) {
            return new TileAlchemyFurnaceAdvancedNozzle();
        }
        return super.createTileEntity(world, metadata);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileAlchemyFurnaceAdvancedNozzle) {
            if (((TileAlchemyFurnaceAdvancedNozzle)te).furnace != null) {
                float r = (float)((TileAlchemyFurnaceAdvancedNozzle)te).furnace.vis / (float)((TileAlchemyFurnaceAdvancedNozzle)te).furnace.maxVis;
                return MathHelper.func_76141_d((float)(r * 14.0f)) + ((TileAlchemyFurnaceAdvancedNozzle)te).furnace.vis > 0 ? 1 : 0;
            }
            return 0;
        }
        return 0;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public void func_149749_a(World world, int x, int y, int z, Block bl, int md) {
        block8: {
            if (world.field_72995_K) break block8;
            if (md != 0) {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        for (int c = -1; c <= 1; ++c) {
                            TileAlchemyFurnaceAdvanced tile;
                            if (world.func_147439_a(x + a, y + b, z + c) != this || world.func_72805_g(x + a, y + b, z + c) != 0 || (tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x + a, y + b, z + c)) == null) continue;
                            tile.destroy = true;
                            break block8;
                        }
                    }
                }
            } else {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = 0; b <= 1; ++b) {
                        for (int c = -1; c <= 1; ++c) {
                            if (a == 0 && b == 0 && c == 0 || world.func_147439_a(x + a, y + b, z + c) != this) continue;
                            int m = world.func_72805_g(x + a, y + b, z + c);
                            world.func_147465_d(x + a, y + b, z + c, Block.func_149634_a((Item)this.func_149650_a(m, world.field_73012_v, 0)), this.func_149692_a(m), 3);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        TileAlchemyFurnaceAdvanced tile;
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0 && (tile = (TileAlchemyFurnaceAdvanced)world.func_147438_o(x, y, z)) != null && tile.vis > 0) {
            FXSlimyBubble ef = new FXSlimyBubble(world, (float)x + rand.nextFloat(), y + 1, (float)z + rand.nextFloat(), 0.06f + rand.nextFloat() * 0.06f);
            ef.func_82338_g(0.8f);
            ef.func_70538_b(0.6f - rand.nextFloat() * 0.2f, 0.0f, 0.6f + rand.nextFloat() * 0.2f);
            ParticleEngine.instance.addEffect(world, ef);
            if (rand.nextInt(50) == 0) {
                double var21 = (float)x + rand.nextFloat();
                double var22 = (double)y + this.field_149756_F;
                double var23 = (float)z + rand.nextFloat();
                world.func_72980_b(var21, var22, var23, "liquid.lavapop", 0.1f + rand.nextFloat() * 0.1f, 0.9f + rand.nextFloat() * 0.15f, false);
            }
            int q = rand.nextInt(2);
            int w = rand.nextInt(2);
            FXSlimyBubble ef2 = new FXSlimyBubble(world, (double)x - 0.6 + (double)rand.nextFloat() * 0.2 + (double)(q * 2), y + 2, (double)z - 0.6 + (double)rand.nextFloat() * 0.2 + (double)(w * 2), 0.06f + rand.nextFloat() * 0.06f);
            ef2.func_82338_g(0.8f);
            ef2.func_70538_b(0.6f - rand.nextFloat() * 0.2f, 0.0f, 0.6f + rand.nextFloat() * 0.2f);
            ParticleEngine.instance.addEffect(world, ef2);
        }
        super.func_149734_b(world, x, y, z, rand);
    }
}

