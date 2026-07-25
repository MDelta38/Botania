/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.aspect.effect.vanilla;

import drunkmafia.thaumicinfusion.common.aspect.effect.vanilla.AspectLink;
import drunkmafia.thaumicinfusion.common.util.annotation.Effect;
import drunkmafia.thaumicinfusion.common.util.annotation.OverrideBlock;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockData;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.WorldCoordinates;

@Effect(aspect="machina")
public class Machina
extends AspectLink {
    @Override
    public int getCost() {
        return 2;
    }

    @OverrideBlock
    public int func_149709_b(IBlockAccess access, int x, int y, int z, int side) {
        WorldServer destinationWorld;
        WorldCoordinates destin = this.getDestination();
        if (destin == null || (destinationWorld = DimensionManager.getWorld((int)destin.dim)) == null || destinationWorld.func_147437_c(destin.x, destin.y, destin.z)) {
            return 0;
        }
        TIWorldData worldData = TIWorldData.getWorldData((World)destinationWorld);
        boolean power = false;
        BlockData data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x - 1, destin.y, destin.z, destin.dim));
        if (data == null || !data.hasEffect(Machina.class)) {
            boolean bl = power = destinationWorld.func_72878_l(destin.x, destin.y - 1, destin.z, 0) > 0;
        }
        if (!(power || (data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x + 1, destin.y, destin.z, destin.dim))) != null && data.hasEffect(Machina.class))) {
            boolean bl = power = destinationWorld.func_72878_l(destin.x + 1, destin.y, destin.z, 0) > 0;
        }
        if (!(power || (data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x, destin.y - 1, destin.z, destin.dim))) != null && data.hasEffect(Machina.class))) {
            boolean bl = power = destinationWorld.func_72878_l(destin.x, destin.y - 1, destin.z, 0) > 0;
        }
        if (!(power || (data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x, destin.y + 1, destin.z, destin.dim))) != null && data.hasEffect(Machina.class))) {
            boolean bl = power = destinationWorld.func_72878_l(destin.x, destin.y + 1, destin.z, 0) > 0;
        }
        if (!(power || (data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x, destin.y, destin.z - 1, destin.dim))) != null && data.hasEffect(Machina.class))) {
            boolean bl = power = destinationWorld.func_72878_l(destin.x, destin.y, destin.z - 1, 0) > 0;
        }
        if (!(power || (data = worldData.getBlock(BlockData.class, new WorldCoordinates(destin.x, destin.y, destin.z + 1, destin.dim))) != null && data.hasEffect(Machina.class))) {
            power = destinationWorld.func_72878_l(destin.x, destin.y, destin.z + 1, 0) > 0;
        }
        return power ? 15 : 0;
    }

    @OverrideBlock
    public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    @OverrideBlock
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        return true;
    }

    @OverrideBlock
    public void func_149695_a(World world, int x, int y, int z, Block block) {
        WorldServer destinationWorld;
        WorldCoordinates destin = this.getDestination();
        if (destin == null || (destinationWorld = DimensionManager.getWorld((int)destin.dim)) == null || block.getClass() == this.getClass()) {
            return;
        }
        destinationWorld.func_147444_c(destin.x, destin.y, destin.z, (Block)this);
    }
}

