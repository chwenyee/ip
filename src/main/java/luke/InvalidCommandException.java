package luke;

class InvalidCommandException extends LukeException {

    public InvalidCommandException() {
        super("Sorry, I don't know what I am supposed to do. :(");
    }

}