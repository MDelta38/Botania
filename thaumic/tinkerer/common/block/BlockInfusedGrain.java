/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.lib.research.ResearchManager
 */
package thaumic.tinkerer.common.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.research.ResearchManager;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileInfusedFarmland;
import thaumic.tinkerer.common.block.tile.TileInfusedGrain;
import thaumic.tinkerer.common.core.helper.AspectCropLootManager;
import thaumic.tinkerer.common.item.ItemInfusedSeeds;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockInfusedGrain
extends BlockCrops
implements ITTinkererBlock {
    public static final int BREEDING_CHANCE = 10;
    private IIcon[][] icons;

    public static int getNumberFromAspectForTexture(Aspect aspect) {
        if (aspect == Aspect.AIR) {
            return 0;
        }
        if (aspect == Aspect.FIRE) {
            return 1;
        }
        if (aspect == Aspect.WATER) {
            return 2;
        }
        if (aspect == Aspect.EARTH) {
            return 3;
        }
        if (aspect == Aspect.ORDER) {
            return 4;
        }
        if (aspect == Aspect.ENTROPY) {
            return 5;
        }
        return 6;
    }

    public static Aspect getAspect(IBlockAccess world, int x, int y, int z) {
        return world.func_147438_o(x, y, z) instanceof TileInfusedGrain ? ((TileInfusedGrain)world.func_147438_o((int)x, (int)y, (int)z)).aspect : null;
    }

    public static void setAspect(IBlockAccess world, int x, int y, int z, Aspect aspect) {
        if (world.func_147438_o(x, y, z) instanceof TileInfusedGrain) {
            ((TileInfusedGrain)world.func_147438_o((int)x, (int)y, (int)z)).aspect = aspect;
        }
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int meta) {
        if (meta < 7) {
            if (meta == 6) {
                meta = 5;
            }
            return this.icons[BlockInfusedGrain.getNumberFromAspectForTexture(BlockInfusedGrain.getAspect(world, x, y, z))][meta >> 1];
        }
        return this.icons[BlockInfusedGrain.getNumberFromAspectForTexture(BlockInfusedGrain.getAspect(world, x, y, z))][3];
    }

    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return this.icons[1][0];
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return LibRenderIDs.idGrain;
    }

    public void func_149651_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[7][4];
        String[] names = new String[]{"aer", "ignis", "aqua", "terra", "ordo", "perditio", "generic"};
        for (int j = 0; j < names.length; ++j) {
            String s = names[j];
            for (int i = 0; i < 4; ++i) {
                this.icons[j][i] = IconHelper.forName(par1IconRegister, "crop_" + s + "_" + i);
            }
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        Random rand = new Random();
        int count = 1;
        for (int i = 0; i < count; ++i) {
            ItemStack seedStack = new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedSeeds.class));
            ItemInfusedSeeds.setAspect(seedStack, this.getAspectDropped(world, x, y, z, metadata));
            ItemInfusedSeeds.setAspectTendencies(seedStack, ((TileInfusedGrain)world.func_147438_o((int)x, (int)y, (int)z)).primalTendencies);
            while ((double)rand.nextInt(10000) < Math.pow(this.getPrimalTendencyCount(world, x, y, z, Aspect.ENTROPY), 2.0)) {
                ++seedStack.field_77994_a;
            }
            ret.add(seedStack);
            this.fertilizeSoil(world, x, y, z, metadata);
        }
        if (metadata >= 7) {
            do {
                ret.add(AspectCropLootManager.getLootForAspect(BlockInfusedGrain.getAspect((IBlockAccess)world, x, y, z)));
            } while (world.field_73012_v.nextInt(75) < this.getPrimalTendencyCount(world, x, y, z, Aspect.ORDER));
        }
        for (ItemStack item : ret) {
            float f = 0.7f;
            double d0 = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double d1 = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            double d2 = (double)(rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
            EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
            entityitem.field_145804_b = 10;
            world.func_72838_d((Entity)entityitem);
        }
        super.func_149749_a(world, x, y, z, block, metadata);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public int getPrimalTendencyCount(World world, int x, int y, int z, Aspect aspect) {
        return world.func_147438_o(x, y, z) instanceof TileInfusedGrain ? ((TileInfusedGrain)world.func_147438_o((int)x, (int)y, (int)z)).primalTendencies.getAmount(aspect) : 0;
    }

    private void fertilizeSoil(World world, int x, int y, int z, int metadata) {
        if (metadata >= 7) {
            do {
                if (!(world.func_147438_o(x, y - 1, z) instanceof TileInfusedFarmland)) continue;
                Aspect currentAspect = BlockInfusedGrain.getAspect((IBlockAccess)world, x, y, z);
                ((TileInfusedFarmland)world.func_147438_o((int)x, (int)(y - 1), (int)z)).aspectList.add(currentAspect, 1);
                ((TileInfusedFarmland)world.func_147438_o(x, y - 1, z)).reduceSaturatedAspects();
                world.func_147471_g(x, y - 1, z);
            } while (world.field_73012_v.nextInt(55) < this.getPrimalTendencyCount(world, x, y, z, Aspect.EARTH));
        }
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        this.func_149855_e(world, x, y, z);
    }

    public Aspect getAspectDropped(World world, int x, int y, int z, int metadata) {
        Aspect currentAspect = BlockInfusedGrain.getAspect((IBlockAccess)world, x, y, z);
        if (metadata >= 7 && currentAspect != null && world.func_147438_o(x, y - 1, z) instanceof TileInfusedFarmland) {
            AspectList farmlandAspectList = ((TileInfusedFarmland)world.func_147438_o((int)x, (int)(y - 1), (int)z)).aspectList;
            for (Aspect aspect : farmlandAspectList.getAspects()) {
                Random rand = new Random();
                if (rand.nextInt(10) >= (this.getPrimalTendencyCount(world, x, y, z, Aspect.FIRE) + 1) * farmlandAspectList.getAmount(aspect) * farmlandAspectList.getAmount(aspect) || ResearchManager.getCombinationResult((Aspect)aspect, (Aspect)currentAspect) == null) continue;
                return ResearchManager.getCombinationResult((Aspect)aspect, (Aspect)currentAspect);
            }
        }
        return currentAspect;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "infusedGrainBlock";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return false;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileInfusedGrain.class;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileInfusedGrain();
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

