/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

@Effect(aspect="spiritus")
public class Spiritus
extends AspectEffect {
    @Override
    public int getCost() {
        return 4;
    }

    @OverrideBlock
    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
    }

    @OverrideBlock
    public boolean isNormalCube(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @OverrideBlock
    public boolean func_149747_d(IBlockAccess access, int x, int y, int z, int meta) {
        return false;
    }
}

