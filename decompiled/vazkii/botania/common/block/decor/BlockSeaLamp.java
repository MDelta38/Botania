/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor;

import cpw.mods.fml.common.Optional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.integration.coloredlights.ColoredLightHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockSeaLamp
extends BlockMod
implements ILexiconable {
    int coloredLight = -1;

    public BlockSeaLamp() {
        super(Material.field_151592_s);
        this.func_149711_c(0.3f);
        this.func_149672_a(field_149778_k);
        this.func_149715_a(1.0f);
        this.func_149663_c("seaLamp");
    }

    @Optional.Method(modid="easycoloredlights")
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return this.coloredLight == -1 ? (this.coloredLight = ColoredLightHelper.makeRGBLightValue(85.0f, 136.0f, 125.0f, this.originalLight)) : this.coloredLight;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.prismarine;
    }
}

