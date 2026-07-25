/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.BlockFluidBase
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidBlock
 *  net.minecraftforge.fluids.IFluidHandler
 */
package thaumcraft.common.entities.ai.fluid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.GolemHelper;
import thaumcraft.common.entities.golems.Marker;

public class AILiquidGather
extends EntityAIBase {
    private EntityGolemBase theGolem;
    private int waterX;
    private int waterY;
    private int waterZ;
    private ForgeDirection markerOrientation;
    private World theWorld;
    private float pumpDist = 0.0f;
    int count = 0;
    HashMap<ChunkCoordinates, ArrayList<SourceBlock>> queue = new HashMap();
    ArrayList<ChunkCoordinates> cache = new ArrayList();
    ChunkCoordinates origin = null;

    public AILiquidGather(EntityGolemBase par1EntityCreature) {
        this.theGolem = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
        if (fluids == null) {
            return false;
        }
        if (this.theGolem.itemWatched == null || fluids.size() == 0 || !this.theGolem.func_70661_as().func_75500_f()) {
            return false;
        }
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        int camt = 0;
        if (this.theGolem.fluidCarried != null) {
            camt = this.theGolem.fluidCarried.amount;
        }
        int max = this.theGolem.getFluidCarryLimit();
        for (FluidStack fluid : fluids) {
            ArrayList<Marker> markers = GolemHelper.getMarkedFluidHandlersAdjacentToGolem(fluid, this.theWorld, this.theGolem);
            for (Marker marker : markers) {
                FluidStack fs;
                TileEntity te = this.theWorld.func_147438_o(marker.x, marker.y, marker.z);
                if (te == null || !(te instanceof IFluidHandler) || (fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation((int)marker.side), new FluidStack(fluid.getFluid(), max - camt), false)) == null || fs.amount <= 0) continue;
                return true;
            }
            ArrayList<ChunkCoordinates> coords = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theWorld, this.theGolem, (byte)-1);
            for (ChunkCoordinates loc : coords) {
                Block bi = this.theWorld.func_147439_a(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
                if (FluidRegistry.getFluid((int)fluid.fluidID).getBlock() != bi) continue;
                if (bi instanceof IFluidBlock && ((IFluidBlock)bi).canDrain(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c)) {
                    FluidStack fs = ((IFluidBlock)bi).drain(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c, false);
                    return fs != null && fs.amount <= max - camt;
                }
                if (fluid.fluidID != FluidRegistry.WATER.getID() && fluid.fluidID != FluidRegistry.LAVA.getID()) continue;
                return this.theWorld.func_72805_g(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c) == 0;
            }
        }
        return false;
    }

    public boolean func_75253_b() {
        return this.count < 20 && this.theGolem.itemWatched != null;
    }

    public boolean func_75252_g() {
        return false;
    }

    public void func_75249_e() {
        this.count = 0;
    }

    public void func_75251_c() {
        this.count = 0;
        this.theGolem.itemWatched = null;
        super.func_75251_c();
    }

    public void func_75246_d() {
        ++this.count;
        if (this.count < 10) {
            return;
        }
        ForgeDirection facing = ForgeDirection.getOrientation((int)this.theGolem.homeFacing);
        ChunkCoordinates home = this.theGolem.func_110172_bL();
        int cX = home.field_71574_a - facing.offsetX;
        int cY = home.field_71572_b - facing.offsetY;
        int cZ = home.field_71573_c - facing.offsetZ;
        int camt = 0;
        if (this.theGolem.fluidCarried != null) {
            camt = this.theGolem.fluidCarried.amount;
        }
        int max = this.theGolem.getFluidCarryLimit();
        ArrayList<FluidStack> fluids = GolemHelper.getMissingLiquids(this.theGolem);
        if (fluids == null) {
            return;
        }
        for (FluidStack fluidstack : fluids) {
            ArrayList<Marker> markers = GolemHelper.getMarkedFluidHandlersAdjacentToGolem(fluidstack, this.theWorld, this.theGolem);
            for (Marker marker : markers) {
                FluidStack fs;
                TileEntity te = this.theWorld.func_147438_o(marker.x, marker.y, marker.z);
                if (te == null || !(te instanceof IFluidHandler) || (fs = ((IFluidHandler)te).drain(ForgeDirection.getOrientation((int)marker.side), new FluidStack(fluidstack.getFluid(), max - camt), true)) == null || fs.amount <= 0) continue;
                if (this.theGolem.fluidCarried != null) {
                    this.theGolem.fluidCarried.amount += fs.amount;
                } else {
                    this.theGolem.fluidCarried = fs.copy();
                }
                if (fs.amount > 200) {
                    this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f * ((float)fs.amount / (float)max), 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                }
                this.theGolem.updateCarried();
                if (this.theGolem.fluidCarried.amount >= this.theGolem.getFluidCarryLimit()) {
                    this.theGolem.itemWatched = null;
                }
                this.count = 0;
            }
            ArrayList<ChunkCoordinates> coords = GolemHelper.getMarkedBlocksAdjacentToGolem(this.theWorld, this.theGolem, (byte)-1);
            for (ChunkCoordinates loc : coords) {
                Block bi = this.theWorld.func_147439_a(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
                int md = this.theWorld.func_72805_g(loc.field_71574_a, loc.field_71572_b, loc.field_71573_c);
                int i = loc.field_71574_a;
                int j = loc.field_71572_b;
                int k = loc.field_71573_c;
                if (this.theGolem.getUpgradeAmount(5) > 0) {
                    if (!this.queue.containsKey(loc) || this.queue.get(loc).size() == 0) {
                        this.rebuildQueue(loc, fluidstack.getFluid());
                    }
                    if (this.queue.containsKey(loc) && this.queue.get(loc).size() > 0) {
                        ArrayList<SourceBlock> t = this.queue.get(loc);
                        do {
                            ChunkCoordinates current = t.get((int)0).loc;
                            i = current.field_71574_a;
                            j = current.field_71572_b;
                            k = current.field_71573_c;
                            t.remove(0);
                        } while (t.size() > 0 && !this.validFluidBlock(fluidstack.getFluid(), i, j, k));
                        this.queue.put(loc, t);
                    }
                }
                if (FluidRegistry.getFluid((int)fluidstack.fluidID).getBlock() != bi) continue;
                if (bi instanceof BlockFluidBase && ((IFluidBlock)bi).canDrain(this.theWorld, i, j, k)) {
                    FluidStack fs = ((IFluidBlock)bi).drain(this.theWorld, i, j, k, false);
                    if (fs == null || fs.amount > max - camt) continue;
                    ((IFluidBlock)bi).drain(this.theWorld, i, j, k, true);
                    if (this.theGolem.fluidCarried != null) {
                        this.theGolem.fluidCarried.amount += fs.amount;
                    } else {
                        this.theGolem.fluidCarried = fs.copy();
                    }
                    this.theWorld.func_147468_f(i, j, k);
                    this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                    this.theGolem.updateCarried();
                    if (this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000) {
                        this.theGolem.itemWatched = null;
                    }
                    this.count = 0;
                    continue;
                }
                if (fluidstack.fluidID != FluidRegistry.WATER.getID() && fluidstack.fluidID != FluidRegistry.LAVA.getID()) continue;
                int wmd = this.theWorld.func_72805_g(i, j, k);
                if ((FluidRegistry.lookupFluidForBlock((Block)bi) != FluidRegistry.WATER || fluidstack.fluidID != FluidRegistry.WATER.getID()) && (FluidRegistry.lookupFluidForBlock((Block)bi) != FluidRegistry.LAVA || fluidstack.fluidID != FluidRegistry.LAVA.getID()) || wmd != 0) continue;
                FluidStack fs = new FluidStack(fluidstack.fluidID, 1000);
                if (this.theGolem.fluidCarried != null) {
                    this.theGolem.fluidCarried.amount += fs.amount;
                } else {
                    this.theGolem.fluidCarried = fs.copy();
                }
                this.theWorld.func_147468_f(i, j, k);
                this.theWorld.func_72956_a((Entity)this.theGolem, "game.neutral.swim", 0.2f, 1.0f + (this.theWorld.field_73012_v.nextFloat() - this.theWorld.field_73012_v.nextFloat()) * 0.3f);
                this.theGolem.updateCarried();
                if (this.theGolem.fluidCarried.amount > this.theGolem.getFluidCarryLimit() - 1000) {
                    this.theGolem.itemWatched = null;
                }
                this.count = 0;
            }
        }
    }

    private boolean validFluidBlock(Fluid fluid, int i, int j, int k) {
        FluidStack fs;
        Block bi = this.theWorld.func_147439_a(i, j, k);
        if (FluidRegistry.lookupFluidForBlock((Block)bi) != fluid) {
            return false;
        }
        if (bi instanceof BlockFluidBase && ((IFluidBlock)bi).canDrain(this.theWorld, i, j, k) && (fs = ((IFluidBlock)bi).drain(this.theWorld, i, j, k, false)) != null) {
            return true;
        }
        return (FluidRegistry.lookupFluidForBlock((Block)bi) == FluidRegistry.WATER && fluid == FluidRegistry.WATER || FluidRegistry.lookupFluidForBlock((Block)bi) == FluidRegistry.LAVA && fluid == FluidRegistry.LAVA) && this.theWorld.func_72805_g(i, j, k) == 0;
    }

    private void rebuildQueue(ChunkCoordinates loc, Fluid fluid) {
        this.pumpDist = this.theGolem.getRange() * this.theGolem.getRange();
        this.cache.clear();
        this.origin = loc;
        ArrayList<SourceBlock> sources = new ArrayList<SourceBlock>();
        this.getConnectedFluidBlocks(this.theWorld, loc.field_71574_a, loc.field_71572_b, loc.field_71573_c, fluid, sources);
        Collections.sort(sources, Collections.reverseOrder());
        this.queue.put(loc, sources);
    }

    private void getConnectedFluidBlocks(World world, int x, int y, int z, Fluid fluid, ArrayList<SourceBlock> sources) {
        try {
            if (this.cache.contains(new ChunkCoordinates(x, y, z))) {
                return;
            }
            this.cache.add(new ChunkCoordinates(x, y, z));
            for (int a = -1; a <= 1; ++a) {
                for (int b = -1; b <= 1; ++b) {
                    for (int c = -1; c <= 1; ++c) {
                        Fluid fi;
                        int zz;
                        int yy;
                        int xx;
                        ChunkCoordinates cc;
                        float dist;
                        if (a == 0 && b == 0 && c == 0 || (dist = (cc = new ChunkCoordinates(xx = x + a, yy = y + b, zz = z + c)).func_82371_e(this.origin)) > this.pumpDist) continue;
                        Block bi = world.func_147439_a(xx, yy, zz);
                        if (bi == Blocks.field_150356_k) {
                            bi = Blocks.field_150353_l;
                        }
                        if (bi == Blocks.field_150358_i) {
                            bi = Blocks.field_150355_j;
                        }
                        if ((fi = FluidRegistry.lookupFluidForBlock((Block)bi)) == null || fi != fluid) continue;
                        if (this.validFluidBlock(fluid, xx, yy, zz)) {
                            sources.add(new SourceBlock(cc, dist));
                        }
                        this.getConnectedFluidBlocks(world, xx, yy, zz, fluid, sources);
                    }
                }
            }
        }
        catch (Exception e) {
            // empty catch block
        }
    }

    private class SourceBlock
    implements Comparable {
        ChunkCoordinates loc;
        float dist;

        public SourceBlock(ChunkCoordinates loc, float dist) {
            this.loc = loc;
            this.dist = dist;
        }

        public int compareTo(SourceBlock target) {
            return target.dist < this.dist ? 1 : (target.dist > this.dist ? -1 : 0);
        }

        public int compareTo(Object target) {
            return this.compareTo((SourceBlock)target);
        }
    }
}

