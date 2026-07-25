/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IAspectSource
 *  thaumcraft.api.wands.IWandable
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.ThaumicExploration;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class TileEntityReplicator
extends TileEntity
implements ISidedInventory,
IWandable,
IAspectContainer {
    private static final int[] slots = new int[]{0};
    public boolean crafting;
    private ItemStack[] oldInventory = new ItemStack[1];
    private ItemStack[] inventory = new ItemStack[1];
    public int ticksLeft;
    public AspectList recipeEssentia = new AspectList();
    public AspectList displayEssentia = new AspectList();
    private ArrayList<ChunkCoordinates> sources = new ArrayList();
    private int soundTicks;
    private int essentiaTicks;
    public boolean redstoneState = false;

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        this.writeInventoryNBT(par1NBTTagCompound);
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.readInventoryNBT(par1NBTTagCompound);
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventory[i];
    }

    public void startCrafting() {
        System.out.println("start crafting - 2");
        if (this.func_70301_a(0) != null && this.func_70301_a((int)0).field_77994_a == 0 && !this.field_145850_b.field_72995_K) {
            System.out.println("start crafting - 3");
            System.out.println("start crafting - 4");
            this.oldInventory = this.inventory;
            this.crafting = true;
            this.ticksLeft = 100;
            ItemStack example = this.func_70301_a(0).func_77946_l();
            example.field_77994_a = 1;
            AspectList ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)example);
            this.recipeEssentia = ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)example, (AspectList)ot);
            this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.5f, 1.0f);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.essentiaTicks = 0;
        }
    }

    public void cancelCrafting() {
        this.crafting = false;
        this.recipeEssentia = new AspectList();
        this.displayEssentia = new AspectList();
    }

    public void func_145845_h() {
        if (this.crafting && this.field_145850_b.field_72995_K && this.ticksLeft < 100) {
            Aspect[] example = this.func_70301_a(0).func_77946_l();
            example.field_77994_a = 1;
            AspectList ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)example);
            ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)example, (AspectList)ot);
            for (int i = 0; i < 5; ++i) {
                ThaumicExploration.proxy.spawnFragmentParticle(this.field_145850_b, (double)((float)this.field_145851_c + 0.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145848_d + 1.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145849_e + 0.5f) + (2.0 * Math.random() - 1.0), (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.5f, (float)this.field_145849_e + 0.5f, Block.func_149634_a((Item)example.func_77973_b()), example.func_77960_j());
            }
            if (this.field_145850_b.field_73012_v.nextInt(4) == 0 && this.ticksLeft > 40) {
                ThaumicExploration.proxy.spawnEssentiaAtLocation(this.field_145850_b, (double)((float)this.field_145851_c + 0.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145848_d + 1.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145849_e + 0.5f) + (2.0 * Math.random() - 1.0), (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.5f, (float)this.field_145849_e + 0.5f, 5, ot.getAspects()[this.field_145850_b.field_73012_v.nextInt(ot.getAspects().length)].getColor());
            }
            if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                ThaumicExploration.proxy.spawnBoreSparkle(this.field_145850_b, (double)((float)this.field_145851_c + 0.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145848_d + 1.5f) + (2.0 * Math.random() - 1.0), (double)((float)this.field_145849_e + 0.5f) + (2.0 * Math.random() - 1.0), (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 1.5f, (float)this.field_145849_e + 0.5f);
            }
        }
        if (this.crafting && !this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.getSurroundings();
            ++this.essentiaTicks;
            if (this.recipeEssentia.visSize() > 0) {
                if (this.essentiaTicks > 49) {
                    this.essentiaTicks = 0;
                    for (Aspect aspect : this.recipeEssentia.getAspects()) {
                        if (this.recipeEssentia.getAmount(aspect) <= 0) continue;
                        for (ChunkCoordinates cc : this.sources) {
                            IAspectSource as;
                            TileEntity te = this.field_145850_b.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
                            if (te == null || !(te instanceof IAspectSource) || !(as = (IAspectSource)te).doesContainerContainAmount(aspect, 1)) continue;
                            as.takeFromContainer(aspect, 1);
                            this.recipeEssentia.reduce(aspect, 1);
                            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                            return;
                        }
                        Aspect[] aspectArray = this.recipeEssentia.getAspects();
                    }
                }
            } else if (this.ticksLeft > 0) {
                if (this.essentiaTicks > 49) {
                    if (260 - this.ticksLeft % 40 == 0) {
                        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:rumble", 0.5f, 1.0f);
                    }
                    --this.ticksLeft;
                }
            } else {
                ItemStack stack = this.func_70301_a(0).func_77946_l();
                ++stack.field_77994_a;
                this.func_70299_a(0, stack);
                this.crafting = false;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.recipeEssentia = new AspectList();
            }
        }
    }

    public boolean validLocation() {
        return this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o() == Config.airyMaterial || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o() == Material.field_151579_a || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).isReplaceable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventory[i] != null) {
            ItemStack template = this.inventory[i].func_77946_l();
            template.field_77994_a = 0;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (this.inventory[i].field_77994_a <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = template;
                return itemstack;
            }
            ItemStack itemstack = this.inventory[i].func_77979_a(j);
            if (this.inventory[i] == null) {
                this.inventory[i] = template;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        if (this.inventory[i] != null) {
            ItemStack itemstack = this.inventory[i];
            this.inventory[i] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
        if (itemstack != null && itemstack.field_77994_a > this.func_70297_j_()) {
            itemstack.field_77994_a = this.func_70297_j_();
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private void getSurroundings() {
        ArrayList stuff = new ArrayList();
        this.sources.clear();
        for (int xx = -12; xx <= 12; ++xx) {
            for (int zz = -12; zz <= 12; ++zz) {
                boolean skip = false;
                for (int yy = -5; yy <= 10; ++yy) {
                    int z;
                    int y;
                    int x;
                    TileEntity te;
                    if (xx == 0 && zz == 0 || (te = this.field_145850_b.func_147438_o(x = this.field_145851_c + xx, y = this.field_145848_d - yy, z = this.field_145849_e + zz)) == null || !(te instanceof IAspectSource)) continue;
                    this.sources.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
    }

    public String func_145825_b() {
        return "replicator";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 1;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public int[] func_94128_d(int var1) {
        return slots;
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        return false;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return this.inventory[i].field_77994_a > 0;
    }

    public void readInventoryNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = (NBTTagList)nbttagcompound.func_74781_a("Items");
        this.inventory = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.inventory.length) continue;
            this.inventory[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
        this.ticksLeft = nbttagcompound.func_74762_e("Ticks");
        this.crafting = nbttagcompound.func_74767_n("Crafting");
        AspectList readAspects = new AspectList();
        NBTTagCompound aspects = nbttagcompound.func_74775_l("Aspects");
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound aspect = aspects.func_74775_l((String)next);
            int amount = aspect.func_74762_e("Amount");
            if (amount <= 0) continue;
            readAspects.add(Aspect.getAspect((String)((String)next)), amount);
        }
        this.recipeEssentia = readAspects;
    }

    public void writeInventoryNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74757_a("Crafting", this.crafting);
        nbttagcompound.func_74768_a("Ticks", this.ticksLeft);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.inventory[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
        NBTTagCompound aspects = new NBTTagCompound();
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74768_a("Amount", this.recipeEssentia.getAmount(Aspect.getAspect((String)((String)next))));
            aspects.func_74782_a((String)next, (NBTBase)tag);
        }
        nbttagcompound.func_74782_a("Aspects", (NBTBase)aspects);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readInventoryNBT(pkt.func_148857_g());
    }

    public Packet func_145844_m() {
        super.func_145844_m();
        NBTTagCompound access = new NBTTagCompound();
        this.writeInventoryNBT(access);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, access);
    }

    public AspectList getAspects() {
        return this.recipeEssentia;
    }

    public void setAspects(AspectList aspects) {
    }

    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    public int containerContains(Aspect tag) {
        return 0;
    }

    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (!this.crafting) {
            this.startCrafting();
            return 0;
        }
        return -1;
    }

    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return wandstack;
    }

    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    public void updateRedstoneState(boolean flag) {
        if (flag != this.redstoneState && !this.crafting && flag) {
            this.startCrafting();
        }
        this.redstoneState = flag;
    }
}

