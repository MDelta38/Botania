/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBloodRose;
import com.emoniph.witchery.blocks.BlockCrystalBall;
import com.emoniph.witchery.blocks.BlockLeechChest;
import com.emoniph.witchery.entity.EntityEye;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.network.PacketCamPos;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ItemTaglockKit
extends ItemBase {
    @SideOnly(value=Side.CLIENT)
    private IIcon emptyIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon fullIcon;
    static final String KEY_PLAYER_NAME = "WITCPlayer";
    static final String KEY_DISPLAY_NAME = "WITCDisplayName";
    static final String KEY_ENTITY_ID_MOST = "WITCEntityIDm";
    static final String KEY_ENTITY_ID_LEAST = "WITCEntityIDl";

    public ItemTaglockKit() {
        this.func_77625_d(16);
        this.func_77656_e(0);
    }

    public String func_77653_i(ItemStack itemStack) {
        String entityID = this.getBoundEntityDisplayName(itemStack, 1);
        String localizedName = super.func_77653_i(itemStack);
        return !entityID.isEmpty() ? String.format("%s (%s)", localizedName, entityID) : localizedName;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips) {
        super.func_77624_a(stack, player, list, advTooltips);
        String entityID = this.getBoundEntityDisplayName(stack, 1);
        if (entityID != null && !entityID.isEmpty()) {
            list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), entityID));
        } else {
            list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
        }
    }

    public void func_94581_a(IIconRegister par1IconRegister) {
        this.emptyIcon = par1IconRegister.func_94245_a(this.func_111208_A());
        this.fullIcon = par1IconRegister.func_94245_a(this.func_111208_A() + ".full");
    }

    public int func_77626_a(ItemStack stack) {
        return 1200;
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown) {
        World world = player.field_70170_p;
        int elapsedTicks = this.func_77626_a(stack) - countdown;
        if (!world.field_72995_K && elapsedTicks % 20 == 0) {
            EntityLivingBase entity = this.getBoundEntity(world, (Entity)player, stack, 1);
            if (entity != null && entity.field_71093_bK == player.field_71093_bK) {
                if (entity == player) {
                    if (elapsedTicks == 0) {
                        EntityEye eye = new EntityEye(world);
                        eye.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, 90.0f);
                        world.func_72838_d((Entity)eye);
                        Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(true, elapsedTicks == 0, (Entity)eye), player);
                    } else {
                        Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(true, false, null), player);
                    }
                } else {
                    Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(true, elapsedTicks == 0, (Entity)entity), player);
                }
            } else {
                Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(false, false, null), player);
            }
        }
    }

    public EnumAction func_77661_b(ItemStack stack) {
        return EnumAction.none;
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(false, false, null), player);
        }
        return stack;
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown) {
        if (!world.field_72995_K) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(false, false, null), player);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int damageValue) {
        return damageValue == 1 ? this.fullIcon : this.emptyIcon;
    }

    public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (block == Blocks.field_150324_C || block == Witchery.Blocks.COFFIN || block.func_149739_a().equals("tile.blockCarpentersBed") || block.isBed((IBlockAccess)world, x, y, z, (EntityLivingBase)player)) {
            int j1;
            int i1 = world.func_72805_g(x, y, z);
            Log.instance().debug(String.format("Using taglock on bed [%s] meta %d", block.func_149739_a(), i1));
            if (block == Blocks.field_150324_C && !BlockBed.func_149975_b((int)i1) && world.func_147439_a(x += BlockBed.field_149981_a[j1 = BlockBed.func_149895_l((int)i1)][0], y, z += BlockBed.field_149981_a[j1][1]) != Blocks.field_150324_C) {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                return !world.field_72995_K;
            }
            ChunkCoordinates clickedBedLocation = new ChunkCoordinates(x, y, z);
            if (player.func_70093_af()) {
                if (!world.field_72995_K) {
                    this.setTaglockForEntity(world, player, itemstack, player);
                }
                return !world.field_72995_K;
            }
            if (!world.field_72995_K) {
                boolean taglockSaved = this.tryBindTaglockFromBed(itemstack, player, world, clickedBedLocation);
                if (!taglockSaved && block != Blocks.field_150324_C) {
                    if (world.func_147439_a(x + 1, y, z) == block) {
                        taglockSaved = this.tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x + 1, y, z));
                    }
                    if (!taglockSaved && world.func_147439_a(x, y, z + 1) == block) {
                        taglockSaved = this.tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z + 1));
                    }
                    if (!taglockSaved && world.func_147439_a(x - 1, y, z) == block) {
                        taglockSaved = this.tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x - 1, y, z));
                    }
                    if (!taglockSaved && world.func_147439_a(x, y, z - 1) == block) {
                        taglockSaved = this.tryBindTaglockFromBed(itemstack, player, world, new ChunkCoordinates(x, y, z - 1));
                    }
                }
                if (taglockSaved) {
                    return !world.field_72995_K;
                }
            }
            if (!world.field_72995_K) {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
            return !world.field_72995_K;
        }
        if (block == Witchery.Blocks.LEECH_CHEST) {
            if (!world.field_72995_K) {
                BlockLeechChest.TileEntityLeechChest chest;
                String username;
                TileEntity tile = world.func_147438_o(x, y, z);
                if (tile != null && tile instanceof BlockLeechChest.TileEntityLeechChest && (username = (chest = (BlockLeechChest.TileEntityLeechChest)tile).popUserExcept(player)) != null && !username.isEmpty()) {
                    this.setTaglockForEntity(world, player, itemstack, username);
                    return !world.field_72995_K;
                }
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
            return !world.field_72995_K;
        }
        if (block == Witchery.Blocks.BLOOD_ROSE) {
            if (!world.field_72995_K) {
                BlockBloodRose.TileEntityBloodRose chest;
                String username;
                TileEntity tile = world.func_147438_o(x, y, z);
                if (tile != null && tile instanceof BlockBloodRose.TileEntityBloodRose && (username = (chest = (BlockBloodRose.TileEntityBloodRose)tile).popUserExcept(player, false)) != null && !username.isEmpty()) {
                    this.setTaglockForEntity(world, player, itemstack, username);
                    return !world.field_72995_K;
                }
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
            return !world.field_72995_K;
        }
        if (block == Witchery.Blocks.CRYSTAL_BALL) {
            if (itemstack.func_77960_j() > 0) {
                if (!world.field_72995_K && BlockCrystalBall.tryConsumePower(world, player, x, y, z)) {
                    player.func_71008_a(itemstack, this.func_77626_a(itemstack));
                } else if (world.field_72995_K) {
                    player.func_71008_a(itemstack, this.func_77626_a(itemstack));
                }
            } else {
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
            return !world.field_72995_K;
        }
        return super.onItemUseFirst(itemstack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    private boolean tryBindTaglockFromBed(ItemStack itemstack, EntityPlayer player, World world, ChunkCoordinates clickedBedLocation) {
        String boundName = "";
        EntityLivingBase boundEntity = this.getBoundEntity(world, (Entity)player, itemstack, 1);
        if (boundEntity != null && boundEntity instanceof EntityPlayer) {
            boundName = ((EntityPlayer)boundEntity).func_70005_c_();
        }
        ArrayList<EntityPlayer> players = new ArrayList<EntityPlayer>();
        for (Object obj : world.field_73010_i) {
            EntityPlayer worldPlayer = (EntityPlayer)obj;
            ChunkCoordinates playerBedLocation = worldPlayer.getBedLocation(player.field_71093_bK);
            if (playerBedLocation == null || !playerBedLocation.equals((Object)clickedBedLocation)) continue;
            players.add(worldPlayer);
        }
        Collections.sort(players, new PlayerComparitor());
        boolean taglockSaved = false;
        if (players.size() > 0) {
            if (boundName.isEmpty()) {
                taglockSaved = this.setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
            } else {
                boolean found = false;
                for (int i = 0; i < players.size() && !found; ++i) {
                    if (!((EntityPlayer)players.get(i)).func_70005_c_().equals(boundName)) continue;
                    taglockSaved = i == players.size() - 1 ? this.setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0)) : this.setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(i + 1));
                    found = true;
                }
                if (!found) {
                    taglockSaved = this.setTaglockForEntity(world, player, itemstack, (EntityPlayer)players.get(0));
                }
            }
        }
        return taglockSaved;
    }

    public static boolean isTaglockRestricted(EntityPlayer collector, EntityLivingBase target) {
        if (!(target instanceof EntityPlayer) || collector.equals((Object)target)) {
            return false;
        }
        if (Config.instance().restrictTaglockCollectionOnNonPVPServers && !MinecraftServer.func_71276_C().func_71219_W()) {
            return true;
        }
        EntityPlayer targetPlayer = (EntityPlayer)target;
        return Config.instance().restrictTaglockCollectionForStaffMembers && MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(targetPlayer.func_146103_bH());
    }

    private boolean setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, EntityPlayer victim) {
        if (!ItemTaglockKit.isTaglockRestricted(player, (EntityLivingBase)victim)) {
            this.setTaglockForEntity(world, player, itemstack, victim.func_70005_c_());
            return true;
        }
        return false;
    }

    private void setTaglockForEntity(World world, EntityPlayer player, ItemStack itemstack, String victimUsername) {
        if (!world.field_72995_K) {
            if (itemstack.field_77994_a > 1) {
                ItemStack newStack = new ItemStack((Item)this, 1, 1);
                this.setTaglockForEntity(newStack, player, victimUsername, true, (Integer)1);
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
                if (!player.field_71071_by.func_70441_a(newStack)) {
                    world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t + 0.5, player.field_70163_u + 1.5, player.field_70161_v + 0.5, newStack));
                } else if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
            } else {
                this.setTaglockForEntity(itemstack, player, victimUsername, true, (Integer)1);
                itemstack.func_77964_b(1);
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void onEntityInteract(World world, EntityPlayer player, ItemStack stack, EntityInteractEvent event) {
        if (world.field_72995_K || stack == null || stack.func_77973_b() != Witchery.Items.TAGLOCK_KIT || event.target == null || !(event.target instanceof EntityLivingBase)) return;
        EntityLivingBase target = (EntityLivingBase)event.target;
        if (!(target instanceof EntityPlayer) || this.isSneakSuccessful(player, target)) {
            if (target instanceof EntityTreefyd || target instanceof EntityImp || target instanceof EntityWingedMonkey && !player.func_70093_af()) return;
            if (stack.field_77994_a > 1) {
                ItemStack newStack = new ItemStack((Item)Witchery.Items.TAGLOCK_KIT, 1, 1);
                Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(newStack, player, (Entity)target, true, (Integer)1);
                --stack.field_77994_a;
                if (stack.field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
                if (!player.field_71071_by.func_70441_a(newStack)) {
                    world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t + 0.5, player.field_70163_u + 1.5, player.field_70161_v + 0.5, newStack));
                } else if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
            } else {
                Witchery.Items.TAGLOCK_KIT.setTaglockForEntity(stack, player, (Entity)target, true, (Integer)1);
                stack.func_77964_b(1);
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                }
            }
        } else {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)event.entityPlayer, "witchery.taglockkit.taglockfailed", new Object[0]);
            if (target instanceof EntityPlayer) {
                ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (ICommandSender)((EntityPlayer)target), "witchery.taglockkit.taglockdiscovered", new Object[0]);
            }
        }
        event.setCanceled(true);
    }

    private boolean isSneakSuccessful(EntityPlayer sneaker, EntityLivingBase target) {
        double targetFacing;
        if (ItemTaglockKit.isTaglockRestricted(sneaker, target)) {
            return false;
        }
        double sneakerFacing = (sneaker.field_70759_as + 90.0f) % 360.0f;
        if (sneakerFacing < 0.0) {
            sneakerFacing += 360.0;
        }
        if ((targetFacing = (double)((target.field_70759_as + 90.0f) % 360.0f)) < 0.0) {
            targetFacing += 360.0;
        }
        double ARC = 45.0;
        double diff = Math.abs(targetFacing - sneakerFacing);
        double chance = 0.0;
        if (360.0 - diff % 360.0 < 45.0 || diff % 360.0 < 45.0) {
            chance = sneaker.func_70093_af() ? 0.6 : 0.3;
        } else {
            double d = chance = sneaker.func_70093_af() ? 0.1 : 0.01;
            if (sneaker.func_82150_aj()) {
                chance += 0.1;
            }
        }
        return sneaker.field_70170_p.field_73012_v.nextDouble() < chance;
    }

    public void setTaglockForEntity(ItemStack stack, EntityPlayer player, Entity entity, boolean playSoundAtPlayer, Integer index) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer hitPlayer = (EntityPlayer)entity;
            stack.func_77978_p().func_74778_a(KEY_PLAYER_NAME + index.toString(), hitPlayer.func_70005_c_());
            stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_MOST + index.toString());
            stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_LEAST + index.toString());
        } else if (entity instanceof EntityLiving) {
            stack.func_77978_p().func_82580_o(KEY_PLAYER_NAME + index.toString());
            UUID id = entity.getPersistentID();
            ((EntityLiving)entity).func_110163_bv();
            stack.func_77978_p().func_74772_a(KEY_ENTITY_ID_MOST + index.toString(), id.getMostSignificantBits());
            stack.func_77978_p().func_74772_a(KEY_ENTITY_ID_LEAST + index.toString(), id.getLeastSignificantBits());
            stack.func_77978_p().func_74778_a(KEY_DISPLAY_NAME + index.toString(), entity.func_70005_c_());
        } else {
            return;
        }
        if (playSoundAtPlayer) {
            player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.5f, 0.4f / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4f + 0.8f));
        }
    }

    public void clearTaglock(ItemStack stack, Integer index) {
        if (stack != null) {
            stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_MOST + index.toString());
            stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_LEAST + index.toString());
            stack.func_77978_p().func_82580_o(KEY_PLAYER_NAME + index.toString());
            stack.func_77978_p().func_82580_o(KEY_DISPLAY_NAME + index.toString());
        }
    }

    public void setTaglockForEntity(ItemStack stack, EntityPlayer player, String username, boolean playSoundAtPlayer, Integer index) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (username == null || username.isEmpty()) {
            return;
        }
        stack.func_77978_p().func_74778_a(KEY_PLAYER_NAME + index.toString(), username);
        stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_MOST + index.toString());
        stack.func_77978_p().func_82580_o(KEY_ENTITY_ID_LEAST + index.toString());
        if (playSoundAtPlayer) {
            player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.5f, 0.4f / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4f + 0.8f));
        }
    }

    public boolean isTaglockPresent(ItemStack itemStack, Integer index) {
        if (itemStack.func_77942_o()) {
            String playerName;
            if (itemStack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString()) && (playerName = itemStack.func_77978_p().func_74779_i(KEY_PLAYER_NAME + index.toString())) != null && !playerName.isEmpty()) {
                return true;
            }
            if (itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString())) {
                return true;
            }
        }
        return false;
    }

    public String getBoundEntityDisplayName(ItemStack itemStack, Integer index) {
        if (itemStack.func_77942_o()) {
            if (itemStack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString())) {
                String playerName = itemStack.func_77978_p().func_74779_i(KEY_PLAYER_NAME + index.toString());
                return playerName;
            }
            if (itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_DISPLAY_NAME + index.toString())) {
                String displayName = itemStack.func_77978_p().func_74779_i(KEY_DISPLAY_NAME + index.toString());
                return displayName;
            }
        }
        return "";
    }

    public BoundType getBoundEntityType(ItemStack itemStack, Integer index) {
        if (itemStack.func_77942_o()) {
            if (itemStack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString())) {
                return BoundType.PLAYER;
            }
            if (itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_DISPLAY_NAME + index.toString())) {
                return BoundType.CREATURE;
            }
        }
        return BoundType.NONE;
    }

    public String getBoundUsername(ItemStack itemStack, Integer index) {
        if (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString())) {
            String playerName = itemStack.func_77978_p().func_74779_i(KEY_PLAYER_NAME + index.toString());
            return playerName;
        }
        return "";
    }

    public UUID getBoundCreatureID(ItemStack itemStack, Integer index) {
        if (itemStack.func_77942_o() && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_DISPLAY_NAME + index.toString())) {
            String displayName = itemStack.func_77978_p().func_74779_i(KEY_DISPLAY_NAME + index.toString());
            UUID entityID = new UUID(itemStack.func_77978_p().func_74763_f(KEY_ENTITY_ID_MOST + index.toString()), itemStack.func_77978_p().func_74763_f(KEY_ENTITY_ID_LEAST + index.toString()));
            return entityID;
        }
        return new UUID(0L, 0L);
    }

    public void addTagLockToPoppet(ItemStack stackTaglockKit, ItemStack stackPoppet, Integer index) {
        assert (stackTaglockKit != null) : "stack of taglock kit cannot be null";
        assert (stackPoppet != null) : "stack poppet cannot be null";
        Integer tagLockIndex = 1;
        if (!stackPoppet.func_77942_o()) {
            stackPoppet.func_77982_d(new NBTTagCompound());
        }
        if (stackTaglockKit.func_77942_o()) {
            if (stackTaglockKit.func_77978_p().func_74764_b(KEY_PLAYER_NAME + tagLockIndex.toString())) {
                String playerName = stackTaglockKit.func_77978_p().func_74779_i(KEY_PLAYER_NAME + tagLockIndex.toString());
                if (playerName != null) {
                    stackPoppet.func_77978_p().func_74778_a(KEY_PLAYER_NAME + index.toString(), playerName);
                }
            } else if (stackTaglockKit.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + tagLockIndex.toString()) && stackTaglockKit.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + tagLockIndex.toString()) && stackTaglockKit.func_77978_p().func_74764_b(KEY_DISPLAY_NAME + tagLockIndex.toString())) {
                stackPoppet.func_77978_p().func_74772_a(KEY_ENTITY_ID_MOST + index.toString(), stackTaglockKit.func_77978_p().func_74763_f(KEY_ENTITY_ID_MOST + tagLockIndex.toString()));
                stackPoppet.func_77978_p().func_74772_a(KEY_ENTITY_ID_LEAST + index.toString(), stackTaglockKit.func_77978_p().func_74763_f(KEY_ENTITY_ID_LEAST + tagLockIndex.toString()));
                stackPoppet.func_77978_p().func_74778_a(KEY_DISPLAY_NAME + index.toString(), stackTaglockKit.func_77978_p().func_74779_i(KEY_DISPLAY_NAME + tagLockIndex.toString()));
            }
        }
    }

    public boolean containsTaglockForEntity(ItemStack itemStack, Entity entity, Integer index) {
        if (itemStack.func_77942_o()) {
            UUID entityID;
            if (entity instanceof EntityPlayer) {
                String playerName;
                EntityPlayer player = (EntityPlayer)entity;
                if (itemStack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString()) && (playerName = itemStack.func_77978_p().func_74779_i(KEY_PLAYER_NAME + index.toString())) != null && playerName.equals(player.func_70005_c_())) {
                    return true;
                }
            } else if (entity instanceof EntityLiving && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && itemStack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString()) && (entityID = new UUID(itemStack.func_77978_p().func_74763_f(KEY_ENTITY_ID_MOST + index.toString()), itemStack.func_77978_p().func_74763_f(KEY_ENTITY_ID_LEAST + index.toString()))).equals(entity.getPersistentID())) {
                return true;
            }
        }
        return false;
    }

    public EntityLivingBase getBoundEntity(World world, Entity entity, ItemStack stack, Integer index) {
        if (stack.func_77942_o()) {
            String playerName;
            if (stack.func_77978_p().func_74764_b(KEY_PLAYER_NAME + index.toString()) && (playerName = stack.func_77978_p().func_74779_i(KEY_PLAYER_NAME + index.toString())) != null && !playerName.isEmpty()) {
                if (!world.field_72995_K) {
                    MinecraftServer server = MinecraftServer.func_71276_C();
                    for (WorldServer worldServer : server.field_71305_c) {
                        for (Object obj : worldServer.field_73010_i) {
                            EntityPlayer player = (EntityPlayer)obj;
                            if (!player.func_70005_c_().equalsIgnoreCase(playerName)) continue;
                            return player;
                        }
                    }
                } else {
                    for (Object obj : world.field_73010_i) {
                        EntityPlayer player = (EntityPlayer)obj;
                        if (!player.func_70005_c_().equalsIgnoreCase(playerName)) continue;
                        return player;
                    }
                }
                return null;
            }
            if (stack.func_77978_p().func_74764_b(KEY_ENTITY_ID_MOST + index.toString()) && stack.func_77978_p().func_74764_b(KEY_ENTITY_ID_LEAST + index.toString())) {
                UUID entityID = new UUID(stack.func_77978_p().func_74763_f(KEY_ENTITY_ID_MOST + index.toString()), stack.func_77978_p().func_74763_f(KEY_ENTITY_ID_LEAST + index.toString()));
                if (!world.field_72995_K) {
                    MinecraftServer server = MinecraftServer.func_71276_C();
                    for (WorldServer worldServer : server.field_71305_c) {
                        for (Object obj : worldServer.field_72996_f) {
                            EntityLivingBase living;
                            UUID livingID;
                            if (!(obj instanceof EntityLivingBase) || !entityID.equals(livingID = (living = (EntityLivingBase)obj).getPersistentID()) || !living.func_70089_S()) continue;
                            return living;
                        }
                    }
                } else {
                    for (Object obj : world.field_72996_f) {
                        EntityLivingBase living;
                        UUID livingID;
                        if (!(obj instanceof EntityLivingBase) || !entityID.equals(livingID = (living = (EntityLivingBase)obj).getPersistentID()) || !living.func_70089_S()) continue;
                        return living;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public static enum BoundType {
        NONE,
        PLAYER,
        CREATURE;

    }

    public static class PlayerComparitor
    implements Comparator<EntityPlayer> {
        @Override
        public int compare(EntityPlayer p1, EntityPlayer p2) {
            return p1.func_70005_c_().compareTo(p2.func_70005_c_());
        }
    }
}

