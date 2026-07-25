/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ChestGenHooks
 */
package thaumcraft.common.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;
import thaumcraft.common.container.SlotOutput;
import thaumcraft.common.entities.InventoryPech;
import thaumcraft.common.entities.monster.EntityPech;

public class ContainerPech
extends Container {
    private EntityPech pech;
    private InventoryPech inventory;
    private EntityPlayer player;
    private final World theWorld;
    ChestGenHooks chest = ChestGenHooks.getInfo((String)"dungeonChest");

    public ContainerPech(InventoryPlayer par1InventoryPlayer, World par3World, EntityPech par2IMerchant) {
        int j;
        int i;
        this.pech = par2IMerchant;
        this.theWorld = par3World;
        this.player = par1InventoryPlayer.field_70458_d;
        this.inventory = new InventoryPech(par1InventoryPlayer.field_70458_d, par2IMerchant, this);
        this.pech.trading = true;
        this.func_75146_a(new Slot((IInventory)this.inventory, 0, 36, 29));
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.func_75146_a(new SlotOutput(this.inventory, 1 + j + i * 2, 106 + 18 * j, 20 + 18 * i));
            }
        }
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)par1InventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public InventoryPech getMerchantInventory() {
        return this.inventory;
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
    }

    public void func_75142_b() {
        super.func_75142_b();
    }

    public boolean func_75140_a(EntityPlayer par1EntityPlayer, int par2) {
        if (par2 == 0) {
            this.generateContents();
            return true;
        }
        return super.func_75140_a(par1EntityPlayer, par2);
    }

    private boolean hasStuffInPack() {
        for (ItemStack stack : this.pech.loot) {
            if (stack == null || stack.field_77994_a <= 0) continue;
            return true;
        }
        return false;
    }

    private void generateContents() {
        if (!this.theWorld.field_72995_K && this.inventory.func_70301_a(0) != null && this.inventory.func_70301_a(1) == null && this.inventory.func_70301_a(2) == null && this.inventory.func_70301_a(3) == null && this.inventory.func_70301_a(4) == null && this.pech.isValued(this.inventory.func_70301_a(0))) {
            int value = this.pech.getValue(this.inventory.func_70301_a(0));
            if (this.theWorld.field_73012_v.nextInt(100) <= value / 2) {
                this.pech.setTamed(false);
                this.pech.updateAINextTick = true;
                this.pech.func_85030_a("thaumcraft:pech_trade", 0.4f, 1.0f);
            }
            if (this.theWorld.field_73012_v.nextInt(5) == 0) {
                value += this.theWorld.field_73012_v.nextInt(3);
            } else if (this.theWorld.field_73012_v.nextBoolean()) {
                value -= this.theWorld.field_73012_v.nextInt(3);
            }
            ArrayList<List> pos = EntityPech.tradeInventory.get(this.pech.getPechType());
            while (value > 0) {
                int am = Math.min(5, Math.max((value + 1) / 2, this.theWorld.field_73012_v.nextInt(value) + 1));
                value -= am;
                if (am == 1 && this.theWorld.field_73012_v.nextBoolean() && this.hasStuffInPack()) {
                    ArrayList<Integer> loot = new ArrayList<Integer>();
                    for (int a = 0; a < this.pech.loot.length; ++a) {
                        if (this.pech.loot[a] == null || this.pech.loot[a].field_77994_a <= 0) continue;
                        loot.add(a);
                    }
                    int r = (Integer)loot.get(this.theWorld.field_73012_v.nextInt(loot.size()));
                    ItemStack is = this.pech.loot[r].func_77946_l();
                    is.field_77994_a = 1;
                    this.func_75135_a(is, 1, 5, false);
                    --this.pech.loot[r].field_77994_a;
                    if (this.pech.loot[r].field_77994_a > 0) continue;
                    this.pech.loot[r] = null;
                    continue;
                }
                if (am >= 4 && this.theWorld.field_73012_v.nextBoolean()) {
                    WeightedRandomChestContent[] contents = this.chest.getItems(this.theWorld.field_73012_v);
                    WeightedRandomChestContent wc = null;
                    int cc = 0;
                    do {
                        wc = contents[this.theWorld.field_73012_v.nextInt(contents.length)];
                    } while (++cc < 50 && (wc.field_76297_b == null || wc.field_76292_a > 5 || wc.field_76296_e > 1));
                    if (wc == null || wc.field_76297_b == null) {
                        value += am;
                        continue;
                    }
                    ItemStack is = wc.field_76297_b.func_77946_l();
                    is.func_77980_a(this.theWorld, this.player, 0);
                    this.func_75135_a(is, 1, 5, false);
                    continue;
                }
                List it = null;
                while ((Integer)(it = pos.get(this.theWorld.field_73012_v.nextInt(pos.size()))).get(0) != am) {
                }
                ItemStack is = ((ItemStack)it.get(1)).func_77946_l();
                is.func_77980_a(this.theWorld, this.player, 0);
                this.func_75135_a(is, 1, 5, false);
            }
            this.inventory.func_70298_a(0, 1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.pech.isTamed();
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 == 0 ? !this.func_75135_a(itemstack1, 5, 41, true) : (par2 >= 1 && par2 < 5 ? !this.func_75135_a(itemstack1, 5, 41, true) : par2 != 0 && par2 >= 5 && par2 < 41 && !this.func_75135_a(itemstack1, 0, 1, true))) {
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
            slot.func_82870_a(par1EntityPlayer, itemstack1);
        }
        return itemstack;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        this.pech.trading = false;
        if (!this.theWorld.field_72995_K) {
            for (int a = 0; a < 5; ++a) {
                EntityItem ei;
                ItemStack itemstack = this.inventory.func_70304_b(a);
                if (itemstack == null || (ei = par1EntityPlayer.func_71019_a(itemstack, false)) == null) continue;
                ei.func_145799_b("PechDrop");
            }
        }
    }

    protected boolean func_75135_a(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_) {
        ItemStack itemstack1;
        Slot slot;
        boolean flag1 = false;
        int k = p_75135_2_;
        if (p_75135_4_) {
            k = p_75135_3_ - 1;
        }
        if (p_75135_1_.func_77985_e()) {
            while (p_75135_1_.field_77994_a > 0 && (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_)) {
                slot = (Slot)this.field_75151_b.get(k);
                itemstack1 = slot.func_75211_c();
                if (itemstack1 != null && itemstack1.func_77973_b() == p_75135_1_.func_77973_b() && (!p_75135_1_.func_77981_g() || p_75135_1_.func_77960_j() == itemstack1.func_77960_j()) && ItemStack.func_77970_a((ItemStack)p_75135_1_, (ItemStack)itemstack1)) {
                    int l = itemstack1.field_77994_a + p_75135_1_.field_77994_a;
                    if (l <= p_75135_1_.func_77976_d()) {
                        p_75135_1_.field_77994_a = 0;
                        itemstack1.field_77994_a = l;
                        slot.func_75218_e();
                        flag1 = true;
                    } else if (itemstack1.field_77994_a < p_75135_1_.func_77976_d()) {
                        p_75135_1_.field_77994_a -= p_75135_1_.func_77976_d() - itemstack1.field_77994_a;
                        itemstack1.field_77994_a = p_75135_1_.func_77976_d();
                        slot.func_75218_e();
                        flag1 = true;
                    }
                }
                if (p_75135_4_) {
                    --k;
                    continue;
                }
                ++k;
            }
        }
        if (p_75135_1_.field_77994_a > 0) {
            k = p_75135_4_ ? p_75135_3_ - 1 : p_75135_2_;
            while (!p_75135_4_ && k < p_75135_3_ || p_75135_4_ && k >= p_75135_2_) {
                slot = (Slot)this.field_75151_b.get(k);
                itemstack1 = slot.func_75211_c();
                if (itemstack1 == null) {
                    slot.func_75215_d(p_75135_1_.func_77946_l());
                    slot.func_75218_e();
                    p_75135_1_.field_77994_a = 0;
                    flag1 = true;
                    break;
                }
                if (p_75135_4_) {
                    --k;
                    continue;
                }
                ++k;
            }
        }
        return flag1;
    }
}

