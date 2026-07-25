/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.nodes.INode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileJarNode;

public class TileWandPedestal
extends TileThaumcraft
implements ISidedInventory,
IAspectContainer {
    private static final int[] slots = new int[]{0};
    private ItemStack[] inventory = new ItemStack[1];
    private String customName;
    int counter = 0;
    boolean somethingChanged = false;
    public boolean draining = false;
    public int drainX = 0;
    public int drainY = 0;
    public int drainZ = 0;
    public int drainColor = 0;
    ArrayList<ChunkCoordinates> nodes = null;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(2.0, 2.0, 2.0);
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.inventory[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.inventory[par1] != null) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (this.inventory[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.inventory[par1];
                this.inventory[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
            if (this.inventory[par1].field_77994_a == 0) {
                this.inventory[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.inventory[par1] != null) {
            ItemStack itemstack = this.inventory[par1];
            this.inventory[par1] = null;
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.inventory[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.wandpedestal";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        this.inventory = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.inventory.length) continue;
            this.inventory[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.inventory[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        if (nbtCompound.func_74764_b("CustomName")) {
            this.customName = nbtCompound.func_74779_i("CustomName");
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        if (this.func_145818_k_()) {
            nbtCompound.func_74778_a("CustomName", this.customName);
        }
    }

    public int func_70297_j_() {
        return 1;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.nodes == null) {
            this.findNodes();
        }
        ++this.counter;
        boolean recalc = false;
        if (this.counter % 20 == 0 && this.somethingChanged && this.nodes != null && this.nodes.size() > 0 && this.func_70301_a(0) != null) {
            this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
            this.somethingChanged = false;
        }
        if (this.counter % 5 == 0 && this.nodes != null && this.nodes.size() > 0 && this.func_70301_a(0) != null) {
            boolean hasThingy = false;
            if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ConfigBlocks.blockStoneDevice && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == 8) {
                hasThingy = true;
            }
            if (this.func_70301_a(0).func_77973_b() instanceof ItemWandCasting) {
                ItemWandCasting wand = (ItemWandCasting)this.func_70301_a(0).func_77973_b();
                int min = 1;
                if (wand.getCap(this.func_70301_a(0)).getTag().equals("iron") || wand.getRod(this.func_70301_a(0)).getTag().equals("wood")) {
                    min = 0;
                }
                AspectList as = wand.getAspectsWithRoom(this.func_70301_a(0));
                this.draining = false;
                if (as != null && as.size() > 0) {
                    block0: for (ChunkCoordinates co : this.nodes) {
                        TileEntity te = this.field_145850_b.func_147438_o(co.field_71574_a, co.field_71572_b, co.field_71573_c);
                        if (te == null || !(te instanceof INode) || te instanceof TileJarNode) continue;
                        INode node = (INode)te;
                        for (Aspect aspect : as.getAspects()) {
                            if (node.getAspects().getAmount(aspect) <= min) continue;
                            wand.addVis(this.func_70301_a(0), aspect, 1, true);
                            node.takeFromContainer(aspect, 1);
                            this.somethingChanged = true;
                            this.draining = true;
                            if (!this.field_145850_b.field_72995_K) break block0;
                            this.drainX = co.field_71574_a;
                            this.drainY = co.field_71572_b;
                            this.drainZ = co.field_71573_c;
                            this.drainColor = aspect.getColor();
                            break block0;
                        }
                        if (!hasThingy) continue;
                        for (Aspect aspect : node.getAspects().getAspects()) {
                            if (aspect == null || aspect.isPrimal()) continue;
                            AspectList primals = ResearchManager.reduceToPrimals(new AspectList().add(aspect, 1));
                            for (Aspect aspect2 : as.getAspects()) {
                                if (primals.getAmount(aspect2) <= 0 || node.getAspects().getAmount(aspect) <= min) continue;
                                wand.addVis(this.func_70301_a(0), aspect2, 1, true);
                                node.takeFromContainer(aspect, 1);
                                this.somethingChanged = true;
                                this.draining = true;
                                if (!this.field_145850_b.field_72995_K) break block0;
                                this.drainX = co.field_71574_a;
                                this.drainY = co.field_71572_b;
                                this.drainZ = co.field_71573_c;
                                this.drainColor = aspect.getColor();
                                break block0;
                            }
                        }
                    }
                    if (!this.draining) {
                        recalc = true;
                    }
                }
            } else if (this.func_70301_a(0).func_77973_b() instanceof ItemAmuletVis) {
                ItemAmuletVis amulet = (ItemAmuletVis)this.func_70301_a(0).func_77973_b();
                int min = 1;
                AspectList as = amulet.getAspectsWithRoom(this.func_70301_a(0));
                this.draining = false;
                if (as != null && as.size() > 0) {
                    block4: for (ChunkCoordinates co : this.nodes) {
                        TileEntity te = this.field_145850_b.func_147438_o(co.field_71574_a, co.field_71572_b, co.field_71573_c);
                        if (te == null || !(te instanceof INode) || te instanceof TileJarNode) continue;
                        INode node = (INode)te;
                        for (Aspect aspect : as.getAspects()) {
                            if (node.getAspects().getAmount(aspect) <= min) continue;
                            amulet.addVis(this.func_70301_a(0), aspect, 1, true);
                            node.takeFromContainer(aspect, 1);
                            this.draining = true;
                            if (!this.field_145850_b.field_72995_K) break block4;
                            this.drainX = co.field_71574_a;
                            this.drainY = co.field_71572_b;
                            this.drainZ = co.field_71573_c;
                            this.drainColor = aspect.getColor();
                            break block4;
                        }
                        if (!hasThingy) continue;
                        for (Aspect aspect : node.getAspects().getAspects()) {
                            if (aspect == null || aspect.isPrimal()) continue;
                            AspectList primals = ResearchManager.reduceToPrimals(new AspectList().add(aspect, 1));
                            for (Aspect aspect2 : as.getAspects()) {
                                if (primals.getAmount(aspect2) <= 0 || node.getAspects().getAmount(aspect) <= min) continue;
                                amulet.addVis(this.func_70301_a(0), aspect2, 1, true);
                                node.takeFromContainer(aspect, 1);
                                this.draining = true;
                                if (!this.field_145850_b.field_72995_K) break block4;
                                this.drainX = co.field_71574_a;
                                this.drainY = co.field_71572_b;
                                this.drainZ = co.field_71573_c;
                                this.drainColor = aspect.getColor();
                                break block4;
                            }
                        }
                    }
                    if (!this.draining) {
                        recalc = true;
                    }
                }
            }
        }
        if (this.counter % 100 == 0 && (recalc || this.nodes.size() == 0)) {
            this.findNodes();
        }
    }

    private void findNodes() {
        this.nodes = new ArrayList();
        for (int xx = -8; xx <= 8; ++xx) {
            for (int yy = -8; yy <= 8; ++yy) {
                for (int zz = -8; zz <= 8; ++zz) {
                    TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz);
                    if (!(te instanceof INode)) continue;
                    this.nodes.add(new ChunkCoordinates(this.field_145851_c + xx, this.field_145848_d + yy, this.field_145849_e + zz));
                }
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

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return par2ItemStack != null && (par2ItemStack.func_77973_b() instanceof ItemWandCasting || par2ItemStack.func_77973_b() instanceof ItemAmuletVis);
    }

    public int[] func_94128_d(int par1) {
        return slots;
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.func_70301_a(par1) == null && (par2ItemStack.func_77973_b() instanceof ItemWandCasting || par2ItemStack.func_77973_b() instanceof ItemAmuletVis);
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    @Override
    public AspectList getAspects() {
        if (this.func_70301_a(0) != null && this.func_70301_a(0).func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)this.func_70301_a(0).func_77973_b();
            AspectList al = wand.getAllVis(this.func_70301_a(0));
            AspectList out = new AspectList();
            for (Aspect a : al.getAspectsSorted()) {
                out.add(a, al.getAmount(a) / 100);
            }
            return out;
        }
        if (this.func_70301_a(0) != null && this.func_70301_a(0).func_77973_b() instanceof ItemAmuletVis) {
            ItemAmuletVis amulet = (ItemAmuletVis)this.func_70301_a(0).func_77973_b();
            AspectList al = amulet.getAllVis(this.func_70301_a(0));
            AspectList out = new AspectList();
            for (Aspect a : al.getAspectsSorted()) {
                out.add(a, al.getAmount(a) / 100);
            }
            return out;
        }
        return null;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }
}

