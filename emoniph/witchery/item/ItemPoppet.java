/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockPoppetShelf;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class ItemPoppet
extends ItemBase {
    private final ArrayList<PoppetType> poppetTypes = new ArrayList();
    public final PoppetType unboundPoppet = PoppetType.access$100(new PoppetType(0, "poppet", "Poppet", 0), this.poppetTypes);
    public final PoppetType earthPoppet = PoppetType.access$100(new PoppetType(1, "protectEarth", "Earth Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType waterPoppet = PoppetType.access$100(new PoppetType(2, "protectWater", "Water Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType firePoppet = PoppetType.access$100(new PoppetType(3, "protectFire", "Fire Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType foodPoppet = PoppetType.access$100(new PoppetType(4, "protectStarvation", "Hunger Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType toolPoppet = PoppetType.access$100(new PoppetType(5, "protectTool", "Tool Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType deathPoppet = PoppetType.access$100(new PoppetType(6, "protectDeath", "Death Protection Poppet", 2).setDestroyOnUse(true), this.poppetTypes);
    public final PoppetType antiVoodooPoppet = PoppetType.access$100(new PoppetType(7, "protectVoodoo", "Voodoo Protection Poppet"), this.poppetTypes);
    public final PoppetType voodooPoppet = PoppetType.access$100(new PoppetType(8, "voodoo", "Voodoo Poppet"), this.poppetTypes);
    public final PoppetType vampiricPoppet = PoppetType.access$100(new PoppetType(9, "vampiric", "Vampiric Poppet", 2), this.poppetTypes);
    public final PoppetType poppetProtectionPoppet = PoppetType.access$100(new PoppetType(10, "protectPoppet", "Poppet Protection", 2), this.poppetTypes);
    public final PoppetType armorPoppet = PoppetType.access$100(new PoppetType(11, "protectArmor", "Armor Protection Poppet").setDestroyOnUse(true), this.poppetTypes);
    private static final String KEY_DAMAGE = "WITCDamage";
    private static final int MAX_DAMAGE = 1000;
    private static final float AUTO_REPAIR_THRESHOLD = 0.1f;

    public ItemPoppet() {
        this.setNoRepair();
        this.func_77625_d(1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.values()[this.poppetTypes.get(itemstack.func_77960_j()).rarity];
    }

    public String func_77667_c(ItemStack itemStack) {
        int damage = itemStack.func_77960_j();
        assert (damage >= 0 && damage < this.poppetTypes.size()) : "damage value is too large";
        if (damage >= 0 && damage < this.poppetTypes.size()) {
            return this.poppetTypes.get(damage).getUnlocalizedName();
        }
        return "";
    }

    public String func_77653_i(ItemStack itemstack) {
        if (this.vampiricPoppet.isMatch(itemstack)) {
            String sourceID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, 1);
            String targetID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, 2);
            String localizedName = super.func_77653_i(itemstack);
            if (!sourceID.isEmpty() && !targetID.isEmpty()) {
                return String.format("%s (%s -> %s)", localizedName, sourceID, targetID);
            }
            if (!sourceID.isEmpty()) {
                return String.format("%s (%s -> ??)", localizedName, sourceID);
            }
            if (!targetID.isEmpty()) {
                return String.format("%s (?? -> %s)", localizedName, targetID);
            }
            return localizedName;
        }
        String entityID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(itemstack, 1);
        String localizedName = super.func_77653_i(itemstack);
        return !entityID.isEmpty() ? String.format("%s (%s)", localizedName, entityID) : localizedName;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean advTooltips) {
        if (this.vampiricPoppet.isMatch(stack)) {
            String sourceID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1);
            String targetID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 2);
            String localizedName = super.func_77653_i(stack);
            if (!sourceID.isEmpty() && !targetID.isEmpty()) {
                list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), String.format("%s -> %s", sourceID, targetID)));
            } else if (!sourceID.isEmpty()) {
                list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), String.format("%s -> ??", sourceID)));
            } else if (!targetID.isEmpty()) {
                list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), String.format("?? -> %s", targetID)));
            } else {
                list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
            }
        } else {
            String entityID = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1);
            String localizedName = super.func_77653_i(stack);
            if (entityID != null && !entityID.isEmpty()) {
                list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), entityID));
            } else {
                list.add(Witchery.resource("item.witcheryTaglockKit.unbound"));
            }
        }
    }

    public void func_94581_a(IIconRegister iconRegister) {
        for (PoppetType poppetType : this.poppetTypes) {
            poppetType.registerIcon(iconRegister, this);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int damage) {
        if (damage < 0 || damage >= this.poppetTypes.size()) {
            damage = 0;
        }
        return this.poppetTypes.get(damage).icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (PoppetType poppetType : this.poppetTypes) {
            itemList.add(new ItemStack(item, 1, poppetType.damageValue));
        }
    }

    public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
        this.ensureNBT(stack);
        super.func_77622_d(stack, world, player);
    }

    public boolean func_77645_m() {
        return true;
    }

    public boolean isDamaged(ItemStack stack) {
        this.ensureNBT(stack);
        return this.getDamageNBT(stack) > 0;
    }

    public void setDamage(ItemStack stack, int damage) {
    }

    public int getDisplayDamage(ItemStack stack) {
        this.ensureNBT(stack);
        return this.getDamageNBT(stack);
    }

    public int func_77612_l() {
        return 1000;
    }

    private int getDamageNBT(ItemStack stack) {
        this.ensureNBT(stack);
        return stack.func_77978_p().func_74762_e(KEY_DAMAGE);
    }

    private void setDamageNBT(IInventory inventory, ItemStack stack, int damage) {
        this.ensureNBT(stack);
        damage = Math.min(damage, 1000);
        stack.func_77978_p().func_74768_a(KEY_DAMAGE, damage);
        if (damage == 1000) {
            stack.field_77994_a = 0;
            if (inventory != null) {
                for (int i = 0; i < inventory.func_70302_i_(); ++i) {
                    if (inventory.func_70301_a(i) != stack) continue;
                    inventory.func_70299_a(i, null);
                    break;
                }
            }
        }
    }

    private void ensureNBT(ItemStack stack) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        if (!stack.func_77978_p().func_74764_b(KEY_DAMAGE)) {
            stack.func_77978_p().func_74768_a(KEY_DAMAGE, 0);
        }
    }

    public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
        EntityLivingBase boundEntity;
        if (!world.field_72995_K && this.voodooPoppet.isMatch(stack) && entity.func_70055_a(Material.field_151586_h) && entity.func_70086_ai() <= 0 && (boundEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, entity, stack, 1)) != null && boundEntity.func_70089_S() && !this.voodooProtectionActivated(entity instanceof EntityPlayer ? (EntityPlayer)entity : null, stack, boundEntity, true, false)) {
            boolean damageDisabled;
            if (entity instanceof EntityPlayer) {
                EntityWitchHunter.blackMagicPerformed((EntityPlayer)entity);
            }
            boolean bl = damageDisabled = boundEntity instanceof EntityPlayer && ((EntityPlayer)boundEntity).field_71075_bZ.field_75102_a;
            if (!(ItemHunterClothes.isMagicalProtectionActive(boundEntity) || boundEntity.func_70648_aU() || boundEntity.func_82165_m(Potion.field_76427_o.field_76415_H) || damageDisabled)) {
                for (int i = 0; i < 8; ++i) {
                    float f = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
                    float f1 = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
                    float f2 = world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat();
                    world.func_72869_a("bubble", boundEntity.field_70165_t + (double)f, boundEntity.field_70163_u + (double)f1, boundEntity.field_70161_v + (double)f2, boundEntity.field_70159_w, boundEntity.field_70181_x, boundEntity.field_70179_y);
                }
                boundEntity.func_70097_a(DamageSource.field_76369_e, 1.0f);
            }
            boundEntity.func_70066_B();
        }
        super.func_77663_a(stack, world, entity, par4, par5);
    }

    public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player) {
        if (this.voodooPoppet.isMatch(itemstack)) {
            player.func_71008_a(itemstack, this.func_77626_a(itemstack));
        }
        return super.func_77659_a(itemstack, world, player);
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 80;
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int ticks) {
        if (!world.field_72995_K) {
            EntityLivingBase entity;
            if (this.voodooPoppet.isMatch(itemstack) && (entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, (Entity)player, itemstack, 1)) != null) {
                EntityWitchHunter.blackMagicPerformed(player);
                MovingObjectPosition hitObject = this.func_77621_a(world, player, true);
                if (hitObject != null && hitObject.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    Material hitMaterial = world.func_147439_a(hitObject.field_72311_b, hitObject.field_72312_c, hitObject.field_72309_d).func_149688_o();
                    Block hitBlock = world.func_147439_a(hitObject.field_72311_b, hitObject.field_72312_c, hitObject.field_72309_d);
                    if (hitMaterial == Material.field_151587_i) {
                        if (!this.voodooProtectionActivated(player, itemstack, entity, true, false) && !ItemHunterClothes.isMagicalProtectionActive((EntityLivingBase)player)) {
                            entity.func_70015_d(10);
                        }
                        this.setDamageNBT((IInventory)player.field_71071_by, itemstack, 1000);
                        world.func_72956_a((Entity)player, "random.fizz", 0.4f, 2.0f + world.field_73012_v.nextFloat() * 0.4f);
                        return;
                    }
                }
                if (player.func_70093_af()) {
                    if (player.field_71075_bZ.field_75098_d || Witchery.Items.GENERIC.itemBoneNeedle.isItemInInventory(player.field_71071_by)) {
                        PoppetDamageSource damageSource = new PoppetDamageSource(DamageSource.field_76376_m, (Entity)player);
                        if (!this.voodooProtectionActivated(player, itemstack, entity, true, false)) {
                            entity.func_70097_a((DamageSource)damageSource, 0.5f);
                            if (!player.field_71075_bZ.field_75098_d) {
                                Witchery.Items.GENERIC.itemBoneNeedle.consumeItemFromInventory(player.field_71071_by);
                                this.setDamageNBT((IInventory)player.field_71071_by, itemstack, this.getDamageNBT(itemstack) + 10);
                            }
                        }
                    }
                    return;
                }
                if (!this.voodooProtectionActivated(player, itemstack, entity, true, false) && !ItemHunterClothes.isMagicalProtectionActive((EntityLivingBase)player)) {
                    Vec3 look = player.func_70040_Z();
                    float scaling = (this.func_77626_a(itemstack) - ticks) / 20;
                    double motionX = look.field_72450_a * 0.9 * (double)scaling;
                    double motionY = look.field_72448_b * 0.3 * (double)scaling;
                    double motionZ = look.field_72449_c * 0.9 * (double)scaling;
                    if (entity instanceof EntityPlayer) {
                        EntityPlayer targetPlayer = (EntityPlayer)entity;
                        Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(motionX, motionY, motionZ), targetPlayer);
                    } else {
                        entity.field_70159_w = motionX;
                        entity.field_70181_x = motionY;
                        entity.field_70179_y = motionZ;
                    }
                    this.setDamageNBT((IInventory)player.field_71071_by, itemstack, this.getDamageNBT(itemstack) + 10);
                }
                return;
            }
            super.func_77615_a(itemstack, world, player, ticks);
        }
    }

    public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, int strength) {
        if (!(strength <= 1 || targetEntity instanceof EntityPlayer && InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo((EntityPlayer)targetEntity))) {
            for (int i = 1; i <= strength; ++i) {
                boolean allowLightning;
                boolean bl = allowLightning = i == strength;
                if (this.voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, allowLightning, false)) continue;
                return false;
            }
        } else {
            return this.voodooProtectionActivated(attackingEntity, voodooStack, targetEntity, true, false);
        }
        return true;
    }

    public boolean voodooProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning, boolean onlyBoosted) {
        EntityPlayer targetPlayer;
        ItemStack defenseStack;
        int ITEM_DAMAGE = 350;
        if (targetEntity instanceof EntityPlayer && (defenseStack = ItemPoppet.findBoundPoppetInWorld(this.antiVoodooPoppet, targetPlayer = (EntityPlayer)targetEntity, 350, false, onlyBoosted)) != null && !targetPlayer.field_70170_p.field_72995_K) {
            if (attackingEntity != null && voodooStack != null) {
                this.setDamageNBT((IInventory)attackingEntity.field_71071_by, voodooStack, this.getDamageNBT(voodooStack) + 350);
            }
            if (attackingEntity != null && allowLightning) {
                EntityLightningBolt lightning = new EntityLightningBolt(attackingEntity.field_70170_p, attackingEntity.field_70165_t, attackingEntity.field_70163_u, attackingEntity.field_70161_v);
                attackingEntity.field_70170_p.func_72942_c((Entity)lightning);
            }
            return true;
        }
        return false;
    }

    public boolean poppetProtectionActivated(EntityPlayer attackingEntity, ItemStack voodooStack, EntityLivingBase targetEntity, boolean allowLightning) {
        EntityPlayer targetPlayer;
        ItemStack defenseStack;
        int ITEM_DAMAGE = 350;
        if (targetEntity instanceof EntityPlayer && (defenseStack = ItemPoppet.findBoundPoppetInWorld(this.poppetProtectionPoppet, targetPlayer = (EntityPlayer)targetEntity, 350)) != null && !attackingEntity.field_70170_p.field_72995_K) {
            if (voodooStack != null) {
                this.setDamageNBT((IInventory)attackingEntity.field_71071_by, voodooStack, this.getDamageNBT(voodooStack) + 350);
            }
            if (attackingEntity != null && allowLightning) {
                EntityLightningBolt lightning = new EntityLightningBolt(attackingEntity.field_70170_p, attackingEntity.field_70165_t, attackingEntity.field_70163_u, attackingEntity.field_70161_v);
                attackingEntity.field_70170_p.func_72942_c((Entity)lightning);
            }
            return true;
        }
        return false;
    }

    public void destroyAntiVoodooPoppets(EntityPlayer attackingEntity, EntityLivingBase targetEntity, int poppetsToDestroy) {
        int ITEM_DAMAGE = 1000;
        int MAX = 1000;
        if (targetEntity instanceof EntityPlayer) {
            EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
            for (int i = 0; i < poppetsToDestroy; ++i) {
                ItemStack defenseStack = ItemPoppet.findBoundPoppetInWorld(this.antiVoodooPoppet, targetPlayer, 1000);
                if (defenseStack != null) continue;
                return;
            }
        }
    }

    public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage) {
        return ItemPoppet.findBoundPoppetInWorld(poppetType, player, foundItemDamage, false, false);
    }

    public static ItemStack findBoundPoppetInWorld(PoppetType poppetType, EntityPlayer player, int foundItemDamage, boolean allIndices, boolean onlyBoosted) {
        if (ItemHunterClothes.isFullSetWorn((EntityLivingBase)player, false)) {
            return null;
        }
        int damageValue = poppetType.damageValue;
        ItemStack poppetStack = ItemPoppet.findBoundPoppetInInventory(Witchery.Items.POPPET, damageValue, player, (IInventory)player.field_71071_by, foundItemDamage, allIndices, onlyBoosted);
        if (poppetStack != null) {
            return poppetStack;
        }
        if (!player.field_70170_p.field_72995_K && !onlyBoosted) {
            MinecraftServer server = MinecraftServer.func_71276_C();
            for (WorldServer world : server.field_71305_c) {
                if (Config.instance().restrictPoppetShelvesToVanillaAndSpiritDimensions && world.field_73011_w.field_76574_g != 0 && world.field_73011_w.field_76574_g != -1 && world.field_73011_w.field_76574_g != 1 && world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) continue;
                for (Object obj : world.field_147482_g) {
                    if (!(obj instanceof BlockPoppetShelf.TileEntityPoppetShelf) || (poppetStack = ItemPoppet.findBoundPoppetInInventory(Witchery.Items.POPPET, damageValue, player, (IInventory)obj, foundItemDamage, allIndices, false)) == null) continue;
                    return poppetStack;
                }
            }
        }
        return null;
    }

    private static ItemStack findBoundPoppetInInventory(Item item, int damage, EntityPlayer player, IInventory inventory, int foundItemDamage, boolean allIndices, boolean onlyBoosted) {
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            ItemStack itemstack = inventory.func_70301_a(i);
            if (itemstack == null || itemstack.func_77973_b() != item || itemstack.func_77960_j() != damage || !Witchery.Items.TAGLOCK_KIT.containsTaglockForEntity(itemstack, (Entity)player, 1) || allIndices && !Witchery.Items.TAGLOCK_KIT.isTaglockPresent(itemstack, 2)) continue;
            if (onlyBoosted) {
                if (InfusedSpiritEffect.POPPET_ENHANCEMENT.isNearTo(player)) {
                    Witchery.Items.POPPET.poppetTypes.get(damage).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
                    return itemstack;
                }
                return null;
            }
            Witchery.Items.POPPET.poppetTypes.get(damage).applyDamageOnUse(inventory, i, itemstack, foundItemDamage);
            return itemstack;
        }
        return null;
    }

    public void addDamageToPoppet(ItemStack sourcePoppet, ItemStack destPoppet) {
        this.setDamageNBT(null, destPoppet, this.getDamageNBT(sourcePoppet));
    }

    public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal) {
        this.cancelEventIfPoppetFound(player, poppetType, event, heal, false);
    }

    public void cancelEventIfPoppetFound(EntityPlayer player, PoppetType poppetType, LivingHurtEvent event, boolean heal, boolean onlyHandheld) {
        ItemStack stack = ItemPoppet.findBoundPoppetInWorld(poppetType, player, 1000, false, onlyHandheld);
        if (stack != null) {
            event.setCanceled(true);
            if (heal && player.func_110143_aJ() < 10.0f) {
                player.func_70606_j(10.0f);
            }
            SoundEffect.RANDOM_ORB.playAtPlayer(player.field_70170_p, player);
        }
    }

    public void checkForArmorProtection(EntityPlayer player) {
        for (int i = 0; i < player.field_71071_by.field_70460_b.length; ++i) {
            int maxDamage;
            int repairThreshold;
            int itemDamage;
            ItemStack armorPiece = player.field_71071_by.field_70460_b[i];
            if (armorPiece == null || !armorPiece.func_77984_f() || (itemDamage = armorPiece.func_77960_j()) < (repairThreshold = (int)((float)(maxDamage = armorPiece.func_77958_k()) * 0.9f))) continue;
            ItemPoppet cfr_ignored_0 = Witchery.Items.POPPET;
            ItemStack protectStack = ItemPoppet.findBoundPoppetInWorld(Witchery.Items.POPPET.armorPoppet, player, 1000);
            if (protectStack == null) continue;
            armorPiece.func_77964_b(0);
            player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.5f, 0.4f / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4f + 0.8f));
        }
    }

    public static class PoppetType {
        public final int damageValue;
        private final String unlocalizedName;
        private final String localizedName;
        private final int rarity;
        @SideOnly(value=Side.CLIENT)
        private IIcon icon;
        private boolean destroyOnUse;

        private static PoppetType register(PoppetType poppetType, ArrayList<PoppetType> poppetTypes) {
            poppetTypes.add(poppetType);
            return poppetType;
        }

        private PoppetType(int damageValue, String unlocalizedName, String localizedName) {
            this(damageValue, unlocalizedName, localizedName, 1);
        }

        private PoppetType(int damageValue, String unlocalizedName, String localizedName, int rarity) {
            this.damageValue = damageValue;
            this.unlocalizedName = unlocalizedName;
            this.localizedName = localizedName;
            this.rarity = rarity;
        }

        public ItemStack createStack() {
            return this.createStack(1);
        }

        public ItemStack createStack(int quantity) {
            return new ItemStack((Item)Witchery.Items.POPPET, quantity, this.damageValue);
        }

        public boolean isMatch(ItemStack itemstack) {
            return itemstack != null && itemstack.func_77960_j() == this.damageValue;
        }

        private String getUnlocalizedName() {
            if (this.damageValue > 0) {
                return String.format("%s.%s", Witchery.Items.POPPET.func_77658_a(), this.unlocalizedName);
            }
            return Witchery.Items.POPPET.func_77658_a();
        }

        @SideOnly(value=Side.CLIENT)
        private PoppetType registerIcon(IIconRegister iconRegister, ItemPoppet item) {
            this.icon = this.unlocalizedName.equals("poppet") ? iconRegister.func_94245_a(item.func_111208_A()) : iconRegister.func_94245_a(item.func_111208_A() + "." + this.unlocalizedName);
            return this;
        }

        public PoppetType setDestroyOnUse(boolean destroyOnUse) {
            this.destroyOnUse = destroyOnUse;
            return this;
        }

        private boolean applyDamageOnUse(IInventory inventory, int i, ItemStack itemstack, int itemDamage) {
            if (this.destroyOnUse) {
                inventory.func_70299_a(i, null);
                itemstack.field_77994_a = 0;
            } else {
                Witchery.Items.POPPET.setDamageNBT(inventory, itemstack, Witchery.Items.POPPET.getDamageNBT(itemstack) + itemDamage);
            }
            return false;
        }

        static /* synthetic */ PoppetType access$100(PoppetType x0, ArrayList x1) {
            return PoppetType.register(x0, x1);
        }
    }

    public static class PoppetEventHooks {
        @SubscribeEvent
        public void onPlayerInteract(PlayerInteractEvent event) {
            ItemStack protectStack;
            int maxDamage;
            int repairThreshold;
            int itemDamage;
            EntityPlayer player;
            ItemStack heldItem;
            if (!event.entityPlayer.field_70170_p.field_72995_K && (heldItem = (player = event.entityPlayer).func_70694_bm()) != null && heldItem.func_77984_f() && (itemDamage = heldItem.func_77960_j()) >= (repairThreshold = (int)((float)(maxDamage = heldItem.func_77958_k()) * 0.9f)) && (protectStack = ItemPoppet.findBoundPoppetInWorld(Witchery.Items.POPPET.toolPoppet, player, 1000)) != null) {
                heldItem.func_77964_b(0);
                player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.5f, 0.4f / ((float)player.field_70170_p.field_73012_v.nextDouble() * 0.4f + 0.8f));
            }
        }
    }

    private static class PoppetDamageSource
    extends EntityDamageSource {
        private PoppetDamageSource(DamageSource damageType, Entity source) {
            super(damageType.func_76355_l(), source);
            this.func_76348_h();
            this.func_82726_p();
        }
    }
}

