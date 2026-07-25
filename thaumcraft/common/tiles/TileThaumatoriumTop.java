/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileThaumatorium;

public class TileThaumatoriumTop
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport,
ISidedInventory {
    public TileThaumatorium thaumatorium = null;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (this.thaumatorium == null) {
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (tile != null && tile instanceof TileThaumatorium) {
                this.thaumatorium = (TileThaumatorium)tile;
                this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            } else {
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 9, 3);
            }
        }
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (this.thaumatorium == null) {
            return am;
        }
        return this.thaumatorium.addToContainer(tt, am);
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.thaumatorium == null) {
            return false;
        }
        return this.thaumatorium.takeFromContainer(tt, am);
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tt, int am) {
        if (this.thaumatorium == null) {
            return false;
        }
        return this.thaumatorium.doesContainerContainAmount(tt, am);
    }

    @Override
    public int containerContains(Aspect tt) {
        if (this.thaumatorium == null) {
            return 0;
        }
        return this.thaumatorium.containerContains(tt);
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        if (this.thaumatorium == null) {
            return false;
        }
        return this.thaumatorium.isConnectable(face);
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        if (this.thaumatorium == null) {
            return false;
        }
        return this.thaumatorium.canInputFrom(face);
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
        if (this.thaumatorium == null) {
            return;
        }
        this.thaumatorium.setSuction(aspect, amount);
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        if (this.thaumatorium == null) {
            return null;
        }
        return this.thaumatorium.getSuctionType(loc);
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        if (this.thaumatorium == null) {
            return 0;
        }
        return this.thaumatorium.getSuctionAmount(loc);
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.thaumatorium == null) {
            return 0;
        }
        return this.thaumatorium.takeEssentia(aspect, amount, face);
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.thaumatorium == null) {
            return 0;
        }
        return this.thaumatorium.addEssentia(aspect, amount, face);
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return false;
    }

    @Override
    public AspectList getAspects() {
        if (this.thaumatorium == null) {
            return null;
        }
        return this.thaumatorium.essentia;
    }

    @Override
    public void setAspects(AspectList aspects) {
        if (this.thaumatorium == null) {
            return;
        }
        this.thaumatorium.setAspects(aspects);
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        if (this.thaumatorium == null) {
            return null;
        }
        return this.thaumatorium.func_70301_a(par1);
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.thaumatorium == null) {
            return null;
        }
        return this.thaumatorium.func_70298_a(par1, par2);
    }

    public ItemStack func_70304_b(int par1) {
        if (this.thaumatorium == null) {
            return null;
        }
        return this.thaumatorium.func_70304_b(par1);
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        if (this.thaumatorium == null) {
            return;
        }
        this.thaumatorium.func_70299_a(par1, par2ItemStack);
    }

    public String func_145825_b() {
        return "container.alchemyfurnace";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public int[] func_94128_d(int par1) {
        return new int[]{0};
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }
}

