/*
 * JTattoo CustomizedTitlebarSample (c) 2013 by MH Software-Entwicklung
 *
 * This sample shows how to customize the window titlebar. The sample adds
 * some additional controls to the tilebar.
 */

package com.jtattoo.samples;

import com.jtattoo.plaf.BaseInternalFrameUI;
import com.jtattoo.plaf.BaseRootPaneUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 *
 * @author Michael Hagen
 */
public class CustomizedTitlebarSample extends BaseSampleFrame {
 
    // The controls for the titlebar
    private TitlebarCheckBox checkBox = null;
    private TitlebarButton button = null;
    private TitlebarMenuButton menuButton = null;
    private JPanel titlebarPanel = null;

    // The desktop pane for the internal frames
    private JDesktopPane desktopPane = null;
    private JInternalFrame internalFrame = null;

    // The controls for the titlebar
    private TitlebarCheckBox ifCheckBox = null;
    private TitlebarButton ifButton = null;
    private TitlebarMenuButton ifMenuButton = null;
    private JPanel ifTitlebarPanel = null;
    
    // Layout options 
    private JCheckBox macStyleCheckBox = null;
    private JCheckBox leftToRightCheckBox = null;
    
    public CustomizedTitlebarSample() {
        super("CustomizedTitlebarSample");
        macStyleCheckBox = new JCheckBox("macStyleWindowDecoration");
        leftToRightCheckBox = new JCheckBox("leftToRight", true);
        
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setOpaque(false);
        checkBoxPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.X_AXIS));
        checkBoxPanel.add(macStyleCheckBox);
        checkBoxPanel.add(leftToRightCheckBox);
        
        desktopPane = new JDesktopPane();
        desktopPane.setBorder(BorderFactory.createLineBorder(Color.gray));
        desktopPane.putClientProperty("backgroundTexture", com.jtattoo.samples.textures.ImageHelper.loadImage("DesktopTexture.jpg"));
        internalFrame = new JInternalFrame("InternalFrameOne", true, true, true, true);
        internalFrame.setBounds(20, 20, 480, 320);
        
        desktopPane.add(internalFrame, 0);
        internalFrame.setVisible(true);
        
        contentPanel.add(checkBoxPanel, BorderLayout.NORTH);
        contentPanel.add(desktopPane, BorderLayout.CENTER);
        
        // Setup titlebar controls
        checkBox = new TitlebarCheckBox("Check");
        button = new TitlebarButton("Button");
        menuButton = new TitlebarMenuButton("Menu");

        // We need a normal panel to place it in the titlebar.
        titlebarPanel = new JPanel(new TitlebarLayoutManager());
        // The panel should be transparent for better visual appearance.
        titlebarPanel.setOpaque(false);

        // Adding the controls to the titlebarPanel. The TitlebarLayoutManager will be responsible for the positions.
        titlebarPanel.add(checkBox);
        titlebarPanel.add(button);
        titlebarPanel.add(menuButton);

        // Same for internal frame
        ifCheckBox = new TitlebarCheckBox("ifCheck");
        ifButton = new TitlebarButton("ifButton");
        ifMenuButton = new TitlebarMenuButton("ifMenu");

        // We need a normal panel to place it in the titlebar.
        ifTitlebarPanel = new JPanel(new TitlebarLayoutManager());
        // The panel should be transparent for better visual appearance.
        ifTitlebarPanel.setOpaque(false);

        // Adding the controls to the ifTitlebarPanel. The TitlebarLayoutManager will be responsible for the positions.
        ifTitlebarPanel.add(ifCheckBox);
        ifTitlebarPanel.add(ifButton);
        ifTitlebarPanel.add(ifMenuButton);

        // Customize the titlebar. This could only be done if one of the JTattoo look and feels
        // is active. So check this first.
        if (getRootPane().getUI() instanceof BaseRootPaneUI) {
            BaseRootPaneUI rootPaneUI = (BaseRootPaneUI)getRootPane().getUI();
            // Here is the magic. Just add the panel to the titlebar
            rootPaneUI.getTitlePane().setCustomizedTitlePanel(titlebarPanel);
        }
        // Customize the internal frame titlebar. This could only be done if one of the JTattoo look and feels
        // is active. So check this first.
        if (internalFrame.getUI() instanceof BaseInternalFrameUI) {
            BaseInternalFrameUI internalFrameUI = (BaseInternalFrameUI)internalFrame.getUI();
            // Here is the magic. Just add the panel to the titlebar
            internalFrameUI.getTitlePane().setCustomizedTitlePanel(ifTitlebarPanel);
        }

        // Add listeners
        macStyleCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel();
            }
        });
        leftToRightCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (leftToRightCheckBox.isSelected()) {
                    applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                } else {
                    applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                }
                validate();
                repaint();
            }
        });
        
        checkBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "CheckBox clicked");
            }
        });
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "Button clicked");
            }
        });
        ifCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "ifCheckBox clicked");
            }
        });
        ifButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "ifButton clicked");
            }
        });

    }

    public void selectInternalFrame() {
        // Activate the internal frame window
        try {
            internalFrame.setSelected(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Properties getLAFProps() {
        Properties props =  super.getLAFProps();
        props.put("macStyleWindowDecoration", macStyleCheckBox.isSelected() ? "on" : "off");
        return props;
    }
   

//------------------------------------------------------------------------------
// inner classes for the title bar controls
//------------------------------------------------------------------------------
    public class TitlebarCheckBox extends JCheckBox {

        public TitlebarCheckBox(String title) {
            super(title);
            // Foreground color should be the same as the window title color
            setForeground(UIManager.getColor("activeCaptionText"));
            setOpaque(false);
            setFocusable(false);
        }

        @Override
        public void updateUI() {
            super.updateUI();
            // update the foreground color if look and feel changes
            setForeground(UIManager.getColor("activeCaptionText"));
        }
        
    } // end TitlebarCheckBox

//------------------------------------------------------------------------------
    public class TitlebarButton extends JButton {

        public TitlebarButton(String title) {
            super(title);
            // Setup the button. We just want a square border with color of the window border.
            setFocusable(false);
            Border innerBorder = BorderFactory.createEmptyBorder(2, 4, 2, 4);
            Border outerBorder = BorderFactory.createLineBorder(UIManager.getColor("activeCaptionBorder"));
            setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        }

        @Override
        public void updateUI() {
            super.updateUI();
            // update the border if look and feel changes
            Border innerBorder = BorderFactory.createEmptyBorder(2, 4, 2, 4);
            Border outerBorder = BorderFactory.createLineBorder(UIManager.getColor("activeCaptionBorder"));
            setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        }
        
    } // end TitlebarButton

//------------------------------------------------------------------------------
    public class TitlebarMenuButton extends JButton {

        public TitlebarMenuButton(String title) {
            super(title, com.jtattoo.samples.icons.ImageHelper.loadImage("DownArrow.gif"));

            // Setup the menu button
            setHorizontalAlignment(SwingConstants.CENTER);
            setHorizontalTextPosition(SwingConstants.LEFT);
            // Foreground color should be the same as the window title color
            setForeground(UIManager.getColor("activeCaptionText"));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusable(false);

            // Add listeners
            addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    setForeground(Color.black);
                    setContentAreaFilled(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setForeground(UIManager.getColor("activeCaptionText"));
                    setContentAreaFilled(false);
                }
            });
            addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    showPopup();
                }
            });
        }

        @Override
        public void updateUI() {
            super.updateUI();
            // update the foreground color if look and feel changes
            setForeground(UIManager.getColor("activeCaptionText"));
        }

        private void showPopup() {
            // create a popup menu
            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem menuItem = new JMenuItem("Menu #1");
            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "Menu #1 clicked");
                }
            });
            popupMenu.add(menuItem);
            menuItem = new JMenuItem("Menu #2");
            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "Menu #2 clicked");
                }
            });
            popupMenu.add(menuItem);
            menuItem = new JMenuItem("Menu #3");
            menuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(CustomizedTitlebarSample.this, "Menu #3 clicked");
                }
            });
            popupMenu.add(menuItem);
            
            // honor the component orientation 
            if (leftToRightCheckBox.isSelected()) {
                popupMenu.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            } else {
                popupMenu.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            }
            // show the popup
            popupMenu.show(this, 0, getHeight() - 1);
        }

    } // end TitlebarMenuButton

