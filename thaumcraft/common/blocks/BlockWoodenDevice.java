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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.Explosion
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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.ItemEssence;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileArcanePressurePlate;
import thaumcraft.common.tiles.TileBanner;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileOwned;
import thaumcraft.common.tiles.TileSensor;

public class BlockWoodenDevice
extends BlockContainer {
    private Random random = new Random();
    public IIcon iconDefault;
    public IIcon iconSilverwood;
    public IIcon iconGreatwood;
    public IIcon[] iconAPPlate = new IIcon[3];
    public IIcon[] iconAEar = new IIcon[7];
    public int renderState = 0;

    public BlockWoodenDevice() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149766_f);
        this.func_149675_a(true);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.iconDefault = ir.func_94245_a("thaumcraft:woodplain");
        this.iconSilverwood = ir.func_94245_a("thaumcraft:planks_silverwood");
        this.iconGreatwood = ir.func_94245_a("thaumcraft:planks_greatwood");
        this.iconAPPlate[0] = ir.func_94245_a("thaumcraft:applate1");
        this.iconAPPlate[1] = ir.func_94245_a("thaumcraft:applate2");
        this.iconAPPlate[2] = ir.func_94245_a("thaumcraft:applate3");
        this.iconAEar[0] = ir.func_94245_a("thaumcraft:arcaneearsideon");
        this.iconAEar[1] = ir.func_94245_a("thaumcraft:arcaneearsideoff");
        this.iconAEar[2] = ir.func_94245_a("thaumcraft:arcaneearbottom");
        this.iconAEar[3] = ir.func_94245_a("thaumcraft:arcaneeartopon");
        this.iconAEar[4] = ir.func_94245_a("thaumcraft:arcaneeartopoff");
        this.iconAEar[5] = ir.func_94245_a("thaumcraft:arcaneearbellside");
        this.iconAEar[6] = ir.func_94245_a("thaumcraft:arcaneearbelltop");
    }

    public int tickRate() {
        return 20;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        for (int a = 0; a < 16; ++a) {
            ItemStack banner = new ItemStack(par1, 1, 8);
            banner.func_77982_d(new NBTTagCompound());
            banner.field_77990_d.func_74774_a("color", (byte)a);
            par3List.add(banner);
        }
    }

    public IIcon func_149691_a(int par1, int par2) {
        if (par2 == 0) {
            return this.iconDefault;
        }
        if (par2 == 6) {
            return this.iconGreatwood;
        }
        if (par2 == 7) {
            return this.iconSilverwood;
        }
        if (par2 == 2 || par2 == 3) {
            return this.iconAPPlate[0];
        }
        if (this.renderState == 0) {
            switch (par1) {
                case 0: {
                    return this.iconAEar[2];
                }
                case 1: {
                    return this.iconAEar[4];
                }
            }
        } else if (this.renderState == 1) {
            switch (par1) {
                case 0: {
                    return this.iconAEar[2];
                }
                case 1: {
                    return this.iconAEar[3];
                }
            }
        } else {
            if (par1 <= 1) {
                return this.iconAEar[6];
            }
            return this.iconAEar[5];
        }
        return this.iconAEar[0];
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity tile;
        int meta = world.func_72805_g(x, y, z);
        if ((meta == 2 || meta == 3) && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileArcanePressurePlate) {
            return this.iconAPPlate[((TileArcanePressurePlate)tile).setting];
        }
        return super.func_149673_e(world, x, y, z, side);
    }

    public int func_149692_a(int par1) {
        return par1 == 3 ? 2 : par1;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        if (Config.wardedStone && (par1 == 2 || par1 == 3)) {
            return Item.func_150899_d((int)0);
        }
        if (par1 == 8) {
            return Item.func_150899_d((int)0);
        }
        return super.func_149650_a(par1, par2Random, par3);
    }

    public float func_149712_f(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) != this) {
            return super.func_149712_f(world, x, y, z);
        }
        int md = world.func_72805_g(x, y, z);
        if (md == 2 || md == 3) {
            return Config.wardedStone ? -1.0f : 2.0f;
        }
        return super.func_149712_f(world, x, y, z);
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        if (world.func_147439_a(x, y, z) != this) {
            return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
        }
        int md = world.func_72805_g(x, y, z);
        if (md == 2 || md == 3) {
            return 999.0f;
        }
        return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        if (world.func_147439_a(x, y, z) == this) {
            int md = world.func_72805_g(x, y, z);
            if (md != 2 && md != 3) {
                super.onBlockExploded(world, x, y, z, explosion);
            }
        } else {
            super.onBlockExploded(world, x, y, z, explosion);
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockWoodenDeviceRI;
    }

    public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
        if (p_149633_1_.func_147439_a(p_149633_2_, p_149633_3_, p_149633_4_) != this) {
            return AxisAlignedBB.func_72330_a((double)p_149633_2_, (double)p_149633_3_, (double)p_149633_4_, (double)((double)p_149633_2_ + 1.0), (double)((double)p_149633_3_ + 1.0), (double)((double)p_149633_4_ + 1.0));
        }
        return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    }

    public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        if (par1iBlockAccess.func_147439_a(par2, par3, par4) != this) {
            super.func_149719_a(par1iBlockAccess, par2, par3, par4);
            return;
        }
        int meta = par1iBlockAccess.func_72805_g(par2, par3, par4);
        if (meta == 0) {
            this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
        } else if (meta == 2) {
            float var6 = 0.0625f;
            this.func_149676_a(var6, 0.0f, var6, 1.0f - var6, 0.0625f, 1.0f - var6);
        } else if (meta == 3) {
            float var6 = 0.0625f;
            this.func_149676_a(var6, 0.0f, var6, 1.0f - var6, 0.03125f, 1.0f - var6);
        } else if (meta == 5) {
            ForgeDirection dir = ForgeDirection.UNKNOWN;
            TileEntity tile = par1iBlockAccess.func_147438_o(par2, par3, par4);
            if (tile != null && tile instanceof TileArcaneBore) {
                dir = ((TileArcaneBore)tile).orientation;
            }
            this.func_149676_a(0 + (dir.offsetX < 0 ? -1 : 0), 0 + (dir.offsetY < 0 ? -1 : 0), 0 + (dir.offsetZ < 0 ? -1 : 0), 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
        } else if (meta == 8) {
            TileEntity tile = par1iBlockAccess.func_147438_o(par2, par3, par4);
            if (tile != null && tile instanceof TileBanner) {
                if (((TileBanner)tile).getWall()) {
                    switch (((TileBanner)tile).getFacing()) {
                        case 0: {
                            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 0.25f);
                            break;
                        }
                        case 8: {
                            this.func_149676_a(0.0f, 0.0f, 0.75f, 1.0f, 2.0f, 1.0f);
                            break;
                        }
                        case 12: {
                            this.func_149676_a(0.0f, 0.0f, 0.0f, 0.25f, 2.0f, 1.0f);
                            break;
                        }
                        case 4: {
                            this.func_149676_a(0.75f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f);
                        }
                    }
                } else {
                    this.func_149676_a(0.33f, 0.0f, 0.33f, 0.66f, 2.0f, 0.66f);
                }
            } else {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            }
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
        super.func_149719_a(par1iBlockAccess, par2, par3, par4);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        if (world.func_147439_a(i, j, k) != this) {
            super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
            return;
        }
        int meta = world.func_72805_g(i, j, k);
        if (meta == 0) {
            this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 1.0f, 0.9f);
        } else if (meta == 2 || meta == 3 || meta == 8) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        } else if (meta == 5) {
            ForgeDirection dir = ForgeDirection.UNKNOWN;
            TileEntity tile = world.func_147438_o(i, j, k);
            if (tile != null && tile instanceof TileArcaneBore) {
                dir = ((TileArcaneBore)tile).orientation;
            }
            this.func_149676_a(0 + (dir.offsetX < 0 ? -1 : 0), 0 + (dir.offsetY < 0 ? -1 : 0), 0 + (dir.offsetZ < 0 ? -1 : 0), 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        }
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        TileArcaneBore tile;
        int meta = world.func_72805_g(x, y, z);
        if (meta == 1) {
            TileEntity tile2 = world.func_147438_o(x, y, z);
            if (tile2 != null && tile2 instanceof TileSensor) {
                ((TileSensor)tile2).updateTone();
            }
        } else if (meta == 5 && (tile = (TileArcaneBore)world.func_147438_o(x, y, z)) != null && tile instanceof TileArcaneBore) {
            ForgeDirection d = tile.baseOrientation.getOpposite();
            Block block = world.func_147439_a(x + d.offsetX, y + d.offsetY, z + d.offsetZ);
            if (block != ConfigBlocks.blockWoodenDevice || !block.isSideSolid((IBlockAccess)world, x + d.offsetX, y + d.offsetY, z + d.offsetZ, tile.baseOrientation)) {
                InventoryUtils.dropItems(world, x, y, z);
                this.func_149697_b(world, x, y, z, 5, 0);
                world.func_147468_f(x, y, z);
            }
        }
        super.func_149695_a(world, x, y, z, par5);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 4 || meta == 6 || meta == 7) {
            return true;
        }
        return super.isSideSolid(world, x, y, z, side);
    }

    public boolean func_149727_a(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9) {
        TileBanner te;
        if (w.func_147439_a(x, y, z) != this) {
            return false;
        }
        int meta = w.func_72805_g(x, y, z);
        if (meta == 4 || meta == 6 || meta == 7) {
            return false;
        }
        if (w.field_72995_K) {
            return true;
        }
        if (!(meta != 5 || p.field_71071_by.func_70448_g() != null && p.field_71071_by.func_70448_g() != null && p.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting)) {
            p.openGui((Object)Thaumcraft.instance, 15, w, x, y, z);
            return true;
        }
        if (meta == 1) {
            TileSensor var6 = (TileSensor)w.func_147438_o(x, y, z);
            if (var6 != null) {
                var6.changePitch();
                var6.triggerNote(w, x, y, z, true);
            }
        } else if (meta == 2 || meta == 3) {
            TileArcanePressurePlate var6 = (TileArcanePressurePlate)w.func_147438_o(x, y, z);
            if (var6 != null && (var6.owner.equals(p.func_70005_c_()) || var6.accessList.contains("1" + p.func_70005_c_()))) {
                var6.setting = (byte)(var6.setting + 1);
                if (var6.setting > 2) {
                    var6.setting = 0;
                }
                switch (var6.setting) {
                    case 0: {
                        p.func_145747_a((IChatComponent)new ChatComponentTranslation("It will now trigger on everything.", new Object[0]));
                        break;
                    }
                    case 1: {
                        p.func_145747_a((IChatComponent)new ChatComponentTranslation("It will now trigger on everything except you.", new Object[0]));
                        break;
                    }
                    case 2: {
                        p.func_145747_a((IChatComponent)new ChatComponentTranslation("It will now trigger on just you.", new Object[0]));
                    }
                }
                w.func_72908_a((double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.1f, 0.9f);
                w.func_147471_g(x, y, z);
                var6.func_70296_d();
            }
        } else if (meta == 8 && (p.func_70093_af() || p.field_71071_by.func_70448_g() != null && p.field_71071_by.func_70448_g().func_77973_b() instanceof ItemEssence) && (te = (TileBanner)w.func_147438_o(x, y, z)) != null && te.getColor() >= 0) {
            if (p.func_70093_af()) {
                te.setAspect(null);
            } else if (((IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()) != null) {
                te.setAspect(((IEssentiaContainerItem)p.func_70694_bm().func_77973_b()).getAspects(p.func_70694_bm()).getAspects()[0]);
                --p.func_70694_bm().field_77994_a;
            }
            w.func_147471_g(x, y, z);
            te.func_70296_d();
            w.func_72908_a((double)x, (double)y, (double)z, "step.cloth", 1.0f, 1.0f);
        }
        return true;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        int md = par1World.func_72805_g(par2, par3, par4);
        if (md == 8) {
            this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        }
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        int md = world.func_72805_g(x, y, z);
        if (md == 8) {
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileBanner) {
                ItemStack drop = new ItemStack((Block)this, 1, 8);
                if (((TileBanner)te).getColor() >= 0 || ((TileBanner)te).getAspect() != null) {
                    drop.func_77982_d(new NBTTagCompound());
                    if (((TileBanner)te).getAspect() != null) {
                        drop.field_77990_d.func_74778_a("aspect", ((TileBanner)te).getAspect().getTag());
                    }
                    drop.field_77990_d.func_74774_a("color", ((TileBanner)te).getColor());
                }
                drops.add(drop);
            }
            return drops;
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    public void func_149689_a(World w, int x, int y, int z, EntityLivingBase p, ItemStack s) {
        TileEntity tile = w.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileOwned && p instanceof EntityPlayer) {
            ((TileOwned)tile).owner = ((EntityPlayer)p).func_70005_c_();
            tile.func_70296_d();
        }
        super.func_149689_a(w, x, y, z, p, s);
    }

    public void func_149726_b(World world, int x, int y, int z) {
        TileEntity tile;
        super.func_149726_b(world, x, y, z);
        if (world.func_147439_a(x, y, z) != this) {
            return;
        }
        int meta = world.func_72805_g(x, y, z);
        if (meta == 1 && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileSensor) {
            ((TileSensor)tile).updateTone();
            tile.func_70296_d();
        }
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            return false;
        }
        if (meta == 1 || meta == 2 || meta == 3 || meta == 4 || meta == 5) {
            return true;
        }
        return super.canConnectRedstone(world, x, y, z, side);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileBellows();
        }
        if (metadata == 1) {
            return new TileSensor();
        }
        if (metadata == 2) {
            return new TileArcanePressurePlate();
        }
        if (metadata == 3) {
            return new TileArcanePressurePlate();
        }
        if (metadata == 4) {
            return new TileArcaneBoreBase();
        }
        if (metadata == 5) {
            return new TileArcaneBore();
        }
        if (metadata == 8) {
            return new TileBanner();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public boolean func_149696_a(World par1World, int par2, int par3, int par4, int par5, int par6) {
        float var7 = (float)Math.pow(2.0, (double)(par6 - 12) / 12.0);
        if (par5 <= 4) {
            if (par5 >= 0) {
                String var8 = "harp";
                if (par5 == 1) {
                    var8 = "bd";
                }
                if (par5 == 2) {
                    var8 = "snare";
                }
                if (par5 == 3) {
                    var8 = "hat";
                }
                if (par5 == 4) {
                    var8 = "bassattack";
                }
                par1World.func_72908_a((double)par2 + 0.5, (double)par3 + 0.5, (double)par4 + 0.5, "note." + var8, 3.0f, var7);
            }
            par1World.func_72869_a("note", (double)par2 + 0.5, (double)par3 + 1.2, (double)par4 + 0.5, (double)par6 / 24.0, 0.0, 0.0);
            return true;
        }
        if (par5 == 99) {
            return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
        }
        return super.func_149696_a(par1World, par2, par3, par4, par5, par6);
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.func_147439_a(par2, par3, par4) != this) {
            return;
        }
        if (!par1World.field_72995_K && par1World.func_72805_g(par2, par3, par4) == 3) {
            this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
        }
    }

    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity) {
        if (par1World.func_147439_a(par2, par3, par4) != this) {
            return;
        }
        if (!par1World.field_72995_K && par1World.func_72805_g(par2, par3, par4) == 2) {
            this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
        }
    }

    private void setStateIfMobInteractsWithPlate(World world, int x, int y, int z) {
        boolean var5 = world.func_72805_g(x, y, z) == 3;
        boolean var6 = false;
        float var7 = 0.125f;
        List var8 = null;
        String username = "";
        byte setting = 0;
        ArrayList accessList = new ArrayList();
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileArcanePressurePlate) {
            setting = ((TileArcanePressurePlate)tile).setting;
            username = ((TileArcanePressurePlate)tile).owner;
            accessList = ((TileArcanePressurePlate)tile).accessList;
        }
        if (setting == 0) {
            var8 = world.func_72839_b((Entity)null, AxisAlignedBB.func_72330_a((double)((float)x + var7), (double)y, (double)((float)z + var7), (double)((float)(x + 1) - var7), (double)((double)y + 0.25), (double)((float)(z + 1) - var7)));
        }
        if (setting == 1) {
            var8 = world.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)((float)x + var7), (double)y, (double)((float)z + var7), (double)((float)(x + 1) - var7), (double)((double)y + 0.25), (double)((float)(z + 1) - var7)));
        }
        if (setting == 2) {
            var8 = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((float)x + var7), (double)y, (double)((float)z + var7), (double)((float)(x + 1) - var7), (double)((double)y + 0.25), (double)((float)(z + 1) - var7)));
        }
        if (!var8.isEmpty()) {
            for (Entity var10 : var8) {
                if (var10.func_145773_az() || setting == 1 && var10 instanceof EntityPlayer && (((EntityPlayer)var10).func_70005_c_().equals(username) || accessList.contains("0" + ((EntityPlayer)var10).func_70005_c_()) || accessList.contains("1" + ((EntityPlayer)var10).func_70005_c_())) || setting == 2 && var10 instanceof EntityPlayer && !((EntityPlayer)var10).func_70005_c_().equals(username) && !accessList.contains("0" + ((EntityPlayer)var10).func_70005_c_()) && !accessList.contains("1" + ((EntityPlayer)var10).func_70005_c_())) continue;
                var6 = true;
                break;
            }
        }
        if (var6 && !var5) {
            world.func_72921_c(x, y, z, 3, 2);
            world.func_147459_d(x, y, z, (Block)this);
            world.func_147459_d(x, y - 1, z, (Block)this);
            world.func_147458_c(x, y, z, x, y, z);
            world.func_72908_a((double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.2f, 0.6f);
        }
        if (!var6 && var5) {
            world.func_72921_c(x, y, z, 2, 2);
            world.func_147459_d(x, y, z, (Block)this);
            world.func_147459_d(x, y - 1, z, (Block)this);
            world.func_147458_c(x, y, z, x, y, z);
            world.func_72908_a((double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.2f, 0.5f);
        }
        if (var6) {
            world.func_147464_a(x, y, z, (Block)this, this.tickRate());
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        if (par6 == 3) {
            par1World.func_147459_d(par2, par3, par4, (Block)this);
            par1World.func_147459_d(par2, par3 - 1, par4, (Block)this);
        } else if (par6 == 5) {
            InventoryUtils.dropItems(par1World, par2, par3, par4);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public int func_149748_c(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 1) {
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileSensor) {
                return ((TileSensor)tile).redstoneSignal > 0 ? 15 : 0;
            }
        } else {
            return world.func_72805_g(x, y, z) == 2 ? 0 : (side == 1 && world.func_72805_g(x, y, z) == 3 ? 15 : 0);
        }
        return super.func_149748_c(world, x, y, z, side);
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 1) {
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileSensor) {
                return ((TileSensor)tile).redstoneSignal > 0 ? 15 : 0;
            }
        } else if (meta == 3) {
            return 15;
        }
        return super.func_149748_c(world, x, y, z, side);
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149656_h() {
        return 1;
    }

    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 6 || meta == 7) {
            return 255;
        }
        return super.getLightOpacity(world, x, y, z);
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 2 || meta == 3) {
            return false;
        }
        return super.canEntityDestroy(world, x, y, z, entity);
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 6 || meta == 7 ? 20 : 0;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        int meta = world.func_72805_g(x, y, z);
        return meta == 6 || meta == 7 ? 5 : 0;
    }
}

