/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityIllusion;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.familiar.IFamiliar;
import com.emoniph.witchery.infusion.infusions.spirit.IFetishTile;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.item.ItemTaglockKit;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class BlockFetish
extends BlockBaseContainer {
    public BlockFetish() {
        super(Material.field_151575_d, TileEntityFetish.class, ClassItemBlock.class);
        this.func_149676_a(0.2f, 0.0f, 0.2f, 0.8f, 1.0f, 0.8f);
        this.func_149752_b(100000.0f);
        this.func_149711_c(3.5f);
        this.func_149672_a(field_149766_f);
    }

    public void func_149666_a(Item item, CreativeTabs tabs, List list) {
        super.func_149666_a(item, tabs, list);
        if (Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW) == item) {
            list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.POPPET_ENHANCEMENT));
        }
        if (Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW) == item || Item.func_150898_a((Block)Witchery.Blocks.FETISH_WITCHS_LADDER) == item) {
            list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SCREAMER));
        }
        if (Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW) == item) {
            list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.SENTINAL));
        }
        if (Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW) == item) {
            list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.TWISTER));
        }
        if (Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW) == item) {
            list.add(InfusedSpiritEffect.setEffect(new ItemStack(item, 1, 0), InfusedSpiritEffect.GHOST_WALKER));
        }
    }

    public void func_149726_b(World world, int posX, int posY, int posZ) {
        super.func_149726_b(world, posX, posY, posZ);
        BlockUtil.setBlockDefaultDirection(world, posX, posY, posZ);
    }

    public AxisAlignedBB func_149668_a(World world, int posX, int posY, int posZ) {
        TileEntityFetish tile = BlockUtil.getTileEntity((IBlockAccess)world, posX, posY, posZ, TileEntityFetish.class);
        if (this == Witchery.Blocks.FETISH_WITCHS_LADDER || tile != null && tile.isSpectral()) {
            return null;
        }
        return super.func_149668_a(world, posX, posY, posZ);
    }

    public float func_149712_f(World world, int posX, int posY, int posZ) {
        TileEntityFetish tile = BlockUtil.getTileEntity((IBlockAccess)world, posX, posY, posZ, TileEntityFetish.class);
        if (tile == null || !tile.isSpectral()) {
            return super.func_149712_f(world, posX, posY, posZ);
        }
        return -1.0f;
    }

    public void func_149689_a(World world, int posX, int posY, int posZ, EntityLivingBase player, ItemStack stack) {
        WorldServer overworld;
        TileEntityFetish tile;
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
        if (stack != null && (tile = BlockUtil.getTileEntity((IBlockAccess)world, posX, posY, posZ, TileEntityFetish.class)) != null) {
            NBTTagCompound nbtRoot = stack.func_77978_p();
            tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
            if (nbtRoot != null && nbtRoot.func_74764_b("TileData")) {
                NBTTagCompound nbtTileData = nbtRoot.func_74775_l("TileData");
                tile.readSubDataFromNBT(nbtTileData);
                if (tile.getEffectType() == 0 && InfusedSpiritEffect.getEffectID(stack) != 0) {
                    tile.setEffectType(InfusedSpiritEffect.getEffectID(stack));
                }
            }
        }
        if (!world.field_72995_K && world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID && (overworld = MinecraftServer.func_71276_C().func_71218_a(0)) != null && overworld.func_147437_c(posX, posY, posZ)) {
            BlockUtil.setBlock((World)overworld, posX, posY, posZ, (Block)this);
            this.func_149689_a((World)overworld, posX, posY, posZ, player, stack);
            TileEntityFetish tile2 = BlockUtil.getTileEntity((IBlockAccess)overworld, posX, posY, posZ, TileEntityFetish.class);
            if (tile2 != null) {
                tile2.setSpectral(true);
            }
        }
    }

    public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer par6EntityPlayer) {
        WorldServer overworld;
        if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
            world.func_72921_c(posX, posY, posZ, par5 |= 8, 4);
        }
        this.func_149697_b(world, posX, posY, posZ, par5, 0);
        super.func_149681_a(world, posX, posY, posZ, par5, par6EntityPlayer);
        if (!world.field_72995_K && world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID && (overworld = MinecraftServer.func_71276_C().func_71218_a(0)) != null && BlockUtil.getBlock((World)overworld, posX, posY, posZ) == this) {
            overworld.func_147468_f(posX, posY, posZ);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        TileEntityFetish tile;
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((metadata & 8) == 0 && (tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityFetish.class)) != null) {
            ItemStack stack = new ItemStack(tile.func_145838_q());
            NBTTagCompound nbtRoot = new NBTTagCompound();
            stack.func_77982_d(nbtRoot);
            nbtRoot.func_74774_a("BlockColor", (byte)tile.getColor());
            InfusedSpiritEffect.setEffectID(stack, tile.getEffectType());
            NBTTagCompound nbtTileData = new NBTTagCompound();
            tile.writeSubDataToNBT(nbtTileData);
            nbtRoot.func_74782_a("TileData", (NBTBase)nbtTileData);
            drops.add(stack);
        }
        return drops;
    }

    public int func_149645_b() {
        return this == Witchery.Blocks.FETISH_WITCHS_LADDER ? 1 : super.func_149645_b();
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return this == Witchery.Blocks.FETISH_WITCHS_LADDER;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack stack;
        TileEntityFetish tile = BlockUtil.getTileEntity((IBlockAccess)world, x, y, z, TileEntityFetish.class);
        if (tile != null && player != null && (stack = player.func_70694_bm()) != null) {
            if (!tile.isSpectral()) {
                if (stack.func_77973_b() == Items.field_151100_aR) {
                    int color = BlockColored.func_150032_b((int)stack.func_77960_j());
                    tile.setColor(color);
                    if (!player.field_71075_bZ.field_75098_d && --stack.field_77994_a == 0) {
                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    }
                    return true;
                }
                if (stack.func_77973_b() == Items.field_151131_as) {
                    tile.clearBoundEntities(stack, player);
                    SoundEffect.WATER_SWIM.playAtPlayer(world, player);
                    return true;
                }
                if (stack.func_77973_b() == Witchery.Items.BOLINE) {
                    tile.cycleBoundMode(player);
                    return true;
                }
            }
            if (stack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
                tile.setBoundEntity(stack, player, tile.isSpectral());
                return true;
            }
        }
        return false;
    }

    public int func_149709_b(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side) {
        TileEntityFetish tile = BlockUtil.getTileEntity(par1IBlockAccess, posX, posY, posZ, TileEntityFetish.class);
        if (tile != null) {
            return tile.getPowerLevel();
        }
        return super.func_149709_b(par1IBlockAccess, posX, posY, posZ, side);
    }

    public int func_149748_c(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side) {
        return side == 1 ? this.func_149709_b(par1IBlockAccess, posX, posY, posZ, side) : 0;
    }

    public boolean func_149744_f() {
        return true;
    }

    private static class CreatureID {
        UUID id;
        String name;

        public CreatureID(UUID id, String name) {
            this.id = id;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj instanceof UUID) {
                return this.id.equals((UUID)obj);
            }
            if (obj.getClass() == this.getClass()) {
                return this.id.equals(((CreatureID)obj).id);
            }
            return false;
        }

        public String toString() {
            return this.name;
        }
    }

    public static class TileEntityFetish
    extends TileEntityBase
    implements IFetishTile {
        private CreatureID testID = new CreatureID(new UUID(0L, 0L), "");
        boolean lastRaiseAlarm;
        long lastActivationTime;
        final int TRIGGER_WHEN_PLAYER_NOT_IN_WHITELIST = 0;
        final int TRIGGER_WHEN_PLAYER_IN_BLACKLIST = 1;
        final int TRIGGER_WHEN_CREATURE_NOT_IN_WHITELIST = 2;
        final int TRIGGER_WHEN_NOT_ALL_CREATURES_FOUND = 3;
        final int TRIGGER_WHEN_SOME_CREATURES_NOT_FOUND = 4;
        final int TRIGGER_OFF = 5;
        int alarmMode = 5;
        private Block expectedBlock;
        private boolean spectral;
        private int color = 0;
        private int effectType = 0;
        private static ArrayList<String> groupables = null;
        private ArrayList<String> knownPlayers = new ArrayList();
        private ArrayList<String> knownCreatureTypes = new ArrayList();
        private ArrayList<CreatureID> knownCreatures = new ArrayList();

        public TileEntityFetish setExpectedBlock(Block block) {
            this.expectedBlock = block;
            return this;
        }

        public Block getExpectedBlock() {
            return this.expectedBlock;
        }

        @Override
        public void func_145845_h() {
            InfusedSpiritEffect effect;
            super.func_145845_h();
            if (!this.field_145850_b.field_72995_K && TimeUtil.secondsElapsed(1, this.ticks) && (effect = InfusedSpiritEffect.getEffect(this)) != null && effect.getRadius() > 0.0) {
                boolean someFound = false;
                int found = 0;
                int someLeft = 0;
                HashSet<String> foundTypes = new HashSet<String>();
                List entities = null;
                ArrayList<EntityLivingBase> foundEntities = new ArrayList<EntityLivingBase>();
                if (this.alarmMode != 5) {
                    double RADIUS = effect.getRadius();
                    double RADIUS_SQ = RADIUS * RADIUS;
                    AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(0.5 + (double)this.field_145851_c - RADIUS), (double)(0.5 + (double)this.field_145848_d - RADIUS), (double)(0.5 + (double)this.field_145849_e - RADIUS), (double)(0.5 + (double)this.field_145851_c + RADIUS), (double)(0.5 + (double)this.field_145848_d + RADIUS), (double)(0.5 + (double)this.field_145849_e + RADIUS));
                    entities = this.alarmMode == 0 || this.alarmMode == 1 ? this.field_145850_b.func_72872_a(EntityPlayer.class, bb) : this.field_145850_b.func_72872_a(EntityLivingBase.class, bb);
                    someLeft = entities.size();
                    for (Object obj : entities) {
                        if (obj instanceof EntityPlayer) {
                            EntityPlayer player = (EntityPlayer)obj;
                            if (this.knownPlayers != null && this.knownPlayers.contains(player.func_70005_c_())) {
                                someFound = true;
                                ++found;
                                --someLeft;
                                if (this.alarmMode != 1) continue;
                                foundEntities.add((EntityLivingBase)player);
                                continue;
                            }
                            if (this.alarmMode != 2 && this.alarmMode != 0) continue;
                            foundEntities.add((EntityLivingBase)player);
                            continue;
                        }
                        if (!(obj instanceof EntityLiving) || this.isIgnorableEntity((EntityLiving)obj)) continue;
                        EntityLiving creature = (EntityLiving)obj;
                        if (this.knownCreatureTypes != null && this.knownCreatureTypes.contains(creature.func_70005_c_())) {
                            someFound = true;
                            foundTypes.add(creature.func_70005_c_());
                            --someLeft;
                            continue;
                        }
                        this.testID.id = creature.func_110124_au();
                        if (this.knownCreatures != null && this.knownCreatures.contains(this.testID)) {
                            someFound = true;
                            ++found;
                            --someLeft;
                            continue;
                        }
                        if (this.alarmMode != 2) continue;
                        foundEntities.add((EntityLivingBase)creature);
                    }
                }
                boolean raiseAlarm = false;
                switch (this.alarmMode) {
                    case 0: 
                    case 2: {
                        raiseAlarm = someLeft > 0;
                        break;
                    }
                    case 1: {
                        raiseAlarm = someFound;
                        break;
                    }
                    case 3: {
                        raiseAlarm = found != this.knownCreatures.size() + this.knownPlayers.size() || this.knownCreatureTypes.size() != foundTypes.size();
                        break;
                    }
                    case 4: {
                        raiseAlarm = !someFound;
                    }
                }
                int cooldown = effect.getCooldownTicks();
                long currentTime = this.field_145850_b.func_82737_E();
                if ((cooldown == -1 || currentTime > this.lastActivationTime + (long)cooldown) && effect.doUpdateEffect(this, raiseAlarm, foundEntities)) {
                    this.lastActivationTime = currentTime;
                }
                if (this.lastRaiseAlarm != raiseAlarm) {
                    this.lastRaiseAlarm = raiseAlarm;
                    if (effect.isRedstoneSignaller()) {
                        BlockUtil.notifyNeighborsOfBlockChange(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
                    }
                }
            }
        }

        private boolean isFamiliar(Entity entity) {
            if (entity instanceof IFamiliar) {
                IFamiliar familiar = (IFamiliar)entity;
                return familiar.isFamiliar();
            }
            return false;
        }

        private boolean isIgnorableEntity(EntityLiving entity) {
            return entity instanceof EntityCorpse || entity instanceof EntityIllusion || entity instanceof EntitySpirit || this.isFamiliar((Entity)entity);
        }

        public boolean isSpectral() {
            return this.spectral;
        }

        public void setSpectral(boolean spectral) {
            this.spectral = spectral;
            if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public int getPowerLevel() {
            InfusedSpiritEffect effect = InfusedSpiritEffect.getEffect(this);
            if (effect != null && effect.isRedstoneSignaller()) {
                return this.lastRaiseAlarm ? 15 : 0;
            }
            return 0;
        }

        public void setColor(int dyeColor) {
            this.color = dyeColor;
            if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.syncSpectralEntities();
            }
        }

        public int getColor() {
            return this.color;
        }

        @Override
        public int getEffectType() {
            return this.effectType;
        }

        public void setEffectType(int effectID) {
            this.effectType = effectID;
            if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        @Override
        public int getX() {
            return this.field_145851_c;
        }

        @Override
        public int getY() {
            return this.field_145848_d;
        }

        @Override
        public int getZ() {
            return this.field_145849_e;
        }

        public void syncSpectralEntities() {
            TileEntityFetish tile;
            WorldServer overworld;
            if (this.field_145850_b != null && !this.field_145850_b.field_72995_K && this.field_145850_b.field_73011_w.field_76574_g == Config.instance().dimensionDreamID && (overworld = MinecraftServer.func_71276_C().func_71218_a(0)) != null && BlockUtil.getBlock((World)overworld, this.field_145851_c, this.field_145848_d, this.field_145849_e) == this.func_145838_q() && (tile = BlockUtil.getTileEntity((IBlockAccess)overworld, this.field_145851_c, this.field_145848_d, this.field_145849_e, TileEntityFetish.class)) != null) {
                NBTTagCompound nbtOurData = new NBTTagCompound();
                this.writeSubDataToNBT(nbtOurData);
                tile.readSubDataFromNBT(nbtOurData);
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

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            this.lastActivationTime = nbtRoot.func_74763_f("LastActivation");
            if (nbtRoot.func_74764_b("Spectral")) {
                this.spectral = nbtRoot.func_74767_n("Spectral");
            }
            this.readSubDataFromNBT(nbtRoot);
        }

        public void readSubDataFromNBT(NBTTagCompound nbtRoot) {
            String playerName;
            int i;
            if (nbtRoot.func_74764_b("BlockColor")) {
                this.color = nbtRoot.func_74771_c("BlockColor");
            }
            if (nbtRoot.func_74764_b("EffectTypeID")) {
                this.effectType = nbtRoot.func_74762_e("EffectTypeID");
            }
            this.alarmMode = nbtRoot.func_74764_b("AlarmMode") ? nbtRoot.func_74762_e("AlarmMode") : 5;
            if (nbtRoot.func_74764_b("KnownPlayers")) {
                NBTTagList nbtPlayers = nbtRoot.func_150295_c("KnownPlayers", 10);
                this.knownPlayers = new ArrayList();
                for (i = 0; i < nbtPlayers.func_74745_c(); ++i) {
                    NBTTagCompound nbtKnownPlayer = nbtPlayers.func_150305_b(i);
                    playerName = nbtKnownPlayer.func_74779_i("PlayerName");
                    if (playerName == null || playerName.isEmpty()) continue;
                    this.knownPlayers.add(playerName);
                }
            }
            if (nbtRoot.func_74764_b("KnownCreatureTypes")) {
                NBTTagList nbtCreatureTypes = nbtRoot.func_150295_c("KnownCreatureTypes", 10);
                this.knownCreatureTypes = new ArrayList();
                for (i = 0; i < nbtCreatureTypes.func_74745_c(); ++i) {
                    NBTTagCompound nbtKnownCreatureType = nbtCreatureTypes.func_150305_b(i);
                    String typeName = nbtKnownCreatureType.func_74779_i("CreatureTypeName");
                    if (typeName == null || typeName.isEmpty()) continue;
                    this.knownCreatureTypes.add(typeName);
                }
            }
            if (nbtRoot.func_74764_b("KnownCreatures")) {
                NBTTagList nbtCreatures = nbtRoot.func_150295_c("KnownCreatures", 10);
                this.knownCreatures = new ArrayList();
                for (i = 0; i < nbtCreatures.func_74745_c(); ++i) {
                    NBTTagCompound nbtKnownCreature = nbtCreatures.func_150305_b(i);
                    playerName = nbtKnownCreature.func_74779_i("PlayerName");
                    long uuidMost = nbtKnownCreature.func_74763_f("CreatureMost");
                    long uuidLeast = nbtKnownCreature.func_74763_f("CreatureLeast");
                    String cname = nbtKnownCreature.func_74779_i("CreatureName");
                    if (uuidMost == 0L && uuidLeast == 0L) continue;
                    UUID creatureID = new UUID(uuidMost, uuidLeast);
                    this.knownCreatures.add(new CreatureID(creatureID, cname));
                }
            }
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            nbtRoot.func_74772_a("LastActivation", this.lastActivationTime);
            nbtRoot.func_74757_a("Spectral", this.spectral);
            this.writeSubDataToNBT(nbtRoot);
        }

        public void writeSubDataToNBT(NBTTagCompound nbtRoot) {
            nbtRoot.func_74774_a("BlockColor", (byte)this.color);
            nbtRoot.func_74768_a("EffectTypeID", this.effectType);
            nbtRoot.func_74768_a("AlarmMode", this.alarmMode);
            if (this.knownPlayers.size() > 0) {
                NBTTagList nbtPlayers = new NBTTagList();
                for (String playerName : this.knownPlayers) {
                    NBTTagCompound nbtKnownPlayer = new NBTTagCompound();
                    nbtKnownPlayer.func_74778_a("PlayerName", playerName);
                    nbtPlayers.func_74742_a((NBTBase)nbtKnownPlayer);
                }
                nbtRoot.func_74782_a("KnownPlayers", (NBTBase)nbtPlayers);
            }
            if (this.knownCreatureTypes.size() > 0) {
                NBTTagList nbtCreatureTypes = new NBTTagList();
                for (String typeName : this.knownCreatureTypes) {
                    NBTTagCompound nbtKnownCreatureType = new NBTTagCompound();
                    nbtKnownCreatureType.func_74778_a("CreatureTypeName", typeName);
                    nbtCreatureTypes.func_74742_a((NBTBase)nbtKnownCreatureType);
                }
                nbtRoot.func_74782_a("KnownCreatureTypes", (NBTBase)nbtCreatureTypes);
            }
            if (this.knownCreatures.size() > 0) {
                NBTTagList nbtCreatures = new NBTTagList();
                for (CreatureID creatureID : this.knownCreatures) {
                    NBTTagCompound nbtKnownCreature = new NBTTagCompound();
                    nbtKnownCreature.func_74772_a("CreatureMost", creatureID.id.getMostSignificantBits());
                    nbtKnownCreature.func_74772_a("CreatureLeast", creatureID.id.getLeastSignificantBits());
                    nbtKnownCreature.func_74778_a("CreatureName", creatureID.name);
                    nbtCreatures.func_74742_a((NBTBase)nbtKnownCreature);
                }
                nbtRoot.func_74782_a("KnownCreatures", (NBTBase)nbtCreatures);
            }
        }

        private boolean isGroupableCreature(UUID otherCreature, String creatureName) {
            if (groupables == null) {
                groupables = new ArrayList();
                this.addGroupableType(EntityVillager.class);
                this.addGroupableType(EntityGoblin.class);
                this.addGroupableType(EntitySheep.class);
                this.addGroupableType(EntityCow.class);
                this.addGroupableType(EntityMooshroom.class);
                this.addGroupableType(EntityChicken.class);
                this.addGroupableType(EntityPig.class);
                this.addGroupableType(EntityHorse.class);
                this.addGroupableType(EntityBat.class);
                this.addGroupableType(EntitySquid.class);
                this.addGroupableType(EntityCovenWitch.class);
            }
            return groupables.contains(creatureName);
        }

        public void setBoundEntity(ItemStack stack, EntityPlayer player, boolean readonly) {
            if (!this.field_145850_b.field_72995_K && stack != null) {
                ItemTaglockKit.BoundType boundEntityType = Witchery.Items.TAGLOCK_KIT.getBoundEntityType(stack, 1);
                switch (boundEntityType) {
                    case PLAYER: {
                        if (!readonly) {
                            String otherUsername = Witchery.Items.TAGLOCK_KIT.getBoundUsername(stack, 1);
                            if (!this.knownPlayers.contains(otherUsername)) {
                                this.knownPlayers.add(otherUsername);
                            } else {
                                this.knownPlayers.remove(otherUsername);
                            }
                            if (!player.field_71075_bZ.field_75098_d && --stack.field_77994_a <= 0) {
                                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                            }
                            if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
                            this.syncSpectralEntities();
                        }
                        this.showCurrentKnownEntities(player);
                        break;
                    }
                    case CREATURE: {
                        if (!readonly) {
                            String creatureName;
                            UUID otherCreature = Witchery.Items.TAGLOCK_KIT.getBoundCreatureID(stack, 1);
                            if (this.isGroupableCreature(otherCreature, creatureName = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1))) {
                                if (!this.knownCreatureTypes.contains(creatureName)) {
                                    this.knownCreatureTypes.add(creatureName);
                                } else {
                                    this.knownCreatureTypes.remove(creatureName);
                                }
                            } else {
                                CreatureID creatureID = new CreatureID(otherCreature, creatureName);
                                if (!this.knownCreatures.contains(creatureID)) {
                                    this.knownCreatures.add(creatureID);
                                } else {
                                    this.knownCreatures.remove(creatureID);
                                }
                            }
                            if (!player.field_71075_bZ.field_75098_d && --stack.field_77994_a <= 0) {
                                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                            }
                            if (player instanceof EntityPlayerMP) {
                                ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                            }
                            this.syncSpectralEntities();
                        }
                        this.showCurrentKnownEntities(player);
                        break;
                    }
                    case NONE: {
                        this.showCurrentKnownEntities(player);
                    }
                }
            }
        }

        public void clearBoundEntities(ItemStack stack, EntityPlayer player) {
            if (player != null && !player.field_70170_p.field_72995_K && stack != null) {
                this.knownCreatureTypes.clear();
                this.knownCreatures.clear();
                this.knownPlayers.clear();
                if (!player.field_71075_bZ.field_75098_d) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, new ItemStack(Items.field_151133_ar));
                }
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
                this.syncSpectralEntities();
                this.showCurrentKnownEntities(player);
            }
        }

        public void cycleBoundMode(EntityPlayer player) {
            if (!this.field_145850_b.field_72995_K) {
                if (++this.alarmMode > 5) {
                    this.alarmMode = 0;
                }
                this.syncSpectralEntities();
                this.showCurrentKnownEntities(player);
            }
        }

        private void addGroupableType(Class<? extends EntityLiving> className) {
            String name = (String)EntityList.field_75626_c.get(className);
            if (name != null) {
                String localName = StatCollector.func_74838_a((String)("entity." + name + ".name"));
                groupables.add(localName);
            }
        }

        private void showCurrentKnownEntities(EntityPlayer player) {
            StringBuffer sb = new StringBuffer();
            for (String s : this.knownPlayers) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(s);
            }
            for (String s : this.knownCreatureTypes) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append("#");
                sb.append(s);
            }
            for (CreatureID cid : this.knownCreatures) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(cid.toString());
            }
            String message = sb.toString();
            String key = "";
            switch (this.alarmMode) {
                case 0: {
                    key = "tile.witchery.scarecrow.operation.playerwhitelist";
                    break;
                }
                case 1: {
                    key = "tile.witchery.scarecrow.operation.playerblacklist";
                    break;
                }
                case 2: {
                    key = "tile.witchery.scarecrow.operation.creaturewhitelist";
                    break;
                }
                case 3: {
                    key = "tile.witchery.scarecrow.operation.allnotfound";
                    break;
                }
                case 4: {
                    key = "tile.witchery.scarecrow.operation.onenotfound";
                    break;
                }
                case 5: {
                    key = "tile.witchery.scarecrow.operation.off";
                }
            }
            ChatUtil.sendTranslated((ICommandSender)player, key, message);
        }
    }

    public static class ClassItemBlock
    extends ItemBlock {
        public ClassItemBlock(Block block) {
            super(block);
        }

        public String func_77653_i(ItemStack stack) {
            String s = super.func_77653_i(stack);
            String effect = InfusedSpiritEffect.getEffectDisplayName(stack);
            if (effect != null) {
                return s + " (" + effect + ")";
            }
            return s;
        }
    }
}

