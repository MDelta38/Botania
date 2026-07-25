/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.client.Minecraft
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.crafting.IInfusionStabiliser
 *  thaumcraft.common.blocks.BlockCandle
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityFloatyCandle;
import java.util.Random;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.common.blocks.BlockCandle;

public class BlockFloatyCandle
extends BlockCandle
implements ITileEntityProvider,
IInfusionStabiliser {
    public BlockFloatyCandle(int i) {
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return true;
    }

    public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.375f, 0.0f, 0.375f, 0.625f, 1.0f, 0.625f);
    }

    public int func_149645_b() {
        return ThaumicExploration.floatCandleRenderID;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        double var7 = (float)par2 + 0.5f;
        double var9 = (float)par3 + 0.7f + 0.25f;
        double var11 = (float)par4 + 0.5f;
        int ticks = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
        int offset = par2 + par3 + par4;
        float move = 0.2f * MathHelper.func_76126_a((float)((float)(offset * 10 + ticks) / 30.0f));
        par1World.func_72869_a("smoke", var7, var9 + (double)move, var11, 0.0, 0.0, 0.0);
        par1World.func_72869_a("flame", var7, var9 + (double)move, var11, 0.0, 0.0, 0.0);
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityFloatyCandle();
    }
}

