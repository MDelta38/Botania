/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibMisc;

public class TileSparkChanger
extends TileSimpleInventory {
    public void doSwap() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        ItemStack changeStack = this.func_70301_a(0);
        ArrayList<ISparkAttachable> attachables = new ArrayList<ISparkAttachable>();
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            int newUpg;
            ISparkAttachable attach;
            ISparkEntity spark;
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
            if (tile == null || !(tile instanceof ISparkAttachable) || (spark = (attach = (ISparkAttachable)tile).getAttachedSpark()) == null) continue;
            int upg = spark.getUpgrade();
            int n = newUpg = changeStack == null ? 0 : changeStack.func_77960_j() + 1;
            if (upg == newUpg) continue;
            attachables.add(attach);
        }
        if (attachables.size() > 0) {
            ISparkAttachable attach = (ISparkAttachable)attachables.get(this.field_145850_b.field_73012_v.nextInt(attachables.size()));
            ISparkEntity spark = attach.getAttachedSpark();
            int upg = spark.getUpgrade();
            ItemStack sparkStack = upg == 0 ? null : new ItemStack(ModItems.sparkUpgrade, 1, upg - 1);
            int newUpg = changeStack == null ? 0 : changeStack.func_77960_j() + 1;
            spark.setUpgrade(newUpg);
            Collection<ISparkEntity> transfers = spark.getTransfers();
            if (transfers != null) {
                transfers.clear();
            }
            this.func_70299_a(0, sparkStack);
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
            this.func_70296_d();
        }
    }

    public int func_70302_i_() {
        return 1;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return itemstack != null && itemstack.func_77973_b() == ModItems.sparkUpgrade;
    }

    @Override
    public int func_70297_j_() {
        return 1;
    }

    public void func_70296_d() {
        super.func_70296_d();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public String func_145825_b() {
        return "sparkChanger";
    }
}

