/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemManaBean;
import thaumcraft.common.tiles.TileManaPod;

public class BlockManaPod
extends Block {
    public IIcon[] icon = new IIcon[3];
    static HashMap<WorldCoordinates, Aspect> st = new HashMap();

    public BlockManaPod() {
        super(Material.field_151585_k);
        this.func_149675_a(true);
        this.field_149782_v = 0.5f;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icon[0] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_0");
        this.icon[1] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_1");
        this.icon[2] = par1IconRegister.func_94245_a("thaumcraft:manapod_stem_2");
    }

    public float func_149712_f(World world, int x, int y, int z) {
        float md = 8 - world.func_72805_g(x, y, z);
        return super.func_149712_f(world, x, y, z) / md;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return meta == 0 ? this.icon[0] : (meta == 1 ? this.icon[1] : this.icon[2]);
    }

    public int func_149645_b() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        this.func_149719_a((IBlockAccess)p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    }

    public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        int l = p_149719_1_.func_72805_g(p_149719_2_, p_149719_3_, p_149719_4_);
        switch (l) {
            case 0: {
                this.func_149676_a(0.25f, BlockRenderer.W12, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 1: {
                this.func_149676_a(0.25f, BlockRenderer.W10, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 2: {
                this.func_149676_a(0.25f, BlockRenderer.W8, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 3: {
                this.func_149676_a(0.25f, BlockRenderer.W6, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 4: {
                this.func_149676_a(0.25f, BlockRenderer.W5, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 5: {
                this.func_149676_a(0.25f, BlockRenderer.W4, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 6: {
                this.func_149676_a(0.25f, BlockRenderer.W3, 0.25f, 0.75f, 1.0f, 0.75f);
                break;
            }
            case 7: {
                this.func_149676_a(0.25f, BlockRenderer.W2, 0.25f, 0.75f, 1.0f, 0.75f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
        this.func_149719_a((IBlockAccess)p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
        return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!this.func_149718_j(par1World, par2, par3, par4)) {
            this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
            par1World.func_147468_f(par2, par3, par4);
        } else if (par1World.field_73012_v.nextInt(30) == 0) {
            TileEntity tile = par1World.func_147438_o(par2, par3, par4);
            if (tile != null && tile instanceof TileManaPod) {
                ((TileManaPod)tile).checkGrowth();
            }
            st.remove(new WorldCoordinates(par2, par3, par4, par1World.field_73011_w.field_76574_g));
        }
    }

    public boolean func_149718_j(World par1World, int par2, int par3, int par4) {
        BiomeGenBase biome = par1World.func_72807_a(par2, par4);
        boolean magicBiome = false;
        if (biome != null) {
            magicBiome = BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL);
        }
        Block i1 = par1World.func_147439_a(par2, par3 + 1, par4);
        return magicBiome && (i1 == Blocks.field_150364_r || i1 == Blocks.field_150363_s || i1 == ConfigBlocks.blockMagicalLog);
    }

    public boolean func_149707_d(World world, int x, int y, int z, int side) {
        Block i1;
        BiomeGenBase biome = world.func_72807_a(x, z);
        boolean magicBiome = false;
        if (biome != null) {
            magicBiome = BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL);
        }
        boolean b = (i1 = world.func_147439_a(x, y + 1, z)) == Blocks.field_150364_r || i1 == Blocks.field_150363_s || i1 == ConfigBlocks.blockMagicalLog;
        return side == 0 && b && magicBiome;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z);
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        if (!this.func_149718_j(par1World, par2, par3, par4)) {
            this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
            par1World.func_147468_f(par2, par3, par4);
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileManaPod && ((TileManaPod)tile).aspect != null) {
            st.put(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g), ((TileManaPod)tile).aspect);
        }
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> dropped = new ArrayList<ItemStack>();
        if (metadata < 2) {
            return dropped;
        }
        int b0 = 1;
        if (metadata == 7 && world.field_73012_v.nextFloat() > 0.33f) {
            b0 = 2;
        }
        Aspect aspect = Aspect.PLANT;
        if (st.containsKey(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g))) {
            aspect = st.get(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
        } else {
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileManaPod && ((TileManaPod)tile).aspect != null) {
                aspect = ((TileManaPod)tile).aspect;
            }
        }
        for (int k1 = 0; k1 < b0; ++k1) {
            ItemStack i = new ItemStack(ConfigItems.itemManaBean);
            ((ItemManaBean)i.func_77973_b()).setAspects(i, new AspectList().add(aspect, 1));
            dropped.add(i);
        }
        st.remove(new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
        return dropped;
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ConfigItems.itemManaBean;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public boolean func_149655_b(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        return true;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileManaPod();
    }
}

