/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$MCVersion
 *  cpw.mods.fml.relauncher.IFMLLoadingPlugin$Name
 */
package witchinggadgets.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.util.Map;
import witchinggadgets.asm.WGCoreTransformer;
import witchinggadgets.asm.WitchingGadgetsCore;

@IFMLLoadingPlugin.MCVersion(value="1.7.10")
@IFMLLoadingPlugin.Name(value="WitchingGadgets Core")
public class WitchingGadgetsCoreLoader
implements IFMLLoadingPlugin {
    public static final String NAME = "WitchingGadgets Core";

    public String[] getASMTransformerClass() {
        return new String[]{WGCoreTransformer.class.getName()};
    }

    public String getModContainerClass() {
        return WitchingGadgetsCore.class.getName();
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

