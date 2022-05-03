package io.github.stupidgame.testplugin;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractWorld;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitWorld;
import java.util.List;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Testplugin extends JavaPlugin {
  @Override
  public void onEnable() {}

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public MythicMobs getMM() {
    return MythicMobs.inst();
  }

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      String label,
      @NotNull String[] args) {
    if (label.equalsIgnoreCase("test")) {
      Entity e = (Entity) sender;
      World w = e.getWorld();
      AbstractWorld aw = new BukkitWorld(w);
      boolean flag = false;
      List<AbstractEntity> aes = getMM().getEntityManager().getLivingEntities(aw);
      for (AbstractEntity ae : aes) {
        String CustomName = (String) ae.getCustomName();
        if (CustomName != null) {
          sender.sendMessage(CustomName);
          flag = true;
        }
      }
      if (!flag) sender.sendMessage("Mobが見つかりませんでした");
    }
    return true;
  }
}
