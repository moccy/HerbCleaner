package com.xdxdxdcat.bots.herbCleaner;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceWindows;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;

import com.runemate.game.api.script.framework.LoopingScript;

import javafx.application.Platform;

public final class HerbCleaner extends LoopingScript {
    private int herbsLeft;
    private String herbToClean;

    @Override
    public void onStart(String... args)
    {
        Platform.runLater(() -> new HerbUI(this));
    }

    @Override
    public void onLoop() {

        if(herbToClean != null)
        {
            if(!Inventory.isEmpty())
            {
                System.out.println("Depositing inventory because inventory not empty.");
                DepositInventory();
            }
            if(Bank.isOpen())
            {
                herbsLeft = Bank.getQuantity(herbToClean);
                if(herbsLeft <= 0)
                {
                    if(Bank.isOpen())
                    {
                        Bank.close();
                    }
                    com.runemate.game.api.client.ClientUI.sendTrayNotification("Finished cleaning.");
                    System.out.println("No more herbs. Bot ending.");
                    stop();
                } else {
                    System.out.println("Withdrawing herbs");
                    if(herbsLeft < 28)
                    {
                        Bank.withdraw(herbToClean, Bank.getQuantity(herbToClean));
                        Bank.close();
                    } else {
                        Bank.withdraw(herbToClean, 28);
                        Bank.close();
                    }
                }
            } else {
                Bank.open();
                herbsLeft = Bank.getQuantity(herbToClean);
                if(herbsLeft <= 0)
                {
                    System.out.println("No more herbs. Bot ending.");
                    stop();
                } else {
                    System.out.println("Withdrawing herbs");
                    if(herbsLeft < 28)
                    {
                        Bank.withdraw(herbToClean, Bank.getQuantity(herbToClean));
                        Bank.close();
                    } else {
                        Bank.withdraw(herbToClean, 28);
                        Bank.close();
                    }
                }
            }
            if(!InterfaceWindows.getInventory().isOpen())
            {
                InterfaceWindows.getInventory().open();
            }
            cleanHerbs();
            DepositInventory();
        } // No input herb.
    }

    private void DepositInventory()
    {
        if(Bank.open())
        {
            Bank.depositInventory();
        }
        else {
            try
            {
                Bank.open();
                Bank.depositInventory();
            }
            catch (Exception e) {
                System.out.println("No bank nearby.");
                throw e;
            }
        }
    }

    void setHerb(String selectedItem) {
        herbToClean = selectedItem;
        System.out.println(selectedItem + " Selected!");
    }

    private void cleanHerbs()
    {
        for(SpriteItem herb : Inventory.getItems(herbToClean))
        {
            herb.interact("Clean");
        }
    }

}
