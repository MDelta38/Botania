/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 */
package vazkii.botania.common.block.subtile.generating;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileNarslimmus
extends SubTileGenerating {
    public static final String TAG_WORLD_SPAWNED = "Botania:WorldSpawned";
    private static final int RANGE = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.ticksExisted % 5 == 0) {
            List slimes = this.supertile.func_145831_w().func_72872_a(EntitySlime.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 2), (double)(this.supertile.field_145848_d - 2), (double)(this.supertile.field_145849_e - 2), (double)(this.supertile.field_145851_c + 2 + 1), (double)(this.supertile.field_145848_d + 2), (double)(this.supertile.field_145849_e + 2 + 1)));
            for (EntitySlime slime : slimes) {
                if (!slime.getEntityData().func_74767_n(TAG_WORLD_SPAWNED) || slime.field_70128_L) continue;
                int size = slime.func_70809_q();
                int mul = (int)Math.pow(2.0, size);
                int mana = 820 * mul;
                if (!slime.field_70170_p.field_72995_K) {
                    slime.func_70106_y();
                    slime.field_70170_p.func_72956_a((Entity)slime, "mob.slime." + (size > 1 ? "big" : "small"), 1.0f, 0.02f);
                    this.mana = Math.min(this.getMaxMana(), this.mana + mana);
                    this.sync();
                }
                for (int j = 0; j < mul * 8; ++j) {
                    float f = slime.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
                    float f1 = slime.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
                    float f2 = MathHelper.func_76126_a((float)f) * (float)size * 0.5f * f1;
                    float f3 = MathHelper.func_76134_b((float)f) * (float)size * 0.5f * f1;
                    float f4 = slime.field_70170_p.field_73012_v.nextFloat() * (float)size * 0.5f * f1;
                    slime.field_70170_p.func_72869_a("slime", slime.field_70165_t + (double)f2, slime.field_70121_D.field_72338_b + (double)f4, slime.field_70161_v + (double)f3, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    @Override
    public int getMaxMana() {
        return 8000;
    }

    @Override
    public int getColor() {
        return 7455603;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.narslimmus;
    }

    public static class SpawnIntercepter {
        @SubscribeEvent
        public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
            if (event.entityLiving instanceof EntitySlime && event.getResult() != Event.Result.DENY && SpawnIntercepter.isSlimeChunk(event.entityLiving.field_70170_p, MathHelper.func_76128_c((double)event.x), MathHelper.func_76128_c((double)event.z))) {
                event.entityLiving.getEntityData().func_74757_a(SubTileNarslimmus.TAG_WORLD_SPAWNED, true);
            }
        }

        public static boolean isSlimeChunk(World world, int x, int z) {
            Chunk chunk = world.func_72938_d(x, z);
            return chunk.func_76617_a(987234911L).nextInt(10) == 0;
        }
    }
}

