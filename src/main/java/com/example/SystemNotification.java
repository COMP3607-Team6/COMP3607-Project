package com.example;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

public class SystemNotification {

    public String message;

    public SystemNotification(String message){
        this.message = message;
        callNotification();
    }

    public void callNotification(){

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

            trayIcon.displayMessage("Title", message, TrayIcon.MessageType.INFO);
        } else {
            System.out.println("SystemTray is not supported");
        }
    }
}
