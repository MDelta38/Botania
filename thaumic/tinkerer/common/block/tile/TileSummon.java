/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.tiles.TilePedestal
 */
package thaumic.tinkerer.common.block.tile;

import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TilePedestal;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.EnumMobAspect;
import thaumic.tinkerer.common.item.ItemMobAspect;

public class TileSummon
extends TileEntity {
    public void func_145845_h() {
        if (this.field_145850_b.func_82737_E() % 300L == 0L) {
            if (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
                return;
            }
            for (int radius = 1; radius < 6; ++radius) {
                ArrayList<TileEntity> pedestals = new ArrayList<TileEntity>();
                for (int x = this.field_145851_c - radius; x < this.field_145851_c + radius; ++x) {
                    for (int z = this.field_145849_e - radius; z < this.field_145849_e + radius; ++z) {
                        TileEntity tile = this.field_145850_b.func_147438_o(x, this.field_145848_d, z);
                        if (!(tile instanceof TilePedestal) || ((TilePedestal)tile).func_70301_a(0) == null || !(((TilePedestal)tile).func_70301_a(0).func_77973_b() instanceof ItemMobAspect)) continue;
                        pedestals.add(tile);
                    }
                }
                for (int i = 0; i < pedestals.size(); ++i) {
                    for (int j = 0; j < pedestals.size(); ++j) {
                        for (int k = 0; k < pedestals.size(); ++k) {
                            TilePedestal ped1 = (TilePedestal)pedestals.get(i);
                            TilePedestal ped2 = (TilePedestal)pedestals.get(j);
                            TilePedestal ped3 = (TilePedestal)pedestals.get(k);
                            if (ped1 == ped2 || ped2 == ped3 || ped1 == ped3) continue;
                            ArrayList<Aspect> aspects = new ArrayList<Aspect>();
                            aspects.add(ItemMobAspect.getAspect(ped1.func_70301_a(0)));
                            aspects.add(ItemMobAspect.getAspect(ped2.func_70301_a(0)));
                            aspects.add(ItemMobAspect.getAspect(ped3.func_70301_a(0)));
                            for (EnumMobAspect recipe : EnumMobAspect.values()) {
                                boolean isInfused;
                                if (!Arrays.asList(recipe.aspects).containsAll(aspects) || !aspects.containsAll(Arrays.asList(recipe.aspects))) continue;
                                boolean bl = isInfused = ItemMobAspect.isInfused(ped1.func_70301_a(0)) && ItemMobAspect.isInfused(ped2.func_70301_a(0)) && ItemMobAspect.isInfused(ped3.func_70301_a(0));
                                if (isInfused && this.field_145850_b.func_82737_E() % 1200L != 0L) {
                                    return;
                                }
                                if (!isInfused) {
                                    ped1.func_70299_a(0, null);
                                    ped2.func_70299_a(0, null);
                                    ped3.func_70299_a(0, null);
                                }
                                if (!isInfused || ItemMobAspect.lastUsedTabletMatches(ped1.func_70301_a(0), this) && ItemMobAspect.lastUsedTabletMatches(ped2.func_70301_a(0), this) && ItemMobAspect.lastUsedTabletMatches(ped3.func_70301_a(0), this)) {
                                    if (!this.field_145850_b.field_72995_K) {
                                        Entity spawn = EntityList.func_75620_a((String)recipe.toString(), (World)this.field_145850_b);
                                        spawn.func_70012_b((double)this.field_145851_c + 0.5, (double)(this.field_145848_d + 1), (double)this.field_145849_e + 0.5, 0.0f, 0.0f);
                                        if (spawn instanceof EntitySkeleton && this.field_145850_b.field_73011_w.field_76575_d) {
                                            ((EntitySkeleton)spawn).func_82201_a(1);
                                        }
                                        this.field_145850_b.func_72838_d(spawn);
                                        ((EntityLiving)spawn).func_110161_a(null);
                                        ((EntityLiving)spawn).func_70642_aH();
                                    }
                                    if (this.field_145850_b.field_72995_K) {
                                        ThaumicTinkerer.tcProxy.essentiaTrailFx(this.field_145850_b, ped1.field_145851_c, ped1.field_145848_d, ped1.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e, 20, ((Aspect)aspects.get(0)).getColor(), 20.0f);
                                        ThaumicTinkerer.tcProxy.essentiaTrailFx(this.field_145850_b, ped2.field_145851_c, ped2.field_145848_d, ped2.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e, 20, ((Aspect)aspects.get(1)).getColor(), 20.0f);
                                        ThaumicTinkerer.tcProxy.essentiaTrailFx(this.field_145850_b, ped3.field_145851_c, ped3.field_145848_d, ped3.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e, 20, ((Aspect)aspects.get(2)).getColor(), 20.0f);
                                    }
                                }
                                if (isInfused) {
                                    ItemMobAspect.markLastUsedTablet(ped1.func_70301_a(0), this);
                                    ItemMobAspect.markLastUsedTablet(ped2.func_70301_a(0), this);
                                    ItemMobAspect.markLastUsedTablet(ped3.func_70301_a(0), this);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}

