/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveTowardsRestriction
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityLookHelper
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import thaumcraft.common.entities.ai.misc.AIWander;

public class EntityWatcher
extends EntityMob {
    private float field_175482_b;
    private float field_175484_c;
    private float field_175483_bk;
    private float field_175485_bl;
    private float field_175486_bm;
    private EntityLivingBase field_175478_bn;
    private int field_175479_bo;
    private boolean field_175480_bp;
    private AIWander wander;
    private EntityMoveHelper moveHelper;
    private GuardianLookHelper lookHelper;
    IEntitySelector field_82192_a = new IEntitySelector(){

        public boolean func_82704_a(Entity ent) {
            return true;
        }
    };

    public EntityWatcher(World worldIn) {
        super(worldIn);
        this.field_70728_aV = 10;
        this.func_70105_a(0.85f, 0.85f);
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new AIGuardianAttack());
        EntityAIMoveTowardsRestriction entityaimovetowardsrestriction = new EntityAIMoveTowardsRestriction((EntityCreature)this, 1.0);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)entityaimovetowardsrestriction);
        this.wander = new AIWander((EntityCreature)this, 1.0);
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)this.wander);
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityWatcher.class, 12.0f, 0.01f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.wander.func_75248_a(3);
        entityaimovetowardsrestriction.func_75248_a(3);
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityLivingBase.class, 10, true, false));
        this.lookHelper = new GuardianLookHelper((EntityLiving)this);
        this.moveHelper = new GuardianMoveHelper();
        this.field_175484_c = this.field_175482_b = this.field_70146_Z.nextFloat();
        this.field_70178_ae = true;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(6.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(30.0);
    }

    public void func_70037_a(NBTTagCompound tagCompund) {
        super.func_70037_a(tagCompund);
    }

    public void func_70014_b(NBTTagCompound tagCompound) {
        super.func_70014_b(tagCompound);
    }

    public GuardianLookHelper getLookHelper() {
        return this.lookHelper;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(16, (Object)0);
        this.field_70180_af.func_75682_a(17, (Object)0);
    }

    private boolean getFlags(int p_175468_1_) {
        return (this.field_70180_af.func_75679_c(16) & p_175468_1_) != 0;
    }

    private void setFlags(int p_175473_1_, boolean p_175473_2_) {
        int j = this.field_70180_af.func_75679_c(16);
        if (p_175473_2_) {
            this.field_70180_af.func_75692_b(16, (Object)(j | p_175473_1_));
        } else {
            this.field_70180_af.func_75692_b(16, (Object)(j & ~p_175473_1_));
        }
    }

    public boolean isGazing() {
        return this.getFlags(2);
    }

    private void setGazing(boolean p_175476_1_) {
        this.setFlags(2, p_175476_1_);
    }

    public int func_175464_ck() {
        return this.func_175461_cl() ? 60 : 80;
    }

    public boolean func_175461_cl() {
        return this.getFlags(4);
    }

    private void func_175463_b(int p_175463_1_) {
        this.field_70180_af.func_75692_b(17, (Object)p_175463_1_);
    }

    public boolean func_175474_cn() {
        return this.field_70180_af.func_75679_c(17) != 0;
    }

    public EntityLivingBase getTargetedEntity() {
        if (!this.func_175474_cn()) {
            return null;
        }
        if (this.field_70170_p.field_72995_K) {
            if (this.field_175478_bn != null) {
                return this.field_175478_bn;
            }
            Entity entity = this.field_70170_p.func_73045_a(this.field_70180_af.func_75679_c(17));
            if (entity instanceof EntityLivingBase) {
                this.field_175478_bn = (EntityLivingBase)entity;
                return this.field_175478_bn;
            }
            return null;
        }
        return this.func_70638_az();
    }

    public void func_145781_i(int p_145781_1_) {
        super.func_145781_i(p_145781_1_);
        if (p_145781_1_ == 16) {
            if (this.func_175461_cl() && this.field_70130_N < 1.0f) {
                this.func_70105_a(1.9975f, 1.9975f);
            }
        } else if (p_145781_1_ == 17) {
            this.field_175479_bo = 0;
            this.field_175478_bn = null;
        }
    }

    public int func_70627_aG() {
        return 160;
    }

    protected String func_70639_aQ() {
        return !this.func_70090_H() ? "mob.guardian.land.idle" : (this.func_175461_cl() ? "mob.guardian.elder.idle" : "mob.guardian.idle");
    }

    protected String func_70621_aR() {
        return !this.func_70090_H() ? "mob.guardian.land.hit" : (this.func_175461_cl() ? "mob.guardian.elder.hit" : "mob.guardian.hit");
    }

    protected String func_70673_aS() {
        return !this.func_70090_H() ? "mob.guardian.land.death" : (this.func_175461_cl() ? "mob.guardian.elder.death" : "mob.guardian.death");
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public float func_70047_e() {
        return this.field_70131_O * 0.5f;
    }

    public float func_70783_a(int x, int y, int z) {
        return this.field_70170_p.func_147437_c(x, y, z) ? 10.0f : super.func_70783_a(x, y, z);
    }

    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K) {
            this.field_175484_c = this.field_175482_b;
            this.field_175483_bk = this.isGazing() ? (this.field_175483_bk < 0.5f ? 4.0f : (this.field_175483_bk += (0.5f - this.field_175483_bk) * 0.1f)) : (this.field_175483_bk += (0.125f - this.field_175483_bk) * 0.2f);
            this.field_175482_b += this.field_175483_bk;
            this.field_175486_bm = this.field_175485_bl;
            this.field_175485_bl = this.isGazing() ? (this.field_175485_bl += (0.0f - this.field_175485_bl) * 0.25f) : (this.field_175485_bl += (1.0f - this.field_175485_bl) * 0.06f);
            if (this.isGazing()) {
                Vec3 vec3 = this.func_70676_i(0.0f);
                for (int i = 0; i < 2; ++i) {
                    this.field_70170_p.func_72869_a("bubble", this.field_70165_t + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N - vec3.field_72450_a * 1.5, this.field_70163_u + this.field_70146_Z.nextDouble() * (double)this.field_70131_O - vec3.field_72448_b * 1.5, this.field_70161_v + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N - vec3.field_72449_c * 1.5, 0.0, 0.0, 0.0);
                }
            }
            if (this.func_175474_cn()) {
                EntityLivingBase entitylivingbase;
                if (this.field_175479_bo < this.func_175464_ck()) {
                    ++this.field_175479_bo;
                }
                if ((entitylivingbase = this.getTargetedEntity()) != null) {
                    this.getLookHelper().func_75651_a((Entity)entitylivingbase, 90.0f, 90.0f);
                    this.getLookHelper().func_75649_a();
                    double d5 = this.func_175477_p(0.0f);
                    double d0 = entitylivingbase.field_70165_t - this.field_70165_t;
                    double d1 = entitylivingbase.field_70163_u + (double)(entitylivingbase.field_70131_O * 0.5f) - (this.field_70163_u + (double)this.func_70047_e());
                    double d2 = entitylivingbase.field_70161_v - this.field_70161_v;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    double d4 = this.field_70146_Z.nextDouble();
                    while (d4 < d3) {
                        this.field_70170_p.func_72869_a("bubble", this.field_70165_t + d0 * (d4 += 1.8 - d5 + this.field_70146_Z.nextDouble() * (1.7 - d5)), this.field_70163_u + d1 * d4 + (double)this.func_70047_e(), this.field_70161_v + d2 * d4, 0.0, 0.0, 0.0);
                    }
                }
            }
        }
        if (this.func_175474_cn()) {
            this.field_70177_z = this.field_70759_as;
        }
        super.func_70636_d();
    }

    @SideOnly(value=Side.CLIENT)
    public float func_175471_a(float p_175471_1_) {
        return this.field_175484_c + (this.field_175482_b - this.field_175484_c) * p_175471_1_;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_175469_o(float p_175469_1_) {
        return this.field_175486_bm + (this.field_175485_bl - this.field_175486_bm) * p_175469_1_;
    }

    public float func_175477_p(float p_175477_1_) {
        return ((float)this.field_175479_bo + p_175477_1_) / (float)this.func_175464_ck();
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.func_175461_cl() && !this.func_110175_bO()) {
            this.func_110171_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, 16);
        }
    }

    protected boolean func_70814_o() {
        return true;
    }

    public boolean func_70097_a(DamageSource source, float amount) {
        if (!this.isGazing() && !source.func_82725_o() && source.func_76364_f() instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)source.func_76364_f();
            if (!source.func_94541_c()) {
                entitylivingbase.func_70097_a(DamageSource.func_92087_a((Entity)this), 2.0f);
                entitylivingbase.func_85030_a("damage.thorns", 0.5f, 1.0f);
            }
        }
        this.wander.setWander();
        return super.func_70097_a(source, amount);
    }

    public int func_70646_bf() {
        return 180;
    }

    public void func_70612_e(float p_70612_1_, float p_70612_2_) {
        this.func_70060_a(p_70612_1_, p_70612_2_, 0.1f);
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= (double)0.9f;
        this.field_70181_x *= (double)0.9f;
        this.field_70179_y *= (double)0.9f;
    }

    class GuardianMoveHelper
    extends EntityMoveHelper {
        private EntityWatcher field_179930_g;

        public GuardianMoveHelper() {
            super((EntityLiving)EntityWatcher.this);
            this.field_179930_g = EntityWatcher.this;
        }

        double getX() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, (Object)((Object)this), (String[])new String[]{"posX", "field_75646_b"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        double getY() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, (Object)((Object)this), (String[])new String[]{"posY", "field_75647_c"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        double getZ() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianMoveHelper.class, (Object)((Object)this), (String[])new String[]{"posZ", "field_75644_d"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        public void func_75641_c() {
            if (this.func_75640_a() && !this.field_179930_g.func_70661_as().func_75500_f()) {
                double d0 = this.getX() - this.field_179930_g.field_70165_t;
                double d1 = this.getY() - this.field_179930_g.field_70163_u;
                double d2 = this.getZ() - this.field_179930_g.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = MathHelper.func_76133_a((double)d3);
                d1 /= d3;
                float f = (float)(Math.atan2(d2, d0) * 180.0 / Math.PI) - 90.0f;
                this.field_179930_g.field_70761_aq = this.field_179930_g.field_70177_z = this.limitAngle(this.field_179930_g.field_70177_z, f, 30.0f);
                float f1 = (float)(this.func_75638_b() * this.field_179930_g.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
                this.field_179930_g.func_70659_e(this.field_179930_g.func_70689_ay() + (f1 - this.field_179930_g.func_70689_ay()) * 0.125f);
                double d4 = Math.sin((double)(this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.5) * 0.05;
                double d5 = Math.cos(this.field_179930_g.field_70177_z * (float)Math.PI / 180.0f);
                double d6 = Math.sin(this.field_179930_g.field_70177_z * (float)Math.PI / 180.0f);
                this.field_179930_g.field_70159_w += d4 * d5;
                this.field_179930_g.field_70179_y += d4 * d6;
                d4 = Math.sin((double)(this.field_179930_g.field_70173_aa + this.field_179930_g.func_145782_y()) * 0.75) * 0.05;
                this.field_179930_g.field_70181_x += d4 * (d6 + d5) * 0.25;
                this.field_179930_g.field_70181_x += (double)this.field_179930_g.func_70689_ay() * d1 * 0.1;
                GuardianLookHelper entitylookhelper = this.field_179930_g.getLookHelper();
                double d7 = this.field_179930_g.field_70165_t + d0 / d3 * 2.0;
                double d8 = (double)this.field_179930_g.func_70047_e() + this.field_179930_g.field_70163_u + d1 / d3 * 1.0;
                double d9 = this.field_179930_g.field_70161_v + d2 / d3 * 2.0;
                double d10 = entitylookhelper.getX();
                double d11 = entitylookhelper.getY();
                double d12 = entitylookhelper.getZ();
                if (!entitylookhelper.getLooking()) {
                    d10 = d7;
                    d11 = d8;
                    d12 = d9;
                }
                this.field_179930_g.getLookHelper().func_75650_a(d10 + (d7 - d10) * 0.125, d11 + (d8 - d11) * 0.125, d12 + (d9 - d12) * 0.125, 10.0f, 40.0f);
                this.field_179930_g.setGazing(true);
            } else {
                this.field_179930_g.func_70659_e(0.0f);
                this.field_179930_g.setGazing(false);
            }
        }

        private float limitAngle(float p_75639_1_, float p_75639_2_, float p_75639_3_) {
            float f3 = MathHelper.func_76142_g((float)(p_75639_2_ - p_75639_1_));
            if (f3 > p_75639_3_) {
                f3 = p_75639_3_;
            }
            if (f3 < -p_75639_3_) {
                f3 = -p_75639_3_;
            }
            return p_75639_1_ + f3;
        }
    }

    class GuardianLookHelper
    extends EntityLookHelper {
        public GuardianLookHelper(EntityLiving p_i1613_1_) {
            super(p_i1613_1_);
        }

        double getX() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, (Object)((Object)this), (String[])new String[]{"posX", "field_75656_e"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        double getY() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, (Object)((Object)this), (String[])new String[]{"posY", "field_75653_f"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        double getZ() {
            try {
                return (Double)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, (Object)((Object)this), (String[])new String[]{"posZ", "field_75654_g"});
            }
            catch (Exception e) {
                return 0.0;
            }
        }

        boolean getLooking() {
            try {
                return (Boolean)ReflectionHelper.getPrivateValue(GuardianLookHelper.class, (Object)((Object)this), (String[])new String[]{"isLooking", "field_75655_d"});
            }
            catch (Exception e) {
                return false;
            }
        }
    }

    class AIGuardianAttack
    extends EntityAIBase {
        private EntityWatcher field_179456_a;
        private int field_179455_b;
        private static final String __OBFID = "CL_00002211";

        public AIGuardianAttack() {
            this.field_179456_a = EntityWatcher.this;
            this.func_75248_a(3);
        }

        public boolean func_75250_a() {
            EntityLivingBase entitylivingbase = this.field_179456_a.func_70638_az();
            return entitylivingbase != null && entitylivingbase.func_70089_S();
        }

        public boolean func_75253_b() {
            return super.func_75253_b() && (this.field_179456_a.func_175461_cl() || this.field_179456_a.func_70068_e((Entity)this.field_179456_a.func_70638_az()) > 9.0);
        }

        public void func_75249_e() {
            this.field_179455_b = -10;
            this.field_179456_a.func_70661_as().func_75499_g();
            this.field_179456_a.getLookHelper().func_75651_a((Entity)this.field_179456_a.func_70638_az(), 90.0f, 90.0f);
            this.field_179456_a.field_70160_al = true;
        }

        public void func_75251_c() {
            this.field_179456_a.func_175463_b(0);
            this.field_179456_a.func_70624_b(null);
            this.field_179456_a.wander.setWander();
        }

        public void func_75246_d() {
            EntityLivingBase entitylivingbase = this.field_179456_a.func_70638_az();
            this.field_179456_a.func_70661_as().func_75499_g();
            this.field_179456_a.getLookHelper().func_75651_a((Entity)entitylivingbase, 90.0f, 90.0f);
            if (!this.field_179456_a.func_70685_l((Entity)entitylivingbase)) {
                this.field_179456_a.func_70624_b(null);
            } else {
                ++this.field_179455_b;
                if (this.field_179455_b == 0) {
                    this.field_179456_a.func_175463_b(this.field_179456_a.func_70638_az().func_145782_y());
                    this.field_179456_a.field_70170_p.func_72960_a((Entity)this.field_179456_a, (byte)21);
                } else if (this.field_179455_b >= this.field_179456_a.func_175464_ck()) {
                    float f = 1.0f;
                    if (this.field_179456_a.field_70170_p.field_73013_u == EnumDifficulty.HARD) {
                        f += 2.0f;
                    }
                    if (this.field_179456_a.func_175461_cl()) {
                        f += 2.0f;
                    }
                    entitylivingbase.func_70097_a(DamageSource.func_76354_b((Entity)this.field_179456_a, (Entity)this.field_179456_a), f);
                    entitylivingbase.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.field_179456_a), (float)this.field_179456_a.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
                    this.field_179456_a.func_70624_b(null);
                } else if (this.field_179455_b < 60 || this.field_179455_b % 20 == 0) {
                    // empty if block
                }
                super.func_75246_d();
            }
        }
    }
}

