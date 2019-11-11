package commands;

import commons.Storage;
import commons.UserInteraction;
import tasks.TaskList;

/**
 * Represents the command to end the program.
 */
public class ByeCommand extends Command {

    /**
     * Executes the updating of the file with current list of tasks
     * in the TaskList object and displays the goodbye message
     * of the program.
     * @param events The TaskList object for events
     * @param deadlines The TaskList object for deadlines
     * @param ui The Ui object to display the goodbye message
     * @param storage The Storage object to access file to load or save the tasks
     * @return This returns the method in the Ui object which returns the string to display goodbye message
     */
    @Override
    public String execute(TaskList events, TaskList deadlines, UserInteraction ui, Storage storage) {
        return ui.showBye();
    }
}
