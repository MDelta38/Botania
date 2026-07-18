/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityFallingStar;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;

public class ItemStarSword
extends ItemManasteelSword
implements ICraftAchievement {
    private static final int MANA_PER_DAMAGE = 120;

    public ItemStarSword() {
        super(BotaniaAPI.terrasteelToolMaterial, "starSword");
    }

    @Override
    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
        if (par3Entity instanceof EntityPlayer) {
            MovingObjectPosition pos;
            float check;
            EntityPlayer player = (EntityPlayer)par3Entity;
            PotionEffect haste = player.func_70660_b(Potion.field_76422_e);
            float f = haste == null ? 0.16666667f : (check = haste.func_76458_c() == 1 ? 0.5f : 0.4f);
            if (player.func_71045_bC() == par1ItemStack && player.field_70733_aJ == check && !par2World.field_72995_K && par2World.field_73012_v.nextInt(2) == 0 && (pos = ToolCommons.raytraceFromEntity(par2World, par3Entity, true, 48.0)) != null) {
                Vector3 posVec = new Vector3(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                Vector3 motVec = new Vector3((Math.random() - 0.5) * 18.0, 24.0, (Math.random() - 0.5) * 18.0);
                posVec.add(motVec);
                motVec.normalize().negate().multiply(1.5);
                EntityFallingStar star = new EntityFallingStar(par2World, (EntityLivingBase)player);
                star.func_70107_b(posVec.x, posVec.y, posVec.z);
                star.field_70159_w = motVec.x;
                star.field_70181_x = motVec.y;
                star.field_70179_y = motVec.z;
                par2World.func_72838_d((Entity)star);
                ToolCommons.damageItem(par1ItemStack, 1, (EntityLivingBase)player, 120);
                par2World.func_72956_a((Entity)player, "botania:starcaller", 0.4f, 1.4f);
            }
        }
    }

    @Override
    public int getManaPerDamage() {
        return 120;
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 4 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.terrasteelWeaponCraft;
    }
}

