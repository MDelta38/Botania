/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="lux")
public class Lux
extends AspectEffect {
    @Override
    public void aspectInit(World world, WorldCoordinates pos) {
        super.aspectInit(world, pos);
        if (world.field_72995_K) {
            Minecraft.func_71410_x().field_71438_f.func_147586_a(pos.x, pos.y, pos.z);
        }
    }

    @Override
    public int getCost() {
        return 1;
    }

    @Override
    public boolean shouldDrain() {
        return false;
    }

    @OverrideBlock(overrideBlockFunc=false)
    public void func_149726_b(World world, int x, int y, int z) {
        if (world.field_72995_K) {
            Minecraft.func_71410_x().field_71438_f.func_147586_a(this.pos.x, this.pos.y, this.pos.z);
        }
    }

    @OverrideBlock
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 14;
    }
}

