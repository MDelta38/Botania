/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.settings.KeyBinding
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package com.emoniph.witchery.util;

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyBindHelper {
    public static boolean isKeyBindDown(KeyBinding keyBinding) {
        return keyBinding.func_151463_i() >= 0 ? Keyboard.isKeyDown((int)keyBinding.func_151463_i()) : Mouse.isButtonDown((int)(keyBinding.func_151463_i() + 100));
    }
}

