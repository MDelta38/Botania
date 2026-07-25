/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  net.minecraft.entity.EntityLivingBase
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.integration.ModHook;
import cpw.mods.fml.common.event.FMLInterModComms;
import net.minecraft.entity.EntityLivingBase;

public class ModHookWaila
extends ModHook {
    @Override
    public String getModID() {
        return "Waila";
    }

    @Override
    protected void doInit() {
        FMLInterModComms.sendMessage((String)this.getModID(), (String)"register", (String)"com.emoniph.witchery.integration.ModHookWailaRegistrar.callbackRegister");
    }

    @Override
    protected void doPostInit() {
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
    }
}

