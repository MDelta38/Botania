/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 *  org.apache.logging.log4j.Level
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.ItemManaBean
 *  thaumcraft.common.items.baubles.ItemAmuletVis
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileNode
 *  travellersgear.api.TravellersGearAPI
 */
package witchinggadgets.common.util;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.Level;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemManaBean;
import thaumcraft.common.items.baubles.ItemAmuletVis;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileNode;
import travellersgear.api.TravellersGearAPI;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.items.baubles.ItemCloak;

public class Utilities {
    static String[] dyes = new String[]{"dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite"};
    static Class c_tconProjectileWeapon;

    public static boolean consumeVisFromWand(ItemStack is, EntityPlayer player, AspectList aspects, boolean doit) {
        return ((ItemWandCasting)is.func_77973_b()).consumeAllVisCrafting(is, player, aspects, doit);
    }

    public static AspectList generateNodeAspects(World world, String node) {
        AspectList tags = new AspectList();
        ArrayList loc = (ArrayList)TileNode.locations.get(node);
        if (loc != null && loc.size() > 0) {
            TileEntity tnb;
            int dim = (Integer)loc.get(0);
            int x = (Integer)loc.get(1);
            int y = (Integer)loc.get(2);
            int z = (Integer)loc.get(3);
            if (dim == world.field_73011_w.field_76574_g && (tnb = world.func_147438_o(x, y, z)) != null && tnb instanceof INode) {
                AspectList ta = ((INode)tnb).getAspects();
                for (Aspect a : ta.getAspectsSorted()) {
                    tags.merge(a, Math.max(4, ta.getAmount(a) / 10));
                }
                switch (((INode)tnb).getNodeType().ordinal()) {
                    case 1: {
                        tags.merge(Aspect.ENTROPY, 4);
                        break;
                    }
                    case 2: {
                        tags.merge(Aspect.HUNGER, 4);
                        break;
                    }
                    case 3: {
                        tags.merge(Aspect.TAINT, 4);
                        break;
                    }
                    case 4: {
                        tags.merge(Aspect.HEAL, 2);
                        tags.add(Aspect.ORDER, 2);
                        break;
                    }
                    case 5: {
                        tags.merge(Aspect.DEATH, 2);
                        tags.add(Aspect.DARKNESS, 2);
                    }
                }
            }
        }
        return tags.size() > 0 ? tags : null;
    }

