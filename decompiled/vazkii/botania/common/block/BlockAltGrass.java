/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockAltGrass
extends BlockMod
implements ILexiconable {
    private static final int SUBTYPES = 6;
    IIcon[] icons;

    public BlockAltGrass() {
        super(Material.field_151577_b);
        this.func_149711_c(0.6f);
        this.func_149672_a(field_149779_h);
        this.func_149663_c("altGrass");
        this.func_149675_a(true);
    }

    public boolean isToolEffective(String type, int metadata) {
        return type.equals("shovel");
    }

    @Override
    public Block func_149663_c(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        return super.func_149663_c(par1Str);
    }

    @Override
    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 6; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[12];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock(par1IconRegister, (Block)this, i);
        }
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == 0 || meta >= 6 ? Blocks.field_150346_d.func_149691_a(side, meta) : (side == 1 ? this.icons[meta * 2] : this.icons[meta * 2 + 1]);
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        if (!world.field_72995_K && world.func_72957_l(x, y + 1, z) >= 9) {
            int meta = world.func_72805_g(x, y, z);
            for (int l = 0; l < 4; ++l) {
                int i1 = x + rand.nextInt(3) - 1;
                int j1 = y + rand.nextInt(5) - 3;
                int k1 = z + rand.nextInt(3) - 1;
                world.func_147439_a(i1, j1 + 1, k1);
                if (world.func_147439_a(i1, j1, k1) != Blocks.field_150346_d || world.func_72805_g(i1, j1, k1) != 0 || world.func_72957_l(i1, j1 + 1, k1) < 4 || world.getBlockLightOpacity(i1, j1 + 1, k1) > 2) continue;
                world.func_147465_d(i1, j1, k1, (Block)this, meta, 3);
            }
        }
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Blocks.field_150346_d.func_149650_a(0, p_149650_2_, p_149650_3_);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return new ItemStack((Block)this, 1, world.func_72805_g(x, y, z));
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        EnumPlantType type = plantable.getPlantType(world, x, y - 1, z);
        return type == EnumPlantType.Plains || type == EnumPlantType.Beach;
    }

    public void func_149734_b(World world, int x, int y, int z, Random r) {
        int meta = world.func_72805_g(x, y, z);
        switch (meta) {
            case 0: {
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                break;
            }
            case 3: {
                if (r.nextInt(80) != 0) break;
                world.func_72869_a("flame", (double)((float)x + r.nextFloat()), (double)y + 1.1, (double)((float)z + r.nextFloat()), 0.0, 0.0, 0.0);
                break;
            }
            case 4: {
                if (r.nextInt(100) != 0) break;
                Botania.proxy.sparkleFX(world, (float)x + r.nextFloat(), (double)y + 1.05, (float)z + r.nextFloat(), 0.0f, 1.0f, 1.0f, r.nextFloat() * 0.2f + 1.0f, 5);
                break;
            }
            case 5: {
                if (r.nextInt(100) != 0) break;
                if (r.nextInt(100) > 25) {
                    Botania.proxy.sparkleFX(world, (float)x + r.nextFloat(), (double)y + 1.05, (float)z + r.nextFloat(), 1.0f, 0.0f, 1.0f, r.nextFloat() * 0.2f + 1.0f, 5);
                    break;
                }
                Botania.proxy.sparkleFX(world, (float)x + r.nextFloat(), (double)y + 1.05, (float)z + r.nextFloat(), 1.0f, 1.0f, 0.0f, r.nextFloat() * 0.2f + 1.0f, 5);
            }
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.grassSeeds;
    }
}

