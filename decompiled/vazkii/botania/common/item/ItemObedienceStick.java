/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ItemTwigWand;

public class ItemObedienceStick
extends ItemMod {
    public ItemObedienceStick() {
        this.func_77625_d(1);
        this.func_77655_b("obedienceStick");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int s, float xs, float ys, float zs) {
        TileEntity tileAt = world.func_147438_o(x, y, z);
        if (tileAt != null && (tileAt instanceof IManaPool || tileAt instanceof IManaCollector)) {
            boolean pool = tileAt instanceof IManaPool;
            Actuator act = pool ? Actuator.functionalActuator : Actuator.generatingActuator;
            int range = pool ? 10 : 6;
            for (int i = -range; i < range + 1; ++i) {
                for (int j = -range; j < range + 1; ++j) {
                    for (int k = -range; k < range + 1; ++k) {
                        SubTileEntity subtile;
                        TileEntity tile;
                        int xp = x + i;
                        int yp = y + j;
                        int zp = z + k;
                        if (MathHelper.pointDistanceSpace(xp, yp, zp, x, y, z) > (float)range || !((tile = world.func_147438_o(xp, yp, zp)) instanceof ISubTileContainer) || !act.actuate(subtile = ((ISubTileContainer)tile).getSubTile(), tileAt)) continue;
                        Vector3 orig = new Vector3((double)xp + 0.5, (double)yp + 0.5, (double)zp + 0.5);
                        Vector3 end = new Vector3((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
                        ItemTwigWand.doParticleBeam(world, orig, end);
                    }
                }
            }
            if (player.field_70170_p.field_72995_K) {
                player.func_71038_i();
            }
        }
        return false;
    }

    public static abstract class Actuator {
        public static final Actuator generatingActuator = new Actuator(){

            @Override
            public boolean actuate(SubTileEntity flower, TileEntity tile) {
                if (flower instanceof SubTileGenerating) {
                    ((SubTileGenerating)flower).linkToForcefully(tile);
                    return true;
                }
                return false;
            }
        };
        public static final Actuator functionalActuator = new Actuator(){

            @Override
            public boolean actuate(SubTileEntity flower, TileEntity tile) {
                if (flower instanceof SubTileFunctional) {
                    ((SubTileFunctional)flower).linkToForcefully(tile);
                    return true;
                }
                return false;
            }
        };

        public abstract boolean actuate(SubTileEntity var1, TileEntity var2);
    }
}

