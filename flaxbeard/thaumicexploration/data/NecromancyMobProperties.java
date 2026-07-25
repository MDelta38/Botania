/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package flaxbeard.thaumicexploration.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NecromancyMobProperties
implements IExtendedEntityProperties {
    private EntityLiving owner;
    private EntityLiving target;

    public static final void register(EntityLiving entity) {
        entity.registerExtendedProperties("thaumicExplorationNecromancyProperties", (IExtendedEntityProperties)new NecromancyMobProperties());
    }

    public static final NecromancyMobProperties get(EntityLiving entity) {
        return (NecromancyMobProperties)entity.getExtendedProperties("thaumicExplorationNecromancyProperties");
    }

    public void saveNBTData(NBTTagCompound data) {
    }

    public void loadNBTData(NBTTagCompound data) {
    }

    public void setTarget(EntityLivingBase targetEntity) {
        this.target = (EntityLiving)targetEntity;
    }

    public EntityLiving getTarget() {
        return this.target;
    }

    public void init(Entity entity, World world) {
        if (entity instanceof EntityLiving) {
            this.owner = (EntityLiving)entity;
        }
    }
}

