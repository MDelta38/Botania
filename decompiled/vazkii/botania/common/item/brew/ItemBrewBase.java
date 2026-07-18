/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.brew;

import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.IPickupAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ItemMod;

public abstract class ItemBrewBase
extends ItemMod
implements IBrewItem,
IPickupAchievement {
    private static final String TAG_BREW_KEY = "brewKey";
    private static final String TAG_SWIGS_LEFT = "swigsLeft";
    String name;
    String texName;
    int swigs;
    int drinkSpeed;
    ItemStack baseItem;
    IIcon[] icons;

    public ItemBrewBase(String name, String texName, int swigs, int drinkSpeed, ItemStack baseItem) {
        this.name = name;
        this.texName = texName;
        this.swigs = swigs;
        this.drinkSpeed = drinkSpeed;
        this.baseItem = baseItem;
        this.func_77625_d(1);
        this.func_77656_e(swigs);
        this.func_77655_b(name);
        this.setNoRepair();
    }

    public int func_77626_a(ItemStack p_77626_1_) {
        return this.drinkSpeed;
    }

    public EnumAction func_77661_b(ItemStack p_77661_1_) {
        return EnumAction.drink;
    }

    public ItemStack func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_) {
        p_77659_3_.func_71008_a(p_77659_1_, this.func_77626_a(p_77659_1_));
        return p_77659_1_;
    }

    public ItemStack func_77654_b(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            for (PotionEffect effect : this.getBrew(stack).getPotionEffects(stack)) {
                PotionEffect newEffect = new PotionEffect(effect.func_76456_a(), effect.func_76459_b(), effect.func_76458_c(), true);
                Potion potion = Potion.field_76425_a[newEffect.func_76456_a()];
                if (potion.func_76403_b()) {
                    potion.func_76402_a((EntityLivingBase)player, (EntityLivingBase)player, newEffect.func_76458_c(), 1.0);
                    continue;
                }
                player.func_70690_d(newEffect);
            }
            if (world.field_73012_v.nextBoolean()) {
                world.func_72956_a((Entity)player, "random.burp", 1.0f, 1.0f);
            }
            int swigs = this.getSwigsLeft(stack);
            if (!player.field_71075_bZ.field_75098_d) {
                if (swigs == 1) {
                    ItemStack copy = this.baseItem.func_77946_l();
                    if (!player.field_71071_by.func_70441_a(copy)) {
                        return this.baseItem.func_77946_l();
                    }
                    return null;
                }
                this.setSwigsLeft(stack, swigs - 1);
            }
        }
        return stack;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List list) {
        for (String s : BotaniaAPI.brewMap.keySet()) {
            ItemStack stack = new ItemStack(item);
            ItemBrewBase.setBrew(stack, s);
            list.add(stack);
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forName(par1IconRegister, this.texName + "0");
        this.icons = new IIcon[this.swigs];
        for (int i = 0; i < this.swigs; ++i) {
            this.icons[i] = IconHelper.forName(par1IconRegister, this.texName + "1_" + i);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return pass == 0 ? this.field_77791_bV : this.icons[Math.max(0, Math.min(this.icons.length - 1, this.swigs - this.getSwigsLeft(stack)))];
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            return 0xFFFFFF;
        }
        Color color = new Color(this.getBrew(stack).getColor(stack));
        int add = (int)(Math.sin((double)ClientTickHandler.ticksInGame * 0.1) * 16.0);
        int r = Math.max(0, Math.min(255, color.getRed() + add));
        int g = Math.max(0, Math.min(255, color.getGreen() + add));
        int b = Math.max(0, Math.min(255, color.getBlue() + add));
        return r << 16 | g << 8 | b;
    }

    public String func_77653_i(ItemStack stack) {
        return String.format(StatCollector.func_74838_a((String)(this.func_77657_g(stack) + ".name")), StatCollector.func_74838_a((String)this.getBrew(stack).getUnlocalizedName(stack)), EnumChatFormatting.BOLD + "" + this.getSwigsLeft(stack) + EnumChatFormatting.RESET);
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        Brew brew = this.getBrew(stack);
        for (PotionEffect effect : brew.getPotionEffects(stack)) {
            Potion potion = Potion.field_76425_a[effect.func_76456_a()];
            EnumChatFormatting format = potion.func_76398_f() ? EnumChatFormatting.RED : EnumChatFormatting.GRAY;
            list.add(format + StatCollector.func_74838_a((String)effect.func_76453_d()) + (effect.func_76458_c() == 0 ? "" : " " + StatCollector.func_74838_a((String)("botania.roman" + (effect.func_76458_c() + 1)))) + EnumChatFormatting.GRAY + (potion.func_76403_b() ? "" : " (" + Potion.func_76389_a((PotionEffect)effect) + ")"));
        }
    }

    @Override
    public Brew getBrew(ItemStack stack) {
        String key = ItemNBTHelper.getString(stack, TAG_BREW_KEY, "");
        return BotaniaAPI.getBrewFromKey(key);
    }

    public static void setBrew(ItemStack stack, Brew brew) {
        ItemBrewBase.setBrew(stack, (brew == null ? BotaniaAPI.fallbackBrew : brew).getKey());
    }

    public static void setBrew(ItemStack stack, String brew) {
        ItemNBTHelper.setString(stack, TAG_BREW_KEY, brew);
    }

    public int getSwigsLeft(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_SWIGS_LEFT, this.swigs);
    }

    public void setSwigsLeft(ItemStack stack, int swigs) {
        ItemNBTHelper.setInt(stack, TAG_SWIGS_LEFT, swigs);
    }

    @Override
    public Achievement getAchievementOnPickup(ItemStack stack, EntityPlayer player, EntityItem item) {
        return ModAchievements.brewPickup;
    }
}

