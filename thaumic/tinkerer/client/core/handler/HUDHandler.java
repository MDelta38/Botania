/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  thaumcraft.api.wands.ItemFocusBasic
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.common.item.foci.ItemFocusDislocation;

public final class HUDHandler {
    RenderItem renderItem = new RenderItem();

    @SubscribeEvent
    public void drawDislocationFocusHUD(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && ClientHelper.minecraft().field_71462_r == null) {
            boolean up = !Config.dialBottom;
            int xpos = 4;
            int ypos = up ? 50 : event.resolution.func_78328_b() - 70;
            ItemStack item = ClientHelper.clientPlayer().func_71045_bC();
            if (item != null && item.func_77973_b() instanceof ItemWandCasting) {
                ItemStack pickedBlock;
                ItemWandCasting wand = (ItemWandCasting)item.func_77973_b();
                wand.getFocusItem(item);
                ItemFocusBasic focus = wand.getFocus(item);
                if (focus != null && focus instanceof ItemFocusDislocation && (pickedBlock = ((ItemFocusDislocation)focus).getPickedBlock(item)) != null) {
                    Gui.func_73734_a((int)(xpos - 1), (int)(ypos - 1), (int)(xpos + 18), (int)(ypos + 18), (int)0x66000000);
                    FontRenderer font = ClientHelper.fontRenderer();
                    boolean unicode = font.func_82883_a();
                    font.func_78264_a(true);
                    String name = StatCollector.func_74838_a((String)"ttmisc.focusDislocation.tooltip");
                    int strLength = font.func_78256_a(name);
                    Gui.func_73734_a((int)(xpos + 18), (int)ypos, (int)(xpos + 18 + strLength + 4), (int)(ypos + 9), (int)0x66000000);
                    font.func_78261_a(name, xpos + 20, ypos, 0xFFAA00);
                    NBTTagCompound cmp = ((ItemFocusDislocation)focus).getStackTileEntity(item);
                    if (cmp != null && !cmp.func_82582_d()) {
                        String content = StatCollector.func_74838_a((String)"ttmisc.focusDislocation.tooltipExtra");
                        font.func_78256_a(content);
                        Gui.func_73734_a((int)(xpos + 18), (int)(ypos + 9), (int)(xpos + 18 + strLength + 4), (int)(ypos + 18), (int)0x66000000);
                        font.func_78261_a(content, xpos + 20, ypos + 9, 0xFFAA00);
                    }
                    if (new ItemStack(((ItemBlock)pickedBlock.func_77973_b()).field_150939_a).func_77973_b() != null) {
                        this.renderItem.func_77015_a(font, ClientHelper.minecraft().field_71446_o, new ItemStack(((ItemBlock)pickedBlock.func_77973_b()).field_150939_a), xpos, ypos);
                    } else if (((ItemBlock)pickedBlock.func_77973_b()).field_150939_a == Blocks.field_150436_aH) {
                        this.renderItem.func_77015_a(font, ClientHelper.minecraft().field_71446_o, new ItemStack(Items.field_151120_aE), xpos, ypos);
                    }
                    font.func_78264_a(unicode);
                }
            }
        }
    }
}

