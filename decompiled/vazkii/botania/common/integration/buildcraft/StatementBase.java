/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.statements.IStatement
 *  buildcraft.api.statements.IStatementParameter
 *  net.minecraft.util.IIcon
 */
package vazkii.botania.common.integration.buildcraft;

import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementParameter;
import net.minecraft.util.IIcon;

public abstract class StatementBase
implements IStatement {
    protected IIcon icon;

    public IIcon getIcon() {
        return this.icon;
    }

    public int maxParameters() {
        return 0;
    }

    public int minParameters() {
        return 0;
    }

    public IStatementParameter createParameter(int index) {
        return null;
    }

    public IStatement rotateLeft() {
        return this;
    }
}

