/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.ServerTickEvents;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.CircleUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityItemWaystone
extends EntityItem {
    public EntityItemWaystone(World world) {
        super(world);
    }

    public EntityItemWaystone(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntityItemWaystone(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    public EntityItemWaystone(EntityItem entityItem) {
        super(entityItem.field_70170_p, entityItem.field_70165_t, entityItem.field_70163_u, entityItem.field_70161_v, entityItem.func_92059_d());
        this.field_145804_b = entityItem.field_145804_b;
        this.field_70159_w = entityItem.field_70159_w;
        this.field_70181_x = entityItem.field_70181_x;
        this.field_70179_y = entityItem.field_70179_y;
    }

    public void func_70100_b_(EntityPlayer player) {
        double minPickupRange = 0.75;
        double minPickupRangeSq = 0.5625;
        if (this.func_70068_e((Entity)player) <= 0.5625) {
            super.func_70100_b_(player);
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && this.field_70292_b > TimeUtil.secsToTicks(2) && this.field_70292_b % 40 == 0) {
            Block glyph;
            Coord center;
            if (Witchery.Items.GENERIC.itemWaystone.isMatch(this.func_92059_d())) {
                Block glyph2 = Witchery.Blocks.GLYPH_OTHERWHERE;
                Coord center2 = EntityItemWaystone.isTinyBlockCircle(this.field_70170_p, new Coord((Entity)this), glyph2);
                if (center2 != null) {
                    int originalStackSize = this.func_92059_d().field_77994_a;
                    int remainingStackSize = 0;
                    double R = 2.0;
                    double RSq = 4.0;
                    EntityPosition centerPoint = new EntityPosition((double)center2.x + 0.5, (double)center2.y + 0.5, (double)center2.z + 0.5);
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(centerPoint.x - 2.0), (double)(centerPoint.y - 2.0), (double)(centerPoint.z - 2.0), (double)(centerPoint.x + 2.0), (double)(centerPoint.y + 2.0), (double)(centerPoint.z + 2.0));
                    ItemStack boundStone = null;
                    EntityPlayer target = null;
                    double targetDistSq = -1.0;
                    List nearbyPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, bounds);
                    for (EntityPlayer player : nearbyPlayers) {
                        double distSq = player.func_70092_e(centerPoint.x, player.field_70163_u, centerPoint.z);
                        if (!(distSq <= 4.0) || target != null && !(distSq < targetDistSq)) continue;
                        target = player;
                        targetDistSq = distSq;
                    }
                    if (target == null) {
                        List nearbyCreatures = this.field_70170_p.func_72872_a(EntityLiving.class, bounds);
                        for (EntityLiving creature : nearbyCreatures) {
                            double distSq = creature.func_70092_e(centerPoint.x, creature.field_70163_u, centerPoint.z);
                            if (!(distSq <= 4.0) || target != null && !(distSq < targetDistSq)) continue;
                            target = creature;
                            targetDistSq = distSq;
                        }
                    }
                    if (target != null) {
                        IPowerSource power = PowerSources.findClosestPowerSource(this.field_70170_p, center2);
                        if (power != null) {
                            if (power.consumePower(4000.0f)) {
                                int convertableStackSize = Math.min(originalStackSize, 1);
                                remainingStackSize = originalStackSize - convertableStackSize;
                                boundStone = Witchery.Items.GENERIC.itemWaystonePlayerBound.createStack(convertableStackSize);
                                Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(boundStone, null, (Entity)target, false, (Integer)1);
                            } else {
                                ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.field_70170_p, center2, 1.0, 1.0, 16);
                            }
                        } else {
                            ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.field_70170_p, center2, 1.0, 1.0, 16);
                        }
                    } else {
                        int convertableStackSize = Math.min(originalStackSize, 8);
                        remainingStackSize = originalStackSize - convertableStackSize;
                        boundStone = Witchery.Items.GENERIC.itemWaystoneBound.createStack(convertableStackSize);
                        Witchery.Items.GENERIC.bindToLocation(this.field_70170_p, center2.x, center2.y, center2.z, this.field_71093_bK, this.field_70170_p.field_73011_w.func_80007_l(), boundStone);
                    }
                    if (boundStone != null) {
                        EntityUtil.spawnEntityInWorld(this.field_70170_p, (Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, boundStone));
                        if (remainingStackSize > 0) {
                            EntityUtil.spawnEntityInWorld(this.field_70170_p, (Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemWaystone.createStack(remainingStackSize)));
                        }
                        ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, (Entity)this, 1.0, 1.0, 16);
                        EntityItemWaystone.isInnerTinyBlockCircle(this.field_70170_p, center2.x, center2.y, center2.z, glyph2, true);
                        this.func_70106_y();
                    }
                }
            } else if (Witchery.Items.GENERIC.itemWaystoneBound.isMatch(this.func_92059_d()) || Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(this.func_92059_d())) {
                Block glyph3 = Witchery.Blocks.GLYPH_OTHERWHERE;
                Coord center3 = EntityItemWaystone.isSmallBlockCircle(this.field_70170_p, new Coord((Entity)this), glyph3);
                if (center3 != null) {
                    double R = 4.0;
                    double RSq = 16.0;
                    ItemStack usedStone = this.func_92059_d().func_77979_a(1);
                    if (this.func_92059_d().field_77994_a > 0) {
                        EntityUtil.spawnEntityInWorld(this.field_70170_p, (Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.func_92059_d()));
                    }
                    this.func_70106_y();
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)((double)center3.x + 0.5 - 4.0), (double)((double)center3.y + 0.5 - 4.0), (double)((double)center3.z + 0.5 - 4.0), (double)((double)center3.x + 0.5 + 4.0), (double)((double)center3.y + 0.5 + 4.0), (double)((double)center3.z + 0.5 + 4.0));
                    List list = this.field_70170_p.func_72872_a(Entity.class, bounds);
                    for (Entity entity : list) {
                        if (entity.field_70128_L || !(entity.func_70092_e(0.5 + (double)center3.x, entity.field_70163_u, 0.5 + (double)center3.z) <= 16.0) || !(entity instanceof EntityLivingBase) && !(entity instanceof EntityItem) || PotionEnderInhibition.isActive(entity, 1)) continue;
                        ServerTickEvents.TASKS.add(new TeleportTask(this.field_70170_p, usedStone, entity));
                    }
                }
            } else if (Witchery.Items.GENERIC.itemAttunedStone.isMatch(this.func_92059_d())) {
                Block glyph4 = Witchery.Blocks.GLYPH_RITUAL;
                Coord center4 = EntityItemWaystone.isTinyBlockCircle(this.field_70170_p, new Coord((Entity)this), glyph4);
                if (center4 != null) {
                    int originalStackSize = this.func_92059_d().field_77994_a;
                    int remainingStackSize = 0;
                    double R = 2.0;
                    double RSq = 4.0;
                    EntityPosition centerPoint = new EntityPosition((double)center4.x + 0.5, (double)center4.y + 0.5, (double)center4.z + 0.5);
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(centerPoint.x - 2.0), (double)(centerPoint.y - 2.0), (double)(centerPoint.z - 2.0), (double)(centerPoint.x + 2.0), (double)(centerPoint.y + 2.0), (double)(centerPoint.z + 2.0));
                    int convertableStackSize = Math.min(originalStackSize, 1);
                    remainingStackSize = originalStackSize - convertableStackSize;
                    EntityCreature creature = Infusion.spawnCreature(this.field_70170_p, EntitySpirit.class, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
                    if (creature != null) {
                        EntitySpirit spirit = (EntitySpirit)creature;
                        creature.func_110163_bv();
                        spirit.setTarget("Village", 2);
                    }
                    if (remainingStackSize > 0) {
                        EntityUtil.spawnEntityInWorld(this.field_70170_p, (Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemAttunedStone.createStack(remainingStackSize)));
                    }
                    ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, (Entity)this, 1.0, 1.0, 16);
                    EntityItemWaystone.isInnerTinyBlockCircle(this.field_70170_p, center4.x, center4.y, center4.z, glyph4, true);
                    this.func_70106_y();
                }
            } else if (Witchery.Items.GENERIC.itemSubduedSpirit.isMatch(this.func_92059_d()) && (center = EntityItemWaystone.isTinyBlockCircle(this.field_70170_p, new Coord((Entity)this), glyph = Witchery.Blocks.GLYPH_RITUAL)) != null) {
                int originalStackSize = this.func_92059_d().field_77994_a;
                int remainingStackSize = 0;
                double R = 2.0;
                double RSq = 4.0;
                EntityPosition centerPoint = new EntityPosition((double)center.x + 0.5, (double)center.y + 0.5, (double)center.z + 0.5);
                AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(centerPoint.x - 2.0), (double)(centerPoint.y - 2.0), (double)(centerPoint.z - 2.0), (double)(centerPoint.x + 2.0), (double)(centerPoint.y + 2.0), (double)(centerPoint.z + 2.0));
                int convertableStackSize = Math.min(originalStackSize, 1);
                remainingStackSize = originalStackSize - convertableStackSize;
                EntityCreature creature = Infusion.spawnCreature(this.field_70170_p, EntitySpirit.class, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
                if (creature != null) {
                    EntitySpirit spirit = (EntitySpirit)creature;
                    creature.func_110163_bv();
                    spirit.setTarget("Village", 2);
                }
                if (remainingStackSize > 0) {
                    EntityUtil.spawnEntityInWorld(this.field_70170_p, (Entity)new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, Witchery.Items.GENERIC.itemSubduedSpirit.createStack(remainingStackSize)));
                }
                ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_POP, (Entity)this, 1.0, 1.0, 16);
                EntityItemWaystone.isInnerTinyBlockCircle(this.field_70170_p, center.x, center.y, center.z, glyph, true);
                this.func_70106_y();
            }
        }
    }

    private static Coord isTinyBlockCircle(World world, Coord coord, Block runeBlock) {
        int x = coord.x;
        int y = coord.y;
        int z = coord.z;
        if (EntityItemWaystone.isInnerTinyBlockCircle(world, x, y, z, runeBlock, false)) {
            return coord;
        }
        return null;
    }

    private static boolean isInnerTinyBlockCircle(World world, int x, int y, int z, Block runeBlock, boolean explode) {
        int[][] circle;
        for (int[] coord : circle = new int[][]{{x, z - 1}, {x + 1, z - 1}, {x + 1, z}, {x + 1, z + 1}, {x, z + 1}, {x - 1, z + 1}, {x - 1, z}, {x - 1, z - 1}}) {
            if (world.func_147439_a(coord[0], y, coord[1]) == runeBlock) continue;
            return false;
        }
        if (explode) {
            for (int[] coord : circle) {
                world.func_147468_f(coord[0], y, coord[1]);
                ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5 + (double)coord[0], y, 0.5 + (double)coord[1], 0.5, 0.5, 16);
            }
        }
        return true;
    }

    private static Coord isSmallBlockCircle(World world, Coord coord, Block runeBlock) {
        int[][] circle;
        int x = coord.x;
        int z = coord.z;
        for (int[] co : circle = new int[][]{{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}}) {
            if (!CircleUtil.isSmallCircle(world, coord.x + co[0], coord.y, coord.z + co[1], runeBlock)) continue;
            return new Coord(coord.x - co[0], coord.y, coord.z - co[1]);
        }
        return null;
    }

    private static class TeleportTask
    extends ServerTickEvents.ServerTickTask {
        ItemStack stone;
        Entity entity;

        public TeleportTask(World world, ItemStack stone, Entity entity) {
            super(world);
            this.stone = stone;
            this.entity = entity;
        }

        @Override
        public boolean process() {
            if (!Witchery.Items.GENERIC.teleportToLocation(this.world, this.stone, this.entity, 0, true)) {
                ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, this.world, this.entity.field_70165_t, this.entity.field_70163_u, this.entity.field_70161_v, 1.0, 1.0, 16);
            }
            return true;
        }
    }
}

