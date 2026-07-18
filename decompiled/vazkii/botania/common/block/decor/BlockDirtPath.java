/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.decor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockDirtPath
extends BlockMod
implements ILexiconable {
    public BlockDirtPath() {
        super(Material.field_151578_c);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.9375f, 1.0f);
        this.func_149713_g(255);
        this.func_149711_c(0.6f);
        this.func_149672_a(field_149767_g);
        this.func_149663_c("dirtPath");
        this.field_149783_u = true;
    }

    public boolean isToolEffective(String type, int metadata) {
        return type.equals("shovel");
    }

    public void func_149724_b(World world, int x, int y, int z, Entity entity) {
        float speed = 2.0f;
        float max = 0.4f;
        double motionX = Math.abs(entity.field_70159_w);
        double motionZ = Math.abs(entity.field_70179_y);
        if (motionX < (double)max) {
            entity.field_70159_w *= (double)speed;
        }
        if (motionZ < (double)max) {
            entity.field_70179_y *= (double)speed;
        }
    }

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        Block blockAbove = world.func_147439_a(x, y + 1, z);
        if (!blockAbove.isAir(world, x, y + 1, z)) {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        } else {
            this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.9375f, 1.0f);
        }
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        this.func_149719_a((IBlockAccess)world, x, y, z);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return plantable.getPlantType(world, x, y - 1, z) == EnumPlantType.Plains;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.dirtPath;
    }
}

