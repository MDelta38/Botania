/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemShears
 *  thaumcraft.api.IRepairable
 *  thaumcraft.api.ThaumcraftApi
 */
package witchinggadgets.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemShears;
import thaumcraft.api.IRepairable;
import thaumcraft.api.ThaumcraftApi;

public class ItemThaumiumShears
extends ItemShears
implements IRepairable {
    public ItemThaumiumShears() {
        this.func_77656_e(ThaumcraftApi.toolMatThaumium.func_77997_a());
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:thaumiumShears");
    }
}

