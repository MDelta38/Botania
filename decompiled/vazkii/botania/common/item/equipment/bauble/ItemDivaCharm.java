/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.lib.LibObfuscation;

public class ItemDivaCharm
extends ItemBauble
implements IManaUsingItem,
IBaubleRender {
    public ItemDivaCharm() {
        super("divaCharm");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onEntityDamaged(LivingHurtEvent event) {
        EntityPlayer player;
        ItemStack amulet;
        if (event.source.func_76346_g() instanceof EntityPlayer && event.entityLiving instanceof EntityLiving && !event.entityLiving.field_70170_p.field_72995_K && Math.random() < (double)0.6f && (amulet = PlayerHandler.getPlayerBaubles((EntityPlayer)(player = (EntityPlayer)event.source.func_76346_g())).func_70301_a(0)) != null && amulet.func_77973_b() == this) {
            int cost = 250;
            if (ManaItemHandler.requestManaExact(amulet, player, 250, false)) {
                int range = 20;
                List mobs = player.field_70170_p.func_72872_a(IMob.class, AxisAlignedBB.func_72330_a((double)(event.entity.field_70165_t - 20.0), (double)(event.entity.field_70163_u - 20.0), (double)(event.entity.field_70161_v - 20.0), (double)(event.entity.field_70165_t + 20.0), (double)(event.entity.field_70163_u + 20.0), (double)(event.entity.field_70161_v + 20.0)));
                if (mobs.size() > 1 && SubTileHeiseiDream.brainwashEntity((EntityLiving)event.entityLiving, mobs)) {
                    if (event.entityLiving instanceof EntityCreeper) {
                        ReflectionHelper.setPrivateValue(EntityCreeper.class, (Object)((EntityCreeper)event.entityLiving), (Object)2, (String[])LibObfuscation.TIME_SINCE_IGNITED);
                    }
                    event.entityLiving.func_70691_i(event.entityLiving.func_110138_aP());
                    if (event.entityLiving.field_70128_L) {
                        event.entityLiving.field_70128_L = false;
                    }
                    ManaItemHandler.requestManaExact(amulet, player, 250, true);
                    player.field_70170_p.func_72956_a((Entity)player, "botania:divaCharm", 1.0f, 1.0f);
                    double x = event.entityLiving.field_70165_t;
                    double y = event.entityLiving.field_70163_u;
                    double z = event.entityLiving.field_70161_v;
                    for (int i = 0; i < 50; ++i) {
                        Botania.proxy.sparkleFX(event.entityLiving.field_70170_p, x + Math.random() * (double)event.entityLiving.field_70130_N, y + Math.random() * (double)event.entityLiving.field_70131_O, z + Math.random() * (double)event.entityLiving.field_70130_N, 1.0f, 1.0f, 0.25f, 1.0f, 3);
                    }
                }
            }
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        if (type == IBaubleRender.RenderType.HEAD) {
            float f = this.field_77791_bV.func_94209_e();
            float f1 = this.field_77791_bV.func_94212_f();
            float f2 = this.field_77791_bV.func_94206_g();
            float f3 = this.field_77791_bV.func_94210_h();
            boolean armor = event.entityPlayer.func_82169_q(3) != null;
            IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)(armor ? -0.35f : -0.3f));
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)this.field_77791_bV.func_94211_a(), (int)this.field_77791_bV.func_94216_b(), (float)0.0625f);
        }
    }
}

