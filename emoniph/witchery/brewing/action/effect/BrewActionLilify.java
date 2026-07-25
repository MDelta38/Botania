/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Coord;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionLilify
extends BrewActionEffect {
    public BrewActionLilify(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel) {
        super(itemKey, namePart, powerCost, new Probability(1.0), effectLevel);
    }

    @Override
    protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {
        x += side.offsetX;
        y += side.offsetY;
        z += side.offsetZ;
        while (!(world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_147437_c(x, y + 1, z) || y >= 255)) {
            ++y;
        }
        if (world.func_147439_a(x, y, z).func_149688_o() == Material.field_151586_h && world.func_147437_c(x, y + 1, z) && BlockProtect.checkModsForBreakOK(world, x, y + 1, z, (EntityLivingBase)modifiers.caster)) {
            int meta = (modifiers.getStrength() & 3) << 2;
            world.func_147465_d(x, y + 1, z, Witchery.Blocks.LILY, meta, 3);
        }
    }

    @Override
    protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack) {
        Coord coord = new Coord((Entity)targetEntity);
        this.doApplyToBlock(world, coord.x, coord.y, coord.z, ForgeDirection.UP, 1, modifiers, actionStack);
    }
}

