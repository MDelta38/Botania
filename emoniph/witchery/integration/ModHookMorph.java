/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.integration.ModHook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ModHookMorph
extends ModHook {
    @SideOnly(value=Side.CLIENT)
    private static Method methodHasMorph;

    @Override
    public String getModID() {
        return "Morph";
    }

    @Override
    protected void doInit() {
        Witchery.modHooks.isMorphPresent = true;
    }

    @Override
    protected void doPostInit() {
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
    }

    @SideOnly(value=Side.CLIENT)
    public static boolean isMorphed(EntityPlayer player, boolean client) {
        if (!Witchery.modHooks.isMorphPresent) {
            return false;
        }
        if (methodHasMorph == null) {
            try {
                methodHasMorph = Class.forName("morph.common.core.ApiHandler").getDeclaredMethod("hasMorph", String.class, Boolean.TYPE);
            }
            catch (ClassNotFoundException ex) {
            }
            catch (NoSuchMethodException ex) {
                // empty catch block
            }
        }
        if (methodHasMorph != null) {
            try {
                return (Boolean)methodHasMorph.invoke(null, player.func_70005_c_(), client);
            }
            catch (IllegalAccessException ex) {
            }
            catch (InvocationTargetException invocationTargetException) {
                // empty catch block
            }
        }
        return false;
    }
}

