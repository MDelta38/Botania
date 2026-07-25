/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySnowman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PotionSnowTrail
extends PotionBase {
    public PotionSnowTrail(int id, int color) {
        super(id, color);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return duration % 10 == 0;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (!entity.field_70170_p.field_72995_K) {
            for (int l = 0; l < 4; ++l) {
                float temp;
                int k;
                int j;
                int i = MathHelper.func_76128_c((double)(entity.field_70165_t + (double)((float)(l % 2 * 2 - 1) * 0.25f)));
                if (entity.field_70170_p.func_147439_a(i, j = MathHelper.func_76128_c((double)entity.field_70163_u), k = MathHelper.func_76128_c((double)(entity.field_70161_v + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25f)))).func_149688_o() != Material.field_151579_a || !((temp = entity.field_70170_p.func_72807_a(i, k).func_150564_a(i, j, k)) < 1.6f) || !Blocks.field_150431_aC.func_149742_c(entity.field_70170_p, i, j, k)) continue;
                entity.field_70170_p.func_147449_b(i, j, k, Blocks.field_150431_aC);
            }
            if (entity instanceof EntitySnowman && entity.field_70170_p.field_73012_v.nextInt(20) == 0) {
                entity.field_70170_p.func_72876_a((Entity)entity, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 3.0f, false);
                Coord coord = new Coord((Entity)entity);
                PotionSnowTrail.createSnowCovering(entity.field_70170_p, coord.x, coord.y, coord.z, 8, null);
                entity.func_70106_y();
            }
        }
    }

    public static void createSnowCovering(World world, int x, int y, int z, int radius, EntityPlayer source) {
        if (BlockProtect.checkModsForBreakOK(world, x, y, z, (EntityLivingBase)source)) {
            new BlockActionCircle(){

                @Override
                public void onBlock(World world, int x, int y, int z) {
                    int maxSearch = 8;
                    if (world.func_147437_c(x, y, z)) {
                        for (int i = 1; i < 8; ++i) {
                            int dy = y - i;
                            Block block = world.func_147439_a(x, dy, z);
                            if (block.func_149688_o() == Material.field_151579_a) continue;
                            this.setBlockToSnow(world, x, dy + 1, z, block);
                            break;
                        }
                    } else {
                        for (int i = 1; i < 8; ++i) {
                            int dy = y + i;
                            Block block = world.func_147439_a(x, dy, z);
                            if (block.func_149688_o() != Material.field_151579_a) continue;
                            Block blockBelow = world.func_147439_a(x, dy - 1, z);
                            this.setBlockToSnow(world, x, dy, z, blockBelow);
                            break;
                        }
                    }
                }

                private void setBlockToSnow(World world, int x, int y, int z, Block blockBelow) {
                    if (blockBelow.func_149662_c() || blockBelow.func_149688_o() == Material.field_151584_j) {
                        world.func_147449_b(x, y, z, Blocks.field_150431_aC);
                    }
                }
            }.processFilledCircle(world, x, y, z, radius);
        }
    }
}

