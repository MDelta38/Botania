/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileRafflowsia
extends SubTileGenerating {
    private static final String TAG_LAST_FLOWER = "lastFlower";
    private static final String TAG_LAST_FLOWER_TIMES = "lastFlowerTimes";
    String lastFlower;
    int lastFlowerTimes;
    private static final int RANGE = 5;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int mana = 2100;
        if (this.getMaxMana() - this.mana >= mana && !this.supertile.func_145831_w().field_72995_K && this.ticksExisted % 40 == 0) {
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 11; ++j) {
                    for (int k = 0; k < 11; ++k) {
                        int x = this.supertile.field_145851_c + i - 5;
                        int y = this.supertile.field_145848_d + j - 5;
                        int z = this.supertile.field_145849_e + k - 5;
                        Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                        TileEntity tile = this.supertile.func_145831_w().func_147438_o(x, y, z);
                        if (!(tile instanceof ISubTileContainer)) continue;
                        SubTileEntity stile = ((ISubTileContainer)tile).getSubTile();
                        String name = stile.getUnlocalizedName();
                        if (!(stile instanceof SubTileGenerating) || !((SubTileGenerating)stile).isPassiveFlower()) continue;
                        boolean last = name.equals(this.lastFlower);
                        if (last) {
                            ++this.lastFlowerTimes;
                        } else {
                            this.lastFlower = name;
                            this.lastFlowerTimes = 1;
                        }
                        float mod = 1.0f / (float)this.lastFlowerTimes;
                        int meta = this.supertile.func_145831_w().func_72805_g(x, y, z) + 1;
                        this.supertile.func_145831_w().func_147468_f(x, y, z);
                        this.supertile.func_145831_w().func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (meta << 12));
                        this.mana = (int)((float)this.mana + (float)mana * mod);
                        this.sync();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74778_a(TAG_LAST_FLOWER, this.lastFlower);
        cmp.func_74768_a(TAG_LAST_FLOWER_TIMES, this.lastFlowerTimes);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.lastFlower = cmp.func_74779_i(TAG_LAST_FLOWER);
        this.lastFlowerTimes = cmp.func_74762_e(TAG_LAST_FLOWER_TIMES);
    }

    @Override
    public void populateDropStackNBTs(List<ItemStack> drops) {
        super.populateDropStackNBTs(drops);
        ItemStack stack = drops.get(0);
        ItemNBTHelper.setString(stack, TAG_LAST_FLOWER, this.lastFlower);
        ItemNBTHelper.setInt(stack, TAG_LAST_FLOWER_TIMES, this.lastFlowerTimes);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        this.lastFlower = ItemNBTHelper.getString(stack, TAG_LAST_FLOWER, "");
        this.lastFlowerTimes = ItemNBTHelper.getInt(stack, TAG_LAST_FLOWER_TIMES, 0);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 5);
    }

    @Override
    public int getColor() {
        return 5254262;
    }

    @Override
    public int getMaxMana() {
        return 9000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.rafflowsia;
    }
}

