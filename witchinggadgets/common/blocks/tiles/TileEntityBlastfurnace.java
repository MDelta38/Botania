/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.api.visnet.VisNetHandler
 *  thaumcraft.common.lib.utils.InventoryUtils
 *  thaumcraft.common.tiles.TileBellows
 */
package witchinggadgets.common.blocks.tiles;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileBellows;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class TileEntityBlastfurnace
extends TileEntityWGBase
implements IEssentiaTransport {
    public byte position = (byte)-1;
    public int[] masterPos = new int[]{-1, -1, -1};
    int speedupTick = 0;
    int processTick = 0;
    int recipeTime = 0;
    boolean specialFuel;
    public ForgeDirection facing = ForgeDirection.UNKNOWN;
    public boolean active = false;
    List<ItemStack> inputs = new ArrayList<ItemStack>();
    public static IIcon icon_bricks;
    public static IIcon[] icon_sideBottom;
    public static IIcon[] icon_sideTop;
    public static IIcon[] icon_cornerBottomL;
    public static IIcon[] icon_cornerBottomR;
    public static IIcon[] icon_cornerTopL;
    public static IIcon[] icon_cornerTopR;
    public static IIcon icon_bottom;
    public static IIcon[] icon_bottomTBLR;
    public static IIcon icon_internal;
    public static IIcon icon_lava;
    public static Block[] brickBlock;
    public static Block stairBlock;

    public void func_145845_h() {
        TileEntityBlastfurnace master;
        if (this.position != 1 && this.position != 3 && this.position != 4 && this.position != 5 && this.position != 7 && this.position != 10 && this.position != 12 && this.position != 14 && this.position != 16 || this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.field_145850_b.func_72820_D() % 5L == 0L && this.position == 1 || this.position == 3 || this.position == 5 || this.position == 7 && this.masterPos != null && this.field_145850_b.func_147438_o(this.masterPos[0], this.masterPos[1], this.masterPos[2]) instanceof TileEntityBlastfurnace) {
            master = (TileEntityBlastfurnace)this.field_145850_b.func_147438_o(this.masterPos[0], this.masterPos[1], this.masterPos[2]);
            if (this.drawEssentia()) {
                master.speedupTick += 600;
            }
        }
        if (this.position == 10 || this.position == 12 || this.position == 14 || this.position == 16) {
            master = (TileEntityBlastfurnace)this.field_145850_b.func_147438_o(this.masterPos[0], this.masterPos[1], this.masterPos[2]);
            if (master.speedupTick <= 0) {
                master.speedupTick = VisNetHandler.drainVis((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (Aspect)Aspect.FIRE, (int)5);
            }
        }
        if (this.position != 4) {
            return;
        }
        boolean cooking = false;
        if (this.processTick > 0) {
            int calc;
            --this.processTick;
            cooking = true;
            if (this.speedupTick > 0) {
                --this.speedupTick;
            }
            if (this.processTick > (calc = this.calculateTime())) {
                this.processTick = calc;
            }
        }
        if (this.processTick <= 0 && !this.inputs.isEmpty()) {
            if (cooking) {
                ItemStack inputStack = this.inputs.get(0);
                InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe.getRecipeForInput(inputStack);
                ItemStack outputStack = recipe.getOutput();
                this.outputItem(outputStack);
                if (recipe.getBonus() != null) {
                    ItemStack bonus = Utilities.copyStackWithSize(recipe.getBonus(), 0);
                    if (this.getBellows() == 0) {
                        if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
                            ++bonus.field_77994_a;
                        }
                    } else {
                        for (int b = 0; b < this.getBellows(); ++b) {
                            if (!(this.field_145850_b.field_73012_v.nextFloat() < 0.44f)) continue;
                            ++bonus.field_77994_a;
                        }
                    }
                    this.outputItem(bonus);
                }
                inputStack.field_77994_a = inputStack.field_77994_a - (recipe.getInput() instanceof Utilities.OreDictStack ? ((Utilities.OreDictStack)recipe.getInput()).amount : ((ItemStack)recipe.getInput()).field_77994_a);
                if (inputStack.field_77994_a > 0) {
                    this.inputs.set(0, inputStack);
                } else {
                    this.inputs.remove(0);
                }
                cooking = false;
                this.specialFuel = false;
            } else {
                ItemStack inputStack = this.inputs.get(0);
                InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe.getRecipeForInput(inputStack);
                if (recipe != null) {
                    this.recipeTime = recipe.getSmeltingTime();
                    this.processTick = this.calculateTime();
                    if (recipe.isSpecial()) {
                        this.specialFuel = true;
                    }
                } else {
                    this.inputs.remove(0);
                }
                cooking = true;
            }
        }
        if (cooking != this.active) {
            this.active = cooking;
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 3, this.specialFuel ? 1 : 0);
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 4, this.active ? 1 : 0);
        }
    }

    int calculateTime() {
        return this.recipeTime / (this.speedupTick > 0 ? 2 : 1) - this.getBellows() * 40;
    }

    boolean drawEssentia() {
        TileEntity tile = ThaumcraftApiHelper.getConnectableTile((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (ForgeDirection)this.facing);
        if (tile != null) {
            IEssentiaTransport et = (IEssentiaTransport)tile;
            ForgeDirection fd = this.position == 1 ? ForgeDirection.NORTH : (this.position == 7 ? ForgeDirection.SOUTH : (this.position == 3 ? ForgeDirection.WEST : ForgeDirection.EAST));
            if (!et.canOutputTo(fd.getOpposite())) {
                return false;
            }
            if (et.getSuctionAmount(fd.getOpposite()) < this.getSuctionAmount(fd) && et.takeEssentia(this.getSuctionType(fd), 1, fd.getOpposite()) == 1) {
                return true;
            }
        }
        return false;
    }

    void outputItem(ItemStack item) {
        TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX * 2, this.field_145848_d + 1, this.field_145849_e + this.facing.offsetZ * 2);
        if (inventory != null && inventory instanceof IInventory) {
            item = InventoryUtils.placeItemStackIntoInventory((ItemStack)item, (IInventory)((IInventory)inventory), (int)this.facing.getOpposite().ordinal(), (boolean)true);
        }
        if (item != null) {
            EntityItem ei = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 + (double)this.facing.offsetX * 1.66, (double)this.field_145848_d + 1.4, (double)this.field_145849_e + 0.5 + (double)this.facing.offsetZ * 1.66, item.func_77946_l());
            ei.field_70159_w = 0.075f * (float)this.facing.offsetX;
            ei.field_70181_x = 0.025000000372529;
            ei.field_70179_y = 0.075f * (float)this.facing.offsetZ;
            this.field_145850_b.func_72838_d((Entity)ei);
        }
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 3, 0);
    }

    int getBellows() {
        int bellows = 0;
        for (ForgeDirection fd : ForgeDirection.VALID_DIRECTIONS) {
            int bz;
            int by;
            int bx;
            if (fd.equals((Object)ForgeDirection.UP) || fd.equals((Object)ForgeDirection.DOWN) || !(this.field_145850_b.func_147438_o(bx = this.field_145851_c + fd.offsetX * 2, by = this.field_145848_d + fd.offsetY * 2, bz = this.field_145849_e + fd.offsetZ * 2) instanceof TileBellows) || ((TileBellows)this.field_145850_b.func_147438_o((int)bx, (int)by, (int)bz)).orientation != fd.getOpposite().ordinal() || this.field_145850_b.func_72864_z(bx, by, bz)) continue;
            ++bellows;
        }
        return bellows;
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        this.position = tags.func_74771_c("position");
        this.speedupTick = tags.func_74762_e("speedupTick");
        this.processTick = tags.func_74762_e("processTick");
        this.recipeTime = tags.func_74762_e("recipeTime");
        this.masterPos = tags.func_74759_k("masterPos");
        this.facing = ForgeDirection.getOrientation((int)tags.func_74762_e("facing"));
        this.active = tags.func_74767_n("active");
        this.specialFuel = tags.func_74767_n("specialFuel");
        NBTTagList invList = tags.func_150295_c("inputs", 10);
        this.inputs.clear();
        for (int i = 0; i < invList.func_74745_c(); ++i) {
            this.inputs.add(ItemStack.func_77949_a((NBTTagCompound)invList.func_150305_b(i)));
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        tags.func_74774_a("position", this.position);
        tags.func_74768_a("speedupTick", this.speedupTick);
        tags.func_74768_a("processTick", this.processTick);
        tags.func_74768_a("recipeTime", this.recipeTime);
        tags.func_74783_a("masterPos", this.masterPos);
        tags.func_74768_a("facing", this.facing.ordinal());
        tags.func_74757_a("active", this.active);
        tags.func_74757_a("specialFuel", this.specialFuel);
        NBTTagList invList = new NBTTagList();
        for (ItemStack s : this.inputs) {
            invList.func_74742_a((NBTBase)s.func_77955_b(new NBTTagCompound()));
        }
        tags.func_74782_a("inputs", (NBTBase)invList);
    }

    public boolean addStackToInputs(ItemStack stack) {
        for (int i = 0; i < this.inputs.size(); ++i) {
            if (this.inputs.get(i) == null || !this.inputs.get(i).func_77969_a(stack) || this.inputs.get((int)i).field_77994_a + stack.field_77994_a > stack.func_77976_d()) continue;
            this.inputs.get((int)i).field_77994_a += stack.field_77994_a;
            return true;
        }
        this.inputs.add(stack);
        return true;
    }

    public boolean isConnectable(ForgeDirection fd) {
        return this.position == 1 && fd == ForgeDirection.NORTH || this.position == 3 && fd == ForgeDirection.WEST || this.position == 5 && fd == ForgeDirection.EAST || this.position == 7 && fd == ForgeDirection.SOUTH;
    }

    public boolean canInputFrom(ForgeDirection fd) {
        return this.position == 1 || this.position == 3 || this.position == 4 || this.position == 5 || this.position == 7;
    }

    public int getSuctionAmount(ForgeDirection fd) {
        if (this.masterPos != null && this.field_145850_b.func_147438_o(this.masterPos[0], this.masterPos[1], this.masterPos[2]) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)this.field_145850_b.func_147438_o((int)this.masterPos[0], (int)this.masterPos[1], (int)this.masterPos[2])).speedupTick < 40) {
            return 128;
        }
        return 0;
    }

    public Aspect getSuctionType(ForgeDirection fd) {
        return Aspect.FIRE;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public boolean canOutputTo(ForgeDirection fd) {
        return false;
    }

    public int getEssentiaAmount(ForgeDirection fd) {
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection fd) {
        return null;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    public void setSuction(Aspect aspect, int amount) {
    }

    public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
        return 0;
    }

    public int takeEssentia(Aspect aspect, int amount, ForgeDirection fd) {
        return 0;
    }

    public boolean func_145842_c(int eventNum, int arg) {
        if (eventNum == 3) {
            this.specialFuel = arg == 1;
            this.field_145850_b.func_147458_c(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
            return true;
        }
        if (eventNum == 4) {
            this.active = arg == 1;
            this.field_145850_b.func_147458_c(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e - 1, this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e + 1);
            return true;
        }
        if (eventNum == 3) {
            for (int i = 0; i < 5; ++i) {
                WitchingGadgets.proxy.createFurnaceOutputBlobFx(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
            }
            this.field_145850_b.func_72980_b((double)((float)this.field_145851_c + 0.5f + (float)this.facing.offsetX * 1.66f), (double)((float)this.field_145848_d + 1.3f), (double)((float)this.field_145849_e + 0.5f + (float)this.facing.offsetZ * 1.66f), "liquid.lavapop", 0.1f, 1.0f + this.field_145850_b.field_73012_v.nextFloat() * 0.8f, false);
            return true;
        }
        if (eventNum == 5) {
            for (int i = 0; i < 3; ++i) {
                this.field_145850_b.func_72869_a("lava", (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.9, (double)this.field_145849_e + 0.5, 0.0, 0.0, 0.0);
            }
            this.field_145850_b.func_72980_b((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 2.5f), (double)((float)this.field_145849_e + 0.5f), "random.fizz", 0.5f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f, false);
            return true;
        }
        return false;
    }

    public IIcon getTexture(int side) {
        int i = this.masterPos == null || this.masterPos.length < 3 ? 0 : (this.field_145850_b.func_147438_o(this.masterPos[0], this.masterPos[1], this.masterPos[2]) instanceof TileEntityBlastfurnace && ((TileEntityBlastfurnace)this.field_145850_b.func_147438_o((int)this.masterPos[0], (int)this.masterPos[1], (int)this.masterPos[2])).active ? (((TileEntityBlastfurnace)this.field_145850_b.func_147438_o((int)this.masterPos[0], (int)this.masterPos[1], (int)this.masterPos[2])).specialFuel ? 2 : 1) : 0);
        switch (this.position) {
            case 0: {
                return side == 2 ? icon_cornerBottomR[i] : (side == 4 ? icon_cornerBottomL[i] : (side == 0 ? icon_cornerTopL[0] : icon_bricks));
            }
            case 1: {
                return side == 2 ? icon_sideBottom[i] : (side == 0 ? icon_bottomTBLR[0] : icon_bricks);
            }
            case 2: {
                return side == 2 ? icon_cornerBottomL[i] : (side == 5 ? icon_cornerBottomR[i] : (side == 0 ? icon_cornerTopR[0] : icon_bricks));
            }
            case 3: {
                return side == 4 ? icon_sideBottom[i] : (side == 0 ? icon_bottomTBLR[2] : icon_bricks);
            }
            case 4: {
                return icon_bottom;
            }
            case 5: {
                return side == 5 ? icon_sideBottom[i] : (side == 0 ? icon_bottomTBLR[3] : icon_bricks);
            }
            case 6: {
                return side == 3 ? icon_cornerBottomL[i] : (side == 4 ? icon_cornerBottomR[i] : (side == 0 ? icon_cornerBottomL[0] : icon_bricks));
            }
            case 7: {
                return side == 3 ? icon_sideBottom[i] : (side == 0 ? icon_bottomTBLR[1] : icon_bricks);
            }
            case 8: {
                return side == 3 ? icon_cornerBottomR[i] : (side == 5 ? icon_cornerBottomL[i] : (side == 0 ? icon_cornerBottomR[0] : icon_bricks));
            }
            case 9: {
                return side == 2 ? icon_cornerTopR[i] : icon_cornerTopL[i];
            }
            case 10: {
                return side == 2 ? icon_sideTop[i] : (i == 1 ? icon_internal : icon_bricks);
            }
            case 11: {
                return side == 2 ? icon_cornerTopL[i] : icon_cornerTopR[i];
            }
            case 12: {
                return side == 4 ? icon_sideTop[i] : (i == 1 ? icon_internal : icon_bricks);
            }
            case 14: {
                return side == 5 ? icon_sideTop[i] : (i == 1 ? icon_internal : icon_bricks);
            }
            case 15: {
                return side == 3 ? icon_cornerTopL[i] : icon_cornerTopR[i];
            }
            case 16: {
                return side == 3 ? icon_sideTop[i] : (i == 1 ? icon_internal : icon_bricks);
            }
            case 17: {
                return side == 3 ? icon_cornerTopR[i] : icon_cornerTopL[i];
            }
            case 22: {
                return icon_lava;
            }
        }
        return icon_bricks;
    }

    static {
        brickBlock = new Block[18];
    }
}

