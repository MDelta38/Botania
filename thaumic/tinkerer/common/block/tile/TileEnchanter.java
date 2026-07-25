/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  org.apache.commons.lang3.ArrayUtils
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.ArrayUtils;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.core.helper.Tuple4Int;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import thaumic.tinkerer.common.lib.LibFeatures;

public class TileEnchanter
extends TileEntity
implements ISidedInventory,
IMovableTile {
    private static final String TAG_ENCHANTS = "enchantsIntArray";
    private static final String TAG_LEVELS = "levelsIntArray";
    private static final String TAG_TOTAL_ASPECTS = "totalAspects";
    private static final String TAG_CURRENT_ASPECTS = "currentAspects";
    private static final String TAG_WORKING = "working";
    public List<Integer> enchantments = new ArrayList<Integer>();
    public List<Integer> levels = new ArrayList<Integer>();
    public AspectList totalAspects = new AspectList();
    public AspectList currentAspects = new AspectList();
    public boolean working = false;
    ItemStack[] inventorySlots = new ItemStack[2];
    private List<Tuple4Int> pillars = new ArrayList<Tuple4Int>();

    public void clearEnchants() {
        this.enchantments.clear();
        this.levels.clear();
    }

    public void appendEnchant(int enchant) {
        this.enchantments.add(enchant);
    }

    public void appendLevel(int level) {
        this.levels.add(level);
    }

    public void removeEnchant(int index) {
        this.enchantments.remove(index);
    }

    public void removeLevel(int index) {
        this.levels.remove(index);
    }

    public void setEnchant(int index, int enchant) {
        this.enchantments.set(index, enchant);
    }

    public void setLevel(int index, int level) {
        this.levels.set(index, level);
    }

    public void func_145845_h() {
        if (this.func_70301_a(0) == null) {
            this.enchantments.clear();
            this.levels.clear();
        }
        if (this.working) {
            block12: {
                ItemStack tool = this.func_70301_a(0);
                if (tool == null) {
                    this.working = false;
                    return;
                }
                this.checkPillars();
                if (!this.working) {
                    return;
                }
                for (Aspect aspect : LibFeatures.PRIMAL_ASPECTS) {
                    int totalAmount;
                    int currentAmount = this.currentAspects.getAmount(aspect);
                    if (currentAmount >= (totalAmount = this.totalAspects.getAmount(aspect))) {
                        continue;
                    }
                    break block12;
                }
                this.working = false;
                this.currentAspects = new AspectList();
                this.totalAspects = new AspectList();
                for (int i = 0; i < this.enchantments.size(); ++i) {
                    int enchant = this.enchantments.get(i);
                    int level = this.levels.get(i);
                    if (this.field_145850_b.field_72995_K) continue;
                    tool.func_77966_a(Enchantment.field_77331_b[enchant], level);
                }
                this.enchantments.clear();
                this.levels.clear();
                this.field_145850_b.func_72908_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, "thaumcraft:wand", 1.0f, 1.0f);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return;
            }
            ItemStack wand = this.func_70301_a(1);
            if (wand != null && wand.func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)wand.func_77973_b()).isStaff(wand)) {
                Aspect aspect;
                ItemWandCasting wandItem = (ItemWandCasting)wand.func_77973_b();
                AspectList wandAspects = wandItem.getAllVis(wand);
                ArrayList<Aspect> aspectsThatCanGet = new ArrayList<Aspect>();
                for (Aspect aspect2 : LibFeatures.PRIMAL_ASPECTS) {
                    int missing = this.totalAspects.getAmount(aspect2) - this.currentAspects.getAmount(aspect2);
                    int onWand = wandAspects.getAmount(aspect2);
                    if (missing <= 0 || onWand < 100) continue;
                    aspectsThatCanGet.add(aspect2);
                }
                int i = aspectsThatCanGet.isEmpty() ? 0 : this.field_145850_b.field_73012_v.nextInt(aspectsThatCanGet.size());
                Aspect aspect3 = aspect = aspectsThatCanGet.isEmpty() ? null : (Aspect)aspectsThatCanGet.get(i);
                if (aspect != null) {
                    this.consumeAllVisCrafting(wand, null, new AspectList().add(aspect, 1), true, wandItem);
                    this.currentAspects.add(aspect, 1);
                    Tuple4Int p = this.pillars.get(i);
                    if (this.field_145850_b.field_73012_v.nextBoolean()) {
                        Thaumcraft.proxy.blockRunes(this.field_145850_b, (double)p.i1, (double)p.i4 - 0.75, (double)p.i3, 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.7f, 15, this.field_145850_b.field_73012_v.nextFloat() / 8.0f);
                        Thaumcraft.proxy.blockRunes(this.field_145850_b, (double)this.field_145851_c, (double)this.field_145848_d + 0.25, (double)this.field_145849_e, 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.7f, 0.0f, 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.7f, 15, this.field_145850_b.field_73012_v.nextFloat() / 8.0f);
                        if (this.field_145850_b.field_73012_v.nextInt(5) == 0) {
                            this.field_145850_b.func_72908_a((double)p.i1, (double)p.i2, (double)p.i3, "thaumcraft:brain", 0.5f, 1.0f);
                        }
                    }
                }
            }
        }
    }

    public boolean consumeAllVisCrafting(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit, ItemWandCasting wandItem) {
        if (aspects != null && aspects.size() != 0) {
            AspectList aspectList = new AspectList();
            for (Aspect aspect : aspects.getAspects()) {
                int cost = aspects.getAmount(aspect) * 100;
                aspectList.add(aspect, cost);
            }
            aspects = aspectList;
            if (aspects != null && aspects.size() != 0) {
                AspectList nl = new AspectList();
                for (Aspect aspect : aspects.getAspects()) {
                    int cost = aspects.getAmount(aspect);
                    cost = (int)((float)cost * wandItem.getConsumptionModifier(is, player, aspect, true));
                    nl.add(aspect, cost);
                }
                for (Aspect aspect : nl.getAspects()) {
                    if (wandItem.getVis(is, aspect) >= nl.getAmount(aspect)) continue;
                    return false;
                }
                if (doit && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                    for (Aspect aspect : nl.getAspects()) {
                        wandItem.storeVis(is, aspect, wandItem.getVis(is, aspect) - nl.getAmount(aspect));
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void func_70296_d() {
        super.func_70296_d();
        if (!this.field_145850_b.field_72995_K && !this.working) {
            this.enchantments.clear();
            this.levels.clear();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean checkPillars() {
        if (this.pillars.isEmpty()) {
            if (this.assignPillars()) {
                this.working = false;
                this.currentAspects = new AspectList();
                return false;
            }
            return true;
        }
        for (int i = 0; i < this.pillars.size(); ++i) {
            Tuple4Int pillar = this.pillars.get(i);
            int pillarHeight = this.findPillar(pillar.i1, pillar.i2, pillar.i3);
            if (pillarHeight == -1) {
                this.pillars.clear();
                return this.checkPillars();
            }
            if (pillarHeight == pillar.i4) continue;
            pillar.i4 = pillarHeight;
        }
        return true;
    }

    public boolean assignPillars() {
        int y = this.field_145848_d;
        for (int x = this.field_145851_c - 4; x <= this.field_145851_c + 4; ++x) {
            for (int z = this.field_145849_e - 4; z <= this.field_145849_e + 4; ++z) {
                int height = this.findPillar(x, y, z);
                if (height != -1) {
                    this.pillars.add(new Tuple4Int(x, y, z, height));
                }
                if (this.pillars.size() != 6) continue;
                return false;
            }
        }
        this.pillars.clear();
        return true;
    }

    public int findPillar(int x, int y, int z) {
        int obsidianFound = 0;
        int i = 0;
        while (y + i < 256) {
            Block id = this.field_145850_b.func_147439_a(x, y + i, z);
            int meta = this.field_145850_b.func_72805_g(x, y + i, z);
            if (id == ConfigBlocks.blockCosmeticSolid && meta == 0) {
                ++obsidianFound;
            } else {
                if (id == ConfigBlocks.blockAiry && meta == 1) {
                    if (obsidianFound >= 2 && obsidianFound < 13) {
                        return y + i;
                    }
                    return -1;
                }
                return -1;
            }
            ++i;
        }
        return -1;
    }

    public void updateAspectList() {
        this.totalAspects = new AspectList();
        for (int i = 0; i < this.enchantments.size(); ++i) {
            int enchant = this.enchantments.get(i);
            int level = this.levels.get(i);
            AspectList aspects = EnchantmentManager.enchantmentData.get((Object)Integer.valueOf((int)enchant)).get((Object)Integer.valueOf((int)level)).aspects;
            for (Aspect aspect : aspects.getAspectsSorted()) {
                this.totalAspects.add(aspect, aspects.getAmount(aspect));
            }
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
        this.working = par1NBTTagCompound.func_74767_n(TAG_WORKING);
        this.currentAspects.readFromNBT(par1NBTTagCompound.func_74775_l(TAG_CURRENT_ASPECTS));
        this.totalAspects.readFromNBT(par1NBTTagCompound.func_74775_l(TAG_TOTAL_ASPECTS));
        this.enchantments.clear();
        for (int i : par1NBTTagCompound.func_74759_k(TAG_ENCHANTS)) {
            this.enchantments.add(i);
        }
        this.levels.clear();
        for (int i : par1NBTTagCompound.func_74759_k(TAG_LEVELS)) {
            this.levels.add(i);
        }
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74783_a(TAG_LEVELS, ArrayUtils.toPrimitive((Integer[])this.levels.toArray(new Integer[this.levels.size()])));
        par1NBTTagCompound.func_74783_a(TAG_ENCHANTS, ArrayUtils.toPrimitive((Integer[])this.enchantments.toArray(new Integer[this.enchantments.size()])));
        NBTTagCompound totalAspectsCmp = new NBTTagCompound();
        this.totalAspects.writeToNBT(totalAspectsCmp);
        NBTTagCompound currentAspectsCmp = new NBTTagCompound();
        this.currentAspects.writeToNBT(currentAspectsCmp);
        par1NBTTagCompound.func_74757_a(TAG_WORKING, this.working);
        par1NBTTagCompound.func_74782_a(TAG_TOTAL_ASPECTS, (NBTBase)totalAspectsCmp);
        par1NBTTagCompound.func_74782_a(TAG_CURRENT_ASPECTS, (NBTBase)currentAspectsCmp);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
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
        return "enchanter";
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
        return false;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readCustomNBT(pkt.func_148857_g());
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public int[] func_94128_d(int var1) {
        return new int[0];
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        return false;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }
}

