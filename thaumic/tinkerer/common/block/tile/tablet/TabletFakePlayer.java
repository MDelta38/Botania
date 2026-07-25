/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  thaumcraft.common.lib.FakeThaumcraftPlayer
 */
package thaumic.tinkerer.common.block.tile.tablet;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import thaumcraft.common.lib.FakeThaumcraftPlayer;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;

public class TabletFakePlayer
extends FakeThaumcraftPlayer {
    TileAnimationTablet tablet;

    public TabletFakePlayer(TileAnimationTablet tablet) {
        super(tablet.func_145831_w(), new GameProfile(UUID.fromString("a8f026a0-135b-11e4-9191-0800200c9a66"), "[ThaumcraftTablet]"));
        this.tablet = tablet;
    }

    public void func_70106_y() {
        this.field_71071_by.func_146027_a(null, -1);
        super.func_70106_y();
    }

    public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {
    }

    public void func_70071_h_() {
        this.field_71075_bZ.field_75098_d = false;
        this.field_70165_t = (double)this.tablet.field_145851_c + 0.5;
        this.field_70163_u = (double)this.tablet.field_145848_d + 1.6;
        this.field_70161_v = (double)this.tablet.field_145849_e + 0.5;
        if (this.field_70153_n != null) {
            this.field_70153_n.field_70154_o = null;
        }
        if (this.field_70154_o != null) {
            this.field_70154_o.field_70153_n = null;
        }
        this.field_70153_n = null;
        this.field_70154_o = null;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.func_70606_j(20.0f);
        this.field_70128_L = false;
        int meta = this.tablet.func_145832_p() & 7;
        int rotation = meta == 2 ? 180 : (meta == 3 ? 0 : (meta == 4 ? 90 : -90));
        this.field_70177_z = this.field_70759_as = (float)rotation;
        this.field_70125_A = -15.0f;
        for (int i = 0; i < this.field_71071_by.func_70302_i_(); ++i) {
            ItemStack stack;
            if (i == this.field_71071_by.field_70461_c || (stack = this.field_71071_by.func_70301_a(i)) == null) continue;
            this.func_70099_a(stack, 1.0f);
            this.field_71071_by.func_70299_a(i, null);
        }
    }

    public void func_145747_a(IChatComponent var1) {
    }

    public ChunkCoordinates func_82114_b() {
        return new ChunkCoordinates(this.tablet.field_145851_c, this.tablet.field_145848_d, this.tablet.field_145849_e);
    }
}

