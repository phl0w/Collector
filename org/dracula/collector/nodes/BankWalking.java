package org.dracula.collector.nodes;

import org.dracula.collector.resources.Methods;
import org.dracula.collector.resources.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;

public class BankWalking extends Node {

    @Override
    public boolean activate() {
        return Inventory.isFull() && !Variables.location.getBank().contains(Players.getLocal());

    }

    @Override
    public void execute() {
        System.out.println("Bank Walking");
        Methods.walkPath(Variables.location.getPath(), false);
    }
}
