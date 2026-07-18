/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityList
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public final class SpawnerChangingHandler {
    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        TileEntity tile;
        ItemStack stack;
        if (event.entityPlayer == null || event.entityPlayer.field_71075_bZ == null || event.world == null) {
            return;
        }
        if (event.entityPlayer.field_71075_bZ.field_75098_d && !event.world.field_72995_K && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !event.entityPlayer.func_70093_af() && (stack = event.entityPlayer.func_71045_bC()) != null && stack.func_77973_b() == Items.field_151063_bx && (tile = event.world.func_147438_o(event.x, event.y, event.z)) instanceof TileEntityMobSpawner) {
            TileEntityMobSpawner spawner = (TileEntityMobSpawner)tile;
            spawner.func_145881_a().func_98272_a(EntityList.func_75617_a((int)stack.func_77960_j()));
            event.world.func_147471_g(event.x, event.y, event.z);
            event.setCanceled(true);
        }
    }
}

