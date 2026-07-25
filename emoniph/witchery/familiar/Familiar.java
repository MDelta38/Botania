/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.familiar;

import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityWitchCat;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class Familiar {
    private static final String[] NAMES_TOAD = new String[]{"Casper", "Wart", "Langston", "Croaker", "Prince Charming", "Frog-n-stien", "Randolph", "Evileye", "Churchill", "Santa", "Dillinger", "Spuds"};
    private static final String[] NAMES_CAT = new String[]{"Pyewackett", "Salem", "Gobbolino", "Sabbath", "Norris", "Crookshanks", "Binx", "Voodoo", "Raven", "Simpkin", "Fishbone", "Kismet"};
    private static final String[] NAMES_OWL = new String[]{"Archimedes", "Dumbledornithologist", "Al Travis", "Baltimore", "Cornelius", "Hadwig", "Hoot", "Merlin", "Owl Capone", "Pigwidgeon", "Athena", "Albertine"};
    private static final String FAMILIAR_TAG_KEY = "WITCFamiliar";
    private static final String FAMILIAR_UUID_MOST = "UUIDMost";
    private static final String FAMILIAR_UUID_LEAST = "UUIDLeast";
    private static final String FAMILIAR_NAME = "FamiliarName";
    private static final String FAMILIAR_TYPE = "FamiliarType";
    private static final String FAMILIAR_COLOR = "FamiliarColor";
    private static final String FAMILIAR_SUMMONED = "FamiliarSummoned";
    public static final int FAMILIAR_NONE = 0;
    public static final int FAMILIAR_CAT = 1;
    public static final int FAMILIAR_TOAD = 2;
    public static final int FAMILIAR_OWL = 3;
    private static final float REDIRECTED_DAMAGE_PCT_FAR = 0.01f;
    private static final float REDIRECTED_DAMAGE_PCT_NEAR = 0.1f;
    private static final float MAX_HEALTH = 50.0f;
    private static final float FAMILIAR_NEAR_DISTANCE_SQ = 576.0f;

    public static void bindToPlayer(EntityPlayer player, EntityTameable familiarEntity) {
        NBTTagCompound nbtTag;
        if (Familiar.canBecomeFamiliar(familiarEntity) && TameableUtil.isOwner(familiarEntity, player) && (nbtTag = Infusion.getNBT((Entity)player)) != null) {
            EntityTameable currentFamiliar = Familiar.getFamiliarEntity(player);
            if (currentFamiliar != null) {
                ((IFamiliar)currentFamiliar).clearFamiliar();
            }
            if (familiarEntity instanceof EntityOcelot) {
                EntityOcelot oldCat = (EntityOcelot)familiarEntity;
                EntityWitchCat newCat = new EntityWitchCat(oldCat.field_70170_p);
                newCat.cloneOcelot(oldCat);
                newCat.func_70912_b(1);
                oldCat.func_70106_y();
                newCat.field_70170_p.func_72838_d((Entity)newCat);
                newCat.field_70170_p.func_72960_a((Entity)newCat, (byte)7);
                familiarEntity = newCat;
            }
            IFamiliar familiar = (IFamiliar)familiarEntity;
            NBTTagCompound nbtFamiliar = new NBTTagCompound();
            nbtFamiliar.func_74772_a(FAMILIAR_UUID_MOST, familiarEntity.func_110124_au().getMostSignificantBits());
            nbtFamiliar.func_74772_a(FAMILIAR_UUID_LEAST, familiarEntity.func_110124_au().getLeastSignificantBits());
            String name = "Familiar";
            if (familiarEntity instanceof EntityOwl) {
                name = NAMES_OWL[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
                nbtFamiliar.func_74768_a(FAMILIAR_TYPE, 3);
                nbtFamiliar.func_74774_a(FAMILIAR_COLOR, Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
            } else if (familiarEntity instanceof EntityToad) {
                name = NAMES_TOAD[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
                nbtFamiliar.func_74768_a(FAMILIAR_TYPE, 2);
                nbtFamiliar.func_74774_a(FAMILIAR_COLOR, Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
            } else if (familiarEntity instanceof EntityOcelot) {
                name = NAMES_CAT[player.field_70170_p.field_73012_v.nextInt(NAMES_OWL.length)];
                nbtFamiliar.func_74768_a(FAMILIAR_TYPE, 1);
                nbtFamiliar.func_74774_a(FAMILIAR_COLOR, Byte.valueOf((byte)0).byteValue());
            }
            if (!familiarEntity.func_94056_bM() && name != null && !name.isEmpty()) {
                familiarEntity.func_94058_c(name);
            }
            nbtFamiliar.func_74778_a(FAMILIAR_NAME, familiarEntity.func_94057_bL());
            nbtFamiliar.func_74774_a(FAMILIAR_SUMMONED, Byte.valueOf((byte)1).byteValue());
            nbtTag.func_74782_a(FAMILIAR_TAG_KEY, (NBTBase)nbtFamiliar);
            familiar.setMaxHealth(50.0f);
        }
    }

    public static boolean canBecomeFamiliar(EntityTameable familiarEntity) {
        return familiarEntity != null && familiarEntity.func_70909_n() && (familiarEntity instanceof EntityWitchCat || familiarEntity instanceof EntityOcelot || familiarEntity instanceof EntityToad || familiarEntity instanceof EntityOwl);
    }

    public static EntityTameable getFamiliarEntityByID(EntityPlayer player, UUID uuidFamiliar) {
        if (uuidFamiliar != null) {
            List list = player.field_70170_p.field_72996_f;
            for (int i = 0; i < list.size(); ++i) {
                EntityTameable tameableEntity;
                Object obj = list.get(i);
                if (!(obj instanceof EntityTameable) || !(tameableEntity = (EntityTameable)obj).func_110124_au().equals(uuidFamiliar)) continue;
                return tameableEntity;
            }
            if (!player.field_70170_p.field_72995_K) {
                MinecraftServer server = MinecraftServer.func_71276_C();
                for (WorldServer worldServer : server.field_71305_c) {
                    List list2 = worldServer.field_72996_f;
                    for (int i = 0; i < list2.size(); ++i) {
                        EntityTameable tameableEntity;
                        Object obj = list2.get(i);
                        if (!(obj instanceof EntityTameable) || !(tameableEntity = (EntityTameable)obj).func_110124_au().equals(uuidFamiliar)) continue;
                        return tameableEntity;
                    }
                }
            }
        }
        return null;
    }

    public static EntityTameable getFamiliarEntity(EntityPlayer player) {
        UUID uuidFamiliar = Familiar.getFamiliarEntityID(player);
        EntityTameable familiar = Familiar.getFamiliarEntityByID(player, uuidFamiliar);
        return familiar;
    }

    public static UUID getFamiliarEntityID(EntityPlayer player) {
        NBTTagCompound nbtFamiliar;
        NBTTagCompound nbtTag;
        if (player != null && (nbtTag = Infusion.getNBT((Entity)player)) != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY) && (nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY)) != null && nbtFamiliar.func_74764_b(FAMILIAR_UUID_MOST) && nbtFamiliar.func_74764_b(FAMILIAR_UUID_LEAST)) {
            UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f(FAMILIAR_UUID_MOST), nbtFamiliar.func_74763_f(FAMILIAR_UUID_LEAST));
            return uuidFamiliar;
        }
        return null;
    }

    public static boolean isPlayerBoundToFamiliar(EntityPlayer player, EntityTameable familiar) {
        NBTTagCompound nbtFamiliar;
        NBTTagCompound nbtTag;
        if (player != null && familiar != null && (nbtTag = Infusion.getNBT((Entity)player)) != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY) && (nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY)) != null && nbtFamiliar.func_74764_b(FAMILIAR_UUID_MOST) && nbtFamiliar.func_74764_b(FAMILIAR_UUID_LEAST)) {
            UUID uuidFamiliar = new UUID(nbtFamiliar.func_74763_f(FAMILIAR_UUID_MOST), nbtFamiliar.func_74763_f(FAMILIAR_UUID_LEAST));
            return uuidFamiliar.equals(familiar.func_110124_au());
        }
        return false;
    }

    public static FamiliarOwner getOwnerForFamiliar(EntityTameable familiar) {
        EntityLivingBase owner;
        if (familiar != null && !familiar.field_70170_p.field_72995_K && familiar.func_70909_n() && (owner = familiar.func_70902_q()) != null && owner instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)owner;
            UUID uuidFamiliar = Familiar.getFamiliarEntityID(player);
            if (uuidFamiliar != null && uuidFamiliar.equals(familiar.func_110124_au())) {
                return new FamiliarOwner(player, true);
            }
            return new FamiliarOwner(player, false);
        }
        return new FamiliarOwner(null, false);
    }

    public static boolean hasActiveCurseMasteryFamiliar(EntityPlayer player) {
        int familiarType = Familiar.getActiveFamiliarType(player);
        return familiarType == 1;
    }

    public static boolean hasActiveBrewMasteryFamiliar(EntityPlayer player) {
        int familiarType = Familiar.getActiveFamiliarType(player);
        return familiarType == 2;
    }

    public static boolean hasActiveBroomMasteryFamiliar(EntityPlayer player) {
        int familiarType = Familiar.getActiveFamiliarType(player);
        return familiarType == 3;
    }

    public static boolean hasActiveFamiliar(EntityPlayer player) {
        int familiarType = Familiar.getActiveFamiliarType(player);
        return familiarType > 0;
    }

    public static int getActiveFamiliarType(EntityPlayer player) {
        byte summoned;
        NBTTagCompound nbtFamiliar;
        NBTTagCompound nbtTag;
        if (player != null && !player.field_70170_p.field_72995_K && (nbtTag = Infusion.getNBT((Entity)player)) != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY) && (nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY)).func_74764_b(FAMILIAR_SUMMONED) && nbtFamiliar.func_74764_b(FAMILIAR_TYPE) && nbtFamiliar.func_74764_b(FAMILIAR_NAME) && (summoned = nbtFamiliar.func_74771_c(FAMILIAR_SUMMONED)) == 1) {
            int type = nbtFamiliar.func_74762_e(FAMILIAR_TYPE);
            return type;
        }
        return 0;
    }

    public static void handlePlayerHurt(LivingHurtEvent event, EntityPlayer player) {
        UUID familiarID;
        World world = event.entityLiving.field_70170_p;
        if (!world.field_72995_K && !event.isCanceled() && (familiarID = Familiar.getFamiliarEntityID(player)) != null) {
            float totalDamage = event.ammount;
            float redirectedDamage = totalDamage * 0.01f;
            EntityTameable familiar = Familiar.getFamiliarEntityByID(player, familiarID);
            if (familiar != null) {
                if (familiar.func_70068_e((Entity)player) <= 576.0) {
                    redirectedDamage = totalDamage * 0.1f;
                }
                if (redirectedDamage >= 1.0f) {
                    familiar.func_70097_a(event.source, redirectedDamage);
                }
            }
            event.ammount -= redirectedDamage;
        }
    }

    public static void handleLivingDeath(LivingDeathEvent event) {
        World world = event.entityLiving.field_70170_p;
        if (!world.field_72995_K && !event.isCanceled()) {
            EntityPlayer player;
            EntityTameable familiar;
            if (event.entityLiving instanceof EntityTameable) {
                EntityTameable tameableEntity = (EntityTameable)event.entityLiving;
                if (Familiar.couldBeFamiliar(tameableEntity)) {
                    FamiliarOwner owner = Familiar.getOwnerForFamiliar(tameableEntity);
                    if (owner.player != null && owner.isOwner()) {
                        NBTTagCompound nbtTag = Infusion.getNBT((Entity)owner.player);
                        owner.player.func_70097_a(DamageSource.field_76376_m, owner.player.func_110138_aP() * 2.0f);
                        Familiar.dismissFamiliar(owner.player, tameableEntity);
                        event.setCanceled(true);
                    } else if (owner.player == null) {
                        tameableEntity.func_70606_j(1.0f);
                        event.setCanceled(true);
                    }
                }
            } else if (event.entityLiving instanceof EntityPlayer && (familiar = Familiar.getFamiliarEntity(player = (EntityPlayer)event.entityLiving)) != null && !familiar.field_70128_L) {
                Familiar.dismissFamiliar(player, familiar);
            }
        }
    }

    public static void dismissFamiliar(EntityPlayer player, EntityTameable familiar) {
        NBTTagCompound nbtTag;
        if (player != null && familiar != null && !player.field_70170_p.field_72995_K && Familiar.isPlayerBoundToFamiliar(player, familiar) && (nbtTag = Infusion.getNBT((Entity)player)) != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY)) {
            NBTTagCompound nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY);
            nbtFamiliar.func_74778_a(FAMILIAR_NAME, familiar.func_94057_bL());
            nbtFamiliar.func_74774_a(FAMILIAR_SUMMONED, Byte.valueOf((byte)0).byteValue());
            if (familiar instanceof EntityOwl) {
                nbtFamiliar.func_74774_a(FAMILIAR_COLOR, Byte.valueOf((byte)((EntityOwl)familiar).getFeatherColor()).byteValue());
            } else if (familiar instanceof EntityToad) {
                nbtFamiliar.func_74774_a(FAMILIAR_COLOR, Byte.valueOf((byte)((EntityToad)familiar).getSkinColor()).byteValue());
            }
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)familiar, 1.0, 1.0, 16);
            familiar.func_70106_y();
        }
    }

    public static String getFamiliarName(EntityPlayer player) {
        NBTTagCompound nbtFamiliar;
        NBTTagCompound nbtTag = Infusion.getNBT((Entity)player);
        if (nbtTag != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY) && (nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY)).func_74764_b(FAMILIAR_SUMMONED) && nbtFamiliar.func_74764_b(FAMILIAR_TYPE) && nbtFamiliar.func_74764_b(FAMILIAR_NAME)) {
            byte summoned = nbtFamiliar.func_74771_c(FAMILIAR_SUMMONED);
            String name = nbtFamiliar.func_74779_i(FAMILIAR_NAME);
            return name;
        }
        return null;
    }

    public static EntityTameable summonFamiliar(EntityPlayer player, double x, double y, double z) {
        byte summoned;
        NBTTagCompound nbtFamiliar;
        NBTTagCompound nbtTag;
        if (player != null && !player.field_70170_p.field_72995_K && (nbtTag = Infusion.getNBT((Entity)player)) != null && nbtTag.func_74764_b(FAMILIAR_TAG_KEY) && (nbtFamiliar = nbtTag.func_74775_l(FAMILIAR_TAG_KEY)).func_74764_b(FAMILIAR_SUMMONED) && nbtFamiliar.func_74764_b(FAMILIAR_TYPE) && nbtFamiliar.func_74764_b(FAMILIAR_NAME) && (summoned = nbtFamiliar.func_74771_c(FAMILIAR_SUMMONED)) == 0) {
            String name = nbtFamiliar.func_74779_i(FAMILIAR_NAME);
            int type = nbtFamiliar.func_74762_e(FAMILIAR_TYPE);
            byte color = nbtFamiliar.func_74771_c(FAMILIAR_COLOR);
            IFamiliar familiar = null;
            switch (type) {
                case 1: {
                    familiar = new EntityWitchCat(player.field_70170_p);
                    break;
                }
                case 2: {
                    familiar = new EntityToad(player.field_70170_p);
                    ((EntityToad)familiar).setSkinColor(color);
                    break;
                }
                case 3: {
                    familiar = new EntityOwl(player.field_70170_p);
                    ((EntityOwl)familiar).setFeatherColor(color);
                    break;
                }
                default: {
                    return null;
                }
            }
            familiar.func_70903_f(true);
            TameableUtil.setOwner((EntityTameable)familiar, player);
            familiar.func_94058_c(name);
            ((IFamiliar)familiar).setMaxHealth(50.0f);
            familiar.func_70012_b(x, y, z, 0.0f, 0.0f);
            player.field_70170_p.func_72838_d((Entity)familiar);
            nbtFamiliar.func_74772_a(FAMILIAR_UUID_MOST, familiar.func_110124_au().getMostSignificantBits());
            nbtFamiliar.func_74772_a(FAMILIAR_UUID_LEAST, familiar.func_110124_au().getLeastSignificantBits());
            ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)familiar, 1.0, 1.0, 16);
            nbtFamiliar.func_74774_a(FAMILIAR_SUMMONED, Byte.valueOf((byte)1).byteValue());
            return familiar;
        }
        return null;
    }

    public static boolean couldBeFamiliar(EntityTameable entity) {
        if (entity instanceof IFamiliar) {
            IFamiliar familiar = (IFamiliar)entity;
            return familiar.isFamiliar();
        }
        return false;
    }

    public static class FamiliarOwner {
        private final EntityPlayer player;
        private final boolean owner;

        public FamiliarOwner(EntityPlayer player, boolean owner) {
            this.player = player;
            this.owner = owner;
        }

        public EntityPlayer getPlayer() {
            return this.player;
        }

        public boolean isOwner() {
            return this.owner;
        }

        public EntityPlayer getCurrentOwner() {
            return this.owner ? this.player : null;
        }
    }
}

