/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ThreadDownloadImageData
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockStatueOfWorship
extends BlockBaseContainer {
    public BlockStatueOfWorship() {
        super(Material.field_151576_e, TileEntityStatueOfWorship.class, ClassItemBlock.class);
        this.func_149711_c(3.5f);
        this.func_149752_b(20.0f);
        this.func_149672_a(field_149769_e);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public String func_149732_F() {
        return super.func_149732_F();
    }

    public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase player, ItemStack stack) {
        String playerName;
        NBTTagCompound nbtRoot;
        TileEntityStatueOfWorship tile;
        int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            world.func_72921_c(posX, posY, posZ, 2, 2);
        } else if (l == 1) {
            world.func_72921_c(posX, posY, posZ, 5, 2);
        } else if (l == 2) {
            world.func_72921_c(posX, posY, posZ, 3, 2);
        } else if (l == 3) {
            world.func_72921_c(posX, posY, posZ, 4, 2);
        }
        if (stack != null && player instanceof EntityPlayer && (tile = BlockUtil.getTileEntity((IBlockAccess)world, posX, posY, posZ, TileEntityStatueOfWorship.class)) != null && (nbtRoot = stack.func_77978_p()) != null && nbtRoot.func_74764_b("WITCBoundPlayer") && (playerName = nbtRoot.func_74779_i("WITCBoundPlayer")) != null && !playerName.isEmpty()) {
            tile.setOwner(playerName);
        }
    }

    public void func_149726_b(World world, int posX, int posY, int posZ) {
        super.func_149726_b(world, posX, posY, posZ);
        BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
    }

    public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer) {
        if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
            world.func_72921_c(posX, posY, posZ, par5 |= 8, 4);
        }
        this.func_149697_b(world, posX, posY, posZ, par5, 0);
        super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        TileEntityStatueOfWorship tile;
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((metadata & 8) == 0 && (tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityStatueOfWorship.class)) != null) {
            ItemStack stack = new ItemStack(tile.func_145838_q());
            NBTTagCompound nbtRoot = new NBTTagCompound();
            stack.func_77982_d(nbtRoot);
            nbtRoot.func_74778_a("WITCBoundPlayer", tile.owner != null ? tile.owner : "");
            drops.add(stack);
        }
        return drops;
    }

    private static String getBoundPlayerName(ItemStack stack) {
        NBTTagCompound nbtRoot = stack.func_77978_p();
        if (nbtRoot != null) {
            return nbtRoot.func_74779_i("WITCBoundPlayer");
        }
        return "";
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hiyY, float hitZ) {
        if (!world.field_72995_K) {
            int worshippers;
            TileEntityStatueOfWorship tile;
            ItemStack item = player.func_70694_bm();
            if (item != null && item.func_77973_b() == Items.field_151156_bN && (tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityStatueOfWorship.class)) != null && tile.owner != null && tile.owner.equals(player.func_70005_c_()) && (worshippers = tile.updateWorshippersAndGetLevel()) >= 5 && tile.summonGoblinGods(player, 16.0, 8)) {
                if (!player.field_71075_bZ.field_75098_d) {
                    if (--item.field_77994_a <= 0) {
                        player.func_70062_b(0, null);
                    }
                    double R = 8.0;
                    AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)((double)x + 0.5 - R), (double)((double)y + 0.5 - R), (double)((double)z + 0.5 - R), (double)((double)x + 0.5 + R), (double)((double)y + 0.5 + R), (double)((double)z + 0.5 + R));
                    List entities = world.func_72872_a(EntityGoblin.class, bb);
                    int iMax = Math.min(entities.size(), 5);
                    for (int i = 0; i < iMax; ++i) {
                        if (!(entities.get(i) instanceof EntityGoblin)) continue;
                        EntityGoblin goblin = (EntityGoblin)entities.get(i);
                        goblin.func_70097_a(DamageSource.field_76376_m, goblin.func_110138_aP());
                    }
                }
                ParticleEffect.FLAME.send(SoundEffect.MOB_ENDERDRAGON_GROWL, world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, 0.5, 0.5, 16);
                return true;
            }
            ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, 0.5, 0.5, 16);
        }
        return false;
    }

    public static class TileEntityStatueOfWorship
    extends TileEntityBase {
        private String owner;
        @SideOnly(value=Side.CLIENT)
        private ThreadDownloadImageData downloadImageSkin;
        @SideOnly(value=Side.CLIENT)
        private ResourceLocation locationSkin;

        public void setOwner(EntityPlayer player) {
            this.owner = player.func_70005_c_();
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public void setOwner(String player) {
            this.owner = player;
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public boolean hasOwner() {
            return this.owner != null && !this.owner.isEmpty();
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            nbtRoot.func_74778_a("Owner", this.owner != null ? this.owner : "");
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            this.owner = nbtRoot.func_74779_i("Owner");
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

        @SideOnly(value=Side.CLIENT)
        public ResourceLocation getLocationSkin() {
            if (this.locationSkin == null) {
                this.setupCustomSkin();
            }
            if (this.locationSkin != null) {
                return this.locationSkin;
            }
            return AbstractClientPlayer.field_110314_b;
        }

        @SideOnly(value=Side.CLIENT)
        private void setupCustomSkin() {
            if (this.owner != null && !this.owner.isEmpty()) {
                this.locationSkin = AbstractClientPlayer.func_110311_f((String)this.owner);
                this.downloadImageSkin = AbstractClientPlayer.func_110304_a((ResourceLocation)this.locationSkin, (String)this.owner);
            }
        }

        public int updateWorshippersAndGetLevel() {
            double R = 8.0;
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.5 - 8.0), (double)((double)this.field_145848_d + 0.5 - 8.0), (double)((double)this.field_145849_e + 0.5 - 8.0), (double)((double)this.field_145851_c + 0.5 + 8.0), (double)((double)this.field_145848_d + 0.5 + 8.0), (double)((double)this.field_145849_e + 0.5 + 8.0));
            List entities = this.field_145850_b.func_72872_a(EntityGoblin.class, bb);
            int worshipCount = 0;
            for (Object entity : entities) {
                if (!(entity instanceof EntityGoblin)) continue;
                EntityGoblin goblin = (EntityGoblin)entity;
                if (goblin.isWorshipping()) {
                    ++worshipCount;
                    continue;
                }
                goblin.beginWorship(this);
            }
            return worshipCount;
        }

        @Override
        public void func_145845_h() {
            super.func_145845_h();
            if (!this.field_145850_b.field_72995_K && this.hasOwner()) {
                int PULSE_INTERVAL_IN_SECS = 5;
                if (TimeUtil.secondsElapsed(5, this.ticks)) {
                    int worshipCount = this.updateWorshippersAndGetLevel();
                    EntityPlayerMP player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(this.owner);
                    if (player != null) {
                        ExtendedPlayer playerEx;
                        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
                        int WORSHIP_LEVEL_1 = 5;
                        int WORSHIP_LEVEL_2 = 10;
                        int WORSHIP_LEVEL_3 = 15;
                        if (worshipCount >= 5) {
                            int maxEnergy;
                            int currentEnergy;
                            int RECHARGE_RADIUS = 64;
                            int RECHARGE_RADIUS_SQ = 4096;
                            if (player.func_70092_e(0.5 + (double)this.field_145851_c, 0.5 + (double)this.field_145848_d, 0.5 + (double)this.field_145849_e) <= 4096.0 && (currentEnergy = Infusion.getCurrentEnergy((EntityPlayer)player)) < (maxEnergy = Infusion.getMaxEnergy((EntityPlayer)player))) {
                                int ENERGY_PER_PULSE = 30;
                                Infusion.setCurrentEnergy((EntityPlayer)player, Math.min(currentEnergy + 30, maxEnergy));
                                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NOTE_PLING, (Entity)player, 1.0, 2.0, 8);
                            }
                        }
                        if (worshipCount >= 10 && (playerEx = ExtendedPlayer.get((EntityPlayer)player)) != null) {
                            playerEx.addWorship(worshipCount >= 15 ? 1 : 0);
                        }
                        double GODS_SUMMON_CHANCE = 0.01 * (double)Config.instance().hobgoblinGodSpawnChance * 0.01;
                        if (worshipCount >= 15 && Config.instance().hobgoblinGodSpawnChance > 0 && this.field_145850_b.field_73012_v.nextDouble() < GODS_SUMMON_CHANCE) {
                            this.summonGoblinGods((EntityPlayer)player, 64.0, 16);
                        }
                    }
                }
            }
        }

        public boolean summonGoblinGods(EntityPlayer player, double detectDistance, int spawnDistance) {
            EntityCreature mog;
            double R2 = detectDistance;
            AxisAlignedBB bb2 = AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c + 0.5 - R2), (double)((double)this.field_145848_d + 0.5 - R2), (double)((double)this.field_145849_e + 0.5 - R2), (double)((double)this.field_145851_c + 0.5 + R2), (double)((double)this.field_145848_d + 0.5 + R2), (double)((double)this.field_145849_e + 0.5 + R2));
            if (this.field_145850_b.func_72872_a(EntityGoblinMog.class, bb2).size() == 0 && this.field_145850_b.func_72872_a(EntityGoblinGulg.class, bb2).size() == 0 && (mog = InfusionInfernal.spawnCreature(this.field_145850_b, EntityGoblinMog.class, this.field_145851_c, this.field_145848_d, this.field_145849_e, (EntityLivingBase)player, 0, spawnDistance, ParticleEffect.FLAME, SoundEffect.MOB_WITHER_SPAWN)) != null) {
                mog.func_110161_a(null);
                mog.func_70624_b((EntityLivingBase)player);
                EntityGoblinGulg gulg = new EntityGoblinGulg(this.field_145850_b);
                gulg.func_70012_b(mog.field_70165_t, mog.field_70163_u, mog.field_70161_v, 0.0f, 0.0f);
                this.field_145850_b.func_72838_d((Entity)gulg);
                gulg.func_110161_a(null);
                gulg.func_70624_b((EntityLivingBase)player);
                return true;
            }
            return false;
        }
    }

    public static class ClassItemBlock
    extends ItemBlock {
        public ClassItemBlock(Block block) {
            super(block);
        }

        public String func_77653_i(ItemStack stack) {
            String s = super.func_77653_i(stack);
            String player = BlockStatueOfWorship.getBoundPlayerName(stack);
            if (player != null && !player.isEmpty()) {
                return s + " (" + player + ")";
            }
            return s;
        }
    }
}

