package luke;

import luke.command.Command;
import java.io.FileNotFoundException;


public class Luke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

//    private static void processTasks(String input, ArrayList<Task> tasks) throws IndexOutOfBoundsException, TaskLimitExceededException,
//            EmptyTaskDescriptionException, InvalidCommandException {
//
//        if (input.equals("list")) {
//            printTaskList(tasks);
//
//        } else if (input.startsWith("mark")) {
//            // -1 because the first task starts with index 0
//            int taskIndex = Integer.parseInt(input.substring(4).trim()) - 1;
//
//            if (taskIndex < 0 || taskIndex >= Task.getTaskCount()) {
//                throw new IndexOutOfBoundsException();
//            }
//
//            showMarkTaskAsDone(tasks, taskIndex);
//
//        } else if (input.startsWith("unmark")) {
//            int taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;
//
//            if (taskIndex < 0 || taskIndex >= Task.getTaskCount()) {
//                throw new IndexOutOfBoundsException();
//            }
//
//            showMarkTaskAsNotDone(tasks, taskIndex);
//
//        } else if (input.startsWith("delete")) {
//            int taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;
//
//            if (taskIndex < 0 || taskIndex > Task.getTaskCount()) {
//                throw new IndexOutOfBoundsException();
//            }
//
//            Task removed = tasks.remove(taskIndex);
//            showTaskRemoved(removed);
//
//        } else if (input.startsWith("deadline")) {
//            // Check if max limit is reached
//            if (Task.getTaskCount() >= 100) {
//                throw new TaskLimitExceededException();
//            }
//
//            if (input.trim().equals("deadline")) {
//                throw new EmptyTaskDescriptionException();
//            }
//
//            String taskDescription = filterInput(input, "/by", "taskDescription");
//            String deadline = filterInput(input, "/by", "deadline");
//
//            if (taskDescription.isEmpty() || deadline.isEmpty()) {
//                throw new EmptyTaskDescriptionException();
//            }
//
//            tasks.add(new Deadline(taskDescription, deadline, false));
//            showTaskAdded(tasks);
//
//        } else if (input.startsWith("event")) {
//            if (Task.getTaskCount() >= 100) { // Check if max limit is reached
//                throw new TaskLimitExceededException();
//            }
//
//            if (input.trim().equals("event")) {
//                throw new EmptyTaskDescriptionException();
//            }
//
//            String taskDescription = filterInput(input, "/from", "taskDescription");
//            String startDate = filterInput(input, "/from", "eventStartDate");
//            String endDate = filterInput(input, "/to", "eventEndDate");
//
//            if (taskDescription.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
//                throw new EmptyTaskDescriptionException();
//            }
//
//            tasks.add(new Event(taskDescription, startDate, endDate, false));
//            showTaskAdded(tasks);
//
//        } else if (input.startsWith("todo")) {
//            // Check if max limit is reached
//            if (Task.getTaskCount() >= 100) {
//                throw new TaskLimitExceededException();
//            }
//
//            String todoDescription = input.substring(4).trim();
//
//            if (todoDescription.isEmpty()) {
//                throw new EmptyTaskDescriptionException();
//            }
//
//            tasks.add(new Todo(todoDescription, false));
//            showTaskAdded(tasks);
//
//        } else {
//            throw new InvalidCommandException();
//        }
//    }
//

    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(Storage.load());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.showGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LukeException e) {
                ui.showError("I'm sorry, but I don't know what that means :'(");
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Luke("data/tasks.txt").run();
    }

}
