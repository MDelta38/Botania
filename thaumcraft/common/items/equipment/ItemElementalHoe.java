/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items.equipment;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCustomPlant;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.Utils;

public class ItemElementalHoe
extends ItemHoe
implements IRepairable {
    public IIcon icon;

    public ItemElementalHoe(Item.ToolMaterial enumtoolmaterial) {
        super(enumtoolmaterial);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:elementalhoe");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    public int func_77619_b() {
        return 5;
    }

    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 2)) ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (player.func_70093_af()) {
            return super.func_77648_a(stack, player, world, x, y, z, par7, par8, par9, par10);
        }
        boolean did = false;
        for (int xx = -1; xx <= 1; ++xx) {
            for (int zz = -1; zz <= 1; ++zz) {
                if (!super.func_77648_a(stack, player, world, x + xx, y, z + zz, par7, par8, par9, par10)) continue;
                Thaumcraft.proxy.blockSparkle(world, x + xx, y, z + zz, 8401408, 2);
                if (did) continue;
                did = true;
            }
        }
        if (!did) {
            did = Utils.useBonemealAtLoc(world, player, x, y, z);
            if (!did) {
                Block bi = world.func_147439_a(x, y, z);
                int md = world.func_72805_g(x, y, z);
                if (bi == ConfigBlocks.blockCustomPlant && md == 0 && stack.func_77960_j() + 20 <= stack.func_77958_k()) {
                    ((BlockCustomPlant)bi).growGreatTree(world, x, y, z, world.field_73012_v);
                    stack.func_77972_a(5, (EntityLivingBase)player);
                    Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
                    did = true;
                } else if (bi == ConfigBlocks.blockCustomPlant && md == 1 && stack.func_77960_j() + 150 <= stack.func_77958_k()) {
                    ((BlockCustomPlant)bi).growSilverTree(world, x, y, z, world.field_73012_v);
                    stack.func_77972_a(25, (EntityLivingBase)player);
                    Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
                    did = true;
                }
            } else {
                stack.func_77972_a(1, (EntityLivingBase)player);
                Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 3);
            }
            if (did) {
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 0.75f, 0.9f + world.field_73012_v.nextFloat() * 0.2f);
            }
        }
        return did;
    }
}

