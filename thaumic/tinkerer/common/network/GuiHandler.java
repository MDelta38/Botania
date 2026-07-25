/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.network;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumic.tinkerer.client.gui.GuiAnimationTablet;
import thaumic.tinkerer.client.gui.GuiAspectAnalyzer;
import thaumic.tinkerer.client.gui.GuiEnchanting;
import thaumic.tinkerer.client.gui.GuiMobMagnet;
import thaumic.tinkerer.client.gui.GuiRemotePlacer;
import thaumic.tinkerer.client.gui.kami.GuiIchorPouch;
import thaumic.tinkerer.client.gui.kami.GuiWarpGate;
import thaumic.tinkerer.client.gui.kami.GuiWarpGateDestinations;
import thaumic.tinkerer.common.block.tile.TileAspectAnalyzer;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.block.tile.TileRPlacer;
import thaumic.tinkerer.common.block.tile.container.ContainerAnimationTablet;
import thaumic.tinkerer.common.block.tile.container.ContainerAspectAnalyzer;
import thaumic.tinkerer.common.block.tile.container.ContainerEnchanter;
import thaumic.tinkerer.common.block.tile.container.ContainerMobMagnet;
import thaumic.tinkerer.common.block.tile.container.ContainerRemotePlacer;
import thaumic.tinkerer.common.block.tile.container.kami.ContainerIchorPouch;
import thaumic.tinkerer.common.block.tile.container.kami.ContainerWarpGate;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        switch (ID) {
            case 0: {
                return new ContainerAnimationTablet((TileAnimationTablet)tile, player.field_71071_by);
            }
            case 1: {
                return new ContainerMobMagnet((TileMobMagnet)tile, player.field_71071_by);
            }
            case 2: {
                return new ContainerEnchanter((TileEnchanter)tile, player.field_71071_by);
            }
            case 3: {
                return new ContainerAspectAnalyzer((TileAspectAnalyzer)tile, player.field_71071_by);
            }
            case 50: {
                return new ContainerIchorPouch(player);
            }
            case 51: {
                return new ContainerWarpGate((TileWarpGate)tile, player.field_71071_by);
            }
            case 4: {
                return new ContainerRemotePlacer((TileRPlacer)tile, player.field_71071_by);
            }
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        switch (ID) {
            case 0: {
                return new GuiAnimationTablet((TileAnimationTablet)tile, player.field_71071_by);
            }
            case 1: {
                return new GuiMobMagnet((TileMobMagnet)tile, player.field_71071_by);
            }
            case 2: {
                return new GuiEnchanting((TileEnchanter)tile, player.field_71071_by);
            }
            case 3: {
                return new GuiAspectAnalyzer((TileAspectAnalyzer)tile, player.field_71071_by);
            }
            case 50: {
                return new GuiIchorPouch(new ContainerIchorPouch(player));
            }
            case 51: {
                return new GuiWarpGate((TileWarpGate)tile, player.field_71071_by);
            }
            case 52: {
                return new GuiWarpGateDestinations((TileWarpGate)tile);
            }
            case 4: {
                return new GuiRemotePlacer((TileRPlacer)tile, player.field_71071_by);
            }
        }
        return null;
    }
}

