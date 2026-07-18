/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ISpecialArmor
 *  net.minecraftforge.common.ISpecialArmor$ArmorProperties
 *  thaumcraft.api.IRunicArmor
 */
package vazkii.botania.common.item.equipment.armor.manasteel;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import thaumcraft.api.IRunicArmor;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IPhantomInkable;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.client.model.armor.ModelArmorManasteel;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IRunicArmor")
public class ItemManasteelArmor
extends ItemArmor
implements ISpecialArmor,
IManaUsingItem,
IPhantomInkable,
IRunicArmor {
    private static final int MANA_PER_DAMAGE = 70;
    private static final String TAG_PHANTOM_INK = "phantomInk";
    protected ModelBiped[] models = null;
    public int type;
    static ItemStack[] armorset;

    public ItemManasteelArmor(int type, String name) {
        this(type, name, BotaniaAPI.manasteelArmorMaterial);
    }

    public ItemManasteelArmor(int type, String name, ItemArmor.ArmorMaterial mat) {
        super(mat, 0, type);
        this.type = type;
        this.func_77637_a(BotaniaCreativeTab.INSTANCE);
        this.func_77655_b(name);
    }

    public Item func_77655_b(String par1Str) {
        GameRegistry.registerItem((Item)this, (String)par1Str);
        return super.func_77655_b(par1Str);
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return super.func_77657_g(par1ItemStack).replaceAll("item.", "item.botania:");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = IconHelper.forItem(par1IconRegister, (Item)this);
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.func_76363_c()) {
            return new ISpecialArmor.ArmorProperties(0, 0.0, 0);
        }
        return new ISpecialArmor.ArmorProperties(0, (double)this.field_77879_b / 25.0, armor.func_77958_k() + 1 - armor.func_77960_j());
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return this.field_77879_b;
    }

    public void func_77663_a(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (player instanceof EntityPlayer) {
            this.onArmorTick(world, (EntityPlayer)player, stack);
        }
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (!world.field_72995_K && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExact(stack, player, 140, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ToolCommons.damageItem(stack, damage, entity, 70);
    }

    public final String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return this.hasPhantomInk(stack) ? "botania:textures/model/invisibleArmor.png" : this.getArmorTextureAfterInk(stack, slot);
    }

    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return ConfigHandler.enableArmorModels ? "botania:textures/model/manasteelNew.png" : (slot == 2 ? "botania:textures/model/manasteel1.png" : "botania:textures/model/manasteel0.png");
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        if (ConfigHandler.enableArmorModels) {
            ModelBiped model = this.getArmorModelForSlot(entityLiving, itemStack, armorSlot);
            if (model == null) {
                model = this.provideArmorModelForSlot(itemStack, armorSlot);
            }
            if (model != null) {
                return model;
            }
        }
        return super.getArmorModel(entityLiving, itemStack, armorSlot);
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, int slot) {
        if (this.models == null) {
            this.models = new ModelBiped[4];
        }
        return this.models[slot];
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped provideArmorModelForSlot(ItemStack stack, int slot) {
        this.models[slot] = new ModelArmorManasteel(slot);
        return this.models[slot];
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.func_77973_b() == ModItems.manaResource && par2ItemStack.func_77960_j() == 0 ? true : super.func_82789_a(par1ItemStack, par2ItemStack);
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        if (GuiScreen.func_146272_n()) {
            this.addInformationAfterShift(stack, player, list, adv);
        } else {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.shiftinfo"), list);
        }
    }

    public void addInformationAfterShift(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        this.addStringToTooltip(this.getArmorSetTitle(player), list);
        this.addArmorSetDescription(stack, list);
        ItemStack[] stacks = this.getArmorSetStacks();
        for (int i = 0; i < stacks.length; ++i) {
            this.addStringToTooltip((this.hasArmorSetItem(player, i) ? EnumChatFormatting.GREEN : "") + " - " + stacks[i].func_82833_r(), list);
        }
        if (this.hasPhantomInk(stack)) {
            this.addStringToTooltip(StatCollector.func_74838_a((String)"botaniamisc.hasPhantomInk"), list);
        }
    }

    public void addStringToTooltip(String s, List<String> tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.manasteelHelm), new ItemStack(ModItems.manasteelChest), new ItemStack(ModItems.manasteelLegs), new ItemStack(ModItems.manasteelBoots)};
        }
        return armorset;
    }

    public boolean hasArmorSet(EntityPlayer player) {
        return this.hasArmorSetItem(player, 0) && this.hasArmorSetItem(player, 1) && this.hasArmorSetItem(player, 2) && this.hasArmorSetItem(player, 3);
    }

    public boolean hasArmorSetItem(EntityPlayer player, int i) {
        ItemStack stack = player.field_71071_by.field_70460_b[3 - i];
        if (stack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                return stack.func_77973_b() == ModItems.manasteelHelm || stack.func_77973_b() == ModItems.manasteelHelmRevealing;
            }
            case 1: {
                return stack.func_77973_b() == ModItems.manasteelChest;
            }
            case 2: {
                return stack.func_77973_b() == ModItems.manasteelLegs;
            }
            case 3: {
                return stack.func_77973_b() == ModItems.manasteelBoots;
            }
        }
        return false;
    }

    public int getSetPiecesEquipped(EntityPlayer player) {
        int pieces = 0;
        for (int i = 0; i < 4; ++i) {
            if (!this.hasArmorSetItem(player, i)) continue;
            ++pieces;
        }
        return pieces;
    }

    public String getArmorSetName() {
        return StatCollector.func_74838_a((String)"botania.armorset.manasteel.name");
    }

    public String getArmorSetTitle(EntityPlayer player) {
        return StatCollector.func_74838_a((String)"botaniamisc.armorset") + " " + this.getArmorSetName() + " (" + this.getSetPiecesEquipped(player) + "/" + this.getArmorSetStacks().length + ")";
    }

    public void addArmorSetDescription(ItemStack stack, List<String> list) {
        this.addStringToTooltip(StatCollector.func_74838_a((String)"botania.armorset.manasteel.desc"), list);
    }

    @Override
    public boolean hasPhantomInk(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, TAG_PHANTOM_INK, false);
    }

    @Override
    public void setPhantomInk(ItemStack stack, boolean ink) {
        ItemNBTHelper.setBoolean(stack, TAG_PHANTOM_INK, ink);
    }

    @Optional.Method(modid="Thaumcraft")
    public int getRunicCharge(ItemStack itemstack) {
        return 0;
    }
}

