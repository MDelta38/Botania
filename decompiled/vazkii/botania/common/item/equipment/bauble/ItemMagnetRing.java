/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.functional.SubTileSolegnolia;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemMagnetRing
extends ItemBauble {
    IIcon iconOff;
    private static final String TAG_COOLDOWN = "cooldown";
    private static final List<String> BLACKLIST = Arrays.asList("appliedenergistics2:item.ItemCrystalSeed");
    int range;

    public ItemMagnetRing() {
        this("magnetRing", 6);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public ItemMagnetRing(String name, int range) {
        super(name);
        this.range = range;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this, 0);
        this.iconOff = IconHelper.forItem(par1IconRegister, (Item)this, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77650_f(ItemStack stack) {
        return ItemMagnetRing.getCooldown(stack) <= 0 ? this.field_77791_bV : this.iconOff;
    }

    @SubscribeEvent
    public void onTossItem(ItemTossEvent event) {
        InventoryBaubles inv = PlayerHandler.getPlayerBaubles((EntityPlayer)event.player);
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemMagnetRing)) continue;
            ItemMagnetRing.setCooldown(stack, 100);
            BotaniaAPI.internalHandler.sendBaubleUpdatePacket(event.player, i);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        int cooldown = ItemMagnetRing.getCooldown(stack);
        if (SubTileSolegnolia.hasSolegnoliaAround((Entity)player)) {
            if (cooldown < 0) {
                ItemMagnetRing.setCooldown(stack, 2);
            }
            return;
        }
        if (cooldown <= 0) {
            if (player.func_70093_af() == ConfigHandler.invertMagnetRing) {
                double x = player.field_70165_t;
                double y = player.field_70163_u - (player.field_70170_p.field_72995_K ? 1.62 : 0.0) + 0.75;
                double z = player.field_70161_v;
                List items = player.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(x - (double)this.range), (double)(y - (double)this.range), (double)(z - (double)this.range), (double)(x + (double)this.range), (double)(y + (double)this.range), (double)(z + (double)this.range)));
                int pulled = 0;
                for (EntityItem item : items) {
                    if (!this.canPullItem(item)) continue;
                    if (pulled <= 200) {
                        vazkii.botania.common.core.helper.MathHelper.setEntityMotionFromVector((Entity)item, new Vector3(x, y, z), 0.45f);
                        if (player.field_70170_p.field_72995_K) {
                            boolean red = player.field_70170_p.field_73012_v.nextBoolean();
                            Botania.proxy.sparkleFX(player.field_70170_p, item.field_70165_t, item.field_70163_u, item.field_70161_v, red ? 1.0f : 0.0f, 0.0f, red ? 0.0f : 1.0f, 1.0f, 3);
                        }
                        ++pulled;
                        continue;
                    }
                    break;
                }
            }
        } else {
            ItemMagnetRing.setCooldown(stack, cooldown - 1);
        }
    }

    private boolean canPullItem(EntityItem item) {
        int meta;
        int z;
        int y;
        if (item.field_70128_L || SubTileSolegnolia.hasSolegnoliaAround((Entity)item)) {
            return false;
        }
        ItemStack stack = item.func_92059_d();
        if (stack == null || stack.func_77973_b() instanceof IManaItem || stack.func_77973_b() instanceof IRelic || BLACKLIST.contains(field_150901_e.func_148750_c((Object)stack.func_77973_b())) || BotaniaAPI.isItemBlacklistedFromMagnet(stack)) {
            return false;
        }
        int x = MathHelper.func_76128_c((double)item.field_70165_t);
        Block block = item.field_70170_p.func_147439_a(x, y = (int)Math.floor(item.field_70163_u), z = MathHelper.func_76128_c((double)item.field_70161_v));
        if (BotaniaAPI.isBlockBlacklistedFromMagnet(block, meta = item.field_70170_p.func_72805_g(x, y, z))) {
            return false;
        }
        block = item.field_70170_p.func_147439_a(x, y - 1, z);
        return !BotaniaAPI.isBlockBlacklistedFromMagnet(block, meta = item.field_70170_p.func_72805_g(x, y - 1, z));
    }

    public static int getCooldown(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
    }

    public static void setCooldown(ItemStack stack, int cooldown) {
        ItemNBTHelper.setInt(stack, TAG_COOLDOWN, cooldown);
    }

    public static void addItemToBlackList(String item) {
        BLACKLIST.add(item);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}

