/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockEnchantmentTable
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import java.util.Random;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.world.World;

@Effect(aspect="cognitio")
public class Cognitio
extends AspectEffect {
    @OverrideBlock
    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        return 1.0f;
    }

    @SideOnly(value=Side.CLIENT)
    @OverrideBlock(overrideBlockFunc=false)
    public void func_149734_b(World world, int x, int y, int z, Random random) {
        int l = x - 2;
        while ((double)l <= this.field_149756_F + 2.0) {
            block1: for (int i1 = z - 2; i1 <= z + 2; ++i1) {
                if (l > x - 2 && l < x + 2 && i1 == z - 1) {
                    i1 = z + 2;
                }
                if (random.nextInt(16) != 0) continue;
                for (int j1 = y; j1 <= y + 1; ++j1) {
                    if (!(world.func_147439_a(l, j1, i1) instanceof BlockEnchantmentTable)) continue;
                    if (!world.func_147437_c((l - x) / 2 + x, j1, (i1 - z) / 2 + z)) continue block1;
                    world.func_72869_a("enchantmenttable", (double)((float)(l - x) + random.nextFloat()) - 0.5, (double)((float)(j1 - y) - random.nextFloat() - 1.0f), (double)((float)(i1 - z) + random.nextFloat()) - 0.5, (double)x + 0.5, (double)y + 2.0, (double)z + 0.5);
                }
            }
            ++l;
        }
    }

    @Override
    public int getCost() {
        return 1;
    }
}

