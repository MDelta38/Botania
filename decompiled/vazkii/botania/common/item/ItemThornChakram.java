/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.item.ItemMod;

public class ItemThornChakram
extends ItemMod
implements ICraftAchievement {
    IIcon iconFire;

    public ItemThornChakram() {
        this.func_77655_b("thornChakram");
        this.func_77625_d(6);
        this.func_77627_a(true);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconFire = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon func_77617_a(int dmg) {
        return dmg == 0 ? this.field_77791_bV : this.iconFire;
    }

    public String func_77667_c(ItemStack stack) {
        return super.func_77658_a() + stack.func_77960_j();
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        --p_77659_1_.field_77994_a;
        p_77659_2_.func_72956_a((Entity)p_77659_3_, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!p_77659_2_.field_72995_K) {
            EntityThornChakram c = new EntityThornChakram(p_77659_2_, (EntityLivingBase)p_77659_3_);
            c.setFire(p_77659_1_.func_77960_j() != 0);
            p_77659_2_.func_72838_d((Entity)c);
        }
        return p_77659_1_;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.terrasteelWeaponCraft;
    }
}

