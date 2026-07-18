/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;

public class SubTileTigerseye
extends SubTileFunctional {
    private static final int RANGE = 10;
    private static final int RANGE_Y = 4;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int cost = 70;
        boolean shouldAfffect = this.mana >= 70;
        List entities = this.supertile.func_145831_w().func_72872_a(EntityLiving.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 10), (double)(this.supertile.field_145848_d - 4), (double)(this.supertile.field_145849_e - 10), (double)(this.supertile.field_145851_c + 10 + 1), (double)(this.supertile.field_145848_d + 4 + 1), (double)(this.supertile.field_145849_e + 10 + 1)));
        for (EntityLiving entity : entities) {
            ArrayList entries = new ArrayList(entity.field_70714_bg.field_75782_a);
            entries.addAll(new ArrayList(entity.field_70715_bh.field_75782_a));
            boolean avoidsOcelots = false;
            if (shouldAfffect) {
                for (EntityAITasks.EntityAITaskEntry entry : entries) {
                    if (entry.field_75733_a instanceof EntityAIAvoidEntity) {
                        boolean bl = avoidsOcelots = this.messWithRunAwayAI((EntityAIAvoidEntity)entry.field_75733_a) || avoidsOcelots;
                    }
                    if (!(entry.field_75733_a instanceof EntityAINearestAttackableTarget)) continue;
                    this.messWithGetTargetAI((EntityAINearestAttackableTarget)entry.field_75733_a);
                }
            }
            if (entity instanceof EntityCreeper) {
                ReflectionHelper.setPrivateValue(EntityCreeper.class, (Object)((EntityCreeper)entity), (Object)2, (String[])LibObfuscation.TIME_SINCE_IGNITED);
                entity.func_70624_b(null);
            }
            if (!avoidsOcelots) continue;
            this.mana -= 70;
            this.sync();
            shouldAfffect = false;
        }
    }

    private boolean messWithRunAwayAI(EntityAIAvoidEntity aiEntry) {
        if (ReflectionHelper.getPrivateValue(EntityAIAvoidEntity.class, (Object)aiEntry, (String[])LibObfuscation.TARGET_ENTITY_CLASS) == EntityOcelot.class) {
            ReflectionHelper.setPrivateValue(EntityAIAvoidEntity.class, (Object)aiEntry, EntityPlayer.class, (String[])LibObfuscation.TARGET_ENTITY_CLASS);
            return true;
        }
        return false;
    }

    private void messWithGetTargetAI(EntityAINearestAttackableTarget aiEntry) {
        if (ReflectionHelper.getPrivateValue(EntityAINearestAttackableTarget.class, (Object)aiEntry, (String[])LibObfuscation.TARGET_CLASS) == EntityPlayer.class) {
            ReflectionHelper.setPrivateValue(EntityAINearestAttackableTarget.class, (Object)aiEntry, EntityEnderCrystal.class, (String[])LibObfuscation.TARGET_CLASS);
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 10);
    }

    @Override
    public int getColor() {
        return 11642392;
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.tigerseye;
    }
}

