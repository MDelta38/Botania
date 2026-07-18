/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.equipment.armor.terrasteel;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.client.model.armor.ModelArmorTerrasteel;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;

public class ItemTerrasteelArmor
extends ItemManasteelArmor {
    static ItemStack[] armorset;

    public ItemTerrasteelArmor(int type, String name) {
        super(type, name, BotaniaAPI.terrasteelArmorMaterial);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
        this.models[slot] = new ModelArmorTerrasteel(slot);
        return this.models[slot];
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? "botania:textures/model/terrasteelNew.png" : (slot == 2 ? "botania:textures/model/terrasteel1.png" : "botania:textures/model/terrasteel0.png");
    }

    @Override
    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 4 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    public Multimap func_111205_h() {
        HashMultimap multimap = HashMultimap.create();
        UUID uuid = new UUID(this.func_77658_a().hashCode(), 0L);
        multimap.put((Object)SharedMonsterAttributes.field_111266_c.func_111108_a(), (Object)new AttributeModifier(uuid, "Terrasteel modifier " + this.type, (double)this.getArmorDisplay(null, new ItemStack((Item)this), this.type) / 20.0, 0));
        return multimap;
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.terrasteelHelm), new ItemStack(ModItems.terrasteelChest), new ItemStack(ModItems.terrasteelLegs), new ItemStack(ModItems.terrasteelBoots)};
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
                return stack.func_77973_b() == ModItems.terrasteelHelm || stack.func_77973_b() == ModItems.terrasteelHelmRevealing;
            }
            case 1: {
                return stack.func_77973_b() == ModItems.terrasteelChest;
            }
            case 2: {
                return stack.func_77973_b() == ModItems.terrasteelLegs;
            }
            case 3: {
                return stack.func_77973_b() == ModItems.terrasteelBoots;
            }
        }
        return false;
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.func_74838_a((String)"botania.armorset.terrasteel.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<String> list) {
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.terrasteel.desc0"), list);
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.terrasteel.desc1"), list);
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.terrasteel.desc2"), list);
    }
}

