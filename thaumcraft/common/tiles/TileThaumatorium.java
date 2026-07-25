/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.container.InventoryFake;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileBrainbox;

public class TileThaumatorium
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport,
ISidedInventory {
    public ItemStack inputStack = null;
    public AspectList essentia = new AspectList();
    public ArrayList<Integer> recipeHash = new ArrayList();
    public ArrayList<AspectList> recipeEssentia = new ArrayList();
    public ArrayList<String> recipePlayer = new ArrayList();
    public int currentCraft = -1;
    public int maxRecipes = 1;
    public ForgeDirection facing = ForgeDirection.NORTH;
    public Aspect currentSuction = null;
    int venting = 0;
    int counter = 0;
    boolean heated = false;
    CrucibleRecipe currentRecipe = null;
    public Container eventHandler;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74771_c("facing"));
        this.essentia.readFromNBT(nbttagcompound);
        this.maxRecipes = nbttagcompound.func_74771_c("maxrec");
        this.recipeEssentia = new ArrayList();
        this.recipeHash = new ArrayList();
        this.recipePlayer = new ArrayList();
        int[] hashes = nbttagcompound.func_74759_k("recipes");
        if (hashes != null) {
            for (int hash : hashes) {
                CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(hash);
                if (recipe == null) continue;
                this.recipeEssentia.add(recipe.aspects.copy());
                this.recipePlayer.add("");
                this.recipeHash.add(hash);
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("facing", (byte)this.facing.ordinal());
        nbttagcompound.func_74774_a("maxrec", (byte)this.maxRecipes);
        this.essentia.writeToNBT(nbttagcompound);
        int[] hashes = new int[this.recipeHash.size()];
        int a = 0;
        for (Integer i : this.recipeHash) {
            hashes[a] = i;
            ++a;
        }
        nbttagcompound.func_74783_a("recipes", hashes);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
        if (nbttaglist.func_74745_c() > 0) {
            this.inputStack = ItemStack.func_77949_a((NBTTagCompound)nbttaglist.func_150305_b(0));
        }
        NBTTagList nbttaglist2 = nbtCompound.func_150295_c("OutputPlayer", 8);
        for (int a = 0; a < nbttaglist2.func_74745_c(); ++a) {
            if (this.recipePlayer.size() <= a) continue;
            this.recipePlayer.set(a, nbttaglist2.func_150307_f(a));
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        NBTTagList nbttaglist = new NBTTagList();
        if (this.inputStack != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)0);
            this.inputStack.func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbtCompound.func_74782_a("Items", (NBTBase)nbttaglist);
        NBTTagList nbttaglist2 = new NBTTagList();
        if (this.recipePlayer.size() > 0) {
            for (int a = 0; a < this.recipePlayer.size(); ++a) {
                if (this.recipePlayer.get(a) == null) continue;
                NBTTagString nbttagcompound1 = new NBTTagString(this.recipePlayer.get(a));
                nbttaglist2.func_74742_a((NBTBase)nbttagcompound1);
            }
        }
        nbtCompound.func_74782_a("OutputPlayer", (NBTBase)nbttaglist2);
    }

    public boolean canUpdate() {
        return true;
    }

    boolean checkHeat() {
        Material mat = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e).func_149688_o();
        Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        return mat == Material.field_151587_i || mat == Material.field_151581_o || bi == ConfigBlocks.blockAiry && md == 1;
    }

    public ItemStack getCurrentOutputRecipe() {
        CrucibleRecipe recipe;
        ItemStack out = null;
        if (this.currentCraft >= 0 && this.recipeHash != null && this.recipeHash.size() > 0 && (recipe = ThaumcraftApi.getCrucibleRecipeFromHash(this.recipeHash.get(this.currentCraft))) != null) {
            out = recipe.getRecipeOutput().func_77946_l();
        }
        return out;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.counter == 0 || this.counter % 40 == 0) {
                this.heated = this.checkHeat();
                this.getUpgrades();
            }
            ++this.counter;
            if (this.heated && !this.gettingPower() && this.counter % 5 == 0 && this.recipeHash != null && this.recipeHash.size() > 0) {
                if (this.inputStack == null) {
                    this.currentSuction = null;
                    return;
                }
                if (this.currentCraft < 0 || this.currentCraft >= this.recipeHash.size() || this.currentRecipe == null || !this.currentRecipe.catalystMatches(this.inputStack)) {
                    for (int a = 0; a < this.recipeHash.size(); ++a) {
                        CrucibleRecipe recipe = ThaumcraftApi.getCrucibleRecipeFromHash(this.recipeHash.get(a));
                        if (!recipe.catalystMatches(this.inputStack)) continue;
                        this.currentCraft = a;
                        this.currentRecipe = recipe;
                        break;
                    }
                }
                if (this.currentCraft < 0 || this.currentCraft >= this.recipeHash.size()) {
                    return;
                }
                TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ);
                if (inventory != null && inventory instanceof IInventory) {
                    ItemStack dropped = this.getCurrentOutputRecipe();
                    if ((dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.facing.getOpposite().ordinal(), false)) != null) {
                        return;
                    }
                }
                boolean done = true;
                this.currentSuction = null;
                for (Aspect aspect : this.recipeEssentia.get(this.currentCraft).getAspectsSorted()) {
                    if (this.essentia.getAmount(aspect) >= this.recipeEssentia.get(this.currentCraft).getAmount(aspect)) continue;
                    this.currentSuction = aspect;
                    done = false;
                    break;
                }
                if (done) {
                    this.completeRecipe();
                } else if (this.currentSuction != null) {
                    this.fill();
                }
            }
        } else if (this.venting > 0) {
            --this.venting;
            float fx = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            float fz = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            float fy = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            float fx2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            float fz2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            float fy2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
            int color = 0xFFFFFF;
            Thaumcraft.proxy.drawVentParticles(this.field_145850_b, (float)this.field_145851_c + 0.5f + fx + (float)this.facing.offsetX / 2.0f, (float)this.field_145848_d + 0.5f + fy, (float)this.field_145849_e + 0.5f + fz + (float)this.facing.offsetZ / 2.0f, (float)this.facing.offsetX / 4.0f + fx2, fy2, (float)this.facing.offsetZ / 4.0f + fz2, color);
        }
    }

    private void completeRecipe() {
        if (this.currentRecipe != null && this.currentCraft < this.recipeHash.size() && this.currentRecipe.matches(this.essentia, this.inputStack) && this.func_70298_a(0, 1) != null) {
            TileEntity inventory;
            this.essentia = new AspectList();
            ItemStack dropped = this.getCurrentOutputRecipe();
            EntityPlayer p = this.field_145850_b.func_72924_a(this.recipePlayer.get(this.currentCraft));
            if (p != null) {
                FMLCommonHandler.instance().firePlayerCraftingEvent(p, dropped, (IInventory)new InventoryFake(new ItemStack[]{this.inputStack}));
            }
            if ((inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ)) != null && inventory instanceof IInventory) {
                dropped = InventoryUtils.placeItemStackIntoInventory(dropped, (IInventory)inventory, this.facing.getOpposite().ordinal(), true);
            }
            if (dropped != null) {
                EntityItem ei = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 + (double)this.facing.offsetX * 0.66, (double)this.field_145848_d + 0.33 + (double)this.facing.getOpposite().offsetY, (double)this.field_145849_e + 0.5 + (double)this.facing.offsetZ * 0.66, dropped.func_77946_l());
                ei.field_70159_w = 0.075f * (float)this.facing.offsetX;
                ei.field_70181_x = 0.025f;
                ei.field_70179_y = 0.075f * (float)this.facing.offsetZ;
                this.field_145850_b.func_72838_d((Entity)ei);
                this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 0, 0);
            }
            this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "random.fizz", 0.25f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f);
            this.currentCraft = -1;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }

    void fill() {
        TileEntity te = null;
        IEssentiaTransport ic = null;
        for (int y = 0; y <= 1; ++y) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                int ess;
                if (dir == this.facing || dir == ForgeDirection.DOWN || y == 0 && dir == ForgeDirection.UP || (te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d + y, this.field_145849_e, dir)) == null || (ic = (IEssentiaTransport)te).getEssentiaAmount(dir.getOpposite()) <= 0 || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(null) || this.getSuctionAmount(null) < ic.getMinimumSuction() || (ess = ic.takeEssentia(this.currentSuction, 1, dir.getOpposite())) <= 0) continue;
                this.addToContainer(this.currentSuction, ess);
                return;
            }
        }
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        int ce = this.currentRecipe.aspects.getAmount(tt) - this.essentia.getAmount(tt);
        if (this.currentRecipe == null || ce <= 0) {
            return am;
        }
        int add = Math.min(ce, am);
        this.essentia.add(tt, add);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return am - add;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.essentia.getAmount(tt) >= am) {
            this.essentia.remove(tt, am);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tt, int am) {
        return this.essentia.getAmount(tt) >= am;
    }

    @Override
    public int containerContains(Aspect tt) {
        return this.essentia.getAmount(tt);
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    public boolean func_145842_c(int i, int j) {
        if (i >= 0) {
            if (this.field_145850_b.field_72995_K) {
                this.venting = 7;
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face != this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face != this.facing;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
        this.currentSuction = aspect;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return this.currentSuction;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return this.currentSuction != null ? 128 : 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public AspectList getAspects() {
        return this.essentia;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.essentia = aspects;
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.inputStack;
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.inputStack != null) {
            if (this.inputStack.field_77994_a <= par2) {
                ItemStack itemstack = this.inputStack;
                this.inputStack = null;
                if (this.eventHandler != null) {
                    this.eventHandler.func_75130_a((IInventory)this);
                }
                return itemstack;
            }
            ItemStack itemstack = this.inputStack.func_77979_a(par2);
            if (this.inputStack.field_77994_a == 0) {
                this.inputStack = null;
            }
            if (this.eventHandler != null) {
                this.eventHandler.func_75130_a((IInventory)this);
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.inputStack != null) {
            ItemStack itemstack = this.inputStack;
            this.inputStack = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.inputStack = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        if (this.eventHandler != null) {
            this.eventHandler.func_75130_a((IInventory)this);
        }
    }

    public String func_145825_b() {
        return "container.alchemyfurnace";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public int[] func_94128_d(int par1) {
        return new int[]{0};
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) || this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) || this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    }

    public void getUpgrades() {
        int mr = 1;
        for (int yy = 0; yy <= 1; ++yy) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity te;
                if (dir == ForgeDirection.DOWN || dir == this.facing) continue;
                int xx = this.field_145851_c + dir.offsetX;
                int zz = this.field_145849_e + dir.offsetZ;
                Block bi = this.field_145850_b.func_147439_a(xx, this.field_145848_d + yy + dir.offsetY, zz);
                int md = this.field_145850_b.func_72805_g(xx, this.field_145848_d + yy + dir.offsetY, zz);
                if (bi != ConfigBlocks.blockMetalDevice || md != 12 || (te = this.field_145850_b.func_147438_o(xx, this.field_145848_d + yy + dir.offsetY, zz)) == null || !(te instanceof TileBrainbox) || ((TileBrainbox)te).facing != dir.getOpposite()) continue;
                mr += 2;
            }
        }
        if (mr != this.maxRecipes) {
            this.maxRecipes = mr;
            while (this.recipeHash.size() > this.maxRecipes) {
                this.recipeHash.remove(this.recipeHash.size() - 1);
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }
}

