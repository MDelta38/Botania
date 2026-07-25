/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityFlying
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 */
package thaumcraft.common.entities.monster;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXWispZap;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class EntityWisp
extends EntityFlying
implements IMob {
    public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;

    public EntityWisp(World world) {
        super(world);
        this.func_70105_a(0.9f, 0.9f);
        this.field_70728_aV = 5;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(22.0);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(3.0);
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public int func_70682_h(int par1) {
        return par1;
    }

    public boolean func_70097_a(DamageSource damagesource, float i) {
        if (damagesource.func_76364_f() instanceof EntityLivingBase) {
            this.targetedEntity = (EntityLivingBase)damagesource.func_76364_f();
            this.aggroCooldown = 200;
        }
        if (damagesource.func_76346_g() instanceof EntityLivingBase) {
            this.targetedEntity = (EntityLivingBase)damagesource.func_76346_g();
            this.aggroCooldown = 200;
        }
        return super.func_70097_a(damagesource, i);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(22, (Object)String.valueOf(""));
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (this.field_70170_p.field_72995_K) {
            Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)0.45f, this.field_70161_v, 1.0f);
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K && this.field_70173_aa <= 1) {
            Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)0.45f, this.field_70161_v, 1.0f);
        }
        if (this.field_70170_p.field_72995_K && this.field_70170_p.field_73012_v.nextBoolean() && Aspect.getAspect(this.getType()) != null) {
            Color color = new Color(Aspect.getAspect(this.getType()).getColor());
            Thaumcraft.proxy.wispFX(this.field_70170_p, this.field_70165_t + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), this.field_70163_u + (double)0.45f + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), this.field_70161_v + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.7f), 0.1f, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f);
        }
    }

    public String getType() {
        return this.field_70180_af.func_75681_e(22);
    }

    public void setType(String t) {
        this.field_70180_af.func_75692_b(22, (Object)String.valueOf(t));
    }

    protected void func_70626_be() {
        if (!this.field_70170_p.field_72995_K && Aspect.getAspect(this.getType()) == null) {
            BiomeGenBase bg = this.field_70170_p.func_72807_a(MathHelper.func_76143_f((double)this.field_70165_t), MathHelper.func_76143_f((double)this.field_70161_v));
            if (bg.field_76756_M == ThaumcraftWorldGenerator.biomeEerie.field_76756_M) {
                switch (this.field_70146_Z.nextInt(6)) {
                    case 0: {
                        this.setType(Aspect.DARKNESS.getTag());
                        break;
                    }
                    case 1: {
                        this.setType(Aspect.UNDEAD.getTag());
                        break;
                    }
                    case 2: {
                        this.setType(Aspect.ENTROPY.getTag());
                        break;
                    }
                    case 3: {
                        this.setType(Aspect.ELDRITCH.getTag());
                        break;
                    }
                    case 4: {
                        this.setType(Aspect.POISON.getTag());
                        break;
                    }
                    case 5: {
                        this.setType(Aspect.DEATH.getTag());
                    }
                }
            } else if (this.field_70170_p.field_73012_v.nextInt(10) != 0) {
                ArrayList<Aspect> as = Aspect.getPrimalAspects();
                this.setType(as.get(this.field_70170_p.field_73012_v.nextInt(as.size())).getTag());
            } else {
                ArrayList<Aspect> as = Aspect.getCompoundAspects();
                this.setType(as.get(this.field_70170_p.field_73012_v.nextInt(as.size())).getTag());
            }
        }
        if (!this.field_70170_p.field_72995_K && this.field_70170_p.field_73013_u.func_151525_a() == 0) {
            this.func_70106_y();
        }
        this.func_70623_bb();
        this.prevAttackCounter = this.attackCounter;
        double attackrange = 16.0;
        double d = this.waypointX - this.field_70165_t;
        double d1 = this.waypointY - this.field_70163_u;
        double d2 = this.waypointZ - this.field_70161_v;
        double d3 = d * d + d1 * d1 + d2 * d2;
        if (d3 < 1.0 || d3 > 3600.0) {
            this.waypointX = this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
            this.waypointY = this.field_70163_u + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
            this.waypointZ = this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * 2.0f - 1.0f) * 16.0;
        }
        if (this.courseChangeCooldown-- <= 0) {
            this.courseChangeCooldown += this.field_70146_Z.nextInt(5) + 2;
            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, d3 = (double)MathHelper.func_76133_a((double)d3))) {
                this.field_70159_w += d / d3 * 0.1;
                this.field_70181_x += d1 / d3 * 0.1;
                this.field_70179_y += d2 / d3 * 0.1;
            } else {
                this.waypointX = this.field_70165_t;
                this.waypointY = this.field_70163_u;
                this.waypointZ = this.field_70161_v;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.field_70128_L) {
            this.targetedEntity = null;
        }
        --this.aggroCooldown;
        if (this.field_70170_p.field_73012_v.nextInt(1000) == 0 && (this.targetedEntity == null || this.aggroCooldown-- <= 0)) {
            this.targetedEntity = this.field_70170_p.func_72856_b((Entity)this, 16.0);
            if (this.targetedEntity != null) {
                this.aggroCooldown = 50;
            }
        }
        if (this.targetedEntity != null && this.targetedEntity.func_70068_e((Entity)this) < attackrange * attackrange) {
            double d5 = this.targetedEntity.field_70165_t - this.field_70165_t;
            double d6 = this.targetedEntity.field_70121_D.field_72338_b + (double)(this.targetedEntity.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
            double d7 = this.targetedEntity.field_70161_v - this.field_70161_v;
            this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(d5, d7)) * 180.0f / 3.141593f;
            if (this.func_70685_l(this.targetedEntity)) {
                ++this.attackCounter;
                if (this.attackCounter == 20) {
                    this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:zap", 1.0f, 1.1f);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXWispZap(this.func_145782_y(), this.targetedEntity.func_145782_y()), new NetworkRegistry.TargetPoint(this.field_70170_p.field_73011_w.field_76574_g, this.field_70165_t, this.field_70163_u, this.field_70161_v, 32.0));
                    float damage = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                    if (Math.abs(this.targetedEntity.field_70159_w) > (double)0.1f || Math.abs(this.targetedEntity.field_70181_x) > (double)0.1f || Math.abs(this.targetedEntity.field_70179_y) > (double)0.1f) {
                        if (this.field_70170_p.field_73012_v.nextFloat() < 0.4f) {
                            this.targetedEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), damage);
                        }
                    } else if (this.field_70170_p.field_73012_v.nextFloat() < 0.66f) {
                        this.targetedEntity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), damage + 1.0f);
                    }
                    this.attackCounter = -20 + this.field_70170_p.field_73012_v.nextInt(20);
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        } else {
            this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0f / 3.141593f;
            if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
    }

    private boolean isCourseTraversable(double d, double d1, double d2, double d3) {
        double d4 = (this.waypointX - this.field_70165_t) / d3;
        double d5 = (this.waypointY - this.field_70163_u) / d3;
        double d6 = (this.waypointZ - this.field_70161_v) / d3;
        AxisAlignedBB axisalignedbb = this.field_70121_D.func_72329_c();
        int i = 1;
        while ((double)i < d3) {
            axisalignedbb.func_72317_d(d4, d5, d6);
            if (!this.field_70170_p.func_72945_a((Entity)this, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        int x = (int)this.waypointX;
        int y = (int)this.waypointY;
        int z = (int)this.waypointZ;
        if (this.field_70170_p.func_147439_a(x, y, z).func_149688_o().func_76224_d()) {
            return false;
        }
        for (int a = 0; a < 11; ++a) {
            if (this.field_70170_p.func_147437_c(x, y - a, z)) continue;
            return true;
        }
        return false;
    }

    protected String func_70639_aQ() {
        return "thaumcraft:wisplive";
    }

    protected String func_70621_aR() {
        return "random.fizz";
    }

    protected String func_70673_aS() {
        return "thaumcraft:wispdead";
    }

    protected Item func_146068_u() {
        return Item.func_150899_d((int)0);
    }

    protected void func_70628_a(boolean flag, int i) {
        if (Aspect.getAspect(this.getType()) != null) {
            ItemStack ess = new ItemStack(ConfigItems.itemWispEssence);
            AspectList al = new AspectList();
            ((ItemWispEssence)ess.func_77973_b()).setAspects(ess, new AspectList().add(Aspect.getAspect(this.getType()), 2));
            this.func_70099_a(ess, 0.0f);
        }
    }

    protected float func_70599_aP() {
        return 0.25f;
    }

    protected boolean func_70692_ba() {
        return true;
    }

    public boolean func_70601_bi() {
        int count = 0;
        try {
            List l = this.field_70170_p.func_72872_a(EntityWisp.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
            if (l != null) {
                count = l.size();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return count < 8 && this.field_70170_p.field_73013_u.func_151525_a() > 0 && this.isValidLightLevel() && super.func_70601_bi();
    }

    protected boolean isValidLightLevel() {
        int k;
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b), k = MathHelper.func_76128_c((double)this.field_70161_v)) > this.field_70146_Z.nextInt(32)) {
            return false;
        }
        int l = this.field_70170_p.func_72957_l(i, j, k);
        if (this.field_70170_p.func_72911_I()) {
            int i1 = this.field_70170_p.field_73008_k;
            this.field_70170_p.field_73008_k = 10;
            l = this.field_70170_p.func_72957_l(i, j, k);
            this.field_70170_p.field_73008_k = i1;
        }
        return l <= this.field_70146_Z.nextInt(8);
    }

    public void func_70014_b(NBTTagCompound nbttagcompound) {
        super.func_70014_b(nbttagcompound);
        nbttagcompound.func_74778_a("Type", this.getType());
    }

    public void func_70037_a(NBTTagCompound nbttagcompound) {
        super.func_70037_a(nbttagcompound);
        this.setType(nbttagcompound.func_74779_i("Type"));
    }

    public int func_70641_bl() {
        return 2;
    }
}

