/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 *  thaumcraft.common.Thaumcraft
 */
package flaxbeard.thaumicexploration.block;

import com.mojang.authlib.GameProfile;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import thaumcraft.common.Thaumcraft;

public class BlockSoulBrazier
extends BlockContainer {
    public BlockSoulBrazier() {
        super(Material.field_151576_e);
        this.func_149658_d("thaumicexploration:soulBrazier");
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        TileEntitySoulBrazier entity = (TileEntitySoulBrazier)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_);
        Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(entity.owner.getName(), entity.storedWarp);
        ForgeChunkManager.unforceChunk((ForgeChunkManager.Ticket)entity.heldChunk, (ChunkCoordIntPair)new ChunkCoordIntPair(entity.field_145851_c >> 4, entity.field_145849_e >> 4));
        super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    public boolean func_149727_a(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        super.func_149727_a(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
        return ((TileEntitySoulBrazier)p_149727_1_.func_147438_o(p_149727_2_, p_149727_3_, p_149727_4_)).setActive(p_149727_5_);
    }

    public void func_149699_a(World p_149699_1_, int p_149699_2_, int p_149699_3_, int p_149699_4_, EntityPlayer p_149699_5_) {
        super.func_149699_a(p_149699_1_, p_149699_2_, p_149699_3_, p_149699_4_, p_149699_5_);
    }

    public void func_149689_a(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        GameProfile profile;
        super.func_149689_a(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_, p_149689_6_);
        ((TileEntitySoulBrazier)p_149689_1_.func_147438_o((int)p_149689_2_, (int)p_149689_3_, (int)p_149689_4_)).owner = profile = ((EntityPlayer)p_149689_5_).func_146103_bH();
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicExploration.soulBrazierRenderID;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySoulBrazier();
    }

    public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.175f, 0.0f, 0.175f, 0.925f, 1.0f, 0.925f);
    }
}

