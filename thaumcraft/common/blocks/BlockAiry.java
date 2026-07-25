/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
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
import net.minecraft.block.BlockContainer;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.nodes.INode;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.tiles.TileNitor;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileNodeConverter;
import thaumcraft.common.tiles.TileNodeEnergized;
import thaumcraft.common.tiles.TileNodeStabilizer;
import thaumcraft.common.tiles.TileWardingStone;
import thaumcraft.common.tiles.TileWardingStoneFence;

public class BlockAiry
extends BlockContainer {
    public IIcon blankIcon;

    public BlockAiry() {
        super(Config.airyMaterial);
        this.func_149672_a(new Block.SoundType("cloth", 0.0f, 1.0f));
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149675_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.blankIcon = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.blankIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        int md = worldObj.func_72805_g(target.field_72311_b, target.field_72312_c, target.field_72309_d);
        if ((md == 0 || md == 5) && worldObj.field_73012_v.nextBoolean()) {
            UtilsFX.infusedStoneSparkle(worldObj, target.field_72311_b, target.field_72312_c, target.field_72309_d, 0);
        }
        return super.addHitEffects(worldObj, target, effectRenderer);
    }

    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        if (meta == 0 || meta == 5) {
            Thaumcraft.proxy.burst(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.0f);
            world.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f, false);
        }
        return super.addDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 0 || md == 5) {
            return 2.0f;
        }
        if (md == 10 || md == 11) {
            return 100.0f;
        }
        if (md == 12) {
            return -1.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        int md = world.func_72805_g(x, y, z);
        if (md == 0 || md == 5) {
            return 200.0f;
        }
        if (md == 10 || md == 11) {
            return 50.0f;
        }
        if (md == 12) {
            return Float.MAX_VALUE;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1 || md == 2 || md == 3) {
            return 15;
        }
        if (md == 4 || md == 12) {
            return 0;
        }
        if (md == 0 || md == 5 || md == 10 || md == 11) {
            return 8;
        }
        return super.getLightValue(world, x, y, z);
    }

    public void func_149719_a(IBlockAccess ba, int x, int y, int z) {
        int md = ba.func_72805_g(x, y, z);
        if (md == 3 || md == 4 || md == 10 || md == 11 || md == 12) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            this.func_149676_a(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
        }
        super.func_149719_a(ba, x, y, z);
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        return md == 2 || md == 3 || md == 4 || md == 10 || md == 11;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2 || md == 3 || md == 4) {
            return true;
        }
        return super.canBeReplacedByLeaves(world, x, y, z);
    }

    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2 || md == 3) {
            return true;
        }
        return super.isLeaves(world, x, y, z);
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 4 && par7Entity != null && par7Entity instanceof EntityLivingBase && !(par7Entity instanceof EntityPlayer)) {
            int a = 1;
            if (world.func_147439_a(x, y - a, z) != ConfigBlocks.blockCosmeticSolid) {
                ++a;
            }
            if (!world.func_72864_z(x, y - a, z)) {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                super.func_149743_a(world, x, y, z, par5AxisAlignedBB, par6List, par7Entity);
            }
        } else if (metadata == 12) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.func_149743_a(world, x, y, z, par5AxisAlignedBB, par6List, par7Entity);
        }
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 4) {
            for (int a = 1; a < 3; ++a) {
                TileEntity te = world.func_147438_o(x, y - a, z);
                if (te == null || !(te instanceof TileWardingStone)) continue;
                return te.func_145831_w().func_72864_z(x, y - a, z);
            }
        }
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata != 4 && metadata != 12) {
            return null;
        }
        return super.func_149668_a(world, x, y, z);
    }

    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        int md = par1World.func_72805_g(par2, par3, par4);
        if (md == 0 || md == 2 || md == 3 || md == 4 || md == 5 || md == 10 || md == 11 || md == 12) {
            return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
        }
        return super.func_149633_g(par1World, par2, par3, par4);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return par1 == 1 ? ConfigItems.itemResource : Item.func_150899_d((int)0);
    }

    public Item func_149694_d(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1) {
            return ConfigItems.itemResource;
        }
        return Item.func_150899_d((int)0);
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        TileEntity te;
        if (par5 == 0 && !par1World.field_72995_K && (te = par1World.func_147438_o(par2, par3, par4)) != null && te instanceof INode && ((INode)te).getAspects().size() > 0) {
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

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World w, int i, int j, int k, Random r) {
        int md = w.func_72805_g(i, j, k);
        if (md == 1) {
            FXSparkle ef2 = new FXSparkle(w, (float)i + 0.5f, (float)j + 0.5f, (float)k + 0.5f, (float)i + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, (float)j + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, (float)k + 0.5f + (r.nextFloat() - r.nextFloat()) / 3.0f, 1.0f, 6, 3);
            ef2.setGravity(0.05f);
            ef2.field_70145_X = true;
            ParticleEngine.instance.addEffect(w, ef2);
        } else if (md == 2 && r.nextInt(500) == 0) {
            int x1 = i + r.nextInt(3) - r.nextInt(3);
            int y1 = j + r.nextInt(3) - r.nextInt(3);
            int z1 = k + r.nextInt(3) - r.nextInt(3);
            int x2 = x1 + r.nextInt(3) - r.nextInt(3);
            int y2 = y1 + r.nextInt(3) - r.nextInt(3);
            int z2 = z1 + r.nextInt(3) - r.nextInt(3);
            Thaumcraft.proxy.wispFX3(w, x1, y1, z1, x2, y2, z2, 0.1f + r.nextFloat() * 0.1f, 7, false, r.nextBoolean() ? -0.033f : 0.033f);
        } else if (md == 10 || md == 11) {
            float h = r.nextFloat() * 0.33f;
            FXSpark ef = new FXSpark(w, (float)i + w.field_73012_v.nextFloat(), (float)j + 0.1515f + h / 2.0f, (float)k + w.field_73012_v.nextFloat(), 0.33f + h);
            if (md == 10) {
                ef.func_70538_b(0.65f + w.field_73012_v.nextFloat() * 0.1f, 1.0f, 1.0f);
                ef.func_82338_g(0.8f);
            } else {
                ef.func_70538_b(0.3f - w.field_73012_v.nextFloat() * 0.1f, 0.0f, 0.5f + w.field_73012_v.nextFloat() * 0.2f);
            }
            ParticleEngine.instance.addEffect(w, ef);
            if (r.nextInt(50) == 0) {
                w.func_72980_b((double)i, (double)j, (double)k, "thaumcraft:jacobs", 0.5f, 1.0f + (r.nextFloat() - r.nextFloat()) * 0.2f, false);
            }
        }
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileNode();
        }
        if (metadata == 1) {
            return new TileNitor();
        }
        if (metadata == 4) {
            return new TileWardingStoneFence();
        }
        if (metadata == 5) {
            return new TileNodeEnergized();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (stack.func_77960_j() == 0 && entity instanceof EntityPlayer) {
            ThaumcraftWorldGenerator.createRandomNodeAt(world, x, y, z, world.field_73012_v, false, false, false);
        }
        super.func_149689_a(world, x, y, z, entity, stack);
    }

    public boolean isAir(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        return md == 2 || md == 3 || md == 10 || md == 11;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        int md = world.func_72805_g(x, y, z);
        if (md == 5) {
            TileEntity te = world.func_147438_o(x, y - 1, z);
            if (world.func_72864_z(x, y - 1, z) || te == null || !(te instanceof TileNodeStabilizer)) {
                BlockAiry.explodify(world, x, y, z);
            } else {
                te = world.func_147438_o(x, y + 1, z);
                if (te == null || !(te instanceof TileNodeConverter)) {
                    BlockAiry.explodify(world, x, y, z);
                }
            }
        }
        super.func_149695_a(world, x, y, z, block);
    }

    public static void explodify(World world, int x, int y, int z) {
        if (!world.field_72995_K) {
            world.func_147468_f(x, y, z);
            world.func_72876_a(null, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 3.0f, false);
            for (int a = 0; a < 50; ++a) {
                int zz;
                int yy;
                int xx = x + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8);
                if (!world.func_147437_c(xx, yy = y + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8), zz = z + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8))) continue;
                if (yy < y) {
                    world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
                    continue;
                }
                world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
            }
        }
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        int md = world.func_72805_g(x, y, z);
        if (md == 10) {
            entity.func_70097_a(DamageSource.field_76376_m, (float)(1 + world.field_73012_v.nextInt(2)));
            entity.field_70159_w *= 0.8;
            entity.field_70179_y *= 0.8;
            if (!world.field_72995_K && world.field_73012_v.nextInt(100) == 0) {
                world.func_147468_f(x, y, z);
            }
        } else if (md == 11 && !(entity instanceof IEldritchMob)) {
            if (world.field_73012_v.nextInt(100) == 0) {
                entity.func_70097_a(DamageSource.field_82727_n, 1.0f);
            }
            entity.field_70159_w *= 0.66;
            entity.field_70179_y *= 0.66;
            if (entity instanceof EntityPlayer) {
                ((EntityPlayer)entity).func_71020_j(0.05f);
            }
            if (entity instanceof EntityLivingBase) {
                PotionEffect pe = new PotionEffect(Potion.field_76437_t.field_76415_H, 100, 1, true);
                ((EntityLivingBase)entity).func_70690_d(pe);
            }
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        super.func_149674_a(world, x, y, z, rand);
        int md = world.func_72805_g(x, y, z);
        if (!(md != 10 && md != 11 || world.field_72995_K)) {
            world.func_147468_f(x, y, z);
        }
    }
}

