/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileThaumcraftInventory;

public class TileFocalManipulator
extends TileThaumcraftInventory {
    public AspectList aspects = new AspectList();
    public int size = 0;
    public int upgrade = -1;
    public int rank = -1;
    int ticks = 0;
    public boolean reset = false;
    public static final int XP_MULT = 8;
    public static final int VIS_MULT = 200;

    public TileFocalManipulator() {
        this.itemStacks = new ItemStack[1];
        this.syncedSlots = new int[]{0};
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.itemStacks = new ItemStack[1];
        this.syncedSlots = new int[]{0};
        super.readCustomNBT(nbttagcompound);
        this.aspects.readFromNBT(nbttagcompound);
        this.size = nbttagcompound.func_74762_e("size");
        this.upgrade = nbttagcompound.func_74762_e("upgrade");
        this.rank = nbttagcompound.func_74762_e("rank");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        this.aspects.writeToNBT(nbttagcompound);
        nbttagcompound.func_74768_a("size", this.size);
        nbttagcompound.func_74768_a("upgrade", this.upgrade);
        nbttagcompound.func_74768_a("rank", this.rank);
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d - 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        super.func_70299_a(par1, par2ItemStack);
        if (this.field_145850_b.field_72995_K) {
            this.reset = true;
        } else {
            this.aspects = new AspectList();
        }
    }

    public void func_145845_h() {
        boolean complete = false;
        if (!this.field_145850_b.field_72995_K) {
            if (this.rank < 0) {
                this.rank = 0;
            }
            ++this.ticks;
            if (this.ticks % 5 == 0) {
                if (this.size > 0 && (this.aspects.visSize() <= 0 || this.func_70301_a(0) == null)) {
                    complete = true;
                    this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftfail", 0.33f, 1.0f);
                }
                if (this.size > 0) {
                    for (Aspect aspect : this.aspects.getAspectsSortedAmount()) {
                        int drain = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, aspect, Math.min(100, this.aspects.getAmount(aspect)));
                        if (drain <= 0) continue;
                        this.aspects.reduce(aspect, drain);
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        this.func_70296_d();
                    }
                    if (this.aspects.visSize() <= 0 && this.func_70301_a(0) != null) {
                        complete = true;
                        ItemFocusBasic focus = (ItemFocusBasic)this.func_70301_a(0).func_77973_b();
                        boolean b = focus.applyUpgrade(this.func_70301_a(0), FocusUpgradeType.types[this.upgrade], this.rank);
                        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:wand", 1.0f, 1.0f);
                    }
                }
            }
        } else if (this.size > 0) {
            Thaumcraft.proxy.drawGenericParticles(this.func_145831_w(), (double)this.field_145851_c + 0.5 + (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3f), (double)this.field_145848_d + 1.25 + (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3f), (double)this.field_145849_e + 0.5 + (double)((this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.3f), 0.0, 0.0, 0.0, 0.5f + this.func_145831_w().field_73012_v.nextFloat() * 0.4f, 1.0f - this.func_145831_w().field_73012_v.nextFloat() * 0.4f, 1.0f - this.func_145831_w().field_73012_v.nextFloat() * 0.4f, 0.8f, false, 112, 9, 1, 6 + this.field_145850_b.field_73012_v.nextInt(5), 0, 0.7f + this.func_145831_w().field_73012_v.nextFloat() * 0.4f);
        }
        if (complete) {
            this.size = 0;
            this.rank = -1;
            this.aspects = new AspectList();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }

    public boolean startCraft(int id, EntityPlayer p) {
        if (this.size > 0 || this.func_70301_a(0) == null || !(this.func_70301_a(0).func_77973_b() instanceof ItemFocusBasic)) {
            return false;
        }
        ItemFocusBasic focus = (ItemFocusBasic)this.func_70301_a(0).func_77973_b();
        short[] s = focus.getAppliedUpgrades(this.func_70301_a(0));
        this.rank = 1;
        while (this.rank <= 5 && s[this.rank - 1] != -1) {
            ++this.rank;
        }
        int xp = this.rank * 8;
        if (p.field_71068_ca < xp) {
            return false;
        }
        FocusUpgradeType[] ut = focus.getPossibleUpgradesByRank(this.func_70301_a(0), this.rank);
        if (ut == null) {
            return false;
        }
        boolean b = false;
        for (int a = 0; a < ut.length; ++a) {
            if (ut[a].id != id) continue;
            b = true;
            break;
        }
        if (!b) {
            return false;
        }
        if (id > FocusUpgradeType.types.length - 1 || FocusUpgradeType.types[id] == null || !focus.canApplyUpgrade(this.func_70301_a(0), p, FocusUpgradeType.types[id], this.rank)) {
            return false;
        }
        int amt = 200;
        for (int a = 1; a < this.rank; ++a) {
            amt *= 2;
        }
        AspectList tal = new AspectList();
        for (Aspect as : FocusUpgradeType.types[id].aspects.getAspects()) {
            tal.add(as, amt);
        }
        this.aspects = ResearchManager.reduceToPrimals(tal);
        this.size = this.aspects.visSize();
        this.upgrade = id;
        if (!p.field_71075_bZ.field_75098_d) {
            p.func_82242_a(-xp);
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:craftstart", 0.25f, 1.0f);
        return true;
    }

    @Override
    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return par2ItemStack != null && par2ItemStack.func_77973_b() instanceof ItemFocusBasic;
    }
}

