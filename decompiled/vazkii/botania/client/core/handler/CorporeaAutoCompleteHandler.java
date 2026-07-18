/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  org.lwjgl.input.Keyboard
 */
package vazkii.botania.client.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.lwjgl.input.Keyboard;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.common.lib.LibObfuscation;

public class CorporeaAutoCompleteHandler {
    boolean isAutoCompleted = false;
    String originalString = "";
    List<CompletionData> completions = new ArrayList<CompletionData>();
    int position;
    static TreeSet<String> itemNames = new TreeSet<String>(new Comparator<String>(){

        @Override
        public int compare(String arg0, String arg1) {
            return arg0.compareToIgnoreCase(arg1);
        }
    });
    private boolean tabLastTick = false;

    public static void updateItemList() {
        itemNames.clear();
        Iterator iterator = Item.field_150901_e.iterator();
        ArrayList curList = new ArrayList();
        while (iterator.hasNext()) {
            Item item = (Item)iterator.next();
            if (item == null || item.func_77640_w() == null) continue;
            curList.clear();
            try {
                item.func_150895_a(item, (CreativeTabs)null, curList);
                for (ItemStack stack : curList) {
                    itemNames.add(CorporeaHelper.stripControlCodes(stack.func_82833_r().trim()));
                }
            }
            catch (Exception exception) {
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        boolean valid;
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        GuiScreen screen = Minecraft.func_71410_x().field_71462_r;
        if (!(screen instanceof GuiChat)) {
            this.isAutoCompleted = false;
            return;
        }
        GuiChat chat = (GuiChat)screen;
        if (this.isAutoCompleted && !(valid = ((Boolean)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)chat, (String[])LibObfuscation.COMPLETE_FLAG)).booleanValue())) {
            this.isAutoCompleted = false;
        }
        if (Keyboard.isKeyDown((int)15)) {
            if (this.tabLastTick) {
                return;
            }
        } else {
            this.tabLastTick = false;
            return;
        }
        this.tabLastTick = true;
        if (!CorporeaHelper.shouldAutoComplete()) {
            return;
        }
        GuiTextField inputField = (GuiTextField)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)chat, (String[])LibObfuscation.INPUT_FIELD);
        if (!this.isAutoCompleted) {
            this.buildAutoCompletes(inputField, chat);
        }
        if (this.isAutoCompleted && !this.completions.isEmpty()) {
            this.advanceAutoComplete(inputField, chat);
        }
    }

    private void advanceAutoComplete(GuiTextField inputField, GuiChat chat) {
        ++this.position;
        if (this.position >= this.completions.size()) {
            this.position -= this.completions.size();
        }
        CompletionData data = this.completions.get(this.position);
        String str = this.originalString.substring(0, this.originalString.length() - data.prefixLength) + data.string;
        inputField.func_146180_a(str);
    }

    private void buildAutoCompletes(GuiTextField inputField, GuiChat chat) {
        String leftOfCursor = inputField.func_146198_h() == 0 ? "" : inputField.func_146179_b().substring(0, inputField.func_146198_h());
        if (leftOfCursor.length() == 0 || leftOfCursor.charAt(0) == '/') {
            return;
        }
        this.completions = this.getNames(leftOfCursor);
        if (this.completions.isEmpty()) {
            return;
        }
        this.position = -1;
        ReflectionHelper.setPrivateValue(GuiChat.class, (Object)chat, (Object)true, (String[])LibObfuscation.COMPLETE_FLAG);
        StringBuilder stringbuilder = new StringBuilder();
        for (CompletionData data : this.completions) {
            if (stringbuilder.length() > 0) {
                stringbuilder.append(", ");
            }
            stringbuilder.append(data.string);
        }
        Minecraft.func_71410_x().field_71456_v.func_146158_b().func_146234_a((IChatComponent)new ChatComponentText(stringbuilder.toString()), 1);
        this.isAutoCompleted = true;
        this.originalString = inputField.func_146179_b();
    }

    private ArrayList<CompletionData> getNames(String prefix) {
        String s = prefix.trim();
        if (s.isEmpty()) {
            return new ArrayList<CompletionData>();
        }
        TreeSet<CompletionData> result = new TreeSet<CompletionData>();
        String[] words = s.split(" ");
        int i = words.length - 1;
        String curPrefix = words[i];
        while (i >= 0) {
            result.addAll(this.getNamesStartingWith(curPrefix.toLowerCase()));
            if (--i < 0) continue;
            curPrefix = words[i] + " " + curPrefix;
        }
        return new ArrayList<CompletionData>(result);
    }

    private List<CompletionData> getNamesStartingWith(String prefix) {
        ArrayList<CompletionData> result = new ArrayList<CompletionData>();
        int length = prefix.length();
        SortedSet<String> after = itemNames.tailSet(prefix);
        for (String str : after) {
            if (str.toLowerCase().startsWith(prefix)) {
                result.add(new CompletionData(str, length));
                continue;
            }
            return result;
        }
        return result;
    }

    private static class CompletionData
    implements Comparable<CompletionData> {
        private String string;
        private int prefixLength;

        public CompletionData(String string, int prefixLength) {
            this.string = string;
            this.prefixLength = prefixLength;
        }

        @Override
        public int compareTo(CompletionData arg0) {
            return this.string.compareTo(arg0.string);
        }
    }
}

