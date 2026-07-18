/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.signature.PassiveFlower;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

@PassiveFlower
public class SubTileHydroangeas
extends SubTilePassiveGenerating {
    private static final String TAG_BURN_TIME = "burnTime";
    private static final String TAG_COOLDOWN = "cooldown";
    private static final int[][] OFFSETS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};
    int burnTime;
    int cooldown;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.cooldown > 0) {
            --this.cooldown;
            for (int i = 0; i < 3; ++i) {
                Botania.proxy.wispFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + 0.5 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145848_d + 0.5 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145849_e + 0.5 + Math.random() * 0.2 - 0.1, 0.1f, 0.1f, 0.1f, (float)Math.random() / 6.0f, (float)(-Math.random()) / 30.0f);
            }
        }
        if (this.burnTime == 0) {
            if (this.mana < this.getMaxMana() && !this.supertile.func_145831_w().field_72995_K) {
                List<int[]> offsets = Arrays.asList(OFFSETS);
                Collections.shuffle(offsets);
                for (int[] offsetArray : offsets) {
                    int[] positions = new int[]{this.supertile.field_145851_c + offsetArray[0], this.supertile.field_145849_e + offsetArray[1]};
                    Material search = this.getMaterialToSearchFor();
                    if (this.supertile.func_145831_w().func_147439_a(positions[0], this.supertile.field_145848_d, positions[1]).func_149688_o() != search || this.getBlockToSearchBelow() != null && this.supertile.func_145831_w().func_147439_a(positions[0], this.supertile.field_145848_d - 1, positions[1]) != this.getBlockToSearchBelow() || this.supertile.func_145831_w().func_72805_g(positions[0], this.supertile.field_145848_d, positions[1]) != 0) continue;
                    if (search != Material.field_151586_h) {
                        this.supertile.func_145831_w().func_147468_f(positions[0], this.supertile.field_145848_d, positions[1]);
                    } else {
                        int waterAround = 0;
                        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
                            if (this.supertile.func_145831_w().func_147439_a(positions[0] + dir.offsetX, this.supertile.field_145848_d, positions[1] + dir.offsetZ).func_149688_o() != search) continue;
                            ++waterAround;
                        }
                        if (waterAround < 2) {
                            this.supertile.func_145831_w().func_147468_f(positions[0], this.supertile.field_145848_d, positions[1]);
                        }
                    }
                    if (this.cooldown == 0) {
                        this.burnTime += this.getBurnTime();
                    } else {
                        this.cooldown = this.getCooldown();
                    }
                    this.sync();
                    this.playSound();
                    break;
                }
            }
        } else {
            if (this.supertile.func_145831_w().field_73012_v.nextInt(8) == 0) {
                this.doBurnParticles();
            }
            --this.burnTime;
            if (this.burnTime == 0) {
                this.cooldown = this.getCooldown();
                this.sync();
            }
        }
    }

    public void doBurnParticles() {
        Botania.proxy.wispFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + 0.55 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145848_d + 0.55 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145849_e + 0.5, 0.05f, 0.05f, 0.7f, (float)Math.random() / 6.0f, (float)(-Math.random()) / 60.0f);
    }

    public Material getMaterialToSearchFor() {
        return Material.field_151586_h;
    }

    public Block getBlockToSearchBelow() {
        return null;
    }

    public void playSound() {
        this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "random.drink", 0.01f, 0.5f + (float)Math.random() * 0.5f);
    }

    public int getBurnTime() {
        return 40;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    @Override
    public int getMaxMana() {
        return 150;
    }

    @Override
    public int getColor() {
        return 5451744;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.hydroangeas;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.func_74768_a(TAG_BURN_TIME, this.burnTime);
        cmp.func_74768_a(TAG_COOLDOWN, this.cooldown);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.burnTime = cmp.func_74762_e(TAG_BURN_TIME);
        this.cooldown = cmp.func_74762_e(TAG_COOLDOWN);
    }

    @Override
    public void populateDropStackNBTs(List<ItemStack> drops) {
        super.populateDropStackNBTs(drops);
        int cooldown = this.cooldown;
        if (this.burnTime > 0) {
            cooldown = this.getCooldown();
        }
        if (cooldown > 0) {
            ItemNBTHelper.setInt(drops.get(0), TAG_COOLDOWN, this.getCooldown());
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        this.cooldown = ItemNBTHelper.getInt(stack, TAG_COOLDOWN, 0);
    }

    @Override
    public boolean canGeneratePassively() {
        return this.burnTime > 0;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        boolean rain = this.supertile.func_145831_w().func_72959_q().func_76935_a(this.supertile.field_145851_c, this.supertile.field_145849_e).func_76744_g() > 0 && (this.supertile.func_145831_w().func_72896_J() || this.supertile.func_145831_w().func_72911_I());
        return rain ? 1 : 2;
    }

    public int getCooldown() {
        return 0;
    }
}

