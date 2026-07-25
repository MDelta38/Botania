/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.launchwrapper.Launch
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.player.AttackEntityEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$BreakSpeed
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.oredict.OreDictionary
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.entities.EntitySpecialItem
 *  thaumcraft.common.entities.monster.EntityCultistCleric
 *  thaumcraft.common.entities.monster.EntityCultistKnight
 *  thaumcraft.common.entities.monster.boss.EntityCultistLeader
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.lib.utils.InventoryUtils
 *  thaumcraft.common.tiles.TileInfusionMatrix
 *  travellersgear.api.TravellersGearAPI
 */
package witchinggadgets.common.util.handler;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileInfusionMatrix;
import travellersgear.api.TravellersGearAPI;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.api.IPrimordialCrafting;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.ItemMaterials;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;
import witchinggadgets.common.items.tools.IPrimordialGear;
import witchinggadgets.common.items.tools.ItemBag;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.handler.WGMultiPartHandler;
import witchinggadgets.common.util.network.message.MessageClientNotifier;

public class EventHandler {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void entityHurt(LivingHurtEvent event) {
        if (event.source.func_76347_k() && event.entityLiving.func_70660_b(WGContent.pot_cinderCoat) != null) {
            event.ammount *= (float)(2 + event.entityLiving.func_70660_b(WGContent.pot_cinderCoat).func_76458_c());
        }
        if (event.source.func_76364_f() instanceof EntityPlayer && ((EntityPlayer)event.source.func_76364_f()).func_71045_bC() != null) {
            EntityPlayer player = (EntityPlayer)event.source.func_76364_f();
            if (player.func_71045_bC().func_77973_b().equals(WGContent.ItemPrimordialHammer) && (event.entityLiving instanceof EntitySlime || event.entityLiving.getClass().getName().endsWith("BlueSlime") || event.entityLiving.func_70668_bt() == EnumCreatureAttribute.ARTHROPOD)) {
                event.ammount *= 2.0f;
            }
            if (player.func_71045_bC().func_77973_b().equals(WGContent.ItemPrimordialAxe) && !event.source.func_76363_c()) {
                float mod = 1.0f;
                for (int i = 1; i <= 4; ++i) {
                    if (event.entityLiving.func_71124_b(i) == null) continue;
                    mod += 0.5f;
                }
                event.ammount *= mod;
            }
            if (EnchantmentHelper.func_77506_a((int)WGContent.enc_backstab.field_77352_x, (ItemStack)player.func_71045_bC()) > 0) {
                Vec3 targetVec = event.entityLiving.func_70040_Z();
                Vec3 attackVec = player.func_70040_Z();
                if (Math.signum(targetVec.field_72450_a) == Math.signum(attackVec.field_72450_a) && Math.signum(targetVec.field_72449_c) == Math.signum(attackVec.field_72449_c)) {
                    float mod = 1.0f + 0.2f * (float)EnchantmentHelper.func_77506_a((int)WGContent.enc_backstab.field_77352_x, (ItemStack)player.func_71045_bC());
                    if (event.entityLiving instanceof EntityCreature && !player.equals((Object)((EntityCreature)event.entityLiving).func_70638_az())) {
                        mod += 0.4f;
                    }
                    event.ammount *= mod;
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingSetTarget(LivingSetAttackTargetEvent event) {
        EntityPlayer player;
        if (!(event.target instanceof EntityPlayer)) {
            return;
        }
        if (event.entityLiving instanceof EntityCreature && (player = (EntityPlayer)event.target).func_70093_af() && (EnchantmentHelper.func_77506_a((int)WGContent.enc_stealth.field_77352_x, (ItemStack)player.func_82169_q(0)) > 0 || EnchantmentHelper.func_77506_a((int)WGContent.enc_stealth.field_77352_x, (ItemStack)player.func_82169_q(1)) > 0)) {
            float chance = (float)EnchantmentHelper.func_77506_a((int)WGContent.enc_stealth.field_77352_x, (ItemStack)player.func_82169_q(0)) * 0.1f + (float)EnchantmentHelper.func_77506_a((int)WGContent.enc_stealth.field_77352_x, (ItemStack)player.func_82169_q(1)) * 0.1f;
            Vec3 targetVec = event.entityLiving.func_70040_Z();
            Vec3 attackVec = player.func_70040_Z();
            if (Math.signum(targetVec.field_72450_a) != Math.signum(attackVec.field_72450_a) || Math.signum(targetVec.field_72449_c) != Math.signum(attackVec.field_72449_c)) {
                chance -= 0.1f;
            }
            if (player.func_70681_au().nextFloat() < chance) {
                Utilities.setAttackTarget((EntityLiving)((EntityCreature)event.entityLiving), null);
            } else {
                for (EntityCreature e : player.field_70170_p.func_72872_a(EntityCreature.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 5.0), (double)(player.field_70163_u - 5.0), (double)(player.field_70161_v - 5.0), (double)(player.field_70165_t + 5.0), (double)(player.field_70163_u + 5.0), (double)(player.field_70161_v + 5.0)))) {
                    if (e == null || e instanceof IBossDisplayData || !player.equals((Object)e.func_70638_az())) continue;
                    Utilities.setAttackTarget((EntityLiving)((EntityCreature)event.entityLiving), null);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer)) {
            if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
            event.setCanceled(true);
        }
        if (Loader.isModLoaded((String)"ForgeMultipart")) {
            WGMultiPartHandler.handleWorldInteraction(event);
        }
    }

    @SubscribeEvent
    public void onPlayerInteractWithEntity(EntityInteractEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer)) {
            if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerAttackEntity(AttackEntityEvent event) {
        for (ItemStack cloak : Utilities.getActiveMagicalCloak(event.entityPlayer)) {
            if (cloak == null || !cloak.func_77942_o() || !cloak.func_77978_p().func_74767_n("isSpectral")) continue;
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerBreaking(PlayerEvent.BreakSpeed event) {
        if (TravellersGearAPI.getExtendedInventory((EntityPlayer)event.entityPlayer)[2] != null && TravellersGearAPI.getExtendedInventory((EntityPlayer)event.entityPlayer)[2].func_77973_b() instanceof ItemMagicalBaubles && TravellersGearAPI.getExtendedInventory((EntityPlayer)event.entityPlayer)[2].func_77960_j() == 3) {
            float hardness;
            Block block = event.entityPlayer.field_70170_p.func_147439_a(event.x, event.y, event.z);
            if (!event.entityPlayer.field_70122_E) {
                event.newSpeed *= 5.0f;
            }
            if (event.entityPlayer.func_70055_a(Material.field_151586_h) && !EnchantmentHelper.func_77510_g((EntityLivingBase)event.entityPlayer)) {
                event.newSpeed *= 5.0f;
            }
            if ((hardness = block.func_149712_f(event.entityPlayer.field_70170_p, event.x, event.y, event.z)) > 20.0f) {
                event.newSpeed = 5.0f + hardness;
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onLivingDrop(LivingDropsEvent event) {
        EntityPlayer player;
        if (event.entityLiving instanceof EntityWolf) {
            EntityWolf enemy = (EntityWolf)event.entityLiving;
            for (int i = 0; i < 2 + Math.min(4, event.lootingLevel); ++i) {
                if (enemy.field_70170_p.field_73012_v.nextInt(Math.max(1, 3 - event.lootingLevel)) != 0) continue;
                EntityItem entityitem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, new ItemStack(WGContent.ItemMaterial, 1, 6));
                entityitem.field_145804_b = 10;
                event.drops.add(entityitem);
            }
        }
        if (event.entityLiving instanceof EntityCultistCleric && event.entityLiving.field_70170_p.field_73012_v.nextInt(10) < 1 + event.lootingLevel) {
            event.drops.add(new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, ItemMagicalBaubles.getItemWithTitle(new ItemStack(WGContent.ItemMagicalBaubles, 1, 4), "wg.title.crimsonCultist")));
        }
        if (event.entityLiving instanceof EntityCultistKnight && event.entityLiving.field_70170_p.field_73012_v.nextInt(10) < 1 + event.lootingLevel) {
            event.drops.add(new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, ItemMagicalBaubles.getItemWithTitle(new ItemStack(WGContent.ItemMagicalBaubles, 1, 4), "wg.title.crimsonKnight")));
        }
        if (event.entityLiving instanceof EntityCultistLeader && event.entityLiving.field_70170_p.field_73012_v.nextInt(2) == 0) {
            event.drops.add(new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, ItemMagicalBaubles.getItemWithTitle(new ItemStack(WGContent.ItemMagicalBaubles, 1, 4), "wg.title.crimsonPraetor")));
        }
        if (event.recentlyHit && event.source != null && event.source.func_76364_f() instanceof EntityPlayer && (player = (EntityPlayer)event.source.func_76364_f()).func_71045_bC() != null && player.func_71045_bC().func_77973_b().equals(WGContent.ItemPrimordialSword) && player.func_70681_au().nextInt(6) < EnchantmentHelper.func_77519_f((EntityLivingBase)player)) {
            ItemStack head = null;
            if (event.entityLiving instanceof EntitySkeleton) {
                head = new ItemStack(Items.field_151144_bL, 1, ((EntitySkeleton)event.entityLiving).func_82202_m());
            } else if (event.entityLiving instanceof EntityZombie) {
                head = new ItemStack(Items.field_151144_bL, 1, 2);
            } else if (event.entityLiving instanceof EntityCreeper) {
                head = new ItemStack(Items.field_151144_bL, 1, 4);
            } else if (event.entityLiving instanceof EntityPlayer) {
                head = new ItemStack(Items.field_151144_bL, 1, 3);
                NBTTagCompound tag = new NBTTagCompound();
                tag.func_74778_a("SkullOwner", player.getDisplayName());
                head.func_77982_d(tag);
            } else if (Loader.isModLoaded((String)"witchery")) {
                Item wwh = GameRegistry.findItem((String)"witchery", (String)"wolfhead");
                if (event.entityLiving instanceof EntityWolf) {
                    head = new ItemStack(wwh, 1, 0);
                }
                if (event.entityLiving.getClass().getName().endsWith("EntityHellhound")) {
                    head = new ItemStack(wwh, 1, 1);
                }
            } else if (Loader.isModLoaded((String)"IguanaTweaksTConstruct")) {
                Item ith = GameRegistry.findItem((String)"IguanaTweaksTConstruct", (String)"skullItem");
                if (event.entityLiving instanceof EntityEnderman) {
                    head = new ItemStack(ith, 1, 0);
                } else if (event.entityLiving instanceof EntityPigZombie) {
                    head = new ItemStack(ith, 1, 1);
                } else if (event.entityLiving instanceof EntityBlaze) {
                    head = new ItemStack(ith, 1, 2);
                } else if (EntityList.func_75621_b((Entity)event.entityLiving).equals("Blizz")) {
                    head = new ItemStack(ith, 1, 3);
                }
            }
            if (head != null) {
                for (EntityItem eitem : event.drops) {
                    if (eitem == null || !OreDictionary.itemMatches((ItemStack)eitem.func_92059_d(), (ItemStack)head, (boolean)true)) continue;
                    return;
                }
                event.entityLiving.field_70170_p.func_72838_d((Entity)new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, head));
            }
        }
    }

    @SubscribeEvent
    public void onLivingDies(LivingDeathEvent event) {
        EntityPlayer player;
        if (event.source != null && event.source.func_76364_f() instanceof EntityPlayer && event.entityLiving instanceof EntityLiving && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving.field_70170_p.func_82736_K().func_82766_b("doMobLoot") && (player = (EntityPlayer)event.source.func_76364_f()).func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof IPrimordialGear && ((IPrimordialGear)player.func_71045_bC().func_77973_b()).getAbility(player.func_71045_bC()) == 4) {
            int i;
            boolean deobf = (Boolean)Launch.blackboard.get("fml.deobfuscatedEnvironment");
            String name = deobf ? "experienceValue" : "field_70728_aV";
            int baseValue = (Integer)ObfuscationReflectionHelper.getPrivateValue(EntityLiving.class, (Object)((EntityLiving)event.entityLiving), (String[])new String[]{name});
            for (int xp = 4 * baseValue; xp > 0; xp -= i) {
                i = EntityXPOrb.func_70527_a((int)xp);
                event.entityLiving.field_70170_p.func_72838_d((Entity)new EntityXPOrb(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, i));
            }
        }
    }

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        for (int i = 0; i < event.entityPlayer.field_71071_by.func_70302_i_(); ++i) {
            if (event.entityPlayer.field_71071_by.func_70301_a(i) == null || !(event.entityPlayer.field_71071_by.func_70301_a(i).func_77973_b() instanceof ItemBag)) continue;
            if (event.entityPlayer.field_71071_by.func_70301_a(i).func_77960_j() == 1) {
                ItemStack[] filter;
                for (ItemStack f : filter = ((ItemBag)event.entityPlayer.field_71071_by.func_70301_a(i).func_77973_b()).getStoredItems(event.entityPlayer.field_71071_by.func_70301_a(i))) {
                    int slot;
                    AspectList primals;
                    Aspect a;
                    if (!OreDictionary.itemMatches((ItemStack)f, (ItemStack)event.item.func_92059_d(), (boolean)true)) continue;
                    AspectList al = ThaumcraftCraftingManager.getObjectTags((ItemStack)event.item.func_92059_d());
                    al = ThaumcraftCraftingManager.getBonusTags((ItemStack)event.item.func_92059_d(), (AspectList)al);
                    if (al != null && al.size() >= 0 && (a = (primals = ResearchManager.reduceToPrimals((AspectList)al)).getAspects()[event.entityPlayer.func_70681_au().nextInt(primals.getAspects().length)]) != null && (slot = InventoryUtils.isWandInHotbarWithRoom((Aspect)a, (int)1, (EntityPlayer)event.entityPlayer)) >= 0) {
                        ItemWandCasting wand = (ItemWandCasting)event.entityPlayer.field_71071_by.field_70462_a[slot].func_77973_b();
                        wand.addVis(event.entityPlayer.field_71071_by.field_70462_a[slot], a, primals.getAmount(a), true);
                    }
                    event.item.func_70106_y();
                    event.setCanceled(true);
                    return;
                }
                continue;
            }
            if (event.entityPlayer.field_71071_by.func_70301_a(i).func_77960_j() != 3) continue;
            ItemStack[] inv = ((ItemBag)event.entityPlayer.field_71071_by.func_70301_a(i).func_77973_b()).getStoredItems(event.entityPlayer.field_71071_by.func_70301_a(i));
            for (int f = 0; f < inv.length; ++f) {
                if (inv[f] == null) {
                    inv[f] = event.item.func_92059_d().func_77946_l();
                    event.item.func_70106_y();
                    event.setCanceled(true);
                    break;
                }
                if (!OreDictionary.itemMatches((ItemStack)inv[f], (ItemStack)event.item.func_92059_d(), (boolean)true)) continue;
                int fit = Math.min(Math.min(64, inv[f].func_77976_d()) - inv[f].field_77994_a, event.item.func_92059_d().field_77994_a);
                inv[f].field_77994_a += fit;
                event.item.func_92059_d().field_77994_a -= fit;
                if (event.item.func_92059_d().field_77994_a > 0) continue;
                event.item.func_70106_y();
                event.setCanceled(true);
                break;
            }
            ((ItemBag)event.entityPlayer.field_71071_by.func_70301_a(i).func_77973_b()).setStoredItems(event.entityPlayer.field_71071_by.func_70301_a(i), inv);
        }
    }

    @SubscribeEvent
    public void onCrafted(PlayerEvent.ItemCraftedEvent event) {
        ItemStack stackInMatrix;
        int matrixSlot;
        ItemStack output = event.crafting;
        IInventory craftMatrix = event.craftMatrix;
        if (output.func_77973_b().equals(WGContent.ItemKama)) {
            for (matrixSlot = 0; matrixSlot < 9; ++matrixSlot) {
                stackInMatrix = craftMatrix.func_70301_a(matrixSlot);
                if (stackInMatrix == null || !stackInMatrix.func_77973_b().equals(WGContent.ItemCloak)) continue;
                output.func_77982_d(stackInMatrix.func_77978_p());
            }
        }
        if (output.func_77973_b().equals(WGContent.ItemMaterial) && output.func_77960_j() == 10) {
            for (matrixSlot = 0; matrixSlot < 9; ++matrixSlot) {
                stackInMatrix = craftMatrix.func_70301_a(matrixSlot);
                if (stackInMatrix == null || !(stackInMatrix.func_77973_b() instanceof ItemMaterials) || stackInMatrix.func_77960_j() != 9) continue;
                ++stackInMatrix.field_77994_a;
                craftMatrix.func_70299_a(matrixSlot, stackInMatrix);
            }
        }
        if (!(!(output.func_77973_b() instanceof IPrimordialCrafting) || event.player.field_70170_p.field_72995_K || output.func_77942_o() && output.func_77978_p().func_74767_n("wasCrafted") || ((IPrimordialCrafting)output.func_77973_b()).getReturnedPearls(output) <= 0)) {
            double iX = event.player.field_70165_t;
            double iY = event.player.field_70163_u + 1.0;
            double iZ = event.player.field_70161_v;
            for (int yy = -16; yy <= 16; ++yy) {
                for (int zz = -16; zz <= 16; ++zz) {
                    for (int xx = -16; xx <= 16; ++xx) {
                        if (!(event.player.field_70170_p.func_147438_o((int)event.player.field_70165_t + xx, (int)event.player.field_70163_u + yy, (int)event.player.field_70161_v + zz) instanceof TileInfusionMatrix)) continue;
                        iX = event.player.field_70165_t + (double)xx;
                        iY = event.player.field_70163_u + (double)yy - 0.5;
                        iZ = event.player.field_70161_v + (double)zz;
                    }
                }
            }
            EntitySpecialItem entityitem = new EntitySpecialItem(event.player.field_70170_p, iX, iY, iZ, new ItemStack(WGContent.ItemMaterial, ((IPrimordialCrafting)output.func_77973_b()).getReturnedPearls(output), 12));
            entityitem.field_70179_y = 0.0;
            entityitem.field_70181_x = 0.0;
            entityitem.field_70159_w = 0.0;
            if (output.func_77978_p() == null) {
                output.func_77982_d(new NBTTagCompound());
            }
            output.func_77978_p().func_74757_a("wasCrafted", true);
            event.player.field_70170_p.func_72838_d((Entity)entityitem);
        }
    }

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        WitchingGadgets.packetHandler.sendTo((IMessage)new MessageClientNotifier(0), (EntityPlayerMP)event.player);
    }
}

