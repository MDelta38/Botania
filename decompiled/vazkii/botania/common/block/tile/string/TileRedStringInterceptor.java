/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile.string;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.block.tile.string.TileRedString;

public class TileRedStringInterceptor
extends TileRedString {
    public static List<TileRedStringInterceptor> interceptors = new ArrayList<TileRedStringInterceptor>();

    @Override
    public void func_145845_h() {
        super.func_145845_h();
        if (!interceptors.contains(this)) {
            interceptors.add(this);
        }
    }

    @Override
    public boolean acceptBlock(int x, int y, int z) {
        return this.field_145850_b.func_147438_o(x, y, z) != null;
    }

    public boolean removeFromList() {
        return !this.field_145846_f && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
    }

    public static void onInteract(EntityPlayer player, World world, int x, int y, int z) {
        ArrayList<TileRedStringInterceptor> remove = new ArrayList<TileRedStringInterceptor>();
        boolean did = false;
        ArrayList<TileRedStringInterceptor> interceptorsCopy = new ArrayList<TileRedStringInterceptor>(interceptors);
        for (TileRedStringInterceptor inter : interceptorsCopy) {
            ChunkCoordinates coords;
            if (!inter.removeFromList()) {
                remove.add(inter);
                continue;
            }
            if (inter.field_145850_b != world || (coords = inter.getBinding()) == null || coords.field_71574_a != x || coords.field_71572_b != y || coords.field_71573_c != z) continue;
            if (!world.field_72995_K) {
                Block block = inter.func_145838_q();
                int meta = inter.func_145832_p();
                world.func_72921_c(inter.field_145851_c, inter.field_145848_d, inter.field_145849_e, meta | 8, 3);
                world.func_147464_a(inter.field_145851_c, inter.field_145848_d, inter.field_145849_e, block, block.func_149738_a(world));
            }
            did = true;
        }
        interceptors.removeAll(remove);
        if (did) {
            if (world.field_72995_K) {
                player.func_71038_i();
            } else {
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "random.click", 0.3f, 0.6f);
            }
        }
    }
}

