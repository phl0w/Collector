package org.dracula.collector;

import org.dracula.collector.nodes.BankWalking;
import org.dracula.collector.nodes.Banking;
import org.dracula.collector.nodes.MineWalking;
import org.dracula.collector.nodes.Mining;
import org.dracula.collector.resources.Paint;
import org.dracula.collector.resources.SkillCalculations;
import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Skills;

import java.awt.*;

import static org.dracula.collector.resources.Variables.oresMined;

@Manifest(authors = "Dracula", name = "AIO Miner", description = "Mine hard or go home.", version = 1.1)
public class Collector extends ActiveScript implements PaintListener, MessageListener {

    public void onStart() {
        Paint.setSkillCalc(new SkillCalculations(Skills.MINING));
    }

    private static final Tree container = new Tree(new Node[]{new Banking(), new BankWalking(), new MineWalking(), new Mining()});

    @Override
    public int loop() {
        final Node node = container.state();
        if (node != null) {
            container.set(node);
            getContainer().submit(node);
            node.join();
        }
        return 100;
    }

    @Override
    public void messageReceived(final MessageEvent me) {
        final String msg = me.getMessage();
        if (msg.contains("You manage")) {
            oresMined++;
        }
    }


    @Override
    public void onRepaint(final Graphics gg) {
        final Graphics2D g = (Graphics2D) gg;
        Paint.onRepaint(g);
    }
}
