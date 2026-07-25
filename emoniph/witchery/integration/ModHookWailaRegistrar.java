/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mcp.mobius.waila.api.IWailaConfigHandler
 *  mcp.mobius.waila.api.IWailaDataAccessor
 *  mcp.mobius.waila.api.IWailaDataProvider
 *  mcp.mobius.waila.api.IWailaEntityAccessor
 *  mcp.mobius.waila.api.IWailaEntityProvider
 *  mcp.mobius.waila.api.IWailaRegistrar
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockPitDirt;
import com.emoniph.witchery.blocks.BlockPitGrass;
import com.emoniph.witchery.blocks.BlockPlantMine;
import com.emoniph.witchery.blocks.BlockWitchDoor;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityVillagerWere;
import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaEntityAccessor;
import mcp.mobius.waila.api.IWailaEntityProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ModHookWailaRegistrar
implements IWailaDataProvider,
IWailaEntityProvider {
    private static final ItemStack yellowPlant = new ItemStack((Block)Blocks.field_150327_N);
    private static final ItemStack redPlant = new ItemStack((Block)Blocks.field_150328_O);
    private static final ItemStack shrubPlant = new ItemStack((Block)Blocks.field_150330_I);
    private static final ItemStack door = new ItemStack(Items.field_151135_aq);
    private static final ItemStack dirt = new ItemStack(Blocks.field_150346_d);
    private static final ItemStack grass = new ItemStack((Block)Blocks.field_150349_c);
    private static final ItemStack rowandoor = new ItemStack(Witchery.Blocks.DOOR_ROWAN);
    private static Entity CREEPER;
    private static Entity ZOMBIE;
    private static Entity SPIDER;
    private static EntityVillager VILLAGER;

    public static void callbackRegister(IWailaRegistrar registrar) {
        ModHookWailaRegistrar provider = new ModHookWailaRegistrar();
        registrar.registerStackProvider((IWailaDataProvider)provider, BlockPlantMine.class);
        registrar.registerStackProvider((IWailaDataProvider)provider, BlockWitchDoor.class);
        registrar.registerStackProvider((IWailaDataProvider)provider, BlockPitDirt.class);
        registrar.registerStackProvider((IWailaDataProvider)provider, BlockPitGrass.class);
        registrar.registerOverrideEntityProvider((IWailaEntityProvider)provider, EntityIllusionCreeper.class);
        registrar.registerOverrideEntityProvider((IWailaEntityProvider)provider, EntityIllusionSpider.class);
        registrar.registerOverrideEntityProvider((IWailaEntityProvider)provider, EntityIllusionZombie.class);
        registrar.registerOverrideEntityProvider((IWailaEntityProvider)provider, EntityVillagerWere.class);
    }

    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getBlock() == Witchery.Blocks.TRAPPED_PLANT) {
            int foundMeta = accessor.getMetadata();
            if (foundMeta == 5 || foundMeta == 6 || foundMeta == 7 || foundMeta == 4) {
                return yellowPlant;
            }
            if (foundMeta == 1 || foundMeta == 2 || foundMeta == 3 || foundMeta == 0) {
                return redPlant;
            }
            if (foundMeta == 9 || foundMeta == 10 || foundMeta == 11 || foundMeta == 8) {
                return shrubPlant;
            }
        } else {
            if (accessor.getBlock() == Witchery.Blocks.DOOR_ALDER) {
                return door;
            }
            if (accessor.getBlock() == Witchery.Blocks.DOOR_ROWAN) {
                return rowandoor;
            }
            if (accessor.getBlock() == Witchery.Blocks.PIT_DIRT) {
                return dirt;
            }
            if (accessor.getBlock() == Witchery.Blocks.PIT_GRASS) {
                return grass;
            }
        }
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public Entity getWailaOverride(IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        if (accessor.getEntity() instanceof EntityIllusionCreeper) {
            if (CREEPER == null || ModHookWailaRegistrar.CREEPER.field_70170_p != accessor.getWorld()) {
                CREEPER = new EntityCreeper(accessor.getWorld());
            }
            return CREEPER;
        }
        if (accessor.getEntity() instanceof EntityIllusionZombie) {
            if (ZOMBIE == null || ModHookWailaRegistrar.ZOMBIE.field_70170_p != accessor.getWorld()) {
                ZOMBIE = new EntityZombie(accessor.getWorld());
            }
            return ZOMBIE;
        }
        if (accessor.getEntity() instanceof EntityIllusionSpider) {
            if (SPIDER == null || ModHookWailaRegistrar.SPIDER.field_70170_p != accessor.getWorld()) {
                SPIDER = new EntitySpider(accessor.getWorld());
            }
            return SPIDER;
        }
        if (accessor.getEntity() instanceof EntityVillagerWere) {
            EntityVillagerWere were = (EntityVillagerWere)accessor.getEntity();
            if (VILLAGER == null || ModHookWailaRegistrar.VILLAGER.field_70170_p != accessor.getWorld()) {
                VILLAGER = new EntityVillager(accessor.getWorld());
            }
            VILLAGER.func_70938_b(were.func_70946_n());
            return VILLAGER;
        }
        return null;
    }

    public List<String> getWailaHead(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaBody(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaTail(Entity entity, List<String> currenttip, IWailaEntityAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }
}

