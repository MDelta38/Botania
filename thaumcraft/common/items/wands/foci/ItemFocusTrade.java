/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.items.wands.foci;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.BlockCoordinates;
import thaumcraft.api.IArchitect;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.events.ServerTickEventsFML;
import thaumcraft.common.lib.utils.BlockUtils;

public class ItemFocusTrade
extends ItemFocusBasic
implements IArchitect {
    public IIcon iconOrnament;
    private static final AspectList cost = new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.EARTH, 5).add(Aspect.ORDER, 5);
    private static AspectList cost2 = null;
    ArrayList<BlockCoordinates> checked = new ArrayList();

    public ItemFocusTrade() {
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "BT" + super.getSortingHelper(itemstack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:focus_trade");
        this.iconOrnament = ir.func_94245_a("thaumcraft:focus_trade_orn");
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

    protected MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer) {
        float f = 1.0f;
        float f1 = par2EntityPlayer.field_70127_C + (par2EntityPlayer.field_70125_A - par2EntityPlayer.field_70127_C) * f;
        float f2 = par2EntityPlayer.field_70126_B + (par2EntityPlayer.field_70177_z - par2EntityPlayer.field_70126_B) * f;
        double d0 = par2EntityPlayer.field_70169_q + (par2EntityPlayer.field_70165_t - par2EntityPlayer.field_70169_q) * (double)f;
        double d1 = par2EntityPlayer.field_70167_r + (par2EntityPlayer.field_70163_u - par2EntityPlayer.field_70167_r) * (double)f + (double)(par1World.field_72995_K ? par2EntityPlayer.func_70047_e() - par2EntityPlayer.getDefaultEyeHeight() : par2EntityPlayer.func_70047_e());
        double d2 = par2EntityPlayer.field_70166_s + (par2EntityPlayer.field_70161_v - par2EntityPlayer.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0;
        if (par2EntityPlayer instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)par2EntityPlayer).field_71134_c.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.func_72441_c((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return par1World.func_72901_a(vec3, vec31, false);
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingobjectposition) {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player);
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            int x = mop.field_72311_b;
            int y = mop.field_72312_c;
            int z = mop.field_72309_d;
            Block bi = world.func_147439_a(x, y, z);
            int md = world.func_72805_g(x, y, z);
            if (player.func_70093_af()) {
                if (!world.field_72995_K && world.func_147438_o(x, y, z) == null) {
                    ItemStack isout = new ItemStack(bi, 1, md);
                    try {
                        ItemStack is;
                        if (bi != Blocks.field_150350_a && (is = BlockUtils.createStackedBlock(bi, md)) != null) {
                            isout = is.func_77946_l();
                        }
                    }
                    catch (Exception e) {
                        // empty catch block
                    }
                    this.storePickedBlock(itemstack, isout);
                } else {
                    player.func_71038_i();
                }
            } else {
                ItemStack pb = this.getPickedBlock(itemstack);
                if (pb != null && world.field_72995_K) {
                    player.func_71038_i();
                } else if (pb != null && world.func_147438_o(x, y, z) == null && world.func_147439_a(x, y, z).func_149688_o() != Config.taintMaterial) {
                    if (this.isUpgradedWith(wand.getFocusItem(itemstack), FocusUpgradeType.architect)) {
                        int sizeX = WandManager.getAreaX(itemstack);
                        int sizeZ = WandManager.getAreaZ(itemstack);
                        ArrayList<BlockCoordinates> blocks = this.getArchitectBlocks(itemstack, world, x, y, z, mop.field_72310_e, player);
                        for (BlockCoordinates c : blocks) {
                            ServerTickEventsFML.addSwapper(world, c.x, c.y, c.z, world.func_147439_a(c.x, c.y, c.z), world.func_72805_g(c.x, c.y, c.z), pb, 0, player, player.field_71071_by.field_70461_c);
                        }
                    } else {
                        ServerTickEventsFML.addSwapper(world, x, y, z, world.func_147439_a(x, y, z), world.func_72805_g(x, y, z), pb, 3 + wand.getFocusEnlarge(itemstack), player, player.field_71071_by.field_70461_c);
                    }
                }
            }
        }
        return itemstack;
    }

    public float func_150893_a(ItemStack itemstack, Block block) {
        return 0.0f;
    }

    public boolean onEntitySwing(EntityLivingBase player, ItemStack stack) {
        if (!player.field_70170_p.field_72995_K && player instanceof EntityPlayer) {
            ItemStack pb = this.getPickedBlock(stack);
            MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(player.field_70170_p, (EntityPlayer)player);
            if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                int x = mop.field_72311_b;
                int y = mop.field_72312_c;
                int z = mop.field_72309_d;
                if (pb != null && player.field_70170_p.func_147438_o(x, y, z) == null && player.field_70170_p.func_147439_a(x, y, z).func_149688_o() != Config.taintMaterial) {
                    ServerTickEventsFML.addSwapper(player.field_70170_p, x, y, z, player.field_70170_p.func_147439_a(x, y, z), player.field_70170_p.func_72805_g(x, y, z), pb, 0, (EntityPlayer)player, ((EntityPlayer)player).field_71071_by.field_70461_c);
                }
            }
        }
        return super.onEntitySwing(player, stack);
    }

    public void storePickedBlock(ItemStack stack, ItemStack stackout) {
        NBTTagCompound item = new NBTTagCompound();
        stack.func_77983_a("picked", (NBTBase)stackout.func_77955_b(item));
    }

    public ItemStack getPickedBlock(ItemStack stack) {
        ItemStack out = null;
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("picked")) {
            out = new ItemStack(Blocks.field_150350_a);
            out.func_77963_c(stack.field_77990_d.func_74775_l("picked"));
        }
        return out;
    }

    @Override
    public int getFocusColor(ItemStack itemstack) {
        return 8747923;
    }

    @Override
    public AspectList getVisCost(ItemStack itemstack) {
        if (this.isUpgradedWith(itemstack, FocusUpgradeType.silktouch)) {
            if (cost2 == null) {
                cost2 = new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1).add(Aspect.WATER, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
                cost2.add(cost);
            }
            return cost2;
        }
        return cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank) {
        switch (rank) {
            case 1: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
            case 2: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
            case 3: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.treasure, FocusUpgradeType.architect};
            }
            case 4: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge};
            }
            case 5: {
                return new FocusUpgradeType[]{FocusUpgradeType.frugal, FocusUpgradeType.enlarge, FocusUpgradeType.silktouch};
            }
        }
        return null;
    }

    @Override
    public int getMaxAreaSize(ItemStack focusstack) {
        return 3 + this.getUpgradeLevel(focusstack, FocusUpgradeType.enlarge) * 2;
    }

    @Override
    public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack stack, World world, int x, int y, int z, int side, EntityPlayer player) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        ItemFocusBasic focus = wand.getFocus(stack);
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        ArrayList<BlockCoordinates> out = new ArrayList<BlockCoordinates>();
        this.checked.clear();
        if (side == 2 || side == 3) {
            this.checkNeighbours(world, x, y, z, bi, md, new BlockCoordinates(x, y, z), side, WandManager.getAreaZ(stack), WandManager.getAreaY(stack), WandManager.getAreaX(stack), out, player);
        } else {
            this.checkNeighbours(world, x, y, z, bi, md, new BlockCoordinates(x, y, z), side, WandManager.getAreaX(stack), WandManager.getAreaY(stack), WandManager.getAreaZ(stack), out, player);
        }
        return out;
    }

    public void checkNeighbours(World world, int x, int y, int z, Block bi, int md, BlockCoordinates pos, int side, int sizeX, int sizeY, int sizeZ, ArrayList<BlockCoordinates> list, EntityPlayer player) {
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
                if (Math.abs(pos.z - z) <= sizeZ) break;
                return;
            }
            case 2: 
            case 3: {
                if (Math.abs(pos.x - x) > sizeX) {
                    return;
                }
                if (Math.abs(pos.y - y) <= sizeZ) break;
                return;
            }
            case 4: 
            case 5: {
                if (Math.abs(pos.y - y) > sizeX) {
                    return;
                }
                if (Math.abs(pos.z - z) <= sizeZ) break;
                return;
            }
        }
        if (world.func_147439_a(pos.x, pos.y, pos.z) != bi || world.func_72805_g(pos.x, pos.y, pos.z) != md || !BlockUtils.isBlockExposed(world, pos.x, pos.y, pos.z) || world.func_147437_c(pos.x, pos.y, pos.z) || !(world.func_147439_a(pos.x, pos.y, pos.z).func_149712_f(world, pos.x, pos.y, pos.z) >= 0.0f) || !world.func_72962_a(player, pos.x, pos.y, pos.z)) {
            return;
        }
        list.add(pos);
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (dir.ordinal() == side || dir.getOpposite().ordinal() == side) continue;
            BlockCoordinates cc = new BlockCoordinates(pos.x + dir.offsetX, pos.y + dir.offsetY, pos.z + dir.offsetZ);
            this.checkNeighbours(world, x, y, z, bi, md, cc, side, sizeX, sizeY, sizeZ, list, player);
        }
    }

    @Override
    public boolean showAxis(ItemStack stack, World world, EntityPlayer player, int side, IArchitect.EnumAxis axis) {
        int dim = WandManager.getAreaDim(stack);
        switch (side) {
            case 0: 
            case 1: {
                if ((axis != IArchitect.EnumAxis.X || dim != 0 && dim != 1) && (axis != IArchitect.EnumAxis.Z || dim != 0 && dim != 2)) break;
                return true;
            }
            case 2: 
            case 3: {
                if ((axis != IArchitect.EnumAxis.Y || dim != 0 && dim != 1) && (axis != IArchitect.EnumAxis.X || dim != 0 && dim != 2)) break;
                return true;
            }
            case 4: 
            case 5: {
                if ((axis != IArchitect.EnumAxis.Y || dim != 0 && dim != 1) && (axis != IArchitect.EnumAxis.Z || dim != 0 && dim != 2)) break;
                return true;
            }
        }
        return false;
    }
}

