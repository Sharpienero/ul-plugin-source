package net.sharp.plugins.chopandburn.tasks;

import net.unethicalite.api.items.Inventory;

import java.util.Locale;

public class SetFindLineTask extends ChopAndBurnTask {
    @Override
    public boolean validate() {
        var hasLogs = Inventory.getFirst(x -> x.getName().toLowerCase(Locale.ROOT).contains("logs"));
        return Inventory.isFull() && hasLogs != null && !this.findLine;
    }

    @Override
    public int execute() {
        this.findLine = true;
        return 333;
    }
}
