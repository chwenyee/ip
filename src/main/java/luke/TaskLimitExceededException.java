package luke;

class TaskLimitExceededException extends LukeException {

    public TaskLimitExceededException() {
        super("You have reached the maximum number of tasks of 100!");
    }

}