/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.armor.terrasteel;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelArmor;

public class ItemTerrasteelHelm
extends ItemTerrasteelArmor
implements IManaDiscountArmor,
IAncientWillContainer,
IManaGivingItem {
    private static final String TAG_ANCIENT_WILL = "AncientWill";
    static IIcon willIcon;

    public ItemTerrasteelHelm() {
        this("terrasteelHelm");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public ItemTerrasteelHelm(String name) {
        super(0, name);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        willIcon = IconHelper.forName(par1IconRegister, "willFlame");
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (this.hasArmorSet(player)) {
            int food = player.func_71024_bL().func_75116_a();
            if (food > 0 && food < 18 && player.func_70996_bM() && player.field_70173_aa % 80 == 0) {
                player.func_70691_i(1.0f);
            }
            ManaItemHandler.dispatchManaExact(stack, player, 1, true);
        }
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.2f : 0.0f;
    }

    @Override
    public void addAncientWill(ItemStack stack, int will) {
        ItemNBTHelper.setBoolean(stack, TAG_ANCIENT_WILL + will, true);
    }

    @Override
    public boolean hasAncientWill(ItemStack stack, int will) {
        return ItemTerrasteelHelm.hasAncientWill_(stack, will);
    }

    public static boolean hasAncientWill_(ItemStack stack, int will) {
        return ItemNBTHelper.getBoolean(stack, TAG_ANCIENT_WILL + will, false);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addArmorSetDescription(ItemStack stack, List<String> list) {
        super.addArmorSetDescription(stack, list);
        for (int i = 0; i < 6; ++i) {
            if (!this.hasAncientWill(stack, i)) continue;
            this.addStringToTooltip(StatCollector.func_74838_a((String)("botania.armorset.will" + i + ".desc")), list);
        }
    }

    public static boolean hasAnyWill(ItemStack stack) {
        for (int i = 0; i < 6; ++i) {
            if (!ItemTerrasteelHelm.hasAncientWill_(stack, i)) continue;
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderOnPlayer(ItemStack stack, RenderPlayerEvent event) {
        if (ItemTerrasteelHelm.hasAnyWill(stack) && !((ItemTerrasteelArmor)stack.func_77973_b()).hasPhantomInk(stack)) {
            GL11.glPushMatrix();
            float f = willIcon.func_94209_e();
            float f1 = willIcon.func_94212_f();
            float f2 = willIcon.func_94206_g();
            float f3 = willIcon.func_94210_h();
            IBaubleRender.Helper.translateToHeadLevel(event.entityPlayer);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.26f, (float)0.15f, (float)-0.39f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)willIcon.func_94211_a(), (int)willIcon.func_94216_b(), (float)0.0625f);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingHurtEvent event) {
        EntityPlayer player;
        Entity attacker = event.source.func_76346_g();
        if (attacker instanceof EntityPlayer && this.hasArmorSet(player = (EntityPlayer)attacker)) {
            boolean crit = player.field_70143_R > 0.0f && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null;
            ItemStack stack = player.field_71071_by.func_70440_f(3);
            if (crit && stack != null && stack.func_77973_b() instanceof ItemTerrasteelHelm) {
                boolean ahrim = this.hasAncientWill(stack, 0);
                boolean dharok = this.hasAncientWill(stack, 1);
                boolean guthan = this.hasAncientWill(stack, 2);
                boolean torag = this.hasAncientWill(stack, 3);
                boolean verac = this.hasAncientWill(stack, 4);
                boolean karil = this.hasAncientWill(stack, 5);
                if (ahrim) {
                    event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 20, 1));
                }
                if (dharok) {
                    event.ammount *= 1.0f + (1.0f - player.func_110143_aJ() / player.func_110138_aP()) * 0.5f;
                }
                if (guthan) {
                    player.func_70691_i(event.ammount * 0.25f);
                }
                if (torag) {
                    event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 60, 1));
                }
                if (verac) {
                    event.source.func_76348_h();
                }
                if (karil) {
                    event.entityLiving.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 60, 1));
                }
            }
        }
    }
}

