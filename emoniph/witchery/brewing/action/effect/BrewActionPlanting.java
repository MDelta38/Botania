/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.util.BlockActionCircle;
import com.emoniph.witchery.util.EntityUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionPlanting
extends BrewActionEffect {
    public BrewActionPlanting(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, EffectLevel effectLevel) {
        super(itemKey, namePart, powerCost, new Probability(1.0), effectLevel);
    }

    @Override
    protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {
    }

    @Override
    protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, final ModifiersEffect modifiers, ItemStack stack) {
        int R = radius + modifiers.getStrength();
        double RADIUS_SQ = R * R;
        AxisAlignedBB areaOfEffect = AxisAlignedBB.func_72330_a((double)(x - R), (double)(y - R), (double)(z - R), (double)(x + R), (double)(y + R), (double)(z + R));
        List entities = world.func_72872_a(EntityItem.class, areaOfEffect);
        if (entities != null && !entities.isEmpty()) {
            final ArrayList<ItemStack> seeds = new ArrayList<ItemStack>();
            for (EntityItem itemEntity : entities) {
                ItemStack seedStack = itemEntity.func_92059_d();
                if (!(itemEntity.func_70092_e((double)x, (double)y, (double)z) <= RADIUS_SQ) || seedStack == null || !(seedStack.func_77973_b() instanceof IPlantable)) continue;
                seeds.add(seedStack);
            }
            int Y_RANGE = 2;
            new BlockActionCircle(){

                @Override
                public void onBlock(World world, int x, int y, int z) {
                    int index = seeds.size() - 1;
                    if (index >= 0) {
                        Block block;
                        ItemStack seed = (ItemStack)seeds.get(index);
                        for (int dy = y - 2; !(dy > y + 2 || (block = world.func_147439_a(x, dy, z)).func_149688_o().func_76220_a() && world.func_147437_c(x, dy + 1, z) && seed.func_77973_b().func_77648_a(seed, EntityUtil.playerOrFake(world, (EntityLivingBase)modifiers.caster), world, x, dy, z, 1, 0.0f, 0.0f, 0.0f)); ++dy) {
                        }
                        if (seed.field_77994_a <= 0) {
                            seeds.remove(index);
                        }
                    }
                }
            }.processFilledCircle(world, x, y, z, radius + modifiers.getStrength());
        }
    }
}

