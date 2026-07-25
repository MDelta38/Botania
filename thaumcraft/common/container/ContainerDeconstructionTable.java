/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.container.SlotLimitedHasAspects;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileDeconstructionTable;

public class ContainerDeconstructionTable
extends Container {
    private TileDeconstructionTable table;
    private int lastBreakTime;

    public ContainerDeconstructionTable(InventoryPlayer par1InventoryPlayer, TileDeconstructionTable tileEntity) {
        int i;
        this.table = tileEntity;
        this.func_75146_a(new SlotLimitedHasAspects((IInventory)tileEntity, 0, 64, 16));
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
        par1ICrafting.func_71112_a((Container)this, 0, this.table.breaktime);
    }

    public boolean func_75140_a(EntityPlayer p, int button) {
        if (button == 1 && this.table.aspect != null) {
            Thaumcraft.proxy.playerKnowledge.addAspectPool(p.func_70005_c_(), this.table.aspect, (short)1);
            ResearchManager.scheduleSave(p);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(this.table.aspect.getTag(), (short)1, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(p.func_70005_c_(), this.table.aspect)), (EntityPlayerMP)p);
            this.table.aspect = null;
            this.table.func_145831_w().func_147471_g(this.table.field_145851_c, this.table.field_145848_d, this.table.field_145849_e);
        }
        return false;
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
            if (this.lastBreakTime == this.table.breaktime) continue;
            icrafting.func_71112_a((Container)this, 0, this.table.breaktime);
        }
        this.lastBreakTime = this.table.breaktime;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.table.breaktime = par2;
        }
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.table.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 != 0) {
                AspectList al = ThaumcraftCraftingManager.getObjectTags(itemstack1);
                if ((al = ThaumcraftCraftingManager.getBonusTags(itemstack1, al)) != null && al.size() > 0 ? !this.func_75135_a(itemstack1, 0, 1, false) : (par2 >= 1 && par2 < 28 ? !this.func_75135_a(itemstack1, 28, 37, false) : par2 >= 28 && par2 < 37 && !this.func_75135_a(itemstack1, 1, 28, false))) {
                    return null;
                }
            } else if (!this.func_75135_a(itemstack1, 1, 37, false)) {
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
}

