import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String title;
    private String description;
    private LocalDateTime dueDate = null;
    private Priority priority;
    boolean isChecked = false;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public String toString() {
        return String.format("\nTitle:%s\nDescription:%s\nDueDate:%s\nPriority:%s\nIsChecked:%b\n", this.title,
                this.description, this.dueDate, this.priority, this.isChecked);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        if (!dueDate.isEmpty() && !dueDate.equals("null")) {
            this.dueDate = LocalDateTime.parse(dueDate, formatter);
        }
        else{
            this.dueDate = null;
        }
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public void setPriority(String priority) {
        try {
            this.priority = Priority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {

            this.priority = Priority.MEDIUM;
        }
    }

    public boolean getIsChecked() {return this.isChecked;}
    public String getDescription(){return this.description;}
    public Priority getPriority(){
        return this.priority;
    }
    public LocalDateTime getDueDate(){
        return this.dueDate;
    }
    public String getTitle(){return this.title;}



    public void markTask() {
        this.isChecked = true;
    }

}
