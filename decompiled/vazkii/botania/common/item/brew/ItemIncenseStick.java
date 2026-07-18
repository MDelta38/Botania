/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.brew;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewContainer;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public class ItemIncenseStick
extends ItemMod
implements IBrewItem,
IBrewContainer {
    private static final String TAG_BREW_KEY = "brewKey";
    public static final int TIME_MULTIPLIER = 60;
    IIcon[] icons;

    public ItemIncenseStick() {
        this.func_77655_b("incenseStick");
        this.func_77625_d(1);
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        super.func_150895_a(item, tab, list);
        for (String s : BotaniaAPI.brewMap.keySet()) {
            ItemStack brewStack = this.getItemForBrew(BotaniaAPI.brewMap.get(s), new ItemStack((Item)this));
            if (brewStack == null) continue;
            list.add(brewStack);
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[2];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forItem(par1IconRegister, (Item)this, i);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.icons[pass];
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            return 0xFFFFFF;
        }
        Brew brew = this.getBrew(stack);
        if (brew == BotaniaAPI.fallbackBrew) {
            return 0x989898;
        }
        Color color = new Color(brew.getColor(stack));
        int add = (int)(Math.sin((double)ClientTickHandler.ticksInGame * 0.2) * 24.0);
        int r = Math.max(0, Math.min(255, color.getRed() + add));
        int g = Math.max(0, Math.min(255, color.getGreen() + add));
        int b = Math.max(0, Math.min(255, color.getBlue() + add));
        return r << 16 | g << 8 | b;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        Brew brew = this.getBrew(stack);
        if (brew == BotaniaAPI.fallbackBrew) {
            this.addStringToTooltip(EnumChatFormatting.LIGHT_PURPLE + StatCollector.func_74838_a((String)"botaniamisc.notInfused"), list);
            return;
        }
        this.addStringToTooltip(EnumChatFormatting.LIGHT_PURPLE + String.format(StatCollector.func_74838_a((String)"botaniamisc.brewOf"), StatCollector.func_74838_a((String)brew.getUnlocalizedName(stack))), list);
        for (PotionEffect effect : brew.getPotionEffects(stack)) {
            Potion potion = Potion.field_76425_a[effect.func_76456_a()];
            EnumChatFormatting format = potion.func_76398_f() ? EnumChatFormatting.RED : EnumChatFormatting.GRAY;
            PotionEffect longEffect = new PotionEffect(effect.func_76456_a(), effect.func_76459_b() * 60, effect.func_76458_c(), false);
            this.addStringToTooltip(" " + format + StatCollector.func_74838_a((String)effect.func_76453_d()) + (effect.func_76458_c() == 0 ? "" : " " + StatCollector.func_74838_a((String)("botania.roman" + (effect.func_76458_c() + 1)))) + EnumChatFormatting.GRAY + (potion.func_76403_b() ? "" : " (" + Potion.func_76389_a((PotionEffect)longEffect) + ")"), list);
        }
    }

    void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    @Override
    public Brew getBrew(ItemStack stack) {
        String key = ItemNBTHelper.getString(stack, TAG_BREW_KEY, "");
        return BotaniaAPI.getBrewFromKey(key);
    }

    public static void setBrew(ItemStack stack, Brew brew) {
        ItemIncenseStick.setBrew(stack, brew.getKey());
    }

    public static void setBrew(ItemStack stack, String brew) {
        ItemNBTHelper.setString(stack, TAG_BREW_KEY, brew);
    }

    @Override
    public ItemStack getItemForBrew(Brew brew, ItemStack stack) {
        if (!brew.canInfuseIncense() || brew.getPotionEffects(stack).size() != 1 || Potion.field_76425_a[brew.getPotionEffects(stack).get(0).func_76456_a()].func_76403_b()) {
            return null;
        }
        ItemStack brewStack = new ItemStack((Item)this);
        ItemIncenseStick.setBrew(brewStack, brew);
        return brewStack;
    }

    @Override
    public int getManaCost(Brew brew, ItemStack stack) {
        return brew.getManaCost() * 10;
    }
}

