/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileBellethorn
extends SubTileFunctional {
    public static final int RANGE = 6;
    public static final int RANGE_MINI = 1;

    @Override
    public int getColor() {
        return 12203041;
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        int manaToUse = this.getManaCost();
        if (this.ticksExisted % 5 == 0) {
            int range = this.getRange();
            List entities = this.supertile.func_145831_w().func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - range), (double)this.supertile.field_145848_d, (double)(this.supertile.field_145849_e - range), (double)(this.supertile.field_145851_c + range + 1), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e + range + 1)));
            IEntitySelector selector = this.getSelector();
            for (EntityLivingBase entity : entities) {
                if (!selector.func_82704_a((Entity)entity) || entity.field_70737_aN != 0 || this.mana < manaToUse) continue;
                int dmg = 4;
                if (entity instanceof EntityWitch) {
                    dmg = 20;
                }
                entity.func_70097_a(DamageSource.field_76376_m, (float)dmg);
                this.mana -= manaToUse;
                break;
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    public int getManaCost() {
        return 24;
    }

    public int getRange() {
        return 6;
    }

    public IEntitySelector getSelector() {
        return new IEntitySelector(){

            public boolean func_82704_a(Entity entity) {
                return !(entity instanceof EntityPlayer);
            }
        };
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.bellethorne;
    }

    public static class Mini
    extends SubTileBellethorn {
        @Override
        public int getRange() {
            return 1;
        }
    }
}

