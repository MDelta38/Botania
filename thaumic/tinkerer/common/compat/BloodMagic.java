/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  WayofTime.alchemicalWizardry.common.entity.projectile.EntityParticleBeam
 */
package thaumic.tinkerer.common.compat;

import WayofTime.alchemicalWizardry.common.entity.projectile.EntityParticleBeam;
import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;

public class BloodMagic {
    public static void setupClass() {
        ItemFocusDeflect.DeflectBlacklist.add(EntityParticleBeam.class);
    }
}

