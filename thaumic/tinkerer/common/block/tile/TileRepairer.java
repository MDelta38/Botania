/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.Loader;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.compat.TinkersConstructCompat;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public class TileRepairer
extends TileEntity
implements ISidedInventory,
IAspectContainer,
IEssentiaTransport,
IMovableTile {
    private static final Map<Aspect, Integer> repairValues = new HashMap<Aspect, Integer>();
    public int ticksExisted = 0;
    public boolean tookLastTick = true;
    int dmgLastTick = 0;
    ItemStack[] inventorySlots = new ItemStack[1];

    public void func_145845_h() {
        if (++this.ticksExisted % 10 == 0) {
            if (Loader.isModLoaded((String)"TConstruct") && ConfigHandler.repairTConTools && this.inventorySlots[0] != null && TinkersConstructCompat.isTConstructTool(this.inventorySlots[0])) {
                int dmg = TinkersConstructCompat.getDamage(this.inventorySlots[0]);
                if (dmg > 0) {
                    int essentia = this.drawEssentia();
                    TinkersConstructCompat.fixDamage(this.inventorySlots[0], essentia);
                    this.func_70296_d();
                    if (this.dmgLastTick != 0 && this.dmgLastTick != dmg) {
                        ThaumicTinkerer.tcProxy.sparkle((float)((double)this.field_145851_c + 0.25 + Math.random() / 2.0), (float)((double)(this.field_145848_d + 1) + Math.random() / 2.0), (float)((double)this.field_145849_e + 0.25 + Math.random() / 2.0), 0);
                        this.tookLastTick = true;
                    } else {
                        this.tookLastTick = false;
                    }
                } else {
                    this.tookLastTick = false;
                }
                this.dmgLastTick = this.inventorySlots[0] == null ? 0 : TinkersConstructCompat.getDamage(this.inventorySlots[0]);
                return;
            }
            if (this.inventorySlots[0] != null && this.inventorySlots[0].func_77960_j() > 0) {
                int essentia = this.drawEssentia();
                int dmg = this.inventorySlots[0].func_77960_j();
                this.inventorySlots[0].func_77964_b(Math.max(0, dmg - essentia));
                this.func_70296_d();
                if (this.dmgLastTick != 0 && this.dmgLastTick != dmg) {
                    ThaumicTinkerer.tcProxy.sparkle((float)((double)this.field_145851_c + 0.25 + Math.random() / 2.0), (float)((double)(this.field_145848_d + 1) + Math.random() / 2.0), (float)((double)this.field_145849_e + 0.25 + Math.random() / 2.0), 0);
                    this.tookLastTick = true;
                } else {
                    this.tookLastTick = false;
                }
            } else {
                this.tookLastTick = false;
            }
            this.dmgLastTick = this.inventorySlots[0] == null ? 0 : this.inventorySlots[0].func_77960_j();
        }
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.readCustomNBT(par1NBTTagCompound);
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        this.writeCustomNBT(par1NBTTagCompound);
    }

    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[1];
        if (nbttaglist.func_74745_c() > 0) {
            NBTTagCompound tagList = nbttaglist.func_150305_b(0);
            this.inventorySlots[0] = ItemStack.func_77949_a((NBTTagCompound)tagList);
        }
    }

    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList nbttaglist = new NBTTagList();
        if (this.inventorySlots[0] != null) {
            NBTTagCompound tagList = new NBTTagCompound();
            tagList.func_74774_a("Slot", (byte)0);
            this.inventorySlots[0].func_77955_b(tagList);
            nbttaglist.func_74742_a((NBTBase)tagList);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventorySlots[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventorySlots[i] != null) {
            if (this.inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = this.inventorySlots[i];
                this.inventorySlots[i] = null;
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[i].func_77979_a(j);
            if (this.inventorySlots[i].field_77994_a == 0) {
                this.inventorySlots[i] = null;
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
    }

    public String func_145825_b() {
        return "repairer";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 1;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int[] func_94128_d(int var1) {
        return new int[]{0};
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        if (Loader.isModLoaded((String)"TConstruct") && ConfigHandler.repairTConTools && TinkersConstructCompat.isTConstructTool(itemstack)) {
            return itemstack != null;
        }
        return itemstack != null && itemstack.func_77973_b().isRepairable();
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return true;
    }

    int drawEssentia() {
        ForgeDirection orientation = this.getOrientation();
        TileEntity te = ThaumcraftApiHelper.getConnectableTile((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e, (ForgeDirection)orientation);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(orientation.getOpposite())) {
                return 0;
            }
            for (Aspect aspect : repairValues.keySet()) {
                if (ic.getSuctionType(orientation.getOpposite()) != aspect || ic.getSuctionAmount(orientation.getOpposite()) >= this.getSuctionAmount(orientation) || ic.takeEssentia(aspect, 1, orientation.getOpposite()) != 1) continue;
                return repairValues.get(aspect);
            }
        }
        return 0;
    }

    ForgeDirection getOrientation() {
        return ForgeDirection.getOrientation((int)this.func_145832_p());
    }

    public AspectList getAspects() {
        ItemStack stack = this.inventorySlots[0];
        if (stack == null) {
            return null;
        }
        if (Loader.isModLoaded((String)"TConstruct") && ConfigHandler.repairTConTools && TinkersConstructCompat.isTConstructTool(stack)) {
            return new AspectList().add(Aspect.ENTROPY, TinkersConstructCompat.getDamage(stack));
        }
        return new AspectList().add(Aspect.ENTROPY, stack.func_77960_j());
    }

    public void setAspects(AspectList paramAspectList) {
    }

    public boolean doesContainerAccept(Aspect paramAspect) {
        return false;
    }

    public int addToContainer(Aspect paramAspect, int paramInt) {
        return 0;
    }

    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean takeFromContainer(AspectList paramAspectList) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean doesContainerContain(AspectList paramAspectList) {
        return false;
    }

    public int containerContains(Aspect paramAspect) {
        return 0;
    }

    public boolean isConnectable(ForgeDirection paramForgeDirection) {
        return paramForgeDirection == this.getOrientation();
    }

    public boolean canInputFrom(ForgeDirection paramForgeDirection) {
        return false;
    }

    public boolean canOutputTo(ForgeDirection paramForgeDirection) {
        return this.isConnectable(paramForgeDirection);
    }

    public void setSuction(Aspect paramAspect, int paramInt) {
    }

    public int takeEssentia(Aspect paramAspect, int paramInt, ForgeDirection direction) {
        return 0;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    public int addEssentia(Aspect arg0, int arg1, ForgeDirection direction) {
        return 0;
    }

    public int getEssentiaAmount(ForgeDirection arg0) {
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection arg0) {
        return null;
    }

    public int getSuctionAmount(ForgeDirection arg0) {
        return arg0 == this.getOrientation() ? 128 : 0;
    }

    public Aspect getSuctionType(ForgeDirection arg0) {
        return arg0 == this.getOrientation() ? Aspect.TOOL : null;
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }

    static {
        repairValues.put(Aspect.TOOL, 8);
        repairValues.put(Aspect.CRAFT, 5);
        repairValues.put(Aspect.ORDER, 3);
    }
}

