/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.equipment.armor.manaweave;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.model.armor.ModelArmorManaweave;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;

public class ItemManaweaveArmor
extends ItemManasteelArmor
implements ICraftAchievement {
    IIcon iconChristmas;
    static ItemStack[] armorset;

    public ItemManaweaveArmor(int type, String name) {
        super(type, name, BotaniaAPI.manaweaveArmorMaterial);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
        this.models[slot] = new ModelArmorManaweave(slot);
        return this.models[slot];
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        super.func_94581_a(par1IconRegister);
        this.iconChristmas = IconHelper.forItem(par1IconRegister, (Item)this, "Holiday");
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? (ClientProxy.jingleTheBells ? "botania:textures/model/manaweaveNewHoliday.png" : "botania:textures/model/manaweaveNew.png") : (slot == 2 ? "botania:textures/model/manaweave1.png" : "botania:textures/model/manaweave0.png");
    }

    public IIcon func_77617_a(int dmg) {
        return ClientProxy.jingleTheBells ? this.iconChristmas : super.func_77617_a(dmg);
    }

    @SideOnly(value=Side.CLIENT)
    public String func_77667_c(ItemStack p_77667_1_) {
        String name = super.func_77667_c(p_77667_1_);
        if (ClientProxy.jingleTheBells) {
            name = name.replaceAll("manaweave", "santaweave");
        }
        return name;
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 22 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.manaweaveHelm), new ItemStack(ModItems.manaweaveChest), new ItemStack(ModItems.manaweaveLegs), new ItemStack(ModItems.manaweaveBoots)};
        }
        return armorset;
    }

    @Override
    public boolean hasArmorSetItem(EntityPlayer player, int i) {
        ItemStack stack = player.field_71071_by.field_70460_b[3 - i];
        if (stack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                return stack.func_77973_b() == ModItems.manaweaveHelm;
            }
            case 1: {
                return stack.func_77973_b() == ModItems.manaweaveChest;
            }
            case 2: {
                return stack.func_77973_b() == ModItems.manaweaveLegs;
            }
            case 3: {
                return stack.func_77973_b() == ModItems.manaweaveBoots;
            }
        }
        return false;
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.func_74838_a((String)"botania.armorset.manaweave.name");
    }

    @Override
    public void addInformationAfterShift(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        if (ClientProxy.jingleTheBells) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.santaweaveInfo"), list);
            this.addStringToTooltip("", list);
        }
        super.addInformationAfterShift(stack, player, list, adv);
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<String> list) {
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.manaweave.desc0"), list);
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.manaweave.desc1"), list);
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.manaweaveArmorCraft;
    }
}

