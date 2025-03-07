# Luke 

## Features
### Adding a Todo: todo 
Adds a todo task to the task list.

Format: todo DESCRIPTION
Example: todo revise CS2113 OOP

### Adding a Deadline: deadline
Adds a deadline task to the task list.

Format: deadline DESCRIPTION /by DEADLINE_DATE_AND_TIME
- DEADLINE_DATE_AND_TIME must follow this format: yyyy-MM-dd HHmm, where yyyy is year, MM is month,
  dd is day, and HHmm is time in 24-hour format

Example: deadline submit CS2113 iP /by 2025-03-14 1600

### Adding an Event: event 
Adds an event to the task list.

Format: event DESCRIPTION /from START_DATE_AND_TIME /to END_DATE_AND_TIME
- START_DATE_AND_TIME and END_DATE_AND_TIME must follow this format: yyyy-MM-dd HHmm, where yyyy is year, MM is month,
  dd is day, and HHmm is time in 24-hour format

Example: event orientation /from 2025-03-07 1000 /to 2025-03-07 2000

### Listing all tasks: list
Shows a list of all tasks in the task list.

Format: list

### Marking a task as done: mark
Marks a specified task as done in the task list.

Format: mark INDEX
- Mark the task as done at the specified INDEX
- The index refers to the index number shown in the displayed task list by command list
- The index must be a positive integer 1, 2, 3, ...

Example: list followed by mark 1 marks the 1st task as done in the task list.

### Marking a task as not done: unmark
Marks a specified task as not done in the task list.

Format: unmark INDEX
- Mark the task as not done at the specified INDEX
- The index refers to the index number shown in the displayed task list by command list
- The index must be a positive integer 1, 2, 3, ...

Example: list followed by unmark 3 marks the 3rd task as not done in the task list.

### Deleting a task: delete
Delete a specified task from the task list.

Format: delete INDEX
- Deletes the task at the specified INDEX
- The index refers to the index number shown in the displayed task list by command list
- The index must be a positive integer 1, 2, 3, ...

Example: list followed by delete 1 deletes the 1st task in the task list.

### Finding tasks by keyword: find
Finds tasks which descriptions contain the given keyword.

Format: find KEYWORD
- The search is case-insensitive. e.g. book will match Book
- Only the task description is searched.
- Partial word matches are allowed e.g. bo will match book
- Tasks matching the keyword will be returned. e.g. book will return borrow book, buy exercise book
- If there is no matching task, a message indicating zero result will be displayed.

Example: find book returns read book and buy book

### Finding tasks by date: findDate
Finds tasks with the specified date.

Format: find DATE
- DATE must follow this format: yyyy-MM-dd, where yyyy is year, MM is month, dd is day
- Todo will not be searched as it does not contain any date.
- For deadline, its DEADLINE_DATE_AND_TIME is searched.
- For event, its START_DATE_AND_TIME and END_DATE_AND_TIME are searched.
- Tasks matching the date will be returned, regardless of the time of the specified date  

Example: findDate 2025-03-07 returns all tasks containing date 2025-03-07

### Exiting the program: bye
Exits the program. 

Format: bye

### Saving the data
Luke data, i.e. tasks in the task list, are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.

### Editing the data file
Luke data, i.e. tasks in the task list, are saved automatically as a text file 
[the file directory you are at]/data/tasks.txt. 
Advanced users are welcome to update data directly by editing that data file. 

> [!CAUTION]
> If your changes to the data file makes its format invalid, Luke will ignore that row of data and proceed to 
> load the next row of data. As a result, the task list will not contain that invalid task.
> Therefore, edit the data file only if you are confident that you can update it correctly.