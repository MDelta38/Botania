/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.event.ServerChatEvent
 *  org.apache.commons.lang3.text.WordUtils
 */
package vazkii.botania.common.block.tile.corporea;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.ServerChatEvent;
import org.apache.commons.lang3.text.WordUtils;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaAutoCompleteController;
import vazkii.botania.api.corporea.ICorporeaRequestor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.core.helper.MathHelper;

public class TileCorporeaIndex
extends TileCorporeaBase
implements ICorporeaRequestor {
    public static final double RADIUS = 2.5;
    private static InputHandler input;
    public static final Set<TileCorporeaIndex> indexes;
    private static final Map<Pattern, IRegexStacker> patterns;
    public int ticks = 0;
    public int ticksWithCloseby = 0;
    public float closeby = 0.0f;
    public boolean hasCloseby;

    public void func_145845_h() {
        super.func_145845_h();
        double x = (double)this.field_145851_c + 0.5;
        double y = (double)this.field_145848_d + 0.5;
        double z = (double)this.field_145849_e + 0.5;
        List players = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(x - 2.5), (double)(y - 2.5), (double)(z - 2.5), (double)(x + 2.5), (double)(y + 2.5), (double)(z + 2.5)));
        this.hasCloseby = false;
        for (EntityPlayer player : players) {
            if (!TileCorporeaIndex.isInRangeOfIndex(player, this)) continue;
            this.hasCloseby = true;
            break;
        }
        float step = 0.2f;
        ++this.ticks;
        if (this.hasCloseby) {
            ++this.ticksWithCloseby;
            if (this.closeby < 1.0f) {
                this.closeby += step;
            }
        } else if (this.closeby > 0.0f) {
            this.closeby -= step;
        }
        if (!this.func_145837_r() && !indexes.contains(this)) {
            indexes.add(this);
        }
    }

    public void func_145843_s() {
        super.func_145843_s();
        indexes.remove(this);
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        indexes.remove(this);
    }

    public int func_70302_i_() {
        return 0;
    }

    public String func_145825_b() {
        return "corporeaIndex";
    }

    @Override
    public void doCorporeaRequest(Object request, int count, ICorporeaSpark spark) {
        if (!(request instanceof String)) {
            return;
        }
        List<ItemStack> stacks = CorporeaHelper.requestItem((String)request, count, spark, true);
        spark.onItemsRequested(stacks);
        for (ItemStack stack : stacks) {
            if (stack == null) continue;
            EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, stack);
            this.field_145850_b.func_72838_d((Entity)item);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isInRangeOfIndex(EntityPlayer player, TileCorporeaIndex index) {
        if (player.field_70170_p.field_73011_w.field_76574_g != index.field_145850_b.field_73011_w.field_76574_g) return false;
        if (!((double)MathHelper.pointDistancePlane((double)index.field_145851_c + 0.5, (double)index.field_145849_e + 0.5, player.field_70165_t, player.field_70161_v) < 2.5)) return false;
        double d = (double)index.field_145848_d + 0.5 - player.field_70163_u;
        double d2 = player.field_70170_p.field_72995_K ? 0.0 : 1.6;
        if (!(Math.abs(d + d2) < 5.0)) return false;
        return true;
    }

    public static void addPattern(String pattern, IRegexStacker stacker) {
        patterns.put(Pattern.compile(pattern), stacker);
    }

    public static int i(Matcher m, int g) {
        try {
            int i = Math.abs(Integer.parseInt(m.group(g)));
            return i;
        }
        catch (NumberFormatException e) {
            return 0;
        }
    }

    public static InputHandler getInputHandler() {
        if (input == null) {
            input = new InputHandler();
        }
        return input;
    }

    static {
        indexes = Collections.newSetFromMap(new WeakHashMap());
        patterns = new LinkedHashMap<Pattern, IRegexStacker>();
        TileCorporeaIndex.addPattern("(.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 1;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("a??n?? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 1;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(\\d+)x?(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return TileCorporeaIndex.i(m, 1);
            }

            @Override
            public String getName(Matcher m) {
                return m.group(2);
            }
        });
        TileCorporeaIndex.addPattern("(?:a )?stack(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 64;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(\\d+)x?? stacks?(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 64 * TileCorporeaIndex.i(m, 1);
            }

            @Override
            public String getName(Matcher m) {
                return m.group(2);
            }
        });
        TileCorporeaIndex.addPattern("(?:a )?stack (?:(?:and)|(?:\\+)) (\\d+)(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 64 + TileCorporeaIndex.i(m, 1);
            }

            @Override
            public String getName(Matcher m) {
                return m.group(2);
            }
        });
        TileCorporeaIndex.addPattern("(\\d+)x?? stacks? (?:(?:and)|(?:\\+)) (\\d+)x?(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 64 * TileCorporeaIndex.i(m, 1) + TileCorporeaIndex.i(m, 2);
            }

            @Override
            public String getName(Matcher m) {
                return m.group(3);
            }
        });
        TileCorporeaIndex.addPattern("(?:a )?half (?:of )?(?:a )?stack(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 32;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(?:a )?quarter (?:of )?(?:a )?stack(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 16;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(?:a )?dozen(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 12;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(\\d+)x?? dozens?(?: of)? (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 12 * TileCorporeaIndex.i(m, 1);
            }

            @Override
            public String getName(Matcher m) {
                return m.group(2);
            }
        });
        TileCorporeaIndex.addPattern("(?:all|every) (?:(?:of|the) )?(.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return Integer.MAX_VALUE;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(?:the )?answer to life,? the universe and everything (?:of )?(.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 42;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
        TileCorporeaIndex.addPattern("(?:count|show|display|tell) (.+)", new IRegexStacker(){

            @Override
            public int getCount(Matcher m) {
                return 0;
            }

            @Override
            public String getName(Matcher m) {
                return m.group(1);
            }
        });
    }

    public static interface IRegexStacker {
        public int getCount(Matcher var1);

        public String getName(Matcher var1);
    }

    public static final class InputHandler
    implements ICorporeaAutoCompleteController {
        public InputHandler() {
            CorporeaHelper.registerAutoCompleteController(this);
        }

        @SubscribeEvent(priority=EventPriority.HIGHEST)
        public void onChatMessage(ServerChatEvent event) {
            List<TileCorporeaIndex> nearbyIndexes = InputHandler.getNearbyIndexes((EntityPlayer)event.player);
            if (!nearbyIndexes.isEmpty()) {
                String msg = event.message.toLowerCase().trim();
                for (TileCorporeaIndex index : nearbyIndexes) {
                    ItemStack stack;
                    ICorporeaSpark spark;
                    if (((TileCorporeaIndex)index).field_145850_b.field_72995_K || (spark = index.getSpark()) == null) continue;
                    String name = "";
                    int count = 0;
                    for (Pattern pattern : patterns.keySet()) {
                        Matcher matcher = pattern.matcher(msg);
                        if (!matcher.matches()) continue;
                        IRegexStacker stacker = (IRegexStacker)patterns.get(pattern);
                        count = stacker.getCount(matcher);
                        name = stacker.getName(matcher).toLowerCase().trim();
                        pattern.toString();
                    }
                    if (name.equals("this") && (stack = event.player.func_71045_bC()) != null) {
                        name = stack.func_82833_r().toLowerCase().trim();
                    }
                    index.doCorporeaRequest(name, count, spark);
                    event.player.func_145747_a(new ChatComponentTranslation("botaniamisc.requestMsg", new Object[]{count, WordUtils.capitalizeFully((String)name), CorporeaHelper.lastRequestMatches, CorporeaHelper.lastRequestExtractions}).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.LIGHT_PURPLE)));
                    if (CorporeaHelper.lastRequestExtractions < 50000) continue;
                    event.player.func_71064_a((StatBase)ModAchievements.superCorporeaRequest, 1);
                }
                event.setCanceled(true);
            }
        }

        public static List<TileCorporeaIndex> getNearbyIndexes(EntityPlayer player) {
            ArrayList<TileCorporeaIndex> indexList = new ArrayList<TileCorporeaIndex>();
            for (TileCorporeaIndex index : indexes) {
                if (!TileCorporeaIndex.isInRangeOfIndex(player, index) || ((TileCorporeaIndex)index).field_145850_b.field_72995_K != player.field_70170_p.field_72995_K) continue;
                indexList.add(index);
            }
            return indexList;
        }

        @Override
        @SideOnly(value=Side.CLIENT)
        public boolean shouldAutoComplete() {
            return !InputHandler.getNearbyIndexes((EntityPlayer)Minecraft.func_71410_x().field_71439_g).isEmpty();
        }
    }
}

