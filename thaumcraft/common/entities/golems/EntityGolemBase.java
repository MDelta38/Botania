/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.IAnimals
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.FluidStack
 *  org.apache.logging.log4j.Level
 */
package thaumcraft.common.entities.golems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.InventoryMob;
import thaumcraft.common.entities.ai.combat.AIAvoidCreeperSwell;
import thaumcraft.common.entities.ai.combat.AIDartAttack;
import thaumcraft.common.entities.ai.combat.AIGolemAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AIHurtByTarget;
import thaumcraft.common.entities.ai.combat.AINearestAttackableTarget;
import thaumcraft.common.entities.ai.combat.AINearestButcherTarget;
import thaumcraft.common.entities.ai.fluid.AIEssentiaEmpty;
import thaumcraft.common.entities.ai.fluid.AIEssentiaGather;
import thaumcraft.common.entities.ai.fluid.AIEssentiaGoto;
import thaumcraft.common.entities.ai.fluid.AILiquidEmpty;
import thaumcraft.common.entities.ai.fluid.AILiquidGather;
import thaumcraft.common.entities.ai.fluid.AILiquidGoto;
import thaumcraft.common.entities.ai.interact.AIFish;
import thaumcraft.common.entities.ai.interact.AIHarvestCrops;
import thaumcraft.common.entities.ai.interact.AIHarvestLogs;
import thaumcraft.common.entities.ai.interact.AIUseItem;
import thaumcraft.common.entities.ai.inventory.AIEmptyDrop;
import thaumcraft.common.entities.ai.inventory.AIEmptyGoto;
import thaumcraft.common.entities.ai.inventory.AIEmptyPlace;
import thaumcraft.common.entities.ai.inventory.AIFillGoto;
import thaumcraft.common.entities.ai.inventory.AIFillTake;
import thaumcraft.common.entities.ai.inventory.AIHomeDrop;
import thaumcraft.common.entities.ai.inventory.AIHomePlace;
import thaumcraft.common.entities.ai.inventory.AIHomeReplace;
import thaumcraft.common.entities.ai.inventory.AIHomeTake;
import thaumcraft.common.entities.ai.inventory.AIHomeTakeSorting;
import thaumcraft.common.entities.ai.inventory.AIItemPickup;
import thaumcraft.common.entities.ai.inventory.AISortingGoto;
import thaumcraft.common.entities.ai.inventory.AISortingPlace;
import thaumcraft.common.entities.ai.misc.AIOpenDoor;
import thaumcraft.common.entities.ai.misc.AIReturnHome;
import thaumcraft.common.entities.golems.EnumGolemType;
import thaumcraft.common.entities.golems.ItemGolemCore;
import thaumcraft.common.entities.golems.ItemGolemDecoration;
import thaumcraft.common.entities.golems.Marker;
import thaumcraft.common.entities.projectile.EntityDart;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.utils.Utils;

