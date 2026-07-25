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

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;
import thaumcraft.common.tiles.TileEssentiaReservoir;
import thaumcraft.common.tiles.TileJarFillable;

public class AIEssentiaEmpty
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private int jarX;
    private int jarY;
    private int jarZ;
    private ForgeDirection markerOrientation;
    private World theWorld;

    public AIEssentiaEmpty(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        if (!this.theGolem.func_70661_as().func_75500_f() || this.theGolem.essentia == null || this.theGolem.essentiaAmount == 0) {
            return false;
        }
        ChunkCoordinates jarloc = GolemHelper.findJarWithRoom(this.theGolem);
        if (jarloc == null) {
            return false;
        }
        if (this.theGolem.func_70092_e((double)jarloc.field_71574_a + 0.5, (double)jarloc.field_71572_b + 0.5, (double)jarloc.field_71573_c + 0.5) > 4.0) {
            return false;
        }
        this.jarX = jarloc.field_71574_a;
        this.jarY = jarloc.field_71572_b;
        this.jarZ = jarloc.field_71573_c;
        return true;
    }

    public boolean func_75253_b() {
        return false;
    }

    public void func_75249_e() {
        block6: {
            TileEntity tile;
            block7: {
                int added;
                block5: {
                    tile = this.theWorld.func_147438_o(this.jarX, this.jarY, this.jarZ);
                    if (tile == null || !(tile instanceof TileJarFillable)) break block5;
                    TileJarFillable jar = (TileJarFillable)tile;
                    this.theGolem.essentiaAmount = jar.addToContainer(this.theGolem.essentia, this.theGolem.essentiaAmount);
                    if (this.theGolem.essentiaAmount == 0) {
                        this.theGolem.essentia = null;
                    }
                    this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                    this.theGolem.updateCarried();
                    this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
                    break block6;
                }
                if (tile == null || !(tile instanceof TileEssentiaReservoir)) break block7;
                TileEssentiaReservoir trans = (TileEssentiaReservoir)tile;
                if (trans.getSuctionAmount(trans.facing) <= 0 || trans.getSuctionType(trans.facing) != null && trans.getSuctionType(trans.facing) != this.theGolem.essentia || (added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, trans.facing)) <= 0) break block6;
                this.theGolem.essentiaAmount -= added;
                if (this.theGolem.essentiaAmount == 0) {
                    this.theGolem.essentia = null;
                }
                this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                this.theGolem.updateCarried();
                this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
                break block6;
            }
            if (tile != null && tile instanceof IEssentiaTransport) {
                Iterator<Integer> i$ = GolemHelper.getMarkedSides(this.theGolem, tile, (byte)-1).iterator();
                while (i$.hasNext()) {
                    int added;
                    IEssentiaTransport trans = (IEssentiaTransport)tile;
                    Integer side = i$.next();
                    if (!trans.canInputFrom(ForgeDirection.getOrientation((int)side)) || trans.getSuctionAmount(ForgeDirection.getOrientation((int)side)) <= 0 || trans.getSuctionType(ForgeDirection.getOrientation((int)side)) != null && trans.getSuctionType(ForgeDirection.getOrientation((int)side)) != this.theGolem.essentia || (added = trans.addEssentia(this.theGolem.essentia, this.theGolem.essentiaAmount, ForgeDirection.getOrientation((int)side))) <= 0) continue;
                    this.theGolem.essentiaAmount -= added;
                    if (this.theGolem.essentiaAmount == 0) {
                        this.theGolem.essentia = null;
                    }
                    this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                    this.theGolem.updateCarried();
                    this.theWorld.func_147471_g(this.jarX, this.jarY, this.jarZ);
                    break;
                }
            }
        }
    }
}

