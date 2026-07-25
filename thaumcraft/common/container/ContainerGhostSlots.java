/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.container.SlotGhost;
import thaumcraft.common.container.SlotGhostFluid;

public class ContainerGhostSlots
extends Container {
    public boolean func_75145_c(EntityPlayer entityplayer) {
        return false;
    }

    public ItemStack func_75144_a(int slotClicked, int button, int mod, EntityPlayer player) {
        ItemStack itemstack = null;
        InventoryPlayer inventoryplayer = player.field_71071_by;
        if (!(mod != 0 && mod != 1 || button != 0 && button != 1)) {
            if (slotClicked == -999) {
                if (inventoryplayer.func_70445_o() != null && slotClicked == -999) {
                    if (button == 0) {
                        player.func_71019_a(inventoryplayer.func_70445_o(), false);
                        inventoryplayer.func_70437_b((ItemStack)null);
                    }
                    if (button == 1) {
                        player.func_71019_a(inventoryplayer.func_70445_o().func_77979_a(1), false);
                        if (inventoryplayer.func_70445_o().field_77994_a == 0) {
                            inventoryplayer.func_70437_b((ItemStack)null);
                        }
                    }
                }
            } else if (mod == 1) {
                if (slotClicked < 0) {
                    return null;
                }
                Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
                if (slot2 != null && slot2.func_75211_c() != null && slot2 instanceof SlotGhost) {
                    if (button == 0) {
                        slot2.func_75215_d((ItemStack)null);
                    } else if (button == 1) {
                        ItemStack slotStack = slot2.func_75211_c();
                        slotStack.field_77994_a += 16;
                        if (slotStack.field_77994_a > slot2.func_75219_a()) {
                            slotStack.field_77994_a = slot2.func_75219_a();
                        }
                    }
                }
            } else {
                if (slotClicked < 0) {
                    return null;
                }
                Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
                if (slot2 != null) {
                    ItemStack slotStack = slot2.func_75211_c();
                    ItemStack playerStack = inventoryplayer.func_70445_o();
                    if (slotStack != null) {
                        itemstack = slotStack.func_77946_l();
                    }
                    if (slotStack == null) {
                        if (playerStack != null && slot2.func_75214_a(playerStack)) {
                            int k1;
                            int n = k1 = button == 0 ? playerStack.field_77994_a : 1;
                            if (k1 > slot2.func_75219_a()) {
                                k1 = slot2.func_75219_a();
                            }
                            if (playerStack.field_77994_a >= k1) {
                                if (slot2 instanceof SlotGhost) {
                                    ItemStack ic = playerStack.func_77946_l();
                                    ic.field_77994_a = k1;
                                    slot2.func_75215_d(ic);
                                } else {
                                    slot2.func_75215_d(playerStack.func_77979_a(k1));
                                }
                            }
                            if (!(slot2 instanceof SlotGhost) && playerStack.field_77994_a == 0) {
                                inventoryplayer.func_70437_b((ItemStack)null);
                            }
                        }
                    } else if (slot2.func_82869_a(player) || slot2 instanceof SlotGhost) {
                        int k1;
                        if (playerStack == null) {
                            if (slot2 instanceof SlotGhost) {
                                int k12;
                                int n = k12 = button == 0 ? 1 : -1;
                                if (slotStack.field_77994_a - k12 <= slot2.func_75219_a()) {
                                    slot2.func_75209_a(k12);
                                }
                                if (slotStack.field_77994_a == 0) {
                                    slot2.func_75215_d((ItemStack)null);
                                }
                            } else {
                                int k13 = button == 0 ? slotStack.field_77994_a : (slotStack.field_77994_a + 1) / 2;
                                ItemStack itemstack3 = slot2.func_75209_a(k13);
                                inventoryplayer.func_70437_b(itemstack3);
                                if (slotStack.field_77994_a == 0) {
                                    slot2.func_75215_d((ItemStack)null);
                                }
                                slot2.func_82870_a(player, inventoryplayer.func_70445_o());
                            }
                        } else if (slot2.func_75214_a(playerStack)) {
                            if (slotStack.func_77973_b() == playerStack.func_77973_b() && slotStack.func_77960_j() == playerStack.func_77960_j() && ItemStack.func_77970_a((ItemStack)slotStack, (ItemStack)playerStack)) {
                                int k14;
                                int n = k14 = button == 0 ? playerStack.field_77994_a : 1;
                                if (k14 > slot2.func_75219_a() - slotStack.field_77994_a) {
                                    k14 = slot2.func_75219_a() - slotStack.field_77994_a;
                                }
                                if (k14 > playerStack.func_77976_d() - slotStack.field_77994_a) {
                                    k14 = playerStack.func_77976_d() - slotStack.field_77994_a;
                                }
                                if (!(slot2 instanceof SlotGhost)) {
                                    playerStack.func_77979_a(k14);
                                    if (playerStack.field_77994_a == 0) {
                                        inventoryplayer.func_70437_b((ItemStack)null);
                                    }
                                }
                                slotStack.field_77994_a += k14;
                            } else if (playerStack.field_77994_a <= slot2.func_75219_a()) {
                                slot2.func_75215_d(playerStack.func_77946_l());
                                if (!(slot2 instanceof SlotGhost)) {
                                    inventoryplayer.func_70437_b(slotStack);
                                }
                            }
                        } else if (slotStack.func_77973_b() == playerStack.func_77973_b() && playerStack.func_77976_d() > 1 && (!slotStack.func_77981_g() || slotStack.func_77960_j() == playerStack.func_77960_j()) && ItemStack.func_77970_a((ItemStack)slotStack, (ItemStack)playerStack) && (k1 = slotStack.field_77994_a) > 0 && k1 + playerStack.field_77994_a <= playerStack.func_77976_d()) {
                            if (!(slot2 instanceof SlotGhost)) {
                                playerStack.field_77994_a += k1;
                            }
                            slotStack = slot2.func_75209_a(k1);
                            if (slotStack.field_77994_a == 0) {
                                slot2.func_75215_d((ItemStack)null);
                            }
                            if (!(slot2 instanceof SlotGhost)) {
                                slot2.func_82870_a(player, inventoryplayer.func_70445_o());
                            }
                        }
                    }
                    slot2.func_75218_e();
                }
            }
        } else if (mod == 2 && button >= 0 && button < 9) {
            Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
            if (slot2.func_82869_a(player)) {
                ItemStack slotStack = inventoryplayer.func_70301_a(button);
                boolean flag = slotStack == null || slot2.field_75224_c == inventoryplayer && slot2.func_75214_a(slotStack);
                int k1 = -1;
                if (!flag) {
                    k1 = inventoryplayer.func_70447_i();
                    flag |= k1 > -1;
                }
                if (slot2.func_75216_d() && flag) {
                    ItemStack itemstack3 = slot2.func_75211_c();
                    if (!(slot2 instanceof SlotGhost)) {
                        inventoryplayer.func_70299_a(button, itemstack3.func_77946_l());
                    }
                    if (!(slot2.field_75224_c == inventoryplayer && slot2.func_75214_a(slotStack) || slotStack == null)) {
                        if (k1 > -1) {
                            if (!(slot2 instanceof SlotGhost)) {
                                inventoryplayer.func_70441_a(slotStack);
                            }
                            slot2.func_75209_a(itemstack3.field_77994_a);
                            slot2.func_75215_d((ItemStack)null);
                            if (!(slot2 instanceof SlotGhost)) {
                                slot2.func_82870_a(player, itemstack3);
                            }
                        }
                    } else {
                        slot2.func_75209_a(itemstack3.field_77994_a);
                        slot2.func_75215_d(slotStack);
                        if (!(slot2 instanceof SlotGhost)) {
                            slot2.func_82870_a(player, itemstack3);
                        }
                    }
                } else if (!slot2.func_75216_d() && slotStack != null && slot2.func_75214_a(slotStack)) {
                    if (!(slot2 instanceof SlotGhost)) {
                        inventoryplayer.func_70299_a(button, (ItemStack)null);
                    }
                    slot2.func_75215_d(slotStack);
                }
            }
        } else if (mod == 3 && player.field_71075_bZ.field_75098_d && inventoryplayer.func_70445_o() == null && slotClicked >= 0) {
            Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
            if (slot2 != null && slot2.func_75216_d()) {
                ItemStack slotStack = slot2.func_75211_c().func_77946_l();
                slotStack.field_77994_a = slotStack.func_77976_d();
                if (!(slot2 instanceof SlotGhost)) {
                    inventoryplayer.func_70437_b(slotStack);
                }
            }
        } else if (mod == 3) {
            Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
            if (slot2 != null && slot2 instanceof SlotGhost && button == 3) {
                slot2.func_75215_d((ItemStack)null);
            }
        } else if (mod == 4 && inventoryplayer.func_70445_o() == null && slotClicked >= 0) {
            Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
            if (slot2 != null && slot2.func_75216_d() && (slot2.func_82869_a(player) || slot2 instanceof SlotGhost)) {
                ItemStack slotStack = slot2.func_75209_a(button == 0 ? 1 : slot2.func_75211_c().field_77994_a);
                if (!(slot2 instanceof SlotGhost)) {
                    slot2.func_82870_a(player, slotStack);
                    player.func_71019_a(slotStack, false);
                }
            }
        } else if (mod == 6 && slotClicked >= 0) {
            Slot slot2 = (Slot)this.field_75151_b.get(slotClicked);
            ItemStack slotStack = inventoryplayer.func_70445_o();
            if (!(slotStack == null || slot2 != null && slot2.func_75216_d() && slot2.func_82869_a(player))) {
                int l = button == 0 ? 0 : this.field_75151_b.size() - 1;
                int k1 = button == 0 ? 1 : -1;
                for (int l1 = 0; l1 < 2; ++l1) {
                    for (int i2 = l; i2 >= 0 && i2 < this.field_75151_b.size() && slotStack.field_77994_a < slotStack.func_77976_d(); i2 += k1) {
                        Slot slot3 = (Slot)this.field_75151_b.get(i2);
                        if (slot3 instanceof SlotGhost || slot3 instanceof SlotGhostFluid || !slot3.func_75216_d() || !ContainerGhostSlots.func_94527_a((Slot)slot3, (ItemStack)slotStack, (boolean)true) || !slot3.func_82869_a(player) || !this.func_94530_a(slotStack, slot3) || l1 == 0 && slot3.func_75211_c().field_77994_a == slot3.func_75211_c().func_77976_d()) continue;
                        int j2 = Math.min(slotStack.func_77976_d() - slotStack.field_77994_a, slot3.func_75211_c().field_77994_a);
                        ItemStack itemstack5 = slot3.func_75209_a(j2);
                        if (!(slot2 instanceof SlotGhost)) {
                            slotStack.field_77994_a += j2;
                        }
                        if (itemstack5.field_77994_a <= 0) {
                            slot3.func_75215_d((ItemStack)null);
                        }
                        if (slot2 instanceof SlotGhost) continue;
                        slot3.func_82870_a(player, itemstack5);
                    }
                }
            }
            this.func_75142_b();
        }
        return itemstack;
    }
}

