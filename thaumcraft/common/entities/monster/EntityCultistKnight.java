/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import java.util.Random;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.monster.EntityCultist;

public class EntityCultistKnight
extends EntityCultist {
    public EntityCultistKnight(World p_i1745_1_) {
        super(p_i1745_1_);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.0, false));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AICultistHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(36.0);
    }

    @Override
    protected void func_82164_bB() {
        this.func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistPlate));
        this.func_70062_b(3, new ItemStack(ConfigItems.itemChestCultistPlate));
        this.func_70062_b(2, new ItemStack(ConfigItems.itemLegsCultistPlate));
        this.func_70062_b(1, new ItemStack(ConfigItems.itemBootsCultist));
        float f = this.field_70146_Z.nextFloat();
        float f2 = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.05f : 0.01f;
        if (f < f2) {
            int i = this.field_70146_Z.nextInt(5);
            if (i == 0) {
                this.func_70062_b(0, new ItemStack(ConfigItems.itemSwordVoid));
                this.func_70062_b(4, new ItemStack(ConfigItems.itemHelmetCultistRobe));
            } else {
                this.func_70062_b(0, new ItemStack(ConfigItems.itemSwordThaumium));
                if (this.field_70146_Z.nextBoolean()) {
                    this.func_70062_b(4, null);
                }
            }
        } else {
            this.func_70062_b(0, new ItemStack(Items.field_151040_l));
        }
    }

    @Override
    protected void func_82162_bC() {
        float f = this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (this.func_70694_bm() != null && this.field_70146_Z.nextFloat() < 0.25f * f) {
            EnchantmentHelper.func_77504_a((Random)this.field_70146_Z, (ItemStack)this.func_70694_bm(), (int)((int)(5.0f + f * (float)this.field_70146_Z.nextInt(18))));
        }
    }
}

