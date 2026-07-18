/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemGoldenLaurel
extends ItemBauble
implements IBaubleRender {
    public ItemGoldenLaurel() {
        super("goldenLaurel");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onPlayerDeath(LivingDeathEvent event) {
        EntityPlayer player;
        ItemStack amulet;
        if (event.entity instanceof EntityPlayer && (amulet = PlayerHandler.getPlayerBaubles((EntityPlayer)(player = (EntityPlayer)event.entity)).func_70301_a(0)) != null && amulet.func_77973_b() == this) {
            event.setCanceled(true);
            player.func_70606_j(player.func_110138_aP());
            player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 300, 6));
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("botaniamisc.savedByLaurel", new Object[0]));
            player.field_70170_p.func_72956_a((Entity)player, "botania:goldenLaurel", 1.0f, 0.3f);
            PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70299_a(0, null);
        }
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.AMULET;
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
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.55f, (float)0.3f);
            if (armor) {
                GL11.glScalef((float)1.1f, (float)1.1f, (float)1.0f);
                GL11.glTranslatef((float)-0.05f, (float)-0.1f, (float)0.0f);
            }
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)this.field_77791_bV.func_94211_a(), (int)this.field_77791_bV.func_94216_b(), (float)0.03125f);
        }
    }
}

