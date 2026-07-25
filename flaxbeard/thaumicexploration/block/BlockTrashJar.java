/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.blocks.BlockJar
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemEssence
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityTrashJar;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.BlockJar;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemEssence;

public class BlockTrashJar
extends BlockJar {
    private Random random;

    public int func_149645_b() {
        return ThaumicExploration.trashJarRenderID;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityTrashJar();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a((Block)ThaumicExploration.trashJar);
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        return drops;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEntity te;
        if (!world.field_72995_K) {
            world.func_147471_g(x, y, z);
        }
        if ((te = world.func_147438_o(x, y, z)) == null || !(te instanceof TileEntityTrashJar)) {
            return false;
        }
        TileEntityTrashJar jar = (TileEntityTrashJar)te;
        ItemStack helditem = player.func_70694_bm();
        if (helditem == null || !(helditem.func_77973_b() instanceof ItemEssence)) {
            return false;
        }
        ItemEssence phial = (ItemEssence)helditem.func_77973_b();
        Aspect aspect = null;
        int amount = 0;
        if (helditem.func_77960_j() == 1) {
            Aspect[] aspects = phial.getAspects(helditem).getAspects();
            if (aspects.length > 0) {
                aspect = aspects[0];
            }
            amount = phial.getAspects(helditem).getAmount(aspect);
        }
        if (helditem != null && jar.amount <= jar.maxAmount - 8 && (jar.aspect != null && jar.aspect != aspect && jar.amount == 0 || jar.aspect == null || jar.aspect != null && jar.aspect == aspect && amount >= 8)) {
            --player.func_70694_bm().field_77994_a;
            jar.aspect = aspect;
            jar.addToContainer(aspect, amount);
            if (!player.field_71071_by.func_70441_a(new ItemStack(ConfigItems.itemEssence, 1, 0))) {
                player.func_145779_a(ConfigItems.itemEssence, 1);
            }
            world.func_72956_a((Entity)player, "liquid.swim", 0.25f, 1.0f);
        } else if (helditem != null && jar.amount >= 8 && aspect == null) {
            player.field_71071_by.func_70298_a(player.field_71071_by.field_70461_c, 1);
            ItemStack newPhial = new ItemStack(ConfigItems.itemEssence, 1, 1);
            AspectList setAspect = new AspectList().add(jar.aspect, 8);
            ((ItemEssence)newPhial.func_77973_b()).setAspects(newPhial, setAspect);
            if (!player.field_71071_by.func_70441_a(newPhial)) {
                player.func_145779_a(newPhial.func_77973_b(), 1);
            }
            jar.takeFromContainer(jar.aspect, 8);
            world.func_72956_a((Entity)player, "liquid.swim", 0.25f, 1.0f);
        }
        return super.func_149727_a(world, x, y, z, player, par6, par7, par8, par9);
    }
}

