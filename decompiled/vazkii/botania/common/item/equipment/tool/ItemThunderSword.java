/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 */
package vazkii.botania.common.item.equipment.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;

public class ItemThunderSword
extends ItemManasteelSword
implements ICraftAchievement {
    private static final String TAG_LIGHTNING_SEED = "lightningSeed";

    public ItemThunderSword() {
        super(BotaniaAPI.terrasteelToolMaterial, "thunderSword");
    }

    @Override
    public boolean func_77644_a(ItemStack stack, EntityLivingBase entity, EntityLivingBase attacker) {
        if (!(entity instanceof EntityPlayer) && entity != null) {
            List entities;
            double range = 8.0;
            final ArrayList<EntityLivingBase> alreadyTargetedEntities = new ArrayList<EntityLivingBase>();
            int dmg = 5;
            long lightningSeed = ItemNBTHelper.getLong(stack, TAG_LIGHTNING_SEED, 0L);
            IEntitySelector selector = new IEntitySelector(){

                public boolean func_82704_a(Entity e) {
                    return e instanceof EntityLivingBase && e instanceof IMob && !(e instanceof EntityPlayer) && !alreadyTargetedEntities.contains(e);
                }
            };
            Random rand = new Random(lightningSeed);
            EntityLivingBase lightningSource = entity;
            for (int i = 0; i < 4 && !(entities = entity.field_70170_p.func_94576_a((Entity)lightningSource, AxisAlignedBB.func_72330_a((double)(lightningSource.field_70165_t - range), (double)(lightningSource.field_70163_u - range), (double)(lightningSource.field_70161_v - range), (double)(lightningSource.field_70165_t + range), (double)(lightningSource.field_70163_u + range), (double)(lightningSource.field_70161_v + range)), selector)).isEmpty(); ++i) {
                EntityLivingBase target = (EntityLivingBase)entities.get(rand.nextInt(entities.size()));
                if (attacker instanceof EntityPlayer) {
                    target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)attacker)), (float)dmg);
                } else {
                    target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)attacker), (float)dmg);
                }
                Botania.proxy.lightningFX(entity.field_70170_p, Vector3.fromEntityCenter((Entity)lightningSource), Vector3.fromEntityCenter((Entity)target), 1.0f, 96708, 0xAADFFF);
                alreadyTargetedEntities.add(target);
                lightningSource = target;
                --dmg;
            }
            if (!entity.field_70170_p.field_72995_K) {
                ItemNBTHelper.setLong(stack, TAG_LIGHTNING_SEED, entity.field_70170_p.field_73012_v.nextLong());
            }
        }
        return super.func_77644_a(stack, entity, attacker);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.terrasteelWeaponCraft;
    }
}

