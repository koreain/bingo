/*
 * JTattoo AluminiumScrollPaneSample (c) 2013 by MH Software-Entwicklung
 *
 * This sample shows what to do to avoid background painting problems within scrollpanes if a look and
 * feel with background texture is selected.
 */

package com.jtattoo.samples;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Michael Hagen
 */
public class AluminiumScrollPaneSample extends BaseSampleFrame {
    
    public AluminiumScrollPaneSample() {
        super("AluminiumScrollPaneSample");
        
        // Setup the controls
        ScrollPaneSamplePanel samplePanel = new ScrollPaneSamplePanel();
        // Add the textarea panel to a scrollpane
        JScrollPane scrollPane = new JScrollPane(samplePanel);
       
        // Set samplePanel, scrollPane and the associated viewport to none opaque to avoid painting problems.
        samplePanel.setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        
    }
    
//------------------------------------------------------------------------------    
    public static void main(String[] args) {
//------------------------------------------------------------------------------    
        try {
            
            // Select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    // Start the application
                    AluminiumScrollPaneSample app = new AluminiumScrollPaneSample();
                    app.setSize(600, 400);
                    app.setLocationRelativeTo(null);
                    app.setVisible(true);
                }
            });
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main
    
}
