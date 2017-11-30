package forms.base;

import objects.ValidationObject;

import javax.swing.*;

public abstract class InputForm {
    protected Object object;
    protected JFrame frame;

    /**
     * Used to initialise the frame and ActionListeners for the buttons provided.
     */
    protected void initialise(Object object, JPanel mainPanel, JButton saveButton, JButton cancelButton) {
        frame = new JFrame(getTitle());
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        if (saveButton != null) {
            saveButton.addActionListener(e -> saveAction());
        }
        if (cancelButton != null) {
            cancelButton.addActionListener(e -> frame.dispose());
        }

        this.object = object;

        initialiseComponents();
    }

    /**
     * Initialises the components on the InputForm.
     */
    protected abstract void initialiseComponents();

    /**
     * Gets the title to use in the JFrame of the InputForm.
     *
     * @return the title to use
     */
    protected abstract String getTitle();

    /**
     * The action that is called when the save button is clicked.
     */
    protected abstract void saveAction();

    /**
     * Checks the input on the InputForm to see if it is valid.
     *
     * @return if there is a validation error and the message that comes with it
     */
    protected abstract ValidationObject validateInput();
}
