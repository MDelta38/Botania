/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.util.ChatUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockBloodRose
extends BlockBaseContainer
implements IPlantable {
    private final float RADIUS = 0.2f;
    @SideOnly(value=Side.CLIENT)
    private IIcon fullIcon;

    public BlockBloodRose() {
        super(Material.field_151585_k, TileEntityBloodRose.class);
        this.func_149711_c(0.0f);
        this.func_149672_a(field_149779_h);
        this.func_149676_a(0.3f, 0.0f, 0.3f, 0.7f, 0.6f, 0.7f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 1;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.func_72805_g(x, y, z);
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        float f = 0.0625f;
        return AxisAlignedBB.func_72330_a((double)((float)par2 + 0.5f - 0.2f + 0.0625f), (double)par3, (double)((float)par4 + 0.5f - 0.2f + 0.0625f), (double)((float)par2 + 0.5f + 0.2f - 0.0625f), (double)((float)par3 + 0.6f - 0.0625f), (double)((float)par4 + 0.5f + 0.2f - 0.0625f));
    }

    public void func_149670_a(World world, int posX, int posY, int posZ, Entity entity) {
        TileEntity tileentity;
        if (!world.field_72995_K && entity instanceof EntityPlayer && (tileentity = world.func_147438_o(posX, posY, posZ)) != null && tileentity instanceof TileEntityBloodRose) {
            TileEntityBloodRose chest = (TileEntityBloodRose)tileentity;
            chest.storePlayer((EntityPlayer)entity);
        }
    }

    public int func_149692_a(int par1) {
        return 0;
    }

    public Item func_149650_a(int par1, Random rand, int fortune) {
        return null;
    }

    @Override
    public TileEntity func_149915_a(World world, int metadata) {
        TileEntityBloodRose tileentitychest = new TileEntityBloodRose();
        return tileentitychest;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(this.func_149641_N());
        this.fullIcon = par1IconRegister.func_94245_a(this.func_149641_N() + "_full");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (meta == 0) {
            return super.func_149691_a(side, meta);
        }
        return this.fullIcon;
    }

    public static class TileEntityBloodRose
    extends TileEntity {
        private String customName;
        public ArrayList<String> players = new ArrayList();

        public boolean canUpdate() {
            return false;
        }

        public void sync() {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        public void storePlayer(EntityPlayer player) {
            if (!this.field_145850_b.field_72995_K && player != null) {
                if (this.players.size() == 0) {
                    this.players.add(player.func_70005_c_());
                } else {
                    this.players.set(0, player.func_70005_c_());
                }
                if (this.func_145832_p() != 1) {
                    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
                    this.sync();
                }
            }
        }

        public String popUserExcept(EntityPlayer usingPlayer, boolean excludeUser) {
            String missingPlayers = "";
            for (int i = this.players.size() - 1; i >= 0; --i) {
                String foundPlayerName = this.players.get(i);
                if (!excludeUser || !foundPlayerName.equals(usingPlayer.func_70005_c_())) {
                    if (this.field_145850_b.func_72924_a(foundPlayerName) != null) {
                        this.players.remove(i);
                        this.sync();
                        if (this.players.size() == 0) {
                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
                        }
                        return foundPlayerName;
                    }
                    missingPlayers = missingPlayers + foundPlayerName + " ";
                    continue;
                }
                if (this.players.size() != 1) continue;
                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)usingPlayer, "tile.witcheryLeechChest.onlyowntaglock", new Object[0]);
                return null;
            }
            if (!missingPlayers.isEmpty()) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)usingPlayer, "tile.witcheryLeechChest.playernotloggedin", missingPlayers);
            }
            return null;
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

        public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
            super.func_145839_a(par1NBTTagCompound);
            this.players.clear();
            NBTTagList nbtPlayersList = par1NBTTagCompound.func_150295_c("WITCPlayers", 10);
            for (int i = 0; i < nbtPlayersList.func_74745_c(); ++i) {
                NBTTagCompound nbtPlayer = nbtPlayersList.func_150305_b(i);
                String s = nbtPlayer.func_74779_i("Player");
                if (s == null || s.isEmpty()) continue;
                this.players.add(s);
            }
        }

        public void func_145841_b(NBTTagCompound nbtTag) {
            super.func_145841_b(nbtTag);
            NBTTagList nbtItemsList = new NBTTagList();
            NBTTagList nbtPlayers = new NBTTagList();
            for (int i = 0; i < this.players.size(); ++i) {
                NBTTagCompound nbtPlayer = new NBTTagCompound();
                nbtPlayer.func_74778_a("Player", this.players.get(i));
                nbtPlayers.func_74742_a((NBTBase)nbtPlayer);
            }
            nbtTag.func_74782_a("WITCPlayers", (NBTBase)nbtPlayers);
        }
    }
}

