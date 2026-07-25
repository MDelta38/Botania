/*
 * Decompiled with CFR 0.152.
 */
package thaumic.tinkerer.client.gui.button;

import java.util.List;
import thaumic.tinkerer.client.gui.button.GuiButtonRP;
import thaumic.tinkerer.client.gui.button.IRadioButton;

public class GuiButtonRPRadio
extends GuiButtonRP
implements IRadioButton {
    List<IRadioButton> linkedButtons;

    public GuiButtonRPRadio(int par1, int par2, int par3, boolean enabled, List<IRadioButton> linkedButtons) {
        super(par1, par2, par3, enabled);
        this.linkedButtons = linkedButtons;
    }

    @Override
    public void enableFromClick() {
        this.setEnabled(true);
        for (IRadioButton button : this.linkedButtons) {
            if (button == this) continue;
            button.updateStatus(this);
        }
    }

    @Override
    public void updateStatus(IRadioButton otherButton) {
        if (otherButton.isEnabled()) {
            this.setEnabled(false);
        }
    }

    @Override
    public boolean isEnabled() {
        return this.buttonEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.buttonEnabled = enabled;
    }
}

