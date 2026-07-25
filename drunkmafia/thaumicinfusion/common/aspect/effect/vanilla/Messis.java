/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

@Effect(aspect="messis")
public class Messis
extends AspectEffect {
    @Override
    public int getCost() {
        return 1;
    }

    @OverrideBlock
    public boolean canSustainPlant(IBlockAccess access, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return true;
    }

    @OverrideBlock
    public boolean isFertile(World world, int x, int y, int z) {
        return true;
    }
}

