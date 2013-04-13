package Collector.Resources;

import org.powerbot.game.api.methods.input.Mouse;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import static Collector.Resources.Variables.oresMined;

public class Paint {
    private Graphics g;
    private SkillCalculations mineCalcs;
    private final Image img1 = getImage("http://i.imgur.com/xUmB5AS.png");

    public Paint(Graphics g, SkillCalculations mineCalcs) {
        this.g = g;
        this.mineCalcs = mineCalcs;
    }

    public void onRepaint(Graphics g) {
        drawMouse();
        this.g.drawImage(img1, 1, 389, null);
        this.g.drawString(mineCalcs.getTimeElapsedAsString(), 125, 425);
        this.g.drawString(mineCalcs.getTTL(), 125, 445);
        this.g.drawString(DecimalFormat.getInstance().format(mineCalcs.getExpGained()) + " (+" + mineCalcs.getExpPerHr() + ")", 125, 485);
        this.g.drawString(DecimalFormat.getInstance().format(mineCalcs.getLvl()) + " (+" + mineCalcs.getLevelsGained() + ")", 125, 505);
        this.g.drawString(DecimalFormat.getInstance().format(oresMined) + " (+" + mineCalcs.oresHour() + ")", 125, 525);
    }

    private void drawMouse() {
        int x = Mouse.getX();
        int y = Mouse.getY();

        g.drawLine(x, y - 10, x, y + 10);
        g.drawLine(x - 10, y, x + 10, y);
    }

    private Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            return null;
        }
    }
}
