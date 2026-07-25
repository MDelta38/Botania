/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IFacadePart;
import appeng.api.parts.IPart;
import net.minecraftforge.common.util.ForgeDirection;

public class SelectedPart {
    public final IPart part;
    public final IFacadePart facade;
    public final ForgeDirection side;

    public SelectedPart() {
        this.part = null;
        this.facade = null;
        this.side = ForgeDirection.UNKNOWN;
    }

    public SelectedPart(IPart part, ForgeDirection side) {
        this.part = part;
        this.facade = null;
        this.side = side;
    }

    public SelectedPart(IFacadePart facade, ForgeDirection side) {
        this.part = null;
        this.facade = facade;
        this.side = side;
    }
}

