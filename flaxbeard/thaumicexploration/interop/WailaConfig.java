/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mcp.mobius.waila.api.IWailaDataProvider
 *  mcp.mobius.waila.api.IWailaRegistrar
 */
package flaxbeard.thaumicexploration.interop;

import flaxbeard.thaumicexploration.block.BlockBoundJar;
import flaxbeard.thaumicexploration.block.BlockEverburnUrn;
import flaxbeard.thaumicexploration.block.BlockSoulBrazier;
import flaxbeard.thaumicexploration.interop.BoundJarProvider;
import flaxbeard.thaumicexploration.interop.EverBurnUrnProvider;
import flaxbeard.thaumicexploration.interop.SoulBrazierProvider;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;

public class WailaConfig {
    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerBodyProvider((IWailaDataProvider)new BoundJarProvider(), BlockBoundJar.class);
        registrar.registerBodyProvider((IWailaDataProvider)new SoulBrazierProvider(), BlockSoulBrazier.class);
        registrar.registerBodyProvider((IWailaDataProvider)new EverBurnUrnProvider(), BlockEverburnUrn.class);
    }
}

