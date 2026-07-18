/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ItemMod;

public class ItemFertilizer
extends ItemMod {
    public ItemFertilizer() {
        this.func_77655_b("fertilizer");
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        int range = 3;
        if (!par3World.field_72995_K) {
            ArrayList<ChunkCoordinates> validCoords = new ArrayList<ChunkCoordinates>();
            for (int i = -4; i < 3; ++i) {
                for (int j = -4; j < 3; ++j) {
                    for (int k = 2; k >= -2; --k) {
                        int x = par4 + i + 1;
                        int y = par5 + k + 1;
                        int z = par6 + j + 1;
                        if (!par3World.func_147437_c(x, y, z) || par3World.field_73011_w.field_76576_e && y >= 255 || !ModBlocks.flower.func_149718_j(par3World, x, y, z)) continue;
                        validCoords.add(new ChunkCoordinates(x, y, z));
                    }
                }
            }
            int flowerCount = Math.min(validCoords.size(), par3World.field_73012_v.nextBoolean() ? 3 : 4);
            for (int i = 0; i < flowerCount; ++i) {
                ChunkCoordinates coords = (ChunkCoordinates)validCoords.get(par3World.field_73012_v.nextInt(validCoords.size()));
                validCoords.remove(coords);
                par3World.func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, ModBlocks.flower, par3World.field_73012_v.nextInt(16), 3);
            }
            --par1ItemStack.field_77994_a;
        } else {
            for (int i = 0; i < 15; ++i) {
                double x = (double)(par4 - 3 + par3World.field_73012_v.nextInt(7)) + Math.random();
                double y = par5 + 1;
                double z = (double)(par6 - 3 + par3World.field_73012_v.nextInt(7)) + Math.random();
                float red = (float)Math.random();
                float green = (float)Math.random();
                float blue = (float)Math.random();
                Botania.proxy.wispFX(par3World, x, y, z, red, green, blue, 0.15f + (float)Math.random() * 0.25f, -((float)Math.random()) * 0.1f - 0.05f);
            }
        }
        return true;
    }
}

