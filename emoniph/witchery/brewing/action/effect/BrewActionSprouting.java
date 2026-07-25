/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
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
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.TimeUtil;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionSprouting
extends BrewActionEffect {
    public BrewActionSprouting(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel) {
        super(itemKey, namePart, powerCost, baseProbability, effectLevel);
    }

    @Override
    protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
        BrewActionSprouting.growBranch(x, y, z, world, side.ordinal(), 8 + 2 * modifiers.getStrength(), bounds);
    }

    @Override
    protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack) {
        targetEntity.func_70690_d(new PotionEffect(Witchery.Potions.SPROUTING.field_76415_H, modifiers.getModifiedDuration(TimeUtil.secsToTicks(15)), modifiers.getStrength()));
    }

    public static void growBranch(EntityLivingBase entity, int extent) {
        Coord coord = new Coord((Entity)entity);
        BrewActionSprouting.growBranch(coord.x, coord.y - 1, coord.z, entity.field_70170_p, ForgeDirection.UP.ordinal(), extent, entity.field_70121_D.func_72314_b(0.5, 0.5, 0.5));
    }

    public static void growBranch(int posX, int posY, int posZ, World world, int sideHit, int extent, AxisAlignedBB boundingBox) {
        AxisAlignedBB axisalignedbb;
        List list1;
        int i;
        int dy;
        int dx;
        Block logBlock;
        if (world.field_72995_K) {
            return;
        }
        Block blockID = world.func_147439_a(posX, posY, posZ);
        int j1 = world.func_72805_g(posX, posY, posZ);
        if (blockID == Blocks.field_150364_r || blockID == Blocks.field_150344_f || blockID == Blocks.field_150345_g || blockID == Blocks.field_150362_t) {
            logBlock = Blocks.field_150364_r;
        } else if (blockID == Witchery.Blocks.LOG || blockID == Witchery.Blocks.PLANKS || blockID == Witchery.Blocks.SAPLING || blockID == Witchery.Blocks.LEAVES) {
            logBlock = Witchery.Blocks.LOG;
        } else {
            logBlock = world.field_73012_v.nextInt(2) == 0 ? Blocks.field_150364_r : Witchery.Blocks.LOG;
            j1 = world.field_73012_v.nextInt(Blocks.field_150364_r == logBlock ? 4 : 3);
        }
        BlockLeaves leavesBlock = Blocks.field_150364_r == logBlock ? Blocks.field_150362_t : Witchery.Blocks.LEAVES;
        int b0 = 0;
        j1 &= 3;
        switch (sideHit) {
            case 0: 
            case 1: {
                b0 = 0;
                break;
            }
            case 2: 
            case 3: {
                b0 = 8;
                break;
            }
            case 4: 
            case 5: {
                b0 = 4;
            }
        }
        int meta = j1 | b0;
        ParticleEffect particleEffect = ParticleEffect.EXPLODE;
        int n = sideHit == 5 ? 1 : (dx = sideHit == 4 ? -1 : 0);
        int n2 = sideHit == 0 ? -1 : (dy = sideHit == 1 ? 1 : 0);
        int dz = sideHit == 3 ? 1 : (sideHit == 2 ? -1 : 0);
        int sproutExtent = extent;
        boolean isInitialBlockSolid = world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a();
        int n3 = i = sideHit == 1 && !isInitialBlockSolid ? 0 : 1;
        while (i <= sproutExtent) {
            int lz;
            int x = posX + i * dx;
            int y = posY + i * dy;
            int z = posZ + i * dz;
            if (y >= 255 || !BlockUtil.setBlockIfReplaceable(world, x, y, z, logBlock, meta)) break;
            int lx = dx == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            int ly = dy == 0 && lx == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            int n4 = lz = dz == 0 && lx == 0 && ly == 0 && world.field_73012_v.nextInt(4) == 0 ? world.field_73012_v.nextInt(3) - 1 : 0;
            if (lx != 0 || ly != 0 || lz != 0) {
                BlockUtil.setBlockIfReplaceable(world, x + lx, y + ly, z + lz, (Block)leavesBlock, meta);
            }
            ++i;
        }
        if (sideHit == 1 && (list1 = world.func_72872_a(EntityLivingBase.class, axisalignedbb = boundingBox.func_72314_b(0.0, 2.0, 0.0))) != null && !list1.isEmpty()) {
            Iterator iterator = list1.iterator();
            int x = posX + i * dx;
            int y = Math.min(posY + i * dy, 255);
            int z = posZ + i * dz;
            while (iterator.hasNext()) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)iterator.next();
                if (world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a() || world.func_147439_a(x, y + 2, z).func_149688_o().func_76220_a()) continue;
                if (entitylivingbase instanceof EntityPlayer) {
                    entitylivingbase.func_70634_a(0.5 + (double)x, (double)(y + 2), 0.5 + (double)z);
                    continue;
                }
                entitylivingbase.func_70634_a(0.5 + (double)x, (double)(y + 2), 0.5 + (double)z);
            }
        }
    }
}

