/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.lens;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

public class LensPaint
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (!burst.isFake() && storedColor > -1 && storedColor < 17) {
            if (pos.field_72308_g != null && pos.field_72308_g instanceof EntitySheep) {
                int r = 20;
                int sheepColor = ((EntitySheep)pos.field_72308_g).func_70896_n();
                List sheepList = entity.field_70170_p.func_72872_a(EntitySheep.class, AxisAlignedBB.func_72330_a((double)(pos.field_72308_g.field_70165_t - (double)r), (double)(pos.field_72308_g.field_70163_u - (double)r), (double)(pos.field_72308_g.field_70161_v - (double)r), (double)(pos.field_72308_g.field_70165_t + (double)r), (double)(pos.field_72308_g.field_70163_u + (double)r), (double)(pos.field_72308_g.field_70161_v + (double)r)));
                for (EntitySheep sheep : sheepList) {
                    if (sheep.func_70896_n() != sheepColor) continue;
                    sheep.func_70891_b(storedColor == 16 ? sheep.field_70170_p.field_73012_v.nextInt(16) : storedColor);
                }
                dead = true;
            } else {
                Block block = entity.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                if (BotaniaAPI.paintableBlocks.contains(block)) {
                    int meta = entity.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                    ArrayList<ChunkCoordinates> coordsToPaint = new ArrayList<ChunkCoordinates>();
                    ArrayList<ChunkCoordinates> coordsFound = new ArrayList<ChunkCoordinates>();
                    ChunkCoordinates theseCoords = new ChunkCoordinates(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                    coordsFound.add(theseCoords);
                    do {
                        ArrayList iterCoords = new ArrayList(coordsFound);
                        for (ChunkCoordinates coords : iterCoords) {
                            coordsFound.remove(coords);
                            coordsToPaint.add(coords);
                            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                                Block block_ = entity.field_70170_p.func_147439_a(coords.field_71574_a + dir.offsetX, coords.field_71572_b + dir.offsetY, coords.field_71573_c + dir.offsetZ);
                                int meta_ = entity.field_70170_p.func_72805_g(coords.field_71574_a + dir.offsetX, coords.field_71572_b + dir.offsetY, coords.field_71573_c + dir.offsetZ);
                                ChunkCoordinates coords_ = new ChunkCoordinates(coords.field_71574_a + dir.offsetX, coords.field_71572_b + dir.offsetY, coords.field_71573_c + dir.offsetZ);
                                if (block_ != block || meta_ != meta || coordsFound.contains(coords_) || coordsToPaint.contains(coords_)) continue;
                                coordsFound.add(coords_);
                            }
                        }
                    } while (!coordsFound.isEmpty() && coordsToPaint.size() < 1000);
                    for (ChunkCoordinates coords : coordsToPaint) {
                        int placeColor;
                        int n = placeColor = storedColor == 16 ? entity.field_70170_p.field_73012_v.nextInt(16) : storedColor;
                        int metaThere = entity.field_70170_p.func_72805_g(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
                        if (metaThere == placeColor) continue;
                        if (!entity.field_70170_p.field_72995_K) {
                            entity.field_70170_p.func_72921_c(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, placeColor, 2);
                        }
                        float[] color = EntitySheep.field_70898_d[placeColor];
                        float r = color[0];
                        float g = color[1];
                        float b = color[2];
                        for (int i = 0; i < 4; ++i) {
                            Botania.proxy.sparkleFX(entity.field_70170_p, (float)coords.field_71574_a + (float)Math.random(), (float)coords.field_71572_b + (float)Math.random(), (float)coords.field_71573_c + (float)Math.random(), r, g, b, 0.6f + (float)Math.random() * 0.3f, 5);
                        }
                    }
                }
            }
        }
        return dead;
    }
}

