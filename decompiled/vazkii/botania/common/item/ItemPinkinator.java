/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.entity.EntityPinkWither;
import vazkii.botania.common.item.ItemMod;

public class ItemPinkinator
extends ItemMod {
    public ItemPinkinator() {
        this.func_77655_b("pinkinator");
        this.func_77625_d(1);
        this.func_77664_n();
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        int range = 16;
        List withers = world.func_72872_a(EntityWither.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)range), (double)(player.field_70163_u - (double)range), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)range), (double)(player.field_70161_v + (double)range)));
        for (EntityWither wither : withers) {
            if (wither.field_70128_L || wither instanceof EntityPinkWither) continue;
            if (!world.field_72995_K) {
                wither.func_70106_y();
                EntityPinkWither pink = new EntityPinkWither(world);
                pink.func_70012_b(wither.field_70165_t, wither.field_70163_u, wither.field_70161_v, wither.field_70177_z, wither.field_70125_A);
                world.func_72838_d((Entity)pink);
                world.func_72956_a((Entity)wither, "random.explode", 4.0f, (1.0f + (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
            }
            player.func_71064_a((StatBase)ModAchievements.pinkinator, 1);
            world.func_72869_a("hugeexplosion", wither.field_70165_t, wither.field_70163_u, wither.field_70161_v, 1.0, 0.0, 0.0);
            --stack.field_77994_a;
            return stack;
        }
        return stack;
    }

    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        p_77624_3_.add(StatCollector.func_74838_a((String)"botaniamisc.pinkinatorDesc"));
    }
}

