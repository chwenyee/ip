package luke;

import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) throws LukeException {
        if (tasks.size() >= 100) {
            throw new LukeException("You have reach the task limit of 100!" + System.lineSeparator()
                    +  "Please delete some tasks before adding new ones.");
        }
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            // Check if the task's description contains the keyword. Case is ignored
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

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

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
