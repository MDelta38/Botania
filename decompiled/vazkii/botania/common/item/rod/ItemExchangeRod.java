/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package vazkii.botania.common.item.rod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IBlockProvider;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.item.IWireframeCoordinateListProvider;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.common.block.BlockCamo;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemExchangeRod
extends ItemMod
implements IManaUsingItem,
IWireframeCoordinateListProvider {
    private static final int RANGE = 3;
    private static final int COST = 40;
    private static final String TAG_BLOCK_NAME = "blockName";
    private static final String TAG_BLOCK_META = "blockMeta";
    private static final String TAG_TARGET_BLOCK_NAME = "targetBlockName";
    private static final String TAG_TARGET_BLOCK_META = "targetBlockMeta";
    private static final String TAG_SWAPPING = "swapping";
    private static final String TAG_SELECT_X = "selectX";
    private static final String TAG_SELECT_Y = "selectY";
    private static final String TAG_SELECT_Z = "selectZ";
    private static final String TAG_EXTRA_RANGE = "extraRange";

    public ItemExchangeRod() {
        this.func_77625_d(1);
        this.func_77655_b("exchangeRod");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        int meta;
        Block block;
        List<ChunkCoordinates> swap;
        Block wblock = par3World.func_147439_a(par4, par5, par6);
        int wmeta = par3World.func_72805_g(par4, par5, par6);
        if (par2EntityPlayer.func_70093_af()) {
            TileEntity tile = par3World.func_147438_o(par4, par5, par6);
            if (tile == null && BlockCamo.isValidBlock(wblock)) {
                Item item = Item.func_150898_a((Block)wblock);
                if (!item.func_77614_k()) {
                    wmeta = 0;
                }
                boolean set = this.setBlock(par1ItemStack, wblock, wmeta);
                par2EntityPlayer.func_70062_b(0, par1ItemStack);
                this.displayRemainderCounter(par2EntityPlayer, par1ItemStack);
                return set;
            }
        } else if (this.canExchange(par1ItemStack) && !ItemNBTHelper.getBoolean(par1ItemStack, TAG_SWAPPING, false) && (swap = this.getBlocksToSwap(par3World, par1ItemStack, block = ItemExchangeRod.getBlock(par1ItemStack), meta = ItemExchangeRod.getBlockMeta(par1ItemStack), par4, par5, par6, null, 0)).size() > 0) {
            ItemNBTHelper.setBoolean(par1ItemStack, TAG_SWAPPING, true);
            ItemNBTHelper.setInt(par1ItemStack, TAG_SELECT_X, par4);
            ItemNBTHelper.setInt(par1ItemStack, TAG_SELECT_Y, par5);
            ItemNBTHelper.setInt(par1ItemStack, TAG_SELECT_Z, par6);
            this.setTargetBlock(par1ItemStack, wblock, wmeta);
            if (par3World.field_72995_K) {
                par2EntityPlayer.func_71038_i();
            }
        }
        return false;
    }

    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent event) {
        ItemStack stack;
        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && (stack = event.entityPlayer.func_71045_bC()) != null && stack.func_77973_b() == this && this.canExchange(stack) && ManaItemHandler.requestManaExactForTool(stack, event.entityPlayer, 40, false) && this.exchange(event.world, event.entityPlayer, event.x, event.y, event.z, stack, ItemExchangeRod.getBlock(stack), ItemExchangeRod.getBlockMeta(stack))) {
            ManaItemHandler.requestManaExactForTool(stack, event.entityPlayer, 40, true);
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int something, boolean somethingelse) {
        int extraRangeNew;
        if (!this.canExchange(stack) || !(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)entity;
        int extraRange = ItemNBTHelper.getInt(stack, TAG_EXTRA_RANGE, 1);
        int n = extraRangeNew = IManaProficiencyArmor.Helper.hasProficiency(player) ? 3 : 1;
        if (extraRange != extraRangeNew) {
            ItemNBTHelper.setInt(stack, TAG_EXTRA_RANGE, extraRangeNew);
        }
        Block block = ItemExchangeRod.getBlock(stack);
        int meta = ItemExchangeRod.getBlockMeta(stack);
        if (ItemNBTHelper.getBoolean(stack, TAG_SWAPPING, false)) {
            int targetMeta;
            Block targetBlock;
            int z;
            int y;
            if (!ManaItemHandler.requestManaExactForTool(stack, player, 40, false)) {
                ItemNBTHelper.setBoolean(stack, TAG_SWAPPING, false);
                return;
            }
            int x = ItemNBTHelper.getInt(stack, TAG_SELECT_X, 0);
            List<ChunkCoordinates> swap = this.getBlocksToSwap(world, stack, block, meta, x, y = ItemNBTHelper.getInt(stack, TAG_SELECT_Y, 0), z = ItemNBTHelper.getInt(stack, TAG_SELECT_Z, 0), targetBlock = ItemExchangeRod.getTargetBlock(stack), targetMeta = ItemExchangeRod.getTargetBlockMeta(stack));
            if (swap.size() == 0) {
                ItemNBTHelper.setBoolean(stack, TAG_SWAPPING, false);
                return;
            }
            ChunkCoordinates coords = swap.get(world.field_73012_v.nextInt(swap.size()));
            boolean exchange = this.exchange(world, player, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, stack, block, meta);
            if (exchange) {
                ManaItemHandler.requestManaExactForTool(stack, player, 40, true);
            } else {
                ItemNBTHelper.setBoolean(stack, TAG_SWAPPING, false);
            }
        }
    }

    public List<ChunkCoordinates> getBlocksToSwap(World world, ItemStack stack, Block blockToSwap, int metaToSwap, int xc, int yc, int zc, Block targetBlock, int targetMeta) {
        if (targetBlock == null) {
            targetBlock = world.func_147439_a(xc, yc, zc);
            targetMeta = world.func_72805_g(xc, yc, zc);
        }
        ArrayList<ChunkCoordinates> coordsList = new ArrayList<ChunkCoordinates>();
        int effRange = 3 + ItemNBTHelper.getInt(stack, TAG_EXTRA_RANGE, 1) - 1;
        for (int offsetX = -effRange; offsetX <= effRange; ++offsetX) {
            for (int offsetY = -effRange; offsetY <= effRange; ++offsetY) {
                block2: for (int offsetZ = -effRange; offsetZ <= effRange; ++offsetZ) {
                    int x = xc + offsetX;
                    int y = yc + offsetY;
                    int z = zc + offsetZ;
                    Block currentBlock = world.func_147439_a(x, y, z);
                    int currentMeta = world.func_72805_g(x, y, z);
                    if (currentBlock != targetBlock || currentMeta != targetMeta || currentBlock == blockToSwap && currentMeta == metaToSwap) continue;
                    for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                        int adjX = x + dir.offsetX;
                        int adjY = y + dir.offsetY;
                        int adjZ = z + dir.offsetZ;
                        Block adjBlock = world.func_147439_a(adjX, adjY, adjZ);
                        if (adjBlock.isSideSolid((IBlockAccess)world, adjX, adjY, adjZ, dir.getOpposite())) continue;
                        coordsList.add(new ChunkCoordinates(x, y, z));
                        continue block2;
                    }
                }
            }
        }
        return coordsList;
    }

    public boolean exchange(World world, EntityPlayer player, int x, int y, int z, ItemStack stack, Block blockToSet, int metaToSet) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            return false;
        }
        ItemStack placeStack = ItemExchangeRod.removeFromInventory(player, stack, blockToSet, metaToSet, false);
        if (placeStack != null) {
            Block blockAt = world.func_147439_a(x, y, z);
            int meta = world.func_72805_g(x, y, z);
            if (!blockAt.isAir((IBlockAccess)world, x, y, z) && blockAt.func_149737_a(player, world, x, y, z) > 0.0f && (blockAt != blockToSet || meta != metaToSet)) {
                if (!world.field_72995_K) {
                    if (!player.field_71075_bZ.field_75098_d) {
                        ArrayList drops = blockAt.getDrops(world, x, y, z, meta, 0);
                        for (ItemStack drop : drops) {
                            world.func_72838_d((Entity)new EntityItem(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, drop));
                        }
                        ItemExchangeRod.removeFromInventory(player, stack, blockToSet, metaToSet, true);
                    }
                    world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)blockAt) + (meta << 12));
                    world.func_147465_d(x, y, z, blockToSet, metaToSet, 3);
                    blockToSet.func_149689_a(world, x, y, z, (EntityLivingBase)player, placeStack);
                }
                this.displayRemainderCounter(player, stack);
                return true;
            }
        }
        return false;
    }

    public boolean canExchange(ItemStack stack) {
        Block block = ItemExchangeRod.getBlock(stack);
        return block != null && block != Blocks.field_150350_a;
    }

    public static ItemStack removeFromInventory(EntityPlayer player, IInventory inv, ItemStack stack, Block block, int meta, boolean doit) {
        ArrayList<ItemStack> providers = new ArrayList<ItemStack>();
        for (int i = inv.func_70302_i_() - 1; i >= 0; --i) {
            ItemStack invStack = inv.func_70301_a(i);
            if (invStack == null) continue;
            Item item = invStack.func_77973_b();
            if (item == Item.func_150898_a((Block)block) && invStack.func_77960_j() == meta) {
                ItemStack retStack = invStack.func_77946_l();
                if (doit) {
                    --invStack.field_77994_a;
                    if (invStack.field_77994_a == 0) {
                        inv.func_70299_a(i, null);
                    }
                }
                return retStack;
            }
            if (!(item instanceof IBlockProvider)) continue;
            providers.add(invStack);
        }
        for (ItemStack provStack : providers) {
            IBlockProvider prov = (IBlockProvider)provStack.func_77973_b();
            if (!prov.provideBlock(player, stack, provStack, block, meta, doit)) continue;
            return new ItemStack(block, 1, meta);
        }
        return null;
    }

    public static ItemStack removeFromInventory(EntityPlayer player, ItemStack stack, Block block, int meta, boolean doit) {
        if (player.field_71075_bZ.field_75098_d) {
            return new ItemStack(block, 1, meta);
        }
        ItemStack outStack = ItemExchangeRod.removeFromInventory(player, BotaniaAPI.internalHandler.getBaublesInventory(player), stack, block, meta, doit);
        if (outStack == null) {
            outStack = ItemExchangeRod.removeFromInventory(player, (IInventory)player.field_71071_by, stack, block, meta, doit);
        }
        return outStack;
    }

    public static int getInventoryItemCount(EntityPlayer player, ItemStack stack, Block block, int meta) {
        if (player.field_71075_bZ.field_75098_d) {
            return -1;
        }
        int baubleCount = ItemExchangeRod.getInventoryItemCount(player, BotaniaAPI.internalHandler.getBaublesInventory(player), stack, block, meta);
        if (baubleCount == -1) {
            return -1;
        }
        int count = ItemExchangeRod.getInventoryItemCount(player, (IInventory)player.field_71071_by, stack, block, meta);
        if (count == -1) {
            return -1;
        }
        return count + baubleCount;
    }

    public static int getInventoryItemCount(EntityPlayer player, IInventory inv, ItemStack stack, Block block, int meta) {
        if (player.field_71075_bZ.field_75098_d) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack invStack = inv.func_70301_a(i);
            if (invStack == null) continue;
            Item item = invStack.func_77973_b();
            if (item == Item.func_150898_a((Block)block) && invStack.func_77960_j() == meta) {
                count += invStack.field_77994_a;
            }
            if (!(item instanceof IBlockProvider)) continue;
            IBlockProvider prov = (IBlockProvider)item;
            int provCount = prov.getBlockCount(player, stack, invStack, block, meta);
            if (provCount == -1) {
                return -1;
            }
            count += provCount;
        }
        return count;
    }

    public void displayRemainderCounter(EntityPlayer player, ItemStack stack) {
        Block block = ItemExchangeRod.getBlock(stack);
        int meta = ItemExchangeRod.getBlockMeta(stack);
        int count = ItemExchangeRod.getInventoryItemCount(player, stack, block, meta);
        if (!player.field_70170_p.field_72995_K) {
            ItemsRemainingRenderHandler.set(new ItemStack(block, 1, meta), count);
        }
    }

    public boolean func_77662_d() {
        return true;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    private boolean setBlock(ItemStack stack, Block block, int meta) {
        ItemNBTHelper.setString(stack, TAG_BLOCK_NAME, Block.field_149771_c.func_148750_c((Object)block));
        ItemNBTHelper.setInt(stack, TAG_BLOCK_META, meta);
        return true;
    }

    public String func_77653_i(ItemStack par1ItemStack) {
        Block block = ItemExchangeRod.getBlock(par1ItemStack);
        int meta = ItemExchangeRod.getBlockMeta(par1ItemStack);
        return super.func_77653_i(par1ItemStack) + (block == null ? "" : " (" + EnumChatFormatting.GREEN + new ItemStack(block, 1, meta).func_82833_r() + EnumChatFormatting.RESET + ")");
    }

    public static String getBlockName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_BLOCK_NAME, "");
    }

    public static Block getBlock(ItemStack stack) {
        Block block = Block.func_149684_b((String)ItemExchangeRod.getBlockName(stack));
        return block;
    }

    public static int getBlockMeta(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_BLOCK_META, 0);
    }

    private boolean setTargetBlock(ItemStack stack, Block block, int meta) {
        ItemNBTHelper.setString(stack, TAG_TARGET_BLOCK_NAME, Block.field_149771_c.func_148750_c((Object)block));
        ItemNBTHelper.setInt(stack, TAG_TARGET_BLOCK_META, meta);
        return true;
    }

    public static String getTargetBlockName(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_TARGET_BLOCK_NAME, "");
    }

    public static Block getTargetBlock(ItemStack stack) {
        Block block = Block.func_149684_b((String)ItemExchangeRod.getTargetBlockName(stack));
        return block;
    }

    public static int getTargetBlockMeta(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_TARGET_BLOCK_META, 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public List<ChunkCoordinates> getWireframesToDraw(EntityPlayer player, ItemStack stack) {
        ItemStack holding = player.func_71045_bC();
        if (holding != stack || !this.canExchange(stack)) {
            return null;
        }
        Block block = ItemExchangeRod.getBlock(stack);
        int meta = ItemExchangeRod.getBlockMeta(stack);
        MovingObjectPosition pos = Minecraft.func_71410_x().field_71476_x;
        if (pos != null) {
            int x = pos.field_72311_b;
            int y = pos.field_72312_c;
            int z = pos.field_72309_d;
            Block targetBlock = null;
            int targetMeta = 0;
            if (ItemNBTHelper.getBoolean(stack, TAG_SWAPPING, false)) {
                x = ItemNBTHelper.getInt(stack, TAG_SELECT_X, 0);
                y = ItemNBTHelper.getInt(stack, TAG_SELECT_Y, 0);
                z = ItemNBTHelper.getInt(stack, TAG_SELECT_Z, 0);
                targetBlock = ItemExchangeRod.getTargetBlock(stack);
                targetMeta = ItemExchangeRod.getTargetBlockMeta(stack);
            }
            if (!player.field_70170_p.func_147437_c(x, y, z)) {
                List<ChunkCoordinates> coordsList = this.getBlocksToSwap(player.field_70170_p, stack, block, meta, x, y, z, targetBlock, targetMeta);
                for (ChunkCoordinates coords : coordsList) {
                    if (coords.field_71574_a != x || coords.field_71572_b != y || coords.field_71573_c != z) continue;
                    coordsList.remove(coords);
                    break;
                }
                return coordsList;
            }
        }
        return null;
    }
}

