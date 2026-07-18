/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingSpawnEvent$CheckSpawn
 */
package vazkii.botania.common.brew.potion;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import vazkii.botania.common.brew.potion.PotionMod;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PotionEmptiness
extends PotionMod {
    private static final int RANGE = 128;

    public PotionEmptiness() {
        super(ConfigHandler.potionIDEmptiness, "emptiness", false, 0xFACFFF, 2);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onSpawn(LivingSpawnEvent.CheckSpawn event) {
        if (event.getResult() != Event.Result.ALLOW && event.entityLiving instanceof IMob) {
            List players = event.world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(event.x - 128.0f), (double)(event.y - 128.0f), (double)(event.z - 128.0f), (double)(event.x + 128.0f), (double)(event.y + 128.0f), (double)(event.z + 128.0f)));
            for (EntityPlayer player : players) {
                if (!this.hasEffect((EntityLivingBase)player)) continue;
                event.setResult(Event.Result.DENY);
                return;
            }
        }
    }
}

