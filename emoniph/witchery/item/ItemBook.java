/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemBase;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class ItemBook
extends ItemBase {
    static final String CURRENT_PAGE_KEY = "CurrentPage";
    public static final BiomeDictionary.Type[] BIOME_TYPES = new BiomeDictionary.Type[]{BiomeDictionary.Type.BEACH, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.HILLS, BiomeDictionary.Type.MESA, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.SNOWY, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.WASTELAND, BiomeDictionary.Type.RIVER, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.SPOOKY, BiomeDictionary.Type.MAGICAL};

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        int posX = MathHelper.func_76128_c((double)player.field_70165_t);
        int posY = MathHelper.func_76128_c((double)player.field_70163_u);
        int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
        player.openGui((Object)Witchery.instance, 6, world, posX, posY, posZ);
        return stack;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean expandedTooltip) {
        list.add(String.format(Witchery.resource("witchery.biomebook.currentpage"), ItemBook.getSelectedBiome((int)ItemBook.getSelectedBiome((ItemStack)stack, (int)1000)).field_76791_y));
        list.add("");
        for (String s : Witchery.resource("item.witchery:biomebook2.tip").split("\n")) {
            if (s.isEmpty()) continue;
            list.add(s);
        }
    }

    public static int getSelectedBiome(ItemStack stack, int maxPages) {
        NBTTagCompound stackCompound = stack.func_77978_p();
        if (stackCompound != null && stackCompound.func_74764_b(CURRENT_PAGE_KEY)) {
            return Math.min(Math.max(stackCompound.func_74762_e(CURRENT_PAGE_KEY), 0), Math.max(maxPages, 1) - 1);
        }
        return 0;
    }

    public static BiomeGenBase getSelectedBiome(int page) {
        ArrayList biomes = new ArrayList();
        int i = 0;
        for (BiomeDictionary.Type biomeType : BIOME_TYPES) {
            BiomeGenBase[] biomesInType = BiomeDictionary.getBiomesForType((BiomeDictionary.Type)biomeType);
            for (int j = 0; j < biomesInType.length; ++j) {
                if (i++ != page) continue;
                return biomesInType[j];
            }
        }
        return BiomeGenBase.field_76772_c;
    }

    public ItemStack getContainerItem(ItemStack stack) {
        if (!this.hasContainerItem(stack)) {
            return null;
        }
        return stack.func_77946_l();
    }

    public static void setSelectedBiome(ItemStack itemstack, int pageIndex) {
        if (itemstack.func_77978_p() == null) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        itemstack.func_77978_p().func_74768_a(CURRENT_PAGE_KEY, pageIndex);
    }
}

