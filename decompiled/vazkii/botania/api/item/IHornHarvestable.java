/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IHornHarvestable {
    public boolean canHornHarvest(World var1, int var2, int var3, int var4, ItemStack var5, EnumHornType var6);

    public boolean hasSpecialHornHarvest(World var1, int var2, int var3, int var4, ItemStack var5, EnumHornType var6);

    public void harvestByHorn(World var1, int var2, int var3, int var4, ItemStack var5, EnumHornType var6);

    public static enum EnumHornType {
        WILD,
        CANOPY,
        COVERING;


        public static EnumHornType getTypeForMeta(int meta) {
            EnumHornType[] values = EnumHornType.values();
            return values[Math.min(values.length - 1, meta)];
        }
    }
}

