/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileMagicWorkbench;
import thaumcraft.common.tiles.TileVisRelay;

public class TileMagicWorkbenchCharger
extends TileVisRelay {
    public short orientation = 0;

    @Override
    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d - 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    @Override
    public boolean isSource() {
        return false;
    }

    @Override
    public void func_145845_h() {
        AspectList al;
        TileMagicWorkbench tm;
        ItemStack wand;
        TileEntity te;
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && (te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) != null && te instanceof TileMagicWorkbench && (wand = (tm = (TileMagicWorkbench)te).func_70301_a(10)) != null && wand.func_77973_b() instanceof ItemWandCasting && (al = ((ItemWandCasting)wand.func_77973_b()).getAspectsWithRoom(wand)).size() > 0) {
            for (Aspect aspect : al.getAspects()) {
                int drain = Math.min(5, ((ItemWandCasting)wand.func_77973_b()).getMaxVis(tm.func_70301_a(10)) - ((ItemWandCasting)wand.func_77973_b()).getVis(tm.func_70301_a(10), aspect));
                if (drain <= 0) continue;
                ((ItemWandCasting)wand.func_77973_b()).addRealVis(tm.func_70301_a(10), aspect, this.consumeVis(aspect, drain), true);
            }
        }
    }
}

