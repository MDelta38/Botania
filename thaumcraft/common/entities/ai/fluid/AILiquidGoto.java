/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.FluidStack
 */
package thaumcraft.common.entities.ai.fluid;

import java.util.ArrayList;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;

public class AILiquidGoto
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private double waterX;
    private double waterY;
    private double waterZ;
    private World theWorld;
    int count = 0;
    int prevX = 0;
    int prevY = 0;
    int prevZ = 0;

    public AILiquidGoto(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (this.theGolem.field_70173_aa % Config.golemDelay > 0 || this.theGolem.fluidCarried != null && this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000) {
            return false;
        }
        ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
        for (FluidStack fluid : fluids) {
            Vec3 var1 = GolemHelper.findPossibleLiquid(fluid, this.theGolem);
            if (var1 == null) continue;
            this.theGolem.itemWatched = new ItemStack(Item.func_150899_d((int)fluid.fluidID), 1, fluid.amount);
            this.waterX = var1.field_72450_a;
            this.waterY = var1.field_72448_b;
            this.waterZ = var1.field_72449_c;
            double dd = this.theGolem.func_70011_f(this.waterX, this.waterY, this.waterZ);
            for (int xx = -1; xx <= 1; ++xx) {
                for (int zz = -1; zz <= 1; ++zz) {
                    double dd2 = this.theGolem.func_70011_f(var1.field_72450_a + (double)xx, this.waterY, var1.field_72449_c + (double)zz);
                    if (!(dd2 < dd) || !this.theGolem.field_70170_p.func_147445_c((int)var1.field_72450_a + xx, (int)this.waterY, (int)var1.field_72449_c + zz, true)) continue;
                    this.waterX = var1.field_72450_a + (double)xx;
                    this.waterZ = var1.field_72449_c + (double)zz;
                    dd = dd2;
                }
            }
            return true;
        }
        return false;
    }

    public boolean func_75253_b() {
        return this.count > 0 && !this.theGolem.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
        this.count = 0;
    }

    public void func_75246_d() {
        Vec3 var2;
        --this.count;
        if (this.count == 0 && this.prevX == MathHelper.func_76128_c((double)this.theGolem.field_70165_t) && this.prevY == MathHelper.func_76128_c((double)this.theGolem.field_70163_u) && this.prevZ == MathHelper.func_76128_c((double)this.theGolem.field_70161_v) && (var2 = RandomPositionGenerator.func_75463_a((EntityCreature)this.theGolem, (int)2, (int)1)) != null) {
            this.count = 20;
            this.theGolem.func_70661_as().func_75492_a(var2.field_72450_a, var2.field_72448_b, var2.field_72449_c, (double)this.theGolem.func_70689_ay());
        }
        super.func_75246_d();
    }

    public void func_75249_e() {
        this.count = 200;
        this.prevX = MathHelper.func_76128_c((double)this.theGolem.field_70165_t);
        this.prevY = MathHelper.func_76128_c((double)this.theGolem.field_70163_u);
        this.prevZ = MathHelper.func_76128_c((double)this.theGolem.field_70161_v);
        this.theGolem.func_70661_as().func_75492_a(this.waterX, this.waterY, this.waterZ, (double)this.theGolem.func_70689_ay());
    }
}

