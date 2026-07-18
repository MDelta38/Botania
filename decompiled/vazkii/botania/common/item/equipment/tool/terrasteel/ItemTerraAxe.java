/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$WorldTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool.terrasteel;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ItemTemperanceStone;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelAxe;
import vazkii.botania.common.item.relic.ItemLokiRing;

public class ItemTerraAxe
extends ItemManasteelAxe
implements ISequentialBreaker {
    public static final int BLOCK_SWAP_RATE = 10;
    public static final int BLOCK_RANGE = 32;
    public static final int LEAF_BLOCK_RANGE = 3;
    private static final int MANA_PER_DAMAGE = 100;
    private static Map<Integer, Set<BlockSwapper>> blockSwappers = new HashMap<Integer, Set<BlockSwapper>>();
    IIcon iconOn;
    IIcon iconOff;

    public ItemTerraAxe() {
        super(BotaniaAPI.terrasteelToolMaterial, "terraAxe");
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.iconOn = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconOff = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    public IIcon func_77617_a(int p_77617_1_) {
        return this.iconOn;
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        return this.shouldBreak(player) ? this.iconOn : this.iconOff;
    }

    public boolean shouldBreak(EntityPlayer player) {
        return !player.func_70093_af() && !ItemTemperanceStone.hasTemperanceActive(player);
    }

    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        MovingObjectPosition raycast = ToolCommons.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0);
        if (raycast != null) {
            this.breakOtherBlock(player, stack, x, y, z, x, y, z, raycast.field_72310_e);
            ItemLokiRing.breakOnAllCursors(player, (Item)this, stack, x, y, z, raycast.field_72310_e);
        }
        return false;
    }

    @Override
    public int getManaPerDamage() {
        return 100;
    }

    @Override
    public void breakOtherBlock(EntityPlayer player, ItemStack stack, int x, int y, int z, int originX, int originY, int originZ, int side) {
        if (this.shouldBreak(player)) {
            ChunkCoordinates coords = new ChunkCoordinates(x, y, z);
            ItemTerraAxe.addBlockSwapper(player.field_70170_p, player, stack, coords, 32, true);
        }
    }

    @Override
    public boolean disposeOfTrashBlocks(ItemStack stack) {
        return false;
    }

    @SubscribeEvent
    public void onTickEnd(TickEvent.WorldTickEvent event) {
        int dim;
        if (event.world.field_72995_K) {
            return;
        }
        if (event.phase == TickEvent.Phase.END && blockSwappers.containsKey(dim = event.world.field_73011_w.field_76574_g)) {
            Set<BlockSwapper> swappers = blockSwappers.get(dim);
            Iterator<BlockSwapper> swapper = swappers.iterator();
            while (swapper.hasNext()) {
                BlockSwapper next = swapper.next();
                if (next != null && next.tick()) continue;
                swapper.remove();
            }
        }
    }

    private static BlockSwapper addBlockSwapper(World world, EntityPlayer player, ItemStack stack, ChunkCoordinates origCoords, int steps, boolean leaves) {
        BlockSwapper swapper = new BlockSwapper(world, player, stack, origCoords, steps, leaves);
        if (world.field_72995_K) {
            return swapper;
        }
        int dim = world.field_73011_w.field_76574_g;
        if (!blockSwappers.containsKey(dim)) {
            blockSwappers.put(dim, new HashSet());
        }
        blockSwappers.get(dim).add(swapper);
        return swapper;
    }

    private static class BlockSwapper {
        public static final int SINGLE_BLOCK_RADIUS = 1;
        private final World world;
        private final EntityPlayer player;
        private final ItemStack truncator;
        private final ChunkCoordinates origin;
        private final boolean treatLeavesSpecial;
        private final int range;
        private PriorityQueue<SwapCandidate> candidateQueue;
        private Set<ChunkCoordinates> completedCoords;

        public BlockSwapper(World world, EntityPlayer player, ItemStack truncator, ChunkCoordinates origCoords, int range, boolean leaves) {
            this.world = world;
            this.player = player;
            this.truncator = truncator;
            this.origin = origCoords;
            this.range = range;
            this.treatLeavesSpecial = leaves;
            this.candidateQueue = new PriorityQueue();
            this.completedCoords = new HashSet<ChunkCoordinates>();
            this.candidateQueue.offer(new SwapCandidate(this.origin, this.range));
        }

        public boolean tick() {
            if (this.candidateQueue.isEmpty()) {
                return false;
            }
            int remainingSwaps = 10;
            while (remainingSwaps > 0 && !this.candidateQueue.isEmpty()) {
                SwapCandidate cand = this.candidateQueue.poll();
                if (this.completedCoords.contains(cand.coordinates) || cand.range <= 0) continue;
                ToolCommons.removeBlockWithDrops(this.player, this.truncator, this.world, cand.coordinates.field_71574_a, cand.coordinates.field_71572_b, cand.coordinates.field_71573_c, this.origin.field_71574_a, this.origin.field_71572_b, this.origin.field_71573_c, null, ToolCommons.materialsAxe, EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)this.truncator) > 0, EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)this.truncator), 0.0f, false, this.treatLeavesSpecial);
                --remainingSwaps;
                this.completedCoords.add(cand.coordinates);
                for (ChunkCoordinates adj : this.adjacent(cand.coordinates)) {
                    Block block = this.world.func_147439_a(adj.field_71574_a, adj.field_71572_b, adj.field_71573_c);
                    boolean isWood = block.isWood((IBlockAccess)this.world, adj.field_71574_a, adj.field_71572_b, adj.field_71573_c);
                    boolean isLeaf = block.isLeaves((IBlockAccess)this.world, adj.field_71574_a, adj.field_71572_b, adj.field_71573_c);
                    if (!isWood && !isLeaf) continue;
                    int newRange = this.treatLeavesSpecial && isLeaf ? Math.min(3, cand.range - 1) : cand.range - 1;
                    this.candidateQueue.offer(new SwapCandidate(adj, newRange));
                }
            }
            return true;
        }

        public List<ChunkCoordinates> adjacent(ChunkCoordinates original) {
            ArrayList<ChunkCoordinates> coords = new ArrayList<ChunkCoordinates>();
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1; ++dy) {
                    for (int dz = -1; dz <= 1; ++dz) {
                        if (dx == 0 && dy == 0 && dz == 0) continue;
                        coords.add(new ChunkCoordinates(original.field_71574_a + dx, original.field_71572_b + dy, original.field_71573_c + dz));
                    }
                }
            }
            return coords;
        }

        public static final class SwapCandidate
        implements Comparable<SwapCandidate> {
            public ChunkCoordinates coordinates;
            public int range;

            public SwapCandidate(ChunkCoordinates coordinates, int range) {
                this.coordinates = coordinates;
                this.range = range;
            }

            @Override
            public int compareTo(SwapCandidate other) {
                return other.range - this.range;
            }

            public boolean equals(Object other) {
                if (!(other instanceof SwapCandidate)) {
                    return false;
                }
                SwapCandidate cand = (SwapCandidate)other;
                return this.coordinates.equals((Object)cand.coordinates) && this.range == cand.range;
            }
        }
    }
}

