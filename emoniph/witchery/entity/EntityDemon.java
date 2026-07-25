/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAIMoveTowardsTarget
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityLargeFireball
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.ai.EntityAIAttackCloseTargetOnCollide;
import com.emoniph.witchery.entity.ai.EntityAIDemonicBarginPlayer;
import com.emoniph.witchery.entity.ai.EntityAILookAtDemonicBarginPlayer;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityDemon
extends EntityGolem
implements IRangedAttackMob,
IMerchant {
    private int attackTimer;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private int tryEscape = -1;

    public EntityDemon(World par1World) {
        super(par1World);
        this.func_70105_a(1.0f, 2.9f);
        this.field_70178_ae = true;
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAttackCloseTargetOnCollide((EntityCreature)this, 1.0, true, 3.0));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIArrowAttack((IRangedAttackMob)this, 1.0, 20, 60, 15.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIDemonicBarginPlayer(this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAILookAtDemonicBarginPlayer(this));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 0.9, 32.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 0.6, true));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.6));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityVillager.class, 0, true));
        this.field_70728_aV = 10;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(17, (Object)0);
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.demon.name");
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        if (this.field_71093_bK != Config.instance().dimensionDreamID) {
            boolean flag;
            ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
            boolean bl = flag = itemstack != null && (itemstack.func_77973_b() == Items.field_151063_bx || itemstack.func_77973_b() == Items.field_151057_cb);
            if (!flag && this.func_70089_S() && !this.isTrading() && !this.func_70631_g_()) {
                if (!this.field_70170_p.field_72995_K) {
                    this.func_70932_a_(par1EntityPlayer);
                    par1EntityPlayer.func_71030_a((IMerchant)this, this.func_70005_c_());
                }
                return true;
            }
            return super.func_70085_c(par1EntityPlayer);
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return super.func_70097_a(par1DamageSource, Math.min(par2, 15.0f));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
    }

    protected int func_70682_h(int par1) {
        return par1;
    }

    protected void func_82167_n(Entity par1Entity) {
        super.func_82167_n(par1Entity);
    }

    public void func_70636_d() {
        int k;
        int j;
        int i;
        Block l;
        super.func_70636_d();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
        if (this.field_71093_bK == Config.instance().dimensionDreamID && this.field_70170_p.field_73011_w instanceof WorldProviderDreamWorld && !((WorldProviderDreamWorld)this.field_70170_p.field_73011_w).isDemonicNightmare()) {
            this.func_70106_y();
        }
        if (this.tryEscape == 0) {
            this.tryEscape = -1;
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0f, true);
        } else if (this.tryEscape > 0) {
            --this.tryEscape;
        }
        if (this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y > 2.500000277905201E-7 && this.field_70146_Z.nextInt(5) == 0 && (l = this.field_70170_p.func_147439_a(i = MathHelper.func_76128_c((double)this.field_70165_t), j = MathHelper.func_76128_c((double)(this.field_70163_u - (double)0.2f - (double)this.field_70129_M)), k = MathHelper.func_76128_c((double)this.field_70161_v))) != Blocks.field_150350_a) {
            this.field_70170_p.func_72869_a("tilecrack_" + l + "_" + this.field_70170_p.func_72805_g(i, j, k), this.field_70165_t + ((double)this.field_70146_Z.nextFloat() - 0.5) * (double)this.field_70130_N, this.field_70121_D.field_72338_b + 0.1, this.field_70161_v + ((double)this.field_70146_Z.nextFloat() - 0.5) * (double)this.field_70130_N, 4.0 * ((double)this.field_70146_Z.nextFloat() - 0.5), 0.5, ((double)this.field_70146_Z.nextFloat() - 0.5) * 4.0);
        }
    }

    public boolean func_70686_a(Class par1Class) {
        return super.func_70686_a(par1Class);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a("PlayerCreated", this.isPlayerCreated());
        if (this.buyingList != null) {
            par1NBTTagCompound.func_74782_a("Bargains", (NBTBase)this.buyingList.func_77202_a());
        }
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.setPlayerCreated(par1NBTTagCompound.func_74767_n("PlayerCreated"));
        if (par1NBTTagCompound.func_74764_b("Bargains")) {
            NBTTagCompound nbttagcompound1 = par1NBTTagCompound.func_74775_l("Bargains");
            this.buyingList = new MerchantRecipeList(nbttagcompound1);
        }
    }

    public boolean func_70652_k(Entity par1Entity) {
        this.attackTimer = 10;
        this.field_70170_p.func_72960_a((Entity)this, (byte)4);
        boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)(7 + this.field_70146_Z.nextInt(15)));
        if (flag) {
            par1Entity.field_70181_x += (double)0.4f;
        }
        this.func_85030_a("mob.irongolem.throw", 1.0f, 1.0f);
        return flag;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 10;
            this.func_85030_a("mob.irongolem.throw", 1.0f, 1.0f);
        } else {
            super.func_70103_a(par1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    protected String func_70639_aQ() {
        return "mob.blaze.breathe";
    }

    protected String func_70621_aR() {
        return "mob.wither.hurt";
    }

    protected String func_70673_aS() {
        return "mob.wither.death";
    }

    protected void func_145780_a(int par1, int par2, int par3, Block par4) {
        this.func_85030_a("mob.irongolem.walk", 1.0f, 1.0f);
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (par1) {
            int j = this.field_70146_Z.nextInt(2 + par2);
            for (int k = 0; k < j; ++k) {
                this.func_145779_a(Items.field_151064_bs, 1);
            }
        }
    }

    protected Item func_146068_u() {
        return Items.field_151064_bs;
    }

    public boolean isPlayerCreated() {
        return (this.field_70180_af.func_75683_a(16) & 1) != 0;
    }

    public void setPlayerCreated(boolean par1) {
        this.func_110163_bv();
        byte b0 = this.field_70180_af.func_75683_a(16);
        if (par1) {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 1)));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 & 0xFFFFFFFE)));
        }
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public void func_82196_d(EntityLivingBase targetEntity, float par2) {
        if (targetEntity.func_70694_bm() == null || targetEntity.func_70694_bm().func_77973_b() != Witchery.Items.DEVILS_TONGUE_CHARM || this.field_70170_p.field_73012_v.nextDouble() < 0.05) {
            double d0 = targetEntity.field_70165_t - this.field_70165_t;
            double d1 = targetEntity.field_70121_D.field_72338_b + (double)(targetEntity.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
            double d2 = targetEntity.field_70161_v - this.field_70161_v;
            float f1 = MathHelper.func_76129_c((float)par2) * 0.5f;
            EntityLargeFireball fireballEntity = new EntityLargeFireball(this.field_70170_p, (EntityLivingBase)this, d0 + this.field_70146_Z.nextGaussian() * (double)f1, d1, d2 + this.field_70146_Z.nextGaussian() * (double)f1);
            double d8 = 1.0;
            Vec3 vec3 = this.func_70676_i(1.0f);
            fireballEntity.field_70165_t = this.field_70165_t + vec3.field_72450_a * d8;
            fireballEntity.field_70163_u = this.field_70163_u + (double)(this.field_70131_O / 2.0f) + 0.5;
            fireballEntity.field_70161_v = this.field_70161_v + vec3.field_72449_c * d8;
            if (!this.field_70170_p.field_72995_K) {
                this.field_70170_p.func_72889_a((EntityPlayer)null, 1009, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 0);
                this.field_70170_p.func_72838_d((Entity)fireballEntity);
            }
        }
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        this.func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, Integer.MAX_VALUE, 4));
        return super.func_110161_a(par1EntityLivingData);
    }

    public void func_70932_a_(EntityPlayer par1EntityPlayer) {
        this.buyingPlayer = par1EntityPlayer;
    }

    public EntityPlayer func_70931_l_() {
        return this.buyingPlayer;
    }

    public boolean isTrading() {
        return this.buyingPlayer != null;
    }

    public void func_70933_a(MerchantRecipe par1MerchantRecipe) {
        par1MerchantRecipe.func_77399_f();
        Item itemToBuy = par1MerchantRecipe.func_77394_a().func_77973_b();
        if (!(this.field_70170_p.field_72995_K || itemToBuy != Items.field_151064_bs && itemToBuy != Items.field_151072_bj)) {
            this.func_85030_a("mob.wither.shoot", this.func_70599_aP(), this.func_70647_i());
            this.tryEscape = 50;
        } else {
            this.func_85030_a("random.breath", this.func_70599_aP(), this.func_70647_i());
        }
        if (this.func_70931_l_() != null && this.func_70931_l_().func_70694_bm() != null && this.func_70931_l_().func_70694_bm().func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM) {
            this.func_70931_l_().func_70694_bm().func_77972_a(5, (EntityLivingBase)this.func_70931_l_());
            if (this.func_70931_l_().func_70694_bm().field_77994_a <= 0) {
                this.func_70931_l_().func_71028_bD();
            }
        }
    }

    public void func_110297_a_(ItemStack par1ItemStack) {
        if (!this.field_70170_p.field_72995_K && this.field_70757_a > -this.func_70627_aG() + 20) {
            this.field_70757_a = -this.func_70627_aG();
            if (par1ItemStack != null) {
                this.func_85030_a("random.breath", this.func_70599_aP(), this.func_70647_i());
            } else {
                this.func_85030_a("mob.wither.idle", this.func_70599_aP(), this.func_70647_i());
            }
        }
    }

    protected void func_70785_a(Entity entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < 2.0f && entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(entity);
        }
        super.func_70785_a(entity, par2);
    }

    public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(this.field_70146_Z.nextInt(4) + 6);
        }
        if (this.func_70931_l_() != null && this.func_70931_l_().func_70694_bm() != null && this.func_70931_l_().func_70694_bm().func_77973_b() == Witchery.Items.DEVILS_TONGUE_CHARM) {
            MerchantRecipeList list = new MerchantRecipeList();
            for (Object recipeObj : this.buyingList) {
                MerchantRecipe recipe = (MerchantRecipe)recipeObj;
                NBTTagCompound nbtTag = recipe.func_77395_g();
                MerchantRecipe recipe2 = new MerchantRecipe(nbtTag);
                ItemStack cost = recipe2.func_77394_a();
                cost.field_77994_a = Math.max(cost.field_77994_a - (cost.func_77973_b() == Items.field_151043_k ? 5 : (cost.func_77973_b() == Items.field_151166_bC ? 2 : (cost.func_77973_b() == Items.field_151045_i ? 0 : 1))), 1);
                list.add((Object)recipe2);
            }
            return list;
        }
        return this.buyingList;
    }

    private Item getCurrency() {
        double chance = this.field_70146_Z.nextDouble();
        if (chance < 0.2) {
            return Items.field_151072_bj;
        }
        if (chance < 0.4) {
            return Items.field_151064_bs;
        }
        if (chance < 0.5) {
            return Items.field_151045_i;
        }
        if (chance < 0.75) {
            return Items.field_151166_bC;
        }
        return Items.field_151043_k;
    }

    private ItemStack getPrice(int basePriceInEmeralds) {
        Item currency = this.getCurrency();
        int multiplier = currency == Items.field_151043_k ? 1 : (currency == Items.field_151166_bC ? 3 : (currency == Items.field_151045_i ? 5 : 4));
        int quantity = Math.max(1, basePriceInEmeralds / multiplier);
        return new ItemStack(currency, quantity);
    }

    private void addDefaultEquipmentAndRecipies(int par1) {
        MerchantRecipeList merchantrecipelist = new MerchantRecipeList();
        int STOCK_REDUCTION = -5;
        for (int i = 0; i < par1; ++i) {
            Enchantment enchantment = Enchantment.field_92090_c[this.field_70146_Z.nextInt(Enchantment.field_92090_c.length)];
            int k = MathHelper.func_76136_a((Random)this.field_70146_Z, (int)enchantment.func_77319_d(), (int)enchantment.func_77325_b());
            ItemStack itemstack = Items.field_151134_bR.func_92111_a(new EnchantmentData(enchantment, k));
            int j = 2 + this.field_70146_Z.nextInt(5 + k * 10) + 3 * k;
            MerchantRecipe recipe = new MerchantRecipe(this.getPrice(j), itemstack);
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        if (this.field_70146_Z.nextDouble() < 0.25) {
            MerchantRecipe recipe = new MerchantRecipe(this.getPrice(this.field_70146_Z.nextInt(3) + 8), Witchery.Items.GENERIC.itemSpectralDust.createStack(this.field_70146_Z.nextInt(4) + 3));
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        if (this.field_70146_Z.nextDouble() < 0.25) {
            MerchantRecipe recipe = new MerchantRecipe(this.getPrice(this.field_70146_Z.nextInt(3) + 8), Witchery.Items.GENERIC.itemDogTongue.createStack(this.field_70146_Z.nextInt(4) + 4));
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        if (this.field_70146_Z.nextDouble() < 0.15) {
            MerchantRecipe recipe = new MerchantRecipe(this.getPrice(this.field_70146_Z.nextInt(10) + 20), Witchery.Items.GENERIC.itemRedstoneSoup.createStack(1));
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        if (this.field_70146_Z.nextDouble() < 0.15) {
            MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151073_bk, 2));
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        if (this.field_70146_Z.nextDouble() < 0.15) {
            MerchantRecipe recipe = new MerchantRecipe(new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151079_bi, 2));
            recipe.func_82783_a(-5);
            merchantrecipelist.add((Object)recipe);
        }
        Collections.shuffle(merchantrecipelist);
        Item currencyForHeart = this.getCurrency();
        MerchantRecipe heartRecipe = new MerchantRecipe(new ItemStack(currencyForHeart, currencyForHeart == Items.field_151043_k ? 30 : 3), Witchery.Items.GENERIC.itemDemonHeart.createStack(1));
        heartRecipe.func_82783_a(-5);
        merchantrecipelist.add(this.field_70146_Z.nextInt(3), (Object)heartRecipe);
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }
        for (int j1 = 0; j1 < par1 && j1 < merchantrecipelist.size(); ++j1) {
            this.buyingList.add(merchantrecipelist.get(j1));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70930_a(MerchantRecipeList par1MerchantRecipeList) {
    }
}

