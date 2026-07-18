/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 */
package vazkii.botania.client.model;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import vazkii.botania.client.model.IPylonModel;

public class ModelPylon
implements IPylonModel {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation("botania:model/pylon.obj"));

    @Override
    public void renderCrystal() {
        this.model.renderPart("Crystal");
    }

    @Override
    public void renderRing() {
        this.model.renderAllExcept(new String[]{"Crystal", "Ring_Gem01", "Ring_Gem02", "Ring_Gem03", "Ring_Gem04"});
    }

    @Override
    public void renderGems() {
        for (int i = 1; i < 5; ++i) {
            this.model.renderPart("Ring_Gem0" + i);
        }
    }
}

