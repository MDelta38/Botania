/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.client.challenge;

public enum EnumChallengeLevel {
    EASY("botania.challengelevel.easy"),
    NORMAL("botania.challengelevel.normal"),
    HARD("botania.challengelevel.hard"),
    LUNATIC("botania.challengelevel.lunatic");

    String name;

    private EnumChallengeLevel(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

