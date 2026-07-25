/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  thaumcraft.common.entities.projectile.EntityFrostShard
 */
package thaumic.tinkerer.common.core.helper;

import com.google.common.base.Function;
import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import thaumcraft.common.entities.projectile.EntityFrostShard;

public final class ProjectileHelper {
    private static Map<Class<? extends Entity>, Function<Entity, Entity>> ownerGetters = new IdentityHashMap<Class<? extends Entity>, Function<Entity, Entity>>();

    public static Entity getOwner(Entity projectile) {
        Function<Entity, Entity> ownerGetterForClass = ownerGetters.get(projectile.getClass());
        if (ownerGetterForClass != null) {
            return (Entity)ownerGetterForClass.apply((Object)projectile);
        }
        for (Map.Entry<Class<? extends Entity>, Function<Entity, Entity>> ownerGetter : ownerGetters.entrySet()) {
            if (!ownerGetter.getKey().isAssignableFrom(projectile.getClass())) continue;
            return (Entity)ownerGetter.getValue().apply((Object)projectile);
        }
        return null;
    }

    public static void registerOwnerGetter(Class<? extends Entity> projectileClass, Function<Entity, Entity> ownerGetter) {
        ownerGetters.put(projectileClass, ownerGetter);
    }

    static {
        ProjectileHelper.registerOwnerGetter(EntityArrow.class, new VanillaArrowOwnerGetter());
        ProjectileHelper.registerOwnerGetter(EntityThrowable.class, new VanillaThrowableOwnerGetter());
        ProjectileHelper.registerOwnerGetter(EntityFrostShard.class, new ThaumcraftFrostShardOwnerGetter());
    }

    public static class ThaumcraftFrostShardOwnerGetter
    implements Function<Entity, Entity> {
        public Entity apply(Entity e) {
            EntityLivingBase owner = ((EntityFrostShard)e).func_85052_h();
            return owner;
        }
    }

    public static class VanillaThrowableOwnerGetter
    implements Function<Entity, Entity> {
        public Entity apply(Entity e) {
            return ((EntityThrowable)e).func_85052_h();
        }
    }

    public static class VanillaArrowOwnerGetter
    implements Function<Entity, Entity> {
        public Entity apply(Entity e) {
            return ((EntityArrow)e).field_70250_c;
        }
    }
}

