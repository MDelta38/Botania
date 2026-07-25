/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.common.tiles.TileJarFillable
 */
package thaumic.tinkerer.common.peripheral.implementation;

import java.util.HashMap;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.tiles.TileJarFillable;

public class IAspectContainerImplementation {
    public static Object[] getAspects(IAspectContainer container) {
        HashMap<Double, String> returnStuff = new HashMap<Double, String>();
        double i = 1.0;
        if (container instanceof TileJarFillable && ((TileJarFillable)container).aspectFilter != null) {
            double d = i;
            i = d + 1.0;
            returnStuff.put(d, ((TileJarFillable)container).aspectFilter.getTag());
            return new Object[]{returnStuff};
        }
        if (container.getAspects() == null || container.getAspects().size() == 0) {
            return new Object[]{returnStuff};
        }
        for (Aspect aspect : container.getAspects().getAspectsSorted()) {
            double d = i;
            i = d + 1.0;
            returnStuff.put(d, aspect.getTag());
        }
        return new Object[]{returnStuff};
    }

    public static Object[] getAspectCount(IAspectContainer container, String aspectName) {
        Aspect aspect = Aspect.getAspect((String)aspectName);
        if (container.getAspects() == null) {
            return new Object[]{0};
        }
        return new Object[]{container.getAspects().getAmount(aspect)};
    }
}

