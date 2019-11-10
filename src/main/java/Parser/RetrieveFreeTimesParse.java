package Parser;

import Commands.Command;
import Commands.FindFreeTimesCommand;
import Commands.RetrieveFreeTimesCommand;
import Commons.DukeConstants;
import Commons.DukeLogger;
import DukeExceptions.DukeInvalidFormatException;
import DukeExceptions.DukeNoValidDataException;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class parses the full command that calls for RetrieveFreeTimesParse.
 */
public class RetrieveFreeTimesParse extends Parse {
    private final Logger LOGGER = DukeLogger.getLogger(FindFreeTimesParse.class);
    private String fullCommand;


    /**
     * Creates RetrieveFreeTimesParse object.
     * @param fullCommand The input by the user
     */
    public RetrieveFreeTimesParse(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public Command parse() throws DukeInvalidFormatException, DukeNoValidDataException {
        ArrayList<Pair<String, String>> retrievedFreeTimes = FindFreeTimesCommand.getCompiledFreeTimesList();
        if (checkIsEmpty(retrievedFreeTimes)) {
            throw new DukeNoValidDataException(DukeConstants.INVALID_NO_FREE_TIME_FOUND);
        }
        fullCommand = fullCommand.replaceFirst(DukeConstants.RETRIEVE_TIME_HEADER, "");
        fullCommand = fullCommand.trim();
        if (fullCommand.isEmpty()) {
            throw new DukeInvalidFormatException(DukeConstants.INVALID_EMPTY_OPTION);
        } else {
            try {
                Integer option = Integer.parseInt(fullCommand);
                if (option >= DukeConstants.RETRIEVE_TIME_LOWER_BOUNDARY
                        && option <= DukeConstants.RETRIEVE_TIME_UPPER_BOUNDARY) {
                    return new RetrieveFreeTimesCommand(option);
                } else {
                    throw new DukeInvalidFormatException(DukeConstants.INVALID_OPTION);
                }
            } catch (NumberFormatException e) {
                LOGGER.info("Unable to parse string to integer" + e.getMessage());
                throw new DukeInvalidFormatException(DukeConstants.INVALID_OPTION);
            }
        }
    }

    /**
     * This method checks if the option is valid.
     * @param input The retrieveFreeTimesCommand input
     * @return true if it is valid. Otherwise, false
     */
    public static boolean isValidOption(String input) {
        input = input.replace(DukeConstants.RETRIEVE_TIME_HEADER, "");
        input = input.trim();
        if (input.isEmpty()) {
            return false;
        } else {
            try {
                Integer option = Integer.parseInt(input);
                if (option >= DukeConstants.RETRIEVE_TIME_LOWER_BOUNDARY
                        && option <= DukeConstants.RETRIEVE_TIME_UPPER_BOUNDARY) {
                    return true;
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    private boolean checkIsEmpty(ArrayList<Pair<String, String>> retrievedFreeTimes) {
        return (retrievedFreeTimes.size() == 0) ? true : false;
    }
}

