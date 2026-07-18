/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item.relic;

import baubles.api.BaubleType;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.api.item.IExtendedWireframeCoordinateListProvider;
import vazkii.botania.api.item.IWireframeCoordinateListProvider;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.crafting.recipe.AesirRingRecipe;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.relic.ItemOdinRing;
import vazkii.botania.common.item.relic.ItemRelicBauble;

public class ItemAesirRing
extends ItemRelicBauble
implements IExtendedWireframeCoordinateListProvider,
ICraftAchievement {
    Multimap<String, AttributeModifier> attributes = HashMultimap.create();

    public ItemAesirRing() {
        super("aesirRing");
        GameRegistry.addRecipe((IRecipe)new AesirRingRecipe());
        RecipeSorter.register((String)"botania:aesirRing", AesirRingRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onDropped(ItemTossEvent event) {
        ItemStack stack;
        if (event.entityItem != null && event.entityItem.func_92059_d() != null && !event.entityItem.field_70170_p.field_72995_K && (stack = event.entityItem.func_92059_d()).func_77973_b() != null && stack.func_77973_b() == this) {
            event.entityItem.func_70106_y();
            String user = this.getSoulbindUsername(stack);
            for (Item item : new Item[]{ModItems.thorRing, ModItems.lokiRing, ModItems.odinRing}) {
                ItemStack stack1 = new ItemStack(item);
                this.bindToUsername(user, stack1);
                EntityItem entity = new EntityItem(event.entityItem.field_70170_p, event.entityItem.field_70165_t, event.entityItem.field_70163_u, event.entityItem.field_70161_v, stack1);
                entity.field_70159_w = event.entityItem.field_70159_w;
                entity.field_70181_x = event.entityItem.field_70181_x;
                entity.field_70179_y = event.entityItem.field_70179_y;
                entity.field_70292_b = event.entityItem.field_70292_b;
                entity.field_145804_b = event.entityItem.field_145804_b;
                entity.field_70170_p.func_72838_d((Entity)entity);
            }
        }
    }

    @Override
    public void onValidPlayerWornTick(ItemStack stack, EntityPlayer player) {
        ((ItemOdinRing)ModItems.odinRing).onValidPlayerWornTick(stack, player);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    @Override
    public List<ChunkCoordinates> getWireframesToDraw(EntityPlayer player, ItemStack stack) {
        return ((IWireframeCoordinateListProvider)ModItems.lokiRing).getWireframesToDraw(player, stack);
    }

    @Override
    public ChunkCoordinates getSourceWireframe(EntityPlayer player, ItemStack stack) {
        return ((IExtendedWireframeCoordinateListProvider)ModItems.lokiRing).getSourceWireframe(player, stack);
    }

    @Override
    public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111147_b(this.attributes);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111148_a(this.attributes);
    }

    void fillModifiers(Multimap<String, AttributeModifier> attributes, ItemStack stack) {
        attributes.put((Object)SharedMonsterAttributes.field_111267_a.func_111108_a(), (Object)new AttributeModifier(ItemAesirRing.getBaubleUUID(stack), "Bauble modifier", 20.0, 0));
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.relicAesirRing;
    }
}

