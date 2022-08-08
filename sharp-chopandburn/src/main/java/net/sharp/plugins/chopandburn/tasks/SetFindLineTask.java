package net.sharp.plugins.chopandburn.tasks;

import net.runelite.api.ItemID;
import net.unethicalite.api.items.Inventory;

public class SetFindLineTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        return Inventory.isFull() && Inventory.contains(ItemID.TEAK_LOGS) && !this.findLine;
    }

    @Override
    public int execute() {
        this.findLine = true;
        return 333;
    }
}
