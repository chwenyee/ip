package luke;

class EmptyTaskDescriptionException extends LukeException {

    public EmptyTaskDescriptionException() {
        super("Hey, I will only execute this task when there is description!");
    }

}