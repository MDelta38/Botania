/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
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
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.CustomStepSound;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.blocks.ItemJarNode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileJarBrain;
import thaumcraft.common.tiles.TileJarFillable;
import thaumcraft.common.tiles.TileJarFillableVoid;
import thaumcraft.common.tiles.TileJarNode;

public class BlockJar
extends BlockContainer {
    public IIcon iconLiquid;
    public IIcon iconJarBottom;
    public IIcon iconJarSide;
    public IIcon iconJarTop;
    public IIcon iconJarTopVoid;
    public IIcon iconJarSideVoid;

    public BlockJar() {
        super(Material.field_151592_s);
        this.func_149711_c(0.3f);
        this.func_149672_a(new CustomStepSound("jar", 1.0f, 1.0f));
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149715_a(0.66f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconLiquid = ir.func_94245_a("thaumcraft:animatedglow");
        this.iconJarSide = ir.func_94245_a("thaumcraft:jar_side");
        this.iconJarTop = ir.func_94245_a("thaumcraft:jar_top");
        this.iconJarTopVoid = ir.func_94245_a("thaumcraft:jar_top_void");
        this.iconJarSideVoid = ir.func_94245_a("thaumcraft:jar_side_void");
        this.iconJarBottom = ir.func_94245_a("thaumcraft:jar_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta == 0 || meta == 1 || meta == 2) {
            return side == 0 ? this.iconJarBottom : (side == 1 ? this.iconJarTop : this.iconJarSide);
        }
        if (meta == 3) {
            return side == 0 ? this.iconJarBottom : (side == 1 ? this.iconJarTopVoid : this.iconJarSideVoid);
        }
        return this.iconJarBottom;
    }

    public int func_149701_w() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileJarFillable();
        }
        if (metadata == 1) {
            return new TileJarBrain();
        }
        if (metadata == 2) {
            return new TileJarNode();
        }
        if (metadata == 3) {
            return new TileJarFillableVoid();
        }
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockJarRI;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int md = world.func_72805_g(x, y, z);
        if (md == 0 || md == 3) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileJarFillable) {
                ItemStack drop = new ItemStack(ConfigItems.itemJarFilled);
                if (((TileJarFillable)te).amount <= 0 && ((TileJarFillable)te).aspectFilter == null) {
                    drop = new ItemStack((Block)this);
                }
                if (te instanceof TileJarFillableVoid) {
                    drop.func_77964_b(3);
                }
                if (((TileJarFillable)te).amount > 0) {
                    ((ItemJarFilled)drop.func_77973_b()).setAspects(drop, new AspectList().add(((TileJarFillable)te).aspect, ((TileJarFillable)te).amount));
                }
                if (((TileJarFillable)te).aspectFilter != null) {
                    if (!drop.func_77942_o()) {
                        drop.func_77982_d(new NBTTagCompound());
                    }
                    drop.field_77990_d.func_74778_a("AspectFilter", ((TileJarFillable)te).aspectFilter.getTag());
                }
                drops.add(drop);
            }
            return drops;
        }
        if (md == 2) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileJarNode && ((TileJarNode)te).drop && ((TileJarNode)te).getAspects() != null) {
                ItemStack drop = new ItemStack(ConfigItems.itemJarNode);
                ((ItemJarNode)drop.func_77973_b()).setAspects(drop, ((TileJarNode)te).getAspects().copy());
                ((ItemJarNode)drop.func_77973_b()).setNodeAttributes(drop, ((TileJarNode)te).getNodeType(), ((TileJarNode)te).getNodeModifier(), ((TileJarNode)te).getId());
                drops.add(drop);
            }
            return drops;
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntity te;
        int md = par1World.func_72805_g(par2, par3, par4);
        if (md == 1 && !par1World.field_72995_K && (te = par1World.func_147438_o(par2, par3, par4)) != null && te instanceof TileJarBrain) {
            int var2;
            for (int xp = ((TileJarBrain)te).xp; xp > 0; xp -= var2) {
                var2 = EntityXPOrb.func_70527_a((int)xp);
                par1World.func_72838_d((Entity)new EntityXPOrb(par1World, (double)par2, (double)par3, (double)par4, var2));
            }
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return meta != 15;
    }

    public void func_149689_a(World world, int par2, int par3, int par4, EntityLivingBase ent, ItemStack stack) {
        int l = MathHelper.func_76128_c((double)((double)(ent.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        TileEntity tile = world.func_147438_o(par2, par3, par4);
        if (tile instanceof TileJarFillable) {
            if (l == 0) {
                ((TileJarFillable)tile).facing = 2;
            }
            if (l == 1) {
                ((TileJarFillable)tile).facing = 5;
            }
            if (l == 2) {
                ((TileJarFillable)tile).facing = 3;
            }
            if (l == 3) {
                ((TileJarFillable)tile).facing = 4;
            }
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float what, float these, float are) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileJarBrain) {
            ((TileJarBrain)te).eatDelay = 40;
            if (!world.field_72995_K) {
                int var6 = world.field_73012_v.nextInt(Math.min(((TileJarBrain)te).xp + 1, 64));
                if (var6 > 0) {
                    int var2;
                    ((TileJarBrain)te).xp -= var6;
                    for (int xp = var6; xp > 0; xp -= var2) {
                        var2 = EntityXPOrb.func_70527_a((int)xp);
                        world.func_72838_d((Entity)new EntityXPOrb(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, var2));
                    }
                    world.func_147471_g(x, y, z);
                    te.func_70296_d();
                }
            } else {
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:jar", 0.2f, 1.0f, false);
            }
        }
        if (te != null && te instanceof TileJarFillable && player.func_70093_af() && ((TileJarFillable)te).aspectFilter != null && side == ((TileJarFillable)te).facing) {
            ((TileJarFillable)te).aspectFilter = null;
            if (world.field_72995_K) {
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:page", 1.0f, 1.0f, false);
            } else {
                ForgeDirection fd = ForgeDirection.getOrientation((int)side);
                world.func_72838_d((Entity)new EntityItem(world, (double)((float)x + 0.5f + (float)fd.offsetX / 3.0f), (double)((float)y + 0.5f), (double)((float)z + 0.5f + (float)fd.offsetZ / 3.0f), new ItemStack(ConfigItems.itemResource, 1, 13)));
            }
        } else if (te != null && te instanceof TileJarFillable && player.func_70093_af() && player.func_70694_bm() == null) {
            ((TileJarFillable)te).amount = 0;
            if (((TileJarFillable)te).aspectFilter == null) {
                ((TileJarFillable)te).aspect = null;
            }
            if (world.field_72995_K) {
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:jar", 0.4f, 1.0f, false);
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "game.neutral.swim", 0.5f, 1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.3f, false);
            }
        } else if (te != null && te instanceof TileJarFillable && player.func_70694_bm() != null && ((TileJarFillable)te).aspectFilter == null && player.func_70694_bm().func_77973_b() == ConfigItems.itemResource && player.func_70694_bm().func_77960_j() == 13) {
            if (((TileJarFillable)te).amount == 0 && ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) == null) {
                return true;
            }
            if (((TileJarFillable)te).amount == 0 && ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()) != null) {
                ((TileJarFillable)te).aspect = ((IEssentiaContainerItem)player.func_70694_bm().func_77973_b()).getAspects(player.func_70694_bm()).getAspects()[0];
            }
            --player.func_70694_bm().field_77994_a;
            this.func_149689_a(world, x, y, z, (EntityLivingBase)player, null);
            ((TileJarFillable)te).aspectFilter = ((TileJarFillable)te).aspect;
            if (world.field_72995_K) {
                world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "thaumcraft:jar", 0.4f, 1.0f, false);
            }
        }
        return true;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        this.func_149676_a(0.1875f, 0.0f, 0.1875f, 0.8125f, 0.75f, 0.8125f);
        super.func_149719_a(world, i, j, k);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileJarBrain) {
            return 2.0f;
        }
        return super.getEnchantPowerBonus(world, x, y, z);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        if (md == 2) {
            return 11;
        }
        return super.getLightValue(world, x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileJarBrain && ((TileJarBrain)tile).xp >= ((TileJarBrain)tile).xpMax) {
            double xx = (double)x + 0.3 + (double)(rand.nextFloat() * 0.4f);
            double yy = (double)y + 0.9;
            double zz = (double)z + 0.3 + (double)(rand.nextFloat() * 0.4f);
            EntitySpellParticleFX var21 = new EntitySpellParticleFX(world, xx, yy, zz, 0.0, 0.0, 0.0);
            var21.func_82338_g(0.5f);
            var21.func_70538_b(0.0f, 0.4f + world.field_73012_v.nextFloat() * 0.1f, 0.3f + world.field_73012_v.nextFloat() * 0.2f);
            Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)var21);
        }
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileJarBrain) {
            float r = (float)((TileJarBrain)tile).xp / (float)((TileJarBrain)tile).xpMax;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileJarBrain)tile).xp > 0 ? 1 : 0);
        }
        if (tile != null && tile instanceof TileJarFillable) {
            float r = (float)((TileJarFillable)tile).amount / (float)((TileJarFillable)tile).maxAmount;
            return MathHelper.func_76141_d((float)(r * 14.0f)) + (((TileJarFillable)tile).amount > 0 ? 1 : 0);
        }
        return super.func_149736_g(world, x, y, z, rs);
    }
}

