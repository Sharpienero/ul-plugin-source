package net.sharp.plugins.chopandburn.tasks;

import lombok.Getter;
import lombok.Setter;
import net.runelite.api.ItemID;
import net.unethicalite.api.items.Inventory;

@Getter
@Setter
public class FindBurnLine extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        return this.findLine && Inventory.isFull() && Inventory.contains(ItemID.TEAK_LOGS);
    }

    @Override
    public int execute() {
        return -1;
    }
}
