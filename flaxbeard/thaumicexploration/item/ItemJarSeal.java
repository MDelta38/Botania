/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.codechicken.lib.colour.ColourRGBA
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.tiles.TileJarFillable
 */
package flaxbeard.thaumicexploration.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.data.BoundJarNetworkManager;
import flaxbeard.thaumicexploration.item.ItemChestSeal;
import flaxbeard.thaumicexploration.misc.NBTHelper;
import flaxbeard.thaumicexploration.tile.TileEntityBoundJar;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.codechicken.lib.colour.ColourRGBA;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileJarFillable;

public class ItemJarSeal
extends Item {
    public IIcon theIcon;

    public void func_94581_a(IIconRegister p_94581_1_) {
        super.func_94581_a(p_94581_1_);
        this.theIcon = p_94581_1_.func_94245_a(this.field_111218_cA + "Inset");
    }

    public ItemJarSeal() {
        this.func_77656_e(0);
        this.func_77627_a(true);
        this.func_77625_d(64);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float dx, float dy, float dz) {
        if (world.func_147439_a(x, y, z) == ConfigBlocks.blockJar) {
            TileJarFillable entity = (TileJarFillable)world.func_147438_o(x, y, z);
            if (entity.amount == 0 && entity.aspectFilter == null) {
                world.func_147449_b(x, y, z, ThaumicExploration.boundJar);
                String id = this.getNetwork(stack);
                TileEntityBoundJar tileEntity = (TileEntityBoundJar)world.func_147438_o(x, y, z);
                tileEntity.colour = stack.func_77960_j();
                if (id != null) {
                    tileEntity.networkName = id;
                    AspectList aList = BoundJarNetworkManager.getAspect(id);
                    tileEntity.aspect = aList.getAspects()[0];
                    tileEntity.amount = aList.getAmount(tileEntity.aspect);
                    tileEntity.func_70296_d();
                }
                world.func_147471_g(x, y, z);
                --player.field_71071_by.func_70448_g().field_77994_a;
                if (player.field_71071_by.func_70448_g().field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
        } else if (world.func_147439_a(x, y, z) == ThaumicExploration.boundJar) {
            TileEntityBoundJar tileEntity = (TileEntityBoundJar)world.func_147438_o(x, y, z);
            ItemStack newStack = new ItemStack((Item)this, 1, tileEntity.colour);
            this.setNetwork(newStack, tileEntity.networkName);
            --player.field_71071_by.func_70448_g().field_77994_a;
            if (player.field_71071_by.func_70448_g().field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
            if (!player.field_71071_by.func_70441_a(newStack)) {
                player.func_71019_a(newStack, true);
            }
        }
        return super.func_77648_a(stack, player, world, x, y, z, side, dx, dy, dz);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List p_150895_3_) {
        for (int i = 0; i < 16; ++i) {
            ItemStack stack = new ItemStack((Item)this, 1, i);
            p_150895_3_.add(stack);
        }
    }

    public int func_82790_a(ItemStack p_82790_1_, int p_82790_2_) {
        int damage = p_82790_1_.func_77960_j();
        int color = ItemDye.field_150922_c[damage];
        ColourRGBA colour = new ColourRGBA(color);
        if (p_82790_2_ == 0) {
            return colour.rgba();
        }
        if (this.getNetwork(p_82790_1_) != null) {
            return colour.invert().rgba();
        }
        return colour.rgba();
    }

    public IIcon func_77618_c(int p_77618_1_, int p_77618_2_) {
        switch (p_77618_2_) {
            case 0: {
                return this.field_77791_bV;
            }
            case 1: {
                return this.theIcon;
            }
        }
        return this.field_77791_bV;
    }

    public boolean func_77623_v() {
        return true;
    }

    public String func_77667_c(ItemStack item) {
        if (item.func_77960_j() <= 15) {
            return this.func_77658_a() + ":" + ItemChestSeal.itemNames[15 - item.func_77960_j()];
        }
        return "";
    }

    public String getNetwork(ItemStack stack) {
        if (stack.func_82837_s()) {
            return stack.func_82833_r();
        }
        if (NBTHelper.getItemStackTag(stack).func_74764_b("network")) {
            return NBTHelper.getItemStackTag(stack).func_74779_i("network");
        }
        return null;
    }

    public void setNetwork(ItemStack stack, String networkName) {
        NBTHelper.getItemStackTag(stack).func_74778_a("network", networkName);
    }

    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        String network = this.getNetwork(p_77624_1_);
        if (network != null) {
            p_77624_3_.add(StatCollector.func_74837_a((String)"txitems.boundJar.networkInfo", (Object[])new Object[]{network}));
        } else {
            p_77624_3_.add(StatCollector.func_74838_a((String)"txitems.boundJar.noNetworkInfo"));
        }
    }
}

