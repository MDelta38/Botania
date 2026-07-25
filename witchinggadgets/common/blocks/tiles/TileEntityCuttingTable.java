/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package witchinggadgets.common.blocks.tiles;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.util.handler.InfusedGemHandler;

public class TileEntityCuttingTable
extends TileEntityWGBase
implements IInventory {
    int tick = 0;
    int tickMax = 400;
    ItemStack[] inventory = new ItemStack[5];
    public int facing = 2;
    public byte targetGemCut = 0;
    static Set<Aspect> acceptedAspects = new HashSet<Aspect>();

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            this.inventory[4] = this.getOutput();
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        this.tick = tag.func_74762_e("tickCount");
        NBTTagList tagList = tag.func_150295_c("Inventory", 10);
        for (int i = 0; i < tagList.func_74745_c(); ++i) {
            NBTTagCompound itemTag = tagList.func_150305_b(i);
            byte slot = itemTag.func_74771_c("Slot");
            if (slot < 0 || slot >= this.inventory.length) continue;
            this.inventory[slot] = ItemStack.func_77949_a((NBTTagCompound)itemTag);
        }
        this.facing = tag.func_74762_e("facing");
        this.targetGemCut = tag.func_74771_c("targetGemCut");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.func_74768_a("tickCount", this.tick);
        if (this.inventory != null) {
            NBTTagList itemList = new NBTTagList();
            for (int i = 0; i < this.inventory.length; ++i) {
                ItemStack stack = this.inventory[i];
                if (stack == null) continue;
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.func_74774_a("Slot", (byte)i);
                stack.func_77955_b(itemTag);
                itemList.func_74742_a((NBTBase)itemTag);
            }
            tag.func_74782_a("Inventory", (NBTBase)itemList);
        }
        tag.func_74768_a("facing", this.facing);
        tag.func_74774_a("targetGemCut", this.targetGemCut);
    }

    public ItemStack getOutput() {
        if (this.inventory[0] == null || !InfusedGemHandler.isGem(this.inventory[0])) {
            return null;
        }
        ItemStack stack = new ItemStack(WGContent.ItemInfusedGem);
        Aspect aspect = this.getInfusingAspect();
        if (aspect != null) {
            int amplifier = (this.inventory[1] != null ? 1 : 0) + (this.inventory[2] != null ? 1 : 0) + (this.inventory[3] != null ? 1 : 0);
            int brittle = ItemInfusedGem.GemCut.getValue(this.targetGemCut) == ItemInfusedGem.GemCut.OVAL ? 1 : 0;
            stack = ItemInfusedGem.createGem(aspect, ItemInfusedGem.GemCut.getValue(this.targetGemCut), false);
            if (amplifier > 0) {
                if (Arrays.asList(InfusedGemHandler.getNaturalAffinities(this.inventory[0])).contains(aspect)) {
                    if (amplifier - brittle > 0) {
                        stack.func_77966_a(WGContent.enc_gemstonePotency, amplifier - brittle);
                    }
                } else {
                    if (Arrays.asList(InfusedGemHandler.getNaturalAversions(this.inventory[0])).contains(aspect)) {
                        ++amplifier;
                    }
                    stack.func_77966_a(WGContent.enc_gemstoneBrittle, amplifier);
                }
            }
            return stack;
        }
        return null;
    }

    public Aspect getInfusingAspect() {
        AspectList l = new AspectList();
        for (int i = 1; i <= 3; ++i) {
            if (this.inventory[i] == null) continue;
            AspectList as = new AspectList();
            as.readFromNBT(this.inventory[i].func_77978_p());
            for (Aspect a : as.getAspects()) {
                l.add(a, as.getAmount(a));
            }
        }
        return l.getAspectsSortedAmount()[0];
    }

    public boolean canAcceptAspect(Aspect a) {
        return a != null && (this.getInfusingAspect() == null || a.equals(this.getInfusingAspect())) && acceptedAspects.contains(a);
    }

    public int func_70302_i_() {
        return this.inventory.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventory[i];
    }

    public ItemStack func_70298_a(int slot, int amt) {
        ItemStack stack = this.func_70301_a(slot);
        if (stack != null) {
            if (stack.field_77994_a <= amt) {
                this.func_70299_a(slot, null);
            } else {
                stack = stack.func_77979_a(amt);
                if (stack.field_77994_a == 0) {
                    this.func_70299_a(slot, null);
                }
            }
        }
        return stack;
    }

    public ItemStack func_70304_b(int slot) {
        ItemStack stack = this.func_70301_a(slot);
        if (stack != null) {
            this.func_70299_a(slot, null);
        }
        return stack;
    }

    public void func_70299_a(int slot, ItemStack stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
    }

    public String func_145825_b() {
        return "CuttingTable";
    }

    public boolean func_145818_k_() {
        return true;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && player.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) < 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    static {
        acceptedAspects.add(Aspect.AIR);
        acceptedAspects.add(Aspect.EARTH);
        acceptedAspects.add(Aspect.FIRE);
        acceptedAspects.add(Aspect.WATER);
        acceptedAspects.add(Aspect.ORDER);
        acceptedAspects.add(Aspect.ENTROPY);
    }
}

