/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.MobSpawnerBaseLogic$WeightedRandomMinecart
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile;

import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.lib.LibObfuscation;

public class TileSpawnerClaw
extends TileMod
implements IManaReceiver {
    private static final String TAG_MANA = "mana";
    int mana = 0;

    public void func_145845_h() {
        TileEntityMobSpawner spawner;
        MobSpawnerBaseLogic logic;
        TileEntity tileBelow = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (this.mana >= 5 && tileBelow instanceof TileEntityMobSpawner && !(logic = (spawner = (TileEntityMobSpawner)tileBelow).func_145881_a()).func_98279_f()) {
            if (!this.field_145850_b.field_72995_K) {
                this.mana -= 6;
            }
            if (logic.func_98271_a().field_72995_K) {
                if (logic.field_98286_b > 0) {
                    --logic.field_98286_b;
                }
                if (Math.random() > 0.5) {
                    Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.3 + Math.random() * 0.5, (double)this.field_145848_d - 0.3 + Math.random() * 0.25, (double)this.field_145849_e + Math.random(), 0.6f - (float)Math.random() * 0.3f, 0.1f, 0.6f - (float)Math.random() * 0.3f, (float)Math.random() / 3.0f, -0.025f - 0.005f * (float)Math.random(), 2.0f);
                }
                logic.field_98284_d = logic.field_98287_c;
                logic.field_98287_c = (logic.field_98287_c + (double)(1000.0f / ((float)logic.field_98286_b + 200.0f))) % 360.0;
            } else if (logic.field_98286_b == -1) {
                this.resetTimer(logic);
            }
            if (logic.field_98286_b > 0) {
                --logic.field_98286_b;
                return;
            }
            boolean flag = false;
            int spawnCount = (Integer)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.SPAWN_COUNT);
            int spawnRange = (Integer)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.SPAWN_RANGE);
            int maxNearbyEntities = (Integer)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.MAX_NEARBY_ENTITIES);
            for (int i = 0; i < spawnCount; ++i) {
                Entity entity = EntityList.func_75620_a((String)logic.func_98276_e(), (World)logic.func_98271_a());
                if (entity == null) {
                    return;
                }
                int j = logic.func_98271_a().func_72872_a(entity.getClass(), AxisAlignedBB.func_72330_a((double)logic.func_98275_b(), (double)logic.func_98274_c(), (double)logic.func_98266_d(), (double)(logic.func_98275_b() + 1), (double)(logic.func_98274_c() + 1), (double)(logic.func_98266_d() + 1)).func_72314_b((double)(spawnRange * 2), 4.0, (double)(spawnRange * 2))).size();
                if (j >= maxNearbyEntities) {
                    this.resetTimer(logic);
                    return;
                }
                double d2 = (double)logic.func_98275_b() + (logic.func_98271_a().field_73012_v.nextDouble() - logic.func_98271_a().field_73012_v.nextDouble()) * (double)spawnRange;
                double d3 = logic.func_98274_c() + logic.func_98271_a().field_73012_v.nextInt(3) - 1;
                double d4 = (double)logic.func_98266_d() + (logic.func_98271_a().field_73012_v.nextDouble() - logic.func_98271_a().field_73012_v.nextDouble()) * (double)spawnRange;
                EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving)entity : null;
                entity.func_70012_b(d2, d3, d4, logic.func_98271_a().field_73012_v.nextFloat() * 360.0f, 0.0f);
                if (entityliving != null && !entityliving.func_70601_bi()) continue;
                if (!this.field_145850_b.field_72995_K) {
                    logic.func_98265_a(entity);
                }
                logic.func_98271_a().func_72926_e(2004, logic.func_98275_b(), logic.func_98274_c(), logic.func_98266_d(), 0);
                if (entityliving != null) {
                    entityliving.func_70656_aK();
                }
                flag = true;
            }
            if (flag) {
                this.resetTimer(logic);
            }
        }
    }

    private void resetTimer(MobSpawnerBaseLogic logic) {
        int maxSpawnDelay = (Integer)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.MAX_SPAWN_DELAY);
        int minSpawnDelay = (Integer)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.MIN_SPAWN_DELAY);
        List potentialEntitySpawns = (List)ReflectionHelper.getPrivateValue(MobSpawnerBaseLogic.class, (Object)logic, (String[])LibObfuscation.POTENTIAL_ENTITY_SPAWNS);
        if (maxSpawnDelay <= minSpawnDelay) {
            logic.field_98286_b = minSpawnDelay;
        } else {
            int i = maxSpawnDelay - minSpawnDelay;
            logic.field_98286_b = minSpawnDelay + logic.func_98271_a().field_73012_v.nextInt(i);
        }
        if (potentialEntitySpawns != null && potentialEntitySpawns.size() > 0) {
            logic.func_98277_a((MobSpawnerBaseLogic.WeightedRandomMinecart)WeightedRandom.func_76271_a((Random)logic.func_98271_a().field_73012_v, (Collection)potentialEntitySpawns));
        }
        logic.func_98267_a(1);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_MANA, this.mana);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.func_74762_e(TAG_MANA);
    }

    @Override
    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public boolean isFull() {
        return this.mana >= 160;
    }

    @Override
    public void recieveMana(int mana) {
        this.mana = Math.min(160, this.mana + mana);
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }
}

