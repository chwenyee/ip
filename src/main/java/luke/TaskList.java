package luke;

import luke.exception.LukeException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a list of tasks. It provides methods to add, delete, mark, unmark and search.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with a given set of tasks.
     *
     * @param tasks The initial list of tasks to be stored in the TaskList..
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     * @throws LukeException If the task limit (100 tasks) is exceeded.
     */
    public void addTask(Task task) throws LukeException {
        if (tasks.size() >= 100) {
            throw new LukeException("You have reach the task limit of 100!" + System.lineSeparator()
                    +  "Please delete some tasks before adding new ones.");
        }
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on the given index.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done based on the given index.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Marks a task as not done based on the given index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for (case-insensitive).
     * @return A list of tasks that contain the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            // Ignore the case in searching process by making both description and keyword lower case.
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Finds tasks that are associated with a specific date.
     * Tasks found are either deadlines or events.
     *
     * @param date The date to search for. The date must follow the input format.
     * @return A list of tasks occurring on the specified date. If no tasks match the date, an empty list is returned.
     */
    public ArrayList<Task> findTasksByDate(LocalDate date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                if (((Deadline) task).getBy().toLocalDate().equals(date)) {
                    matchingTasks.add(task);
                }
            } else if (task instanceof Event) {
                if (((Event) task).getFrom().toLocalDate().equals(date) || ((Event) task).getTo().toLocalDate().equals(date)) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks;
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
