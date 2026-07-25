/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.EnumSkyBlock
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class TileAlchemyFurnaceAdvanced
extends TileThaumcraft {
    public AspectList aspects = new AspectList();
    public int vis;
    public int maxVis = 500;
    int bellows = -1;
    public int heat = 0;
    public int power1 = 0;
    public int power2 = 0;
    public int maxPower = 500;
    public boolean destroy = false;
    int count = 0;
    int processed = 0;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.vis = nbttagcompound.func_74765_d("vis");
        this.heat = nbttagcompound.func_74765_d("heat");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74777_a("vis", (short)this.vis);
        nbttagcompound.func_74777_a("heat", (short)this.heat);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        this.aspects.readFromNBT(nbtCompound);
        this.power1 = nbtCompound.func_74765_d("power1");
        this.power2 = nbtCompound.func_74765_d("power2");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        this.aspects.writeToNBT(nbtCompound);
        nbtCompound.func_74777_a("power1", (short)this.power1);
        nbtCompound.func_74777_a("power2", (short)this.power2);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null) {
            this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        ++this.count;
        if (!this.field_145850_b.field_72995_K) {
            if (this.destroy) {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = 0; b <= 1; ++b) {
                        for (int c = -1; c <= 1; ++c) {
                            if (a == 0 && b == 0 && c == 0 || this.field_145850_b.func_147439_a(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c) != this.func_145838_q()) continue;
                            int m = this.field_145850_b.func_72805_g(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c);
                            this.field_145850_b.func_147465_d(this.field_145851_c + a, this.field_145848_d + b, this.field_145849_e + c, Block.func_149634_a((Item)this.func_145838_q().func_149650_a(m, this.field_145850_b.field_73012_v, 0)), this.func_145838_q().func_149692_a(m), 3);
                        }
                    }
                }
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149634_a((Item)this.func_145838_q().func_149650_a(0, this.field_145850_b.field_73012_v, 0)), this.func_145838_q().func_149692_a(0), 3);
                return;
            }
            if (this.processed > 0) {
                --this.processed;
            }
            if (this.count % 5 == 0) {
                int pt = this.heat--;
                if (this.heat <= this.maxPower) {
                    this.heat += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.FIRE, 50);
                }
                if (this.power1 <= this.maxPower) {
                    this.power1 += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.ENTROPY, 50);
                }
                if (this.power2 <= this.maxPower) {
                    this.power2 += VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.WATER, 50);
                }
                if (pt / 50 != this.heat / 50) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
            }
        }
    }

    public boolean process(ItemStack stack) {
        if (this.processed == 0 && this.canSmelt(stack)) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
            int aa = (al = ThaumcraftCraftingManager.getBonusTags(stack, al)).visSize();
            if (aa * 2 > this.heat || aa > this.power1 || aa > this.power2) {
                return false;
            }
            this.heat -= aa * 2;
            this.power1 -= aa;
            this.power2 -= aa;
            this.processed = (int)((float)this.processed + (5.0f + Math.max(0.0f, (1.0f - (float)this.heat / (float)this.maxPower) * 100.0f)));
            this.aspects.add(al);
            this.vis = this.aspects.visSize();
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    private boolean canSmelt(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        AspectList al = ThaumcraftCraftingManager.getObjectTags(stack);
        if ((al = ThaumcraftCraftingManager.getBonusTags(stack, al)) == null || al.size() == 0) {
            return false;
        }
        int vs = al.visSize();
        return vs + this.aspects.visSize() <= this.maxVis;
    }
}

