/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;

public class ItemWispEssence
extends Item
implements IEssentiaContainerItem {
    public IIcon icon;
    static Aspect[] displayAspects = Aspect.aspects.values().toArray(new Aspect[0]);

    public ItemWispEssence() {
        this.func_77625_d(64);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:wispessence");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (Aspect tag : Aspect.aspects.values()) {
            ItemStack i = new ItemStack((Item)this, 1, 0);
            this.setAspects(i, new AspectList().add(tag, 2));
            par3List.add(i);
        }
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        AspectList aspects = this.getAspects(stack);
        if (aspects != null && aspects.size() > 0) {
            for (Aspect tag : aspects.getAspectsSorted()) {
                if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.func_70005_c_(), tag)) {
                    list.add(tag.getName() + " x" + aspects.getAmount(tag));
                    continue;
                }
                list.add(StatCollector.func_74838_a((String)"tc.aspect.unknown"));
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        if (this.getAspects(stack) != null) {
            return this.getAspects(stack).getAspects()[0].getColor();
        }
        int idx = (int)(System.currentTimeMillis() / 500L % (long)displayAspects.length);
        return displayAspects[idx].getColor();
    }

    @Override
    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.func_77942_o()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.func_77978_p());
            return aspects.size() > 0 ? aspects : null;
        }
        return null;
    }

    @Override
    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        aspects.writeToNBT(itemstack.func_77978_p());
    }
}

