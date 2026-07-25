/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.SimpleComponent
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.codechicken.lib.vec.Vector3
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import java.util.List;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.MiscHelper;

@Optional.InterfaceList(value={@Optional.Interface(iface="li.cil.oc.api.network.SimpleComponent", modid="OpenComputers"), @Optional.Interface(iface="dan200.computercraft.api.peripheral.IPeripheral", modid="ComputerCraft")})
public class TileMagnet
extends TileEntity
implements IPeripheral,
IMovableTile,
SimpleComponent {
    public void func_145845_h() {
        int redstone = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            redstone = Math.max(redstone, this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal()));
        }
        if (redstone > 0) {
            double x1 = (double)this.field_145851_c + 0.5;
            double y1 = (double)this.field_145848_d + 0.5;
            double z1 = (double)this.field_145849_e + 0.5;
            boolean blue = (this.func_145832_p() & 1) == 0;
            int speedMod = blue ? 1 : -1;
            double range = redstone / 2;
            AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)(x1 - range), (double)this.field_145848_d, (double)(z1 - range), (double)(x1 + range), (double)(y1 + range), (double)(z1 + range));
            List entities = this.field_145850_b.func_82733_a(Entity.class, boundingBox, this.getEntitySelector());
            for (Entity entity : entities) {
                double x2 = entity.field_70165_t;
                double y2 = entity.field_70163_u;
                double z2 = entity.field_70161_v;
                float f = blue ? (float)((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2)) : 1.1f;
                float distanceSqrd = f;
                if (!(distanceSqrd > 1.0f)) continue;
                MiscHelper.setEntityMotionFromVector(entity, new Vector3(x1, y1, z1), (float)speedMod * 0.25f);
                ThaumicTinkerer.tcProxy.sparkle((float)x2, (float)y2, (float)z2, blue ? 2 : 4);
            }
        }
    }

    IEntitySelector getEntitySelector() {
        return new IEntitySelector(){

            public boolean func_82704_a(Entity entity) {
                return entity instanceof EntityItem;
            }
        };
    }

    public String getType() {
        return "tt_magnet";
    }

    public String[] getMethodNames() {
        return new String[]{"isPulling", "setPulling", "getSignal"};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        switch (method) {
            case 0: {
                return new Object[]{(this.func_145832_p() & 1) == 0};
            }
            case 1: {
                return this.setPullingImplementation((Boolean)arguments[0]);
            }
            case 2: {
                return this.gotSignalImplementation();
            }
        }
        return null;
    }

    private Object[] gotSignalImplementation() {
        int redstone = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            redstone = Math.max(redstone, this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal()));
        }
        return new Object[]{redstone};
    }

    private Object[] setPullingImplementation(boolean argument) {
        int meta = (this.func_145832_p() & 2) + (argument ? 0 : 1);
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 3);
        return null;
    }

    @Optional.Method(modid="ComputerCraft")
    public void attach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public void detach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }

    @Callback(doc="function():boolean -- Returns Whether magnet is pushing or pulling")
    @Optional.Method(modid="OpenComputers")
    public Object[] isPulling(Context context, Arguments args) throws Exception {
        return new Object[]{(this.func_145832_p() & 1) == 0};
    }

    @Callback(doc="function(boolean):nil -- Sets Whether magnet is pushing or pulling")
    @Optional.Method(modid="OpenComputers")
    public Object[] setPulling(Context context, Arguments args) throws Exception {
        this.setPullingImplementation(args.checkBoolean(0));
        return new Object[0];
    }

    @Callback(doc="function():boolean -- Sets Whether magnet is pushing or pulling")
    @Optional.Method(modid="OpenComputers")
    public Object[] getSignal(Context context, Arguments args) throws Exception {
        return this.gotSignalImplementation();
    }

    public String getComponentName() {
        return this.getType();
    }
}

