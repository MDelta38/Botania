/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package vazkii.botania.common.world;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileManaFlame;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.world.WorldTypeSkyblock;

public final class SkyblockWorldEvents {
    private static final String TAG_MADE_ISLAND = "Botania-MadeIsland";
    private static final String TAG_HAS_OWN_ISLAND = "Botania-HasOwnIsland";
    private static final String TAG_ISLAND_X = "Botania-IslandX";
    private static final String TAG_ISLAND_Y = "Botania-IslandY";
    private static final String TAG_ISLAND_Z = "Botania-IslandZ";

    @SubscribeEvent
    public void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !event.entity.field_70170_p.field_72995_K) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            NBTTagCompound data = player.getEntityData();
            if (!data.func_74764_b("PlayerPersisted")) {
                data.func_74782_a("PlayerPersisted", (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound persist = data.func_74775_l("PlayerPersisted");
            if (player.field_70173_aa > 3 && !persist.func_74767_n(TAG_MADE_ISLAND)) {
                World world = player.field_70170_p;
                if (WorldTypeSkyblock.isWorldSkyblock(world)) {
                    ChunkCoordinates coords = world.func_72861_E();
                    if (world.func_147439_a(coords.field_71574_a, coords.field_71572_b - 4, coords.field_71573_c) != Blocks.field_150357_h && world.field_73011_w.field_76574_g == 0) {
                        SkyblockWorldEvents.spawnPlayer(player, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, false);
                    }
                }
                persist.func_74757_a(TAG_MADE_ISLAND, true);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (WorldTypeSkyblock.isWorldSkyblock(event.world)) {
            int k;
            int j;
            int i;
            MovingObjectPosition movingobjectposition;
            ItemStack equipped = event.entityPlayer.func_71045_bC();
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && equipped == null && event.entityPlayer.func_70093_af()) {
                Block block = event.world.func_147439_a(event.x, event.y, event.z);
                if (block == Blocks.field_150349_c || block == Blocks.field_150346_d) {
                    if (event.world.field_72995_K) {
                        event.entityPlayer.func_71038_i();
                    } else {
                        event.world.func_72908_a((double)event.x + 0.5, (double)event.y + 0.5, (double)event.z + 0.5, block.field_149762_H.func_150495_a(), block.field_149762_H.func_150497_c() * 0.4f, block.field_149762_H.func_150494_d() + (float)(Math.random() * 0.2 - 0.1));
                        if (Math.random() < 0.8) {
                            event.entityPlayer.func_71019_a(new ItemStack(ModItems.manaResource, 1, 21), false);
                        }
                    }
                }
            } else if (equipped != null && equipped.func_77973_b() == Items.field_151054_z && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && !event.world.field_72995_K && (movingobjectposition = ToolCommons.raytraceFromEntity(event.world, (Entity)event.entityPlayer, true, 4.5)) != null && movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && !event.world.field_72995_K && event.world.func_147439_a(i = movingobjectposition.field_72311_b, j = movingobjectposition.field_72312_c, k = movingobjectposition.field_72309_d).func_149688_o() == Material.field_151586_h) {
                --equipped.field_77994_a;
                if (equipped.field_77994_a <= 0) {
                    event.entityPlayer.field_71071_by.func_70299_a(event.entityPlayer.field_71071_by.field_70461_c, new ItemStack(ModItems.waterBowl));
                } else {
                    event.entityPlayer.func_71019_a(new ItemStack(ModItems.waterBowl), false);
                }
            }
        }
    }

    @SubscribeEvent
    public void onDrops(BlockEvent.HarvestDropsEvent event) {
        if (WorldTypeSkyblock.isWorldSkyblock(event.world) && event.block == Blocks.field_150329_H) {
            ItemStack stackToRemove = null;
            for (ItemStack stack : event.drops) {
                if (stack.func_77973_b() != Items.field_151014_N || event.world.field_73012_v.nextInt(4) != 0) continue;
                stackToRemove = stack;
                break;
            }
            if (stackToRemove != null) {
                event.drops.remove(stackToRemove);
                event.drops.add(new ItemStack(event.world.field_73012_v.nextBoolean() ? Items.field_151080_bb : Items.field_151081_bc));
            }
        }
    }

    public static void spawnPlayer(EntityPlayer player, int x, int y, int z, boolean fabricated) {
        NBTTagCompound data = player.getEntityData();
        if (!data.func_74764_b("PlayerPersisted")) {
            data.func_74782_a("PlayerPersisted", (NBTBase)new NBTTagCompound());
        }
        NBTTagCompound persist = data.func_74775_l("PlayerPersisted");
        boolean test = false;
        if (!persist.func_74767_n(TAG_HAS_OWN_ISLAND)) {
            SkyblockWorldEvents.createSkyblock(player.field_70170_p, x, y, z);
            if (player instanceof EntityPlayerMP) {
                EntityPlayerMP pmp = (EntityPlayerMP)player;
                pmp.func_70634_a((double)x + 0.5, (double)y + 1.6, (double)z + 0.5);
                pmp.func_71063_a(new ChunkCoordinates(x, y, z), true);
                player.field_71071_by.func_70441_a(new ItemStack(ModItems.lexicon));
            }
            if (fabricated) {
                persist.func_74757_a(TAG_HAS_OWN_ISLAND, true);
                persist.func_74780_a(TAG_ISLAND_X, player.field_70165_t);
                persist.func_74780_a(TAG_ISLAND_Y, player.field_70163_u);
                persist.func_74780_a(TAG_ISLAND_Z, player.field_70161_v);
            }
        } else {
            double posX = persist.func_74769_h(TAG_ISLAND_X);
            double posY = persist.func_74769_h(TAG_ISLAND_Y);
            double posZ = persist.func_74769_h(TAG_ISLAND_Z);
            if (player instanceof EntityPlayerMP) {
                EntityPlayerMP pmp = (EntityPlayerMP)player;
                pmp.func_70634_a(posX, posY, posZ);
            }
        }
    }

    public static void createSkyblock(World world, int x, int y, int z) {
        int[][] rootPositions;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 3; ++k) {
                    world.func_147449_b(x - 1 + i, y - 1 - j, z - 1 + k, (Block)(j == 0 ? Blocks.field_150349_c : Blocks.field_150346_d));
                }
            }
        }
        world.func_147449_b(x - 1, y - 2, z, (Block)Blocks.field_150358_i);
        world.func_147449_b(x + 1, y + 2, z + 1, ModBlocks.manaFlame);
        ((TileManaFlame)world.func_147438_o(x + 1, y + 2, z + 1)).setColor(new Color(70 + world.field_73012_v.nextInt(185), 70 + world.field_73012_v.nextInt(185), 70 + world.field_73012_v.nextInt(185)).getRGB());
        for (int[] root : rootPositions = new int[][]{{-1, -3, -1}, {-2, -4, -1}, {-2, -4, -2}, {1, -4, -1}, {1, -5, -1}, {2, -5, -1}, {2, -6, 0}, {0, -4, 2}, {0, -5, 2}, {0, -5, 3}, {0, -6, 3}}) {
            world.func_147449_b(x + root[0], y + root[1], z + root[2], ModBlocks.root);
        }
        world.func_147449_b(x, y - 4, z, Blocks.field_150357_h);
    }
}

