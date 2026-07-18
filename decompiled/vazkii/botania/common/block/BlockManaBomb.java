/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.entity.EntityManaStorm;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockManaBomb
extends BlockMod
implements IManaTrigger,
ILexiconable,
ICraftAchievement {
    public BlockManaBomb() {
        super(Material.field_151575_d);
        this.func_149711_c(12.0f);
        this.func_149672_a(field_149766_f);
        this.func_149663_c("manaBomb");
    }

    @Override
    public void onBurstCollision(IManaBurst burst, World world, int x, int y, int z) {
        if (!burst.isFake() && !world.field_72995_K) {
            world.func_72926_e(2001, x, y, z, BlockManaBomb.func_149682_b((Block)this));
            world.func_147468_f(x, y, z);
            EntityManaStorm storm = new EntityManaStorm(world);
            storm.func_70107_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            world.func_72838_d((Entity)storm);
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.manaBomb;
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.manaBombIgnite;
    }
}

