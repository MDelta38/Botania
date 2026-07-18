/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.entity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public class EntityThornChakram
extends EntityThrowable {
    private static final int MAX_BOUNCES = 16;
    boolean bounced = false;

    public EntityThornChakram(World world) {
        super(world);
    }

    public EntityThornChakram(World world, EntityLivingBase e) {
        super(world, e);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(30, (Object)0);
        this.field_70180_af.func_75682_a(31, (Object)0);
        this.field_70180_af.func_82708_h(30);
        this.field_70180_af.func_82708_h(31);
    }

    public void func_70071_h_() {
        int bounces;
        double mx = this.field_70159_w;
        double my = this.field_70181_x;
        double mz = this.field_70179_y;
        super.func_70071_h_();
        if (this.isFire()) {
            double r = 0.1;
            double m = 0.1;
            for (int i = 0; i < 3; ++i) {
                this.field_70170_p.func_72869_a("flame", this.field_70165_t + r * (Math.random() - 0.5), this.field_70163_u + r * (Math.random() - 0.5), this.field_70161_v + r * (Math.random() - 0.5), m * (Math.random() - 0.5), m * (Math.random() - 0.5), m * (Math.random() - 0.5));
            }
        }
        if ((bounces = this.getTimesBounced()) >= 16 || this.field_70173_aa > 60) {
            EntityLivingBase thrower = this.func_85052_h();
            this.field_70145_X = true;
            if (thrower == null) {
                this.dropAndKill();
            } else {
                Vector3 motion = Vector3.fromEntityCenter((Entity)thrower).sub(Vector3.fromEntityCenter((Entity)this)).normalize();
                this.field_70159_w = motion.x;
                this.field_70181_x = motion.y;
                this.field_70179_y = motion.z;
                if (MathHelper.pointDistanceSpace(this.field_70165_t, this.field_70163_u, this.field_70161_v, thrower.field_70165_t, thrower.field_70163_u, thrower.field_70161_v) < 1.0f) {
                    if (!(thrower instanceof EntityPlayer) || !((EntityPlayer)thrower).field_71075_bZ.field_75098_d && !((EntityPlayer)thrower).field_71071_by.func_70441_a(this.getItemStack())) {
                        this.dropAndKill();
                    } else if (!this.field_70170_p.field_72995_K) {
                        this.func_70106_y();
                    }
                }
            }
        } else {
            if (!this.bounced) {
                this.field_70159_w = mx;
                this.field_70181_x = my;
                this.field_70179_y = mz;
            }
            this.bounced = false;
        }
    }

    private void dropAndKill() {
        if (!this.field_70170_p.field_72995_K) {
            ItemStack stack = this.getItemStack();
            EntityItem item = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, stack);
            this.field_70170_p.func_72838_d((Entity)item);
            this.func_70106_y();
        }
    }

    private ItemStack getItemStack() {
        return new ItemStack(ModItems.thornChakram, 1, this.isFire() ? 1 : 0);
    }

    protected void func_70184_a(MovingObjectPosition pos) {
        if (this.field_70145_X) {
            return;
        }
        Block block = this.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        this.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        if (block instanceof BlockBush || block instanceof BlockLeaves) {
            return;
        }
        boolean fire = this.isFire();
        EntityLivingBase thrower = this.func_85052_h();
        if (pos.field_72308_g != null && pos.field_72308_g instanceof EntityLivingBase && pos.field_72308_g != thrower) {
            ((EntityLivingBase)pos.field_72308_g).func_70097_a(thrower != null ? (thrower instanceof EntityPlayer ? DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)thrower)) : DamageSource.func_76358_a((EntityLivingBase)thrower)) : DamageSource.field_76377_j, 12.0f);
            if (fire) {
                ((EntityLivingBase)pos.field_72308_g).func_70015_d(5);
            } else if (this.field_70170_p.field_73012_v.nextInt(3) == 0) {
                ((EntityLivingBase)pos.field_72308_g).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 60, 0));
            }
        } else {
            int bounces = this.getTimesBounced();
            if (bounces < 16) {
                Vector3 currentMovementVec = new Vector3(this.field_70159_w, this.field_70181_x, this.field_70179_y);
                ForgeDirection dir = ForgeDirection.getOrientation((int)pos.field_72310_e);
                Vector3 normalVector = new Vector3(dir.offsetX, dir.offsetY, dir.offsetZ).normalize();
                Vector3 movementVec = normalVector.multiply(-2.0 * currentMovementVec.dotProduct(normalVector)).add(currentMovementVec);
                this.field_70159_w = movementVec.x;
                this.field_70181_x = movementVec.y;
                this.field_70179_y = movementVec.z;
                this.bounced = true;
            }
        }
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    public int getTimesBounced() {
        return this.field_70180_af.func_75679_c(30);
    }

    public void setTimesBounced(int times) {
        this.field_70180_af.func_75692_b(30, (Object)times);
    }

    public boolean isFire() {
        return this.field_70180_af.func_75683_a(31) != 0;
    }

    public void setFire(boolean fire) {
        this.field_70180_af.func_75692_b(31, (Object)((byte)(fire ? 1 : 0)));
    }
}

