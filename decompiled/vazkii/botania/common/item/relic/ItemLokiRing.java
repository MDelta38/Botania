/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  baubles.common.network.PacketHandler
 *  baubles.common.network.PacketSyncBauble
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package vazkii.botania.common.item.relic;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import baubles.common.network.PacketHandler;
import baubles.common.network.PacketSyncBauble;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.api.item.IExtendedWireframeCoordinateListProvider;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemLokiRing
extends ItemRelicBauble
implements IExtendedWireframeCoordinateListProvider,
IManaUsingItem {
    private static final String TAG_CURSOR_LIST = "cursorList";
    private static final String TAG_CURSOR_PREFIX = "cursor";
    private static final String TAG_CURSOR_COUNT = "cursorCount";
    private static final String TAG_X_OFFSET = "xOffset";
    private static final String TAG_Y_OFFSET = "yOffset";
    private static final String TAG_Z_OFFSET = "zOffset";
    private static final String TAG_X_ORIGIN = "xOrigin";
    private static final String TAG_Y_ORIGIN = "yOrigin";
    private static final String TAG_Z_ORIGIN = "zOrigin";

    public ItemLokiRing() {
        super("lokiRing");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        block16: {
            EntityPlayer player = event.entityPlayer;
            ItemStack lokiRing = ItemLokiRing.getLokiRing(player);
            if (lokiRing == null || player.field_70170_p.field_72995_K) {
                return;
            }
            int slot = -1;
            InventoryBaubles inv = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
            for (int i = 0; i < inv.func_70302_i_(); ++i) {
                ItemStack stack = inv.func_70301_a(i);
                if (stack != lokiRing) continue;
                slot = i;
                break;
            }
            ItemStack heldItemStack = player.func_71045_bC();
            ChunkCoordinates originCoords = ItemLokiRing.getOriginPos(lokiRing);
            MovingObjectPosition lookPos = ToolCommons.raytraceFromEntity(player.field_70170_p, (Entity)player, true, 10.0);
            List<ChunkCoordinates> cursors = ItemLokiRing.getCursorList(lokiRing);
            int cursorCount = cursors.size();
            int cost = Math.min(cursorCount, (int)Math.pow(Math.E, (double)cursorCount * 0.25));
            if (heldItemStack == null && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && player.func_70093_af()) {
                if (originCoords.field_71572_b == -1 && lookPos != null) {
                    ItemLokiRing.setOriginPos(lokiRing, lookPos.field_72311_b, lookPos.field_72312_c, lookPos.field_72309_d);
                    ItemLokiRing.setCursorList(lokiRing, null);
                    if (player instanceof EntityPlayerMP) {
                        PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncBauble(player, slot), (EntityPlayerMP)player);
                    }
                } else if (lookPos != null) {
                    if (originCoords.field_71574_a == lookPos.field_72311_b && originCoords.field_71572_b == lookPos.field_72312_c && originCoords.field_71573_c == lookPos.field_72309_d) {
                        ItemLokiRing.setOriginPos(lokiRing, 0, -1, 0);
                        if (player instanceof EntityPlayerMP) {
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncBauble(player, slot), (EntityPlayerMP)player);
                        }
                    } else {
                        int relX = lookPos.field_72311_b - originCoords.field_71574_a;
                        int relY = lookPos.field_72312_c - originCoords.field_71572_b;
                        int relZ = lookPos.field_72309_d - originCoords.field_71573_c;
                        for (ChunkCoordinates cursor : cursors) {
                            if (cursor.field_71574_a != relX || cursor.field_71572_b != relY || cursor.field_71573_c != relZ) continue;
                            cursors.remove(cursor);
                            ItemLokiRing.setCursorList(lokiRing, cursors);
                            if (player instanceof EntityPlayerMP) {
                                PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncBauble(player, slot), (EntityPlayerMP)player);
                            }
                            break block16;
                        }
                        ItemLokiRing.addCursor(lokiRing, relX, relY, relZ);
                        if (player instanceof EntityPlayerMP) {
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncBauble(player, slot), (EntityPlayerMP)player);
                        }
                    }
                }
            } else if (heldItemStack != null && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && lookPos != null && player.func_70093_af()) {
                for (ChunkCoordinates cursor : cursors) {
                    int x = lookPos.field_72311_b + cursor.field_71574_a;
                    int y = lookPos.field_72312_c + cursor.field_71572_b;
                    int z = lookPos.field_72309_d + cursor.field_71573_c;
                    Item item = heldItemStack.func_77973_b();
                    if (player.field_70170_p.func_147437_c(x, y, z) || !ManaItemHandler.requestManaExact(lokiRing, player, cost, true)) continue;
                    item.func_77648_a(player.field_71075_bZ.field_75098_d ? heldItemStack.func_77946_l() : heldItemStack, player, player.field_70170_p, x, y, z, lookPos.field_72310_e, (float)lookPos.field_72307_f.field_72450_a - (float)x, (float)lookPos.field_72307_f.field_72448_b - (float)y, (float)lookPos.field_72307_f.field_72449_c - (float)z);
                    if (heldItemStack.field_77994_a != 0) continue;
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }

    public static void breakOnAllCursors(EntityPlayer player, Item item, ItemStack stack, int x, int y, int z, int side) {
        ItemStack lokiRing = ItemLokiRing.getLokiRing(player);
        if (lokiRing == null || player.field_70170_p.field_72995_K || !(item instanceof ISequentialBreaker)) {
            return;
        }
        List<ChunkCoordinates> cursors = ItemLokiRing.getCursorList(lokiRing);
        ISequentialBreaker breaker = (ISequentialBreaker)item;
        World world = player.field_70170_p;
        boolean silk = EnchantmentHelper.func_77506_a((int)Enchantment.field_77348_q.field_77352_x, (ItemStack)stack) > 0;
        int fortune = EnchantmentHelper.func_77506_a((int)Enchantment.field_77346_s.field_77352_x, (ItemStack)stack);
        boolean dispose = breaker.disposeOfTrashBlocks(stack);
        for (int i = 0; i < cursors.size(); ++i) {
            ChunkCoordinates coords = cursors.get(i);
            int xp = x + coords.field_71574_a;
            int yp = y + coords.field_71572_b;
            int zp = z + coords.field_71573_c;
            Block block = world.func_147439_a(xp, yp, zp);
            breaker.breakOtherBlock(player, stack, xp, yp, zp, x, y, z, side);
            ToolCommons.removeBlockWithDrops(player, stack, player.field_70170_p, xp, yp, zp, x, y, z, block, new Material[]{block.func_149688_o()}, silk, fortune, block.func_149712_f(world, xp, yp, zp), dispose);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        ItemLokiRing.setCursorList(stack, null);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public List<ChunkCoordinates> getWireframesToDraw(EntityPlayer player, ItemStack stack) {
        if (ItemLokiRing.getLokiRing(player) != stack) {
            return null;
        }
        MovingObjectPosition lookPos = Minecraft.func_71410_x().field_71476_x;
        if (lookPos != null && !player.field_70170_p.func_147437_c(lookPos.field_72311_b, lookPos.field_72312_c, lookPos.field_72309_d) && lookPos.field_72308_g == null) {
            List<ChunkCoordinates> list = ItemLokiRing.getCursorList(stack);
            ChunkCoordinates origin = ItemLokiRing.getOriginPos(stack);
            if (origin.field_71572_b != -1) {
                for (ChunkCoordinates coords : list) {
                    coords.field_71574_a += origin.field_71574_a;
                    coords.field_71572_b += origin.field_71572_b;
                    coords.field_71573_c += origin.field_71573_c;
                }
            } else {
                for (ChunkCoordinates coords : list) {
                    coords.field_71574_a += lookPos.field_72311_b;
                    coords.field_71572_b += lookPos.field_72312_c;
                    coords.field_71573_c += lookPos.field_72309_d;
                }
            }
            return list;
        }
        return null;
    }

    @Override
    public ChunkCoordinates getSourceWireframe(EntityPlayer player, ItemStack stack) {
        return ItemLokiRing.getLokiRing(player) == stack ? ItemLokiRing.getOriginPos(stack) : null;
    }

    private static ItemStack getLokiRing(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
        ItemStack stack1 = baubles.func_70301_a(1);
        ItemStack stack2 = baubles.func_70301_a(2);
        return ItemLokiRing.isLokiRing(stack1) ? stack1 : (ItemLokiRing.isLokiRing(stack2) ? stack2 : null);
    }

    private static boolean isLokiRing(ItemStack stack) {
        return stack != null && (stack.func_77973_b() == ModItems.lokiRing || stack.func_77973_b() == ModItems.aesirRing);
    }

    private static ChunkCoordinates getOriginPos(ItemStack stack) {
        int x = ItemNBTHelper.getInt(stack, TAG_X_ORIGIN, 0);
        int y = ItemNBTHelper.getInt(stack, TAG_Y_ORIGIN, -1);
        int z = ItemNBTHelper.getInt(stack, TAG_Z_ORIGIN, 0);
        return new ChunkCoordinates(x, y, z);
    }

    private static void setOriginPos(ItemStack stack, int x, int y, int z) {
        ItemNBTHelper.setInt(stack, TAG_X_ORIGIN, x);
        ItemNBTHelper.setInt(stack, TAG_Y_ORIGIN, y);
        ItemNBTHelper.setInt(stack, TAG_Z_ORIGIN, z);
    }

    private static List<ChunkCoordinates> getCursorList(ItemStack stack) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_CURSOR_LIST, false);
        ArrayList<ChunkCoordinates> cursors = new ArrayList<ChunkCoordinates>();
        int count = cmp.func_74762_e(TAG_CURSOR_COUNT);
        for (int i = 0; i < count; ++i) {
            NBTTagCompound cursorCmp = cmp.func_74775_l(TAG_CURSOR_PREFIX + i);
            int x = cursorCmp.func_74762_e(TAG_X_OFFSET);
            int y = cursorCmp.func_74762_e(TAG_Y_OFFSET);
            int z = cursorCmp.func_74762_e(TAG_Z_OFFSET);
            cursors.add(new ChunkCoordinates(x, y, z));
        }
        return cursors;
    }

    private static void setCursorList(ItemStack stack, List<ChunkCoordinates> cursors) {
        NBTTagCompound cmp = new NBTTagCompound();
        if (cursors != null) {
            int i = 0;
            for (ChunkCoordinates cursor : cursors) {
                NBTTagCompound cursorCmp = ItemLokiRing.cursorToCmp(cursor.field_71574_a, cursor.field_71572_b, cursor.field_71573_c);
                cmp.func_74782_a(TAG_CURSOR_PREFIX + i, (NBTBase)cursorCmp);
                ++i;
            }
            cmp.func_74768_a(TAG_CURSOR_COUNT, i);
        }
        ItemNBTHelper.setCompound(stack, TAG_CURSOR_LIST, cmp);
    }

    private static NBTTagCompound cursorToCmp(int x, int y, int z) {
        NBTTagCompound cmp = new NBTTagCompound();
        cmp.func_74768_a(TAG_X_OFFSET, x);
        cmp.func_74768_a(TAG_Y_OFFSET, y);
        cmp.func_74768_a(TAG_Z_OFFSET, z);
        return cmp;
    }

    private static void addCursor(ItemStack stack, int x, int y, int z) {
        NBTTagCompound cmp = ItemNBTHelper.getCompound(stack, TAG_CURSOR_LIST, false);
        int count = cmp.func_74762_e(TAG_CURSOR_COUNT);
        cmp.func_74782_a(TAG_CURSOR_PREFIX + count, (NBTBase)ItemLokiRing.cursorToCmp(x, y, z));
        cmp.func_74768_a(TAG_CURSOR_COUNT, count + 1);
        ItemNBTHelper.setCompound(stack, TAG_CURSOR_LIST, cmp);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

