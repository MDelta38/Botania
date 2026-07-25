/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionWakingNightmare
extends PotionBase
implements IHandleLivingUpdate {
    public PotionWakingNightmare(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void postContructInitialize() {
        this.setPermenant();
        this.setIncurable();
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 20L == 3L && entity.field_71093_bK != Config.instance().dimensionDreamID && world.field_73012_v.nextInt(amplifier > 3 ? 30 : (amplifier > 1 ? 60 : 180)) == 0) {
            double R = 16.0;
            double H = 8.0;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - 16.0), (double)(entity.field_70163_u - 8.0), (double)(entity.field_70161_v - 16.0), (double)(entity.field_70165_t + 16.0), (double)(entity.field_70163_u + 8.0), (double)(entity.field_70161_v + 16.0));
            List entities = world.func_72872_a(EntityNightmare.class, bounds);
            boolean doNothing = false;
            for (EntityNightmare nightmare : entities) {
                if (!nightmare.getVictimName().equalsIgnoreCase(entity.func_70005_c_())) continue;
                doNothing = true;
                break;
            }
            if (!doNothing) {
                Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c((double)entity.field_70165_t), MathHelper.func_76128_c((double)entity.field_70163_u), MathHelper.func_76128_c((double)entity.field_70161_v), entity, 2, 6, null, SoundEffect.NONE);
            }
        }
    }
}

