/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.common.container.ContainerMagicBox;

public class TileMagicBox
extends TileThaumcraft
implements IInventory {
    ArrayList<ItemStack> boxContents = new ArrayList();
    WorldCoordinates master = null;
    byte sorting = (byte)-1;
    short linkedBoxes = (short)-1;
    public static ContainerMagicBox tc;

    public int func_70302_i_() {
        return 27 * (this.getInventory().linkedBoxes + 1);
    }

    private ArrayList<ItemStack> getContents() {
        return this.master != null ? this.getInventory().boxContents : this.boxContents;
    }

    private TileMagicBox getInventory() {
        TileEntity tile = null;
        if (this.master != null) {
            tile = this.field_145850_b.func_147438_o(this.master.x, this.master.y, this.master.z);
        }
        return tile != null && tile instanceof TileMagicBox ? (TileMagicBox)tile : this;
    }

    public ItemStack func_70301_a(int par1) {
        return par1 >= this.getContents().size() ? null : this.getContents().get(par1);
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (par1 < this.getContents().size() && this.getContents().get(par1) != null) {
            if (this.getContents().get((int)par1).field_77994_a <= par2) {
                ItemStack var3 = this.getContents().get(par1);
                this.getContents().remove(par1);
                this.getInventory().func_70296_d();
                return var3;
            }
            ItemStack var3 = this.getContents().get(par1).func_77979_a(par2);
            if (this.getContents().get((int)par1).field_77994_a == 0) {
                this.getContents().remove(par1);
            }
            this.getInventory().func_70296_d();
            return var3;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (par1 < this.getContents().size() && this.getContents().get(par1) != null) {
            ItemStack var2 = this.getContents().get(par1);
            this.getContents().remove(par1);
            return var2;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        if (par1 >= this.getContents().size() && par2ItemStack != null && par2ItemStack.field_77994_a > 0) {
            this.getContents().add(par2ItemStack);
        } else if (par2ItemStack != null && par2ItemStack.field_77994_a > 0) {
            this.getContents().set(par1, par2ItemStack);
        } else if (par1 < this.getContents().size()) {
            this.getContents().remove(par1);
        }
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.getInventory().func_70296_d();
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.sort();
    }

    public String func_145825_b() {
        return "Magic Box";
    }

    @Override
    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.boxContents = new ArrayList();
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            this.boxContents.add(ItemStack.func_77949_a((NBTTagCompound)var4));
        }
        this.sort();
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        this.sorting = par1NBTTagCompound.func_74771_c("sort");
        this.master = null;
        if (par1NBTTagCompound.func_74764_b("w_x")) {
            this.master = new WorldCoordinates();
            this.master.readNBT(par1NBTTagCompound);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.boxContents.size(); ++var3) {
            if (this.boxContents.get(var3) == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            this.boxContents.get(var3).func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74774_a("sort", this.sorting);
        if (this.master != null) {
            this.master.writeNBT(par1NBTTagCompound);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.getInventory() == this && this.linkedBoxes < 0) {
            this.refreshLinks();
        }
    }

    public boolean func_145842_c(int par1, int par2) {
        if (par1 == 1) {
            return true;
        }
        if (par1 == 2) {
            return true;
        }
        return this.field_145846_f;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public void func_145843_s() {
        this.func_145836_u();
        super.func_145843_s();
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public void sort() {
        if (this.func_145831_w() == null || this.sorting < 0) {
            return;
        }
        boolean done = false;
        block0: while (!done) {
            done = true;
            for (int i = 0; i < this.getContents().size() - 1; ++i) {
                done = this.swopSlots(i, i + 1);
                if (this.getContents().get((int)i).field_77994_a >= this.getContents().get(i).func_77976_d() || !this.getContents().get(i).func_77969_a(this.getContents().get(i + 1)) || !ItemStack.func_77970_a((ItemStack)this.getContents().get(i), (ItemStack)this.getContents().get(i + 1))) continue;
                ItemStack is1 = this.getContents().get(i).func_77946_l();
                ItemStack is2 = this.getContents().get(i + 1).func_77946_l();
                int c = Math.min(is1.func_77976_d() - is1.field_77994_a, is2.field_77994_a);
                is1.field_77994_a += c;
                is2.field_77994_a -= c;
                this.getContents().set(i, is1);
                done = false;
                if (is2.field_77994_a > 0) {
                    this.getContents().set(i + 1, is2);
                    continue;
                }
                this.getContents().remove(i + 1);
                continue block0;
            }
        }
    }

    private boolean swopSlots(int i, int j) {
        if (this.sorting == 0 || this.sorting == 1) {
            if (this.getContents().get(i).func_82833_r() != null && this.getContents().get(j).func_82833_r() != null) {
                int r;
                String s1 = "";
                String s2 = "";
                s1 = s1 + this.getContents().get(i).func_82833_r();
                s2 = s2 + this.getContents().get(j).func_82833_r();
                if (this.getContents().get(i).func_77942_o()) {
                    s1 = s1 + "" + this.getContents().get((int)i).field_77990_d.hashCode();
                }
                if (this.getContents().get(j).func_77942_o()) {
                    s2 = s2 + "" + this.getContents().get((int)j).field_77990_d.hashCode();
                }
                if ((r = s1.compareToIgnoreCase(s2)) > 0 && this.sorting == 0 || r < 0 && this.sorting == 1) {
                    ItemStack is1 = this.getContents().get(i).func_77946_l();
                    ItemStack is2 = this.getContents().get(j).func_77946_l();
                    this.getContents().set(i, is2);
                    this.getContents().set(j, is1);
                    return false;
                }
            }
        } else if (this.sorting == 2 && this.getContents().get(i).func_82833_r() != null && this.getContents().get(j).func_82833_r() != null) {
            int r;
            String s1 = "";
            String s2 = "";
            if (GameRegistry.findUniqueIdentifierFor((Item)this.getContents().get(i).func_77973_b()) != null) {
                s1 = s1 + GameRegistry.findUniqueIdentifierFor((Item)this.getContents().get((int)i).func_77973_b()).modId;
            } else if (GameRegistry.findUniqueIdentifierFor((Block)Block.func_149634_a((Item)this.getContents().get(i).func_77973_b())) != null) {
                s1 = s1 + GameRegistry.findUniqueIdentifierFor((Block)Block.func_149634_a((Item)this.getContents().get((int)i).func_77973_b())).modId;
            }
            if (GameRegistry.findUniqueIdentifierFor((Item)this.getContents().get(j).func_77973_b()) != null) {
                s1 = s1 + GameRegistry.findUniqueIdentifierFor((Item)this.getContents().get((int)j).func_77973_b()).modId;
            } else if (GameRegistry.findUniqueIdentifierFor((Block)Block.func_149634_a((Item)this.getContents().get(j).func_77973_b())) != null) {
                s1 = s1 + GameRegistry.findUniqueIdentifierFor((Block)Block.func_149634_a((Item)this.getContents().get((int)j).func_77973_b())).modId;
            }
            s1 = s1 + this.getContents().get(i).func_82833_r();
            s2 = s2 + this.getContents().get(j).func_82833_r();
            if (this.getContents().get(i).func_77942_o()) {
                s1 = s1 + "" + this.getContents().get((int)i).field_77990_d.hashCode();
            }
            if (this.getContents().get(j).func_77942_o()) {
                s2 = s2 + "" + this.getContents().get((int)j).field_77990_d.hashCode();
            }
            if ((r = s1.compareToIgnoreCase(s2)) > 0 && this.sorting == 2) {
                ItemStack is1 = this.getContents().get(i).func_77946_l();
                ItemStack is2 = this.getContents().get(j).func_77946_l();
                this.getContents().set(i, is2);
                this.getContents().set(j, is1);
                return false;
            }
        }
        return true;
    }

    public void refreshLinks() {
        if (this.getInventory() != this) {
            return;
        }
        this.linkedBoxes = 0;
        ArrayList<WorldCoordinates> list = new ArrayList<WorldCoordinates>();
        this.findBoxes(this.field_145851_c, this.field_145848_d, this.field_145849_e, list);
        this.linkedBoxes = (short)list.size();
    }

    private void findBoxes(int x, int y, int z, ArrayList<WorldCoordinates> list) {
        if (list.size() >= 1024) {
            return;
        }
        for (int a = 0; a < 6; ++a) {
            WorldCoordinates wc;
            ForgeDirection dir = ForgeDirection.getOrientation((int)a);
            TileEntity tile = this.field_145850_b.func_147438_o(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
            if (tile == null || !(tile instanceof TileMagicBox) || list.contains(wc = new WorldCoordinates(tile))) continue;
            list.add(wc);
            this.findBoxes(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, list);
        }
    }
}

