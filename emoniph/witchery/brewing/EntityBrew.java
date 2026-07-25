/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.brewing.EntityThrowableBase;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBrew
extends EntityThrowableBase {
    private ItemStack brewStack;
    private int color;
    private boolean isSpell;

    public EntityBrew(World world) {
        super(world);
    }

    public EntityBrew(World world, EntityLivingBase thrower, ItemStack brewStack, boolean isSpell) {
        super(world, thrower, isSpell ? 0.0f : -20.0f);
        this.brewStack = brewStack;
        this.setIsSpell(isSpell);
        this.setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
    }

    public EntityBrew(World world, double x, double y, double z, ItemStack brewStack, boolean isSpell) {
        super(world, x, y, z, isSpell ? 0.0f : -20.0f);
        this.brewStack = brewStack;
        this.setIsSpell(isSpell);
        this.setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(brewStack.func_77978_p()));
    }

    @Override
    protected void func_70088_a() {
        this.field_70180_af.func_75682_a(6, (Object)0);
        this.field_70180_af.func_75682_a(12, (Object)0);
        super.func_70088_a();
    }

    protected void setColor(int color) {
        this.func_70096_w().func_75692_b(6, (Object)color);
    }

    public int getColor() {
        return this.func_70096_w().func_75679_c(6);
    }

    protected void setIsSpell(boolean spell) {
        this.func_70096_w().func_75692_b(12, (Object)(spell ? (byte)1 : 0));
    }

    public boolean getIsSpell() {
        return this.func_70096_w().func_75683_a(12) == 1;
    }

    public ItemStack getBrew() {
        return this.brewStack;
    }

    @Override
    protected float getGravityVelocity() {
        return this.getIsSpell() ? 0.0f : 0.05f;
    }

    @Override
    protected float func_70182_d() {
        return this.getIsSpell() ? 4.0f : 0.75f;
    }

    @Override
    protected float func_70183_g() {
        return this.getIsSpell() ? 0.0f : -20.0f;
    }

    @Override
    protected void onImpact(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K && mop != null && WitcheryBrewRegistry.INSTANCE.impactSplashPotion(this.field_70170_p, this.brewStack, mop, new ModifiersImpact(new EntityPosition(this), false, 0, EntityUtil.playerOrFake(this.field_70170_p, this.getThrower())))) {
            this.field_70170_p.func_72926_e(2002, MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), this.getColor());
        }
        this.func_70106_y();
    }

    @Override
    public void func_70037_a(NBTTagCompound nbtRoot) {
        super.func_70037_a(nbtRoot);
        if (nbtRoot.func_150297_b("Brew", 10)) {
            this.brewStack = ItemStack.func_77949_a((NBTTagCompound)nbtRoot.func_74775_l("Brew"));
            this.setColor(WitcheryBrewRegistry.INSTANCE.getBrewColor(this.brewStack.func_77978_p()));
            if (nbtRoot.func_74764_b("Spell")) {
                this.setIsSpell(nbtRoot.func_74767_n("Spell"));
            }
        }
        if (this.brewStack == null) {
            this.func_70106_y();
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound nbtRoot) {
        super.func_70014_b(nbtRoot);
        if (this.brewStack != null) {
            nbtRoot.func_74782_a("Brew", (NBTBase)this.brewStack.func_77955_b(new NBTTagCompound()));
            nbtRoot.func_74757_a("Spell", this.getIsSpell());
        }
    }
}

