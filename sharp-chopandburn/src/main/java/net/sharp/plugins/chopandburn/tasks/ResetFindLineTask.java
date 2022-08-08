package net.sharp.plugins.chopandburn.tasks;

import net.runelite.api.ItemID;
import net.unethicalite.api.items.Inventory;

public class ResetFindLineTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        return !Inventory.contains(ItemID.TEAK_LOGS) && this.findLine;
    }

    @Override
    public int execute() {
        this.findLine = false;
        return 333;
    }
}
