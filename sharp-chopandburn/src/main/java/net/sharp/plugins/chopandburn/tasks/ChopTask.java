package net.sharp.plugins.chopandburn.tasks;

import net.sharp.plugins.chopandburn.framework.ChopAndBurnTask;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileObjects;
import net.unethicalite.api.items.Inventory;

import java.util.Comparator;

public class ChopTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var shouldBurn = this.isShouldBurnInventory();
        var ita = this.isTreeAlive();
        var iif = Inventory.isFull();

        var allTrue = this.isShouldBurnInventory() && this.isTreeAlive() && !iif;

        return ita && shouldBurn == false && !Inventory.isFull();
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
                .getSurrounding(Players.getLocal().getWorldLocation(), 15, "Oak")
                .stream()
                .min(Comparator.comparing(x -> x.distanceTo(Players.getLocal().getWorldLocation())))
                .orElse(null);

        if (tree == null)
        {
            return 1000;
        }


        if (tree != null)
        {
            tree.interact(x -> x.contains("Chop"));
        }

        return 1000;
    }
}
