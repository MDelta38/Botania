/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.IBlockAccess
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.world.IBlockAccess;

@Effect(aspect="potentia")
public class Potentia
extends AspectEffect {
    @Override
    public int getCost() {
        return 1;
    }

    @OverrideBlock
    public int func_149709_b(IBlockAccess access, int x, int y, int z, int side) {
        return 15;
    }

    public int func_149748_c(IBlockAccess access, int x, int y, int z, int side) {
        return this.func_149709_b(access, x, y, z, side);
    }

    @OverrideBlock
    public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    @OverrideBlock
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }
}

