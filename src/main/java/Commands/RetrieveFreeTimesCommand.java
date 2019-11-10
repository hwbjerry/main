package Commands;

import Commons.Storage;
import Commons.UserInteraction;
import Tasks.TaskList;
import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Represents the command to retrieve the free time requested by the user.
 */
public class RetrieveFreeTimesCommand extends Command {
    private Integer option;
    private static String selectedOption;
    private static String selectedOptionCommand;

    public RetrieveFreeTimesCommand(Integer option) {
        this.option = option;
    }

    /**
     * Executes the retriever of earliest available block period generated by FindFreeTimesCommand.
     * @param events The TaskList object for events
     * @param deadlines The TaskList object for deadlines
     * @param ui The Ui object to display the done task message
     * @param storage The Storage object to access file to load or save the tasks
     * @return This returns the method in the Ui object which returns the string to display freeTimes message
     */
    @Override
    public String execute(TaskList events, TaskList deadlines, UserInteraction ui, Storage storage) {
        ArrayList<Pair<String, String>> retrievedFreeTimes = FindFreeTimesCommand.getCompiledFreeTimesList();
        Integer indexOfOption = option - 1;
        selectedOption = retrievedFreeTimes.get(indexOfOption).getKey();
        selectedOptionCommand = retrievedFreeTimes.get(indexOfOption).getValue();
        return ui.showSelectionOption(option, selectedOption);
    }

    /**
     * This method gets the earliest free time option selected by the user.
     */
    public static String getSelectedOption() {
        return selectedOptionCommand;
    }
}