    public static NBTTagCompound writeScanResultToNBT(ScanResult scan) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74774_a("type", scan.type);
        tag.func_74768_a("blockId", scan.id);
        tag.func_74768_a("blockMeta", scan.meta);
        if (scan.entity != null) {
            NBTTagCompound entityTag = new NBTTagCompound();
            scan.entity.func_70109_d(entityTag);
            tag.func_74782_a("entity", (NBTBase)entityTag);
            tag.func_74778_a("entityClass", scan.entity.getClass().getName());
        }
        tag.func_74778_a("phenomena", scan.phenomena);
        return tag;
    }

    public static ScanResult readScanResultFromNBT(NBTTagCompound tag, World world) {
        byte type = tag.func_74771_c("type");
        int blockId = tag.func_74762_e("blockId");
        int blockMeta = tag.func_74762_e("blockMeta");
        Entity entity = null;
        if (tag.func_74764_b("entity")) {
            try {
                Class<?> clazz = Class.forName(tag.func_74779_i("entityClass"));
                Constructor<?> cons = clazz.getConstructor(World.class);
                entity = (Entity)cons.newInstance(world);
                entity.func_70020_e(tag.func_74775_l("entity"));
            }
            catch (Exception e) {
                e.printStackTrace();
                entity = null;
            }
        }
        String phenomena = tag.func_74779_i("phenomena");
        ScanResult scan = new ScanResult(type, blockId, blockMeta, entity, phenomena);
        return scan;
    }

    public static String findCloseNode(World world, ChunkCoordinates cc) {
        Map.Entry closest = null;
        for (Map.Entry e : TileNode.locations.entrySet()) {
            if ((Integer)((ArrayList)e.getValue()).get(0) != world.field_73011_w.field_76574_g || closest != null && !(cc.func_71569_e(((Integer)((ArrayList)e.getValue()).get(1)).intValue(), ((Integer)((ArrayList)e.getValue()).get(2)).intValue(), ((Integer)((ArrayList)e.getValue()).get(3)).intValue()) < cc.func_71569_e(((Integer)((ArrayList)closest.getValue()).get(1)).intValue(), ((Integer)((ArrayList)closest.getValue()).get(2)).intValue(), ((Integer)((ArrayList)closest.getValue()).get(3)).intValue()))) continue;
            closest = e;
        }
        if (closest != null) {
            return (String)closest.getKey();
        }
        return null;
    }

    public static boolean researchExists(String category, String key) {
        return ((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)category)).research.containsKey(key);
    }

    public static boolean consumeVisFromInventoryWithoutDiscount(EntityPlayer player, AspectList cost) {
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (int b = 0; b < 4; ++b) {
            boolean comnsumed;
            if (baubles.func_70301_a(b) == null || !(baubles.func_70301_a(b).func_77973_b() instanceof ItemAmuletVis) || !(comnsumed = ((ItemAmuletVis)baubles.func_70301_a(b).func_77973_b()).consumeAllVis(baubles.func_70301_a(b), player, cost, true, true))) continue;
            return true;
        }
        for (int i = player.field_71071_by.field_70462_a.length - 1; i >= 0; --i) {
            boolean comnsumed;
            ItemStack item = player.field_71071_by.field_70462_a[i];
            if (item == null || !(item.func_77973_b() instanceof ItemWandCasting) || !(comnsumed = ((ItemWandCasting)item.func_77973_b()).consumeAllVisCrafting(item, player, cost, true))) continue;
            return true;
        }
        return false;
    }

    public static boolean isBlockPlaceable(World world, int x, int y, int z) {
        boolean flag = false;
        Block b = world.func_147439_a(x, y, z);
        if (world.func_147437_c(x, y, z)) {
            flag = true;
        } else if (b.equals(Blocks.field_150433_aE) || b.equals(Blocks.field_150395_bd) || b.equals(Blocks.field_150329_H) || b.equals(Blocks.field_150330_I) || b.isReplaceable((IBlockAccess)world, x, y, z)) {
            flag = true;
        }
        return flag;
    }

    public static boolean compareToOreName(ItemStack item, String oreName) {
        for (int oid : OreDictionary.getOreIDs((ItemStack)item)) {
            if (!OreDictionary.getOreName((int)oid).equalsIgnoreCase(oreName)) continue;
            return true;
        }
        return false;
    }

    public static boolean stacksMatchWithOreDic(ItemStack stack0, ItemStack stack1) {
        for (int oid0 : OreDictionary.getOreIDs((ItemStack)stack0)) {
            for (int oid1 : OreDictionary.getOreIDs((ItemStack)stack1)) {
                if (oid0 != oid1) continue;
                return true;
            }
        }
        return false;
    }

    public static String getItemStackString(ItemStack stack) {
        StringBuilder result = new StringBuilder();
        result.append(Item.field_150901_e.func_148750_c((Object)stack.func_77973_b()));
        if (stack.func_77960_j() == Short.MAX_VALUE) {
            result.append(":*");
        } else if (stack.func_77960_j() > 0) {
            result.append(':').append(stack.func_77960_j());
        }
        return result.toString();
    }

    public static boolean isDye(ItemStack item) {
        if (Utilities.compareToOreName(item, "dye")) {
            return true;
        }
        for (String d : dyes) {
            if (!Utilities.compareToOreName(item, d)) continue;
            return true;
        }
        return false;
    }

    public static int getDamageForDye(ItemStack item) {
        for (int d = 0; d < dyes.length; ++d) {
            if (!Utilities.compareToOreName(item, dyes[d])) continue;
            return d;
        }
        return -1;
    }

    public static boolean isOre(ItemStack item) {
        for (int oid : OreDictionary.getOreIDs((ItemStack)item)) {
            if (!OreDictionary.getOreName((int)oid).startsWith("ore")) continue;
            return true;
        }
        return false;
    }

    public static boolean isOre(World world, int x, int y, int z) {
        return Utilities.isOre(new ItemStack(world.func_147439_a(x, y, z), 1, world.func_72805_g(x, y, z)));
    }

    public static String getTitleCase(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void extendPotionArray(int extendBy) {
        WitchingGadgets.logger.log(Level.INFO, "Attempting to extend PotionArray by " + extendBy);
        Potion[] potions = new Potion[Potion.field_76425_a.length + extendBy];
        for (int i = 0; i < Potion.field_76425_a.length; ++i) {
            potions[i] = Potion.field_76425_a[i];
        }
        try {
            Field[] fields;
            AccessibleObject field = null;
            for (Field f : fields = Potion.class.getDeclaredFields()) {
                if (!f.getType().toString().equals("class [Lnet.minecraft.potion.Potion;")) continue;
                field = f;
                break;
            }
            field.setAccessible(true);
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, ((Field)field).getModifiers() & 0xFFFFFFEF);
            ((Field)field).set(null, potions);
            WitchingGadgets.logger.log(Level.INFO, "Variable " + Potion.field_76425_a.length);
            WitchingGadgets.logger.log(Level.INFO, "Reflection " + ((Potion[])Potion.class.getFields()[0].get(null)).length);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getNextPotionId(int start) {
        if (Potion.field_76425_a != null && start > 0 && start < Potion.field_76425_a.length && Potion.field_76425_a[start] == null) {
            return start;
        }
        start = ++start < 256 ? Utilities.getNextPotionId(start) : -1;
        return start;
    }

    public static int getNextEnchantmentId(int start) {
        if (Enchantment.field_77331_b != null && start > 0 && start < Enchantment.field_77331_b.length && Enchantment.field_77331_b[start] == null) {
            return start;
        }
        start = ++start < 256 ? Utilities.getNextEnchantmentId(start) : -1;
        return start;
    }

    public static ItemStack getAspectBean(Aspect a) {
        ItemStack bean = new ItemStack(ConfigItems.itemManaBean);
        ((ItemManaBean)bean.func_77973_b()).setAspects(bean, new AspectList().add(a, 1));
        return bean;
    }

    public static ItemStack getShard(Aspect a) {
        if (!Aspect.getPrimalAspects().contains(a)) {
            return null;
        }
        int meta = a.equals(Aspect.AIR) ? 0 : (a.equals(Aspect.FIRE) ? 1 : (a.equals(Aspect.WATER) ? 2 : (a.equals(Aspect.EARTH) ? 3 : (a.equals(Aspect.ORDER) ? 4 : 5))));
        ItemStack shard = new ItemStack(ConfigItems.itemShard, 1, meta);
        return shard;
    }

    public static ItemStack[] getActiveMagicalCloak(EntityPlayer player) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        if (TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0] != null && TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0].func_77973_b() instanceof ItemCloak) {
            list.add(TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0]);
        } else if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77973_b() instanceof ItemCloak) {
            list.add(BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3));
        }
        return list.toArray(new ItemStack[0]);
    }

    public static void updateActiveMagicalCloak(EntityPlayer player, ItemStack cloak) {
        if (cloak != null && cloak.func_77973_b().equals(WGContent.ItemKama)) {
            if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3) != null && BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77973_b() instanceof ItemCloak) {
                if (BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3).func_77960_j() == cloak.func_77960_j()) {
                    BaublesApi.getBaubles((EntityPlayer)player).func_70299_a(3, cloak);
                }
                BaublesApi.getBaubles((EntityPlayer)player).func_70296_d();
            }
        } else if (TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0] != null && TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0].func_77973_b() instanceof ItemCloak) {
            ItemStack[] tgInv = TravellersGearAPI.getExtendedInventory((EntityPlayer)player);
            if (tgInv[0].func_77960_j() == cloak.func_77960_j()) {
                tgInv[0] = cloak;
            }
            TravellersGearAPI.setExtendedInventory((EntityPlayer)player, (ItemStack[])tgInv);
        }
    }

    public static boolean isPlayerUsingBow(EntityPlayer player) {
        if (!player.func_71039_bw() || player.field_71071_by.func_70448_g() == null || player.field_71071_by.func_70448_g().func_77973_b() == null) {
            return false;
        }
        if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemBow) {
            return true;
        }
        if (Loader.isModLoaded((String)"TConstruct")) {
            if (c_tconProjectileWeapon == null) {
                try {
                    c_tconProjectileWeapon = Class.forName("tconstruct.library.weaponry.ProjectileWeapon");
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (c_tconProjectileWeapon != null && c_tconProjectileWeapon.isAssignableFrom(player.field_71071_by.func_70448_g().func_77973_b().getClass())) {
                return true;
            }
        }
        return false;
    }

    public static void addAttributeModToLivingUnsaved(EntityLivingBase living, IAttribute attr, UUID uuid, String tag, double val, int type) {
        IAttributeInstance attrIns = living.func_110140_aT().func_111151_a(attr);
        if (attrIns == null || attrIns.func_111127_a(uuid) != null) {
            return;
        }
        attrIns.func_111121_a(new AttributeModifier(uuid, tag, val, type).func_111168_a(false));
    }

    public static void addAttributeModToLiving(EntityLivingBase living, IAttribute attr, UUID uuid, String tag, double val, int type) {
        IAttributeInstance attrIns = living.func_110140_aT().func_111151_a(attr);
        if (attrIns == null || attrIns.func_111127_a(uuid) != null) {
            return;
        }
        attrIns.func_111121_a(new AttributeModifier(uuid, tag, val, type));
    }

    public static boolean livingHasAttributeMod(EntityLivingBase living, IAttribute attr, UUID uuid) {
        IAttributeInstance attrIns = living.func_110140_aT().func_111151_a(attr);
        return attrIns != null && attrIns.func_111127_a(uuid) != null;
    }

    public static void removeAttributeModFromLiving(EntityLivingBase living, IAttribute attr, UUID uuid, String tag, double val, int type) {
        IAttributeInstance attrIns = living.func_110140_aT().func_111151_a(attr);
        if (attrIns != null) {
            attrIns.func_111124_b(new AttributeModifier(uuid, tag, val, type));
        }
    }

    public static ItemStack copyStackWithSize(ItemStack stack, int i) {
        ItemStack s = new ItemStack(stack.func_77973_b(), i, stack.func_77960_j());
        if (stack.func_77942_o()) {
            s.func_77982_d(stack.func_77978_p());
        }
        return s;
    }

    public static void setAttackTarget(EntityLiving living, EntityLivingBase target) {
        ReflectionHelper.setPrivateValue(EntityLiving.class, (Object)living, (Object)target, (String[])new String[]{"attackTarget", "field_70696_bz"});
    }

    public static boolean isRightMaterial(Material mat, Material[] materials) {
        for (Material m : materials) {
            if (m != mat) continue;
            return true;
        }
        return false;
    }

    public static ItemStack getPickedBlock(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z) == null) {
            return null;
        }
        Item item = Item.func_150898_a((Block)world.func_147439_a(x, y, z));
        if (item == null) {
            return null;
        }
        Block block = item instanceof ItemBlock ? Block.func_149634_a((Item)item) : world.func_147439_a(x, y, z);
        return new ItemStack(item, 1, block.func_149643_k(world, x, y, z));
    }

    public static class OreDictStack {
        public final String key;
        public final int amount;

        public OreDictStack(String key, int amount) {
            this.key = key;
            this.amount = amount;
        }

        public boolean matches(ItemStack stack) {
            return Utilities.compareToOreName(stack, this.key) && stack.field_77994_a >= this.amount;
        }
    }
}

