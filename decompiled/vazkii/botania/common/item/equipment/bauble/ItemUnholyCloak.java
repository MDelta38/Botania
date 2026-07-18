/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package vazkii.botania.common.item.equipment.bauble;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.equipment.bauble.ItemHolyCloak;

public class ItemUnholyCloak
extends ItemHolyCloak {
    private static final ResourceLocation texture = new ResourceLocation("botania:textures/model/unholyCloak.png");

    public ItemUnholyCloak() {
        super("unholyCloak");
    }

    @Override
    public boolean effectOnDamage(LivingHurtEvent event, EntityPlayer player, ItemStack stack) {
        if (!event.source.func_76363_c()) {
            int range = 6;
            List mobs = player.field_70170_p.func_72872_a(IMob.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - (double)range), (double)(player.field_70163_u - (double)range), (double)(player.field_70161_v - (double)range), (double)(player.field_70165_t + (double)range), (double)(player.field_70163_u + (double)range), (double)(player.field_70161_v + (double)range)));
            for (IMob mob : mobs) {
                if (!(mob instanceof EntityLivingBase)) continue;
                EntityLivingBase entity = (EntityLivingBase)mob;
                entity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), event.ammount);
            }
            player.field_70170_p.func_72956_a((Entity)player, "botania:unholyCloak", 1.0f, 1.0f);
            for (int i = 0; i < 90; ++i) {
                float rad = (float)i * 4.0f * (float)Math.PI / 180.0f;
                float xMotion = (float)Math.cos(rad) * 0.2f;
                float zMotion = (float)Math.sin(rad) * 0.2f;
                Botania.proxy.wispFX(player.field_70170_p, player.field_70165_t, player.field_70163_u + 0.5, player.field_70161_v, 0.4f + (float)Math.random() + 0.25f, 0.0f, 0.0f, 0.6f + (float)Math.random() * 0.2f, xMotion, 0.0f, zMotion);
            }
            return true;
        }
        return false;
    }

    @Override
    ResourceLocation getRenderTexture() {
        return texture;
    }
}

