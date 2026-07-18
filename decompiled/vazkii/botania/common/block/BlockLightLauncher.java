/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

public class BlockLightLauncher
extends BlockMod
implements ILexiconable {
    public BlockLightLauncher() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("lightLauncher");
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            this.pickUpEntities(world, x, y, z);
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public void pickUpEntities(World world, int x, int y, int z) {
        ArrayList<TileLightRelay> relays = new ArrayList<TileLightRelay>();
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            TileLightRelay relay;
            TileEntity tile = world.func_147438_o(x + dir.offsetX, y, z + dir.offsetZ);
            if (!(tile instanceof TileLightRelay) || (relay = (TileLightRelay)tile).getBinding() == null) continue;
            relays.add(relay);
        }
        if (!relays.isEmpty()) {
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)x, (double)y, (double)z, (double)(x + 1), (double)(y + 1), (double)(z + 1));
            List entities = world.func_72872_a(EntityLivingBase.class, aabb);
            entities.addAll(world.func_72872_a(EntityItem.class, aabb));
            if (!entities.isEmpty()) {
                for (Entity entity : entities) {
                    TileLightRelay relay = (TileLightRelay)relays.get(world.field_73012_v.nextInt(relays.size()));
                    relay.mountEntity(entity);
                }
            }
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.luminizerTransport;
    }
}

