/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 */
package flaxbeard.thaumicexploration.common;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommonProxy {
    public void registerRenderers() {
    }

    public void addRecipes() {
    }

    public void setUnicode() {
    }

    public void spawnWaterOnPlayer(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player) {
    }

    public void spawnRandomWaterFountain(World worldObj, int xCoord, int yCoord, int zCoord) {
    }

    public void spawnWaterAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
    }

    public void spawnBoreSparkle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2) {
    }

    public void spawnHarvestParticle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2) {
    }

    public void spawnActiveBrazierParticle(World worldObj, int xCoord, int yCoord, int zCoord) {
    }

    public boolean getIsReadyForWisp() {
        return true;
    }

    public void spawnFragmentParticle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2, Block block, int id) {
    }

    public void spawnEssentiaAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ, int size, int color) {
    }

    public void crucibleBubble(World world, float x, float y, float z, float cr, float cg, float cb) {
    }

    public void spawnLightningBolt(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
    }

    public void spawnHarvestParticleSlow(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2) {
    }

    public void spawnFragmentParticleSlow(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2, Block block, int id) {
    }

    public boolean isServer() {
        return true;
    }

    public World getOverworld() {
        return DimensionManager.getWorld((int)0);
    }
}

