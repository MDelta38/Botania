/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.item.equipment.bauble;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.bauble.ItemTravelBelt;

public class ItemSpeedUpBelt
extends ItemTravelBelt {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/speedUpBelt.png");
    private static final String TAG_SPEED = "speed";
    private static final String TAG_OLD_X = "oldX";
    private static final String TAG_OLD_Y = "oldY";
    private static final String TAG_OLD_Z = "oldZ";

    public ItemSpeedUpBelt() {
        super("speedUpBelt", 0.0f, 0.2f, 2.0f);
    }

    @Override
    public ResourceLocation getRenderTexture() {
        return texture;
    }

    @Override
    public float getSpeed(ItemStack stack) {
        return ItemNBTHelper.getFloat(stack, TAG_SPEED, 0.0f);
    }

    @Override
    public void onMovedTick(ItemStack stack, EntityPlayer player) {
        float speed = this.getSpeed(stack);
        float newspeed = Math.min(0.25f, speed + 3.5E-4f);
        ItemNBTHelper.setFloat(stack, TAG_SPEED, newspeed);
        this.commitPositionAndCompare(stack, player);
    }

    @Override
    public void onNotMovingTick(ItemStack stack, EntityPlayer player) {
        if (!this.commitPositionAndCompare(stack, player)) {
            ItemNBTHelper.setFloat(stack, TAG_SPEED, 0.0f);
        }
    }

    public boolean commitPositionAndCompare(ItemStack stack, EntityPlayer player) {
        double oldX = ItemNBTHelper.getDouble(stack, TAG_OLD_X, 0.0);
        double oldY = ItemNBTHelper.getDouble(stack, TAG_OLD_Y, 0.0);
        double oldZ = ItemNBTHelper.getDouble(stack, TAG_OLD_Z, 0.0);
        ItemNBTHelper.setDouble(stack, TAG_OLD_X, player.field_70165_t);
        ItemNBTHelper.setDouble(stack, TAG_OLD_Y, player.field_70163_u);
        ItemNBTHelper.setDouble(stack, TAG_OLD_Z, player.field_70161_v);
        return Math.abs(oldX - player.field_70165_t) > 0.001 || Math.abs(oldY - player.field_70163_u) > 0.001 || Math.abs(oldZ - player.field_70161_v) > 0.001;
    }
}

