/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.IArchitect;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.tiles.TileWarded;

public class ItemFocusWarding
extends ItemFocusBasic
implements IArchitect {
    public IIcon iconOrnament;
    IIcon depthIcon = null;
    private static final AspectList cost = new AspectList().add(Aspect.EARTH, 25).add(Aspect.ORDER, 25).add(Aspect.WATER, 10);
    public static HashMap<String, Long> delay = new HashMap();
    ArrayList<BlockCoordinates> checked = new ArrayList();

    public ItemFocusWarding() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BWA" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.depthIcon = ir.func_94245_a("thaumcraft:focus_warding_depth");
        this.icon = ir.func_94245_a("thaumcraft:focus_warding");
        this.iconOrnament = ir.func_94245_a("thaumcraft:focus_warding_orn");
    }

    @Override
    public IIcon getFocusDepthLayerIcon(ItemStack itemstack) {
        return this.depthIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int renderPass) {
        return renderPass == 1 ? this.icon : this.iconOrnament;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    @Override
    public IIcon getOrnament(ItemStack itemstack) {
        return this.iconOrnament;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 16771535;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        return cost.copy();
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition mop) {
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        player.func_71038_i();
        if (!world.field_72995_K && mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            String key = mop.field_72311_b + ":" + mop.field_72312_c + ":" + mop.field_72309_d + ":" + world.field_73011_w.field_76574_g;
            if (delay.containsKey(key) && delay.get(key) > System.currentTimeMillis()) {
                return itemstack;
            }
            delay.put(key, System.currentTimeMillis() + 500L);
            TileEntity tt = world.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
            boolean solid = world.func_147445_c(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, true);
            if (tt == null && solid) {
                ArrayList<BlockCoordinates> blocks = this.getArchitectBlocks(itemstack, world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, player);
                for (BlockCoordinates c : blocks) {
                    if (!wand.consumeAllVis(itemstack, player, this.getVisCost(itemstack), true, false)) break;
                    if (world.func_147438_o(c.x, c.y, c.z) != null || !world.func_147445_c(c.x, c.y, c.z, true)) continue;
                    Block bi = world.func_147439_a(c.x, c.y, c.z);
                    int md = world.func_72805_g(c.x, c.y, c.z);
                    int ll = bi.getLightValue((IBlockAccess)world, c.x, c.y, c.z);
                    world.func_147465_d(c.x, c.y, c.z, ConfigBlocks.blockWarded, md, 3);
                    TileEntity tile = world.func_147438_o(c.x, c.y, c.z);
                    if (tile == null || !(tile instanceof TileWarded)) continue;
                    TileWarded tw = (TileWarded)tile;
                    tw.block = bi;
                    tw.blockMd = (byte)md;
                    tw.light = (byte)ll;
                    tw.owner = player.func_70005_c_().hashCode();
                    world.func_147471_g(c.x, c.y, c.z);
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(c.x, c.y, c.z, 16556032), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)c.x, (double)c.y, (double)c.z, 32.0));
                }
                world.func_72908_a((double)mop.field_72311_b + 0.5, (double)mop.field_72312_c + 0.5, (double)mop.field_72309_d + 0.5, "thaumcraft:zap", 0.25f, 1.0f);
            } else if (tt != null && tt instanceof TileWarded) {
                TileWarded tw = (TileWarded)tt;
                if (tw.owner == player.func_70005_c_().hashCode()) {
                    ArrayList<BlockCoordinates> blocks = this.getArchitectBlocks(itemstack, world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, mop.field_72310_e, player);
                    for (BlockCoordinates c : blocks) {
                        TileEntity tile = world.func_147438_o(c.x, c.y, c.z);
                        if (tile == null || !(tile instanceof TileWarded)) continue;
                        TileWarded tw2 = (TileWarded)tile;
                        if (tw2.owner != player.func_70005_c_().hashCode()) continue;
                        world.func_147465_d(c.x, c.y, c.z, tw2.block, (int)tw2.blockMd, 3);
                        world.func_147471_g(c.x, c.y, c.z);
                        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockSparkle(c.x, c.y, c.z, 16556032), new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, (double)c.x, (double)c.y, (double)c.z, 32.0));
                    }
                    world.func_72908_a((double)mop.field_72311_b + 0.5, (double)mop.field_72312_c + 0.5, (double)mop.field_72309_d + 0.5, "thaumcraft:zap", 0.25f, 1.0f);
                }
            }
        }
        return itemstack;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.architect};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
        }
        return null;
    }

    @Override
    public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank) {
        if (type.equals(FocusUpgradeType.enlarge)) {
            return this.isUpgradedWith(focusstack, FocusUpgradeType.architect);
        }
        return true;
    }

    @Override
    public int getMaxAreaSize(ItemStack focusstack) {
        return 3 + this.getUpgradeLevel(focusstack, FocusUpgradeType.enlarge);
    }

    @Override
    public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player) {
        ArrayList<BlockCoordinates> out = new ArrayList<BlockCoordinates>();
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        ItemFocusBasic focus = wand.getFocus(stack);
        this.checked.clear();
        boolean tiles = false;
        TileEntity tt = world.func_147438_o(x, y, z);
        boolean solid = world.func_147445_c(x, y, z, true);
        if ((tt != null || !solid) && tt != null && tt instanceof TileWarded) {
            tiles = true;
        }
        int sizeX = 0;
        int sizeY = 0;
        int sizeZ = 0;
        if (this.isUpgradedWith(wand.getFocusItem(stack), FocusUpgradeType.architect)) {
            sizeX = WandManager.getAreaX(stack);
            sizeY = WandManager.getAreaY(stack);
            sizeZ = WandManager.getAreaZ(stack);
        }
        if (side == 2 || side == 3) {
            this.checkNeighbours(world, x, y, z, new BlockCoordinates(x, y, z), side, sizeZ, sizeY, sizeX, out, player, tiles);
        } else {
            this.checkNeighbours(world, x, y, z, new BlockCoordinates(x, y, z), side, sizeX, sizeY, sizeZ, out, player, tiles);
        }
        return out;
    }

    public void checkNeighbours(World world, int x, int y, int z, BlockCoordinates pos, int side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockCoordinates> list, EntityPlayer player, boolean tiles) {
        if (this.checked.contains(pos)) {
            return;
        }
        this.checked.add(pos);
        switch (side) {
            case 0: 
            case 1: {
                if (Math.abs(pos.x - x) > sizeX) {
                    return;
                }
                if (Math.abs(pos.z - z) > sizeZ) {
                    return;
                }
                if (Math.abs(pos.y - y) <= sizeY) break;
                return;
            }
            case 2: 
            case 3: {
                if (Math.abs(pos.x - x) > sizeX) {
                    return;
                }
                if (Math.abs(pos.y - y) > sizeZ) {
                    return;
                }
                if (Math.abs(pos.z - z) <= sizeY) break;
                return;
            }
            case 4: 
            case 5: {
                if (Math.abs(pos.y - y) > sizeX) {
                    return;
                }
                if (Math.abs(pos.z - z) > sizeZ) {
                    return;
                }
                if (Math.abs(pos.x - x) <= sizeY) break;
                return;
            }
        }
        TileEntity tt = world.func_147438_o(pos.x, pos.y, pos.z);
        boolean solid = world.func_147445_c(pos.x, pos.y, pos.z, true);
        if (tiles && (tt == null || !(tt instanceof TileWarded))) {
            return;
        }
        if (!(tiles || tt == null && solid)) {
            return;
        }
        if (tiles && tt != null && tt instanceof TileWarded) {
            TileWarded tw2 = (TileWarded)tt;
            if (tw2.owner != player.func_70005_c_().hashCode()) {
                return;
            }
        }
        if (world.func_147437_c(pos.x, pos.y, pos.z)) {
            return;
        }
        list.add(pos);
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            BlockCoordinates cc = new BlockCoordinates(pos.x + dir.offsetX, pos.y + dir.offsetY, pos.z + dir.offsetZ);
            this.checkNeighbours(world, x, y, z, cc, side, sizeX, sizeY, sizeZ, list, player, tiles);
        }
    }

    @Override
    public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis) {
        int dim = WandManager.getAreaDim(stack);
        if (dim == 0) {
            return true;
        }
        switch (side) {
            case 0: 
            case 1: {
                if (!(axis == IArchitect.EnumAxis.X && dim == 1 || axis == IArchitect.EnumAxis.Z && dim == 2) && (axis != IArchitect.EnumAxis.Y || dim != 3)) break;
                return true;
            }
            case 2: 
            case 3: {
                if (!(axis == IArchitect.EnumAxis.Y && dim == 1 || axis == IArchitect.EnumAxis.X && dim == 2) && (axis != IArchitect.EnumAxis.Z || dim != 3)) break;
                return true;
            }
            case 4: 
            case 5: {
                if (!(axis == IArchitect.EnumAxis.Y && dim == 1 || axis == IArchitect.EnumAxis.Z && dim == 2) && (axis != IArchitect.EnumAxis.X || dim != 3)) break;
                return true;
            }
        }
        return false;
    }
}

