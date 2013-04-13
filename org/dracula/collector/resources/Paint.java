package org.dracula.collector.resources;

import org.powerbot.game.api.methods.input.Mouse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.dracula.collector.resources.Variables.oresMined;

public class Paint {

    private static SkillCalculations mineCalcs;
    private static final Image img1 = getImage("http://i.imgur.com/xUmB5AS.png");
    private static final NumberFormat format = DecimalFormat.getInstance();

    public static void setSkillCalc(final SkillCalculations sc) {
        mineCalcs = sc;
    }

    public static void onRepaint(final Graphics2D g) {
        drawMouse(g);
        g.drawImage(img1, 1, 389, null);
        if (mineCalcs != null) {
            g.drawString(mineCalcs.getTimeElapsedAsString(), 125, 425);
            g.drawString(mineCalcs.getTTL(), 125, 445);
            g.drawString(format.format(mineCalcs.getExpGained()) + " (+" + mineCalcs.getExpPerHr() + ")", 125, 485);
            g.drawString(format.format(mineCalcs.getLvl()) + " (+" + mineCalcs.getLevelsGained() + ")", 125, 505);
            g.drawString(format.format(oresMined) + " (+" + mineCalcs.oresHour() + ")", 125, 525);
        }
    }

    private static void drawMouse(final Graphics2D g) {
        int x = Mouse.getX();
        int y = Mouse.getY();

        g.drawLine(x, y - 10, x, y + 10);
        g.drawLine(x - 10, y, x + 10, y);
    }

    private static Image getImage(final String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
}