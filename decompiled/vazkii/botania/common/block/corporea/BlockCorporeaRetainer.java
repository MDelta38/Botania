/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.corporea;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.corporea.TileCorporeaRetainer;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockCorporeaRetainer
extends BlockModContainer
implements ILexiconable,
ICraftAchievement {
    public BlockCorporeaRetainer() {
        super(Material.field_151573_f);
        this.func_149711_c(5.5f);
        this.func_149672_a(field_149777_j);
        this.func_149663_c("corporeaRetainer");
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            ((TileCorporeaRetainer)world.func_147438_o(x, y, z)).fulfilRequest();
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int s) {
        return ((TileCorporeaRetainer)world.func_147438_o(x, y, z)).hasPendingRequest() ? 15 : 0;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileCorporeaRetainer();
    }

    @Override
    public Achievement getAchievementOnCraft(ItemStack stack, EntityPlayer player, IInventory matrix) {
        return ModAchievements.corporeaCraft;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.corporeaRetainer;
    }
}

