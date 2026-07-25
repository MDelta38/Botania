/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EntityDamageSourceIndirect
 */
package com.emoniph.witchery.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class EntityDamageSourceIndirectSilver
extends EntityDamageSourceIndirect {
    public EntityDamageSourceIndirectSilver(Entity directSource, Entity indirectSource) {
        super("indirectMagic", directSource, indirectSource);
    }
}

