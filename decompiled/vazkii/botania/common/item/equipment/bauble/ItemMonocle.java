/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraftforge.client.event.RenderPlayerEvent
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.IBurstViewerBauble;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemMonocle
extends ItemBauble
implements IBurstViewerBauble,
ICosmeticBauble {
    public ItemMonocle() {
        super("monocle");
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    @Override
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
            GL11.glTranslatef((float)-0.35f, (float)-0.1f, (float)(armor ? -0.3f : -0.25f));
            GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
            ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)f1, (float)f2, (float)f, (float)f3, (int)this.field_77791_bV.func_94211_a(), (int)this.field_77791_bV.func_94216_b(), (float)0.0625f);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderHUD(ScaledResolution resolution, EntityPlayer player) {
        Minecraft mc = Minecraft.func_71410_x();
        MovingObjectPosition pos = mc.field_71476_x;
        if (pos == null) {
            return;
        }
        Block block = player.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        int meta = player.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        player.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        ItemStack dispStack = null;
        String text = "";
        if (block == Blocks.field_150488_af) {
            dispStack = new ItemStack(Items.field_151137_ax);
            text = EnumChatFormatting.RED + "" + meta;
        } else if (block == Blocks.field_150413_aR || block == Blocks.field_150416_aS) {
            dispStack = new ItemStack(Items.field_151107_aW);
            text = "" + (((meta & 0xC) >> 2) + 1);
        } else if (block == Blocks.field_150441_bU || block == Blocks.field_150455_bV) {
            dispStack = new ItemStack(Items.field_151132_bS);
            String string = text = (meta & 4) == 4 ? "-" : "+";
        }
        if (dispStack == null) {
            return;
        }
        int x = resolution.func_78326_a() / 2 + 15;
        int y = resolution.func_78328_b() / 2 - 8;
        RenderHelper.func_74520_c();
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, dispStack, x, y);
        RenderHelper.func_74518_a();
        mc.field_71466_p.func_78261_a(text, x + 20, y + 4, 0xFFFFFF);
    }

    public static boolean hasMonocle(EntityPlayer player) {
        for (int i = 0; i < 4; ++i) {
            ICosmeticAttachable attach;
            ItemStack cosmetic;
            ItemStack stack = PlayerHandler.getPlayerBaubles((EntityPlayer)player).func_70301_a(i);
            if (stack == null) continue;
            Item item = stack.func_77973_b();
            if (item instanceof IBurstViewerBauble) {
                return true;
            }
            if (!(item instanceof ICosmeticAttachable) || (cosmetic = (attach = (ICosmeticAttachable)item).getCosmeticItem(stack)) == null || !(cosmetic.func_77973_b() instanceof IBurstViewerBauble)) continue;
            return true;
        }
        return false;
    }
}

