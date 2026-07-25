/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.ritual.Circle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.RitualTraits;
import com.emoniph.witchery.ritual.Sacrifice;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteRegistry {
    private static final RiteRegistry INSTANCE = new RiteRegistry();
    final ArrayList<Ritual> rituals = new ArrayList();

    public static RiteRegistry instance() {
        return INSTANCE;
    }

    private RiteRegistry() {
    }

    public final List<Ritual> getRituals() {
        return this.rituals;
    }

    public static Ritual addRecipe(int ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle ... circles) {
        Ritual ritual = new Ritual((byte)ritualID, bookIndex, rite, initialSacrifice, traits, circles);
        RiteRegistry.instance().rituals.add(ritual);
        return ritual;
    }

    public Ritual getRitual(byte ritualID) {
        return this.rituals.get(ritualID - 1);
    }

    public List<Ritual> getSortedRituals() {
        ArrayList<Ritual> sortedRituals = new ArrayList<Ritual>();
        sortedRituals.addAll(this.rituals);
        Collections.sort(sortedRituals, new IndexComparitor());
        return sortedRituals;
    }

    public static void RiteError(String translationID, String username, World world) {
        if (world != null && !world.field_72995_K && username != null) {
            for (Object obj : world.field_73010_i) {
                EntityPlayer worldPlayer;
                if (!(obj instanceof EntityPlayer) || !(worldPlayer = (EntityPlayer)obj).func_70005_c_().equals(username)) continue;
                RiteRegistry.RiteError(translationID, worldPlayer, world);
                return;
            }
        }
    }

    public static void RiteError(String translationID, EntityPlayer player, World world) {
        if (world != null && !world.field_72995_K && player != null) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, translationID, new Object[0]);
        }
    }

    public static class IndexComparitor
    implements Comparator<Ritual> {
        @Override
        public int compare(Ritual o1, Ritual o2) {
            return Integer.valueOf(o1.bookIndex).compareTo(o2.bookIndex);
        }
    }

    public static class Ritual {
        private String unlocalizedName;
        public final Rite rite;
        final Sacrifice initialSacrifice;
        final EnumSet<RitualTraits> traits;
        final Circle[] circles;
        final byte ritualID;
        final int bookIndex;
        boolean visibleInBook;
        private boolean consumeAttunedStoneCharged = false;
        private boolean consumeNecroStone = false;

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        public Ritual setUnlocalizedName(String unlocalizedName) {
            this.unlocalizedName = unlocalizedName;
            return this;
        }

        public String getLocalizedName() {
            if (this.unlocalizedName != null) {
                return Witchery.resource(this.getUnlocalizedName());
            }
            return this.toString();
        }

        Ritual(byte ritualID, int bookIndex, Rite rite, Sacrifice initialSacrifice, EnumSet<RitualTraits> traits, Circle[] circles) {
            this.ritualID = ritualID;
            this.bookIndex = bookIndex;
            this.rite = rite;
            this.initialSacrifice = initialSacrifice;
            this.traits = traits;
            this.circles = circles;
            this.visibleInBook = true;
        }

        public String getDescription() {
            StringBuffer sb = new StringBuffer();
            sb.append("\u00a7n");
            sb.append(this.getLocalizedName());
            sb.append("\u00a7r");
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Const.BOOK_NEWLINE);
            this.initialSacrifice.addDescription(sb);
            return sb.toString();
        }

        public boolean isMatch(World world, int posX, int posY, int posZ, Circle[] nearbyCircles, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks, boolean isDaytime, boolean isRaining, boolean isThundering) {
            if (this.traits.contains((Object)RitualTraits.ONLY_AT_NIGHT) && isDaytime || this.traits.contains((Object)RitualTraits.ONLY_AT_DAY) && !isDaytime || this.traits.contains((Object)RitualTraits.ONLY_IN_RAIN) && !isRaining || this.traits.contains((Object)RitualTraits.ONLY_IN_STROM) && !isThundering || this.traits.contains((Object)RitualTraits.ONLY_OVERWORLD) && world.field_73011_w.field_76574_g != 0) {
                return false;
            }
            if (this.circles.length > 0) {
                ArrayList<Circle> circlesToFind = new ArrayList<Circle>(Arrays.asList(this.circles));
                for (Circle circle : nearbyCircles) {
                    circle.removeIfRequired(circlesToFind);
                    if (circlesToFind.size() == 0) break;
                }
                if (circlesToFind.size() > 0) {
                    return false;
                }
            }
            return this.initialSacrifice.isMatch(world, posX, posY, posZ, this.getMaxDistance(), entities, grassperStacks);
        }

        public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds) {
            int maxDistance = this.getMaxDistance();
            this.initialSacrifice.addSteps(steps, bounds, maxDistance);
            this.addRiteSteps(steps, 0);
        }

        private int getMaxDistance() {
            int maxDistance = this.circles.length > 0 ? 0 : 4;
            for (Circle circle : this.circles) {
                maxDistance = Math.max(circle.getRadius(), maxDistance);
            }
            return maxDistance;
        }

        public byte getRitualID() {
            return this.ritualID;
        }

        public void addRiteSteps(ArrayList<RitualStep> ritualSteps, int stage) {
            this.rite.addSteps(ritualSteps, stage);
        }

        public byte[] getCircles() {
            byte[] result = new byte[this.circles.length];
            int index = 0;
            for (Circle circle : this.circles) {
                result[index++] = (byte)circle.getTextureIndex();
            }
            return result;
        }

        public Ritual setShowInBook(boolean show) {
            this.visibleInBook = show;
            return this;
        }

        public boolean showInBook() {
            return this.visibleInBook;
        }

        public boolean isConsumeAttunedStoneCharged() {
            return this.consumeAttunedStoneCharged;
        }

        public void setConsumeAttunedStoneCharged() {
            this.consumeAttunedStoneCharged = true;
        }

        public boolean isConsumeNecroStone() {
            return this.consumeNecroStone;
        }

        public void setConsumeNecroStone() {
            this.consumeNecroStone = true;
        }
    }
}

