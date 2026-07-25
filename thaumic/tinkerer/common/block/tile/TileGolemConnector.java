/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.lua.LuaException
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.SimpleComponent
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.StatCollector
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.entities.golems.Marker
 */
package thaumic.tinkerer.common.block.tile;

import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.StatCollector;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.Marker;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.core.golems.EnumGolemCores;
import thaumic.tinkerer.common.core.golems.EnumGolemDecorations;

@Optional.InterfaceList(value={@Optional.Interface(iface="li.cil.oc.api.network.SimpleComponent", modid="OpenComputers"), @Optional.Interface(iface="dan200.computercraft.api.peripheral.IPeripheral", modid="ComputerCraft")})
public class TileGolemConnector
extends TileCamo
implements IPeripheral,
SimpleComponent {
    private static final String TAG_UUID_MOST = "UUIDMost";
    private static final String TAG_UUID_LEAST = "UUIDLeast";
    public UUID golemConnected;
    public EntityGolemBase golem;

    @Override
    public boolean canUpdate() {
        return true;
    }

    public void verifyGolem() {
        if (this.golemConnected == null) {
            return;
        }
        List list = this.field_145850_b.func_72872_a(EntityGolemBase.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 30), (double)(this.field_145848_d - 30), (double)(this.field_145849_e - 30), (double)(this.field_145851_c + 30), (double)(this.field_145848_d + 30), (double)(this.field_145849_e + 30)));
        for (EntityGolemBase entityGolem : list) {
            if (!entityGolem.func_110124_au().equals(this.golemConnected)) continue;
            this.golem = entityGolem;
            break;
        }
    }

    public String getType() {
        return "tt_golemconnector";
    }

    public Object[] getGolemDecorationsImplementation() throws LuaException {
        if (this.golem == null || this.golem.decoration == null || this.golem.decoration.length() == 0) {
            return new String[0];
        }
        HashMap<Double, String> decorations = new HashMap<Double, String>();
        for (int i = 0; i < this.golem.decoration.length(); ++i) {
            EnumGolemDecorations golemDec = EnumGolemDecorations.getFromChar(this.golem.decoration.charAt(i));
            if (golemDec == null) {
                throw new LuaException("Golem is wearing unknown accessory: " + this.golem.decoration.charAt(i));
            }
            decorations.put(Double.valueOf(i), StatCollector.func_74838_a((String)golemDec.getName()));
        }
        return new Object[]{decorations};
    }

    public String[] getGolemCoreImplementation() throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        if (this.golem.getCore() == -1) {
            return new String[]{StatCollector.func_74838_a((String)"item.ItemGolemCore.100.name")};
        }
        String[] decorations = new String[1];
        EnumGolemCores golemCore = EnumGolemCores.getFromByte(this.golem.getCore());
        if (golemCore == null) {
            throw new LuaException("Golem has Unknown core: " + this.golem.getCore());
        }
        decorations[0] = StatCollector.func_74838_a((String)golemCore.getName());
        return decorations;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        if (this.golemConnected != null) {
            cmp.func_74772_a(TAG_UUID_MOST, this.golemConnected.getMostSignificantBits());
            cmp.func_74772_a(TAG_UUID_LEAST, this.golemConnected.getLeastSignificantBits());
        }
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.func_82737_E() % 100L == 1L) {
            this.verifyGolem();
        }
    }

    public void ConnectGolem(UUID golemID) {
        this.golemConnected = golemID;
        if (golemID == null) {
            this.golem = null;
        } else {
            this.verifyGolem();
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        if (cmp.func_74764_b(TAG_UUID_LEAST) && cmp.func_74764_b(TAG_UUID_MOST)) {
            this.golemConnected = new UUID(cmp.func_74763_f(TAG_UUID_MOST), cmp.func_74763_f(TAG_UUID_LEAST));
        }
    }

    public String[] getMethodNames() {
        return new String[]{"getDecorations", "getPosition", "getType", "getHealth", "getCore", "getHome", "setHome", "getMarkers", "setMarkers", "newMarker", "addMarker", "saveMarker", "deleteMarker", "getMarker", "getMarkerCount"};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException {
        switch (method) {
            case 0: {
                return this.getGolemDecorationsImplementation();
            }
            case 1: {
                if (this.golem == null) {
                    return new Integer[0];
                }
                return new Integer[]{(int)this.golem.field_70165_t, (int)this.golem.field_70163_u, (int)this.golem.field_70161_v};
            }
            case 2: {
                return this.getGolemTypeImplementation();
            }
            case 3: {
                if (this.golem == null) {
                    return new Float[0];
                }
                return new Float[]{Float.valueOf(this.golem.func_110143_aJ())};
            }
            case 4: {
                return this.getGolemCoreImplementation();
            }
            case 5: {
                return this.getHomeImplementation();
            }
            case 6: {
                if (arguments.length != 4) {
                    throw new LuaException("Invalid arguments");
                }
                double x = (Double)arguments[0];
                double y = (Double)arguments[1];
                double z = (Double)arguments[2];
                double facing = (Double)arguments[3];
                return this.setHomeImplementation(x, y, z, facing);
            }
            case 7: {
                return this.getMarkersImplementation();
            }
            case 8: {
                if (arguments.length != 1) {
                    throw new LuaException("setMarkers takes 1 argument");
                }
                return this.setMarkersImplementation((HashMap)arguments[0]);
            }
            case 9: {
                return this.newMarkerImplementation();
            }
            case 10: {
                if (arguments.length != 1) {
                    throw new LuaException("addMarker must have 1 argument");
                }
                return this.addMarkerImplementation((Map)arguments[0]);
            }
            case 11: {
                if (arguments.length != 2) {
                    throw new LuaException("saveMarker must have 2 arguments");
                }
                return this.saveMarkerImplementation((Double)arguments[0], (Map)arguments[1]);
            }
            case 12: {
                if (arguments.length != 1) {
                    throw new LuaException("deleteMarker must have 1 argument");
                }
                return this.deleteMarkerImplementation((Double)arguments[0]);
            }
            case 13: {
                if (arguments.length != 1) {
                    throw new LuaException("getMarker must have 1 argument");
                }
                return this.getMarkerImplementation((Double)arguments[0]);
            }
            case 14: {
                return this.getMarkerCountImplementation();
            }
        }
        return null;
    }

    private Object[] getMarkerCountImplementation() {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList markers = this.golem.getMarkers();
        if (markers == null) {
            return new Object[]{0};
        }
        return new Object[]{(double)markers.size()};
    }

    private Object[] getMarkerImplementation(Double arguments) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList markers = this.golem.getMarkers();
        if (markers == null || (double)markers.size() <= arguments) {
            throw new LuaException("marker " + (int)arguments.doubleValue() + " does not exist");
        }
        Marker mark = (Marker)markers.get((int)arguments.doubleValue());
        return new Object[]{this.fromMarkerImplementation(mark)};
    }

    private Object[] deleteMarkerImplementation(double arguments) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList markers = this.golem.getMarkers();
        if (markers == null || (double)markers.size() <= arguments) {
            throw new LuaException("marker " + (int)arguments + " does not exist");
        }
        markers.remove((int)arguments);
        this.golem.setMarkers(markers);
        return new String[0];
    }

    private Object[] saveMarkerImplementation(double markerNum, Map markerArg) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList markers = this.golem.getMarkers();
        if (markers == null || (double)markers.size() <= markerNum) {
            throw new LuaException("marker " + (int)markerNum + " does not exist");
        }
        Marker mark = this.toMarkerImplementation(markerArg);
        markers.set((int)markerNum, mark);
        this.golem.setMarkers(markers);
        return this.getMarkerImplementation(markerNum);
    }

    private Object[] addMarkerImplementation(Map arguments) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList<Marker> markers = this.golem.getMarkers();
        if (markers == null) {
            markers = new ArrayList<Marker>();
        }
        Marker mark = this.toMarkerImplementation(arguments);
        markers.add(mark);
        this.golem.setMarkers(markers);
        return this.getMarkerImplementation(Double.valueOf(markers.size() - 1));
    }

    private Object[] newMarkerImplementation() {
        HashMap<String, Integer> mark = new HashMap<String, Integer>();
        mark.put("posX", this.field_145851_c);
        mark.put("posY", this.field_145848_d);
        mark.put("posZ", this.field_145849_e);
        mark.put("dim", this.field_145850_b.field_73011_w.field_76574_g);
        mark.put("color", -1);
        mark.put("side", 1);
        return new Object[]{mark};
    }

    private Object[] setMarkersImplementation(Map arguments) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList<Marker> arrList = new ArrayList<Marker>();
        for (Object map : arguments.values()) {
            Marker mark = this.toMarkerImplementation((Map)map);
            arrList.add(mark);
        }
        this.golem.setMarkers(arrList);
        return this.getMarkersImplementation();
    }

    private Marker toMarkerImplementation(Map markerMap) {
        double posX = (Double)markerMap.get("posX");
        double posY = (Double)markerMap.get("posY");
        double posZ = (Double)markerMap.get("posZ");
        double dim = (Double)markerMap.get("dim");
        double side = (Double)markerMap.get("side");
        double color = (Double)markerMap.get("color");
        return new Marker((int)posX, (int)posY, (int)posZ, (int)dim, (byte)side, (byte)color);
    }

    private Object[] getMarkersImplementation() {
        if (this.golem == null) {
            return new String[0];
        }
        ArrayList markers = this.golem.getMarkers();
        HashMap<Integer, HashMap<String, Object>> luaMarkers = new HashMap<Integer, HashMap<String, Object>>();
        int i = 1;
        for (Marker mark : markers) {
            HashMap<String, Object> luaMarker = this.fromMarkerImplementation(mark);
            luaMarkers.put(i++, luaMarker);
        }
        return new Object[]{luaMarkers};
    }

    private HashMap<String, Object> fromMarkerImplementation(Marker mark) {
        HashMap<String, Object> luaMarker = new HashMap<String, Object>();
        luaMarker.put("posX", mark.x);
        luaMarker.put("posY", mark.y);
        luaMarker.put("posZ", mark.z);
        luaMarker.put("dim", mark.dim);
        luaMarker.put("color", mark.color);
        luaMarker.put("side", mark.side);
        return luaMarker;
    }

    private Object[] setHomeImplementation(double x, double y, double z, double facing) throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        this.golem.func_110171_b((int)x, (int)y, (int)z, 35);
        this.golem.homeFacing = (int)facing;
        return this.getHomeImplementation();
    }

    private Object[] getHomeImplementation() {
        if (this.golem == null) {
            return new String[0];
        }
        ChunkCoordinates home = this.golem.func_110172_bL();
        return new Integer[]{home.field_71574_a, home.field_71572_b, home.field_71573_c, this.golem.homeFacing};
    }

    private String[] getGolemTypeImplementation() throws LuaException {
        if (this.golem == null) {
            return new String[0];
        }
        switch (this.golem.getGolemType()) {
            case CLAY: {
                return new String[]{"Clay"};
            }
            case FLESH: {
                return new String[]{"Flesh"};
            }
            case IRON: {
                return new String[]{"Iron"};
            }
            case STONE: {
                return new String[]{"Stone"};
            }
            case STRAW: {
                return new String[]{"Straw"};
            }
            case TALLOW: {
                return new String[]{"Tallow"};
            }
            case THAUMIUM: {
                return new String[]{"Thaumium"};
            }
            case WOOD: {
                return new String[]{"Wood"};
            }
        }
        throw new LuaException("Unknown Golem Type: " + this.golem.getGolemType());
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

    public String getComponentName() {
        return this.getType();
    }

    @Callback(doc="function():table -- Returns table of current decorations on golem")
    @Optional.Method(modid="OpenComputers")
    public Object[] getDecorations(Context context, Arguments args) throws LuaException {
        return this.getGolemDecorationsImplementation();
    }

    @Callback(doc="function():x:number,y:number,z:number -- Returns The golems current position")
    @Optional.Method(modid="OpenComputers")
    public Object[] getPosition(Context context, Arguments args) {
        return new Double[]{this.golem.field_70165_t, this.golem.field_70163_u, this.golem.field_70161_v};
    }

    @Callback(doc="function():string -- Returns The golems type")
    @Optional.Method(modid="OpenComputers")
    public Object[] getType(Context context, Arguments args) throws LuaException {
        return this.getGolemTypeImplementation();
    }

    @Callback(doc="function():number -- Returns The golems health")
    @Optional.Method(modid="OpenComputers")
    public Object[] getHealth(Context context, Arguments args) throws LuaException {
        return new Float[]{Float.valueOf(this.golem.func_110143_aJ())};
    }

    @Callback(doc="function():string -- Returns The golems core")
    @Optional.Method(modid="OpenComputers")
    public Object[] getCore(Context context, Arguments args) throws LuaException {
        return this.getGolemCoreImplementation();
    }

    @Callback(doc="function():x:number,y:number,z:number,facing:number -- Returns The golems home position")
    @Optional.Method(modid="OpenComputers")
    public Object[] getHome(Context context, Arguments args) throws LuaException {
        return this.getHomeImplementation();
    }

    @Callback(doc="function(x:number,y:number,z:number,facing:number):nill -- Sets The golems home position")
    @Optional.Method(modid="OpenComputers")
    public Object[] setHome(Context context, Arguments args) throws LuaException {
        return this.setHomeImplementation(args.checkDouble(0), args.checkDouble(1), args.checkDouble(2), args.checkDouble(3));
    }

    @Callback(doc="function():table -- Gets list of markers")
    @Optional.Method(modid="OpenComputers")
    public Object[] getMarkers(Context context, Arguments args) throws LuaException {
        return this.getMarkersImplementation();
    }

    @Callback(doc="function(list:table):table -- Sets list of markers")
    @Optional.Method(modid="OpenComputers")
    public Object[] setMarkers(Context context, Arguments args) throws LuaException {
        return this.setMarkersImplementation(args.checkTable(0));
    }

    @Callback(doc="function():table -- Returns a blank marker with sensible defaults")
    @Optional.Method(modid="OpenComputers")
    public Object[] newMarker(Context context, Arguments args) throws LuaException {
        return this.newMarkerImplementation();
    }

    @Callback(doc="function(marker:table):table -- Adds the marker to the end of the marker list")
    @Optional.Method(modid="OpenComputers")
    public Object[] addMarker(Context context, Arguments args) throws LuaException {
        return this.addMarkerImplementation(args.checkTable(0));
    }

    @Callback(doc="function(markerNum:number,marker:table):table -- saves current marker")
    @Optional.Method(modid="OpenComputers")
    public Object[] saveMarker(Context context, Arguments args) throws LuaException {
        return this.saveMarkerImplementation(args.checkInteger(0), args.checkTable(0));
    }

    @Callback(doc="function(markerNum:number):table -- Deletes marker number")
    @Optional.Method(modid="OpenComputers")
    public Object[] deleteMarker(Context context, Arguments args) throws LuaException {
        return this.deleteMarkerImplementation(args.checkInteger(0));
    }

    @Callback(doc="function(markerNum:number):table -- gets a specific marker")
    @Optional.Method(modid="OpenComputers")
    public Object[] getMarker(Context context, Arguments args) throws LuaException {
        return this.getMarkerImplementation(Double.valueOf(args.checkInteger(0)));
    }

    @Callback(doc="function():number -- Returns number of markers")
    @Optional.Method(modid="OpenComputers")
    public Object[] getMarkerCount(Context context, Arguments args) throws LuaException {
        return this.getMarkerCountImplementation();
    }
}

