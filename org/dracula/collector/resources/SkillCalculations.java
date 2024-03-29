package org.dracula.collector.resources;

import org.powerbot.core.script.util.Timer;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Time;

public class SkillCalculations {

    private int skill;
    private int startExp;
    private int startLvl;
    private static Timer t = null;

    public SkillCalculations(final int skill) {
        this.skill = skill;
        this.startExp = Skills.getExperience(skill);
        this.startLvl = Skills.getRealLevel(skill);
        if (t == null) t = new Timer(0);
    }

    public int oresHour() {
        return (int) Math.round(Variables.oresMined * 3600000d / getTimeElapsed());
    }

    public int getCurrentExp() {
        return Skills.getExperience(skill);
    }

    public int getExpGained() {
        return Skills.getExperience(skill) - startExp;
    }

    public int getLevelsGained() {
        return Skills.getRealLevel(skill) - startLvl;
    }

    public int getExpPerHr() {
        return (int) ((getExpGained()) * 3600000D / t.getElapsed());
    }

    public int getLvl() {
        return Skills.getLevel(skill);
    }

    public String getTTL() {
        if (getExpGained() > 0)
            return Time
                    .format((long) ((Skills.getExperienceRequired(getLvl() + 1)
                            - getCurrentExp()) * 3600000D / getExpPerHr()));
        return "00:00:00";
    }

    public int getPercentToLvl() {
        final int lastXPNeed = Skills.getExperienceRequired(getLvl());
        final int nextXPNeed = Skills.getExperienceRequired(getLvl() + 1);
        final double totalXPNeed = nextXPNeed - lastXPNeed;
        final double gainFromLevel = getCurrentExp() - lastXPNeed;
        return (int) ((gainFromLevel / totalXPNeed) * 100);
    }

    public long getTimeElapsed() {
        return t.getElapsed();
    }

    public String getTimeElapsedAsString() {
        return t.toElapsedString();
    }

}
