/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.equipment.armor.elementium;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IPixieSpawner;
import vazkii.botania.client.model.armor.ModelArmorElementium;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;

public abstract class ItemElementiumArmor
extends ItemManasteelArmor
implements IPixieSpawner {
    static ItemStack[] armorset;

    public ItemElementiumArmor(int type, String name) {
        super(type, name, BotaniaAPI.elementiumArmorMaterial);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
        this.models[slot] = new ModelArmorElementium(slot);
        return this.models[slot];
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? "botania:textures/model/elementiumNew.png" : (slot == 2 ? "botania:textures/model/elementium1.png" : "botania:textures/model/elementium0.png");
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 7 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.elementiumHelm), new ItemStack(ModItems.elementiumChest), new ItemStack(ModItems.elementiumLegs), new ItemStack(ModItems.elementiumBoots)};
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
                return stack.func_77973_b() == ModItems.elementiumHelm || stack.func_77973_b() == ModItems.elementiumHelmRevealing;
            }
            case 1: {
                return stack.func_77973_b() == ModItems.elementiumChest;
            }
            case 2: {
                return stack.func_77973_b() == ModItems.elementiumLegs;
            }
            case 3: {
                return stack.func_77973_b() == ModItems.elementiumBoots;
            }
        }
        return false;
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.func_74838_a((String)"botania.armorset.elementium.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<String> list) {
        super.addArmorSetDescription(stack, list);
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.elementium.desc"), list);
    }
}

