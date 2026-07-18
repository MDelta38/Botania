/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemWorldSeed
extends ItemMod {
    public ItemWorldSeed() {
        this.func_77655_b("worldSeed");
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        ChunkCoordinates coords = world.func_72861_E();
        if (MathHelper.pointDistanceSpace((double)coords.field_71574_a + 0.5, (double)coords.field_71572_b + 0.5, (double)coords.field_71573_c + 0.5, player.field_70165_t, player.field_70163_u, player.field_70161_v) > 24.0f) {
            player.field_70125_A = 0.0f;
            player.field_70177_z = 0.0f;
            player.func_70634_a((double)coords.field_71574_a + 0.5, (double)coords.field_71572_b + 1.6, (double)coords.field_71573_c + 0.5);
            while (!world.func_72945_a((Entity)player, player.field_70121_D).isEmpty()) {
                player.func_70634_a(player.field_70165_t, player.field_70163_u + 1.0, player.field_70161_v);
            }
            world.func_72956_a((Entity)player, "mob.endermen.portal", 1.0f, 1.0f);
            for (int i = 0; i < 50; ++i) {
                Botania.proxy.sparkleFX(world, player.field_70165_t + Math.random() * (double)player.field_70130_N, player.field_70163_u - 1.6 + Math.random() * (double)player.field_70131_O, player.field_70161_v + Math.random() * (double)player.field_70130_N, 0.25f, 1.0f, 0.25f, 1.0f, 10);
            }
            --stack.field_77994_a;
        }
        return stack;
    }
}

