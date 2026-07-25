/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileMagnet;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.item.ItemBlockMagnet;
import thaumic.tinkerer.common.item.foci.ItemFocusTelekinesis;
import thaumic.tinkerer.common.registry.IMultiTileEntityBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.RecipeHelper;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockMagnet
extends BlockModContainer
implements IMultiTileEntityBlock {
    Random random;

    public BlockMagnet() {
        super(Material.field_151573_f);
        this.func_149676_a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.125f, 0.9375f);
        this.func_149711_c(1.7f);
        this.func_149752_b(1.0f);
        this.func_149672_a(Block.field_149766_f);
        this.random = new Random();
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() instanceof ItemWandCasting) {
            TileEntity tile = par1World.func_147438_o(par2, par3, par4);
            if (tile != null && tile instanceof TileMobMagnet) {
                par5EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 1, par1World, par2, par3, par4);
                if (!par1World.field_72995_K) {
                    par1World.func_72908_a((double)par2, (double)par3, (double)par4, "thaumcraft:key", 1.0f, 0.5f);
                }
            }
            return true;
        }
        int meta = par1World.func_72805_g(par2, par3, par4);
        par1World.func_72921_c(par2, par3, par4, (meta & 1) == 0 ? meta + 1 : meta - 1, 2);
        if (!par1World.field_72995_K) {
            par1World.func_72908_a((double)par2, (double)par3, (double)par4, "random.click", 1.0f, 0.5f);
        }
        return true;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileMobMagnet mobMagnet;
        TileMagnet magnet = (TileMagnet)par1World.func_147438_o(par2, par3, par4);
        TileMobMagnet tileMobMagnet = mobMagnet = magnet instanceof TileMobMagnet ? (TileMobMagnet)magnet : null;
        if (mobMagnet != null) {
            for (int j1 = 0; j1 < mobMagnet.func_70302_i_(); ++j1) {
                ItemStack itemstack = mobMagnet.func_70301_a(j1);
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
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
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

    public int func_149692_a(int par1) {
        switch (par1) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 1;
            }
        }
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        super.func_149666_a(par1, par2CreativeTabs, par3List);
        par3List.add(new ItemStack(par1, 1, 1));
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return Block.func_149684_b((String)"log").func_149691_a(par1, 1);
    }

    public int func_149645_b() {
        return LibRenderIDs.idMagnet;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public TileEntity func_149915_a(World world, int metadata) {
        return (metadata & 2) == 2 ? new TileMobMagnet() : new TileMagnet();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "magnet";
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
        return ItemBlockMagnet.class;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileMagnet.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("MAGNETS", new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.MOTION, 1).add(Aspect.SENSES, 1), -6, 3, 3, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"INTERFACE"}).setParentsHidden(new String[]{"FOCUS_TELEKINESIS"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), new ResearchPage("1"), ResearchHelper.arcaneRecipePage("MAGNET"), ResearchHelper.arcaneRecipePage("MOB_MAGNET"), ResearchHelper.crucibleRecipePage("MAGNETS")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererArcaneRecipe("MAGNET", "MAGNETS", new ItemStack((Block)this), new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.EARTH, 15).add(Aspect.ENTROPY, 5), " I ", "SIs", "WFW", Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('s'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('S'), new ItemStack(ConfigItems.itemShard), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog), Character.valueOf('F'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusTelekinesis.class))), new ThaumicTinkererArcaneRecipe("MOB_MAGNET", "MAGNETS", new ItemStack((Block)this, 1, 1), new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.EARTH, 15).add(Aspect.ENTROPY, 5), " G ", "SGs", "WFW", Character.valueOf('G'), RecipeHelper.oreDictOrStack(new ItemStack(Items.field_151043_k), "ingotCopper"), Character.valueOf('s'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('S'), new ItemStack(ConfigItems.itemShard), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog), Character.valueOf('F'), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusTelekinesis.class))));
    }

    @Override
    public HashMap<Class<? extends TileEntity>, String> getAdditionalTileEntities() {
        HashMap<Class<? extends TileEntity>, String> r = new HashMap<Class<? extends TileEntity>, String>();
        r.put(TileMobMagnet.class, "mobMagnet");
        return r;
    }
}

