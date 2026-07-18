/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.block.tile;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class TileEnderEye
extends TileMod {
    public void func_145845_h() {
        int newMeta;
        int meta = this.func_145832_p();
        int range = 80;
        List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - range), (double)(this.field_145848_d - range), (double)(this.field_145849_e - range), (double)(this.field_145851_c + range), (double)(this.field_145848_d + range), (double)(this.field_145849_e + range)));
        boolean looking = false;
        for (EntityPlayer player : players) {
            MovingObjectPosition pos;
            ItemStack helm = player.func_82169_q(3);
            if (helm != null && helm.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150423_aK) || (pos = ToolCommons.raytraceFromEntity(this.field_145850_b, (Entity)player, true, 64.0)) == null || pos.field_72311_b != this.field_145851_c || pos.field_72312_c != this.field_145848_d || pos.field_72309_d != this.field_145849_e) continue;
            looking = true;
            break;
        }
        int n = newMeta = looking ? 15 : 0;
        if (newMeta != meta && !this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, newMeta, 3);
        }
        if (looking) {
            double x = (double)this.field_145851_c - 0.1 + Math.random() * 1.2;
            double y = (double)this.field_145848_d - 0.1 + Math.random() * 1.2;
            double z = (double)this.field_145849_e - 0.1 + Math.random() * 1.2;
            this.field_145850_b.func_72869_a("reddust", x, y, z, 1.0, 0.0, 0.0);
        }
    }
}

