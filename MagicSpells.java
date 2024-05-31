import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MagicSpells extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType() == Material.STICK) {
            player.launchProjectile(Fireball.class);
            player.sendMessage(ChatColor.RED + "You cast a fireball spell!");
        } else if (item.getType() == Material.BLAZE_ROD) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 1));
            player.sendMessage(ChatColor.GRAY + "You cast an invisibility spell!");
        }
    }
}
