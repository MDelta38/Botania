/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.ITinyPlanetExcempt;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemTinyPlanet
extends ItemBauble
implements IBaubleRender {
    public static final String TAG_ORBIT = "orbit";

    public ItemTinyPlanet() {
        super("tinyPlanet");
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        double x = player.field_70165_t;
        double y = player.field_70163_u + (double)1.2f;
        double z = player.field_70161_v;
        if (player.field_70170_p.field_72995_K) {
            y -= (double)1.62f;
        }
        ItemTinyPlanet.applyEffect(player.field_70170_p, x, y, z);
    }

    public static void applyEffect(World world, double x, double y, double z) {
        int range = 8;
        List entities = world.func_72872_a(IManaBurst.class, AxisAlignedBB.func_72330_a((double)(x - (double)range), (double)(y - (double)range), (double)(z - (double)range), (double)(x + (double)range), (double)(y + (double)range), (double)(z + (double)range)));
        for (Entity entity : entities) {
            IManaBurst burst = (IManaBurst)entity;
            ItemStack lens = burst.getSourceLens();
            if (lens != null && lens.func_77973_b() instanceof ITinyPlanetExcempt && !((ITinyPlanetExcempt)lens.func_77973_b()).shouldPull(lens)) continue;
            int orbitTime = ItemTinyPlanet.getEntityOrbitTime(entity);
            if (orbitTime == 0) {
                burst.setMinManaLoss(burst.getMinManaLoss() * 3);
            }
            float radius = Math.min(7.5f, (float)(Math.max(40, orbitTime) - 40) / 40.0f + 1.5f);
            int angle = orbitTime % 360;
            float xTarget = (float)(x + Math.cos((double)(angle * 10) * Math.PI / 180.0) * (double)radius);
            float yTarget = (float)y;
            float zTarget = (float)(z + Math.sin((double)(angle * 10) * Math.PI / 180.0) * (double)radius);
            Vector3 targetVec = new Vector3(xTarget, yTarget, zTarget);
            Vector3 currentVec = new Vector3(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
            Vector3 moveVector = targetVec.copy().sub(currentVec);
            burst.setMotion(moveVector.x, moveVector.y, moveVector.z);
            ItemTinyPlanet.incrementOrbitTime(entity);
        }
    }

    public static int getEntityOrbitTime(Entity entity) {
        NBTTagCompound cmp = entity.getEntityData();
        if (cmp.func_74764_b(TAG_ORBIT)) {
            return cmp.func_74762_e(TAG_ORBIT);
        }
        return 0;
    }

    public static void incrementOrbitTime(Entity entity) {
        NBTTagCompound cmp = entity.getEntityData();
        int time = ItemTinyPlanet.getEntityOrbitTime(entity);
        cmp.func_74768_a(TAG_ORBIT, time + 1);
    }

    @Override
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.HEAD) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            GL11.glTranslatef((float)0.25f, (float)-0.5f, (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            RenderBlocks.getInstance().func_147800_a(ModBlocks.tinyPlanet, 0, 1.0f);
        }
    }
}

