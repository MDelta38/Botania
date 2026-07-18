/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityBeacon
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 */
package vazkii.botania.common.core.handler;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public final class TerrasteelCraftingHandler {
    private static final String TAG_TIME = "Botania-CraftingTime";
    private static final int TIME = 100;
    private static final int MANA_PER_TICK = 4500;

    public static void onEntityUpdate(EntityItem item) {
        int time;
        ItemStack stack = item.func_92059_d();
        if (stack != null && stack.func_77973_b() == ModItems.manaResource && stack.func_77960_j() == 0 && (time = TerrasteelCraftingHandler.validateCraftingItem(item)) != -1) {
            TerrasteelCraftingHandler.doParticles(item, time);
            if (time == 100) {
                item.field_70170_p.func_72956_a((Entity)item, "botania:terrasteelCraft", 1.0f, 1.0f);
            }
            int x = MathHelper.func_76128_c((double)item.field_70165_t);
            int y = MathHelper.func_76128_c((double)item.field_70163_u);
            int z = MathHelper.func_76128_c((double)item.field_70161_v);
            int range = 12;
            block0: for (int i = -range; i < range + 1; ++i) {
                for (int j = -range; j < range + 1; ++j) {
                    for (int k = -range; k < range + 1; ++k) {
                        TileEntity tile = item.field_70170_p.func_147438_o(x + i, y + j, z + k);
                        if (!(tile instanceof IManaPool)) continue;
                        IManaPool pool = (IManaPool)tile;
                        if (item.field_70170_p.field_72995_K || pool.getCurrentMana() < 4500) continue;
                        pool.recieveMana(-4500);
                        item.field_70170_p.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
                        TerrasteelCraftingHandler.incrementCraftingTime(item, time);
                        break block0;
                    }
                }
            }
        }
    }

    static int validateCraftingItem(EntityItem item) {
        int z;
        int y;
        ItemStack estack = item.func_92059_d();
        if (estack.field_77994_a != 1) {
            return -1;
        }
        int x = MathHelper.func_76128_c((double)item.field_70165_t);
        if (item.field_70170_p.func_147439_a(x, (y = MathHelper.func_76128_c((double)item.field_70163_u)) - 1, z = MathHelper.func_76128_c((double)item.field_70161_v)) != Blocks.field_150461_bJ) {
            return -1;
        }
        TileEntityBeacon beacon = (TileEntityBeacon)item.field_70170_p.func_147438_o(x, y - 1, z);
        if (beacon.func_145998_l() <= 0) {
            return -1;
        }
        List items = item.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)));
        EntityItem diamond = null;
        EntityItem pearl = null;
        for (EntityItem otherItem : items) {
            if (otherItem == item) continue;
            ItemStack stack = otherItem.func_92059_d();
            if (stack.func_77973_b() == ModItems.manaResource && stack.field_77994_a == 1) {
                int meta = stack.func_77960_j();
                if (meta == 1) {
                    if (pearl == null) {
                        pearl = otherItem;
                        continue;
                    }
                    return -1;
                }
                if (meta == 2) {
                    if (diamond == null) {
                        diamond = otherItem;
                        continue;
                    }
                    return -1;
                }
                return -1;
            }
            return -1;
        }
        if (diamond != null && pearl != null) {
            int time = TerrasteelCraftingHandler.getTimeInCrafting(item);
            if (time > 0) {
                diamond.field_145804_b = 1;
                diamond.field_70292_b = 0;
                pearl.field_145804_b = 1;
                pearl.field_70292_b = 0;
                item.field_145804_b = 1;
                item.field_70292_b = 0;
            }
            return time;
        }
        return -1;
    }

    static int getTimeInCrafting(EntityItem item) {
        ItemStack stack = item.func_92059_d();
        return ItemNBTHelper.getInt(stack, TAG_TIME, 0);
    }

    static void doParticles(EntityItem item, int ticks) {
        if (item.field_70170_p.field_72995_K) {
            int totalSpiritCount = 3;
            double tickIncrement = 360.0 / (double)totalSpiritCount;
            int speed = 5;
            double wticks = (double)(ticks * speed) - tickIncrement;
            double r = Math.sin((double)(ticks - 100) / 10.0) * 2.0;
            double g = Math.sin(wticks * Math.PI / 180.0 * 0.55);
            for (int i = 0; i < totalSpiritCount; ++i) {
                double x = (double)((int)item.field_70165_t) + Math.sin(wticks * Math.PI / 180.0) * r + 0.5;
                double y = (double)((int)item.field_70163_u) + 0.25;
                double z = (double)((int)item.field_70161_v) + Math.cos(wticks * Math.PI / 180.0) * r + 0.5;
                wticks += tickIncrement;
                float[] colorsfx = new float[]{0.0f, (float)ticks / 100.0f, 1.0f - (float)ticks / 100.0f};
                Botania.proxy.wispFX(item.field_70170_p, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], 0.85f, (float)g * 0.05f, 0.25f);
                Botania.proxy.wispFX(item.field_70170_p, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], (float)Math.random() * 0.1f + 0.1f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, 0.9f);
                if (ticks != 100) continue;
                for (int j = 0; j < 15; ++j) {
                    Botania.proxy.wispFX(item.field_70170_p, item.field_70165_t, item.field_70163_u, item.field_70161_v, colorsfx[0], colorsfx[1], colorsfx[2], (float)Math.random() * 0.15f + 0.15f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f);
                }
            }
        }
    }

    static void incrementCraftingTime(EntityItem item, int time) {
        if (time >= 100) {
            TerrasteelCraftingHandler.finalizeCraftingRecipe(item);
        } else {
            ItemStack stack = item.func_92059_d();
            ItemNBTHelper.setInt(stack, TAG_TIME, time + 1);
        }
    }

    static void finalizeCraftingRecipe(EntityItem item) {
        int x = MathHelper.func_76128_c((double)item.field_70165_t);
        int y = MathHelper.func_76128_c((double)item.field_70163_u);
        int z = MathHelper.func_76128_c((double)item.field_70161_v);
        List items = item.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1)));
        for (EntityItem otherItem : items) {
            if (otherItem != item) {
                otherItem.func_70106_y();
                continue;
            }
            item.func_92058_a(new ItemStack(ModItems.manaResource, 1, 4));
        }
    }
}

