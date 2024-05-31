import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class PlayerEconomy extends JavaPlugin {

    private Map<String, Integer> balances;

    @Override
    public void onEnable() {
        balances = new HashMap<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerName = player.getName();

            switch (command.getName().toLowerCase()) {
                case "balance":
                    int balance = balances.getOrDefault(playerName, 0);
                    player.sendMessage(ChatColor.GREEN + "Your balance: " + balance + " coins");
                    return true;

                case "earn":
                    balances.put(playerName, balances.getOrDefault(playerName, 0) + 100);
                    player.sendMessage(ChatColor.GREEN + "You earned 100 coins!");
                    return true;

                case "pay":
                    if (args.length == 2) {
                        Player recipient = getServer().getPlayer(args[0]);
                        int amount;
                        try {
                            amount = Integer.parseInt(args[1]);
                        } catch (NumberFormatException e) {
                            player.sendMessage(ChatColor.RED + "Invalid amount.");
                            return false;
                        }

                        if (recipient != null && recipient.isOnline() && amount > 0 && balances.getOrDefault(playerName, 0) >= amount) {
                            balances.put(playerName, balances.get(playerName) - amount);
                            balances.put(recipient.getName(), balances.getOrDefault(recipient.getName(), 0) + amount);
                            player.sendMessage(ChatColor.GREEN + "You paid " + amount + " coins to " + recipient.getName() + ".");
                            recipient.sendMessage(ChatColor.GREEN + "You received " + amount + " coins from " + playerName + ".");
                        } else {
                            player.sendMessage(ChatColor.RED + "Payment failed.");
                        }
                        return true;
                    }
                    break;
            }
        }
        return false;
    }
}
