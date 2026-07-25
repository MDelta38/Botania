/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidHandler
 */
package thaumcraft.common.entities.ai.fluid;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;

public class AILiquidEmpty
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private int waterX;
    private int waterY;
    private int waterZ;
    private ForgeDirection markerOrientation;
    private World theWorld;

    public AILiquidEmpty(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        if (!this.theGolem.func_70661_as().func_75500_f() || this.theGolem.fluidCarried == null || this.theGolem.fluidCarried.amount == 0 || this.theGolem.func_70092_e((float)home.field_71574_a + 0.5f, (float)home.field_71572_b + 0.5f, (float)home.field_71573_c + 0.5f) > 5.0) {
            return false;
        }
        ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
        if (fluids == null) {
            return false;
        }
        for (FluidStack fluid : fluids) {
            if (!fluid.isFluidEqual(this.theGolem.fluidCarried)) continue;
            return true;
        }
        return false;
    }

    public boolean func_75253_b() {
        return false;
    }

    public void func_75249_e() {
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        TileEntity tile = this.theWorld.func_147438_o(cX, cY, cZ);
        if (tile != null && tile instanceof IFluidHandler) {
            IFluidHandler fh = (IFluidHandler)tile;
            int amt = fh.fill(ForgeDirection.getOrientation((int)this.theGolem.homeFacing), this.theGolem.fluidCarried, true);
            this.theGolem.fluidCarried.amount -= amt;
            if (this.theGolem.fluidCarried.amount <= 0) {
                this.theGolem.fluidCarried = null;
            }
            if (amt > 200) {
                this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", Math.min(0.2f, 0.2f * ((float)amt / (float)this.theGolem.getFluidCarryLimit())), 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
            }
            this.theGolem.updateCarried();
            this.theWorld.func_147471_g(cX, cY, cZ);
            this.theGolem.itemWatched = null;
        }
    }
}