public class EntityGolemBase
extends EntityGolem
implements IEntityAdditionalSpawnData {
    public InventoryMob inventory = new InventoryMob((Entity)this, 1);
    public ItemStack itemCarried;
    public FluidStack fluidCarried;
    public ItemStack itemWatched = null;
    public Aspect essentia;
    public int essentiaAmount;
    public boolean advanced = false;
    public int homeFacing = 0;
    public boolean paused = false;
    public boolean inactive = false;
    public boolean flag = false;
    public byte[] colors = null;
    public byte[] upgrades = null;
    public String decoration = "";
    public float bootup = -1.0f;
    public EnumGolemType golemType = EnumGolemType.WOOD;
    public int regenTimer = 0;
    protected ArrayList<Marker> markers = new ArrayList();
    boolean pdw = false;
    public int action = 0;
    public int leftArm = 0;
    public int rightArm = 0;
    public int healing = 0;

    public EntityGolemBase(World par1World) {
        super(par1World);
        this.field_70180_af.func_75682_a(30, (Object)((byte)this.func_110138_aP()));
        this.field_70138_W = 1.0f;
        this.colors = new byte[]{-1};
        this.upgrades = new byte[]{-1};
        this.func_70105_a(0.4f, 0.95f);
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75490_c(true);
        this.func_70661_as().func_75495_e(true);
        this.func_110163_bv();
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.6);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(32.0);
    }

    public EntityGolemBase(World par0World, EnumGolemType type, boolean adv) {
        this(par0World);
        this.golemType = type;
        this.advanced = adv;
        this.upgrades = new byte[this.golemType.upgrades + (this.advanced ? 1 : 0)];
        for (int a = 0; a < this.upgrades.length; ++a) {
            this.upgrades[a] = -1;
        }
    }

    public boolean setupGolemInventory() {
        Object core = null;
        if (!ItemGolemCore.hasInventory(this.getCore())) {
            return false;
        }
        if (this.getCore() > -1) {
            int invSize = 0;
            switch (this.getCore()) {
                default: {
                    invSize = 6;
                    invSize += this.getUpgradeAmount(2) * 6;
                    break;
                }
                case 5: {
                    invSize = 1;
                    invSize += this.getUpgradeAmount(2);
                }
                case 3: 
                case 4: 
                case 6: 
            }
            InventoryMob inventory2 = new InventoryMob((Entity)this, invSize);
            for (int a = 0; a < this.inventory.inventory.length; ++a) {
                inventory2.inventory[a] = this.inventory.inventory[a];
            }
            this.inventory = inventory2;
        }
        byte[] oldcolors = this.colors;
        this.colors = new byte[this.inventory.slotCount];
        for (int a = 0; a < this.inventory.slotCount; ++a) {
            this.colors[a] = -1;
            if (a >= oldcolors.length) continue;
            this.colors[a] = oldcolors[a];
        }
        return true;
    }

    public boolean setupGolem() {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(19, (Object)((byte)this.golemType.ordinal()));
        }
        if (this.getGolemType() == EnumGolemType.STONE || this.getGolemType() == EnumGolemType.IRON || this.getGolemType() == EnumGolemType.THAUMIUM) {
            this.func_70661_as().func_75491_a(false);
        } else {
            this.func_70661_as().func_75491_a(true);
        }
        if (this.getGolemType().fireResist) {
            this.field_70178_ae = true;
        }
        int bonus = 0;
        try {
            bonus = this.getGolemDecoration().contains("H") ? 5 : 0;
        }
        catch (Exception e) {
            // empty catch block
        }
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(this.getGolemType().health + bonus));
        int damage = 2 + this.getGolemStrength() + this.getUpgradeAmount(1);
        try {
            if (this.getGolemDecoration().contains("M")) {
                damage += 2;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((double)damage);
        this.field_70714_bg.field_75782_a.clear();
        if (this.getCore() > -1) {
            this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIAvoidCreeperSwell(this));
        }
        switch (this.getCore()) {
            case 0: {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIHomeReplace(this));
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIHomePlace(this));
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIHomeDrop(this));
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIFillTake(this));
                this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIFillGoto(this));
                break;
            }
            case 1: {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIHomeReplace(this));
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIEmptyPlace(this));
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIEmptyDrop(this));
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIEmptyGoto(this));
                this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIHomeTake(this));
                break;
            }
            case 2: {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIHomeReplace(this));
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIHomePlace(this));
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIItemPickup(this));
                break;
            }
            case 3: {
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIHarvestCrops(this));
                break;
            }
            case 4: {
                if (this.decoration.contains("R")) {
                    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIDartAttack(this));
                }
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIGolemAttackOnCollide(this));
                this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AIHurtByTarget((EntityCreature)this, false));
                this.field_70715_bh.func_75776_a(2, (EntityAIBase)new AINearestAttackableTarget(this, 0, true));
                break;
            }
            case 5: {
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AILiquidEmpty(this));
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILiquidGather(this));
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AILiquidGoto(this));
                break;
            }
            case 6: {
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AIEssentiaEmpty(this));
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIEssentiaGather(this));
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIEssentiaGoto(this));
                break;
            }
            case 7: {
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIHarvestLogs(this));
                break;
            }
            case 8: {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIHomeReplace(this));
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIUseItem(this));
                this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIHomeTake(this));
                break;
            }
            case 9: {
                if (this.decoration.contains("R")) {
                    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIDartAttack(this));
                }
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIGolemAttackOnCollide(this));
                this.field_70715_bh.func_75776_a(1, (EntityAIBase)new AINearestButcherTarget(this, 0, true));
                break;
            }
            case 10: {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new AIHomeReplace(this));
                this.field_70714_bg.func_75776_a(1, (EntityAIBase)new AISortingPlace(this));
                this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AISortingGoto(this));
                this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIHomeTakeSorting(this));
                break;
            }
            case 11: {
                this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AIFish(this));
            }
        }
        if (this.getCore() > -1) {
            this.field_70714_bg.func_75776_a(5, (EntityAIBase)new AIOpenDoor(this, true));
            this.field_70714_bg.func_75776_a(6, (EntityAIBase)new AIReturnHome(this));
            this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
            this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        }
        return true;
    }

    public int getCarryLimit() {
        int base = this.golemType.carry;
        if (this.field_70170_p.field_72995_K) {
            base = this.getGolemType().carry;
        }
        base += Math.min(16, Math.max(4, base)) * this.getUpgradeAmount(1);
        return base;
    }

    public int getFluidCarryLimit() {
        return MathHelper.func_76128_c((double)Math.sqrt(this.getCarryLimit())) * 1000;
    }

    public float func_70689_ay() {
        if (this.paused || this.inactive) {
            return 0.0f;
        }
        float speed = this.golemType.speed * (this.decoration.contains("B") ? 1.1f : 1.0f);
        if (this.decoration.contains("P")) {
            speed *= 0.88f;
        }
        speed *= 1.0f + (float)this.getUpgradeAmount(0) * 0.15f;
        if (this.advanced) {
            speed *= 1.1f;
        }
        if (this.func_70090_H() && (this.getGolemType() == EnumGolemType.STONE || this.getGolemType() == EnumGolemType.IRON || this.getGolemType() == EnumGolemType.THAUMIUM)) {
            speed *= 2.0f;
        }
        return speed;
    }

    public void setup(int side) {
        this.homeFacing = side;
        this.setupGolem();
        this.setupGolemInventory();
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_82709_a(16, 5);
        this.field_70180_af.func_75682_a(17, (Object)"");
        this.field_70180_af.func_75682_a(18, (Object)0);
        this.field_70180_af.func_75682_a(19, (Object)0);
        this.field_70180_af.func_75682_a(20, (Object)String.valueOf(""));
        this.field_70180_af.func_75682_a(21, (Object)-1);
        this.field_70180_af.func_75682_a(22, (Object)String.valueOf(""));
        this.field_70180_af.func_75682_a(23, (Object)String.valueOf(""));
    }

    public boolean func_70650_aV() {
        return !this.paused && !this.inactive;
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (this.action > 0) {
            --this.action;
        }
        if (this.leftArm > 0) {
            --this.leftArm;
        }
        if (this.rightArm > 0) {
            --this.rightArm;
        }
        if (this.healing > 0) {
            --this.healing;
        }
        int xx = MathHelper.func_76128_c((double)this.field_70165_t);
        int yy = MathHelper.func_76128_c((double)this.field_70163_u);
        int zz = MathHelper.func_76128_c((double)this.field_70161_v);
        this.inactive = yy > 0 && this.field_70170_p.func_147439_a(xx, yy - 1, zz) == ConfigBlocks.blockCosmeticSolid && this.field_70170_p.func_72805_g(xx, yy - 1, zz) == 10;
        if (!this.field_70170_p.field_72995_K) {
            if (this.regenTimer > 0) {
                --this.regenTimer;
            } else {
                this.regenTimer = this.golemType.regenDelay;
                if (this.decoration.contains("F")) {
                    this.regenTimer = (int)((float)this.regenTimer * 0.66f);
                }
                if (!this.field_70170_p.field_72995_K && this.func_110143_aJ() < this.func_110138_aP()) {
                    this.field_70170_p.func_72960_a((Entity)this, (byte)5);
                    this.func_70691_i(1.0f);
                }
            }
            if (this.func_70092_e(this.func_110172_bL().field_71574_a, this.func_110172_bL().field_71572_b, this.func_110172_bL().field_71573_c) >= 2304.0 || this.func_70094_T()) {
                int var1 = MathHelper.func_76128_c((double)this.func_110172_bL().field_71574_a);
                int var2 = MathHelper.func_76128_c((double)this.func_110172_bL().field_71573_c);
                int var3 = MathHelper.func_76128_c((double)this.func_110172_bL().field_71572_b);
                for (int var0 = 1; var0 >= -1; --var0) {
                    for (int var4 = -1; var4 <= 1; ++var4) {
                        for (int var5 = -1; var5 <= 1; ++var5) {
                            if (!World.func_147466_a((IBlockAccess)this.field_70170_p, (int)(var1 + var4), (int)(var3 - 1 + var0), (int)(var2 + var5)) || this.field_70170_p.func_147445_c(var1 + var4, var3 + var0, var2 + var5, false)) continue;
                            this.func_70012_b((float)(var1 + var4) + 0.5f, (double)var3 + (double)var0, (float)(var2 + var5) + 0.5f, this.field_70177_z, this.field_70125_A);
                            this.func_70661_as().func_75499_g();
                            return;
                        }
                    }
                }
            }
        } else if (this.bootup > 0.0f && this.getCore() > -1) {
            this.bootup *= this.bootup / 33.1f;
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:cameraticks", this.bootup * 0.2f, 1.0f * this.bootup, false);
        }
    }

    public float getRange() {
        float dmod = 16 + this.getUpgradeAmount(3) * 4;
        if (this.decoration.contains("G")) {
            dmod += Math.max(dmod * 0.1f, 1.0f);
        }
        if (this.advanced) {
            dmod += Math.max(dmod * 0.2f, 2.0f);
        }
        return dmod;
    }

    public boolean func_110176_b(int par1, int par2, int par3) {
        float dmod = this.getRange();
        return this.func_110172_bL().func_71569_e(par1, par2, par3) < dmod * dmod;
    }

    protected void func_70626_be() {
        ++this.field_70708_bq;
        this.func_70623_bb();
        boolean vara = this.func_70090_H();
        boolean varb = this.func_70058_J();
        if (vara || varb) {
            this.field_70703_bu = this.field_70146_Z.nextFloat() < 0.8f;
        }
    }

    public void func_70645_a(DamageSource ds) {
        if (!this.field_70170_p.field_72995_K) {
            FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] " + (Object)((Object)this) + " was killed by " + ds.func_76364_f() + " (" + ds.func_76355_l() + ")");
        }
        super.func_70645_a(ds);
    }

    public void func_70015_d(int par1) {
        if (!this.golemType.fireResist) {
            super.func_70015_d(par1);
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_70623_bb() {
    }

    public int func_70682_h(int par1) {
        return par1;
    }

    public short getColors(int slot) {
        char[] chars = this.field_70180_af.func_75681_e(22).toCharArray();
        if (slot < chars.length) {
            if (("" + chars[slot]).equals("h")) {
                return -1;
            }
            return Short.parseShort("" + chars[slot], 16);
        }
        return -1;
    }

    public void setColors(int slot, int color) {
        this.colors[slot] = (byte)color;
        String s = "";
        for (byte c : this.colors) {
            s = c == -1 ? s + "h" : s + Integer.toHexString(c);
        }
        this.field_70180_af.func_75692_b(22, (Object)String.valueOf(s));
    }

    public byte getUpgrade(int slot) {
        char[] chars = this.field_70180_af.func_75681_e(23).toCharArray();
        if (slot < chars.length) {
            byte t = Byte.parseByte("" + chars[slot], 16);
            if (t == 15) {
                return -1;
            }
            return t;
        }
        return -1;
    }

    public int getUpgradeAmount(int type) {
        int a = 0;
        for (byte b : this.upgrades) {
            if (type != b) continue;
            ++a;
        }
        return a;
    }

    public void setUpgrade(int slot, byte upgrade) {
        this.upgrades[slot] = upgrade;
        String s = "";
        for (byte c : this.upgrades) {
            s = s + Integer.toHexString(c);
        }
        this.field_70180_af.func_75692_b(23, (Object)String.valueOf(s));
    }

    public ArrayList<Byte> getColorsMatching(ItemStack match) {
        ArrayList<Byte> l = new ArrayList<Byte>();
        if (this.inventory.inventory != null && this.inventory.inventory.length > 0) {
            int a;
            boolean allNull = true;
            for (a = 0; a < this.inventory.inventory.length; ++a) {
                if (this.inventory.func_70301_a(a) != null) {
                    allNull = false;
                }
                if (!InventoryUtils.areItemStacksEqual(this.inventory.func_70301_a(a), match, this.checkOreDict(), this.ignoreDamage(), this.ignoreNBT())) continue;
                l.add(this.colors[a]);
            }
            if (allNull) {
                for (a = 0; a < this.inventory.inventory.length; ++a) {
                    l.add(this.colors[a]);
                }
            }
        }
        return l;
    }

    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74768_a("HomeX", this.func_110172_bL().field_71574_a);
        nbt.func_74768_a("HomeY", this.func_110172_bL().field_71572_b);
        nbt.func_74768_a("HomeZ", this.func_110172_bL().field_71573_c);
        nbt.func_74774_a("HomeFacing", (byte)this.homeFacing);
        nbt.func_74774_a("GolemType", (byte)this.golemType.ordinal());
        nbt.func_74774_a("Core", this.getCore());
        nbt.func_74778_a("Decoration", this.decoration);
        nbt.func_74774_a("toggles", this.getTogglesValue());
        nbt.func_74757_a("advanced", this.advanced);
        nbt.func_74773_a("colors", this.colors);
        nbt.func_74773_a("upgrades", this.upgrades);
        if (this.getCore() == 5 && this.fluidCarried != null) {
            this.fluidCarried.writeToNBT(nbt);
        }
        if (this.getCore() == 6 && this.essentia != null && this.essentiaAmount > 0) {
            nbt.func_74778_a("essentia", this.essentia.getTag());
            nbt.func_74774_a("essentiaAmount", (byte)this.essentiaAmount);
        }
        NBTTagCompound var4 = new NBTTagCompound();
        if (this.itemCarried != null) {
            this.itemCarried.func_77955_b(var4);
        }
        nbt.func_74782_a("ItemCarried", (NBTBase)var4);
        if (this.getOwnerName() == null) {
            nbt.func_74778_a("Owner", "");
        } else {
            nbt.func_74778_a("Owner", this.getOwnerName());
        }
        NBTTagList tl = new NBTTagList();
        for (Marker l : this.markers) {
            NBTTagCompound nbtc = new NBTTagCompound();
            nbtc.func_74768_a("x", l.x);
            nbtc.func_74768_a("y", l.y);
            nbtc.func_74768_a("z", l.z);
            nbtc.func_74768_a("dim", l.dim);
            nbtc.func_74774_a("side", l.side);
            nbtc.func_74774_a("color", l.color);
            tl.func_74742_a((NBTBase)nbtc);
        }
        nbt.func_74782_a("Markers", (NBTBase)tl);
        nbt.func_74782_a("Inventory", (NBTBase)this.inventory.writeToNBT(new NBTTagList()));
    }

    public void func_70037_a(NBTTagCompound nbt) {
        String s;
        super.func_70037_a(nbt);
        int hx = nbt.func_74762_e("HomeX");
        int hy = nbt.func_74762_e("HomeY");
        int hz = nbt.func_74762_e("HomeZ");
        this.homeFacing = nbt.func_74771_c("HomeFacing");
        this.func_110171_b(hx, hy, hz, 32);
        this.advanced = nbt.func_74767_n("advanced");
        this.golemType = EnumGolemType.getType(nbt.func_74771_c("GolemType"));
        this.setCore(nbt.func_74771_c("Core"));
        if (this.getCore() == 5) {
            this.fluidCarried = FluidStack.loadFluidStackFromNBT((NBTTagCompound)nbt);
        }
        if (this.getCore() == 6 && (s = nbt.func_74779_i("essentia")) != null) {
            this.essentia = Aspect.getAspect(s);
            if (this.essentia != null) {
                this.essentiaAmount = nbt.func_74771_c("essentiaAmount");
            }
        }
        this.setTogglesValue(nbt.func_74771_c("toggles"));
        NBTTagCompound var4 = nbt.func_74775_l("ItemCarried");
        this.itemCarried = ItemStack.func_77949_a((NBTTagCompound)var4);
        this.updateCarried();
        this.decoration = nbt.func_74779_i("Decoration");
        this.setGolemDecoration(this.decoration);
        String var2 = nbt.func_74779_i("Owner");
        if (var2.length() > 0) {
            this.setOwner(var2);
        }
        this.field_70180_af.func_75692_b(30, (Object)((byte)this.func_110143_aJ()));
        NBTTagList nbttaglist = nbt.func_150295_c("Markers", 10);
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int x = nbttagcompound1.func_74762_e("x");
            int y = nbttagcompound1.func_74762_e("y");
            int z = nbttagcompound1.func_74762_e("z");
            int dim = nbttagcompound1.func_74762_e("dim");
            byte s2 = nbttagcompound1.func_74771_c("side");
            byte c = nbttagcompound1.func_74771_c("color");
            this.markers.add(new Marker(x, y, z, (byte)dim, s2, c));
        }
        this.upgrades = new byte[this.golemType.upgrades + (this.advanced ? 1 : 0)];
        int ul = this.upgrades.length;
        this.upgrades = nbt.func_74770_j("upgrades");
        if (ul != this.upgrades.length) {
            int a;
            byte[] tt = new byte[ul];
            for (a = 0; a < ul; ++a) {
                tt[a] = -1;
            }
            for (a = 0; a < this.upgrades.length; ++a) {
                if (a >= ul) continue;
                tt[a] = this.upgrades[a];
            }
            this.upgrades = tt;
        }
        String st = "";
        for (byte c : this.upgrades) {
            st = st + Integer.toHexString(c);
        }
        this.field_70180_af.func_75692_b(23, (Object)String.valueOf(st));
        this.setupGolem();
        this.setupGolemInventory();
        NBTTagList nbttaglist2 = nbt.func_150295_c("Inventory", 10);
        this.inventory.readFromNBT(nbttaglist2);
        byte[] oldcolors = this.colors = nbt.func_74770_j("colors");
        this.colors = new byte[this.inventory.slotCount];
        for (int a = 0; a < this.inventory.slotCount; ++a) {
            this.colors[a] = -1;
            if (a >= oldcolors.length) continue;
            this.colors[a] = oldcolors[a];
        }
        st = "";
        for (byte c : this.colors) {
            st = c == -1 ? st + "h" : st + Integer.toHexString(c);
        }
        this.field_70180_af.func_75692_b(22, (Object)String.valueOf(st));
    }

    public String getOwnerName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setOwner(String par1Str) {
        this.field_70180_af.func_75692_b(17, (Object)par1Str);
    }

    public void setMarkers(ArrayList<Marker> markers) {
        this.markers = markers;
    }

    public ArrayList<Marker> getMarkers() {
        this.validateMarkers();
        return this.markers;
    }

    protected void validateMarkers() {
        ArrayList<Marker> newMarkers = new ArrayList<Marker>();
        for (Marker marker : this.markers) {
            if (marker.dim != this.field_70170_p.field_73011_w.field_76574_g) continue;
            newMarkers.add(marker);
        }
        this.markers = newMarkers;
    }

    public EntityLivingBase getOwner() {
        return this.field_70170_p.func_72924_a(this.getOwnerName());
    }

    protected void func_70665_d(DamageSource ds, float par2) {
        if (ds.func_76347_k() && this.golemType.fireResist) {
            return;
        }
        if (ds == DamageSource.field_76368_d || ds == DamageSource.field_76380_i) {
            this.func_70012_b((double)this.func_110172_bL().field_71574_a + 0.5, (double)this.func_110172_bL().field_71572_b + 0.5, (double)this.func_110172_bL().field_71573_c + 0.5, 0.0f, 0.0f);
        }
        super.func_70665_d(ds, par2);
        if (!this.field_70170_p.field_72995_K) {
            this.field_70180_af.func_75692_b(30, (Object)((byte)this.func_110143_aJ()));
        }
    }

    public void func_70691_i(float par1) {
        super.func_70691_i(par1);
        try {
            if (!this.field_70170_p.field_72995_K) {
                this.field_70180_af.func_75692_b(30, (Object)((byte)this.func_110143_aJ()));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void func_70606_j(float par1) {
        super.func_70606_j(par1);
        try {
            if (!this.field_70170_p.field_72995_K) {
                this.field_70180_af.func_75692_b(30, (Object)((byte)this.func_110143_aJ()));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public float getHealthPercentage() {
        return (float)this.field_70180_af.func_75683_a(30) / this.func_110138_aP();
    }

    public void setCarried(ItemStack stack) {
        this.itemCarried = stack;
        this.updateCarried();
    }

    public boolean hasSomething() {
        return this.inventory.hasSomething();
    }

    public ItemStack getCarried() {
        if (this.itemCarried != null && this.itemCarried.field_77994_a <= 0) {
            this.setCarried(null);
        }
        return this.itemCarried;
    }

    public int getCarrySpace() {
        if (this.itemCarried == null) {
            return this.getCarryLimit();
        }
        return Math.min(this.getCarryLimit() - this.itemCarried.field_77994_a, this.itemCarried.func_77976_d() - this.itemCarried.field_77994_a);
    }

    public boolean[] getToggles() {
        return Utils.unpack(this.field_70180_af.func_75683_a(18));
    }

    public byte getTogglesValue() {
        return this.field_70180_af.func_75683_a(18);
    }

    public void setToggle(int index, boolean tog) {
        boolean[] fz = this.getToggles();
        fz[index] = tog;
        this.field_70180_af.func_75692_b(18, (Object)Utils.pack(fz));
    }

    public void setTogglesValue(byte tog) {
        this.field_70180_af.func_75692_b(18, (Object)tog);
    }

    public boolean canAttackHostiles() {
        return !this.getToggles()[1];
    }

    public boolean canAttackAnimals() {
        return !this.getToggles()[2];
    }

    public boolean canAttackPlayers() {
        return !this.getToggles()[3];
    }

    public boolean canAttackCreepers() {
        return !this.getToggles()[4];
    }

    public boolean checkOreDict() {
        return this.getToggles()[5];
    }

    public boolean ignoreDamage() {
        return this.getToggles()[6];
    }

    public boolean ignoreNBT() {
        return this.getToggles()[7];
    }

    public EnumGolemType getGolemType() {
        return EnumGolemType.getType(this.field_70180_af.func_75683_a(19));
    }

    public int getGolemStrength() {
        return this.getGolemType().strength + this.getUpgradeAmount(1);
    }

    public void setCore(byte core) {
        this.field_70180_af.func_75692_b(21, (Object)core);
    }

    public byte getCore() {
        return this.field_70180_af.func_75683_a(21);
    }

    public String getGolemDecoration() {
        return this.field_70180_af.func_75681_e(20);
    }

    public void setGolemDecoration(String string) {
        this.field_70180_af.func_75692_b(20, (Object)String.valueOf(this.decoration));
    }

    public ItemStack getCarriedForDisplay() {
        if (this.field_70180_af.func_82710_f(16) != null) {
            return this.field_70180_af.func_82710_f(16);
        }
        return null;
    }

    public void updateCarried() {
        if (this.itemCarried != null) {
            this.func_70096_w().func_75692_b(16, (Object)this.itemCarried.func_77946_l());
            this.func_70096_w().func_82708_h(16);
        } else if (this.getCore() == 5 && this.fluidCarried != null) {
            this.func_70096_w().func_75692_b(16, (Object)new ItemStack(Item.func_150899_d((int)this.fluidCarried.fluidID), 1, this.fluidCarried.amount));
            this.func_70096_w().func_82708_h(16);
        } else if (this.getCore() == 6) {
            ItemStack disp = new ItemStack(ConfigItems.itemJarFilled);
            int amt = (int)(64.0f * ((float)this.essentiaAmount / (float)this.getCarryLimit()));
            if (this.essentia != null && this.essentiaAmount > 0) {
                ((IEssentiaContainerItem)disp.func_77973_b()).setAspects(disp, new AspectList().add(this.essentia, amt));
            }
            this.func_70096_w().func_75692_b(16, (Object)disp);
            this.func_70096_w().func_82708_h(16);
        } else {
            this.func_70096_w().func_82709_a(16, 5);
            this.func_70096_w().func_82708_h(16);
        }
    }

    protected float func_70599_aP() {
        return 0.1f;
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.dropStuff();
    }

    public void dropStuff() {
        if (!this.field_70170_p.field_72995_K && this.itemCarried != null) {
            this.func_70099_a(this.itemCarried, 0.5f);
        }
    }

    protected boolean addDecoration(String type, ItemStack itemStack) {
        if (this.decoration.contains(type)) {
            return false;
        }
        if ((type.equals("F") || type.equals("H")) && (this.decoration.contains("F") || this.decoration.contains("H"))) {
            return false;
        }
        if ((type.equals("G") || type.equals("V")) && (this.decoration.contains("G") || this.decoration.contains("V"))) {
            return false;
        }
        if ((type.equals("B") || type.equals("P")) && (this.decoration.contains("P") || this.decoration.contains("B"))) {
            return false;
        }
        this.decoration = this.decoration + type;
        if (!this.field_70170_p.field_72995_K) {
            this.setGolemDecoration(this.decoration);
            --itemStack.field_77994_a;
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:cameraclack", 1.0f, 1.0f);
        }
        this.setupGolem();
        return true;
    }

    public boolean customInteraction(EntityPlayer player) {
        if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == ConfigItems.itemGolemBell) {
            return false;
        }
        if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == ConfigItems.itemGolemDecoration) {
            this.addDecoration(ItemGolemDecoration.getDecoChar(player.field_71071_by.func_70448_g().func_77960_j()), player.field_71071_by.func_70448_g());
            player.func_71038_i();
            return false;
        }
        if (player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() == Items.field_151106_aX) {
            player.field_71071_by.func_146026_a(Items.field_151106_aX);
            player.func_71038_i();
            for (int var3 = 0; var3 < 3; ++var3) {
                double var4 = this.field_70146_Z.nextGaussian() * 0.02;
                double var6 = this.field_70146_Z.nextGaussian() * 0.02;
                double var8 = this.field_70146_Z.nextGaussian() * 0.02;
                this.field_70170_p.func_72869_a("heart", this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, var4, var6, var8);
                this.field_70170_p.func_72956_a((Entity)this, "random.eat", 0.3f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
                int duration = 600;
                if (!this.field_70170_p.field_72995_K) continue;
                if (this.func_70660_b(Potion.field_76424_c) != null && this.func_70660_b(Potion.field_76424_c).func_76459_b() < 2400) {
                    duration += this.func_70660_b(Potion.field_76424_c).func_76459_b();
                }
                this.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, duration, 0));
            }
            this.func_70691_i(5.0f);
            return false;
        }
        if (!(this.getCore() <= -1 || !ItemGolemCore.hasGUI(this.getCore()) || player.field_71071_by.func_70448_g() != null && player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemWandCasting || this.field_70170_p.field_72995_K)) {
            player.openGui((Object)Thaumcraft.instance, 0, this.field_70170_p, this.func_145782_y(), 0, 0);
            return false;
        }
        return false;
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (player.func_70093_af()) {
            return false;
        }
        ItemStack itemstack = player.field_71071_by.func_70448_g();
        if (this.getCore() > -1 && itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemBell) {
            return false;
        }
        if (this.getCore() == -1 && itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemCore && itemstack.func_77960_j() != 100) {
            this.setCore((byte)itemstack.func_77960_j());
            this.setupGolem();
            this.setupGolemInventory();
            --itemstack.field_77994_a;
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
            }
            this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:upgrade", 0.5f, 1.0f);
            player.func_71038_i();
            this.field_70170_p.func_72960_a((Entity)this, (byte)7);
            return true;
        }
        if (itemstack != null && itemstack.func_77973_b() == ConfigItems.itemGolemUpgrade) {
            for (int a = 0; a < this.upgrades.length; ++a) {
                if (this.getUpgrade(a) != -1 || this.getUpgradeAmount(itemstack.func_77960_j()) >= 2) continue;
                this.setUpgrade(a, (byte)itemstack.func_77960_j());
                this.setupGolem();
                this.setupGolemInventory();
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
                }
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:upgrade", 0.5f, 1.0f);
                player.func_71038_i();
                return true;
            }
            return false;
        }
        return this.customInteraction(player);
    }

    public int getActionTimer() {
        return 3 - Math.abs(this.action - 3);
    }

    public void startActionTimer() {
        if (this.action == 0) {
            this.action = 6;
            this.field_70170_p.func_72960_a((Entity)this, (byte)4);
        }
    }

    public void startLeftArmTimer() {
        if (this.leftArm == 0) {
            this.leftArm = 5;
            this.field_70170_p.func_72960_a((Entity)this, (byte)6);
        }
    }

    public void startRightArmTimer() {
        if (this.rightArm == 0) {
            this.rightArm = 5;
            this.field_70170_p.func_72960_a((Entity)this, (byte)8);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.action = 6;
        } else if (par1 == 5) {
            this.healing = 5;
            int bonus = 0;
            try {
                bonus = this.getGolemDecoration().contains("H") ? 5 : 0;
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(this.getGolemType().health + bonus));
        } else if (par1 == 6) {
            this.leftArm = 5;
        } else if (par1 == 8) {
            this.rightArm = 5;
        } else if (par1 == 7) {
            this.bootup = 33.0f;
        } else {
            super.func_70103_a(par1);
        }
    }

    protected void func_70064_a(double par1, boolean par3) {
        if (par3 && this.field_70143_R > 0.0f) {
            int var4 = MathHelper.func_76128_c((double)this.field_70165_t);
            int var5 = MathHelper.func_76128_c((double)(this.field_70163_u - (double)0.2f - (double)this.field_70129_M));
            int var6 = MathHelper.func_76128_c((double)this.field_70161_v);
            Block var7 = this.field_70170_p.func_147439_a(var4, var5, var6);
            if (this.field_70170_p.func_147437_c(var4, var5, var6) && this.field_70170_p.func_147439_a(var4, var5 - 1, var6) == Blocks.field_150422_aJ) {
                var7 = this.field_70170_p.func_147439_a(var4, var5 - 1, var6);
            }
        }
        if (par3) {
            if (this.field_70143_R > 0.0f) {
                this.func_70069_a(this.field_70143_R);
                this.field_70143_R = 0.0f;
            }
        } else if (par1 < 0.0) {
            this.field_70143_R = (float)((double)this.field_70143_R - par1);
        }
    }

    public EntityLivingBase func_70638_az() {
        EntityLivingBase e = super.func_70638_az();
        if (e != null && !e.func_70089_S()) {
            e = null;
        }
        return e;
    }

    public int func_70658_aO() {
        int var1 = super.func_70658_aO() + this.golemType.armor;
        if (this.decoration.contains("V")) {
            ++var1;
        }
        if (this.decoration.contains("P")) {
            var1 += 4;
        }
        if (var1 > 20) {
            var1 = 20;
        }
        return var1;
    }

    public boolean func_70652_k(Entity par1Entity) {
        boolean flag;
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int i = 0;
        if (par1Entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
        }
        if (flag = par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f)) {
            int j;
            if (this.decoration.contains("V")) {
                EntityUtils.setRecentlyHit((EntityLivingBase)par1Entity, 100);
            }
            if (i > 0) {
                par1Entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this) + this.getUpgradeAmount(2)) > 0) {
                par1Entity.func_70015_d(j * 4);
            }
            if (par1Entity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)par1Entity), (Entity)this);
            }
            EnchantmentHelper.func_151385_b((EntityLivingBase)this, (Entity)par1Entity);
        }
        return flag;
    }

    public boolean func_70097_a(DamageSource ds, float par2) {
        this.paused = false;
        if (ds == DamageSource.field_76367_g) {
            return false;
        }
        if (this.getGolemType() == EnumGolemType.THAUMIUM && ds == DamageSource.field_76376_m) {
            par2 *= 0.5f;
        }
        if (ds.func_76364_f() != null && this.getUpgradeAmount(5) > 0 && ds.func_76364_f().func_145782_y() != this.func_145782_y()) {
            ds.func_76364_f().func_70097_a(DamageSource.func_92087_a((Entity)this), (float)(this.getUpgradeAmount(5) * 2 + this.field_70146_Z.nextInt(2 * this.getUpgradeAmount(5))));
            ds.func_76364_f().func_85030_a("damage.thorns", 0.5f, 1.0f);
        }
        return super.func_70097_a(ds, par2);
    }

    public boolean func_70686_a(Class par1Class) {
        return EntityVillager.class != par1Class && EntityGolemBase.class != par1Class && EntityBat.class != par1Class;
    }

    public boolean isValidTarget(Entity target) {
        if (!target.func_70089_S()) {
            return false;
        }
        if (target instanceof EntityPlayer && ((EntityPlayer)target).func_70005_c_().equals(this.getOwnerName())) {
            return false;
        }
        if (!this.func_110176_b(MathHelper.func_76128_c((double)target.field_70165_t), MathHelper.func_76128_c((double)target.field_70163_u), MathHelper.func_76128_c((double)target.field_70161_v))) {
            return false;
        }
        if (this.getCore() == 9) {
            if (!(!(target instanceof EntityAnimal) && !(target instanceof IAnimals) || target instanceof IMob || target instanceof EntityTameable && ((EntityTameable)target).func_70909_n() || target instanceof EntityGolem)) {
                return !(target instanceof EntityAnimal) || !((EntityAnimal)target).func_70631_g_();
            }
        } else {
            if (this.canAttackCreepers() && this.getUpgradeAmount(4) > 0 && target instanceof EntityCreeper) {
                return true;
            }
            if (this.canAttackHostiles() && (target instanceof EntityMob || target instanceof IMob) && !(target instanceof EntityCreeper)) {
                return true;
            }
            if (!(!this.canAttackAnimals() || this.getUpgradeAmount(4) <= 0 || !(target instanceof EntityAnimal) && !(target instanceof IAnimals) || target instanceof IMob || target instanceof EntityTameable && ((EntityTameable)target).func_70909_n() || target instanceof EntityGolem)) {
                return true;
            }
            if (this.canAttackPlayers() && this.getUpgradeAmount(4) > 0 && target instanceof EntityPlayer) {
                return true;
            }
        }
        return false;
    }

    public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLiving) {
        EntityDart var2 = new EntityDart(this.field_70170_p, (EntityLivingBase)this, par1EntityLiving, 1.6f, 7.0f - (float)this.getUpgradeAmount(3) * 1.75f);
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        var2.func_70239_b(f * 0.4f);
        this.func_85030_a("thaumcraft:golemironshoot", 0.5f, 1.0f / (this.func_70681_au().nextFloat() * 0.4f + 0.6f));
        this.field_70170_p.func_72838_d((Entity)var2);
        this.startLeftArmTimer();
    }

    public int getAttackSpeed() {
        return 20 - (this.advanced ? 2 : 0);
    }

    protected String func_70639_aQ() {
        return "thaumcraft:cameraclack";
    }

    protected String func_70621_aR() {
        return "thaumcraft:cameraclack";
    }

    protected String func_70673_aS() {
        return "thaumcraft:cameraclack";
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeByte((int)this.getCore());
        data.writeBoolean(this.advanced);
        data.writeByte(this.inventory.slotCount);
        data.writeByte(this.upgrades.length);
        for (byte b : this.upgrades) {
            data.writeByte((int)b);
        }
    }

    public void readSpawnData(ByteBuf data) {
        try {
            int a;
            this.setCore(data.readByte());
            this.advanced = data.readBoolean();
            if (this.getCore() >= 0) {
                this.bootup = 0.0f;
            }
            this.inventory = new InventoryMob((Entity)this, data.readByte());
            this.colors = new byte[this.inventory.slotCount];
            for (a = 0; a < this.inventory.slotCount; ++a) {
                this.colors[a] = -1;
            }
            this.upgrades = new byte[data.readByte()];
            for (a = 0; a < this.upgrades.length; ++a) {
                this.upgrades[a] = data.readByte();
            }
            int bonus = 0;
            try {
                bonus = this.getGolemDecoration().contains("H") ? 5 : 0;
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)(this.getGolemType().health + bonus));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)("item.ItemGolemPlacer." + this.getGolemType().ordinal() + ".name"));
    }
}

