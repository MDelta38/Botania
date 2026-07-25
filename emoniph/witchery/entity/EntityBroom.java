/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockColored
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class EntityBroom
extends Entity {
    private boolean field_70279_a = true;
    private double speedMultiplier = 0.07;
    private int broomPosRotationIncrements;
    private double broomX;
    private double broomY;
    private double broomZ;
    private double broomYaw;
    private double broomPitch;
    @SideOnly(value=Side.CLIENT)
    private double velocityX;
    @SideOnly(value=Side.CLIENT)
    private double velocityY;
    @SideOnly(value=Side.CLIENT)
    private double velocityZ;
    boolean riderHasOwlFamiliar = false;
    boolean riderHasSoaringBrew = false;

    public EntityBroom(World world) {
        super(world);
        this.field_70156_m = true;
        this.func_70105_a(1.2f, 0.5f);
        this.field_70129_M = this.field_70131_O / 2.0f;
    }

    public EntityBroom(World world, double x, double y, double z) {
        this(world);
        this.func_70107_b(x, y + (double)this.field_70129_M, z);
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.field_70169_q = x;
        this.field_70167_r = y;
        this.field_70166_s = z;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(10, (Object)"");
        this.field_70180_af.func_75682_a(16, (Object)-1);
        this.field_70180_af.func_75682_a(17, (Object)new Integer(0));
        this.field_70180_af.func_75682_a(18, (Object)new Integer(1));
        this.field_70180_af.func_75682_a(19, (Object)new Float(0.0f));
    }

    protected void func_70081_e(int par1) {
    }

    public void setBrushColor(int color) {
        this.field_70180_af.func_75692_b(16, (Object)((byte)color));
    }

    public int getBrushColor() {
        return this.field_70180_af.func_75683_a(16);
    }

    public void setCustomNameTag(String par1Str) {
        this.field_70180_af.func_75692_b(10, (Object)par1Str);
    }

    public String getCustomNameTag() {
        return this.field_70180_af.func_75681_e(10);
    }

    public boolean hasCustomNameTag() {
        return this.field_70180_af.func_75681_e(10).length() > 0;
    }

    public AxisAlignedBB func_70114_g(Entity par1Entity) {
        return par1Entity.field_70121_D;
    }

    public AxisAlignedBB func_70046_E() {
        return this.field_70121_D;
    }

    public boolean func_70104_M() {
        return true;
    }

    public double func_70042_X() {
        return (double)this.field_70131_O * 0.55;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L) {
            boolean flag;
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0f);
            this.func_70018_K();
            boolean bl = flag = par1DamageSource.func_76346_g() instanceof EntityPlayer && ((EntityPlayer)par1DamageSource.func_76346_g()).field_71075_bZ.field_75098_d;
            if (flag || this.getDamageTaken() > 40.0f) {
                if (this.field_70153_n != null) {
                    this.field_70153_n.func_70078_a((Entity)this);
                }
                if (!flag) {
                    int brushColor;
                    ItemStack broomStack = Witchery.Items.GENERIC.itemBroomEnchanted.createStack();
                    if (this.hasCustomNameTag()) {
                        broomStack.func_151001_c(this.getCustomNameTag());
                    }
                    if ((brushColor = this.getBrushColor()) >= 0 && brushColor <= 15) {
                        Witchery.Items.GENERIC.setBroomItemColor(broomStack, brushColor);
                    }
                    this.func_70099_a(broomStack, 0.0f);
                }
                this.func_70106_y();
            }
            return true;
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70057_ab() {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0f);
    }

    public boolean func_70067_L() {
        return !this.field_70128_L && this.field_70153_n == null;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double x, double y, double z, float yaw, float pitch, int par9) {
        if (this.field_70279_a) {
            this.broomPosRotationIncrements = par9 + 5;
        } else {
            double d3 = x - this.field_70165_t;
            double d4 = y - this.field_70163_u;
            double d5 = z - this.field_70161_v;
            double d6 = d3 * d3 + d4 * d4 + d5 * d5;
            if (d6 <= 1.0) {
                return;
            }
            this.broomPosRotationIncrements = 3;
        }
        this.broomX = x;
        this.broomY = y;
        this.broomZ = z;
        this.broomYaw = yaw;
        this.broomPitch = pitch;
        this.field_70159_w = this.velocityX;
        this.field_70181_x = this.velocityY;
        this.field_70179_y = this.velocityZ;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70016_h(double x, double y, double z) {
        this.velocityX = this.field_70159_w = x;
        this.velocityY = this.field_70181_x = y;
        this.velocityZ = this.field_70179_y = z;
    }

    public void func_70071_h_() {
        double d5;
        double newHorzVelocity;
        super.func_70071_h_();
        if (this.field_70173_aa % 100 == 0 && this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer) {
            this.riderHasSoaringBrew = InfusedBrewEffect.Soaring.isActive((EntityPlayer)this.field_70153_n);
        }
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.getDamageTaken() > 0.0f) {
            this.setDamageTaken(this.getDamageTaken() - 1.0f);
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        int b0 = 5;
        double d0 = 0.0;
        double initialHorzVelocity = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        if (initialHorzVelocity > 0.26249999999999996) {
            newHorzVelocity = Math.cos((double)this.field_70177_z * Math.PI / 180.0);
            double d = Math.sin((double)this.field_70177_z * Math.PI / 180.0);
        }
        if (this.field_70170_p.field_72995_K && this.field_70279_a) {
            if (this.broomPosRotationIncrements > 0) {
                newHorzVelocity = this.field_70165_t + (this.broomX - this.field_70165_t) / (double)this.broomPosRotationIncrements;
                d5 = this.field_70163_u + (this.broomY - this.field_70163_u) / (double)this.broomPosRotationIncrements;
                double d11 = this.field_70161_v + (this.broomZ - this.field_70161_v) / (double)this.broomPosRotationIncrements;
                double d10 = MathHelper.func_76138_g((double)(this.broomYaw - (double)this.field_70177_z));
                this.field_70177_z = (float)((double)this.field_70177_z + d10 / (double)this.broomPosRotationIncrements);
                this.field_70125_A = (float)((double)this.field_70125_A + (this.broomPitch - (double)this.field_70125_A) / (double)this.broomPosRotationIncrements);
                --this.broomPosRotationIncrements;
                this.func_70107_b(newHorzVelocity, d5, d11);
                this.func_70101_b(this.field_70177_z, this.field_70125_A);
            } else {
                newHorzVelocity = this.field_70165_t + this.field_70159_w;
                d5 = this.field_70163_u + this.field_70181_x;
                double d11 = this.field_70161_v + this.field_70179_y;
                this.func_70101_b((float)((double)this.field_70177_z + (this.broomYaw - (double)this.field_70177_z)), (float)((double)this.field_70125_A + (this.broomPitch - (double)this.field_70125_A)));
                this.func_70107_b(newHorzVelocity, d5, d11);
                this.field_70159_w *= (double)0.99f;
                this.field_70179_y *= (double)0.99f;
            }
        } else {
            double d11;
            if (this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase) {
                newHorzVelocity = ((EntityLivingBase)this.field_70153_n).field_70701_bs;
                if (newHorzVelocity > 0.0) {
                    d5 = -Math.sin(this.field_70153_n.field_70177_z * (float)Math.PI / 180.0f);
                    d11 = Math.cos(this.field_70153_n.field_70177_z * (float)Math.PI / 180.0f);
                    this.field_70159_w += d5 * this.speedMultiplier * (0.1 + (this.riderHasSoaringBrew ? 0.1 : 0.0) + (this.riderHasOwlFamiliar ? 0.2 : 0.0));
                    this.field_70179_y += d11 * this.speedMultiplier * (0.1 + (this.riderHasSoaringBrew ? 0.1 : 0.0) + (this.riderHasOwlFamiliar ? 0.2 : 0.0));
                    double pitch = -Math.sin(this.field_70153_n.field_70125_A * (float)Math.PI / 180.0f);
                    if (pitch > -0.5 && pitch < 0.2) {
                        pitch = 0.0;
                    } else if (pitch < 0.0) {
                        pitch *= 0.5;
                    }
                    this.field_70181_x = pitch * this.speedMultiplier * 2.0;
                } else if (newHorzVelocity == 0.0 && (this.riderHasOwlFamiliar || this.riderHasSoaringBrew)) {
                    this.field_70159_w *= 0.9;
                    this.field_70179_y *= 0.9;
                }
            } else if (this.field_70153_n == null) {
                this.riderHasOwlFamiliar = false;
                double moX = this.field_70159_w * 0.9;
                double moZ = this.field_70179_y * 0.9;
                this.field_70159_w = Math.abs(moX) < 0.01 ? 0.0 : moX;
                double d = this.field_70179_y = Math.abs(moZ) < 0.01 ? 0.0 : moZ;
                if (!this.field_70122_E) {
                    this.field_70181_x = -0.2;
                }
            }
            newHorzVelocity = Math.sqrt(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
            double SPEED_LIMIT = 0.9 + (this.riderHasOwlFamiliar ? 0.3 : 0.0) + (this.riderHasSoaringBrew ? 0.3 : 0.0);
            if (newHorzVelocity > SPEED_LIMIT) {
                d5 = SPEED_LIMIT / newHorzVelocity;
                this.field_70159_w *= d5;
                this.field_70179_y *= d5;
                this.field_70181_x *= d5;
                newHorzVelocity = SPEED_LIMIT;
            }
            double MAX_ACCELERATION = this.riderHasSoaringBrew || this.riderHasOwlFamiliar ? 0.35 : 0.35;
            double MAX_ACCELERATION_FACTOR = MAX_ACCELERATION * 100.0;
            if (newHorzVelocity > initialHorzVelocity && this.speedMultiplier < MAX_ACCELERATION) {
                this.speedMultiplier += (MAX_ACCELERATION - this.speedMultiplier) / MAX_ACCELERATION_FACTOR;
                if (this.speedMultiplier > MAX_ACCELERATION) {
                    this.speedMultiplier = MAX_ACCELERATION;
                }
            } else {
                this.speedMultiplier -= (this.speedMultiplier - 0.07) / MAX_ACCELERATION_FACTOR;
                if (this.speedMultiplier < 0.07) {
                    this.speedMultiplier = 0.07;
                }
            }
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            this.field_70159_w *= (double)0.99f;
            this.field_70181_x *= (double)0.99f;
            this.field_70179_y *= (double)0.99f;
            this.field_70125_A = 0.0f;
            d5 = this.field_70177_z;
            d11 = this.field_70169_q - this.field_70165_t;
            double d10 = this.field_70166_s - this.field_70161_v;
            if (d11 * d11 + d10 * d10 > 0.001) {
                d5 = (float)(Math.atan2(d10, d11) * 180.0 / Math.PI);
            }
            double d12 = MathHelper.func_76138_g((double)(d5 - (double)this.field_70177_z));
            this.field_70177_z = (float)((double)this.field_70177_z + d12);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            if (!this.field_70170_p.field_72995_K) {
                List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b((double)0.2f, 0.0, (double)0.2f));
                if (list != null && !list.isEmpty()) {
                    for (int l = 0; l < list.size(); ++l) {
                        Entity entity = (Entity)list.get(l);
                        if (entity == this.field_70153_n || !entity.func_70104_M() || !(entity instanceof EntityBroom)) continue;
                        entity.func_70108_f((Entity)this);
                    }
                }
                if (this.field_70153_n != null && this.field_70153_n.field_70128_L) {
                    this.field_70153_n = null;
                }
            }
        }
    }

    public void func_70043_V() {
        super.func_70043_V();
    }

    protected void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74778_a("CustomName", this.getCustomNameTag());
        int brushColor = this.getBrushColor();
        if (brushColor >= 0) {
            par1NBTTagCompound.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
        }
    }

    protected void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        if (par1NBTTagCompound.func_74764_b("CustomName") && par1NBTTagCompound.func_74779_i("CustomName").length() > 0) {
            this.setCustomNameTag(par1NBTTagCompound.func_74779_i("CustomName"));
        }
        if (par1NBTTagCompound.func_74764_b("BrushColor") && par1NBTTagCompound.func_74771_c("BrushColor") >= 0) {
            this.setBrushColor(par1NBTTagCompound.func_74771_c("BrushColor"));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public boolean func_130002_c(EntityPlayer player) {
        if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70153_n != player) {
            return true;
        }
        if (!this.field_70170_p.field_72995_K && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == Items.field_151100_aR) {
            ItemStack itemstack = player.func_70694_bm();
            int i = BlockColored.func_150032_b((int)itemstack.func_77960_j());
            this.setBrushColor(i);
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
            }
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
            }
            return true;
        }
        if (!this.field_70170_p.field_72995_K) {
            this.riderHasOwlFamiliar = Familiar.hasActiveBroomMasteryFamiliar(player);
            this.riderHasSoaringBrew = InfusedBrewEffect.Soaring.isActive(player);
            player.func_70078_a((Entity)this);
        }
        return true;
    }

    public void setDamageTaken(float par1) {
        this.field_70180_af.func_75692_b(19, (Object)Float.valueOf(par1));
    }

    public float getDamageTaken() {
        return this.field_70180_af.func_111145_d(19);
    }

    public void setTimeSinceHit(int par1) {
        this.field_70180_af.func_75692_b(17, (Object)par1);
    }

    public int getTimeSinceHit() {
        return this.field_70180_af.func_75679_c(17);
    }

    public void setForwardDirection(int par1) {
        this.field_70180_af.func_75692_b(18, (Object)par1);
    }

    public int getForwardDirection() {
        return this.field_70180_af.func_75679_c(18);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70270_d(boolean par1) {
        this.field_70279_a = par1;
    }

    public static class EventHooks {
        @SubscribeEvent
        public void onLivingFall(LivingFallEvent event) {
            EntityPlayer player;
            if (event.entityLiving instanceof EntityPlayer && (player = (EntityPlayer)event.entityLiving).func_70115_ae() && player.field_70154_o instanceof EntityBroom) {
                event.distance = 0.0f;
            }
        }
    }
}

