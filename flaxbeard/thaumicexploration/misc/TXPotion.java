/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 */
package flaxbeard.thaumicexploration.misc;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class TXPotion
extends Potion {
    private static final ResourceLocation icon = new ResourceLocation("thaumicexploration:textures/tabs/binding.png");

    public TXPotion(int par1, boolean par2, int par3) {
        super(par1, par2, par3);
    }

    public Potion func_76399_b(int par1, int par2) {
        super.func_76399_b(par1, par2);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76400_d() {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(icon);
        return true;
    }
}

