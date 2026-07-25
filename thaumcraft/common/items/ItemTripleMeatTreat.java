/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.ItemFood
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.IIcon
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import thaumcraft.common.Thaumcraft;

public class ItemTripleMeatTreat
extends ItemFood {
    public IIcon icon;

    public ItemTripleMeatTreat() {
        super(6, 0.8f, true);
        this.func_77848_i();
        this.func_77844_a(Potion.field_76428_l.field_76415_H, 5, 0, 0.66f);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:tripletreat");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int meta) {
        return this.icon;
    }
}

