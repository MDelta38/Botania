/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBrazier;
import com.emoniph.witchery.blocks.BlockWitchCrop;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Const;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BrazierRecipes {
    private static final double POLTERGEIST_CHANCE = 0.05;
    private static final BrazierRecipes INSTANCE = new BrazierRecipes();
    public final ArrayList<BrazierRecipe> recipes = new ArrayList();
    public static final BrazierRecipe SPECTRES = new BrazierRecipe("witchery.brazier.spectre", true, TimeUtil.secsToTicks(30), new ItemStack[]{Witchery.Items.GENERIC.itemWormwood.createStack(), Witchery.Items.GENERIC.itemBatWool.createStack(), Witchery.Items.GENERIC.itemGraveyardDust.createStack()}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(5, ticks)) {
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, 1.0 + (double)y, 0.5 + (double)z, 0.5, 1.0, 16);
            }
            return 0;
        }

        @Override
        public void onBurnt(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            EntityCreature creature = Infusion.spawnCreature(world, EntitySpectre.class, x, y, z, null, 1, 2, ParticleEffect.INSTANT_SPELL, SoundEffect.NOTE_HARP);
            CreatureUtil.spawnWithEgg((EntityLiving)creature, true);
            if (world.field_73012_v.nextDouble() < 0.05) {
                EntityCreature poltergeist = Infusion.spawnCreature(world, EntityPoltergeist.class, x, y, z, null, 6, 10, ParticleEffect.MOB_SPELL, null);
                CreatureUtil.spawnWithEgg((EntityLiving)poltergeist, true);
            }
        }
    };
    public static final BrazierRecipe BANSHEES = new BrazierRecipe("witchery.brazier.banshee", true, TimeUtil.secsToTicks(30), new ItemStack[]{Witchery.Items.GENERIC.itemWormwood.createStack(), Witchery.Items.GENERIC.itemCondensedFear.createStack(), Witchery.Items.GENERIC.itemGraveyardDust.createStack()}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(5, ticks)) {
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, 1.0 + (double)y, 0.5 + (double)z, 0.5, 1.0, 16);
            }
            return 0;
        }

        @Override
        public void onBurnt(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            EntityCreature creature = Infusion.spawnCreature(world, EntityBanshee.class, x, y, z, null, 1, 2, ParticleEffect.INSTANT_SPELL, SoundEffect.NOTE_HARP);
            CreatureUtil.spawnWithEgg((EntityLiving)creature, true);
            if (world.field_73012_v.nextDouble() < 0.05) {
                EntityCreature poltergeist = Infusion.spawnCreature(world, EntityPoltergeist.class, x, y, z, null, 6, 10, ParticleEffect.MOB_SPELL, null);
                CreatureUtil.spawnWithEgg((EntityLiving)poltergeist, true);
            }
        }
    };
    public static final BrazierRecipe POLTERGEIST = new BrazierRecipe("witchery.brazier.poltergeist", false, TimeUtil.secsToTicks(45), new ItemStack[]{Witchery.Items.GENERIC.itemWormwood.createStack(), Witchery.Items.GENERIC.itemRefinedEvil.createStack(), Witchery.Items.GENERIC.itemFocusedWill.createStack()}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(5, ticks)) {
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, 1.0 + (double)y, 0.5 + (double)z, 0.5, 1.0, 16);
            }
            return 0;
        }

        @Override
        public void onBurnt(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            EntityCreature creature = Infusion.spawnCreature(world, EntityPoltergeist.class, x, y, z, null, 1, 2, ParticleEffect.INSTANT_SPELL, SoundEffect.NOTE_HARP);
            CreatureUtil.spawnWithEgg((EntityLiving)creature, true);
            if (world.field_73012_v.nextDouble() < 0.05) {
                EntityCreature poltergeist = Infusion.spawnCreature(world, EntityPoltergeist.class, x, y, z, null, 6, 10, ParticleEffect.MOB_SPELL, null);
                CreatureUtil.spawnWithEgg((EntityLiving)poltergeist, true);
            }
        }
    };
    public static final BrazierRecipe SMOKE = new BrazierRecipe("witchery.brazier.smoke", true, TimeUtil.minsToTicks(5), new ItemStack[]{new ItemStack(Items.field_151016_H), Witchery.Items.GENERIC.itemQuicklime.createStack(), new ItemStack(Items.field_151114_aO)}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5 + (double)x, y, 0.5 + (double)z, 16.0, 4.0, 64);
            return 0;
        }
    };
    public static final BrazierRecipe STRONG = new BrazierRecipe("witchery.brazier.strong", true, TimeUtil.minsToTicks(5), new ItemStack[]{Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151065_br)}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(3, ticks)) {
                int radius = 4;
                int radiusSq = 16;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(0.5 + (double)x - 4.0), (double)(y - 4), (double)(0.5 + (double)z - 4.0), (double)(0.5 + (double)x + 4.0), (double)(y + 4), (double)(0.5 + (double)z + 4.0));
                List entities = world.func_72872_a(EntityLivingBase.class, bb);
                for (Object obj : entities) {
                    EntityLivingBase entity = (EntityLivingBase)obj;
                    if (!(entity.func_70092_e(0.5 + (double)x, (double)y, 0.5 + (double)z) <= 16.0)) continue;
                    entity.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, TimeUtil.secsToTicks(6), 0));
                }
            }
            return 0;
        }
    };
    public static final BrazierRecipe TOUGH = new BrazierRecipe("witchery.brazier.tough", true, TimeUtil.minsToTicks(5), new ItemStack[]{Witchery.Items.GENERIC.itemTearOfTheGoddess.createStack(), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151065_br)}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(3, ticks)) {
                int radius = 4;
                int radiusSq = 16;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(0.5 + (double)x - 4.0), (double)(y - 4), (double)(0.5 + (double)z - 4.0), (double)(0.5 + (double)x + 4.0), (double)(y + 4), (double)(0.5 + (double)z + 4.0));
                List entities = world.func_72872_a(EntityLivingBase.class, bb);
                for (Object obj : entities) {
                    EntityLivingBase entity = (EntityLivingBase)obj;
                    if (!(entity.func_70092_e(0.5 + (double)x, (double)y, 0.5 + (double)z) <= 16.0)) continue;
                    entity.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, TimeUtil.secsToTicks(6), 0));
                }
            }
            return 0;
        }
    };
    public static final BrazierRecipe INVISIBLE = new BrazierRecipe("witchery.brazier.invisible", true, TimeUtil.minsToTicks(10), new ItemStack[]{new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151072_bj)}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (TimeUtil.secondsElapsed(3, ticks)) {
                int radius = 6;
                int radiusSq = 36;
                AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(0.5 + (double)x - 6.0), (double)(y - 6), (double)(0.5 + (double)z - 6.0), (double)(0.5 + (double)x + 6.0), (double)(y + 6), (double)(0.5 + (double)z + 6.0));
                List entities = world.func_72872_a(EntityLivingBase.class, bb);
                for (Object obj : entities) {
                    EntityLivingBase entity = (EntityLivingBase)obj;
                    if (!(entity.func_70092_e(0.5 + (double)x, (double)y, 0.5 + (double)z) <= 36.0)) continue;
                    entity.func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, TimeUtil.secsToTicks(6), 0));
                }
            }
            return 0;
        }
    };
    public static final BrazierRecipe WILTING = new BrazierRecipe("witchery.brazier.wilting", true, TimeUtil.minsToTicks(1), new ItemStack[]{Witchery.Items.GENERIC.itemCondensedFear.createStack(), Witchery.Items.GENERIC.itemWormyApple.createStack(), Witchery.Items.GENERIC.itemGraveyardDust.createStack()}){

        @Override
        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            if (ticks % 5L == 0L) {
                int posZ;
                int posY;
                int offsetY = (int)(ticks % 30L) / 5;
                int radius = 3;
                int diameter = 6;
                int posX = x - 3 + world.field_73012_v.nextInt(7);
                Block block = BlockUtil.getBlock(world, posX, posY = y - 2 + offsetY, posZ = z - 3 + world.field_73012_v.nextInt(7));
                if (block != null && block instanceof IPlantable) {
                    int currentMeta;
                    IPlantable plantable = (IPlantable)block;
                    if ((block instanceof BlockWitchCrop || plantable.getPlantType((IBlockAccess)world, posX, posY, posZ) == EnumPlantType.Crop) && (currentMeta = plantable.getPlantMetadata((IBlockAccess)world, posX, posY, posZ)) > 0) {
                        BlockUtil.setMetadata(world, posX, posY, posZ, currentMeta - 1);
                        ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)x, 1.0 + (double)y, 0.5 + (double)z, 0.3, 0.5, 8);
                        this.healNearbyUndead(world, x, y, z, 3);
                        return 2;
                    }
                }
            }
            return 0;
        }

        private void healNearbyUndead(World world, int x, int y, int z, int radius) {
            AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(x - radius), (double)(y - radius), (double)(z - radius), (double)(x + radius), (double)(y + radius), (double)(z + radius));
            List entities = world.func_72872_a(EntityLivingBase.class, bb);
            for (Object obj : entities) {
                EntityLivingBase entity = (EntityLivingBase)obj;
                if (!CreatureUtil.isUndead((Entity)entity)) continue;
                float maxHealth = entity.func_110138_aP();
                if (!(entity.func_110143_aJ() < maxHealth)) continue;
                entity.func_70691_i(maxHealth * 0.1f);
                ParticleEffect.HEART.send(SoundEffect.NONE, (Entity)entity, 0.5, 1.0, 8);
            }
        }

        @Override
        public boolean getNeedsPower() {
            return false;
        }
    };

    public static BrazierRecipes instance() {
        return INSTANCE;
    }

    private BrazierRecipes() {
    }

    public BrazierRecipe getRecipe(ItemStack[] availableItems) {
        for (BrazierRecipe recipe : this.recipes) {
            if (!recipe.isMatch(availableItems)) continue;
            return recipe;
        }
        return null;
    }

    public static class BrazierRecipe {
        public final int burnTicks;
        public final ItemStack[] items;
        public final boolean inBook;
        public String unlocalizedName;

        private BrazierRecipe(String unlocalizedName, boolean inBook, int burnTicks, ItemStack ... ingredients) {
            this.items = ingredients;
            this.burnTicks = burnTicks;
            this.unlocalizedName = unlocalizedName;
            this.inBook = inBook;
            BrazierRecipes.instance().recipes.add(this);
        }

        public boolean getNeedsPower() {
            return true;
        }

        public ArrayList<ItemStack> getMutableModifiersList() {
            ArrayList<ItemStack> available = new ArrayList<ItemStack>();
            for (ItemStack item : this.items) {
                if (item == null) continue;
                available.add(item);
            }
            return available;
        }

        private boolean isMatch(ItemStack[] availableItems) {
            ArrayList<ItemStack> availableItemList = new ArrayList<ItemStack>();
            for (ItemStack availableItem : availableItems) {
                if (availableItem == null) continue;
                availableItemList.add(availableItem);
            }
            for (ItemStack neededItem : this.items) {
                int index = this.indexOf(availableItemList, neededItem);
                if (index == -1) {
                    return false;
                }
                availableItemList.remove(index);
            }
            return true;
        }

        private int indexOf(ArrayList<ItemStack> list, ItemStack item) {
            for (int i = 0; i < list.size(); ++i) {
                if (!list.get(i).func_77969_a(item)) continue;
                return i;
            }
            return -1;
        }

        public boolean uses(ItemStack ingredient) {
            if (ingredient == null) {
                return false;
            }
            for (ItemStack item : this.items) {
                if (item == null || !item.func_77969_a(ingredient)) continue;
                return true;
            }
            return false;
        }

        public String getDescription() {
            StringBuffer sb = new StringBuffer();
            sb.append("\u00a7n");
            sb.append(Witchery.resource(this.unlocalizedName + ".name"));
            sb.append("\u00a7r");
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Const.BOOK_NEWLINE);
            String description = Witchery.resource(this.unlocalizedName + ".desc");
            if (!description.equals(this.unlocalizedName + ".desc")) {
                sb.append(description);
                sb.append(Const.BOOK_NEWLINE);
                sb.append(Const.BOOK_NEWLINE);
            }
            for (ItemStack stack : this.items) {
                sb.append("\u00a78>\u00a70 ");
                if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150337_Q)) {
                    sb.append(Witchery.resource("witchery.book.mushroomred"));
                } else if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150338_P)) {
                    sb.append(Witchery.resource("witchery.book.mushroombrown"));
                } else if (stack.func_77973_b() == Items.field_151068_bn) {
                    List list = Items.field_151068_bn.func_77832_l(stack);
                    if (list != null && !list.isEmpty()) {
                        PotionEffect effect = (PotionEffect)list.get(0);
                        String s = stack.func_82833_r();
                        if (effect.func_76458_c() > 0) {
                            s = s + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
                        }
                        if (effect.func_76459_b() > 20) {
                            s = s + " (" + Potion.func_76389_a((PotionEffect)effect) + ")";
                        }
                        sb.append(s);
                    } else {
                        sb.append(stack.func_82833_r());
                    }
                } else {
                    sb.append(stack.func_82833_r());
                }
                sb.append(Const.BOOK_NEWLINE);
            }
            return sb.toString();
        }

        public int onBurning(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
            return 0;
        }

        public void onBurnt(World world, int x, int y, int z, long ticks, BlockBrazier.TileEntityBrazier tile) {
        }
    }
}

