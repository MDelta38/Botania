/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool.terrasteel;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;

public class ItemTerraSword
extends ItemManasteelSword
implements ILensEffect,
ICraftAchievement {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final int MANA_PER_DAMAGE = 100;

    public ItemTerraSword() {
        super(BotaniaAPI.terrasteelToolMaterial, "terraSword");
    }

    @Override
    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        super.func_77663_a(par1ItemStack, par2World, par3Entity, par4, par5);
        if (par3Entity instanceof EntityPlayer) {
            float check;
            EntityPlayer player = (EntityPlayer)par3Entity;
            PotionEffect haste = player.func_70660_b(Potion.field_76422_e);
            float f = haste == null ? 0.16666667f : (check = haste.func_76458_c() == 1 ? 0.5f : 0.4f);
            if (player.func_71045_bC() == par1ItemStack && player.field_70733_aJ == check && !par2World.field_72995_K && par2World.field_73012_v.nextInt(2) == 0) {
                EntityManaBurst burst = this.getBurst(player, par1ItemStack);
                par2World.func_72838_d((Entity)burst);
                ToolCommons.damageItem(par1ItemStack, 1, (EntityLivingBase)player, 100);
                par2World.func_72956_a((Entity)player, "botania:terraBlade", 0.4f, 1.4f);
            }
        }
    }

    @Override
    public int getManaPerDamage() {
        return 100;
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(player);
        float motionModifier = 7.0f;
        burst.setColor(0x20FF20);
        burst.setMana(100);
        burst.setStartingMana(100);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(4.0f);
        burst.setGravity(0.0f);
        burst.setMotion(burst.field_70159_w * (double)motionModifier, burst.field_70181_x * (double)motionModifier, burst.field_70179_y * (double)motionModifier);
        ItemStack lens = stack.func_77946_l();
        ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.func_70005_c_());
        burst.setSourceLens(lens);
        return burst;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
    }

    @Override
    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.func_72330_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v, (double)entity.field_70142_S, (double)entity.field_70137_T, (double)entity.field_70136_U).func_72314_b(1.0, 1.0, 1.0);
        List entities = entity.field_70170_p.func_72872_a(EntityLivingBase.class, axis);
        String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
        for (EntityLivingBase living : entities) {
            if (living instanceof EntityPlayer && (((EntityPlayer)living).func_70005_c_().equals(attacker) || MinecraftServer.func_71276_C() != null && !MinecraftServer.func_71276_C().func_71219_W()) || living.field_70737_aN != 0) continue;
            int cost = 33;
            int mana = burst.getMana();
            if (mana < cost) continue;
            burst.setMana(mana - cost);
            float damage = 4.0f + BotaniaAPI.terrasteelToolMaterial.func_78000_c();
            if (burst.isFake() || entity.field_70170_p.field_72995_K) continue;
            EntityPlayer player = living.field_70170_p.func_72924_a(attacker);
            living.func_70097_a(player == null ? DamageSource.field_76376_m : DamageSource.func_76365_a((EntityPlayer)player), damage);
            entity.func_70106_y();
            break;
        }
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
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

