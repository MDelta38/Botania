/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityVineBall;
import vazkii.botania.common.item.ItemMod;
import vazkii.botania.common.item.ModItems;

public class ItemSlingshot
extends ItemMod {
    public ItemSlingshot() {
        this.func_77625_d(1);
        this.func_77655_b("slingshot");
    }

    public void func_77615_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        int j = this.func_77626_a(par1ItemStack) - par4;
        if (par3EntityPlayer.field_71075_bZ.field_75098_d || par3EntityPlayer.field_71071_by.func_146028_b(ModItems.vineBall)) {
            float f = (float)j / 20.0f;
            if ((f = (f * f + f * 2.0f) / 3.0f) < 1.0f) {
                return;
            }
            if (!par3EntityPlayer.field_71075_bZ.field_75098_d) {
                par3EntityPlayer.field_71071_by.func_146026_a(ModItems.vineBall);
            }
            if (!par2World.field_72995_K) {
                EntityVineBall ball = new EntityVineBall(par3EntityPlayer, false);
                ball.field_70159_w *= 1.6;
                ball.field_70181_x *= 1.6;
                ball.field_70179_y *= 1.6;
                par2World.func_72838_d((Entity)ball);
            }
        }
    }

    public ItemStack func_77654_b(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (par3EntityPlayer.field_71075_bZ.field_75098_d || par3EntityPlayer.field_71071_by.func_146028_b(ModItems.vineBall)) {
            par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        }
        return par1ItemStack;
    }
}

