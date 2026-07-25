/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 */
package thaumic.tinkerer.common.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;

public class DimensionalShardDropHandler {
    @SubscribeEvent
    public void onEntityLivingDrops(LivingDropsEvent event) {
        if (event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            if (event.entityLiving instanceof EntityEnderman && event.entityLiving.field_71093_bK == ConfigHandler.endDimensionID && Math.random() <= 0.03125) {
                event.drops.add(new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 7)));
            }
            if (event.entityLiving instanceof EntityPigZombie && event.entityLiving.field_71093_bK == ConfigHandler.netherDimensionID && Math.random() <= 0.0625) {
                event.drops.add(new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 6)));
            }
        }
    }
}

