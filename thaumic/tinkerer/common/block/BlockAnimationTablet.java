/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockAnimationTablet
extends BlockModContainer {
    IIcon iconBottom;
    IIcon iconTop;
    IIcon iconSides;
    Random random;

    public BlockAnimationTablet() {
        super(Material.field_151573_f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.func_149711_c(3.0f);
        this.func_149752_b(50.0f);
        this.func_149672_a(field_149777_j);
        this.random = new Random();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.iconBottom = IconHelper.forBlock(par1IconRegister, (Block)this, 0);
        this.iconTop = IconHelper.forBlock(par1IconRegister, (Block)this, 1);
        this.iconSides = IconHelper.forBlock(par1IconRegister, (Block)this, 2);
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
        TileAnimationTablet tablet = (TileAnimationTablet)par1World.func_147438_o(par2, par3, par4);
        if (tablet != null) {
            if (tablet.getIsBreaking()) {
                // empty if block
            }
            for (int j1 = 0; j1 < tablet.func_70302_i_(); ++j1) {
                ItemStack itemstack = tablet.func_70301_a(j1);
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

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        boolean on;
        if (par1World.field_72995_K) {
            return;
        }
        boolean power = par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4);
        int meta = par1World.func_72805_g(par2, par3, par4);
        boolean bl = on = (meta & 8) != 0;
        if (power && !on) {
            par1World.func_147464_a(par2, par3, par4, (Block)this, this.func_149738_a(par1World));
            par1World.func_72921_c(par2, par3, par4, meta | 8, 4);
        } else if (!power && on) {
            par1World.func_72921_c(par2, par3, par4, meta & 7, 4);
        }
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile != null && tile instanceof TileAnimationTablet) {
            TileAnimationTablet tablet = (TileAnimationTablet)tile;
            if (tablet.redstone && tablet.swingProgress == 0) {
                tablet.findEntities(tablet.getTargetLoc());
                tablet.initiateSwing();
                par1World.func_147452_c(par2, par3, par4, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class), 0, 0);
            }
        }
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile;
        if (!par1World.field_72995_K && (tile = par1World.func_147438_o(par2, par3, par4)) != null) {
            TileAnimationTablet tablet = (TileAnimationTablet)tile;
            if (par5EntityPlayer.func_71045_bC() != null && par5EntityPlayer.func_71045_bC().func_77973_b() instanceof ItemWandCasting) {
                boolean activated;
                int meta = par1World.func_72805_g(par2, par3, par4);
                boolean bl = activated = (meta & 8) != 0;
                if (!activated && !tablet.getIsBreaking() && tablet.swingProgress == 0) {
                    par1World.func_72921_c(par2, par3, par4, meta == 5 ? 2 : meta + 1, 3);
                    par1World.func_72908_a((double)par2, (double)par3, (double)par4, "thaumcraft:tool", 0.6f, 1.0f);
                } else {
                    par5EntityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("ttmisc.animationTablet.notRotatable", new Object[0]));
                }
                return true;
            }
            par5EntityPlayer.openGui((Object)ThaumicTinkerer.instance, 0, par1World, par2, par3, par4);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return par1 == ForgeDirection.UP.ordinal() ? this.iconTop : (par1 == ForgeDirection.DOWN.ordinal() ? this.iconBottom : this.iconSides);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public TileEntity func_149915_a(World world, int par2) {
        return new TileAnimationTablet();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "animationTablet";
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
        return TileAnimationTablet.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("ANIMATION_TABLET", new AspectList().add(Aspect.MECHANISM, 2).add(Aspect.METAL, 1).add(Aspect.MOTION, 1).add(Aspect.ENERGY, 1), -8, 2, 4, new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class)), new ResearchPage[0]).setWarp(1).setParents(new String[]{"MAGNETS"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ANIMATION_TABLET")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("ANIMATION_TABLET", "ANIMATION_TABLET", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class)), new AspectList().add(Aspect.AIR, 25).add(Aspect.ORDER, 15).add(Aspect.FIRE, 10), "GIG", "ICI", Character.valueOf('G'), new ItemStack(Items.field_151043_k), Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('C'), new ItemStack(ConfigItems.itemGolemCore, 1, 100));
    }
}

