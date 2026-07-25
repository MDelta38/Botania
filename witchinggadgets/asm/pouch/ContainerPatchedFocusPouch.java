/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.common.container.ContainerFocusPouch
 *  thaumcraft.common.container.InventoryFocusPouch
 *  thaumcraft.common.items.wands.ItemFocusPouch
 */
package witchinggadgets.asm.pouch;

import baubles.api.BaublesApi;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.container.ContainerFocusPouch;
import thaumcraft.common.container.InventoryFocusPouch;
import thaumcraft.common.items.wands.ItemFocusPouch;

public class ContainerPatchedFocusPouch
extends ContainerFocusPouch {
    public ContainerPatchedFocusPouch(InventoryPlayer iinventory, World world, int par3, int par4, int par5) {
        super(iinventory, world, par3, par4, par5);
        ReflectionHelper.setPrivateValue(ContainerFocusPouch.class, (Object)((Object)this), (Object)-1, (String[])new String[]{"blockSlot"});
        ItemStack beltPouch = null;
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)iinventory.field_70458_d);
        for (int a = 0; a < 4; ++a) {
            if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemFocusPouch)) continue;
            beltPouch = baubles.func_70301_a(a);
        }
        if (beltPouch != null) {
            ReflectionHelper.setPrivateValue(ContainerFocusPouch.class, (Object)((Object)this), beltPouch, (String[])new String[]{"pouch"});
            if (!world.field_72995_K) {
                ((InventoryFocusPouch)this.input).stackList = ((ItemFocusPouch)beltPouch.func_77973_b()).getInventory(beltPouch);
            }
        }
        this.func_75130_a(this.input);
    }

    public void func_75134_a(EntityPlayer player) {
        ItemStack beltPouch;
        super.func_75134_a(player);
        if (!player.field_70170_p.field_72995_K && (beltPouch = (ItemStack)ReflectionHelper.getPrivateValue(ContainerFocusPouch.class, (Object)((Object)this), (String[])new String[]{"pouch"})) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77969_a(beltPouch)) {
            BaublesApi.getBaubles((EntityPlayer)player).func_70299_a(3, beltPouch);
            BaublesApi.getBaubles((EntityPlayer)player).func_70296_d();
        }
    }
}

