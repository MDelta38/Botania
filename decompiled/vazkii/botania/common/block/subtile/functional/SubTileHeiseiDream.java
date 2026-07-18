/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;

public class SubTileHeiseiDream
extends SubTileFunctional {
    private static final int RANGE = 5;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int cost = 100;
        List mobs = this.supertile.func_145831_w().func_72872_a(IMob.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 5), (double)(this.supertile.field_145848_d - 5), (double)(this.supertile.field_145849_e - 5), (double)(this.supertile.field_145851_c + 5 + 1), (double)(this.supertile.field_145848_d + 5 + 1), (double)(this.supertile.field_145849_e + 5 + 1)));
        if (mobs.size() > 1 && this.mana >= 100) {
            for (IMob mob : mobs) {
                EntityLiving entity;
                if (!(mob instanceof EntityLiving) || !SubTileHeiseiDream.brainwashEntity(entity = (EntityLiving)mob, mobs)) continue;
                this.mana -= 100;
                this.sync();
                break;
            }
        }
    }

    public static boolean brainwashEntity(EntityLiving entity, List<IMob> mobs) {
        EntityLivingBase target = entity.func_70638_az();
        boolean did = false;
        if (target == null || !(target instanceof IMob)) {
            IMob newTarget;
            while ((newTarget = mobs.get(entity.field_70170_p.field_73012_v.nextInt(mobs.size()))) == entity) {
            }
            if (newTarget instanceof EntityLiving) {
                ArrayList entries = new ArrayList(entity.field_70714_bg.field_75782_a);
                entries.addAll(new ArrayList(entity.field_70715_bh.field_75782_a));
                for (EntityAITasks.EntityAITaskEntry entry : entries) {
                    if (entry.field_75733_a instanceof EntityAINearestAttackableTarget) {
                        SubTileHeiseiDream.messWithGetTargetAI((EntityAINearestAttackableTarget)entry.field_75733_a, (EntityLivingBase)((EntityLiving)newTarget));
                        did = true;
                        continue;
                    }
                    if (!(entry.field_75733_a instanceof EntityAIAttackOnCollide)) continue;
                    SubTileHeiseiDream.messWithAttackOnCollideAI((EntityAIAttackOnCollide)entry.field_75733_a);
                    did = true;
                }
                if (did) {
                    entity.func_70624_b((EntityLivingBase)((EntityLiving)newTarget));
                }
            }
        }
        return did;
    }

    private static void messWithGetTargetAI(EntityAINearestAttackableTarget aiEntry, EntityLivingBase target) {
        ReflectionHelper.setPrivateValue(EntityAINearestAttackableTarget.class, (Object)aiEntry, IMob.class, (String[])LibObfuscation.TARGET_CLASS);
        ReflectionHelper.setPrivateValue(EntityAINearestAttackableTarget.class, (Object)aiEntry, (Object)target, (String[])LibObfuscation.TARGET_ENTITY);
    }

    private static void messWithAttackOnCollideAI(EntityAIAttackOnCollide aiEntry) {
        ReflectionHelper.setPrivateValue(EntityAIAttackOnCollide.class, (Object)aiEntry, IMob.class, (String[])LibObfuscation.CLASS_TARGET);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 5);
    }

    @Override
    public int getColor() {
        return 16720285;
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.heiseiDream;
    }
}

