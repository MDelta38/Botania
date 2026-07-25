/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$WorldTickEvent
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  org.apache.logging.log4j.Level
 */
package thaumcraft.common.lib.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.apache.logging.log4j.Level;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.world.ChunkLoc;
import thaumcraft.common.tiles.TileSensor;

public class ServerTickEventsFML {
    public static Map<Integer, LinkedBlockingQueue<VirtualSwapper>> swapList = new HashMap<Integer, LinkedBlockingQueue<VirtualSwapper>>();
    public static HashMap<Integer, ArrayList<ChunkLoc>> chunksToGenerate = new HashMap();

    @SubscribeEvent
    public void serverWorldTick(TickEvent.WorldTickEvent event) {
        if (event.side == Side.CLIENT) {
            return;
        }
        if (event.phase != TickEvent.Phase.START) {
            this.tickChunkRegeneration(event);
            this.tickBlockSwap(event.world);
            if (TileSensor.noteBlockEvents.get(event.world) != null) {
                TileSensor.noteBlockEvents.get(event.world).clear();
            }
        }
    }

    public void tickChunkRegeneration(TickEvent.WorldTickEvent event) {
        int dim = event.world.field_73011_w.field_76574_g;
        int count = 0;
        ArrayList<ChunkLoc> chunks = chunksToGenerate.get(dim);
        if (chunks != null && chunks.size() > 0) {
            for (int a = 0; a < 10 && (chunks = chunksToGenerate.get(dim)) != null && chunks.size() > 0; ++a) {
                ++count;
                ChunkLoc loc = chunks.get(0);
                long worldSeed = event.world.func_72905_C();
                Random fmlRandom = new Random(worldSeed);
                long xSeed = fmlRandom.nextLong() >> 3;
                long zSeed = fmlRandom.nextLong() >> 3;
                fmlRandom.setSeed(xSeed * (long)loc.chunkXPos + zSeed * (long)loc.chunkZPos ^ worldSeed);
                Thaumcraft.instance.worldGen.worldGeneration(fmlRandom, loc.chunkXPos, loc.chunkZPos, event.world, false);
                chunks.remove(0);
                chunksToGenerate.put(dim, chunks);
            }
        }
        if (count > 0) {
            FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] Regenerated " + count + " chunks. " + Math.max(0, chunks.size()) + " chunks left");
        }
    }

    private void tickBlockSwap(World world) {
        int dim = world.field_73011_w.field_76574_g;
        LinkedBlockingQueue<VirtualSwapper> queue = swapList.get(dim);
        if (queue != null) {
            boolean didSomething = false;
            while (!didSomething) {
                VirtualSwapper vs = queue.poll();
                if (vs != null) {
                    Block bi = world.func_147439_a(vs.x, vs.y, vs.z);
                    int md = world.func_72805_g(vs.x, vs.y, vs.z);
                    ItemWandCasting wand = null;
                    ItemFocusBasic focus = null;
                    ItemStack focusStack = null;
                    if (vs.player.field_71071_by.func_70301_a(vs.wand) != null && vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b() instanceof ItemWandCasting) {
                        wand = (ItemWandCasting)vs.player.field_71071_by.func_70301_a(vs.wand).func_77973_b();
                        focusStack = wand.getFocusItem(vs.player.field_71071_by.func_70301_a(vs.wand));
                        focus = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand));
                    }
                    if (!world.func_72962_a(vs.player, vs.x, vs.y, vs.z) || vs.target.func_77969_a(new ItemStack(bi, 1, md)) || wand == null || focus == null || ForgeEventFactory.onPlayerInteract((EntityPlayer)vs.player, (PlayerInteractEvent.Action)PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK, (int)vs.x, (int)vs.y, (int)vs.z, (int)1, (World)world).isCanceled() || !wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), false, false)) continue;
                    int slot = InventoryUtils.isPlayerCarrying(vs.player, vs.target);
                    if (vs.player.field_71075_bZ.field_75098_d) {
                        slot = 1;
                    }
                    if (vs.bSource != bi || vs.mSource != md || slot < 0) continue;
                    didSomething = true;
                    if (!vs.player.field_71075_bZ.field_75098_d) {
                        int fortune = wand.getFocusTreasure(vs.player.field_71071_by.func_70301_a(vs.wand));
                        boolean silk = wand.getFocus(vs.player.field_71071_by.func_70301_a(vs.wand)).isUpgradedWith(wand.getFocusItem(vs.player.field_71071_by.func_70301_a(vs.wand)), FocusUpgradeType.silktouch);
                        vs.player.field_71071_by.func_70298_a(slot, 1);
                        ArrayList ret = new ArrayList();
                        if (silk && bi.canSilkHarvest(world, vs.player, vs.x, vs.y, vs.z, md)) {
                            ItemStack itemstack = BlockUtils.createStackedBlock(bi, md);
                            if (itemstack != null) {
                                ret.add(itemstack);
                            }
                        } else {
                            ret = bi.getDrops(world, vs.x, vs.y, vs.z, md, fortune);
                        }
                        if (ret.size() > 0) {
                            for (ItemStack is : ret) {
                                if (vs.player.field_71071_by.func_70441_a(is)) continue;
                                world.func_72838_d((Entity)new EntityItem(world, (double)vs.x + 0.5, (double)vs.y + 0.5, (double)vs.z + 0.5, is));
                            }
                        }
                        wand.consumeAllVis(vs.player.field_71071_by.func_70301_a(vs.wand), vs.player, focus.getVisCost(focusStack), true, false);
                    }
                    world.func_147465_d(vs.x, vs.y, vs.z, Block.func_149634_a((Item)vs.target.func_77973_b()), vs.target.func_77960_j(), 3);
                    Block.func_149634_a((Item)vs.target.func_77973_b()).func_149689_a(world, vs.x, vs.y, vs.z, (EntityLivingBase)vs.player, vs.target);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(vs.x, vs.y, vs.z, 0xC0C0FF), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)vs.x, (double)vs.y, (double)vs.z, 32.0));
                    world.func_72926_e(2001, vs.x, vs.y, vs.z, Block.func_149682_b((Block)vs.bSource) + (vs.mSource << 12));
                    if (vs.lifespan <= 0) continue;
                    for (int xx = -1; xx <= 1; ++xx) {
                        for (int yy = -1; yy <= 1; ++yy) {
                            for (int zz = -1; zz <= 1; ++zz) {
                                if (xx == 0 && yy == 0 && zz == 0 || world.func_147439_a(vs.x + xx, vs.y + yy, vs.z + zz) != vs.bSource || world.func_72805_g(vs.x + xx, vs.y + yy, vs.z + zz) != vs.mSource || !BlockUtils.isBlockExposed(world, vs.x + xx, vs.y + yy, vs.z + zz)) continue;
                                queue.offer(new VirtualSwapper(vs.x + xx, vs.y + yy, vs.z + zz, vs.bSource, vs.mSource, vs.target, vs.lifespan - 1, vs.player, vs.wand));
                            }
                        }
                    }
                    continue;
                }
                didSomething = true;
            }
            swapList.put(dim, queue);
        }
    }

    public static void addSwapper(World world, int x, int y, int z, Block bs, int ms, ItemStack target, int life, EntityPlayer player, int wand) {
        int dim = world.field_73011_w.field_76574_g;
        if (bs == Blocks.field_150350_a || bs.func_149712_f(world, x, y, z) < 0.0f || target.func_77969_a(new ItemStack(bs, 1, ms))) {
            return;
        }
        LinkedBlockingQueue<VirtualSwapper> queue = swapList.get(dim);
        if (queue == null) {
            swapList.put(dim, new LinkedBlockingQueue());
            queue = swapList.get(dim);
        }
        queue.offer(new VirtualSwapper(x, y, z, bs, ms, target, life, player, wand));
        world.func_72956_a((Entity)player, "thaumcraft:wand", 0.25f, 1.0f);
        swapList.put(dim, queue);
    }

    public static class RestorableWardedBlock {
        int x = 0;
        int y = 0;
        int z = 0;
        Block bi;
        int md = 0;
        NBTTagCompound nbt = null;

        RestorableWardedBlock(World world, int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.bi = world.func_147439_a(x, y, z);
            this.md = world.func_72805_g(x, y, z);
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null) {
                this.nbt = new NBTTagCompound();
                te.func_145841_b(this.nbt);
            }
        }
    }

    public static class VirtualSwapper {
        int lifespan = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        Block bSource;
        int mSource = 0;
        ItemStack target;
        int wand = 0;
        EntityPlayer player = null;

        VirtualSwapper(int x, int y, int z, Block bs, int ms, ItemStack t, int life, EntityPlayer p, int wand) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.bSource = bs;
            this.mSource = ms;
            this.target = t;
            this.lifespan = life;
            this.player = p;
            this.wand = wand;
        }
    }
}

