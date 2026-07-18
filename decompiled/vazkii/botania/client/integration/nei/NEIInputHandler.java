/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.LayoutManager
 *  codechicken.nei.NEIClientConfig
 *  codechicken.nei.guihook.GuiContainerManager
 *  codechicken.nei.guihook.IContainerInputHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.integration.nei;

import codechicken.nei.LayoutManager;
import codechicken.nei.NEIClientConfig;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerInputHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;

public class NEIInputHandler
implements IContainerInputHandler {
    public boolean keyTyped(GuiContainer gui, char c, int i) {
        ItemStack stack;
        LayoutManager layoutManager;
        Minecraft mc = Minecraft.func_71410_x();
        if (TileCorporeaIndex.InputHandler.getNearbyIndexes((EntityPlayer)mc.field_71439_g).isEmpty()) {
            return false;
        }
        int bind = NEIClientConfig.getKeyBinding((String)"gui.botania_corporea_request");
        if (i == bind && (layoutManager = LayoutManager.instance()) != null && LayoutManager.itemPanel != null && !NEIClientConfig.isHidden() && (stack = GuiContainerManager.getStackMouseOver((GuiContainer)gui)) != null && stack.func_77973_b() != null) {
            int count = 1;
            int max = stack.func_77976_d();
            if (gui.func_146272_n()) {
                count = max;
                if (gui.func_146271_m()) {
                    count /= 4;
                }
            } else if (gui.func_146271_m()) {
                count = max / 2;
            }
            if (count > 0) {
                String name = CorporeaHelper.stripControlCodes(stack.func_82833_r());
                String full = count + " " + name;
                mc.field_71456_v.func_146158_b().func_146239_a(full);
                mc.field_71439_g.func_71165_d(full);
                return true;
            }
        }
        return false;
    }

    public boolean lastKeyTyped(GuiContainer arg0, char arg1, int arg2) {
        return false;
    }

    public boolean mouseClicked(GuiContainer arg0, int arg1, int arg2, int arg3) {
        return false;
    }

    public boolean mouseScrolled(GuiContainer arg0, int arg1, int arg2, int arg3) {
        return false;
    }

    public void onKeyTyped(GuiContainer arg0, char arg1, int arg2) {
    }

    public void onMouseClicked(GuiContainer arg0, int arg1, int arg2, int arg3) {
    }

    public void onMouseDragged(GuiContainer arg0, int arg1, int arg2, int arg3, long arg4) {
    }

    public void onMouseScrolled(GuiContainer arg0, int arg1, int arg2, int arg3) {
    }

    public void onMouseUp(GuiContainer arg0, int arg1, int arg2, int arg3) {
    }
}

