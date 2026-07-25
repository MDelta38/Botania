/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.blocks.BlockVoidBramble;
import com.emoniph.witchery.client.model.ModelWitchesClothes;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ItemUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemWitchesClothes
extends ItemArmor {
    private static final int CHARGE_PER_PIECE = 2;
    @SideOnly(value=Side.CLIENT)
    private ModelWitchesClothes modelClothesChest;
    @SideOnly(value=Side.CLIENT)
    private ModelWitchesClothes modelNecroChest;
    @SideOnly(value=Side.CLIENT)
    private ModelWitchesClothes modelClothesLegs;
    private static final String BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME = "AbstractSteve";
    private static String noPlaceLikeHome = null;
    private static final double WILD_EFFECT_CHANCE = 0.01;

    public ItemWitchesClothes(int armorSlot) {
        super(ItemArmor.ArmorMaterial.CLOTH, 1, armorSlot);
        this.func_77637_a(WitcheryCreativeTab.INSTANCE);
    }

    public Item func_77655_b(String itemName) {
        ItemUtil.registerItem((Item)this, itemName);
        return super.func_77655_b(itemName);
    }

    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (stack != null && (stack.func_77973_b() == Witchery.Items.WITCH_HAT || stack.func_77973_b() == Witchery.Items.WITCH_ROBES || stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES || stack.func_77973_b() == Witchery.Items.ICY_SLIPPERS || stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS || stack.func_77973_b() == Witchery.Items.SEEPING_SHOES || stack.func_77973_b() == Witchery.Items.BABAS_HAT)) {
            return "witchery:textures/entities/witchclothes" + (type == null ? "" : "_overlay") + ".png";
        }
        if (stack != null && stack.func_77973_b() == Witchery.Items.BITING_BELT) {
            return "witchery:textures/entities/witchclothes_legs" + (type == null ? "" : "_overlay") + ".png";
        }
        if (stack != null && stack.func_77973_b() == Witchery.Items.BARK_BELT) {
            return "witchery:textures/entities/witchclothes" + (type == null ? "2_legs" : "_legs_overlay") + ".png";
        }
        return null;
    }

    public int getMaxChargeLevel(EntityLivingBase entity) {
        int level = 0;
        for (int i = 1; i <= 4; ++i) {
            ItemStack stack = entity.func_71124_b(i);
            if (stack == null || !(stack.func_77973_b() instanceof ItemWitchesClothes)) continue;
            level += 2;
        }
        return level;
    }

    public void setChargeLevel(ItemStack stack, int level) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        NBTTagCompound nbtRoot = stack.func_77978_p();
        nbtRoot.func_74768_a("WITCBarkPieces", level);
    }

    public int getChargeLevel(ItemStack stack) {
        NBTTagCompound nbtRoot;
        if (stack.func_77942_o() && (nbtRoot = stack.func_77978_p()).func_74764_b("WITCBarkPieces")) {
            return nbtRoot.func_74762_e("WITCBarkPieces");
        }
        return 0;
    }

    public boolean func_82816_b_(ItemStack stack) {
        return stack == null || stack.func_77973_b() != Witchery.Items.BABAS_HAT;
    }

    public int func_82814_b(ItemStack stack) {
        if (!this.func_82816_b_(stack)) {
            return super.func_82814_b(stack);
        }
        if (stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS) {
            return 0xDD0000;
        }
        int color = super.func_82814_b(stack);
        if (color == 10511680) {
            color = this == Witchery.Items.ICY_SLIPPERS ? 7842303 : (this == Witchery.Items.SEEPING_SHOES ? 0x226633 : (this == Witchery.Items.BARK_BELT ? 6968628 : 2628115));
        }
        return color;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int par2) {
        return super.func_82790_a(stack, par2);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int par1, int par2) {
        return this.func_77617_a(par1);
    }

    public boolean isHatWorn(EntityPlayer player) {
        return player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() == this;
    }

    public boolean isRobeWorn(EntityPlayer player) {
        return player.field_71071_by.func_70440_f(2) != null && player.field_71071_by.func_70440_f(2).func_77973_b() == this;
    }

    public boolean isBeltWorn(EntityPlayer player) {
        return player.field_71071_by.func_70440_f(1) != null && player.field_71071_by.func_70440_f(1).func_77973_b() == this;
    }

    @SideOnly(value=Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, int armorSlot) {
        int type;
        if (this.modelClothesChest == null) {
            this.modelClothesChest = new ModelWitchesClothes(0.61f, false);
        }
        if (this.modelNecroChest == null) {
            this.modelNecroChest = new ModelWitchesClothes(0.61f, true);
        }
        if (this.modelClothesLegs == null) {
            this.modelClothesLegs = new ModelWitchesClothes(0.45f, false);
        }
        ModelWitchesClothes armorModel = null;
        if (stack != null && stack.func_77973_b() instanceof ItemWitchesClothes && (armorModel = (type = ((ItemArmor)stack.func_77973_b()).field_77881_a) == 1 || type == 3 ? (stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES ? this.modelNecroChest : this.modelClothesChest) : this.modelClothesLegs) != null) {
            boolean isVisible = true;
            if (entityLiving != null && entityLiving.func_82150_aj()) {
                String entityTypeName = entityLiving.getClass().getSimpleName();
                isVisible = entityTypeName == null || entityTypeName.isEmpty() || entityTypeName.equals(BIBLIOCRAFT_ARMOR_STAND_ENTITY_NAME);
            }
            armorModel.field_78116_c.field_78806_j = isVisible && armorSlot == 0;
            armorModel.field_78114_d.field_78806_j = isVisible && armorSlot == 0;
            armorModel.field_78115_e.field_78806_j = isVisible && (armorSlot == 1 || armorSlot == 2);
            armorModel.field_78112_f.field_78806_j = isVisible && armorSlot == 1;
            armorModel.field_78113_g.field_78806_j = isVisible && armorSlot == 1;
            armorModel.field_78123_h.field_78806_j = isVisible && armorSlot == 3;
            armorModel.field_78124_i.field_78806_j = isVisible && armorSlot == 3;
            armorModel.field_78117_n = entityLiving.func_70093_af();
            armorModel.field_78093_q = entityLiving.func_70115_ae();
            armorModel.field_78091_s = entityLiving.func_70631_g_();
            ItemStack heldStack = entityLiving.func_71124_b(0);
            armorModel.field_78120_m = heldStack != null ? 1 : 0;
            armorModel.field_78118_o = false;
            if (entityLiving instanceof EntityPlayer && heldStack != null && ((EntityPlayer)entityLiving).func_71057_bx() > 0) {
                EnumAction enumaction = heldStack.func_77975_n();
                if (enumaction == EnumAction.block) {
                    armorModel.field_78120_m = 3;
                }
                armorModel.field_78118_o = enumaction == EnumAction.bow;
            }
            return armorModel;
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack stack) {
        if (stack == null) {
            return EnumRarity.common;
        }
        if (stack.func_77973_b() == Witchery.Items.BABAS_HAT) {
            return EnumRarity.epic;
        }
        if (stack.func_77973_b() == Witchery.Items.BARK_BELT) {
            return EnumRarity.rare;
        }
        return EnumRarity.uncommon;
    }

    public String func_77653_i(ItemStack stack) {
        String baseName = super.func_77653_i(stack);
        return baseName;
    }

    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
        String s2;
        String s1;
        int potion;
        List effects;
        String localText = Witchery.resource(this.func_77658_a() + ".tip");
        if (localText != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
        if (stack != null && stack.func_77942_o() && stack.func_77978_p().func_74764_b("WITCPotion") && (effects = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion"))) != null && !effects.isEmpty()) {
            PotionEffect effect = (PotionEffect)effects.get(0);
            s1 = effect.func_76453_d();
            s1 = s1 + ".postfix";
            s2 = "\u00a76" + StatCollector.func_74838_a((String)s1).trim() + "\u00a7r";
            if (effect.func_76458_c() > 0) {
                s2 = s2 + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
            }
            if (effect.func_76459_b() > 20) {
                s2 = s2 + " [" + Potion.func_76389_a((PotionEffect)effect) + "]";
            }
            list.add(s2);
        }
        if (stack != null && stack.func_77942_o() && stack.func_77978_p().func_74764_b("WITCPotion2") && (effects = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion2"))) != null && !effects.isEmpty()) {
            PotionEffect effect = (PotionEffect)effects.get(0);
            s1 = effect.func_76453_d();
            s1 = s1 + ".postfix";
            s2 = "\u00a76" + StatCollector.func_74838_a((String)s1).trim() + "\u00a7r";
            if (effect.func_76458_c() > 0) {
                s2 = s2 + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
            }
            if (effect.func_76459_b() > 20) {
                s2 = s2 + " [" + Potion.func_76389_a((PotionEffect)effect) + "]";
            }
            list.add(s2);
        }
    }

    public boolean trySayTheresNoPlaceLikeHome(EntityPlayer player, String message) {
        NBTTagCompound nbtPlayer;
        ItemStack stack;
        if (player == null || player.field_70170_p.field_72995_K) {
            return false;
        }
        if (noPlaceLikeHome == null) {
            noPlaceLikeHome = Witchery.resource("witchery.rite.noplacelikehome").toLowerCase().replace("'", "");
        }
        if (message.toLowerCase().replace("'", "").startsWith(noPlaceLikeHome) && (stack = player.func_71124_b(1)) != null && stack.func_77973_b() == Witchery.Items.RUBY_SLIPPERS && player.field_71093_bK != Config.instance().dimensionDreamID && (nbtPlayer = Infusion.getNBT((Entity)player)) != null) {
            int R = 3;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 3.0), (double)(player.field_70163_u - 3.0), (double)(player.field_70161_v - 3.0), (double)(player.field_70165_t + 3.0), (double)(player.field_70163_u + 3.0), (double)(player.field_70161_v + 3.0));
            List list = player.field_70170_p.func_72872_a(EntityItem.class, bounds);
            for (Object obj : list) {
                int waystoneDimension;
                EntityItem waystoneEntity = (EntityItem)obj;
                ItemStack waystoneStack = waystoneEntity.func_92059_d();
                if (waystoneStack == null || !Witchery.Items.GENERIC.itemWaystoneBound.isMatch(waystoneStack)) continue;
                if (nbtPlayer.func_74764_b("WITCLastRubySlipperWayTime")) {
                    long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperWayTime");
                    long currentTime = MinecraftServer.func_130071_aq();
                    long timeSince = currentTime - lastTime;
                    long COOLDOWN = 60000L;
                    if (timeSince < 60000L) {
                        int cooldownRemaining = Math.max(1, (int)(60000L - timeSince) / 60000);
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.slippersoncooldown", Integer.valueOf(cooldownRemaining).toString());
                        return true;
                    }
                }
                if (Infusion.aquireEnergy(player.field_70170_p, player, nbtPlayer, (waystoneDimension = ItemGeneral.getWaystoneDimension(waystoneStack)) != player.field_71093_bK ? 80 : 40, true)) {
                    waystoneEntity.func_70106_y();
                    nbtPlayer.func_74772_a("WITCLastRubySlipperWayTime", MinecraftServer.func_130071_aq());
                    if (player.field_70170_p.field_73012_v.nextDouble() < 0.01) {
                        BlockVoidBramble.teleportRandomly(player.field_70170_p, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), (Entity)player, 500);
                    } else {
                        Witchery.Items.GENERIC.teleportToLocation(player.field_70170_p, waystoneStack, (Entity)player, 2, true);
                    }
                }
                return true;
            }
            if (nbtPlayer.func_74764_b("WITCLastRubySlipperTime")) {
                long lastTime = nbtPlayer.func_74763_f("WITCLastRubySlipperTime");
                long currentTime = MinecraftServer.func_130071_aq();
                long timeSince = currentTime - lastTime;
                long COOLDOWN = 1800000L;
                if (timeSince < 1800000L && !player.field_71075_bZ.field_75098_d) {
                    int cooldownRemaining = Math.max(1, (int)(1800000L - timeSince) / 60000);
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.slippersoncooldown", Integer.valueOf(cooldownRemaining).toString());
                    return true;
                }
            }
            ChunkCoordinates coords = player.getBedLocation(player.field_71093_bK);
            int dimension = player.field_71093_bK;
            World world = player.field_70170_p;
            if (coords == null) {
                coords = player.getBedLocation(0);
                dimension = 0;
                world = MinecraftServer.func_71276_C().func_71218_a(0);
                if (coords == null) {
                    coords = world.func_72861_E();
                    while (world.func_147439_a(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c).func_149721_r() && coords.field_71572_b < 255) {
                        ++coords.field_71572_b;
                    }
                }
            }
            if (coords != null) {
                nbtPlayer.func_74772_a("WITCLastRubySlipperTime", MinecraftServer.func_130071_aq());
                coords = Blocks.field_150324_C.getBedSpawnPosition((IBlockAccess)world, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, null);
                if (coords != null) {
                    if (Infusion.aquireEnergy(player.field_70170_p, player, nbtPlayer, dimension != player.field_71093_bK ? 120 : 80, true)) {
                        ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                        ItemGeneral.teleportToLocation(player.field_70170_p, coords.field_71574_a, coords.field_71572_b + 1, coords.field_71573_c, dimension, (Entity)player, true);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}

