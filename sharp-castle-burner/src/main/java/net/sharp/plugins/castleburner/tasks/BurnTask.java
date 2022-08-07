package net.sharp.plugins.castleburner.tasks;

import net.runelite.api.TileItem;
import net.unethicalite.api.entities.Players;
import net.unethicalite.api.entities.TileItems;
import net.unethicalite.api.items.Inventory;
import net.unethicalite.api.plugins.Task;

public class BurnTask implements Task {
    public boolean validate() {
        var tinderbox = Inventory.getFirst("Tinderbox");
        TileItem log = TileItems.getNearest("Logs");

        //if we have a tinderbox and there's a log nearby
        if (tinderbox != null && log != null) {
            return true;
        }

        return false;
    }

    public int execute() {
        var local = Players.getLocal();
        var tinderbox = Inventory.getFirst("Tinderbox");
        TileItem log = TileItems.getNearest("Logs");

        if (local.isAnimating()) {
            return 333;
        }
        if (log != null) {
            if (local.isMoving()) {
                return 333;
            }

            tinderbox.useOn(log);
        }

        return 500;
    }
}
