/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.data.AnimationMetadataSection
 */
package vazkii.botania.client.render.block;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import vazkii.botania.common.lib.LibObfuscation;

@SideOnly(value=Side.CLIENT)
public class InterpolatedIcon
extends TextureAtlasSprite {
    protected int[][] interpolatedFrameData;
    private Field fanimationMetadata = ReflectionHelper.findField(TextureAtlasSprite.class, (String[])LibObfuscation.ANIMATION_METADATA);

    public InterpolatedIcon(String name) {
        super(name);
        this.fanimationMetadata.setAccessible(true);
    }

    public void func_94219_l() {
        super.func_94219_l();
        try {
            this.updateAnimationInterpolated();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void updateAnimationInterpolated() throws IllegalArgumentException, IllegalAccessException {
        int j;
        int k;
        AnimationMetadataSection animationMetadata = (AnimationMetadataSection)this.fanimationMetadata.get((Object)this);
        double d0 = 1.0 - (double)this.field_110983_h / (double)animationMetadata.func_110472_a(this.field_110973_g);
        int i = animationMetadata.func_110468_c(this.field_110973_g);
        if (i != (k = animationMetadata.func_110468_c((this.field_110973_g + 1) % (j = animationMetadata.func_110473_c() == 0 ? this.field_110976_a.size() : animationMetadata.func_110473_c()))) && k >= 0 && k < this.field_110976_a.size()) {
            int[][] aint = (int[][])this.field_110976_a.get(i);
            int[][] aint1 = (int[][])this.field_110976_a.get(k);
            if (this.interpolatedFrameData == null || this.interpolatedFrameData.length != aint.length) {
                this.interpolatedFrameData = new int[aint.length][];
            }
            for (int l = 0; l < aint.length; ++l) {
                if (this.interpolatedFrameData[l] == null) {
                    this.interpolatedFrameData[l] = new int[aint[l].length];
                }
                if (l >= aint1.length || aint1[l].length != aint[l].length) continue;
                for (int i1 = 0; i1 < aint[l].length; ++i1) {
                    int j1 = aint[l][i1];
                    int k1 = aint1[l][i1];
                    int l1 = (int)((double)((j1 & 0xFF0000) >> 16) * d0 + (double)((k1 & 0xFF0000) >> 16) * (1.0 - d0));
                    int i2 = (int)((double)((j1 & 0xFF00) >> 8) * d0 + (double)((k1 & 0xFF00) >> 8) * (1.0 - d0));
                    int j2 = (int)((double)(j1 & 0xFF) * d0 + (double)(k1 & 0xFF) * (1.0 - d0));
                    this.interpolatedFrameData[l][i1] = j1 & 0xFF000000 | l1 << 16 | i2 << 8 | j2;
                }
            }
            TextureUtil.func_147955_a((int[][])this.interpolatedFrameData, (int)this.field_130223_c, (int)this.field_130224_d, (int)this.field_110975_c, (int)this.field_110974_d, (boolean)false, (boolean)false);
        }
    }
}

