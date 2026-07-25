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
 */
package thaumcraft.common.entities.ai.fluid;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileEssentiaReservoir;
import thaumcraft.common.tiles.TileJarFillable;

public class AIEssentiaGather
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private double crucX;
    private double crucY;
    private double crucZ;
    private World theWorld;
    private long delay = 0L;
    int start = 0;

    public AIEssentiaGather(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        if (!this.theGolem.func_70661_as().func_75500_f() || this.delay > System.currentTimeMillis()) {
            return false;
        }
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        if (this.theGolem.func_70092_e((float)cX + 0.5f, (float)cY + 0.5f, (float)cZ + 0.5f) > 6.0) {
            return false;
        }
        this.start = 0;
        TileEntity te = this.theWorld.func_147438_o(cX, cY, cZ);
        if (te != null) {
            if (te instanceof IEssentiaTransport) {
                IEssentiaTransport etrans = (IEssentiaTransport)te;
                if ((te instanceof TileJarFillable || te instanceof TileEssentiaReservoir || etrans.canOutputTo(facing)) && etrans.getEssentiaAmount(facing) > 0 && (this.theGolem.essentiaAmount == 0 || (this.theGolem.essentia == null || this.theGolem.essentia.equals(etrans.getEssentiaType(facing)) || this.theGolem.essentia.equals(etrans.getEssentiaType(ForgeDirection.UNKNOWN))) && this.theGolem.essentiaAmount < this.theGolem.getCarryLimit())) {
                    this.delay = System.currentTimeMillis() + 1000L;
                    this.start = 0;
                    return true;
                }
            } else {
                this.start = -1;
                int prevTot = -1;
                for (int a = 5; a >= 0; --a) {
                    te = this.theWorld.func_147438_o(cX, cY + a, cZ);
                    if (te == null || !(te instanceof TileAlembic)) continue;
                    TileAlembic ta = (TileAlembic)te;
                    if (this.theGolem.essentiaAmount != 0 && (this.theGolem.essentia != null && !this.theGolem.essentia.equals(ta.aspect) || this.theGolem.essentiaAmount >= this.theGolem.getCarryLimit()) || ta.amount <= prevTot) continue;
                    this.delay = System.currentTimeMillis() + 1000L;
                    this.start = a;
                    prevTot = ta.amount;
                }
                if (this.start >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void func_75249_e() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        TileEntity te = this.theWorld.func_147438_o(cX, cY + this.start, cZ);
        if (te != null && te instanceof IEssentiaTransport) {
            IEssentiaTransport ta;
            if (te instanceof TileAlembic || te instanceof TileJarFillable) {
                facing = ForgeDirection.UP;
            }
            if (te instanceof TileEssentiaReservoir) {
                facing = ((TileEssentiaReservoir)te).facing;
            }
            if ((ta = (IEssentiaTransport)te).getEssentiaAmount(facing) == 0) {
                return;
            }
            if (ta.canOutputTo(facing) && ta.getEssentiaAmount(facing) > 0 && (this.theGolem.essentiaAmount == 0 || (this.theGolem.essentia == null || this.theGolem.essentia.equals(ta.getEssentiaType(facing)) || this.theGolem.essentia.equals(ta.getEssentiaType(ForgeDirection.UNKNOWN))) && this.theGolem.essentiaAmount < this.theGolem.getCarryLimit())) {
                Aspect a = ta.getEssentiaType(facing);
                if (a == null) {
                    a = ta.getEssentiaType(ForgeDirection.UNKNOWN);
                }
                int qq = ta.getEssentiaAmount(facing);
                if (te instanceof TileEssentiaReservoir) {
                    qq = ((TileEssentiaReservoir)te).containerContains(a);
                }
                int am = Math.min(qq, this.theGolem.getCarryLimit() - this.theGolem.essentiaAmount);
                this.theGolem.essentia = a;
                int taken = ta.takeEssentia(a, am, facing);
                if (taken > 0) {
                    this.theGolem.essentiaAmount += taken;
                    this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.05f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                    this.theGolem.updateCarried();
                } else {
                    this.theGolem.essentia = null;
                }
                this.delay = System.currentTimeMillis() + 100L;
            }
        }
    }
}

