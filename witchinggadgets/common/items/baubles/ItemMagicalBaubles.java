/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  travellersgear.api.ITravellersGear
 *  vazkii.botania.api.item.ICosmeticAttachable
 */
package witchinggadgets.common.items.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import travellersgear.api.ITravellersGear;
import vazkii.botania.api.item.ICosmeticAttachable;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.render.ModelMagicalBaubles;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.util.Utilities;

@Optional.Interface(iface="vazkii.botania.api.item.ICosmeticAttachable", modid="Botania")
public class ItemMagicalBaubles
extends Item
implements IBauble,
ITravellersGear,
ICosmeticAttachable {
    public static String[] subNames = new String[]{"shouldersDoublejump", "shouldersKnockback", "vambraceStrength", "vambraceHaste", "titleCrimsonCult", "ringLuck", "ringSniper"};
    IIcon[] icons = new IIcon[subNames.length];
    public static HashSet<String> bowSpeedPlayers = new HashSet();

    public ItemMagicalBaubles() {
        this.field_77777_bU = 1;
        this.func_77637_a(WitchingGadgets.tabWG);
        this.func_77627_a(true);
    }

    public boolean func_77616_k(ItemStack stack) {
        return stack.field_77994_a == 1;
    }

    public int func_82790_a(ItemStack stack, int pass) {
        return super.func_82790_a(stack, pass);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        return super.func_77659_a(stack, world, player);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        ItemStack cosmetic;
        String type = this.getSlot(stack) > 0 ? "tg." + this.getSlot(stack) : "bauble." + this.getBaubleType(stack);
        list.add(StatCollector.func_74837_a((String)("wg.desc.gearSlot." + type), (Object[])new Object[0]));
        if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("title")) {
            list.add(StatCollector.func_74837_a((String)stack.func_77978_p().func_74779_i("title"), (Object[])new Object[0]));
        }
        if (Loader.isModLoaded((String)"Botania") && (cosmetic = this.getCosmeticItem(stack)) != null) {
            list.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.hasCosmetic"), cosmetic.func_82833_r()).replaceAll("&", "\u00a7"));
        }
    }

    public void func_94581_a(IIconRegister iconRegister) {
        for (int i = 0; i < subNames.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a("witchinggadgets:bauble_" + subNames[i]);
        }
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return "witchinggadgets:textures/models/magicalBaubles.png";
    }

    public ModelBiped getArmorModel(EntityLivingBase entity, ItemStack stack, int slot) {
        return ModelMagicalBaubles.getModel(entity, stack);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int metadata) {
        return this.icons[metadata];
    }

    public IIcon func_77618_c(int meta, int pass) {
        return this.func_77617_a(meta);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        return this.func_77618_c(stack.func_77960_j(), pass);
    }

    public int func_77647_b(int damageValue) {
        return damageValue;
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + subNames[itemstack.func_77960_j()];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < subNames.length; ++i) {
            if (i == 4) {
                itemList.add(ItemMagicalBaubles.getItemWithTitle(new ItemStack((Item)this, 1, i), "wg.title.crimsonCultist"));
                itemList.add(ItemMagicalBaubles.getItemWithTitle(new ItemStack((Item)this, 1, i), "wg.title.crimsonKnight"));
                itemList.add(ItemMagicalBaubles.getItemWithTitle(new ItemStack((Item)this, 1, i), "wg.title.crimsonPraetor"));
                continue;
            }
            itemList.add(new ItemStack((Item)this, 1, i));
        }
    }

    public static ItemStack getItemWithTitle(ItemStack stack, String title) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74778_a("title", title);
        return stack;
    }

    public boolean canEquip(ItemStack stack, EntityLivingBase living) {
        return true;
    }

    public boolean canUnequip(ItemStack stack, EntityLivingBase living) {
        return !subNames[stack.func_77960_j()].contains("binding");
    }

    public BaubleType getBaubleType(ItemStack stack) {
        return subNames[stack.func_77960_j()].startsWith("ring") ? BaubleType.RING : (subNames[stack.func_77960_j()].startsWith("belt") ? BaubleType.BELT : (subNames[stack.func_77960_j()].startsWith("necklace") ? BaubleType.AMULET : null));
    }

    public int getSlot(ItemStack stack) {
        return subNames[stack.func_77960_j()].startsWith("cloak") ? 0 : (subNames[stack.func_77960_j()].startsWith("shoulders") ? 1 : (subNames[stack.func_77960_j()].startsWith("vambrace") ? 2 : (subNames[stack.func_77960_j()].startsWith("title") ? 3 : -1)));
    }

    public void onWornTick(ItemStack stack, EntityLivingBase living) {
        this.onItemTicked(living, stack);
    }

    public void onTravelGearTick(EntityPlayer player, ItemStack stack) {
        this.onItemTicked((EntityLivingBase)player, stack);
    }

    public void onEquipped(ItemStack stack, EntityLivingBase living) {
        this.onItemEquipped(living, stack);
    }

    public void onTravelGearEquip(EntityPlayer player, ItemStack stack) {
        this.onItemEquipped((EntityLivingBase)player, stack);
    }

    public void onUnequipped(ItemStack stack, EntityLivingBase living) {
        this.onItemUnequipped(living, stack);
    }

    public void onTravelGearUnequip(EntityPlayer player, ItemStack stack) {
        this.onItemUnequipped((EntityLivingBase)player, stack);
    }

    public void onItemTicked(EntityLivingBase living, ItemStack stack) {
        if (living.field_70173_aa < 1) {
            this.onItemUnequipped(living, stack);
            this.onItemEquipped(living, stack);
        }
        if (stack.func_77960_j() == 3 && living.func_70617_f_()) {
            if (living.field_70123_F) {
                living.func_70091_d(0.0, 0.25, 0.0);
            } else if (!living.func_70093_af()) {
                living.func_70091_d(0.0, -0.1875, 0.0);
            }
        }
    }

    public void onItemEquipped(EntityLivingBase living, ItemStack stack) {
        if (stack.func_77960_j() == 1) {
            Utilities.addAttributeModToLiving(living, SharedMonsterAttributes.field_111266_c, new UUID(109406L, stack.func_77960_j()), "WGKnockbackResistance", 0.6, 0);
        }
        if (stack.func_77960_j() == 2) {
            Utilities.addAttributeModToLiving(living, SharedMonsterAttributes.field_111264_e, new UUID(109406L, stack.func_77960_j()), "WGStrengthBonus", 2.0, 0);
        }
    }

    public void onItemUnequipped(EntityLivingBase living, ItemStack stack) {
        if (stack.func_77960_j() == 1) {
            Utilities.removeAttributeModFromLiving(living, SharedMonsterAttributes.field_111266_c, new UUID(109406L, stack.func_77960_j()), "WGKnockbackResistance", 0.6, 0);
        }
        if (stack.func_77960_j() == 2) {
            Utilities.removeAttributeModFromLiving(living, SharedMonsterAttributes.field_111264_e, new UUID(109406L, stack.func_77960_j()), "WGStrengthBonus", 2.0, 0);
        }
    }

    public static ItemStack getInlaidGem(ItemStack ring) {
        return ItemInfusedGem.createGem(ItemInfusedGem.getAspect(ring), ItemInfusedGem.getCut(ring), false);
    }

    public static ItemStack setInlaidGem(ItemStack ring, ItemStack gem) {
        if (!ring.func_77942_o()) {
            ring.func_77982_d(new NBTTagCompound());
        }
        ring.func_77978_p().func_74774_a("GemCut", (byte)ItemInfusedGem.getCut(gem).ordinal());
        ring.func_77978_p().func_74778_a("Aspect", ItemInfusedGem.getAspect(gem).getTag());
        return ring;
    }

    @Optional.Method(modid="Botania")
    public ItemStack getCosmeticItem(ItemStack stack) {
        if (!stack.func_77942_o()) {
            return null;
        }
        ItemStack cosmetic = ItemStack.func_77949_a((NBTTagCompound)stack.func_77978_p().func_74775_l("botaniaCosmeticOverride"));
        return cosmetic;
    }

    @Optional.Method(modid="Botania")
    public void setCosmeticItem(ItemStack stack, ItemStack cosmetic) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (cosmetic == null) {
            stack.func_77978_p().func_82580_o("botaniaCosmeticOverride");
        } else {
            NBTTagCompound cosTag = cosmetic.func_77955_b(new NBTTagCompound());
            stack.func_77978_p().func_74782_a("botaniaCosmeticOverride", (NBTBase)cosTag);
        }
    }
}

