package net.sharp.plugins.chopandburn.tasks;

import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Inventory;

import java.util.Comparator;

public class ChopTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        boolean isTeakAlive = teakAlive();
        boolean isInventoryFull = Inventory.isFull();
        boolean doBurnInventory = this.burnInventory;

        boolean isTest = isTeakAlive || isInventoryFull || doBurnInventory;

        return teakAlive() && this.burnInventory == false && Inventory.isFull() == false;
    }

    @Override
    public int execute() {

        if (Players.getLocal().isAnimating())
        {
            return 1000;
        }

        if (Players.getLocal().isMoving())
        {
            return 333;
        }

        var tree = TileObjects
                .getSurrounding(Players.getLocal().getWorldLocation(), 15, "Teak")
                .stream()
                .min(Comparator.comparing(x -> x.distanceTo(Players.getLocal().getWorldLocation())))
                .orElse(null);

        if (tree == null)
        {
            return 1000;
        }


        if (tree != null)
        {
            tree.interact("Chop down");
        }

        return 1000;
    }
}
