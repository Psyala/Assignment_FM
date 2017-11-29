package forms.base;

import objects.ValidationObject;

import javax.swing.*;

public abstract class InputForm {
    protected Object object;
    protected JFrame frame;

    protected void initialise(Object object, JPanel mainPanel, JButton saveButton, JButton cancelButton) {
        frame = new JFrame(getTitle());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        saveButton.addActionListener(e -> saveAction());
        cancelButton.addActionListener(e -> frame.dispose());

        this.object = object;

        initialiseComponents();
    }

    protected abstract void initialiseComponents();

    protected abstract String getTitle();

    protected abstract void saveAction();

    protected abstract ValidationObject validateInput();
}
