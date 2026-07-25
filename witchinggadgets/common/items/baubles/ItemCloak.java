/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.entities.IEldritchMob
 *  thaumcraft.api.nodes.IRevealer
 *  thaumcraft.common.items.armor.Hover
 *  travellersgear.api.IActiveAbility
 *  travellersgear.api.IEventGear
 *  travellersgear.api.ITravellersGear
 *  vazkii.botania.api.item.ICosmeticAttachable
 */
package witchinggadgets.common.items.baubles;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import thaumcraft.api.IGoggles;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.common.items.armor.Hover;
import travellersgear.api.IActiveAbility;
import travellersgear.api.IEventGear;
import travellersgear.api.ITravellersGear;
import vazkii.botania.api.item.ICosmeticAttachable;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.client.render.ModelCloak;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.util.Utilities;

@Optional.Interface(iface="vazkii.botania.api.item.ICosmeticAttachable", modid="Botania")
public class ItemCloak
extends Item
implements ITravellersGear,
IActiveAbility,
IEventGear,
ICosmeticAttachable {
    public static String[] subNames = new String[]{"standard", "spectral", "storage", "wolf", "raven"};
    int[] defaultColours = new int[0];
    IIcon iconRaven;
    IIcon iconWolf;

    public ItemCloak() {
        this.func_77627_a(true);
        this.func_77637_a(WitchingGadgets.tabWG);
    }

    public boolean func_77616_k(ItemStack stack) {
        return stack.field_77994_a == 1;
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("witchinggadgets:cloak");
        this.iconRaven = iconRegister.func_94245_a("witchinggadgets:cloak_raven");
        this.iconWolf = iconRegister.func_94245_a("witchinggadgets:cloak_wolf");
    }

    public IIcon func_77617_a(int meta) {
        if (meta == 3) {
            return this.iconWolf;
        }
        if (meta == 4) {
            return this.iconRaven;
        }
        return this.field_77791_bV;
    }

    public boolean hasColor(ItemStack stack) {
        return true;
    }

    public int func_82790_a(ItemStack stack, int pass) {
        return this.getColor(stack);
    }

    public int getColor(ItemStack stack) {
        if (stack == null) {
            return 0xFFFFFF;
        }
        int meta = stack.func_77960_j();
        if (meta == 0) {
            NBTTagCompound tag = stack.func_77978_p();
            if (tag == null) {
                return ClientUtilities.colour_CloakBlue;
            }
            NBTTagCompound tagDisplay = tag.func_74775_l("display");
            return tagDisplay == null ? ClientUtilities.colour_CloakBlue : (tagDisplay.func_74764_b("color") ? tagDisplay.func_74762_e("color") : ClientUtilities.colour_CloakBlue);
        }
        return meta == 1 ? Aspect.DARKNESS.getColor() : (meta == 2 ? Aspect.VOID.getColor() : 0xFFFFFF);
    }

    public void removeColor(ItemStack stack) {
        NBTTagCompound tagDisplay;
        if (stack == null) {
            return;
        }
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && (tagDisplay = tag.func_74775_l("display")).func_74764_b("color")) {
            tagDisplay.func_82580_o("color");
        }
    }

    public void setColour(ItemStack stack, int colour) {
        NBTTagCompound nbttagcompound = stack.func_77978_p();
        if (nbttagcompound == null) {
            nbttagcompound = new NBTTagCompound();
            stack.func_77982_d(nbttagcompound);
        }
        NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("display");
        if (!nbttagcompound.func_74764_b("display")) {
            nbttagcompound.func_74782_a("display", (NBTBase)nbttagcompound1);
        }
        nbttagcompound1.func_74768_a("color", colour);
    }

    public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer) {
        if (itemstack.func_77960_j() < subNames.length) {
            if (subNames[itemstack.func_77960_j()].equals("wolf")) {
                return "witchinggadgets:textures/models/cloakWolf.png";
            }
            if (subNames[itemstack.func_77960_j()].equals("raven")) {
                return "witchinggadgets:textures/models/cloakRaven.png";
            }
        }
        return "witchinggadgets:textures/models/cloak.png";
    }

    public int getMaxDamage(ItemStack stack) {
        return 0;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelCloak(this.getColor(itemStack));
    }

    public String func_77667_c(ItemStack stack) {
        return this.func_77658_a() + "." + subNames[stack.func_77960_j()];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (int i = 0; i < subNames.length; ++i) {
            if (i == 4 && !WGModCompat.loaded_Twilight) continue;
            itemList.add(new ItemStack(item, 1, i));
        }
    }

    public ItemStack[] getStoredItems(ItemStack item) {
        ItemStack[] stackList = new ItemStack[27];
        if (item.func_77942_o()) {
            NBTTagList inv = item.func_77978_p().func_150295_c("InternalInventory", 10);
            for (int i = 0; i < inv.func_74745_c(); ++i) {
                NBTTagCompound tag = inv.func_150305_b(i);
                int slot = tag.func_74771_c("Slot") & 0xFF;
                if (slot < 0 || slot >= stackList.length) continue;
                stackList[slot] = ItemStack.func_77949_a((NBTTagCompound)tag);
            }
        }
        return stackList;
    }

    public void setStoredItems(ItemStack item, ItemStack[] stackList) {
        NBTTagList inv = new NBTTagList();
        for (int i = 0; i < stackList.length; ++i) {
            if (stackList[i] == null) continue;
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74774_a("Slot", (byte)i);
            stackList[i].func_77955_b(tag);
            inv.func_74742_a((NBTBase)tag);
        }
        if (!item.func_77942_o()) {
            item.func_77982_d(new NBTTagCompound());
        }
        item.func_77978_p().func_74782_a("InternalInventory", (NBTBase)inv);
    }

    public void func_77624_a(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        ItemStack cosmetic;
        if (stack.func_77942_o() && stack.func_77978_p().func_74767_n("noGlide")) {
            list.add(StatCollector.func_74838_a((String)"wg.desc.noGlide"));
        }
        list.add(StatCollector.func_74837_a((String)("wg.desc.gearSlot.tg." + this.getSlot(stack)), (Object[])new Object[0]));
        if (Loader.isModLoaded((String)"Botania") && (cosmetic = this.getCosmeticItem(stack)) != null) {
            list.add(String.format(StatCollector.func_74838_a((String)"botaniamisc.hasCosmetic"), cosmetic.func_82833_r()).replaceAll("&", "\u00a7"));
        }
    }

    public int getSlot(ItemStack stack) {
        return 0;
    }

    public void onItemTicked(EntityPlayer player, ItemStack stack) {
        if (player.field_70173_aa < 1) {
            this.onItemUnequipped(player, stack);
            this.onItemEquipped(player, stack);
        }
        if (stack.func_77960_j() < subNames.length) {
            if (subNames[stack.func_77960_j()].equals("spectral") && !player.field_70170_p.field_72995_K && stack.func_77942_o() && stack.func_77978_p().func_74767_n("isSpectral") && player.field_70173_aa % 100 == 0 && !Utilities.consumeVisFromInventoryWithoutDiscount(player, new AspectList().add(Aspect.AIR, 1))) {
                stack.func_77978_p().func_74757_a("isSpectral", false);
            }
            if (subNames[stack.func_77960_j()].equals("raven") && !player.field_70122_E) {
                if (player.field_71075_bZ.field_75100_b || Hover.getHover((int)player.func_145782_y())) {
                    if (player.field_70701_bs > 0.0f) {
                        player.func_70060_a(0.0f, 1.0f, 0.05f);
                    }
                    player.field_70181_x *= 1.125;
                } else if (!(!(player.field_70181_x < 0.0) || stack.func_77942_o() && stack.func_77978_p().func_74767_n("noGlide"))) {
                    float mod = player.func_70093_af() ? 0.1f : 0.05f;
                    player.field_70181_x = player.field_70181_x * (player.func_70093_af() ? 0.75 : 0.5);
                    double x = Math.cos(Math.toRadians(player.field_70759_as + 90.0f)) * (double)mod;
                    double z = Math.sin(Math.toRadians(player.field_70759_as + 90.0f)) * (double)mod;
                    player.field_70159_w += x;
                    player.field_70179_y += z;
                }
                player.field_70143_R = 0.0f;
            }
            if (subNames[stack.func_77960_j()].equals("wolf") && stack.func_77942_o() && stack.func_77978_p().func_74764_b("wolfPotion")) {
                int amp = stack.func_77978_p().func_74762_e("wolfPotion");
                player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 60, amp));
                player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 60, amp));
                player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 60, amp));
                stack.func_77978_p().func_82580_o("wolfPotion");
                if (stack.func_77978_p().func_82582_d()) {
                    stack.func_77982_d(null);
                }
            }
        }
    }

    public void onItemEquipped(EntityPlayer player, ItemStack stack) {
    }

    public void onItemUnequipped(EntityPlayer player, ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74767_n("isSpectral")) {
            stack.func_77978_p().func_74757_a("isSpectral", false);
        }
    }

    public void onTravelGearTick(EntityPlayer player, ItemStack stack) {
        this.onItemTicked(player, stack);
    }

    public void onTravelGearEquip(EntityPlayer player, ItemStack stack) {
        this.onItemEquipped(player, stack);
    }

    public void onTravelGearUnequip(EntityPlayer player, ItemStack stack) {
        this.onItemUnequipped(player, stack);
    }

    public boolean canActivate(EntityPlayer player, ItemStack stack, boolean isInHand) {
        return !isInHand && stack.func_77960_j() != 0 && stack.func_77960_j() != 3;
    }

    public void activate(EntityPlayer player, ItemStack stack) {
        if (stack.func_77960_j() < subNames.length) {
            if (subNames[stack.func_77960_j()].equals("storage") && !player.field_70170_p.field_72995_K) {
                player.openGui((Object)WitchingGadgets.instance, ((Object)((Object)this)).equals(WGContent.ItemKama) ? 5 : 4, player.field_70170_p, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v));
            } else if (subNames[stack.func_77960_j()].equals("raven") && !player.field_70170_p.field_72995_K) {
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                }
                stack.func_77978_p().func_74757_a("noGlide", !stack.func_77978_p().func_74767_n("noGlide"));
            } else if (subNames[stack.func_77960_j()].equals("spectral") && !player.field_70170_p.field_72995_K && Utilities.consumeVisFromInventoryWithoutDiscount(player, new AspectList().add(Aspect.AIR, 1))) {
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                }
                stack.func_77978_p().func_74757_a("isSpectral", !stack.func_77978_p().func_74767_n("isSpectral"));
                if (stack.func_77978_p().func_74767_n("isSpectral")) {
                    for (EntityCreature e : player.field_70170_p.func_72872_a(EntityCreature.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 16.0), (double)(player.field_70163_u - 16.0), (double)(player.field_70161_v - 16.0), (double)(player.field_70165_t + 16.0), (double)(player.field_70163_u + 16.0), (double)(player.field_70161_v + 16.0)))) {
                        if (e == null || e instanceof IBossDisplayData || !player.equals((Object)e.func_70638_az())) continue;
                        Utilities.setAttackTarget((EntityLiving)e, null);
                    }
                }
            }
        }
    }

    public void onUserDamaged(LivingHurtEvent event, ItemStack stack) {
        if (!stack.equals(event.entityLiving.func_71124_b(0)) && stack.func_77960_j() == 3) {
            int amp = 1;
            if (event.ammount >= 8.0f) {
                ++amp;
            }
            if (event.ammount >= 12.0f) {
                ++amp;
            }
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            stack.func_77978_p().func_74768_a("wolfPotion", amp);
        }
    }

    public void onUserAttacking(AttackEntityEvent event, ItemStack stack) {
    }

    public void onUserJump(LivingEvent.LivingJumpEvent event, ItemStack stack) {
    }

    public void onUserFall(LivingFallEvent event, ItemStack stack) {
    }

    public void onUserTargeted(LivingSetAttackTargetEvent event, ItemStack stack) {
        if (stack.func_77942_o() && stack.func_77978_p().func_74767_n("isSpectral") && event.entityLiving instanceof EntityCreature) {
            boolean special;
            boolean goggles = event.entityLiving.func_71124_b(4) != null && (event.entityLiving.func_71124_b(4).func_77973_b() instanceof IRevealer || event.entityLiving.func_71124_b(4).func_77973_b() instanceof IGoggles);
            boolean bl = special = event.entityLiving instanceof IEldritchMob || event.entityLiving instanceof IBossDisplayData;
            if (!goggles && !special) {
                Utilities.setAttackTarget((EntityLiving)((EntityCreature)event.entityLiving), null);
            }
        }
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
        NBTTagCompound cosTag = cosmetic.func_77955_b(new NBTTagCompound());
        stack.func_77978_p().func_74782_a("botaniaCosmeticOverride", (NBTBase)cosTag);
    }
}

