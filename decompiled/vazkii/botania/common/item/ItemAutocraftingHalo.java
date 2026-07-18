/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import vazkii.botania.common.item.ItemCraftingHalo;

public class ItemAutocraftingHalo
extends ItemCraftingHalo {
    private static final ResourceLocation glowTexture = new ResourceLocation("botania:textures/misc/glow1.png");

    public ItemAutocraftingHalo() {
        super("autocraftingHalo");
    }

    @Override
    public void func_77663_a(ItemStack stack, World world, Entity entity, int pos, boolean equipped) {
        super.func_77663_a(stack, world, entity, pos, equipped);
        if (entity instanceof EntityPlayer && !equipped) {
            EntityPlayer player = (EntityPlayer)entity;
            IInventory inv = ItemAutocraftingHalo.getFakeInv(player);
            for (int i = 1; i < 12; ++i) {
                this.tryCraft(player, stack, i, false, inv, false);
            }
        }
    }

    @Override
    public ResourceLocation getGlowResource() {
        return glowTexture;
    }
}

