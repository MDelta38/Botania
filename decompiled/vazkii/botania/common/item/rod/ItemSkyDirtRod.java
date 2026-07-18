/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.rod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.rod.ItemDirtRod;

public class ItemSkyDirtRod
extends ItemDirtRod {
    public ItemSkyDirtRod() {
        super("skyDirtRod");
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K && ManaItemHandler.requestManaExactForTool(stack, player, 150, false)) {
            int z;
            int y;
            Vector3 playerVec = Vector3.fromEntityCenter((Entity)player);
            Vector3 lookVec = new Vector3(player.func_70040_Z()).multiply(3.0);
            Vector3 placeVec = playerVec.copy().add(lookVec);
            int x = MathHelper.func_76128_c((double)placeVec.x);
            int entities = world.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)x, (double)(y = MathHelper.func_76128_c((double)placeVec.y) + 1), (double)(z = MathHelper.func_76128_c((double)placeVec.z)), (double)(x + 1), (double)(y + 1), (double)(z + 1))).size();
            if (entities == 0) {
                ItemStack stackToPlace = new ItemStack(Blocks.field_150346_d);
                stackToPlace.func_77943_a(player, world, x, y, z, 0, 0.0f, 0.0f, 0.0f);
                if (stackToPlace.field_77994_a == 0) {
                    ManaItemHandler.requestManaExactForTool(stack, player, 150, true);
                    for (int i = 0; i < 6; ++i) {
                        Botania.proxy.sparkleFX(world, (double)x + Math.random(), (double)y + Math.random(), (double)z + Math.random(), 0.35f, 0.2f, 0.05f, 1.0f, 5);
                    }
                }
            }
        }
        if (world.field_72995_K) {
            player.func_71038_i();
        }
        return stack;
    }
}

