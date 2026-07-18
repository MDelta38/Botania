/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.entity.living.LivingEvent;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lexicon.page.PageShedding;

public final class SheddingHandler {
    public static ArrayList<ShedPattern> patterns = new ArrayList();
    public static ArrayList<ShedPattern> defaultPatterns = new ArrayList();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entity.field_70170_p.field_72995_K) {
            return;
        }
        ShedPattern pattern = SheddingHandler.getShedPattern(event.entity);
        if (pattern != null && event.entity.field_70170_p.field_73012_v.nextInt(pattern.getRate()) == 0) {
            event.entity.func_70099_a(pattern.getItemStack(), 0.0f);
        }
    }

    public static ShedPattern getShedPattern(Entity entity) {
        for (ShedPattern pattern : patterns) {
            if (!pattern.EntityClass.isInstance(entity)) continue;
            return pattern;
        }
        return null;
    }

    public static boolean hasShedding() {
        return patterns.size() > 0;
    }

    public static void addToLexicon() {
        if (!SheddingHandler.hasShedding()) {
            return;
        }
        int i = 1;
        for (ShedPattern pattern : patterns) {
            PageShedding page = new PageShedding(String.valueOf(i), (String)EntityList.field_75626_c.get(pattern.EntityClass), pattern.lexiconSize, pattern.getItemStack());
            LexiconData.shedding.addPage(page);
        }
    }

    public static void loadFromConfig(Configuration config) {
        defaultPatterns.add(new ShedPattern(EntityChicken.class, new ItemStack(Items.field_151008_G), 26000, 20));
        defaultPatterns.add(new ShedPattern(EntitySquid.class, new ItemStack(Items.field_151100_aR), 18000, 20));
        defaultPatterns.add(new ShedPattern(EntityVillager.class, new ItemStack(Items.field_151166_bC), 226000, 40));
        defaultPatterns.add(new ShedPattern(EntitySpider.class, new ItemStack(Items.field_151007_F), 12000, 40));
        defaultPatterns.add(new ShedPattern(EntityBlaze.class, new ItemStack(Items.field_151065_br), 8000, 40));
        defaultPatterns.add(new ShedPattern(EntityGhast.class, new ItemStack(Items.field_151073_bk), 9001, 30));
        defaultPatterns.add(new ShedPattern(EntitySkeleton.class, new ItemStack(Items.field_151103_aS), 36000, 40));
        defaultPatterns.add(new ShedPattern(EntitySlime.class, new ItemStack(Items.field_151123_aH), 21000, 40));
        ArrayList<String> defaultNames = new ArrayList<String>();
        for (ShedPattern shedPattern : defaultPatterns) {
            SheddingHandler.loadFromConfig(config, shedPattern.getEntityString(), shedPattern);
            defaultNames.add(shedPattern.getEntityString());
        }
        for (Object object : EntityList.field_75625_b.entrySet()) {
            String name;
            Map.Entry entry = (Map.Entry)object;
            if (!EntityLiving.class.isAssignableFrom((Class)entry.getValue()) || defaultNames.contains(name = (String)entry.getKey())) continue;
            SheddingHandler.loadFromConfig(config, name, null);
        }
    }

    public static void loadFromConfig(Configuration config, String key, ShedPattern defaultPattern) {
        String itemName = "";
        int metadata = 0;
        int rate = -1;
        int lexiconSize = 40;
        if (defaultPattern != null) {
            itemName = Item.field_150901_e.func_148750_c((Object)defaultPattern.getItemStack().func_77973_b());
            metadata = defaultPattern.getItemStack().func_77960_j();
            rate = defaultPattern.rate;
            lexiconSize = defaultPattern.lexiconSize;
        }
        Property prop = config.get("Shedding", key + ".item", itemName);
        prop.comment = "Configuration of Shedding for " + key;
        itemName = prop.getString();
        rate = config.get("Shedding", key + ".rate", rate).getInt();
        metadata = config.get("Shedding", key + ".metadata", metadata).getInt();
        lexiconSize = config.get("Shedding", key + ".lexiconDisplaySize", lexiconSize).getInt();
        if (itemName != null && !itemName.isEmpty() && rate != -1) {
            patterns.add(new ShedPattern((Class)EntityList.field_75625_b.get(key), new ItemStack((Item)Item.field_150901_e.func_82594_a(itemName), 1, metadata), rate, lexiconSize));
        }
    }

    public static class ShedPattern {
        Class EntityClass;
        ItemStack itemStack;
        int rate;
        int lexiconSize;

        public ShedPattern(Class EntityClass, ItemStack itemStack, int rate, int lexiconSize) {
            this.EntityClass = EntityClass;
            this.itemStack = itemStack;
            this.rate = rate;
            this.lexiconSize = lexiconSize;
        }

        public ItemStack getItemStack() {
            return this.itemStack.func_77946_l();
        }

        public int getRate() {
            return this.rate;
        }

        public String getEntityString() {
            return (String)EntityList.field_75626_c.get(this.EntityClass);
        }
    }
}

