/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockCircleGlyph;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EarthItems;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityWitchProjectile
extends EntityThrowable {
    private int damageValue;
    private boolean skipFX = false;
    private static final String DAMAGE_VALUE_KEY = "damageValue";

    public EntityWitchProjectile(World world) {
        super(world);
    }

    public EntityWitchProjectile(World world, EntityLivingBase entityLiving, ItemGeneral.SubItem generalSubItem) {
        super(world, entityLiving);
        this.setDamageValue(generalSubItem.damageValue);
    }

    public EntityWitchProjectile(World world, double posX, double posY, double posZ, ItemGeneral.SubItem generalSubItem) {
        super(world, posX, posY, posZ);
        this.setDamageValue(generalSubItem.damageValue);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(6, (Object)0);
        super.func_70088_a();
    }

    public void setDamageValue(int damageValue) {
        this.damageValue = damageValue;
        this.func_70096_w().func_75692_b(6, (Object)damageValue);
    }

    public int getDamageValue() {
        return this.func_70096_w().func_75679_c(6);
    }

    public boolean isPotion() {
        return Witchery.Items.GENERIC.subItems.get(this.damageValue) instanceof ItemGeneral.Brew || Witchery.Items.GENERIC.itemQuicklime.damageValue == this.damageValue;
    }

    protected float func_70185_h() {
        if (this.isPotion()) {
            return 0.05f;
        }
        return super.func_70185_h();
    }

    protected float func_70182_d() {
        if (this.isPotion()) {
            return 0.5f;
        }
        return super.func_70182_d();
    }

    protected float func_70183_g() {
        if (this.isPotion()) {
            return -20.0f;
        }
        return super.func_70183_g();
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K && mop != null) {
            boolean enhanced = false;
            EntityLivingBase thrower = this.func_85052_h();
            if (thrower != null && thrower instanceof EntityPlayer) {
                enhanced = Familiar.hasActiveBrewMasteryFamiliar((EntityPlayer)thrower);
            }
            this.skipFX = false;
            if (Witchery.Items.GENERIC.itemBrewOfVines.damageValue == this.damageValue) {
                this.impactVines(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfThorns.damageValue == this.damageValue) {
                this.impactThorns(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfWebs.damageValue == this.damageValue) {
                this.impactWebBig(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfInk.damageValue == this.damageValue) {
                this.impactInk(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfWasting.damageValue == this.damageValue) {
                this.impactWasting(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfSprouting.damageValue == this.damageValue) {
                this.impactSprout(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfErosion.damageValue == this.damageValue) {
                this.impactErosion(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfLove.damageValue == this.damageValue) {
                this.impactLove(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemWeb.damageValue == this.damageValue) {
                this.impactWebSmall(mop);
                this.skipFX = true;
            } else if (Witchery.Items.GENERIC.itemRock.damageValue == this.damageValue) {
                this.impactRock(mop);
                this.skipFX = true;
            } else if (Witchery.Items.GENERIC.itemBrewOfRaising.damageValue == this.damageValue) {
                this.impactRaising(mop);
            } else if (Witchery.Items.GENERIC.itemQuicklime.damageValue == this.damageValue) {
                this.impactQuicklime(mop);
            } else if (Witchery.Items.GENERIC.itemBrewOfIce.damageValue == this.damageValue) {
                this.impactIce(mop);
            } else if (Witchery.Items.GENERIC.itemBrewOfFrogsTongue.damageValue == this.damageValue) {
                this.impactFrogsTongue(mop, false);
            } else if (Witchery.Items.GENERIC.itemBrewOfCursedLeaping.damageValue == this.damageValue) {
                this.impactLeaping(mop, false);
            } else if (Witchery.Items.GENERIC.itemBrewOfHitchcock.damageValue == this.damageValue) {
                this.impactHitchcock(mop);
            } else if (Witchery.Items.GENERIC.itemBrewOfInfection.damageValue == this.damageValue) {
                this.impactInfection(mop, enhanced);
            } else if (Witchery.Items.GENERIC.itemBrewOfBats.damageValue == this.damageValue) {
                this.impactBats(mop, enhanced);
            } else {
                ItemGeneral.SubItem item = Witchery.Items.GENERIC.subItems.get(this.damageValue);
                if (item instanceof ItemGeneral.Brew) {
                    ItemGeneral.Brew brew = (ItemGeneral.Brew)item;
                    ItemGeneral.Brew.BrewResult result = brew.onImpact(this.field_70170_p, thrower, mop, enhanced, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70121_D);
                    if (result == ItemGeneral.Brew.BrewResult.DROP_ITEM) {
                        EntityItem itemEntity = null;
                        if (mop != null) {
                            ItemStack newBrewStack = brew.createStack();
                            switch (mop.field_72313_a) {
                                case BLOCK: {
                                    itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                                    break;
                                }
                                case ENTITY: {
                                    itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                                    break;
                                }
                            }
                        }
                        this.skipFX = true;
                        if (itemEntity != null) {
                            this.field_70170_p.func_72838_d(itemEntity);
                        }
                    } else {
                        boolean bl = this.skipFX = result == ItemGeneral.Brew.BrewResult.HIDE_EFFECT;
                    }
                }
            }
            if (!this.skipFX) {
                this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), 2);
            }
        }
        this.func_70106_y();
    }

    private void impactBats(MovingObjectPosition mop, boolean enhanced) {
        switch (mop.field_72313_a) {
            case BLOCK: {
                this.explodeBats(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, enhanced);
                break;
            }
            case ENTITY: {
                int x = MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t);
                int y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                int z = MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v);
                this.explodeBats(this.field_70170_p, x, y, z, -1, enhanced);
                break;
            }
        }
        double RADIUS = enhanced ? 4.0 : 3.0;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0, RADIUS);
        List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (list1 != null && !list1.isEmpty()) {
            for (EntityLivingBase entitylivingbase : list1) {
                double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (!(d0 < RADIUS * RADIUS)) continue;
                double d1 = 1.0 - Math.sqrt(d0) / RADIUS;
                if (entitylivingbase == mop.field_72308_g) {
                    d1 = 1.0;
                }
                int j = (int)(d1 * 100.0 + 0.5);
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, j, 5));
                if (!(entitylivingbase instanceof EntityLiving)) continue;
                EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
            }
        }
    }

    private void explodeBats(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
        int x = posX + (side == 4 ? -1 : (side == 5 ? 1 : 0));
        int z = posZ + (side == 2 ? -1 : (side == 3 ? 1 : 0));
        int y = posY + (side == 0 ? -1 : (side == 1 ? 1 : 0));
        if (side == 1 && !world.func_147439_a(x, posY, z).func_149688_o().func_76220_a()) {
            --y;
        }
        int NUM_BATS = enhanced ? 14 : 10;
        for (int i = 0; i < NUM_BATS; ++i) {
            EntityBat bat = new EntityBat(world);
            NBTTagCompound nbtBat = bat.getEntityData();
            nbtBat.func_74757_a("WITCNoDrops", true);
            bat.func_70012_b((double)x, (double)y, (double)z, 0.0f, 0.0f);
            this.field_70170_p.func_72838_d((Entity)bat);
        }
        ParticleEffect.LARGE_EXPLODE.send(SoundEffect.MOB_ENDERMEN_PORTAL, world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, 3.0, 3.0, 16);
    }

    private void impactInfection(MovingObjectPosition mop, boolean enhanced) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block blockID = this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            int blockMeta = this.field_70170_p.func_72805_g(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            if ((blockID == Blocks.field_150348_b || blockID == Blocks.field_150347_e || blockID == Blocks.field_150417_aV && blockMeta == 0) && BlockProtect.canBreak(mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, this.field_70170_p)) {
                if (blockID == Blocks.field_150348_b) {
                    this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 0, 3);
                } else if (blockID == Blocks.field_150347_e) {
                    this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 1, 3);
                } else if (blockID == Blocks.field_150417_aV) {
                    this.field_70170_p.func_147465_d(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, Blocks.field_150418_aU, 2, 3);
                }
                return;
            }
        } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase entity = (EntityLivingBase)mop.field_72308_g;
            if (entity instanceof EntityVillager) {
                EntityZombie entityzombie = new EntityZombie(this.field_70170_p);
                entityzombie.func_82149_j((Entity)entity);
                this.field_70170_p.func_72900_e((Entity)entity);
                entityzombie.func_110161_a((IEntityLivingData)null);
                entityzombie.func_82229_g(true);
                if (entity.func_70631_g_()) {
                    entityzombie.func_82227_f(true);
                }
                this.field_70170_p.func_72838_d((Entity)entityzombie);
                this.field_70170_p.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
            } else {
                float WORM_DAMAGE = enhanced ? 4.0f : 1.0f;
                entity.func_70097_a(DamageSource.func_76356_a((Entity)entity, (Entity)this.func_85052_h()), WORM_DAMAGE);
                entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100, 8));
            }
            return;
        }
        EntityItem itemEntity = null;
        if (mop != null) {
            ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfInfection.createStack();
            switch (mop.field_72313_a) {
                case BLOCK: {
                    itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                    break;
                }
                case ENTITY: {
                    itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                    break;
                }
            }
        }
        this.skipFX = true;
        if (itemEntity != null) {
            this.field_70170_p.func_72838_d(itemEntity);
        }
    }

    private void impactHitchcock(MovingObjectPosition mop) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && mop.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase victim = (EntityLivingBase)mop.field_72308_g;
            int BIRDS = this.field_70170_p.field_73012_v.nextInt(2) + 3;
            for (int i = 0; i < BIRDS; ++i) {
                EntityOwl owl = new EntityOwl(this.field_70170_p);
                owl.func_70012_b(victim.field_70165_t - 2.0 + (double)this.field_70170_p.field_73012_v.nextInt(5), victim.field_70163_u + (double)victim.field_70131_O + 1.0 + (double)this.field_70170_p.field_73012_v.nextInt(2), victim.field_70161_v - 2.0 + (double)this.field_70170_p.field_73012_v.nextInt(5), 0.0f, 0.0f);
                owl.func_70624_b(victim);
                owl.setTimeToLive(400);
                this.field_70170_p.func_72838_d((Entity)owl);
                ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)owl, 1.0, 1.0, 16);
            }
        } else {
            EntityItem itemEntity = null;
            if (mop != null) {
                ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfHitchcock.createStack();
                switch (mop.field_72313_a) {
                    case BLOCK: {
                        itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                        break;
                    }
                    case ENTITY: {
                        itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                        break;
                    }
                }
            }
            this.skipFX = true;
            if (itemEntity != null) {
                this.field_70170_p.func_72838_d(itemEntity);
            }
        }
    }

    private void impactLeaping(MovingObjectPosition mop, boolean enhanced) {
        Entity livingEntity = mop.field_72308_g;
        double RADIUS = enhanced ? 6.0 : 5.0;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0, RADIUS);
        List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (list1 != null && !list1.isEmpty()) {
            for (EntityLivingBase entitylivingbase : list1) {
                double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (!(d0 < RADIUS * RADIUS)) continue;
                double d1 = 1.0 - 0.5 * (Math.sqrt(d0) / RADIUS / 2.0);
                if (entitylivingbase == livingEntity) {
                    d1 = 1.0;
                }
                int j = (int)(d1 * 400.0 + 0.5);
                double LEAP = d1 * 1.6;
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 200, 3));
                if (entitylivingbase instanceof EntityPlayer) {
                    Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(entitylivingbase.field_70159_w, entitylivingbase.field_70181_x + LEAP, entitylivingbase.field_70179_y), (EntityPlayer)entitylivingbase);
                    continue;
                }
                entitylivingbase.field_70181_x += LEAP;
            }
        }
    }

    private void impactFrogsTongue(MovingObjectPosition mop, boolean enhanced) {
        if (!this.field_70170_p.field_72995_K && this.func_85052_h() != null) {
            double RADIUS = enhanced ? 5.0 : 4.0;
            double RADIUS_SQ = RADIUS * RADIUS;
            EntityLivingBase thrower = this.func_85052_h();
            boolean pulled = false;
            AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0, RADIUS);
            List entityLivingList = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            if (entityLivingList != null && !entityLivingList.isEmpty()) {
                for (EntityLivingBase livingEntity : entityLivingList) {
                    double distanceSq = livingEntity.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                    if (!(distanceSq < RADIUS_SQ) || livingEntity == this.func_85052_h()) continue;
                    this.pull(this.field_70170_p, (Entity)livingEntity, thrower.field_70165_t, thrower.field_70163_u, thrower.field_70161_v, 0.05, 0.0);
                }
            }
        }
    }

    private void pull(World world, Entity entity, double posX, double posY, double posZ, double dy, double yy) {
        if (entity instanceof EntityDragon || entity instanceof EntityHornedHuntsman) {
            return;
        }
        double d = posX - entity.field_70165_t;
        double d1 = posY - entity.field_70163_u;
        double d2 = posZ - entity.field_70161_v;
        float distance = MathHelper.func_76133_a((double)(d * d + d1 * d1 + d2 * d2));
        float f2 = 0.1f + (float)dy;
        double mx = d / (double)distance * (double)f2 * (double)distance;
        double my = yy == 0.0 ? 0.4 : d1 / (double)distance * (double)distance * 0.2 + 0.2 + yy;
        double mz = d2 / (double)distance * (double)f2 * (double)distance;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 20, 1));
        }
        if (entity instanceof EntityPlayer) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(mx, my, mz), (EntityPlayer)entity);
        } else {
            entity.field_70159_w = mx;
            entity.field_70181_x = my;
            entity.field_70179_y = mz;
        }
    }

    private void impactIce(MovingObjectPosition mop) {
        switch (mop.field_72313_a) {
            case BLOCK: {
                int FREEZE_RANGE = 3;
                if (this.field_70170_p.func_147439_a(mop.field_72311_b + 1, mop.field_72312_c, mop.field_72309_d).func_149688_o() == Material.field_151586_h || this.field_70170_p.func_147439_a(mop.field_72311_b - 1, mop.field_72312_c, mop.field_72309_d).func_149688_o() == Material.field_151586_h || this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d).func_149688_o() == Material.field_151586_h || this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d).func_149688_o() == Material.field_151586_h || this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d + 1).func_149688_o() == Material.field_151586_h || this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d - 1).func_149688_o() == Material.field_151586_h) {
                    this.freezeSurroundingWater(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 3);
                    return;
                }
                int SHIELD_HEIGHT = 3;
                if (mop.field_72310_e == 1) {
                    EntityWitchProjectile.explodeIceShield(this.field_70170_p, this.func_85052_h(), mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, 3);
                    return;
                }
                if (mop.field_72310_e != 0) {
                    int dy;
                    int dx;
                    int b0 = 0;
                    switch (mop.field_72310_e) {
                        case 0: 
                        case 1: {
                            b0 = 0;
                            break;
                        }
                        case 2: 
                        case 3: {
                            b0 = 8;
                            break;
                        }
                        case 4: 
                        case 5: {
                            b0 = 4;
                        }
                    }
                    int n = mop.field_72310_e == 5 ? 1 : (dx = mop.field_72310_e == 4 ? -1 : 0);
                    int n2 = mop.field_72310_e == 0 ? -1 : (dy = mop.field_72310_e == 1 ? 1 : 0);
                    int dz = mop.field_72310_e == 3 ? 1 : (mop.field_72310_e == 2 ? -1 : 0);
                    EntityWitchProjectile.explodeIceShield(this.field_70170_p, this.func_85052_h(), mop.field_72311_b + dx, mop.field_72312_c + dy, mop.field_72309_d + dz, 3);
                    return;
                }
                EntityItem itemEntity = null;
                if (mop != null) {
                    ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfIce.createStack();
                    switch (mop.field_72313_a) {
                        case BLOCK: {
                            itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                            break;
                        }
                        case ENTITY: {
                            itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                            break;
                        }
                    }
                }
                this.skipFX = true;
                if (itemEntity == null) break;
                this.field_70170_p.func_72838_d(itemEntity);
                break;
            }
            case ENTITY: {
                int x = (int)Math.round(mop.field_72308_g.field_70165_t);
                int y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                int z = (int)Math.round(mop.field_72308_g.field_70161_v);
                EntityWitchProjectile.explodeIceBlock(this.field_70170_p, x, y, z, -1, mop.field_72308_g);
                break;
            }
        }
    }

    private void freezeSurroundingWater(World world, int x, int y, int z, int x0, int y0, int z0, int range) {
        if (Math.abs(x0 - x) >= range || Math.abs(y0 - y) >= range || Math.abs(z0 - z) >= range) {
            return;
        }
        if (this.freezeWater(world, x + 1, y, z)) {
            this.freezeSurroundingWater(world, x + 1, y, z, x0, y0, z0, range);
        }
        if (this.freezeWater(world, x - 1, y, z)) {
            this.freezeSurroundingWater(world, x - 1, y, z, x0, y0, z0, range);
        }
        if (this.freezeWater(world, x, y, z + 1)) {
            this.freezeSurroundingWater(world, x, y, z + 1, x0, y0, z0, range);
        }
        if (this.freezeWater(world, x, y, z - 1)) {
            this.freezeSurroundingWater(world, x, y, z - 1, x0, y0, z0, range);
        }
        if (this.freezeWater(world, x, y + 1, z)) {
            this.freezeSurroundingWater(world, x, y + 1, z, x0, y0, z0, range);
        }
        if (this.freezeWater(world, x, y - 1, z)) {
            this.freezeSurroundingWater(world, x, y - 1, z, x0, y0, z0, range);
        }
    }

    private boolean freezeWater(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h) {
            world.func_147449_b(x, y, z, Blocks.field_150432_aD);
            return true;
        }
        return false;
    }

    public static void explodeIceBlock(World world, int posX, int posY, int posZ, int side, Entity entity) {
        boolean resistent;
        int x = posX + (side == 4 ? -1 : (side == 5 ? 1 : 0));
        int z = posZ + (side == 2 ? -1 : (side == 3 ? 1 : 0));
        int y = posY + (side == 0 ? -1 : (side == 1 ? 1 : 0)) - 1;
        if (side == 1 && !world.func_147439_a(x, posY, z).func_149688_o().func_76220_a()) {
            --y;
        }
        Block block = Blocks.field_150432_aD;
        boolean bl = resistent = entity instanceof EntityDemon || entity instanceof EntityBlaze || entity instanceof EntityDragon || entity instanceof EntityHornedHuntsman || entity instanceof EntityEnt || entity instanceof EntityWither || entity instanceof EntityIronGolem;
        if (resistent) {
            EntityWitchProjectile.setBlockIfNotSolid(world, x, y + 1, z, (Block)Blocks.field_150358_i);
        } else {
            int HEIGHT = resistent ? 2 : 4;
            for (int i = 0; i < HEIGHT; ++i) {
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 2, y + i, z - 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 2, y + i, z, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y + i, z + 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x, y + i, z + 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y + i, z, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y + i, z - 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x, y + i, z - 2, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y + i, z - 2, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 2, y + i, z - 2, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 2, y + i, z + 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y + i, z + 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y + i, z - 2, block);
            }
            EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z, block);
            if (!resistent) {
                EntityWitchProjectile.setBlockIfNotSolid(world, x, y + HEIGHT - 1, z, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z - 1, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y + HEIGHT - 1, z, block);
                EntityWitchProjectile.setBlockIfNotSolid(world, x, y + HEIGHT - 1, z - 1, block);
            }
            if (entity instanceof EntityCreeper) {
                EntityCreeper creeper = (EntityCreeper)entity;
                boolean flag = world.func_82736_K().func_82766_b("mobGriefing");
                if (creeper.func_70830_n()) {
                    world.func_72876_a((Entity)creeper, creeper.field_70165_t, creeper.field_70163_u, creeper.field_70161_v, 6.0f, flag);
                } else {
                    world.func_72876_a((Entity)creeper, creeper.field_70165_t, creeper.field_70163_u, creeper.field_70161_v, 3.0f, flag);
                }
                creeper.func_70106_y();
            }
        }
    }

    public static void explodeIceShield(World world, EntityLivingBase player, int posX, int posY, int posZ, int height) {
        double f1 = player != null ? (double)MathHelper.func_76134_b((float)(-player.field_70177_z * ((float)Math.PI / 180) - (float)Math.PI)) : 0.0;
        double f2 = player != null ? (double)MathHelper.func_76126_a((float)(-player.field_70177_z * ((float)Math.PI / 180) - (float)Math.PI)) : 0.0;
        Vec3 loc = Vec3.func_72443_a((double)f2, (double)0.0, (double)f1);
        if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
            --posY;
        }
        EntityWitchProjectile.explodeIceColumn(world, posX, posY + 1, posZ, height);
        loc.func_72442_b((float)Math.toRadians(90.0));
        int newX = MathHelper.func_76128_c((double)((double)posX + 0.5 + loc.field_72450_a * 1.0));
        int newZ = MathHelper.func_76128_c((double)((double)posZ + 0.5 + loc.field_72449_c * 1.0));
        EntityWitchProjectile.explodeIceColumn(world, newX, posY + 1, newZ, height);
        loc.func_72442_b((float)Math.toRadians(180.0));
        newX = MathHelper.func_76128_c((double)((double)posX + 0.5 + loc.field_72450_a * 1.0));
        newZ = MathHelper.func_76128_c((double)((double)posZ + 0.5 + loc.field_72449_c * 1.0));
        EntityWitchProjectile.explodeIceColumn(world, newX, posY + 1, newZ, height);
    }

    public static void explodeIceColumn(World world, int posX, int posY, int posZ, int height) {
        for (int offsetPosY = posY; offsetPosY < posY + height; ++offsetPosY) {
            EntityWitchProjectile.setBlockIfNotSolid(world, posX, offsetPosY, posZ, Blocks.field_150432_aD);
        }
    }

    private void impactLove(MovingObjectPosition mop, boolean enhanced) {
        double RADIUS = enhanced ? 5.0 : 4.0;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(RADIUS, 2.0, RADIUS);
        List list1 = this.field_70170_p.func_72872_a(EntityLiving.class, axisalignedbb);
        if (list1 != null && !list1.isEmpty() && !this.field_70170_p.field_72995_K) {
            EntityVillager mate;
            EntityLivingBase entityThrower = this.func_85052_h();
            EntityPlayer thrower = entityThrower != null && entityThrower instanceof EntityPlayer ? (EntityPlayer)entityThrower : null;
            Iterator iterator = list1.iterator();
            ArrayList<EntityVillager> villagers = new ArrayList<EntityVillager>();
            ArrayList<EntityZombie> zombies = new ArrayList<EntityZombie>();
            while (iterator.hasNext()) {
                EntityZombie zombie;
                EntityLiving entitylivingbase = (EntityLiving)iterator.next();
                double d0 = entitylivingbase.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (!(d0 < RADIUS * RADIUS)) continue;
                double d1 = 1.0 - Math.sqrt(d0) / RADIUS;
                if (entitylivingbase == mop.field_72308_g) {
                    d1 = 1.0;
                }
                int j = (int)(d1 * 400.0 + 0.5);
                if (entitylivingbase instanceof EntityAnimal) {
                    EntityAnimal animal = (EntityAnimal)entitylivingbase;
                    if (animal.func_70874_b() < 0) continue;
                    animal.func_70873_a(0);
                    animal.func_146082_f(null);
                    continue;
                }
                if (entitylivingbase instanceof EntityVillager) {
                    EntityVillager villager = (EntityVillager)entitylivingbase;
                    if (villager.func_70874_b() < 0) continue;
                    villagers.add(villager);
                    continue;
                }
                if (!(entitylivingbase instanceof EntityZombie) || (zombie = (EntityZombie)entitylivingbase).func_70631_g_() || thrower == null) continue;
                NBTTagCompound nbt = zombie.getEntityData();
                if (!PotionEnslaved.isMobEnslavedBy((EntityLiving)zombie, thrower)) continue;
                zombies.add(zombie);
            }
            int limit = 10;
            while (villagers.size() > 1 && limit-- > 0) {
                EntityVillager villager = (EntityVillager)villagers.get(0);
                mate = (EntityVillager)villagers.get(1);
                villager.func_70107_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v);
                ParticleEffect.HEART.send(SoundEffect.NONE, (Entity)mate, 1.0, 2.0, 8);
                this.giveBirth(villager, mate);
                villagers.remove(0);
                villagers.remove(0);
            }
            limit = 10;
            while (zombies.size() > 1 && limit-- > 0) {
                EntityZombie zombie = (EntityZombie)zombies.get(0);
                mate = (EntityZombie)zombies.get(1);
                zombie.func_70107_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v);
                ParticleEffect.HEART.send(SoundEffect.NONE, (Entity)mate, 1.0, 2.0, 8);
                zombie.func_82229_g(true);
                mate.func_82229_g(true);
                EntityZombie baby = new EntityZombie(this.field_70170_p);
                baby.func_70012_b(mate.field_70165_t, mate.field_70163_u, mate.field_70161_v, 0.0f, 0.0f);
                baby.func_82227_f(true);
                this.field_70170_p.func_72838_d((Entity)baby);
                zombies.remove(0);
                zombies.remove(0);
            }
        }
    }

    private void giveBirth(EntityVillager villagerObj, EntityVillager mate) {
        EntityVillager entityvillager = villagerObj.func_90011_a((EntityAgeable)mate);
        mate.func_70873_a(6000);
        villagerObj.func_70873_a(6000);
        entityvillager.func_70873_a(-24000);
        entityvillager.func_70012_b(villagerObj.field_70165_t, villagerObj.field_70163_u, villagerObj.field_70161_v, 0.0f, 0.0f);
        this.field_70170_p.func_72838_d((Entity)entityvillager);
        this.field_70170_p.func_72960_a((Entity)entityvillager, (byte)12);
    }

    private void impactQuicklime(MovingObjectPosition mop) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            if (mop.field_72308_g instanceof EntityLivingBase) {
                EntityLivingBase livingEntity = (EntityLivingBase)mop.field_72308_g;
                if (!livingEntity.func_70644_a(Potion.field_76440_q)) {
                    livingEntity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 60, 0));
                }
                float DAMAGE = mop.field_72308_g instanceof EntitySlime ? 4.0f : (livingEntity.func_110143_aJ() == livingEntity.func_110138_aP() ? 0.5f : 0.1f);
                livingEntity.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), DAMAGE);
            }
            this.skipFX = true;
            return;
        }
        EntityItem itemEntity = null;
        if (mop != null) {
            ItemStack newBrewStack = Witchery.Items.GENERIC.itemQuicklime.createStack();
            switch (mop.field_72313_a) {
                case BLOCK: {
                    itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                    break;
                }
                case ENTITY: {
                    itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                    break;
                }
            }
        }
        this.skipFX = true;
        if (itemEntity != null) {
            this.field_70170_p.func_72838_d(itemEntity);
        }
    }

    private void impactRaising(MovingObjectPosition mop) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mop.field_72310_e == 1) {
            int posX = mop.field_72311_b;
            int posY = mop.field_72312_c;
            int posZ = mop.field_72309_d;
            World world = this.field_70170_p;
            EntityWitchProjectile.raiseDead(posX, posY, posZ, world, this.func_85052_h());
            return;
        }
        EntityItem itemEntity = null;
        if (mop != null) {
            ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfRaising.createStack();
            switch (mop.field_72313_a) {
                case BLOCK: {
                    itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                    break;
                }
                case ENTITY: {
                    itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                    break;
                }
            }
        }
        this.skipFX = true;
        if (itemEntity != null) {
            this.field_70170_p.func_72838_d(itemEntity);
        }
    }

    public static void raiseDead(int posX, int posY, int posZ, World world, EntityLivingBase raiser) {
        int y0 = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a() ? posY : posY - 1;
        int MAX_SPAWNS = 3;
        int MAX_DISTANCE = 3;
        int MAX_DROP = 6;
        EntityPlayer playerThrower = (EntityPlayer)(raiser instanceof EntityPlayer ? raiser : null);
        EntityWitchProjectile.raiseUndead(world, posX, y0, posZ, playerThrower);
        int extraCount = 0;
        double chance = world.field_73012_v.nextDouble();
        if (chance < 0.1) {
            extraCount = 2;
        } else if (chance < 0.4) {
            extraCount = 1;
        }
        for (int i = 0; i < extraCount; ++i) {
            int x = posX - 3 + world.field_73012_v.nextInt(6) + 1;
            int z = posZ - 3 + world.field_73012_v.nextInt(6) + 1;
            int y = -1;
            for (int dy = -6; dy < 6; ++dy) {
                if (!world.func_147439_a(x, y0 - dy, z).func_149688_o().func_76220_a()) continue;
                y = y0 - dy;
                break;
            }
            if (y == -1) continue;
            EntityWitchProjectile.raiseUndead(world, x, y, z, playerThrower);
        }
    }

    private static void raiseUndead(World world, int posX, int posY, int posZ, EntityPlayer thrower) {
        if (!world.field_72995_K) {
            Block blockID = world.func_147439_a(posX, posY, posZ);
            if (blockID != Blocks.field_150346_d && blockID != Blocks.field_150348_b && blockID != Blocks.field_150349_c && blockID != Blocks.field_150424_aL && blockID != Blocks.field_150391_bh && blockID != Blocks.field_150425_aM && blockID != Blocks.field_150347_e && blockID != Blocks.field_150351_n && blockID != Blocks.field_150354_m) {
                ++posY;
            }
            EntityWitchProjectile.spawnParticles(world, ParticleEffect.SMOKE, 0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
            world.func_147468_f(posX, posY, posZ);
            world.func_147468_f(posX, posY + 1, posZ);
            EntityLiving undeadEntity = EntityWitchProjectile.createUndeadCreature(world);
            undeadEntity.func_70012_b(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ, 1.0f, 0.0f);
            IEntityLivingData entitylivingData = null;
            entitylivingData = undeadEntity.func_110161_a(entitylivingData);
            undeadEntity.func_110163_bv();
            if (thrower != null) {
                try {
                    PotionEnslaved.setEnslaverForMob(undeadEntity, thrower);
                }
                catch (Exception e) {
                    Log.instance().warning(e, "Unhandled exception occurred setting enslaver from raiseUnded potion.");
                }
            }
            world.func_72838_d((Entity)undeadEntity);
        }
    }

    private static EntityLiving createUndeadCreature(World world) {
        double value = world.field_73012_v.nextDouble();
        if (value < 0.6) {
            return new EntityZombie(world);
        }
        if (value < 0.97) {
            return new EntitySkeleton(world);
        }
        return new EntityPigZombie(world);
    }

    private void impactErosion(MovingObjectPosition mop, boolean enhanced) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (BlockProtect.checkModsForBreakOK(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, this.func_85052_h())) {
                int RADIUS = 2;
                int obsidianMetled = 0;
                obsidianMetled += this.drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c, 2);
                for (int i = 0; i < 2; ++i) {
                    int dy = i + 1;
                    obsidianMetled += this.drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c + dy, 2 - dy);
                    obsidianMetled += this.drawFilledCircle(this.field_70170_p, mop.field_72311_b, mop.field_72309_d, mop.field_72312_c - dy, 2 - dy);
                }
                if (obsidianMetled > 0) {
                    this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, 0.5 + (double)mop.field_72311_b, 0.5 + (double)mop.field_72312_c, 0.5 + (double)mop.field_72309_d, new ItemStack(Blocks.field_150343_Z, obsidianMetled, 0)));
                }
            }
        } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            if (mop.field_72308_g instanceof EntityLivingBase) {
                EntityLivingBase entity = (EntityLivingBase)mop.field_72308_g;
                float ACID_DAMAGE = enhanced ? 10.0f : 8.0f;
                entity.func_70097_a(DamageSource.func_76356_a((Entity)entity, (Entity)this.func_85052_h()), ACID_DAMAGE);
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)entity;
                    if (this.causeAcidDamage(entity.func_70694_bm())) {
                        player.func_71028_bD();
                    }
                    for (int slot = 0; slot < player.field_71071_by.field_70460_b.length; ++slot) {
                        if (!this.causeAcidDamage(player.field_71071_by.field_70460_b[slot])) continue;
                        player.field_71071_by.field_70460_b[slot] = null;
                    }
                } else {
                    for (int slot = 0; slot < 5; ++slot) {
                        if (!this.causeAcidDamage(entity.func_71124_b(slot))) continue;
                        entity.func_70062_b(slot, null);
                    }
                }
            } else {
                this.skipFX = true;
                this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, Witchery.Items.GENERIC.itemBrewOfErosion.createStack()));
            }
        }
    }

    private boolean causeAcidDamage(ItemStack itemstack) {
        int ITEM_ACID_DAMAGE = 100;
        if (itemstack != null && itemstack.func_77984_f() && EarthItems.instance().isMatch(itemstack)) {
            itemstack.func_77972_a(100, this.func_85052_h());
            return itemstack.func_77960_j() <= 0;
        }
        return false;
    }

    protected int drawFilledCircle(World world, int x0, int z0, int y, int radius) {
        int x = radius;
        int radiusError = 1 - x;
        int obsidianMelted = 0;
        for (int z = 0; x >= z; ++z) {
            obsidianMelted += this.drawLine(world, -x + x0, x + x0, z + z0, y);
            obsidianMelted += this.drawLine(world, -z + x0, z + x0, x + z0, y);
            obsidianMelted += this.drawLine(world, -x + x0, x + x0, -z + z0, y);
            obsidianMelted += this.drawLine(world, -z + x0, z + x0, -x + z0, y);
            if (radiusError < 0) {
                radiusError += 2 * z + 1;
                continue;
            }
            radiusError += 2 * (z - --x + 1);
        }
        return obsidianMelted;
    }

    protected int drawLine(World world, int x1, int x2, int z, int y) {
        int obsidianMelted = 0;
        for (int x = x1; x <= x2; ++x) {
            Block blockID = world.func_147439_a(x, y, z);
            if (blockID == Blocks.field_150343_Z) {
                ++obsidianMelted;
            }
            if (blockID == Blocks.field_150350_a || blockID == Blocks.field_150353_l || blockID == Blocks.field_150356_k || blockID == Blocks.field_150480_ab || blockID == Blocks.field_150358_i || blockID == Blocks.field_150355_j || !BlockProtect.canBreak(blockID, world)) continue;
            world.func_147468_f(x, y, z);
            EntityWitchProjectile.spawnParticles(this.field_70170_p, ParticleEffect.SPLASH, this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        return obsidianMelted;
    }

    private void impactSprout(MovingObjectPosition mop, boolean enhanced) {
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int posX = mop.field_72311_b;
            int posY = mop.field_72312_c;
            int posZ = mop.field_72309_d;
            World world = this.field_70170_p;
            int sideHit = mop.field_72310_e;
            EntityWitchProjectile.growBranch(posX, posY, posZ, world, sideHit, enhanced ? 20 : 15, this.field_70121_D);
        } else {
            EntityItem itemEntity = null;
            if (mop != null) {
                ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfSprouting.createStack();
                switch (mop.field_72313_a) {
                    case BLOCK: {
                        itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                        break;
                    }
                    case ENTITY: {
                        itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                        break;
                    }
                }
            }
            this.skipFX = true;
            if (itemEntity != null) {
                this.field_70170_p.func_72838_d(itemEntity);
            }
        }
    }

    public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox) {
        AxisAlignedBB axisalignedbb;
        List list1;
        int i;
        int dy;
        int dx;
        Block logBlock;
        Block blockID = world.func_147439_a(posX, posY, posZ);
        int j1 = world.func_72805_g(posX, posY, posZ);
        if (blockID == Blocks.field_150364_r || blockID == Blocks.field_150344_f || blockID == Blocks.field_150345_g || blockID == Blocks.field_150362_t) {
            logBlock = Blocks.field_150364_r;
        } else if (blockID == Witchery.Blocks.LOG || blockID == Witchery.Blocks.PLANKS || blockID == Witchery.Blocks.SAPLING || blockID == Witchery.Blocks.LEAVES) {
            logBlock = Witchery.Blocks.LOG;
        } else {
            logBlock = world.field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : Witchery.Blocks.LOG;
            j1 = world.field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
        }
        BlockLeaves leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : Witchery.Blocks.LEAVES;
        int b0 = 0;
        j1 &= 3;
        switch (sideHit) {
            case 0: 
            case 1: {
                b0 = 0;
                break;
            }
            case 2: 
            case 3: {
                b0 = 8;
                break;
            }
            case 4: 
            case 5: {
                b0 = 4;
            }
        }
        int meta = j1 | b0;
        ParticleEffect particleEffect = ParticleEffect.EXPLODE;
        int n = sideHit == 5 ? 1 : (dx = sideHit == 4 ? -1 : 0);
        int n2 = sideHit == 0 ? -1 : (dy = sideHit == 1 ? 1 : 0);
        int dz = sideHit == 3 ? 1 : (sideHit == 2 ? -1 : 0);
        int sproutExtent = extent;
        boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
        int n3 = i = sideHit == 1 && !isInitialBlockSolid ? 0 : 1;
        while (i < sproutExtent) {
            int lz;
            int x = posX + i * dx;
            int y = posY + i * dy;
            int z = posZ + i * dz;
            if (y >= 255 || !EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z, logBlock, meta)) break;
            int lx = dx == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            int ly = dy == 0 && lx == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            int n4 = lz = dz == 0 && lx == 0 && ly == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            if (lx != 0 || ly != 0 || lz != 0) {
                EntityWitchProjectile.setBlockIfNotSolid(world, x + lx, y + ly, z + lz, (Block)leavesBlock, meta);
            }
            ++i;
        }
        if (sideHit == 1 && (list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb = boundingBox.func_72314_b(0.0, 2.0, 0.0))) != null && !list1.isEmpty()) {
            Iterator iterator = list1.iterator();
            int x = posX + i * dx;
            int y = Math.min(posY + i * dy, 255);
            int z = posZ + i * dz;
            while (iterator.hasNext()) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                if (world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a() || world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a()) continue;
                entitylivingbase.func_70107_b(0.5 + (double)x, (double)(y + 1), 0.5 + (double)z);
            }
        }
    }

    private void impactWasting(MovingObjectPosition mop, boolean enhanced) {
        double z;
        double y;
        double x;
        Entity livingEntity = mop.field_72308_g;
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            x = livingEntity.field_70165_t;
            y = livingEntity.field_70163_u;
            z = livingEntity.field_70161_v;
        } else {
            x = mop.field_72311_b;
            y = mop.field_72312_c;
            z = mop.field_72309_d;
        }
        EntityWitchProjectile.explodeWasting(this.field_70170_p, x, y, z, livingEntity, this.field_70121_D, enhanced);
    }

    public static void explodeWasting(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
        double RADIUS = enhanced ? 5.0 : 4.0;
        AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0, RADIUS);
        List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (list1 != null && !list1.isEmpty()) {
            for (EntityLivingBase entitylivingbase : list1) {
                double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
                if (!(d0 < RADIUS * RADIUS)) continue;
                double d1 = 1.0 - Math.sqrt(d0) / RADIUS;
                if (entitylivingbase == livingEntity) {
                    d1 = 1.0;
                }
                int j = (int)(d1 * 400.0 + 0.5);
                if (entitylivingbase instanceof EntityPlayer) {
                    int minLevel;
                    EntityPlayer victim = (EntityPlayer)entitylivingbase;
                    int n = minLevel = enhanced ? 6 : 10;
                    if (victim.func_71024_bL().func_75116_a() > minLevel) {
                        victim.func_71024_bL().func_75122_a(-minLevel, 0.0f);
                    }
                    victim.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, j * 2, enhanced ? 2 : 1));
                    victim.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, Math.max(j / 3, 40), 0));
                    continue;
                }
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, j * 2, enhanced ? 1 : 0));
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, Math.max(j / 3, 40), 0));
            }
        }
        int BLOCK_RADIUS = (int)RADIUS - 1;
        int BLOCK_RADIUS_SQ = BLOCK_RADIUS * BLOCK_RADIUS;
        int blockX = MathHelper.func_76128_c((double)posX);
        int blockY = MathHelper.func_76128_c((double)posY);
        int blockZ = MathHelper.func_76128_c((double)posZ);
        for (int y = blockY - BLOCK_RADIUS; y <= blockY + BLOCK_RADIUS; ++y) {
            for (int x = blockX - BLOCK_RADIUS; x <= blockX + BLOCK_RADIUS; ++x) {
                for (int z = blockZ - BLOCK_RADIUS; z <= blockZ + BLOCK_RADIUS; ++z) {
                    Block blockID;
                    Material material;
                    if (!(Coord.distanceSq(x, y, z, blockX, blockY, blockZ) <= (double)BLOCK_RADIUS_SQ) || (material = world.func_147439_a(x, y, z).func_149688_o()) == null || material != Material.field_151584_j && (material != Material.field_151585_k && material != Material.field_151582_l || !material.func_76222_j()) || (blockID = world.func_147439_a(x, y, z)) instanceof BlockCircle || blockID instanceof BlockCircleGlyph) continue;
                    blockID.func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
                    world.func_147468_f(x, y, z);
                }
            }
        }
    }

    private void impactInk(MovingObjectPosition mop, boolean enhanced) {
        Entity livingEntity = mop.field_72308_g;
        EntityWitchProjectile.explodeInk(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, livingEntity, this.field_70121_D, enhanced);
    }

    public static void explodeInk(World world, double posX, double posY, double posZ, Entity livingEntity, AxisAlignedBB boundingBox, boolean enhanced) {
        double RADIUS = enhanced ? 5.0 : 4.0;
        AxisAlignedBB axisalignedbb = boundingBox.func_72314_b(RADIUS, 2.0, RADIUS);
        List list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb);
        if (list1 != null && !list1.isEmpty()) {
            for (EntityLivingBase entitylivingbase : list1) {
                double d0 = entitylivingbase.func_70092_e(posX, posY, posZ);
                if (!(d0 < RADIUS * RADIUS)) continue;
                double d1 = 1.0 - Math.sqrt(d0) / RADIUS;
                if (entitylivingbase == livingEntity) {
                    d1 = 1.0;
                }
                int j = (int)(d1 * 400.0 + 0.5);
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, j, 0));
                if (!(entitylivingbase instanceof EntityLiving)) continue;
                EntityUtil.dropAttackTarget((EntityLiving)entitylivingbase);
            }
        }
    }

    private void impactRock(MovingObjectPosition mop) {
        if (mop.field_72308_g != null) {
            float DAMAGE = 6.0f;
            mop.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), 6.0f);
        }
        EntityWitchProjectile.spawnParticles(this.field_70170_p, ParticleEffect.EXPLODE, this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    private static void spawnParticles(World world, ParticleEffect effect, double posX, double posY, double posZ) {
        effect.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0, 1.0, 8);
    }

    private void impactWebSmall(MovingObjectPosition mop) {
        switch (mop.field_72313_a) {
            case ENTITY: {
                this.field_70170_p.func_147449_b((int)mop.field_72308_g.field_70165_t, (int)mop.field_72308_g.field_70163_u, (int)mop.field_72308_g.field_70161_v, Blocks.field_150321_G);
                break;
            }
            case BLOCK: {
                if (this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150433_aE) {
                    --mop.field_72312_c;
                    mop.field_72310_e = 1;
                }
                switch (mop.field_72310_e) {
                    case 0: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c - 1, mop.field_72309_d, Blocks.field_150321_G);
                        break;
                    }
                    case 1: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, Blocks.field_150321_G);
                        break;
                    }
                    case 2: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b - 1, mop.field_72312_c, mop.field_72309_d, Blocks.field_150321_G);
                        break;
                    }
                    case 3: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b + 1, mop.field_72312_c, mop.field_72309_d, Blocks.field_150321_G);
                        break;
                    }
                    case 4: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d - 1, Blocks.field_150321_G);
                        break;
                    }
                    case 5: {
                        EntityWitchProjectile.setBlockIfNotSolid(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d + 1, Blocks.field_150321_G);
                    }
                }
                break;
            }
        }
    }

    private void impactWebBig(MovingObjectPosition mop, boolean enhanced) {
        switch (mop.field_72313_a) {
            case BLOCK: {
                EntityWitchProjectile.explodeWeb(this.field_70170_p, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, enhanced);
                break;
            }
            case ENTITY: {
                int x = MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t);
                int y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                int z = MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v);
                EntityWitchProjectile.explodeWeb(this.field_70170_p, x, y, z, -1, enhanced);
                break;
            }
        }
    }

    public static void explodeWeb(World world, int posX, int posY, int posZ, int side, boolean enhanced) {
        int x = posX + (side == 4 ? -1 : (side == 5 ? 1 : 0));
        int z = posZ + (side == 2 ? -1 : (side == 3 ? 1 : 0));
        int y = posY + (side == 0 ? -1 : (side == 1 ? 1 : 0));
        if (side == 1 && !world.func_147439_a(x, posY, z).func_149688_o().func_76220_a()) {
            --y;
        }
        EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z, Blocks.field_150321_G);
        EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y, z, Blocks.field_150321_G);
        EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y, z, Blocks.field_150321_G);
        EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z + 1, Blocks.field_150321_G);
        EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z - 1, Blocks.field_150321_G);
        if (enhanced) {
            EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y, z + 1, Blocks.field_150321_G);
            EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y, z - 1, Blocks.field_150321_G);
            EntityWitchProjectile.setBlockIfNotSolid(world, x - 1, y, z + 1, Blocks.field_150321_G);
            EntityWitchProjectile.setBlockIfNotSolid(world, x + 1, y, z - 1, Blocks.field_150321_G);
        }
        EntityWitchProjectile.setBlockIfNotSolid(world, x, y + 1, z, Blocks.field_150321_G);
        EntityWitchProjectile.setBlockIfNotSolid(world, x, y - 1, z, Blocks.field_150321_G);
    }

    private void impactThorns(MovingObjectPosition mop, boolean enhanced) {
        switch (mop.field_72313_a) {
            case BLOCK: {
                int z;
                int x;
                int y;
                if (mop.field_72310_e == 1 || this.field_70170_p.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Blocks.field_150434_aF) {
                    int CACTUS_HEIGHT;
                    y = mop.field_72312_c;
                    x = mop.field_72311_b;
                    z = mop.field_72309_d;
                    int n = CACTUS_HEIGHT = enhanced ? 4 : 3;
                    if (EntityWitchProjectile.plantCactus(this.field_70170_p, x, y, z, CACTUS_HEIGHT)) break;
                }
                ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfThorns.createStack();
                x = mop.field_72311_b + (mop.field_72310_e == 4 ? -1 : (mop.field_72310_e == 5 ? 1 : 0));
                z = mop.field_72309_d + (mop.field_72310_e == 2 ? -1 : (mop.field_72310_e == 3 ? 1 : 0));
                y = mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : (mop.field_72310_e == 1 ? 1 : 0));
                this.skipFX = true;
                this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, newBrewStack));
                break;
            }
            case ENTITY: {
                int CACTUS_HEIGHT = enhanced ? 2 : 1;
                int x = MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t);
                int y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                int z = MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v);
                boolean success = EntityWitchProjectile.plantCactus(this.field_70170_p, x + 1, y, z, CACTUS_HEIGHT);
                success = success && EntityWitchProjectile.plantCactus(this.field_70170_p, x - 1, y, z, CACTUS_HEIGHT);
                success = success && EntityWitchProjectile.plantCactus(this.field_70170_p, x, y, z + 1, CACTUS_HEIGHT);
                boolean bl = success = success && EntityWitchProjectile.plantCactus(this.field_70170_p, x, y, z - 1, CACTUS_HEIGHT);
                if (success) break;
                this.skipFX = true;
                this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, Witchery.Items.GENERIC.itemBrewOfThorns.createStack()));
                break;
            }
        }
    }

    public static boolean plantCactus(World world, int x, int y, int z, int CACTUS_HEIGHT) {
        Material material;
        if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
            --y;
        }
        if ((material = world.func_147439_a(x, y, z).func_149688_o()) != Material.field_151571_B && material != Material.field_151596_z && material != Material.field_151577_b && material != Material.field_151578_c && material != Material.field_151576_e && material != Material.field_151595_p && material != Material.field_151597_y && material != Material.field_151583_m && material != Material.field_151570_A) {
            return false;
        }
        Block blockID = world.func_147439_a(x, y, z);
        if (!BlockProtect.canBreak(blockID, world)) {
            return false;
        }
        if (material != Material.field_151570_A) {
            world.func_147449_b(x, y, z, (Block)Blocks.field_150354_m);
        } else {
            while (world.func_147439_a(x, y, z) == Blocks.field_150434_aF) {
                ++y;
            }
            --y;
        }
        for (int i = 1; i <= CACTUS_HEIGHT && y + i < 256 && EntityWitchProjectile.setBlockIfNotSolid(world, x, y + i, z, Blocks.field_150434_aF); ++i) {
        }
        return true;
    }

    private void impactVines(MovingObjectPosition mop, boolean enhanced) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && mop.field_72310_e != 0 && mop.field_72310_e != 1) {
            int dx;
            int n = mop.field_72310_e == 4 ? -1 : (dx = mop.field_72310_e == 5 ? 1 : 0);
            int dz = mop.field_72310_e == 2 ? -1 : (mop.field_72310_e == 3 ? 1 : 0);
            int y0 = mop.field_72312_c;
            int meta = 0;
            switch (mop.field_72310_e) {
                case 2: {
                    meta = 1;
                    break;
                }
                case 3: {
                    meta = 4;
                    break;
                }
                case 4: {
                    meta = 8;
                    break;
                }
                case 5: {
                    meta = 2;
                }
            }
            ParticleEffect EFFECT = ParticleEffect.EXPLODE;
            int y = y0;
            int x = mop.field_72311_b;
            int z = mop.field_72309_d;
            if (!this.isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o()) || !this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) {
                x += dx;
                z += dz;
            }
            while (this.isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o()) && this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a() && y > 0) {
                this.field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
                EntityWitchProjectile.spawnParticles(this.field_70170_p, EFFECT, 0.5 + (double)x + (double)dx, 0.5 + (double)y, 0.5 + (double)z + (double)dz);
                if (this.isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, --y, z + dz).func_149688_o()) && this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a() || !enhanced || this.isNotSolidOrLeaves(this.field_70170_p.func_147439_a((x += dx) + dx, y, (z += dz) + dz).func_149688_o()) && this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a()) continue;
                x += dx;
                z += dz;
            }
            x = mop.field_72311_b;
            y = y0 + 1;
            z = mop.field_72309_d;
            if (!this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a() && enhanced && !this.field_70170_p.func_147439_a(x -= dx, y, z -= dz).func_149688_o().func_76220_a()) {
                x -= dx;
                z -= dz;
            }
            while (this.isNotSolidOrLeaves(this.field_70170_p.func_147439_a(x + dx, y, z + dz).func_149688_o()) && this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76220_a() && y < 256) {
                this.field_70170_p.func_147465_d(x + dx, y, z + dz, Blocks.field_150395_bd, meta, 3);
                EntityWitchProjectile.spawnParticles(this.field_70170_p, EFFECT, 0.5 + (double)x + (double)dx, 0.5 + (double)y, 0.5 + (double)z + (double)dz);
                if (this.field_70170_p.func_147439_a(x, ++y, z).func_149688_o().func_76220_a() || !enhanced || this.field_70170_p.func_147439_a(x -= dx, y, z -= dz).func_149688_o().func_76220_a()) continue;
                x -= dx;
                z -= dz;
            }
        } else {
            EntityItem itemEntity = null;
            ItemStack newBrewStack = Witchery.Items.GENERIC.itemBrewOfVines.createStack();
            switch (mop.field_72313_a) {
                case BLOCK: {
                    itemEntity = new EntityItem(this.field_70170_p, (double)mop.field_72311_b + 0.5, (double)(mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : 1)) + 0.5, (double)mop.field_72309_d + 0.5, newBrewStack);
                    break;
                }
                case ENTITY: {
                    itemEntity = new EntityItem(this.field_70170_p, mop.field_72308_g.field_70165_t, mop.field_72308_g.field_70163_u, mop.field_72308_g.field_70161_v, newBrewStack);
                    break;
                }
            }
            this.skipFX = true;
            this.field_70170_p.func_72838_d(itemEntity);
        }
    }

    private boolean isNotSolidOrLeaves(Material material) {
        return material == null || !material.func_76220_a() || material == Material.field_151584_j;
    }

    private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
        return EntityWitchProjectile.setBlockIfNotSolid(world, x, y, z, block, 0);
    }

    private static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
        if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a() || block == Blocks.field_150321_G && world.func_147439_a(x, y, z) == Blocks.field_150433_aE) {
            world.func_147465_d(x, y, z, block, metadata, 3);
            EntityWitchProjectile.spawnParticles(world, ParticleEffect.EXPLODE, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z);
            return true;
        }
        return false;
    }

    public void func_70037_a(NBTTagCompound nbtTag) {
        super.func_70037_a(nbtTag);
        if (nbtTag.func_74764_b(DAMAGE_VALUE_KEY)) {
            this.damageValue = nbtTag.func_74762_e(DAMAGE_VALUE_KEY);
            this.setDamageValue(this.damageValue);
        } else {
            this.func_70106_y();
        }
    }

    public void func_70014_b(NBTTagCompound nbtTag) {
        super.func_70014_b(nbtTag);
        nbtTag.func_74768_a(DAMAGE_VALUE_KEY, this.damageValue);
    }
}

