/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.projectile.EntityLargeFireball
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.dimension;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockDreamCatcher;
import com.emoniph.witchery.blocks.BlockFetish;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldProviderDreamWorld
extends WorldProvider {
    int nightmare = 0;
    private static final String SPIRIT_WORLD_KEY = "WITCSpiritWorld";
    private static final String SPIRIT_WORLD_WALKING_KEY = "WITCSpiritWalking";
    private static final String SPIRIT_WORLD_NIGHTMARE_KEY = "Nightmare";
    private static final String SPIRIT_WORLD_DEMONIC_KEY = "Demonic";
    private static final String SPIRIT_WORLD_OVERWORLD_BODY_KEY = "OverworldBody";
    private static final String SPIRIT_WORLD_OVERWORLD_HEALTH_KEY = "OverworldHealth";
    private static final String SPIRIT_WORLD_SPIRIT_HEALTH_KEY = "SpiritHealth";
    private static final String SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY = "OverworldHunger";
    private static final String SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY = "SpiritHunger";
    private static final String SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY = "OverworldInventory";
    private static final String SPIRIT_WORLD_SPIRIT_INVENTORY_KEY = "SpiritInventory";
    private static final String SPIRIT_WORLD_MANIFEST_GHOST_KEY = "WITCManifested";
    public static final String SPIRIT_WORLD_MANIFEST_TIME_KEY = "WITCManifestDuration";
    public static final String SPIRIT_WORLD_AWAKEN_PLAYER_KEY = "WITCForceAwaken";
    private static final String SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY = "LastNightmareKillTime";
    public static final String SPIRIT_WORLD_MANIFEST_SKIP_TIME_TICK_KEY = "WITCManifestSkipTick";

    public void setDimension(int dim) {
        this.field_76574_g = dim;
        super.setDimension(dim);
    }

    public long getSeed() {
        Long seed = super.getSeed();
        return seed;
    }

    public IChunkProvider func_76555_c() {
        WorldProvider overworldProvider = DimensionManager.getProvider((int)0);
        return overworldProvider.field_76577_b.getChunkGenerator(this.field_76579_a, this.field_76579_a.func_72912_H().func_82571_y());
    }

    public void func_76572_b() {
        super.func_76572_b();
        this.field_76574_g = Config.instance().dimensionDreamID;
    }

    public String getWelcomeMessage() {
        if (this instanceof WorldProviderDreamWorld) {
            return "Entering the " + this.func_80007_l();
        }
        return null;
    }

    public String getDepartMessage() {
        if (this instanceof WorldProviderDreamWorld) {
            return "Leaving the " + this.func_80007_l();
        }
        return null;
    }

    public String func_80007_l() {
        return "Spirit World";
    }

    public float getStarBrightness(float par1) {
        return 0.0f;
    }

    public boolean func_76567_e() {
        return false;
    }

    public double getMovementFactor() {
        return 1.0;
    }

    public float func_76563_a(long par1, float par3) {
        return this.nightmare > 0 ? 0.5f : 1.0f;
    }

    public float func_76571_f() {
        return 0.0f;
    }

    public boolean func_76566_a(int par1, int par2) {
        int var3 = this.field_76579_a.func_72825_h(par1, par2);
        return var3 != -1;
    }

    public ChunkCoordinates func_76554_h() {
        return new ChunkCoordinates(100, 50, 0);
    }

    public int func_76557_i() {
        return 64;
    }

    public double getHorizon() {
        return 0.0;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasVoidParticles(boolean var1) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76561_g() {
        return true;
    }

    public double func_76565_k() {
        return 1.0;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float par1, float par2) {
        float var6;
        float var5;
        float var4;
        float var3 = MathHelper.func_76134_b((float)(par1 * (float)Math.PI * 2.0f)) * 2.0f + 0.5f;
        if (var3 < 0.0f) {
            var3 = 0.0f;
        }
        if (var3 > 1.0f) {
            var3 = 1.0f;
        }
        if (this.nightmare == 0) {
            var4 = 0.8f;
            var5 = 0.2f;
            var6 = 0.6f;
        } else if (this.nightmare == 1) {
            var4 = 0.0f;
            var5 = 1.0f;
            var6 = 0.0f;
        } else {
            var4 = 1.0f;
            var5 = 0.0f;
            var6 = 0.0f;
        }
        return Vec3.func_72443_a((double)(var4 *= var3 * 0.94f + 0.06f), (double)(var5 *= var3 * 0.94f + 0.06f), (double)(var6 *= var3 * 0.91f + 0.09f));
    }

    public void setAllowedSpawnTypes(boolean allowHostile, boolean allowPeaceful) {
        allowPeaceful = true;
    }

    public void updateWeather() {
        if (this.field_76579_a != null && this.field_76579_a.field_73012_v.nextInt(20) == 0) {
            int playerHasNightmare = 0;
            for (Object obj : this.field_76579_a.field_73010_i) {
                EntityPlayer player = (EntityPlayer)obj;
                int level = WorldProviderDreamWorld.getPlayerHasNightmare(player);
                if (level <= playerHasNightmare) continue;
                playerHasNightmare = level;
                break;
            }
            if (this.nightmare != playerHasNightmare) {
                this.nightmare = playerHasNightmare;
            }
        }
        super.updateWeather();
    }

    public boolean isNightmare() {
        return this.nightmare > 0;
    }

    public boolean isDemonicNightmare() {
        return this.nightmare > 1;
    }

    public static int getPlayerHasNightmare(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        return WorldProviderDreamWorld.getPlayerHasNightmare(nbtPlayer);
    }

    public static int getPlayerHasNightmare(NBTTagCompound nbtPlayer) {
        if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
            return 0;
        }
        NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
        boolean nightmare = nbtSpirit.func_74767_n(SPIRIT_WORLD_NIGHTMARE_KEY);
        boolean demonic = nbtSpirit.func_74767_n(SPIRIT_WORLD_DEMONIC_KEY);
        return nightmare && demonic ? 2 : (nightmare ? 1 : 0);
    }

    public static void setPlayerHasNightmare(EntityPlayer player, boolean nightmare, boolean demonic) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        WorldProviderDreamWorld.setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
    }

    public static void setPlayerHasNightmare(NBTTagCompound nbtPlayer, boolean nightmare, boolean demonic) {
        if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
            nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
        }
        NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
        nbtSpirit.func_74757_a(SPIRIT_WORLD_NIGHTMARE_KEY, nightmare);
        nbtSpirit.func_74757_a(SPIRIT_WORLD_DEMONIC_KEY, demonic);
    }

    public static void setPlayerLastNightmareKillNow(EntityPlayer player) {
        if (player != null) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            long time = MinecraftServer.func_130071_aq();
            WorldProviderDreamWorld.setPlayerLastNightmareKill(nbtPlayer, time);
        }
    }

    public static void setPlayerLastNightmareKill(NBTTagCompound nbtPlayer, long time) {
        if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
            nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
        }
        NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
        nbtSpirit.func_74772_a(SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY, time);
    }

    public static long getPlayerLastNightmareKill(NBTTagCompound nbtPlayer) {
        if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
            return 0L;
        }
        NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
        if (!nbtSpirit.func_74764_b(SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY)) {
            return 0L;
        }
        long time = nbtSpirit.func_74763_f(SPIRIT_WORLD_LAST_NIGHTMARE_KILL_KEY);
        return time;
    }

    public static boolean getPlayerIsSpiritWalking(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        return WorldProviderDreamWorld.getPlayerIsSpiritWalking(nbtPlayer);
    }

    public static boolean getPlayerIsSpiritWalking(NBTTagCompound nbtPlayer) {
        boolean walking = nbtPlayer.func_74767_n(SPIRIT_WORLD_WALKING_KEY);
        return walking;
    }

    public static void setPlayerIsSpiritWalking(EntityPlayer player, boolean walking) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        WorldProviderDreamWorld.setPlayerIsSpiritWalking(nbtPlayer, walking);
    }

    public static void setPlayerIsSpiritWalking(NBTTagCompound nbtPlayer, boolean walking) {
        nbtPlayer.func_74757_a(SPIRIT_WORLD_WALKING_KEY, walking);
    }

    private static void addItemToInventory(EntityPlayer player, ItemStack protoStack, int totalQuantity) {
        if (totalQuantity > 0) {
            int quantity;
            int maxStack = protoStack.func_77976_d();
            for (int itemsRemaining = totalQuantity; itemsRemaining > 0; itemsRemaining -= quantity) {
                quantity = itemsRemaining > maxStack ? maxStack : itemsRemaining;
                ItemStack newStack = new ItemStack(protoStack.func_77973_b(), quantity, protoStack.func_77960_j());
                player.field_71071_by.func_70441_a(newStack);
            }
        }
    }

    private static void addItemToInventory(EntityPlayer player, ArrayList<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            if (player.field_71071_by.func_70441_a(stack)) continue;
            player.field_70170_p.func_72838_d((Entity)new EntityItem(player.field_70170_p, player.field_70165_t, 0.5 + player.field_70163_u, player.field_70161_v, stack));
        }
    }

    public static void sendPlayerToSpiritWorld(EntityPlayer player, double nightmareChance) {
        if (player != null && !player.field_70170_p.field_72995_K) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
                nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
            Coord posBody = new Coord((Entity)player);
            posBody.setNBT(nbtSpirit, SPIRIT_WORLD_OVERWORLD_BODY_KEY);
            int fireFound = 0;
            int heartsFound = 0;
            int spiritPoolFound = 0;
            int cottonFound = 0;
            boolean nightmareCatcherFound = false;
            double modifiedNightmareChance = nightmareChance;
            if (nightmareChance > 0.0 && nightmareChance < 1.0) {
                int R = 8;
                int posX = MathHelper.func_76128_c((double)player.field_70165_t);
                int posY = MathHelper.func_76128_c((double)player.field_70163_u);
                int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
                for (int x = posX - 8; x <= posX + 8; ++x) {
                    for (int z = posZ - 8; z <= posZ + 8; ++z) {
                        for (int y = posY - 8; y <= posY + 8; ++y) {
                            Block block = player.field_70170_p.func_147439_a(x, y, z);
                            if (!nightmareCatcherFound && block == Witchery.Blocks.DREAM_CATCHER) {
                                BlockDreamCatcher cfr_ignored_0 = Witchery.Blocks.DREAM_CATCHER;
                                if (BlockDreamCatcher.causesNightmares(player.field_70170_p, x, y, z)) {
                                    modifiedNightmareChance -= 0.5;
                                    nightmareCatcherFound = true;
                                }
                            }
                            if (spiritPoolFound < 3 && block == Witchery.Blocks.FLOWING_SPIRIT && player.field_70170_p.func_72805_g(x, y, z) == 0) {
                                ++spiritPoolFound;
                                modifiedNightmareChance -= 0.1;
                            }
                            if (cottonFound < 2 && block == Witchery.Blocks.WISPY_COTTON) {
                                ++cottonFound;
                                modifiedNightmareChance -= 0.1;
                            }
                            if (heartsFound < 2 && block == Witchery.Blocks.DEMON_HEART) {
                                ++heartsFound;
                                modifiedNightmareChance += 0.35;
                            }
                            if (fireFound >= 3 || block != Blocks.field_150480_ab) continue;
                            ++fireFound;
                            modifiedNightmareChance += 0.1;
                        }
                    }
                }
                modifiedNightmareChance = nightmareCatcherFound ? Math.min(Math.max(modifiedNightmareChance, 0.0), 1.0) : nightmareChance;
            }
            boolean nightmare = modifiedNightmareChance != 0.0 && (modifiedNightmareChance == 1.0 || player.field_70170_p.field_73012_v.nextDouble() < modifiedNightmareChance);
            boolean demonic = nightmare && nightmareCatcherFound && spiritPoolFound > 0 && heartsFound > 0 && player.field_70170_p.field_73012_v.nextDouble() < (double)heartsFound * 0.35 + (double)fireFound * 0.1;
            WorldProviderDreamWorld.setPlayerHasNightmare(nbtPlayer, nightmare, demonic);
            WorldProviderDreamWorld.setPlayerIsSpiritWalking(nbtPlayer, true);
            EntityCorpse corpse = new EntityCorpse(player.field_70170_p);
            corpse.func_70606_j(player.func_110143_aJ());
            corpse.func_94058_c(player.func_70005_c_());
            corpse.setOwner(player.func_70005_c_());
            corpse.func_70012_b(0.5 + (double)MathHelper.func_76128_c((double)player.field_70165_t), player.field_70163_u, 0.5 + (double)MathHelper.func_76128_c((double)player.field_70161_v), 0.0f, 0.0f);
            player.field_70170_p.func_72838_d((Entity)corpse);
            int boneNeedles = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
            int mutandis = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemMutandis.damageValue);
            WorldProviderDreamWorld.dropBetterBackpacks(player);
            NBTTagList nbtOverworldInventory = new NBTTagList();
            player.field_71071_by.func_70442_a(nbtOverworldInventory);
            nbtSpirit.func_74782_a(SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY, (NBTBase)nbtOverworldInventory);
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY)) {
                NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY, 10);
                player.field_71071_by.func_70443_b(nbtSpiritInventory);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY);
            } else {
                player.field_71071_by.func_146027_a(null, -1);
            }
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemMutandis.createStack(), mutandis);
            nbtSpirit.func_74776_a(SPIRIT_WORLD_OVERWORLD_HEALTH_KEY, Math.max(player.func_110143_aJ(), 1.0f));
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_SPIRIT_HEALTH_KEY)) {
                float health = Math.max(nbtSpirit.func_74760_g(SPIRIT_WORLD_SPIRIT_HEALTH_KEY), 10.0f);
                player.func_70606_j(health);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_SPIRIT_HEALTH_KEY);
            }
            NBTTagCompound nbtOverworldFood = new NBTTagCompound();
            player.func_71024_bL().func_75117_b(nbtOverworldFood);
            nbtSpirit.func_74782_a(SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY, (NBTBase)nbtOverworldFood);
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY)) {
                NBTTagCompound nbtSpiritFood = nbtSpirit.func_74775_l(SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY);
                player.func_71024_bL().func_75112_a(nbtSpiritFood);
                player.func_71024_bL().func_75122_a(16, 0.8f);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY);
            }
            WorldProviderDreamWorld.changeDimension(player, Config.instance().dimensionDreamID);
            WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
            Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(0.0, 0.1, 0.0), player);
        }
    }

    private static void dropBetterBackpacks(EntityPlayer player) {
        try {
            Boolean result;
            Method methodGetPackpack;
            Class<?> classItemBackpack = Class.forName("net.mcft.copy.betterstorage.item.ItemBackpack");
            Method[] methods = classItemBackpack.getDeclaredMethods();
            if (Config.instance().isDebugging()) {
                for (Method method : methods) {
                    Log.instance().debug(method.toString());
                }
            }
            if ((methodGetPackpack = classItemBackpack.getMethod("getBackpack", EntityLivingBase.class)) == null) {
                Log.instance().debug("No getBackpack method found");
            } else {
                Log.instance().debug("using method: " + methodGetPackpack.toString());
            }
            ItemStack stackBackpack = (ItemStack)methodGetPackpack.invoke(null, player);
            if (stackBackpack == null) {
                Log.instance().debug("No backpack stack found");
            } else {
                Log.instance().debug("got backpack stack: " + stackBackpack.toString());
            }
            Method methodPlaceBackpack = classItemBackpack.getDeclaredMethod("placeBackpack", EntityLivingBase.class, EntityPlayer.class, ItemStack.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, ForgeDirection.class, Boolean.TYPE, Boolean.TYPE);
            if (methodPlaceBackpack == null) {
                Log.instance().debug("No placebackpack method found");
            } else {
                Log.instance().debug("using method: " + methodPlaceBackpack.toString());
            }
            World w = player.field_70170_p;
            int x = MathHelper.func_76128_c((double)player.field_70165_t);
            int y = MathHelper.func_76128_c((double)player.field_70163_u);
            int z = MathHelper.func_76128_c((double)player.field_70161_v);
            boolean found = true;
            if (WorldProviderDreamWorld.isReplaceable(w, x + 1, y, z)) {
                ++x;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z)) {
                --x;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x, y, z + 1)) {
                ++z;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z - 1)) {
                --z;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x + 1, y, z + 1)) {
                ++x;
                ++z;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z + 1)) {
                --x;
                ++z;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x + 1, y, z - 1)) {
                ++x;
                --z;
            } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z - 1)) {
                --x;
                --z;
            } else {
                found = false;
            }
            if (found) {
                if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
                    w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
                }
            } else {
                found = true;
                if (WorldProviderDreamWorld.isReplaceable(w, x + 1, ++y, z)) {
                    ++x;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z)) {
                    --x;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x, y, z + 1)) {
                    ++z;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z - 1)) {
                    --z;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x + 1, y, z + 1)) {
                    ++x;
                    ++z;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z + 1)) {
                    --x;
                    ++z;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x + 1, y, z - 1)) {
                    ++x;
                    --z;
                } else if (WorldProviderDreamWorld.isReplaceable(w, x - 1, y, z - 1)) {
                    --x;
                    --z;
                } else {
                    found = false;
                }
                if (!found) {
                    w.func_147468_f(++x, ++y, z);
                    if (!w.func_147439_a(x, y - 1, z).func_149662_c()) {
                        w.func_147449_b(x, y - 1, z, Blocks.field_150348_b);
                    }
                }
            }
            if ((result = (Boolean)methodPlaceBackpack.invoke(null, player, player, stackBackpack, x, y, z, 1, ForgeDirection.NORTH, false, false)).equals(Boolean.FALSE)) {
                Log.instance().debug("Backpack could not be placed");
            } else {
                Method methodSetBackpack = classItemBackpack.getDeclaredMethod("setBackpack", EntityLivingBase.class, ItemStack.class, ItemStack[].class);
                if (methodSetBackpack == null) {
                    Log.instance().debug("No setBackpack method found");
                } else {
                    Log.instance().debug("using method: " + methodPlaceBackpack.toString());
                }
                methodSetBackpack.invoke(null, player, null, null);
            }
        }
        catch (ClassNotFoundException ex) {
            Log.instance().debug("No class found for ItemBackpack");
        }
        catch (NoSuchMethodException ex) {
            Log.instance().debug("No onPlaceBackpack method found: " + ex.toString());
        }
        catch (InvocationTargetException ex) {
            Log.instance().debug("No onPlaceBackpack target found");
        }
        catch (IllegalAccessException ex) {
            Log.instance().debug("No onPlaceBackpack method access allowed");
        }
        catch (IndexOutOfBoundsException ex) {
            Log.instance().debug("No onPlaceBackpack method index");
        }
        catch (Throwable ex) {
            Log.instance().debug("Unexpected onPlaceBackpack error: " + ex.toString());
        }
    }

    private static boolean isReplaceable(World world, int x, int y, int z) {
        Material m = world.func_147439_a(x, y, z).func_149688_o();
        if (m == null) {
            return false;
        }
        return m.func_76222_j();
    }

    public static void changeDimension(EntityPlayer player, int dimension) {
        WorldProviderDreamWorld.dismountEntity(player);
        ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
        ItemGeneral.travelToDimension(player, dimension);
    }

    private static void dismountEntity(EntityPlayer player) {
        if (player.func_70115_ae()) {
            player.func_70078_a(null);
        }
    }

    public static void findTopAndSetPosition(World world, EntityPlayer player) {
        WorldProviderDreamWorld.findTopAndSetPosition(world, player, player.field_70165_t, player.field_70163_u, player.field_70161_v);
    }

    private static void findTopAndSetPosition(World world, EntityPlayer player, double posX, double posY, double posZ) {
        int z;
        int y;
        int x = MathHelper.func_76128_c((double)posX);
        if (!WorldProviderDreamWorld.isValidSpawnPoint(world, x, y = MathHelper.func_76128_c((double)posY), z = MathHelper.func_76128_c((double)posZ))) {
            for (int i = 1; i <= 256; ++i) {
                int yPlus = y + i;
                int yMinus = y - i;
                if (yPlus < 256 && WorldProviderDreamWorld.isValidSpawnPoint(world, x, yPlus, z)) {
                    y = yPlus;
                    break;
                }
                if (yMinus > 2 && WorldProviderDreamWorld.isValidSpawnPoint(world, x, yMinus, z)) {
                    y = yMinus;
                    break;
                }
                if (yMinus <= 2 && yPlus >= 255) break;
            }
        }
        player.func_70634_a(0.5 + (double)x, 0.1 + (double)y, 0.5 + (double)z);
    }

    private static boolean isValidSpawnPoint(World world, int x, int y, int z) {
        Material materialBelow = world.func_147439_a(x, y - 1, z).func_149688_o();
        return !world.func_147437_c(x, y - 1, z) && materialBelow != Material.field_151587_i && world.func_147437_c(x, y, z) && world.func_147437_c(x, y + 1, z);
    }

    public static void returnPlayerToOverworld(EntityPlayer player) {
        if (player != null && !player.field_70170_p.field_72995_K) {
            NBTTagCompound nbtPlayer;
            if (player.field_71093_bK != Config.instance().dimensionDreamID) {
                Log.instance().warning("Player " + player.getDisplayName() + " is in incorrect dimension when returning frmo spirit world, dimension=" + player.field_71093_bK);
            }
            if (!(nbtPlayer = Infusion.getNBT((Entity)player)).func_74764_b(SPIRIT_WORLD_KEY)) {
                nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
            boolean isSpiritWorld = player.field_71093_bK == Config.instance().dimensionDreamID;
            int cottonRemoved = isSpiritWorld ? player.field_71071_by.func_146027_a(Item.func_150898_a((Block)Witchery.Blocks.WISPY_COTTON), 0) : 0;
            int disturbedCottonRemoved = isSpiritWorld ? player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemDisturbedCotton.damageValue) : 0;
            int hunger = isSpiritWorld ? player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemMellifluousHunger.damageValue) : 0;
            int spirit = isSpiritWorld ? player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.damageValue) : 0;
            int subduedSpirits = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemSubduedSpirit.damageValue);
            int boneNeedles = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
            WorldProviderDreamWorld.dropBetterBackpacks(player);
            if (player.field_71093_bK == Config.instance().dimensionDreamID) {
                NBTTagList nbtSpiritInventory = new NBTTagList();
                player.field_71071_by.func_70442_a(nbtSpiritInventory);
                nbtSpirit.func_74782_a(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY, (NBTBase)nbtSpiritInventory);
            }
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY)) {
                NBTTagList nbtOverworldInventory = nbtSpirit.func_150295_c(SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY, 10);
                player.field_71071_by.func_70443_b(nbtOverworldInventory);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_OVERWORLD_INVENTORY_KEY);
            } else {
                player.field_71071_by.func_146027_a(null, -1);
            }
            WorldProviderDreamWorld.addItemToInventory(player, new ItemStack(Witchery.Blocks.WISPY_COTTON, 1, 0), cottonRemoved);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemDisturbedCotton.createStack(), disturbedCottonRemoved);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemMellifluousHunger.createStack(), hunger);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemBrewOfFlowingSpirit.createStack(), spirit);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemSubduedSpirit.createStack(), subduedSpirits);
            nbtSpirit.func_74776_a(SPIRIT_WORLD_SPIRIT_HEALTH_KEY, Math.max(player.func_110143_aJ(), 10.0f));
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_OVERWORLD_HEALTH_KEY)) {
                float health = nbtSpirit.func_74760_g(SPIRIT_WORLD_OVERWORLD_HEALTH_KEY);
                player.func_70606_j(health);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_OVERWORLD_HEALTH_KEY);
            }
            NBTTagCompound nbtSpiritFood = new NBTTagCompound();
            player.func_71024_bL().func_75117_b(nbtSpiritFood);
            nbtSpirit.func_74782_a(SPIRIT_WORLD_SPIRIT_HUNGER_FOOD_KEY, (NBTBase)nbtSpiritFood);
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY)) {
                NBTTagCompound nbtOverworldFood = nbtSpirit.func_74775_l(SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY);
                player.func_71024_bL().func_75112_a(nbtOverworldFood);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_OVERWORLD_HUNGER_FOOD_KEY);
            }
            WorldProviderDreamWorld.setPlayerHasNightmare(nbtPlayer, false, false);
            WorldProviderDreamWorld.setPlayerIsGhost(nbtPlayer, false);
            WorldProviderDreamWorld.setPlayerIsSpiritWalking(nbtPlayer, false);
            player.func_70066_B();
            Coord posBody = Coord.createFrom(nbtSpirit, SPIRIT_WORLD_OVERWORLD_BODY_KEY);
            if (player.field_71093_bK != 0) {
                if (posBody != null) {
                    WorldProviderDreamWorld.dismountEntity(player);
                    player.func_70634_a((double)posBody.x, (double)posBody.y, (double)posBody.z);
                }
                WorldProviderDreamWorld.changeDimension(player, 0);
            }
            World world = player.field_70170_p;
            if (posBody != null) {
                WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player, posBody.x, posBody.y, posBody.z);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_OVERWORLD_BODY_KEY);
            } else {
                WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
            }
            for (Object obj : player.field_70170_p.field_72996_f) {
                EntityCorpse corpse;
                String owner;
                if (!(obj instanceof EntityCorpse) || (owner = (corpse = (EntityCorpse)((Object)obj)).getOwnerName()) == null || !owner.equalsIgnoreCase(player.func_70005_c_())) continue;
                player.field_70170_p.func_72900_e((Entity)corpse);
            }
            Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(0.0, 0.1, 0.0), player);
        }
    }

    public static void manifestPlayerInOverworldAsGhost(EntityPlayer player) {
        if (player != null && !player.field_70170_p.field_72995_K) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
                nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
            int boneNeedles = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
            WorldProviderDreamWorld.dropBetterBackpacks(player);
            NBTTagList nbtSpiritInventory = new NBTTagList();
            player.field_71071_by.func_70442_a(nbtSpiritInventory);
            nbtSpirit.func_74782_a(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY, (NBTBase)nbtSpiritInventory);
            player.field_71071_by.func_146027_a(null, -1);
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
            nbtSpirit.func_74776_a(SPIRIT_WORLD_SPIRIT_HEALTH_KEY, Math.max(player.func_110143_aJ(), 1.0f));
            WorldProviderDreamWorld.setPlayerIsGhost(nbtPlayer, true);
            WorldProviderDreamWorld.changeDimension(player, 0);
            WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
            Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
        }
    }

    public static void returnGhostPlayerToSpiritWorld(EntityPlayer player) {
        if (player != null && !player.field_70170_p.field_72995_K) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            if (!nbtPlayer.func_74764_b(SPIRIT_WORLD_KEY)) {
                nbtPlayer.func_74782_a(SPIRIT_WORLD_KEY, (NBTBase)new NBTTagCompound());
            }
            NBTTagCompound nbtSpirit = nbtPlayer.func_74775_l(SPIRIT_WORLD_KEY);
            int boneNeedles = player.field_71071_by.func_146027_a((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemIcyNeedle.damageValue);
            ArrayList<ItemStack> fetishes = WorldProviderDreamWorld.getBoundFetishes(player.field_71071_by);
            player.field_71071_by.func_70436_m();
            WorldProviderDreamWorld.dropBetterBackpacks(player);
            if (nbtSpirit.func_74764_b(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY)) {
                NBTTagList nbtSpiritInventory = nbtSpirit.func_150295_c(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY, 10);
                player.field_71071_by.func_70443_b(nbtSpiritInventory);
                nbtSpirit.func_82580_o(SPIRIT_WORLD_SPIRIT_INVENTORY_KEY);
            }
            WorldProviderDreamWorld.addItemToInventory(player, Witchery.Items.GENERIC.itemIcyNeedle.createStack(), boneNeedles);
            WorldProviderDreamWorld.addItemToInventory(player, fetishes);
            WorldProviderDreamWorld.setPlayerIsGhost(nbtPlayer, false);
            WorldProviderDreamWorld.changeDimension(player, Config.instance().dimensionDreamID);
            WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
            Witchery.packetPipeline.sendToAll(new PacketPlayerStyle(player));
        }
    }

    private static ArrayList<ItemStack> getBoundFetishes(InventoryPlayer inventory) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            ItemStack stack = inventory.func_70301_a(i);
            if (stack == null || !(stack.func_77973_b() instanceof BlockFetish.ClassItemBlock) || InfusedSpiritEffect.getEffectID(stack) <= 0) continue;
            stacks.add(stack);
        }
        return stacks;
    }

    public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
        if (!world.field_72995_K) {
            boolean mustAwaken;
            boolean done = false;
            if (counter % 20L == 0L && (mustAwaken = WorldProviderDreamWorld.getPlayerMustAwaken(nbtPlayer))) {
                WorldProviderDreamWorld.setPlayerMustAwaken(nbtPlayer, false);
                if (player.field_71093_bK != Config.instance().dimensionDreamID && WorldProviderDreamWorld.getPlayerIsSpiritWalking(player) && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
                    WorldProviderDreamWorld.returnPlayerToOverworld(player);
                } else if (player.field_71093_bK == Config.instance().dimensionDreamID) {
                    WorldProviderDreamWorld.returnPlayerToOverworld(player);
                }
            }
            if (!done && counter % 100L == 0L) {
                int nightmareLevel = WorldProviderDreamWorld.getPlayerHasNightmare(nbtPlayer);
                if (player.field_71093_bK == Config.instance().dimensionDreamID && nightmareLevel > 0) {
                    double R = 18.0;
                    double H = 18.0;
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 18.0), (double)(player.field_70163_u - 18.0), (double)(player.field_70161_v - 18.0), (double)(player.field_70165_t + 18.0), (double)(player.field_70163_u + 18.0), (double)(player.field_70161_v + 18.0));
                    if (nightmareLevel > 1) {
                        List entities;
                        EntityLargeFireball fireball;
                        double chance = world.field_73012_v.nextDouble();
                        if (chance < 0.5) {
                            fireball = new EntitySmallFireball(world, player.field_70165_t - 2.0 + (double)world.field_73012_v.nextInt(5), player.field_70163_u + 15.0, player.field_70161_v - 2.0 + (double)world.field_73012_v.nextInt(5), 0.0, -0.2, 0.0);
                            world.func_72838_d((Entity)fireball);
                        } else if (chance < 0.65) {
                            fireball = new EntityLargeFireball(world);
                            double par2 = player.field_70165_t - 2.0 + (double)world.field_73012_v.nextInt(5);
                            double par4 = player.field_70163_u + 15.0;
                            double par6 = player.field_70161_v - 2.0 + (double)world.field_73012_v.nextInt(5);
                            double par8 = 0.0;
                            double par10 = -0.2;
                            double par12 = 0.0;
                            fireball.func_70012_b(par2, par4, par6, fireball.field_70177_z, fireball.field_70125_A);
                            fireball.func_70107_b(par2, par4, par6);
                            double d6 = MathHelper.func_76133_a((double)(par8 * par8 + par10 * par10 + par12 * par12));
                            fireball.field_70232_b = par8 / d6 * 0.1;
                            fireball.field_70233_c = par10 / d6 * 0.1;
                            fireball.field_70230_d = par12 / d6 * 0.1;
                            world.func_72838_d((Entity)fireball);
                        } else if (chance < 0.75 && (entities = world.func_72872_a(EntityMob.class, bounds)).size() < 10 && !WorldProviderDreamWorld.containsDemons(entities, 2)) {
                            EntityDemon blaze = new EntityDemon(world);
                            Infusion.spawnCreature(world, EntityDemon.class, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), (EntityLivingBase)player, 4, 8, ParticleEffect.SMOKE, SoundEffect.MOB_WITHER_DEATH);
                        }
                    }
                    List entities = world.func_72872_a(EntityNightmare.class, bounds);
                    for (Object obj : entities) {
                        EntityNightmare nightmare = (EntityNightmare)((Object)obj);
                        if (!nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) continue;
                        return;
                    }
                    long currentTime = MinecraftServer.func_130071_aq();
                    long lastKillTime = WorldProviderDreamWorld.getPlayerLastNightmareKill(nbtPlayer);
                    if (lastKillTime < currentTime - 30000L) {
                        Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), player, 2, 6);
                    }
                } else if (player.field_71093_bK != Config.instance().dimensionDreamID && WorldProviderDreamWorld.getPlayerIsGhost(nbtPlayer)) {
                    int timeRemaining = 0;
                    boolean skipNext = WorldProviderDreamWorld.getPlayerSkipNextManifestTick(nbtPlayer);
                    if (nbtPlayer.func_74764_b(SPIRIT_WORLD_MANIFEST_TIME_KEY)) {
                        timeRemaining = nbtPlayer.func_74762_e(SPIRIT_WORLD_MANIFEST_TIME_KEY);
                        if (((timeRemaining = Math.max(0, timeRemaining - 5)) >= 60 && timeRemaining <= 64 || timeRemaining >= 30 && timeRemaining <= 34 || timeRemaining >= 15 && timeRemaining <= 19) && !skipNext) {
                            ChatUtil.sendTranslated(EnumChatFormatting.LIGHT_PURPLE, (ICommandSender)player, "witchery.rite.manifestation.countdown", Integer.valueOf(timeRemaining).toString());
                        }
                    }
                    if (timeRemaining == 0) {
                        if (nbtPlayer.func_74764_b(SPIRIT_WORLD_MANIFEST_TIME_KEY)) {
                            nbtPlayer.func_82580_o(SPIRIT_WORLD_MANIFEST_TIME_KEY);
                        }
                        WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
                    } else if (!skipNext) {
                        nbtPlayer.func_74768_a(SPIRIT_WORLD_MANIFEST_TIME_KEY, timeRemaining);
                    } else {
                        WorldProviderDreamWorld.setPlayerSkipNextManifestationReduction(nbtPlayer, false);
                    }
                }
            }
        }
    }

    public static void setPlayerSkipNextManifestationReduction(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        WorldProviderDreamWorld.setPlayerSkipNextManifestationReduction(nbtPlayer, true);
    }

    public static void setPlayerSkipNextManifestationReduction(NBTTagCompound nbtPlayer, boolean skip) {
        nbtPlayer.func_74757_a(SPIRIT_WORLD_MANIFEST_SKIP_TIME_TICK_KEY, skip);
    }

    public static boolean getPlayerSkipNextManifestTick(NBTTagCompound nbtPlayer) {
        return nbtPlayer.func_74767_n(SPIRIT_WORLD_MANIFEST_SKIP_TIME_TICK_KEY);
    }

    private static boolean containsDemons(List entities, int max) {
        int count = 0;
        for (Object obj : entities) {
            if (!(obj instanceof EntityDemon) || ++count < max) continue;
            return true;
        }
        return false;
    }

    public static void setPlayerIsGhost(NBTTagCompound nbtPlayer, boolean ghost) {
        nbtPlayer.func_74757_a(SPIRIT_WORLD_MANIFEST_GHOST_KEY, ghost);
    }

    public static boolean getPlayerIsGhost(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        return WorldProviderDreamWorld.getPlayerIsGhost(nbtPlayer);
    }

    public static boolean getPlayerIsGhost(NBTTagCompound nbtPlayer) {
        return nbtPlayer.func_74767_n(SPIRIT_WORLD_MANIFEST_GHOST_KEY);
    }

    public static void setPlayerMustAwaken(EntityPlayer player, boolean awaken) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        WorldProviderDreamWorld.setPlayerMustAwaken(nbtPlayer, awaken);
    }

    public static void setPlayerMustAwaken(NBTTagCompound nbtPlayer, boolean ghost) {
        nbtPlayer.func_74757_a(SPIRIT_WORLD_AWAKEN_PLAYER_KEY, ghost);
    }

    public static boolean getPlayerMustAwaken(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        return WorldProviderDreamWorld.getPlayerMustAwaken(nbtPlayer);
    }

    public static boolean getPlayerMustAwaken(NBTTagCompound nbtPlayer) {
        return nbtPlayer.func_74767_n(SPIRIT_WORLD_AWAKEN_PLAYER_KEY);
    }

    public static boolean canPlayerManifest(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        int timeRemaining = 0;
        if (nbtPlayer != null && nbtPlayer.func_74764_b(SPIRIT_WORLD_MANIFEST_TIME_KEY)) {
            timeRemaining = nbtPlayer.func_74762_e(SPIRIT_WORLD_MANIFEST_TIME_KEY);
        }
        return timeRemaining >= 5;
    }
}

