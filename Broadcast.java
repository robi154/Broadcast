package com.github.robi154.Broadcast;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.util.io.*;
import java.io.*;

/**
 *
 * @author Robin
 */
public final class Broadcast extends JavaPlugin {
    /*Variables*/
    private static File config = new File("Broadcast/config/.config");
    private static String title = "";
    
    @Override
    public void onEnable() {
    //Plugin startet
    getLogger().info("Broadcast ist gestartet!  Copyright 2014 by ParadoxDE");
    getLogger().info("Vielen Dank für die Benutzung von Broadcast von ParadoxDE");
    getLogger().info("Überprüfen ob die Config Datei Existiert...");
    Bukkit.broadcastMessage("Broadcast by ParadoxDE wurde gestartet");
    if (!config.exists()) {
        getLogger().warning("Config Datei existiert nicht!... Datei wird erstellt");
        config.getParentFile().mkdirs();
        try {
            config.createNewFile();
            getLogger().finest("Config Datei wurde erstellt. Stelle Standart Parameter ein...");
            settitle("defaultTitle");
            title = getTitle();
        } catch (IOException ex) {
            getLogger().severe("Config Datei konnte nicht erstellt werden");
            getLogger().severe(ex.toString());
        }
    }
    }
    
    @Override
    public void onDisable() {
    //Plugin stoppt
    getLogger().info("Broadcast ist gestoppt!   Copyright 2014 by ParadoxDE");
    getLogger().info("Vielen Dank für die Benutzung von Broadcast von ParadoxDE");
    Bukkit.broadcastMessage(ChatColor.RED + "Broadcast by ParadoxDE wurde gestoppt");
    }
    /**
     * Possible commands:
     *  - cast
     *  - cast --h
     *  - stop
     *  - start
     * 
     * @param sender
     * @param cmd
     * @param label
     * @param args
     * @return 
     */
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        boolean returns = false;
        
        if(cmd.getName().equalsIgnoreCase("cast")) {
            
            if (!sender.hasPermission("Broadcast.allowBroadcast")) return false;
            
            for (int i = 0; i < args.length; i++) {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Moderatoren Broadcast===========");
                Bukkit.broadcastMessage(ChatColor.DARK_RED + args[i]);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Moderatoren Broadcast===========");
            }
            
            returns = true;
        }
        
        if(cmd.getName().equalsIgnoreCase("admincast")) {
            
            if (!sender.hasPermission("Broadcast.allowadminBroadcast")) return false;
            
            for (int i = 0; i < args.length; i++) {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Admin Broadcast===========");
                Bukkit.broadcastMessage(ChatColor.DARK_RED + args[i]);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Admin Broadcast===========");
            }
            
            returns = true;
        }
        
        if(cmd.getName().equalsIgnoreCase("ownercast")) {
            
            if (!sender.hasPermission("Broadcast.allowownerBroadcast")) return false;
            
            for (int i = 0; i < args.length; i++) {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Owner Broadcast===========");
                Bukkit.broadcastMessage(ChatColor.DARK_RED + args[i]);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "============Owner Broadcast===========");
            }
            
            returns = true;
        }
       
        if (cmd.getName().equalsIgnoreCase("settitle")) {
            if (!sender.hasPermission("Broadcast.setname")) return false;
            
        }
        
        return returns;
    }
    private void settitle(String name) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(config));
        writer.write("### Broadcast Config File Copyright 2014 by ParadoxDE###\n\n"
                //
                + "title::(defaultTitle)");
        writer.close();
        writer = null;
    }
   private String getTitle() throws IOException {
        String returns = "";
        
        BufferedReader reader = new BufferedReader(new FileReader(config));
        String[] s1, s2, s3;
        // Read file
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("###")) continue;
            if (line.startsWith("title::")) { /*Read title and split into parts*/
                s1 = line.split("\\{");
                s2 = s1[1].split("\\}");
                returns = s2[0];
            }
        }
        
        return returns;
    }
}
