package com.example;
import java.awt.*;

public class SystemNotification {
    public static void main(String[] args) {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image icon = Toolkit.getDefaultToolkit().getImage("path_to_icon/icon.png"); // Replace with your icon path

            PopupMenu menu = new PopupMenu();
            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(e -> System.exit(0));
            menu.add(exitItem);

            TrayIcon trayIcon = new TrayIcon(icon, "System Notification", menu);
            trayIcon.setImageAutoSize(true);

            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.out.println("TrayIcon could not be added.");
            }

            trayIcon.displayMessage("Title", "Your waste of time assignment corrections is done!!!", TrayIcon.MessageType.INFO);
        } else {
            System.out.println("SystemTray is not supported");
        }
    }
}
