/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockBloodCrucible
extends BlockBaseContainer {
    public BlockBloodCrucible() {
        super(Material.field_151576_e, TileEntityBloodCrucible.class);
        this.func_149752_b(1000.0f);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149769_e);
        this.func_149676_a(0.25f, 0.0f, 0.25f, 0.75f, 0.31f, 0.75f);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K) {
            TileEntityBloodCrucible crucible = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityBloodCrucible.class);
            if (crucible != null && world instanceof WorldServer) {
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                ItemStack stack = player.func_70694_bm();
                if (playerEx.getVampireLevel() >= 10 && (crucible.isFull() || player.field_71075_bZ.field_75098_d) && stack != null) {
                    boolean success = false;
                    if (Witchery.Items.GENERIC.itemArtichoke.isMatch(stack)) {
                        playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.STORM);
                        success = true;
                    } else if (Witchery.Items.GENERIC.itemBatWool.isMatch(stack)) {
                        playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.SWARM);
                        success = true;
                    } else if (stack.func_77973_b() == Items.field_151103_aS) {
                        playerEx.setVampireUltimate(ExtendedPlayer.VampireUltimate.FARM);
                        success = true;
                    }
                    if (success) {
                        crucible.drainAll();
                        --stack.field_77994_a;
                        ParticleEffect.REDDUST.send(SoundEffect.RANDOM_FIZZ, world, 0.5 + (double)x, y, 0.5 + (double)z, 0.5, 0.5, 16);
                    } else {
                        ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5 + (double)x, y, 0.5 + (double)z, 0.5, 0.5, 16);
                    }
                } else {
                    ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5 + (double)x, y, 0.5 + (double)z, 0.5, 0.5, 16);
                }
            }
            return true;
        }
        return true;
    }

    public int func_149745_a(Random rand) {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
    }

    public static class TileEntityBloodCrucible
    extends TileEntity {
        private static final int MAX_BLOOD_LEVEL = 20;
        private int bloodLevel;

        public boolean canUpdate() {
            return false;
        }

        public boolean isFull() {
            return this.bloodLevel == 20;
        }

        public void drainAll() {
            this.bloodLevel = 0;
            this.markBlockForUpdate(false);
        }

        public int getBloodLevel() {
            return this.bloodLevel;
        }

        public void increaseBloodLevel() {
            if (this.bloodLevel < 20) {
                this.bloodLevel = Math.min(5 + this.bloodLevel, 20);
                this.markBlockForUpdate(false);
            }
        }

        public float getPercentFilled() {
            return (float)this.bloodLevel / 20.0f;
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            nbtRoot.func_74768_a("BloodLevel", this.bloodLevel);
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            this.bloodLevel = nbtRoot.func_74762_e("BloodLevel");
        }

        public void markBlockForUpdate(boolean notifyNeighbours) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (notifyNeighbours && this.field_145850_b != null) {
                this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
            }
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            this.func_145839_a(packet.func_148857_g());
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

