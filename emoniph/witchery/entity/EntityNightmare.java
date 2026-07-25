/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIBreakDoor
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveThroughVillage
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAIMoveTowardsTarget
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityNightmare
extends EntityMob
implements IEntitySelector {
    private int attackTimer;
    private int defenseTimer;

    public EntityNightmare(World par1World) {
        super(par1World);
        this.field_70178_ae = true;
        this.func_70105_a(0.6f, 1.8f);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIBreakDoor((EntityLiving)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 0.9, 32.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMoveThroughVillage((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, false, true, (IEntitySelector)this));
        this.field_70728_aV = 25;
    }

    public boolean func_82704_a(Entity entity) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            String victim = this.getVictimName();
            return victim == null || victim.isEmpty() || player.func_70005_c_().equalsIgnoreCase(victim);
        }
        return false;
    }

    public int func_70627_aG() {
        return super.func_70627_aG() * 2;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, (Object)"");
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(21, (Object)0);
    }

    public void func_70110_aj() {
    }

    protected void func_70069_a(float par1) {
    }

    public boolean isScreaming() {
        return this.field_70180_af.func_75683_a(16) > 0;
    }

    public void setScreaming(boolean par1) {
        this.field_70180_af.func_75692_b(16, (Object)((byte)(par1 ? 1 : 0)));
    }

    public boolean isDefended() {
        return this.field_70180_af.func_75683_a(21) > 0;
    }

    public void setDefended(boolean par1) {
        this.field_70180_af.func_75692_b(21, (Object)((byte)(par1 ? 1 : 0)));
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.nightmare.name");
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        super.func_70629_bd();
        if (!this.field_70170_p.field_72995_K && this.func_70089_S()) {
            if (this.func_70638_az() != null) {
                this.setScreaming(true);
            } else {
                this.setScreaming(false);
            }
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(100.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.35);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
        this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
    }

    protected void func_70626_be() {
        super.func_70626_be();
    }

    protected int func_70682_h(int par1) {
        return par1;
    }

    protected void func_82167_n(Entity par1Entity) {
        super.func_82167_n(par1Entity);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        if (this.getVictimName() == null) {
            par1NBTTagCompound.func_74778_a("Victim", "");
        } else {
            par1NBTTagCompound.func_74778_a("Victim", this.getVictimName());
        }
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        String s = par1NBTTagCompound.func_74779_i("Victim");
        if (s.length() > 0) {
            this.setVictim(s);
        }
    }

    public String getVictimName() {
        String s = this.field_70180_af.func_75681_e(17);
        return s != null ? s : "";
    }

    public void setVictim(String par1Str) {
        this.field_70180_af.func_75692_b(17, (Object)par1Str);
    }

    public void func_70636_d() {
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K) {
            if (this.defenseTimer > 0 && --this.defenseTimer == 0) {
                this.setDefended(false);
            }
            if (!this.field_70128_L && !this.getVictimName().isEmpty() && (this.func_70638_az() == null || this.func_70638_az().field_70128_L || this.func_70068_e((Entity)this.func_70638_az()) > 256.0) || this.field_70170_p.field_73012_v.nextInt(5) == 0 && this.func_70638_az() instanceof EntityPlayer && WorldProviderDreamWorld.getPlayerHasNightmare((EntityPlayer)this.func_70638_az()) == 0 && !this.isWakingNightmare((EntityPlayer)this.func_70638_az())) {
                ParticleEffect.EXPLODE.send(SoundEffect.NONE, (Entity)this, 1.0, 2.0, 16);
                this.func_70106_y();
            }
        }
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    private boolean isWakingNightmare(EntityPlayer player) {
        NBTTagCompound nbtTag = Infusion.getNBT((Entity)player);
        if (nbtTag != null && nbtTag.func_74764_b("witcheryWakingNightmare")) {
            return nbtTag.func_74762_e("witcheryWakingNightmare") > 0;
        }
        return player.func_70644_a(Witchery.Potions.WAKING_NIGHTMARE);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 4) {
            this.attackTimer = 15;
        } else {
            super.func_70103_a(par1);
        }
    }

    public boolean func_70652_k(Entity entity) {
        boolean flag;
        this.attackTimer = 15;
        this.field_70170_p.func_72960_a((Entity)this, (byte)4);
        if (entity != null && entity instanceof EntityPlayer) {
            int index;
            EntityPlayer player = (EntityPlayer)entity;
            if (!this.findInInventory(player.field_71071_by, Witchery.Items.GENERIC.itemCharmOfDisruptedDreams) && player.field_71071_by.field_70460_b[index = player.field_70170_p.field_73012_v.nextInt(player.field_71071_by.field_70460_b.length)] != null) {
                Infusion.dropEntityItemWithRandomChoice((EntityLivingBase)player, player.field_71071_by.field_70460_b[index], true);
                player.field_71071_by.field_70460_b[index] = null;
            }
        }
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        if (this.field_71093_bK != Config.instance().dimensionDreamID) {
            f = 0.5f;
        }
        int i = 0;
        if (entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity));
        }
        if (flag = entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f)) {
            int j;
            if (i > 0) {
                entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                entity.func_70015_d(j * 4);
            }
        }
        return flag;
    }

    private boolean findInInventory(InventoryPlayer inventory, ItemGeneral.SubItem item) {
        for (int i = 0; i < inventory.field_70462_a.length; ++i) {
            ItemStack stack = inventory.field_70462_a[i];
            if (stack == null || !item.isMatch(stack)) continue;
            return true;
        }
        return false;
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        EntityLivingBase living;
        if (this.isDefended()) {
            return false;
        }
        boolean weakeningWeapon = false;
        if (source instanceof EntityDamageSource && ((EntityDamageSource)source).func_76346_g() != null && ((EntityDamageSource)source).func_76346_g() instanceof EntityLivingBase && (living = (EntityLivingBase)((EntityDamageSource)source).func_76346_g()).func_70694_bm() != null && living.func_70694_bm().func_77973_b() == Witchery.Items.HUNTSMANS_SPEAR) {
            weakeningWeapon = true;
        }
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v)) != Witchery.Blocks.FLOWING_SPIRIT) {
            this.defenseTimer = this.field_71093_bK == Config.instance().dimensionDreamID ? (weakeningWeapon ? 40 : 80) : (weakeningWeapon ? 30 : 40);
            this.setDefended(true);
        }
        return super.func_70097_a(source, Math.min(damage, 15.0f));
    }

    @SideOnly(value=Side.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    protected String func_70639_aQ() {
        return "witchery:mob.nightmare.nightmare_live";
    }

    protected String func_70621_aR() {
        return "witchery:mob.nightmare.nightmare_dead";
    }

    protected String func_70673_aS() {
        return "witchery:mob.nightmare.nightmare_hit";
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (this.field_71093_bK == Config.instance().dimensionDreamID) {
            int chance = this.field_70146_Z.nextInt(Math.max(10 - par2, 5));
            int quantity = par2 > 0 && chance == 0 ? 2 : 1;
            this.func_70099_a(Witchery.Items.GENERIC.itemMellifluousHunger.createStack(quantity), 0.0f);
        }
    }

    public void func_70645_a(DamageSource source) {
        if (!this.field_70170_p.field_72995_K && source != null && source.func_76346_g() != null && source.func_76346_g() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)source.func_76346_g();
            String victim = this.getVictimName();
            if (victim != null && !victim.isEmpty() && player.func_70005_c_().equalsIgnoreCase(victim) && this.field_71093_bK == Config.instance().dimensionDreamID) {
                WorldProviderDreamWorld.setPlayerLastNightmareKillNow(player);
            }
        }
        super.func_70645_a(source);
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        return super.func_110161_a(par1EntityLivingData);
    }
}

