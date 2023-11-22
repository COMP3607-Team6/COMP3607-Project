package com.example;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;

/**
 * Class creates system notification upon the creation of an instance
*/
public class SystemNotification {

    public String message;
    public String filePath;

    public SystemNotification(){
        
    }

    public SystemNotification(String message){
        this.message = message;
        callNotification();
    }

    public SystemNotification(String message,String filePath){
        this.message = message;
        callNotification();
        openFolderInExplorer(filePath);
    }

    /**
    * Calls  System notification
    */
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

    /**
     * Opens file explorer  when given a specified path
    */
     public void openFolderInExplorer(String folderPath) {
        File folder = new File(folderPath);

        if (Desktop.isDesktopSupported() && folder.exists()) {
            try {
                Desktop.getDesktop().open(folder);
            } catch (IOException e) {
                System.out.println("Error opening folder: " + e.getMessage());
            }
        } else {
            System.out.println("Folder does not exist or is not supported.");
        }
    }

    // public static void main(String[] args) {
    //     SystemNotification s = new SystemNotification("This is a test", STUDENT_SUBMISSIONS_FOLDER);
    // }

}


