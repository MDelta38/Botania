/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.Facing
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class ItemTrunkSpawner
extends Item {
    private IIcon icon;

    public ItemTrunkSpawner() {
        this.func_77625_d(1);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icon = par1IconRegister.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77942_o()) {
            if (stack.field_77990_d.func_74764_b("upgrade")) {
                byte ba = stack.field_77990_d.func_74771_c("upgrade");
                String text = "\u00a79";
                if (ba > -1) {
                    text = text + StatCollector.func_74838_a((String)("item.ItemGolemUpgrade." + ba + ".name")) + " ";
                }
                list.add(text);
            }
            if (stack.field_77990_d.func_74764_b("inventory")) {
                list.add(StatCollector.func_74838_a((String)"item.TrunkSpawner.text.1"));
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        EntityTravelingTrunk entity;
        if (par3World.field_72995_K) {
            return true;
        }
        Block i1 = par3World.func_147439_a(par4, par5, par6);
        double d0 = 0.0;
        if (par7 == 1 && !i1.isAir((IBlockAccess)par3World, par4 += Facing.field_71586_b[par7], par5 += Facing.field_71587_c[par7], par6 += Facing.field_71585_d[par7]) && i1.func_149645_b() == 11) {
            d0 = 0.5;
        }
        if ((entity = new EntityTravelingTrunk(par3World)) != null && entity instanceof EntityLivingBase) {
            EntityLiving entityliving = entity;
            entity.func_70012_b(par4, (double)par5 + d0, par6, MathHelper.func_76142_g((float)(par3World.field_73012_v.nextFloat() * 360.0f)), 0.0f);
            entityliving.field_70759_as = entityliving.field_70177_z;
            entityliving.field_70761_aq = entityliving.field_70177_z;
            entity.setOwner(par2EntityPlayer.func_70005_c_());
            if (stack.func_82837_s()) {
                ((EntityLiving)entity).func_94058_c(stack.func_82833_r());
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("upgrade")) {
                entity.setUpgrade(stack.field_77990_d.func_74771_c("upgrade"));
                entity.setInvSize();
            }
            if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("inventory")) {
                NBTTagList nbttaglist = stack.field_77990_d.func_150295_c("inventory", 10);
                entity.inventory.readFromNBT(nbttaglist);
            }
            entityliving.func_110161_a((IEntityLivingData)null);
            par3World.func_72838_d((Entity)entity);
            entityliving.func_70642_aH();
            if (!par2EntityPlayer.field_71075_bZ.field_75098_d) {
                --stack.field_77994_a;
            }
        }
        return true;
    }
}

