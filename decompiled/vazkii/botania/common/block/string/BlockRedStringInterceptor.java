/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package vazkii.botania.common.block.string;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import vazkii.botania.common.block.string.BlockRedString;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.block.tile.string.TileRedStringInterceptor;

public class BlockRedStringInterceptor
extends BlockRedString {
    public BlockRedStringInterceptor() {
        super("redStringInterceptor");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onInteract(PlayerInteractEvent event) {
        if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            TileRedStringInterceptor.onInteract(event.entityPlayer, event.world, event.x, event.y, event.z);
        }
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        return (world.func_72805_g(x, y, z) & 8) == 0 ? 0 : 15;
    }

    public void func_149674_a(World world, int x, int y, int z, Random update) {
        world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) & 0xFFFFFFF7, 3);
    }

    public int func_149738_a(World p_149738_1_) {
        return 2;
    }

    public TileRedString createNewTileEntity(World world, int meta) {
        return new TileRedStringInterceptor();
    }
}

