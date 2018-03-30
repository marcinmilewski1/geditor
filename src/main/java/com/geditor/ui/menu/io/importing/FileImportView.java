package com.geditor.ui.menu.io.importing;

import com.geditor.io.importer.FileImporter;
import com.geditor.io.importer.exception.ImportFileException;
import com.geditor.io.importer.exception.InvalidExtensionException;
import com.geditor.io.importer.factory.FileImporterFactory;
import com.geditor.io.util.FileExtension;
import com.geditor.ui.editor.Editor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by marcin on 19.03.16.
 */
public class FileImportView extends JPanel
        implements ActionListener {
    static private final String newline = "\n";
    JButton openButton;
    JTextArea actionHistory;
    JFileChooser fileChooser;
    Editor editor;

    public FileImportView(Editor editor) {
        super(new BorderLayout());

        this.editor = editor;
        actionHistory = new JTextArea(5, 20);
        actionHistory.setMargin(new Insets(5, 5, 5, 5));
        actionHistory.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(actionHistory);

        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("ppmp3", "ppmp3"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("ppmp6", "ppmp6"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
        openButton = new JButton("Open a File...");
        openButton.addActionListener(this);


        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(openButton);

        //Add the buttons and the actionHistory to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == openButton) {
            int returnVal = fileChooser.showOpenDialog(FileImportView.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                FileExtension fileExtension = FileExtension.valueOfIgnoreCase(file.getName().substring(file.getName().lastIndexOf('.') + 1));
                actionHistory.append("Opening: " + file.getName() + "." + newline);
                try {
                    FileImporter fileImporter = FileImporterFactory.getFileImporter(fileExtension);
                    BufferedImage importedImage = fileImporter.importImage(file);
                    editor.setImage(importedImage);
                } catch (InvalidExtensionException e1) {
                    actionHistory.append("File extension incorrect");
                } catch (ImportFileException e1) {
                    e1.printStackTrace();
                    e1.printStackTrace();
                    actionHistory.append("Error, file incorrect");
                }
            } else {
                actionHistory.append("Open command cancelled by user." + newline);
            }
            actionHistory.setCaretPosition(actionHistory.getDocument().getLength());
        }
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FileImportView.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}