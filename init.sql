
CREATE TABLE Tasks(
    title VARCHAR(126),
    description VARCHAR(252),
    dueDate VARCHAR(252),
    priority VARCHAR(252),
    isChecked BOOLEAN );

INSERT INTO Tasks(title, description, dueDate, priority, isChecked) VALUES
('Shopping','I need to go to the mall','' ,'',False),
('Gym','I need to get some pump','03-03-2026 14:30','High',False),
('Homework','mathematics and literature','04-04-2026 10:50','Medium',False),
('Lunch','sandwich with tone ','04-04-2026 10:50','Low',False),
('Dinner','eggs with salmon','04-04-2026 10:50','High',False);
