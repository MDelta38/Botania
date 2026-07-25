/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  dan200.computercraft.api.turtle.ITurtleAccess
 *  dan200.computercraft.api.turtle.ITurtleUpgrade
 *  dan200.computercraft.api.turtle.TurtleCommandResult
 *  dan200.computercraft.api.turtle.TurtleSide
 *  dan200.computercraft.api.turtle.TurtleUpgradeType
 *  dan200.computercraft.api.turtle.TurtleVerb
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.event.TextureStitchEvent
 */
package thaumic.tinkerer.common.compat;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import dan200.computercraft.api.turtle.TurtleCommandResult;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.api.turtle.TurtleUpgradeType;
import dan200.computercraft.api.turtle.TurtleVerb;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.TextureStitchEvent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockGas;
import thaumic.tinkerer.common.item.ItemGasRemover;

@Optional.Interface(iface="dan200.computercraft.api.turtle.ITurtleUpgrade", modid="ComputerCraft")
public class FumeTool
implements ITurtleUpgrade {
    @SideOnly(value=Side.CLIENT)
    public static IIcon icon;

    @Optional.Method(modid="ComputerCraft")
    public int getUpgradeID() {
        return 171;
    }

    @Optional.Method(modid="ComputerCraft")
    public String getUnlocalisedAdjective() {
        return "ttcomputer.dissipator";
    }

    @Optional.Method(modid="ComputerCraft")
    public TurtleUpgradeType getType() {
        return TurtleUpgradeType.Tool;
    }

    @Optional.Method(modid="ComputerCraft")
    public ItemStack getCraftingItem() {
        return new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemGasRemover.class));
    }

    @Optional.Method(modid="ComputerCraft")
    public IPeripheral createPeripheral(ITurtleAccess turtle, TurtleSide side) {
        return null;
    }

    @Optional.Method(modid="ComputerCraft")
    public TurtleCommandResult useTool(ITurtleAccess turtle, TurtleSide side, TurtleVerb verb, int direction) {
        if (verb == TurtleVerb.Dig) {
            ChunkCoordinates pos = turtle.getPosition();
            int xs = pos.field_71574_a;
            int ys = pos.field_71572_b;
            int zs = pos.field_71573_c;
            for (int x = xs - 3; x < xs + 3; ++x) {
                for (int y = ys - 3; y < ys + 3; ++y) {
                    for (int z = zs - 3; z < zs + 3; ++z) {
                        Block block = turtle.getWorld().func_147439_a(x, y, z);
                        if (block == null || !(block instanceof BlockGas)) continue;
                        BlockGas gas = (BlockGas)block;
                        gas.placeParticle(turtle.getWorld(), x, y, z);
                        turtle.getWorld().func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
                    }
                }
            }
            return TurtleCommandResult.success();
        }
        return TurtleCommandResult.failure();
    }

    @Optional.Method(modid="ComputerCraft")
    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ITurtleAccess turtle, TurtleSide side) {
        return icon;
    }

    @Optional.Method(modid="ComputerCraft")
    public void update(ITurtleAccess turtle, TurtleSide side) {
    }

    @SubscribeEvent
    @Optional.Method(modid="ComputerCraft")
    public void registerIcons(TextureStitchEvent evt) {
        if (evt.map.func_130086_a() == 1) {
            icon = evt.map.func_94245_a("ttinkerer:gasRemover");
        }
    }
}

