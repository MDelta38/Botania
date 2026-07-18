/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package vazkii.botania.common.network;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import vazkii.botania.client.gui.bag.ContainerFlowerBag;
import vazkii.botania.client.gui.bag.GuiFlowerBag;
import vazkii.botania.client.gui.box.ContainerBaubleBox;
import vazkii.botania.client.gui.box.GuiBaubleBox;
import vazkii.botania.client.gui.crafting.ContainerCraftingHalo;
import vazkii.botania.client.gui.crafting.GuiCraftingHalo;
import vazkii.botania.client.gui.lexicon.GuiLexicon;

public class GuiHandler
implements IGuiHandler {
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1: {
                return new ContainerCraftingHalo(player.field_71071_by, world);
            }
            case 2: {
                return new ContainerFlowerBag(player);
            }
            case 3: {
                return new ContainerBaubleBox(player);
            }
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: {
                GuiLexicon lex = GuiLexicon.currentOpenLexicon;
                return lex;
            }
            case 1: {
                return new GuiCraftingHalo(player.field_71071_by, world);
            }
            case 2: {
                return new GuiFlowerBag(player);
            }
            case 3: {
                return new GuiBaubleBox(player);
            }
        }
        return null;
    }
}

