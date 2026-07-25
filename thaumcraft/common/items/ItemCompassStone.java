/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemCompassStone
extends Item {
    public IIcon[] icon = new IIcon[2];
    private IIcon t = null;
    public static HashMap<WorldCoordinates, Long> sinisterNodes = new HashMap();

    public ItemCompassStone() {
        this.func_77625_d(1);
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:sinister_stone");
        this.icon[1] = ir.func_94245_a("thaumcraft:sinister_stone_active");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int par1) {
        return par1 == 1 ? this.icon[1] : (this.t == null ? this.icon[0] : this.t);
    }

    public void func_77663_a(ItemStack p_77663_1_, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        if (world.field_72995_K) {
            ArrayList<WorldCoordinates> del = new ArrayList<WorldCoordinates>();
            this.t = null;
            for (WorldCoordinates wc : sinisterNodes.keySet()) {
                if (sinisterNodes.get(wc) < System.currentTimeMillis() - 10000L) {
                    del.add(wc);
                }
                if (wc.dim != world.field_73011_w.field_76574_g || !EntityUtils.isVisibleTo(0.66f, entity, (double)wc.x + 0.5, (double)wc.y + 0.5, (double)wc.z + 0.5, 256.0f)) continue;
                this.t = this.icon[1];
                break;
            }
            for (WorldCoordinates wc : del) {
                sinisterNodes.remove(wc);
            }
        }
    }

    private double directionToPoint(double x1, double z1, double x2, double z2) {
        double dx = x1 - x2;
        double dz = z1 - z2;
        return Math.atan2(dz, dx) * 180.0 / Math.PI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack((Item)this, 1, 0));
    }

    public EnumRarity func_77613_e(ItemStack stack) {
        return EnumRarity.rare;
    }
}

