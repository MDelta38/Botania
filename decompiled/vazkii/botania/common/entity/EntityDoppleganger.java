/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntityBeacon
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.FakePlayer
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.entity;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityMagicLandmine;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

public class EntityDoppleganger
extends EntityCreature
implements IBotaniaBossWithShader {
    public static final int SPAWN_TICKS = 160;
    private static final float RANGE = 12.0f;
    private static final float MAX_HP = 800.0f;
    public static final int MOB_SPAWN_START_TICKS = 20;
    public static final int MOB_SPAWN_END_TICKS = 80;
    public static final int MOB_SPAWN_BASE_TICKS = 800;
    public static final int MOB_SPAWN_TICKS = 900;
    public static final int MOB_SPAWN_WAVES = 10;
    public static final int MOB_SPAWN_WAVE_TIME = 80;
    private static final String TAG_INVUL_TIME = "invulTime";
    private static final String TAG_AGGRO = "aggro";
    private static final String TAG_SOURCE_X = "sourceX";
    private static final String TAG_SOURCE_Y = "sourceY";
    private static final String TAG_SOURCE_Z = "sourcesZ";
    private static final String TAG_MOB_SPAWN_TICKS = "mobSpawnTicks";
    private static final String TAG_HARD_MODE = "hardMode";
    private static final String TAG_PLAYER_COUNT = "playerCount";
    private static final int[][] PYLON_LOCATIONS = new int[][]{{4, 1, 4}, {4, 1, -4}, {-4, 1, 4}, {-4, 1, -4}};
    private static final List<String> CHEATY_BLOCKS = Arrays.asList("OpenBlocks:beartrap", "ThaumicTinkerer:magnet");
    boolean spawnLandmines = false;
    boolean spawnPixies = false;
    boolean anyWithArmor = false;
    List<String> playersWhoAttacked = new ArrayList<String>();
    private static boolean isPlayingMusic = false;
    private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
    @SideOnly(value=Side.CLIENT)
    private static Rectangle barRect;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle hpBarRect;
    @SideOnly(value=Side.CLIENT)
    private ShaderCallback shaderCallback;

    public EntityDoppleganger(World par1World) {
        super(par1World);
        this.func_70105_a(0.6f, 1.8f);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, Float.MAX_VALUE));
        this.field_70178_ae = true;
        this.field_70728_aV = 825;
    }

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        for (int[] p : PYLON_LOCATIONS) {
            mb.addComponent(p[0], p[1] + 1, p[2], ModBlocks.pylon, 2);
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                mb.addComponent(new BeaconComponent(new ChunkCoordinates(i - 1, 0, j - 1)));
            }
        }
        mb.addComponent(new BeaconBeamComponent(new ChunkCoordinates(0, 1, 0)));
        mb.setRenderOffset(0, -1, 0);
        return mb.makeSet();
    }

    public static boolean spawn(EntityPlayer player, ItemStack par1ItemStack, World par3World, int par4, int par5, int par6, boolean hard) {
        if (par3World.func_147438_o(par4, par5, par6) instanceof TileEntityBeacon && EntityDoppleganger.isTruePlayer((Entity)player)) {
            if (par3World.field_73013_u == EnumDifficulty.PEACEFUL) {
                if (!par3World.field_72995_K) {
                    player.func_145747_a(new ChatComponentTranslation("botaniamisc.peacefulNoob", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
                }
                return false;
            }
            for (int[] coords : PYLON_LOCATIONS) {
                int x = par4 + coords[0];
                int y = par5 + coords[1];
                int z = par6 + coords[2];
                Block blockat = par3World.func_147439_a(x, y, z);
                int meta = par3World.func_72805_g(x, y, z);
                if (blockat == ModBlocks.pylon && meta == 2) continue;
                if (!par3World.field_72995_K) {
                    player.func_145747_a(new ChatComponentTranslation("botaniamisc.needsCatalysts", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
                }
                return false;
            }
            if (!EntityDoppleganger.hasProperArena(par3World, par4, par5, par6)) {
                for (int i = 0; i < 360; i += 8) {
                    float r = 1.0f;
                    float g = 0.0f;
                    float b = 1.0f;
                    float rad = (float)i * (float)Math.PI / 180.0f;
                    double x = (double)par4 + 0.5 - Math.cos(rad) * 12.0;
                    double y = (double)par5 + 0.5;
                    double z = (double)par6 + 0.5 - Math.sin(rad) * 12.0;
                    Botania.proxy.sparkleFX(par3World, x, y, z, r, g, b, 5.0f, 120);
                }
                if (!par3World.field_72995_K) {
                    player.func_145747_a(new ChatComponentTranslation("botaniamisc.badArena", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
                }
                return false;
            }
            --par1ItemStack.field_77994_a;
            if (par3World.field_72995_K) {
                return true;
            }
            EntityDoppleganger e = new EntityDoppleganger(par3World);
            e.func_70107_b((double)par4 + 0.5, par5 + 3, (double)par6 + 0.5);
            e.setInvulTime(160);
            e.func_70606_j(1.0f);
            e.setSource(par4, par5, par6);
            e.setMobSpawnTicks(900);
            e.setHardMode(hard);
            List<EntityPlayer> players = e.getPlayersAround();
            int playerCount = 0;
            for (EntityPlayer p : players) {
                if (!EntityDoppleganger.isTruePlayer((Entity)p)) continue;
                ++playerCount;
            }
            e.setPlayerCount(playerCount);
            e.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(800.0f * (float)playerCount));
            par3World.func_72956_a((Entity)e, "mob.enderdragon.growl", 10.0f, 0.1f);
            par3World.func_72838_d((Entity)e);
            return true;
        }
        return false;
    }

    private static boolean hasProperArena(World world, int sx, int sy, int sz) {
        int heightCheck = 3;
        int heightMin = 2;
        int range = (int)Math.ceil(12.0);
        for (int i = -range; i < range + 1; ++i) {
            block1: for (int j = -range; j < range + 1; ++j) {
                if (Math.abs(i) == 4 && Math.abs(j) == 4 || vazkii.botania.common.core.helper.MathHelper.pointDistancePlane(i, j, 0.0, 0.0) > 12.0f) continue;
                int x = sx + i;
                int z = sz + j;
                int air = 0;
                for (int k = heightCheck + heightMin + 1; k >= -heightCheck; --k) {
                    boolean isAir;
                    int y = sy + k;
                    boolean bl = isAir = world.func_147439_a(x, y, z).func_149668_a(world, x, y, z) == null;
                    if (isAir) {
                        ++air;
                        continue;
                    }
                    if (k > heightCheck) continue;
                    if (air > 2) continue block1;
                    air = 0;
                }
                return false;
            }
        }
        return true;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(20, (Object)0);
        this.field_70180_af.func_75682_a(21, (Object)0);
        this.field_70180_af.func_75682_a(22, (Object)0);
        this.field_70180_af.func_75682_a(23, (Object)0);
        this.field_70180_af.func_75682_a(24, (Object)0);
        this.field_70180_af.func_75682_a(25, (Object)0);
        this.field_70180_af.func_75682_a(26, (Object)0);
        this.field_70180_af.func_75682_a(27, (Object)0);
        this.field_70180_af.func_75682_a(28, (Object)0);
    }

    public int getInvulTime() {
        return this.field_70180_af.func_75679_c(20);
    }

    public boolean isAggored() {
        return this.field_70180_af.func_75683_a(21) == 1;
    }

    public int getTPDelay() {
        return this.field_70180_af.func_75679_c(22);
    }

    public ChunkCoordinates getSource() {
        int x = this.field_70180_af.func_75679_c(23);
        int y = this.field_70180_af.func_75679_c(24);
        int z = this.field_70180_af.func_75679_c(25);
        return new ChunkCoordinates(x, y, z);
    }

    public int getMobSpawnTicks() {
        return this.field_70180_af.func_75679_c(26);
    }

    public boolean isHardMode() {
        return this.field_70180_af.func_75683_a(27) == 1;
    }

    public int getPlayerCount() {
        return this.field_70180_af.func_75679_c(28);
    }

    public void setInvulTime(int time) {
        this.field_70180_af.func_75692_b(20, (Object)time);
    }

    public void setAggroed(boolean aggored) {
        this.field_70180_af.func_75692_b(21, (Object)((byte)(aggored ? 1 : 0)));
    }

    public void setTPDelay(int delay) {
        this.field_70180_af.func_75692_b(22, (Object)delay);
    }

    public void setSource(int x, int y, int z) {
        this.field_70180_af.func_75692_b(23, (Object)x);
        this.field_70180_af.func_75692_b(24, (Object)y);
        this.field_70180_af.func_75692_b(25, (Object)z);
    }

    public void setMobSpawnTicks(int ticks) {
        this.field_70180_af.func_75692_b(26, (Object)ticks);
    }

    public void setHardMode(boolean hardMode) {
        this.field_70180_af.func_75692_b(27, (Object)((byte)(hardMode ? 1 : 0)));
    }

    public void setPlayerCount(int count) {
        this.field_70180_af.func_75692_b(28, (Object)count);
    }

    public void func_70014_b(NBTTagCompound par1nbtTagCompound) {
        super.func_70014_b(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_INVUL_TIME, this.getInvulTime());
        par1nbtTagCompound.func_74757_a(TAG_AGGRO, this.isAggored());
        par1nbtTagCompound.func_74768_a(TAG_MOB_SPAWN_TICKS, this.getMobSpawnTicks());
        ChunkCoordinates source = this.getSource();
        par1nbtTagCompound.func_74768_a(TAG_SOURCE_X, source.field_71574_a);
        par1nbtTagCompound.func_74768_a(TAG_SOURCE_Y, source.field_71572_b);
        par1nbtTagCompound.func_74768_a(TAG_SOURCE_Z, source.field_71573_c);
        par1nbtTagCompound.func_74757_a(TAG_HARD_MODE, this.isHardMode());
        par1nbtTagCompound.func_74768_a(TAG_PLAYER_COUNT, this.getPlayerCount());
    }

    public void func_70037_a(NBTTagCompound par1nbtTagCompound) {
        super.func_70037_a(par1nbtTagCompound);
        this.setInvulTime(par1nbtTagCompound.func_74762_e(TAG_INVUL_TIME));
        this.setAggroed(par1nbtTagCompound.func_74767_n(TAG_AGGRO));
        this.setMobSpawnTicks(par1nbtTagCompound.func_74762_e(TAG_MOB_SPAWN_TICKS));
        int x = par1nbtTagCompound.func_74762_e(TAG_SOURCE_X);
        int y = par1nbtTagCompound.func_74762_e(TAG_SOURCE_Y);
        int z = par1nbtTagCompound.func_74762_e(TAG_SOURCE_Z);
        this.setSource(x, y, z);
        this.setHardMode(par1nbtTagCompound.func_74767_n(TAG_HARD_MODE));
        if (par1nbtTagCompound.func_74764_b(TAG_PLAYER_COUNT)) {
            this.setPlayerCount(par1nbtTagCompound.func_74762_e(TAG_PLAYER_COUNT));
        } else {
            this.setPlayerCount(1);
        }
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.func_76346_g();
        if ((par1DamageSource.field_76373_n.equals("player") || e instanceof EntityPixie) && e != null && EntityDoppleganger.isTruePlayer(e) && this.getInvulTime() == 0) {
            EntityPlayer player = (EntityPlayer)e;
            if (!this.playersWhoAttacked.contains(player.func_70005_c_())) {
                this.playersWhoAttacked.add(player.func_70005_c_());
            }
            float dmg = par2;
            boolean crit = false;
            if (e instanceof EntityPlayer) {
                EntityPlayer p = (EntityPlayer)e;
                crit = p.field_70143_R > 0.0f && !p.field_70122_E && !p.func_70617_f_() && !p.func_70090_H() && !p.func_70644_a(Potion.field_76440_q) && p.field_70154_o == null;
            }
            int cap = crit ? 60 : 40;
            return super.func_70097_a(par1DamageSource, Math.min((float)cap, dmg) * (this.isHardMode() ? 0.6f : 1.0f));
        }
        return false;
    }

    public static boolean isTruePlayer(Entity e) {
        if (!(e instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer)e;
        String name = player.func_70005_c_();
        return !(player instanceof FakePlayer) && !FAKE_PLAYER_PATTERN.matcher(name).matches();
    }

    protected void func_70665_d(DamageSource par1DamageSource, float par2) {
        super.func_70665_d(par1DamageSource, par2);
        Entity attacker = par1DamageSource.func_76346_g();
        if (attacker != null) {
            Vector3 thisVector = Vector3.fromEntityCenter((Entity)this);
            Vector3 playerVector = Vector3.fromEntityCenter(attacker);
            Vector3 motionVector = thisVector.copy().sub(playerVector).copy().normalize().multiply(0.75);
            if (this.func_110143_aJ() > 0.0f) {
                this.field_70159_w = -motionVector.x;
                this.field_70181_x = 0.5;
                this.field_70179_y = -motionVector.z;
                this.setTPDelay(4);
                this.spawnPixies = this.isAggored();
            }
            this.setAggroed(true);
        }
    }

    public void func_70645_a(DamageSource p_70645_1_) {
        super.func_70645_a(p_70645_1_);
        EntityLivingBase entitylivingbase = this.func_94060_bK();
        if (entitylivingbase instanceof EntityPlayer) {
            ((EntityPlayer)entitylivingbase).func_71064_a((StatBase)ModAchievements.gaiaGuardianKill, 1);
            if (!this.anyWithArmor) {
                ((EntityPlayer)entitylivingbase).func_71064_a((StatBase)ModAchievements.gaiaGuardianNoArmor, 1);
            }
        }
        this.field_70170_p.func_72956_a((Entity)this, "random.explode", 20.0f, (1.0f + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
        this.field_70170_p.func_72869_a("hugeexplosion", this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0, 0.0, 0.0);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(800.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (par1) {
            for (int pl = 0; pl < this.playersWhoAttacked.size(); ++pl) {
                boolean hard = this.isHardMode();
                this.func_70099_a(new ItemStack(ModItems.manaResource, pl == 0 ? (hard ? 16 : 8) : (hard ? 10 : 6), 5), 1.0f);
                boolean droppedRecord = false;
                if (hard) {
                    int i;
                    this.func_70099_a(new ItemStack(ModItems.ancientWill, 1, this.field_70146_Z.nextInt(6)), 1.0f);
                    if (ConfigHandler.relicsEnabled) {
                        ItemStack dice = new ItemStack(ModItems.dice);
                        ItemRelic.bindToUsernameS(this.playersWhoAttacked.get(pl), dice);
                        this.func_70099_a(dice, 1.0f);
                    }
                    if (Math.random() < 0.25) {
                        this.func_70099_a(new ItemStack(ModItems.overgrowthSeed, this.field_70146_Z.nextInt(3) + 1), 1.0f);
                    }
                    if (Math.random() < 0.5) {
                        boolean voidLotus = Math.random() < (double)0.3f;
                        this.func_70099_a(new ItemStack(ModItems.blackLotus, voidLotus ? 1 : this.field_70146_Z.nextInt(3) + 1, voidLotus ? 1 : 0), 1.0f);
                    }
                    if (Math.random() < 0.9) {
                        this.func_70099_a(new ItemStack(ModItems.manaResource, 16 + this.field_70146_Z.nextInt(12)), 1.0f);
                    }
                    if (Math.random() < 0.7) {
                        this.func_70099_a(new ItemStack(ModItems.manaResource, 8 + this.field_70146_Z.nextInt(6), 1), 1.0f);
                    }
                    if (Math.random() < 0.5) {
                        this.func_70099_a(new ItemStack(ModItems.manaResource, 4 + this.field_70146_Z.nextInt(3), 2), 1.0f);
                    }
                    int runes = this.field_70146_Z.nextInt(6) + 1;
                    for (i = 0; i < runes; ++i) {
                        if (!(Math.random() < 0.3)) continue;
                        this.func_70099_a(new ItemStack(ModItems.rune, 2 + this.field_70146_Z.nextInt(3), this.field_70146_Z.nextInt(16)), 1.0f);
                    }
                    if (Math.random() < 0.2) {
                        this.func_70099_a(new ItemStack(ModItems.pinkinator), 1.0f);
                    }
                    if (Math.random() < 0.3) {
                        i = Item.func_150891_b((Item)Items.field_151096_cd);
                        int j = Item.func_150891_b((Item)Items.field_151084_co);
                        int k = i + this.field_70146_Z.nextInt(j - i + 1);
                        this.func_70099_a(new ItemStack(Item.func_150899_d((int)k)), 1.0f);
                        droppedRecord = true;
                    }
                }
                if (droppedRecord || !(Math.random() < 0.2)) continue;
                this.func_70099_a(new ItemStack(hard ? ModItems.recordGaia2 : ModItems.recordGaia1), 1.0f);
            }
        }
    }

    public void func_70106_y() {
        ChunkCoordinates source = this.getSource();
        Botania.proxy.playRecordClientSided(this.field_70170_p, source.field_71574_a, source.field_71572_b, source.field_71573_c, null);
        isPlayingMusic = false;
        super.func_70106_y();
    }

    public List<EntityPlayer> getPlayersAround() {
        ChunkCoordinates source = this.getSource();
        float range = 15.0f;
        List players = this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((double)source.field_71574_a + 0.5 - (double)range), (double)((double)source.field_71572_b + 0.5 - (double)range), (double)((double)source.field_71573_c + 0.5 - (double)range), (double)((double)source.field_71574_a + 0.5 + (double)range), (double)((double)source.field_71572_b + 0.5 + (double)range), (double)((double)source.field_71573_c + 0.5 + (double)range)));
        return players;
    }

    public void func_70636_d() {
        boolean spawnMissiles;
        boolean peaceful;
        super.func_70636_d();
        if (this.field_70154_o != null) {
            if (this.field_70154_o.field_70153_n != null) {
                this.field_70154_o.field_70153_n = null;
            }
            this.field_70154_o = null;
        }
        boolean bl = peaceful = this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL;
        if (!this.field_70170_p.field_72995_K && peaceful) {
            this.func_70106_y();
        }
        if (!this.field_70170_p.field_72995_K) {
            int radius = 1;
            int posXInt = MathHelper.func_76128_c((double)this.field_70165_t);
            int posYInt = MathHelper.func_76128_c((double)this.field_70163_u);
            int posZInt = MathHelper.func_76128_c((double)this.field_70161_v);
            for (int i = -radius; i < radius + 1; ++i) {
                for (int j = -radius; j < radius + 1; ++j) {
                    for (int k = -radius; k < radius + 1; ++k) {
                        int xp = posXInt + i;
                        int yp = posYInt + j;
                        int zp = posZInt + k;
                        if (!EntityDoppleganger.isCheatyBlock(this.field_70170_p, xp, yp, zp)) continue;
                        Block block = this.field_70170_p.func_147439_a(xp, yp, zp);
                        ArrayList items = block.getDrops(this.field_70170_p, xp, yp, zp, 0, 0);
                        for (ItemStack stack : items) {
                            if (ConfigHandler.blockBreakParticles) {
                                this.field_70170_p.func_72926_e(2001, xp, yp, zp, Block.func_149682_b((Block)block) + (this.field_70170_p.func_72805_g(xp, yp, zp) << 12));
                            }
                            this.field_70170_p.func_72838_d((Entity)new EntityItem(this.field_70170_p, (double)xp + 0.5, (double)yp + 0.5, (double)zp + 0.5, stack));
                        }
                        this.field_70170_p.func_147468_f(xp, yp, zp);
                    }
                }
            }
        }
        ChunkCoordinates source = this.getSource();
        boolean hard = this.isHardMode();
        float range = 15.0f;
        List players = this.getPlayersAround();
        int playerCount = this.getPlayerCount();
        if (this.field_70170_p.field_72995_K && !isPlayingMusic && !this.field_70128_L && !players.isEmpty()) {
            Botania.proxy.playRecordClientSided(this.field_70170_p, source.field_71574_a, source.field_71572_b, source.field_71573_c, (ItemRecord)(hard ? ModItems.recordGaia2 : ModItems.recordGaia1));
            isPlayingMusic = true;
        }
        range = 12.0f;
        for (int i = 0; i < 360; i += 8) {
            float r = 0.6f;
            float g = 0.0f;
            float b = 0.2f;
            float m = 0.15f;
            float mv = 0.35f;
            float rad = (float)i * (float)Math.PI / 180.0f;
            double x = (double)source.field_71574_a + 0.5 - Math.cos(rad) * (double)range;
            double y = (double)source.field_71572_b + 0.5;
            double z = (double)source.field_71573_c + 0.5 - Math.sin(rad) * (double)range;
            Botania.proxy.wispFX(this.field_70170_p, x, y, z, r, g, b, 0.5f, (float)(Math.random() - 0.5) * m, (float)(Math.random() - 0.5) * mv, (float)(Math.random() - 0.5) * m);
        }
        if (players.isEmpty() && !this.field_70170_p.field_73010_i.isEmpty()) {
            this.func_70106_y();
        } else {
            for (EntityPlayer player : players) {
                if (player.field_71071_by.field_70460_b[0] != null || player.field_71071_by.field_70460_b[1] != null || player.field_71071_by.field_70460_b[2] != null || player.field_71071_by.field_70460_b[3] != null) {
                    this.anyWithArmor = true;
                }
                ArrayList<PotionEffect> remove = new ArrayList<PotionEffect>();
                Collection active = player.func_70651_bq();
                for (PotionEffect effect : active) {
                    if (effect.func_76459_b() >= 200 || !effect.func_82720_e() || ((Boolean)ReflectionHelper.getPrivateValue(Potion.class, (Object)Potion.field_76425_a[effect.func_76456_a()], (String[])LibObfuscation.IS_BAD_EFFECT)).booleanValue()) continue;
                    remove.add(effect);
                }
                active.removeAll(remove);
                boolean bl2 = player.field_71075_bZ.field_75100_b = player.field_71075_bZ.field_75100_b && player.field_71075_bZ.field_75098_d;
                if (!(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(player.field_70165_t, player.field_70163_u, player.field_70161_v, (double)source.field_71574_a + 0.5, (double)source.field_71572_b + 0.5, (double)source.field_71573_c + 0.5) >= range)) continue;
                Vector3 sourceVector = new Vector3((double)source.field_71574_a + 0.5, (double)source.field_71572_b + 0.5, (double)source.field_71573_c + 0.5);
                Vector3 playerVector = Vector3.fromEntityCenter((Entity)player);
                Vector3 motion = sourceVector.copy().sub(playerVector).copy().normalize();
                player.field_70159_w = motion.x;
                player.field_70181_x = 0.2;
                player.field_70179_y = motion.z;
            }
        }
        if (this.field_70128_L) {
            return;
        }
        int invul = this.getInvulTime();
        int mobTicks = this.getMobSpawnTicks();
        boolean bl3 = spawnMissiles = hard && this.field_70173_aa % 15 < 4;
        if (invul > 10) {
            Vector3 pos = Vector3.fromEntityCenter((Entity)this).subtract(new Vector3(0.0, 0.2, 0.0));
            for (int i = 0; i < PYLON_LOCATIONS.length; ++i) {
                int[] arr = PYLON_LOCATIONS[i];
                int x = arr[0];
                int y = arr[1];
                int z = arr[2];
                Vector3 pylonPos = new Vector3(source.field_71574_a + x, source.field_71572_b + y, source.field_71573_c + z);
                double worldTime = this.field_70173_aa;
                float rad = 0.75f + (float)Math.random() * 0.05f;
                double xp = pylonPos.x + 0.5 + Math.cos(worldTime /= 5.0) * (double)rad;
                double zp = pylonPos.z + 0.5 + Math.sin(worldTime) * (double)rad;
                Vector3 partPos = new Vector3(xp, pylonPos.y, zp);
                Vector3 mot = pos.copy().sub(partPos).multiply(0.04);
                float r = 0.7f + (float)Math.random() * 0.3f;
                float g = (float)Math.random() * 0.3f;
                float b = 0.7f + (float)Math.random() * 0.3f;
                Botania.proxy.wispFX(this.field_70170_p, partPos.x, partPos.y, partPos.z, r, g, b, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                Botania.proxy.wispFX(this.field_70170_p, partPos.x, partPos.y, partPos.z, r, g, b, 0.4f, (float)mot.x, (float)mot.y, (float)mot.z);
            }
        }
        if (invul > 0 && mobTicks == 900) {
            if (invul < 160 && invul > 80 && this.field_70170_p.field_73012_v.nextInt(160 - invul + 1) == 0) {
                for (int i = 0; i < 2; ++i) {
                    this.func_70656_aK();
                }
            }
            if (!this.field_70170_p.field_72995_K) {
                this.func_70606_j(this.func_110143_aJ() + (this.func_110138_aP() - 1.0f) / 160.0f);
                this.setInvulTime(invul - 1);
            }
            this.field_70181_x = 0.0;
        } else if (this.isAggored()) {
            boolean dying;
            boolean bl4 = dying = (double)(this.func_110143_aJ() / this.func_110138_aP()) < 0.2;
            if (dying && mobTicks > 0) {
                this.field_70159_w = 0.0;
                this.field_70181_x = 0.0;
                this.field_70179_y = 0.0;
                int reverseTicks = 900 - mobTicks;
                if (reverseTicks < 20) {
                    this.field_70181_x = 0.2;
                    this.setInvulTime(invul + 1);
                }
                if (reverseTicks > 40 && mobTicks > 80 && mobTicks % 80 == 0 && !this.field_70170_p.field_72995_K) {
                    for (int pl = 0; pl < playerCount; ++pl) {
                        for (int i = 0; i < 3 + this.field_70170_p.field_73012_v.nextInt(2); ++i) {
                            EntityZombie entity = null;
                            switch (this.field_70170_p.field_73012_v.nextInt(2)) {
                                case 0: {
                                    entity = new EntityZombie(this.field_70170_p);
                                    if (this.field_70170_p.field_73012_v.nextInt(hard ? 3 : 12) != 0) break;
                                    entity = new EntityWitch(this.field_70170_p);
                                    break;
                                }
                                case 1: {
                                    entity = new EntitySkeleton(this.field_70170_p);
                                    ((EntitySkeleton)entity).func_70062_b(0, new ItemStack((Item)Items.field_151031_f));
                                    if (this.field_70170_p.field_73012_v.nextInt(8) != 0) break;
                                    ((EntitySkeleton)entity).func_82201_a(1);
                                    ((EntitySkeleton)entity).func_70062_b(0, new ItemStack(hard ? ModItems.elementiumSword : Items.field_151052_q));
                                    break;
                                }
                                case 3: {
                                    if (players.isEmpty()) break;
                                    for (int j = 0; j < 1 + this.field_70170_p.field_73012_v.nextInt(hard ? 8 : 5); ++j) {
                                        EntityPixie pixie = new EntityPixie(this.field_70170_p);
                                        pixie.setProps((EntityLivingBase)players.get(this.field_70146_Z.nextInt(players.size())), (EntityLivingBase)this, 1, 8.0f);
                                        pixie.func_70107_b(this.field_70165_t + (double)(this.field_70130_N / 2.0f), this.field_70163_u + 2.0, this.field_70161_v + (double)(this.field_70130_N / 2.0f));
                                        this.field_70170_p.func_72838_d((Entity)pixie);
                                    }
                                    break;
                                }
                            }
                            if (entity == null) continue;
                            range = 6.0f;
                            entity.func_70107_b(this.field_70165_t + 0.5 + Math.random() * (double)range - (double)(range / 2.0f), this.field_70163_u - 1.0, this.field_70161_v + 0.5 + Math.random() * (double)range - (double)(range / 2.0f));
                            this.field_70170_p.func_72838_d((Entity)entity);
                        }
                    }
                    if (hard && this.field_70173_aa % 3 < 2) {
                        for (int i = 0; i < playerCount; ++i) {
                            this.spawnMissile();
                        }
                        spawnMissiles = false;
                    }
                }
                this.setMobSpawnTicks(mobTicks - 1);
                this.setTPDelay(10);
            } else if (this.getTPDelay() > 0 && !this.field_70170_p.field_72995_K) {
                if (invul > 0) {
                    this.setInvulTime(invul - 1);
                }
                this.setTPDelay(this.getTPDelay() - 1);
                if (this.getTPDelay() == 0 && this.func_110143_aJ() > 0.0f) {
                    int tries;
                    for (tries = 0; !this.teleportRandomly() && tries < 50; ++tries) {
                    }
                    if (tries >= 50) {
                        this.teleportTo((double)source.field_71574_a + 0.5, (double)source.field_71572_b + 1.6, (double)source.field_71573_c + 0.5);
                    }
                    if (this.spawnLandmines) {
                        int count = dying && hard ? 7 : 6;
                        for (int i = 0; i < count; ++i) {
                            int x = source.field_71574_a - 10 + this.field_70146_Z.nextInt(20);
                            int z = source.field_71573_c - 10 + this.field_70146_Z.nextInt(20);
                            int y = this.field_70170_p.func_72825_h(x, z);
                            EntityMagicLandmine landmine = new EntityMagicLandmine(this.field_70170_p);
                            landmine.func_70107_b((double)x + 0.5, y, (double)z + 0.5);
                            landmine.summoner = this;
                            this.field_70170_p.func_72838_d((Entity)landmine);
                        }
                    }
                    if (!players.isEmpty()) {
                        for (int pl = 0; pl < playerCount; ++pl) {
                            for (int i = 0; i < (this.spawnPixies ? this.field_70170_p.field_73012_v.nextInt(hard ? 6 : 3) : 1); ++i) {
                                EntityPixie pixie = new EntityPixie(this.field_70170_p);
                                pixie.setProps((EntityLivingBase)players.get(this.field_70146_Z.nextInt(players.size())), (EntityLivingBase)this, 1, 8.0f);
                                pixie.func_70107_b(this.field_70165_t + (double)(this.field_70130_N / 2.0f), this.field_70163_u + 2.0, this.field_70161_v + (double)(this.field_70130_N / 2.0f));
                                this.field_70170_p.func_72838_d((Entity)pixie);
                            }
                        }
                    }
                    this.setTPDelay(hard ? (dying ? 35 : 45) : (dying ? 40 : 60));
                    this.spawnLandmines = true;
                    this.spawnPixies = false;
                }
            }
            if (spawnMissiles) {
                this.spawnMissile();
            }
        } else {
            range = 3.0f;
            players = this.field_70170_p.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)range), (double)(this.field_70163_u - (double)range), (double)(this.field_70161_v - (double)range), (double)(this.field_70165_t + (double)range), (double)(this.field_70163_u + (double)range), (double)(this.field_70161_v + (double)range)));
            if (!players.isEmpty()) {
                this.func_70665_d(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)players.get(0))), 0.0f);
            }
        }
    }

    void spawnMissile() {
        if (!this.field_70170_p.field_72995_K) {
            EntityMagicMissile missile = new EntityMagicMissile((EntityLivingBase)this, true);
            missile.func_70107_b(this.field_70165_t + (Math.random() - 0.05), this.field_70163_u + 2.4 + (Math.random() - 0.05), this.field_70161_v + (Math.random() - 0.05));
            if (missile.getTarget()) {
                this.field_70170_p.func_72956_a((Entity)this, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                this.field_70170_p.func_72838_d((Entity)missile);
            }
        }
    }

    public static boolean isCheatyBlock(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        String name = Block.field_149771_c.func_148750_c((Object)block);
        return CHEATY_BLOCKS.contains(name);
    }

    protected boolean teleportRandomly() {
        double d0 = this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        double d1 = this.field_70163_u + (double)(this.field_70146_Z.nextInt(64) - 32);
        double d2 = this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * 64.0;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportTo(double par1, double par3, double par5) {
        int k;
        int j;
        double d3 = this.field_70165_t;
        double d4 = this.field_70163_u;
        double d5 = this.field_70161_v;
        this.field_70165_t = par1;
        this.field_70163_u = par3;
        this.field_70161_v = par5;
        boolean flag = false;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72899_e(i, j = MathHelper.func_76128_c((double)this.field_70163_u), k = MathHelper.func_76128_c((double)this.field_70161_v))) {
            boolean flag1 = false;
            while (!flag1 && j > 0) {
                Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
                if (block.func_149688_o().func_76230_c()) {
                    flag1 = true;
                    continue;
                }
                this.field_70163_u -= 1.0;
                --j;
            }
            if (flag1) {
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                if (this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
                    flag = true;
                }
                ChunkCoordinates source = this.getSource();
                if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(this.field_70165_t, this.field_70163_u, this.field_70161_v, source.field_71574_a, source.field_71572_b, source.field_71573_c) > 12.0f) {
                    flag = false;
                }
            }
        }
        if (!flag) {
            this.func_70107_b(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * (double)this.field_70131_O;
            double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            this.field_70170_p.func_72869_a("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.func_85030_a("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getBossBarTexture() {
        return BossBarHandler.defaultBossBar;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if (barRect == null) {
            barRect = new Rectangle(0, 0, 185, 15);
        }
        return barRect;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if (hpBarRect == null) {
            hpBarRect = new Rectangle(0, EntityDoppleganger.barRect.y + EntityDoppleganger.barRect.height, 181, 7);
        }
        return hpBarRect;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void bossBarRenderCallback(ScaledResolution res, int x, int y) {
        GL11.glPushMatrix();
        int px = x + 160;
        int py = y + 12;
        Minecraft mc = Minecraft.func_71410_x();
        ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
        mc.field_71446_o.func_110577_a(TextureMap.field_110576_c);
        RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().func_77015_a(mc.field_71466_p, mc.field_71446_o, stack, px, py);
        RenderHelper.func_74518_a();
        boolean unicode = mc.field_71466_p.func_82883_a();
        mc.field_71466_p.func_78264_a(true);
        mc.field_71466_p.func_78261_a("" + this.getPlayerCount(), px + 15, py + 4, 0xFFFFFF);
        mc.field_71466_p.func_78264_a(unicode);
        GL11.glPopMatrix();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
        if (this.shaderCallback == null) {
            this.shaderCallback = new ShaderCallback(){

                @Override
                public void call(int shader) {
                    int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
                    int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"hpFract");
                    float time = EntityDoppleganger.this.getInvulTime();
                    float grainIntensity = time > 20.0f ? 1.0f : Math.max(EntityDoppleganger.this.isHardMode() ? 0.5f : 0.0f, time / 20.0f);
                    ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
                    ARBShaderObjects.glUniform1fARB((int)hpFractUniform, (float)(EntityDoppleganger.this.func_110143_aJ() / EntityDoppleganger.this.func_110138_aP()));
                }
            };
        }
        return background ? null : this.shaderCallback;
    }

    public static class BeaconBeamComponent
    extends MultiblockComponent {
        public BeaconBeamComponent(ChunkCoordinates relPos) {
            super(relPos, (Block)Blocks.field_150461_bJ, 0);
        }

        @Override
        public boolean matches(World world, int x, int y, int z) {
            return world.func_147438_o(x, y, z) instanceof TileEntityBeacon;
        }
    }

    public static class BeaconComponent
    extends MultiblockComponent {
        public BeaconComponent(ChunkCoordinates relPos) {
            super(relPos, Blocks.field_150339_S, 0);
        }

        @Override
        public boolean matches(World world, int x, int y, int z) {
            return world.func_147439_a(x, y, z).isBeaconBase((IBlockAccess)world, x, y, z, x - this.relPos.field_71574_a, y - this.relPos.field_71572_b, z - this.relPos.field_71573_c);
        }
    }
}