//------------------------------------------------------------------------------
    /**
     * A special layout manager for the custom title panel.
     */
    public class TitlebarLayoutManager implements LayoutManager {
        
        private Dimension preferredSize = null;

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            layoutContainer(parent);
            return preferredSize;
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return parent.getMinimumSize();
        }

        @Override
        public void layoutContainer(Container parent) {
            // Calculate the new positions of the titlebar controls and the preferred size of the panel
            int width = 0;
            int count = parent.getComponentCount();
            int w[] = new int[count];
            for (int i = 0; i < count; i++) {
                Component c = parent.getComponent(i);
                if (c.isVisible()) {
                    w[i] = c.getPreferredSize().width;
                    width += w[i];
                    width += 4;
                }
            }
            if (preferredSize == null) {
                preferredSize = new Dimension(width, parent.getHeight());
            } else {
                preferredSize.width = width;
                preferredSize.height = parent.getHeight();
            }
            int x = parent.getWidth() - width;
            if (!parent.getComponentOrientation().isLeftToRight()) {
                x = 0;
            }
            int y = 0;
            int h = parent.getHeight() - 2;
            for (int i = 0; i < count; i++) {
                Component c = parent.getComponent(i);
                if (c.isVisible()) {
                    c.setBounds(x, y, w[i], h);
                    x += w[i] + 4;
                }
            }
        }
    } // end TitlebarLayoutManager

    
    
//------------------------------------------------------------------------------    
    public static void main(String[] args) {
//------------------------------------------------------------------------------    
        try {
            // Select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    // Start the application
                    CustomizedTitlebarSample app = new CustomizedTitlebarSample();
                    app.setSize(800, 600);
                    app.setLocationRelativeTo(null);
                    app.setVisible(true);
                    app.selectInternalFrame();
                }
            });
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // end main


}
