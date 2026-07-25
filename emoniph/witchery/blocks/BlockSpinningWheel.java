/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.inventory.SlotFurnace
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockAltar;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.crafting.SpinningRecipes;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Coord;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpinningWheel
extends BlockBaseContainer {
    public BlockSpinningWheel() {
        super(Material.field_151575_d, TileEntitySpinningWheel.class);
        this.func_149711_c(3.5f);
        this.func_149672_a(field_149766_f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess iblockaccess, int i, int j, int k, int l) {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149726_b(World world, int x, int y, int z) {
        super.func_149726_b(world, x, y, z);
        BlockUtil.setBlockDefaultDirection(world, x, y, z);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntitySpinningWheel) {
            TileEntitySpinningWheel spinningWheel = (TileEntitySpinningWheel)tile;
            player.openGui((Object)Witchery.instance, 4, world, x, y, z);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        int l = MathHelper.func_76128_c((double)((double)(entity.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        switch (l) {
            default: {
                world.func_72921_c(x, y, z, 2, 2);
                break;
            }
            case 1: {
                world.func_72921_c(x, y, z, 5, 2);
                break;
            }
            case 2: {
                world.func_72921_c(x, y, z, 3, 2);
                break;
            }
            case 3: {
                world.func_72921_c(x, y, z, 4, 2);
            }
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block oldBlockID, int oldBlockMetadata) {
        TileEntitySpinningWheel tileentityfurnace;
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntitySpinningWheel && (tileentityfurnace = (TileEntitySpinningWheel)tile) != null) {
            for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); ++j1) {
                ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f1 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                float f2 = world.field_73012_v.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = world.field_73012_v.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)world.field_73012_v.nextGaussian() * 0.05f;
                    entityitem.field_70181_x = (float)world.field_73012_v.nextGaussian() * 0.05f + 0.2f;
                    entityitem.field_70179_y = (float)world.field_73012_v.nextGaussian() * 0.05f;
                    world.func_72838_d((Entity)entityitem);
                }
            }
            world.func_147453_f(x, y, z, oldBlockID);
        }
        super.func_149749_a(world, x, y, z, oldBlockID, oldBlockMetadata);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int side) {
        return Container.func_94526_b((IInventory)((IInventory)world.func_147438_o(x, y, z)));
    }

    public static class ContainerSpinningWheel
    extends Container {
        private TileEntitySpinningWheel furnace;
        private int lastCookTime;
        private int lastPowerLevel;

        public ContainerSpinningWheel(InventoryPlayer par1InventoryPlayer, TileEntitySpinningWheel par2TileEntityFurnace) {
            int i;
            this.furnace = par2TileEntityFurnace;
            this.func_75146_a(new Slot((IInventory)par2TileEntityFurnace, 0, 56, 20));
            this.func_75146_a(new Slot((IInventory)par2TileEntityFurnace, 1, 56, 53));
            this.func_75146_a((Slot)new SlotFurnace(par1InventoryPlayer.field_70458_d, (IInventory)par2TileEntityFurnace, 2, 118, 21));
            this.func_75146_a(new Slot((IInventory)par2TileEntityFurnace, 3, 74, 53));
            this.func_75146_a(new Slot((IInventory)par2TileEntityFurnace, 4, 92, 53));
            for (i = 0; i < 3; ++i) {
                for (int j = 0; j < 9; ++j) {
                    this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
                }
            }
            for (i = 0; i < 9; ++i) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, i, 8 + i * 18, 142));
            }
        }

        public void func_75132_a(ICrafting par1ICrafting) {
            super.func_75132_a(par1ICrafting);
            par1ICrafting.func_71112_a((Container)this, 0, this.furnace.furnaceCookTime);
            par1ICrafting.func_71112_a((Container)this, 1, this.furnace.powerLevel);
        }

        public void func_75142_b() {
            super.func_75142_b();
            for (int i = 0; i < this.field_75149_d.size(); ++i) {
                ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
                if (this.lastCookTime != this.furnace.furnaceCookTime) {
                    icrafting.func_71112_a((Container)this, 0, this.furnace.furnaceCookTime);
                }
                if (this.lastPowerLevel == this.furnace.powerLevel) continue;
                icrafting.func_71112_a((Container)this, 1, this.furnace.powerLevel);
            }
            this.lastCookTime = this.furnace.furnaceCookTime;
            this.lastPowerLevel = this.furnace.powerLevel;
        }

        @SideOnly(value=Side.CLIENT)
        public void func_75137_b(int par1, int par2) {
            if (par1 == 0) {
                this.furnace.furnaceCookTime = par2;
            }
            if (par1 == 1) {
                this.furnace.powerLevel = par2;
            }
        }

        public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
            return this.furnace.func_70300_a(par1EntityPlayer);
        }

        public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
            ItemStack itemstack = null;
            Slot slot = (Slot)this.field_75151_b.get(slotIndex);
            if (slot != null && slot.func_75216_d()) {
                ItemStack itemstack1 = slot.func_75211_c();
                itemstack = itemstack1.func_77946_l();
                if (slotIndex == 2) {
                    if (!this.func_75135_a(itemstack1, 5, 41, true)) {
                        return null;
                    }
                    slot.func_75220_a(itemstack1, itemstack);
                } else if (slotIndex != 1 && slotIndex != 0 && slotIndex != 4 && slotIndex != 3 ? (SpinningRecipes.instance().findRecipeUsingFibre(itemstack1) != null && (this.furnace.func_70301_a(0) == null || this.furnace.func_70301_a(0).func_77969_a(itemstack1)) ? !this.func_75135_a(itemstack1, 0, 1, false) : (SpinningRecipes.instance().findRecipeUsing(itemstack1) != null ? !this.func_75135_a(itemstack1, 1, 2, false) && !this.func_75135_a(itemstack1, 3, 4, false) && !this.func_75135_a(itemstack1, 4, 5, false) : (slotIndex >= 5 && slotIndex < 32 ? !this.func_75135_a(itemstack1, 32, 41, false) : slotIndex >= 32 && slotIndex < 41 && !this.func_75135_a(itemstack1, 5, 32, false)))) : !this.func_75135_a(itemstack1, 5, 41, false)) {
                    return null;
                }
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                } else {
                    slot.func_75218_e();
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
                slot.func_82870_a(player, itemstack1);
            }
            return itemstack;
        }
    }

    public static class TileEntitySpinningWheel
    extends TileEntityBase
    implements ISidedInventory {
        private ItemStack[] slots = new ItemStack[5];
        public int furnaceCookTime = 0;
        private final int TICKS_PER_SPIN = 20;
        private final int SPINS_PER_STEP = 3;
        private final int STEPS_TO_COMPLETE = 5;
        Coord powerSourceCoord;
        static final int POWER_SOURCE_RADIUS = 16;
        static final float POWER_PER_TICK = 0.6f;
        public int powerLevel;
        private static final int SLOT_TO_SPIN = 0;
        private static final int SLOT_SPUN = 2;
        private static final int SLOT_FUEL = 1;
        private static final int SLOT_BY_PRODUCT = 3;
        private static final int SLOT_JARS = 4;
        private static final int[] slots_top = new int[]{0};
        private static final int[] slots_bottom = new int[]{4, 1, 3};
        private static final int[] slots_sides = new int[]{3, 2, 4, 1};

        public int func_70302_i_() {
            return this.slots.length;
        }

        public ItemStack func_70301_a(int slot) {
            return this.slots[slot];
        }

        public ItemStack func_70298_a(int slot, int quantity) {
            if (this.slots[slot] != null) {
                if (this.slots[slot].field_77994_a <= quantity) {
                    ItemStack itemstack = this.slots[slot];
                    this.slots[slot] = null;
                    return itemstack;
                }
                ItemStack itemstack = this.slots[slot].func_77979_a(quantity);
                if (this.slots[slot].field_77994_a == 0) {
                    this.slots[slot] = null;
                }
                return itemstack;
            }
            return null;
        }

        public ItemStack func_70304_b(int slot) {
            if (this.slots[slot] != null) {
                ItemStack itemstack = this.slots[slot];
                this.slots[slot] = null;
                return itemstack;
            }
            return null;
        }

        public void func_70299_a(int slot, ItemStack stack) {
            this.slots[slot] = stack;
            if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
                stack.field_77994_a = this.func_70297_j_();
            }
        }

        public String func_145825_b() {
            return this.func_145838_q().func_149732_F();
        }

        public boolean func_145818_k_() {
            return true;
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            NBTTagList nbtSlotList = nbtRoot.func_150295_c("Items", 10);
            this.slots = new ItemStack[this.func_70302_i_()];
            for (int i = 0; i < nbtSlotList.func_74745_c(); ++i) {
                NBTTagCompound nbtSlot = nbtSlotList.func_150305_b(i);
                byte b0 = nbtSlot.func_74771_c("Slot");
                if (b0 < 0 || b0 >= this.slots.length) continue;
                this.slots[b0] = ItemStack.func_77949_a((NBTTagCompound)nbtSlot);
            }
            this.furnaceCookTime = nbtRoot.func_74765_d("CookTime");
            this.powerLevel = nbtRoot.func_74765_d("PowerLevel");
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            nbtRoot.func_74777_a("CookTime", (short)this.furnaceCookTime);
            nbtRoot.func_74777_a("PowerLevel", (short)this.powerLevel);
            NBTTagList nbtSlotList = new NBTTagList();
            for (int i = 0; i < this.slots.length; ++i) {
                if (this.slots[i] == null) continue;
                NBTTagCompound nbtSlot = new NBTTagCompound();
                nbtSlot.func_74774_a("Slot", (byte)i);
                this.slots[i].func_77955_b(nbtSlot);
                nbtSlotList.func_74742_a((NBTBase)nbtSlot);
            }
            nbtRoot.func_74782_a("Items", (NBTBase)nbtSlotList);
        }

        public int func_70297_j_() {
            return 64;
        }

        @SideOnly(value=Side.CLIENT)
        public int getCookProgressScaled(int par1) {
            return this.furnaceCookTime * par1 / this.getTotalCookTime();
        }

        public int getTotalCookTime() {
            int time = 300;
            return 300;
        }

        public int getCookTime() {
            return this.furnaceCookTime;
        }

        IPowerSource getPowerSource() {
            if (this.powerSourceCoord == null || this.ticks % 100L == 0L) {
                return this.findNewPowerSource();
            }
            TileEntity tileEntity = this.powerSourceCoord.getBlockTileEntity(this.field_145850_b);
            if (!(tileEntity instanceof BlockAltar.TileEntityAltar)) {
                return this.findNewPowerSource();
            }
            BlockAltar.TileEntityAltar altarTileEntity = (BlockAltar.TileEntityAltar)tileEntity;
            if (!altarTileEntity.isValid()) {
                return this.findNewPowerSource();
            }
            return altarTileEntity;
        }

        private IPowerSource findNewPowerSource() {
            ArrayList<PowerSources.RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(this.field_145850_b, new Coord(this), 16) : null;
            return sources != null && sources.size() > 0 ? ((PowerSources.RelativePowerSource)sources.get(0)).source() : null;
        }

        @Override
        public void func_145845_h() {
            boolean cooking;
            super.func_145845_h();
            boolean update = false;
            boolean bl = cooking = this.furnaceCookTime > 0;
            if (!this.field_145850_b.field_72995_K) {
                boolean powered;
                boolean bl2 = powered = this.powerLevel > 0;
                if (this.canSmelt()) {
                    IPowerSource powerSource = this.getPowerSource();
                    this.powerSourceCoord = powerSource != null && !powerSource.isLocationEqual(this.powerSourceCoord) ? powerSource.getLocation() : null;
                    int n = this.powerLevel = powerSource == null ? 0 : 1;
                    if (powerSource != null && powerSource.consumePower(0.6f)) {
                        update = this.furnaceCookTime == 0;
                        ++this.furnaceCookTime;
                        if (this.furnaceCookTime == this.getTotalCookTime()) {
                            this.furnaceCookTime = 0;
                            this.smeltItem();
                            update = true;
                        }
                        if (powered != this.powerLevel > 0) {
                            update = true;
                        }
                    } else {
                        this.powerLevel = 0;
                        if (powered != this.powerLevel > 0) {
                            update = true;
                        }
                    }
                } else {
                    if (this.ticks % 40L == 0L) {
                        IPowerSource powerSource = this.getPowerSource();
                        if (powerSource != null && !powerSource.isLocationEqual(this.powerSourceCoord)) {
                            this.powerSourceCoord = powerSource.getLocation();
                        }
                        int n = this.powerLevel = powerSource == null ? 0 : 1;
                    }
                    update = this.furnaceCookTime > 0 || powered != this.powerLevel > 0;
                    this.furnaceCookTime = 0;
                }
            }
            if (update) {
                this.func_70296_d();
            }
        }

        public void func_70296_d() {
            super.func_70296_d();
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            this.func_145839_a(packet.func_148857_g());
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

        private boolean canSmelt() {
            if (this.slots[0] == null) {
                return false;
            }
            SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(this.slots[0], new ItemStack[]{this.slots[1], this.slots[3], this.slots[4]});
            if (recipe == null) {
                return false;
            }
            if (this.slots[2] == null) {
                return true;
            }
            ItemStack itemstack = recipe.getResult();
            if (!this.slots[2].func_77969_a(itemstack)) {
                return false;
            }
            int result = this.slots[2].field_77994_a + itemstack.field_77994_a;
            return result <= this.func_70297_j_() && result <= itemstack.func_77976_d();
        }

        public void smeltItem() {
            if (this.canSmelt()) {
                SpinningRecipes.SpinningRecipe recipe = SpinningRecipes.instance().getRecipe(this.slots[0], new ItemStack[]{this.slots[1], this.slots[3], this.slots[4]});
                ItemStack itemstack = recipe.getResult();
                if (this.slots[2] == null) {
                    this.slots[2] = itemstack.func_77946_l();
                } else if (this.slots[2].func_77969_a(itemstack)) {
                    this.slots[2].field_77994_a += itemstack.field_77994_a;
                }
                this.slots[0].field_77994_a -= recipe.fibre.field_77994_a;
                if (this.slots[0].field_77994_a <= 0) {
                    this.slots[0] = null;
                }
                ArrayList<ItemStack> available = recipe.getMutableModifiersList();
                this.updateIfContained(available, 1);
                this.updateIfContained(available, 3);
                this.updateIfContained(available, 4);
            }
        }

        private void updateIfContained(ArrayList<ItemStack> available, int slot) {
            if (this.slots[slot] != null) {
                for (int i = 0; i < available.size(); ++i) {
                    if (!available.get(i).func_77969_a(this.slots[slot])) continue;
                    --this.slots[slot].field_77994_a;
                    if (this.slots[slot].field_77994_a <= 0) {
                        this.slots[slot] = null;
                    }
                    available.remove(i);
                    return;
                }
            }
        }

        public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
            return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
        }

        public void func_70295_k_() {
        }

        public void func_70305_f() {
        }

        public boolean func_94041_b(int slot, ItemStack itemstack) {
            return slot != 2;
        }

        public int[] func_94128_d(int side) {
            return BlockSide.BOTTOM.isEqual(side) ? slots_bottom : (BlockSide.TOP.isEqual(side) ? slots_top : slots_sides);
        }

        public boolean func_102007_a(int slot, ItemStack itemstack, int par3) {
            return this.func_94041_b(slot, itemstack);
        }

        public boolean func_102008_b(int slot, ItemStack stack, int side) {
            if (BlockSide.TOP.isEqual(side)) {
                return false;
            }
            if (BlockSide.BOTTOM.isEqual(side)) {
                return false;
            }
            return slot == 2;
        }
    }
}

