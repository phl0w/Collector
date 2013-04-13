package org.dracula.collector.nodes;

import org.dracula.collector.resources.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.Arrays;

public class Banking extends Node {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Variables.location.getBank().contains(Players.getLocal());
    }

    @Override
    public void execute() {
        System.out.println("Bank");
        if (Bank.open()) {
            for (final Item item : Inventory.getItems()) {
                if (Arrays.binarySearch(Variables.pickaxe, item.getId()) < 0 && item.getWidgetChild().getChildStackSize() != 0) {
                    Task.sleep(250, 750);
                    Bank.deposit(item.getId(), 0);
                }
            }
            Bank.close();
        }
    }
}
