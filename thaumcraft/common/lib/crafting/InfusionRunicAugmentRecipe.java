/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.crafting;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.world.World;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.events.EventHandlerRunic;

public class InfusionRunicAugmentRecipe
extends InfusionRecipe {
    private ItemStack[] components;

    public InfusionRunicAugmentRecipe() {
        super("RUNICAUGMENTATION", null, 0, null, null, new ItemStack[]{new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14)});
    }

    public InfusionRunicAugmentRecipe(ItemStack in) {
        super("RUNICAUGMENTATION", null, 0, null, in, new ItemStack[]{new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14)});
        this.components = new ItemStack[]{new ItemStack(Items.field_151045_i), new ItemStack(ConfigItems.itemResource, 1, 14)};
        int fc = EventHandlerRunic.getFinalCharge(in);
        if (fc > 0) {
            ArrayList<ItemStack> com = new ArrayList<ItemStack>();
            com.add(new ItemStack(Items.field_151045_i));
            com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
            for (int c = 0; c < fc; ++c) {
                com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
            }
            this.components = com.toArray(this.components);
        }
    }

    @Override
    public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research)) {
            return false;
        }
        if (!(central.func_77973_b() instanceof IRunicArmor)) {
            return false;
        }
        ItemStack i2 = null;
        ArrayList<ItemStack> ii = new ArrayList<ItemStack>();
        for (ItemStack is : input) {
            ii.add(is.func_77946_l());
        }
        for (ItemStack comp : this.getComponents(central)) {
            boolean b = false;
            for (int a = 0; a < ii.size(); ++a) {
                i2 = ((ItemStack)ii.get(a)).func_77946_l();
                if (comp.func_77960_j() == Short.MAX_VALUE) {
                    i2.func_77964_b(Short.MAX_VALUE);
                }
                if (!InfusionRunicAugmentRecipe.areItemStacksEqual(i2, comp, true)) continue;
                ii.remove(a);
                b = true;
                break;
            }
            if (b) continue;
            return false;
        }
        return ii.size() == 0;
    }

    @Override
    public Object getRecipeOutput(ItemStack input) {
        if (input == null) {
            return null;
        }
        ItemStack out = input.func_77946_l();
        int base = EventHandlerRunic.getHardening(input) + 1;
        out.func_77983_a("RS.HARDEN", (NBTBase)new NBTTagByte((byte)base));
        return out;
    }

    @Override
    public AspectList getAspects(ItemStack input) {
        AspectList out = new AspectList();
        int vis = (int)(32.0 * Math.pow(2.0, EventHandlerRunic.getFinalCharge(input)));
        if (vis > 0) {
            out.add(Aspect.ARMOR, vis / 2);
            out.add(Aspect.MAGIC, vis / 2);
            out.add(Aspect.ENERGY, vis);
        }
        return out;
    }

    @Override
    public int getInstability(ItemStack input) {
        int i = 5 + EventHandlerRunic.getFinalCharge(input) / 2;
        return i;
    }

    public ItemStack[] getComponents(ItemStack input) {
        ArrayList<ItemStack> com = new ArrayList<ItemStack>();
        com.add(new ItemStack(Items.field_151045_i));
        com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
        int fc = EventHandlerRunic.getFinalCharge(input);
        if (fc > 0) {
            for (int c = 0; c < fc; ++c) {
                com.add(new ItemStack(ConfigItems.itemResource, 1, 14));
            }
        }
        return com.toArray(new ItemStack[0]);
    }

    @Override
    public ItemStack[] getComponents() {
        return this.components;
    }
}

