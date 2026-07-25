/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileRepairer;
import thaumic.tinkerer.common.compat.TinkersConstructCompat;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockRepairer
extends BlockModContainer {
    Random random;

    public BlockRepairer() {
        super(Material.field_151573_f);
        this.func_149711_c(5.0f);
        this.func_149752_b(10.0f);
        this.random = new Random();
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileRepairer repairer = (TileRepairer)par1World.func_147438_o(par2, par3, par4);
        ItemStack stack = repairer.func_70301_a(0);
        if (stack == null) {
            ItemStack playerStack = par5EntityPlayer.func_71045_bC();
            if (repairer.func_102007_a(0, playerStack, 1)) {
                repairer.func_70299_a(0, playerStack.func_77979_a(1));
                if (playerStack.field_77994_a <= 0) {
                    par5EntityPlayer.field_71071_by.func_70299_a(par5EntityPlayer.field_71071_by.field_70461_c, null);
                }
                repairer.func_70296_d();
                return true;
            }
        } else {
            if (!par5EntityPlayer.field_71071_by.func_70441_a(stack)) {
                par5EntityPlayer.func_71019_a(stack, false);
            }
            repairer.func_70299_a(0, null);
            repairer.func_70296_d();
            return true;
        }
        return false;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int b0 = 0;
        int l1 = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l1 == 0) {
            b0 = 2;
        }
        if (l1 == 1) {
            b0 = 5;
        }
        if (l1 == 2) {
            b0 = 3;
        }
        if (l1 == 3) {
            b0 = 4;
        }
        par1World.func_72921_c(par2, par3, par4, b0, 2);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileRepairer repairer = (TileRepairer)par1World.func_147438_o(par2, par3, par4);
        if (repairer != null) {
            for (int j1 = 0; j1 < repairer.func_70302_i_(); ++j1) {
                ItemStack itemstack = repairer.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    int dmg = Loader.isModLoaded((String)"TConstruct") ? (TinkersConstructCompat.isTConstructTool(itemstack) ? TinkersConstructCompat.getDamage(itemstack) : itemstack.func_77960_j()) : itemstack.func_77960_j();
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, dmg));
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return ConfigBlocks.blockCosmeticSolid.func_149691_a(par1, 4);
    }

    public int func_149645_b() {
        return LibRenderIDs.idRepairer;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileRepairer();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "repairer";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileRepairer.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("REPAIRER", new AspectList().add(Aspect.TOOL, 2).add(Aspect.CRAFT, 1).add(Aspect.ORDER, 1).add(Aspect.MAGIC, 1), -1, -9, 3, new ItemStack((Block)this), new ResearchPage[0]).setConcealed().setParents(new String[]{"FUNNEL"}).setParentsHidden(new String[]{"THAUMIUM", "ENCHFABRIC"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("REPAIRER")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("REPAIRER", new ItemStack((Block)this), 8, new AspectList().add(Aspect.TOOL, 15).add(Aspect.CRAFT, 20).add(Aspect.ORDER, 10).add(Aspect.MAGIC, 15), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151045_i), new ItemStack(Blocks.field_150347_e), new ItemStack(Blocks.field_150344_f), new ItemStack(Items.field_151116_aA), new ItemStack(ConfigItems.itemResource, 1, 7), new ItemStack(ConfigItems.itemResource, 1, 2));
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149748_c(IBlockAccess world, int x, int y, int z, int meta) {
        TileRepairer tile = (TileRepairer)world.func_147438_o(x, y, z);
        if (tile.func_70301_a(0) != null && tile.func_70301_a(0).func_77960_j() != 0) {
            return 15;
        }
        return 0;
    }
}

