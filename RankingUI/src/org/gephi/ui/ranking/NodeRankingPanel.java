/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.ui.ranking;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.gephi.ranking.RankingUIModel;
import org.openide.util.Exceptions;

/**
 *
 * @author Mathieu Bastian
 */
public class NodeRankingPanel extends javax.swing.JPanel {

    private JPanel contentPanel;
    private final RankingUIModel model;
    private TransformerUI[] transformerUIs;

    public NodeRankingPanel(RankingUIModel model) {
        this.model = model;
        initComponents();
        initTransformers();
    }

    private void initTransformers() {
        transformerUIs = new TransformerUI[4];
        TransformerUI colorTransformerUI = new TransformerUI("color.png", "ColorTransformerUI.tooltip", ColorTransformerPanel.class, RankingUIModel.COLOR_TRANSFORMER);
        tranformersPanel.add(colorTransformerUI.getButton());
        transformerUIs[0] = colorTransformerUI;
        TransformerUI sizeTransformerUI = new TransformerUI("size.png", "SizeTransformerUI.tooltip", SizeTransformerPanel.class, RankingUIModel.SIZE_TRANSFORMER);
        tranformersPanel.add(sizeTransformerUI.getButton());
        transformerUIs[1] = sizeTransformerUI;
        TransformerUI labelColorTransformerUI = new TransformerUI("labelcolor.png", "LabelColorTransformerUI.tooltip", ColorTransformerPanel.class, RankingUIModel.LABEL_COLOR_TRANSFORMER);
        tranformersPanel.add(labelColorTransformerUI.getButton());
        transformerUIs[2] = labelColorTransformerUI;
        TransformerUI labelSizeTransformerUI = new TransformerUI("labelsize.png", "LabelSizeTransformerUI.tooltip", SizeTransformerPanel.class, RankingUIModel.LABEL_SIZE_TRANSFORMER);
        tranformersPanel.add(labelSizeTransformerUI.getButton());
        transformerUIs[3] = labelSizeTransformerUI;
    }

    private void refreshContentPanel() {
        if (contentPanel != null) {
            remove(contentPanel);
        }
        for (TransformerUI transformerUI : transformerUIs) {
            if (model.getNodeTransformer() == transformerUI.transformer) {
                contentPanel = transformerUI.getContentPanel();
                add(contentPanel, BorderLayout.CENTER);
                transformerGroup.setSelected(transformerUI.button.getModel(), true);
            }
        }
        revalidate();
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        transformerGroup = new javax.swing.ButtonGroup();
        transformerButtons = new javax.swing.JPanel();
        rankComboBox = new javax.swing.JComboBox();
        tranformersPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        transformerButtons.setLayout(new java.awt.GridBagLayout());

        rankComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 4, 6, 8);
        transformerButtons.add(rankComboBox, gridBagConstraints);

        tranformersPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 4);
        transformerButtons.add(tranformersPanel, gridBagConstraints);

        add(transformerButtons, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox rankComboBox;
    private javax.swing.JPanel tranformersPanel;
    private javax.swing.JPanel transformerButtons;
    private javax.swing.ButtonGroup transformerGroup;
    // End of variables declaration//GEN-END:variables

    private class TransformerUI {

        private JToggleButton button;
        private Class<JPanel> contentPanel;
        private int transformer;

        public TransformerUI(String iconFile, String tooltipText, Class contentPanel, int transformerID) {
            button = new JToggleButton();
            button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gephi/ui/ranking/resources/" + iconFile)));
            button.setToolTipText(org.openide.util.NbBundle.getMessage(NodeRankingPanel.class, tooltipText));
            button.setMargin(new java.awt.Insets(2, 4, 2, 4));
            button.setRolloverEnabled(true);
            button.setFocusPainted(false);
            transformerGroup.add(button);
            this.contentPanel = contentPanel;
            this.transformer = transformerID;
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (button.isSelected()) {
                        model.setNodeTransformer(transformer);
                        refreshContentPanel();
                    }
                }
            });
        }

        public JPanel getContentPanel() {
            try {
                return contentPanel.newInstance();
            } catch (InstantiationException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
            return null;
        }

        public int getTransformer() {
            return transformer;
        }

        public JToggleButton getButton() {
            return button;
        }
    }
}