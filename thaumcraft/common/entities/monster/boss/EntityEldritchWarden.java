/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 */
package thaumcraft.common.entities.monster.boss;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.ai.combat.AIAttackOnCollide;
import thaumcraft.common.entities.ai.combat.AILongRangeAttack;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.projectile.EntityEldritchOrb;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockArc;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.network.fx.PacketFXSonic;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityEldritchWarden
extends EntityThaumcraftBoss
implements IRangedAttackMob,
IEldritchMob {
    String[] titles = new String[]{"Aphoom-Zhah", "Basatan", "Chaugnar Faugn", "Mnomquah", "Nyogtha", "Oorn", "Shaikorth", "Rhan-Tegoth", "Rhogog", "Shudde M'ell", "Vulthoom", "Yag-Kosha", "Yibb-Tstll", "Zathog", "Zushakon"};
    boolean fieldFrenzy = false;
    int fieldFrenzyCounter = 0;
    boolean lastBlast = false;
    public float armLiftL = 0.0f;
    public float armLiftR = 0.0f;

    public EntityEldritchWarden(World p_i1745_1_) {
        super(p_i1745_1_);
        this.func_70661_as().func_75498_b(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new AILongRangeAttack(this, 3.0, 1.0, 20, 40, 24.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.1, false));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIMoveTowardsRestriction((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityCultist.class, 0, true));
        this.func_70105_a(1.5f, 3.5f);
    }

    @Override
    public void generateName() {
        int t = (int)this.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
        if (t >= 0) {
            this.func_94058_c(String.format(StatCollector.func_74838_a((String)"entity.Thaumcraft.EldritchWarden.name"), this.getTitle(), ChampionModifier.mods[t].getModNameLocalized()));
        }
    }

    private String getTitle() {
        return this.titles[this.func_70096_w().func_75683_a(16)];
    }

    private void setTitle(int title) {
        this.field_70180_af.func_75692_b(16, (Object)((byte)title));
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.33);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.func_70096_w().func_75682_a(16, (Object)0);
    }

    @Override
    public void func_70014_b(NBTTagCompound nbt) {
        super.func_70014_b(nbt);
        nbt.func_74774_a("title", this.func_70096_w().func_75683_a(16));
    }

    @Override
    public void func_70037_a(NBTTagCompound nbt) {
        super.func_70037_a(nbt);
        this.setTitle(nbt.func_74771_c("title"));
    }

    public int func_70658_aO() {
        return super.func_70658_aO() + 4;
    }

    @Override
    protected void func_70619_bc() {
        if (this.fieldFrenzyCounter == 0) {
            super.func_70619_bc();
        }
        if (this.field_70172_ad <= 0 && this.field_70173_aa % 25 == 0) {
            int bh = (int)(this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66);
            if (this.func_110139_bj() < (float)bh) {
                this.func_110149_m(this.func_110139_bj() + 1.0f);
            }
        }
    }

    @Override
    public void func_70071_h_() {
        if (this.getSpawnTimer() == 150) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)18);
        }
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            if (this.armLiftL > 0.0f) {
                this.armLiftL -= 0.05f;
            }
            if (this.armLiftR > 0.0f) {
                this.armLiftR -= 0.05f;
            }
            float x = (float)(this.field_70165_t + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f));
            float z = (float)(this.field_70161_v + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f));
            Thaumcraft.proxy.wispFXEG(this.field_70170_p, x, (float)(this.field_70163_u + 0.25 * (double)this.field_70131_O), z, (Entity)this);
            if (this.spawnTimer > 0) {
                float he = Math.max(1.0f, this.field_70131_O * ((float)(150 - this.spawnTimer) / 150.0f));
                for (int a = 0; a < 33; ++a) {
                    Thaumcraft.proxy.smokeSpiral(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b + (double)(he / 2.0f), this.field_70161_v, he, this.field_70146_Z.nextInt(360), MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b) - 1, 0x22112F);
                }
            }
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        int j = MathHelper.func_76128_c((double)this.field_70163_u);
        int k = MathHelper.func_76128_c((double)this.field_70161_v);
        for (int l = 0; l < 4; ++l) {
            i = MathHelper.func_76128_c((double)(this.field_70165_t + (double)((float)(l % 2 * 2 - 1) * 0.25f)));
            if (!this.field_70170_p.func_147437_c(i, j = MathHelper.func_76128_c((double)this.field_70163_u), k = MathHelper.func_76128_c((double)(this.field_70161_v + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25f))))) continue;
            this.field_70170_p.func_147465_d(i, j, k, ConfigBlocks.blockAiry, 11, 3);
        }
        if (!this.field_70170_p.field_72995_K && this.fieldFrenzyCounter > 0) {
            if (this.fieldFrenzyCounter == 150) {
                this.teleportHome();
            }
            this.performFieldFrenzy();
        }
    }

    private void performFieldFrenzy() {
        if (this.fieldFrenzyCounter < 121 && this.fieldFrenzyCounter % 10 == 0) {
            this.field_70170_p.func_72960_a((Entity)this, (byte)17);
            double radius = (double)(150 - this.fieldFrenzyCounter) / 8.0;
            int d = 1 + this.fieldFrenzyCounter / 8;
            int i = MathHelper.func_76128_c((double)this.field_70165_t);
            int j = MathHelper.func_76128_c((double)this.field_70163_u);
            int k = MathHelper.func_76128_c((double)this.field_70161_v);
            for (int q = 0; q < 180 / d; ++q) {
                int deltaZ;
                double radians = Math.toRadians(q * 2 * d);
                int deltaX = (int)(radius * Math.cos(radians));
                if (!this.field_70170_p.func_147437_c(i + deltaX, j, k + (deltaZ = (int)(radius * Math.sin(radians)))) || !this.field_70170_p.func_147445_c(i + deltaX, j - 1, k + deltaZ, false)) continue;
                this.field_70170_p.func_147465_d(i + deltaX, j, k + deltaZ, ConfigBlocks.blockAiry, 11, 3);
                this.field_70170_p.func_147464_a(i + deltaX, j, k + deltaZ, ConfigBlocks.blockAiry, 250 + this.field_70146_Z.nextInt(150));
                if (this.field_70146_Z.nextFloat() < 0.3f) {
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockArc(i + deltaX, j, k + deltaZ, this.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)(i + deltaX), (double)j, (double)(k + deltaZ), 32.0));
                    continue;
                }
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(i + deltaX, j, k + deltaZ, 0x800080), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, (double)(i + deltaX), (double)j, (double)(k + deltaZ), 32.0));
            }
            this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:zap", 1.0f, 0.9f + this.field_70146_Z.nextFloat() * 0.1f);
        }
        --this.fieldFrenzyCounter;
    }

    protected void teleportHome() {
        int k;
        int j;
        EnderTeleportEvent event = new EnderTeleportEvent((EntityLivingBase)this, (double)this.func_110172_bL().field_71574_a, (double)this.func_110172_bL().field_71572_b, (double)this.func_110172_bL().field_71573_c, 0.0f);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return;
        }
        double d3 = this.field_70165_t;
        double d4 = this.field_70163_u;
        double d5 = this.field_70161_v;
        this.field_70165_t = event.targetX;
        this.field_70163_u = event.targetY;
        this.field_70161_v = event.targetZ;
        boolean flag = false;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72899_e(i, j = MathHelper.func_76128_c((double)this.field_70163_u), k = MathHelper.func_76128_c((double)this.field_70161_v))) {
            boolean flag1 = false;
            int tries = 20;
            while (!flag1 && tries > 0) {
                Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
                Block block2 = this.field_70170_p.func_147439_a(i, j, k);
                if (block.func_149688_o().func_76230_c() && !block2.func_149688_o().func_76230_c()) {
                    flag1 = true;
                    continue;
                }
                i = MathHelper.func_76128_c((double)this.field_70165_t) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
                k = MathHelper.func_76128_c((double)this.field_70161_v) + this.field_70146_Z.nextInt(8) - this.field_70146_Z.nextInt(8);
                --tries;
            }
            if (flag1) {
                this.func_70107_b((double)i + 0.5, (double)j + 0.1, (double)k + 0.5);
                if (this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty()) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.func_70107_b(d3, d4, d5);
            return;
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
    }

    @Override
    public boolean func_85032_ar() {
        return this.fieldFrenzyCounter > 0 || super.func_85032_ar();
    }

    @Override
    public boolean func_70097_a(DamageSource source, float damage) {
        if (this.func_85032_ar() || source == DamageSource.field_76369_e || source == DamageSource.field_82727_n) {
            return false;
        }
        boolean aef = super.func_70097_a(source, damage);
        if (!this.field_70170_p.field_72995_K && aef && !this.fieldFrenzy && this.func_110139_bj() <= 0.0f) {
            this.fieldFrenzy = true;
            this.fieldFrenzyCounter = 150;
        }
        return aef;
    }

    @Override
    public IEntityLivingData func_110161_a(IEntityLivingData p_110161_1_) {
        this.spawnTimer = 150;
        this.setTitle(this.field_70146_Z.nextInt(this.titles.length));
        this.func_110149_m((float)((double)this.func_110139_bj() + this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * 0.66));
        return super.func_110161_a(p_110161_1_);
    }

    public float func_70047_e() {
        return 3.1f;
    }

    public void func_82196_d(EntityLivingBase entitylivingbase, float f) {
        if (this.field_70146_Z.nextFloat() > 0.2f) {
            EntityEldritchOrb blast = new EntityEldritchOrb(this.field_70170_p, (EntityLivingBase)this);
            this.lastBlast = !this.lastBlast;
            this.field_70170_p.func_72960_a((Entity)this, this.lastBlast ? (byte)16 : 15);
            int rr = this.lastBlast ? 90 : 180;
            double xx = MathHelper.func_76134_b((float)((this.field_70177_z + (float)rr) % 360.0f / 180.0f * (float)Math.PI)) * 0.5f;
            double yy = 0.13;
            double zz = MathHelper.func_76126_a((float)((this.field_70177_z + (float)rr) % 360.0f / 180.0f * (float)Math.PI)) * 0.5f;
            blast.func_70107_b(blast.field_70165_t - xx, blast.field_70163_u - yy, blast.field_70161_v - zz);
            double d0 = entitylivingbase.field_70165_t + entitylivingbase.field_70159_w - this.field_70165_t;
            double d1 = entitylivingbase.field_70163_u - this.field_70163_u - (double)(entitylivingbase.field_70131_O / 2.0f);
            double d2 = entitylivingbase.field_70161_v + entitylivingbase.field_70179_y - this.field_70161_v;
            blast.func_70186_c(d0, d1, d2, 1.0f, 2.0f);
            this.func_85030_a("thaumcraft:egattack", 2.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
            this.field_70170_p.func_72838_d((Entity)blast);
        } else if (this.func_70685_l((Entity)entitylivingbase)) {
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXSonic(this.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0));
            entitylivingbase.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 1.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * 1.5f));
            try {
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 400, 0));
                entitylivingbase.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 400, 0));
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (entitylivingbase instanceof EntityPlayer) {
                Thaumcraft.addWarpToPlayer((EntityPlayer)entitylivingbase, 3 + this.field_70170_p.field_73012_v.nextInt(3), true);
            }
            this.func_85030_a("thaumcraft:egscreech", 4.0f, 1.0f + this.field_70146_Z.nextFloat() * 0.1f);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte p_70103_1_) {
        if (p_70103_1_ == 15) {
            this.armLiftL = 0.5f;
        } else if (p_70103_1_ == 16) {
            this.armLiftR = 0.5f;
        } else if (p_70103_1_ == 17) {
            this.armLiftL = 0.9f;
            this.armLiftR = 0.9f;
        } else if (p_70103_1_ == 18) {
            this.spawnTimer = 150;
        } else {
            super.func_70103_a(p_70103_1_);
        }
    }

    @Override
    protected void func_70600_l(int fortune) {
        super.func_70600_l(fortune);
    }

    public boolean func_70686_a(Class clazz) {
        if (clazz == EntityEldritchGuardian.class) {
            return false;
        }
        return super.func_70686_a(clazz);
    }

    protected String func_70639_aQ() {
        return "thaumcraft:egidle";
    }

    protected String func_70673_aS() {
        return "thaumcraft:egdeath";
    }

    public int func_70627_aG() {
        return 500;
    }
}

